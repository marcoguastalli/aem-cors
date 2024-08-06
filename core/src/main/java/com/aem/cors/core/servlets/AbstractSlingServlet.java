package com.aem.cors.core.servlets;

import static com.aem.cors.core.CoreConstants.CONTENT_TYPE_APPLICATION_JSON;
import static com.aem.cors.core.CoreConstants.HTTP_HEADER_CACHE_CONTROL;
import static com.aem.cors.core.CoreConstants.HTTP_HEADER_NO_CACHE;
import static com.aem.cors.core.CoreConstants.JSON_ERROR_OBJECT_DEFAULT;
import static com.aem.cors.core.utils.LoggerUtils.logErrorTrackingId;
import static com.aem.cors.core.utils.LoggerUtils.logWarnTrackingId;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.jetbrains.annotations.NotNull;

import com.aem.cors.core.exceptions.AemRuntimeException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractSlingServlet extends SlingAllMethodsServlet {
    private static final Gson GSON_INSTANCE = new Gson();


    protected <T> T readInput(@NotNull SlingHttpServletRequest slingHttpServletRequest, @NotNull String trackingId, @NotNull Class<T> clazz) {
        try {
            final JsonObject postRequestDataAsJson = GSON_INSTANCE.fromJson(slingHttpServletRequest.getReader(), JsonObject.class);
            return GSON_INSTANCE.fromJson(postRequestDataAsJson, clazz);
        } catch (IllegalStateException | IOException e) {
            final String errorMessage = "Cannot create a json with the provided input";
            logWarnTrackingId(log, trackingId, errorMessage);
        }
        return null;
    }

    /**
     * Write the input jsonAsString as json
     *
     * @param response                   the response
     * @param trackingId                 to track the user
     * @param statusCode                 the http return code
     * @param jsonAsString               the json that will be printed
     * @param headerValueForCacheControl the value for the http header "Cache-Control"
     * @throws AemRuntimeException if something goes wrong
     */
    protected void writeJsonObject(@NotNull SlingHttpServletResponse response, @NotNull String trackingId, int statusCode, @NotNull String jsonAsString, @NotNull String headerValueForCacheControl) throws AemRuntimeException {
        try {
            response.setStatus(statusCode);
            response.setContentType(CONTENT_TYPE_APPLICATION_JSON);
            addResponseHeaders(response, headerValueForCacheControl);
            PrintWriter out = response.getWriter();
            out.print(jsonAsString);
        } catch (IOException e) {
            final String errorMessage = format("Error writeJsonAsString with json %s", jsonAsString);
            logErrorTrackingId(log, trackingId, errorMessage, e);
            throw new AemRuntimeException(errorMessage, e);
        }
    }

    /**
     * Write the json error
     *
     * @param response   the response
     * @param trackingId to track the user
     * @param statusCode the http return code
     * @throws AemRuntimeException if something goes wrong
     */
    protected void writeErrorJsonObject(@NotNull SlingHttpServletResponse response, @NotNull String trackingId, int statusCode) throws AemRuntimeException {
        writeJsonObject(response, trackingId, statusCode, JSON_ERROR_OBJECT_DEFAULT, HTTP_HEADER_NO_CACHE);
    }

    /**
     * Get the session id from the input request
     *
     * @param request a Request
     * @return a String
     */
    static String getIdFromSession(@NotNull HttpServletRequest request) {
        if (null != request.getSession() && null != request.getSession().getId()) {
            return request.getSession().getId();
        }
        return EMPTY;
    }

    /**
     * add the Http Headers to the response
     *
     * @param response                   the response
     * @param headerValueForCacheControl the value for the http header "Cache-Control"
     */
    private static void addResponseHeaders(@NotNull SlingHttpServletResponse response, @NotNull String headerValueForCacheControl) {
        response.addHeader(HTTP_HEADER_CACHE_CONTROL, headerValueForCacheControl);
    }
}
