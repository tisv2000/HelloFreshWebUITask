package com.hellofresh.challenge.pages.checkout;

import com.hellofresh.challenge.pages.YourLogoBasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductSummaryCheckoutPage extends YourLogoBasePage<ProductSummaryCheckoutPage> {

    private By proceedToCheckoutButton = By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']");

    @Step("Click 'Proceed to checkout' button")
    public ProductAddressCheckoutPage clickProceedToCheckoutButton() {
        log.info("Click 'Proceed to checkout' button");
        click(proceedToCheckoutButton);
        return createPage(ProductAddressCheckoutPage.class);
    }
}
