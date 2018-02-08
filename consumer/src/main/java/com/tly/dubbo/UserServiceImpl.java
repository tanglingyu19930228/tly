package com.tly.dubbo;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;

@Component
public class UserServiceImpl {
	@Reference(version="1.0.0")
	UserService userService;
	public void test(){
		String id="tly";
		String a= userService.getDetials(id);
		System.out.println(a);
	}

}
