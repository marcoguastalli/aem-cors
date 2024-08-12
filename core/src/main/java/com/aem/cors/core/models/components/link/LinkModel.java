package com.aem.cors.core.models.components.link;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Getter
@Model(adaptables = Resource.class, resourceType = LinkModel.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LinkModel {

    public static final String RESOURCE_TYPE = "aemcors/components/link";

    @SlingObject
    private Resource resource;
    @ValueMapValue
    private String linkPath;
    @ValueMapValue
    private String linkText;

    public String getLinkPath() {
        return resource.getResourceResolver().map(linkPath);
    }

    public boolean isEmpty() {
        return StringUtils.isEmpty(getLinkPath()) || StringUtils.isEmpty(getLinkText());
    }
}
