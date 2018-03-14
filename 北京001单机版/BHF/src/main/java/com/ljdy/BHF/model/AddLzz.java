package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class AddLzz  implements java.io.Serializable{


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
	@Column(name="LZZLX",length=200,nullable=true)
	public String getLzzlx() {
		return lzzlx;
	}

	public void setLzzlx(String lzzlx) {
		this.lzzlx = lzzlx;
	}
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddLzz(String jsdd, Integer jsgm, String lzzlx, String id) {
		super();
		this.jsdd = jsdd;
		this.jsgm = jsgm;
		this.lzzlx = lzzlx;
		this.id = id;
	}

	public AddLzz() {
		// TODO Auto-generated constructor stub
	}

}
