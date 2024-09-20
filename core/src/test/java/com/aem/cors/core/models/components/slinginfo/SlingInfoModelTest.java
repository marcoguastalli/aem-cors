package com.aem.cors.core.models.components.slinginfo;


import com.aem.cors.core.AppAemContext;
import com.aem.cors.core.commonbeans.EnvironmentType;
import com.aem.cors.core.services.EnvironmentInfoService;
import com.aem.cors.core.services.SlingInfoService;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;

@ExtendWith(AemContextExtension.class)
@ExtendWith(MockitoExtension.class)
class SlingInfoModelTest {

    AemContext aemContext = AppAemContext.newAemContext(ResourceResolverType.JCR_MOCK);
    @Mock
    SlingInfoService slingInfoService;
    @Mock
    EnvironmentInfoService environmentInfoService;

    @BeforeEach
    void init() {
        aemContext.addModelsForClasses(SlingInfoModel.class);
        aemContext.registerService(SlingInfoService.class, slingInfoService);
        aemContext.registerService(EnvironmentInfoService.class, environmentInfoService);
        aemContext.load().json("/com/aem/cors/core/models/components/slinginfo/slinginfo.json", "/content");
        // when
        when(environmentInfoService.getEnvironmentAndRunMode()).thenReturn(EnvironmentType.AUTHOR.name());
    }

    @Test
    void testModel() {
        // given
        final Resource resource = aemContext.resourceResolver().getResource("/content/aemcors/en/home/jcr:content/root/container/container/slinginfo");
        assertThat(resource, notNullValue());
        assertThat(resource.getResourceType(), is("aemcors/components/slinginfo"));
        aemContext.request().setResource(resource);
        //final SlingInfoModel model = resource.adaptTo(SlingInfoModel.class);
        final SlingInfoModel model = aemContext.request().adaptTo(SlingInfoModel.class);
        // then
        assertThat(model, notNullValue());
        model.init();
        assertThat(model.getClass().getName(), equalTo(SlingInfoModel.class.getName()));
        assertThat(model.getParam(), is(EMPTY));
        assertThat(model.getSlingId(), nullValue());
        assertThat(model.getSlingHome(), nullValue());
        assertThat(model.getSlingUrl(), nullValue());
        assertThat(model.getSlingName(), nullValue());
        assertThat(model.getSlingDescription(), nullValue());
        assertThat(model.getRunModes(), notNullValue());
        assertThat(model.isAuthor(), is(Boolean.TRUE));
        assertThat(model.isPublish(), is(Boolean.FALSE));
    }
}