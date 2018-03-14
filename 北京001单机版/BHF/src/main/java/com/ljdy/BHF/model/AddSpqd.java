package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 指挥监控设施----视频前端
 * 
 * @author 郎川
 * 修改记录 
 * 	[2017-09-25]  继承基本信息表 by 郎川
 * 	
 */
@SuppressWarnings("all")
@Entity
@Table(name="SPQD")
public class AddSpqd implements java.io.Serializable{

	
	/**
	 * 所在监控站标识
	 */
	private String jkz_id;
	
	/**
     * 所在监控站名称
     */
    private String jkz_name;

	/**
	 * 放置地点
	 */
	private String fzdd;

	/**
	 * 传输方式
	 */
	private String csfs;

	/**
	 * 设备类型（共用字典表 typename='视频前端类型'）
	 */
	private String sblx;
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

    @Column(name="FZDD",length=2000)
	public String getFzdd() {
		return fzdd;
	}

	public void setFzdd(String fzdd) {
		this.fzdd = fzdd;
	}
	@Column(name="CSFS",length=200,nullable=true)
	public String getCsfs() {
		return csfs;
	}

	public void setCsfs(String csfs) {
		this.csfs = csfs;
	}
	@Column(name="SBLX",length=200,nullable=false)
	public String getSblx() {
		return sblx;
	}

	public void setSblx(String sblx) {
		this.sblx = sblx;
	}
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddSpqd(String jkz_id, String jkz_name, String fzdd, String csfs,
			String sblx, String id) {
		super();
		this.jkz_id = jkz_id;
		this.jkz_name = jkz_name;
		this.fzdd = fzdd;
		this.csfs = csfs;
		this.sblx = sblx;
		this.id = id;
	}

	public AddSpqd() {
		// TODO Auto-generated constructor stub
	}
	

}
