package ui.pages.logination;

import io.qameta.allure.Step;
import ui.pages.MainPage;

import static com.codeborne.selenide.Selenide.page;

public class AtlassianAddPage {

    @Step("Переход на другую страницу")
    public MainPage getMainPage() {
        return page(MainPage.class);
    }

    @Step("Переход на другую страницу")
    public StartPage getStartPage() {
        return page(StartPage.class);
    }

}
