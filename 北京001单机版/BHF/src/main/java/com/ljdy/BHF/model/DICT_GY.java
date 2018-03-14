package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 共用字典表
 * @author 郎川
 * 修改记录：
 * 		[2017-09-25]增加实体类注解 by 徐成
 */
@SuppressWarnings("all")
@Entity
@Table(name="DICT_GY")
public class DICT_GY implements java.io.Serializable{
	
	/**
	 * 主键id 数据库唯一标识符
	 */
	private String id;
	
	/**
	 * 字典类型
	 */
	private String typeName;
	
	/**
	 * 字典名称
	 */
	private String codeName;
	
	/**
	 * 字典编码
	 */
	private String codeValue;
	
	/**
	 * 上级字典编码
	 */
	private String superCode;
	
	/**
	 * 排序号
	 */
	private Integer orderCode;
	
	/**
	 * 备注
	 */
	private String remark;

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

	@Column(name="typeName")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name="codeName")
	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	@Column(name="codeValue")
	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	@Column(name="superCode")
	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode;
	}

	@Column(name="orderCode")
	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	@Column(name="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
