package com.github.greatwqs.app.common.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.github.greatwqs.app.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * AsyncConfig
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Slf4j
@Configuration
public class AsyncConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(200);
		executor.setThreadNamePrefix("AppAsyncExecutor-");
		executor.setDaemon(true);
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return (throwable, method, params) -> {
			log.error("AsyncConfig, AsyncUncaughtExceptionHandler"
				+ ", class: " + method.getDeclaringClass().getName()
				+ ", method: " + method.getName()
				+ ", params: " + JsonUtil.toJson(params), throwable);
		};
	}
}
