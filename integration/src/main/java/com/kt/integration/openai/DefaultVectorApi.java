package com.kt.integration.openai;

import java.util.UUID;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import com.kt.common.profile.AppProfile;
import com.kt.common.profile.DevProfile;
import com.kt.common.profile.LocalProfile;
import com.kt.common.vector.VectorApi;
import com.kt.integration.openai.dto.OpenAIRequest;

import lombok.RequiredArgsConstructor;

@Component
@DevProfile
@AppProfile
@LocalProfile
@RequiredArgsConstructor
public class DefaultVectorApi implements VectorApi {
	private final OpenAIClient openAIClient;
	private final OpenAIProperties openAIProperties;

	@Override
	public String create(String name, String description) {
		var response = openAIClient.create(
			String.format("Bearer %s", openAIProperties.apiKey()),
			new OpenAIRequest.VectorCreate(name, description)
		);
		return response.id();
	}

	@Override
	public String uploadFile(String vectorStoreId, byte[] json) {
		var map = new LinkedMultiValueMap<String, Object>();

		var fileResource = new ByteArrayResource(
			json
		) {
			@Override
			public String getFilename() {
				return String.format("%s.json", UUID.randomUUID());
			}
		};

		map.add("purpose", "assistants");
		map.add("file", fileResource);

		var response = openAIClient.upload(
			String.format("Bearer %s", openAIProperties.apiKey()),
			map
		);

		openAIClient.uploadVectorStore(
			vectorStoreId,
			String.format("Bearer %s", openAIProperties.apiKey()),
			new OpenAIRequest.UploadFile(response.id())
		);

		return response.id();
	}

	@Override
	public void delete(String vectorStoreId, String fileId) {
		openAIClient.delete(
			vectorStoreId,
			fileId,
			String.format("Bearer %s", openAIProperties.apiKey())
		);

		openAIClient.deleteFile(
			fileId,
			String.format("Bearer %s", openAIProperties.apiKey())
		);
	}
}
