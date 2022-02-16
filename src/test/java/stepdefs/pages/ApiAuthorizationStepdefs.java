package stepdefs.pages;

import api.pojo.UserRootData;
import api.utils.ApiValue;
import api.utils.ConfProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class ApiAuthorizationStepdefs {

    private static String getUserInfoRequest = "members/me/?fields=id,fullName,url,username,status,email&";
    UserRootData userInfo = new UserRootData();

    @When("sent the get request about user info")
    public void sentTheGetRequestAboutUserInfo() {
        userInfo = given()
                .when()
                .header("Authorization", "Bearer " + ApiValue.TOKEN)
                .contentType(ContentType.JSON)
                .get(ApiValue.API_URL + getUserInfoRequest + ApiValue.APIKEY_TOKEN_STRING)
                .as(UserRootData.class);
    }

    @Then("the response is insert into pojo class")
    public void theResponseIsInsertIntoPojoClass() {

    }

    @And("match the result with real user info")
    public void matchTheResultWithRealUserInfo() {
        Assertions.assertAll("User profile",
                () -> Assertions.assertEquals(ConfProperties.getProperty("user"), userInfo.getFullName()),
                () -> Assertions.assertEquals(ConfProperties.getProperty("username"), userInfo.getUsername()),
                () -> Assertions.assertEquals(ConfProperties.getProperty("email"), userInfo.getEmail()),
                () -> Assertions.assertEquals(ConfProperties.getProperty("url"), userInfo.getUrl())
        );
    }
}
