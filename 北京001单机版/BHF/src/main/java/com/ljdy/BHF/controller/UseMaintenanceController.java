package com.ljdy.BHF.controller;

import java.math.BigDecimal;
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

import com.ljdy.BHF.model.DICT_GY;
import com.ljdy.BHF.model.User;

/**
 * 使用维护管理模块控制层
 * @author 郎川
 * @date [2017年11月22日] 创建文件 by 郎川
 */
@Controller
@RequestMapping("usemaintenance")
public class UseMaintenanceController extends BaseController{
	
	@RequestMapping("/load")
	public String load(HttpServletRequest request,ModelMap model){
		User user=(User)request.getSession().getAttribute("loginUser");
		String  partTag=request.getParameter("partTag");
		model.addAttribute("partTag", partTag);
		// 查询项目类别
		List<DICT_GY> xmlbList = dict_gyService.getDict_GYsByType("项目类别");
		// 查询项目子类
		List<DICT_GY> xmzlList = dict_gyService.getDict_GYsByType("项目子类");
		
		request.setAttribute("xmlbList", xmlbList);
		request.setAttribute("xmzlList", xmzlList);
		
		String szsf=request.getParameter("szsf");
		String szcs=request.getParameter("szcs");
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
		Map<String, Object> conditionMap = new HashMap<String,Object>();
		
		String bxxm = null;
		if("4".equals(partTag)){
			bxxm="否";
		}
		conditionMap.put("bxxm", bxxm);
		if("1".equals(user.getRoleName())){
			conditionMap.put("szsf", szsf.equals("全国") ? null : szsf);
		}else if("2".equals(user.getRoleName())){
			conditionMap.put("szsf", szsf);
		}
		conditionMap.put("szcs", szcs);
		//表格数据填充
		Map<String, Object>data=getTableData(conditionMap);
		model.addAttribute("data", data);
		
	    //根据使用状态分组查询中央投资和地方投资比例图
		Map<String, Object>pei_map = new HashMap<String,Object>();
		pei_map.put("partTag", partTag);
		pei_map.put("bxxm", bxxm);
		pei_map.put("szsf", szsf.equals("全国") ? null :szsf );
		pei_map.put("szcs", szcs);
		Map<String, Object>syzt_map=getPieFindProvince(pei_map);
		model.addAttribute("syzt_map", syzt_map);
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
        
        //分组查询参数
        Map<String, Object> groupParam = new HashMap<String, Object>();
        groupParam.put("szsf", conditionMap.get("szsf"));
        groupParam.put("szcs", conditionMap.get("szcs"));
        //使用维护模块第一个柱状图和第二个饼状图的数据(根据省份分组统计中央投资和地方投资求和)
        Map<String, Double> cityColumn = getWxfyGroupByArea(groupParam, areaList);
        model.addAttribute("cityColumn", cityColumn);
        //使用维护模块第二个柱状图和第三个饼状图的数据(根据项目类别分组统计中央投资和地方投资求和)
        Map<String, Double> xmlbMap = getWxfyGroupByXmlb(groupParam);
        model.addAttribute("xmlbMap", xmlbMap);
		//国家级用户
		if("1".equals(user.getRoleName())){
			List<Object> sfList = statisticsService.getProvinceShort();
			model.addAttribute("areaList", sfList);
			return "useMaintenance";
		}else if("2".equals(user.getRoleName())){
			model.addAttribute("areaList",statisticsService.getCityByProvince(user.getProvince()));
			return "useMaintenance";
		}
		return "login";
	}
	
