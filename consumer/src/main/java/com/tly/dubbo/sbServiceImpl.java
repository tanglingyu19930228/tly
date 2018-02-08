package com.tly.dubbo;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;

@Component
public class sbServiceImpl {
		@Reference(version="1.0.0")
		sbService sbservice;
		public void a(){
			String b=sbservice.a();
			System.out.println(b);
		}

}
