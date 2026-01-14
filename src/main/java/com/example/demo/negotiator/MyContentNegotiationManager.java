package com.example.demo.negotiator;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;

import java.util.Map;

public class MyContentNegotiationManager {
    @Bean
    public ContentNegotiationManager contentNegotiationManager() {
        return new ContentNegotiationManager(
                new HeaderContentNegotiationStrategy(),
                new ParameterContentNegotiationStrategy(Map.of(
                        "json", MediaType.APPLICATION_JSON,
                        "xml", MediaType.APPLICATION_XML
                ))
        );
    }
}