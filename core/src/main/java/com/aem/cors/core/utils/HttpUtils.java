package com.aem.cors.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.SlingHttpServletRequest;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.aem.cors.core.CoreConstants.HTTP_HEADER_UUID;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * Util class for http operations
 */
@Slf4j
public class HttpUtils {

    private HttpUtils() {
        throw new UnsupportedOperationException("Do not instantiate Util class");
    }

    /**
     * Returns the HTTP_HEADER_UUID from the input request if present
     * <p>
     * The HTTP_HEADER_UUID is used to track the users
     *
     * @param request the request that contains header
     * @return either the header value, the session-id, or empty
     */
    public static String getTrackingId(@NotNull HttpServletRequest request) {
        final String idFromHeader = request.getHeader(HTTP_HEADER_UUID);
        if (isNotBlank(idFromHeader)) {
            return idFromHeader;
        }
        return getIdFromSession(request);
    }

    /**
     * Get the session id from the input request
     *
     * @param request a Request
     * @return a String
     */
    private static String getIdFromSession(@NotNull HttpServletRequest request) {
        if (null != request.getSession() && null != request.getSession().getId()) {
            return request.getSession().getId();
        }
        return EMPTY;
    }

    /**
     * Verify the input paramName value, in case isBlank an error line is added in the returned list
     *
     * @param request   a Request
     * @param paramName the param name
     * @return a List with errors, or an empty list
     */
    public static List<String> verifyMandatoryRequestParameter(@NotNull SlingHttpServletRequest request,
                                                               @NotNull String... paramName) {
        List<String> result = new ArrayList<>();
        for (String param : paramName) {
            String paramValue = request.getParameter(param);
            if (isBlank(paramValue)) {
                result.add(format("The '%s' parameter is mandatory. ", param));
            }
        }
        return Collections.unmodifiableList(result);
    }
}
