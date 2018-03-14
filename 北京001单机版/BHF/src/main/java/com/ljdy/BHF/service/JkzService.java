package com.ljdy.BHF.service;

import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.AddJkz;
import com.ljdy.BHF.model.Jkz;

/**
 * 监控站(JkzService)
 * @author 郎川
 * @date 2017年9月26日
 */
public interface JkzService {
	public List<Object> getJsgm(Map<String,Object> param_map);

	//poi导出列(备选库)
	public static final String[] columns = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq", "jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","xsltqk","xsltwlxz","xscsxl","xkzdgs","ydqdgs","gdqdgs","fj","bz"};
	
	//poi导出数据excel文件表头
	public static final String[] header = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
		"建设区域（所在城市）","建设区域（所在区）", "经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
		"向上联通情况","向上联通网络性质","向上传输线路","显控终端个数","移动前端个数","固定前端个数","附件","备注"};
	//poi导出列(备选库)实施监督模块
  	public static final String[] columnsJiandu = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq", "jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","xsltqk","xsltwlxz","xscsxl","xkzdgs","ydqdgs","gdqdgs","fj","bz","jszt","cjdw","jldw","ztbsj","kgsj","cysj","jgsj","sjsj", "syzt"};
  	
  	
   //poi导出数据excel文件表头(实施监督模块)
  	public static final String[] headerJianDu = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
  		"建设区域（所在城市）","建设区域（所在区）", "经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
  		"向上联通情况","向上联通网络性质","向上传输线路","显控终端个数","移动前端个数","固定前端个数","附件","备注","建设状态","承建单位","监理单位","招投标时间","开工时间","初验时间","竣工时间","审计时间", "使用状态"};
	/**
	 * 新增监控站
	 * @param jkz
	 * 修改历史：
	 *		[2017年9月28日]创建文件	by 徐成
	 */
	public void addJkz(Jkz jkz);
	/**
	 * 监控站页面查询数据
	 * @param condition
	 * @param page
	 * @param rows
	 * @return List<Object>
	 * @date [2017-09-28] by 郎川
	 */
	public List<Object> getDataByCondition(Map<String,Object>condition ,int page,int rows);
	
	/**
	 * 视频前端需要查询监控站的信息  视频前端关联监控站
	 * 
	 * @return List<Object>
	 * @date [2017-09-30] 创建文件 by 郎川
	 */
	public List<Object> getJkzName(String szsf,String szcs,String szq, String partTag);
	
	/**
	 * 根据主键id查询数据详情
	 * @param id
	 * @return Object
	 * @date [2017-10-09] 创建文件 by 郎川
	 */
	public Object getDataById(String id);
	
	/**
	 * 更新监控站数据
	 * @param jkz
	 * @return void 
	 * @date [2017-10-11] 创建文件 by 郎川 
	 */
	public void updateJkz(Jkz jkz);
	
	/**
	 * 根据监控站的id查询监控站的名称
	 * @param id
	 * @return Object
	 * @date [2017-10-13] 创建文件 by 郎川
	 */
	public Object getJkzNameByJkzId(String id);
	
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
	 * 获取监控站 中央投资求和
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	public List<Object> getZytzSum();
	
	/**
	 * 获取监控站 地方投资求和
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
	 * 监督实施管理页面数据查询
	 * @return List<Object>
	 * @param condition
	 * @date [2017-11-8] 创建文件 by 郎川
	 */
	public List<Object> getManageData(Map<String, Object> condition);
	
	/**
	 * 实施监督管理页面建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 郎川
	 */
	public List<Object> getManageTz(Map<String, Object>condition);
	
	/**
     * @Title getDxtzGroupByXmlb 
     * @Description (根据项目类别分组获取监控站单项投资) 
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
     * @Description (根据设施类型分组查询监控站单项投资) 
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
     * 
     * @Title getTz 
     * @Description (获取投资) 
     * @param param
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年11月20日
     * @修改历史  
     *     1. [2017年11月20日]创建文件 by 顾冲
     */
    public List<Object> getTz(Map<String, Object> param);
    
    /**
     * @Title updateZlgs 
     * @Description (修改监控站中项目子类个数) 
     * @param zdm 字段名
     * @param jkz_id 监控站id
     * @param zlgs 子类个数
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年11月20日
     * @修改历史  
     *     1. [2017年11月20日]创建文件 by 顾冲
     */
    public void updateZlgs(String zdm, String jkz_id, Long zlgs);
    
    /**
     * @Title updateZlgs 
     * @Description (根据jkz_id、gdqdgs、ydqdgs修改监控站中项目移动前端个数、固定前端个数) 
     * @param jkz_id
     * @param xkzdgs
     * @param ydzdgs
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年11月20日
     * @修改历史  
     *     1. [2017年11月20日]创建文件 by 顾冲
     */
    public void updateZlgs(String jkz_id, Long gdqdgs, Long ydqdgs);
    
    /**
     * 使用维护模块首页数据查询
     * @param condition
     * @return List<Object>
     * @date [2017-11-22] 创建文件 by 郎川
     */
    public List<Object> getMaintenanceData(Map<String, Object>condition);
    
    /**
     * 使用维护模块首页数据建设投资合计
     * @param condition
     * @return List<Object>
     * @date [2017-11-23] 创建文件  by 郎川
     */
    public List<Object> getMaintenanceTz(Map<String,Object>condition);

	public void addJkz(AddJkz addJkz);

    
    /**
     * @Title getDataByIds 
     * @Description (根据id查询) 
     * @param idList
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月4日
     * @修改历史  
     *     1. [2017年12月4日]创建文件 by 顾冲
     */
    public List<Object> getDataByIds(List<Object> idList);
    
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
