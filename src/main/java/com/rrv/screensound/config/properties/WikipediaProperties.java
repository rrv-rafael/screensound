package com.rrv.screensound.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "external.wikipedia")
public record WikipediaProperties(
        String baseUrl,
        String userAgent
) {
}
