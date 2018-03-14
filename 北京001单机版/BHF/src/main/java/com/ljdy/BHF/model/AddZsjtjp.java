
package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class AddZsjtjp implements java.io.Serializable{

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
	private String id;
	
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
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddZsjtjp(String jsdd, Integer jsgm, String tjplx, String id) {
		super();
		this.jsdd = jsdd;
		this.jsgm = jsgm;
		this.tjplx = tjplx;
		this.id = id;
	}

	public AddZsjtjp() {
		// TODO Auto-generated constructor stub
	}
    
	
}
