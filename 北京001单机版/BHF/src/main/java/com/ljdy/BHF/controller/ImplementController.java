package com.ljdy.BHF.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljdy.BHF.model.DICT_GY;
import com.ljdy.BHF.model.User;
import com.ljdy.BHF.utils.ArithUtil;

/**
 * 监督实施模块控制层
 * 
 * @author 郎川
 * @date 2017年11月6日
 */
@Controller
@RequestMapping("implement")
public class ImplementController extends BaseController {

	@RequestMapping("/load")
	public String load(HttpServletRequest req, ModelMap model) {
		User user = (User) req.getSession().getAttribute("loginUser");
		String partTag = req.getParameter("partTag");
		model.addAttribute("partTag", partTag);
		model.addAttribute("roleName", user.getRoleName());
		String szsf = req.getParameter("szsf");
		String szcs =req.getParameter("szcs");
		if("1".equals(user.getRoleName())){
			if (szsf == null || szsf.equals("null")) {
				szsf = "全国";
			}
		}else if("2".equals(user.getRoleName())){
			szsf=user.getProvince();
			if(!szsf.equals(szcs) && szcs!=null){
				model.addAttribute("isHidden",1);
			}
		}
		model.addAttribute("szsf", szsf);
		if("2".equals(user.getRoleName()) && (szcs!=null && !szcs.equals("null"))){
			model.addAttribute("szsf", szcs);
		}
		// 查询项目类别
		List<DICT_GY> xmlbList = dict_gyService.getDict_GYsByType("项目类别");
		// 查询项目子类
		List<DICT_GY> xmzlList = dict_gyService.getDict_GYsByType("项目子类");

		req.setAttribute("xmlbList", xmlbList);
		req.setAttribute("xmzlList", xmzlList);
		
		// 查询条件
		Map<String, Object> condition = new HashMap<String, Object>();
		String bxxm = null;
		if ("3".equals(partTag)) {
			bxxm = "否";
		}
		condition.put("bxxm", bxxm);
		if("1".equals(user.getRoleName())){
			condition.put("szsf", szsf.equals("全国") ? null:szsf);
		}else if("2".equals(user.getRoleName())){
			condition.put("szsf", szsf);
		}
		condition.put("szcs", szcs);

		Map<String, Object>data=tableData(condition);
		model.addAttribute("data", data);
		
		//饼状图查询条件
		Map<String, Object>pie_map = new HashMap<String,Object>();
		pie_map.put("partTag", partTag);
		pie_map.put("bxxm", bxxm);
		pie_map.put("szsf", szsf.equals("全国") ? null:szsf);
		pie_map.put("szcs", szcs);
		Map<String, Object>pie_jszt=getPieFindProvince(pie_map);
		model.addAttribute("pie_jszt", pie_jszt);
		
		//柱状图查询条件
		Map<String, Object>column_map = new HashMap<String,Object>();
		column_map.put("roleName", user.getRoleName());
		column_map.put("bxxm", bxxm);
		column_map.put("partTag", partTag);
		//column_map.put("szsf", szsf.equals("全国") ? null :szsf);
		if("1".equals(user.getRoleName())){
			column_map.put("szsf", szsf.equals("全国") ? null:szsf);
		}else if("2".equals(user.getRoleName())){
			column_map.put("szsf",user.getProvince());
			column_map.put("szcs",szcs);
		}
		List<Object> areaList = new ArrayList<Object>();
        if ("1".equals(user.getRoleName())) {
        	if(szsf.equals("全国")){
        		areaList = statisticsService.getProvince();
        	}else{
        		areaList = statisticsService.getCityByProvince(szsf);
        	}
        } else if ("2".equals(user.getRoleName())) {
            areaList = statisticsService.getCityByProvince(user.getProvince());
        }
		Map<String, Double>columnMap=getColumnData(column_map,areaList);
		model.addAttribute("columnMap", columnMap);
		
		Map<String, Double>xmlbMap=getColumnFindByXmlb(column_map);
		model.addAttribute("xmlbMap", xmlbMap);
		// 国家级用户登陆
		if ("1".equals(user.getRoleName())) {
			List<Object> sfList = statisticsService.getProvinceShort();
			model.addAttribute("areaList", sfList);
			return "manage";
		//省级用户登陆	
		}else if("2".equals(user.getRoleName())){
			model.addAttribute("areaList",statisticsService.getCityByProvince(user.getProvince()));
			if(szsf.equals("全国")){
				model.addAttribute("szsf", user.getProvince());
			}
			return "manage";
		}
		return "login";

	}

