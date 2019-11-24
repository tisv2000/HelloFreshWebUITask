package com.hellofresh.challenge.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends YourLogoBasePage<HomePage> {

    private By loginLink = By.className("login");

    public HomePage() {
        setPagePath("/index.php");
        setRightPageCondition(ExpectedConditions.presenceOfElementLocated(loginLink));
    }

    @Step("Click 'Sign in' button")
    public AuthenticationPage clickSignInButton() {
        log.info("Click 'Sign in' button");
        click(loginLink);
        return createPage(AuthenticationPage.class);
    }
}
