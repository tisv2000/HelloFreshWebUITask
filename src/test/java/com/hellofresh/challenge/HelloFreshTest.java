package com.hellofresh.challenge;

import com.hellofresh.challenge.data.User;
import com.hellofresh.challenge.data.UserDataProvider;
import com.hellofresh.challenge.pages.AccountPage;
import com.hellofresh.challenge.pages.HomePage;
import com.hellofresh.challenge.pages.checkout.OrderConfirmationPage;
import core.base_test.BaseTest;
import org.testng.annotations.Test;

import static com.hellofresh.challenge.data.Constants.*;
import static com.hellofresh.challenge.data.RandomUtils.generateRandomItemIndex;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HelloFreshTest extends BaseTest {

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "RandomUserDataProvider")
    public void testSignUp(User user) {
        AccountPage accountPage = openNewPage(HomePage.class)
                .validateIsRightPage()
                .clickSignInButton()
                .enterEmailForRegistration(user.getEmail())
                .clickCreateAnAccountButton()
                .fillAllRegistrationFields(user)
                .clickRegisterButton()
                .validateIsRightPage();
        assertEquals(accountPage.getAccountLabelText(), user.getCustomerFirstName() + " " + user.getCustomerLastName());
        assertEquals(accountPage.getInfoAccountMessage(), ACCOUNT_WELCOME_MESSAGE);
        assertTrue(accountPage.isSignOutButtonDisplayed(), SING_OUT_BUTTON_NOT_DISPLAYED);
        assertTrue(accountPage.isCurrentUrlCorrect(), ACCOUNT_PAGE_NOT_CORRECT);
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "ExistedUserDataProvider")
    public void testSignIn(User user) {
        AccountPage accountPage = openNewPage(HomePage.class)
                .validateIsRightPage()
                .clickSignInButton()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickSignInButton()
                .validateIsRightPage();
        assertEquals(accountPage.getAccountLabelText(), user.getCustomerFirstName() + " " + user.getCustomerLastName());
        assertEquals(accountPage.getInfoAccountMessage(), ACCOUNT_WELCOME_MESSAGE);
        assertTrue(accountPage.isSignOutButtonDisplayed(), SING_OUT_BUTTON_NOT_DISPLAYED);
        assertTrue(accountPage.isCurrentUrlCorrect(), ACCOUNT_PAGE_NOT_CORRECT);
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "ExistedUserDataProvider")
    public void testCheckout(User user) {
        OrderConfirmationPage orderConfirmationPage = openNewPage(HomePage.class)
                .validateIsRightPage()
                .clickSignInButton()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickSignInButton()
                .validateIsRightPage()
                .clickWomenLink()
                .clickOnItem(generateRandomItemIndex())
                .clickAddToCartButton()
                .clickProceedToCheckoutButton()
                .clickProceedToCheckoutButton()
                .clickProceedToCheckoutButton()
                .tickTermsOfServiceCheckbox()
                .clickProceedToCheckoutButtonOnProductShippingCheckoutPage()
                .clickPayByBankWireButton()
                .clickConfirmOrderButton()
                .validateIsRightPage();
        assertTrue(orderConfirmationPage.isShippingLabelDisplayed(), SHIPPING_LABEL_NOT_DISPLAYED);
        assertTrue(orderConfirmationPage.isPaymentLabelDisplayed(), PAYMENT_LABEL_NOT_DISPLAYED);
        assertEquals(orderConfirmationPage.getOrderConfirmationMessage(), ORDER_IS_COMPLETE_MESSAGE);
        assertTrue(orderConfirmationPage.isCurrentUrlCorrect(), ORDER_CONFIRMATION_PAGE_NOT_CORRECT);
    }
}
