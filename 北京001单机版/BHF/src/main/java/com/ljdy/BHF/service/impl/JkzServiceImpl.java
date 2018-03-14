package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.AddJkz;
import com.ljdy.BHF.model.Jkz;
import com.ljdy.BHF.service.JkzService;
import com.ljdy.BHF.utils.BeanToMap;
import com.ljdy.BHF.utils.CommonUtil;
import com.ljdy.BHF.utils.DateUtils;

/**
 *  监控站service实现类(JkzServiceImpl)
 * @author 郎川
 *@date 2017年9月26日
 */
@Service("jkzService")
public class JkzServiceImpl implements JkzService {

	@Resource
	private BaseDao<Object> baseDao;

	@Override
	public List<Object> getJsgm(Map<String, Object> param_map) {

		String roleName = null;
		StringBuffer hql = new StringBuffer("select new map(count(*) as jsgm,j.jsxz as jsxz ");
		List<Object> list_param = new ArrayList<Object>();
		// 控制层必须传角色名值 先判断角色名是否为空
		if (CommonUtil.validCondition(param_map, "roleName")) {
			roleName = (String) param_map.get("roleName");
		}
		// 根据不同的角色名拼接sql语句
		if ("2".equals(roleName)) {

			hql.append(" ,j.szsf as szsf ,j.szcs as szcs ) ");
		} else {
			hql.append(" ,j.szsf as szsf ) ");
		}
		hql.append(" from Jkz j where 1=1 ");
		if(CommonUtil.validCondition(param_map, "bxxm")){
            hql.append(" and j.bxxm = ?");
            list_param.add(param_map.get("bxxm"));
        }

		// 先判断建设性质是否为空
		if (CommonUtil.validCondition(param_map, "jsxz")) {
			list_param.add(param_map.get("jsxz"));
			hql.append(" and j.jsxz = ? ");
		}

		// 先判断投资年度是否为空
		if (CommonUtil.validCondition(param_map, "tznd")) {
			list_param.add(param_map.get("tznd"));
			hql.append(" and j.tznd = ? ");
		}
		// 根据不同的角色名拼接不同的sql语句
		if ("2".equals(param_map.get("roleName"))) {
			list_param.add(param_map.get("szsf"));
			hql.append(" and j.szsf = ? ");
			hql.append(" group by j.szsf ,j.szcs,j.jsxz");
		} else {
			hql.append(" group by j.szsf,j.jsxz");
		}
		List<Object> list_jkz = baseDao.find(hql.toString(), list_param);
		return list_jkz;
	}

	/**
	 * 新增监控站
	 * @param jkz
	 * 修改历史：
	 *		[2017年9月28日]创建文件	by 徐成
	 */
	public void addJkz(Jkz jkz){
		baseDao.save(jkz);
	}
	/**
	 * 监控站页面数据查询
	 * @param  condition page  rows
	 * @return  List<Object>
	 * @date 
	 */
	@Override
	public List<Object> getDataByCondition(Map<String, Object> condition,
			int page, int rows) {
		String str = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.gdqdgs as gdqdgs,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz,z.xscsxl as xscsxl,z.xkzdgs as xkzdgs,z.ydqdgs as ydqdgs) from Jkz z where 1=1";
		if(page == 0 || rows==0){
			str = "select count(*) from Jkz z where 1=1";
		}
		StringBuffer hql = new StringBuffer(str);
		List<Object> param = new ArrayList<Object>(); 
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
		
		//建设性质
		if(CommonUtil.validCondition(condition, "jsxz")){
			hql.append(" and z.jsxz = ?");
			param.add(condition.get("jsxz"));
		}
		
		//投资年度 
		if(CommonUtil.validCondition(condition, "tznd")){
			hql.append(" and z.tznd = ? ");
			param.add(condition.get("tznd"));
		}

