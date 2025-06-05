package api.tests.userTest;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import api.endpoints.UserModel_Endpoints;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class GetUserTests {

	@Test(priority = 3)
	public void TC003() {

		Response res = UserModel_Endpoints.getUser(CreateUserTests.userPayload.getUsername()); 

		res.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.header("Content-Type", "application/json")
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UserSchema.json"))
		.log().all();

	}

	@Test(priority = 4)
	public void TC004() {

		Response res = UserModel_Endpoints.getUser("Albert_Einstein123"); // Random username

		res.then()
		.statusCode(404)
		.statusLine("HTTP/1.1 404 Not Found")
		.header("Content-Type", "application/json")
		.assertThat().body("message", equalTo("User not found"))
		.log().ifValidationFails();
	}

	@Test(priority = 5)
	public void TC005() {

		Response res = UserModel_Endpoints.getUser(""); // Empty String username

		res.then()
		.statusCode(405)
		.statusLine("HTTP/1.1 405 Method Not Allowed")
		.header("Content-Type", "application/json")
		.log().ifValidationFails();
	}
}
