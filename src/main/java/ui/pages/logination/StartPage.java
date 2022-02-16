package ui.pages.logination;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverConditions.url;

public class StartPage {

    private final SelenideElement loginButton = $(By.xpath("//a[contains(@href,'login')]"));
    private final SelenideElement language = $(By.id("language-picker"));

    @Step("Нажатие на подтвеждающую действие кнопку")
    public LoginPage loginButtonClick() {
        try {
            loginButton.click();
        } catch (Exception e) {
            System.out.println("Login button was not clicked. " + e.getMessage());
        }
        return Selenide.page(LoginPage.class);
    }

    @Step("Проверка нахождения на нужной странице")
    public boolean ifStartPage(String homePageUrl) {
        try {
            webdriver().shouldHave(url(homePageUrl));
            return true;
        } catch (Exception e) {
            System.out.println("Logout was failed. " + e.getMessage());
            return false;
        }
    }

    @Step("Выбор языка интерфейа")
    public StartPage setRusLanguage() {
        if (language.getValue() != "ru") {
            language.selectOptionByValue("ru");
        }
        return this;
    }
}
