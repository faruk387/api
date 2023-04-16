package hmw;

import base_url.RegresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anEmptyMap;

public class Hmw02 extends RegresBaseUrl {
     /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty

     */
@Test
    public void hmw02(){
    // i) Set the URL
    spec.pathParams("first","api","second","users","third",23);
    //ii) Set the expected data

    // iii) Send the request and get the response
Response response=given(spec).when().get("/{first}/{second}/{third}");
response.prettyPrint();
    //iv) Do assertion
    response.then().statusCode(404).statusLine("HTTP/1.1 404 Not Found").header("Server","cloudflare").body("",anEmptyMap());
}
}
