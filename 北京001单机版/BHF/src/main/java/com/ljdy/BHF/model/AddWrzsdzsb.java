package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 指挥监控设施----无人值守电子哨兵
 * 
 * @author 郎川
 * 	修改记录 
 * 	[2017-09-25] 继承基本信息表
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name="WRZSDZSB")
public class AddWrzsdzsb  implements java.io.Serializable{


	/**
	 * 放置地点
	 */
	private String fzdd;

	/**
	 * 建设规模(套)
	 */
	private Integer jsgm;
	private String id;


	@Column(name="FZDD",length=2000,nullable=false)
	public String getFzdd() {
		return fzdd;
	}

	public void setFzdd(String fzdd) {
		this.fzdd = fzdd;
	}

	@Column(name="JSGM",nullable=false)
	public Integer getJsgm() {
		return jsgm;
	}

	public void setJsgm(Integer jsgm) {
		this.jsgm = jsgm;
	}
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddWrzsdzsb(String fzdd, Integer jsgm, String id) {
		super();
		this.fzdd = fzdd;
		this.jsgm = jsgm;
		this.id = id;
	}

	public AddWrzsdzsb() {
		// TODO Auto-generated constructor stub
	}
	
}
