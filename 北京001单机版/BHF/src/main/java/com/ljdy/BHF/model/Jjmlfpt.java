package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 指挥监控设施----军警民联防平台
 * 
 * @author 郎川
 * 	修改记录
 * 	[2017-09-25] 继承基本信息表 by 郎川
 * 		
 */
@SuppressWarnings("all")
@Entity
@Table(name="JJMLFPT")
public class Jjmlfpt extends Jbxx implements java.io.Serializable{


	/**
	 * 向上联通情况（共用字典表 typename = ‘向上联通情况’）
	 */
	private String xsltqk;

	/**
	 * 向上联通网络性质（共用字典表 typename = ‘向上联通网络性质’）
	 */
	private String xsltwlxz;

	/**
	 * 向上传输线路（共用字典表 typename = ‘向上传送线路’）
	 */
	private String xscsxl;

	/**
	 * 横向联通情况（共用字典表 typename = ‘横向联通情况’）
	 */
	private String hxltqk;

	/**
	 * 横向联通网络性质（共用字典表 typename = ‘横向联通情况’）
	 */
	private String hxltwlxz;

	/**
	 * 横向传输线路（共用字典表 typename = ‘横向传送线路’）
	 */
	private String hxcsxl;

	/**
	 * 级别（共用字典表 typename = ‘级别’）
	 */
	private String jb;

	/**
	 * 放置地点
	 */
	private String fzdd;

	
	@Column(name="XSLTQK",length=200,nullable=true)
	public String getXsltqk() {
		return xsltqk;
	}

	public void setXsltqk(String xsltqk) {
		this.xsltqk = xsltqk;
	}

	@Column(name="XSLTWLXZ",length=200,nullable=true)
	public String getXsltwlxz() {
		return xsltwlxz;
	}

	public void setXsltwlxz(String xsltwlxz) {
		this.xsltwlxz = xsltwlxz;
	}
	
	@Column(name="XSCSXL",length=200,nullable=true)
	public String getXscsxl() {
		return xscsxl;
	}

	public void setXscsxl(String xscsxl) {
		this.xscsxl = xscsxl;
	}

	@Column(name="HXLTQK",length=200,nullable=true)
	public String getHxltqk() {
		return hxltqk;
	}

	public void setHxltqk(String hxltqk) {
		this.hxltqk = hxltqk;
	}

	@Column(name="HXLTWLXZ",length=200,nullable=true)
	public String getHxltwlxz() {
		return hxltwlxz;
	}

	public void setHxltwlxz(String hxltwlxz) {
		this.hxltwlxz = hxltwlxz;
	}
	@Column(name="HXCSXL",length=200,nullable=true)
	public String getHxcsxl() {
		return hxcsxl;
	}

	public void setHxcsxl(String hxcsxl) {
		this.hxcsxl = hxcsxl;
	}
	
	@Column(name="JB",length=200,nullable=true)
	public String getJb() {
		return jb;
	}

	public void setJb(String jb) {
		this.jb = jb;
	}

	@Column(name="FZDD",length=2000,nullable=false)
	public String getFzdd() {
		return fzdd;
	}

	public void setFzdd(String fzdd) {
		this.fzdd = fzdd;
	}

}
