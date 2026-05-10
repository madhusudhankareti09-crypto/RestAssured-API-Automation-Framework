package api.endpoints;

import api.payload.Pet;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetEndPoints {

    public static Response createPet(Pet payload) {
        Response response
                = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.create_pet);
        return response;
    }

    public static Response getPet(int petId) {
        Response response =
                given()
                        .pathParam("petId", petId)
                        .when()
                        .get(Routes.get_pet);

        return response;
    }

    public static Response updatePet(String petID, User payload) {
        Response response
                = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("petID", petID)
                .body(payload)
                .when()
                .put(Routes.update_pet);
        return response;
    }

    public static Response deletePet(int petID) {
        Response response
                = given()
                .pathParam("petID", petID)
                .when()
                .delete(Routes.delete_pet);
        return response;
    }

}
