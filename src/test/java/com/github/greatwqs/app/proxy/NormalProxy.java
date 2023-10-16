package com.github.greatwqs.app.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.greatwqs.app.proxy.real.IPerson;
import com.github.greatwqs.app.proxy.real.Person;

public class NormalProxy {
	private final static Logger logger = LoggerFactory.getLogger(NormalProxy.class);
	private IPerson iPerson;

	public NormalProxy(IPerson iPerson) {
		this.iPerson = iPerson;
	}

	public static void main(String[] args) {
		NormalProxy personProxy = new NormalProxy(new Person());
		personProxy.doSomething();
	}

	public void doSomething() {
		logger.info("Before Proxy");
		iPerson.doSomething();
		logger.info("After Proxy");
	}
}