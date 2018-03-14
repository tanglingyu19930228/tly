package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.Xmwhjl;
import com.ljdy.BHF.service.XmwhjlService;
import com.ljdy.BHF.utils.CommonUtil;
@Service("xmwhjlService")
public class XmwhjlServiceImpl implements XmwhjlService{
    
    @Resource BaseDao<Object> baseDao;
    
    /**
      * @Title getDataByCondition 
      * @Description (多条件查询数据，带分页) 
      * @param condition
      * @param page
      * @param rows
      * @return 
      * @see com.ljdy.BHF.service.XmwhjlService#getDataByCondition(java.util.Map, int, int)
      * @Date  2017年11月15日
      * @修改历史  
      *     1. [2017年11月15日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getDataByCondition(Map<String, Object> condition, int page, int rows) {
        String str = "select new map(w.wxdw as wxdw, x.wxgm as wxgm, x.wxxmmc as wxxmmc, x.wxfy as wxfy, x.gzsb as gzsb, "
                + "x.gzsbpp as gzsbpp, x.gzsbxh as gzsbxh, x.gzbj as gzbj, x.shyy as shyy, x.gzsj as gzsj, x.wxsj as wxsj, x.id as id, x.wxxmid as wxxmid) from Xmwhjl x,Whxm  w where x.whxmid = w.id";
        if (page == 0 || rows == 0) {
            str = "select count(*) from Xmwhjl x,Whxm  w where x.whxmid = w.id";
        }
        StringBuffer hql = new StringBuffer(str);
        
        List<Object> param = new ArrayList<Object>();
        
        if(CommonUtil.validCondition(condition, "wxxmid")){
            hql.append(" and x.wxxmid = ? ");
            param.add(condition.get("wxxmid"));
        }
        if (page == 0 || rows == 0) {
            return baseDao.find(hql.toString(), param);
        } else {
            return baseDao.find(hql.toString(), param);
        }
    }
    
    /**
      * @Title addXhjl 
      * @Description (新增维护记录) 
      * @param xmwhjl 
      * @see com.ljdy.BHF.service.XmwhjlService#addXhjl(com.ljdy.BHF.model.Xmwhjl)
      * @Date  2017年11月17日
      * @修改历史  
      *     1. [2017年11月17日]创建文件 by 顾冲 
     *
     */
    @Override
    public void addXhjl(Xmwhjl xmwhjl) {
        baseDao.save(xmwhjl);
    }
    
    /**
      * @Title getDataByCondition 
      * @Description (条件查询维护记录) 
      * @param condition
      * @return 
      * @see com.ljdy.BHF.service.XmwhjlService#getDataByCondition(java.util.Map)
      * @Date  2017年11月24日
      * @修改历史  
      *     1. [2017年11月24日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getDataByCondition(Map<String, Object> condition) {
        String str = "from Xmwhjl x where 1=1";
        StringBuffer hql = new StringBuffer(str);
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "wxxmid")){
            hql.append(" and x.wxxmid = ? ");
            param.add(condition.get("wxxmid"));
        }
        if(CommonUtil.validCondition(condition, "syzt")){
            hql.append(" and x.syzt = ? ");
            param.add(condition.get("syzt"));
        }
        return baseDao.find(hql.toString(), param);
    }
    
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
    @Override
    public List<Object> getWhjl(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(x.id as id, x.wxgm as wxgm, x.wxfy as wxfy, x.gzsb as gzsb,  x.gzsbpp as gzsbpp, "
                + "x.gzsbxh as gzsbxh, x.gzbj as gzbj, x.shyy as shyy, x.gzsj as gzsj, x.wxsj as wxsj, x.whjlzt as whjlzt, x.whzt as whzt,"
                + " x.wxxmid as wxxmid, x.wxxmbh as wxxmbh, x.wxxmmc as wxxmmc, x.whxmid as whxmid, w.wxdw as wxdw) from Xmwhjl x, Whxm w where x.whxmid = w.id");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "szsf")){
            hql.append(" and w.szsf = ? ");
            param.add(condition.get("szsf"));
        }
        //hql.append(" order by j.xmzl");
        return baseDao.find(hql.toString(), param);
    }

    /**
      * @Title getDataByArray 
      * @Description (根据id集合获取数据) 
      * @param arr
      * @return 
      * @see com.ljdy.BHF.service.XmwhjlService#getDataByArray(java.util.ArrayList)
      * @Date  2017年12月11日
      * @修改历史  
      *     1. [2017年12月11日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getDataByArray(List<String> arr) {
        StringBuffer hql =new StringBuffer("from Xmwhjl x where x.id in (");
        List<Object> param = new ArrayList<Object>();
        for (int i = 0; i < arr.size(); i++) {
            if(i == 0) {
                hql.append("?");
            }else{
                hql.append(",?");
            }
            param.add(arr.get(i));
        }
        hql.append(")");
        return baseDao.find(hql.toString(), param);
    }
    
    /**
      * @Title update 
      * @Description (修改项目维护记录) 
      * @param xmwhjl
      * @return 
      * @see com.ljdy.BHF.service.XmwhjlService#update(com.ljdy.BHF.model.Xmwhjl)
      * @Date  2017年12月21日
      * @修改历史  
      *     1. [2017年12月21日]创建文件 by 顾冲 
     *
     */
    @Override
    public int update(String sql, Object [] param) {
        return  baseDao.updateBySql(sql, param);
    }
    
