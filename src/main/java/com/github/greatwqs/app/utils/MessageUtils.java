package com.github.greatwqs.app.utils;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.github.greatwqs.app.component.RequestComponent;

/**
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Component
public class MessageUtils {

	private static MessageUtils instance;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private RequestComponent requestComponent;

	/***
	 * not call, only for unit test.
	 * @param messageSource
	 */
	public static void setResource(MessageSource messageSource, RequestComponent requestComponent) {
		instance = new MessageUtils();
		instance.messageSource = messageSource;
		instance.requestComponent = requestComponent;
	}

	/****
	 * get message.
	 * @param msgKey, i18n config msgKey.
	 * @param params, params for replacement.
	 * @return
	 */
	public static String getMessage(String msgKey, Object... params) {
		final Locale locale = instance.requestComponent.getLocale();
		return getMessage(msgKey, locale, params);
	}

	/**
	 * getMessage by locale
	 * @param msgKey
	 * @param locale eg: en_US / ko_KR / zh_CN
	 * @param params
	 * @return
	 */
	public static String getMessage(String msgKey, String locale, Object... params) {
		if (StringUtils.isEmpty(locale)) {
			locale = instance.requestComponent.getLocale().toString();
		}
		/*** get Local ***/
		final Locale localeObj = org.springframework.util.StringUtils.parseLocaleString(locale);
		return getMessage(msgKey, localeObj, params);
	}

	/**
	 * getMessage by locale
	 * @param msgKey
	 * @param locale eg: en_US / ko_KR / zh_CN
	 * @param params
	 * @return
	 */
	public static String getMessage(String msgKey, Locale locale, Object... params) {
		/*** get locale msgKey value, and replacement ***/
		String message = instance.messageSource.getMessage(msgKey, params, locale);
		return StringUtils.replaceAll(message, "\\.+$", ".");
	}

	/***
	 * construct for static method.
	 */
	@PostConstruct
	public void init() {
		synchronized (MessageUtils.class) {
			if (instance != null) {
				return;
			}
			// set value for static fields.
			instance = this;
		}
	}
}
