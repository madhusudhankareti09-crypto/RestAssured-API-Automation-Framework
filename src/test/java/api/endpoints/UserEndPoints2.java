package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static api.endpoints.Routes.get_url;
import static io.restassured.RestAssured.given;

public class UserEndPoints2 {

   static ResourceBundle getURL(){
       //Load Properties file
        ResourceBundle routes=ResourceBundle.getBundle("routes");
        return routes;
    }


    public static Response createUser(User payload){

       String  post_url=getURL().getString("post_url");

        Response response
                = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url+"/user");
        return response;
    }
    public static Response readUser(String username){
        String  get_url=getURL().getString("get_url");

        Response response
                = given()
                .pathParam("username",username)
                .when()
                .get(get_url+"/user/{username}");
        return response;
    }

    public static Response updateUser(String username,User payload){
        String  update_url=getURL().getString("update_url");
        Response response
                = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)
                .when()
                .put(update_url+"/user/{username}");
        return response;
    }

    public static Response deleteUser(String username){
        String  delete_url=getURL().getString("delete_url");
        Response response
                = given()
                .pathParam("username",username)
                .when()
                .delete(delete_url+"/user/{username}");
        return response;
    }

}
