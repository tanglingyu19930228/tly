package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 交通保障设施----执勤码头
 * @author 郎川
 * 修改记录：
 * 		[2017-09-25]增加实体类注解 by 徐成
 */
@SuppressWarnings("all")
@Entity
@Table(name="ZQMT")
public class Zqmt extends Jbxx implements java.io.Serializable{
	
	/**
	 * 建设规模(座)
	 */
	private Integer jsgm;
	
	/**
	 * 建设地点
	 */
	private String jsdd;

	@Column(name="JSGM")
	public Integer getJsgm() {
		return jsgm;
	}

	public void setJsgm(Integer jsgm) {
		this.jsgm = jsgm;
	}

	@Column(name="JSDD")
	public String getJsdd() {
		return jsdd;
	}

	public void setJsdd(String jsdd) {
		this.jsdd = jsdd;
	}

}
