package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.AddJjmlfpt;
import com.ljdy.BHF.model.Jjmlfpt;
import com.ljdy.BHF.service.JjmlfptService;
import com.ljdy.BHF.utils.BeanToMap;
import com.ljdy.BHF.utils.CommonUtil;

/**
 * 军警民联防平台service实现类(JjmlfptServiceImpl)
 * 
 * @author 郎川
 * @date 2017年9月26日
 */
/**
 * @author Administrator
 *@date 2017年10月18日
JjmlfptServiceImpl*
	
 */
/**
 * @author Administrator
 *@date 2017年10月18日
JjmlfptServiceImpl*
	
 */
@Service("jjmlfptService")
public class JjmlfptServiceImpl implements JjmlfptService {

	@Resource
	private BaseDao<Object> baseDao;

	/**
	 * 军警民联防平台建设规模统计
	 * 
	 * @param param_map
	 * @return List<Object>
	 * @date 2017-09-27
	 * 
	 */
	@Override
	public List<Object> getJsgm(Map<String, Object> param_map) {

		String roleName = null;
		StringBuffer hql = new StringBuffer(
				" select new map(count(*) as jsgm");
		List<Object> param_list = new ArrayList<Object>();

		// 控制层必须传角色名值 先判断角色名是否为空
		if (CommonUtil.validCondition(param_map, "roleName")) {
			roleName = (String) param_map.get("roleName");
		}
		// 根据不同角色名拼接不同sql语句
		if ("2".equals(roleName)) {

			hql.append(" ,j.szsf as szsf ,j.szcs as szcs ) ");
		} else {
			hql.append(" ,j.szsf as szsf ) ");
		}
		hql.append(" from Jjmlfpt j where 1=1 ");
		if(CommonUtil.validCondition(param_map, "bxxm")){
            hql.append(" and j.bxxm = ?");
            param_list.add(param_map.get("bxxm"));
        }
		// 先判断投资年度是否为空
		if (CommonUtil.validCondition(param_map, "tznd")) {
			param_list.add(param_map.get("tznd"));
			hql.append(" and j.tznd = ? ");
		}
		// 根据不同的角色名拼接分组查询
		if ("2".equals(param_map.get("roleName"))) {
			param_list.add(param_map.get("szsf"));
			hql.append(" and j.szsf = ? ");
			hql.append(" group by j.szsf,j.szcs");
		} else {
			hql.append(" group by j.szsf");
		}

		return baseDao.find(hql.toString(), param_list);

	}

	/**
	 * 军警民联防平台的投资统计
	 * 
	 * @param param_map
	 * @return List<Object>
	 * @date 2017-09-28
	 */
	@Override
	public List<Object> getTz(Map<String, Object> param_map) {
		String roleName = null;
		List<Object> list_param = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"select new map(sum(j.dftz+j.zytz) as dxtz, sum(j.dftz) as dftz, sum(j.zytz) as zytz) from Jjmlfpt j where 1=1");
		if(CommonUtil.validCondition(param_map, "bxxm")){
            hql.append(" and j.bxxm = ?");
            list_param.add(param_map.get("bxxm"));
        }
		// 控制层必须传角色名值 先判断角色名是否为空
		if (CommonUtil.validCondition(param_map, "roleName")) {
			roleName = (String) param_map.get("roleName");
		}
		if ("2".equals(roleName)) {
			hql.append(" and j.szsf = ? ");
			list_param.add(param_map.get("szsf"));
		}
		// 先判断投资年度是否为空
		if (CommonUtil.validCondition(param_map, "tznd")) {
			list_param.add(param_map.get("tznd"));
			hql.append(" and j.tznd = ? ");
		}

