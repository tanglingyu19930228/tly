package com.tly01;
/**
 * 匿名内部类
 * @author Administrator
 *
 */
public class Dog {
	public interface Pet{
		public void beFriendly();
		public void play();
	}
	public static void main(String[] args) {
		Pet dog=new Pet(){

			@Override
			public void beFriendly() {
				System.out.println("蹭蹭你");
			}

			@Override
			public void play() {
			 System.out.println("一起来玩吧");
			}
			
		};
		dog.beFriendly();
		dog.play();
	}

}