	public Map<String, Object> getTableData(Map<String, Object>condition){
		
		//返回结果集的map
		Map<String, Object>resultMap = new HashMap<String, Object>();
		//建设投资合计  中央投资合计 地方投资合计
		List<Object>AllTz=zqdlService.getAllTz(condition);
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
		
		
		resultMap.put("map_tz", map_tz);
		
		//交通保障设施  执行道路
		List<Object>zqdl_data=zqdlService.getMaintenanceData(condition);
		List<Object>zqdl_tz=zqdlService.getMaintenancetz(condition);
		resultMap.put("zqdl_data", zqdl_data);
		resultMap.put("zqdl_tz", zqdl_tz);
		
		//交通保障设施 桥梁
		List<Object>qiaoliang_data=qiaoLiangService.getMaintenanceData(condition);
		List<Object>qiaoliang_tz=qiaoLiangService.getMaintenanceTz(condition);
		resultMap.put("qiaoliang_data", qiaoliang_data);
		resultMap.put("qiaoliang_tz", qiaoliang_tz);
		
		//交通保障设施 执勤码头
		List<Object>zqmt_data=zqmtService.getMaintenanceData(condition);
		List<Object>zqmt_tz=zqmtService.getMaintenanceTz(condition);
		resultMap.put("zqmt_data", zqmt_data);
		resultMap.put("zqmt_tz", zqmt_tz);
		
		//交通保障设施 直升机停机坪
		List<Object>zsjtjp_data=zsjtjpService.getMaintenanceData(condition);
		List<Object>zsjtjp_tz=zsjtjpService.getMaintenanceTz(condition);
		resultMap.put("zsjtjp_data", zsjtjp_data);
		resultMap.put("zsjtjp_tz", zsjtjp_tz);
		
		//拦阻设施报警 铁丝网
		List<Object>tsw_data=tswService.getMaintenanceData(condition);
		List<Object>tsw_tz=tswService.getMaintenanceTz(condition);
		resultMap.put("tsw_data", tsw_data);
		resultMap.put("tsw_tz", tsw_tz);
		
		//拦阻设施报警 铁栅栏
		List<Object>tzl_data=tzlService.getMaintenanceData(condition);
		List<Object>tzl_tz=tzlService.getMaintenanceTz(condition);
		resultMap.put("tzl_data", tzl_data);
		resultMap.put("tzl_tz", tzl_tz);
		
		//拦阻设施报警 拒马
		List<Object>juma_data=jumaService.getMaintenanceData(condition);
		List<Object>juma_tz=jumaService.getMaintenanceTz(condition);
		resultMap.put("juma_data", juma_data);
		resultMap.put("juma_tz", juma_tz);
		
		//拦阻设施报警 拦阻桩
		List<Object>lzz_data=lzzService.getMaintenanceData(condition);
		List<Object>lzz_tz=lzzService.getMaintenanceTz(condition);
		resultMap.put("lzz_data", lzz_data);
		resultMap.put("lzz_tz", lzz_tz);
		
		//拦阻设施报警 隔离带
		List<Object>gld_data=gldService.getMaintenanceData(condition);
		List<Object>gld_tz=gldService.getMaintenanceTz(condition);
		resultMap.put("gld_data", gld_data);
		resultMap.put("gld_tz", gld_tz);
		
		//拦阻设施报警 报警线路
		List<Object>bjxl_data=bjxlService.getMaintenanceData(condition);
		List<Object>bjxl_tz=bjxlService.getMaintenanceTz(condition);
		resultMap.put("bjxl_data", bjxl_data);
		resultMap.put("bjxl_tz", bjxl_tz);
		
		//指挥监控设施 国家级 监控中心
		condition.put("jb", "国家级");
		List<Object>gjjkzx_data=jkzxService.getMaintenanceData(condition);
		List<Object>gjjkzx_tz=jkzxService.getMaintenanceTz(condition);
		resultMap.put("gjjkzx_data", gjjkzx_data);
		resultMap.put("gjjkzx_tz", gjjkzx_tz);
		
		//指挥监控设施 省级 监控中心
		condition.put("jb", "省级");
		List<Object>sjjkzx_data=jkzxService.getMaintenanceData(condition);
		List<Object>sjjkzx_tz=jkzxService.getMaintenanceTz(condition);
		resultMap.put("sjjkzx_data", sjjkzx_data);
		resultMap.put("sjjkzx_tz", sjjkzx_tz);
		
		//指挥监控设施  地级 监控中心
		condition.put("jb", "地级");
		List<Object>djjkzx_data=jkzxService.getMaintenanceData(condition);
		List<Object>djjkzx_tz=jkzxService.getMaintenanceTz(condition);
		resultMap.put("djjkzx_data", djjkzx_data);
		resultMap.put("djjkzx_tz", djjkzx_tz);
		
		//指挥监控设施 县级 监控中心
		condition.put("jb", "县级");
		List<Object>xjjkzx_data=jkzxService.getMaintenanceData(condition);
		List<Object>xjjkzx_tz=jkzxService.getMaintenanceTz(condition);
		resultMap.put("xjjkzx_data", xjjkzx_data);
		resultMap.put("xjjkzx_tz", xjjkzx_tz);
		
		//指挥监控设施 监控站
		List<Object>jkz_data=jkzService.getMaintenanceData(condition);
		List<Object>jkz_tz=jkzService.getMaintenanceTz(condition);
		resultMap.put("jkz_data", jkz_data);
		resultMap.put("jkz_tz", jkz_tz);
		
		//指挥监控设施 传输线路
		List<Object>csxl_data=csxlService.getMaintenanceData(condition);
		List<Object>csxl_tz=csxlService.getMaintenanceTz(condition);
		resultMap.put("csxl_data", csxl_data);
		resultMap.put("csxl_tz", csxl_tz);
		
		//指挥监控设施 供电系统
		List<Object>gdxt_data=gdxtService.getMaintenanceData(condition);
		List<Object>gdxt_tz=gdxtService.getMaintenanceTz(condition);
		resultMap.put("gdxt_data", gdxt_data);
		resultMap.put("gdxt_tz", gdxt_tz);
		
		//指挥监控设施 军警民联防平台
		List<Object>jjmlfpt_data=jjmlfptService.getMaintenanceData(condition);
		List<Object>jjmlfpt_tz=jjmlfptService.getMaintenanceTz(condition);
		resultMap.put("jjmlfpt_data", jjmlfpt_data);
		resultMap.put("jjmlfpt_tz", jjmlfpt_tz);
		
		//指挥监控设施  无人值守电子哨兵
		List<Object>wrzsdzsb_data=wrzsdzsbService.getMaintenanceData(condition);
		List<Object>wrzsdzsb_tz=wrzsdzsbService.getMaintenanceTz(condition);
		resultMap.put("wrzsdzsb_data", wrzsdzsb_data);
		resultMap.put("wrzsdzsb_tz", wrzsdzsb_tz);
		
		//指挥监控设施 视频前端
		List<Object>spqd_data=spqdService.getMaintenanceData(condition);
		List<Object>spqd_tz=spqdService.getMaintenanceTz(condition);
		resultMap.put("spqd_data", spqd_data);
		resultMap.put("spqd_tz", spqd_tz);
		
		//指挥监控设施  显控终端
		List<Object>xkzd_data=xkzdService.getMaintenanceData(condition);
		List<Object>xkzd_tz=xkzdService.getMaintenanceTz(condition);
		resultMap.put("xkzd_data", xkzd_data);
		resultMap.put("xkzd_tz", xkzd_tz);
		
		//指挥监控设施 报警装置
		List<Object>bjzz_data=bjzzService.getMaintenanceData(condition);
		List<Object>bjzz_tz=bjzzService.getMaintenanceTz(condition);
		resultMap.put("bjzz_data", bjzz_data);
		resultMap.put("bjzz_tz", bjzz_tz);
		
		//辅助配套设施  执勤房
		List<Object>zqf_data=zqfService.getMaintenanceData(condition);
		List<Object>zqf_tz=zqfService.getMaintenanceTz(condition);
		resultMap.put("zqf_data", zqf_data);
		resultMap.put("zqf_tz", zqf_tz);
		
		//辅助配套设施  了望塔
		List<Object>lwt_data=lwtService.getMaintenanceData(condition);
		List<Object>lwt_tz=lwtService.getMaintenanceTz(condition);
		resultMap.put("lwt_data", lwt_data);
		resultMap.put("lwt_tz", lwt_tz);
		
		//辅助配套设施 标志牌
		List<Object>bzp_data=bzpService.getMaintenanceData(condition);
		List<Object>bzp_tz=bzpService.getMaintenanceTz(condition);
		resultMap.put("bzp_data", bzp_data);
		resultMap.put("bzp_tz", bzp_tz);
		
		return resultMap;
	}
	
