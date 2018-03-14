package com.ljdy.BHF.service;

import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.AddGdxt;
import com.ljdy.BHF.model.Gdxt;
/**
 * 供电系统(GdxtService)
 * @author 郎川
 * @date 2017年9月26日
 */
public interface GdxtService {
	
	public List<Object> getJsgm(Map<String,Object> param_map);
	public List<Object> getTz(Map<String,Object>param_map);

	//poi导出列
	public static final String[] columns = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq", "jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd", "jkz_id", "jkz_name","jsdd","jsgm","fj","bz"};
	
	//poi导出数据excel文件表头
	public static final String[] header = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
		"建设区域（所在城市）","建设区域（所在区）", "经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度", "所在监控站id",
		"所在监控站名称","建设地点","建设规模（套）","附件","备注"};
	
	//poi导出列(备选库)实施监督模块
  	public static final String[] columnsJiandu = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq", "jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd", "jkz_id", "jkz_name","jsdd","jsgm","fj","bz","jszt","cjdw","jldw","ztbsj","kgsj","cysj","jgsj","sjsj", "syzt"};
  	
  	
   //poi导出数据excel文件表头(实施监督模块)
  	public static final String[] headerJianDu = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
		"建设区域（所在城市）","建设区域（所在区）", "经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度", "所在监控站id",
		"所在监控站名称","建设地点","建设规模（套）","附件","备注","建设状态","承建单位","监理单位","招投标时间","开工时间","初验时间","竣工时间","审计时间", "使用状态"};
	/**
	 * 新增供电系统
	 * @param gdxt
	 * 修改历史：
	 *		[2017年9月28日]创建文件	by 徐成
	 */
	public void addGdxt(Gdxt gdxt);
	
	/**
	 * 新增供电系统数据查询
	 * @param condition
	 * @param page
	 * @param rows
	 * @return List<Object>
	 * @date [2017-09-29] 创建文件 by 郎川
	 */
	public List<Object> getDataByCondition(Map<String,Object>condition,int page,int rows);
	
	/**
	 * 根据主键id查询数据详情
	 * @param id 
	 * @return Object 
	 * @date [2017-10-09] 创建文件 By 郎川
	 */
	public Object getDataById(String id);
	
	/**
	 * 更新供电系统数据
	 * @param gdxt
	 * @return void 
	 * @date [2017-10-11] 创建文件 by 郎川
	 */
	public void updateGdxt(Gdxt gdxt);
	
	/**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition);

	
	/**
     * 供电系统根据建设性质求和
     * @param condition
     * @return List<Object>
     * @date [2017-10-23] 创建文件 by 郎川
     */
    public List<Object>getSumByJsxz(Map<String, Object>condition);
    
    /**
	 * 供电系统 求和中央投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 * 
	 */
	public List<Object> getZytzSum(); 
	
	/**
	 * 供电系统 求和地方投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	public List<Object> getDftzSum(); 

	
	/**
	 * @Title getData 
	 * @Description (省级报表数据) 
	 * @param condition
	 * @return
	 * @Return List<Object> 返回类型 
	 * @Throws 
	 * @Date  2017年10月23日
	 * @修改历史  
	 *     1. [2017年10月23日]创建文件 by 顾冲
	 */
    public List<Object> getData(Map<String, Object> condition);
    
    /**
     * 实施建设页面数据查询
     * @param condition
     * @return List<Object>
     * @date [2017-11-8] 创建文件 by 郎川
     */
    public List<Object> getManageData(Map<String, Object>condition);
    
    /**
     * 实施监督管理页面建设投资合计
     * @param condition
     * @return List<Object>
     * @date [2017-11-8] 创建文件  by 郎川
     */
    public List<Object> getManagetz(Map<String, Object>condition);
    
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
     * @date [2017-11-23] 创建文件 by 郎川
     */
    public List<Object> getMaintenanceTz(Map<String, Object>condition);

	public void addGdxt(AddGdxt addGdxt);

    
    /**
     * @Title getJkz_idByIds 
     * @Description (通过id获取jkz_id) 
     * @param idsArr
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月4日
     * @修改历史  
     *     1. [2017年12月4日]创建文件 by 顾冲
     */
    public List<Object> getJkz_idByIds(String [] idsArr);
    
    /**
     * @Title getDestroyData 
     * @Description (获取损坏项目信息) 
     * @param condition
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月5日
     * @修改历史  
     *     1. [2017年12月5日]创建文件 by 顾冲
     */
    public List<Object> getDestroyData(Map<String, Object> condition);


}
