package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.AddZqf;
import com.ljdy.BHF.model.Zqf;
import com.ljdy.BHF.service.ZqfService;
import com.ljdy.BHF.utils.BeanToMap;
import com.ljdy.BHF.utils.CommonUtil;

/**
 * @ClassName ZqfServiceImpl
 * @Description (辅助配套设施---执勤房service实现类)
 * @Author 顾冲 guchong@luojiadeyi.com
 * @Date 2017年9月26日 上午11:42:30
 * @修改历史 1. [2017年9月26日]创建文件 by 顾冲
 */
@Service("zqfService")
public class ZqfServiceImpl implements ZqfService {
	@Resource
	private BaseDao<Object> baseDao;

	/**
	 * @Title getJsgm
	 * @Description (获取建设规模)
	 * @param paramMap
	 * @return
	 * @see com.ljdy.BHF.service.ZqfService#getJsgm(java.util.Map)
	 * @Date 2017年9月26日
	 * @修改历史 1. [2017年9月26日]创建文件 by 顾冲
	 * 
	 */
	@Override
	public List<Object> getJsgm(Map<String, Object> paramMap) {
		// 用户角色必传(1国家，2省级)，省级用户必传所在省份
		String roleName = (String) paramMap.get("roleName");
		List<Object> param = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"select new map(sum(z.jsgm) as jsgm");
		if ("2".equals(roleName)) {
			hql.append(",z.szcs as szcs");
		} else {
			hql.append(",z.szsf as szsf");
		}
		hql.append(") from Zqf z where 1=1");
		if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and z.bxxm = ?");
            param.add(paramMap.get("bxxm"));
        }
		if (CommonUtil.validCondition(paramMap, "tznd")) {
			hql.append(" and z.tznd = ?");
			param.add(paramMap.get("tznd"));
		}
		if ("2".equals(roleName)) {
			hql.append(" and z.szsf = ?");
			param.add(paramMap.get("szsf"));
			hql.append(" group by z.szcs");
		} else {
			hql.append(" group by z.szsf");
		}
		return baseDao.find(hql.toString(), param);
	}

	/**
	 * @Title getTz
	 * @Description (获取投资)
	 * @param paramMap
	 * @return
	 * @see com.ljdy.BHF.service.ZqdlService#getTz(java.util.Map)
	 * @Date 2017年9月28日
	 * @修改历史 1. [2017年9月28日]创建文件 by 顾冲
	 * 
	 */
	@Override
	public List<Object> getTz(Map<String, Object> paramMap) {
		// 用户角色必传(1国家，2省级)，省级用户必传所在省份
		String roleName = (String) paramMap.get("roleName");
		List<Object> param = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"select new map(sum(z.zytz+z.dftz) as dxtz, sum(z.dftz) as dftz, sum(z.zytz) as zytz) from Zqf z where 1=1");
		if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and z.bxxm = ?");
            param.add(paramMap.get("bxxm"));
        }
		if ("2".equals(roleName)) {
			hql.append(" and z.szsf = ?");
			param.add(paramMap.get("szsf"));
		}
		if (CommonUtil.validCondition(paramMap, "tznd")) {
			param.add(paramMap.get("tznd"));
			hql.append(" and z.tznd = ?");
		}
		return baseDao.find(hql.toString(), param);
	}

	/**
	 * 新增执勤房
	 * 
	 * @param zqf
	 *            修改历史： [2017年9月28日]创建文件 by 徐成
	 */
	@Override
	public void addZqf(Zqf zqf) {
		baseDao.save(zqf);
	}

	/**
	 * 新增执勤房数据查询
	 * 
	 * @param condition
	 *            condition rows
	 * @return List<Object>
	 * @date [2017-09-29] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDataByCondition(Map<String, Object> condition,
			int page, int rows) {
		String str = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt, z.jsdd as jsdd,z.jsgm as jsgm,z.zqflx as zqflx) from Zqf z where 1=1";
		List<Object> param = new ArrayList<Object>();
		if (page == 0 || rows == 0) {
			// 查询数据总条数的sql语句
			str = "select count(*) from Zqf z where 1=1";
		}
		StringBuffer hql = new StringBuffer(str);
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

		// 执勤房类型
		if (CommonUtil.validCondition(condition, "zqflx")) {
			hql.append(" and z.zqflx = ? ");
			param.add(condition.get("zqflx"));
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
	 * @return Object
	 * @date [2017-10-09] 创建文件 by 郎川
	 */
	@Override
	public Object getDataById(String id) {
		String hql="select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt, z.jsdd as jsdd,z.jsgm as jsgm,z.zqflx as zqflx) from Zqf z where 1=1 and z.id = ? ";
		Object [] objParam = {id};
		return baseDao.get(hql.toString(), objParam);
	}

	/**
	 * 更新执勤房数据
	 * @param zqf
	 * @return void 
	 * @date [2017-10-11] 创建文件 by 郎川
	 */
	@Override
	public void updateZqf(Zqf zqf) {
		baseDao.update(zqf);
	}
	
	/**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition){
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt, z.jsdd as jsdd,z.jsgm as jsgm,z.zqflx as zqflx) from Zqf z where 1=1");
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
	 * @return List<Object> getData
	 * @date [2017-10-18] 创建文件 by 徐成
	 */
	public List<Object> getData(Map<String,Object>condition){
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt, z.jsdd as jsdd,z.jsgm as jsgm,z.zqflx as zqflx) from Zqf z where 1=1 ");
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
			result.add(BeanToMap.transMapToBean(map, Zqf.class));
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
		String hql="select sum(jsgm) from Zqf z where 1=1 and z.jsxz = ? ";
		Object[] objParam={condition.get("jsxz")};
		
		return baseDao.find(hql, objParam);
	}

	/**
	 * 执勤房求和中央投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getZytzSum() {
		String hql="select sum(zytz) from Zqf";
		return baseDao.find(hql);
	}

	/**
	 * 执勤房求和地方投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDftzSum() {
		String hql="select sum(dftz) from Zqf";
		return baseDao.find(hql);
		
	}

	/**
	 * 实施监督管理页面数据查询
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getManageData(Map<String, Object> condition) {
		List<Object>param = new ArrayList<Object>();
		String hql=" select new map(z.jszt as jszt ,sum(z.jsgm) as jsgm) from Zqf z where 1=1 ";
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
		hqlStr.append(" and z.jszt is not null and z.syzt is null group by z.jszt ");
		
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
		String hql = "select new map(sum(z.zytz) as zytz , sum(z.dftz) as dftz, sum(z.dftz + z.zytz) as dxtz) from Zqf z where 1=1 ";
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
	 * @date [2017-11-22] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getMaintenanceData(Map<String, Object> condition) {
		List<Object>param = new ArrayList<Object>();
		String hql=" select new map(z.syzt as syzt ,sum(z.jsgm) as jsgm) from Zqf z where 1=1 ";
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
		hqlStr.append(" and z.jszt ='已审计' and z.syzt in ('良好','损坏','废弃') group by z.syzt ");
		
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
		String hql = "select new map(sum(z.zytz) as zytz , sum(z.dftz) as dftz, sum(z.dftz + z.zytz) as dxtz) from Zqf z where 1=1 ";
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
		hqlStr.append(" and z.jszt ='已审计' and z.syzt in ('良好','损坏','废弃') ");
		return baseDao.find(hqlStr.toString(), param);
	}
	
    /**
     * @Title getDestroyData
     * @Description (获取损坏项目信息)
     * @param condition
     * @return
     * @see com.ljdy.BHF.service.ZqfService#getDestroyData(java.util.Map)
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDestroyData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(z.id as id, z.xmmc as xmmc, z.xmbh as xmbh, z.jsgm as jsgm, z.syzt as syzt) from Zqf z where 1=1");
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
	public void addZqf(AddZqf addZqf) {
		baseDao.save(addZqf);
		
	}

}
