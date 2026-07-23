package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.List;

public class RobotsTest extends BaseTest {

    @Test(description = "ТК №1: Проверка генерации базового robots.txt с одним правилом Disallow для " +
            "всех ботов")
    public void getDisallowRulleForAllRobots() {
        String expectedText = "# Сгенерировано https://pr-cy.ru/\n" +
                "\n" +
                "User-agent: *\n" +
                "Disallow: /private/";
        robotsPage
                .openPage()
                .checkTitleText()
                .clickAgreeCheckbox()
                .addUrl("/private/")
                .pickDisallowRadioButton()
                .clickCopyButon()
                .checkSuccessfulCopyingText();
        assertEquals(robotsPage.getResultTextAreaText(), expectedText, "ожидаемый текст " +
                "не соответствует фактическому");
    }

    @Test(description = "ТК №2: Проверка генерации robots.txt с правилом Disallow " +
            "для конкретного бота (Googlebot")
    public void getDisallowRulleForGoogleBot() {
        String expectedText = "# Сгенерировано https://pr-cy.ru/\n" +
                "\n" +
                "User-agent: Googlebot\n" +
                "Disallow: /temp/";
        robotsPage
                .openPage()
                .checkTitleText()
                .clickAgreeCheckbox()
                .addUrl("/temp/")
                .pickDisallowRadioButton()
                .pickInDropListWithScroll("Google Bot")
                .clickCopyButon()
                .checkSuccessfulCopyingText();
        assertEquals(robotsPage.getResultTextAreaText(), expectedText, "ожидаемый текст " +
                "не соответствует фактическому");
    }

    @Test(description = "ТК №3: Проверка копирования, сгенерированного содержимого")
    public void checkCopyButton() {
        String expectedText = "# Сгенерировано https://pr-cy.ru/\n" +
                "User-agent: Pinterestbot\n" +
                "Allow: /auth/\n";
        robotsPage
                .openPage()
                .checkTitleText()
                .clickAgreeCheckbox()
                .addUrl("/auth/")
                .pickAllowRadioButton()
                .pickInDropListWithScroll("Pinterest Bot")
                .clickCopyButon()
                .checkSuccessfulCopyingText()
                .checkCopiedText(expectedText);
    }

    @Test(description = "ТК №4: Проверка генерации robots.txt с несколькими правилами Disallow для разных ботов")
    public void checkSomeGroupsRules() {
        String expectedText = "# Сгенерировано https://pr-cy.ru/\n" +
                "\n" +
                "User-agent: YandexBot\n" +
                "Disallow: /admin/\n" +
                "\n" +
                "User-agent: Baiduspider\n" +
                "Disallow: /search/\n";
        robotsPage
                .openPage()
                .checkTitleText()
                .clickAgreeCheckbox()
                .addSomeRules("/admin/", "/search/", "Yandex Bot", "Baidu Spider")
                .clickCopyButon()
                .checkSuccessfulCopyingText()
                .checkCopiedText(expectedText);
    }

    @Test(description = "ТК №5: Проверка генерации robots.txt с указанием директивы Allow")
    public void checkDifferentRules() {
        String expectedText = "# Сгенерировано https://pr-cy.ru/\n" +
                "\n" +
                "User-agent: *\n" +
                "Allow: /images/logo.png\n" +
                "Disallow: /images/";
        robotsPage
                .openPage()
                .checkTitleText()
                .clickAgreeCheckbox()
                .addSomeRules("/images/", "/images/logo.png")
                .clickCopyButon()
                .checkSuccessfulCopyingText()
                .checkTextAreaResult(expectedText);
    }

    @Test(description = "ТК №6: Проверка генерации robots.txt с указанием директивы Crawl-delay со значением  5")
    public void checkAddCrawlВelayRules() {
        String expectedText = "# Сгенерировано https://pr-cy.ru/\n" +
                "\n" +
                "Crawl-delay: 5\n" +
                "User-agent: *\n" +
                "Allow: \n";
        robotsPage
                .openPage()
                .checkTitleText()
                .clickAgreeCheckbox()
                .pickCrawlDelayButton("5")
                .checkTextAreaResult(expectedText);
    }

    @Test(description = "ТК №7: множественное добавление ботов в одно правило")
    public void addMultiRobotsRules() {
        List<String> botsRules = List.of("Google Video Bot", "Bing Bot", "DuckDuckGo Bot", "Facebook Bot");
        String expectedText = "# Сгенерировано https://pr-cy.ru/\n" +
                "\n" +
                "User-agent: Googlebot-Video\n" +
                "Allow: \n" +
                "\n" +
                "User-agent: Bingbot\n" +
                "Allow: \n" +
                "\n" +
                "User-agent: DuckDuckBot\n" +
                "Allow: \n" +
                "\n" +
                "User-agent: facebookexternalhit\n" +
                "Allow: ";
        robotsPage
                .openPage()
                .checkTitleText()
                .clickAgreeCheckbox();
        for (String rule : botsRules) {
            robotsPage.pickInDropListWithScroll(rule);
        }
        assertEquals(robotsPage.getResultTextAreaText(), expectedText, "ожидаемый текст " +
                "не соответствует фактическому");
    }
}
