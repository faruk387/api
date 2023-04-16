package herokuapp_smoketest;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.S1_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S5_Delete extends HerOkuAppBaseUrl {

 /*
    Given
        https://restful-booker.herokuapp.com/booking/{id}
    When
        Send delete request
    Then
        Status code should be 201
    And
        Response body should be "Created"
     */

    @Test
    public void deleteTest(){
        // i) Set the URL
        spec.pathParams("first","booking","second",bookingId);

        //ii) Set the expected data
String expectedData="Created";

        // iii) Send the request and get the response

        Response response=given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //iv) Do assertion
        assertEquals(201,response.statusCode());
        assertEquals(expectedData,response.asString());
    }
}
