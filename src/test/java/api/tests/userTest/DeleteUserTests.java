package api.tests.userTest;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserModel_Endpoints;
import io.restassured.response.Response;

public class DeleteUserTests {

	@Test
	public void TC009() {
		Response res = UserModel_Endpoints.deleteUser(CreateUserTests.userPayload.getUsername());

		res.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.header("Content-Type", "application/json");
	}
	
	@Test
	public void TC010() {
		
		Faker faker = new Faker();
		
		Response res = UserModel_Endpoints.deleteUser(faker.name().username()); //random username 

		res.then()
		.statusCode(404)
		.statusLine("HTTP/1.1 404 Not Found");
	}
	
	@Test
	public void TC011() {
		Response res = UserModel_Endpoints.deleteUser(""); //empty string

		res.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 405 Method Not Allowed")
		.header("Content-Type", "application/json");
	}
}
