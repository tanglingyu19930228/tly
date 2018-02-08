package com.tly.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tly.dubbo.sbService;
@Service(version="1.0.0")
public class sbServiceImpl implements sbService {

	@Override
	public String a() {
		return "sbbbbbbbbbbbbbbbbbbbbbbb";
	}

}
