package com.striveonger.common.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * JSON 辅助工具
 * @author Mr.Lee
 * @since 2023-02-24 10:42
 */
public class JacksonUtils {

    private static final Logger log = LoggerFactory.getLogger(JacksonUtils.class);
    private static final TypeReference<Map<String, Object>> mapType = new TypeReference<>() {};
    private static final ObjectMapper mapper;

    static {
        mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .build();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String toJSONString(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("Jackson Convert String error ", e);
        }
        return null;
    }

    public static Map<String, Object> toMap(String s) {
        try {
            return mapper.readValue(s, mapType);
        } catch (JsonProcessingException e) {
            log.error("Jackson Convert Map error ", e);
        }
        return null;
    }

    public static <T> T toObject(String s, Class<T> clazz) {
        try {
            return mapper.readValue(s, clazz);
        } catch (JsonProcessingException e) {
            log.error("Jackson Convert Object error ", e);
        }
        return null;
    }

    public static <T> T toObject(String s, TypeReference<T> type) {
        try {
            return mapper.readValue(s, type);
        } catch (JsonProcessingException e) {
            log.error("Jackson Convert Object error ", e);
        }
        return null;
    }

    public static byte[] toJSONBytes(Object o) {
        String str = toJSONString(o);
        return str == null ? null : str.getBytes(StandardCharsets.UTF_8);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static JsonNode toJsonNode(String props) {
        try {
            return mapper.readTree(props);
        } catch (JsonProcessingException e) {
            // throw new CustomException(ResultStatus.READ_JSON_FAIL);
            log.error("Jackson Convert JsonNode error ", e);
        }
        return null;
    }

    public static JsonNode toJsonNode(Object object) {
        return mapper.valueToTree(object);
    }

    public static ObjectNode toObjectNode(String props) {
        JsonNode node = toJsonNode(props);
        if (node instanceof ObjectNode object) {
            return object;
        }
        log.warn("Jackson Convert ObjectNode error, props is not ObjectNode");
        return null;
    }

    public static ArrayNode toArrayNode(String props) {
        JsonNode node = toJsonNode(props);
        if (node instanceof ArrayNode array) {
            return array;
        }
        log.warn("Jackson Convert ArrayNode error, props is not ArrayNode");
        return null;
    }

    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    public static <T> T eval(JsonNode node, String path) {
        return eval(node.toString(), path);
    }

    public static <T> T eval(String json, String path) {
        return JsonPath.read(json, path);
    }

    public static JSONBuilder builder() {
        return JSONBuilder.builder();
    }

    public static JSONBuilder builder(String split) {
        return JSONBuilder.builder(split);
    }

    public static JSONBuilder builder(Map<String, Object> root) {
        return JSONBuilder.builder(null, root);
    }

    public static JSONBuilder builder(String split, Map<String, Object> root) {
        return JSONBuilder.builder(split, root);
    }

}
