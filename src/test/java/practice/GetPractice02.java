package practice;

import base_url.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class GetPractice02 extends PetStoreBaseUrl {
       /*
    Given
    https://petstore.swagger.io/v2/pet/findByStatus?status=available
    When
    User sens Get request
    Then
    Assert that number of pets whose status is "available" is more than 100
            */
    @Test
    public void getPractice02(){
        // i) Set the URL

        spec.pathParams("first","v2","second","pet","third","findByStatus").queryParams("status","available");

        //ii) Set the expected data


        // iii) Send the request and get the response
        Response response=given(spec).get("/{first}/{second}/{third}");
        response.prettyPrint();

        //iv) Do assertion
        int availablePetNumber=response.jsonPath().getList("").size();
        System.out.println("availablePetNumber= "+availablePetNumber);
        assertTrue(availablePetNumber>100);
    }
}
