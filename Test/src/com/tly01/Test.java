package com.tly01;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


public class Test {
	@User
	public void sb(){
	}
	@User(password="admin",name="admin")
	public void ss(){
	}
	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> classTest=Class.forName("com.tly01.Test");
		Method[] me=classTest.getDeclaredMethods();
		for(Method m:me){
			Annotation[] ann=m.getAnnotations();
			for(Annotation aa:ann){
				User u=(User) aa;
				System.out.println(u.name());
				System.out.println(u.password());
			}
		}
	}
}
