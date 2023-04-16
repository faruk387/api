package hmw;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Hmw04 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"

 */
    @Test
    public void hmw04(){
        // i) Set the URL
        spec.pathParam("first","booking").queryParams("firstname","Brandon","lastname","Wilson");
        //ii) Set the expected data
Response response=given(spec).when().get("/{first}");
response.prettyPrint();

        // iii) Send the request and get the response

        //iv) Do assertion
        assertEquals(200,response.statusCode());
        assertTrue(response.asString().contains("booingid"));
    }

}
