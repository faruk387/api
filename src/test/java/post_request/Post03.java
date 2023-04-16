package post_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Post03 extends JsonPlaceHolderBaseUrl {
    /*
     Given
        https://jsonplaceholder.typicode.com/todos
        {
        "userId": 55,
        "title": "Tidy your room",
        "completed": false
        }
    When
        I send POST Request to the Url
    Then
        Status code is 201
    And
        response body is like {
                                "userId": 55,
                                "title": "Tidy your room",
                                "completed": false,
                                "id": 201
                                }
 */

    @Test
    public void post03(){
        // i) Set the URL
spec.pathParam("first","todos");

        //ii) Set the expected data
JsonPlaceHolderPojo expectedData=new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("Expected data: "+expectedData);
        // iii) Send the request and get the response
Response response=given(spec).body(expectedData).post("{first}");
response.prettyPrint();
        //iv) Do assertion
       JsonPlaceHolderPojo actualData=response.as(JsonPlaceHolderPojo.class);//De-serialization
        System.out.println("Actualdata= "+actualData);
        assertEquals(201,response.statusCode());
       assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());

    }
}
