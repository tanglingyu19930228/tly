package com.tly05;

public class UserServiceImpl implements UserService{

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
		return "tly";
	}

}
