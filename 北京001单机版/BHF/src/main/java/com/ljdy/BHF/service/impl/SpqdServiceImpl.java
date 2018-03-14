package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.AddSpqd;
import com.ljdy.BHF.model.Spqd;
import com.ljdy.BHF.service.SpqdService;
import com.ljdy.BHF.utils.BeanToMap;
import com.ljdy.BHF.utils.CommonUtil;
/**
 * @ClassName SpqdServiceImpl 
 * @Description (指挥监控设施----视频前端service实现类) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月26日 上午10:20:24 
 * @修改历史  
 *     1. [2017年9月26日]创建文件 by 顾冲
 */
@Service("spqdService")
public class SpqdServiceImpl implements SpqdService{
    @Resource
    private BaseDao<Object> baseDao;
    /**
      * @Title getJsgm 
      * @Description (获取建设规模) 
      * @param paramMap
      * @return 
      * @see com.ljdy.BHF.service.SpqdService#getJsgm(java.util.Map)
      * @Date  2017年9月26日
      * @修改历史  
      *     1. [2017年9月26日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getJsgm(Map<String, Object> paramMap) {
        //用户角色必传(1国家，2省级)，省级用户必传所在省份 
        String roleName = (String)paramMap.get("roleName");
        List<Object> param = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("select new map(count(*) as jsgm, sum(s.dftz) as dftz, sum(s.zytz) as zytz,s.sblx as sblx,s.jsxz as jsxz");
        if("2".equals(roleName)){
            hql.append(",s.szcs as szcs");
        }else{
            hql.append(",s.szsf as szsf");
        }
        hql.append(") from Spqd s where 1=1");
        if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and s.bxxm = ?");
            param.add(paramMap.get("bxxm"));
        }
        if(CommonUtil.validCondition(paramMap, "jsxz")){
            hql.append(" and s.jsxz = ?");
            param.add(paramMap.get("jsxz"));
        }
        
        if(CommonUtil.validCondition(paramMap, "tznd")){
            hql.append(" and s.tznd = ?");
            param.add(paramMap.get("tznd"));
        }
        
        if("2".equals(roleName)){
            //省级 根据所在城市、设备类型和建设性质分组
            hql.append(" and s.szsf = ?");
            param.add(paramMap.get("szsf"));
            hql.append(" group by s.szcs,s.sblx,s.jsxz");
        }else{
            //国家级 根据所在省份、设备类型和建设性质分组
            hql.append(" group by s.szsf,s.sblx,s.jsxz");
        }
       
        return baseDao.find(hql.toString(), param);
    }
    /**
     * @Title getTz 
     * @Description (获取投资) 
     * @param paramMap
     * @return 
     * @see com.ljdy.BHF.service.SpqdService#getTz(java.util.Map)
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
       StringBuffer hql = new StringBuffer("select new map(sum(s.zytz+s.dftz) as dxtz, sum(s.dftz) as dftz, sum(s.zytz) as zytz, s.jsxz as jsxz, s.sblx as sblx) from Spqd s where 1=1");
       if(CommonUtil.validCondition(paramMap, "bxxm")){
           hql.append(" and s.bxxm = ?");
           param.add(paramMap.get("bxxm"));
       }
       if("2".equals(roleName)){
           hql.append(" and s.szsf = ?");
           param.add(paramMap.get("szsf"));
       }
       if(CommonUtil.validCondition(paramMap, "tznd")) {
           param.add(paramMap.get("tznd"));
           hql.append(" and s.tznd = ?");
       }
       hql.append(" group by s.jsxz,s.sblx");
       return baseDao.find(hql.toString(), param);
   }

    /**
     * 新增视频前端
     * @param spqd
     * 修改历史：
	 *		[2017年9月28日]创建文件	by 徐成
     */
    @Override
    public void addSpqd(Spqd spqd){
    	baseDao.save(spqd);
    }
    
    /**
     * 新增视频前端数据查询
     * @param condition  page  rows
     * @return List<Object>  
     * @date [2017-09-29]  新增文件 by 郎川
     * 
     */
	@Override
	public List<Object> getDataByCondition(Map<String, Object> condition,
			int page, int rows) {
		String str="select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.fzdd as fzdd,z.csfs as csfs,z.sblx as sblx,z.jkz_id as jkz_id,z.jkz_name as jkz_name) from Spqd z where 1=1";
		if(page==0 || rows == 0){
			str="select count(*) from Spqd z where 1=1";
		}
		StringBuffer hql = new StringBuffer(str);
		List<Object>param = new ArrayList<Object>();
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
			hql.append(" and z.bjfx like ? ");
			param.add("%"+condition.get("bjfx")+"%");
		}
		