		//所在省份
		if(CommonUtil.validCondition(condition, "szsf")){
			hql.append(" and z.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		//所在市(区)
		if(CommonUtil.validCondition(condition, "szcs")){
			hql.append(" and z.szcs like ?");
			param.add("%"+condition.get("szcs")+"%");
		}
		
		//向上联通情况
		if(CommonUtil.validCondition(condition, "xsltqk")){
			hql.append(" and z.xsltqk = ? ");
			param.add(condition.get("xsltqk"));
		}
		
		//向上联通网络性质
		if(CommonUtil.validCondition(condition, "xsltwlxz")){
			hql.append(" and z.xsltwlxz = ? ");
			param.add(condition.get("xsltwlxz"));
		}
		
		//向上传输线路
		if(CommonUtil.validCondition(condition, "xscsxl")){
			hql.append(" and z.xscsxl = ? ");
			param.add(condition.get("xscsxl"));
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
	 * 传输线路 关联监空站信息
	 * @param szsf szcs szq
	 * @return List<Object> 
	 * @date [2017-10-10] 创建文件 by 郎川
	 * 
	 */
	@Override
	public List<Object> getJkzName(String szsf,String szcs,String szq, String partTag) {
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.gdqdgs as gdqdgs,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz,z.xscsxl as xscsxl,z.xkzdgs as xkzdgs,z.ydqdgs as ydqdgs) from Jkz z where z.szsf =?");
		List<Object> param = new ArrayList<>();
		param.add(szsf);
		if(szcs!=null && !szcs.trim().equals("") && !szcs.trim().equals("null")){
			hql.append(" and z.szcs = ?");
			param.add(szcs);
		}
		if(szq!=null && !szq.trim().equals("") && !szq.trim().equals("null")){
			hql.append(" and z.szq = ?");
			param.add(szq);
		}
		if("2".equals(partTag)){
            hql.append(" and (z.tznd = ? or z.bxxm = '否')");
            param.add(String.valueOf(DateUtils.getYear()));
        }
        if("3".equals(partTag)){
            hql.append(" and z.bxxm = '否'");
        }
        if("4".equals(partTag)){
            hql.append(" and z.syzt in('良好','损坏')");
        }
        hql.append(" and (z.syzt <> '废弃' or z.syzt is null)");
		List<Object> list = baseDao.find(hql.toString(),param);
		List<Object> result = new ArrayList<Object>();
		for(Object obj:list){
			@SuppressWarnings("unchecked")
            Map<String,Object> map = (Map<String,Object>)obj;
			result.add(BeanToMap.transMapToBean(map, Jkz.class));
		}
		return result;
		
	}

	/**
	 * 根据主键id查询数据详情
	 * @param id
	 * @return Object
	 * @date [2017-10-09] 创建文件 by 郎川
	 */
	@Override
	public Object getDataById(String id) {
		String hql="select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.gdqdgs as gdqdgs,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz,z.xscsxl as xscsxl,z.xkzdgs as xkzdgs,z.ydqdgs as ydqdgs) from Jkz z where 1=1 and z.id = ? ";
		Object [] objParam = {id};
		return baseDao.get(hql.toString(), objParam);
	}
/**
 * 更新监控站数据
 * @param jkz 
 * @return void 
 * @date [2017-10-11] 创建文件 by 郎川 
 */
	@Override
	public void updateJkz(Jkz jkz) {
		baseDao.update(jkz);
		
	}

	/**
	 * 根据监控站id查询监控站的名称
	 * @param id
	 * @return Object
	 * @date [2017-10-13] 创建文件 by 郎川
	 */
	@Override
	public Object getJkzNameByJkzId(String id) {
		String hql ="select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.gdqdgs as gdqdgs,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz,z.xscsxl as xscsxl,z.xkzdgs as xkzdgs,z.ydqdgs as ydqdgs) from Jkz z where z.id = ? ";
		Object [] param = {id};
		return baseDao.get(hql, param);
	}
	
	/**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition){
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.gdqdgs as gdqdgs,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz,z.xscsxl as xscsxl,z.xkzdgs as xkzdgs,z.ydqdgs as ydqdgs) from Jkz z where 1=1");
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
	  * @see com.ljdy.BHF.service.JkzService#getData(Map<String, Object>)
	  * @Date  2017年10月18日
	  * @修改历史  
	  *     1. [2017年10月18日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public List<Object> getData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.gdqdgs as gdqdgs,z.xsltqk as xsltqk,z.xsltwlxz as xsltwlxz,z.xscsxl as xscsxl,z.xkzdgs as xkzdgs,z.ydqdgs as ydqdgs) from Jkz z where 1=1");
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
        if(CommonUtil.validCondition(condition, "jszt")){
        	hql.append(" and z.jszt = ? ");
        	param.add(condition.get("jszt"));
        }
        List<Object> list = baseDao.find(hql.toString(),param);
		List<Object> result = new ArrayList<Object>();
		for(Object obj:list){
			@SuppressWarnings("unchecked")
            Map<String,Object> map = (Map<String,Object>)obj;
			result.add(BeanToMap.transMapToBean(map, Jkz.class));
		}
		return result;
    }

    /**
     * 监控站根据建设性质统计个数
     * @param condition
     * @return List<Object>
     * @date [2017-10-23] 创建文件 by 郎川
     */
	@Override
	public List<Object> getCountByJsxz(Map<String, Object> condition) {
		String hql="select count(*) as count from Jkz j where 1=1 and j.jsxz = ? ";
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
		String hql="select sum(zytz) from Jkz";
		return baseDao.find(hql);
	}
	
	/**
	 * 监控站 求和地方投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDftzSum() {
		String hql="select sum(dftz) from Jkz";
		return baseDao.find(hql);
	}

	/**
	 * 监督实施管理页面数据查询
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getManageData(Map<String, Object> condition) {
		List<Object>param = new ArrayList<Object>();
		String hql=" select new map(j.jszt as jszt ,count(*) as jsgm) from Jkz j where 1=1 ";
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
		hqlStr.append(" and j.jszt is not null and j.syzt is null group by j.jszt ");
		
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
		String hql = "select new map(sum(j.zytz) as zytz , sum(j.dftz) as dftz, sum(j.dftz + j.zytz) as dxtz) from Jkz j where 1=1 ";
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
      * @Title getTzGroupByArea 
      * @Description (根据区域分组查询投资) 
      * @param condition
      * @return 
      * @see com.ljdy.BHF.service.JkzService#getTzGroupByArea(java.util.Map)
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
       hql.append(") from Jkz j where 1=1");
       if(CommonUtil.validCondition(condition, "bxxm")){
           hql.append(" and j.bxxm = ? ");
           param.add(condition.get("bxxm"));
       }
       if(CommonUtil.validCondition(condition, "tznd")){
           hql.append(" and j.tznd = ? ");
           param.add(condition.get("tznd"));
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
     * @Title getDxtzGroupByXmlb 
     * @Description (根据项目类别分组获取监控站单项投资) 
     * @param condition
     * @return 
     * @see com.ljdy.BHF.service.JkzService#getDxtzGroupByXmlb(java.util.Map)
     * @Date  2017年11月9日
     * @修改历史  
     *     1. [2017年11月9日]创建文件 by 顾冲 
    *
    */
    @Override
    public List<Object> getDxtzGroupByXmlb(Map<String, Object> condition) {
        //角色编号必传,省级用户必传所在省份
        String roleName = condition.get("roleName") == null ? "" : (String) condition.get("roleName"); 
        StringBuffer hql=new StringBuffer("select new map(sum(j.zytz + j.dftz) as dxtz, j.xmlb as xmlb) from Jkz j where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and j.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and j.tznd =?");
            param.add(condition.get("tznd"));
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
      * @Description (根据设施类型分组查询监控站单项投资) 
      * @param condition
      * @return 
      * @see com.ljdy.BHF.service.JkzService#getDxtzGroupBySslx(java.util.Map)
      * @Date  2017年11月9日
      * @修改历史  
      *     1. [2017年11月9日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getDxtzGroupBySslx(Map<String, Object> condition) {
        //角色编号必传,省级用户必传所在省份
        String roleName = condition.get("roleName") == null ? "" : (String) condition.get("roleName"); 
        StringBuffer hql=new StringBuffer("select new map(sum(j.zytz + j.dftz) as dxtz, j.sslx as sslx) from Jkz j where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and j.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and j.tznd =?");
            param.add(condition.get("tznd"));
        }
       if("2".equals(roleName)){
           hql.append(" and j.szsf =?");
           param.add(condition.get("szsf"));
        }
        hql.append(" group by j.sslx");
        return baseDao.find(hql.toString(), param);
    }
    
    /**
      * @Title getTz 
      * @Description (获取投资) 
      * @param param
      * @return 
      * @see com.ljdy.BHF.service.JkzService#getTz(java.util.Map)
      * @Date  2017年11月20日
      * @修改历史  
      *     1. [2017年11月20日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getTz(Map<String, Object> param) {
        String roleName = null;
        List<Object> list_param = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer(
                "select new map(sum(j.dftz+j.zytz) as dxtz, sum(j.dftz) as dftz, sum(j.zytz) as zytz, j.jsxz as jsxz) from Jkz j where 1=1");
        if(CommonUtil.validCondition(param, "bxxm")){
            hql.append(" and j.bxxm = ?");
            list_param.add(param.get("bxxm"));
        }
        // 控制层必须传角色名值 先判断角色名是否为空
        if (CommonUtil.validCondition(param, "roleName")) {
            roleName = (String) param.get("roleName");
        }
        if ("2".equals(roleName)) {
            hql.append(" and j.szsf = ? ");
            list_param.add(param.get("szsf"));
        }
        // 先判断投资年度是否为空
        if (CommonUtil.validCondition(param, "tznd")) {
            list_param.add(param.get("tznd"));
            hql.append(" and j.tznd = ? ");
        }
        hql.append(" group by j.jsxz");
        return baseDao.find(hql.toString(), list_param);
    }
    
    /**
      * @Title updateZlgs 
      * @Description (修改监控站中项目子类个数) 
      * @param zdm 字段名
      * @param jkz_id 监控站id
      * @param zlgs 子类个数
      * @see com.ljdy.BHF.service.JkzService#updateZlgs(java.lang.String, java.lang.String, java.lang.Long)
      * @Date  2017年11月20日
      * @修改历史  
      *     1. [2017年11月20日]创建文件 by 顾冲 
     *
     */
    @Override
    public void updateZlgs(String zdm, String jkz_id, Long zlgs) {
        StringBuffer sql = new StringBuffer("update jkz set ");
        if("xkzdgs".equals(zdm)){
            sql.append("xkzdgs = ?");
        }
        if("ydqdgs".equals(zdm)){
            sql.append("ydqdgs = ?");
        }
        if("gdqdgs".equals(zdm)){
            sql.append("gdqdgs = ?");
        }
        sql.append(" where id = ? ");
        baseDao.updateBySql(sql.toString(),new Object[]{zlgs, jkz_id});
    }
    
    /**
      * @Title updateZlgs 
      * @Description (根据jkz_id、gdqdgs、ydqdgs修改监控站中项目移动前端个数、固定前端个数) 
      * @param jkz_id
      * @param gdqdgs
      * @param ydqdgs 
      * @see com.ljdy.BHF.service.JkzService#updateZlgs(java.lang.String, java.lang.Long, java.lang.Long)
      * @Date  2017年11月20日
      * @修改历史  
      *     1. [2017年11月20日]创建文件 by 顾冲 
     *
     */
    @Override
    public void updateZlgs(String jkz_id, Long gdqdgs, Long ydqdgs) {
        StringBuffer sql = new StringBuffer("update jkz set gdqdgs = ?,ydqdgs = ? where id = ? ");
        baseDao.updateBySql(sql.toString(),new Object[]{gdqdgs ,ydqdgs, jkz_id});
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
		String hql=" select new map(j.syzt as syzt ,count(*) as jsgm) from Jkz j where 1=1 ";
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
		String hql = "select new map(sum(j.zytz) as zytz , sum(j.dftz) as dftz, sum(j.dftz + j.zytz) as dxtz) from Jkz j where 1=1 ";
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
	public void addJkz(AddJkz addJkz) {
		baseDao.save(addJkz);
		
	}

	
	/**
	  * @Title getDataByIds 
	  * @Description (根据id查询) 
	  * @param idList
	  * @return 
	  * @see com.ljdy.BHF.service.JkzService#getDataByIds(java.util.List)
	  * @Date  2017年12月4日
	  * @修改历史  
	  *     1. [2017年12月4日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public List<Object> getDataByIds(List<Object> idList) {
        if(idList == null || idList.isEmpty()){
            return new ArrayList<Object>();
        }
        StringBuffer hql = new StringBuffer("select new map(bxxm as bxxm, syzt as syzt) from Jkz where id in(");
        List<Object> param = new ArrayList<Object>();
        for (int i = 0; i < idList.size(); i++) {
            if(i < idList.size() - 1){
                hql.append("?, ");
                param.add(idList.get(i));
            }else{
                hql.append("?");
                param.add(idList.get(i));
            }
        }
        hql.append(")");
        return baseDao.find(hql.toString(), param);
    }

    
    /**
     * @Title getDestroyData
     * @Description (获取损坏项目信息)
     * @param condition
     * @return
     * @see com.ljdy.BHF.service.JkzService#getDestroyData(java.util.Map)
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDestroyData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(j.id as id, j.xmmc as xmmc, j.xmbh as xmbh, j.syzt as syzt) from Jkz j where 1=1");
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
