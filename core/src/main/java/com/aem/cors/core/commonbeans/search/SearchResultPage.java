package com.aem.cors.core.commonbeans.search;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * Bean used to return the result of a Search operation
 */
@Data
@Builder
public class SearchResultPage {
    private String pagePath;
    private String pageTitle;
    private Set<String> properties;
}
