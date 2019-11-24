package com.hellofresh.challenge.pages.checkout;

import com.hellofresh.challenge.pages.YourLogoBasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductPaymentCheckoutPage extends YourLogoBasePage<ProductPaymentCheckoutPage> {

    private By payByBankWireButton = By.className("bankwire");
    private By confirmOrderButton = By.xpath("//*[@id='cart_navigation']/button");

    @Step("Click 'Pay by bank wire' button")
    public ProductPaymentCheckoutPage clickPayByBankWireButton() {
        log.info("Click 'Pay by bank wire' button");
        click(payByBankWireButton);
        return this;
    }

    @Step("Click 'Confirm order' button")
    public OrderConfirmationPage clickConfirmOrderButton() {
        log.info("Click 'Confirm order' button");
        click(confirmOrderButton);
        return createPage(OrderConfirmationPage.class);
    }
}
