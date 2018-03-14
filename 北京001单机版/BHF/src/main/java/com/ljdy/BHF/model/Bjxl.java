package com.ljdy.BHF.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 拦阻报警设施----报警线路
 * @author 郎川
 *
 *修改记录
 * [2017-09-25] 实现继承基本信息表 修改字段数据类型 by 郎川
 */
@SuppressWarnings("all")
@Entity
@Table(name="BJXL")
public class Bjxl extends Jbxx  implements java.io.Serializable{
	
	/**
	 * 建设地点
	 */
	private String jsdd;
	
	/**
	 * 建设规模
	 */
	
	private BigDecimal jsgm;
	
	/**
	 * 设备品牌
	 */
	
	private String sbpp;

	
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
	
	@Column(name="SBPP",length=36,nullable=true)
	public String getSbpp() {
		return sbpp;
	}

	public void setSbpp(String sbpp) {
		this.sbpp = sbpp;
	}
	
}
