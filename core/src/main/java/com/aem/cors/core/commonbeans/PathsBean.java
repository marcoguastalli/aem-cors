package com.aem.cors.core.commonbeans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Common Bean used to represent in the json a list of paths
 */
@Data
@AllArgsConstructor
public class PathsBean implements Serializable {
    @JsonProperty
    private List<String> paths;
}
