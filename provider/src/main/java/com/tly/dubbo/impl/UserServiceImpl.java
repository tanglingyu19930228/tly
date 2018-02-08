package com.tly.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tly.dubbo.UserService;

@Service(version="1.0.0")
public class UserServiceImpl implements UserService{

	@Override
	public String getDetials(String id) {
		return "dubbo-test-tly";
	}

}
