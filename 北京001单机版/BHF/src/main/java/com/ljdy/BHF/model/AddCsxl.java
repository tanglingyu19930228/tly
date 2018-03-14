package com.ljdy.BHF.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 指挥监控设施----传输线路
 * 
 * @author 郎川
 * 修改记录 
 * 	[2017-09-25] 继承基本信息表 by 郎川
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name="CSXL")
public class AddCsxl  implements java.io.Serializable{
	
	/**
	 * 所在监控站标识
	 */
	private String jkz_id;
	
	/**
	 * 所在监控站名称
	 */
	private String jkz_name;

    /**
	 * 线路起点
	 */
	private String xlqd;

	/**
	 * 线路终点
	 */
	private String xlzd;

	/**
	 * 建设地点
	 */
	private String jsdd;

	/**
	 * 建设规模(米)
	 */
	private BigDecimal  jsgm;

	/**
	 * 线路性质（共用字典表 typename='线路性质'）
	 */
	private String xlxz;
	/**
	 * 线路类型（共用字典表 typename='线路类型'）
	 */
	private String xllx;
	private String id;

	@Column(name="JKZ_ID",length=36,nullable=true)
	public String getJkz_id() {
		return jkz_id;
	}

	public void setJkz_id(String jkz_id) {
		this.jkz_id = jkz_id;
	}
	
	@Column(name="JKZ_NAME",length=600)
	public String getJkz_name() {
        return jkz_name;
    }

    public void setJkz_name(String jkz_name) {
        this.jkz_name = jkz_name;
    }

	@Column(name="XLQD",length=600,nullable=false)
	public String getXlqd() {
		return xlqd;
	}

	public void setXlqd(String xlqd) {
		this.xlqd = xlqd;
	}

	@Column(name="XLZD",length=600,nullable=false)
	public String getXlzd() {
		return xlzd;
	}

	public void setXlzd(String xlzd) {
		this.xlzd = xlzd;
	}

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

	@Column(name="XLXZ",length=200,nullable=true)
	public String getXlxz() {
		return xlxz;
	}

	public void setXlxz(String xlxz) {
		this.xlxz = xlxz;
	}

	@Column(name="XLLX",length=200,nullable=true)
	public String getXllx() {
		return xllx;
	}

	public void setXllx(String xllx) {
		this.xllx = xllx;
	}
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddCsxl(String jkz_id, String jkz_name, String xlqd, String xlzd,
			String jsdd, BigDecimal jsgm, String xlxz, String xllx, String id) {
		super();
		this.jkz_id = jkz_id;
		this.jkz_name = jkz_name;
		this.xlqd = xlqd;
		this.xlzd = xlzd;
		this.jsdd = jsdd;
		this.jsgm = jsgm;
		this.xlxz = xlxz;
		this.xllx = xllx;
		this.id = id;
	}

	public AddCsxl() {
		// TODO Auto-generated constructor stub
	}
	

}
