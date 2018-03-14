package com.ljdy.BHF.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 拦阻报警设施----铁丝网
 * 
 * @author 郎川
 * 修改记录 
 * 	[2017-09-25] 继承基本信息表 by 郎川
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name="TSW")
public class Tsw extends Jbxx implements java.io.Serializable{
	
	/**
	 * 建设地点
	 */

	private String jsdd;

	/**
	 * 建设规模(公里)
	 */
	private BigDecimal jsgm;

	/**
	 * 拦阻门个数
	 */
	private Integer lzmgs;

	/**
	 * 铁丝网类型（共用字典表 typename=‘铁丝网类型’）
	 */
	private String tswlx;

	@Column(name="JSDD",length=2000,nullable=false)
	public String getJsdd() {
		return jsdd;
	}

	public void setJsdd(String jsdd) {
		this.jsdd = jsdd;
	}
	
	@Column(name="JSGM",nullable=false)
	public BigDecimal getJsgm() {
		return jsgm;
	}

	public void setJsgm(BigDecimal jsgm) {
		this.jsgm = jsgm;
	}
	@Column(name="LZMGS",nullable=true)
	public Integer getLzmgs() {
		return lzmgs;
	}

	public void setLzmgs(Integer lzmgs) {
		this.lzmgs = lzmgs;
	}
	@Column(name="TSWLX",length=200,nullable=true)
	public String getTswlx() {
		return tswlx;
	}

	public void setTswlx(String tswlx) {
		this.tswlx = tswlx;
	}

}
