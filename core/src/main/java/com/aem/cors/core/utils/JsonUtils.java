package com.aem.cors.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * Util class for json operations with the Jackson library
 */
@Slf4j
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils() {
        throw new UnsupportedOperationException("Do not instantiate Util class");
    }

    /**
     * Create a String representing the Json of the input Serializable object
     *
     * @param serializable the object to be 'jsoned'
     * @return a String with the json, or null if an exception is thrown
     */
    public static String createJsonStringFromObject(@NotNull Serializable serializable) {
        try {
            return OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                    .writeValueAsString(serializable);
        } catch (JsonProcessingException e) {
            log.error("Error createJsonStringFromObject", e);
            return null;
        }
    }

    /**
     * Create an instance of the object from the input string and class
     *
     * @param jsonAsString the string to be converted to a json
     * @param clazz        the type of the class to be created
     * @return a Serializable instance object
     */
    public static <T> T createObjectFromJsonString(@NotNull String jsonAsString, @NotNull Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(jsonAsString, clazz);
        } catch (JsonProcessingException e) {
            log.error("Error createObjectFromJsonString", e);
            return null;
        }
    }


}
