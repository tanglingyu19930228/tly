package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.AddWrzsdzsb;
import com.ljdy.BHF.model.Wrzsdzsb;
import com.ljdy.BHF.service.WrzsdzsbService;
import com.ljdy.BHF.utils.BeanToMap;
import com.ljdy.BHF.utils.CommonUtil;

/**
 * @ClassName WrzsdzsbServiceImpl
 * @Description (指挥监控设施----无人值守电子哨兵service实现类)
 * @Author 顾冲 guchong@luojiadeyi.com
 * @Date 2017年9月26日 上午10:43:07
 * @修改历史 1. [2017年9月26日]创建文件 by 顾冲
 */
@Service("wrzsdzsbService")
public class WrzsdzsbServiceImpl implements WrzsdzsbService {
	@Resource
	private BaseDao<Object> baseDao;

	/**
	 * @Title getJsgm
	 * @Description (获取建设规模)
	 * @param paramMap
	 * @return
	 * @see com.ljdy.BHF.service.WrzsdzsbService#getJsgm(java.util.Map)
	 * @Date 2017年9月26日
	 * @修改历史 1. [2017年9月26日]创建文件 by 顾冲
	 * 
	 */
	@Override
	public List<Object> getJsgm(Map<String, Object> paramMap) {
		// 用户角色必传(1国家，2省级)，省级用户必传所在省份
		String roleName = (String) paramMap.get("roleName");
		StringBuffer hql = new StringBuffer(
				"select new map(sum(w.jsgm) as jsgm");
		if ("2".equals(roleName)) {
			hql.append(",w.szcs as szcs");
		} else {
			hql.append(",w.szsf as szsf");
		}
		hql.append(") from Wrzsdzsb w where 1=1");
		List<Object> param = new ArrayList<Object>();
		if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and w.bxxm = ?");
            param.add(paramMap.get("bxxm"));
        }
		if (CommonUtil.validCondition(paramMap, "tznd")) {
			hql.append(" and w.tznd = ?");
			param.add(paramMap.get("tznd"));
		}
		if ("2".equals(roleName)) {
			hql.append(" and w.szsf = ?");
			param.add(paramMap.get("szsf"));
			hql.append(" group by w.szcs");
		} else {
			hql.append(" group by w.szsf");
		}
		return baseDao.find(hql.toString(), param);
	}

	/**
	 * 
	 * @Title getTz
	 * @Description (获取投资)
	 * @param paramMap
	 * @return
	 * @see com.ljdy.BHF.service.WrzsdzsbService#getTz(java.util.Map)
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
				"select new map(sum(w.zytz+w.dftz) as dxtz, sum(w.dftz) as dftz, sum(w.zytz) as zytz) from Wrzsdzsb w where 1=1");
		if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and w.bxxm = ?");
            param.add(paramMap.get("bxxm"));
        }
		if ("2".equals(roleName)) {
			hql.append(" and w.szsf = ?");
			param.add(paramMap.get("szsf"));
		}
		if (CommonUtil.validCondition(paramMap, "tznd")) {
			param.add(paramMap.get("tznd"));
			hql.append(" and w.tznd = ?");
		}
		return baseDao.find(hql.toString(), param);
	}

	/**
	 * 新增无人值守电子哨兵
	 * 
	 * @param wrzsdzsb
	 *            修改历史： [2017年9月28日]创建文件 by 徐成
	 */
	@Override
	public void addWrzsdzsb(Wrzsdzsb wrzsdzsb) {
		baseDao.save(wrzsdzsb);
	}

	/**
	 * 新增无人值守电子哨兵数据查询
	 * 
	 * @param condition
	 *            page rows
	 * @return List<Object>
	 * @date [2017-09-29] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDataByCondition(Map<String, Object> condition,
			int page, int rows) {
		String str = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.fzdd as fzdd,z.jsgm as jsgm) from Wrzsdzsb z where 1=1";
		if (page == 0 || rows == 0) {
			// 统计数据总数sql语句
			str = "select count(*) from Wrzsdzsb z where 1=1";
		}
		List<Object> param = new ArrayList<Object>();
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
		String hql = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.fzdd as fzdd,z.jsgm as jsgm) from Wrzsdzsb z where 1=1 and z.id = ? ";
		Object[] objParam ={id};
		return baseDao.get(hql.toString(), objParam);
		
	}

	/**
	 * 更新无人值守电子哨兵
	 * @param wrzsdzsb
	 * @return void 
	 * @date [2017-10-11] 创建文件 by 郎川
	 */
	@Override
	public void updateWrzsdzsb(Wrzsdzsb wrzsdzsb) {
		baseDao.update(wrzsdzsb);
	}
	
	 /**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition){
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.fzdd as fzdd,z.jsgm as jsgm) from Wrzsdzsb z where 1=1");
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
     * 无人值守电子哨兵根据建设性质求和建设规模数量
     * @param condition
     * @return List<Object>
     * @date [2017-10-23] 创建文件 by 郎川
     */
	@Override
	public List<Object> getSumByJsxz(Map<String, Object> condition) {
		String hql="select sum(jsgm) from Wrzsdzsb w where 1=1 and w.jsxz = ? ";
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
		String hql="select sum(zytz) from Wrzsdzsb";
		return baseDao.find(hql);
	}

	/**
	 * 传输线路求和地方投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDftzSum() {
		
		String hql="select sum(dftz) from Wrzsdzsb";
		return baseDao.find(hql);
		
	}


	
	/**
	  * @Title getData 
	  * @Description (省级报表数据) 
	  * @param condition
	  * @return 
	  * @see com.ljdy.BHF.service.WrzsdzsbService#getData(java.util.Map)
	  * @Date  2017年10月23日
	  * @修改历史  
	  *     1. [2017年10月23日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public List<Object> getData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.fzdd as fzdd,z.jsgm as jsgm) from Wrzsdzsb z where 1=1 ");
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
			result.add(BeanToMap.transMapToBean(map, Wrzsdzsb.class));
		}
		return result;
    }

	@Override
	public List<Object> getManageData(Map<String, Object> condition) {
		
		List<Object>param = new ArrayList<Object>();
		String hql=" select new map(w.jszt as jszt ,sum(w.jsgm) as jsgm) from Wrzsdzsb w where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and w.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and w.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and w.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and w.jszt is not null and w.syzt is null group by w.jszt ");
		
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
		String hql = "select new map(sum(w.zytz) as zytz , sum(w.dftz) as dftz, sum(w.dftz + w.zytz) as dxtz) from Wrzsdzsb w where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and w.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and w.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and w.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and w.jszt is not null and w.syzt is null ");
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
		String hql=" select new map(w.syzt as syzt ,sum(w.jsgm) as jsgm) from Wrzsdzsb w where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and w.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and w.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and w.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and w.jszt ='已审计' and w.syzt in ('良好','损坏','废弃') group by w.syzt ");
		
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
		String hql = "select new map(sum(w.zytz) as zytz , sum(w.dftz) as dftz, sum(w.dftz + w.zytz) as dxtz) from Wrzsdzsb w where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and w.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and w.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and w.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and w.jszt ='已审计' and w.syzt in ('良好','损坏','废弃') ");
		return baseDao.find(hqlStr.toString(), param);
	}
	
    /**
     * @Title getDestroyData
     * @Description (获取损坏项目信息)
     * @param condition
     * @return
     * @see com.ljdy.BHF.service.WrzsdzsbService#getDestroyData(java.util.Map)
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDestroyData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(w.id as id, w.xmmc as xmmc, w.xmbh as xmbh, w.jsgm as jsgm, w.syzt as syzt) from Wrzsdzsb w where 1=1");
        List<Object> param = new ArrayList<Object>();
        if (CommonUtil.validCondition(condition, "id")) {
            hql.append(" and w.id = ? ");
            param.add(condition.get("id"));
        }else{
            if (CommonUtil.validCondition(condition, "szsf")) {
                hql.append(" and w.szsf like ? ");
                param.add("%" + condition.get("szsf") + "%");
            }
            if (CommonUtil.validCondition(condition, "syzt")) {
                hql.append(" and w.syzt = ? ");
                param.add(condition.get("syzt"));
            }
        }
        return baseDao.find(hql.toString(), param);
    }
	@Override
	public void addWrzsdzsb(AddWrzsdzsb addWrzsdzsb) {
             baseDao.save(addWrzsdzsb);		
	}

}
