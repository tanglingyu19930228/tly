package com.ljdy.BHF.service;

import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.AddJjmlfpt;
import com.ljdy.BHF.model.Jjmlfpt;

/**
 *  军警民联防平台(JjmlfptService)
 * @author 郎川
 * @date 2017年9月26日
 */
public interface JjmlfptService {
	
	public List<Object> getJsgm(Map<String,Object> param_map);
	public List<Object> getTz(Map<String,Object>param_map);
	
	//poi导出列(备选库)
	public static final String[] columns = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq", "jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","xsltqk","xsltwlxz","xscsxl","hxltqk","hxltwlxz","hxcsxl","jb","fzdd","fj","bz"};
	
	//poi导出数据excel文件表头(备选库)
	public static final String[] header = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
		"建设区域（所在城市）","建设区域（所在区）", "经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
		"向上联通情况","向上联通网络性质","向上传输线路","横向联通情况","横向联通网络性质","横向传输线路","级别","放置地点","附件","备注"};
	
	//poi导出列(备选库)实施监督模块
  	public static final String[] columnsJiandu = {"id", "xmlb","xmzl","xmmc","xmbh","bjfx","sslx","szsf","szcs","szq", "jd", "wd", "dxlb","jsxz",
		"sydw","sbdw","zytz","dftz","tznd","xsltqk","xsltwlxz","xscsxl","hxltqk","hxltwlxz","hxcsxl","jb","fzdd","fj","bz","jszt","cjdw","jldw","ztbsj","kgsj","cysj","jgsj","sjsj", "syzt"};
  	
  	
   //poi导出数据excel文件表头(实施监督模块)
  	public static final String[] headerJianDu = {"项目id", "项目类别","项目子类","项目名称","项目编号","边界方向","设施类型","建设区域（所在省份）",
		"建设区域（所在城市）","建设区域（所在区）", "经度", "纬度", "地形类别","建设性质","使用单位","申报单位","中央投资","地方投资","投资年度",
		"向上联通情况","向上联通网络性质","向上传输线路","横向联通情况","横向联通网络性质","横向传输线路","级别","放置地点","附件","备注","建设状态","承建单位","监理单位","招投标时间","开工时间","初验时间","竣工时间","审计时间", "使用状态"};
	/**
	 * 新增军警民联防平台
	 * @param jjmlfpt
	 * 修改历史：
	 *		[2017年9月28日]创建文件	by 徐成
	 */
	public void addJjmlfpt(Jjmlfpt jjmlfpt);
	
	/**
	 * 新增军警民联防平台数据查询
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
	 * @date [2017-10-09] 创建文件 by 郎川
	 */
	public Object getDataById(String id);
	
	/**
	 * 更新军警民联防平台数据
	 * @param jjmlfpt
	 * @return void 
	 * @date [2017-10-11] 创建文件 by 郎川
	 */
	public void updateJjmlfpt(Jjmlfpt jjmlfpt);
	
	/**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition);
	
	/**
	 * 获取省份报表的数据
	 * @param  condition
	 * @return List<Object>
	 * @date[2017-10-18] 创建文件 by 郎川
	 */
	
	public List<Object> getData(Map<String, Object>condition);
	
	/**
	 * 根据建设性质统计个数
	 * @param condition
	 * @return int
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	public List<Object> getCountByJsxz(Map<String, Object>condition);
	
	/**
	 * 获取军警民联防平台  中央投资求和
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	public List<Object> getZytzSum();
	
	/**
	 * 获取军警民联防平台  地方投资求和
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	public List<Object> getDftzSum();
	
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
	public List<Object> getMaintenanceTz(Map<String, Object>condition);

	public void addJjmlfpt(AddJjmlfpt addJjmlfpt);

	
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
