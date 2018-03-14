package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.Whxm;
import com.ljdy.BHF.service.WhxmService;
import com.ljdy.BHF.utils.CommonUtil;

/**
 * @ClassName WhxmServiceImpl 
 * @Description (维护项目service实现类) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年11月22日 上午9:17:46 
 * @修改历史  
 *     1. [2017年11月22日]创建文件 by 顾冲
 */
@Service("whxmService")
public class WhxmServiceImpl implements WhxmService {
    
    @Resource
    private BaseDao<Object> baseDao;
    
    /**
      * @Title getDataByCondition 
      * @Description (多条件查询数据，带分页) 
      * @param condition
      * @param page
      * @param rows
      * @return 
      * @see com.ljdy.BHF.service.WhxmService#getDataByCondition(java.util.Map, int, int)
      * @Date  2017年11月22日
      * @修改历史  
      *     1. [2017年11月22日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getDataByCondition(Map<String, Object> condition, int page, int rows) {
        StringBuffer hql = new StringBuffer("");
        List<Object> param = new ArrayList<Object>();
        if(page == 0 || rows == 0){
            hql.append("select count(*) from Whxm w where 1=1");
        }else{
            hql.append("from Whxm w where 1=1");
        }
        if(CommonUtil.validCondition(condition, "szsf")){
            hql.append(" and w.szsf like ? ");
            param.add("%" + condition.get("szsf") + "%");
        }
        hql.append(" order by w.createTime desc");
        return baseDao.find(hql.toString(), param);
        
    }
    
    /**
      * @Title addWhxm 
      * @Description (添加维护项目) 
      * @param whxm 
      * @see com.ljdy.BHF.service.WhxmService#addWhxm(com.ljdy.BHF.model.Whxm)
      * @Date  2017年11月22日
      * @修改历史  
      *     1. [2017年11月22日]创建文件 by 顾冲 
     *
     */
    @Override
    public void addWhxm(Whxm whxm) {
        baseDao.save(whxm);
    }
    
    /**
      * @Title getProvinceByShortName 
      * @Description (根据省份简称查询全称) 
      * @param shortName
      * @return 
      * @see com.ljdy.BHF.service.WhxmService#getProvinceByShortName(java.lang.String)
      * @Date  2017年11月28日
      * @修改历史  
      *     1. [2017年11月28日]创建文件 by 顾冲 
     *
     */
    @Override
    public String getProvinceByShortName(String shortName){
        if(shortName == null || shortName.trim() == ""){
            return shortName;
        }
        StringBuffer hql = new StringBuffer(
                "select new map(a.codeName as codeName) from DICT_AREA a where a.superCode is null and a.codeName like ? ");
        List<Object> queryList = baseDao.find(hql.toString(),new Object []{shortName});
        if(queryList == null || queryList.isEmpty()){
            return shortName;
        }
        return (String)queryList.get(0);
    }
    
    /**
      * @Title getData 
      * @Description (获取维护项目信息) 
      * @param condition
      * @return 
      * @see com.ljdy.BHF.service.WhxmService#getData(java.util.Map)
      * @Date  2017年12月11日
      * @修改历史  
      *     1. [2017年12月11日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("from Whxm w where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "szsf")){
            hql.append(" and w.szsf like ? ");
            param.add("%" + condition.get("szsf") + "%");
        }
       return baseDao.find(hql.toString(),param);
    }

    /**
     * @Title getDataByArray
     * @Description (根据id集合获取数据)
     * @param arr
     * @return
     * @see com.ljdy.BHF.service.WhxmService#getDataByArray(java.util.List)
     * @Date 2017年12月11日
     * @修改历史 1. [2017年12月11日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDataByArray(List<String> arr) {
        StringBuffer hql = new StringBuffer("from Whxm w where w.id in (");
        List<Object> param = new ArrayList<Object>();
        for (int i = 0; i < arr.size(); i++) {
            if (i == 0) {
                hql.append("?");
            } else {
                hql.append(",?");
            }
            param.add(arr.get(i));
        }
        hql.append(")");
        return baseDao.find(hql.toString(), param);
    }
   
    /**
     * @Title getDataById
     * @Description (根据主键获取数据)
     * @param id
     * @return
     * @see com.ljdy.BHF.service.WhxmService#getDataById(java.lang.String)
     * @Date 2017年12月22日
     * @修改历史 1. [2017年12月22日]创建文件 by 顾冲
     */

    @Override
    public List<Object> getDataById(String id) {
        StringBuffer hql = new StringBuffer("select new map(w.id as whxmid, w.xmmc as xmmc, w.xmbh as xmbh, w.wxdw as wxdw, w.wxzfy as wxzfy,"
                + " x.wxgm as wxgm, x.wxxmmc as wxxmmc, x.wxxmbh as wxxmbh, x.wxfy as wxfy, x.whzt as whzt,"
                + " x.id as whjlid) from Xmwhjl x,Whxm w where w.id = ? and x.whxmid = w.id");
        List<Object> param = new ArrayList<Object>();
        param.add(id);
        return baseDao.find(hql.toString(), param);
    }

    /**
      * @Title update 
      * @Description (维护项目修改) 
      * @param sql
      * @param param
      * @return 
      * @see com.ljdy.BHF.service.WhxmService#update(java.lang.String, java.lang.Object[])
      * @Date  2017年12月22日
      * @修改历史  
      *     1. [2017年12月22日]创建文件 by 顾冲 
     *
     */
    @Override
    public int update(String sql, Object[] param) {
        return baseDao.updateBySql(sql, param);
    }

}
