package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.AddLzz;
import com.ljdy.BHF.model.Lzz;
import com.ljdy.BHF.service.LzzService;
import com.ljdy.BHF.utils.BeanToMap;
import com.ljdy.BHF.utils.CommonUtil;

/**
 * 拦阻栏service实现类(LzzServiceImpl)
 * 
 * @author 郎川
 * @date 2017年9月26日 // select sum(l.jsgm) as jsgm,j.szsf,j.jsxz,l.lzzlx from lzz l , //jbxx j where l.bzm=j.bzm group by j.szsf,j.jsxz,l.lzzlx
 */
@Service("lzzService")
public class LzzServiceImpl implements LzzService {
    @Resource
    private BaseDao<Object> baseDao;

    /**
     * 统计栏阻桩的建设规模
     * 
     * @param map_param
     * @return List<Object>
     * @date 2017-09-27
     */
    @Override
    public List<Object> getJsgm(Map<String, Object> map_param) {
        String roleName = null;
        StringBuffer hql = new StringBuffer("select new map(sum(l.jsgm) as jsgm ,l.jsxz as jsxz ,l.lzzlx as lzzlx");
        List<Object> list_param = new ArrayList<Object>();
        // 控制层必须传角色值 先判断角色名是否为空
        if (CommonUtil.validCondition(map_param, "roleName")) {
            roleName = (String) map_param.get("roleName");
        }
        // 根据不同的角色名拼接不同的sql语句
        if ("2".equals(roleName)) {

            hql.append(" , l.szsf as szsf,l.szcs as szcs) ");
        } else {
            hql.append(" , l.szsf as szsf) ");
        }
        hql.append(" from Lzz l where 1=1 ");
        if(CommonUtil.validCondition(map_param, "bxxm")){
            hql.append(" and l.bxxm = ?");
            list_param.add(map_param.get("bxxm"));
        }

        // 判断投资年度是否为空
        if (CommonUtil.validCondition(map_param, "tznd")) {
            list_param.add(map_param.get("tznd"));
            hql.append(" and l.tznd = ? ");
        }
        // 根据不同角色名拼接不同分组查询
        if ("2".equals(map_param.get("roleName"))) {
            list_param.add(map_param.get("szsf"));
            hql.append(" and l.szsf = ? ");
            hql.append(" group by l.szsf , l.szcs,l.jsxz,l.lzzlx");
        } else {
            hql.append(" group by l.szsf,l.jsxz,l.lzzlx");
        }

        return baseDao.find(hql.toString(), list_param);

    }

    /**
     * 统计栏阻桩的投资
     * 
     * @param map_param
     * @return List<Object>
     * @date 2017-09-28
     */
    @Override
    public List<Object> getTz(Map<String, Object> map_param) {
        String roleName = null;
        List<Object> list_param = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("select new map(sum(l.dftz+l.zytz) as dxtz, sum(l.dftz) as dftz, sum(l.zytz) as zytz, l.lzzlx as lzzlx, l.jsxz as jsxz) from Lzz l where 1=1 ");
        if(CommonUtil.validCondition(map_param, "bxxm")){
            hql.append(" and l.bxxm = ?");
            list_param.add(map_param.get("bxxm"));
        }
        // 控制层必须传角色值 先判断角色名是否为空
        if (CommonUtil.validCondition(map_param, "roleName")) {
            roleName = (String) map_param.get("roleName");
        }
        // 判断投资年度是否为空
        if (CommonUtil.validCondition(map_param, "tznd")) {
            list_param.add(map_param.get("tznd"));
            hql.append(" and l.tznd = ? ");
        }
        // 如果当前是省级用户登陆 就需要传省份值
        if ("2".equals(roleName)) {
            hql.append(" and l.szsf = ? ");
            list_param.add(map_param.get("szsf"));
        }
        hql.append(" group by l.jsxz , l.lzzlx, l.jsxz");

        return baseDao.find(hql.toString(), list_param);
    }

    /**
     * 新增拦阻桩
     * 
     * @param lzz
     *            修改历史： [2017年9月28日]创建文件 by 徐成
     */
    @Override
    public void addLzz(Lzz lzz) {
        baseDao.save(lzz);
    }

