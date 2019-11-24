package core.base_test;

import core.base_page.BasePage;
import core.base_page.PageFactory;
import core.config.ConfigReader;
import core.driver.DriverFactory;
import core.driver.DriverHolder;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {

    protected Logger log = LoggerFactory.getLogger(getClass());

    static {
        ConfigReader.readConfig();
    }

    //region ===== Working with WebDriver instance, initialize and tearDown =====
    @BeforeMethod
    protected void initializeWebDriver() {
        log.info("Initialize WebDriver");
        DriverHolder.setDriver(DriverFactory.createNewWebDriverInstance());
    }

    @AfterMethod
    protected void tearDownDriver() {
        log.info("Tear down WebDriver");
        if (DriverHolder.isDriverCreated()) {
            DriverHolder.getDriver().quit();
            DriverHolder.removeDriver();
        }
    }
    //endregion

    //region ===== Opening pages =====
    @Step("Open page: {pageClass}")
    protected <T extends BasePage<T>> T openNewPage(Class<T> pageClass) {
        return PageFactory.openNewPage(pageClass);
    }
    //endregion
}
