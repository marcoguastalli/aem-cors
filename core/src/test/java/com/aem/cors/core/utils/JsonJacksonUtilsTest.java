package com.aem.cors.core.utils;

import com.aem.cors.core.commonbeans.RestOperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static com.aem.cors.core.CoreConstantsTest.JSON_A_STRING;
import static com.aem.cors.core.CoreConstantsTest.JUST_A_STRING;
import static com.aem.cors.core.utils.JsonJacksonUtils.createJsonStringFromObject;
import static com.aem.cors.core.utils.JsonJacksonUtils.createObjectFromJsonString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

class JsonJacksonUtilsTest {

    @Test
    void testCreateJsonStringFromObject() {
        assertThat(createJsonStringFromObject(createRestOperationResult()), is(JSON_A_STRING));
    }

    Serializable createRestOperationResult() {
        return new RestOperationResult(JUST_A_STRING);
    }

    @Test
    void testCreateObjectFromJsonString() {
        JsonJacksonUtilsTestBean jsonJacksonUtilsTestBean = createObjectFromJsonString(JSON_A_STRING, JsonJacksonUtilsTestBean.class);
        assertThat(jsonJacksonUtilsTestBean, notNullValue());
        assertThat(jsonJacksonUtilsTestBean.result, notNullValue());
        assertThat(jsonJacksonUtilsTestBean.result, is(JUST_A_STRING));
    }

    static final class JsonJacksonUtilsTestBean implements Serializable {
        @JsonProperty
        String result;
    }
}
