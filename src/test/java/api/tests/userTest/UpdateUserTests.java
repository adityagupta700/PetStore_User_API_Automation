package api.tests.userTest;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserModel_Endpoints;
import api.payload.User_Payload;
import io.restassured.response.Response;

public class UpdateUserTests {
	
	Faker faker;
	
	@Test
	public void TC006() {		
		
		User_Payload oldUserPayload = CreateUserTests.userPayload;
		
		String Username = oldUserPayload.getUsername();
		
		Faker faker = new Faker();
		
		//Setting new values => converting the oldUserPayload's field to new ones
		oldUserPayload.setId(faker.idNumber().hashCode());
		oldUserPayload.setFirstName(faker.name().firstName());
		oldUserPayload.setUserStatus(1);
		
		User_Payload newUserPayload = CreateUserTests.userPayload;  // after changing the field values old => new
		
		int newUserId = newUserPayload.getId(); 
		
		Response res = UserModel_Endpoints.updateUser(Username, newUserPayload);
		
		res.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.header("Content-Type", "application/json")
		.body("message", equalTo(String.valueOf(newUserId)));
	}
	
	@Test
	public void TC007() {		 
		
		Response res = UserModel_Endpoints.updateUserWithoutPayload(faker.name().username()); // any random username
		
		res.then()
		.statusCode(405)
		.statusLine("HTTP/1.1 405 Method Not Allowed")
		.header("Content-Type", equalTo("application/json"))
		.body("message", equalTo("no data"));
		
	}
	
	@Test
	public void TC008() {		 
		
		Response res = UserModel_Endpoints.updateUser("", CreateUserTests.userPayload); // any random username
		
		res.then()
		.statusCode(405)
		.statusLine("HTTP/1.1 405 Method Not Allowed")
		.header("Content-Type", equalTo("application/json"));
		
	}
}
