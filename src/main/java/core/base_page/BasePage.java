package core.base_page;

import core.driver.DriverHolder;
import core.exceptions.ConfigurationException;
import core.exceptions.InvalidPageException;
import core.exceptions.UnassignedPageConditionException;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class BasePage<T extends BasePage<T>> implements URLProvider {

    private static final String SUPPRESSED_VALUE = "*****";

    protected Logger log = LoggerFactory.getLogger(getClass());

    //region ===== Working with WebDriver instance =====
    protected static WebDriver getDriver() {
        return DriverHolder.getDriver();
    }
    //endregion

    //region ===== Opening pages =====
    private String baseUrl;
    private String pagePath;

    protected <T extends BasePage<T>> T createPage(Class<T> pageClass) {
        return PageFactory.createPage(pageClass);
    }

    @Override
    public String getURL() {
        log.debug("About to get page URL");
        if (getBaseUrl() == null || getPagePath() == null) {
            log.error("baseUrl or pagePath is not specified");
            throw new ConfigurationException("baseUrl or pagePath is not specified");
        }
        return getBaseUrl() + getPagePath();
    }

    private String getBaseUrl() {
        return baseUrl;
    }

    protected void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private String getPagePath() {
        return pagePath;
    }

    protected void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    protected String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
    //endregion


    //region ===== Waiting elements and page conditions =====
    private int defaultTimeOutInSeconds = 15;
    private WebDriverWait wait = null;

    private WebDriverWait getWait() {
        if (wait == null) wait = new WebDriverWait(getDriver(), defaultTimeOutInSeconds);
        return wait;
    }

    private WebElement waitElement(By by) {
        log.debug("Wait when element " + by.toString() + " is present");
        return getWait()
                .withTimeout(Duration.ofSeconds(defaultTimeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitElement(ExpectedCondition<WebElement> condition) {
        log.debug("Wait condition {}", condition.toString());
        return getWait()
                .withTimeout(Duration.ofSeconds(defaultTimeOutInSeconds))
                .until(condition);
    }

    @SuppressWarnings("unchecked")
    private T waitCondition(ExpectedCondition<WebElement> condition) {
        log.debug("Wait condition {}", condition.toString());
        getWait().withTimeout(Duration.ofSeconds(defaultTimeOutInSeconds))
                .until(condition);
        return (T) this;
    }
    //endregion

    //region ===== Working with Web elements =====
    protected WebElement findElement(By by) {
        log.debug("Find Web Element {}", by.toString());
        return waitElement(by);
    }

    protected List<WebElement> findElements(By by) {
        log.debug("Find Web Elements {}", by.toString());
        return waitElement(by).findElements(by);
    }

    protected T enterValue(By by, String value) {
        return enterValue(by, value, true);
    }

    @SuppressWarnings("unchecked")
    protected T enterValue(By by, String value, boolean putValueToLog) {
        log.debug("Enter value {} for {}", (putValueToLog ? value : SUPPRESSED_VALUE), by.toString());
        WebElement element = waitElement(ExpectedConditions.visibilityOfElementLocated(by));
        element.clear();
        element.sendKeys(value);
        return (T) this;
    }

    protected String getText(By by) {
        log.debug("Get text for {}", by.toString());
        return findElement(by).getText();
    }

    protected boolean isDisplayed(By by) {
        log.debug("Check if {} is displayed", by.toString());
        return findElement(by).isDisplayed();
    }

    @SuppressWarnings("unchecked")
    protected T click(By by) {
        log.debug("Click {}", by.toString());
        waitElement(ExpectedConditions.elementToBeClickable(by)).click();
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T click(ExpectedCondition<WebElement> condition) {
        log.debug("Click {}", condition.toString());
        waitElement(condition).click();
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T moveToElementAndClick(WebElement element) {
        log.debug("Move to element and click {}", element.getText());
        Actions actions = new Actions(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", element);
        actions.moveToElement(element).click(element).perform();
        return (T) this;
    }

    private Select getSelect(By by) {
        log.debug("Get Select {}", by.toString());
        return new Select(waitElement(by));
    }

    @SuppressWarnings("unchecked")
    protected T selectByValue(By by, String value) {
        log.debug("Select " + by.toString() + " by value");
        getSelect(by).selectByValue(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T selectByIndex(By by, int index) {
        log.debug("Select item {} by index {}", by.toString(), index);
        getSelect(by).selectByIndex(index);
        return (T) this;
    }
    //endregion

    //region ===== Right page validation =====
    private ExpectedCondition<WebElement> rightPageCondition;

    @SuppressWarnings("unchecked")
    protected T setRightPageCondition(ExpectedCondition<WebElement> rightPageCondition) {
        this.rightPageCondition = rightPageCondition;
        return (T) this;
    }

    /**
     * protected modifier is used here to show that it ca be overridden in child page
     * to enable them to implement their own logic of validating the page
     */
    protected boolean isRightPage() {
        log.debug("Check if {} is right", rightPageCondition.toString());
        if (rightPageCondition == null) {
            log.error("Call isRightPage() requires the rightPageCondition, but it's not assigned");
            throw new UnassignedPageConditionException("Call isRightPage() requires the rightPageCondition, but it's not assigned");
        } else {
            try {
                waitCondition(rightPageCondition);
                return true;
            } catch (TimeoutException e) {
                return false;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Step("Validate if the page is right")
    public T validateIsRightPage() {
        log.debug("Validate if the page is right");
        if (!isRightPage()) {
            log.error("The page is not right");
            throw new InvalidPageException(makeMessage());
        }
        log.debug("The page is right");
        return (T) this;
    }

    private String makeMessage() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("Current page is not '")
                .append(this.getClass().getSimpleName())
                .append("' since the rightPageCondition has not match\n")
                .append("rightPageCondition: ")
                .append(rightPageCondition.toString())
                .append("\ncurrent url: ")
                .append(getDriver().getCurrentUrl())
                .append("\n");
        return sb.toString();
    }
    //endregion
}
