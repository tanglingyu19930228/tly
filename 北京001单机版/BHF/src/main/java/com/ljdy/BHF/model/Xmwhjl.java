package com.ljdy.BHF.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.ljdy.BHF.utils.DateUtils;

/**
 * 项目维护记录表
 * 
 * @author 郎川
 * 修改记录：
 * 		[2017-09-25]增加实体类注解及级联关系配置 by 徐成
 */
@SuppressWarnings("all")
@Entity
@Table(name="XMWHJL")
public class Xmwhjl implements java.io.Serializable{

	/**
	 * 主键id 数据库唯一标识
	 */
	private String id;
	
	/**
	 * 使用状态（共用字典表 typename='使用状态'）
	 */
	private String syzt;
	
	/**
	 * 维修项目名称
	 */
	private String wxxmmc;
	
	/**
	 * 维修项目变化
	 */
	private String wxxmbh;
	/**
	 * 维修项目id
	 */
	private String wxxmid;
	
	/**
	 * 维修规模
	 */
	private String wxgm;

	/**
	 * 故障设备
	 */
	private String gzsb;

	/**
	 * 故障设备品牌
	 */
	private String gzsbpp;

	/**
	 * 故障设备型号
	 */
	private String gzsbxh;

	/**
	 * 故障部件
	 */
	private String gzbj;

	/**
	 * 损坏原因
	 */
	private String shyy;

	/**
	 * 故障时间
	 */
	private Date gzsj;

	/**
	 * 维修时间
	 */
	private Date wxsj;

	/**
	 * 故障时间显示名
	 */
	private String gzsjxsm;
	
	/**
	 * 维修时间显示名
	 */
	private String wxsjxsm;
	
	/**维修费用
	 */
	private BigDecimal wxfy;
	
	/**
	 * 维护项目id
	 */
	private String whxmid;
	
	/**
	 * 维护记录状态 ：   删除，则表示与其想关联的项目子类已删除。
	 */
	private String whjlzt;
	
	/**
	 * 维护状态
	 */
	private String whzt;
	
//	private Jbxx jbxx;
   

	@Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="syzt")
	public String getSyzt() {
		return syzt;
	}

	public void setSyzt(String syzt) {
		this.syzt = syzt;
	}
	
	@Column(name="wxxmmc")
	public String getWxxmmc() {
        return wxxmmc;
    }

    public void setWxxmmc(String wxxmmc) {
        this.wxxmmc = wxxmmc;
    }
    
    @Column(name="wxxmbh")
    public String getWxxmbh() {
        return wxxmbh;
    }

    public void setWxxmbh(String wxxmbh) {
        this.wxxmbh = wxxmbh;
    }
    
    @Column(name="wxxmid")
    public String getWxxmid() {
        return wxxmid;
    }

    public void setWxxmid(String wxxmid) {
        this.wxxmid = wxxmid;
    }

    @Column(name="wxgm")
	public String getWxgm() {
		return wxgm;
	}

	public void setWxgm(String wxgm) {
		this.wxgm = wxgm;
	}

	@Column(name="gzsb")
	public String getGzsb() {
		return gzsb;
	}

	public void setGzsb(String gzsb) {
		this.gzsb = gzsb;
	}

	@Column(name="gzsbpp")
	public String getGzsbpp() {
		return gzsbpp;
	}

	public void setGzsbpp(String gzsbpp) {
		this.gzsbpp = gzsbpp;
	}

	@Column(name="gzsbxh")
	public String getGzsbxh() {
		return gzsbxh;
	}

	public void setGzsbxh(String gzsbxh) {
		this.gzsbxh = gzsbxh;
	}

	@Column(name="gzbj")
	public String getGzbj() {
		return gzbj;
	}

	public void setGzbj(String gzbj) {
		this.gzbj = gzbj;
	}

	@Column(name="shyy")
	public String getShyy() {
		return shyy;
	}

	public void setShyy(String shyy) {
		this.shyy = shyy;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="gzsj")
	public Date getGzsj() {
		return gzsj;
	}

	public void setGzsj(Date gzsj) {
		this.gzsj = gzsj;
	}
	   
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="wxsj")
	public Date getWxsj() {
		return wxsj;
	}

	public void setWxsj(Date wxsj) {
		this.wxsj = wxsj;
	}

//    @ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name="XMID")
//	public Jbxx getJbxx() {
//		return jbxx;
//	}
//
//	public void setJbxx(Jbxx jbxx) {
//		this.jbxx = jbxx;
//	}

	@Transient
    public String getGzsjxsm() {
	    if(this.gzsj == null){
	        return "";
	    }
        return DateUtils.formatDate(this.gzsj, "yyyy-MM-dd");
    }
	
	@Transient
    public String getWxsjxsm() {
	    if(this.wxsj == null){
            return "";
        }
        return DateUtils.formatDate(this.wxsj, "yyyy-MM-dd");
    }
	
    @Column(name="WXFY")
    public BigDecimal getWxfy() {
        return wxfy;
    }

    public void setWxfy(BigDecimal wxfy) {
        this.wxfy = wxfy;
    }
    
    
    @Column(name="WHXMID")
    public String getWhxmid() {
        return whxmid;
    }

    public void setWhxmid(String whxmid) {
        this.whxmid = whxmid;
    }

    @Column(name="WHJLZT")
    public String getWhjlzt() {
        return whjlzt;
    }

    public void setWhjlzt(String whjlzt) {
        this.whjlzt = whjlzt;
    }
    
    @Column(name="WHZT")
    public String getWhzt() {
        return whzt;
    }

    public void setWhzt(String whzt) {
        this.whzt = whzt;
    }
    
    
	
}
