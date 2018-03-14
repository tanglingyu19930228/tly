package com.ljdy.BHF.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.AddJbxx;

/**
 * @ClassName JbxxService 
 * @Description (基本信息service) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月26日 下午3:36:15 
 * @修改历史  
 *     1. [2017年9月26日]创建文件 by 顾冲
 */
public interface JbxxService {
    /**
     * @Title getZytzGroupByArea 
     * @Description (根据投资年度条件，所在省份或者城市分组获取单项投资额) 
     * @param paramMap
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年10月9日
     * @修改历史  
     *     1. [2017年10月9日]创建文件 by 顾冲
     */
    public List<Object> getDxtzGroupByArea(Map<String, Object> paramMap);
    /**
     * @Title getZytzGroupBySslx 
     * @Description (根据投资年度、所在省份等条件，设施类别分组获取单项投资额) 
     * @param paramMap
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年10月9日
     * @修改历史  
     *     1. [2017年10月9日]创建文件 by 顾冲
     */
    public List<Object> getDxtzGroupBySslx(Map<String, Object> paramMap); 
    /**
     * @Title getZytzGroupByXmlb 
     * @Description (根据投资年度、所在省份等条件，项目类别分组获取单项投资额) 
     * @param paramMap
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年10月9日
     * @修改历史  
     *     1. [2017年10月9日]创建文件 by 顾冲
     */
    public List<Object> getDxtzGroupByXmlb(Map<String, Object> paramMap); 
    /**
     * @Title deleteByIds 
     * @Description (根据主键删除) 
     * @param modelName 实体类名称
     * @param ids 主键字符数
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年10月13日
     * @修改历史  
     *     1. [2017年10月13日]创建文件 by 顾冲
     */
    public void deleteByIds(String modelName, String ids);
    
    /**
     * @Title deleteByIds 
     * @Description (根据主键删除,使用维护部分) 
     * @param modelName
     * @param ids
     * @param partTag
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年12月1日
     * @修改历史  
     *     1. [2017年12月1日]创建文件 by 顾冲
     */
    public void deleteByIds(String modelName, String ids, String partTag);
    
    /**
     * 根据数组获取数据
     * @param arr		项目id数组
     * @param tableName	表名
     * 修改历史  
     *     1. [2017年10月18日]创建文件 by 徐成
     *     2. [2017年12月13日]修改文件 增加参数“tableName” by 徐成
     */
    public List<Object> getDataByArray(ArrayList<String> arr,String tableName);
    
    /**
     * 批量更新
     * @param list
     * 修改历史  
     *     1. [2017年10月18日]创建文件 by 徐成
     */
    public void batchUpdate(List<Object> list);
    
    /**
     * 批量保存
     * @param list
     * 修改历史  
     *     1. [2017年10月18日]创建文件 by 徐成
     */
    public void batchSave(List<Object> list);
    
    /**
     * @Title getDftzAndZytz 
     * @Description (根据投资年度、所在省份等条件，地区分组获取地方投资、中央投资) 
     * @param paramMap
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年10月19日
     * @修改历史  
     *     1. [2017年10月19日]创建文件 by 顾冲
     */
    public List<Object> getDftzAndZytzGroupByArea(Map<String, Object> paramMap);
    
    /**
     * @Title getZytzAndDftzGroupByXmlb 
     * @Description (根据投资年度、所在省份等条件，项目类别分组获取地方投资、中央投资) 
     * @param paramMap
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年10月19日
     * @修改历史  
     *     1. [2017年10月19日]创建文件 by 顾冲
     */
    public List<Object> getZytzAndDftzGroupByXmlb(Map<String, Object> paramMap);
    /**
     * @Title toImplement 
     * @Description (转入实施) 
     * @param ids
     * @param xmzl
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年10月20日
     * @修改历史  
     *     1. [2017年10月20日]创建文件 by 顾冲
     */
    public void toImplement(String ids, String xmzl);
    
