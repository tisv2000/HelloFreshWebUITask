package com.hellofresh.challenge.pages;

import core.base_page.BasePage;

public class YourLogoBasePage<T extends YourLogoBasePage<T>> extends BasePage<T> {

    public YourLogoBasePage() {
        setBaseUrl(System.getProperty("base.url"));
    }
}
