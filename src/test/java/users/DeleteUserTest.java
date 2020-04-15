package users;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest {

    @Test
    public void deleteUserTest() {

        given()
                .contentType("application/json")
                .when().delete("/users/2").then().statusCode(204);

    }
}
