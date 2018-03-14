package com.ljdy.BHF.service;

import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.Xmwhjl;

/**
 * @ClassName XmwhjlService 
 * @Description (项目维护记录service) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年11月15日 下午5:32:21 
 * @修改历史  
 *     1. [2017年11月15日]创建文件 by 顾冲
 */
public interface XmwhjlService {
    /**
     * @Title getDataByCondition 
     * @Description (多条件查询数据，带分页) 
     * @param condition
     * @param page
     * @param rows
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年11月15日
     * @修改历史  
     *     1. [2017年11月15日]创建文件 by 顾冲
     */
    public List<Object> getDataByCondition(Map<String,Object>condition,int page,int rows);
    
    /**
     * @Title getDataByCondition 
     * @Description (条件查询维护记录) 
     * @param condition
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年11月24日
     * @修改历史  
     *     1. [2017年11月24日]创建文件 by 顾冲
     */
    public List<Object> getDataByCondition(Map<String,Object>condition);
    
    /**
     * @Title addXhjl 
     * @Description (新增维护记录) 
     * @param xmwhjl
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年11月17日
     * @修改历史  
     *     1. [2017年11月17日]创建文件 by 顾冲
     */
    public void addXhjl(Xmwhjl xmwhjl);
    
    //poi导出列
  	public static final String[] columns = {"wxxmid", "wxxmbh", "wxxmmc", "wxgm", "wxfy", "wxdw", "gzsb", "gzsbpp", "gzsbxh", "gzbj", "shyy", "gzsj", 
  	    "wxsj", "whzt", "id", "whxmid", "whjlzt"};
  	
  	//poi导出数据excel文件表头
    public static final String[] header = {"维修项目id", "维修项目编号", "维修项目名称","维修规模","维修费用", "维修单位","故障设备","故障设备品牌",
      		"故障设备型号","故障部件","损坏原因","故障时间","维修时间", "维护状态", "唯一标识", "维护项目id", "维护记录状态"};
    
    /**
     * @Title getWhjlByXmids 
     * @Description (通过xmid查询维护信息) 
     * @param condition
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月8日
     * @修改历史  
     *     1. [2017年12月8日]创建文件 by 顾冲
     */
    public List<Object> getWhjl(Map<String, Object> condition);
    
    /**
     * @Title getDataByArray 
     * @Description (根据id集合获取数据) 
     * @param arr
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月11日
     * @修改历史  
     *     1. [2017年12月11日]创建文件 by 顾冲
     */
    public List<Object> getDataByArray(List<String> arr);
    
    /**
     * @Title update 
     * @Description (修改项目维护记录) 
     * @param sql
     * @param param
     * @return
     * @Return int 返回类型 
     * @Throws 
     * @Date  2017年12月22日
     * @修改历史  
     *     1. [2017年12月22日]创建文件 by 顾冲
     */
    public int update(String sql, Object [] param);
    
    /**
     * @Title getWxxmbhByWhxmid 
     * @Description (这里用一句话描述这个方法的作用) 
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月25日
     * @修改历史  
     *     1. [2017年12月25日]创建文件 by 顾冲
     */
    public List<Object> getWxxmidByWhxmid(String whxmid);
    
    /**
     * @Title getUnCompletedCountByWxxmid 
     * @Description (根据维修项目id统计维护未完成的维护记录) 
     * @param wxxmid
     * @return
     * @Return long 返回类型 
     * @Throws 
     * @Date  2017年12月25日
     * @修改历史  
     *     1. [2017年12月25日]创建文件 by 顾冲
     */
    public long getUnCompletedCountByWxxmid(String wxxmid);
    
    /**
     * @Title getWxfyGroupByArea 
     * @Description (获取个地区的维护费用) 
     * @param condition
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月27日
     * @修改历史  
     *     1. [2017年12月27日]创建文件 by 顾冲
     */
    public List<Object> getWxfyGroupByArea(Map<String, Object> condition);
    
    /**
     * @Title getWxfyGroupByXmlb 
     * @Description (根据项目类别分组查询维修费用) 
     * @param param
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月27日
     * @修改历史  
     *     1. [2017年12月27日]创建文件 by 顾冲
     */
    public List<Object> getWxfyGroupByXmlb(Map<String, Object> condition);
}
