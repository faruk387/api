package practice;

import base_url.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetPractice01 extends HerOkuAppBaseUrl {
    /*
   Given
           https://restful-booker.herokuapp.com/booking/555
   When
           I send GET Request to the URL
   Then
           Status code is 200
                   {
                       "firstname": "Jane",
                       "lastname": "Doe",
                       "totalprice": 111,
                       "depositpaid": true,
                       "bookingdates": {
                           "checkin": "2018-01-01",
                           "checkout": "2019-01-01"
                       },
                       "additionalneeds":  "Extra pillows please"
                   }
*/

@Test
    public void getPractice01() throws IOException {
    // i) Set the URL
    spec.pathParams("first","booking","second",520);

    //ii) Set the expected data
    BookingDatesPojo bookingDates=new BookingDatesPojo("2018-01-01","2019-01-01");
    BookingPojo expectedData=new BookingPojo("John","Smith",111,true,bookingDates,"Breakfast");
    System.out.println("expectedData = "+expectedData);

    // iii) Send the request and get the response
Response response=given(spec).body(expectedData).get("/{first}/{second}");
response.prettyPrint();

    //iv) Do assertion
    BookingPojo actualData=new ObjectMapper().readValue(response.asString(), BookingPojo.class);
    System.out.println("actualData= "+actualData);
    JsonPath jsonPath=response.jsonPath();
    SoftAssert softAssert=new SoftAssert();


    softAssert.assertEquals(200,response.statusCode());
    softAssert.assertEquals(expectedData.getFirstname(),actualData.getFirstname());
    softAssert.assertEquals(expectedData.getLastname(),actualData.getLastname());
    softAssert.assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
    softAssert.assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
    softAssert.assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
    softAssert.assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
    softAssert.assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());
    softAssert.assertAll();
}

}
