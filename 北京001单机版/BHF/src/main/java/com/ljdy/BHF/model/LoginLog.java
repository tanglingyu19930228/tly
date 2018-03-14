package com.ljdy.BHF.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @ClassName Login_log 
 * @Description (用户登陆日志信息) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月22日 下午1:10:00 
 * @修改历史  
 *     1. [2017年9月22日]创建文件 by 顾冲
 */
@Entity
@Table(name = "login_log")
public class LoginLog  implements Serializable {
    
    private static final long serialVersionUID = -7877384598402595519L;

    /**
     * 登陆日志编号
     */
    private String id;
    
    /**
     * 登陆ip
     */
    private String loginIp;
    
    /**
     * 登陆时间
     */
    private Date loginTime;
    
    /**
     * 登陆用户
     */
    private User user;
    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", unique = true, nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name = "loginIp")
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
    
    @Column(name = "loginTime")
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    
    @ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
    @JoinColumn(name="userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
