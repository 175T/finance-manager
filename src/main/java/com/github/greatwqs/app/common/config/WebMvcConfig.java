package com.github.greatwqs.app.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.greatwqs.app.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * WebMvcConfig
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor loginInterceptor;

	/**
	 * addInterceptors
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor);
	}
}
