package practice;

import base_url.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q02 extends RegresBaseUrl {
    /*
    Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }

     */
    @Test
    public void Q02(){
        // i) Set the URL
        spec.pathParams("first","api","second","users");

        //ii) Set the expected data
        Map<String,String> expectedData=new HashMap<>();
        expectedData.put("name","morpheus");
        expectedData.put("job","leader");
        System.out.println("expectedData= "+expectedData);


        // iii) Send the request and get the response
        Response response=given(spec).contentType(ContentType.JSON).body(expectedData).post("/{first}/{second}");
response.prettyPrint();
        //iv) Do assertion
      Map<String,String> actualData=response.as(HashMap.class);
        System.out.println("Actual Data= "+actualData);
        assertEquals(201,response.statusCode());
assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));

    }
}
