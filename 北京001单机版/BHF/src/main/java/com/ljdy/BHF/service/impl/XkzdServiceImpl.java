package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.AddXkzd;
import com.ljdy.BHF.model.Xkzd;
import com.ljdy.BHF.service.XkzdService;
import com.ljdy.BHF.utils.BeanToMap;
import com.ljdy.BHF.utils.CommonUtil;

/**
 * @ClassName XkzdService 
 * @Description (指挥监控设施----显控终端service实现类) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月26日 上午11:08:32 
 * @修改历史  
 *     1. [2017年9月26日]创建文件 by 顾冲
 */
@Service("xkzdService")
public class XkzdServiceImpl implements XkzdService{
    @Resource
    private BaseDao<Object> baseDao;
    /**
      * @Title getJsgm 
      * @Description (获取建设规模) 
      * @param paramMap
      * @return 
      * @see com.ljdy.BHF.service.XkzdService#getJsgm(java.util.Map)
      * @Date  2017年9月26日
      * @修改历史  
      *     1. [2017年9月26日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getJsgm(Map<String, Object> paramMap) {
        //用户角色必传(1国家，2省级)，省级用户必传所在省份 
        String roleName = (String)paramMap.get("roleName");
        StringBuffer hql = new StringBuffer("select new map(count(*) as jsgm,x.jsxz as jsxz");
        List<Object> param = new ArrayList<Object>();
        if("2".equals(roleName)){
            hql.append(",x.szcs as szcs");
        }else{
            hql.append(",x.szsf as szsf");
        }
        hql.append(") from Xkzd x where 1=1");
        if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and x.bxxm = ?");
            param.add(paramMap.get("bxxm"));
        }
        if(CommonUtil.validCondition(paramMap, "tznd")){
            hql.append(" and x.tznd = ?");
            param.add(paramMap.get("tznd"));
        }
        if("2".equals(roleName)){
            hql.append(" and x.szsf = ?");
            param.add(paramMap.get("szsf"));
            //省级用户根据所在城市和建设性质分组
            hql.append(" group by x.szcs,x.jsxz");
        }else{
            //国家级用户根据所在省份和建设性质分组
            hql.append(" group by x.szsf,x.jsxz");
        }
        return baseDao.find(hql.toString(), param);
    }

    /**
     * @Title getTz 
     * @Description (获取投资) 
     * @param paramMap
     * @return 
     * @see com.ljdy.BHF.service.XkzdService#getTz(java.util.Map)
     * @Date  2017年9月28日
     * @修改历史  
     *     1. [2017年9月28日]创建文件 by 顾冲 
    *
    */
   @Override
   public List<Object> getTz(Map<String, Object> paramMap) {
       //用户角色必传(1国家，2省级)，省级用户必传所在省份
       String roleName = (String)paramMap.get("roleName");
       List<Object> param = new ArrayList<Object>(); 
       StringBuffer hql = new StringBuffer("select new map(sum(x.zytz+x.dftz) as dxtz, sum(x.dftz) as dftz, sum(x.zytz) as zytz, x.jsxz as jsxz) from Xkzd x where 1=1");
       if(CommonUtil.validCondition(paramMap, "bxxm")){
           hql.append(" and x.bxxm = ?");
           param.add(paramMap.get("bxxm"));
       }
       if("2".equals(roleName)){
           hql.append(" and x.szsf = ?");
           param.add(paramMap.get("szsf"));
       }
       if(CommonUtil.validCondition(paramMap, "tznd")) {
           param.add(paramMap.get("tznd"));
           hql.append(" and x.tznd = ?");
       }
       hql.append(" group by x.jsxz");
       return baseDao.find(hql.toString(), param);
   }

    /**
     * 新增显控终端
     * @param xkzd
     * 修改历史：
	 *		[2017年9月28日]创建文件	by 徐成
     */
	@Override
	public void addXkzd(Xkzd xkzd) {
		baseDao.save(xkzd);
	}
	/**
	 * 新增显控终端数据查询
	 * @param condition page  rows
	 * @return List<Object>
	 * @date [2017-09-29] by 创建文件 郎川
	 */

