package com.ljdy.BHF.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class AddZqmt  implements java.io.Serializable{
	
	/**
	 * 建设规模(座)
	 */
	private Integer jsgm; 
	
	/**
	 * 建设地点
	 */
	private String jsdd;
	
	private String id;

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
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddZqmt(Integer jsgm, String jsdd, String id) {
		super();
		this.jsgm = jsgm;
		this.jsdd = jsdd;
		this.id = id;
	}

	public AddZqmt() {
		// TODO Auto-generated constructor stub
	}
}

