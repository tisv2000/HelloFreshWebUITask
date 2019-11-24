package com.hellofresh.challenge.pages;

import com.hellofresh.challenge.data.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CreateAccountPage extends YourLogoBasePage<CreateAccountPage> {

    private By genderMaleRadioButton = By.id("id_gender1");
    private By genderFemaleRadioButton = By.id("id_gender2");
    private By customerFirstNameField = By.id("customer_firstname");
    private By customerLastNameField = By.id("customer_lastname");
    private By passwordField = By.id("passwd");
    private By dayOfBirthSelector = By.id("days");
    private By monthOfBirthSelector = By.id("months");
    private By yearOfBirthSelector = By.id("years");
    private By companyField = By.id("company");
    private By address1Field = By.id("address1");
    private By address2Field = By.id("address2");
    private By cityField = By.id("city");
    private By stateField = By.id("id_state");
    private By postCodeField = By.id("postcode");
    private By additionalInfoField = By.id("other");
    private By phoneField = By.id("phone");
    private By mobilePhoneField = By.id("phone_mobile");
    private By aliasField = By.id("alias");
    private By submitAccountButton = By.id("submitAccount");

    @Step("Fill all registration fields")
    public CreateAccountPage fillAllRegistrationFields(User user) {
        log.info("Fill all registration fields:");
        return selectGender(user.getGender())
                .enterCustomerFirstName(user.getCustomerFirstName())
                .enterCustomerLastName(user.getCustomerLastName())
                .enterPassword(user.getPassword())
                .selectDayOfBirth(user.getDayOfBirth())
                .selectMonthOfBirth(user.getMonthOfBirth())
                .selectYearOfBirth(user.getYearOfBirth())
                .enterCompany(user.getCompany())
                .enterAddress1(user.getAddress1())
                .enterAddress2(user.getAddress2())
                .enterCity(user.getCity())
                .selectState(user.getState())
                .enterPostCode(user.getPostCode())
                .enterAdditionalInfo(user.getAdditionalInfo())
                .enterPhone(user.getPhone())
                .enterMobilePhone(user.getMobilePhone())
                .enterAlias(user.getAlias());
    }

    @Step("Select gender: {gender}")
    private CreateAccountPage selectGender(User.UserGender gender) {
        log.info("Select gender {}", gender);
        switch (gender) {
            case FEMALE:
                click(genderFemaleRadioButton);
                break;
            case MALE:
                click(genderMaleRadioButton);
                break;
        }
        return this;
    }

    @Step("Enter customer first name: {customerFirstName}")
    private CreateAccountPage enterCustomerFirstName(String customerFirstName) {
        log.info("Enter customer first name: {}", customerFirstName);
        return enterValue(customerFirstNameField, customerFirstName);
    }

    @Step("Enter customer last name: {customerLastName}")
    private CreateAccountPage enterCustomerLastName(String customerLastName) {
        log.info("Enter customer last name: {}", customerLastName);
        return enterValue(customerLastNameField, customerLastName);
    }

    @Step("Enter password: *****")
    private CreateAccountPage enterPassword(String password) {
        log.info("Enter password");
        return enterValue(passwordField, password, false);
    }

    @Step("Select day of birth: {dayOfBirth}")
    private CreateAccountPage selectDayOfBirth(int dayOfBirth) {
        log.info("Select day of birth: {}", dayOfBirth);
        return selectByValue(dayOfBirthSelector, Integer.toString(dayOfBirth));
    }

    @Step("Select month of birth: {monthOfBirth}")
    private CreateAccountPage selectMonthOfBirth(int monthOfBirth) {
        log.info("Select month of birth: {}", monthOfBirth);
        return selectByValue(monthOfBirthSelector, Integer.toString(monthOfBirth));
    }

    @Step("Select year of birth: {yearOfBirth}")
    private CreateAccountPage selectYearOfBirth(int yearOfBirth) {
        log.info("Select year of birth: {}", yearOfBirth);
        return selectByValue(yearOfBirthSelector, Integer.toString(yearOfBirth));
    }

    @Step("Enter company: {company}")
    private CreateAccountPage enterCompany(String company) {
        log.info("Enter company: {}", company);
        return enterValue(companyField, company);
    }

    @Step("Enter address: {address1}")
    private CreateAccountPage enterAddress1(String address1) {
        log.info("Enter address: {}", address1);
        return enterValue(address1Field, address1);
    }

    @Step("Enter additional address: {address2}")
    private CreateAccountPage enterAddress2(String address2) {
        log.info("Enter additional address: {}", address2);
        return enterValue(address2Field, address2);
    }

    @Step("Enter city: {city}")
    private CreateAccountPage enterCity(String city) {
        log.info("Enter city: {}", city);
        return enterValue(cityField, city);
    }

    @Step("Select state")
    private CreateAccountPage selectState(String state) {
        log.info("Enter state: {}", state);
        return selectByIndex(stateField, Integer.parseInt(state));
    }

    @Step("Enter postCode: {postCode}")
    private CreateAccountPage enterPostCode(String postCode) {
        log.info("Enter post code: {}", postCode);
        return enterValue(postCodeField, postCode);
    }

    @Step("Enter additional information: {additionalInfo}")
    private CreateAccountPage enterAdditionalInfo(String additionalInfo) {
        log.info("Enter additional information: {}", additionalInfo);
        return enterValue(additionalInfoField, additionalInfo);
    }

    @Step("Enter phone: {phone}")
    private CreateAccountPage enterPhone(String phone) {
        log.info("Enter phone: {}", phone);
        return enterValue(phoneField, phone);
    }

    @Step("Enter mobilePhone: {mobilePhone}")
    private CreateAccountPage enterMobilePhone(String mobilePhone) {
        log.info("Enter mobilePhone: {}", mobilePhone);
        return enterValue(mobilePhoneField, mobilePhone);
    }

    @Step("Enter alias: {alias}")
    private CreateAccountPage enterAlias(String alias) {
        log.info("Enter alias: {}", alias);
        return enterValue(aliasField, alias);
    }

    @Step("click 'Register' button")
    public AccountPage clickRegisterButton() {
        log.info("Click Resister button");
        click(submitAccountButton);
        return createPage(AccountPage.class);
    }
}
