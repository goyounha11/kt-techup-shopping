package com.kt.integration.openai;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OpenAIConfiguration {
	private final OpenAIProperties openAIProperties;

	// http 통신으로 저희가 벡터스토어 통신 구현을 해야함
	// 1. resttemplate(동기)
	// 2. webclient - 비동기
	// 3. feign client - 선언적 http 클라이언트
	// 4. restclient - spring5에서 추가된 동기/비동기 모두 지원 (v)
	// XXXXClient
	@PostConstruct
	void init() {
		System.out.println("OpenAI API Key: " + openAIProperties.apiKey());
	}
}
