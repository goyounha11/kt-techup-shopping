package com.kt.config;

import java.util.Set;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import com.kt.security.DefaultCurrentUser;
import com.kt.security.TechUpAuthenticationToken;
import com.kt.support.TestCurrentUser;

public class CurrentUserSecurityContextFactory implements WithSecurityContextFactory<TestCurrentUser> {
	@Override
	public SecurityContext createSecurityContext(TestCurrentUser annotation) {
		var context = SecurityContextHolder.createEmptyContext();

		var token = new TechUpAuthenticationToken(
			new DefaultCurrentUser(1L, "loginId"),
			Set.of()
		);

		context.setAuthentication(token);

		return context;
	}
}
