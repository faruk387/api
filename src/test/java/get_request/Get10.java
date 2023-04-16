package get_request;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/9525
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
public void get10(){
        //Set the url
        spec.pathParams("first","booking","second",2986);
        //Set the expected data
        Map<String,String> bookingDateMap=new HashMap<>();
        bookingDateMap.put("checkin","2018-01-01");
        bookingDateMap.put("checkout","2019-01-01");
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstname","John");
        expectedData.put("lastname","Smith");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingDateMap);
        expectedData.put("additionalneeds","Breakfast");
        System.out.println("Expected Data ="+expectedData);
        //Send the request and get the response
        Response response=given(spec).get("/{first}/{second}");
        response.prettyPrint();
        //Do assertion
        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println("acutal data= "+actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals( ((Map)expectedData.get("bookingdates")).get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals( ((Map)expectedData.get("bookingdates")).get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
}
@Test//Recommended
        public void get10b(){
                //Set the url
                spec.pathParams("first","booking","second",2986);
                //Set the expected data
                Map<String,String> bookingDateMap=new HerOkuAppTestData().bookingDatesMapMethod("2018-01-01","2019-01-01");

                Map<String,Object> expectedData=new HerOkuAppTestData().expectedDataMethod("John","Smith",111,true,bookingDateMap,"Breakfast");

                System.out.println("Expected Data ="+expectedData);
                //Send the request and get the response
                Response response=given(spec).get("/{first}/{second}");
                response.prettyPrint();
                //Do assertion
                Map<String,Object> actualData=response.as(HashMap.class);
                System.out.println("acutal data= "+actualData);

                assertEquals(200,response.statusCode());
                assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
                assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
                assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
                assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
                assertEquals( ((Map)expectedData.get("bookingdates")).get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
                assertEquals( ((Map)expectedData.get("bookingdates")).get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        }
}
