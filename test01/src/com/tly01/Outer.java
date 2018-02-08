package com.tly01;
/**
 * 常规内部类
 * @author Administrator
 *
 */
public class Outer {
	private int x=1;
	public Outer(){
	 System.out.println("Outer initial");	
	}
	public class Inner{
		public Inner(){
			System.out.println("Inner initial");
		}
		private int x=2;
		public void add(){
			int x=3;
			System.out.println(x);
			System.out.println(this.x);
			System.out.println(Outer.this.x);
		}
	}
	public static void main(String[] args) {
		Inner inner=new Outer().new Inner();
		inner.add();
	}

}
