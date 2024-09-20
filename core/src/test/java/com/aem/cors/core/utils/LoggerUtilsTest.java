package com.aem.cors.core.utils;

import com.aem.cors.core.exceptions.AemRuntimeException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static com.aem.cors.core.utils.LoggerUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(MockitoExtension.class)
class LoggerUtilsTest {

    static final String LOG_MESSAGE = "bla bla bla";
    static final String ERROR_MESSAGE = "error";
    static final String TRACKING_ID = "session_27";
    @Mock
    Logger log;
    @Mock
    SlingHttpServletRequest slingHttpServletRequest;

    @Test
    void testLogDebugTrackingId() {
        logDebugTrackingId(log, TRACKING_ID, LOG_MESSAGE);
        assertThat(log, notNullValue());
    }

    @Test
    void testLogInfoTrackingId() {
        logInfoTrackingId(log, TRACKING_ID, LOG_MESSAGE);
        assertThat(log, notNullValue());
    }

    @Test
    void testLogWarnTrackingId() {
        logWarnTrackingId(log, TRACKING_ID, LOG_MESSAGE);
        assertThat(log, notNullValue());
    }

    @Test
    void testLogErrorTrackingIdWithException() {
        logErrorTrackingId(log, TRACKING_ID, LOG_MESSAGE, new AemRuntimeException(ERROR_MESSAGE, new RuntimeException()));
        assertThat(log, notNullValue());
    }

    @Test
    void testLogErrorTrackingId() {
        logErrorTrackingId(log, TRACKING_ID, LOG_MESSAGE);
        assertThat(log, notNullValue());
    }

    @Test
    void testLogHttpRequestParameters() {
        logHttpRequestParameters(log, TRACKING_ID, slingHttpServletRequest);
        assertThat(log, notNullValue());
    }
}
