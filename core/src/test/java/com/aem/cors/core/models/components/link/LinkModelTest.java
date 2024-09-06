package com.aem.cors.core.models.components.link;


import com.aem.cors.core.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.aem.cors.core.CoreConstants.DOT;
import static com.aem.cors.core.CoreConstants.HTML_EXTENSION;
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

    @Test
    void testModel() {
        // given
        Resource resource = aemContext.resourceResolver().getResource("/content/aemcors/en/home/jcr:content/root/container/container/link");
        assertThat(resource.getResourceType(), is(RESOURCE_TYPE));
        aemContext.currentResource(resource);
        final LinkModel model = aemContext.request().adaptTo(LinkModel.class);
        // then
        assertThat(model, notNullValue());
        assertThat(model.getClass().getName(), equalTo(LinkModel.class.getName()));
        assertThat(model.getLinkUrl(), is(PATH_CONTENT_EN_HOME.concat(DOT).concat(HTML_EXTENSION)));
        assertThat(model.getLinkText(), is("reload"));
        assertThat(model.isEmpty(), is(Boolean.FALSE));
        assertThat(model.getLink(), notNullValue());
    }

    @Test
    void testModelEmpty() {
        // given
        Resource resource = aemContext.resourceResolver().getResource("/content/aemcors/en/home/jcr:content/root/container/container/link_empty");
        assertThat(resource.getResourceType(), is(RESOURCE_TYPE));
        aemContext.currentResource(resource);
        final LinkModel model = aemContext.request().adaptTo(LinkModel.class);
        // then
        assertThat(model, notNullValue());
        assertThat(model.getClass().getName(), equalTo(LinkModel.class.getName()));
    }
}
