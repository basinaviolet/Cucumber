package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BoardPage {
    // https://trello.com/b/qlvQVaH7/agile-board

    //  private final SelenideElement mainPageCheck = $(By.xpath("//title"));
    private final SelenideElement boardPageName = $(By.tagName("h1"));
    private String boardPageCheckH1Locator = "//h1[contains(text(),";

    //Header
    private final SelenideElement topNavigatorOptionLink = $(By.id("header"));
    private final SelenideElement topNavigatorMenuLink = $(By.xpath("//div[@id='header']//button[@type='button']"));
    private final ElementsCollection topNavigatorMenu = $$(By.xpath("//div[@id='header']//button[@type='button']"));
    private final ElementsCollection topNavigatorDropdownMenu = $$(By.xpath("//div[@id='header']//button[@type='button']//span"));

    private final ElementsCollection headerInfoSectionButton = $$(By.xpath("//button[contains(@data-test-id,'header')]//span"));
    private final SelenideElement headerInfoSectionLink = $(By.xpath("//button[contains(@data-test-id,'header')]"));
    private final SelenideElement searchLink = $(By.xpath("//*[@type='search']"));
    private final ElementsCollection search = $$(By.xpath("//*[@type='search']"));

    private final SelenideElement returnToMainButton = $(By.xpath("//*[@aria-label='Вернуться на главную страницу']"));

    //Home container

    //Menu for other options
    private final SelenideElement menuForOtherOptions = $(By.xpath("//*[contains(@class,'board-header-btn mod-show-menu')]"));
    private final SelenideElement menuForOtherOptionsContainer = $(By.xpath("//div[@class='board-menu-header-content']"));
    private final SelenideElement menuForOtherOptionsContainerOpenMore = $(By.xpath("//*[contains(@class,'js-open-more')]"));
    private final SelenideElement menuForOtherOptionsContainerCloseBoard = $(By.xpath("//*[contains(@class,'js-close-board')]"));
    private final SelenideElement menuForOtherOptionsContainerSubmitCloseBoard = $(By.xpath("//*[contains(@class,'js-confirm')]"));

    //Actions
    //list
    private final SelenideElement addList = $(By.className("placeholder"));
    private final SelenideElement listNameInput = $(By.className("list-name-input"));
    private final SelenideElement addListSubmitButton = $(By.xpath("//input[@type='submit']"));
    private final SelenideElement cancelEditButton = $(By.xpath("//a[contains(@class,'js-cancel-edit')]"));

  //  private final SelenideElement listWrapper = $(By.className("js-list list-wrapper"));
    private final SelenideElement addCardButton = $(By.xpath("//a[contains(@class,'open-card-composer')]"));
    private final SelenideElement cardNameInput = $(By.xpath("//textarea[contains(@class,'list-card-composer-textarea')]"));
    private final SelenideElement addCardCancel = $(By.xpath("//a[contains(@class,'js-cancel')]"));

    private final SelenideElement addCardDetails = $(By.xpath("//span[contains(@class,'list-card-title')]"));
    private final SelenideElement cardDescription = $(By.xpath("//div[contains(@class,'description-edit')]//textarea[contains(@class,'card-description')]"));//a[contains(@class,'description-fake-text-are')]"));
    private final SelenideElement cardDescriptionEdit = $(By.xpath("//div[contains(@class,'description-edit')]"));//textarea
    private final SelenideElement cardDescriptionSave = $(By.xpath("//div[contains(@class,'description-edit')]//input[contains(@class,'js-save-edit')]"));
    private final SelenideElement cardCommentBox = $(By.xpath("//div[@class='comment-box']//textarea[contains(@class,'comment-box-input')]"));
    private final SelenideElement cardCommentSubscribe = $(By.xpath("//span[contains(@class,'comment-subscribe')]"));
    private final SelenideElement cardCommentSave = $(By.xpath("//div[contains(@class,'comment-controls')]//input[contains(@class,'js-add-comment')]"));
    private final SelenideElement closeListEditButton = $(By.xpath("//a[contains(@class,'js-close-window')]"));


    private final SelenideElement addCardListEnd = $(By.xpath("//a[contains(@class,'dialog-close-button')]"));


    @Step("Проверка нахождения на нужной странице")
    public boolean ifBoardPage(String forCheck) {
        try {
            boardPageName.hover();
            return (boardPageName.getText().equals(forCheck));
        } catch (Exception e) {
            System.out.println("Main page was not found. " + e.getMessage());
            return false;
        }
    }

    @Step("Проверка наименования доски")
    public boolean ifTemplateBoardPage(String forCheck) {
        try {
            boardPageName.hover();
            return (boardPageName.getText().indexOf(forCheck) == 0);
        } catch (Exception e) {
            System.out.println("Main page was not found. " + e.getMessage());
            return false;
        }
    }

    @Step("Добавить новый список")
    public void addNewList(String listName) {
        try {
            addList.hover().click();
            listNameInput.setValue(listName).pressEnter();
            addListSubmitButton.hover().click();
            cancelEditButton.click();
        } catch (Exception e) {
            System.out.println("During creating of a new list, some errors took place. " + e.getMessage());
        }
    }

    @Step("Добавить новую карточку")
    public void addNewCard(String cardName) {
        try {
            addCardButton.hover().click();
            cardNameInput.setValue(cardName).pressEnter();
            addCardCancel.hover().click();
        } catch (Exception e) {
            System.out.println("During creating of a new card, some errors took place. " + e.getMessage());
        }
    }

    @Step("Добавить описание карточки")
    public void addCardDescription( String descriptionText) {
        try {
            addCardDetails.click();
            cardDescription.click();
            cardDescriptionEdit.find(By.tagName("textarea")).setValue(descriptionText);
            cardDescriptionSave.click();
        } catch (Exception e) {
            System.out.println("During adding of card description, some errors took place. " + e.getMessage());
        }
    }

    @Step("Добавить комментарий карточки")
    public void addCardComment(String commentText) {
        try {
            cardCommentBox.setValue(commentText).pressEnter();
            cardCommentSubscribe.hover().click();
            cardCommentSave.hover().click();
            closeListEditButton.hover().click();
        } catch (Exception e) {
            System.out.println("During  adding of card comment, some errors took place. " + e.getMessage());
        }
    }

    @Step("Вернуться на главную страницу")
    public MainPage returnToMain() {
        try {
            returnToMainButton.hover().click();
        } catch (Exception e) {
            System.out.println("to return was not found. " + e.getMessage());
        }
        return page(MainPage.class);
    }

    @Step("Удалить доску")
    public CloseBoardPage closeBoardPage() {
        menuForOtherOptions.click();
        menuForOtherOptionsContainerOpenMore.shouldBe(visible).parent().click();//waitUntil(visible, 15000).
        menuForOtherOptionsContainerCloseBoard.click();
        menuForOtherOptionsContainerSubmitCloseBoard.click();
        return Selenide.page(CloseBoardPage.class);
    }


}
