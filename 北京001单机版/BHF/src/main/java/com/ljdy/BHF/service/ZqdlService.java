package com.ljdy.BHF.service;

import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.AddZqdl;
import com.ljdy.BHF.model.Zqdl;

/**
 * @ClassName ZqdlService 
 * @Description (执勤道路service) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月25日 上午11:41:43 
 * @修改历史  
 *     1. [2017年9月25日]创建文件 by 顾冲
 */
/**
 * @author Administrator
 *@date 2017年11月22日
ZqdlService*
	
 */
public interface ZqdlService {
	
	//poi导出列(备选库)
	public static final String[] columns = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq","jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","jsdd","jsgm","dllb","dllx","dldj","ljlx","lmlx","fj","bz"};
	
	//poi导出数据excel文件表头
  	public static final String[] header = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
  		"建设区域（所在城市）","建设区域（所在区）","经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
  		"建设地点","建设规模（公里）","道路类别","道路类型","道路等级","路基类型","路面类型","附件","备注"};
  	
  //poi导出列(备选库)实施监督模块
  	public static final String[] columnsJiandu = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq","jd","wd","dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","jsdd","jsgm","dllb","dllx","dldj","ljlx","lmlx","fj","bz","jszt","cjdw","jldw","ztbsj","kgsj","cysj","jgsj","sjsj", "syzt"};
  	
  	
   //poi导出数据excel文件表头(实施监督模块)
  	public static final String[] headerJianDu = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
  		"建设区域（所在城市）","建设区域（所在区）","经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
  		"建设地点","建设规模（公里）","道路类别","道路类型","道路等级","路基类型","路面类型","附件","备注","建设状态","承建单位","监理单位","招投标时间","开工时间","初验时间","竣工时间","审计时间", "使用状态"};
	
    /**
     * @Title getJsgm 
     * @Description (获取建设规模) 
     * @param param
     * @return
     * @Return double 返回类型 
     * @Throws 
     * @Date  2017年9月25日
     * @修改历史  
     *     1. [2017年9月25日]创建文件 by 顾冲
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
     * 新增执勤道路路
     * @param testZqdl
     * 修改历史：
     * 		[2017年9月28日]创建文件 by 徐成
     */
    public void addZqdl(AddZqdl addZqdl); 
    public void addZqdl(Zqdl zqdl); 
    
    /**
     * 获取所有数据
     * @return
     */
    public List<Object> getAllData();
    
    /**
     * 多条件查询数据，带分页
     * @param condition		查询条件集合
     * @param page			当前页码
     * @param rows			每页条数
     * @return
     */
    public List<Object> getDataByCondition(Map<String,Object> condition,int page,int rows);
    
    /**
     * 根据主键获取数据
     * @param id
     * @return
     */
    public Object getDataById(String id);
    
    /**
     * 更新执勤道路数据
     * @param zqdl
     * 修改历史：
     * 		[2017年10月10日]创建文件 by 徐成
     */
	public void updateData(Zqdl zqdl);
	
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
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> getData(Map<String, Object>condition);
	
	/**
     * 执勤道路根据建设性质求和
     * @param condition
     * @return List<Object>
     * @date [2017-10-24] 创建文件 by 郎川
     */
    public List<Object>getSumByJsxz(Map<String, Object>condition);
    
    /**
	 * 执勤道路 求和中央投资
	 * @return List<Object>
	 * @date [2017-10-24] 创建文件 by 郎川
	 * 
	 */
	public List<Object> getZytzSum(); 
	
	/**
	 * 传输线路执勤道路 求和地方投资
	 * @return List<Object>
	 * @date [2017-10-24] 创建文件 by 郎川
	 */
	public List<Object> getDftzSum(); 
	
	/**
	 * 监督实施管理界面首页数据查询
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 郎川
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
	 * 使用维护首页数据查询
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-22] 创建文件 by 郎川
	 */
	public List<Object> getMaintenanceData(Map<String, Object>condition);
	
	/**
	 * 使用维护模块首页建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-23] 创建文件  by 郎川
	 */
	public List<Object> getMaintenancetz(Map<String, Object>condition);
	
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
    
    /**
     * 根据sql语句更新
     */
    public void updateBySql(String sql,Object[] parqam);
	public void update(AddZqdl zqdl);
	
	/**
	 * 统计使用维护模块各项总的投资
	 * @param condition
	 * @return List<Object>
	 * @date[2017-12-18] 创建文件 by 郎川
	 */
	public List<Object> getAllTz(Map<String, Object>condition);
	
	/**
	 * 实施监督模块各项总的投资
	 * @param condition
	 * @return List<Object>
	 * @date[2017-12-18] 创建文件 by 郎川
	 */
	public List<Object> getManageAllTz(Map<String, Object>condition);
}
