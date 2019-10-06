import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Tests {

    @Test
    public void dumbTest() {
    open("http://google.com");
        Assert.assertEquals(getWebDriver().getTitle(),"Google");

        $("input[class=\"gLFyf gsfi\"]").sendKeys("Открытие");
        $("input[class=\"gLFyf gsfi\"]").pressEnter();
        List<SelenideElement> searchResults=$$(".rc");

        for (SelenideElement item: searchResults) {
            if(item.has(Condition.text("https://www.open.ru "))){
                System.out.println(item);
                item.$("div[class=\"TbwUpd\"]").click();
                break;
            }
        }
    }

}
