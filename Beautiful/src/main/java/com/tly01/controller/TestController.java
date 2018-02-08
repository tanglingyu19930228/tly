package com.tly01.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tly01.service.TestService;

@RestController
@RequestMapping("/user")
public class TestController {
	@Autowired
	private TestService testService;
	@RequestMapping("/all")
	public List<HashMap<String,Object>> get(){
		System.out.println("sb");
		return testService.sel();
	}
	@RequestMapping("/hello")
	public String a() {
		return "hehe!";
	}

}
