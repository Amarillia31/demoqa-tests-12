package com.elena;

import Config.CredentialsConfig;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {
    @BeforeAll
    static void setUp() {
        CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        String login = config.login();
        String password = config.password();
        String remoteDriver = System.getProperty("remoteDriver", "selenoid.autotests.cloud/wd/hub");
        String browserSize = System.getProperty("browserSize", "1920x1080");
        String baseUrl = System.getProperty("baseUrl", "https://demoqa.com");


//        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = baseUrl;
        Configuration.browserSize = browserSize;
        Configuration.remote = "https://" + login + ":" + password + "@" + remoteDriver;

        //video configuration
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
//        closeWebDriver();
    }
}
