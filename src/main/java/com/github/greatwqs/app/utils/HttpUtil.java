package com.github.greatwqs.app.utils;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

/**
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Component
public class HttpUtil {

	@Autowired
	private RestTemplate restTemplate;

	private static HttpUtil httpUtil;

	@PostConstruct
	public void init() {
		synchronized (HttpUtil.class) {
			if (httpUtil != null) {
				return;
			}
			httpUtil = this;
		}
		httpUtil.restTemplate = this.restTemplate;
	}

	private static final int RETRY_ATTEMPT = 3;
	private static final long RETRY_PERIOD = 50L;

	private static RetryTemplate retryTemplate;

	static {
		retryTemplate = new RetryTemplate();
		FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
		fixedBackOffPolicy.setBackOffPeriod(RETRY_PERIOD);
		retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
		retryPolicy.setMaxAttempts(RETRY_ATTEMPT);
		retryTemplate.setRetryPolicy(retryPolicy);
	}

	public static <T> T getForObject(String url, Class<T> resType) {
		return exchangeForObjWithRetry(url, HttpMethod.GET, Maps.newHashMap(), null, null, resType);
	}

	public static <T> T getForObject(String url, HttpHeaders headers, Class<T> resType) {
		return exchangeForObjWithRetry(url, HttpMethod.GET, Maps.newHashMap(), headers, null, resType);
	}

	public static <T> T getForObject(String url, Map<String, ?> uriParams, HttpHeaders headers, Class<T> resType) {
		return exchangeForObjWithRetry(url, HttpMethod.GET, uriParams, headers, null, resType);
	}

	private static <T> T exchangeForObjWithRetry(String url, HttpMethod method, Map<String, ?> uriParams,
		HttpHeaders headers, Object body, Class<T> resType) {
		return retryTemplate.execute(
			(RetryCallback<T, RestClientException>)context ->
				exchangeForObj(url, method, uriParams, headers, body, resType)
		);
	}

	public static <T> T postForObject(String url, Map<String, ?> uriParams, HttpHeaders headers, Object body,
		Class<T> resType) {
		return exchangeForObjWithRetry(url, HttpMethod.POST, uriParams, headers, body, resType);
	}

	private static <T> T exchangeForObj(String url, HttpMethod method, Map<String, ?> uriParams, HttpHeaders headers,
		Object body, Class<T> resType) {
		//RestTemplate restTemplate = new RestTemplate();
		HttpEntity entity = new HttpEntity<>(body, headers);
		ResponseEntity<T> res = httpUtil.restTemplate.exchange(url, method, entity, resType, uriParams);
		if (!res.getStatusCode().is2xxSuccessful()) {
			throw new RuntimeException("Error response, code=" + res.getBody() + ", message=" + res.getBody());
		}
		return res.getBody();
	}
}
