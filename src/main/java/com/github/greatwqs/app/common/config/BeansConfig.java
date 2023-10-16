package com.github.greatwqs.app.common.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import lombok.extern.slf4j.Slf4j;

/**
 * BeansConfig
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Slf4j
@Configuration
public class BeansConfig {

	/****
	 * classpath:i18n/message
	 * @return
	 */
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:i18n/message");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean("messageSourceAccessor")
	public MessageSourceAccessor messageSourceAccessor(
		@Autowired @Qualifier("messageSource") MessageSource messageSource) {
		MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(messageSource);
		return messageSourceAccessor;
	}

	@Bean("modelMapper")
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		// https://github.com/modelmapper/modelmapper/issues/239
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper;
	}

	@Bean("validator")
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}
