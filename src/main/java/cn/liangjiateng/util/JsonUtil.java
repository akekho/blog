package cn.liangjiateng.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public final class JsonUtil {

    /**
     * string 转list
     *
     * @param jsonStr
     * @param cls
     * @return
     * @throws IOException
     */
    public static <T> List<T> string2List(String jsonStr, Class<?> cls) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, cls);
        List<T> list = objectMapper.readValue(jsonStr, javaType);
        return list;
    }

    public static <T> String list2Str(List<T> list , Class<?> cla) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, cla);
        String res = objectMapper.writeValueAsString(list);
        return res ;
    }

    /**
     * 获得子串
     * @param key
     * @param json
     * @return
     * @throws IOException
     */
    public static String get(String key, String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        return jsonNode.get(key).toString();
    }

    /**
     * string 转java bean
     *
     * @param jsonStr
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T string2Bean(String jsonStr, Class<?> cls) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return (T) objectMapper.readValue(jsonStr, cls);
    }

    /**
     * bean 转java
     *
     * @param bean
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> String bean2String(T bean) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(bean);
    }

}