package com.ljdy.BHF.service;

import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.AddLzz;
import com.ljdy.BHF.model.Lzz;

/**
 * 栏柱庄(LzzService)
 * @author 郎川
 * @date 2017年9月26日
 */
public interface LzzService {
	public List<Object> getJsgm(Map<String,Object> map_param);
	public List<Object> getTz(Map<String,Object>map_param);

	//poi导出列(备选库)
	public static final String[] columns = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq","jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","jsdd","jsgm","lzzlx","fj","bz"};
	
	//poi导出数据excel文件表头
  	public static final String[] header = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
  		"建设区域（所在城市）","建设区域（所在区）","经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
  		"建设地点","建设规模（个）","拦阻桩类型","附件","备注"};
	
  //poi导出列(备选库)实施监督模块
  	public static final String[] columnsJiandu = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq","jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","jsdd","jsgm","lzzlx","fj","bz","jszt","cjdw","jldw","ztbsj","kgsj","cysj","jgsj","sjsj", "syzt"};
  	
  	
   //poi导出数据excel文件表头(实施监督模块)
  	public static final String[] headerJianDu = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
  		"建设区域（所在城市）","建设区域（所在区）","经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
  		"建设地点","建设规模（个）","拦阻桩类型","附件","备注","建设状态","承建单位","监理单位","招投标时间","开工时间","初验时间","竣工时间","审计时间", "使用状态"};
	/**
	 * 新增拦阻桩
	 * @param lzz
	 * 修改历史：
	 *		[2017年9月28日]创建文件	by 徐成
	 */
	public void addLzz(Lzz lzz);
	
	 /**
     * @Title getDataByCondition 
     * @Description (多条件查询数据，带分页) 
     * @param condition     查询条件集合
     * @param page          当前页码
     * @param rows          每页条数
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年9月29日
     * @修改历史  
     *     1. [2017年9月29日]创建文件 by 顾冲
     */
    public List<Object> getDataByCondition(Map<String,Object> condition,int page,int rows);
    
    /**
     * @Title getDataById 
     * @Description (根据主键获取数据) 
     * @param id
     * @return
     * @Return Object 返回类型 
     * @Throws 
     * @Date  2017年10月9日
     * @修改历史  
     *     1. [2017年10月9日]创建文件 by 顾冲
     */
    public Object getDataById(String id);
    
    /**
     * @Title updateData 
     * @Description (更新拦阻桩数据) 
     * @param lzz
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年10月11日
     * @修改历史  
     *     1. [2017年10月11日]创建文件 by 顾冲
     */
    public void updateData(Lzz lzz);

    /**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition);
	
	/**
	 * 省级报表数据
	 * @param condition
	 * @return
	 */
	public List<Object> getData(Map<String, Object> condition);
	
	/**
     * 拦阻桩根据建设性质求和
     * @param condition
     * @return List<Object>
     * @date [2017-10-24] 创建文件 by 郎川
     */
    public List<Object>getSumByJsxz(Map<String, Object> condition);
    
    /**
	 * 拦阻桩求和中央投资
	 * @return List<Object>
	 * @date [2017-10-24] 创建文件 by 郎川
	 * 
	 */
	public List<Object> getZytzSum(); 
	
	/**
	 * 拦阻桩求和地方投资
	 * @return List<Object>
	 * @date [2017-10-24] 创建文件 by 郎川
	 */
	public List<Object> getDftzSum();
	
	/**
	 * 监督实施管理界面首页数据查询
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 汤凌宇
	 */
	public List<Object> getManageData(Map<String, Object> condition);
	
	/**
	 * 监督实施管理页面建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 郎川
	 */
	public List<Object> getManageTz(Map<String, Object> condition);
	
	
	
	/**
	 * @Title getTzGroupByArea 
	 * @Description (根据地区分组获取投资) 
	 * @return
	 * @Return List<Object> 返回类型 
	 * @Throws 
	 * @Date  2017年11月8日
	 * @修改历史  
	 *     1. [2017年11月8日]创建文件 by 顾冲
	 */
	public List<Object> getTzGroupByArea(Map<String, Object> condition);
	
	/**
	 * @Title getDxtzGroupByXmlb 
	 * @Description (根据项目类别分组获取拦阻桩单项投资) 
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
	 * @Description (根据设施类型分组查询拦阻桩单项投资) 
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
	 * @date [2017-11-23] 创建文件 by 郎川
	 */
	public List<Object> getMaintenanceTz(Map<String, Object>condition);

	public void addlzz(AddLzz addLzz);

	
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
