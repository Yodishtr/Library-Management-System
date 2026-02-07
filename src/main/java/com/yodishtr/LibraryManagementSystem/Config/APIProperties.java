package com.yodishtr.LibraryManagementSystem.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "google.books.api")
public class APIProperties {

    private String key;
    private String baseurl;

}
