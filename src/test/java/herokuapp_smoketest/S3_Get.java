package herokuapp_smoketest;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utils.ObjectMapperUtils;

import static herokuapp_smoketest.S1_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S3_Get extends HerOkuAppBaseUrl {
    /*
       Given
           https://restful-booker.herokuapp.com/booking/{id}
       When
           Send the get request
       Then
           Status Code should be 200
       And
           Response body should be
           {
               "firstname": "Mark",
               "lastname": "Twain",
               "totalprice": 555,
               "depositpaid": false,
               "bookingdates": {
                   "checkin": "2023-01-01",
                   "checkout": "2024-01-01"
               },
               "additionalneeds": "Extra Pillow"
           }

        */
    @Test
    public void getTest(){
        // i) Set the URL
        spec.pathParams("first","booking","second",bookingId);

        //ii) Set the expected data
        BookingDatesPojo bookingDates=new BookingDatesPojo("2023-01-01","2024-01-01");
        BookingPojo expectedData=new BookingPojo("Mark","Twain",555,false,bookingDates,"Extra Pillow");
        System.out.println("Expected data= "+expectedData);
        // iii) Send the request and get the response
Response response=given(spec).body(expectedData).get("{first}/{second}");
response.prettyPrint();
        //iv) Do assertion
   BookingPojo actualData=     ObjectMapperUtils.convertJsonToJavaObject(response.asString(),BookingPojo.class);
        System.out.println("Actual Data= "+actualData );
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());
    }
}
