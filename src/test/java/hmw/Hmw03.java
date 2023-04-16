package hmw;

import base_url.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Hmw03 extends RegresBaseUrl {
      /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be “application/json”
       And
           “email” is “janet.weaver@reqres.in”,
       And
           “first_name” is "Janet"
       And
           “last_name” is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */
    @Test
    public void hmw03(){
        // i) Set the URL
        spec.pathParams("first","api","second","users","third",2);
        //ii) Set the expected data

        // iii) Send the request and get the response
        Response response=given(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();
        //iv) Do assertion
        response.then().statusCode(200).contentType(ContentType.JSON).body("data.first_name",equalTo("Janet"),"data.email",equalTo("janet.weaver@reqres.in"),"data.last_name",equalTo("Weaver"),"support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }
}
