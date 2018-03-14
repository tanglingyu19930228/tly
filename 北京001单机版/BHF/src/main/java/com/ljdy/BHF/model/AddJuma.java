package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 骏马
 * @author 郎川
 * 修改记录 
 * 	[2017-09-25] 继承基本信息表 by 郎川
 *	
 */
@SuppressWarnings("all")
@Entity
@Table(name="JUMA")
public class AddJuma implements java.io.Serializable{

	
	/**
	 * 建设规模(个)
	 */
	private Integer jsgm;

	/**
	 * 建设地点
	 */
	
	private String jsdd;
	private String id;

	@Column(name="JSGM",nullable=true)
	public Integer getJsgm() {
		return jsgm;
	}

	public void setJsgm(Integer jsgm) {
		this.jsgm = jsgm;
	}
	@Column(name="JSDD",length=2000,nullable=true)
	public String getJsdd() {
		return jsdd;
	}

	public void setJsdd(String jsdd) {
		this.jsdd = jsdd;
	}
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddJuma(Integer jsgm, String jsdd, String id) {
		super();
		this.jsgm = jsgm;
		this.jsdd = jsdd;
		this.id = id;
	}

	public AddJuma() {
		// TODO Auto-generated constructor stub
	}
	

}
