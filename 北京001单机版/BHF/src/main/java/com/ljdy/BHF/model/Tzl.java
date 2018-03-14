package com.ljdy.BHF.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 拦阻报警设施----铁栅栏
 * 
 * @author 郎川 
 * 修改记录 
 * 	[2017-09-25] 继承基本信息表 by 郎川
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name="TZL")
public class Tzl extends Jbxx implements java.io.Serializable{

	

	/**
	 * 建设地点
	 */
	private String jsdd;

	/**
	 * 建设规模(公里)
	 */
	
	private BigDecimal jsgm;

	
	@Column(name="JSDD",length=2000,nullable=true)
	public String getJsdd() {
		return jsdd;
	}

	public void setJsdd(String jsdd) {
		this.jsdd = jsdd;
	} 	
	@Column(name="JSGM",nullable=true)
	public BigDecimal getJsgm() {
		return jsgm;
	}

	public void setJsgm(BigDecimal jsgm) {
		this.jsgm = jsgm;
	}

}
