package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.AddBzp;
import com.ljdy.BHF.model.Bzp;
import com.ljdy.BHF.service.BzpService;
import com.ljdy.BHF.utils.BeanToMap;
import com.ljdy.BHF.utils.CommonUtil;

/**
 * 标志牌(BzpServiceImpl)
 * 
 * @author 郎川
 * @date 2017年9月26日
 */
@Service("bzpService")
public class BzpServiceImpl implements BzpService {

	@Resource
	private BaseDao<Object> baseDao;

	@Override
	public List<Object> getJsgm(Map<String, Object> param_map) {
		String roleName = null;
		StringBuffer hql = new StringBuffer(
				"select  new map(sum(b.jsgm) as jsgm ");
		List<Object> list_param = new ArrayList<Object>();
		// 控制层必须传角色名 先判断角色名是否为空
		if (CommonUtil.validCondition(param_map, "roleName")) {
			roleName = (String) param_map.get("roleName");
		}
		// 根据不同的角色名拼接sql语句
		if ("2".equals(roleName)) {

			hql.append(" ,b.szsf as szsf ,b.szcs as szcs ) ");
		} else {
			hql.append(" ,b.szsf as szsf ) ");
		}
		hql.append(" from Bzp b where 1=1 ");
		if(CommonUtil.validCondition(param_map, "bxxm")){
            hql.append(" and b.bxxm = ?");
            list_param.add(param_map.get("bxxm"));
        }
		// 先判断角色名是否为空
		if (CommonUtil.validCondition(param_map, "tznd")) {
			list_param.add(param_map.get("tznd"));
			hql.append(" and b.tznd =? ");
		}
		// 根据不同的角色名拼接不同的分组查询
		if ("2".equals(param_map.get("roleName"))) {
			list_param.add(param_map.get("szsf"));
			hql.append(" and b.szsf = ? ");
			hql.append(" group by b.szsf,b.szcs ");

		} else {
			hql.append(" group by b.szsf ");
		}

		List<Object> list_bzp = baseDao.find(hql.toString(), list_param);
		return list_bzp;
	}