    /**
     * 根据省份、投资年度获取中央投资、地方投资、总投资
     * @param condition
     * @return
     */
    public Object getZytzAndDftzBySzsf(Map<String,Object> condition);
    
    /**
     * 监督实施模块根据省份查询饼状图的数据
     * @param conditionPie
     * @return List<Object>
     * @date [2017-11-10] 创建文件 by 郎川
     */
    public List<Object> getPieFindProvice(Map<String, Object> conditionPie);
    
    /**
     * 获取项目类别
     * @return List<Object>
     * @param 
     * @date [2017-11-13] 创建文件 by 郎川
     */
    public List<Object> getXmlb();
    

    /**
     * 获取项目类别数据
     * @return List<Object>
     * @param
     * @date[2017-11-13] 创建文件 by郎川
     */
    public List<Object>getXmlbData();
    
    /**
     * 根据项目类别获取建设投资
     * @return List<Object>
     * @param xmlbCondition
     * @date [2017-11-13] 创建文件 by 郎川
     */
    public List<Object> getJstzGroupByXmlb(Map<String, Object>paramMap);
    

    /**
     * @Title getTzByArea 
     * @Description (根据地区获取投资) 
     * @return
     * @Return Map<String, Object> 返回类型 
     * @Throws 
     * @Date  2017年11月13日
     * @修改历史  
     *     1. [2017年11月13日]创建文件 by 顾冲
     */
    public Map<String, Object> getTzByArea(Map<String, Object> condition);
    
    /**
     * @Title getDataById
     * @Description (根据主键查询) 
     * @param id
     * @return
     * @Return Object 返回类型 
     * @Throws 
     * @Date  2017年11月16日
     * @修改历史  
     *     1. [2017年11月16日]创建文件 by 顾冲
     */
    public Object getDataById(String id);
    
    /**
     * 转入使用维护
     * @param id xmzl
     * @return  void
     * @date [2017-11-21] 创建文件 by 郎川
     */
    public void toUseMaintenance(String id,String xmzl);
    
    /**
     * 添加
     */
    
    public void addJbxx(AddJbxx addJbxx);
    /**
     * 根据使用状态分组中央投资和地方投资
     * @return List<Object>
     * @date [2017-11-23] 创建文件  by 郎川
     */
    public List<Object> getsyzt();
    
    /**
     * 查询省下面的市
     * @param codeName
     * @return List<Object>
     * @date [2017-12-5]创建文件 by 郎川
     */
    public List<Object> findCity(String codeName);
    /**
     * 根据项目编号查询
     * @param xmbh
     * @return
     */
    public List<Object> getDataByArray(String xmbh);
    
    /**
     * 更新
     */
    public void updateJbxx(String sql,Object[] param); 
    
    public void update(AddJbxx jbxx);
    
    /**
     * @Title getSywhDataByCondition 
     * @Description (获取使用维护信息) 
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月8日
     * @修改历史  
     *     1. [2017年12月8日]创建文件 by 顾冲
     */
    public List<Object> getSywhData();

    
    /**
     * @Title getDataByIds 
     * @Description (根据ids获取信息) 
     * @param idsArr
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月15日
     * @修改历史  
     *     1. [2017年12月15日]创建文件 by 顾冲
     */
    public List<Object> getDataByIds(String [] idsArr);
    
    /**
     * @Title updateBySql
     * @Description (sql语句更新)
     * @param sql
     * @param param
     * @Return void 返回类型
     * @Throws
     * @Date 2017年12月18日
     * @修改历史 1. [2017年12月18日]创建文件 by 顾冲
     */
    public void updateBySql(String sql, Object[] param);
    
    /**
     * 
     * @Title updateSyztByXmid 
     * @Description (这里用一句话描述这个方法的作用) 
     * @param xmid
     * @param syzt
     * @return
     * @Return int 返回类型 
     * @Throws 
     * @Date  2017年12月25日
     * @修改历史  
     *     1. [2017年12月25日]创建文件 by 顾冲
     */
    public int updateSyztByXmid(String xmid, String syzt);
    
}
