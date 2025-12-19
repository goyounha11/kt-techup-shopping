package com.kt.integration.openai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAIResponse {
	public record VectorCreate(
		String id,
		String object,
		@JsonProperty("created_at")
		Long createdAt,
		String name,
		String description,
		Long bytes,
		@JsonProperty("file_counts")
		FileCounts fileCounts
	) {

	}

	public record FileCounts(
		int in_progress,
		int completed,
		int failed,
		int cancelled,
		int total
	) {
	}
}