package com.tly05;

public interface UserService {
	public void addUser(String userId,String userName);
	public void delUser(String userId);
	public void modifyUser(String userId,String userName);
	public String findUser(String userId);

}
