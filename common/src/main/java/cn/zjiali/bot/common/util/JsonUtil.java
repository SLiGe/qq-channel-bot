package cn.zjiali.bot.common.util;

import cn.zjiali.bot.common.exception.JsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private JsonUtil() {
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * 将对象序列化为JSON格式的字符串
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    /**
     * 将JSON格式的字符串反序列化为对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) throws IOException {
        return objectMapper.readValue(json, typeReference);
    }

    /**
     * 将JSON格式的字符串反序列化为List对象
     */
    public static <T> List<T> fromJsonToList(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    /**
     * 将JSON格式的字符串反序列化为Map对象
     */
    public static <K, V> Map<K, V> fromJsonToMap(String json, Class<K> keyClazz, Class<V> valueClazz) throws IOException {
        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(Map.class, keyClazz, valueClazz));
    }

    /**
     * 将JSON格式的字符串解析为JsonNode对象
     */
    public static JsonNode parseJson(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }
}