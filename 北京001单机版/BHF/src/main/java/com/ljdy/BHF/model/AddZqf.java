package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 辅助配套设施---执勤房
 * 
 * @author 郎川
 * 	修改记录 
 * 	[2017-09-25] 继承基本信息表 by 郎川
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name="ZQF")
public class AddZqf implements java.io.Serializable{

	

	/**
	 * 建设地点
	 */
	private String jsdd;

	/**
	 * 建设规模(座)
	 */
	private Integer jsgm;

	/**
	 * 执勤房类型（共用字典表 typename='执勤房类型'）
	 */
	private String zqflx;
	private String id;


	@Column(name="JSDD",length=2000,nullable=false)
	public String getJsdd() {
		return jsdd;
	}

	public void setJsdd(String jsdd) {
		this.jsdd = jsdd;
	}

	@Column(name="JSGM",nullable=false)
	public Integer getJsgm() {
		return jsgm;
	}

	public void setJsgm(Integer jsgm) {
		this.jsgm = jsgm;
	}

	@Column(name="ZQFLX",length=200,nullable=false)
	public String getZqflx() {
		return zqflx;
	}

	public void setZqflx(String zqflx) {
		this.zqflx = zqflx;
	}
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddZqf(String jsdd, Integer jsgm, String zqflx, String id) {
		super();
		this.jsdd = jsdd;
		this.jsgm = jsgm;
		this.zqflx = zqflx;
		this.id = id;
	}

	public AddZqf() {
		// TODO Auto-generated constructor stub
	}
	
}
