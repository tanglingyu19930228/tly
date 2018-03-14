package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Wrzsdzsb  extends Jbxx implements java.io.Serializable{


	/**
	 * 放置地点
	 */
	private String fzdd;

	/**
	 * 建设规模(套)
	 */
	private Integer jsgm;


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

}
