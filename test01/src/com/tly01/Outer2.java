package com.tly01;
/**
 * ¾²Ì¬Ç¶Ì×Àà
 * @author Administrator
 *
 */
public class Outer2 {
	static int x=1;
	static class Next{
		void print(){
			System.out.println("Next "+x);
		}
	}
	public static void main(String[] args){
		Outer2.Next next=new Outer2.Next();
		next.print();
	}

}
