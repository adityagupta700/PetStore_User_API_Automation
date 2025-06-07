package api.tests.userTest;

import static org.hamcrest.Matchers.equalTo;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserModel_Endpoints;
import api.payload.User_Payload;
import io.restassured.response.Response;

public class CreateUserTests{

	User_Payload userPayload;
	
	@BeforeClass
	public void setup(ITestContext context) {
		Faker faker = new Faker();
		userPayload = new User_Payload();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setUsername(faker.name().username());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		context.getSuite().setAttribute("UserPayload", userPayload);
	}

	@Test(priority = 1)
	public void TC001(ITestContext context) {

		User_Payload userPayload = (User_Payload)context.getSuite().getAttribute("UserPayload");

		Response res = UserModel_Endpoints.createUser(userPayload);

		int expectedUserId = userPayload.getId();

		res.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.header("Content-Type", "application/json")
		.body("message", equalTo(String.valueOf(expectedUserId)))
		.log().all();


	}

	@Test(priority = 2)
	public void TC002() {

		Response res = UserModel_Endpoints.createUserWithoutPayload();

		res.then()
		.statusCode(405)
		.statusLine("HTTP/1.1 405 Method Not Allowed")
		.header("Content-Type", "application/json")
		.assertThat().body("message", equalTo("no data"))
		.log().ifValidationFails();

	}


}
