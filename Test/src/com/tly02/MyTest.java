
package com.tly02;

import org.junit.Test;

public class MyTest {
	@Test
	public void test() {
		// pkgToScan:要扫描的包路径,多个同前缀的包会递归处理
		String pkgToScan = "com.tly02";
		BeanFactory bf = new BeanFactory(pkgToScan);
		IStudentService iss = bf.getInstance("studentServiceImp");
		// AOP绑定
		iss = DynamicProxy.bind(iss);
		iss.save();

	}
}