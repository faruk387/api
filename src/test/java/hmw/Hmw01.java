package hmw;

import base_url.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Hmw01 extends RegresBaseUrl {
     /*
        Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */
    @Test
    public void hmw01(){
        // i) Set the URL
spec.pathParams("first","api","second","users","third",3);

        //ii) Set the expected data

        // iii) Send the request and get the response
     Response response= given(spec).when().get("/{first}/{second}/{third}");
     response.prettyPrint();
        //iv) Do assertion
response.then().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");
    }
}
