package com.tly04;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogHandler implements InvocationHandler {
	private Object targetObject;
	public Object newInstance(Object targetObject){
		this.targetObject=targetObject;
		Class targetClass=targetObject.getClass();
		return Proxy.newProxyInstance(targetClass.getClassLoader(),
				targetClass.getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("调用方法"+method.getName());
		Object ret=null;
		try {
			ret=method.invoke(targetObject, args);
			System.out.println("成功调用方法:"+method.getName());
			for(int i=0;i<args.length;i++){
				System.out.println(args[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("调用方法:"+method.getName()+"失败!");
			for(int i=0;i<args.length;++i){
				System.out.println(args[i]);
			}
		}
		return ret;
	}

}
