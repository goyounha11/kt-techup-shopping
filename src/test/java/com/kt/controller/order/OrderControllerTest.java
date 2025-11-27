package com.kt.controller.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kt.domain.product.Product;
import com.kt.dto.order.OrderRequest;
import com.kt.repository.product.ProductRepository;
import com.kt.support.TestCurrentUser;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class OrderControllerTest {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	// 테스트 기반으로하는 문서화를한다. open-api-spec, rest-docs
	// 토큰을 처리하는 두가지 방법
	// swagger어노테이션으로 문서화를하고있음
	// 1.Security의가짜인가객체를 만들어서 속임 (v)
	// 2.실제 jwt토큰을 항상 만들어서 넣어줌

	@Test
	@TestCurrentUser
	void 주문_생성_성공() throws Exception {
		var product = productRepository.save(
			new Product(
				"테스트 상품",
				10000L,
				100L
			)
		);

		System.out.println(product.getId());

		var request = new OrderRequest.Create(
			product.getId(),
			3L,
			"홍길동",
			"서울시 강남구 테헤란로 123",
			"010-1234-5678"
		);

		mockMvc.perform(post("/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))
			)
			.andDo(print())
			.andExpectAll(
				status().isOk(),
				jsonPath("$.code").value("ok"),
				jsonPath("$.message").value("성공")
			);
	}

	// 컨트롤러 테스트는 요청을 쏴보고 응답을 받아봐야함
	// MockMvc(V), TestRestTemplate, RestAssured 등 다양한 방법이 있음
}