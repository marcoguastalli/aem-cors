package com.aem.cors.core.models.components.title;

import com.adobe.cq.export.json.ComponentExporter;
import com.aem.cors.core.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(AemContextExtension.class)
class TitleV4Test {

    AemContext aemContext = AppAemContext.newAemContext(ResourceResolverType.JCR_MOCK);

    @BeforeEach
    void init() {
        //aemContext.addModelsForPackage("com.adobe.cq.wcm.core.components.models");
        aemContext.addModelsForClasses(TitleV4.class, ComponentExporter.class);
        aemContext.load().json("/com/aem/cors/core/models/components/title/title.json", "/content");
    }

    @Test
    void testTitle() throws NoSuchFieldException, IllegalAccessException {
        Resource resource = aemContext.resourceResolver().getResource("/content/aemcors/en/home/jcr:content/root/container/title");
        assertThat(resource, notNullValue());
        aemContext.currentResource(resource);
        TitleV4 title = aemContext.request().adaptTo(TitleV4.class);
        assertThat(title, notNullValue());
        assertThat(title.getExportedType(), is(TitleV4.RESOURCE_TYPE));
        assertThat(title.getId(), notNullValue());
        assertThat(title.getText(), is("Home Page"));
        assertThat(title.getType(), is("h1"));
        assertThat(title.getLink(), nullValue());
        assertThat(title.getLinkURL(), nullValue());
        assertThat(title.isLinkDisabled(), is(Boolean.FALSE));
        assertThat(title.getComponentData(), notNullValue());
    }

    @Test
    void testTitleInTheContainer() throws NoSuchFieldException, IllegalAccessException {
        Resource resource = aemContext.resourceResolver().getResource("/content/aemcors/en/home/jcr:content/root/container/container/title");
        assertThat(resource, notNullValue());
        aemContext.currentResource(resource);
        TitleV4 title = aemContext.request().adaptTo(TitleV4.class);
        assertThat(title, notNullValue());
        assertThat(title.getExportedType(), is(TitleV4.RESOURCE_TYPE));
        assertThat(title.getId(), is("id_title_2"));
        assertThat(title.getText(), is("Title as h2"));
        assertThat(title.getType(), is("h2"));
        assertThat(title.getLink(), nullValue());
        assertThat(title.getLinkURL(), nullValue());
        assertThat(title.isLinkDisabled(), is(Boolean.FALSE));
        assertThat(title.getComponentData(), notNullValue());
    }

    @Test
    void testTitleInTheContainerWithLink() throws NoSuchFieldException, IllegalAccessException {
        Resource resource = aemContext.resourceResolver().getResource("/content/aemcors/en/home/jcr:content/root/container/container/title_with_link");
        assertThat(resource, notNullValue());
        aemContext.currentResource(resource);
        TitleV4 title = aemContext.request().adaptTo(TitleV4.class);
        assertThat(title, notNullValue());
        assertThat(title.getExportedType(), is(TitleV4.RESOURCE_TYPE));
        assertThat(title.getId(), is("id_title_3"));
        assertThat(title.getText(), is("Title as h3 (with link)"));
        assertThat(title.getType(), is("h3"));
        assertThat(title.getLink(), notNullValue());
        assertThat(title.getLinkURL(), is("/content/aemcors/language-masters/page"));
        assertThat(title.isLinkDisabled(), is(Boolean.FALSE));
        assertThat(title.getComponentData(), notNullValue());
    }
}
