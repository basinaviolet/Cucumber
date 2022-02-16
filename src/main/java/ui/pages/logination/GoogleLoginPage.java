package ui.pages.logination;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.pages.MainPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class GoogleLoginPage {

    String insertLoginPageCheckStr = "https://trello.com/login";
    private SelenideElement emailInput = $(By.name("identifier")); //"//input[@name='identifier']"
    private SelenideElement nextButton = $(By.id("identifierNext"));

    @Step("Нажатие на подтвеждающую действие кнопку")
    public MainPage enterTextToInputString(String text) {
                emailInput.setValue(text);
        return page(MainPage.class);
    }
}
