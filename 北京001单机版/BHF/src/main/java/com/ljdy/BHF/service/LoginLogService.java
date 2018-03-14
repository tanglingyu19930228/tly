package com.ljdy.BHF.service;

import javax.servlet.http.HttpServletRequest;

import com.ljdy.BHF.model.User;

/**
 * @ClassName LoginLogService 
 * @Description (登陆日志service) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月22日 下午1:19:51 
 * @修改历史  
 *     1. [2017年9月22日]创建文件 by 顾冲
 */
public interface LoginLogService {
    /**
     * @Title add 
     * @Description (写入登录日志) 
     * @param user
     * @param ip
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年9月22日
     * @修改历史  
     *     1. [2017年9月22日]创建文件 by 顾冲
     */
    public void add(User user, HttpServletRequest request);

    /**
     * 根据用户ID删除数据
     * @param userIdArr
     */
    public void deleteByUserId(String[] userIdArr);
}
