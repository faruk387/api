package get_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 {
    /*
       Given
           https://restful-booker.herokuapp.com/booking/0
       When
           User send a GET Request to the url
       Then
           HTTP Status code should be 404
       And
           Status Line should be HTTP/1.1 404 Not Found
       And
           Response body contains "Not Found"
       And
           Response body does not contain "TechProEd"
       And
           Server is "Cowboy"
    */
    @Test
    public  void get02(){
        //i) Set the URL
        String url="https://restful-booker.herokuapp.com/booking/0";
        //ii) Set the expected data

        //iii) Send the request and get the response
        Response response=given().when().get(url);
        response.prettyPrint();
        //iv) Do assertion
        response.then().
                assertThat().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found");
        //How to assert if response body contains any data
        //Response body contains "Not found"
        assertEquals("Not Found",response.asString());//assertEquals() checks if the expected data and actual data matches
        assertFalse(response.asString().contains("TechProEd"));//assertFalse() passes if the value between  parenthesis "false"
        assertTrue(response.header("Server").contains("Cowboy"));//assertTrue() passes if the value between  parenthesis "false"
        assertEquals("Cowboy","Server");//2nd way ==> Recommended

    }
}
