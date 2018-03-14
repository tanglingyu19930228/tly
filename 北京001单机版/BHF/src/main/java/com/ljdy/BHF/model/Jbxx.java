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
 * @author 郎川
 * 修改记录：
 * [2017-09-25]增加实体类注解及级联关系配置 by 徐成
 * [2017-10-10]增加注解JsonIgnore 消除对象转Json时序列化异常 by 徐成
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name = "JBXX")
@Inheritance(strategy=InheritanceType.JOINED)
public class Jbxx implements java.io.Serializable{

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
	 *//*
	private String jwd;*/
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
	
	/**
	 * 执勤道路
	 */
	private transient Zqdl zqdl;

	/**
	 * 桥梁
	 */
	private QiaoLiang qiaoLiang;
	
	/**
	 * 执勤码头
	 */
	private Zqmt zqmt;
	
	/**
	 * 直升机停机坪
	 */
	private Zsjtjp zsjtjp;
	
	/**
	 * 报警线路
	 */
	private Bjxl bjxl;
	
	/**
	 * 报警装置
	 */
	private Bjzz bjzz;
	
	/**
	 * 标志牌
	 */
	private Bzp bzp;
	/**
	 * 传输线路
	 */
	private Csxl csxl;
	/**
	 * 供电系统
	 * @return
	 */
	private Gdxt gdxt;
	
	/**
	 * 隔离带
	 * @return
	 */
	private Gld gld;
	/**
	 * 指挥监控设施----军警民联防平台
	 * @return
	 */
	private Jjmlfpt jjmlfpt;
	
	/**
	 * 监控站
	 */
	private Jkz jkz;
	/**
	 * 
	 * 监控中心
	 */
	private Jkzx jkzx;
	/**
	 * 拒马
	 * @return
	 */
	private Juma juma;
	/**
	 * 
	 * 瞭望塔
	 */
	private Lwt lwt;
	/**
	 * 
	 * 拦阻桩
	 */
	private Lzz lzz;
	/**
	 * 
	 * 视频前端
	 */
	private Spqd spqd;
	/**
	 * 
	 * 铁丝网
	 */
	private Tsw tsw;
	/**
	 * 
	 * 铁栅栏
	 */
	private Tzl tzl;
	/**
	 * 
	 * 无人值守电子哨兵
	 */
	private Wrzsdzsb wrzsdzsb;
	/**
	 * 
	 * 显控终端
	 */
	private Xkzd xkzd;
	/**
	 * 
	 *执勤房
	 */
	private Zqf zqf;
	
	/**
	 * 附件显示名
	 */
	private String fjxsm;
	
	/**
	 * 招投标时间显示名 （模板渲染无法格式化）
	 */
	private String ztbsjxsm;
	/**
	 * 开工时间显示名 
	 */
	private String kgsjxsm;
	
	/**
	 * 初验时间显示名
	 */
	private String cysjxsm;
	/**
	 * 竣工时间显示名
	 */
	private String jgsjxsm;
	/**
	 * 审计时间显示名
	 */
	private String sjsjxsm;
	
//	/**
//	 * 项目维护记录
//	 */
//	private List<Xmwhjl> xmwhjlList;
//	
//	@OneToMany(cascade=CascadeType.ALL,mappedBy="xmid")
//	public List<Xmwhjl> getXmwhjlList() {
//		return xmwhjlList;
//	}
//
//	public void setXmwhjlList(List<Xmwhjl> xmwhjlList) {
//		this.xmwhjlList = xmwhjlList;
//	}

