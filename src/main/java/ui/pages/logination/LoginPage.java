package ui.pages.logination;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    String insertLoginPageCheckStr = "https://trello.com/login";
    private final SelenideElement language = $(By.id("language-picker"));
    private final SelenideElement insertLoginPageCheck = $(By.xpath("//h1[contains(text(),'Вход в Trello')]"));
    private SelenideElement emailInput = $(By.id("user"));
    private SelenideElement passwordInput = $(By.id("password"));
    private SelenideElement loginButton = $(By.id("login"));
    private SelenideElement ssoButton = $(By.id("use-sso-button"));
    private SelenideElement googleButton = $(By.id("googleButton"));
    private SelenideElement msButton = $(By.id("msftButton"));
    private SelenideElement appleButton = $(By.id("signInWithAppleButton"));
    private SelenideElement errorMessage = $(By.xpath("//p[@class='error-message']"));

    @Step("Проверка нахождения на нужной странице")
    public LoginPage ifLoginPage() {
        try {
            insertLoginPageCheck.exists();
        }
        catch (Exception e) {
            System.out.println("Login page does not found. " + e.getMessage());
        }
        return this;
    }

    @Step("Выбор типа авторизаци")
    public AtlassianPage loginButtonClick(String loginAction) {
        switch (loginAction){
            case "Google":
                googleButton.click();
                break;
            case "Microsoft":
                msButton.click();
                break;
            case "Apple":
                appleButton.click();
                break;
            default: loginButton.click();
        }
        return Selenide.page(AtlassianPage.class);
    }

    @Step("Ошибка")
    public boolean isErrorMessage() {
        return errorMessage.exists();
    }

    @Step("Нажатие на подтвеждающую действие кнопку")
    public LoginPage ssoClick() {
        if (loginButton.getValue().compareTo("Войти") == 0) {
            ssoButton.click();
        }
        return this;
    }

    @Step("Ввод данных пользователя")
    public LoginPage enterTextToLoginString(String inputElement, String text) {
        switch (inputElement) {
            case "email":
                emailInput.setValue(text);
                break;
            case "password":
                passwordInput.setValue(text);
                break;
        }
        return this;
    }

    @Step("Выбор языка интерфейа")
    public LoginPage setRusLanguage() {
        if (language.getValue() != "ru"){
            language.selectOptionByValue("ru");
        }
        return this;
    }
}
