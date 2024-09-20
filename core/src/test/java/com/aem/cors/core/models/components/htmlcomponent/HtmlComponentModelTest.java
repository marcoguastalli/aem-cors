package com.aem.cors.core.models.components.htmlcomponent;


import com.aem.cors.core.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(AemContextExtension.class)
class HtmlComponentModelTest {

    AemContext aemContext = AppAemContext.newAemContext(ResourceResolverType.JCR_MOCK);

    @BeforeEach
    void init() {
        aemContext.addModelsForClasses(HtmlComponentModel.class);
        aemContext.load().json("/com/aem/cors/core/models/components/htmlcomponent/htmlcomponent.json", "/content");
    }

    @Test
    void testModel() {
        // given
        final Resource resource = aemContext.resourceResolver().getResource("/content/aemcors/en/en/jcr:content/root/container/container/htmlcomponent");
        assertThat(resource, notNullValue());
        assertThat(resource.getResourceType(), is(HtmlComponentModel.RESOURCE_TYPE));
        final HtmlComponentModel model = resource.adaptTo(HtmlComponentModel.class);
        // then
        assertThat(model, notNullValue());
        assertThat(model.getClass().getName(), equalTo(HtmlComponentModel.class.getName()));
        assertThat(model.getHtmlCode(), is("&lt;script type=&quot;text/javascript&quot;>&#xd;&#xa;alert('do not do this!');&#xd;&#xa;&lt;/script>&#xd;&#xa;"));
        assertThat(model.isEmpty(), is(Boolean.FALSE));
    }

}
