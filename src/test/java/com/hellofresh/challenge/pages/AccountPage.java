package com.hellofresh.challenge.pages;

import com.hellofresh.challenge.pages.checkout.WomenCategoryPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends YourLogoBasePage<AccountPage> {

    private By accountPageHeader = By.cssSelector("h1");
    private By accountLabel = By.className("account");
    private By infoAccountMessage = By.className("info-account");
    private By signOutButton = By.className("logout");
    private By womenLink = By.linkText("Women");

    public AccountPage() {
        setRightPageCondition(ExpectedConditions.presenceOfElementLocated(accountPageHeader));
    }

    public String getAccountLabelText() {
        return getText(accountLabel);
    }

    public String getInfoAccountMessage() {
        return getText(infoAccountMessage);
    }

    public boolean isSignOutButtonDisplayed() {
        return isDisplayed(signOutButton);
    }

    public boolean isCurrentUrlCorrect() {
        return getCurrentUrl().contains("controller=my-account");
    }

    @Step("Click 'WOMEN' link")
    public WomenCategoryPage clickWomenLink() {
        log.info("Click 'WOMEN' link");
        click(womenLink);
        return createPage(WomenCategoryPage.class);
    }
}
