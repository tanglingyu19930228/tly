package com.ljdy.BHF.service;

import java.util.List;

import com.ljdy.BHF.model.DICT_GY;

/**
 * 公用字典表数据操作接口
 * @author 徐成
 * 修改历史：
 * 		[2017年9月26日] 创建文件	by	徐成
 *
 */
public interface Dict_GYService {

	/**
     * @Title getDict_GYsByType 
     * @Description (根据字典类型获取字典数据) 
     * @param typeName
     * @return
     * @Return List<DICT_GY> 返回类型 
     * @Throws 
     * @Date  2017年9月26日
     * @修改历史  
     *     1. [2017年9月26日]创建文件 by 徐成
     */
    public List<DICT_GY> getDict_GYsByType(String typeName);
    
    /**
     * @Title getDict_GyBySuper 
     * @Description (根据字典类型、上级字典类型、上级字典名称获取字典数据) 
     * @param typeName
     * @param superTypeName
     * @param superCodeName
     * @return
     * @Return List<DICT_GY> 返回类型 
     * @Throws 
     * @Date  2017年9月26日
     * @修改历史  
     *     1. [2017年9月26日]创建文件 by 徐成
     */
    public List<DICT_GY> getDict_GyBySuperCode(String typeName,String superTypeName,String superCodeName);

    /**
     * 根据字典类型获取所有字典数据
     * @param typeName      字典类型
     * @return
     */
    public List<DICT_GY> findAll(String typeName);

    /**
     * 新增
     * @param dict_gy
     */
    public void save(DICT_GY dict_gy);

    /**
     * 修改
     * @param dict_gy
     */
    public void update(DICT_GY dict_gy);

    /**
     * 删除
     * @param id
     */
    public void deleteByID(String id);

    /**
     * 根据主键获取数据
     * @param id
     */
    public DICT_GY getDateByID(String id);

    /**
     * 根据字典类型获取下一个排序号
     * @param typeName
     * @return
     */
    public int getNextOrderCode(String typeName);
}
