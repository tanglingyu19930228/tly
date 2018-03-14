package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 指挥监控设施----监控中心
 * 
 * @author 郎川
 * 修改记录
 * 	[2017-09-25] 继承基本信息表  by 郎川
 * 	
 */
@SuppressWarnings("all")
@Entity
@Table(name="JKZX")
public class Jkzx extends Jbxx implements java.io.Serializable{

	

	/**
	 * 向上联通情况（共用字典表 typename=‘向上联通情况’）
	 */
	private String xsltqk;

	/**
	 * 向上联通网络性质（共用字典表 typename=‘向上联通网络性质’）
	 */
	
	private String xsltwlxz;

	/**
	 * 向上传输线路（共用字典表 typename=‘向上传输线路’）
	 */
	
	private String xscsxl;

	/**
	 * 级别（共用字典表 typename=‘级别’）
	 */
	
	private String jb;

	@Column(name="XSLTQK",length=200,nullable=false)
	public String getXsltqk() {
		return xsltqk;
	}

	public void setXsltqk(String xsltqk) {
		this.xsltqk = xsltqk;
	}
	@Column(name="XSLTWLXZ",length=200,nullable=false)
	public String getXsltwlxz() {
		return xsltwlxz;
	}

	public void setXsltwlxz(String xsltwlxz) {
		this.xsltwlxz = xsltwlxz;
	}
	@Column(name="XSCSXL",length=200,nullable=false)
	public String getXscsxl() {
		return xscsxl;
	}

	public void setXscsxl(String xscsxl) {
		this.xscsxl = xscsxl;
	}
	@Column(name="JB",length=200,nullable=false)
	public String getJb() {
		return jb;
	}

	public void setJb(String jb) {
		this.jb = jb;
	}

}
