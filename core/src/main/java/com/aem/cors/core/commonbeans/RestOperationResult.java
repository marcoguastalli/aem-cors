package com.aem.cors.core.commonbeans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Bean used as json response for rest operations
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class RestOperationResult implements Serializable {

    private transient Object result;

}