	public Map<String, Object> tableData(Map<String, Object>condition) {

		// 返回的数据
		Map<String, Object> data = new HashMap<String, Object>();
		
		//建设投资合计 中央投资合计 地方投资合计
		List<Object>AllTz=zqdlService.getManageAllTz(condition);
		Map<String, Object>map_tz = new HashMap<String,Object>();
		for(Object obj :AllTz){
			 @SuppressWarnings("unchecked")
			 Map<String, Object>map =(Map<String, Object>)obj;
			 Object zytz = map.get("zytz") == null ? 0 :map.get("zytz");
			 Object dftz = map.get("dftz") == null ? 0 :map.get("dftz");
			 Object dxtz = map.get("dxtz") == null ? 0 :map.get("dxtz");
			 map_tz.put("zytz", zytz);
			 map_tz.put("dftz", dftz);
			 map_tz.put("dxtz", dxtz);
		}
		data.put("map_tz", map_tz);
		
		// 交通报警设施 执勤道路
		List<Object> zqdl_data = zqdlService.getManageData(condition);
		List<Object> zqdl_tz = zqdlService.getManageTz(condition);

		data.put("zqdl_data", zqdl_data);
		data.put("zqdl_tz", zqdl_tz);

		// 交通保障设施 桥梁
		List<Object> qiaoliang_data = qiaoLiangService.getManageData(condition);
		List<Object> qiaoliang_tz = qiaoLiangService.getManageTz(condition);
		data.put("qiaoliang_data", qiaoliang_data);
		data.put("qiaoliang_tz", qiaoliang_tz);

		// 交通保障设施 执勤码头
		List<Object> zqmt_data = zqmtService.getManageData(condition);
		List<Object> zqmt_tz = zqmtService.getManageTz(condition);
		data.put("zqmt_data", zqmt_data);
		data.put("zqmt_tz", zqmt_tz);

		// 交通保障设施 直升机停机坪
		List<Object> zsjtjp_data = zsjtjpService.getManageData(condition);
		List<Object> zsjtjp_tz = zsjtjpService.getManageTz(condition);
		data.put("zsjtjp_data", zsjtjp_data);
		data.put("zsjtjp_tz", zsjtjp_tz);

		// 拦阻报警设施 铁丝网
		List<Object> tsw_data = tswService.getManageData(condition);
		List<Object> tsw_tz = tswService.getManageTz(condition);

		data.put("tsw_data", tsw_data);
		data.put("tsw_tz", tsw_tz);

		// 拦阻报警设施 铁栅栏
		List<Object> tzl_data = tzlService.getManageData(condition);
		List<Object> tzl_tz = tzlService.getManageTz(condition);
		data.put("tzl_data", tzl_data);
		data.put("tzl_tz", tzl_tz);

		// 拦阻报警设施 骏马
		List<Object> juma_data = jumaService.getManageData(condition);
		List<Object> juma_tz = jumaService.getManageTz(condition);
		data.put("juma_data", juma_data);
		data.put("juma_tz", juma_tz);

		// 拦阻报警设施 拦阻桩
		List<Object> lzz_data = lzzService.getManageData(condition);
		List<Object> lzz_tz = lzzService.getManageTz(condition);
		data.put("lzz_data", lzz_data);
		data.put("lzz_tz", lzz_tz);

		// 拦阻报警设施 隔离带
		List<Object> gld_data = gldService.getManageData(condition);
		List<Object> gld_tz = gldService.getManageTz(condition);
		data.put("gld_data", gld_data);
		data.put("gld_tz", gld_tz);

		// 拦阻报警设施 报警线路
		List<Object> bjxl_data = bjxlService.getManageData(condition);
		List<Object> bjxl_tz = bjxlService.getManageTz(condition);
		data.put("bjxl_data", bjxl_data);
		data.put("bjxl_tz", bjxl_tz);

		// 指挥监控设施 监控中心(国家级)
		condition.put("jb", "国家级");
		List<Object> gj_jkzx_data = jkzxService.getManageData(condition);
		List<Object> gj_jkzx_tz = jkzxService.getManageTz(condition);
		data.put("gj_jkzx_data", gj_jkzx_data);
		data.put("gj_jkzx_tz", gj_jkzx_tz);

		// 指挥监控设施 监控中心(省级)
		condition.put("jb", "省级");
		List<Object> sj_jkzx_data = jkzxService.getManageData(condition);
		List<Object> sj_jkzx_tz = jkzxService.getManageTz(condition);
		data.put("sj_jkzx_data", sj_jkzx_data);
		data.put("sj_jkzx_tz", sj_jkzx_tz);

		// 指挥监控设施 监控中心(省级)
		condition.put("jb", "地级");
		List<Object> dj_jkzx_data = jkzxService.getManageData(condition);
		List<Object> dj_jkzx_tz = jkzxService.getManageTz(condition);
		data.put("dj_jkzx_data", dj_jkzx_data);
		data.put("dj_jkzx_tz", dj_jkzx_tz);

		// 指挥监控设施 监控中心(省级)
		condition.put("jb", "县级");
		List<Object> xj_jkzx_data = jkzxService.getManageData(condition);
		List<Object> xj_jkzx_tz = jkzxService.getManageTz(condition);
		data.put("xj_jkzx_data", xj_jkzx_data);
		data.put("xj_jkzx_tz", xj_jkzx_tz);

		// 指挥监控设施 监控站
		List<Object> jkz_data = jkzService.getManageData(condition);
		List<Object> jkz_tz = jkzService.getManageTz(condition);
		data.put("jkz_data", jkz_data);
		data.put("jkz_tz", jkz_tz);

		// 指挥监控设施 传输线路
		List<Object> csxl_data = csxlService.getManageData(condition);
		List<Object> csxl_tz = csxlService.getManagetz(condition);
		data.put("csxl_data", csxl_data);
		data.put("csxl_tz", csxl_tz);

		// 指挥监控设施 供电系统
		List<Object> gdxt_data = gdxtService.getManageData(condition);
		List<Object> gdxt_tz = gdxtService.getManagetz(condition);
		data.put("gdxt_data", gdxt_data);
		data.put("gdxt_tz", gdxt_tz);

		// 指挥监控设施 军警民联防平台
		List<Object> jjm_data = jjmlfptService.getManageData(condition);
		List<Object> jjm_tz = jjmlfptService.getManageTz(condition);
		data.put("jjm_data", jjm_data);
		data.put("jjm_tz", jjm_tz);

		// 指挥监控设施 无人值守电子哨兵
		List<Object> wrzs_data = wrzsdzsbService.getManageData(condition);
		List<Object> wrzs_tz = wrzsdzsbService.getManageTz(condition);
		data.put("wrzs_data", wrzs_data);
		data.put("wrzs_tz", wrzs_tz);

		// 指挥监控设施 视频前端
		List<Object> spqd_data = spqdService.getManageData(condition);
		List<Object> spqd_tz = spqdService.getManageTz(condition);
		data.put("spqd_data", spqd_data);
		data.put("spqd_tz", spqd_tz);

		// 指挥监控设施 显控终端
		List<Object> xkzd_data = xkzdService.getManageData(condition);
		List<Object> xkzd_tz = xkzdService.getManageTz(condition);
		data.put("xkzd_data", xkzd_data);
		data.put("xkzd_tz", xkzd_tz);

		// 指挥监控设施 报警装置
		List<Object> bjzz_data = bjzzService.getManageData(condition);
		List<Object> bjzz_tz = bjzzService.getManageTz(condition);
		data.put("bjzz_data", bjzz_data);
		data.put("bjzz_tz", bjzz_tz);

		// 辅助配套设施 执勤房
		List<Object> zqf_data = zqfService.getManageData(condition);
		List<Object> zqf_tz = zqfService.getManageTz(condition);
		data.put("zqf_data", zqf_data);
		data.put("zqf_tz", zqf_tz);

		// 辅助配套设施 了望塔
		List<Object> lwt_data = lwtService.getManageData(condition);
		List<Object> lwt_tz = lwtService.getManageTz(condition);
		data.put("lwt_data", lwt_data);
		data.put("lwt_tz", lwt_tz);

		// 辅助配套设施 标志牌
		List<Object> bzp_data = bzpService.getManageData(condition);
		List<Object> bzp_tz = bzpService.getManageTz(condition);
		data.put("bzp_data", bzp_data);
		data.put("bzp_tz", bzp_tz);
		return data;
	}
	/**
	 * 监督实施模块首页根据建设状态查询饼状图数据
	 * @param conditionPie
	 * @return Map<String, Object>
	 * @date [2017-11-10] 创建文件 by 郎川 
	 */
	public Map<String, Object>getPieFindProvince(Map<String, Object>conditionPie){
		Map<String, Object>map_pie = new HashMap<String,Object>();
		
		List<Object>pie_data=jbxxService.getPieFindProvice(conditionPie);
		List<Object>xmlbList=jbxxService.getXmlb();
		
		for (Object xmlb : xmlbList) {
            Object value = null;
            if (xmlb == null || xmlb.toString() == "") {
                continue;
            }
            for (Object object : pie_data) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                if (xmlb.equals(map.get("jszt"))) {
                    value = map.get("all_tz");
                }
            }
            map_pie.put((String) xmlb, value == null ? 0 : (Double)value);
        }
		
