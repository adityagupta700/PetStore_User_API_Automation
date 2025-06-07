package api.tests.userTest;

import org.testng.annotations.Test;

import api.endpoints.UserModel_Endpoints;
import api.payload.User_Payload;
import api.utilities.DataProviderClass;
import io.restassured.response.Response;

public class DDTests {

	@Test(priority = 1, dataProvider = "UserData", dataProviderClass = api.utilities.DataProviderClass.class)
	public void createUser(String id, String username, String firstName, String lastName, String email, String password,
			String phone, String userstatus) {

		User_Payload payload = new User_Payload();

		payload.setId(Integer.parseInt(id));
		payload.setUsername(username);
		payload.setFirstName(firstName);
		payload.setLastName(lastName);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phone);
		payload.setUserStatus(Integer.parseInt(userstatus));

		Response res = UserModel_Endpoints.createUser(payload);

		res.then().statusCode(200).header("Content-Type", "application/json");

	}

	@Test(priority = 2, dataProvider = "Username", dataProviderClass = DataProviderClass.class, dependsOnMethods = "createUser")
	public void deleteUser(String username) {

		Response res = UserModel_Endpoints.deleteUser(username);

		res.then().statusCode(200);

	}
}
