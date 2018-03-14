package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.AddJkzx;
import com.ljdy.BHF.model.Jkzx;
import com.ljdy.BHF.service.JkzxService;
import com.ljdy.BHF.utils.BeanToMap;
import com.ljdy.BHF.utils.CommonUtil;

/**
 * 监控中心service实现类(JkzxServiceImpl)
 * 
 * @author 郎川
 * @date 2017年9月27日 select count(*), j.szsf,x.jb,j.jsxz from JKZX x, jbxx j
 *       where x.bzm = j.bzm group by j.szsf,x.jb,j.jsxz;
 */
@Service("jkzxService")
public class JkzxServiceImpl implements JkzxService {

	@Resource
	private BaseDao<Object> baseDao;

	/**
	 * 统计监控中心的建设规模
	 * 
	 * @param param_map
	 * @return List<Object>
	 * @date 2017-09-27
	 */
	@Override
	public List<Object> getJsgm(Map<String, Object> param_map) {

		String roleName = null;
		StringBuffer hql = new StringBuffer("select new map(count(*) as jsgm,j.jsxz as jsxz");
		List<Object> param_list = new ArrayList<Object>();
		// 控制层必须传角色名值 先判断角色名是否为空
		if (CommonUtil.validCondition(param_map, "roleName")) {
			roleName = (String) param_map.get("roleName");
		}
		// 根据不同的角色名拼接不同sql语句
		if ("2".equals(roleName)) {
			hql.append(" ,j.szsf as szsf ,j.szcs as szcs ,j.jb as jb ) ");
		} else {
			hql.append(" ,j.szsf as szsf ,j.jb as jb ) ");
		}
		hql.append(" from Jkzx j where 1=1 ");
		if(CommonUtil.validCondition(param_map, "bxxm")){
            hql.append(" and j.bxxm = ?");
            param_list.add(param_map.get("bxxm"));
        }
		// 先判断投资年度是否为空
		if (CommonUtil.validCondition(param_map, "tznd")) {
			param_list.add(param_map.get("tznd"));
			hql.append(" and j.tznd = ? ");
		}
		// 根据不同的角色名拼接不同的分组查询
		if ("2".equals(param_map.get("roleName"))) {
			hql.append(" and j.szsf=? ");
			hql.append(" group by j.szsf, j.szcs,j.jb,j.jsxz ");
			param_list.add(param_map.get("szsf"));
		} else {
			hql.append(" group by j.szsf,j.jb,j.jsxz ");
		}

		return baseDao.find(hql.toString(), param_list);

	}

	/**
	 * 统计监控中心的投资
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
				"select new map(sum(j.dftz+j.zytz) as dxtz,sum(j.dftz) as dftz, sum(j.zytz) as zytz,j.jb as jb,j.jsxz as jsxz) from Jkzx j where 1=1");
		if(CommonUtil.validCondition(param_map, "bxxm")){
            hql.append(" and j.bxxm = ?");
            list_param.add(param_map.get("bxxm"));
        }
		// 控制层必须传角色名值 先判断角色名是否为空
		if (CommonUtil.validCondition(param_map, "roleName")) {
			roleName = (String) param_map.get("roleName");
		}
		// 先判断投资年度是否为空
		if (CommonUtil.validCondition(param_map, "tznd")) {
			list_param.add(param_map.get("tznd"));
			hql.append(" and j.tznd = ? ");
		}

		if ("2".equals(roleName)) {
			list_param.add(param_map.get("szsf"));
			hql.append(" and j.szsf = ? ");
		}
		hql.append(" group by j.jb,j.jsxz");

		return baseDao.find(hql.toString(), list_param);
	}

	/**
	 * 新增监控中心
	 * 
	 * @param jkzx
	 *            修改历史： [2017年9月28日]创建文件 by 徐成
	 */
	@Override
	public void addJkzx(Jkzx jkzx) {
		baseDao.save(jkzx);
	}

	/**
	 * 新增监控中心查询数据 带分页
	 * 
	 * @param condition
	 *            page rows
	 * @return List<Object> 修改历史 [2017-09-28] by 郎川
	 */
	@Override
	public List<Object> getDataByCondition(Map<String, Object> condition,
			int page, int rows) {
		String str = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jb as jb,z.xscsxl as xscsxl,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz) from Jkzx z where 1=1";
		if (page == 0 || rows == 0) {
			str = "select count(*) from Jkzx z where 1=1";
		}
		StringBuffer hql = new StringBuffer(str);
		List<Object> param = new ArrayList<>();
		if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and z.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
		// 设施类型
		if (CommonUtil.validCondition(condition, "sslx")) {
			hql.append(" and z.sslx =?");
			param.add(condition.get("sslx"));
		}

		// 边界方向
		if (CommonUtil.validCondition(condition, "bjfx")) {
			hql.append(" and z.bjfx like ?");
			param.add("%" + condition.get("bjfx") + "%");
		}

		// 地形类别
		if (CommonUtil.validCondition(condition, "dxlb")) {
			hql.append(" and z.dxlb like ?");
			param.add("%" + condition.get("dxlb") + "%");
		}

