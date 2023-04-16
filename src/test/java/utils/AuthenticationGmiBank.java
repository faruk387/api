package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationGmiBank {
    public  static String generateToken(){
        Map<String, Object> bodyMap=new HashMap<>();
        bodyMap.put("password","Mark.123");
        bodyMap.put("rememberMe",true);
        bodyMap.put("username","mark_twain");
      Response response=given().body(bodyMap).contentType(ContentType.JSON).post("https://gmibank.com/api/authenticate");
      return response.jsonPath().getString("id_token");
    }
}
