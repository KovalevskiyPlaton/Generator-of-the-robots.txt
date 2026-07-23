package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.RobotsPage;

import static com.codeborne.selenide.WebDriverRunner.*;

public class BaseTest {
    RobotsPage robotsPage;

    @BeforeMethod
    public void setup() {
        Configuration.browser = "firefox";
        Configuration.timeout = 15000;
        Configuration.headless = false;
        Configuration.baseUrl = "https://pr-cy.ru";
        Configuration.browserSize = "1920x1080";
        
        robotsPage = new RobotsPage();
    }

    @AfterMethod
    public void close() {
        clearBrowserCache();
        closeWebDriver();
    }
}
