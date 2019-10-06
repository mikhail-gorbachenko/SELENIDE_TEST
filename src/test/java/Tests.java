import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
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
        //        System.out.println(item);
                item.$("div[class=\"TbwUpd\"]").click();
                break;
            }
        }

        //table[class="main-page-exchange__table main-page-exchange__table--online"]

        SelenideElement table = $("table[class=\"main-page-exchange__table main-page-exchange__table--online\"]");
    //    System.out.println(table);
        List<SelenideElement> rowList = table.$$(By.tagName("tr"));
     //   rowList.forEach(System.out::println);

        for (SelenideElement item: rowList) {
            List<SelenideElement> columnList = item.$$(By.tagName("td"));
            BigDecimal sell = null;
            BigDecimal buy= null;
            if(columnList.get(0).text().contains("USD")){
                buy = new BigDecimal(columnList.get(1).text().replaceAll(",", "."));
                sell = new BigDecimal(columnList.get(3).text().replaceAll(",", "."));
                int compare = buy.compareTo(sell);
                Assert.assertEquals(-1, compare, "USD sells cheaper");
            }
            if(columnList.get(0).text().contains("EUR")){
                buy = new BigDecimal(columnList.get(1).text().replaceAll(",", "."));
                sell = new BigDecimal(columnList.get(3).text().replaceAll(",", "."));
                int compare = buy.compareTo(sell);
                Assert.assertEquals(-1, compare, "EUR sells cheaper");
            }
        }


    }

}
