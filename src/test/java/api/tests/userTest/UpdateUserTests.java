package api.tests.userTest;

import static org.hamcrest.Matchers.equalTo;

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
	public void setup() {
		
		faker = new Faker();
		
		User_Payload oldUserPayload = CreateUserTests.userPayload;

		username = oldUserPayload.getUsername();

		//Setting new values => setting the oldUserPayload's field values to new values
		oldUserPayload.setId(faker.idNumber().hashCode());
		oldUserPayload.setFirstName(faker.name().firstName());
		oldUserPayload.setUserStatus(1);

		// Same object reference: renamed for clarity after modifying fields above
		newUserPayload =  CreateUserTests.userPayload;
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

		Response res = UserModel_Endpoints.updateUserWithoutPayload(this.faker.name().username()); // any random username

		res.then()
		.statusCode(405)
		.statusLine("HTTP/1.1 405 Method Not Allowed")
		.header("Content-Type", equalTo("application/json"))
		.body("message", equalTo("no data"))
		.log().ifValidationFails();

	}

	@Test(priority = 8)
	public void TC008() {		 

		Response res = UserModel_Endpoints.updateUser("", CreateUserTests.userPayload); // without any username

		res.then()
		.statusCode(405)
		.statusLine("HTTP/1.1 405 Method Not Allowed")
		.header("Content-Type", equalTo("application/json"))
		.log().ifValidationFails();

	}
}
