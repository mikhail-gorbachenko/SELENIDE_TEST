package pageobjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class GoogleMainPage{
    @FindBy(css = "input[class=\"gLFyf gsfi\"]")
    private SelenideElement searchInputField;

    public GoogleSearchPage performSearch(String value){
        searchInputField.setValue(value).pressEnter();
        return page(GoogleSearchPage.class);
    }

}
