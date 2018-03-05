package com.tly01;

public class Demo5 extends Demo4{
	static {
		System.out.println("Demo5 静态块");
	}
	public Demo5() {
		System.out.println("Demo5 构造函数");
	}
	{
		System.out.println("Demo5构造代码块");
	}
	public static void main(String[] args) {
		new Demo5();
		System.out.println("-----------");
		new Demo5();
	}
}
