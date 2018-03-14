package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Juma extends Jbxx  implements java.io.Serializable{

	
	/**
	 * 建设规模(个)
	 */
	private Integer jsgm;

	/**
	 * 建设地点
	 */
	
	private String jsdd;

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

}
