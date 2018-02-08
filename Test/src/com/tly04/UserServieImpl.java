package com.tly04;

import com.tly05.UserService;

public class UserServieImpl  implements UserService,Service{

	@Override
	public void sayHello(String name) {
		System.out.println("ÄãºÃ:"+name);
		
	}

	@Override
	public int addOperate(int num, int num2) {
		return num+num2;
	}

	@Override
	public void addUser(String userId, String userName) {
        System.out.println("add");		
	}

	@Override
	public void delUser(String userId) {
         System.out.println("del");		
	}

	@Override
	public void modifyUser(String userId, String userName) {
          System.out.println("modify");		
	}

	@Override
	public String findUser(String userId) {
		System.out.println("find");
		return "tly666666";
	}

}
