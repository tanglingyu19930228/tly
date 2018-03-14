package com.ljdy.BHF.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 指挥监控设施----供电系统
 * 
 * @author 郎川
 * 修改记录
 * 	[2017-09-25] 实现继承基本信息表 by 郎川
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name="GDXT")
public class AddGdxt  implements java.io.Serializable{

	

	/**
	 * 所在监控站标识
	 */
	private String jkz_id;
	
	/**
     * 所在监控站名称
     */
    private String jkz_name;

	/**
	 * 建设地点
	 */
	private String jsdd;

	/**
	 * 建设规模(套)
	 */
	private Integer jsgm;
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

    @Column(name="JSDD",length=2000,nullable=false)
	public String getJsdd() {
		return jsdd;
	}

	public void setJsdd(String jsdd) {
		this.jsdd = jsdd;
	}

	@Column(name="JSGM")
	public Integer getJsgm() {
		return jsgm;
	}

	public void setJsgm(Integer jsgm) {
		this.jsgm = jsgm;
	}
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AddGdxt(String jkz_id, String jkz_name, String jsdd, Integer jsgm,
			String id) {
		super();
		this.jkz_id = jkz_id;
		this.jkz_name = jkz_name;
		this.jsdd = jsdd;
		this.jsgm = jsgm;
		this.id = id;
	}

	public AddGdxt() {
		// TODO Auto-generated constructor stub
	}
	

}
