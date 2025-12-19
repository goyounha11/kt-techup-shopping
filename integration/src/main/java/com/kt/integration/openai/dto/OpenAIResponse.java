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
		@JsonProperty("in_progress")
		int inProgress,
		int completed,
		int failed,
		int cancelled,
		int total
	) {
	}

	public record Upload(
		String id,
		String object,
		Long bytes,
		@JsonProperty("created_at")
		Long createdAt,
		@JsonProperty("expires_at")
		Long expiresAt,
		String filename,
		String purpose
	) {
	}
}