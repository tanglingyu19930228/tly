package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.AddJbxx;
import com.ljdy.BHF.service.JbxxService;
import com.ljdy.BHF.utils.CommonUtil;
/**
 * @ClassName JbxxServiceImp 
 * @Description (基本信息service实现类) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月26日 下午3:36:04 
 * @修改历史  
 *     1. [2017年9月26日]创建文件 by 顾冲
 */
@Service("jbxxService")
public class JbxxServiceImp implements JbxxService{
    @Resource
    private BaseDao<Object> baseDao;
    /**
      * @Title getDxtzGroupByArea 
      * @Description (根据投资年度、项目类别、项目子类、设施类型等，根据所在省份或者城市分组获取单项投资额) 
      * @param paramMap
      * @return 
      * @see com.ljdy.BHF.service.JbxxService#getZytzGroupByArea(java.util.Map)
      * @Date  2017年10月9日
      * @修改历史  
      *     1. [2017年10月9日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getDxtzGroupByArea(Map<String, Object> paramMap) {
        //角色编号必传,省级用户必传所在省份
        String roleName = (String) paramMap.get("roleName"); 
        StringBuffer hql=new StringBuffer("select new map(sum(j.zytz + j.dftz) as dxtz");
        List<Object> param = new ArrayList<Object>();
        if("1".equals(roleName)){
        	if(paramMap.get("szsf")==null || paramMap.get("szsf").equals("null")){
        		hql.append(", j.szsf as area");
        	}else if(paramMap.get("szsf")!=null || !paramMap.get("szsf").equals("null")){
        		hql.append(", j.szcs as area");
        	}
        }else if("2".equals(roleName)){
            hql.append(", j.szcs as area");
        }
        hql.append(") from Jbxx j where 1=1");
        if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and j.bxxm = ?");
            param.add(paramMap.get("bxxm"));
        }
        if(CommonUtil.validCondition(paramMap, "tznd")){
            hql.append(" and j.tznd =?");
            param.add(paramMap.get("tznd"));
        }
        //监督实施模块查询建设状态不能为空
        if(CommonUtil.validCondition(paramMap, "partTag")){
        	if("3".equals(paramMap.get("partTag"))){
        		hql.append(" and j.jszt is not null and j.syzt is null ");
        	}else if("4".equals(paramMap.get("partTag"))){
        		hql.append(" and j.jszt ='已审计' and j.syzt in('良好','损坏','废弃') ");
        	}
        }
        if("1".equals(roleName)){
        		if(paramMap.get("szsf")==null || paramMap.get("szsf").equals("null")){
        			hql.append(" group by j.szsf");
        		}else if(paramMap.get("szsf")!=null || !paramMap.get("szsf").equals("null")){
        			hql.append(" and j.szsf like ? ");
        			param.add("%"+paramMap.get("szsf")+"%");
        			hql.append(" group by j.szcs");
        	}
        }else if("2".equals(roleName)){
            hql.append(" and j.szsf like ?");
            param.add("%"+paramMap.get("szsf")+"%");
            hql.append(" group by j.szcs");
        }
        hql.append(" order by dxtz desc");
        return baseDao.find(hql.toString(), param);
    }
    /**
      * @Title getZytzGroupBySslx 
      * @Description (根据投资年度、所在省份等条件，设施类别分组获取单项投资额) 
      * @param paramMap
      * @return 
      * @see com.ljdy.BHF.service.JbxxService#getZytzGroupBySslx(java.util.Map)
      * @Date  2017年10月9日
      * @修改历史  
      *     1. [2017年10月9日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getDxtzGroupBySslx(Map<String, Object> paramMap) {
        //角色编号必传,省级用户必传所在省份
        String roleName = (String) paramMap.get("roleName"); 
        StringBuffer hql=new StringBuffer("select new map(sum(j.zytz + j.dftz) as dxtz, j.sslx as sslx) from Jbxx j where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and j.bxxm = ?");
            param.add(paramMap.get("bxxm"));
        }
        if(CommonUtil.validCondition(paramMap, "tznd")){
            hql.append(" and j.tznd =?");
            param.add(paramMap.get("tznd"));
        }
        
       if("2".equals(roleName)){
           hql.append(" and j.szsf =?");
           param.add(paramMap.get("szsf"));
        }
        hql.append(" group by j.sslx");
        return baseDao.find(hql.toString(), param);
    }
    /**
      * @Title getDxtzGroupByXmlb 
      * @Description (根据投资年度、所在省份等条件，项目类别分组获取单项投资额) 
      * @param paramMap
      * @return 
      * @see com.ljdy.BHF.service.JbxxService#getDxtzGroupByXmlb(java.util.Map)
      * @Date  2017年10月9日
      * @修改历史  
      *     1. [2017年10月19日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getDxtzGroupByXmlb(Map<String, Object> paramMap) {
        //角色编号必传,省级用户必传所在省份
        String roleName = (String) paramMap.get("roleName"); 
        StringBuffer hql=new StringBuffer("select new map(sum(j.zytz + j.dftz) as dxtz, j.xmlb as xmlb) from Jbxx j where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and j.bxxm = ?");
            param.add(paramMap.get("bxxm"));
        }
        if(CommonUtil.validCondition(paramMap, "tznd")){
            hql.append(" and j.tznd =?");
            param.add(paramMap.get("tznd"));
        }
        /*if(CommonUtil.validCondition(paramMap, "partTag")){
        	if("3".equals(paramMap.get("partTag"))){
        		hql.append(" and j.jszt is not null ");
        	}
        }*/
       if("2".equals(roleName)){
           hql.append(" and j.szsf =?");
           param.add(paramMap.get("szsf"));
        }
		/*if (CommonUtil.validCondition(paramMap, "partTag")) {
			if ("3".equals(paramMap.get("partTag"))) {
				if ("1".equals(roleName)) {
					hql.append(" and j.szsf like ?");
					param.add("%" + paramMap.get("szsf") + "%");
				} else if ("2".equals(roleName)) {
					hql.append(" and j.szcs like ? ");
					param.add("%" + paramMap.get("szcs") + "%");
				}
			}

		}*/
     
      
        hql.append(" group by j.xmlb");
        return baseDao.find(hql.toString(), param);
    }
    /**
      * @Title deleteByIds  
      * @Description (根据主键删除) 
      * @param modelName 实体类名称
      * @param ids 主键字符数
      * @see com.ljdy.BHF.service.JbxxService#deleteByIds(java.lang.String, java.lang.String)
      * @Date  2017年10月13日
      * @修改历史  
      *     1. [2017年10月13日]创建文件 by 顾冲 
     *
     */
    @Override
    public void deleteByIds(String modelName, String ids) {
        String [] idsArr = ids.split(",");
        List<Object> idsList = new ArrayList<Object>();//所有jbxx 的id集合
        List<Object> param = new ArrayList<Object>();
        List<Object> jbxxParam = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("delete from " + modelName + " t where");
        StringBuffer deleteJbxxHql=new StringBuffer("delete from AddJbxx t where t.id in(");
        if("AddJkz".equals(modelName)){//监控站下面有视频前端、显控终端、传输线路、供电系统、报警装置,需要先删除视频前端、显控终端、传输线路、供电系统、报警装置，然后删除监控站
            StringBuffer spqdHql =new StringBuffer("delete from AddSpqd where");
            StringBuffer xkzdHql =new StringBuffer("delete from AddXkzd where");
            StringBuffer csxlHql =new StringBuffer("delete from AddCsxl where");
            StringBuffer bjzzHql =new StringBuffer("delete from AddBjzz where");
            StringBuffer gdxtHql =new StringBuffer("delete from AddGdxt where");
            List<Object> paramList = new ArrayList<Object>();
            for (int i = 0; i < idsArr.length; i++) {
                if(i != idsArr.length - 1){
                    spqdHql.append(" jkz_id = ? or");
                    xkzdHql.append(" jkz_id = ? or");
                    csxlHql.append(" jkz_id = ? or");
                    bjzzHql.append(" jkz_id = ? or");
                    gdxtHql.append(" jkz_id = ? or");
                    paramList.add(idsArr[i]);
                }else{
                    spqdHql.append(" jkz_id = ?");
                    xkzdHql.append(" jkz_id = ?");
                    csxlHql.append(" jkz_id = ?");
                    bjzzHql.append(" jkz_id = ?");
                    gdxtHql.append(" jkz_id = ?");
                    paramList.add(idsArr[i]);
                }
            }
            //查询删除子类的id
            Set<Object> zlIdSet =  getIdsByJkz_ids(idsArr);
            for (Object object : zlIdSet) {
                idsList.add(object);
            }
            
            baseDao.executeHql(spqdHql.toString(), paramList);
            baseDao.executeHql(xkzdHql.toString(), paramList);
            baseDao.executeHql(csxlHql.toString(), paramList);
            baseDao.executeHql(bjzzHql.toString(), paramList);
            baseDao.executeHql(gdxtHql.toString(), paramList);
            
        }
        for (int i = 0; i < idsArr.length; i++) {
            if(i != idsArr.length -1){
                hql.append(" t.id = ? or");
                param.add(idsArr[i]);
            }else{
                hql.append(" t.id = ?");
                param.add(idsArr[i]);
            }
            idsList.add(idsArr[i]);
        }
        baseDao.executeHql(hql.toString(), param);
        for (int i = 0; i < idsList.size(); i++) {
            if(i != idsList.size() - 1){
                deleteJbxxHql.append("?, ");
                jbxxParam.add(idsList.get(i));
            }else{
                deleteJbxxHql.append("?");
                jbxxParam.add(idsList.get(i));
            }
        }
        deleteJbxxHql.append(")");
        baseDao.executeHql(deleteJbxxHql.toString(), jbxxParam);
    }
    
    /**
     * 根据项目id数组获取数据
      * @param arr		项目id数组
     * @param tableName	表名
     * 修改历史  
     *     1. [2017年10月18日]创建文件 by 徐成
     *     2. [2017年12月13日]修改文件 增加参数“tableName” by 徐成
     */
    public List<Object> getDataByArray(ArrayList<String> arr,String tableName){
    	StringBuffer hql = new StringBuffer("select new map(j.id as id,j.xmbh as xmbh) from Jbxx j,"+tableName+" z where j.id = z.id and j.id in (");
    	/*// 查询传递过来的arr所对应的xmzl
    	StringBuffer selectXmzl=new StringBuffer("select z.xmzl as xmzl from jbxx z where z.xmbh =?");
    	List<Object> Xmzlparam = new ArrayList<>();
    	Xmzlparam.add(arr.get(0));
    	List<Object> xmzlList=baseDao.queryBySql(selectXmzl.toString(), Xmzlparam);
    	String xmzl=null;
    	if(xmzlList!=null&&xmzlList.size()>0){
    		xmzl=(String)xmzlList.get(0);
    	}
    	*//**
    	 * 查询相匹配的数据
    	 *//*
    	StringBuffer hql=new StringBuffer("select new map(z.id as id,z.xmbh as xmbh) from Jbxx z where z.xmzl='"+xmzl+"' and z.xmbh in (");*/
    	List<Object> param = new ArrayList<>();
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
     * 根据项目编号获取数据库数据 
     */
    
    public List<Object> getDataByArray(String xmbh){
    	StringBuffer hql=new StringBuffer("select new map(z.id as id,z.xmzl as xmzl) from Jbxx z where z.xmbh =?");   	
    	List<Object> param = new ArrayList<>();
    	param.add(xmbh);
        return baseDao.find(hql.toString(), param);
    }

    /**
     * 批量更新
     * @param list
     * 修改历史  
     *     1. [2017年10月18日]创建文件 by 徐成
     */
    public void batchUpdate(List<Object> list){
    	baseDao.batchUpdate(list);
    }
    
    /**
     * 批量保存
     * @param list
     * 修改历史  
     *     1. [2017年10月18日]创建文件 by 徐成
     */
    public void batchSave(List<Object> list){
    	baseDao.batchSave(list);
    }
    
    /**
      * @Title getDftzAndZytzGroupByArea 
      * @Description (根据投资年度、所在省份等条件，地区分组获取地方投资、中央投资) 
      * @param paramMap
      * @return 
      * @see com.ljdy.BHF.service.JbxxService#getDftzGroupByArea(java.util.Map)
      * @Date  2017年10月19日
      * @修改历史  
      *     1. [2017年10月19日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getDftzAndZytzGroupByArea(Map<String, Object> paramMap) {
        List<Object> paramList = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("select new map(sum(j.dftz) as dftz, sum(j.zytz) as zytz, j.szcs as szcs) from Jbxx j where j.szsf like ?");
        paramList.add("%" + paramMap.get("szsf") + "%");
        if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and j.bxxm = ?");
            paramList.add(paramMap.get("bxxm"));
        }
        if (CommonUtil.validCondition(paramMap, "tznd")) {
            paramList.add(paramMap.get("tznd"));
            hql.append(" and j.tznd = ?");
        }
        hql.append(" group by szcs");
        return baseDao.find(hql.toString(), paramList);
    }
    
    /**
      * @Title getZytzAndDftzGroupByXmlb 
      * @Description (根据投资年度、所在省份等条件，项目类别分组获取地方投资、中央投资) 
      * @param paramMap
      * @return 
      * @see com.ljdy.BHF.service.JbxxService#getZytzAndDftzGroupByXmlb(java.util.Map)
      * @Date  2017年10月19日
      * @修改历史  
      *     1. [2017年10月19日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getZytzAndDftzGroupByXmlb(Map<String, Object> paramMap) {
        StringBuffer hql=new StringBuffer("select new map(sum(j.dftz) as dftz, sum(j.zytz) as zytz, j.xmlb as xmlb) from Jbxx j where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(paramMap, "szsf")){
            hql.append(" and j.szsf like ?");
            param.add("%" + paramMap.get("szsf") + "%");
        }
        if(CommonUtil.validCondition(paramMap, "szcs")){
            hql.append(" and j.szcs like ?");
            param.add("%" + paramMap.get("szcs") + "%");
        }
        if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and j.bxxm = ?");
            param.add(paramMap.get("bxxm"));
        }
        if(CommonUtil.validCondition(paramMap, "tznd")){
            hql.append(" and j.tznd =?");
            param.add(paramMap.get("tznd"));
        }
        hql.append(" group by j.xmlb");
        return baseDao.find(hql.toString(), param);
    }
    
    /**
      * @Title toImplement 
      * @Description (转入实施) 
      * @param ids
      * @param xmzl 
      * @see com.ljdy.BHF.service.JbxxService#toImplement(java.lang.String, java.lang.String)
      * @Date  2017年10月20日
      * @修改历史  
      *     1. [2017年10月20日]创建文件 by 顾冲 
     *
     */
    @Override
    public void toImplement(String ids, String xmzl) {
        String [] idsArr = ids.split(",");
        List<Object> param = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("update Jbxx set jszt = '待实施',bxxm = '否' where");
        for (int i = 0; i < idsArr.length; i++) {
            if(i != idsArr.length - 1){
                hql.append(" id = ? or");
                param.add(idsArr[i]);
            }else{
                hql.append(" id = ?");
                param.add(idsArr[i]);
            }
        }
        baseDao.executeHql(hql.toString(), param);
    }
    
    /**
     * 根据省份、投资年度获取中央投资、地方投资、总投资
     * @param condition
     * @return
     */
	@Override
	public Object getZytzAndDftzBySzsf(Map<String, Object> condition) {
		StringBuffer hql = new StringBuffer("select new map(sum(j.zytz) as zytz,sum(j.dftz) as dftz,sum(j.zytz+j.dftz) as ztz) from Jbxx j where j.bxxm='是'"); 
		List<Object> param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hql.append(" and j.szsf like ?");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "tznd")){
			hql.append(" and j.tznd = ?");
			param.add(condition.get("tznd"));
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
		    hql.append(" and j.bxxm = ?");
		    param.add(condition.get("bxxm"));
		}
		return baseDao.get(hql.toString(), param);
	}
	
	/**
	 * 监督实施模块饼状图根据省份查询数据
	 */
	@Override
	public List<Object> getPieFindProvice(Map<String, Object> conditionPie) {
		String hql = "";
		if(CommonUtil.validCondition(conditionPie, "partTag")){
			if("3".equals(conditionPie.get("partTag"))){
				 hql ="select new map(j.jszt as jszt ,sum(j.zytz+j.dftz) as all_tz) from Jbxx j where 1=1 ";
			}else if("4".equals(conditionPie.get("partTag"))){
				 hql ="select new map(j.syzt as syzt ,sum(j.zytz+j.dftz) as all_tz) from Jbxx j where 1=1 ";
			}
		}
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(conditionPie, "szsf")){
			hqlStr.append(" and j.szsf like ? ");
			param.add("%"+conditionPie.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(conditionPie, "szcs")){
			hqlStr.append(" and j.szcs like ? ");
			param.add("%"+conditionPie.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(conditionPie, "bxxm")){
			hqlStr.append(" and j.bxxm = ? ");
			param.add(conditionPie.get("bxxm"));
		}
		if(CommonUtil.validCondition(conditionPie, "partTag")){
			if("3".equals(conditionPie.get("partTag"))){
				hqlStr.append(" and j.jszt is not null and j.syzt is null group by j.jszt ");
			}else if("4".equals(conditionPie.get("partTag"))){
				hqlStr.append(" and j.jszt ='已审计' and j.syzt in('良好','损坏','废弃') group by j.syzt ");
			}
		}
		
		return baseDao.find(hqlStr.toString(), param);
	}
	@Override
	public List<Object> getXmlb() {
		
		List<Object> list = baseDao.find("select new map(d.codeName as xmlb) from DICT_GY d where d.typeName = '建设状态' order by orderCode");
        List<Object> resultList = new ArrayList<Object>();
        for (Object object : list) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) object;
            resultList.add(map.get("xmlb"));
        }
        return resultList;
	}
	
	/**
	 * 根据使用状态分组统计中央投资和地方投资
	 * @return List<Object>
	 * @date [2017-11-23] 创建文件  by 郎川
	 */
	@Override
	public List<Object> getsyzt() {
		List<Object> list = baseDao.find("select new map(d.codeName as xmlb) from DICT_GY d where d.typeName = '使用状态' order by orderCode");
        List<Object> resultList = new ArrayList<Object>();
        for (Object object : list) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) object;
            resultList.add(map.get("xmlb"));
        }
        return resultList;
	}

	

	/**
     * @Title getXmlb
     * @Description (获取项目类别)
     * @return
     * @Return List<Object> 返回类型
     * @Throws
     * @Date 2017年10月13日
     * @修改历史 1. [2017年10月9日]创建文件 by 郎川
     */
    public List<Object> getXmlbData() {
        List<Object> list = baseDao.find("select new map(d.codeName as xmlb) from DICT_GY d where d.typeName = '项目类别' order by orderCode");
        List<Object> resultList = new ArrayList<Object>();
        for (Object object : list) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) object;
            resultList.add(map.get("xmlb"));
        }
        return resultList;
    }

	
	/**
	  * @Title getTzByArea 
	  * @Description (根据地区获取投资) 
	  * @return 
	  * @see com.ljdy.BHF.service.JbxxService#getTzByArea()
	  * @Date  2017年11月13日
	  * @修改历史  
	  *     1. [2017年11月13日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public Map<String, Object> getTzByArea(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(sum(j.zytz + j.dftz) as dxtz, sum(j.zytz) as zytz, sum(j.dftz) as dftz) from Jbxx j where 1=1");
        List<Object>param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and j.bxxm = ? ");
            param.add(condition.get("bxxm"));
        }
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and j.tznd = ? ");
            param.add(condition.get("tznd"));
        }
        if(CommonUtil.validCondition(condition, "szsf")){
            hql.append(" and j.szsf like ? ");
            param.add("%" + condition.get("szsf") + "%");
        }
        if(CommonUtil.validCondition(condition, "szcs")){
            hql.append(" and j.szcs like ? ");
            param.add("%" + condition.get("szcs") + "%");
        }
        List<Object> list = baseDao.find(hql.toString(), param);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        for(Object obj : list){
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>)obj;
            Object dxtz = map.get("dxtz") == null ? 0 : map.get("dxtz");
            Object zytz = map.get("zytz") == null ? 0 : map.get("zytz");
            Object dftz = map.get("dftz") == null ? 0 : map.get("dftz");
            resultMap.put("dxtz", dxtz);
            resultMap.put("zytz", zytz);
            resultMap.put("dftz", dftz);
        }
        return resultMap;
    }

    
    /**
     * 根据项目类别获取建设投资数据
     * @param  xmlbCondition
     * @return  List<Object>
     * @date [2017-11-13] 创建文件 by 郎川
     */
	@Override
	public List<Object> getJstzGroupByXmlb(Map<String, Object> paramMap) {
		
		 //角色编号必传,省级用户必传所在省份
        String roleName = (String) paramMap.get("roleName"); 
        StringBuffer hql=new StringBuffer("select new map(sum(j.zytz + j.dftz) as dxtz, j.xmlb as xmlb) from Jbxx j where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and j.bxxm = ?");
            param.add(paramMap.get("bxxm"));
        }
       if(CommonUtil.validCondition(paramMap, "partTag")){
        	if("3".equals(paramMap.get("partTag"))){
        		hql.append(" and j.jszt is not null and j.syzt is null ");
        	}else if("4".equals(paramMap.get("partTag"))){
				hql.append(" and j.jszt ='已审计' and j.syzt is not null ");
			}
        }
       if (CommonUtil.validCondition(paramMap, "partTag")) {
    	   if ("3".equals(paramMap.get("partTag")) || "4".equals(paramMap.get("partTag"))) {
    		   if ("1".equals(roleName)) {
    			   if(paramMap.get("szsf")!=null && !paramMap.get("szsf").equals("null")){
    				   hql.append(" and j.szsf like ?");
    				   param.add("%" + paramMap.get("szsf") + "%");
    			   }
    		   } else if ("2".equals(roleName)) {
    			   if(paramMap.get("szcs")!=null && !paramMap.get("szcs").equals("null") ){
    				   hql.append(" and j.szcs like ? ");
    				   param.add("%" + paramMap.get("szcs") + "%");
    			   }
    			   hql.append(" and j.szsf like ? ");
    			   param.add("%"+paramMap.get("szsf")+"%");
    		   }
    	   }

       }
        hql.append(" group by j.xmlb");
        return baseDao.find(hql.toString(), param);
	}
	
	/**
	  * @Title getDataById 
	  * @Description (根据主键查询) 
	  * @param id
	  * @return 
	  * @see com.ljdy.BHF.service.JbxxService#getDataById(java.lang.String)
	  * @Date  2017年11月16日
	  * @修改历史  
	  *     1. [2017年11月16日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public Object getDataById(String id) {
        String hql = "select new map(j.xmmc as xmmc, j.id as id, j.syzt as syzt, j.xmbh as xmbh) from Jbxx j where j.id = ?";
        List<Object> param = new ArrayList<Object>();
        param.add(id);
        return baseDao.getUniqueResult(hql, param);
    }
    
    /**
     * 转入使用维护
     * @param id xmzl
     * @return  void
     * @date [2017-11-21] 创建文件 by 郎川
     */
	@Override
	public void toUseMaintenance(String id, String xmzl) {
		String []strId=id.split(",");
		List<Object>param = new ArrayList<Object>();
		StringBuffer hqlstr = new StringBuffer(" update Jbxx j set j.syzt='良好' where j.bxxm ='否' and ");
		for(int i=0;i<strId.length;i++){
			if(i!=strId.length-1){
				hqlstr.append("  j.id = ? or ");
                param.add(strId[i]);
            }else {
				hqlstr.append("  j.id = ? ");
				param.add(strId[i]);
			}
		}
		baseDao.executeHql(hqlstr.toString(), param);
	}

	@Override
	public void addJbxx(AddJbxx addJbxx) {
      		baseDao.save(addJbxx);
	}
	
	  /**
     * @Title deleteByIds 
     * @Description (根据主键删除,使用维护部分) 
     * @param modelName
     * @param ids
     * @param partTag 
     * @see com.ljdy.BHF.service.JbxxService#deleteByIds(java.lang.String, java.lang.String, java.lang.String)
     * @Date  2017年12月1日
     * @修改历史  
     *     1. [2017年12月1日]创建文件 by 顾冲 
    *
    */
   @Override
   public void deleteByIds(String modelName, String ids, String partTag) {
       String [] idsArr = ids.split(",");
       List<Object> xmidsList = new ArrayList<Object>();
       if("4".equals(partTag)){//先删除维护记录，在删除基本信息
           for(int i = 0; i < idsArr.length; i++){
               xmidsList.add(idsArr[i]);
           }
           if("AddJkz".equals(modelName)){
               List<Object> spqdIdsList =getIdsByJkz_ids("AddSpqd", idsArr);
               List<Object> xkzdIdsList =getIdsByJkz_ids("AddXkzd", idsArr);
               List<Object> csxlIdsList =getIdsByJkz_ids("AddCsxl", idsArr);
               List<Object> bjzzIdsList =getIdsByJkz_ids("AddBjzz", idsArr);
               List<Object> gdxtIdsList =getIdsByJkz_ids("AddGdxt", idsArr);
               for (Object obj : spqdIdsList) {
                   xmidsList.add(obj);
               }
               for (Object obj : xkzdIdsList) {
                   xmidsList.add(obj);
               }
               for (Object obj : csxlIdsList) {
                   xmidsList.add(obj);
               }
               for (Object obj : bjzzIdsList) {
                   xmidsList.add(obj);
               }
               for (Object obj : gdxtIdsList) {
                   xmidsList.add(obj);
               }
           }
           updateWhjlztByWxxmmid(xmidsList);
       }
      
       if("AddJkz".equals(modelName)){//监控站下面有视频前端、显控终端、传输线路、供电系统、报警装置,需要先删除视频前端、显控终端、传输线路、供电系统、报警装置，然后删除监控站
           StringBuffer spqdHql =new StringBuffer("delete from AddSpqd where");
           StringBuffer xkzdHql =new StringBuffer("delete from AddXkzd where");
           StringBuffer csxlHql =new StringBuffer("delete from AddCsxl where");
           StringBuffer bjzzHql =new StringBuffer("delete from AddBjzz where");
           StringBuffer gdxtHql =new StringBuffer("delete from AddGdxt where");
           List<Object> paramList = new ArrayList<Object>();
           for (int i = 0; i < idsArr.length; i++) {
               if(i != idsArr.length - 1){
                   spqdHql.append(" jkz_id = ? or");
                   xkzdHql.append(" jkz_id = ? or");
                   csxlHql.append(" jkz_id = ? or");
                   bjzzHql.append(" jkz_id = ? or");
                   gdxtHql.append(" jkz_id = ? or");
                   paramList.add(idsArr[i]);
               }else{
                   spqdHql.append(" jkz_id = ?");
                   xkzdHql.append(" jkz_id = ?");
                   csxlHql.append(" jkz_id = ?");
                   bjzzHql.append(" jkz_id = ?");
                   gdxtHql.append(" jkz_id = ?");
                   paramList.add(idsArr[i]);
               }
           }
           baseDao.executeHql(spqdHql.toString(), paramList);
           baseDao.executeHql(xkzdHql.toString(), paramList);
           baseDao.executeHql(csxlHql.toString(), paramList);
           baseDao.executeHql(bjzzHql.toString(), paramList);
           baseDao.executeHql(gdxtHql.toString(), paramList);
       }
       List<Object> param = new ArrayList<Object>();
       StringBuffer hql = new StringBuffer("delete from " + modelName + " where");
       for (int i = 0; i < idsArr.length; i++) {
           if(i != idsArr.length - 1){
               hql.append(" id = ? or");
               param.add(idsArr[i]);
           }else{
               hql.append(" id = ?");
               param.add(idsArr[i]);
           }
           xmidsList.add(idsArr[i]);
       }
       baseDao.executeHql(hql.toString(), param);
       StringBuffer deleteJbxxHql=new StringBuffer("delete from AddJbxx t where");
       List<Object> jbxxParam = new ArrayList<Object>();
       for (int i = 0; i < xmidsList.size(); i++) {
           if(i != xmidsList.size() - 1){
               deleteJbxxHql.append(" t.id=? or");
               jbxxParam.add(xmidsList.get(i));
           }else{
               deleteJbxxHql.append(" t.id=?");
               jbxxParam.add(xmidsList.get(i));
           }
       }
       baseDao.executeHql(deleteJbxxHql.toString(), jbxxParam);
   }
   
   /**
     * @Title deleteWhjlByXmid 
     * @Description (这里用一句话描述重构方法的作用) 
     * @param condition 
     * @see com.ljdy.BHF.service.JbxxService#deleteWhjlByXmid(java.util.List)
     * @Date  2017年12月1日
     * @修改历史  
     *     1. [2017年12月1日]创建文件 by 顾冲 
    *
    */
   public void updateWhjlztByWxxmmid(List<Object> condition) {
       if(condition != null && !condition.isEmpty()){
           StringBuffer hql = new StringBuffer("update xmwhjl set whjlzt = '已删除' where 1=1 and ");
           List<Object> param = new ArrayList<Object>();
           for(int i = 0; i < condition.size(); i++){
               if(i != condition.size() - 1){
                   hql.append(" wxxmid = ? or");
                   param.add(condition.get(i));
               }else{
                   hql.append(" wxxmid = ? ");
                   param.add(condition.get(i));
               }
           }
           baseDao.updateBySql(hql.toString(),param);
       }
   }
   
   /**
    * @Title getIdsByJkz_ids
    * @Description (根据jkz_ids获取“监控站”子类项目的id) 
    * @param modelName
    * @param jkz_idsArr
    * @return
    * @Return List<Object> 返回类型 
    * @Throws 
    * @Date  2017年12月1日
    * @修改历史  
    *     1. [2017年12月1日]创建文件 by 顾冲
    */
   public List<Object> getIdsByJkz_ids(String modelName, String [] jkz_idsArr){
       if(jkz_idsArr == null || jkz_idsArr.length == 0){
           return new ArrayList<Object>();
       }
       //Spqd、Xkzd、Csxl、Bjzz、Gdxt
       StringBuffer hql = new StringBuffer("select id from " + modelName + " where 1=1 and ");
       List<Object> param = new ArrayList<Object>();
       for (int i = 0; i < jkz_idsArr.length; i++) {
           if(i != jkz_idsArr.length -1 ){
               hql.append(" jkz_id = ? or");
               param.add(jkz_idsArr[i]);
           }else{
               hql.append(" jkz_id = ?");
               param.add(jkz_idsArr[i]);
           }
       }
       return baseDao.find(hql.toString(), param);
   }
   
   /**
    * 查询省下面的市
    * @param codeName
    * @return List<Object>
    * @date [2017-12-5] 创建文件  by 郎川
    */
	@Override
	public List<Object> findCity(String codeName) {
		String hql ="select codeName from DICT_AREA t where t.superCode in(select codeValue from DICT_AREA t where t.codeName like ?)";
		Object[]obj = {codeName};
		return baseDao.find(hql, obj);
	}
	
	@Override
	public void updateJbxx(String sql, Object[] param) {
		baseDao.updateBySql(sql, param);
	}
    
	@Override
	public void update(AddJbxx jbxx) {
	     baseDao.update(jbxx);	
	}
	
	/**
	 * @Title getSywhDataByCondition 
	 * @Description (获取使用维护信息) 
	 * @return 
	 * @see com.ljdy.BHF.service.JbxxService#getSywhDataByCondition()
	 * @Date  2017年12月8日
	 * @修改历史  
	 *     1. [2017年12月8日]创建文件 by 顾冲 
	*
	*/
	@Override
	public List<Object> getSywhData() {
	   StringBuffer hql = new StringBuffer("select new map(j.id as id, j.xmzl as xmzl, j.xmbh as xmbh) from Jbxx j where "
	           + "j.bxxm = '否' and j.syzt in('良好', '废弃', '损坏')");
	   return baseDao.find(hql.toString());
	}
	
	/**
	  * @Title getDataByIds 
	  * @Description (根据ids获取信息) 
	  * @param idsArr
	  * @return 
	  * @see com.ljdy.BHF.service.JbxxService#getDataByIds(java.lang.String[])
	  * @Date  2017年12月15日
	  * @修改历史  
	  *     1. [2017年12月15日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public List<Object> getDataByIds(String[] idsArr) {
        if(idsArr == null || idsArr.length == 0){
            return new ArrayList<Object>();
        }
        StringBuffer hql = new StringBuffer("select new map(j.jszt as jszt) from Jbxx j where id in(");
        List<Object> param = new ArrayList<Object>();
        for (int i = 0; i < idsArr.length; i++) {
            if(i < idsArr.length -1){
                hql.append("?, ");
                param.add(idsArr[i]);
            }else{
                hql.append("?");
                param.add(idsArr[i]);
            }
        }
        hql.append(")");
        return baseDao.find(hql.toString(), param);
    }
    
    /**
      * @Title updateBySql 
      * @Description (sql语句更新)
      * @param sql
      * @param param 
      * @see com.ljdy.BHF.service.JbxxService#updateBySql(java.lang.String, java.util.List)
      * @Date  2017年12月18日
      * @修改历史  
      *     1. [2017年12月18日]创建文件 by 顾冲 
     *
     */
    @Override
    public void updateBySql(String sql, Object [] param) {
        baseDao.updateBySql(sql, param);
    }
    /**
     * @Title getIdsByJkz_ids 
     * @Description (根据jkz_id查询子类项目的id) 
     * @param jkz_idArr
     * @return
     * @Return Set<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月19日
     * @修改历史  
     *     1. [2017年12月19日]创建文件 by 顾冲
     */
    public Set<Object> getIdsByJkz_ids(Object [] jkz_idArr){
        Set<Object> resultSet = new HashSet<Object>();
        if(jkz_idArr == null || jkz_idArr.length == 0){
            return resultSet;
        }
        StringBuffer xkzdHql = new StringBuffer("select distinct id from Xkzd where jkz_id in(");
        StringBuffer spqdHql = new StringBuffer("select distinct id from Spqd where jkz_id in(");
        StringBuffer csxlHql = new StringBuffer("select distinct id from Csxl where jkz_id in(");
        StringBuffer bjzzHql = new StringBuffer("select distinct id from Bjzz where jkz_id in(");
        StringBuffer gdxtHql = new StringBuffer("select distinct id from Gdxt where jkz_id in(");
        List<Object> param = new ArrayList<Object>();
        for (int i = 0; i < jkz_idArr.length; i++) {
            if(i < jkz_idArr.length -1){
                xkzdHql.append("?, ");
                spqdHql.append("?, ");
                csxlHql.append("?, ");
                bjzzHql.append("?, ");
                gdxtHql.append("?, ");
            }else{
                xkzdHql.append("?");
                spqdHql.append("?");
                csxlHql.append("?");
                bjzzHql.append("?");
                gdxtHql.append("?");
            }
            param.add(jkz_idArr[i]);
        }
        xkzdHql.append(")");
        spqdHql.append(")");
        csxlHql.append(")");
        bjzzHql.append(")");
        gdxtHql.append(")");
        List<Object> xkzdList = baseDao.find(xkzdHql.toString(), param);
        List<Object> spqdList = baseDao.find(spqdHql.toString(), param);
        List<Object> csxlList = baseDao.find(csxlHql.toString(), param);
        List<Object> bjzzList = baseDao.find(bjzzHql.toString(), param);
        List<Object> gdxtList = baseDao.find(gdxtHql.toString(), param);
        for (Object object : xkzdList) {
            resultSet.add(object);
        }
        for (Object object : spqdList) {
            resultSet.add(object);
        }
        for (Object object : csxlList) {
            resultSet.add(object);
        }
        for (Object object : bjzzList) {
            resultSet.add(object);
        }
        for (Object object : gdxtList) {
            resultSet.add(object);
        }
        return resultSet;
    }
    
    /**
      * @Title updateSyztByXmid 
      * @Description (根据xmid修改syzt) 
      * @param xmid
      * @param syzt
      * @return 
      * @see com.ljdy.BHF.service.JbxxService#updateSyztByXmid(java.lang.String, java.lang.String)
      * @Date  2017年12月25日
      * @修改历史  
      *     1. [2017年12月25日]创建文件 by 顾冲 
     *
     */
    @Override
    public int updateSyztByXmid(String xmid, String syzt) {
        return baseDao.updateBySql("update jbxx set syzt = ? where id = ? ", new Object[]{syzt, xmid});
    }
    
}