    /**
     * @Title getDataByCondition
     * @Description (这里用一句话描述重构方法的作用)
     * @param condition
     *            查询条件集合
     * @param page
     *            当前页码
     * @param rows
     *            每页条数
     * @return
     * @see com.ljdy.BHF.service.LzzService#getDataByCondition(java.util.Map, int, int)
     * @Date 2017年9月29日
     * @修改历史 1. [2017年9月29日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDataByCondition(Map<String, Object> condition, int page, int rows) {
        String str = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.lzzlx as lzzlx) from Lzz z where 1=1";
        if (page == 0 || rows == 0) {
            str = "select count(*) from Lzz z where 1=1";
        }
        StringBuffer hql = new StringBuffer(str);
        List<Object> param = new ArrayList<Object>();
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

        // TODO 使用状态未添加

        // 拦阻桩类型
        if (CommonUtil.validCondition(condition, "lzzlx")) {
            hql.append(" and z.lzzlx =?");
            param.add(condition.get("lzzlx"));
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
     * @Title getDataById
     * @Description (根据主键获取数据)
     * @param id
     * @return
     * @see com.ljdy.BHF.service.LzzService#getDataById(java.lang.String)
     * @Date 2017年10月9日
     * @修改历史 1. [2017年10月9日]创建文件 by 顾冲
     */
    public Object getDataById(String id) {
        String hql = " select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.lzzlx as lzzlx) from Lzz z where z.id = ?";
        Object[] param = { id };
        return baseDao.get(hql.toString(), param);
    }
    
    /**
      * @Title updateData 
      * @Description (更新拦阻桩数据) 
      * @param lzz 
      * @see com.ljdy.BHF.service.LzzService#updateData(com.ljdy.BHF.model.Lzz)
      * @Date  2017年10月11日
      * @修改历史  
      *     1. [2017年10月11日]创建文件 by 顾冲 
     *
     */
    @Override
    public void updateData(Lzz lzz) {
        baseDao.update(lzz);
    };
    
