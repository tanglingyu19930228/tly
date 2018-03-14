package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.AddBjxl;
import com.ljdy.BHF.model.Bjxl;
import com.ljdy.BHF.service.BjxlService;
import com.ljdy.BHF.utils.BeanToMap;
import com.ljdy.BHF.utils.CommonUtil;

/**
 * 报警装置service实现类(BjxlServiceImpl)
 * 
 * @author 郎川
 * @date 2017年9月26日
 */
@Service("bjxlService")
public class BjxlServiceImpl implements BjxlService {

    @Resource
    private BaseDao<Object> baseDao;

    @Override
    public List<Object> getJsgm(Map<String, Object> paramMap) {
        
        String roleName = null;
        StringBuffer hql = new StringBuffer("select new map(sum(b.jsgm) as jsgm ");
        List<Object> param_list = new ArrayList<Object>();

        // 控制层必须传角色名 先判断角色名是否为空
        if (CommonUtil.validCondition(paramMap, "roleName")) {
            roleName = (String) paramMap.get("roleName");
        }
        // 根据不同的角色名拼接sql语句 当角色是省级的时候 就需要所在省份
        if ("2".equals(roleName)) {
            hql.append(" ,b.szsf as szsf ,b.szcs as szcs )");
        } else {
            hql.append(" ,b.szsf as szsf )");
        }
        hql.append(" from Bjxl b where 1=1 ");
        
        if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and b.bxxm = ?");
            param_list.add(paramMap.get("bxxm"));
        }

        // 判断投资年度是否为空
        if (CommonUtil.validCondition(paramMap, "tznd")) {
            param_list.add(paramMap.get("tznd"));
            hql.append(" and b.tznd=? ");

        }
        // 根据不同的角色名进行分组查询
        if ("2".equals(paramMap.get("roleName"))) {
            param_list.add(paramMap.get("szsf"));
            hql.append(" and b.szsf = ? ");
            hql.append(" group by b.szsf, b.szcs");
        } else {
            hql.append(" group by b.szsf ");
        }

