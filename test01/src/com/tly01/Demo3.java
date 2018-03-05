package com.tly01;
/**
 * try catch finally
 * @author Administrator
 *
 */

public class Demo3 {
	public static void main(String[] args) {
		System.out.println(test());
		System.out.println(get());
	}
	
	@SuppressWarnings({ "finally", "unused" })
	public static String test() {
		int value=0;
		try {
			System.out.println("try...");
			int result=1/value;
			return "111";
		}catch(Exception e) {
			System.out.println("catch...");
			return "444";
		}finally {
			System.out.println("finally...");
			return "333";
		}
	}
	public static String get() {
		try {
			System.out.println("try...");
			return "111";
			
		}catch(Exception e) {
		System.out.println("catch...");	
		}finally {
			System.out.println("finally...");
		}
		return "222";
	}
		
}
