package com.github.greatwqs.app;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * AppTestConfiguration
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.github.greatwqs.app"},
	excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Application.class),
		// task ignore
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.github.greatwqs.app.task.*")
	})
@EnableTransactionManagement
@EnableAsync
@ActiveProfiles("test")
public class TestConfiguration {
}
