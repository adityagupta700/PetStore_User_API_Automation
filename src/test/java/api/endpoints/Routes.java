package api.endpoints;

public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2/";
	
	//User module
	public static String create_user_endp = base_url + "user";
	public static String get_user_endp = base_url + "user/{username}";
	public static String update_user_endp = base_url + "user/{username}";
	public static String delete_user_endp = base_url + "user/{username}";
}
