package com.ljdy.BHF.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ljdy.BHF.utils.DateUtils;

/**
 * 各类项目共有信息表
 * 
 * @author 郎川 修改记录： [2017-09-25]增加实体类注解及级联关系配置 by 徐成 [2017-10-10]增加注解JsonIgnore
 *         消除对象转Json时序列化异常 by 徐成
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name = "JBXX")
@Inheritance(strategy = InheritanceType.JOINED)
public class AddJbxx implements java.io.Serializable {
	/**
	 * 主键id 数据库唯一标识符
	 */
	private String id;

	/**
	 * 项目类别（共用字典表 typename=‘项目类别’）
	 */
	private String xmlb;

	/**
	 * 项目子类（共用字典表 typename=‘项目子类’）
	 */
	private String xmzl;

	/**
	 * 项目名称（使用单位全称+项目子类）
	 */
	private String xmmc;

	/**
	 * 项目编号（系统自动生成）
	 */
	private String xmbh;
	/**
	 * 边界方向（共用字典表 typename=‘边界方向’）
	 */
	private String bjfx;

	/**
	 * 设施类型（共用字典表 typename=‘设施类型’）
	 */
	private String sslx;

	/**
	 * 建设区域 所在省份（直辖市）
	 */
	private String szsf;

	/**
	 * 建设区域 所在市（区）
	 */
	private String szcs;

	/**
	 * 建设区域 所在区（县）
	 */
	private String szq;

	/**
	 * 经纬度
	 */
	/*
	 * private String jwd;
	 */
	/**
	 * 经度
	 */
	private String jd;
	/**
	 * 纬度
	 */
	private String wd;

	/**
	 * 地形类别（共用字典表 typename='地形类别'）
	 */
	private String dxlb;
	/**
	 * 建设性质（共用字典表 typename=‘建设性质’）
	 */

	private String jsxz;
	/**
	 * 使用单位
	 */

	private String sydw;

	/**
	 * 申报单位(各级边海防委员会办公室)
	 */

	private String sbdw;

	/**
	 * 中央投资(元)
	 */
	private Double zytz;

	/**
	 * 地方投资(元)
	 */
	private Double dftz;

	/**
	 * 附件，存储项目相关文件路径
	 */
	private String fj;
	/**
	 * 备注，项目相关文字描述
	 */

	private String bz;

	/**
	 * 备选库专用字段 备选项目
	 */
	private String bxxm;

	/**
	 * 备选库专用字段 投资年度
	 */
	private String tznd;

	/**
	 * 在建库专用字段 建设状态（共用字典表 typename='建设状态'）
	 */
	private String jszt;

	/**
	 * 在建库专用字段 承建单位
	 */
	private String cjdw;
	/**
	 * 在建库专用字段 监理单位
	 */

	private String jldw;

	/**
	 * 在建库专用字段 招投标时间
	 */
	private Date ztbsj;

	/**
	 * 在建库专用字段 开工时间
	 */
	private Date kgsj;
	/**
	 * 在建库专用字段 初验时间
	 */
	private Date cysj;

	/**
	 * 在建库专用字段 竣工时间
	 */
	private Date jgsj;
	/**
	 * 在建库专用字段 审计时间
	 */

	private Date sjsj;

	/**
	 * 使用状态
	 */
	private String syzt;

	/**
	 * 数据创建或修改时间
	 */
	private Date time;

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "XMLB")
	public String getXmlb() {
		return xmlb;
	}

	public void setXmlb(String xmlb) {
		this.xmlb = xmlb;
	}

	@Column(name = "XMZL")
	public String getXmzl() {
		return xmzl;
	}

	public void setXmzl(String xmzl) {
		this.xmzl = xmzl;
	}

	@Column(name = "XMMC")
	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	@Column(name = "XMBH")
	public String getXmbh() {
		return xmbh;
	}

	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}

	@Column(name = "BJFX")
	public String getBjfx() {
		return bjfx;
	}

	public void setBjfx(String bjfx) {
		this.bjfx = bjfx;
	}

	@Column(name = "SSLX")
	public String getSslx() {
		return sslx;
	}

	public void setSslx(String sslx) {
		this.sslx = sslx;
	}

	@Column(name = "SZSF")
	public String getSzsf() {
		if (this.szsf == null) {
			return "";
		}
		return szsf;
	}

	public void setSzsf(String szsf) {
		this.szsf = szsf;
	}

	@Column(name = "SZCS")
	public String getSzcs() {
		if (this.szcs == null) {
			return "";
		}
		return szcs;
	}

	public void setSzcs(String szcs) {
		this.szcs = szcs;
	}

	@Column(name = "SZQ")
	public String getSzq() {
		if (this.szq == null) {
			return "";
		}
		return szq;
	}

	public void setSzq(String szq) {
		this.szq = szq;
	}

	/*
	 * @Column(name="JWD") public String getJwd() { return jwd; }
	 * 
	 * public void setJwd(String jwd) { this.jwd = jwd; }
	 */

	@Column(name = "DXLB")
	public String getDxlb() {
		return dxlb;
	}

	@Column(name = "JD")
	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	@Column(name = "WD")
	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public void setDxlb(String dxlb) {
		this.dxlb = dxlb;
	}

	@Column(name = "JSXZ")
	public String getJsxz() {
		return jsxz;
	}

	public void setJsxz(String jsxz) {
		this.jsxz = jsxz;
	}

	@Column(name = "SYDW")
	public String getSydw() {
		return sydw;
	}

	public void setSydw(String sydw) {
		this.sydw = sydw;
	}

	@Column(name = "SBDW")
	public String getSbdw() {
		return sbdw;
	}

	public void setSbdw(String sbdw) {
		this.sbdw = sbdw;
	}

	@Column(name = "ZYTZ")
	public Double getZytz() {
		return zytz;
	}

	public void setZytz(Double zytz) {
		this.zytz = zytz;
	}

	@Column(name = "DFTZ")
	public Double getDftz() {
		return dftz;
	}

	public void setDftz(Double dftz) {
		this.dftz = dftz;
	}

	@Column(name = "FJ")
	public String getFj() {
		return fj;
	}

	public void setFj(String fj) {
		this.fj = fj;
	}

	@Column(name = "BZ")
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "BXXM")
	public String getBxxm() {
		return bxxm;
	}

	public void setBxxm(String bxxm) {
		this.bxxm = bxxm;
	}

	@Column(name = "TZND")
	public String getTznd() {
		return tznd;
	}

	public void setTznd(String tznd) {
		this.tznd = tznd;
	}

	@Column(name = "JSZT")
	public String getJszt() {
		return jszt;
	}

	public void setJszt(String jszt) {
		this.jszt = jszt;
	}

	@Column(name = "CJDW")
	public String getCjdw() {
		return cjdw;
	}

	public void setCjdw(String cjdw) {
		this.cjdw = cjdw;
	}

	@Column(name = "JLDW")
	public String getJldw() {
		return jldw;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}

	@Column(name = "ZTBSJ")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getZtbsj() {
		return ztbsj;
	}

	public void setZtbsj(Date ztbsj) {
		this.ztbsj = ztbsj;
	}

	@Column(name = "KGSJ")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getKgsj() {
		return kgsj;
	}

	public void setKgsj(Date kgsj) {
		this.kgsj = kgsj;
	}

	@Column(name = "CYSJ")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getCysj() {
		return cysj;
	}

	public void setCysj(Date cysj) {
		this.cysj = cysj;
	}

	@Column(name = "JGSJ")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getJgsj() {
		return jgsj;
	}

	public void setJgsj(Date jgsj) {
		this.jgsj = jgsj;
	}

	@Column(name = "SJSJ")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getSjsj() {
		return sjsj;
	}

	public void setSjsj(Date sjsj) {
		this.sjsj = sjsj;
	}

	@Column(name = "TIME")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Transient
	public String getFjxsm() {
		if (this.fj == null) {
			return "";
		}
		String[] arr = this.fj.split(",");
		String fjNew = "";
		for (int i = 0; i < arr.length; i++) {
			int last = arr[i].lastIndexOf("/");// 斜线后面是uuid生成的，为32位
			if (last != -1 && last <= arr[i].length() - 33) {
				fjNew = fjNew + arr[i].substring(last + 33) + ",";
			}
		}
		if (fjNew.endsWith(",")) {
			fjNew = fjNew.substring(0, fjNew.length() - 1);
		}
		return fjNew;
	}

	@Transient
	public String getZtbsjxsm() {
		if (this.ztbsj == null) {
			return "";
		}
		return DateUtils.formatDate(this.ztbsj, "yyyy-MM-dd");
	}

	@Transient
	public String getKgsjxsm() {
		if (this.kgsj == null) {
			return "";
		}
		return DateUtils.formatDate(this.kgsj, "yyyy-MM-dd");
	}

	@Transient
	public String getCysjxsm() {
		if (this.cysj == null) {
			return "";
		}
		return DateUtils.formatDate(this.cysj, "yyyy-MM-dd");
	}

	@Transient
	public String getJgsjxsm() {
		if (this.jgsj == null) {
			return "";
		}
		return DateUtils.formatDate(this.jgsj, "yyyy-MM-dd");
	}

	@Transient
	public String getSjsjxsm() {
		if (this.sjsj == null) {
			return "";
		}
		return DateUtils.formatDate(this.sjsj, "yyyy-MM-dd");
	}

	@Column(name = "SYZT")
	public String getSyzt() {
		return syzt;
	}

	public void setSyzt(String syzt) {
		this.syzt = syzt;
	}

	public AddJbxx(String id, String xmlb, String xmzl, String xmmc,
			String xmbh, String bjfx, String sslx, String szsf, String szcs,
			String szq, String jd, String wd, String dxlb, String jsxz,
			String sydw, String sbdw, Double zytz, Double dftz, String fj,
			String bz, String bxxm, String tznd, String jszt, String cjdw,
			String jldw, Date ztbsj, Date kgsj, Date cysj, Date jgsj,
			Date sjsj, String syzt, Date time) {
		super();
		this.id = id;
		this.xmlb = xmlb;
		this.xmzl = xmzl;
		this.xmmc = xmmc;
		this.xmbh = xmbh;
		this.bjfx = bjfx;
		this.sslx = sslx;
		this.szsf = szsf;
		this.szcs = szcs;
		this.szq = szq;
		this.jd = jd;
		this.wd = wd;
		this.dxlb = dxlb;
		this.jsxz = jsxz;
		this.sydw = sydw;
		this.sbdw = sbdw;
		this.zytz = zytz;
		this.dftz = dftz;
		this.fj = fj;
		this.bz = bz;
		this.bxxm = bxxm;
		this.tznd = tznd;
		this.jszt = jszt;
		this.cjdw = cjdw;
		this.jldw = jldw;
		this.ztbsj = ztbsj;
		this.kgsj = kgsj;
		this.cysj = cysj;
		this.jgsj = jgsj;
		this.sjsj = sjsj;
		this.syzt = syzt;
		this.time = time;
	}

	public AddJbxx() {
		super();
	}
}