	/**
	 * @author 郎川 标志牌的投资
	 * @param (Map<String,Object>param_map)
	 * @return List<Object>
	 * @date 2017-09-28
	 */
	@Override
	public List<Object> getTz(Map<String, Object> param_map) {
		String roleName = null;
		List<Object> list_param = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"select new map(sum(b.dftz+b.zytz) as dxtz, sum(b.dftz) as dftz, sum(b.zytz) as zytz) from Bzp b where 1=1 ");
		if(CommonUtil.validCondition(param_map, "bxxm")){
            hql.append(" and b.bxxm = ?");
            list_param.add(param_map.get("bxxm"));
        }
		// 控制层必须传角色名 先判断角色名是否为空
		if (CommonUtil.validCondition(param_map, "roleName")) {
			roleName = (String) param_map.get("roleName");
		}
		// 判断投资年度是否为空
		if (CommonUtil.validCondition(param_map, "tznd")) {
			list_param.add(param_map.get("tznd"));
			hql.append(" and b.tznd=? ");

		}
		if ("2".equals(roleName)) {
			hql.append(" and b.szsf = ? ");
			list_param.add(param_map.get("szsf"));

		}
		return baseDao.find(hql.toString(), list_param);

	}

	/**
	 * 新增标志牌
	 * 
	 * @param bzp
	 *            修改历史： [2017年9月28日]创建文件 by 徐成
	 */
	public void addBzp(Bzp bzp) {
		baseDao.save(bzp);
	}

	/**
	 * 新增标志牌数据查询
	 * 
	 * @param condition
	 *            page rows
	 * @return List<Object>
	 * @date [2017-09-29] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDataByCondition(Map<String, Object> condition,
			int page, int rows) {

		String str = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.bzplx as bzplx) from Bzp z where 1=1";
		if (page == 0 || rows == 0) {
			str = "select count(*) from Bzp z where 1=1";
		}
		StringBuffer hql = new StringBuffer(str);
		List<Object> param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and z.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
		// 设施类型
		if (CommonUtil.validCondition(condition, "sslx")) {
			hql.append(" and z.sslx = ? ");
			param.add(condition.get("sslx"));
		}

		// 边界方向
		if (CommonUtil.validCondition(condition, "bjfx")) {
			hql.append(" and z.bjfx like ? ");
			param.add("%" + condition.get("bjfx") + "%");
		}

		// 地形类别
		if (CommonUtil.validCondition(condition, "dxlb")) {
			hql.append(" and z.dxlb like ? ");
			param.add("%" + condition.get("dxlb") + "%");
		}

		// 建设性质
		if (CommonUtil.validCondition(condition, "jsxz")) {
			hql.append(" and z.jsxz = ? ");
			param.add(condition.get("jsxz"));
		}

		// 投资年度
		if (CommonUtil.validCondition(condition, "tznd")) {
			hql.append(" and z.tznd = ? ");
			param.add(condition.get("tznd"));
		}

		// 标志牌类型
		if (CommonUtil.validCondition(condition, "bzplx")) {
			hql.append(" and z.bzplx = ? ");
			param.add(condition.get("bzplx"));
		}

		// 所在省份
		if (CommonUtil.validCondition(condition, "szsf")) {
			hql.append(" and z.szsf like ?");
			param.add("%" + condition.get("szsf") + "%");
		}

		// 所在市（区）
		if (CommonUtil.validCondition(condition, "szcs")) {
			hql.append(" and z.szcs like ?");
			param.add("%" + condition.get("szcs") + "%");
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
	 * 根据主键id查询数据详情
	 * @param id
	 * @return  Object
	 * @date [2017-10-09] 创建文件  by 郎川
	 */
	@Override
	public Object getDataById(String id) {
		String hql="select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.bzplx as bzplx) from Bzp z where 1=1 and z.id = ? ";
		Object [] objParam = {id};
		return baseDao.get(hql.toString(), objParam);
	}

	/**
	 * 更新标志牌数据
	 * @param bzp
	 * @return void 
	 * @date [2017-10-11] 创建文件 by 郎川
	 */
	@Override
	public void updateBzp(Bzp bzp) {
		baseDao.update(bzp);
	}
	
	/**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition){
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.bzplx as bzplx) from Bzp z where 1=1");
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
	 * @param szsf
	 * @param jsxz
	 * @param tznd
	 * @return
	 */
	public List<Object> getData(Map<String, Object>condition){
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.bzplx as bzplx) from Bzp z where 1=1 ");
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
		
		if(CommonUtil.validCondition(condition, "jsxz")){
			hql.append(" and z.jsxz =?");
			param.add(condition.get("jsxz"));
		}
		if(CommonUtil.validCondition(condition, "tznd")){
			hql.append(" and z.tznd =? ");
			param.add(condition.get("tznd"));
		}
		
		List<Object> list = baseDao.find(hql.toString(),param);
		List<Object> result = new ArrayList<Object>();
		for(Object obj:list){
			@SuppressWarnings("unchecked")
            Map<String,Object> map = (Map<String,Object>)obj;
			result.add(BeanToMap.transMapToBean(map, Bzp.class));
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
		String hql="select sum(jsgm) from Bzp b where 1=1 and b.jsxz = ? ";
		Object[] objParam={condition.get("jsxz")};
		
		return baseDao.find(hql, objParam);
	}

	/**
	 * 标志牌求和中央投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getZytzSum() {
		String hql="select sum(zytz) from Bzp";
		return baseDao.find(hql);
	}

	/**
	 *标志牌求和地方投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDftzSum() {
		String hql="select sum(dftz) from Bzp";
		return baseDao.find(hql);
		
	}

	/**
	 * 实施监督管理页面数据查询
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件  by 郎川
	 */
	@Override
	public List<Object> getManageData(Map<String, Object> condition) {
		List<Object>param = new ArrayList<Object>();
		String hql=" select new map(b.jszt as jszt , sum(b.jsgm) as jsgm)  from Bzp b where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and b.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and b.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and b.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and b.jszt is not null and b.syzt is null group by b.jszt ");
		
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
		String hql = "select new map(sum(b.zytz) as zytz , sum(b.dftz) as dftz, sum(b.dftz + b.zytz) as dxtz) from Bzp b where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and b.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and b.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and b.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and b.jszt is not null and b.syzt is null ");
		return baseDao.find(hqlStr.toString(), param);
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
		String hql=" select new map(b.syzt as syzt , sum(b.jsgm) as jsgm)  from Bzp b where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and b.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and b.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and b.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and b.jszt ='已审计' and b.syzt in ('良好','损坏','废弃') group by b.syzt ");
		
		return baseDao.find(hqlStr.toString(), param);
	}

	/**
	 * 使用维护模块首页数据建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-23] 创建文件  by 郎川 
	 */
	@Override
	public List<Object> getMaintenanceTz(Map<String, Object> condition) {
		String hql = "select new map(sum(b.zytz) as zytz , sum(b.dftz) as dftz, sum(b.dftz + b.zytz) as dxtz) from Bzp b where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and b.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and b.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and b.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and b.jszt ='已审计' and b.syzt in ('良好','损坏','废弃') ");
		return baseDao.find(hqlStr.toString(), param);
	}


	@Override
	public void addBzp(AddBzp addBzp) {
		baseDao.save(addBzp);
		
	}

	
    /**
     * @Title getDestroyData
     * @Description (获取损坏项目信息)
     * @param condition
     * @return
     * @see com.ljdy.BHF.service.BzpService#getDestroyData(java.util.Map)
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDestroyData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(b.id as id, b.xmmc as xmmc, b.xmbh as xmbh, b.jsgm as jsgm, b.syzt as syzt) from Bzp b where 1=1");
        List<Object> param = new ArrayList<Object>();
        if (CommonUtil.validCondition(condition, "id")) {
            hql.append(" and b.id = ? ");
            param.add(condition.get("id"));
        }else{
            if (CommonUtil.validCondition(condition, "szsf")) {
                hql.append(" and b.szsf like ? ");
                param.add("%" + condition.get("szsf") + "%");
            }
            if (CommonUtil.validCondition(condition, "syzt")) {
                hql.append(" and b.syzt = ? ");
                param.add(condition.get("syzt"));
            }
        }
        return baseDao.find(hql.toString(), param);
    } 

}