		return baseDao.find(hql.toString(), list_param);
	}

	/**
	 * 新增军警民联防平台
	 * 
	 * @param jjmlfpt
	 *            修改历史： [2017年9月28日]创建文件 by 徐成
	 */
	public void addJjmlfpt(Jjmlfpt jjmlfpt) {
		baseDao.save(jjmlfpt);
	}

	/**
	 * 新增军警民联防平台数据查询
	 * 
	 * @param condition
	 *            page rows
	 * @return List<Object>
	 * @date [2017 -09-29] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDataByCondition(Map<String, Object> condition,
			int page, int rows) {
		String str = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz,z.xscsxl as xscsxl ,z.hxltqk as hxltqk,z.hxltwlxz as hxltwlxz,z.hxcsxl as hxcsxl,z.jb as jb,z.fzdd as fzdd) from Jjmlfpt z where 1=1";
		if (page == 0 || rows == 0) {
			// 统计数据的总数的sql语句
			str = "select count(*) from  Jjmlfpt z where 1=1";
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

		// 向上联通情况
		if (CommonUtil.validCondition(condition, "xsltqk")) {
			hql.append(" and z.xsltqk = ? ");
			param.add(condition.get("xsltqk"));
		}

		// 向上联通网络性质
		if (CommonUtil.validCondition(condition, "xsltwlxz")) {
			hql.append(" and z.xsltwlxz = ? ");
			param.add(condition.get("xsltwlxz"));
		}

		// 向上传输线路
		if (CommonUtil.validCondition(condition, "xscsxl")) {
			hql.append(" and z.xscsxl = ? ");
			param.add(condition.get("xscsxl"));
		}

		// 横向联通情况
		if (CommonUtil.validCondition(condition, "hxltqk")) {
			hql.append(" and z.hxltqk = ? ");
			param.add(condition.get("hxltqk"));
		}

		// 横向联通网络性质
		if (CommonUtil.validCondition(condition, "hxltwlxz")) {
			hql.append(" and z.hxltwlxz = ? ");
			param.add(condition.get("hxltwlxz"));
		}

		// 横向传输线路
		if (CommonUtil.validCondition(condition, "hxcsxl")) {
			hql.append(" and z.hxcsxl = ? ");
			param.add(condition.get("hxcsxl"));
		}

		// 级别
		if (CommonUtil.validCondition(condition, "jb")) {
			hql.append(" and z.jb = ? ");
			param.add(condition.get("jb"));
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
		String hql = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz,z.xscsxl as xscsxl ,z.hxltqk as hxltqk,z.hxltwlxz as hxltwlxz,z.hxcsxl as hxcsxl,z.jb as jb,z.fzdd as fzdd) from Jjmlfpt z where 1=1 and z.id = ? ";
		Object [] objParam ={id};
		return baseDao.get(hql.toString(), objParam);
		
	}

	/**
	 * 更新军警民联防平台
	 * @param jjmlfpt
	 * @return void 
	 * @date [2017-10-11] 创建文件 by 郎川
	 */
	@Override
	public void updateJjmlfpt(Jjmlfpt jjmlfpt) {
		baseDao.update(jjmlfpt);
	}
	
	/**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition){
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz,z.xscsxl as xscsxl ,z.hxltqk as hxltqk,z.hxltwlxz as hxltwlxz,z.hxcsxl as hxcsxl,z.jb as jb,z.fzdd as fzdd) from Jjmlfpt z where 1=1");
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
	 *获取省份报表的数据
	 *@param condition
	 *@return List<Object>
	 *@date [2017-10-18] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getData(Map<String, Object>condition) {
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz,z.xscsxl as xscsxl ,z.hxltqk as hxltqk,z.hxltwlxz as hxltwlxz,z.hxcsxl as hxcsxl,z.jb as jb,z.fzdd as fzdd) from Jjmlfpt z where 1=1 ");
		List<Object>param = new ArrayList<Object>();
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
			hql.append(" and z.jsxz = ? ");
			param.add(condition.get("jsxz"));
		}
		if(CommonUtil.validCondition(condition, "tznd")){
			hql.append(" and z.tznd = ? ");
			param.add(condition.get("tznd"));
		}
		
		List<Object> list = baseDao.find(hql.toString(),param);
		List<Object> result = new ArrayList<Object>();
		for(Object obj:list){
			@SuppressWarnings("unchecked")
            Map<String,Object> map = (Map<String,Object>)obj;
			result.add(BeanToMap.transMapToBean(map, Jjmlfpt.class));
		}
		return result;
	}

	/**
	 * 根据建设性质 统计军警民联防平台数量
	 * @param condition
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getCountByJsxz(Map<String, Object> condition) {
		String hql="select count(*) as count from Jjmlfpt j where 1=1 and j.jsxz = ? ";
		Object[]objParam={condition.get("jsxz")};
		return baseDao.find(hql.toString(), objParam);
	}

	/**
	 * 军警民联防平台中央投资求和
	 */
	@Override
	public List<Object> getZytzSum() {
		String hql="select sum(zytz) from Jjmlfpt";
		return baseDao.find(hql);
	}

	/**
	 * 军警民联防平台地方投资求和
	 */
	@Override
	public List<Object> getDftzSum() {
		String hql="select sum(dftz) from Jjmlfpt";
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
		String hql=" select new map(j.jszt as jszt ,count(*) as jsgm ) from Jjmlfpt j where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and j.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and j.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and j.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and j.jszt is not null  and j.syzt is null group by j.jszt ");
		
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
		String hql = "select new map(sum(j.zytz) as zytz , sum(j.dftz) as dftz, sum(j.dftz + j.zytz) as dxtz) from Jjmlfpt j where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and j.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and j.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and j.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and j.jszt is not null and j.syzt is null ");
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
		String hql=" select new map(j.syzt as syzt ,count(*) as jsgm ) from Jjmlfpt j where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and j.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and j.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and j.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and j.jszt ='已审计' and j.syzt in ('良好','损坏','废弃') group by j.syzt ");
		
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
		String hql = "select new map(sum(j.zytz) as zytz , sum(j.dftz) as dftz, sum(j.dftz + j.zytz) as dxtz) from Jjmlfpt j where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and j.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and j.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and j.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and j.jszt ='已审计' and j.syzt in ('良好','损坏','废弃') ");
		return baseDao.find(hqlStr.toString(), param);
	}


	@Override
	public void addJjmlfpt(AddJjmlfpt addJjmlfpt) {
           baseDao.save(addJjmlfpt);	
	}

	
    /**
     * @Title getDestroyData
     * @Description (获取损坏项目信息)
     * @param condition
     * @return
     * @see com.ljdy.BHF.service.JjmlfptService#getDestroyData(java.util.Map)
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDestroyData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(j.id as id, j.xmmc as xmmc, j.xmbh as xmbh, j.syzt as syzt) from Jjmlfpt j where 1=1");
        List<Object> param = new ArrayList<Object>();
        if (CommonUtil.validCondition(condition, "id")) {
            hql.append(" and j.id = ? ");
            param.add(condition.get("id"));
        }else{
            if (CommonUtil.validCondition(condition, "szsf")) {
                hql.append(" and j.szsf like ? ");
                param.add("%" + condition.get("szsf") + "%");
            }
            if (CommonUtil.validCondition(condition, "syzt")) {
                hql.append(" and j.syzt = ? ");
                param.add(condition.get("syzt"));
            }
        }
        return baseDao.find(hql.toString(), param);
    }

}
