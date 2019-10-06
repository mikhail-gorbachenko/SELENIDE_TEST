package pageobjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.List;

public class OpenHomePage {

    @FindBy(css = "table[class=\"main-page-exchange__table main-page-exchange__table--online\"]")
    SelenideElement currencyExchangeTable;

    public void assertThatSellValueLargeThenBueValue(){
        List<SelenideElement> rowList = currencyExchangeTable.$$(By.tagName("tr"));
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
