package com.aem.cors.core.services;

/**
 * Service used to read the Environment Variables and expose them as info
 */
public interface EnvironmentInfoService {

    String getEnvironmentAndRunMode();

    String getOrganizationId();
}
