package com.generative.ai.highlevel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ExpediaApiConfig {

    @Value("${expedia.api.key}")
    private String apiKey;

    @Value("${expedia.api.base-url}")
    private String baseUrl;


    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}