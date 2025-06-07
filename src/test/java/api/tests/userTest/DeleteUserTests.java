package api.tests.userTest;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

import com.github.javafaker.Faker;

import api.endpoints.UserModel_Endpoints;
import api.payload.User_Payload;
import io.restassured.response.Response;

public class DeleteUserTests {

	@Test(priority = 9)
	public void TC009(ITestContext context) {
		User_Payload userPayload = (User_Payload)context.getSuite().getAttribute("UserPayload");

		Response res = UserModel_Endpoints.deleteUser(userPayload.getUsername());

		res.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.header("Content-Type", "application/json")
		.body("message", equalTo(userPayload.getUsername()))
		.log().all();
		
	}

	@Test(priority = 10)
	public void TC010() {

		Faker faker = new Faker();

		Response res = UserModel_Endpoints.deleteUser(faker.name().username()); //random username 

		res.then()
		.statusCode(404)
		.statusLine("HTTP/1.1 404 Not Found")
		.log().ifValidationFails();
	}

	@Test(priority = 11)
	public void TC011() {
		Response res = UserModel_Endpoints.deleteUser(""); //empty string

		res.then()
		.statusCode(405)
		.statusLine("HTTP/1.1 405 Method Not Allowed")
		.header("Content-Type", "application/json")
		.log().ifValidationFails();
	}
}
