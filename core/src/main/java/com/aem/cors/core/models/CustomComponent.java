package com.aem.cors.core.models;

import com.adobe.cq.export.json.ComponentExporter;

public interface CustomComponent extends ComponentExporter {

    public String getMessage();

}