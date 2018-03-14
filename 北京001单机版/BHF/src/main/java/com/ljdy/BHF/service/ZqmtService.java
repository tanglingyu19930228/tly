package com.ljdy.BHF.service;

import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.AddZqmt;
import com.ljdy.BHF.model.Zqmt;

/**
 * @ClassName ZqmtService 
 * @Description (交通保障设施----执勤码头service) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月26日 上午11:44:39 
 * @修改历史  
 *     1. [2017年9月26日]创建文件 by 顾冲
 */
public interface ZqmtService {
    
	//poi导出列(备选库)
	public static final String[] columns = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq","jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","jsdd","jsgm","fj","bz"};
	
	//poi导出数据excel文件表头
  	public static final String[] header = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
  		"建设区域（所在城市）","建设区域（所在区）","经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
  		"建设地点","建设规模（座）","附件","备注"};
  //poi导出列(备选库)实施监督模块
  	public static final String[] columnsJiandu = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq","jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","jsdd","jsgm","fj","bz","jszt","cjdw","jldw","ztbsj","kgsj","cysj","jgsj","sjsj", "syzt"};
  	
  	
   //poi导出数据excel文件表头(实施监督模块)
  	public static final String[] headerJianDu = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
  		"建设区域（所在城市）","建设区域（所在区）","经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
  		"建设地点","建设规模（座）","附件","备注","建设状态","承建单位","监理单位","招投标时间","开工时间","初验时间","竣工时间","审计时间", "使用状态"};
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
     * 新增执勤码头
     * @param zqmt
     * 修改历史：
	 *		[2017年9月28日]创建文件	by 徐成
     */
    public void addZqmt(Zqmt zqmt);
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
     * @Description (更新执勤码头数据) 
     * @param zqmt
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年10月11日
     * @修改历史  
     *     1. [2017年10月11日]创建文件 by 顾冲
     */
    public void updateData(Zqmt zqmt);
    
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
	public List<Object> getData(Map<String, Object>condition);
	
	/**
     * 执勤码头根据建设性质求和
     * @param condition
     * @return List<Object>
     * @date [2017-10-24] 创建文件 by 郎川
     */
    public List<Object>getSumByJsxz(Map<String, Object>condition);
    
    /**
	 * 执勤码头求和中央投资
	 * @return List<Object>
	 * @date [2017-10-24] 创建文件 by 郎川
	 * 
	 */
	public List<Object> getZytzSum(); 
	
	/**
	 * 执勤码头 求和地方投资
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
	public List<Object> getManageData(Map<String, Object>condition);
	
	/**
	 * 监督实施管理页面建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 郎川
	 */
	public List<Object> getManageTz(Map<String, Object>condition);
	
	/**
	 * 使用维护模块首页数据查询
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-22] 创建文件 by 郎川
	 */
	public List<Object> getMaintenanceData(Map<String, Object>condition);
	
	/**
	 *使用维护模块首页数据建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-23] 创建文件 by 郎川
	 */
	public List<Object> getMaintenanceTz(Map<String, Object>condition);

	public void addZqmt(AddZqmt addZqmt);
	
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
