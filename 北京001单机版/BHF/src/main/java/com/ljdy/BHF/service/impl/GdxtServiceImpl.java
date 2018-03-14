package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.AddGdxt;
import com.ljdy.BHF.model.Gdxt;
import com.ljdy.BHF.service.GdxtService;
import com.ljdy.BHF.utils.BeanToMap;
import com.ljdy.BHF.utils.CommonUtil;

/**
 * 供电系统service实现类(GdxtServiceImpl)
 * 
 * @author 郎川
 * @date 2017年9月26日
 */
@Service("gdxtService")
public class GdxtServiceImpl implements GdxtService {

	@Resource
	private BaseDao<Object> baseDao;

	/**
	 * 供电系统的建设规模统计
	 */
	@Override
	public List<Object> getJsgm(Map<String, Object> param_map) {

		String roleName = null;
		StringBuffer hql = new StringBuffer("select new map(sum(g.jsgm) as jsgm");
		List<Object> list_param = new ArrayList<Object>();

		// 控制层必须传角色名值 先判断角色名是否为空
		if (CommonUtil.validCondition(param_map, "roleName")) {
			roleName = (String) param_map.get("roleName");
		}
		// 根据不同的角色名拼接sql语句
		if ("2".equals(roleName)) {
			hql.append(" , g.szsf as szsf ,g.szcs as szcs ) ");
		} else {
			hql.append(" , g.szsf as szsf ) ");
		}
		hql.append(" from Gdxt g where 1=1 ");
		if(CommonUtil.validCondition(param_map, "bxxm")){
            hql.append(" and g.bxxm = ?");
            list_param.add(param_map.get("bxxm"));
        }
		// 先判断投资年度是否为空
		if (CommonUtil.validCondition(param_map, "tznd")) {
			list_param.add(param_map.get("tznd"));
			hql.append(" and g.tznd=? ");
		}
		// 省级用户必须传rolename 所在省份
		// 根据不同的角色名拼接sql语句
		if ("2".equals(param_map.get("roleName"))) {
			list_param.add(param_map.get("szsf"));
			hql.append(" and g.szsf = ? ");
			hql.append(" group by g.szsf, g.szcs");
		} else {
			hql.append(" group by g.szsf");
		}
		return baseDao.find(hql.toString(), list_param);

	}

