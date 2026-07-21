package tests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.RobotsPage;

import static com.codeborne.selenide.WebDriverRunner.*;

public class BaseTest {
    RobotsPage robotsPage;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\soulshon\\chrome-for-testing\\chromedriver-win64\\chromedriver.exe");
        System.setProperty("selenide.browserBinary", "C:\\Users\\soulshon\\chrome-for-testing\\chrome-win64\\chrome.exe");
        Configuration.timeout = 15000;
        Configuration.headless = false;
        Configuration.baseUrl = "https://pr-cy.ru";
        Configuration.holdBrowserOpen = false;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("guest");
        options.addArguments("start-maximized");
        options.addArguments("headless");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = "maximize";

        robotsPage = new RobotsPage();
    }

    @AfterMethod
    public void close() {
        clearBrowserCache();
    }
}
