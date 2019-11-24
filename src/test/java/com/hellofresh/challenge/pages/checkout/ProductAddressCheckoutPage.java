package com.hellofresh.challenge.pages.checkout;

import com.hellofresh.challenge.pages.YourLogoBasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductAddressCheckoutPage extends YourLogoBasePage<ProductAddressCheckoutPage> {

    private By proceedToCheckoutButton = By.name("processAddress");

    @Step("Click 'Proceed to checkout' button")
    public ProductShippingCheckoutPage clickProceedToCheckoutButton() {
        log.info("Click 'Proceed to checkout' button");
        click(proceedToCheckoutButton);
        return createPage(ProductShippingCheckoutPage.class);
    }
}
