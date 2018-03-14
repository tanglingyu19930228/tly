package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 指挥监控设施----报警装置
 * @author 郎川
 * 修改记录 
 * 		[2017-09-25] 修改表继承基本信息表 by 郎川
 *
 */
@SuppressWarnings("all")
@Entity
@Table(name="BJZZ")
public class AddBjzz implements java.io.Serializable{
	
	
	/**
	 * 所在监控站标识
	 */
	private String jkz_id;
	
	/**
     * 所在监控站名称
     */
    private String jkz_name;
	
	/**
	 * 建设规模(套)
	 */
	
	private Integer jsgm;
	
	/**
	 * 设备品牌
	 */
	
	private String sbpp;
	
	/**
	 * 设备型号
	 */
	
	private String sbxh;
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

    @Column(name="JSGM",nullable=false)
	public Integer getJsgm() {
		return jsgm;
	}

	public void setJsgm(Integer jsgm) {
		this.jsgm = jsgm;
	}
	
	@Column(name="SBPP",length=200,nullable=true)
	public String getSbpp() {
		return sbpp;
	}

	public void setSbpp(String sbpp) {
		this.sbpp = sbpp;
	}
	
	@Column(name="SBXH" ,length=200,nullable=true)
	public String getSbxh() {
		return sbxh;
	}

	public void setSbxh(String sbxh) {
		this.sbxh = sbxh;
	}
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddBjzz(String jkz_id, String jkz_name, Integer jsgm, String sbpp,
			String sbxh, String id) {
		super();
		this.jkz_id = jkz_id;
		this.jkz_name = jkz_name;
		this.jsgm = jsgm;
		this.sbpp = sbpp;
		this.sbxh = sbxh;
		this.id = id;
	}

	public AddBjzz() {
		// TODO Auto-generated constructor stub
	}
	
}
