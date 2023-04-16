package put_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Put01 extends JsonPlaceHolderBaseUrl {
     /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "userId": 21,
             "title": "Wash the dishes",
             "completed": false
           }
    When
I send PUT Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 21,
                   "title": "Wash the dishes",
                   "completed": false
                  }
 */
    @Test
    public void put01(){
        // i) Set the URL
        spec.pathParams("first","todos","second",198);

        //ii) Set the expected data
        Map<String,Object> expectedData= new JsonPlaceHolderTestData().expectedData(21,"Wash the dishes",false);
        System.out.println("expected data : "+expectedData);
        // iii) Send the request and get the response
Response response=given(spec).body(expectedData).put("/{first}/{second}");
response.prettyPrint();
//iv) Do assertion
        Map<String,Object> actualData=response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("complete"),actualData.get("complete"));

    }
}
