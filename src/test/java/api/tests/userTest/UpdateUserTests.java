package api.tests.userTest;

import static org.hamcrest.Matchers.equalTo;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserModel_Endpoints;
import api.payload.User_Payload;
import io.restassured.response.Response;

public class UpdateUserTests {
	
	Faker faker;
	User_Payload newUserPayload;
	String username;
	
	@BeforeClass
	public void updateSetup(ITestContext context) {
		
		faker = new Faker();
		
		newUserPayload = (User_Payload)context.getSuite().getAttribute("UserPayload");

		username = newUserPayload.getUsername();

		//Setting new values => setting the old UserPayload's field values to new values
		newUserPayload.setId(faker.idNumber().hashCode());
		newUserPayload.setFirstName(faker.name().firstName());
		newUserPayload.setUserStatus(1);
		
		context.getSuite().setAttribute("UserPayload", newUserPayload); // setting the new payload's value to previous payload
	}

	@Test(priority = 6)
	public void TC006() {		

		int newUserId = newUserPayload.getId();

		Response res = UserModel_Endpoints.updateUser(username, newUserPayload);

		System.out.println(res.getBody().asPrettyString());
		
		res.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.header("Content-Type", "application/json")
		.body("message", equalTo(String.valueOf(newUserId)))
		.log().all();


	}

	@Test(priority = 7)
	public void TC007() {		 

		Response res = UserModel_Endpoints.updateUserWithoutPayload(faker.name().username()); // any random username

		res.then()
		.statusCode(405)
		.statusLine("HTTP/1.1 405 Method Not Allowed")
		.header("Content-Type", equalTo("application/json"))
		.body("message", equalTo("no data"))
		.log().ifValidationFails();

	}

	@Test(priority = 8)
	public void TC008() {		 

		Response res = UserModel_Endpoints.updateUser("", newUserPayload); // without any username

		res.then()
		.statusCode(405)
		.statusLine("HTTP/1.1 405 Method Not Allowed")
		.header("Content-Type", equalTo("application/json"))
		.log().ifValidationFails();

	}
}