	/**
	 * 使用维护模块首页饼状图的数据
	 * @param conditionPie
	 * @return Map<String, Object
	 */
	public Map<String, Object>getPieFindProvince(Map<String, Object>conditionPie){
		Map<String, Object>map_pie = new HashMap<String,Object>();
		
		List<Object>pie_data=jbxxService.getPieFindProvice(conditionPie);
		List<Object>xmlbList=jbxxService.getsyzt();
		
		for (Object xmlb : xmlbList) {
            Object value = null;
            if (xmlb == null || xmlb.toString() == "") {
                continue;
            }
            for (Object object : pie_data) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                if (xmlb.equals(map.get("syzt"))) {
                    value = map.get("all_tz");
                }
            }
            map_pie.put((String) xmlb, value == null ? 0 : (Double)value);
        }
		
		return map_pie;
	}
	
//	/**
//	 * 使用维护模块首页根据城市分组显示中央投资和地方投资求和
//	 * @param conditionColumn
//	 * @param areaList
//	 * @return
//	 */
//	public Map<String, Double> getColumnData(Map<String, Object>conditionColumn,List<Object>areaList){
//		
//		List<Object> sfsxList = null;
//		if(conditionColumn.get("szsf")==null || conditionColumn.get("szsf").equals("null")){
//			sfsxList = statisticsService.getProvinceShort();// 省份简称集合
//		}else if(conditionColumn.get("szsf")!=null || !conditionColumn.get("szsf").equals("null")){
//			String codeName=(String)conditionColumn.get("szsf");
//			sfsxList=jbxxService.findCity(codeName);
//		}
//		
//		List<Object>queryList=jbxxService.getDxtzGroupByArea(conditionColumn);
//		Map<String, Double> resultMap = new HashMap<String, Double>();
//      if(areaList != null && !areaList.isEmpty()){
//          for (Object area : areaList) {
//              if (area == null || area.toString() == "") {
//                  continue;
//              }
//              double value = 0.0;
//              for (Object object : queryList) {
//                  @SuppressWarnings("unchecked")
//                  Map<String, Object> map = (Map<String, Object>) object;
//                  if (area.equals(map.get("area"))) {
//                      value = map.get("dxtz") == null ? 0.0 : (Double)map.get("dxtz");
//                  }
//              }
//              Object areaSx = area;
//              if ("1".equals(conditionColumn.get("roleName"))) {
//                  for (Object obj : sfsxList) {
//                      if (area.toString().indexOf(obj.toString()) >= 0) {
//                          areaSx = obj;
//                      }
//                  }
//              }
//              resultMap.put((String) areaSx, value);
//          }
//      }else{
//          double value = 0.0;
//          for (Object object : queryList) {
//              @SuppressWarnings("unchecked")
//              Map<String, Object> map = (Map<String, Object>) object;
//              double dxtz = map.get("dxtz") == null ? 0.0 : (Double)map.get("dxtz");
//              value = ArithUtil.add(value, dxtz);
//          }
//         // double removeVal = removeMap.get("noAreaSum") == null ? 0.0 : removeMap.get("noAreaSum");//无边防市，则建为noAreaSum
//          //value = value - removeVal;
//          //resultMap.put(paramMap.get("szsf") != null ? (String)paramMap.get("szsf") : "", value);//无边防市以省替代
//      }
//      return sortMap(resultMap);
//	}
//	
//	/**
//	 * 使用维护模块首页根据项目类别分组统计中央投资和地方投资求和
//	 * @param conditionXmlb
//	 * @return Map<String, Double>
//	 * @date [2017-11-24] 创建文件 by 郎川
//	 * 
//	 */
//	public Map<String, Double>getColumnFindByXmlb(Map<String, Object>conditionXmlb){
//    	Map<String, Double> resultMap = new HashMap<String, Double>();
//    	List<Object> queryList = jbxxService.getJstzGroupByXmlb(conditionXmlb);
//    	List<Object> xmlbList = jbxxService.getXmlbData();
//    	for (Object xmlb : xmlbList) {
//            if (xmlb == null || "".equals(((String)xmlb).trim())) {
//                continue;
//            }
//            double value = 0.0;
//            for (Object object : queryList) {
//                @SuppressWarnings("unchecked")
//                Map<String, Object> map = (Map<String, Object>) object;
//                if (xmlb.equals(map.get("xmlb"))) {
//                    value = map.get("dxtz") == null ? 0.0 : (Double)map.get("dxtz");
//                }
//            }
//            //double removeVal = removeMap.get(xmlb) == null ? 0.0 : removeMap.get(xmlb);
//            //value = value - removeVal;
//            resultMap.put((String) xmlb, value);
//        }
//        return sortMap(resultMap);
//    }
    
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
     * @Title getWxfyGroupByArea 
     * @Description (获取各个地区的维修费用) 
     * @param param
     * @param areaList
     * @return
     * @Return Map<String,Object> 返回类型 
     * @Throws 
     * @Date  2017年12月27日
     * @修改历史  
     *     1. [2017年12月27日]创建文件 by 顾冲
     */
    public Map<String, Double> getWxfyGroupByArea(Map<String, Object> param, List<Object> areaList){
        List<Object> list = xmwhjlService.getWxfyGroupByArea(param);
        Map<String, Double> resultMap = new HashMap<String, Double>();
        for (Object area : areaList) {
            if(area == null){
                continue;
            }
            Object wxfy = null;
            for (Object obj : list) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>)obj;
                String szdq = map.get("area") == null ? "" : map.get("area").toString();
                if(szdq.equals(area)){
                    wxfy = map.get("wxfy");
                }
            }
            resultMap.put(area.toString(), wxfy == null ? 0.0 : ((BigDecimal)wxfy).doubleValue());
        }
        return sortMap(resultMap);
    }
    
    /**
     * @Title getWxfyGroupByXmlb 
     * @Description (获取各种项目类别的维修费用) 
     * @param condition
     * @return
     * @Return Map<String,Double> 返回类型 
     * @Throws 
     * @Date  2017年12月27日
     * @修改历史  
     *     1. [2017年12月27日]创建文件 by 顾冲
     */
    public Map<String, Double> getWxfyGroupByXmlb(Map<String, Object> condition){
        List<Object> list = xmwhjlService.getWxfyGroupByXmlb(condition);
        List<DICT_GY> xmlbList = dict_gyService.getDict_GYsByType("项目类别");
        Map<String, Double> resultMap = new HashMap<String, Double>();
        for (DICT_GY dict_gy : xmlbList) {
            if(dict_gy == null){
                continue;
            }
            Object wxfy = null;
            for (Object obj : list) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>)obj;
                String xmlb = map.get("xmlb") == null ? "" : map.get("xmlb").toString();
                if(xmlb.equals(dict_gy.getCodeName())){
                    wxfy = map.get("wxfy");
                }
            }
            resultMap.put(dict_gy.getCodeName(), wxfy == null ? 0.0 : ((BigDecimal)wxfy).doubleValue());
        }
        return sortMap(resultMap);
    }
}
