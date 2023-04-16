package utils;



import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {
    //new ObjectMapper().readValue(response.asString(),BookingResponsePojo.class);
   // Thi method will accept json data as String,then convert it in any data type
    public static <T> T convertJsonToJavaObject(String json,Class<T> cls){//generic method
        try {
            return new ObjectMapper().readValue(json,cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
