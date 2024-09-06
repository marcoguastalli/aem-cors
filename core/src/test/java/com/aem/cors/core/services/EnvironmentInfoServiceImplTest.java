package com.aem.cors.core.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

class EnvironmentInfoServiceImplTest {

    EnvironmentInfoServiceImpl environmentInfoService;

    @BeforeEach
    void init() {
        environmentInfoService = new EnvironmentInfoServiceImpl();
    }

    @Test
    void testGetEnvironmentType() {
        assertThat(environmentInfoService, notNullValue());
        assertThat(environmentInfoService.getEnvironmentAndRunMode(), nullValue());
        assertThat(environmentInfoService.getOrganizationId(), nullValue());
    }
}
