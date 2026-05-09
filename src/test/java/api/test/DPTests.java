package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilites.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DPTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
   public  void testPostUser(String UserID, String UserName, String fName,
                      String lName, String email, String pwd, String Ph
    ) {
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(UserID));
        userPayload.setUsername(UserName);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setPassword(pwd);
        userPayload.setEmail(email);
        userPayload.setPhone(Ph);

        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName (String userName){

        System.out.println("Deleting user: " + userName);

        Response response = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}