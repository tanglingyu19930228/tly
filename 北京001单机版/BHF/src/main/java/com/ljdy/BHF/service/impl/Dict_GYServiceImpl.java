package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.DICT_GY;
import com.ljdy.BHF.service.Dict_GYService;

/**
 * 公用字典表数据操作接口实现
 * @author 徐成
 * 修改历史：
 * 		[2017年9月26日] 创建文件	by	徐成
 *
 */
@Service("dict_gyService")
public class Dict_GYServiceImpl implements Dict_GYService {

	@Resource
	private BaseDao<DICT_GY> baseDao;
	
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
	@Override
	public List<DICT_GY> getDict_GYsByType(String typeName) {
		String hql = "from DICT_GY d where d.typeName =? order by d.orderCode";
		Object[] param = {typeName};
		return baseDao.find(hql, param);
	}

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
	@Override
	public List<DICT_GY> getDict_GyBySuperCode(String typeName,String superTypeName, String superCodeName) {
		String hql = "from DICT_GY d where d.typeName= ? and d.superCode =(select d.codeValue from DICT_GY d where d.typeName=? and d.codeName=?) order by d.orderCode";
        Object[] param = {typeName,superTypeName,superCodeName};
        return baseDao.find(hql, param);
	}

	/**
	 * 获取所有字典数据
	 * @return
	 */
	@Override
	public List<DICT_GY> findAll(String typeName) {
		StringBuffer hql = new StringBuffer("from DICT_GY d");
		List<Object> param = new ArrayList<Object>();
		if(typeName!=null && !typeName.trim().equals("")){
			hql.append(" where d.typeName like ?");
			param.add("%"+typeName+"%");
		}
		hql.append(" order by d.typeName ,d.orderCode");
		return baseDao.find(hql.toString(),param);
	}

	@Override
	public void save(DICT_GY dict_gy) {
		baseDao.save(dict_gy);
	}

	@Override
	public void update(DICT_GY dict_gy) {
		baseDao.update(dict_gy);
	}

	@Override
	public void deleteByID(String id) {
		String hql = "delete from DICT_GY d where d.id=?";
		Object[] param = {id};
		baseDao.executeHql(hql,param);
	}

	@Override
	public DICT_GY getDateByID(String id) {
		String hql = "from DICT_GY d where d.id=?";
		Object[] param = {id};
		return baseDao.get(hql,param);
	}

	@Override
	public int getNextOrderCode(String typeName){
		String hql = "from DICT_GY d where d.typeName = ?";
		Object[] param ={typeName};
		List<DICT_GY> list = baseDao.find(hql,param);
		return list==null||list.size()==0?1:list.size()+1;
	}
}