		//地形类别
		if(CommonUtil.validCondition(condition, "dxlb")){
			hql.append(" and z.dxlb  like ? ");
			param.add("%"+condition.get("dxlb")+"%");
		}
		
		//建设性质
		if(CommonUtil.validCondition(condition, "jsxz")){
			hql.append(" and z.jsxz = ? ");
			param.add(condition.get("jsxz"));
		}
		
		//投资年度
		if(CommonUtil.validCondition(condition, "tznd")){
			hql.append(" and z.tznd = ? ");
			param.add(condition.get("tznd"));
		}
		
		//设备类型
		if(CommonUtil.validCondition(condition, "sblx")){
			hql.append(" and z.sblx = ? ");
			param.add(condition.get("sblx"));
		}
		
		//所在省份
		if(CommonUtil.validCondition(condition, "szsf")){
			hql.append(" and z.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		//所在市(区)
		if(CommonUtil.validCondition(condition, "szcs")){
			hql.append(" and z.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		
		/*//设备类型
		if(CommonUtil.validCondition(condition, "sblx")){
			hql.append(" and z.sblx = ? ");
			param.add(condition.get("sblx"));
		}*/
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
	 * 根据主键id获取数据详情
	 * @param id
	 * @return Object
	 * @date [2017-10-09] 创建文件 by 郎川
	 */
	@Override
	public Object getDataById(String id) {
		String hql="select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.fzdd as fzdd,z.csfs as csfs,z.sblx as sblx,z.jkz_id as jkz_id,z.jkz_name as jkz_name) from Spqd z where 1=1 and z.id = ? ";
		Object [] objParam = {id};
		return baseDao.get(hql.toString(), objParam);
		
	}
	
	/**
	 * 更新视频前端数据
	 * @param spqd
	 * @return void 
	 * @date [2017-10-11] 创建文件 by 郎川
	 */
	@Override
	public void updateSpqd(Spqd spqd) {
		baseDao.update(spqd);
	}
	
	 /**
	 * 按条件导出数据
	 * @param condition
	 * @return
	 * 修改历史：
     * 		[2017年10月16日]创建文件 by 徐成
	 */
	public List<Object> exportDataByCondition(Map<String,Object> condition){
		StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.fzdd as fzdd,z.csfs as csfs,z.sblx as sblx,z.jkz_id as jkz_id,z.jkz_name as jkz_name) from Spqd z where 1=1");
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
	  * @see com.ljdy.BHF.service.SpqdService#getData(java.util.Map)
	  * @Date  2017年10月18日
	  * @修改历史  
	  *     1. [2017年10月18日]创建文件 by 顾冲 
	 *
	 */
    @Override
    public List<Object> getData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,z.fzdd as fzdd,z.csfs as csfs,z.sblx as sblx,z.jkz_id as jkz_id,z.jkz_name as jkz_name) from Spqd z where 1=1");
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
        List<Object> list = baseDao.find(hql.toString(),param);
		List<Object> result = new ArrayList<Object>();
		for(Object obj:list){
			@SuppressWarnings("unchecked")
            Map<String,Object> map = (Map<String,Object>)obj;
			result.add(BeanToMap.transMapToBean(map, Spqd.class));
		}
		return result;
    }
    
    /**
     *视频前端根据建设性质统计个数
     * @param condition
     * @return List<Object>
     * @date [2017-10-23] 创建文件 by 郎川
     */
	@Override
	public List<Object> getCountByJsxz(Map<String, Object> condition) {
		String hql="select count(*) as count from Spqd s where 1=1 and s.jsxz = ? ";
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
		String hql="select sum(zytz) from Spqd";
		return baseDao.find(hql);
	}
	
	/**
	 * 监控站 求和地方投资
	 * @return List<Object>
	 * @date [2017-10-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getDftzSum() {
		String hql="select sum(dftz) from Spqd";
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
		String hql=" select new map(s.jszt as jszt  ,count(*) as jsgm) from Spqd s where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and s.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and s.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and s.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and s.jszt is not null and s.syzt is null group by s.jszt ");
		
		return baseDao.find(hqlStr.toString(), param);
	}
	
	/**
	 * 实施监督管理页面建设投资合计
	 * @param condition
	 * @return List<Object>
	 * @date [2017-11-8] 创建文件  by 郎川
	 */
	@Override
	public List<Object> getManageTz(Map<String, Object> condition) {
		String hql = "select new map(sum(s.zytz) as zytz , sum(s.dftz) as dftz, sum(s.dftz + s.zytz) as dxtz) from Spqd s where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and s.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and s.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and s.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and s.jszt is not null and s.syzt is null ");
		return baseDao.find(hqlStr.toString(), param);
	}
	
	/**
     * @Title getTzGroupByArea
     * @Description (根据区域分组查询投资)
     * @param condition
     * @return
     * @see com.ljdy.BHF.service.SpqdService#getTzGroupByArea(java.util.Map)
     * @Date 2017年11月8日
     * @修改历史 1. [2017年11月8日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getTzGroupByArea(Map<String, Object> condition) {
        List<Object> param = new ArrayList<Object>();
        // 用户角色必传
        String roleName = condition.get("roleName") == null ? "" : (String) condition.get("roleName");
        StringBuffer hql = new StringBuffer("select new map(sum(s.zytz + s.dftz) as dxtz, sum(s.zytz) as zytz, sum(s.dftz) as dftz");
        if ("1".equals(roleName)) {
            hql.append(",s.szsf as area");
        } else if ("2".equals(roleName)) {
            hql.append(",s.szcs as area");
        }
        hql.append(") from Spqd s where 1=1");
        if (CommonUtil.validCondition(condition, "bxxm")) {
            hql.append(" and s.bxxm = ? ");
            param.add(condition.get("bxxm"));
        }
        if (CommonUtil.validCondition(condition, "tznd")) {
            hql.append(" and s.tznd = ? ");
            param.add(condition.get("tznd"));
        }
        if (CommonUtil.validCondition(condition, "sblx")) {
            hql.append(" and s.sblx = ? ");
            param.add(condition.get("sblx"));
        }
        if (CommonUtil.validCondition(condition, "jsxz")) {
            hql.append(" and s.jsxz = ? ");
            param.add(condition.get("jsxz"));
        }
        if ("1".equals(roleName)) {
            hql.append(" group by s.szsf");
        } else if ("2".equals(roleName)) {
            hql.append(" and s.szsf =?");
            param.add(condition.get("szsf"));
            hql.append(" group by s.szcs");
        }
        return baseDao.find(hql.toString(), param);
    }
    
    /**
      * @Title getDxtzGroupByXmlb 
      * @Description (根据项目类别分组获取视频前端单项投资) 
      * @param condition
      * @return 
      * @see com.ljdy.BHF.service.SpqdService#getDxtzGroupByXmlb(java.util.Map)
      * @Date  2017年11月9日
      * @修改历史  
      *     1. [2017年11月9日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getDxtzGroupByXmlb(Map<String, Object> condition) {
        //角色编号必传,省级用户必传所在省份
        String roleName = condition.get("roleName") == null ? "" : (String) condition.get("roleName"); 
        StringBuffer hql=new StringBuffer("select new map(sum(s.zytz + s.dftz) as dxtz, s.xmlb as xmlb) from Spqd s where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and s.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and s.tznd =?");
            param.add(condition.get("tznd"));
        }
        if(CommonUtil.validCondition(condition, "sblx")){
            hql.append(" and s.sblx =?");
            param.add(condition.get("sblx"));
        }
        if(CommonUtil.validCondition(condition, "jsxz")){
            hql.append(" and s.jsxz =?");
            param.add(condition.get("jsxz"));
        }
        
       if("2".equals(roleName)){
           hql.append(" and s.szsf =?");
           param.add(condition.get("szsf"));
        }
        hql.append(" group by s.xmlb");
        return baseDao.find(hql.toString(), param);
    }
    
    /**
      * @Title getDxtzGroupBySslx 
      * @Description (根据设施类型分组获取视频前端单项投资) 
      * @param condition
      * @return 
      * @see com.ljdy.BHF.service.SpqdService#getDxtzGroupBySslx(java.util.Map)
      * @Date  2017年11月9日
      * @修改历史  
      *     1. [2017年11月9日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getDxtzGroupBySslx(Map<String, Object> condition) {
      //角色编号必传,省级用户必传所在省份
        String roleName = condition.get("roleName") == null ? "" : (String) condition.get("roleName"); 
        StringBuffer hql=new StringBuffer("select new map(sum(s.zytz + s.dftz) as dxtz, s.sslx as sslx) from Spqd s where 1=1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "bxxm")){
            hql.append(" and s.bxxm = ?");
            param.add(condition.get("bxxm"));
        }
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and s.tznd =?");
            param.add(condition.get("tznd"));
        }
        if(CommonUtil.validCondition(condition, "sblx")){
            hql.append(" and s.sblx =?");
            param.add(condition.get("sblx"));
        }
        if(CommonUtil.validCondition(condition, "jsxz")){
            hql.append(" and s.jsxz =?");
            param.add(condition.get("jsxz"));
        }
        
       if("2".equals(roleName)){
           hql.append(" and s.szsf =?");
           param.add(condition.get("szsf"));
        }
        hql.append(" group by s.sslx");
        return baseDao.find(hql.toString(), param);
    }
    
    /**
      * @Title countByCondition 
      * @Description (根据jkz_id、sblx等条件统计条数) 
      * @param condition
      * @return 
      * @see com.ljdy.BHF.service.SpqdService#countByCondition(java.util.Map)
      * @Date  2017年11月20日
      * @修改历史  
      *     1. [2017年11月20日]创建文件 by 顾冲 
     *
     */
    @Override
    public Long countByCondition(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select count(*) from Spqd where 1 = 1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "jkz_id")){
            hql.append(" and jkz_id = ?");
            param.add(condition.get("jkz_id"));
        }
        if(CommonUtil.validCondition(condition, "sblx")){
            hql.append(" and sblx = ?");
            param.add(condition.get("sblx"));
        }
        List<Object> list = baseDao.find(hql.toString(), param);
        return (Long)list.get(0);
    }
    
    /**
      * @Title countGroupBySblx 
      * @Description (根据jkz_id条件,sblx分组统计条数) 
      * @param condition
      * @return 
      * @see com.ljdy.BHF.service.SpqdService#countGroupBySblx(java.util.Map)
      * @Date  2017年11月20日
      * @修改历史  
      *     1. [2017年11月20日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> countGroupBySblx(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(count(*) as total,sblx as sblx) from Spqd where 1 = 1");
        List<Object> param = new ArrayList<Object>();
        if(CommonUtil.validCondition(condition, "jkz_id")){
            hql.append(" and jkz_id = ?");
            param.add(condition.get("jkz_id"));
        }
        hql.append(" group by sblx");
        return baseDao.find(hql.toString(), param);
    }
    
    /**
      * @Title getJkz_idsByPrimaryKey 
      * @Description (根据主键字符串查询) 
      * @param idsStr
      * @return 
      * @see com.ljdy.BHF.service.SpqdService#getDataByPrimaryKeys(java.lang.String)
      * @Date  2017年11月21日
      * @修改历史  
      *     1. [2017年11月21日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getDataByPrimaryKeys(String idsStr) {
        StringBuffer hql = new StringBuffer("select new map(jkz_id as jkz_id, sblx as sblx) from Spqd where");
        String [] idsArr = idsStr.split(",");
        List<Object> param = new ArrayList<Object>();
        for (int i = 0; i < idsArr.length; i++) {
            if(i != idsArr.length - 1){
                hql.append(" id = ? or");
                param.add(idsArr[i]);
            }else{
                hql.append(" id = ?");
                param.add(idsArr[i]);
            }
        }
        return baseDao.find(hql.toString(), param);
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
		String hql=" select new map(s.syzt as syzt  ,count(*) as jsgm) from Spqd s where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and s.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and s.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and s.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		hqlStr.append(" and s.jszt ='已审计' and s.syzt in ('良好','损坏','废弃') group by s.syzt ");
		
		return baseDao.find(hqlStr.toString(), param);
	}
	
	/**
	 * 使用维护模块首页数据建设投资合计
	 * @param condition
	 * @return  List<Object>
	 * @date [2017-11-23] 创建文件 by 郎川
	 */
	@Override
	public List<Object> getMaintenanceTz(Map<String, Object> condition) {
		String hql = "select new map(sum(s.zytz) as zytz , sum(s.dftz) as dftz, sum(s.dftz + s.zytz) as dxtz) from Spqd s where 1=1 ";
		StringBuffer hqlStr = new StringBuffer(hql);
		List<Object>param = new ArrayList<Object>();
		if(CommonUtil.validCondition(condition, "szsf")){
			hqlStr.append(" and s.szsf like ? ");
			param.add("%"+condition.get("szsf")+"%");
		}
		
		if(CommonUtil.validCondition(condition, "szcs")){
			hqlStr.append(" and s.szcs like ? ");
			param.add("%"+condition.get("szcs")+"%");
		}
		if(CommonUtil.validCondition(condition, "bxxm")){
			hqlStr.append(" and s.bxxm = ? ");
			param.add(condition.get("bxxm"));
		}
		hqlStr.append(" and s.jszt ='已审计' and s.syzt in ('良好','损坏','废弃') ");
		return baseDao.find(hqlStr.toString(), param);
	}

	@Override
	public void addSpqd(AddSpqd addSpqd) {
		       baseDao.save(addSpqd);
	}

	
	/**
	  * @Title getJkz_idByIds 
	  * @Description (通过id获取jkz_id) 
	  * @param idsArr
	  * @return 
	  * @see com.ljdy.BHF.service.SpqdService#getJkz_idByIds(java.lang.String[])
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
        StringBuffer sql = new StringBuffer("select distinct jkz_id from spqd where id in (");
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
     * @see com.ljdy.BHF.service.SpqdService#getDestroyData(java.util.Map)
     * @Date 2017年12月5日
     * @修改历史 1. [2017年12月5日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getDestroyData(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(s.id as id, s.xmmc as xmmc, s.xmbh as xmbh, s.syzt as syzt) from Spqd s where 1=1");
        List<Object> param = new ArrayList<Object>();
        if (CommonUtil.validCondition(condition, "id")) {
            hql.append(" and s.id = ? ");
            param.add(condition.get("id"));
        }else{
            if (CommonUtil.validCondition(condition, "szsf")) {
                hql.append(" and s.szsf like ? ");
                param.add("%" + condition.get("szsf") + "%");
            }
            if (CommonUtil.validCondition(condition, "syzt")) {
                hql.append(" and s.syzt = ? ");
                param.add(condition.get("syzt"));
            }
        }
        return baseDao.find(hql.toString(), param);
    }
    
    /**
      * @Title getSpqdByCondition 
      * @Description (根据jkz_id、sblx获取视频前端信息) 
      * @param condition
      * @return 
      * @see com.ljdy.BHF.service.SpqdService#getSpqdByCondition(java.util.Map)
      * @Date  2018年1月6日
      * @修改历史  
      *     1. [2018年1月6日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> getSpqdByCondition(Map<String, Object> condition) {
        StringBuffer hql = new StringBuffer("select new map(z.id as id,z.xmlb as xmlb,z.xmzl as xmzl,z.xmmc as xmmc,"
                + "z.xmbh as xmbh,z.bjfx as bjfx,z.sslx as sslx,z.szsf as szsf,z.szcs as szcs,z.szq as szq,z.dxlb as dxlb,"
                + "z.jsxz as jsxz,z.sydw as sydw,z.sbdw as sbdw,z.zytz as zytz,z.dftz as dftz,z.fj as fj,z.bz as bz,"
                + "z.bxxm as bxxm,z.tznd as tznd,z.jszt as jszt,z.cjdw as cjdw,z.jldw as jldw,z.ztbsj as ztbsj,z.kgsj as kgsj,"
                + "z.cysj as cysj, z.jgsj as jgsj,z.sjsj as sjsj,z.time as time,z.jd as jd,z.wd as wd,z.syzt as syzt,"
                + "z.fzdd as fzdd,z.csfs as csfs,z.sblx as sblx,z.jkz_id as jkz_id,z.jkz_name as jkz_name) from Spqd z "
                + "where z.jkz_id = ? and z.sblx = ?");
        List<Object> param = new ArrayList<Object>();
        param.add(condition.get("jkz_id"));
        param.add(condition.get("sblx"));
        //设施类型
        if(CommonUtil.validCondition(condition, "sslx")){
            hql.append(" and z.sslx = ? ");
            param.add(condition.get("sslx"));
        }
        //边界方向
        if(CommonUtil.validCondition(condition, "bjfx")){
            hql.append(" and z.bjfx like ? ");
            param.add("%"+condition.get("bjfx")+"%");
        }
        
        //地形类别
        if(CommonUtil.validCondition(condition, "dxlb")){
            hql.append(" and z.dxlb  like ? ");
            param.add("%"+condition.get("dxlb")+"%");
        }
        
        //建设性质
        if(CommonUtil.validCondition(condition, "jsxz")){
            hql.append(" and z.jsxz = ? ");
            param.add(condition.get("jsxz"));
        }
        
        //投资年度
        if(CommonUtil.validCondition(condition, "tznd")){
            hql.append(" and z.tznd = ? ");
            param.add(condition.get("tznd"));
        }
        //使用状态
        if(CommonUtil.validCondition(condition, "syzt")){
            hql.append(" and z.syzt = ? ");
            param.add(condition.get("syzt"));
        }
        //建设状态
        if(CommonUtil.validCondition(condition, "jszt")){
            hql.append(" and z.jszt = ? ");
            param.add(condition.get("jszt"));
        }
        
        return baseDao.find(hql.toString(), param);
    }
}
