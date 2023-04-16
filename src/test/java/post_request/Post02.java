package post_request;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerOkuAppBaseUrl {
     /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
*/
    @Test
    public void post02(){
        // i) Set the URL
spec.pathParam("first","booking");

        //ii) Set the expected data
        Map<String,String> bookingDatesMap= new HerOkuAppTestData().bookingDatesMapMethod("2021-09-09","2021-09-21");
        Map<String,Object> expectedData= new HerOkuAppTestData().expectedDataMethod("John","Doe",11111,true,bookingDatesMap,null);
        System.out.println("Expected Data: "+expectedData);
        // iii) Send the request and get the response
Response response=given(spec).body(expectedData).post("{first}");
response.prettyPrint();

        //iv) Do assertion
        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println("Actual data= "+actualData);
        assertEquals(200,response.statusCode());
       assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(expectedData.get("bookingdates"),((Map)actualData.get("booking")).get("bookingdates"));
        //Since the value data type is "Object" in actualData Map,all values will return as Object data type.
        //To use map methods from object container, we need to do type casting.

    }
}
