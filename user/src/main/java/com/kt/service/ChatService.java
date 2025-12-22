package com.kt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.domain.vector.Vector;
import com.kt.domain.vector.VectorType;
import com.kt.integration.openai.OpenAIChatApi;
import com.kt.repository.vector.VectorRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {
	private final OpenAIChatApi openAIChatApi;
	private final VectorRepository vectorRepository;

	public String questions(String query) {
		var ids = vectorRepository.findByTypeIn(VectorType.chatbotRange()).stream().map(Vector::getStoreId).toList();

		var newQuery = String.format("%s:%s", String.join(",", ids), query);

		return openAIChatApi.search(newQuery);
	}
}
