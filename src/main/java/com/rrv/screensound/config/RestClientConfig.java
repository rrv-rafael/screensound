package com.rrv.screensound.config;

import com.rrv.screensound.config.properties.WikipediaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(WikipediaProperties.class)
public class RestClientConfig {
    @Bean
    public RestClient wikipediaRestClient(RestClient.Builder builder, WikipediaProperties wikipediaProperties) {
        return builder
                .baseUrl(wikipediaProperties.baseUrl())
                .defaultHeader("User-Agent", wikipediaProperties.userAgent())
                .build();
    }
}
