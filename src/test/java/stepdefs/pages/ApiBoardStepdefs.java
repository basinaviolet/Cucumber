package stepdefs.pages;

import api.pojo.BoardData;
import api.utils.ApiValue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ApiBoardStepdefs {

    private static String getBoardNamesRequest = ApiValue.getBoardInfoRequest;
    private static String getBoardInfoFilterRequest = ApiValue.getBoardInfoFilterRequest;

    List<BoardData> boardList;
    List<String> boardNameList;
    List<String> finalBoardNameList;
    int startBoardsCount = 0;
    int finalBoardsCount = 0;

    @When("sent get request about existed boards")
    public void sentGetRequestAboutExistedBoards() {
        boardList = given()
                .when()
                .contentType(ContentType.JSON)
                .get(ApiValue.API_URL + getBoardNamesRequest + ApiValue.APIKEY_TOKEN_STRING)
                .then().statusCode(200)
                .extract().jsonPath().getList(".", BoardData.class);
    }

    @Then("the response is insert into list")
    public void theResponseIsInsertIntoList() {
    }

    @Then("check the result")
    public void checkTheResult() {
        boardList
                .forEach(
                        i -> Assertions.assertTrue(i.getUrl().contains(i.getName().toLowerCase()),
                                "Error: " + i.getName())
                );
    }


    @When("sent the get request about existed board names")
    public void sentTheGetRequestAboutExistedBoardNames() {
        boardNameList = getBoardNameList();
    }

    @Then("get count of boards and check that number > {int}")
    public void getCountOfBoardsAndCheckThatNumber(int arg0) {
        startBoardsCount = boardNameList.size();
    }

    @Then("the post request to create a new board with name={word}")
    public void thePostRequestToCreateANewBoardWithNameBoardName(String boardName) {
        addNewBoard(boardName);
    }

    @Then("sent the new get request about existed board names")
    public void sentTheNewGetRequestAboutExistedBoardNames() {
        boardNameList = getBoardNameList();
    }

    @And("get new count of boards")
    public void getNewCountOfBoards() {
        finalBoardsCount = boardNameList.size();
    }

    @And("get new boards name list")
    public void getNewBoardsNameList() {
        finalBoardNameList = boardNameList;
    }

    @And("check that the count of pages is changed by {int} and that the board with name={word} is exist")
    public void checkThatTheCountOfPagesIsChangedByAndThatTheBoardWithNameBoardNameIsExist(int arg0, String boardName) {
        Assertions.assertAll("add new board",
                () -> Assertions.assertEquals(1, finalBoardsCount - startBoardsCount),
                () -> Assertions.assertTrue(
                        finalBoardNameList.stream()
                                .filter(i -> i.equals(boardName))
                                .collect(Collectors.toList()).size() >0)
        );
    }

    public List<String> getBoardNameList() {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get(ApiValue.API_URL + getBoardNamesRequest + ApiValue.APIKEY_TOKEN_STRING)
                .then().statusCode(200)
                .extract().jsonPath().getList("name");
    }

    public void addNewBoard(String boardName) {
        given()
                .when()
                .contentType(ContentType.JSON)
                .post(ApiValue.API_URL + getBoardInfoFilterRequest + boardName + "&" + ApiValue.APIKEY_TOKEN_STRING)
                .then()
                .statusCode(200);
    }


}
