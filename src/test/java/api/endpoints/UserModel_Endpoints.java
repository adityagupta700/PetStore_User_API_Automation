package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User_Payload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserModel_Endpoints {

	//POST request with payload
	public static Response createUser(User_Payload payload) {

		return given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when()
				.post(Routes.create_user_endp);
	}
	
	//POST request without payload
	public static Response createUserWithoutPayload() {

		return given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)

				.when()
				.post(Routes.create_user_endp);
	}
	
	public static Response getUser(String username) {

		return given()
				.accept(ContentType.JSON)
				.pathParam("username", username)

				.when()
				.get(Routes.get_user_endp);
	}

	//PUT request with payload
	public static Response updateUser(String username, User_Payload payload) {

		return given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)

				.when()
				.put(Routes.update_user_endp);
	}
	
	
	//PUT request without payload
	public static Response updateUserWithoutPayload(String username) {

		return given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)

				.when()
				.put(Routes.update_user_endp);
	}

	public static Response deleteUser(String username) {

		return given()
				.accept(ContentType.JSON)
				.pathParam("username", username)

				.when()
				.delete(Routes.delete_user_endp);
	}


}
