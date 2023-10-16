package com.github.greatwqs.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.github.greatwqs.app.component.RequestComponent;
import com.github.greatwqs.app.component.SmsComponent;

/**
 *
 * BaseServiceTest @MockBean for third part!
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
public abstract class BaseSpringServiceTest extends BaseSpringTest {

	@MockBean
	@Autowired
	protected SmsComponent smsComponent;

	@MockBean
	@Autowired
	private RequestComponent requestComponent;

}
