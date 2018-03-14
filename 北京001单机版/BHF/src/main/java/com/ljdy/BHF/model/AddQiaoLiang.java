package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 交通保障设施----桥梁信息
 * @author 郎川
 * 修改记录：
 * 		[2017-09-25]增加实体类注解 by 徐成
 */
@SuppressWarnings("all")
@Entity
@Table(name="QIAOLIANG")
public class AddQiaoLiang implements java.io.Serializable{

	/**
	 * 建设地点
	 */
	private String jsdd;

	/**
	 * 建设规模(座)
	 */
	private Integer jsgm;

	/**
	 * 桥梁类型（共用字典表 typename='桥梁类型'）
	 */
	private String qllx;

	/**
	 * 载重（吨）
	 */
	private Double zz;
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
	@Column(name="QLLX",length=200, nullable=true)
	public String getQllx() {
		return qllx;
	}

	public void setQllx(String qllx) {
		this.qllx = qllx;
	}
	@Column(name="ZZ",nullable=true)
	public Double getZz() {
		return zz;
	}

	public void setZz(Double zz) {
		this.zz = zz;
	}
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public AddQiaoLiang() {
		super();
	}

	public AddQiaoLiang(String jsdd, Integer jsgm, String qllx, Double zz,
			String id) {
		super();
		this.jsdd = jsdd;
		this.jsgm = jsgm;
		this.qllx = qllx;
		this.zz = zz;
		this.id = id;
	}
}

