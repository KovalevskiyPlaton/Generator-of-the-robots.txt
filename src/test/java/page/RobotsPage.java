package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RobotsPage {
    public final String dropListLocator = "//div[@class='lgt-select-content']";
    public final String secondDropLocator = "//h2[text()='Правила']/ancestor::div[@style='margin-bottom:25px']" +
            "/child::div[last()]//div[@class='lgt-select-content']";
    public static final String pickBot = "//div[@title='%s']/div";
    public static final String pickSecondBot = "//following-sibling::div[@title='%s']/div";
    public final String crawlRadioButton = "//div[@class='lgt-segmented-item-label'][@title='%s']";
    final String titleText = "Ваш Robots.txt";
    final String copyText = "Robots.txt скопирован!";
    final String robotsTitle = "//h2[text()='Ваш Robots.txt']";
    final String agreeUseCheckbox = "//input[@class='lgt-checkbox-input']";
    final String copyButon = "//button[@type='button']/span[text()='Скопировать']";
    final String copiedMessage = "//span[text() = 'Robots.txt скопирован!']";
    final String areaResult = "#robots";
    final String inputUrl = "//div[text()='URL']/following-sibling::input";
    final String inputSecondUrl = "//h2[text()='Правила']/ancestor::div[@style='margin-bottom:25px']/child::" +
            "div[last()]//div[text()='URL']/following-sibling::input";
    final String allowRadioButton = "//div[@title='Разрешить']";
    final String allowSecondRadioButton = "//h2[text()='Правила']/ancestor::div[@style='margin-bottom:25px']/" +
            "child::div[last()]//div[@title='Разрешить']";
    final String disallowRadioButton = "//div[@title='Запретить']";
    final String disallowSecondRadioButton = "//h2[text()='Правила']/ancestor::div[@style='margin-bottom:25px']/" +
            "child::div[last()]//div[@title='Запретить']";
    final String addNewRuleButton = "//span[text()='Добавить правило']/ancestor::button";

    public RobotsPage openPage() {
        open("/robots/");
        return this;
    }

    public RobotsPage checkTitleText() {
        $x(robotsTitle).shouldHave(text(titleText), Duration.ofSeconds(15));
        return this;
    }

    public RobotsPage clickAgreeCheckbox() {
        $x(agreeUseCheckbox).click();
        return this;
    }

    public RobotsPage clickCopyButon() {
        $x(copyButon).click();
        return this;
    }

    public RobotsPage checkSuccessfulCopyingText() {
        $x(copiedMessage).shouldHave(text(copyText), Duration.ofSeconds(8));
        return this;
    }

    public RobotsPage pickInDropListWithScroll(String botName) {
        $x(dropListLocator).click();
        SelenideElement targetBot = $x(pickBot.formatted(botName));
        for (int i = 0; i < 100; i++) {
            if (!targetBot.isDisplayed()) {
                actions().sendKeys(Keys.ARROW_DOWN).perform();
            }
        }
        targetBot.click();
        return this;
    }

    public RobotsPage checkCopiedText(String innerTextArea) {
        SelenideElement area = $(areaResult);
        area.click();
        area.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        area.sendKeys(Keys.chord(Keys.CONTROL, "c"));
        area.sendKeys(Keys.chord(Keys.DELETE));
        area.sendKeys(Keys.chord(Keys.CONTROL, "v"));
        checkTextAreaResult(innerTextArea);
        return this;
    }

    public RobotsPage checkTextAreaResult(String innerTextArea) {
        SelenideElement area = $(areaResult);
        area.shouldHave(text(innerTextArea), Duration.ofSeconds(8));
        return this;
    }

    public String getResultTextAreaText() {
        return $(areaResult).getText();
    }

    public RobotsPage pickDisallowRadioButton() {
        $x(disallowRadioButton).click();
        return this;
    }

    public RobotsPage pickSecondDisallowRadioButton() {
        $x(disallowSecondRadioButton).click();
        return this;
    }

    public RobotsPage pickAllowRadioButton() {
        $x(allowRadioButton).click();
        return this;
    }

    public RobotsPage pickAllowSecondRadioButton() {
        $x(allowSecondRadioButton).click();
        return this;
    }

    public RobotsPage addUrl(String url) {
        $x(inputUrl).setValue(url);
        return this;
    }

    public RobotsPage pickNewRulesButton() {
        $x(addNewRuleButton).click();
        return this;
    }

    public RobotsPage pickSecondInDropListWithScroll(String botName) {
        $x(secondDropLocator).click();
        SelenideElement targetBot = $x(pickSecondBot.formatted(botName));
        for (int i = 0; i < 16; i++) {
            if (!targetBot.isDisplayed()) {
                actions().sendKeys(Keys.ARROW_DOWN).perform();
            }
        }
        targetBot.click();
        return this;
    }

    public RobotsPage addSomeRules(String rules1, String rules2, String botName1, String botName2) {
        pickDisallowRadioButton();
        addUrl(rules1);
        pickInDropListWithScroll(botName1);
        pickNewRulesButton();
        pickSecondDisallowRadioButton();
        $x(inputSecondUrl).setValue(rules2);
        pickSecondInDropListWithScroll(botName2);
        return this;
    }

    public RobotsPage addSomeRules(String rules1, String rules2) {
        pickDisallowRadioButton();
        addUrl(rules1);
        pickNewRulesButton();
        pickAllowSecondRadioButton();
        $x(inputSecondUrl).setValue(rules2);
        return this;
    }

    public RobotsPage pickCrawlDelayButton(String numDelay) {
        SelenideElement targetIndexButton = $x(crawlRadioButton.formatted(numDelay));
        targetIndexButton.click();
        return this;
    }
}