    /**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition){
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.lzzlx as lzzlx) from Lzz z where 1=1");
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

        /*List<Object> list = baseDao.find(hql.toString(), param);
        List<Object> result = new ArrayList<Object>();
        for (Object obj:list) {
            Map<String,Object> map = (Map<String,Object>)obj;
            result.add(BeanToMap.transMapToBean(map,Lzz.class));
        }*/
        return baseDao.find(hql.toString(), param);
	}
	
	/**
	 * 省级报表数据
	 * @param condition
	 * @return
	 */
	@Override
	public List<Object> getData(Map<String, Object> condition) {
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.lzzlx as lzzlx) from Lzz z where 1=1 ");
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
			hql.append(" and z.tznd =? ");
			param.add(condition.get("tznd"));
		}
		
		List<Object> list = baseDao.find(hql.toString(),param);
		List<Object> result = new ArrayList<Object>();
		for(Object obj:list){
			@SuppressWarnings("unchecked")
            Map<String,Object> map = (Map<String,Object>)obj;
			result.add(BeanToMap.transMapToBean(map, Lzz.class));
		}
		return result;
	}
	
	/**
     * 根据建设性质求和建设规模数量
     * @param condition
     * @return List<Object>
     * @date [2017-10-24] 创建文件 by 郎川
     */
	@Override
	public List<Object> getSumByJsxz(Map<String, Object> condition) {
		String hql="select sum(jsgm) from Lzz l where 1=1 and l.jsxz = ? ";
		Object[] objParam={condition.get("jsxz")};
		
		return baseDao.find(hql, objParam);
	}

	/**
	 * 铁栅栏求和中央投资
	 * @return List<Object>
	 * @date [2017-10-24] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getZytzSum() {
		String hql="select sum(zytz) from Lzz";
		return baseDao.find(hql);
	}

	/**
	 * 铁栅栏求和地方投资
	 * @return List<Object>
	 * @date [2017-10-24] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDftzSum() {
		String hql="select sum(dftz) from Lzz";
		return baseDao.find(hql);
		
	}
	
	/**
	  * @Title getTzGroupByArea 
	  * @Description (根据地区分组获取投资) 
	  * @param condition
	  * @return 
	  * @see com.ljdy.BHF.service.LzzService#getTzGroupByArea(java.util.Map)
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
        StringBuffer hql = new StringBuffer("select new map(sum(l.zytz + l.dftz) as dxtz, sum(l.zytz) as zytz, sum(l.dftz) as dftz");
        if("1".equals(roleName)){
            hql.append(",l.szsf as area");
        }else if("2".equals(roleName)){
            hql.append(",l.szcs as area");
        }
        hql.append(") from Lzz l where 1=1");
        if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and l.bxxm = ? ");
            param.add(condition.get("bxxm"));
        }
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and l.tznd = ? ");
            param.add(condition.get("tznd"));
        }
        if(CommonUtil.validCondition(condition, "jsxz")){
            hql.append(" and l.jsxz = ? ");
            param.add(condition.get("jsxz"));
        }
        if("1".equals(roleName)){
            hql.append(" group by l.szsf");
        }else if("2".equals(roleName)){
            hql.append(" and l.szsf =?");
            param.add(condition.get("szsf"));
            hql.append(" group by l.szcs");
        }
        return baseDao.find(hql.toString(), param);
    }
	
	/**
	 * 监督实施管理页面首页数据查询
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件 by 汤凌宇
	 */
	@Override
	public List<Object> getManageData(Map<String, Object> condition) {
		List<Object>param = new ArrayList<Object>();
		String hql=" select new map(l.jszt as jszt ,sum(l.jsgm) as jsgm)  from Lzz l where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and l.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and l.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and l.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and l.jszt is not null and l.syzt is null group by l.jszt ");
		
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
		String hql = "select new map(sum(l.zytz) as zytz , sum(l.dftz) as dftz, sum(l.dftz + l.zytz) as dxtz) from Lzz l where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and l.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and l.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and l.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and l.jszt is not null and l.syzt is null ");
		return baseDao.find(hqlStr.toString(), param);
	}
	
	/**
	  * @Title getDxtzGroupByXmlb 
	  * @Description (根据项目类别分组获取拦阻桩单项投资) 
	  * @param condition
	  * @return 
	  * @see com.ljdy.BHF.service.LzzService#getDxtzGroupByXmlb(java.util.Map)
	  * @Date  2017年11月9日
	  * @修改历史  
	  *     1. [2017年11月9日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public List<Object> getDxtzGroupByXmlb(Map<String, Object> condition) {
        //角色编号必传,省级用户必传所在省份
        String roleName = condition.get("roleName") == null ? "" : (String) condition.get("roleName"); 
        StringBuffer hql=new StringBuffer("select new map(sum(l.zytz + l.dftz) as dxtz, l.xmlb as xmlb) from Lzz l where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and l.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and l.tznd =?");
            param.add(condition.get("tznd"));
        }
        if(CommonUtil.validCondition(condition, "jsxz")){
            hql.append(" and l.jsxz =?");
            param.add(condition.get("jsxz"));
        }
        
       if("2".equals(roleName)){
           hql.append(" and l.szsf =?");
           param.add(condition.get("szsf"));
        }
        hql.append(" group by l.xmlb");
        return baseDao.find(hql.toString(), param);
    }
    
    
    /**
      * @Title getDxtzGroupBySslx 
      * @Description (根据设施类型分组查询拦阻桩单项投资) 
      * @param condition
      * @return 
      * @see com.ljdy.BHF.service.LzzService#getDxtzGroupBySslx(java.util.Map)
      * @Date  2017年11月9日
      * @修改历史  
      *     1. [2017年11月9日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getDxtzGroupBySslx(Map<String, Object> condition) {
        //角色编号必传,省级用户必传所在省份
        String roleName = condition.get("roleName") == null ? "" : (String) condition.get("roleName"); 
        StringBuffer hql=new StringBuffer("select new map(sum(l.zytz + l.dftz) as dxtz, l.sslx as sslx) from Lzz l where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and l.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and l.tznd =?");
            param.add(condition.get("tznd"));
        }
        if(CommonUtil.validCondition(condition, "jsxz")){
            hql.append(" and l.jsxz =?");
            param.add(condition.get("jsxz"));
        }
        
       if("2".equals(roleName)){
           hql.append(" and l.szsf =?");
           param.add(condition.get("szsf"));
        }
        hql.append(" group by l.sslx");
        return baseDao.find(hql.toString(), param);
    }

    /**
     * 使用维护模块首页数据查询
     * @param condition
     * @return List<Object>
     * @date [2017-11-22] 创建 文件  by 郎川
     */
	@Override
	public List<Object> getMaintenanceData(Map<String, Object> condition) {
		List<Object>param = new ArrayList<Object>();
		String hql=" select new map(l.syzt as syzt ,sum(l.jsgm) as jsgm)  from Lzz l where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and l.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and l.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and l.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and l.jszt ='已审计' and l.syzt in ('良好','损坏','废弃') group by l.syzt ");
		
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
		String hql = "select new map(sum(l.zytz) as zytz , sum(l.dftz) as dftz, sum(l.dftz + l.zytz) as dxtz) from Lzz l where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and l.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and l.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and l.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and l.jszt ='已审计' and l.syzt in ('良好','损坏','废弃')");
		return baseDao.find(hqlStr.toString(), param);
	}

	@Override
	public void addlzz(AddLzz addLzz) {
              baseDao.save(addLzz);		
	}	
	
	 /**
     * @Title getDestroyData
     * @Description (获取损坏项目信息)
     * @param condition
     * @return
     * @see com.ljdy.BHF.service.LzzService#getDestroyData(java.util.Map)
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDestroyData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(l.id as id, l.xmmc as xmmc, l.xmbh as xmbh, l.jsgm as jsgm, l.syzt as syzt) from Lzz l where 1=1");
        List<Object> param = new ArrayList<Object>();
        if (CommonUtil.validCondition(condition, "id")) {
            hql.append(" and l.id = ? ");
            param.add(condition.get("id"));
        }else{
            if (CommonUtil.validCondition(condition, "szsf")) {
                hql.append(" and l.szsf like ? ");
                param.add("%" + condition.get("szsf") + "%");
            }
            if (CommonUtil.validCondition(condition, "syzt")) {
                hql.append(" and l.syzt = ? ");
                param.add(condition.get("syzt"));
            }
        }
        return baseDao.find(hql.toString(), param);
    }
}
