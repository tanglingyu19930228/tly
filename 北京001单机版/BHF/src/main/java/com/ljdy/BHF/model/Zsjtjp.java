package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 交通保障设施----直升机停机坪
 * @author 郎川
 * 修改记录：
 * 		[2017-09-25]增加实体类注解 by 徐成
 */
@SuppressWarnings("all")
@Entity
@Table(name="ZSJTJP")
public class Zsjtjp extends Jbxx implements java.io.Serializable{

	/**
	 * 建设地点
	 */
	private String jsdd;

	/**
	 * 建设规模(个)
	 */
	private Integer jsgm;

	/**
	 * 	停机坪类型（共用字典表 typename=‘停机坪类型’）
	 */
	private String tjplx;
	
	@Column(name="JSDD")
	public String getJsdd() {
		return jsdd;
	}

	public void setJsdd(String jsdd) {
		this.jsdd = jsdd;
	}

	@Column(name="JSGM")
	public Integer getJsgm() {
		return jsgm;
	}

	public void setJsgm(Integer jsgm) {
		this.jsgm = jsgm;
	}

	@Column(name="TJPLX")
	public String getTjplx() {
		return tjplx;
	}

	public void setTjplx(String tjplx) {
		this.tjplx = tjplx;
	}

}
