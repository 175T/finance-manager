package com.github.greatwqs.app.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.github.greatwqs.app.proxy.real.IPerson;
import com.github.greatwqs.app.proxy.real.Person;

public class JdkProxy implements InvocationHandler {

	private Object delegate;

	public static void main(String[] args) {
		JdkProxy personProxy = new JdkProxy();
		IPerson iperson = (IPerson)personProxy.bind(new Person());
		iperson.doSomething();
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		try {
			System.out.println("Before Proxy");
			result = method.invoke(delegate, args);
			System.out.println("After Proxy");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public Object bind(Object delegate) {
		this.delegate = delegate;
		return Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass().getInterfaces(), this);
	}
}