		return map_pie;
	}
	
	/**
	 * 监督实施模块首页建设投资总额数据列的数据
	 * @param conditionColumn
	 * @param areaList
	 * @return Map<String, Double>
	 * @date [2017-11-13] 创建文件 by 郎川
	 */
	public Map<String, Double> getColumnData(Map<String, Object>conditionColumn,List<Object>areaList){
			List<Object> sfsxList=null;
			if(conditionColumn.get("szsf")==null || conditionColumn.get("szsf").equals("null")){
				sfsxList= statisticsService.getProvinceShort();// 省份简称集合
			}else if(conditionColumn.get("szsf")!=null || !conditionColumn.get("szsf").equals("null")){
				String codeName=(String)conditionColumn.get("szsf");
				sfsxList=jbxxService.findCity(codeName);
			}
		List<Object>queryList=jbxxService.getDxtzGroupByArea(conditionColumn);
		Map<String, Double> resultMap = new HashMap<String, Double>();
        if(areaList != null && !areaList.isEmpty()){
            for (Object area : areaList) {
                if (area == null || area.toString() == "") {
                    continue;
                }
                double value = 0.0;
                for (Object object : queryList) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map = (Map<String, Object>) object;
                    if (area.equals(map.get("area"))) {
                        value = map.get("dxtz") == null ? 0.0 : (Double)map.get("dxtz");
                    }
                }
                Object areaSx = area;
                if ("1".equals(conditionColumn.get("roleName"))) {
                    for (Object obj : sfsxList) {
                        if (area.toString().indexOf(obj.toString()) >= 0) {
                            areaSx = obj;
                        }
                    }
                }
                resultMap.put((String) areaSx, value);
            }
        }else{
            double value = 0.0;
            for (Object object : queryList) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                double dxtz = map.get("dxtz") == null ? 0.0 : (Double)map.get("dxtz");
                value = ArithUtil.add(value, dxtz);
            }
           // double removeVal = removeMap.get("noAreaSum") == null ? 0.0 : removeMap.get("noAreaSum");//无边防市，则建为noAreaSum
            //value = value - removeVal;
            //resultMap.put(paramMap.get("szsf") != null ? (String)paramMap.get("szsf") : "", value);//无边防市以省替代
        }
        return sortMap(resultMap);
	}
    
    /**
     * 监督实施模块首页根据项目类别查询数据
     * 
     * @return
     */
    public Map<String, Double>getColumnFindByXmlb(Map<String, Object>conditionXmlb){
    	Map<String, Double> resultMap = new HashMap<String, Double>();
    	List<Object> queryList = jbxxService.getJstzGroupByXmlb(conditionXmlb);
    	List<Object> xmlbList = jbxxService.getXmlbData();
    	for (Object xmlb : xmlbList) {
            if (xmlb == null || "".equals(((String)xmlb).trim())) {
                continue;
            }
            double value = 0.0;
            for (Object object : queryList) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                if (xmlb.equals(map.get("xmlb"))) {
                    value = map.get("dxtz") == null ? 0.0 : (Double)map.get("dxtz");
                }
            }
            //double removeVal = removeMap.get(xmlb) == null ? 0.0 : removeMap.get(xmlb);
            //value = value - removeVal;
            resultMap.put((String) xmlb, value);
        }
        return sortMap(resultMap);
    }
    
    /**
     * @Title sortMap 
     * @Description (根据key值对map排序) 
     * @param unsortMap
     * @return
     * @Return Map<String,Double> 返回类型 
     * @Throws 
     * @Date  2017年10月19日
     * @修改历史  
     *     1. [2017年10月19日]创建文件 by 顾冲
     */
    public Map<String, Double> sortMap(Map<String, Double> unsortMap) {
        List<Entry<String, Double>> list = new ArrayList<Entry<String,Double>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                double q1 = o1.getValue();
                double q2 = o2.getValue();
                double p = q2-q1;
                if(p > 0){
                    return 1;
                }else if(p == 0){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        Map<String, Double> sortMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> set : list) {
            sortMap.put(set.getKey(), set.getValue());
        }
        return sortMap;
    }
    
    /**
     * 转入使用维护方法
     * @param request
     * @return
     */
    @RequestMapping("/toUseMaintenance")
    public @ResponseBody Map<String, Object> toUseMaintenance(HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<String,Object>();
    	String szsf=request.getParameter("szsf");
    	String xmzl=request.getParameter("xmzl");
    	String xmlb=request.getParameter("xmlb");
    	String partTag=request.getParameter("partTag");
    	resultMap.put("szsf", szsf);
    	resultMap.put("xmlb", xmlb);
    	resultMap.put("xmzl", xmzl);
    	resultMap.put("partTag", partTag);
    	String id=request.getParameter("toUseId");
    	jbxxService.toUseMaintenance(id, xmzl);
    	return resultMap;
    }

	/**
	 * 验证视频前段、显控终端、传输线路、供电系统、报警装置5类设施所在监控站是否已转入维护
	 * @return
     * 修改时间：
     *      【2017-12-19】  创建方法  by 徐成
	 */
	@RequestMapping("/checkSzjkzInUse")
	public @ResponseBody List<Object> checkSzjkzInUse(HttpServletRequest request){
		String ids = request.getParameter("ids");
		String[] idArr = ids.split(",");
		String xmzl = request.getParameter("xmzl");
		List<Object> jkz_idList = new ArrayList<Object>();
		if ("视频前段".equals(xmzl)){
			jkz_idList = spqdService.getJkz_idByIds(idArr);
		}
		if ("显控终端".equals(xmzl)){
			jkz_idList = xkzdService.getJkz_idByIds(idArr);
		}
		if ("传输线路".equals(xmzl)){
			jkz_idList = csxlService.getJkz_idByIds(idArr);
		}
		if ("供电系统".equals(xmzl)){
			jkz_idList = gdxtService.getJkz_idByIds(idArr);
		}
		if ("报警装置".equals(xmzl)){
			jkz_idList = bjzzService.getJkz_idByIds(idArr);
		}
		List<Object> list = jkzService.getDataByIds(jkz_idList);
		return list;
	}
}