	/**
	 * 供电系统的投资统计
	 * 
	 * @param param_map
	 * @return List<Object>
	 */
	@Override
	public List<Object> getTz(Map<String, Object> param_map) {
		String roleName = null;
		List<Object> list_param = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"select new map(sum(g.dftz+g.zytz) as dxtz, sum(g.dftz) as dftz, sum(g.zytz) as zytz) from Gdxt g where 1=1 ");
		if(CommonUtil.validCondition(param_map, "bxxm")){
            hql.append(" and g.bxxm = ?");
            list_param.add(param_map.get("bxxm"));
        }
		// 控制层必须传角色名值 先判断角色名是否为空
		if (CommonUtil.validCondition(param_map, "roleName")) {
			roleName = (String) param_map.get("roleName");
		}
		// 先判断投资年度是否为空
		if (CommonUtil.validCondition(param_map, "tznd")) {
			list_param.add(param_map.get("tznd"));
			hql.append(" and g.tznd=? ");
		}
		// 省级用户要传当前用户登陆的省份
		if ("2".equals(roleName)) {
			hql.append(" and g.szsf = ? ");
			list_param.add(param_map.get("szsf"));
		}
		return baseDao.find(hql.toString(), list_param);

	}

	/**
	 * 新增供电系统
	 * 
	 * @param gdxt
	 *            修改历史： [2017年9月28日]创建文件 by 徐成
	 */
	public void addGdxt(Gdxt gdxt) {
		baseDao.save(gdxt);
	}

	/**
	 * 新增供电系统数据查询
	 * 
	 * @param condition
	 *            page rows
	 * @return List<Object>
	 * @date [2017-09-29] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDataByCondition(Map<String, Object> condition,
			int page, int rows) {
		String str = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jkz_id as jkz_id,z.jsdd as jsdd,z.jsgm as jsgm,z.jkz_name as jkz_name) from Gdxt z where 1=1";
		if (page == 0 || rows == 0) {
			// 统计数据总条数sql语句
			str = "select count(*) from Gdxt z where 1=1";
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
			hql.append(" and z.dxlb like ?");
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
		String hql = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jkz_id as jkz_id,z.jsdd as jsdd,z.jsgm as jsgm,z.jkz_name as jkz_name) from Gdxt z where 1=1 and z.id = ? ";
		Object [] objParam = {id};
		return baseDao.get(hql.toString(), objParam);
		
	}

	/**
	 * 更新供电系统数据
	 * @param gdxt
	 * @return void 
	 * @date [2017-10-11] 创建文件 by 郎川
	 */
	@Override
	public void updateGdxt(Gdxt gdxt) {
		baseDao.update(gdxt);
	}
	
	/**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition){
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jkz_id as jkz_id,z.jsdd as jsdd,z.jsgm as jsgm,z.jkz_name as jkz_name) from Gdxt z where 1=1");
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
	 * 根据建设性质求建设规模
	 * @param condition
	 * @return List<Object> 
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getSumByJsxz(Map<String, Object> condition) {
		String hql="select sum(jsgm) from Gdxt g where 1=1 and g.jsxz = ? ";
		Object[] objParam={condition.get("jsxz")};
		
		return baseDao.find(hql, objParam);
	}

	/**
	 * 供电系统 中央投资求和
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getZytzSum() {
		String hql="select sum(zytz) from Gdxt";
		return baseDao.find(hql);
	}
	/**供电系统 地方投资求和
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 * 
	 */
	@Override
	public List<Object> getDftzSum() {
		String hql="select sum(dftz) from Gdxt";
		return baseDao.find(hql);
	}

	
	/**
	  * @Title getData 
	  * @Description (省级报表数据) 
	  * @param condition
	  * @return 
	  * @see com.ljdy.BHF.service.GdxtService#getData(java.util.Map)
	  * @Date  2017年10月23日
	  * @修改历史  
	  *     1. [2017年10月23日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public List<Object> getData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jkz_id as jkz_id,z.jsdd as jsdd,z.jsgm as jsgm,z.jkz_name as jkz_name) from Gdxt z where 1=1 ");
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
            hql.append(" and z.jsxz =? ");
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
			result.add(BeanToMap.transMapToBean(map, Gdxt.class));
		}
		return result;
       
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
		String hql=" select new map(g.jszt as jszt  ,sum(g.jsgm) as jsgm) from Gdxt g where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and g.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and g.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and g.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and g.jszt is not null and g.syzt is null group by g.jszt ");
		
		return baseDao.find(hqlStr.toString(), param);
	}

	/**
	 * 实施建设管理页面建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getManagetz(Map<String, Object> condition) {
		String hql = "select new map(sum(g.zytz) as zytz , sum(g.dftz) as dftz, sum(g.dftz + g.zytz) as dxtz) from Gdxt g where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and g.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and g.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and g.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and g.jszt is not null and g.syzt is null");
		return baseDao.find(hqlStr.toString(), param);
	}

	/**
	 *使用维护模块首页数据查询
	 *@param condition
	 *@return List<Object>
	 *@date [2017-11-22] 创建文件 by 郎川
	 * 
	*/
	@Override
	public List<Object> getMaintenanceData(Map<String, Object> condition) {
		List<Object>param = new ArrayList<Object>();
		String hql=" select new map(g.syzt as syzt  ,sum(g.jsgm) as jsgm) from Gdxt g where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and g.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and g.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and g.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and g.jszt ='已审计' and g.syzt in ('良好','损坏','废弃') group by g.syzt ");
		
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
		String hql = "select new map(sum(g.zytz) as zytz , sum(g.dftz) as dftz, sum(g.dftz + g.zytz) as dxtz) from Gdxt g where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and g.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and g.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and g.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and g.jszt ='已审计' and g.syzt in ('良好','损坏','废弃')");
		return baseDao.find(hqlStr.toString(), param);
	}
	
	/**
	  * @Title getJkz_idByIds 
	  * @Description (通过id获取jkz_id) 
	  * @param idsArr
	  * @return 
	  * @see com.ljdy.BHF.service.GdxtService#getJkz_idByIds(java.lang.String[])
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
        StringBuffer sql = new StringBuffer("select distinct jkz_id from gdxt where id in (");
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
     * @see com.ljdy.BHF.service.GdxtService#getDestroyData(java.util.Map)
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDestroyData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(g.id as id, g.xmmc as xmmc, g.xmbh as xmbh, g.jsgm as jsgm, g.syzt as syzt) from Gdxt g where 1=1");
        List<Object> param = new ArrayList<Object>();
        if (CommonUtil.validCondition(condition, "id")) {
            hql.append(" and g.id = ? ");
            param.add(condition.get("id"));
        }else{
            if (CommonUtil.validCondition(condition, "szsf")) {
                hql.append(" and g.szsf like ? ");
                param.add("%" + condition.get("szsf") + "%");
            }
            if (CommonUtil.validCondition(condition, "syzt")) {
                hql.append(" and g.syzt = ? ");
                param.add(condition.get("syzt"));
            }
        }
        return baseDao.find(hql.toString(), param);
    }
	@Override
	public void addGdxt(AddGdxt addGdxt) {
            baseDao.save(addGdxt);		
	}

}