	@Override
	public List<Object> getDataByCondition(Map<String, Object> condition,
			int page, int rows) {
		String str="select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jkz_id as jkz_id,z.fzdd as fzdd,z.csfs as csfs,z.jkz_name as jkz_name) from Xkzd z where 1=1";
		List<Object> param =  new ArrayList<Object>();
		if(page==0 || rows==0){
			//统计数据总条数sql语句
			str="select count(*) from Xkzd z where 1=1";
		}
		StringBuffer hql = new StringBuffer(str);
		if(CommonUtil.validCondition(condition, "bxxm")){
		    hql.append(" and z.bxxm = ?");
		    param.add(condition.get("bxxm"));
		}
		
		//设施类型
		if(CommonUtil.validCondition(condition, "sslx")){
			hql.append(" and z.sslx = ? ");
			param.add(condition.get("sslx"));
		}
		
		//边界方向
		if(CommonUtil.validCondition(condition, "bjfx")){
			hql.append(" and z.bjfx like ?");
			param.add("%"+condition.get("bjfx")+"%");
		}
		
		//地形类别
		if(CommonUtil.validCondition(condition, "dxlb")){
			hql.append(" and z.dxlb like ?");
			param.add("%"+condition.get("dxlb")+"%");
		}
		
		//投资年度
		if(CommonUtil.validCondition(condition, "tznd")){
			hql.append(" and z.tznd = ? ");
			param.add(condition.get("tznd"));
		}
		//建设性质
		if(CommonUtil.validCondition(condition, "jsxz")){
			hql.append(" and z.jsxz = ? ");
			param.add(condition.get("jsxz"));
		}
		
		//所在省
		if(CommonUtil.validCondition(condition, "szsf")){
			hql.append(" and z.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		//所在市(区)
		if(CommonUtil.validCondition(condition, "szcs")){
			hql.append(" and z.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
			
		}
		/**
		 * 添加模糊查询条件 建设状态 jszt
		 */
		if(CommonUtil.validCondition(condition, "jszt")){
			hql.append(" and z.jszt = ?");
			param.add(condition.get("jszt"));
		}
		//使用状态查询
		if(CommonUtil.validCondition(condition, "syzt")){
			hql.append(" and z.syzt = ? ");
			param.add(condition.get("syzt"));
		}
		if(CommonUtil.validCondition(condition, "partTag")){
			if("3".equals(condition.get("partTag"))){
				hql.append(" and z.syzt is null ");
			}else if("4".equals(condition.get("partTag"))){
				hql.append(" and z.jszt ='已审计' and z.syzt in('良好','损坏','废弃') ");
			}
		}
		hql.append(" order by z.time desc");
	    return baseDao.find(hql.toString(), param);
	}

	/**
	 *根据主键id查询详情数据
	 *@param id
	 *@return Object
	 *@date [2017-10-09] 创建文件 by  郎川
	 */
	@Override
	public Object getDataById(String id) {
		String hql= "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jkz_id as jkz_id,z.fzdd as fzdd,z.csfs as csfs,z.jkz_name as jkz_name) from Xkzd z where 1=1 and z.id = ? ";
		Object[] objParam ={id};
		return baseDao.get(hql.toString(), objParam);
		
	}

	/**
	 * 更新显控终端数据
	 * @param xkzd
	 * @return void 
	 * @date [2017-10-11] 创建文件 by 郎川
	 */
	@Override
	public void updateXkzd(Xkzd xkzd) {
		baseDao.update(xkzd);
	}
	
	/**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition){
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jkz_id as jkz_id,z.fzdd as fzdd,z.csfs as csfs,z.jkz_name as jkz_name) from Xkzd z where 1=1");
		List<Object> param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and z.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
		if(CommonUtil.validCondition(condition, "szsf")){
			hql.append(" and z.szsf =?");
			param.add(condition.get("szsf"));
		}
		if(CommonUtil.validCondition(condition, "tznd")){
			hql.append(" and z.tznd =?");
			param.add(condition.get("tznd"));
		}
		if("4".equals(condition.get("partTag"))){
		    hql.append(" and z.syzt in('良好','损坏','废弃')");
		}
		if("3".equals(condition.get("partTag"))){
		    hql.append(" and z.syzt is null");
		}
		return baseDao.find(hql.toString(), param);
	}

	
	 /**
     *显控终端根据建设性质统计个数
     * @param condition
     * @return List<Object>
     * @date [2017-10-23] 创建文件 by 郎川
     */
	@Override
	public List<Object> getCountByJsxz(Map<String, Object> condition) {
		String hql="select count(*) as count from Xkzd x where 1=1 and x.jsxz = ? ";
		Object[]objParam={condition.get("jsxz")};
		return baseDao.find(hql.toString(), objParam);
	}
	
	/**
	 * 监控站 求和中央投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 * 
	 */
	@Override
	public List<Object> getZytzSum() {
		String hql="select sum(zytz) from Xkzd";
		return baseDao.find(hql);
	}
	
	/**
	 * 监控站 求和地方投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDftzSum() {
		String hql="select sum(dftz) from Xkzd";
		return baseDao.find(hql);
	}
    

	
	/**
	  * @Title getData 
	  * @Description (省级报表数据) 
	  * @param condition
	  * @return 
	  * @see com.ljdy.BHF.service.XkzdService#getData(java.util.Map)
	  * @Date  2017年10月23日
	  * @修改历史  
	  *     1. [2017年10月23日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public List<Object> getData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jkz_id as jkz_id,z.fzdd as fzdd,z.csfs as csfs,z.jkz_name as jkz_name) from Xkzd z where 1=1 ");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "szsf")){
            hql.append(" and z.szsf like ? ");
            param.add("%"+condition.get("szsf")+"%");
        }
        if(CommonUtil.validCondition(condition, "szcs")){
            hql.append(" and z.szcs like ? ");
            param.add("%"+condition.get("szcs")+"%");
        }
        if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and z.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
        if(CommonUtil.validCondition(condition, "jsxz")){
            hql.append(" and z.jsxz =? ");
            param.add(condition.get("jsxz"));
        }
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and z.tznd =?");
            param.add(condition.get("tznd"));
        }
        List<Object> list = baseDao.find(hql.toString(),param);
		List<Object> result = new ArrayList<Object>();
		for(Object obj:list){
			@SuppressWarnings("unchecked")
            Map<String,Object> map = (Map<String,Object>)obj;
			result.add(BeanToMap.transMapToBean(map, Xkzd.class));
		}
		return result;
       
    }

    /**
     * 实施监督管理页面 数据查询
     * @param condition
     * @return List<Object>
     * @date [2017-11-8] 创建文件 by 郎川
     */
	@Override
	public List<Object> getManageData(Map<String, Object> condition) {
		List<Object>param = new ArrayList<Object>();
		String hql=" select new map(x.jszt as jszt   ,count(*) as jsgm) from Xkzd x where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and x.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and x.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and x.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and x.jszt is not null and x.syzt is null group by x.jszt ");
		
		return baseDao.find(hqlStr.toString(), param);
	}

	/**
	 * 实施监督管理建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件  by 郎川
	 */
	@Override
	public List<Object> getManageTz(Map<String, Object> condition) {
		String hql = "select new map(sum(x.zytz) as zytz , sum(x.dftz) as dftz, sum(x.dftz + x.zytz) as dxtz) from Xkzd x where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and x.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and x.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and x.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and x.jszt is not null and x.syzt is null ");
		return baseDao.find(hqlStr.toString(), param);
	}
	
	/**
	  * @Title countByJzk_id 
	  * @Description (根据jkz_id、sblx等条件统计条数) 
	  * @param condition
	  * @return 
	  * @see com.ljdy.BHF.service.XkzdService#countByCondition(Map<String, Object>)
	  * @Date  2017年11月20日
	  * @修改历史  
	  *     1. [2017年11月20日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public Long countByCondition(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select count(*) from Xkzd where 1 = 1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "jkz_id")){
            hql.append(" and jkz_id = ? ");
            param.add(condition.get("jkz_id"));
        }
        List<Object> list = baseDao.find(hql.toString(), param);
        return (Long)list.get(0);
    }
    
    /**
      * @Title getJkz_idsByPrimaryKeys
      * @Description (根据主键字符串查询) 
      * @param idsStr
      * @return 
      * @see com.ljdy.BHF.service.XkzdService#getJkz_idsByPrimaryKeys(java.lang.String)
      * @Date  2017年11月21日
      * @修改历史  
      *     1. [2017年11月21日]创建文件 by 顾冲 
     *
     */
   @Override
   public List<Object> getJkz_idsByPrimaryKeys(String idsStr) {
       StringBuffer hql = new StringBuffer("select new map(jkz_id as jkz_id) from Xkzd where");
       String [] idsArr = idsStr.split(",");
       List<Object> param = new ArrayList<Object>();
       for (int i = 0; i < idsArr.length; i++) {
           if(i != idsArr.length - 1){
               hql.append(" id = ? or");
               param.add(idsArr[i]);
           }else{
               hql.append(" id = ?");
               param.add(idsArr[i]);
           }
       }
       return baseDao.find(hql.toString(), param);
   }

   /**
    * 使用维护模块首页数据查询
    * @param condition
    * @return List<Object> 
    * @date [2017-11-22] 创建文件 by 郎川
    */
	@Override
	public List<Object> getMaintenanceData(Map<String, Object> condition) {
		List<Object>param = new ArrayList<Object>();
		String hql=" select new map(x.syzt as syzt   ,count(*) as jsgm) from Xkzd x where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and x.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and x.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and x.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and x.jszt ='已审计' and x.syzt in ('良好','损坏','废弃') group by x.syzt ");
		
		return baseDao.find(hqlStr.toString(), param);
	}
	
