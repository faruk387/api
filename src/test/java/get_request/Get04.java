package get_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.reset;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
	 	    I send a GET request to the Url
	    And
	        Accept type is “application/json”
	    Then
	        HTTP Status Code should be 200
	    And
	        Response format should be "application/json"
	    And
	        There should be 200 todos
	    And
	        "quis eius est sint explicabo" should be one of the todos title
	    And
	        2, 7, and 9 should be among the userIds
     */
    @Test
    public void get04(){
        //i) Set the URL
  spec.pathParam("first","todos");

        //ii) Set the expected data

        //iii) Send the request and get the response
Response response=given().spec(spec).when().get("{first}");
response.prettyPrint();
        //iv) Do assertion
        response.then().
                contentType("application/json").
                statusCode(200).
                body("userId",hasSize(200),"title",hasItem("quis eius est sint explicabo"),"userId",hasItems(2,7,9));
    }

}
