package com.aem.cors.core.models.components.link;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.commons.link.Link;
import com.adobe.cq.wcm.core.components.commons.link.LinkManager;
import com.adobe.cq.wcm.core.components.util.AbstractComponentImpl;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Getter
@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {LinkModel.class, ComponentExporter.class},
        resourceType = LinkModel.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class LinkModel extends AbstractComponentImpl {

    public static final String RESOURCE_TYPE = "aemcors/components/link";

    @SlingObject
    private ResourceResolver resourceResolver;
    @Self
    private SlingHttpServletRequest request;
    @ValueMapValue
    private String linkURL;
    @ValueMapValue
    private String linkText;

    @Self
    private LinkManager linkManager;
    protected Link link;

    @PostConstruct
    private void initModel() {
        final Resource linkPathResource = resourceResolver.getResource(linkURL);
        if (null != linkPathResource) {
            link = linkManager.get(linkPathResource).build();
        }
    }

    public String getLinkUrl() {
        if (null != link && link.isValid()) {
            return link.getURL();
        }
        return linkURL;
    }

    public boolean isEmpty() {
        //return StringUtils.isEmpty(getLinkPath()) || StringUtils.isEmpty(getLinkText());
        return StringUtils.isEmpty(getLinkText());
    }
}
