package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {
    public Map<String,String> bookingDatesMapMethod(String checkin,String checkout){
        Map<String,String> bookingDateMap=new HashMap<>();
        bookingDateMap.put("checkin",checkin);
        bookingDateMap.put("checkout",checkout);
return bookingDateMap;
    }
    public Map<String,Object> expectedDataMethod(String firstname,String lastname,Integer totalprice,Boolean depositpaid,Map<String,String> bookingDateMap,String addditionalneeds){
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstname",firstname);
        expectedData.put("lastname",lastname);
        expectedData.put("totalprice",totalprice);
        expectedData.put("depositpaid",depositpaid);
        expectedData.put("bookingdates",bookingDateMap);
        if(addditionalneeds!=null){
            expectedData.put("additionalneeds",addditionalneeds);
        }

        return expectedData;
    }
}
