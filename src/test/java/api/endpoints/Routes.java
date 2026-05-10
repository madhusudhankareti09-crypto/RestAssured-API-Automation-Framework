package api.endpoints;

public class Routes {

    public static String base_url = "https://petstore.swagger.io/v2";

    //User_Module
    public static String post_url = base_url + "/user";
    public static String get_url = base_url + "/user/{username}";
    public static String update_url = base_url + "/user/{username}";
    public static String delete_url = base_url + "/user/{username}";
    //Pet_Module
    public static String create_pet=base_url + "/pet";
    public static String update_pet=base_url + "/pet";
    public static String get_pet=base_url + "/pet/{petId}";
    public static String delete_pet=base_url+"/pet/{petID}";




    //Store_Module
}
