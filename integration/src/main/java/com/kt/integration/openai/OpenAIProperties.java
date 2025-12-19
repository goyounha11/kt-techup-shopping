package com.kt.integration.openai;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integration.openai")
public record OpenAIProperties(
	String apiKey
) {
}
