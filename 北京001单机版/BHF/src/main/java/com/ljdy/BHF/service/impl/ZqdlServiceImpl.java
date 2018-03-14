package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.AddZqdl;
import com.ljdy.BHF.model.Zqdl;
import com.ljdy.BHF.service.ZqdlService;
import com.ljdy.BHF.utils.BeanToMap;
import com.ljdy.BHF.utils.CommonUtil;
/**
 * @ClassName ZqdlServiceImpl 
 * @Description (执勤道路service实现类) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月25日 上午11:42:13 
 * @修改历史  
 *     1. [2017年9月25日]创建文件 by 顾冲
 */
@Service("zqdlService")
public class ZqdlServiceImpl implements ZqdlService{
    @Resource
    private BaseDao<Object> baseDao;
    
    /**
      * @Title getJsgm 
      * @Description (获取建设规模) 
      * @param paramMap
      * @return 
      * @see com.ljdy.BHF.service.ZqdlService#getJsgm(java.util.Map)
      * @Date  2017年9月25日
      * @修改历史  
      *     1. [2017年9月25日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getJsgm(Map<String, Object> paramMap) {
        //用户角色必传(1国家，2省级)，省级用户必传所在省份 
        String roleName = (String)paramMap.get("roleName");
        StringBuffer hql = new StringBuffer("select new map(sum(z.jsgm) as jsgm,z.jsxz as jsxz");
        if("2".equals(roleName)){
            hql.append(",z.szcs as szcs");
        }else{
            hql.append(",z.szsf as szsf");
        }
        hql.append(") from Zqdl z where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and z.bxxm = ?");
            param.add(paramMap.get("bxxm"));
        }
        if(CommonUtil.validCondition(paramMap, "tznd")){
            hql.append(" and z.tznd = ?");
            param.add(paramMap.get("tznd"));
        }
        if("2".equals(roleName)){
            //省级 根据所在城市和建设性质分组
            hql.append(" and z.szsf = ?");
            param.add(paramMap.get("szsf"));
            hql.append(" group by z.szcs,z.jsxz");
        }else{
            //国家级根据所在省份和建设性质分组
            hql.append(" group by z.szsf,z.jsxz");
        }
        return baseDao.find(hql.toString(), param);
    }
    /**
     * @Title getTz 
     * @Description (获取投资) 
     * @param paramMap
     * @return 
     * @see com.ljdy.BHF.service.ZqdlService#getTz(java.util.Map)
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
       StringBuffer hql = new StringBuffer("select new map(sum(z.zytz+z.dftz) as dxtz, sum(z.dftz) as dftz, sum(z.zytz) as zytz, z.jsxz as jsxz) from Zqdl z where 1=1");
       if(CommonUtil.validCondition(paramMap, "bxxm")){
           hql.append(" and z.bxxm = ?");
           param.add(paramMap.get("bxxm"));
       }
       if("2".equals(roleName)){
           hql.append(" and z.szsf = ?");
           param.add(paramMap.get("szsf"));
       }
       if(CommonUtil.validCondition(paramMap, "tznd")) {
           param.add(paramMap.get("tznd"));
           hql.append(" and z.tznd = ?");
       }
       hql.append(" group by z.jsxz");
       return baseDao.find(hql.toString(), param);
   }

    /**
     * 新增执勤道路路
     * @param zqdl
     * 修改历史：
     * 		[2017年9月28日]创建文件 by 徐成
     */
	@Override
	public void addZqdl(Zqdl zqdl) {
		baseDao.save(zqdl);
	}

