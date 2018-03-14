package com.ljdy.BHF.service;

import java.util.List;

import com.ljdy.BHF.model.DICT_AREA;

/**
 * 地区字典表操作接口
 * @author 徐成
 * 修改历史：
 * 		[2017年9月26日] 创建文件	by	徐成
 *
 */
public interface DICT_AREAService {

    /**
     * 查询省一级数据
     * @return
     */
    public List<DICT_AREA> findProvince();

    /**
     * 根据上级字典编码或者字典数据
     * @param superCode
     * @return
     */
    public List<DICT_AREA> findBySuperCode(String superCode);

    /**
     * 根据字典编码获取字典名称
     * @param codeValue
     * @return
     */
    public String getCodeNameByCodeValue(String codeValue);

    /**
     * 根据字典名称获取字典编码
     * @param codeName
     * @return
     */
    public String getCodeValueByCodeName(String codeName);

    /**
     * 更加字典名称和上级编码获取字典编码
     * @param codeName
     * @param superCode
     * @return
     */
    public String getCodeValueByCodeNameAndSuperCode(String codeName,String superCode);

    /**
     * 获取地区数据
     * @param codeValue
     * @param codeName
     * @param codeName_key      页面查询条件--行政区名称
     * @param codeValue_key     页面查询条件--行政区编码
     * @return
     */
    public List<DICT_AREA> getData(String codeValue,String codeName,String codeName_key,String codeValue_key);

    /**
     * 添加
     * @param dict_area
     */
    public void add(DICT_AREA dict_area);

    /**
     * 修改
     * @param dict_area
     */
    public void update(DICT_AREA dict_area);

    /**
     * 根据主键删除
     * @param id
     */
    public void deleteByID(String id);

    /**
     * 根据主键查询数据
     * @param id
     */
    public DICT_AREA getDataByID(String id);
}
