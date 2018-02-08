package com.tly01;

import com.tly01.Outer.Inner;

/**
 * 局部内部类
 * @author Administrator
 *
 */
public class Outer1 {
	int x=1;
	public void doSomething(){
		final int y=2;
		class inner{
			int x=3;
			void print(){
				int x=4;
				System.out.println(x);
				System.out.println(this.x);
				System.out.println(Outer1.this.x);
				System.out.println(y);
			}
		}
		inner in=new inner();
		in.print();
	}
	public static void main(String[] args) {
		Outer1 o=new Outer1();
		o.doSomething();
	}

}
