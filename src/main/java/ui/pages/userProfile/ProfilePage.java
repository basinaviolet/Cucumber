package ui.pages.userProfile;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.MainPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {
    private final SelenideElement userDescription = $(By.xpath("//div[@data-test-id='profile-tab-container']//textarea"));
    private final SelenideElement userDescriptionSave = $(By.xpath("//div[@data-test-id='profile-tab-container']//button"));

    //User info
    private final SelenideElement userInfoButton = $(By.xpath("//button[@data-test-id='header-member-menu-button']"));
    private final SelenideElement userInfoMenu = $(By.xpath("//section[@data-test-id='header-member-menu-popover']"));
    private final SelenideElement userLogout = $(By.xpath("//button[@data-test-id='header-member-menu-logout']"));
    private final SelenideElement userLogoutSubmit = $(By.id("logout-submit"));
    private final SelenideElement userLogoutExit = $(By.xpath("//button[@data-test-id='popover-close']"));

    private final SelenideElement changeUserMenuProfile = $(By.xpath("//a[@data-test-id='header-member-menu-profile']"));
    private final SelenideElement changeUserMenuCards = $(By.xpath("//a[@data-test-id='header-member-menu-cards']"));
    private final SelenideElement changeUserMenuSettings = $(By.xpath("//a[@data-test-id='header-member-menu-settings']"));

    private final SelenideElement returnToMainButton = $(By.xpath("//*[@aria-label='Вернуться на главную страницу']"));

    public String setUserDescription(String description){
        userDescription.setValue(description);
        userDescriptionSave.click();
        return userDescription.getText();
    }

    public SettingsPage changeUserSettings() {
        try {
            userInfoButton.hover().click();
            userInfoMenu.shouldBe(enabled);
            changeUserMenuSettings.click();
        } catch (Exception e) {
            System.out.println("Changing user settings was failed. " + e.getMessage());
        }
        return page(SettingsPage.class);
    }

    public MainPage returnToMain() {
        try {
            returnToMainButton.hover().click();
        } catch (Exception e) {
            System.out.println("to return was not found. " + e.getMessage());
        }
        return page(MainPage.class);
    }
}
