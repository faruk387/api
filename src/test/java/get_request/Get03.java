package get_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be “application/json”
		And
		    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
		And
		    “completed” is false
		And
		    “userId” is 2
     */
    @Test
    public void get03(){
       // i) Set the URL
       // String url="https://jsonplaceholder.typicode.com/todos/23";//This is not recommended way --> We will create base urls package
spec.pathParams("first","todos","second",23);

        //ii) Set the expected data


       // iii) Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
       // iv) Do assertion
        //1. Way
response.then().
        statusCode(200).
        contentType("application/json").
        body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
        body("userId",equalTo(2)).
        body("completed",equalTo(false));
//2. Way
        response.then().
                statusCode(200).
                contentType("application/json").
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "userId",equalTo(2),"completed",
                        equalTo(false));
    }
       /*
        Note 1: When you run code, Java stops execution after first failure.
              So, assertion after the failure were not executed.
              But assertion before the failure were passed because all of them were executed and there were no failure.
        Note 2: If you type your code as execution will stop in the first failure. This is called "Hard Assertion"
        Note 3: If you type your code as execution will NOT stop in failures. This is called "Soft Assertion"
        Note 4: If you use multiple "body()" method it will work like "Hard Assertion"
        Note 5: If you use multiple assertion in one single body() method it will work like "Soft Assertion"
         */




}
