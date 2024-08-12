package com.aem.cors.core.models.components.link;


import com.aem.cors.core.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.aem.cors.core.CoreConstantsTest.PATH_CONTENT_EN_HOME;
import static com.aem.cors.core.models.components.link.LinkModel.RESOURCE_TYPE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith({MockitoExtension.class, AemContextExtension.class})
class LinkModelTest {

    AemContext aemContext = AppAemContext.newAemContext(ResourceResolverType.JCR_MOCK);

    @BeforeEach
    void init() {
        aemContext.addModelsForClasses(LinkModel.class);
        aemContext.load().json("/com/aem/cors/core/models/components/link/link.json", "/content");
    }

    @Disabled
    @Test
    void testModel() {
        // given
        final Resource resource = aemContext.resourceResolver().getResource("/content/aemcors/en/home/jcr:content/root/link");
        assertThat(resource, notNullValue());
        assertThat(resource.getResourceType(), is(RESOURCE_TYPE));
        final LinkModel model = resource.adaptTo(LinkModel.class);
        // then
        assertThat(model, notNullValue());
        assertThat(model.getClass().getName(), equalTo(LinkModel.class.getName()));
        assertThat(model.getLinkUrl(), is(PATH_CONTENT_EN_HOME));
        assertThat(model.getLinkText(), is("reload"));
        assertThat(model.isEmpty(), is(Boolean.FALSE));
        assertThat(model.getLink(), notNullValue());
    }

    @Disabled
    @Test
    void testModelEmpty() {
        // given
        final Resource resource = aemContext.resourceResolver().getResource("/content/aemcors/en/home/jcr:content/root/link_empty");
        assertThat(resource, notNullValue());
        assertThat(resource.getResourceType(), is(RESOURCE_TYPE));
        final LinkModel model = resource.adaptTo(LinkModel.class);
        // then
        assertThat(model, notNullValue());
        assertThat(model.getClass().getName(), equalTo(LinkModel.class.getName()));
    }
}