	/**
	 * 使用维护模块首页数据建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getMaintenanceTz(Map<String, Object> condition) {
		String hql = "select new map(sum(x.zytz) as zytz , sum(x.dftz) as dftz, sum(x.dftz + x.zytz) as dxtz) from Xkzd x where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and x.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and x.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and x.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and x.jszt ='已审计' and x.syzt in ('良好','损坏','废弃') ");
		return baseDao.find(hqlStr.toString(), param);
	}
	
	/**
	  * @Title getJkz_idByIds 
	  * @Description (通过id获取jkz_id) 
	  * @param idsArr
	  * @return 
	  * @see com.ljdy.BHF.service.XkzdService#getJkz_idByIds(java.lang.String[])
	  * @Date  2017年12月4日
	  * @修改历史  
	  *     1. [2017年12月4日]创建文件 by 顾冲 
	 *
	 */
   @Override
   public List<Object> getJkz_idByIds(String[] idsArr) {
       if(idsArr == null || idsArr.length == 0){
           return new ArrayList<Object>();
       }
       StringBuffer sql = new StringBuffer("select distinct jkz_id from xkzd where id in (");
       List<Object> param = new ArrayList<Object>();
       for (int i = 0; i < idsArr.length; i++) {
           if(i < idsArr.length - 1){
               sql.append("?, ");
               param.add(idsArr[i]);
           }else{
               sql.append("?");
               param.add(idsArr[i]);
           }
       }
       sql.append(")");
       return baseDao.queryBySql(sql.toString(), param);
   }
   
