package com.tly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.tly.dubbo.CityDubboConsumerService;
import com.tly.dubbo.UserServiceImpl;
import com.tly.dubbo.sbServiceImpl;


@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext run=SpringApplication.run(Application.class,args);
		CityDubboConsumerService cityService=run.getBean(CityDubboConsumerService.class);
		cityService.printCity();
		UserServiceImpl userService=run.getBean(UserServiceImpl.class);
		userService.test();
		sbServiceImpl sb=run.getBean(sbServiceImpl.class);
		sb.a();
	}

}
