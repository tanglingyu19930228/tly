package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Zqf extends Jbxx implements java.io.Serializable{

	

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
}
