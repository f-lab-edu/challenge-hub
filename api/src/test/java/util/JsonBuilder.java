package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonBuilder {
    private final Map<String, Object> map = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonBuilder add(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public String build() throws JsonProcessingException {
        return objectMapper.writeValueAsString(map);
    }
}
