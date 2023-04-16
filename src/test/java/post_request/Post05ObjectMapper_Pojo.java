package post_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {
       /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2) {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
          }


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
    public void post05() throws IOException {
        // i) Set the URL
        spec.pathParam("first","todos");

        //ii) Set the expected data
        JsonPlaceHolderPojo expectedData=new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expectedData = "+expectedData);
        // iii) Send the request and get the response
Response response=given(spec).body(expectedData).post("{first}");
response.prettyPrint();
        //iv) Do assertion

      JsonPlaceHolderPojo actualData= new ObjectMapper().readValue(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println("Actual data = "+actualData);
        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
    }
}
