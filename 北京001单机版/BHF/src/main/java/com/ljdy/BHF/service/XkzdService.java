package com.ljdy.BHF.service;

import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.AddXkzd;
import com.ljdy.BHF.model.Xkzd;

/**
 * @ClassName XkzdService 
 * @Description (指挥监控设施----显控终端service) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月26日 上午11:08:32 
 * @修改历史  
 *     1. [2017年9月26日]创建文件 by 顾冲
 */
public interface XkzdService {
    
	//poi导出列(备选库)
	public static final String[] columns = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq", "jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd", "jkz_id", "jkz_name","fzdd","csfs","fj","bz"};
	
	//poi导出数据excel文件表头
  	public static final String[] header = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
  		"建设区域（所在城市）","建设区域（所在区）", "经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度", "所在监控站id",
  		"所在监控站名称","放置地点","传输方式","附件","备注"};
  //poi导出列(备选库)实施监督模块
  	public static final String[] columnsJiandu = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq", "jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd", "jkz_id", "jkz_name","fzdd","csfs","fj","bz","jszt","cjdw","jldw","ztbsj","kgsj","cysj","jgsj","sjsj", "syzt"};
  	
  	
   //poi导出数据excel文件表头(实施监督模块)
  	public static final String[] headerJianDu = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
  		"建设区域（所在城市）","建设区域（所在区）", "经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度","所在监控站id",
  		"所在监控站名称","放置地点","传输方式","附件","备注","建设状态","承建单位","监理单位","招投标时间","开工时间","初验时间","竣工时间","审计时间", "使用状态"};
	/**
     * @Title getJsgm 
     * @Description (获取建设规模) 
     * @param paramMap
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年9月26日
     * @修改历史  
     *     1. [2017年9月26日]创建文件 by 顾冲
     */
    public List<Object> getJsgm(Map<String, Object> paramMap);
    
    /**
     * @Title getDxtz 
     * @Description (获取投资) 
     * @param paramMap
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年9月28日
     * @修改历史  
     *     1. [2017年9月28日]创建文件 by 顾冲
     */
    public List<Object> getTz(Map<String, Object> paramMap);
    
    /**
     * 新增显控终端
     * @param xkzd
     * 修改历史：
	 *		[2017年9月28日]创建文件	by 徐成
     */
    public void addXkzd(Xkzd xkzd);
   
    /**
    * 新增显空终端数据查询
    * @param condition
    * @param page
    * @param rows
    * @return List<Object>
    * @date [2017-09-29] 创建文件 by 郎川
    */
    public List<Object> getDataByCondition(Map<String,Object>condition,int page,int rows);
    
    /**
     * 根据主键id 查询详情数据
     * @param id
     * @return Object
     * @date [2017-10-09] 创建文件 by 郎川
     */
    public Object getDataById(String id);
    
    /**
     * 更新显空终端数据
     * @param xkzd
     * @return void 
     * @date [2017-10-11] 创建文件 by 郎川
     */
    public void updateXkzd(Xkzd xkzd);
 
    /**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition);

	
	/**
 	 * 显控终端根据建设性质统计个数
 	 * @param condition
 	 * @return int
 	 * @date [2017-10-23] 创建文件 by 郎川
 	 */
 	public List<Object> getCountByJsxz(Map<String, Object>condition);
 	
 	/**
 	 * 获取视频前端 中央投资求和
 	 * @return List<Object>
 	 * @date [2017-10-23] 创建文件 by 郎川
 	 */
 	public List<Object> getZytzSum();
 	
 	/**
 	 * 获取视频前端 地方投资求和
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
     * 实施监督管理页面数据查询
     * @param condition
     * @return List<Object>
     * @date [2017-11-8] 创建文件 by 郎川
     */
    public List<Object> getManageData(Map<String, Object>condition);
    
    /**
     * 实施监督管理建设投资合计
     * @param condition
     * @return List<Object>
     * @date [2017-11-8] 创建文件  by 郎川
     */
    public List<Object> getManageTz(Map<String, Object>condition);
    
    /**
     * @Title countByCondition 
     * @Description (根据jkz_id、sblx等条件统计条数) 
     * @param condition
     * @return
     * @Return Long 返回类型 
     * @Throws 
     * @Date  2017年11月20日
     * @修改历史  
     *     1. [2017年11月20日]创建文件 by 顾冲
     */
    public Long countByCondition(Map<String, Object> condition);
    
    /**
     * @Title getJkz_idByPrimaryKeys 
     * @Description (根据主键字符串查询) 
     * @param idsStr
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年11月21日
     * @修改历史  
     *     1. [2017年11月21日]创建文件 by 顾冲
     */
    public List<Object> getJkz_idsByPrimaryKeys(String idsStr);
    
    /**
     * 使用维护模块首页数据查询
     * @param condition
     * @return List<Object>
     * @date [2017-11-22] 创建文件 by 创建文件
     */
    public List<Object> getMaintenanceData(Map<String, Object>condition);
    
    /**
     * 使用维护模块首页数据建设投资合计
     * @param condition
     * @return List<Object>
     * @date [2017-11-23] 创建文件 by 郎川
     */
    public List<Object> getMaintenanceTz(Map<String, Object>condition);
    
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
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    public List<Object> getDestroyData(Map<String, Object> condition);

	public void addXkzd(AddXkzd addXkzd);
	
	/**
	 * @Title getXkzdByCondition 
	 * @Description (根据jkz_id获取显控终端数据) 
	 * @param condition
	 * @return
	 * @Return List<Object> 返回类型 
	 * @Throws 
	 * @Date  2018年1月6日
	 * @修改历史  
	 *     1. [2018年1月6日]创建文件 by 顾冲
	 */
	public List<Object> getXkzdByCondition(Map<String, Object> condition);

}
