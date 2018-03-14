package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 辅助配套设施----了望塔
 * 
 * @author 郎川
 * 修改历史
 * 	 [2017-09-25]  基本基本信息表 by 郎川
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name="LWT")
public class AddLwt  implements java.io.Serializable{

	/**
	 * 建设地点
	 */
	@Column(name="JSDD",length=2000,nullable=true)
	private String jsdd;

	/**
	 * 建设规模(座)
	 */	
	@Column(name="JSGM",nullable=false)
	private Integer jsgm;

	/**
	 * 了望塔类型（共用字典表 typename=‘了望塔类型’）
	 */
	@Column(name="LWTLX",length=200,nullable=false)
	private String lwtlx;
	private String id;


	public String getJsdd() {
		return jsdd;
	}

	public void setJsdd(String jsdd) {
		this.jsdd = jsdd;
	}

	public Integer getJsgm() {
		return jsgm;
	}

	public void setJsgm(Integer jsgm) {
		this.jsgm = jsgm;
	}

	public String getLwtlx() {
		return lwtlx;
	}

	public void setLwtlx(String lwtlx) {
		this.lwtlx = lwtlx;
	}
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddLwt(String jsdd, Integer jsgm, String lwtlx, String id) {
		super();
		this.jsdd = jsdd;
		this.jsgm = jsgm;
		this.lwtlx = lwtlx;
		this.id = id;
	}

	public AddLwt() {
		// TODO Auto-generated constructor stub
	}
	

}