    /**
     * @Title getDestroyData
     * @Description (获取损坏项目信息)
     * @param condition
     * @return
     * @see com.ljdy.BHF.service.XkzdService#getDestroyData(java.util.Map)
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDestroyData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(x.id as id, x.xmmc as xmmc, x.xmbh as xmbh, x.syzt as syzt) from Xkzd x where 1=1");
        List<Object> param = new ArrayList<Object>();
        if (CommonUtil.validCondition(condition, "id")) {
            hql.append(" and x.id = ? ");
            param.add(condition.get("id"));
        }else{
            if (CommonUtil.validCondition(condition, "szsf")) {
                hql.append(" and x.szsf like ? ");
                param.add("%" + condition.get("szsf") + "%");
            }
            if (CommonUtil.validCondition(condition, "syzt")) {
                hql.append(" and x.syzt = ? ");
                param.add(condition.get("syzt"));
            }
        }
        return baseDao.find(hql.toString(), param);
    }
	@Override
	public void addXkzd(AddXkzd addXkzd) {
		baseDao.save(addXkzd);
		
	}
	
	/**
	  * @Title getXkzdByCondition 
	  * @Description (根据jkz_id获取显控终端数据) 
	  * @param condition
	  * @return 
	  * @see com.ljdy.BHF.service.XkzdService#getXkzdByCondition(java.util.Map)
	  * @Date  2018年1月6日
	  * @修改历史  
	  *     1. [2018年1月6日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public List<Object> getXkzdByCondition(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,"
                + "z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,"
                + "z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,"
                + "z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,"
                + "z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jkz_id as jkz_id,z.fzdd as fzdd,z.csfs as csfs,"
                + "z.jkz_name as jkz_name) from Xkzd z where z.jkz_id = ?");
        List<Object> param = new ArrayList<Object>();
        param.add(condition.get("jkz_id"));
        //设施类型
        if(CommonUtil.validCondition(condition, "sslx")){
            hql.append(" and z.sslx = ? ");
            param.add(condition.get("sslx"));
        }
        //边界方向
        if(CommonUtil.validCondition(condition, "bjfx")){
            hql.append(" and z.bjfx like ? ");
            param.add("%"+condition.get("bjfx")+"%");
        }
        
        //地形类别
        if(CommonUtil.validCondition(condition, "dxlb")){
            hql.append(" and z.dxlb  like ? ");
            param.add("%"+condition.get("dxlb")+"%");
        }
        
        //建设性质
        if(CommonUtil.validCondition(condition, "jsxz")){
            hql.append(" and z.jsxz = ? ");
            param.add(condition.get("jsxz"));
        }
        
        //投资年度
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and z.tznd = ? ");
            param.add(condition.get("tznd"));
        }
        //使用状态
        if(CommonUtil.validCondition(condition, "syzt")){
            hql.append(" and z.syzt = ? ");
            param.add(condition.get("syzt"));
        }
        //建设状态
        if(CommonUtil.validCondition(condition, "jszt")){
            hql.append(" and z.jszt = ? ");
            param.add(condition.get("jszt"));
        }
        return baseDao.find(hql.toString(), param);
    }
}