	@Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}
	
    public void setId(String id) {
		this.id = id;
	}

	@Column(name="XMLB")
	public String getXmlb() {
		return xmlb;
	}

	public void setXmlb(String xmlb) {
		this.xmlb = xmlb;
	}

	@Column(name="XMZL")
	public String getXmzl() {
		return xmzl;
	}

	public void setXmzl(String xmzl) {
		this.xmzl = xmzl;
	}

	@Column(name="XMMC")
	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	@Column(name="XMBH")
	public String getXmbh() {
		return xmbh;
	}

	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}

	@Column(name="BJFX")
	public String getBjfx() {
		return bjfx;
	}

	public void setBjfx(String bjfx) {
		this.bjfx = bjfx;
	}

	@Column(name="SSLX")
	public String getSslx() {
		return sslx;
	}

	public void setSslx(String sslx) {
		this.sslx = sslx;
	}

	@Column(name="SZSF")
	public String getSzsf() {
	    if(this.szsf == null){
	        return "";
	    }
		return szsf;
	}

	public void setSzsf(String szsf) {
		this.szsf = szsf;
	}

	@Column(name="SZCS")
	public String getSzcs() {
	    if(this.szcs == null){
            return "";
        }
		return szcs;
	}

	public void setSzcs(String szcs) {
		this.szcs = szcs;
	}

	@Column(name="SZQ")
	public String getSzq() {
	    if(this.szq == null){
            return "";
        }
		return szq;
	}

	public void setSzq(String szq) {
		this.szq = szq;
	}

	/*@Column(name="JWD")
	public String getJwd() {
		return jwd;
	}

	public void setJwd(String jwd) {
		this.jwd = jwd;
	}*/

	@Column(name="DXLB")
	public String getDxlb() {
		return dxlb;
	}
	@Column(name="JD")
	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}
	
	@Column(name="WD")
	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public void setDxlb(String dxlb) {
		this.dxlb = dxlb;
	}

	@Column(name="JSXZ")
	public String getJsxz() {
		return jsxz;
	}

	public void setJsxz(String jsxz) {
		this.jsxz = jsxz;
	}

	@Column(name="SYDW")
	public String getSydw() {
		return sydw;
	}

	public void setSydw(String sydw) {
		this.sydw = sydw;
	}

	@Column(name="SBDW")
	public String getSbdw() {
		return sbdw;
	}

	public void setSbdw(String sbdw) {
		this.sbdw = sbdw;
	}

	@Column(name="ZYTZ")
	public Double getZytz() {
		return zytz;
	}

	public void setZytz(Double zytz) {
		this.zytz = zytz;
	}

	@Column(name="DFTZ")
	public Double getDftz() {
		return dftz;
	}

	public void setDftz(Double dftz) {
		this.dftz = dftz;
	}

	@Column(name="FJ")
	public String getFj() {
		return fj;
	}

	public void setFj(String fj) {
		this.fj = fj;
	}

	@Column(name="BZ")
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name="BXXM")
	public String getBxxm() {
		return bxxm;
	}

	public void setBxxm(String bxxm) {
		this.bxxm = bxxm;
	}

	@Column(name="TZND")
	public String getTznd() {
		return tznd;
	}

	public void setTznd(String tznd) {
		this.tznd = tznd;
	}

	@Column(name="JSZT")
	public String getJszt() {
		return jszt;
	}

	public void setJszt(String jszt) {
		this.jszt = jszt;
	}

	@Column(name="CJDW")
	public String getCjdw() {
		return cjdw;
	}

	public void setCjdw(String cjdw) {
		this.cjdw = cjdw;
	}

	@Column(name="JLDW")
	public String getJldw() {
		return jldw;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}

	@Column(name="ZTBSJ")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getZtbsj() {
		return ztbsj;
	}

	public void setZtbsj(Date ztbsj) {
		this.ztbsj = ztbsj;
	}

	@Column(name="KGSJ")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getKgsj() {
		return kgsj;
	}

	public void setKgsj(Date kgsj) {
		this.kgsj = kgsj;
	}

	@Column(name="CYSJ")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getCysj() {
		return cysj;
	}

	public void setCysj(Date cysj) {
		this.cysj = cysj;
	}

	@Column(name="JGSJ")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getJgsj() {
		return jgsj;
	}

	public void setJgsj(Date jgsj) {
		this.jgsj = jgsj;
	}

	@Column(name="SJSJ")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getSjsj() {
		return sjsj;
	}

	public void setSjsj(Date sjsj) {
		this.sjsj = sjsj;
	}

	@Column(name="TIME")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @JsonIgnore
	public Zqdl getZqdl() {
		return zqdl;
	}

	public void setZqdl(Zqdl zqdl) {
		this.zqdl = zqdl;
	}

	@OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @JsonIgnore
	public QiaoLiang getQiaoLiang() {
		return qiaoLiang;
	}

	public void setQiaoLiang(QiaoLiang qiaoLiang) {
		this.qiaoLiang = qiaoLiang;
	}

	@OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @JsonIgnore
	public Zqmt getZqmt() {
		return zqmt;
	}

	public void setZqmt(Zqmt zqmt) {
		this.zqmt = zqmt;
	}

	@OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @JsonIgnore
	public Zsjtjp getZsjtjp() {
		return zsjtjp;
	}

	public void setZsjtjp(Zsjtjp zsjtjp) {
		this.zsjtjp = zsjtjp;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Bjxl getBjxl() {
		return bjxl;
	}

	public void setBjxl(Bjxl bjxl) {
		this.bjxl = bjxl;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Bjzz getBjzz() {
		return bjzz;
	}

	public void setBjzz(Bjzz bjzz) {
		this.bjzz = bjzz;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Bzp getBzp() {
		return bzp;
	}

	public void setBzp(Bzp bzp) {
		this.bzp = bzp;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Csxl getCsxl() {
		return csxl;
	}

	public void setCsxl(Csxl csxl) {
		this.csxl = csxl;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Gdxt getGdxt() {
		return gdxt;
	}

	public void setGdxt(Gdxt gdxt) {
		this.gdxt = gdxt;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Gld getGld() {
		return gld;
	}

	public void setGld(Gld gld) {
		this.gld = gld;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Jjmlfpt getJjmlfpt() {
		return jjmlfpt;
	}

	public void setJjmlfpt(Jjmlfpt jjmlfpt) {
		this.jjmlfpt = jjmlfpt;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Jkz getJkz() {
		return jkz;
	}

	public void setJkz(Jkz jkz) {
		this.jkz = jkz;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Jkzx getJkzx() {
		return jkzx;
	}

	public void setJkzx(Jkzx jkzx) {
		this.jkzx = jkzx;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Juma getJuma() {
		return juma;
	}

	public void setJuma(Juma juma) {
		this.juma = juma;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Lwt getLwt() {
		return lwt;
	}

	public void setLwt(Lwt lwt) {
		this.lwt = lwt;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Lzz getLzz() {
		return lzz;
	}

	public void setLzz(Lzz lzz) {
		this.lzz = lzz;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Spqd getSpqd() {
		return spqd;
	}

	public void setSpqd(Spqd spqd) {
		this.spqd = spqd;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Tsw getTsw() {
		return tsw;
	}

	public void setTsw(Tsw tsw) {
		this.tsw = tsw;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Tzl getTzl() {
		return tzl;
	}

	public void setTzl(Tzl tzl) {
		this.tzl = tzl;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Wrzsdzsb getWrzsdzsb() {
		return wrzsdzsb;
	}

	public void setWrzsdzsb(Wrzsdzsb wrzsdzsb) {
		this.wrzsdzsb = wrzsdzsb;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Xkzd getXkzd() {
		return xkzd;
	}

	public void setXkzd(Xkzd xkzd) {
		this.xkzd = xkzd;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	public Zqf getZqf() {
		return zqf;
	}

	public void setZqf(Zqf zqf) {
		this.zqf = zqf;
	}
	@Transient
	public String getFjxsm(){
	    if(this.fj == null){
	        return "";
	    }
	    String [] arr = this.fj.split(",");
	    String fjNew = "";
	    for (int i = 0; i < arr.length; i++) {
            int last = arr[i].lastIndexOf("/");//斜线后面是uuid生成的，为32位
            if(last != -1 && last <= arr[i].length() -33){
                fjNew = fjNew + arr[i].substring(last + 33) + ",";
            }
        }
	    if(fjNew.endsWith(",")){
	        fjNew = fjNew.substring(0, fjNew.length() -1);
	    }
	    return fjNew;
	}
	
	@Transient
	public String getZtbsjxsm() {
	    if(this.ztbsj == null){
	        return "";
	    }
	    return DateUtils.formatDate(this.ztbsj, "yyyy-MM-dd");
	}
	
	@Transient
    public String getKgsjxsm() {
	    if(this.kgsj == null){
	        return "";
	    }
        return DateUtils.formatDate(this.kgsj, "yyyy-MM-dd");
    }
    
    @Transient
    public String getCysjxsm() {
        if(this.cysj == null){
            return "";
        }
        return DateUtils.formatDate(this.cysj, "yyyy-MM-dd");
    }
    
    @Transient
    public String getJgsjxsm() {
        if(this.jgsj == null){
            return "";
        }
        return DateUtils.formatDate(this.jgsj, "yyyy-MM-dd");
    }
    
    @Transient
    public String getSjsjxsm() {
        if(this.sjsj == null){
            return "";
        }
        return DateUtils.formatDate(this.sjsj, "yyyy-MM-dd");
    }
    
    @Column(name="SYZT")
    public String getSyzt() {
        return syzt;
    }
    
	public void setSyzt(String syzt) {
		this.syzt = syzt;
	}

}
