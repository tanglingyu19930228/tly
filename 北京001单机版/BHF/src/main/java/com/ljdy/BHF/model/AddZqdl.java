package com.ljdy.BHF.model;

import java.math.BigDecimal;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 交通保障设施----执勤道路
 * 
 * @author 郎川 修改记录： [2017-09-25]增加实体类注解 by 徐成
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name = "ZQDL")
public class AddZqdl implements Serializable {

	/**
	 * 建设地点
	 */
	private String jsdd;

	/**
	 * 建设规模(公里)
	 */
	private BigDecimal jsgm;

	/**
	 * 道路类别（共用字典表 typename='道路类别'）
	 */
	private String dllb;

	/**
	 * 道路类型（共用字典表 typename='道路类型'）
	 */
	private String dllx;

	/**
	 * 道路等级（共用字典表 typename='道路等级'）
	 */
	private String dldj;

	/**
	 * 路基类型（共用字典表 typename='路基类型'）
	 */
	private String ljlx;

	/**
	 * 路面类型（共用字典表 typename='路面类型'）
	 */
	private String lmlx;

	private String id;

	@Column(name = "JSDD")
	public String getJsdd() {
		return jsdd;
	}

	public void setJsdd(String jsdd) {
		this.jsdd = jsdd;
	}

	@Column(name = "JSGM")
	public BigDecimal getJsgm() {
		return jsgm;
	}

	public void setJsgm(BigDecimal jsgm) {
		this.jsgm = jsgm;
	}

	@Column(name = "DLLB")
	public String getDllb() {
		return dllb;
	}

	public void setDllb(String dllb) {
		this.dllb = dllb;
	}

	@Column(name = "DLLX")
	public String getDllx() {
		return dllx;
	}

	public void setDllx(String dllx) {
		this.dllx = dllx;
	}

	@Column(name = "DLDJ")
	public String getDldj() {
		return dldj;
	}

	public void setDldj(String dldj) {
		this.dldj = dldj;
	}

	@Column(name = "LJLX")
	public String getLjlx() {
		return ljlx;
	}

	public void setLjlx(String ljlx) {
		this.ljlx = ljlx;
	}

	@Column(name = "LMLX")
	public String getLmlx() {
		return lmlx;
	}

	public void setLmlx(String lmlx) {
		this.lmlx = lmlx;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddZqdl(String jsdd, BigDecimal jsgm, String dllb, String dllx,
			String dldj, String ljlx, String lmlx, String id) {
		super();
		this.jsdd = jsdd;
		this.jsgm = jsgm;
		this.dllb = dllb;
		this.dllx = dllx;
		this.dldj = dldj;
		this.ljlx = ljlx;
		this.lmlx = lmlx;
		this.id = id;
	}

	public AddZqdl(String jsdd, BigDecimal jsgm, String dllb, String dllx,
			String dldj, String ljlx, String lmlx) {
		super();
		this.jsdd = jsdd;
		this.jsgm = jsgm;
		this.dllb = dllb;
		this.dllx = dllx;
		this.dldj = dldj;
		this.ljlx = ljlx;
		this.lmlx = lmlx;
	}

	public AddZqdl() {
		super();
	}
	
	
}
