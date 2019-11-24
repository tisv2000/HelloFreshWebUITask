package com.hellofresh.challenge.data;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

import static com.hellofresh.challenge.data.RandomUtils.*;

@Slf4j
class UserDataRegistry {

    private static final int LENGTH_10 = 10;
    private static final int LENGTH_5 = 5;

    static User getRandomUser() {
        log.info("Generate random user");
        User user = new User();
        user.setGender(generateRandomEnum(User.UserGender.class));
        user.setCustomerFirstName(generateRandomStringLettersOnly(LENGTH_10));
        user.setCustomerLastName(generateRandomStringLettersOnly(LENGTH_10));
        user.setPassword(generateRandomString(LENGTH_10));
        user.setDayOfBirth(generateRandomDayOfBirth());
        user.setMonthOfBirth(generateRandomMonthOfBirth());
        user.setYearOfBirth(generateRandomYearOfBirth());
        user.setCompany(generateRandomString(LENGTH_10));
        user.setAddress1(generateRandomString(LENGTH_10));
        user.setAddress2(generateRandomString(LENGTH_10));
        user.setCity(generateRandomString(LENGTH_10));
        user.setState(generateRandomState());
        user.setPostCode(generateRandomStringDigitsOnly(LENGTH_5));
        user.setAdditionalInfo(generateRandomString(LENGTH_10));
        user.setPhone(generateRandomStringDigitsOnly(LENGTH_10));
        user.setMobilePhone(generateRandomStringDigitsOnly(LENGTH_10));
        user.setAlias(generateRandomString(LENGTH_10));
        user.setEmail(generateRandomEmail(LENGTH_5));
        return user;
    }

    static User getExistedUser() {
        log.info("Get existing user from config");
        try {
            Properties properties = new Properties();
            properties.load(UserDataRegistry.class.getResourceAsStream("/users.properties"));
            User user = new User();
            user.setEmail(properties.getProperty("user.email"));
            user.setPassword(properties.getProperty("user.password"));
            user.setCustomerFirstName(properties.getProperty("user.firstname"));
            user.setCustomerLastName(properties.getProperty("user.lastname"));
            return user;
        } catch (IOException e) {
            log.error("Cannot load properties");
            throw new RuntimeException("Cannot load properties");
        }
    }
}
