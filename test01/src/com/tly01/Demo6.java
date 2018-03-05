package com.tly01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * lambda表达式用法
 * @author Administrator
 *
 */
public class Demo6 {
	public static void main(String[] args) {
		a();
		b();
		c();
		d();
		e();
		List<String> names=Arrays.asList("Java","Python","Ruby","C","C++","C#");
		Predicate<String> cc=(a)->a.length()>=1;
		Predicate<String> dd=(a)->a.startsWith("C");
		f(names,cc.and(dd));
	}
   public static void a() {
	   new Thread(new Runnable() {
		@Override
		public void run() {
			System.out.println("呵呵哒!");
		}
	}).start();
   }
   
   public static void b() {
	   new Thread(()->System.out.println("啪啪啪!")).start();;
   }
   
   public static void c() {
	   List<Double> aa=Arrays.asList(10.0,20.0,30.0);
	   aa.stream().map((a)->a+a*0.1).forEach((b)->{
		   System.out.println(b);
	   });
   }
   
   public static void d() {
	   List<Double> aa=Arrays.asList(10.0,20.0,30.0);
	   double d=aa.stream().map((a)->a+a*0.1).reduce((sum,x)->sum+x).get();
	   System.out.println(d);
   }
   
   public static void e() {
	   List<Double> aa=Arrays.asList(10.0,20.0,30.0);
	   List<Double> re=aa.stream().filter(a->a>25).collect(Collectors.toList());
	   re.forEach(x->System.out.println(x));
   }
   public static void f(List<String> names,Predicate<String> condition) {
	   names.stream().filter((a)->condition.test(a)).forEach(name->System.out.println(name+""));
   }
}
