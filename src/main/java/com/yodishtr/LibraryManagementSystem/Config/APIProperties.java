package com.yodishtr.LibraryManagementSystem.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "google.books.api")
public class APIProperties {

    private String key;
    private String baseurl;

    // Getters
    public String getKey() {
        return key;
    }

    public String getBaseurl() {
        return baseurl;
    }

    // Setters
    public void setKey(String key){
        this.key = key;
    }

    public void setBaseurl(String baseurl){
        this.baseurl = baseurl;
    }
}
