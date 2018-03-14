package com.ljdy.BHF.service;

import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.User;
/**
 * @ClassName StatisticsService 
 * @Description (统计service) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月28日 下午7:43:53 
 * @修改历史  
 *     1. [2017年9月28日]创建文件 by 顾冲
 */
public interface StatisticsService {
    /**
     * @Title CombinedJsgmData 
     * @Description (封装建设规模数据) 
     * @param tznd
     * @param partTag
     * @param loginUser
     * @return
     * @Return Map<String,Object> 返回类型 
     * @Throws 
     * @Date  2017年9月28日
     * @修改历史  
     *     1. [2017年9月28日]创建文件 by 顾冲
     */
    public Map<String, Object> CombinedJsgmData(String tznd, String partTag, User loginUser);
    /**
     * @Title getCityByProvince 
     * @Description (查询某省的城市) 
     * @param province
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年9月30日
     * @修改历史  
     *     1. [2017年9月30日]创建文件 by 顾冲
     */
    public List<Object> getCityByProvince(String province);
    /**
     * @Title getProvince 
     * @Description (获取省份集合) 
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年10月9日
     * @修改历史  
     *     1. [2017年10月9日]创建文件 by 顾冲
     */
    public List<Object> getProvince();
    /**
     * @Title getTzGroupByArea 
     * @Description (根据地区分组获取投资) 
     * @param roleName 用户角色
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年10月9日
     * @修改历史  
     *     1. [2017年10月9日]创建文件 by 顾冲
     */
    public List<Object> getTzGroupByArea(Map<String, Object> param);
    /**
     * @Title getProvinceShort 
     * @Description (获取所在省份列表) 
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年10月10日
     * @修改历史  
     *     1. [2017年10月10日]创建文件 by 顾冲
     */
    public List<Object> getProvinceShort();
    
    /**
     * @Title getDxtzGroupByArea 
     * @Description (根据地区分组获取单项投资) 
     * @param paramMap
     * @param areaList
     * @param roleName
     * @return
     * @Return Map<String,Double> 返回类型 
     * @Throws 
     * @Date  2017年10月9日
     * @修改历史  
     *     1. [2017年10月9日]创建文件 by 顾冲
     */
    public Map<String, Double> getDxtzGroupByArea(Map<String, Object> paramMap, List<Object> areaList, String roleName);
    
    /**
     * @Title getZytzGroupByXmlb 
     * @Description (根据项目类别分组获取单项投资) 
     * @param paramMap
     * @return
     * @Return Map<String,Double> 返回类型 
     * @Throws 
     * @Date  2017年10月9日
     * @修改历史  
     *     1. [2017年10月9日]创建文件 by 顾冲
     */
    public Map<String, Double> getDxtzGroupByXmlb(Map<String, Object> paramMap);
    
    /**
     * @Title getDxtzGroupBySslx 
     * @Description (根据设施类型获取单项投资) 
     * @param paramMap
     * @return
     * @Return Map<String,Double> 返回类型 
     * @Throws 
     * @Date  2017年10月9日
     * @修改历史  
     *     1. [2017年10月9日]创建文件 by 顾冲
     */
    public Map<String, Double> getDxtzGroupBySslx(Map<String, Object> paramMap);
    
    /**
     * @Title getDftzAndZytzGroupByArea 
     * @Description (根据投资年度、所在省份等条件，地区分组获取中央投资和地方投资) 
     * @param param
     * @param areaList
     * @return
     * @Return Map<String,Object> 返回类型 
     * @Throws 
     * @Date  2017年10月19日
     * @修改历史  
     *     1. [2017年10月19日]创建文件 by 顾冲
     */
    public Map<String, Object> getDftzAndZytzGroupByArea(Map<String, Object> param, List<Object> areaList);
    
    /**
     * @Title getDftzAndZytzGroupByXmlb 
     * @Description (根据省份、投资年度等条件，项目类别分组获取中央投资和地方投资) 
     * @param param
     * @return
     * @Return Map<String,Object> 返回类型 
     * @Throws 
     * @Date  2017年10月19日
     * @修改历史  
     *     1. [2017年10月19日]创建文件 by 顾冲
     */
    public Map<String, Object> getDftzAndZytzGroupByXmlb(Map<String, Object> param);
    /**
     * @Title getDftzAndZytz 
     * @Description (根据省份、投资年度等条件获取中央投资和地方投资) 
     * @param param
     * @return
     * @Return Map<String,Object> 返回类型 
     * @Throws 
     * @Date  2017年10月20日
     * @修改历史  
     *     1. [2017年10月20日]创建文件 by 顾冲
     */
    public Map<String, Object> getDftzAndZytz(Map<String, Object> param);
}
