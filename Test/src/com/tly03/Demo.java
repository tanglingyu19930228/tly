package com.tly03;

import java.util.Arrays;

public class Demo {
	public static void main(String[] args) {
		int [] a=new int[10];
		for(int i=0;i<a.length;i++){
			a[i]=(int) (Math.random()*100+1);
		}
		System.out.println(Arrays.toString(a));
		int temp=0;
		for(int i=1;i<a.length;++i){
			for(int j=i;j>0;j--){
				if(a[j]<a[j-1]){
					temp=a[j-1];
					a[j-1]=a[j];
					a[j]=temp;
				}
			}
		}
		System.out.println(Arrays.toString(a));
	}
}
