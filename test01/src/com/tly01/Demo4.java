package com.tly01;
/**
 * static 代码块 构造函数 与继承执行顺序
 * @author Administrator
 *
 */
public class Demo4 {
	static {
		System.out.println("Demo4 静态块");
	}

	public Demo4() {
		System.out.println("Demo4 构造函数");
	}

	{
		System.out.println("Demo4 构造代码块");
	}
}
