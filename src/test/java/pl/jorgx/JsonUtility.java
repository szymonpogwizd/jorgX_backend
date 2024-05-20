package pl.jorgx;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtility {

    private  static ObjectMapper objectMapper = new ObjectMapper();

    public  static  String toJson(Object o)
    {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (Exception e)
        {
            throw new RuntimeException("Cannot create json string", e);
        }
    }
}
