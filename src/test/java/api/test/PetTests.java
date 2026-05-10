package api.test;

import api.endpoints.PetEndPoints;
import api.endpoints.UserEndPoints;
import api.payload.Pet;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.payload.Tag;
import api.payload.Category;

import java.util.Arrays;


public class PetTests {

    Faker faker;
    Pet petPayload;

    public Logger logger;//for logs

    @BeforeClass
    void setup() {
        faker = new Faker();
        petPayload = new Pet();

        // Category
        Category category = new Category();
        category.setId(faker.number().numberBetween(1, 10));
        category.setName(faker.animal().name());

        // Tag 1
        Tag tag1 = new Tag();
        tag1.setId(faker.number().numberBetween(1, 100));
        tag1.setName(faker.bool().bool() ? "friendly" : "aggressive");

        // Tag 2
        Tag tag2 = new Tag();
        tag2.setId(faker.number().numberBetween(101, 200));
        tag2.setName("vaccinated");

        // Pet Payload
        petPayload.setId(faker.number().numberBetween(100, 1000));

        petPayload.setCategory(category);

        petPayload.setName(faker.dog().name());

        petPayload.setPhotoUrls(Arrays.asList(
                faker.internet().image(),
                faker.internet().image()
        ));

        petPayload.setTags(Arrays.asList(tag1, tag2));

        petPayload.setStatus("available");

        System.out.println(petPayload);

        logger = LogManager.getLogger(this.getClass());

        logger.debug("debug.........");
    }


    @Test(priority = 1)
    public void testPostPet() {
        logger.info("*********Creating Pet********");
        Response response = PetEndPoints.createPet(petPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("*********Pet is Created********");
    }

    @Test(priority = 2)
    public void testGetPetByID() {
        logger.info("*********Getting User Info********");
        Response response = PetEndPoints.getPet(this.petPayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("*********User Info is Displayed********");

    }

    @Test(priority = 3)
    public void testDeletePetByID() {
        logger.info("*********Delete User********");

        Response response = PetEndPoints.deletePet(petPayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("*********Pet is Deleted********");

    }
}

