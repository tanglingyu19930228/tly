package com.tly01;

public class test02 extends test01 {
	public static void main(String[] args) {
		new test02();
		
	}

	@Override
	void a() {
       System.out.println("sb");		
	}
	
	public test02(){
		System.out.println("子类实例化");
	}

}
