package com.tly05;

public class UserServiceImplProxy implements UserService {
	private UserService userService;
	public UserServiceImplProxy(UserService userService){
		this.userService=userService;
	}

	@Override
	public void addUser(String userId, String userName) {
		try {
			System.out.println("开始执行:add");
			userService.addUser(userId, userName);
			System.out.println("add执行成功");
		} catch (Exception e) {
			System.out.println("add执行失败");
		}
	}

	@Override
	public void delUser(String userId) {
		
	}

	@Override
	public void modifyUser(String userId, String userName) {
		
	}

	@Override
	public String findUser(String userId) {
		return null;
	}

}
