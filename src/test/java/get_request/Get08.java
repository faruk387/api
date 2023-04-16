package get_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get08 extends JsonPlaceHolderBaseUrl {
     /*
        Given
              https://jsonplaceholder.typicode.com/todos
      When
          I send GET Request to the URL
      Then
          1)Status code is 200
          2)Print all ids greater than 190 on the console
            Assert that there are 10 ids greater than 190
          3)Print all userIds whose ids are less than 5 on the console
            Assert that the number of userIds whose ids are less than 5 is 4
          4)Print all titles whose ids are less than 5
            Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */
    @Test
    public void get08(){
        //Set the url
        spec.pathParam("first","todos");
        //Set the expected data
        // Send the request and get the response
       Response response= given().spec(spec).when().get("{first}");
       response.prettyPrint();
       //Do assertion
        //1)Status code is 200
        assertEquals(200,response.statusCode());

        //2)Print all ids greater than 190 on the console
        JsonPath jsonPath=response.jsonPath();
        List<Integer> idList=jsonPath.getList("id");
        System.out.println("id list: "+idList);
        //Assert that there are 10 ids greater than 190
        //1. way: By using foreach loop
        int idsGreaterThan190=0;
        for(int w: idList){
            if(w>190){
              idsGreaterThan190++;
            }
        }
        System.out.println("idsGreaterThan190: "+idsGreaterThan190);
        assertEquals(10,idsGreaterThan190);
        //2. way: Recommended
      List<Objects> intlistGroovy=jsonPath.getList("findAll{it.id>190}.id");//Groovy: Java based programming language
      assertEquals(10,intlistGroovy.size());
        //3)Print all userIds whose ids are less than 5 on the console
      List<Integer> userIdList= jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("user id list : "+userIdList);
       // Assert that the number of userIds whose ids are less than 5 is 4
assertEquals(4,userIdList.size());
        //4)Print all titles whose ids are less than 5
List<Object> listTitles=jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("list titles: "+listTitles);
        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
assertTrue(listTitles.contains("delectus aut autem"));
    }

}
