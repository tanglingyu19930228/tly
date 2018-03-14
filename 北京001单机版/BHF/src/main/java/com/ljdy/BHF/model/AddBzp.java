package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 辅助配套设施-----标志牌
 * @author 郎川
 * 修改记录 
 * 	[2017-09-25] 修改表继承基本信息表 by 郎川
 *		
 */
@SuppressWarnings("all")
@Entity
@Table(name="BZP")
public class AddBzp  implements java.io.Serializable{
	
	
	/**
	 * 建设地点
	 */
	private String jsdd;
	
	/**
	 * 建设规模(面)
	 */
	private Integer jsgm;
	
	/**
	 * 标志牌类型（共用字典表 typename=标志牌类型
	 */
	private String bzplx;
	private String id;
	

	@Column(name="JSDD",length=2000,nullable=false)
	public String getJsdd() {
		return jsdd;
	}

	public void setJsdd(String jsdd) {
		this.jsdd = jsdd;
	}

	@Column(name="JSGM",nullable=true)
	public Integer getJsgm() {
		return jsgm;
	}

	public void setJsgm(Integer jsgm) {
		this.jsgm = jsgm;
	}
	
	@Column(name="BZPLX",nullable=false,length=200)
	public String getBzplx() {
		return bzplx;
	}

	public void setBzplx(String bzplx) {
		this.bzplx = bzplx;
	}
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddBzp(String jsdd, Integer jsgm, String bzplx, String id) {
		super();
		this.jsdd = jsdd;
		this.jsgm = jsgm;
		this.bzplx = bzplx;
		this.id = id;
	}

	public AddBzp() {
		// TODO Auto-generated constructor stub
	}

}
