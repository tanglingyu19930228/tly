package com.ljdy.BHF.model;

import java.util.List;

/**
 * 省级报表数据中间类
 * @author LJDY817
 *
 */
public class ProvinceTabelData {
	//建设性质
	private String jsxz;
	
	//项目数据
	private List<Object> dataList;
	
	//建设数量合计
	private double jsgm_sum;
	
	//建设投资合计
	private double jstz_sum;
	
	//中央投资合计
	private double zytz_sum;
	
	//地方投资合计
	private double dftz_sum;
	
	//数据行数
	private int rows;

	public ProvinceTabelData(String jsxz, List<Object> dataList) {
	    super();
	    this.jsxz = jsxz;
	    this.dataList = dataList;
	    this.rows = dataList==null || dataList.size()==0 ?1:dataList.size();
	    for (Object o : dataList) {
			if(o instanceof Zqdl){
				Zqdl zqdl = (Zqdl) o;
				this.jsgm_sum += zqdl.getJsgm().doubleValue();
			}else if(o instanceof QiaoLiang){
				QiaoLiang qiaoLiang = (QiaoLiang)o;
				this.jsgm_sum += qiaoLiang.getJsgm();
			}else if(o instanceof Zqmt){
				Zqmt zqmt = (Zqmt) o;
				this.jsgm_sum += zqmt.getJsgm();
			}else if(o instanceof Zsjtjp){
				Zsjtjp zsjtjp = (Zsjtjp) o;
				this.jsgm_sum += zsjtjp.getJsgm();
			}else if(o instanceof Tsw){
				Tsw tsw = (Tsw) o;
				this.jsgm_sum += tsw.getJsgm().doubleValue();
			}else if(o instanceof Tzl){
				Tzl tzl = (Tzl) o;
				this.jsgm_sum += tzl.getJsgm().doubleValue();
			}else if(o instanceof Lzz){
				Lzz lzz = (Lzz) o;
				this.jsgm_sum += lzz.getJsgm();
			}else if(o instanceof Gld){
				Gld gld = (Gld) o;
				this.jsgm_sum += gld.getJsgm().doubleValue();
			}else if(o instanceof Juma){
				Juma juma = (Juma) o;
				this.jsgm_sum += juma.getJsgm();
			}else if(o instanceof Bjxl){
				Bjxl bjxl = (Bjxl) o;
				this.jsgm_sum += bjxl.getJsgm().doubleValue();
			}else if(o instanceof Jkzx || o instanceof Jkz || o instanceof Spqd 
				|| o instanceof Xkzd || o instanceof Jjmlfpt){
				this.jsgm_sum = dataList == null ? 0 : dataList.size();
			}else if(o instanceof Csxl){
				Csxl csxl = (Csxl) o;
				this.jsgm_sum += csxl.getJsgm().doubleValue();
			}else if(o instanceof Gdxt){
				Gdxt gdxt = (Gdxt) o;
				this.jsgm_sum += gdxt.getJsgm();
			}else if(o instanceof Bjzz){
				Bjzz bjzz = (Bjzz) o;
				this.jsgm_sum += bjzz.getJsgm();
			}else if(o instanceof Wrzsdzsb){
				Wrzsdzsb wrzsdzsb = (Wrzsdzsb) o;
				this.jsgm_sum += wrzsdzsb.getJsgm();
			}else if(o instanceof Zqf){
				Zqf zqf = (Zqf) o;
				this.jsgm_sum += zqf.getJsgm();
			}else if(o instanceof Lwt){
				Lwt lwt = (Lwt) o;
				this.jsgm_sum += lwt.getJsgm();
			}else if(o instanceof Bzp){
				Bzp bzp = (Bzp) o;
				this.jsgm_sum += bzp.getJsgm();
			}
			Jbxx jbxx = (Jbxx) o;
	        this.zytz_sum +=jbxx.getZytz();
	        this.dftz_sum +=jbxx.getDftz();
		}
	    this.jstz_sum = this.zytz_sum+this.dftz_sum;
	}

	public String getJsxz() {
		return jsxz;
	}

	public void setJsxz(String jsxz) {
		this.jsxz = jsxz;
	}

	public double getJsgm_sum() {
		return jsgm_sum;
	}

	public void setJsgm_sum(double jsgm_sum) {
		this.jsgm_sum = jsgm_sum;
	}

	public double getZytz_sum() {
		return zytz_sum;
	}

	public void setZytz_sum(double zytz_sum) {
		this.zytz_sum = zytz_sum;
	}

	public double getDftz_sum() {
		return dftz_sum;
	}

	public void setDftz_sum(double dftz_sum) {
		this.dftz_sum = dftz_sum;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<Object> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}

	public double getJstz_sum() {
		return jstz_sum;
	}

	public void setJstz_sum(double jstz_sum) {
		this.jstz_sum = jstz_sum;
	}
	
}
