package ui.pages.userProfile;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.MainPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SettingsPage {
    private final SelenideElement marketingNotifications = $(By.xpath("//a[contains(@class,'marketing-opt')]"));
    private final SelenideElement marketingNotificationsText = $(By.xpath("//a[contains(@class,'marketing-opt')]//span"));

    private final SelenideElement recommendations = $(By.xpath("//a[contains(@class,'suggestions')]"));
    private final SelenideElement recommendationsText = $(By.xpath("//a[contains(@class,'suggestions')]//span"));

    private final SelenideElement emailNotifications = $(By.xpath("//a[contains(@class,'change-email-freq')]"));
    private final SelenideElement emailNotificationClose = $(By.xpath("//a[contains(@class,'icon-close')]"));
    private final SelenideElement emailNotificationsSelect = $(By.xpath(
            "//ul[contains(@name,'minutesBetweenSummaries')]//span[contains(@class,'icon-check')]"));
    private final SelenideElement emailNotificationsMenu = $(By.xpath("//ul[contains(@name,'minutesBetweenSummaries')]//a"));

    //User info
    private final SelenideElement userInfoButton = $(By.xpath("//button[@data-test-id='header-member-menu-button']"));
    private final SelenideElement userInfoMenu = $(By.xpath("//section[@data-test-id='header-member-menu-popover']"));
    private final SelenideElement userLogout = $(By.xpath("//button[@data-test-id='header-member-menu-logout']"));
    private final SelenideElement userLogoutSubmit = $(By.id("logout-submit"));
    private final SelenideElement userLogoutExit = $(By.xpath("//button[@data-test-id='popover-close']"));

    //Menu
    private final SelenideElement changeUserMenuProfile = $(By.xpath("//a[@data-test-id='header-member-menu-profile']"));
    private final SelenideElement changeUserMenuCards = $(By.xpath("//a[@data-test-id='header-member-menu-cards']"));
    private final SelenideElement changeUserMenuSettings = $(By.xpath("//a[@data-test-id='header-member-menu-settings']"));

    private final SelenideElement returnToMainButton = $(By.xpath("//*[@aria-label='Вернуться на главную страницу']"));

    public boolean changeMarketingNotification(){
        String[] marketingNotificationType = {"Отказаться", "Согласиться"};
        String ifAgree = getTypeNotification(marketingNotificationsText);
        marketingNotifications.click();
        String ifAgreeNew = getTypeNotification(marketingNotificationsText);

        return ((ifAgree.equals(marketingNotificationType[0]) && ifAgreeNew.equals(marketingNotificationType[1])) ||
                (ifAgree.equals(marketingNotificationType[1]) && ifAgreeNew.equals(marketingNotificationType[0])))
                ? true : false;
    }

    public boolean changeRecommendation(){
        String[] recommendationNotificationType = {"Включить", "Отключить"};
        String ifAgree = getTypeNotification(recommendationsText);
        recommendations.click();
        String ifAgreeNew = getTypeNotification(recommendationsText);

        return ((ifAgree.equals(recommendationNotificationType[0]) && ifAgreeNew.equals(recommendationNotificationType[1])) ||
                (ifAgree.equals(recommendationNotificationType[1]) && ifAgreeNew.equals(recommendationNotificationType[0])))
                ? true : false;
    }

    public String changeEmailNotificationType(String emailNotificationType){
//        String[] emailNotificationType = {"Никогда", "Периодиески", "Мгновенно"};
        emailNotifications.click();
//        SelenideElement emailNotificationItem = emailNotificationText.filter(text(emailNotificationType)).first();
//        if(!emailNotificationItem.find(By.xpath("span[contains(@class,'icon-check')]")).exists())
//            emailNotificationItem.click();
        emailNotificationsMenu.shouldHave(value(emailNotificationType)).click();
        emailNotifications.click();
        String value = emailNotificationsSelect.parent().getValue();
        emailNotificationClose.click();
     //   String selectItem = emailNotificationsMenu.first().getValue();
        return value;
    }

    public String getTypeNotification(SelenideElement element){
        return element.getText().split(" ")[0];
    }

    public ProfilePage changeUserProfile() {
        //Профиль и доступ
        try {
            userInfoButton.hover().click();
            userInfoMenu.shouldBe(enabled);
            changeUserMenuProfile.click();
        } catch (Exception e) {
            System.out.println("Changing user profile was failed. " + e.getMessage());
        }
        return Selenide.page(ProfilePage.class);
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
