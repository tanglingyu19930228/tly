package com.ljdy.BHF.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.ljdy.BHF.utils.StringUtils;

/**
 * 维护项目表
 * 
 * @author 郎川
 * 修改记录 
 * 	[2017-09-25] 继承基本信息表 by 郎川
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name="WHXM")
public class Whxm implements java.io.Serializable{

	/**
	 * 主键id 数据库唯一标识
	 */
	private String id;

	/**
	 * 项目名称
	 */
	private String xmmc;

	/**
	 * 项目编号
	 */
	private String xmbh;

	/**
	 * 维修单位
	 */
	private String wxdw;

	/**
	 * 维修规模
	 */
	private String wxgm;

	/**
	 * 维修项目名称
	 */
	private String wxxmmc;

	/**
	 * 维修项目编号
	 */
	private String wxxmbh;
	
	/**
     * 项目子类
     */
	private String xmzl;
	
	/**
     * 所在省份
     */
	private String szsf;
	
	/**
	 * 维修项目规模数组
	 */
	private String [] wxgmArr;
	
	/**
     * 维修项目名称数组
     */
	private String [] wxxmmcArr;
	
	/**
     * 维修项目编号数组
     */
	private String [] wxxmbhArr;
	
	/**维修项目包含的维修项目个数
	 */
	private int wxxmsize;
	
	/**
     * 维修费用
     */
	private BigDecimal wxzfy;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	@Id
	@Column(name="id",length=36,nullable=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="XMMC",length=600,nullable=true)
	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	@Column(name="XMBH",length=600,nullable=true)
	public String getXmbh() {
		return xmbh;
	}

	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}

	@Column(name="WXDW",length=600,nullable=true)
	public String getWxdw() {
		return wxdw;
	}

	public void setWxdw(String wxdw) {
		this.wxdw = wxdw;
	}

	@Column(name="WXGM",length=600,nullable=true)
	public String getWxgm() {
		return wxgm;
	}

	public void setWxgm(String wxgm) {
		this.wxgm = wxgm;
	}

	@Column(name="wxxmmc",nullable=true,length=600)
	public String getWxxmmc() {
		return wxxmmc;
	}

	public void setWxxmmc(String wxxmmc) {
		this.wxxmmc = wxxmmc;
	}

	@Column(name="wxxmbh",length=600,nullable=true)
	public String getWxxmbh() {
		return wxxmbh;
	}

	public void setWxxmbh(String wxxmbh) {
		this.wxxmbh = wxxmbh;
	}

	@Column(name="XMZL",length=200,nullable=true)
	public String getXmzl() {
        return xmzl;
    }

    public void setXmzl(String xmzl) {
        this.xmzl = xmzl;
    }
    
    @Column(name="SZSF",length=200,nullable=true)
    public String getSzsf() {
        return szsf;
    }

    public void setSzsf(String szsf) {
        this.szsf = szsf;
    }

    @Transient
    public String[] getWxgmArr() {
        if(this.wxgm == null){
            return null;
        }
        return this.wxgm.split("; ");
    }
	
	@Transient
    public String[] getWxxmmcArr() {
	    if(this.wxxmmc == null){
            return null;
        }
        return this.wxxmmc.split(", ");
    }
	
	@Transient
    public String[] getWxxmbhArr() {
	    if(this.wxxmbh == null){
            return null;
        }
        return this.wxxmbh.split(", ");
    }
	
	@Transient
	public int getWxxmsize(){
	    if(this.wxxmmc == null){
            return 0;
        }
        return this.wxxmmc.split(", ").length;
	}
	
	@Column(name="WXZFY")
    public BigDecimal getWxzfy() {
        return wxzfy;
    }

    public void setWxzfy(BigDecimal wxzfy) {
        this.wxzfy = wxzfy;
    }
    
    @Column(name="CREATETIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
	
	
}
