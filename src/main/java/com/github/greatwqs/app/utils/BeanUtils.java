package com.github.greatwqs.app.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Component
public class BeanUtils implements ApplicationContextAware {
	private ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;
	}

	public Object getBean(String name, Object... obj) {
		return ctx.getBean(name, obj);
	}

	public <T> T getBean(Class<T> clazz, Object... obj) {
		return ctx.getBean(clazz, obj);
	}

	public void initializeBean(Object obj) {
		ctx.getAutowireCapableBeanFactory().autowireBean(obj);
	}
}
