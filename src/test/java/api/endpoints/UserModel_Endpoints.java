package api.endpoints;

import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserModel_Endpoints {

	public static Response createUser(User payload) {

		return given()
				.header("accept", "application/json")
				.header("Content-Type", "application.json")
				.body(payload)

				.when()
				.post(Routes.create_user_endp);
	}

	public static Response getUser(String username) {

		return given()
				.header("accept", "application/json")
				.pathParam("username", username)

				.when()
				.get(Routes.get_user_endp);
	}

	public static Response updateUser(User payload, String username) {

		return given()
				.header("accept", "application/json")
				.header("Content-Type", "application.json")
				.pathParam("username", username)
				.body(payload)

				.when()
				.put(Routes.update_user_endp);
	}
	
	public static Response deleteUser(String username) {

		return given()
				.header("accept", "application/json")
				.pathParam("username", username)
				
				.when()
				.delete(Routes.delete_user_endp);
	}


}
