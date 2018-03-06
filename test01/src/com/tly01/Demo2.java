package com.tly01;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class Demo2 {
	public static void main(String[] args) {
		int[] arr= {1,9,5,6,8,7};
		int a=8;
        System.out.println(h(arr,a));
	}

	// 三位数的水仙花数
	public static void a() {
		for (int i = 101; i < 1000; i++) {
			int bai = i / 100;
			int shi = i % 100 / 10;
			int ge = i % 100 % 10;
			if (ge * ge * ge + shi * shi * shi + bai * bai * bai == i) {
				System.out.println(i);
			}
		}
	}

	// 判断是否是质数
	public static void b() {
		int count = 0;
		for (int i = 101; i < 200; i += 2) {
			boolean b = false;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					b = false;
					break;
				} else {
					b = true;
				}
			}
			if (b == true) {
				count++;
				System.out.print(i + " ");
				if (count % 5 == 0) {
					System.out.println();
				}
			}
		}
		System.out.println("素数个数:" + count);
	}

	// 递归求阶乘
	public static void c() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("请输入正整数:");
			int n = sc.nextInt();
			System.out.println(d(n));
			System.out.println("按q退出!按其他键继续");
			String ss = sc.next();
			if (ss.equals("q")) {
				break;
			}
		}
	}

	public static int d(int n) {
		if (n == 0 || n == 1) {
			return 1;
		} else {
			return d(n - 1) * n;
		}
	}

	// 生成随机数
	public static int[] getRandom(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 100 + 1);
		}
		return arr;
	}

	// 冒泡排序
	public static void e() {
		int[] a = getRandom(10);
		System.out.println(Arrays.toString(a));
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					a[j] = a[j] + a[j + 1];
					a[j + 1] = a[j] - a[j + 1];
					a[j] = a[j] - a[j + 1];
				}
			}
		}
		System.out.println(Arrays.toString(a));
	}

	// 选择排序
	public static void f() {
		int[] a = getRandom(10);
		System.out.println(Arrays.toString(a));
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i; j < a.length - 1; j++) {
				if (a[i] > a[j + 1]) {
					a[i] = a[i] + a[j + 1];
					a[j + 1] = a[i] - a[j + 1];
					a[i] = a[i] - a[j + 1];
				}
			}
		}
		System.out.println(Arrays.toString(a));
	}

	// 插入排序
	public static void g() {
		int[] a = getRandom(10);
		System.out.println(Arrays.toString(a));
		for (int i = 1; i < a.length; i++) {
			for (int j = i; j > 0; j--) {
				if (a[j] < a[j - 1]) {
					a[j] = a[j] + a[j - 1];
					a[j - 1] = a[j] - a[j - 1];
					a[j] = a[j] - a[j - 1];
				}
			}
		}
		System.out.println(Arrays.toString(a));
	}

	// 二分查找非递归算法
	public static int h(int[] array, int a) {
		int low = 0;
		int high = array.length - 1;
		int mid;
		while (low <= high) {
			mid = (low + high) / 2;
			if (array[mid] == a) {
				return mid + 1;
			} else if (array[mid] < a) {
				low = mid + 1;
			} else {
				high = mid + 1;
			}
		}
		return -1;
	}
}
