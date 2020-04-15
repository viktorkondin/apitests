package users;

import common.BaseTest;
import dto.UpdateUserResponse;
import dto.UserRequest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.AssertJUnit.*;
import static org.testng.AssertJUnit.assertFalse;

public class UpdateUserTest extends BaseTest {

    @Test
    public void updateUserTest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Viktor");
        userRequest.setJob("QA");

        UpdateUserResponse updateUserResponse = given()
                .contentType("application/json")
                .body(userRequest)
                .when().put("/users").as(UpdateUserResponse.class);

        assertEquals(updateUserResponse.getJob(), "QA");
        assertEquals(updateUserResponse.getName(), "Viktor");
        assertFalse(updateUserResponse.getUpdatedAt().isEmpty());

    }

    @Test
    public void updateUserWithEmptyDataTest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("");
        userRequest.setJob("");

        UpdateUserResponse updateUserResponse = given()
                .contentType("application/json")
                .body(userRequest)
                .when().put("/users").as(UpdateUserResponse.class);

        assertTrue(updateUserResponse.getJob().isEmpty());
        assertTrue(updateUserResponse.getName().isEmpty());
        assertFalse(updateUserResponse.getUpdatedAt().isEmpty());
    }

    @Test
    public void updateUserWithUnexpectedPropertyTest() {
        given()
                .contentType("application/json")
                .body("{\"weight\":\"80\"}")
                .when().put("/users").then().statusCode(200).body("weight", equalTo("80"));
    }
}
