package com.hellofresh.challenge.pages.checkout;

import com.hellofresh.challenge.pages.YourLogoBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderConfirmationPage extends YourLogoBasePage<OrderConfirmationPage> {

    private By orderConfirmationPageHeader = By.cssSelector("h1");
    private By shippingLabel = By.xpath("//li[@class='step_done step_done_last four']");
    private By paymentLabel = By.xpath("//li[@id='step_end' and @class='step_current last']");
    private By orderConfirmationMessage = By.xpath("//*[@class='cheque-indent']/strong");

    public OrderConfirmationPage() {
        setRightPageCondition(ExpectedConditions.presenceOfElementLocated(orderConfirmationPageHeader));
    }

    public boolean isCurrentUrlCorrect() {
        return getDriver().getCurrentUrl().contains("controller=order-confirmation");
    }

    public boolean isShippingLabelDisplayed() {
        return findElement(shippingLabel).isDisplayed();
    }

    public boolean isPaymentLabelDisplayed() {
        return findElement(paymentLabel).isDisplayed();
    }

    public String getOrderConfirmationMessage() {
        return findElement(orderConfirmationMessage).getText();
    }
}
