package com.ljdy.BHF.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @ClassName User 
 * @Description (用户信息) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月21日 下午5:43:40 
 * @修改历史  
 *     1. [2017年9月21日]创建文件 by 顾冲
 */
@Entity
@Table(name = "p_user")
public class User  implements Serializable {
	
    private static final long serialVersionUID = -1650388474894230997L;

    /** 
     * 用户编号
    */ 
    private String id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 省份
     */
    private String province;

    /**
     * 账号
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String pwd;
    
    /**
     * 用户角色:1 国家  2 省级  3超级管理员
     */
    private String roleName;
    
    /**
     * 用户单位
     */
    private String user_dep;
    
    /**
     * 责任人
     */
    private String mainUser;
    
    /**
     * 用户创建时间
     */
    private Date createTime;

    /**
     * 用户状态，1表示正常，0表示注销
     */
    private Integer status;
    
    private Set<Operatelog> set = new HashSet<Operatelog>();

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", unique = true, nullable = false, length = 36)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    
    @Column(name = "loginName")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    @Column(name = "pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    @Column(name = "roleName")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    @Column(name = "user_dep")
    public String getUser_dep() {
        return user_dep;
    }

    public void setUser_dep(String user_dep) {
        this.user_dep = user_dep;
    }
    
    @Column(name = "mainUser")
    public String getMainUser() {
        return mainUser;
    }

    public void setMainUser(String mainUser) {
        this.mainUser = mainUser;
    }

    @Column(name = "createTime")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
