package herokuapp_smoketest;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utils.ObjectMapperUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S1_Post extends HerOkuAppBaseUrl {
/*
Given
https://restful-booker.herokuapp.com/booking

And

 {
            "firstname" : "Jim",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
  When

  Send post request

  Then

  Status code should be 200

  And

  Response body should be:
   {
            "bookingid": 5729,
            "booking": {
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
        }

 */
public static int bookingId; //we put the created bookingid into a container and we use it in other class


@Test
    public void s1post()  {

    // i) Set the URL

    spec.pathParam("first","booking");
    //ii) Set the expected data
    BookingDatesPojo bookingDates=new BookingDatesPojo("2018-01-01","2019-01-01");
    BookingPojo expectedData=new BookingPojo("Jim","Brown",111,true,bookingDates,"Breakfast");
    System.out.println("Expected data= "+expectedData);
    // iii) Send the request and get the response
    Response response=given(spec).body(expectedData).post("{first}");
    response.prettyPrint();
    //iv) Do assertion
   BookingResponsePojo actualData= ObjectMapperUtils.convertJsonToJavaObject(response.asString(),BookingResponsePojo.class);
   //With convertJsonToJavaObject(9 method we handled throw exception issue
   System.out.println("Actual Data= "+actualData);
assertEquals(200,response.statusCode());
assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
    assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
    assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
    assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
    assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
    assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
    assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());
 bookingId= actualData.getBookingid();
}


}
