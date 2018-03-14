package com.ljdy.BHF.service;

import com.ljdy.BHF.model.User;

import java.util.List;

public interface UserService {
    /**
     * @Title login 
     * @Description (用户登陆) 
     * @param loginName
     * @param pwd
     * @return
     * @Return User 返回类型 
     * @Throws 
     * @Date  2017年9月22日
     * @修改历史  
     *     1. [2017年9月22日]创建文件 by 顾冲
     */
    public User login(String loginName, String pwd);
    
    /**
     * 新增用户
     * @param user
     * 修改历史：
	 *		[2017年9月28日]创建文件	by 徐成
     */
    public void addUser(User user);

    /**
     * 根据省份获取用户列表
     * @param province
     * @return
     */
    public List<Object> findUserByProvince(String province);

    /**
     * 根据ID获取用户信息
     * @param id
     * @return
     */
    public Object getUserByID(String id);

    /**
     * 更新用户信息
     * @param user
     */
    public void updateUser(User user);

    /**
     * 根据ID批量删除用户
     * @param idArr
     */
    public void deleteUser(String[] idArr);

    /**
     * 根据ID获取用户原始密码
     * @param id
     * @return
     */
    public String getOldPwdById(String id);

    /**
     * 根据账号获取用户数量
     * @param loginName
     * @return
     */
    public int countUserByLoginName(String loginName);
}
