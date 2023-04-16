package get_request;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;


import java.lang.ref.SoftReference;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/32
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
                {
                    "firstname": "Jane",
                    "lastname": "Doe",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Extra pillows please"
                }

     */
    @Test
    public void get06(){
        //i) Set the URL
        spec.pathParams("first","booking","second",55);
        //ii) Set the expected data

        //iii) Send the request and get the response
Response response=given(spec).when().get("/{first}/{second}");
response.prettyPrint();
        //iv) Do assertion
        //first way
response.then().statusCode(200).
        contentType(ContentType.JSON).
        body("firstname",equalTo("John"),
                "lastname",equalTo("Smith"),
                "totalprice",equalTo(111),
                "depositpaid",equalTo(true),
                "bookingdates.checkin",equalTo("2018-01-01"),
                "bookingdates.checkout",equalTo("2019-01-01"),
                "additionalneeds",equalTo("Breakfast"));
//2. way: we will use JsonPAth class
        JsonPath jsonPath=response.jsonPath();//we can extract the data from body to outside with jsonpath
        System.out.println(jsonPath.getInt("totalprice"));
        assertEquals("John",jsonPath.getString("firstname"));
        assertEquals("Smith",jsonPath.getString("lastname"));
        assertEquals(111,jsonPath.getInt("totalprice"));
        assertEquals(true,jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01",jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast",jsonPath.getString("additionalneeds"));
        //Soft Assertion
        //To do soft assertion flow these 3 step
        //1.step: Create SoftAsser object
        SoftAssert softAssert=new SoftAssert();
        //2.Step: Do assertion by using SoftAssert object
        softAssert.assertEquals(jsonPath.getString("firstname"),"John","first name did not match");
        softAssert.assertEquals(jsonPath.getString("lastname"),"Smith","last name did not match");
        softAssert.assertEquals(jsonPath.getInt("totalprice"),111,"total price did not match");
        softAssert.assertEquals(jsonPath.getBoolean("depositpaid"),true,"deposit paid did not match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2018-01-01","checkin did not match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"),"2019-01-01","checkout did not match");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast","additional needs did not match");
        //3.step use assertAll();
        softAssert.assertAll();




    }
}
