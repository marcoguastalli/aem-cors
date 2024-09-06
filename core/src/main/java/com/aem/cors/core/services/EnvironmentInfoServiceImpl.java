package com.aem.cors.core.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import static com.aem.cors.core.CoreConstants.OSGI_CONFIG_PREFIX;

@Getter
@Slf4j
@Component(service = EnvironmentInfoService.class, immediate = true)
@Designate(ocd = EnvironmentInfoServiceImpl.Configuration.class)
public class EnvironmentInfoServiceImpl implements EnvironmentInfoService {

    @ObjectClassDefinition(name = OSGI_CONFIG_PREFIX + "Environment Info Service")
    public @interface Configuration {
        @AttributeDefinition(name = "Environment and Run Mode", description = "Environment and Run Mod")
        String environmentAndRunMode() default "";

        @AttributeDefinition(name = "Organization Id", description = "Id of the Organization")
        String organizationId() default "";

        @AttributeDefinition(name = "Organization Secret", description = "Password of the Organization (unused)")
        String organizationSecret() default "";

    }

    private String environmentAndRunMode;
    private String organizationId;
    private String organizationSecret;

    @Activate
    protected void activate(final EnvironmentInfoServiceImpl.Configuration config) {
        this.environmentAndRunMode = config.environmentAndRunMode();
        this.organizationId = config.organizationId();
        this.organizationSecret = config.organizationSecret();
        log.info("Environment variable ENVIRONMENT_AND_RUN_MODE is set with value: '{}'", getEnvironmentAndRunMode());
        log.info("Environment variable ORGANIZATION_ID is set with value: '{}'", getOrganizationId());
        log.info("Environment variable ORGANIZATION_SECRET is set with value: '{}'", getOrganizationSecret());
    }
}
