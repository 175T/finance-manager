package com.github.greatwqs.app.common.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateConfig
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Configuration
public class RestTemplateConfig {

	@Bean("restTemplate")
	public RestTemplate restTemplate(@Autowired ClientHttpRequestFactory wbClientHttpRequestFactory) throws Exception {
		RestTemplate restTemplate = new RestTemplate(wbClientHttpRequestFactory);
		ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {
			@Override
			public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
				return true;
			}

			@Override
			public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
			}
		};
		restTemplate.setErrorHandler(responseErrorHandler);
		return restTemplate;
	}

	@Bean("wbClientHttpRequestFactory")
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		// setReadTimeout: 5 seconds
		factory.setReadTimeout(5000);
		// setConnectTimeout: 15 seconds
		factory.setConnectTimeout(15000);
		return factory;
	}
}
