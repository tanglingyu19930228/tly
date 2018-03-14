package com.ljdy.BHF.model;

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
 * 操作日志
 * 
 * @author 郎川
 * 
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name="OPERATELOG")
public class Operatelog implements java.io.Serializable{

	/**
	 * 主键id 数据库唯一标识
	 */
	private String id;

	/**
	 * 操作IP地址
	 */
	private String ip;
	/**
	 * 操作时间
	 */

	private Date operatetime;

	/**
	 * 操作内容
	 */
	private String operate_context;

	/**
	 * 用户标识
	 */
	//private String userid;
	
	private User user;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name="ID",length=36,nullable=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	@Column(name="OPERATE_TIME")
	public Date getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}

	
	@Column(name="IP",length=255,nullable=true)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name="OPERATE_CONTEXT",length=2000,nullable=true)
	public String getOperate_context() {
		return operate_context;
	}

	public void setOperate_context(String operate_context) {
		this.operate_context = operate_context;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