	/**
     * 获取所有数据
     * @return
     */
	@Override
	public List<Object> getAllData() {
		String hql = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.dllb as dllb,z.dllx as dllx,z.dldj as dldj,z.ljlx as ljlx,z.lmlx as lmlx) from Zqdl z";
		return baseDao.find(hql);
	}
	
	/**
     * 多条件查询数据
     * @param condition		查询条件集合
     * @return
     */
	@Override
	public List<Object> getDataByCondition(Map<String, Object> condition,int page,int rows) {
	    String str = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.dllb as dllb,z.dllx as dllx,z.dldj as dldj,z.ljlx as ljlx,z.lmlx as lmlx) from Zqdl z where 1=1";
        if(page == 0 || rows ==0){
            str = "select count(*) from Zqdl z where 1=1";
        }
        StringBuffer hql = new StringBuffer(str);
		List<Object> param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and z.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
		//设施类型
		if(CommonUtil.validCondition(condition,"sslx")){
			hql.append(" and z.sslx =?");
			param.add(condition.get("sslx"));
		}
		
		//边界方向
		if(CommonUtil.validCondition(condition,"bjfx")){
			hql.append(" and z.bjfx like ?");
			param.add("%"+condition.get("bjfx")+"%");
		}
		
		//地形类别
		if(CommonUtil.validCondition(condition,"dxlb")){
			hql.append(" and z.dxlb like ?");
			param.add("%"+condition.get("dxlb")+"%");
		}
		
		//建设性质
		if(CommonUtil.validCondition(condition,"jsxz")){
			hql.append(" and z.jsxz =?");
			param.add(condition.get("jsxz"));
		}
		
		//投资年度
		if(CommonUtil.validCondition(condition,"tznd")){
			hql.append(" and z.tznd =?");
			param.add(condition.get("tznd"));
		}
		
		//道路类别
		if(CommonUtil.validCondition(condition,"dllb")){
			hql.append(" and z.dllb =?");
			param.add(condition.get("dllb"));
		}
		
		//道路类型
		if(CommonUtil.validCondition(condition,"dllx")){
			hql.append(" and z.dllx =?");
			param.add(condition.get("dllx"));
		}
		
		//道路等级
		if(CommonUtil.validCondition(condition,"dldj")){
			hql.append(" and z.dldj =?");
			param.add(condition.get("dldj"));
		}
		
		//路基类型
		if(CommonUtil.validCondition(condition,"ljlx")){
			hql.append(" and z.ljlx =?");
			param.add(condition.get("ljlx"));
		}
		
		//路面类型
		if(CommonUtil.validCondition(condition,"lmlx")){
			hql.append(" and z.lmlx =?");
			param.add(condition.get("lmlx"));
		}
		
		//所在省份
		if(CommonUtil.validCondition(condition,"szsf")){
			hql.append(" and z.szsf like ?");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		//所在市（区）
		if(CommonUtil.validCondition(condition,"szcs")){
			hql.append(" and z.szcs like ?");
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
     * 根据主键获取数据
     * @param id
     * @return
     */
	@Override
	public Object getDataById(String id) {
		String hql = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.dllb as dllb,z.dllx as dllx,z.dldj as dldj,z.ljlx as ljlx,z.lmlx as lmlx) from Zqdl z where z.id = ?";
		Object[] param = {id};
		return baseDao.get(hql.toString(), param);
	}
	
	/**
     * 更新执勤道路数据
     * @param zqdl
     * 修改历史：
     * 		[2017年10月10日]创建文件 by 徐成
     */
	@Override
	public void updateData(Zqdl zqdl) {
		baseDao.update(zqdl);
	}
	
	/**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition){
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.dllb as dllb,z.dllx as dllx,z.dldj as dldj,z.ljlx as ljlx,z.lmlx as lmlx) from Zqdl z where 1=1");
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
	 * 省级报表数据
	 * @param condition
	 * @return String szsf,String sslx,String jsxz,String tznd
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> getData(Map<String, Object>condition){
		StringBuffer hql =new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.dllb as dllb,z.dllx as dllx,z.dldj as dldj,z.ljlx as ljlx,z.lmlx as lmlx) from Zqdl z where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and z.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
		if(CommonUtil.validCondition(condition, "szsf")){
			hql.append(" and z.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
		    hql.append(" and z.szcs like ? ");
		    param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "sslx")){
			hql.append(" and z.sslx = ? ");
			param.add(condition.get("sslx"));
		}
		
		if(CommonUtil.validCondition(condition, "jsxz")){
			hql.append(" and z.jsxz = ?");
			param.add(condition.get("jsxz"));
		}
		if(CommonUtil.validCondition(condition, "tznd")){
			hql.append(" and z.tznd = ? ");
			param.add(condition.get("tznd"));
		}
		//新增建设状态
		if(CommonUtil.validCondition(condition, "jszt")){
			hql.append(" and z.jszt = ? ");
			param.add(condition.get("jszt"));
		}
		List<Object> list = baseDao.find(hql.toString(),param);
		List<Object> result = new ArrayList<Object>();
		for(Object obj:list){
			@SuppressWarnings("unchecked")
            Map<String,Object> map = (Map<String,Object>)obj;
			result.add(BeanToMap.transMapToBean(map, Zqdl.class));
		}
		return result;
	}
	
	/**
     * 根据建设性质求和建设规模数量
     * @param condition
     * @return List<Object>
     * @date [2017-10-23] 创建文件 by 郎川
     */
	@Override
	public List<Object> getSumByJsxz(Map<String, Object> condition) {
		String hql="select sum(jsgm) from Zqdl z where 1=1 and z.jsxz = ?";
		Object[] objParam={condition.get("jsxz")};
		
		return baseDao.find(hql, objParam);
	}

	/**
	 * 传输线路求和中央投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getZytzSum() {
		String hql="select sum(zytz) from Zqdl";
		return baseDao.find(hql);
	}

	/**
	 * 传输线路求和地方投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDftzSum() {
		String hql="select sum(dftz) from Zqdl";
		return baseDao.find(hql);
		
	}
	/**
	 * 监督实施管理页面首页数据查询
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 郎川 
	 */
	@Override
	public List<Object> getManageData(Map<String, Object> condition) {
		List<Object>param = new ArrayList<Object>();
		String hql=" select new map(z.jszt as jszt ,sum(z.jsgm)  as jsgm ) from Zqdl z where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and z.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and z.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and z.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and z.jszt is not null and z.syzt is null  group by z.jszt");
		
		return baseDao.find(hqlStr.toString(), param);
	}
	
	/**
	 * 实施监督管理页面建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getManageTz(Map<String, Object> condition) {
		String hql = "select new map(sum(z.zytz) as zytz , sum(z.dftz) as dftz, sum(z.dftz + z.zytz) as dxtz) from Zqdl z where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and z.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and z.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and z.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and z.jszt is not null and z.syzt is null ");
		return baseDao.find(hqlStr.toString(), param);
	}
	
	/**
	 * 使用维护模块首页数据查询
	 * @param condition
	 * @return  List<Object>
	 * @date [2017-11-22]
	 */
	@Override
	public List<Object> getMaintenanceData(Map<String, Object> condition) {
		List<Object>param = new ArrayList<Object>();
		String hql=" select new map(z.syzt as syzt ,sum(z.jsgm)  as jsgm ) from Zqdl z where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and z.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and z.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and z.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and z.jszt ='已审计' and z.syzt in ('良好','损坏','废弃') group by z.syzt");
		
		return baseDao.find(hqlStr.toString(), param);
	}
	
	/**
	 * 使用维护模块首页数据建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getMaintenancetz(Map<String, Object> condition) {
		String hql = "select new map(sum(z.zytz) as zytz , sum(z.dftz) as dftz, sum(z.dftz + z.zytz) as dxtz) from Zqdl z where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and z.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and z.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and z.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and z.jszt = '已审计' and z.syzt in ('良好','损坏','废弃') ");
		return baseDao.find(hqlStr.toString(), param);
	}

	@Override
	public void addZqdl(AddZqdl addZqdl) {
		baseDao.save(addZqdl);
	}


	
    /**
     * @Title getDestroyData
     * @Description (获取损坏项目信息)
     * @param condition
     * @return
     * @see com.ljdy.BHF.service.ZqdlService#getDestroyData(java.util.Map)
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDestroyData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(z.id as id, z.xmmc as xmmc, z.xmbh as xmbh, z.jsgm as jsgm, z.syzt as syzt) from Zqdl z where 1=1");
        List<Object> param = new ArrayList<Object>();
        if (CommonUtil.validCondition(condition, "id")) {
            hql.append(" and z.id = ? ");
            param.add(condition.get("id"));
        }else{
            if (CommonUtil.validCondition(condition, "szsf")) {
                hql.append(" and z.szsf like ? ");
                param.add("%" + condition.get("szsf") + "%");
            }
            if (CommonUtil.validCondition(condition, "syzt")) {
                hql.append(" and z.syzt = ? ");
                param.add(condition.get("syzt"));
            }
        }
        return baseDao.find(hql.toString(), param);
    }
	@Override
	public void updateBySql(String sql, Object[] parqam) {
		baseDao.updateBySql(sql, parqam);
		
	}
	@Override
	public void update(AddZqdl zqdl) {
        baseDao.update(zqdl);		
	}
	
	/**
	 * 统计使用维护模块各项总的投资
	 * @param condition
	 * @return List<Object>
	 * @date[2017-12-18] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getAllTz(Map<String, Object> condition) {
		String hql="select new map(sum(zytz) as zytz,sum(dftz) as dftz,sum(zytz + dftz) as dxtz) from Jbxx j ";
		StringBuffer hqlstr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlstr.append(" where  j.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlstr.append(" and j.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlstr.append(" and j.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlstr.append(" and j.jszt = '已审计' and j.syzt in ('良好', '损坏', '废弃') ");
		return baseDao.find(hqlstr.toString(), param);
	}
	
	/**
	 * 实施监督模块各项总的投资
	 * @param condition
	 * @return List<Object>
	 * @date [2017-12-18] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getManageAllTz(Map<String, Object> condition) {
		
		String hql="select new map(sum(zytz) as zytz,sum(dftz) as dftz,sum(zytz + dftz) as dxtz) from Jbxx j where 1=1";
		StringBuffer hqlstr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlstr.append(" and j.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlstr.append(" and j.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlstr.append(" and j.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		//hqlstr.append(" and j.jszt in ('待实施','已招投标','已开工','已初验','已竣工','已审计') and j.syzt not in ('良好', '损坏', '废弃')");
		hqlstr.append(" and j.jszt is not null and j.syzt is null ");
		return baseDao.find(hqlstr.toString(), param);
	}
}
