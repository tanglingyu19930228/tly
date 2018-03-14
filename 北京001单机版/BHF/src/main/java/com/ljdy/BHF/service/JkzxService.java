package com.ljdy.BHF.service;

import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.AddJkzx;
import com.ljdy.BHF.model.Jkzx;

/**
 * @author 郎川
 * @date 2017年9月26日
 */
public interface JkzxService {
	public List<Object> getJsgm(Map<String,Object> map_param);
	public List<Object> getTz(Map<String,Object>param_map);

	//poi导出列(备选库)
	public static final String[] columns = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq", "jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","xsltqk","xsltwlxz","xscsxl","jb","fj","bz"};
	
	//poi导出数据excel文件表头
	public static final String[] header = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
		"建设区域（所在城市）","建设区域（所在区）", "经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
		"向上联通情况","向上联通网络性质","向上传输线路","级别","附件","备注"};
	//poi导出列(备选库)实施监督模块
  	public static final String[] columnsJiandu = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq", "jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","xsltqk","xsltwlxz","xscsxl","jb","fj","bz","jszt","cjdw","jldw","ztbsj","kgsj","cysj","jgsj","sjsj", "syzt"};
  	
  	
   //poi导出数据excel文件表头(实施监督模块)
  	public static final String[] headerJianDu = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
		"建设区域（所在城市）","建设区域（所在区）", "经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
		"向上联通情况","向上联通网络性质","向上传输线路","级别","附件","备注","建设状态","承建单位","监理单位","招投标时间","开工时间","初验时间","竣工时间","审计时间", "使用状态"};
	/**
	 * 新增监控中心
	 * @param jkzx
	 * 修改历史：
	 *		[2017年9月28日]创建文件	by 徐成
	 */
	public void addJkzx(Jkzx jkzx);
	
	/**
	 * 多条件查询 带分页
	 * @param condition
	 * @param page
	 * @param rows
	 * @return List<Object>
	 * 修改历史 
	 * 		[2017-09-28] by 郎川 
	 */
	public List<Object> getDataByCondition(Map<String,Object>condition,int page,int rows);
	
	/**
	 * 根据主键id获取数据
	 * @param id
	 * @return Object
	 * @date [2017-10-09] 创建文件  by 郎川
	 */
	public Object getDataById(String id);
	
	/**
	 * 更新监控中心数据
	 * @param id
	 * @return void
	 * @date [2017-10-11] 创建文件 by 郎川  
	 */
	public void updateJkzx(Jkzx jkzx );
	
	/**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition);
	/**
	 * @Title getData 
	 * @Description (省级报表数据) 
	 * @param condition
	 * @return
	 * @Return List<Object> 返回类型 
	 * @Throws 
	 * @Date  2017年10月18日
	 * @修改历史  
	 *     1. [2017年10月18日]创建文件 by 顾冲
	 */
	public List<Object> getData(Map<String, Object> condition);
	
	/**
	 * 根据建设性质统计个数
	 * @param condition
	 * @return int
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	public List<Object> getCountByJsxz(Map<String, Object>condition);
	
	/**
	 * 获取监控中心 中央投资求和
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	public List<Object> getZytzSum();
	
	/**
	 * 获取监控中心 地方投资求和
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	public List<Object> getDftzSum();
	
	/**
	 * @Title getTzGroupByArea 
	 * @Description (根据区域分组查询投资) 
	 * @param condition
	 * @return
	 * @Return List<Object> 返回类型 
	 * @Throws 
	 * @Date  2017年11月8日
	 * @修改历史  
	 *     1. [2017年11月8日]创建文件 by 顾冲
	 */
	public List<Object> getTzGroupByArea(Map<String, Object>condition);
	
	/**
	 * 实施监督管理页面数据查询
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 郎川
	 */
	public List<Object> getManageData(Map<String, Object>condition);
	
	/**
	 * 实施监督管理页面建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 郎川
	 */
	public List<Object> getManageTz(Map<String, Object>condition);
	
	/**
     * @Title getDxtzGroupByXmlb 
     * @Description (根据项目类别分组获取监控中心单项投资) 
     * @param condition
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年11月9日
     * @修改历史  
     *     1. [2017年11月9日]创建文件 by 顾冲
     */
    public List<Object> getDxtzGroupByXmlb(Map<String, Object> condition);
    
    /**
     * @Title getDxtzGroupBySslx 
     * @Description (根据设施类型分组获取监控中心单项投资) 
     * @param condition
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年11月9日
     * @修改历史  
     *     1. [2017年11月9日]创建文件 by 顾冲
     */
    public List<Object> getDxtzGroupBySslx(Map<String, Object> condition);
    
    /**
     * 使用维护模块首页数据查询
     * @param condition
     * @return List<Object>
     * @date [2017-11-22] 创建文件  by 郎川
     */
    public List<Object> getMaintenanceData(Map<String, Object>condition);
    
    /**
     * 使用维护模块首页数据建设投资合计
     * @param condition
     * @return List<Object>
     * @date [2017-11-23] 创建文件  by 郎川
     */
    public List<Object> getMaintenanceTz(Map<String, Object>condition);

	public void addJkzx(AddJkzx addJkzx);

    
    /**
     * @Title getDestroyData
     * @Description (获取损坏项目信息)
     * @param condition
     * @return
     * @Return List<Object> 返回类型
     * @Throws
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    public List<Object> getDestroyData(Map<String, Object> condition);

}
