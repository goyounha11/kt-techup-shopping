package com.kt.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.test.context.support.WithSecurityContext;

import com.kt.config.CurrentUserSecurityContextFactory;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = CurrentUserSecurityContextFactory.class)
public @interface TestCurrentUser {
	long getId() default 1L;

	String getLoginId() default "test";

}
