package com.tly04;

import com.tly05.UserService;


public class Client {
	public static void main(String[] args) {
		 Service service=(Service) new LogHandler().newInstance(new UserServieImpl());
		 UserService userService=(UserService) new LogHandler().newInstance(new UserServieImpl());
//		  userService.addUser("001", "centre");
         String name = userService.findUser("002");
         System.out.println(name);
//         int num = service.addOperate(12, 23);
//         System.out.println(num);
//         service.sayHello("centre");
	}

}
