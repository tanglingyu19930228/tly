package com.ljdy.BHF.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 交通保障设施----执勤道路
 * 
 * @author 郎川
 * 修改记录：
 * 		[2017-09-25]增加实体类注解 by 徐成
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name="ZQDL")
public class Zqdl extends Jbxx implements Serializable{

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
	
	
	
	

	@Column(name="JSDD")
	public String getJsdd() {
		return jsdd;
	}

	public void setJsdd(String jsdd) {
		this.jsdd = jsdd;
	}

	@Column(name="JSGM")
	public BigDecimal getJsgm() {
		return jsgm;
	}

	public void setJsgm(BigDecimal jsgm) {
		this.jsgm = jsgm;
	}

	@Column(name="DLLB")
	public String getDllb() {
		return dllb;
	}

	public void setDllb(String dllb) {
		this.dllb = dllb;
	}

	@Column(name="DLLX")
	public String getDllx() {
		return dllx;
	}

	public void setDllx(String dllx) {
		this.dllx = dllx;
	}

	@Column(name="DLDJ")
	public String getDldj() {
		return dldj;
	}

	public void setDldj(String dldj) {
		this.dldj = dldj;
	}

	@Column(name="LJLX")
	public String getLjlx() {
		return ljlx;
	}

	public void setLjlx(String ljlx) {
		this.ljlx = ljlx;
	}

	@Column(name="LMLX")
	public String getLmlx() {
		return lmlx;
	}

	public void setLmlx(String lmlx) {
		this.lmlx = lmlx;
	}
	}
	

