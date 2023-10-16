package com.github.greatwqs.app.component;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * SmsComponent
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Slf4j
@Component
public class SmsComponent {

	@Async
	public void sendSms(String phoneNo, String smsCode) {
		log.warn("phoneNo: " + phoneNo + ", smsCode: " + smsCode);
	}
}
