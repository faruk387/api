package get_request;

import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get07 extends PetStoreBaseUrl {
     /*
    Given
        https://petstore.swagger.io/v2/pet/3467889
    When
         User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is “application/json”
    And
        Response body should be like;
           {
           "id": 5,
    "category": {
        "id": 0,
        "name": "string"
    },
    "name": "doggie",
    "photoUrls": [
        "string"
    ],
    "tags": [
        {
            "id": 0,
            "name": "string"
        }
    ],
    "status": "string"
        }
     */
   @Test
    public void get07(){
      // i) Set the URL
       spec.pathParams("first","v2","second","pet","third","5");
       //ii) Set the expected data

       //iii) Send the request and get the response
       Response response=given().spec(spec).when().get("/{first}/{second}/{third}");
       response.prettyPrint();

       //iv) Do assertion
       //first way
       response.then().statusCode(200).
               contentType(ContentType.JSON).
               body("category.name",equalTo("string"),"name",equalTo("doggie"),"status",equalTo("string"));
       //second way
       JsonPath jsonPath=response.jsonPath();
       assertEquals("string",jsonPath.getString("category.name"));
       assertEquals("doggie",jsonPath.getString("name"));
       assertEquals("string",jsonPath.getString("status"));
       //third way
       SoftAssert softAssert=new SoftAssert();
       softAssert.assertEquals(jsonPath.getString("category.name"),"string");
       softAssert.assertEquals(jsonPath.getString("name"),"doggie");
       softAssert.assertEquals(jsonPath.getString("status"),"string");
       softAssert.assertAll();
    }

}
