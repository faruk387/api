package get_request;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {
     /*
       Given
           https://restful-booker.herokuapp.com/booking
       When
           User sends get request to the URL
       Then
           Status code is 200
         And
             Among the data there should be someone whose firstname is "John" and lastname is "Smith"
    */
    @Test
    public void get05(){
      //  i) Set the URL
        spec.pathParam("first","booking").queryParams("firstname","John","lastname","Smith");
      //  ii) Set the expected data

      //  iii) Send the request and get the response
Response response=given().spec(spec).when().get("{first}");
response.prettyPrint();

     //   iv) Do assertion
        //first way
        response.then().statusCode(200);
        //second way
        assertEquals(200,response.statusCode());
        assertTrue(response.asString().contains("bookingid"));
    }
}
