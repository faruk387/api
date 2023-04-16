package get_request;

import base_url.DummyRestApiUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get13 extends DummyRestApiUrl {
      /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "[Tatyana Fitzpatrick]"
    And
        Total salary of all employees is 6,644,770
     */

@Test
    public void get13(){
    // i) Set the URL
    spec.pathParam("first","employees");


    //ii) Set the expected data


    // iii) Send the request and get the response
    Response response=given(spec).get("{first}");
    response.prettyPrint();

    //iv) Do assertion
response.then().statusCode(200).body("data",hasSize(24),
        "data.employee_name",hasItems("Garrett Winters","Garrett Winters"));
    JsonPath jsonPath=response.jsonPath();
    List<Integer> ages=jsonPath.get("data.employee_age");
    Collections.sort(ages);
    int greatestAge= ages.get(ages.size()-1);
    assertEquals(66,greatestAge);
    String nameOfTheLowestAge= jsonPath.getString("data.findAll{it.employee_age=="+ages.get(0)+"}.employee_name");
assertEquals("[Tatyana Fitzpatrick]",nameOfTheLowestAge);
List<Integer> salaries=jsonPath.getList("data.employee_salary");
//first way
    int sumOfSalaries=0;
    for(Integer w:salaries){
        sumOfSalaries=sumOfSalaries+w;
    }
    assertEquals(6644770,sumOfSalaries);
    //Second way
   int sumOfSalariesLambda= salaries.stream().reduce(0,Math::addExact);
    assertEquals(6644770,sumOfSalariesLambda);
}


}
