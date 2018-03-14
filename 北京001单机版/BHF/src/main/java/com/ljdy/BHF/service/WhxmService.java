package com.ljdy.BHF.service;

import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.Whxm;

/**
 * @ClassName WhxmService 
 * @Description (维护项目service) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年11月22日 上午9:08:55 
 * @修改历史  
 *     1. [2017年11月22日]创建文件 by 顾冲
 */
public interface WhxmService {
    
    /**
     * @Title getDataByCondition 
     * @Description (多条件查询数据，带分页) 
     * @param condition
     * @param page
     * @param rows
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年11月22日
     * @修改历史  
     *     1. [2017年11月22日]创建文件 by 顾冲
     */
    public List<Object> getDataByCondition(Map<String, Object> condition, int page, int rows);
    
    /**
     * @Title addWhxm 
     * @Description (添加维护项目) 
     * @param whxm
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年11月22日
     * @修改历史  
     *     1. [2017年11月22日]创建文件 by 顾冲
     */
    public void addWhxm(Whxm whxm);
    
    /**
     * @Title getProvinceByShortName 
     * @Description (根据省份简称查询全称) 
     * @param shortName
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2017年11月28日
     * @修改历史  
     *     1. [2017年11月28日]创建文件 by 顾冲
     */
    public String getProvinceByShortName(String shortName);
    
    //poi导出列
    public static final String[] columns = {"xmbh", "xmmc", "id" , "szsf", "wxdw", "wxxmmc", "wxxmbh", "wxgm", "wxzfy"};
    
    //poi导出数据excel文件表头
    public static final String[] header = {"项目编号", "项目名称", "维护项目id", "所在省份","维修单位", "维修项目名称", "维修项目编号", "维修规模", "维修总费用"};
    
    /**
     * @Title getDataBy 
     * @Description (获取维护项目信息) 
     * @param condition
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月11日
     * @修改历史  
     *     1. [2017年12月11日]创建文件 by 顾冲
     */
    public List<Object> getData(Map<String, Object> condition); 
    
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
     * @Title getDataById 
     * @Description (根据主键获取数据) 
     * @param id
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月22日
     * @修改历史  
     *     1. [2017年12月22日]创建文件 by 顾冲
     */
    public List<Object> getDataById(String id);
    
    /**
     * @Title update 
     * @Description (维护项目修改) 
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
    
    
}