        List<Object> list_bjxl = baseDao.find(hql.toString(), param_list);
        return list_bjxl;
    }

    /**
     * @author 郎川 报警线路投资
     * @return List<Object>
     * @param Map
     *            <String,Object>paramMap
     * @date 2017-09-28
     */
    @Override
    public List<Object> getTz(Map<String, Object> paramMap) {
        // select sum(j.dftz+j.zytz) as all_tz from bjxl b , jbxx j where b.bzm=j.bzm
        String roleName = null;
        List<Object> param_list = new ArrayList<Object>();

        StringBuffer hql = new StringBuffer("select new map(sum(b.dftz+b.zytz) as dxtz, sum(b.dftz) as dftz, sum(b.zytz) as zytz) from Bjxl b where 1=1 ");
        if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and b.bxxm = ?");
            param_list.add(paramMap.get("bxxm"));
        }
        // 控制层必须传角色名 先判断角色名是否为空
        if (CommonUtil.validCondition(paramMap, "roleName")) {
            roleName = (String) paramMap.get("roleName");
        }
        // 根据不同的角色名拼接hql语句 当角色是省份的时候 要传当前省份
        // hql.append(" from bjxl b where 1=1 ");

        // 判断投资年度是否为空
        if (CommonUtil.validCondition(paramMap, "tznd")) {
            param_list.add(paramMap.get("tznd"));
            hql.append(" and b.tznd=? ");

        }
        if ("2".equals(roleName)) {
            hql.append(" and b.szsf = ? ");
            param_list.add(paramMap.get("szsf"));
        }
        return baseDao.find(hql.toString(), param_list);

    }

    /**
     * 新增报警线路
     * 
     * @param bjxl
     *            修改历史： [2017年9月28日]创建文件 by 徐成
     */
    public void addBjxl(Bjxl bjxl) {
        baseDao.save(bjxl);
    }

    /**
     * @Title getDataByCondition
     * @Description (多条件查询数据，带分页)
     * @param condition
     *            查询条件集合
     * @param page
     *            当前页码
     * @param rows
     *            每页条数
     * @return
     * @see com.ljdy.BHF.service.BjxlService#getDataByCondition(java.util.Map, int, int)
     * @Date 2017年9月29日
     * @修改历史 1. [2017年9月29日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDataByCondition(Map<String, Object> condition, int page, int rows) {
        String str = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.sbpp as sbpp) from Bjxl z where 1=1";
        if (page == 0 || rows == 0) {
            str = "select count(*) from Bjxl z where 1=1";
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

       
        // 停机坪类型
        if (CommonUtil.validCondition(condition, "tjplx")) {
            hql.append(" and z.tjplx =?");
            param.add(condition.get("tjplx"));
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
			hql.append(" and z.jszt = ? ");
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
				hql.append(" and z.jszt ='已审计' and z.syzt in('良好','损坏','废弃')");
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
     * @see com.ljdy.BHF.service.BjxlService#getDataById(java.lang.String)
     * @Date 2017年10月9日
     * @修改历史 1. [2017年10月9日]创建文件 by 顾冲
     */
    public Object getDataById(String id) {
        String hql = "select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.sbpp as sbpp) from Bjxl z where z.id = ?";
        Object[] param = { id };
        return baseDao.get(hql.toString(), param);
    }
    
    /**
      * @Title updateData 
      * @Description (更新报警线路数据) 
      * @param bjxl 
      * @see com.ljdy.BHF.service.BjxlService#updateData(com.ljdy.BHF.model.Bjxl)
      * @Date  2017年10月11日
      * @修改历史  
      *     1. [2017年10月11日]创建文件 by 顾冲 
     *
     */
    @Override
    public void updateData(Bjxl bjxl) {
        baseDao.update(bjxl);
    };
    
    /**
     * 按条件导出数据
     * @param condition
     * @return
     */
    public List<Object> exportByCondition(Map<String,Object> condition){
    	StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.sbpp as sbpp) from Bjxl z where 1=1");
		List<Object> param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hql.append(" and z.szsf =?");
			param.add(condition.get("szsf"));
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and z.bxxm = ?");
            param.add(condition.get("bxxm"));
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
      * @see com.ljdy.BHF.service.BjxlService#getData(java.lang.String, java.lang.String, java.lang.String)
      * @Date  2017年10月18日
      * @修改历史  
      *     1. [2017年10月18日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.jsdd as jsdd,z.jsgm as jsgm,z.sbpp as sbpp) from Bjxl z where 1=1");
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
        List<Object> obj=baseDao.find(hql.toString(), param);
        List<Object> list=new ArrayList<Object>();
        for (Object o : obj) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) o;
            list.add(BeanToMap.transMapToBean(map, Bjxl.class));
        }
       return list;  
    }
    
    /**
     * 根据建设性质求和建设规模数量
     * @param condition
     * @return List<Object>
     * @date [2017-10-24] 创建文件 by 郎川
     */
	@Override
	public List<Object> getSumByJsxz(Map<String, Object> condition) {
		String hql="select sum(jsgm) from Bjxl b where 1=1 and b.jsxz = ? ";
		Object[] objParam={condition.get("jsxz")};
		
		return baseDao.find(hql, objParam);
	}

	/**
	 * 报警线路求和中央投资
	 * @return List<Object>
	 * @date [2017-10-24] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getZytzSum() {
		String hql="select sum(zytz) from Bjxl";
		return baseDao.find(hql);
	}

	/**
	 *报警线路求和地方投资
	 * @return List<Object>
	 * @date [2017-10-24] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDftzSum() {
		String hql="select sum(dftz) from Bjxl";
		return baseDao.find(hql);
		
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
		String hql=" select new map(b.jszt as jszt ,sum(b.jsgm) as jsgm) from Bjxl b where 1=1 ";
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
		String hql = "select new map(sum(b.zytz) as zytz , sum(b.dftz) as dftz, sum(b.dftz + b.zytz) as dxtz) from Bjxl b where 1=1 ";
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

	@Override
	public List<Object> getMaintenanceData(Map<String, Object> condition) {
		List<Object>param = new ArrayList<Object>();
		String hql=" select new map(b.syzt as syzt ,sum(b.jsgm) as jsgm) from Bjxl b where 1=1 ";
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
		String hql = "select new map(sum(b.zytz) as zytz , sum(b.dftz) as dftz, sum(b.dftz + b.zytz) as dxtz) from Bjxl b where 1=1 ";
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
	public void addBjxl(AddBjxl addBjxl) {
		baseDao.save(addBjxl);
		
	}	

	
    /**
     * @Title getDestroyData
     * @Description (获取损坏项目信息)
     * @param condition
     * @return
     * @see com.ljdy.BHF.service.BjxlService#getDestroyData(java.util.Map)
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDestroyData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(b.id as id, b.xmmc as xmmc, b.xmbh as xmbh, b.jsgm as jsgm, b.syzt as syzt) from Bjxl b where 1=1");
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
