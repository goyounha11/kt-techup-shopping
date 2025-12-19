package com.kt.integration.openai.dto;

public class OpenAIRequest {
	public record VectorCreate(
		String name,
		String description
	) {
	}
}
