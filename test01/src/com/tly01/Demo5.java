package com.tly01;

public class Demo5 extends Demo4{
	static {
		System.out.println("Demo5 ��̬��");
	}
	public Demo5() {
		System.out.println("Demo5 ���캯��");
	}
	{
		System.out.println("Demo5��������");
	}
	public static void main(String[] args) {
		new Demo5();
		System.out.println("-----------");
		new Demo5();
	}
}
