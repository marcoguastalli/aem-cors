package com.aem.cors.core.commonbeans.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Bean used to return the result of a Search operation
 */
@Data
@AllArgsConstructor
public class SearchPageReferencesBean implements Serializable {

    @JsonProperty
    private transient Map<String, SearchResultPage> references;
}