		// 建设性质
		if (CommonUtil.validCondition(condition, "jsxz")) {
			hql.append(" and z.jsxz =?");
			param.add(condition.get("jsxz"));
		}

		// 投资年度
		if (CommonUtil.validCondition(condition, "tznd")) {
			hql.append(" and z.tznd =?");
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

		// 向上联通情况
		if (CommonUtil.validCondition(condition, "xsltqk")) {
			hql.append(" and z.xsltqk = ?");
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

		// 级别
		if (CommonUtil.validCondition(condition, "jb")) {
			hql.append(" and z.jb = ? ");
			param.add(condition.get("jb"));
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
	 * 根据主键id获取数据
	 * @param id
	 * @return Object
	 * @date [2017-10-09] 创建文件  by 郎川 
	 */
	@Override
	public Object getDataById(String id) {
		String hql ="select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jb as jb,z.xscsxl as xscsxl,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz) from Jkzx z where 1=1 and z.id = ? ";
		Object[] objParam={id};
		return baseDao.get(hql.toString(), objParam);
		
	}

	/**
	 * 更新监控中心数据
	 * @param jkzx
	 * @return void 
	 * @date [2017-10-11] 创建文件 by 郎川
	 */
	@Override
	public void updateJkzx(Jkzx jkzx) {
		baseDao.update(jkzx);
	}
	
	/**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition){
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jb as jb,z.xscsxl as xscsxl,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz) from Jkzx z where 1=1");
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
	  * @Title getData 
	  * @Description (省级报表数据) 
	  * @param condition
	  * @return 
	  * @see com.ljdy.BHF.service.JkzxService#getData(Map<String, Object>)
	  * @Date  2017年10月18日
	  * @修改历史  
	  *     1. [2017年10月18日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public List<Object> getData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jb as jb,z.xscsxl as xscsxl,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz) from Jkzx z where 1=1");
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
            hql.append(" and z.jsxz =?");
            param.add(condition.get("jsxz"));
        }
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and z.tznd =?");
            param.add(condition.get("tznd"));
        }
        if(CommonUtil.validCondition(condition, "jb")){
            hql.append(" and z.jb =?");
            param.add(condition.get("jb"));
        }
        if(CommonUtil.validCondition(condition, "jszt")){
        	hql.append(" and z.jszt = ? ");
        	param.add(condition.get("jszt"));
        }
        List<Object> list = baseDao.find(hql.toString(),param);
		List<Object> result = new ArrayList<Object>();
		for(Object obj:list){
			@SuppressWarnings("unchecked")
            Map<String,Object> map = (Map<String,Object>)obj;
			result.add(BeanToMap.transMapToBean(map, Jkzx.class));
		}
		return result;
    }

    /**
     * 根据建设性质查询监控中心个数
     * @param condition
     * @return int
     */
	@Override
	public List<Object> getCountByJsxz(Map<String, Object> condition) {
		String hql="select count(*) as count from Jkzx j where 1=1 and j.jsxz = ? ";
		Object[]objParam={condition.get("jsxz")};
		return baseDao.find(hql.toString(), objParam);
		
	}

	/**
	 * 监控中心 中央投资求和
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getZytzSum() {
		String hql="select sum(zytz) from Jkzx";
		return baseDao.find(hql);
	}

	/**
	 * 监控中心 地方投资求和
	 */
	@Override
	public List<Object> getDftzSum() {
		String hql="select sum(dftz) from Jkzx";
		return baseDao.find(hql);
	}
	
	/**
	  * @Title getTzGroupByArea 
	  * @Description (根据区域分组查询投资) 
	  * @param condition
	  * @return 
	  * @see com.ljdy.BHF.service.JkzxService#getTzGroupByArea(java.util.Map)
	  * @Date  2017年11月8日
	  * @修改历史  
	  *     1. [2017年11月8日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public List<Object> getTzGroupByArea(Map<String, Object> condition) {
        List<Object> param = new ArrayList<Object>();
        //用户角色必传
        String roleName = condition.get("roleName") == null ? "" : (String)condition.get("roleName");
        StringBuffer hql = new StringBuffer("select new map(sum(j.zytz + j.dftz) as dxtz, sum(j.zytz) as zytz, sum(j.dftz) as dftz");
        if("1".equals(roleName)){
            hql.append(",j.szsf as area");
        }else if("2".equals(roleName)){
            hql.append(",j.szcs as area");
        }
        hql.append(") from Jkzx j where 1=1");
        if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and j.bxxm = ? ");
            param.add(condition.get("bxxm"));
        }
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and j.tznd = ? ");
            param.add(condition.get("tznd"));
        }
        if(CommonUtil.validCondition(condition, "jb")){
            hql.append(" and j.jb = ? ");
            param.add(condition.get("jb"));
        }
        if("1".equals(roleName)){
            hql.append(" group by j.szsf");
        }else if("2".equals(roleName)){
            hql.append(" and j.szsf =?");
            param.add(condition.get("szsf"));
            hql.append(" group by j.szcs");
        }
        return baseDao.find(hql.toString(), param);
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
		String hql=" select new map(j.jszt as jszt,count(*) as jsgm) from Jkzx j where 1=1 ";
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
		if(CommonUtil.validCondition(condition, "jb")){
			hqlStr.append(" and j.jb = ? ");
			param.add(condition.get("jb"));
		}
		hqlStr.append(" and j.jszt is not null and j.syzt is null group by j.jszt ");
		
		return baseDao.find(hqlStr.toString(), param);
	}

	/**
	 * 监督实施管理页面建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getManageTz(Map<String, Object> condition) {
		String hql = "select new map(sum(j.zytz) as zytz , sum(j.dftz) as dftz, sum(j.dftz + j.zytz) as dxtz) from Jkzx j where 1=1 ";
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
		if(CommonUtil.validCondition(condition, "jb")){
			hqlStr.append(" and j.jb = ? ");
			param.add(condition.get("jb"));
		}
		hqlStr.append(" and j.jszt is not null and j.syzt is null ");
		return baseDao.find(hqlStr.toString(), param);
	}
	
	/**
	  * @Title getDxtzGroupByXmlb 
	  * @Description (根据项目类别分组获取监控中心单项投资) 
	  * @param condition
	  * @return 
	  * @see com.ljdy.BHF.service.JkzxService#getDxtzGroupByXmlb(java.util.Map)
	  * @Date  2017年11月9日
	  * @修改历史  
	  *     1. [2017年11月9日]创建文件 by 顾冲 
	 *
	 */
	@Override
    public List<Object> getDxtzGroupByXmlb(Map<String, Object> condition) {
        //角色编号必传,省级用户必传所在省份
        String roleName = condition.get("roleName") == null ? "" : (String) condition.get("roleName"); 
        StringBuffer hql=new StringBuffer("select new map(sum(j.zytz + j.dftz) as dxtz, j.xmlb as xmlb) from Jkzx j where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and j.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and j.tznd =?");
            param.add(condition.get("tznd"));
        }
        if(CommonUtil.validCondition(condition, "jb")){
            hql.append(" and j.jb =?");
            param.add(condition.get("jb"));
        }
        
       if("2".equals(roleName)){
           hql.append(" and j.szsf =?");
           param.add(condition.get("szsf"));
        }
        hql.append(" group by j.xmlb");
        return baseDao.find(hql.toString(), param);
    }
	
	/**
	  * @Title getDxtzGroupBySslx 
	  * @Description (根据设施类型分组查询监控中心单项投资) 
	  * @param condition
	  * @return 
	  * @see com.ljdy.BHF.service.JkzxService#getDxtzGroupBySslx(java.util.Map)
	  * @Date  2017年11月9日
	  * @修改历史  
	  *     1. [2017年11月9日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public List<Object> getDxtzGroupBySslx(Map<String, Object> condition) {
        //角色编号必传,省级用户必传所在省份
        String roleName = condition.get("roleName") == null ? "" : (String) condition.get("roleName"); 
        StringBuffer hql=new StringBuffer("select new map(sum(j.zytz + j.dftz) as dxtz, j.sslx as sslx) from Jkzx j where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and j.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and j.tznd =?");
            param.add(condition.get("tznd"));
        }
        if(CommonUtil.validCondition(condition, "jb")){
            hql.append(" and j.jb =?");
            param.add(condition.get("jb"));
        }
        
       if("2".equals(roleName)){
           hql.append(" and j.szsf =?");
           param.add(condition.get("szsf"));
        }
        hql.append(" group by j.sslx");
        return baseDao.find(hql.toString(), param);
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
		String hql=" select new map(j.syzt as syzt,count(*) as jsgm) from Jkzx j where 1=1 ";
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
		if(CommonUtil.validCondition(condition, "jb")){
			hqlStr.append(" and j.jb = ? ");
			param.add(condition.get("jb"));
		}
		hqlStr.append(" and j.jszt ='已审计' and j.syzt in ('良好','损坏','废弃') group by j.syzt ");
		
		return baseDao.find(hqlStr.toString(), param);
	}

	/**
	 * 使用维护模块首页数据建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date[2017-11-23] 创建文件  by 郎川
	 */
	@Override
	public List<Object> getMaintenanceTz(Map<String, Object> condition) {
		String hql = "select new map(sum(j.zytz) as zytz , sum(j.dftz) as dftz, sum(j.dftz + j.zytz) as dxtz) from Jkzx j where 1=1 ";
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
		if(CommonUtil.validCondition(condition, "jb")){
			hqlStr.append(" and j.jb = ? ");
			param.add(condition.get("jb"));
		}
		hqlStr.append(" and j.jszt ='已审计' and j.syzt in ('良好','损坏','废弃') ");
		return baseDao.find(hqlStr.toString(), param);
	}

	@Override
	public void addJkzx(AddJkzx addJkzx) {
            baseDao.save(addJkzx);		
	}
    
    /**
     * @Title getDestroyData
     * @Description (获取损坏项目信息)
     * @param condition
     * @return
     * @see com.ljdy.BHF.service.JkzxService#getDestroyData(java.util.Map)
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDestroyData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(j.id as id, j.xmmc as xmmc, j.xmbh as xmbh, j.syzt as syzt) from Jkzx j where 1=1");
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
