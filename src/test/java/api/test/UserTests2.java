package api.test;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserTests2 {
    Faker faker;
    User userPayload;

    public Logger logger;//for logs

    @BeforeClass
    void setup(){
        faker=new Faker();
        userPayload=new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setPassword(faker.internet().password());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //Initiated logs
        logger= LogManager.getLogger(this.getClass());
        logger.debug("debug.........");

    }

    @Test(priority = 1)
    public void testPostUser()
    {
        logger.info("*********Creating User********");
        Response response= UserEndPoints2.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("*********User is Created********");

    }

    @Test(priority = 2)
    public void testGetUserByName()
    {
        logger.info("*********Getting User Info********");
        Response response= UserEndPoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("*********User Info is Displayed********");

    }

    @Test(priority = 3)
    public void testUpdateUser()
    {
        logger.info("*********Update User Info********");

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response= UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

        Response responseAfterupdate= UserEndPoints2.readUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterupdate.getStatusCode(),200);

        logger.info("*********User Info is Updated********");

    }

    @Test(priority = 4)
    public void testDeleteUserByName()
    {
        logger.info("*********Delete User********");

        Response response= UserEndPoints2.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("*********User is Deleted********");

    }

}
