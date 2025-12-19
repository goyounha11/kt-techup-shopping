package com.kt.integration.openai;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import com.kt.integration.openai.dto.OpenAIRequest;
import com.kt.integration.openai.dto.OpenAIResponse;

@HttpExchange(contentType = MediaType.APPLICATION_JSON_VALUE)
public interface OpenAIClient {
	@PostExchange("https://api.openai.com/v1/vector_stores")
	OpenAIResponse.VectorCreate create(@RequestBody OpenAIRequest.VectorCreate request);
}
