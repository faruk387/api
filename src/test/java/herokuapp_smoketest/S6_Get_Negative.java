package herokuapp_smoketest;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.S1_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class S6_Get_Negative extends HerOkuAppBaseUrl {
  /*
    Given
        https://restful-booker.herokuapp.com/booking/{id}
    When
        Send get request
    Then
        Status code should be 404
    And
         Response body should be "Not Found"
     */

    @Test
    public void getTestNegative(){
        // i) Set the URL
        spec.pathParams("first","booking","second",bookingId);

        //ii) Set the expected data
        String expectedData="Not Found";

        // iii) Send the request and get the response
      Response response= given(spec).get("{first}/{second}");
     response.prettyPrint();
        //iv) Do assertion
        assertEquals(404,response.statusCode());
        assertEquals(expectedData,response.asString());
    }
}
