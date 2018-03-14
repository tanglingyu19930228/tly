package com.ljdy.BHF.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 拦阻报警设施-----隔离带
 * 
 * @author 郎川
 * 修改历史 
 * 	[2017=09-25]
 * 		继承基本信息表  by 郎川
 * 
 */
@Entity
@Table(name="GLD")
public class AddGld implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 建设地点
	 */
	private String jsdd;

	/**
	 * 建设规模(米)
	 */
	private BigDecimal jsgm;
	private String id;

	
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
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddGld(String jsdd, BigDecimal jsgm, String id) {
		super();
		this.jsdd = jsdd;
		this.jsgm = jsgm;
		this.id = id;
	}

	public AddGld() {
		// TODO Auto-generated constructor stub
	}
	

}
