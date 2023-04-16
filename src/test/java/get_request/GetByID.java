package get_request;

import base_url.HerOkuAppBaseUrl;
import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetByID extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/246
        When
            I send GET Request to the url
        Then
            Response body should be like that;
           {
                "firstname": "John",
                "lastname": "Smith",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
     */
    @Test
    public void get09(){
        //Set the url
        spec.pathParams("first","booking","second",326);
        //ii) Set the expected data
        //iii) Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
       //iv) Do assertion
        response.then().body("firstname",
                equalTo("Josh"),
                "totalprice",equalTo(111),
                "depositpaid",equalTo(true),
                "bookingdates.checkin",equalTo("2018-01-01"),
                "bookingdates.checkout",equalTo("2019-01-01"),
                "additionalneeds",equalTo("midnight snack"),"lastname",equalTo("Allen"));

    }


}
