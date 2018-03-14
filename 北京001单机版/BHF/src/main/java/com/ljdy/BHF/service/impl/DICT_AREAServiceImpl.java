package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.DICT_AREA;
import com.ljdy.BHF.service.DICT_AREAService;

/**
 * 地区字典表操作接口实现
 * @author 徐成
 * 修改历史：
 * 		[2017年9月26日] 创建文件	by	徐成
 *
 */
@Service("dict_areaService")
public class DICT_AREAServiceImpl implements DICT_AREAService {
    
    @Resource
    private BaseDao<DICT_AREA> baseDao;

    /**
     * 获取省份数据
     * @return
     */
    @Override
    public List<DICT_AREA> findProvince() {
        String hql = "from DICT_AREA a where a.typeName = '省（直辖市、自治区）' order by a.orderCode";
        return baseDao.find(hql);
    }

    /**
     * 根据上级行政编码获取数据
     * @param superCode
     * @return
     */
    @Override
    public List<DICT_AREA> findBySuperCode(String superCode) {
        String hql = "from DICT_AREA d where d.superCode = ? order by d.orderCode";
        Object[] param = {superCode};
        return baseDao.find(hql,param);
    }

    /**
     * 根据行政编码获取地区名称
     * @param codeValue
     * @return
     */
    @Override
    public String getCodeNameByCodeValue(String codeValue) {
        String hql = "select d.codeName from DICT_AREA d where d.codeValue = ?";
        Object[] param = {codeValue};
        Object obj = baseDao.getUniqueResult(hql,param);
        return obj==null?null:obj.toString();
    }

    /**
     * 根据地名获取行政编码
     * @param codeName
     * @return
     */
    @Override
    public String getCodeValueByCodeName(String codeName) {
        String hql = "select d.codeValue from DICT_AREA d where d.codeName = ?";
        Object[] param ={codeName};
        Object obj = baseDao.getUniqueResult(hql,param);
        return obj==null?null:obj.toString();
    }

    /**
     * 根据地名和上级行政编码获取行政编码
     * @param codeName
     * @param superCode
     * @return
     */
    @Override
    public String getCodeValueByCodeNameAndSuperCode(String codeName, String superCode) {
        String hql = "select d.codeValue from DICT_AREA d where d.codeName = ? and d.superCode = ?";
        Object[] param = {codeName,superCode};
        Object obj = baseDao.getUniqueResult(hql,param);
        return obj==null?null:obj.toString();
    }

    /**
     * 获取地区数据
     * @param codeValue
     * @param codeName
     * @return
     */
    @Override
    public List<DICT_AREA> getData(String codeValue, String codeName,String codeName_key,String codeValue_key) {
        StringBuffer hql = new StringBuffer("from DICT_AREA d where 1=1 ");
        List<Object> param = new ArrayList<Object>();
        if(codeName_key!=null && !codeName_key.trim().equals("")){
            hql.append(" and d.codeName like ?");
            param.add("%"+codeName_key+"%");
        }
        if(codeValue_key!=null && !codeValue_key.trim().equals("")){
            hql.append(" and d.codeValue like ?");
            param.add("%"+codeValue_key+"%");
        }

        if(codeName==null){
            hql.append(" order by d.codeValue,d.orderCode");
            return baseDao.find(hql.toString(),param);
        }else{
            hql.append(" and (d.superCode in (select d.codeValue from DICT_AREA d where d.superCode = ?)");
            hql.append(" or d.superCode = (select d.codeValue from DICT_AREA d where d.codeName = ?))");
            hql.append(" order by d.codeValue,d.orderCode");
            //List<Object> param = new ArrayList<Object>();
            param.add(codeValue);
            param.add(codeName);
            //param.add(codeName);
            return baseDao.find(hql.toString(),param);
        }
    }

    @Override
    public void add(DICT_AREA dict_area) {
        baseDao.save(dict_area);
    }

    @Override
    public void update(DICT_AREA dict_area) {
        baseDao.update(dict_area);
    }

    @Override
    public void deleteByID(String id) {
        String sql = "delete from DICT_AREA d where d.id=?";
        Object[] param = {id};
        baseDao.executeHql(sql,param);
    }

    @Override
    public DICT_AREA getDataByID(String id) {
        String sql = "from DICT_AREA d where d.id=?";
        Object[] param = {id};
        return baseDao.get(sql,param);
    }


}
