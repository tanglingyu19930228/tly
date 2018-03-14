package com.ljdy.BHF.service;

import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.AddBzp;
import com.ljdy.BHF.model.Bzp;
/**
 * 标志牌(BzpService)
 * @author 郎川
 * @date 2017年9月26日
 */
public interface BzpService {

	public List<Object> getJsgm(Map<String,Object> param_map);
	public List<Object> getTz(Map<String,Object>param_map);
	
	//poi导出列
	public static final String[] columns = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq", "jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","jsdd","jsgm","bzplx","fj","bz"};
	
	//poi导出数据excel文件表头
	public static final String[] header = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
		"建设区域（所在城市）","建设区域（所在区）", "经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
		"建设地点","建设规模（座）","标志牌类型","附件","备注"};
	//poi导出列(备选库)实施监督模块
  	public static final String[] columnsJiandu = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq", "jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","jsdd","jsgm","bzplx","fj","bz","jszt","cjdw","jldw","ztbsj","kgsj","cysj","jgsj","sjsj", "syzt"};
  	
  	
   //poi导出数据excel文件表头(实施监督模块)
  	public static final String[] headerJianDu = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
		"建设区域（所在城市）","建设区域（所在区）", "经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
		"建设地点","建设规模（座）","标志牌类型","附件","备注","建设状态","承建单位","监理单位","招投标时间","开工时间","初验时间","竣工时间","审计时间", "使用状态"};
	/**
	 * 新增标志牌
	 * @param bzp
	 * 修改历史：
     * 		[2017年9月28日]创建文件 by 徐成
	 */
	public void addBzp(Bzp bzp);
	
	/**
	 * 新增标志牌数据查询
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
	 * 更新标志牌数据
	 * @param bzp
	 * @return void 
	 * @date [2017-10-11] 创建文件 by 郎川
	 */
	public void updateBzp(Bzp bzp);
	
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
     * 标志牌根据建设性质求和
     * @param condition
     * @return List<Object>
     * @date [2017-10-23] 创建文件 by 郎川
     */
    public List<Object>getSumByJsxz(Map<String, Object>condition);
    
    /**
	 * 标志牌求和中央投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 * 
	 */
	public List<Object> getZytzSum(); 
	
	/**
	 * 标志牌求和地方投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	public List<Object> getDftzSum(); 
	
	/**
	 * 实施监督管理页面
	 * @param condition
	 * @return List<Object>
	 * @date[2017-11-8] 创建文件 by 郎川
	 */
	public List<Object>getManageData(Map<String, Object>condition);
	
	/**
	 * 实施监督管理页面建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件  by 郎川
	 */
	public List<Object> getManageTz(Map<String, Object>condition);
	
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
	public List<Object>getMaintenanceTz(Map<String, Object>condition);

	public void addBzp(AddBzp addBzp);

	
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
