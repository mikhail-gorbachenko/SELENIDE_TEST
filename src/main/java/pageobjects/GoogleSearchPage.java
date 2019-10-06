package pageobjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class GoogleSearchPage{

    //в прошлых тестах я старался делать один ассерт в конце тела теста
    //но в обучалке селенида сказали что лучше скрывать.
    //https://github.com/mikhail-gorbachenko/SE_MENT/blob/master/src/test/java/com/epam/se_ment/YandexMailTests.java
    public void assertThatSiteExistsInResults(String url){
        List<SelenideElement> searchResults=$$(".rc").shouldHave(CollectionCondition.sizeGreaterThan(1));
        boolean found = false;
        for (SelenideElement item: searchResults) {
            if(item.has(Condition.text(url))){
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Not found ref");
    }

    public OpenHomePage goToHomePage(){
        List<SelenideElement> searchResults=$$(".rc").shouldHave(CollectionCondition.sizeGreaterThan(1));
        for (SelenideElement item: searchResults) {
            if(item.has(Condition.text("https://www.open.ru "))){
                item.$("div[class=\"TbwUpd\"]").click();
                break;
            }
        }
        return page(OpenHomePage.class);
    }

}