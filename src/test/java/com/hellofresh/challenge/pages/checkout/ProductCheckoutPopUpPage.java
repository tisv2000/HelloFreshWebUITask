package com.hellofresh.challenge.pages.checkout;

import com.hellofresh.challenge.pages.YourLogoBasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductCheckoutPopUpPage extends YourLogoBasePage<ProductCheckoutPopUpPage> {

    private By proceedToCheckoutButton = By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']");

    @Step("Click 'Proceed to checkout' button")
    public ProductSummaryCheckoutPage clickProceedToCheckoutButton() {
        log.info("Click 'Proceed to checkout' button");
        click(proceedToCheckoutButton);
        return createPage(ProductSummaryCheckoutPage.class);
    }
}
