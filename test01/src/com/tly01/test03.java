package com.tly01;

public class test03 {
	public static void main(String[] args) {
		Singleton s1=Singleton.getInstance();
		Singleton s2=Singleton.getInstance();
		if(s1==s2){
			System.out.println("两个实例是一样的!");
		}else{
			System.out.println("两个实例是不一样的!");
		}
		
	}

}
