package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 拦阻报警设施----拦阻桩
 * 
 * @author 郎川
 * 修改记录 
 * 	[2017-09-25] 基本基本信息表 by 郎川
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name="LZZ")
public class Lzz extends Jbxx implements java.io.Serializable{


	/**
	 * 建设地点
	 */
	private String jsdd;

	/**
	 * 建设规模(个)
	 */
	private Integer jsgm;

	/**
	 * 拦阻桩类型（共用字典表 typename=‘拦阻桩类型’）
	 */
	private String lzzlx;

	
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
	@Column(name="LZZLX",length=200,nullable=true)
	public String getLzzlx() {
		return lzzlx;
	}

	public void setLzzlx(String lzzlx) {
		this.lzzlx = lzzlx;
	}

}