    /**
      * @Title getWxxmbhByWhxmid 
      * @Description (这里用一句话描述重构方法的作用) 
      * @param whxmid
      * @return 
      * @see com.ljdy.BHF.service.XmwhjlService#getWxxmidByWhxmid(java.lang.String)
      * @Date  2017年12月25日
      * @修改历史  
      *     1. [2017年12月25日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getWxxmidByWhxmid(String whxmid) {
        StringBuffer hql =new StringBuffer("select distinct wxxmid from Xmwhjl  where whxmid = ?");
        return baseDao.find(hql.toString(), new Object []{whxmid});
    }
    
    /**
      * @Title getUnCompletedCountByWxxmid 
      * @Description (根据维修项目id统计维护未完成的维护记录) 
      * @param wxxmid
      * @return 
      * @see com.ljdy.BHF.service.XmwhjlService#getUnCompletedCountByWxxmid(java.lang.String)
      * @Date  2017年12月25日
      * @修改历史  
      *     1. [2017年12月25日]创建文件 by 顾冲 
     *
     */
    @Override
    public long getUnCompletedCountByWxxmid(String wxxmid) {
        StringBuffer hql =new StringBuffer("select count(*) from Xmwhjl  where wxxmid = ? and whzt <> '维护完成'");
        return (long)baseDao.find(hql.toString(), new Object []{wxxmid}).get(0);
    }
    
    /**
      * @Title getWxfyGroupByArea 
      * @Description ((获取个地区的维护费用) 
      * @param condition
      * @return 
      * @see com.ljdy.BHF.service.XmwhjlService#getWxfyGroupByArea(java.util.Map, com.ljdy.BHF.model.User)
      * @Date  2017年12月27日
      * @修改历史  
      *     1. [2017年12月27日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getWxfyGroupByArea(Map<String, Object> condition) {
        String str = "select new map(sum(l.wxfy) as wxfy, j.szsf as area) from Xmwhjl l,Jbxx j where l.wxxmid = j.id";
        if(CommonUtil.validCondition(condition, "szsf")){
            str = "select new map(sum(l.wxfy) as wxfy, j.szcs as area) from Xmwhjl l,Jbxx j where l.wxxmid = j.id";
        }
        StringBuffer hql = new StringBuffer(str);
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "szsf")){
            hql.append(" and j.szsf like ?");
            param.add("%" + condition.get("szsf") + "%");
            hql.append(" group by j.szcs");
        }else{
            hql.append(" group by j.szsf");
        }
        return baseDao.find(hql.toString(), param);
    }
    
    /**
      * @Title getWxfyGroupByXmlb 
      * @Description (根据项目类别分组查询维修费用) 
      * @param condition
      * @return 
      * @see com.ljdy.BHF.service.XmwhjlService#getWxfyGroupByXmlb(java.util.Map)
      * @Date  2017年12月27日
      * @修改历史  
      *     1. [2017年12月27日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getWxfyGroupByXmlb(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(sum(l.wxfy) as wxfy, j.xmlb as xmlb) from Xmwhjl l,Jbxx j where l.wxxmid = j.id");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "szsf")){
            hql.append(" and j.szsf like ?");
            param.add("%" + condition.get("szsf") + "%");
        }
        if(CommonUtil.validCondition(condition, "szcs")){
            hql.append(" and j.szcs like ?");
            param.add("%" + condition.get("szcs") + "%");
        }
        hql.append(" group by j.xmlb");
        return baseDao.find(hql.toString(), param);
    }

}
