package com.hellofresh.challenge.pages.checkout;

import com.hellofresh.challenge.pages.YourLogoBasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductInformationPage extends YourLogoBasePage<ProductInformationPage> {

    private By addToCartButton = By.name("Submit");

    @Step("Click 'Add to cart' button")
    public ProductCheckoutPopUpPage clickAddToCartButton() {
        log.info("Click 'Add to cart' button");
        click(addToCartButton);
        return createPage(ProductCheckoutPopUpPage.class);
    }
}
