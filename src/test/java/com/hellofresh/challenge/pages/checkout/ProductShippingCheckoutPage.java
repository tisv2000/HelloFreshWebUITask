package com.hellofresh.challenge.pages.checkout;

import com.hellofresh.challenge.pages.YourLogoBasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductShippingCheckoutPage extends YourLogoBasePage<ProductShippingCheckoutPage> {

    private By proceedToCheckoutButton = By.name("processCarrier");
    private By termsOfServiceCheckBox = By.id("uniform-cgv");

    @Step("Click 'Proceed to checkout' button")
    public ProductPaymentCheckoutPage clickProceedToCheckoutButtonOnProductShippingCheckoutPage() {
        log.info("Click 'Proceed to checkout' button");
        click(proceedToCheckoutButton);
        return createPage(ProductPaymentCheckoutPage.class);
    }

    @Step("Tick 'Terms of Service' checkbox")
    public ProductShippingCheckoutPage tickTermsOfServiceCheckbox() {
        log.info("Tick 'Terms of Service' checkbox");
        click(termsOfServiceCheckBox);
        return this;
    }
}
