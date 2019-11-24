package com.hellofresh.challenge.data;

import org.testng.annotations.DataProvider;

public class UserDataProvider {

    @DataProvider(name = "RandomUserDataProvider")
    public static Object[] getRandomUser() {
        return new Object[]{
                UserDataRegistry.getRandomUser()
        };
    }

    @DataProvider(name = "ExistedUserDataProvider")
    public static Object[] getExistedUser() {
        return new Object[]{
                UserDataRegistry.getExistedUser()
        };
    }
}
