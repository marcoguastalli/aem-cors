package com.aem.cors.core.commonbeans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Common Bean used to represent in the json a list of paths
 */
@Data
@AllArgsConstructor
public class PathsBean implements Serializable {
    @JsonProperty
    private List<String> paths;
}
