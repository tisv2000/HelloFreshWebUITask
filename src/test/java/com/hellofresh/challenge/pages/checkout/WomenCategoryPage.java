package com.hellofresh.challenge.pages.checkout;

import com.hellofresh.challenge.pages.YourLogoBasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class WomenCategoryPage extends YourLogoBasePage<WomenCategoryPage> {

    private By itemsList = By.xpath("//*[@class='product_list grid row']/li");

    @Step("Select random item")
    public ProductInformationPage clickOnItem(int random) {
        log.info("Select random item");
        moveToElementAndClick(findElements(itemsList).get(random));
        return createPage(ProductInformationPage.class);
    }
}
