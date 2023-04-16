package practice;

import base_url.RegresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q01 extends RegresBaseUrl {
/*
    Given
    https://reqres.in/api/unknown/
    When
    I send GET Request to the URL
            Then
            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
    Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
    Assert that the number of names whose ids are less than 3 is 2
            */
   @Test
    public void Q01(){
//Set the url
       spec.pathParams("first","api","second","unknown");
       //ii) Set the expected data

       // iii) Send the request and get the response
      Response response= given(spec).get("/{first}/{second}");
response.prettyPrint();
       //iv) Do assertion
       assertEquals(200,response.statusCode());


JsonPath jsonPath=response.jsonPath();

       List<String> pantoneValueList =jsonPath.getList("data.pantone_value");
       System.out.println("panto value lis= "+pantoneValueList);
       List<Integer> idGreaterThen3=jsonPath.getList("data.findAll{it.id>3}.id");
       System.out.println("idGreaterThen3= "+idGreaterThen3);
assertEquals(3,idGreaterThen3.size());
       List<String> nameIdLessThen3=jsonPath.getList("data.findAll{it.id<3}.name");
       System.out.println("nameIdLessThen3= "+nameIdLessThen3);
       assertEquals(2,nameIdLessThen3.size());
   }
}
