import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.GoogleMainPage;
import pageobjects.OpenHomePage;

import static com.codeborne.selenide.Selenide.open;


public class PageObjectTests {

    private GoogleMainPage gmain;
    private OpenHomePage open;

    @BeforeMethod
    public void start() {
        Configuration.startMaximized = true;
    }

    @Test
    public void performSearch(){
        gmain = open("http://google.com" ,GoogleMainPage.class);
        gmain.performSearch("Открытие")
                .assertThatSiteExistsInResults("https://www.open.ru");
    }

    @Test
    public void checkCurrencyExchangeWidget() {
        open = open("https://www.open.ru", OpenHomePage.class);
        open.assertThatSellValueLargeThenBueValue();
    }

    @Test
    public void testInOnePeace(){
        gmain = open("http://google.com" ,GoogleMainPage.class);
        gmain.performSearch("Открытие")
                .goToHomePage()
                .assertThatSellValueLargeThenBueValue();
    }


}
