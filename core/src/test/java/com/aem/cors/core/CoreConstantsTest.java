package com.aem.cors.core;

import java.util.Set;

public class CoreConstantsTest {

    public static final String TEST_TRACKING_ID = "TEST_TRACKING_ID";
    public static final String JUST_A_STRING = "just a string";
    public static final String JSON_A_STRING = "{\"result\":\"" + JUST_A_STRING + "\"}";

    public static final String PATH_CONTENT_LANGUAGE_MASTERS_EN_HOME = "/content/aemcors/language-masters/en/home";
    public static final String PATH_CONTENT_LANGUAGE_MASTERS_EN_HOME_TITLE = "Home Page";
    public static final String PATH_CONTENT_LANGUAGE_MASTERS_EN_HOME_PROPERTY_TEXT = "/content/wasisa/es/homepage/jcr:content/root/container/text";
    public static final Set<String> PATH_CONTENT_LANGUAGE_MASTERS_EN_HOME_PROPERTIES = Set.of(PATH_CONTENT_LANGUAGE_MASTERS_EN_HOME_PROPERTY_TEXT);

    public static final String PATH_CONTENT_EN_HOME = "/content/aemcors/en/home";
}
