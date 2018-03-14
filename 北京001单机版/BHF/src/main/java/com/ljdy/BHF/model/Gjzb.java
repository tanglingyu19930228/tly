package com.ljdy.BHF.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 国家总表(用于存储国家总表的数据)
 * 修改记录：
 * 		[2017-09-25]增加实体类注解，增加字段‘平均造价’、‘单项投资’、‘合计’ by 徐成
 * 		[2017-09-26]修改字段注解 徐成
 */
@SuppressWarnings("all")
@Entity
@Table(name="GJZB")
public class Gjzb implements java.io.Serializable{
	/**
	 * 主键id
	 * 数据库唯一标识符
	 */
	private String id;
	
	/**
	 * 类型(项目子类+项目建设单位性质+单位)
	 */
	private String type;
	
	/**
	 * 辽宁(存储辽宁统计的数据)
	 */
	private BigDecimal liaoning;
	
	/**
	 * 吉林(存储吉林统计的数据)
	 */
	private BigDecimal jilin;
	
	/**
	 * 黑龙江(存储黑龙江的数据)
	 */
	private BigDecimal heilongjiang;
	
	/**
	 * 内蒙古(存储内蒙古的数据)
	 */
	private BigDecimal neimenggu;
	
	/**
	 * 甘肃（存储甘肃统计数据）
	 */
	private BigDecimal gansu;
	
	/**
	 * 新疆（存储新疆统计数据）
	 */
	private BigDecimal xinjiang;
	
	/**
	 * 河北（存储河北统计数据）
	 */
	private BigDecimal hebei;
	
	/**
	 * 天津（存储天津统计数据）
	 */
	private BigDecimal tianjin;
	
	/**
	 * 山东（存储山东统计数据）
	 */
	private BigDecimal shandong;
	
	/**
	 * 江苏（存储江苏统计数据）
	 */
	private BigDecimal jiangsu;
	
	/**
	 * 上海（存储上海统计数据）
	 */
	private BigDecimal shanghai;
	
	/**
	 * 浙江（存储浙江统计数据）
	 */
	private BigDecimal zhejiang;
	
	/**
	 * 福建（存储福建统计数据）
	 */
	private BigDecimal fujian;
	
	/**
	 * 广东（存储广东统计数据）
	 */
	private BigDecimal guangdong;
	
	/**
	 * 海南（存储海南统计数据）
	 */
	private BigDecimal hainan;
	
	/**
	 * 广西（存储广西统计数据）
	 */
	private BigDecimal guangxi;
	
	/**
	 * 西藏（存储西藏统计数据）
	 */
	private BigDecimal xizang;
	
	/**
	 * 云南（存储云南统计数据）
	 */
	private BigDecimal yunnan;
	
	/**
	 * 平均造价
	 */
	private BigDecimal average;
	
	/**
	 * 单项投资
	 */
	private BigDecimal dxtz;
	
	/**
	 * 合计
	 */
	private BigDecimal heji;

	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="liaoning")
	public BigDecimal getLiaoning() {
		return liaoning;
	}

	public void setLiaoning(BigDecimal liaoning) {
		this.liaoning = liaoning;
	}

	@Column(name="jilin")
	public BigDecimal getJilin() {
		return jilin;
	}

	public void setJilin(BigDecimal jilin) {
		this.jilin = jilin;
	}

	@Column(name="heilongjiang")
	public BigDecimal getHeilongjiang() {
		return heilongjiang;
	}

	public void setHeilongjiang(BigDecimal heilongjiang) {
		this.heilongjiang = heilongjiang;
	}

	@Column(name="neimenggu")
	public BigDecimal getNeimenggu() {
		return neimenggu;
	}

	public void setNeimenggu(BigDecimal neimenggu) {
		this.neimenggu = neimenggu;
	}

	@Column(name="gansu")
	public BigDecimal getGansu() {
		return gansu;
	}

	public void setGansu(BigDecimal gansu) {
		this.gansu = gansu;
	}

	@Column(name="xinjiang")
	public BigDecimal getXinjiang() {
		return xinjiang;
	}

	public void setXinjiang(BigDecimal xinjiang) {
		this.xinjiang = xinjiang;
	}

	@Column(name="hebei")
	public BigDecimal getHebei() {
		return hebei;
	}

	public void setHebei(BigDecimal hebei) {
		this.hebei = hebei;
	}

	@Column(name="tianjin")
	public BigDecimal getTianjin() {
		return tianjin;
	}

	public void setTianjin(BigDecimal tianjin) {
		this.tianjin = tianjin;
	}

	@Column(name="shandong")
	public BigDecimal getShandong() {
		return shandong;
	}

	public void setShandong(BigDecimal shandong) {
		this.shandong = shandong;
	}

	@Column(name="jiangsu")
	public BigDecimal getJiangsu() {
		return jiangsu;
	}

	public void setJiangsu(BigDecimal jiangsu) {
		this.jiangsu = jiangsu;
	}

	@Column(name="shanghai")
	public BigDecimal getShanghai() {
		return shanghai;
	}

	public void setShanghai(BigDecimal shanghai) {
		this.shanghai = shanghai;
	}

	@Column(name="zhejiang")
	public BigDecimal getZhejiang() {
		return zhejiang;
	}

	public void setZhejiang(BigDecimal zhejiang) {
		this.zhejiang = zhejiang;
	}

	@Column(name="fujian")
	public BigDecimal getFujian() {
		return fujian;
	}

	public void setFujian(BigDecimal fujian) {
		this.fujian = fujian;
	}

	@Column(name="guangdong")
	public BigDecimal getGuangdong() {
		return guangdong;
	}

	public void setGuangdong(BigDecimal guangdong) {
		this.guangdong = guangdong;
	}

	@Column(name="hainan")
	public BigDecimal getHainan() {
		return hainan;
	}

	public void setHainan(BigDecimal hainan) {
		this.hainan = hainan;
	}

	@Column(name="guangxi")
	public BigDecimal getGuangxi() {
		return guangxi;
	}

	public void setGuangxi(BigDecimal guangxi) {
		this.guangxi = guangxi;
	}

	@Column(name="xizang")
	public BigDecimal getXizang() {
		return xizang;
	}

	public void setXizang(BigDecimal xizang) {
		this.xizang = xizang;
	}

	@Column(name="yunnan")
	public BigDecimal getYunnan() {
		return yunnan;
	}

	public void setYunnan(BigDecimal yunnan) {
		this.yunnan = yunnan;
	}

	@Column(name="average")
	public BigDecimal getAverage() {
		return average;
	}

	public void setAverage(BigDecimal average) {
		this.average = average;
	}

	@Column(name="dxtz")
	public BigDecimal getDxtz() {
		return dxtz;
	}

	public void setDxtz(BigDecimal dxtz) {
		this.dxtz = dxtz;
	}

	@Column(name="heji")
	public BigDecimal getHeji() {
		return heji;
	}

	public void setHeji(BigDecimal heji) {
		this.heji = heji;
	}
}
