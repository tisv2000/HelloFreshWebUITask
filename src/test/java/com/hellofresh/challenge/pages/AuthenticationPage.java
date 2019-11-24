package com.hellofresh.challenge.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class AuthenticationPage extends YourLogoBasePage<AuthenticationPage> {

    private By emailCreate = By.id("email_create");
    private By emailField = By.id("email");
    private By passwordField = By.id("passwd");
    private By signInButton = By.id("SubmitLogin");
    private By createAnAccountButton = By.id("SubmitCreate");

    @Step("Enter email: {email}")
    public AuthenticationPage enterEmail(String email) {
        log.info("Enter email: {}", email);
        return enterValue(emailField, email);
    }

    @Step("Enter password: *****")
    public AuthenticationPage enterPassword(String password) {
        return enterValue(passwordField, password, false);
    }

    @Step("Enter email: {email}")
    public AuthenticationPage enterEmailForRegistration(String email) {
        log.info("Enter email: {}", email);
        return enterValue(emailCreate, email);
    }

    @Step("Click 'Sign in' button")
    public AccountPage clickSignInButton() {
        log.info("Click 'Sign in' button");
        click(signInButton);
        return createPage(AccountPage.class);
    }

    @Step("Click 'Create an account' button")
    public CreateAccountPage clickCreateAnAccountButton() {
        log.info("Click 'Create an account' button");
        click(createAnAccountButton);
        return createPage(CreateAccountPage.class);
    }
}
