package users;

import common.BaseTest;
import dto.UserRequest;
import dto.CreateUserResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.AssertJUnit.*;

public class CreateUserTest extends BaseTest {

    @Test
    public void createUserTest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Viktor");
        userRequest.setJob("QA");

        CreateUserResponse createUserResponse = given()
                .contentType("application/json")
                .body(userRequest)
                .when().post("/users").as(CreateUserResponse.class);

        assertEquals(createUserResponse.getJob(), "QA");
        assertEquals(createUserResponse.getName(), "Viktor");
        assertFalse(createUserResponse.getId().isEmpty());
        assertFalse(createUserResponse.getCreatedAt().isEmpty());
    }

    @Test
    public void createUserWithDuplicatedParameterTest() {
        CreateUserResponse createUserResponse = given()
                .contentType("application/json")
                .body("{\"name\":\"viktor\", \"name\":\"viktor\"}")
                .when().post("/users").as(CreateUserResponse.class);

        assertEquals(createUserResponse.getName(), "viktor");
        assertFalse(createUserResponse.getId().isEmpty());
        assertFalse(createUserResponse.getCreatedAt().isEmpty());
    }

    @Test
    public void createEmptyUserTest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("");
        userRequest.setJob("");

        CreateUserResponse createUserResponse = given()
                .contentType("application/json")
                .body(userRequest)
                .when().post("/users").as(CreateUserResponse.class);

        assertTrue(createUserResponse.getJob().isEmpty());
        assertTrue(createUserResponse.getName().isEmpty());
        assertFalse(createUserResponse.getId().isEmpty());
        assertFalse(createUserResponse.getCreatedAt().isEmpty());
    }

    @Test
    public void createUserWithUnexpectedPropertyTest() {
        given()
                .contentType("application/json")
                .body("{\"weight\":\"80\"}")
                .when().post("/users").then().statusCode(201).body("weight", equalTo("80"));
    }

}
