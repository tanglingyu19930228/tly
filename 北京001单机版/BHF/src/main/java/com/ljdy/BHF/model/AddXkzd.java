package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 指挥监控设施----显控终端
 * 
 * @author 郎川
 * 修改记录 
 * 	[2017-09-25] 继承基本信息表 by 郎川
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name="XKZD")
public class AddXkzd implements java.io.Serializable{

	

	/**
	 * 所在监控站标识；外键，关联监控站信息表
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

    @Column(name="FZDD",length=2000,nullable=true)
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
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddXkzd(String jkz_id, String jkz_name, String fzdd, String csfs,
			String id) {
		super();
		this.jkz_id = jkz_id;
		this.jkz_name = jkz_name;
		this.fzdd = fzdd;
		this.csfs = csfs;
		this.id = id;
	}

	public AddXkzd() {
		// TODO Auto-generated constructor stub
	}

}
