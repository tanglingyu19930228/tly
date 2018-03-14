package com.ljdy.BHF.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljdy.BHF.model.AddBjxl;
import com.ljdy.BHF.model.AddBjzz;
import com.ljdy.BHF.model.AddBzp;
import com.ljdy.BHF.model.AddCsxl;
import com.ljdy.BHF.model.AddGdxt;
import com.ljdy.BHF.model.AddGld;
import com.ljdy.BHF.model.AddJbxx;
import com.ljdy.BHF.model.AddJjmlfpt;
import com.ljdy.BHF.model.AddJkz;
import com.ljdy.BHF.model.AddJkzx;
import com.ljdy.BHF.model.AddJuma;
import com.ljdy.BHF.model.AddLwt;
import com.ljdy.BHF.model.AddLzz;
import com.ljdy.BHF.model.AddQiaoLiang;
import com.ljdy.BHF.model.AddSpqd;
import com.ljdy.BHF.model.AddTsw;
import com.ljdy.BHF.model.AddTzl;
import com.ljdy.BHF.model.AddWrzsdzsb;
import com.ljdy.BHF.model.AddXkzd;
import com.ljdy.BHF.model.AddZqdl;
import com.ljdy.BHF.model.AddZqf;
import com.ljdy.BHF.model.AddZqmt;
import com.ljdy.BHF.model.AddZsjtjp;
import com.ljdy.BHF.model.Bjxl;
import com.ljdy.BHF.model.Bjzz;
import com.ljdy.BHF.model.Bzp;
import com.ljdy.BHF.model.ConstantParam;
import com.ljdy.BHF.model.Csxl;
import com.ljdy.BHF.model.DICT_AREA;
import com.ljdy.BHF.model.DICT_GY;
import com.ljdy.BHF.model.Gdxt;
import com.ljdy.BHF.model.Gld;
import com.ljdy.BHF.model.Jbxx;
import com.ljdy.BHF.model.Jjmlfpt;
import com.ljdy.BHF.model.Jkz;
import com.ljdy.BHF.model.Jkzx;
import com.ljdy.BHF.model.Juma;
import com.ljdy.BHF.model.Lwt;
import com.ljdy.BHF.model.Lzz;
import com.ljdy.BHF.model.PageBean;
import com.ljdy.BHF.model.ProvinceTabelData;
import com.ljdy.BHF.model.QiaoLiang;
import com.ljdy.BHF.model.Spqd;
import com.ljdy.BHF.model.Tsw;
import com.ljdy.BHF.model.Tzl;
import com.ljdy.BHF.model.User;
import com.ljdy.BHF.model.Wrzsdzsb;
import com.ljdy.BHF.model.Xkzd;
import com.ljdy.BHF.model.Zqdl;
import com.ljdy.BHF.model.Zqf;
import com.ljdy.BHF.model.Zqmt;
import com.ljdy.BHF.model.Zsjtjp;
import com.ljdy.BHF.service.BjxlService;
import com.ljdy.BHF.service.BjzzService;
import com.ljdy.BHF.service.BzpService;
import com.ljdy.BHF.service.CsxlService;
import com.ljdy.BHF.service.GdxtService;
import com.ljdy.BHF.service.GldService;
import com.ljdy.BHF.service.JjmlfptService;
import com.ljdy.BHF.service.JkzService;
import com.ljdy.BHF.service.JkzxService;
import com.ljdy.BHF.service.JumaService;
import com.ljdy.BHF.service.LwtService;
import com.ljdy.BHF.service.LzzService;
import com.ljdy.BHF.service.QiaoLiangService;
import com.ljdy.BHF.service.SpqdService;
import com.ljdy.BHF.service.TswService;
import com.ljdy.BHF.service.TzlService;
import com.ljdy.BHF.service.WrzsdzsbService;
import com.ljdy.BHF.service.XkzdService;
import com.ljdy.BHF.service.ZqdlService;
import com.ljdy.BHF.service.ZqfService;
import com.ljdy.BHF.service.ZqmtService;
import com.ljdy.BHF.service.ZsjtjpService;
import com.ljdy.BHF.utils.BeanToMap;
import com.ljdy.BHF.utils.CommonUtil;
import com.ljdy.BHF.utils.DateUtils;
import com.ljdy.BHF.utils.ExportUtil;

/**
 * 各类设施数据操作请求处理
 * 
 * @author 徐成 修改历史： [2017年9月28日] 创建文件 by 徐成
 */
@Scope("prototype")
@Controller
@RequestMapping("xmzl")
public class XmzlDataOperateController extends BaseController {

	/**
	 * 加载项目子类数据列表——初始化查询
	 * 
	 * @param request
	 * @return 修改历史： [2017年9月28日] 创建文件 by 徐成
	 */
	@RequestMapping("/loadDataTable")
	public String loadDataTable(
			HttpServletRequest request,
			@RequestParam Map<String, Object> condition,
			@RequestParam(required = false, defaultValue = "10") Integer rows,
			@RequestParam(value = "offiset", required = false, defaultValue = "1") Integer currentPage,
			ModelMap modelMap) {
		User user = (User)request.getSession().getAttribute("loginUser");
	    if(CommonUtil.validCondition(condition, "methodTag") && ("update".equals(condition.get("methodTag")) 
                || "add".equals(condition.get("methodTag")))){
	        if("1".equals(user.getRoleName())){
	            modelMap.addAttribute("szsf", condition.get("szsf"));//此为导航栏参数
	        }else if("2".equals(user.getRoleName())){
	            modelMap.addAttribute("szsf", condition.get("szcs"));//此为导航栏参数
	        }
	    }else{
	        modelMap.addAttribute("szsf", condition.get("szsf"));
	    }
		modelMap.addAttribute("xmlb", condition.get("xmlb"));
		modelMap.addAttribute("xmzl", condition.get("xmzl"));
		modelMap.addAttribute("year", DateUtils.getYear());
		modelMap.addAttribute("partTag", condition.get("partTag"));
		if("2".equals(user.getRoleName())){
		    if("上海市".equals(user.getProvince()) || "天津市".equals(user.getProvince())){
		    	modelMap.addAttribute("szsf", user.getProvince());
		    }
		}

		if("1".equals(condition.get("partTag")) || "2".equals(condition.get("partTag"))){
		    condition.put("bxxm", "是");
		}else if("3".equals(condition.get("partTag")) || "4".equals(condition.get("partTag"))){
		    condition.put("bxxm", "否");
		}
		if("4".equals(condition.get("partTag"))){
		    //TODO  建设状态是否为“已审计”
		    condition.put("jszt", "已审计");
		}
		// 上海、天津没有边防市的需要特殊处理   
		if(!"上海市".equals(user.getProvince()) && !"天津市".equals(user.getProvince())){
		    if("2".equals(user.getRoleName())){
		        //condition中szsf表示地区，省级用户登陆时是szcs
		        if(CommonUtil.validCondition(condition, "methodTag") && ("update".equals(condition.get("methodTag")) 
		                || "add".equals(condition.get("methodTag")))){ 
		            condition.put("szcs", condition.get("szcs"));
		        }else{
		            condition.put("szcs", condition.get("szsf"));
		        }
		        condition.put("szsf", user.getProvince());
		    }else if("1".equals(user.getRoleName())){
		        condition.put("szsf", condition.get("szsf"));
		    }
		}else{
		    condition.put("szcs", null);
		    condition.put("szsf", user.getProvince());
		}
		
		//组装页面导航栏数据
		if("1".equals(user.getRoleName())){
		    condition.put("szcs", null);//国家级用户区域只根据szsf查询，去除重定向来的参数
			modelMap.addAttribute("areaList", statisticsService.getProvinceShort());
		}else if("2".equals(user.getRoleName())){
			if(!"上海市".equals(user.getProvince()) && !"天津市".equals(user.getProvince())){
				modelMap.addAttribute("areaList", statisticsService.getCityByProvince(user.getProvince()));
			}
		}

		// 查询项目类别 以及项目类别
		List<DICT_GY> xmlblist = dict_gyService.getDict_GYsByType("项目类别");
		List<DICT_GY> xmzllist = dict_gyService.getDict_GYsByType("项目子类");
		modelMap.addAttribute("xmlbList", xmlblist);
		modelMap.addAttribute("xmzlList", xmzllist);
		// 建设状态
		if ("3".equals(condition.get("partTag"))) {
			List<DICT_GY> list_jszt = dict_gyService.getDict_GYsByType("建设状态");
			modelMap.addAttribute("list_jszt", list_jszt);
		}
		//使用状态
		if("4".equals(condition.get("partTag"))){
		    List<DICT_GY>list_syzt=dict_gyService.getDict_GYsByType("使用状态");
            modelMap.addAttribute("list_syzt", list_syzt);
		}
		String xmzl = "";// 项目子类
		if (CommonUtil.validCondition(condition, "xmzl")) {
			xmzl = (String) condition.get("xmzl");
		}
		if ("2".equals(condition.get("partTag"))) {
			condition.put("tznd", String.valueOf(DateUtils.getYear()));
		}
		List<Object> dataList=null;
		if ("执勤道路".equals(xmzl)) {
			dataList=zqdlService.getDataByCondition(condition, currentPage, rows);
			// 执勤道路 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = zqdlService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 执勤道路 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = zqdlService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 执勤道路 中央投资
			List<Object> zytz = zqdlService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 执勤道路 地方投资
			List<Object> dftz = zqdlService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));

			// 道路类别
			List<DICT_GY> dllbList = dict_gyService.getDict_GYsByType("道路类别");
			modelMap.addAttribute("dllbList", dllbList);
			// 道路类型
			List<DICT_GY> dllxList = dict_gyService.getDict_GYsByType("道路类型");
			modelMap.addAttribute("dllxList", dllxList);
			// 道路等级
			List<DICT_GY> dldjList = dict_gyService.getDict_GYsByType("道路等级");
			modelMap.addAttribute("dldjList", dldjList);
			// 路基类型
			List<DICT_GY> ljlxList = dict_gyService.getDict_GYsByType("路基类型");
			modelMap.addAttribute("ljlxList", ljlxList);
			// 路面类型
			List<DICT_GY> lmlxList = dict_gyService.getDict_GYsByType("路面类型");
			modelMap.addAttribute("lmlxList", lmlxList);

		} else if ("桥梁".equals(xmzl)) {
			dataList=qiaoLiangService.getDataByCondition(condition, currentPage,
					rows);
			// 桥梁 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = qiaoLiangService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 桥梁 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = qiaoLiangService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 桥梁 中央投资
			List<Object> zytz = qiaoLiangService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 桥梁 地方投资
			List<Object> dftz = qiaoLiangService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));
			// 桥梁类型
			List<DICT_GY> qllxList = dict_gyService.getDict_GYsByType("桥梁类型");
			modelMap.addAttribute("qllxList", qllxList);
		} else if ("执勤码头".equals(xmzl)) {
			dataList=zqmtService.getDataByCondition(condition, currentPage, rows);
			// 执勤码头 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = zqmtService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 执勤码头 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = zqmtService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 执勤道路 中央投资
			List<Object> zytz = zqmtService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 执勤道路 地方投资
			List<Object> dftz = zqmtService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));
		} else if ("直升机停机坪".equals(xmzl)) {
			dataList=zsjtjpService.getDataByCondition(condition, currentPage,
					rows);
			// 直升机停机坪 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = zsjtjpService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 直升机停机坪 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = zsjtjpService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 直升机停机坪
			List<Object> zytz = zsjtjpService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 直升机停机坪
			List<Object> dftz = zsjtjpService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));
			// 停机坪类型
			List<DICT_GY> tjpList = dict_gyService.getDict_GYsByType("停机坪类型");
			modelMap.addAttribute("tjplxList", tjpList);
		} else if ("铁丝网".equals(xmzl)) {
			 dataList=tswService.getDataByCondition(condition, currentPage, rows);
			// 铁丝网 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = tswService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 铁丝网 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = tswService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 铁丝网 中央投资
			List<Object> zytz = tswService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 铁丝网 地方投资
			List<Object> dftz = tswService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));

			// 铁丝网类型
			List<DICT_GY> tswList = dict_gyService.getDict_GYsByType("铁丝网类型");
			modelMap.addAttribute("tswlxList", tswList);
		} else if ("铁栅栏".equals(xmzl)) {
			dataList=tzlService.getDataByCondition(condition, currentPage, rows);
			// 铁栅栏 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = tzlService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 铁栅栏 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = tzlService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 铁栅栏 中央投资
			List<Object> zytz = tzlService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 铁栅栏 地方投资
			List<Object> dftz = tzlService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));

		} else if ("拦阻桩".equals(xmzl)) {
			dataList=lzzService.getDataByCondition(condition, currentPage, rows);
			// 拦阻桩 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = lzzService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 拦阻桩 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = lzzService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 拦阻桩 中央投资
			List<Object> zytz = lzzService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 拦阻桩 地方投资
			List<Object> dftz = lzzService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));

			// 拦阻桩类型
			List<DICT_GY> lzzList = dict_gyService.getDict_GYsByType("拦阻桩类型");
			modelMap.addAttribute("lzzlxList", lzzList);
		} else if ("隔离带".equals(xmzl)) {
			 dataList=gldService.getDataByCondition(condition, currentPage, rows);
			// 隔离带 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = gldService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 隔离带 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = gldService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 隔离带 中央投资
			List<Object> zytz = gldService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 隔离带 地方投资
			List<Object> dftz = gldService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));

		} else if ("拒马".equals(xmzl)) {
			 dataList=jumaService.getDataByCondition(condition, currentPage, rows);
			// 骏马 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = jumaService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 骏马 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = jumaService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 骏马 中央投资
			List<Object> zytz = jumaService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 骏马 地方投资
			List<Object> dftz = jumaService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));
		} else if ("监控中心".equals(xmzl)) {
			dataList=jkzxService.getDataByCondition(condition, currentPage, rows);
			// 向上联通情况
			List<DICT_GY> list_ltqk = dict_gyService.getDict_GYsByType("联通情况");
			modelMap.addAttribute("list_ltqk", list_ltqk);

			// 向上联通网络性质
			List<DICT_GY> list_ltwlxz = dict_gyService
					.getDict_GYsByType("联通网络性质");
			modelMap.addAttribute("list_ltwlxz", list_ltwlxz);
			// 向上传输线路
			List<DICT_GY> list_csxl = dict_gyService.getDict_GYsByType("传输线路");
			modelMap.addAttribute("list_csxl", list_csxl);
			// 级别
			List<DICT_GY> list_jb = dict_gyService.getDict_GYsByType("级别");
			modelMap.addAttribute("list_jb", list_jb);

			// 监控中心 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = jkzxService.getCountByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0));

			// 监控中心 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = jkzxService.getCountByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0));

			// 监控中心 中央投资
			List<Object> zytz = jkzxService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 监控中心 地方投资
			List<Object> dftz = jkzxService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));
		} else if ("监控站".equals(xmzl)) {
			dataList=jkzService.getDataByCondition(condition, currentPage, rows);
			// 向上联通情况
			List<DICT_GY> list_ltqk = dict_gyService.getDict_GYsByType("联通情况");
			modelMap.addAttribute("list_ltqk", list_ltqk);

			// 向上联通网络性质
			List<DICT_GY> list_ltwlxz = dict_gyService
					.getDict_GYsByType("联通网络性质");
			modelMap.addAttribute("list_ltwlxz", list_ltwlxz);

			// 向上传输线路
			List<DICT_GY> list_csxl = dict_gyService.getDict_GYsByType("传输线路");
			modelMap.addAttribute("list_csxl", list_csxl);

			// 监控中心 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = jkzService.getCountByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0));

			// 监控中心 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = jkzService.getCountByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0));

			// 监控中心 中央投资
			List<Object> zytz = jkzService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 监控中心 地方投资
			List<Object> dftz = jkzService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));

		} else if ("传输线路".equals(xmzl)) {
			dataList=csxlService.getDataByCondition(condition, currentPage, rows);
			// 线路类型
			List<DICT_GY> list_xllx = dict_gyService.getDict_GYsByType("传输线路");
			modelMap.addAttribute("list_xllx", list_xllx);

			// 线路性质
			List<DICT_GY> list_xlxz = dict_gyService.getDict_GYsByType("线路性质");
			modelMap.addAttribute("list_xlxz", list_xlxz);

			// 传输线路 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = csxlService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0));

			// 传输线路 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = csxlService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 传输线路 中央投资
			List<Object> zytz = csxlService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 传输线路 地方投资
			List<Object> dftz = csxlService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));

		} else if ("供电系统".equals(xmzl)) {
			dataList=gdxtService.getDataByCondition(condition, currentPage, rows);
			// 供电系统 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = gdxtService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 供电系统 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = gdxtService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 供电系统 中央投资
			List<Object> zytz = gdxtService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 供电系统 地方投资
			List<Object> dftz = gdxtService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));

		} else if ("军警民联防平台".equals(xmzl)) {
			 dataList=jjmlfptService.getDataByCondition(condition, currentPage,
					rows);
			// 向上联通情况
			List<DICT_GY> list_ltqk = dict_gyService.getDict_GYsByType("联通情况");
			modelMap.addAttribute("list_ltqk", list_ltqk);

			// 向上联通网络性质
			List<DICT_GY> list_ltwlxz = dict_gyService
					.getDict_GYsByType("联通网络性质");
			modelMap.addAttribute("list_ltwlxz", list_ltwlxz);

			// 向上传输线路
			List<DICT_GY> list_csxl = dict_gyService.getDict_GYsByType("传输线路");
			modelMap.addAttribute("list_csxl", list_csxl);

			// 横向联通情况
			modelMap.addAttribute("hx_list_ltqk", list_ltqk);

			// 横向联通网络性质
			modelMap.addAttribute("hx_list_ltwlxz", list_ltwlxz);

			// 横向传输线路
			modelMap.addAttribute("hx_list_csxl", list_csxl);

			// 级别
			List<DICT_GY> list_jb = dict_gyService.getDict_GYsByType("级别");
			modelMap.addAttribute("list_jb", list_jb);

			// 军警民联防平台 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = jjmlfptService.getCountByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0));

			// 军警民联防平台 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = jjmlfptService.getCountByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0));

			// 军警民联防平台 中央投资
			List<Object> zytz = jjmlfptService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 军警民联防平台 地方投资
			List<Object> dftz = jjmlfptService.getZytzSum();
			modelMap.put("dftz", dftz.get(0));

		} else if ("无人值守电子哨兵".equals(xmzl)) {
			dataList=wrzsdzsbService.getDataByCondition(condition, currentPage,
					rows);
			// 无人值守电子哨兵 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = wrzsdzsbService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 无人值守电子哨兵 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = wrzsdzsbService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 无人值守电子哨兵 中央投资
			List<Object> zytz = wrzsdzsbService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 无人值守电子哨兵 地方投资
			List<Object> dftz = wrzsdzsbService.getZytzSum();
			modelMap.put("dftz", dftz.get(0));

		} else if ("直升机停机坪".equals(xmzl)) {
			dataList=zsjtjpService.getDataByCondition(condition, currentPage,
					rows);
			// 停机坪类型
			List<DICT_GY> tjpList = dict_gyService.getDict_GYsByType("停机坪类型");
			modelMap.addAttribute("tjplxList", tjpList);
		} else if ("报警线路".equals(xmzl)) {
			dataList=bjxlService.getDataByCondition(condition, currentPage, rows);
			// 报警线路 新增
			condition.put("jsxz", "新建");
			List<Object> list_xj = bjxlService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 报警线路 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = bjxlService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 报警线路 中央投资
			List<Object> zytz = bjxlService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 报警线路 地方投资
			List<Object> dftz = bjxlService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));

		} else if ("视频前端".equals(xmzl)) {
			dataList=spqdService.getDataByCondition(condition, currentPage, rows);
			// 视频前端类型
			List<DICT_GY> list_spqdllx = dict_gyService
					.getDict_GYsByType("视频前端类型");
			modelMap.addAttribute("list_spqdllx", list_spqdllx);

			// 视频前端 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = spqdService.getCountByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0));

			// 视频前端 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = spqdService.getCountByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0));

			// 视频前端 中央投资
			List<Object> zytz = spqdService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 视频前端 中央投资
			List<Object> dftz = spqdService.getZytzSum();
			modelMap.put("dftz", dftz.get(0));

		} else if ("显控终端".equals(xmzl)) {
			dataList=xkzdService.getDataByCondition(condition, currentPage, rows);
			// 显控终端 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = xkzdService.getCountByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0));

			// 显控终端 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = xkzdService.getCountByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0));

			// 显控终端 中央投资
			List<Object> zytz = xkzdService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 显控终端 地方投资
			List<Object> dftz = xkzdService.getZytzSum();
			modelMap.put("dftz", dftz.get(0));

		} else if ("报警装置".equals(xmzl)) {
			 dataList=bjzzService.getDataByCondition(condition, currentPage, rows);
			// 报警装置 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = bjzzService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 报警装置 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = bjzzService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 报警装置 中央投资
			List<Object> zytz = bjzzService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 报警装置 地方投资
			List<Object> dftz = bjzzService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));

		} else if ("执勤房".equals(xmzl)) {
			dataList=zqfService.getDataByCondition(condition, currentPage, rows);
			// 执勤房 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = zqfService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 执勤房 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = zqfService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 执勤房 中央投资
			List<Object> zytz = zqfService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 执勤房 地方投资
			List<Object> dftz = zqfService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));

			// 执勤房类型
			List<DICT_GY> list_zqflx = dict_gyService
					.getDict_GYsByType("执勤房类型");
			modelMap.addAttribute("list_zqflx", list_zqflx);

		} else if ("了望塔".equals(xmzl)) {
			dataList=lwtService.getDataByCondition(condition, currentPage, rows);
			// 了望塔 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = lwtService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 了望塔 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = lwtService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_xj.get(0));

			// 了望塔 中央投资
			List<Object> zytz = lwtService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 了望塔 地方投资
			List<Object> dftz = lwtService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));

			// 了望塔类型
			List<DICT_GY> list_lwtlx = dict_gyService
					.getDict_GYsByType("了望塔类型");
			modelMap.addAttribute("list_lwtlx", list_lwtlx);

		} else if ("标志牌".equals(xmzl)) {
			dataList=bzpService.getDataByCondition(condition, currentPage, rows);
			// 标志牌 新建
			condition.put("jsxz", "新建");
			List<Object> list_xj = bzpService.getSumByJsxz(condition);
			modelMap.put("list_xj", list_xj.get(0) == null ? 0 : list_xj.get(0));

			// 标志牌 升级
			condition.put("jsxz", "升级");
			List<Object> list_sj = bzpService.getSumByJsxz(condition);
			modelMap.put("list_sj", list_sj.get(0) == null ? 0 : list_sj.get(0));

			// 标志牌 中央投资
			List<Object> zytz = bzpService.getZytzSum();
			modelMap.put("zytz", zytz.get(0));

			// 标志牌 地方投资
			List<Object> dftz = bzpService.getDftzSum();
			modelMap.put("dftz", dftz.get(0));
			// 标志牌类型
			List<DICT_GY> list_bzplx = dict_gyService
					.getDict_GYsByType("标志牌类型");
			modelMap.addAttribute("list_bzplx", list_bzplx);
			modelMap.addAttribute("list_bzplx", list_bzplx);

		}
		PageBean pageBean=getPageBean(currentPage, rows, dataList);
		modelMap.addAttribute("pageBean", pageBean);
		// 下拉列表数据
		// 项目类别
		List<DICT_GY> xmlbList = dict_gyService.getDict_GYsByType("项目类别");
		modelMap.addAttribute("xmlbList", xmlbList);
		// 项目子类
		List<DICT_GY> xmzlList = dict_gyService.getDict_GYsByType("项目子类");
		modelMap.addAttribute("xmzlList", xmzlList);
		// 设施类型
		List<DICT_GY> sslxList = dict_gyService.getDict_GYsByType("设施类型");
		modelMap.addAttribute("sslxList", sslxList);
		// 边界方向
		List<DICT_GY> bjfxList = dict_gyService.getDict_GYsByType("边界方向");
		modelMap.addAttribute("bjfxList", bjfxList);
		// 地形类别
		List<DICT_GY> dxlbList = dict_gyService.getDict_GYsByType("地形类别");
		modelMap.addAttribute("dxlbList", dxlbList);
		// 建设性质
		List<DICT_GY> jsxzList = dict_gyService.getDict_GYsByType("建设性质");
		modelMap.addAttribute("jsxzList", jsxzList);

		// 省份用户是本省，次参数不需要传，页面处理
		List<DICT_AREA> sfList = dict_AREAService.findProvince();
		modelMap.addAttribute("sfList", sfList);
		return "provinceTableDetail";
	}

	/**
	 * 项目子类数据新增
	 * 
	 * @return 修改历史： [2017年9月28日] 创建文件 by 徐成
	 */
	@RequestMapping("/add")
	public @ResponseBody
	Map<String, Object> add(HttpServletRequest request, Jbxx jbxx) {
	    User user = (User) request.getSession().getAttribute("loginUser");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("methodTag", "add");// 方法标识，重定向时使用
		String xmzl = jbxx.getXmzl();
		String partTag = request.getParameter("partTag");
		resultMap.put("xmlb", jbxx.getXmlb());
		resultMap.put("xmzl", xmzl);
		if("1".equals(user.getRoleName())){
		    resultMap.put("szsf",
		            dict_AREAService.getCodeNameByCodeValue(jbxx.getSzsf()));
		}else if("2".equals(user.getRoleName())){
		    resultMap.put("szsf", user.getProvince());
		}
		resultMap.put("szcs",
				dict_AREAService.getCodeNameByCodeValue(jbxx.getSzcs()));
		if (partTag.equals("1") || partTag.equals("2")) {
			jbxx.setBxxm("是");
		} else {
			jbxx.setBxxm("否");
		}

		jbxx.setXmbh(CommonUtil.createUUID());
		jbxx.setId(CommonUtil.createUUID());
		jbxx.setTime(new Date());
		if ("执勤道路".equals(xmzl)) {
			Zqdl zqdl = jbxx.getZqdl();
			zqdl.setId(jbxx.getId());
			AddZqdl addZqdl = new AddZqdl(zqdl.getJsdd(), zqdl.getJsgm(),
					zqdl.getDllb(), zqdl.getDllx(), zqdl.getDldj(),
					zqdl.getLjlx(), zqdl.getLmlx(), zqdl.getId());
			zqdlService.addZqdl(addZqdl);
			saveJbxx(jbxx, user);
		} else if ("桥梁".equals(xmzl)) {
			QiaoLiang qiaoLiang = jbxx.getQiaoLiang();
			qiaoLiang.setId(jbxx.getId());
			AddQiaoLiang addQiaoLiang = new AddQiaoLiang(qiaoLiang.getJsdd(),
					qiaoLiang.getJsgm(), qiaoLiang.getQllx(),
					qiaoLiang.getZz(), qiaoLiang.getId());
			qiaoLiangService.addQiaoLiang(addQiaoLiang);
			saveJbxx(jbxx, user);
		} else if ("执勤码头".equals(xmzl)) {
			Zqmt zqmt = jbxx.getZqmt();
			zqmt.setId(jbxx.getId());
			AddZqmt addZqmt = new AddZqmt(zqmt.getJsgm(), zqmt.getJsdd(),
					zqmt.getId());
			zqmtService.addZqmt(addZqmt);
			saveJbxx(jbxx, user);
		} else if ("直升机停机坪".equals(xmzl)) {
			Zsjtjp zsjtjp = jbxx.getZsjtjp();
			zsjtjp.setId(jbxx.getId());
			AddZsjtjp addZsjtjp = new AddZsjtjp(zsjtjp.getJsdd(),
					zsjtjp.getJsgm(), zsjtjp.getTjplx(), zsjtjp.getId());
			zsjtjpService.addZsjtjp(addZsjtjp);
			saveJbxx(jbxx, user);
		} else if ("铁丝网".equals(xmzl)) {
			Tsw tsw = jbxx.getTsw();
			tsw.setId(jbxx.getId());
			AddTsw addTsw = new AddTsw(tsw.getJsdd(), tsw.getJsgm(),
					tsw.getLzmgs(), tsw.getTswlx(), tsw.getId());
			tswService.addtsw(addTsw);
			saveJbxx(jbxx, user);
		} else if ("铁栅栏".equals(xmzl)) {
			Tzl tzl = jbxx.getTzl();
			tzl.setId(jbxx.getId());
			AddTzl addTzl = new AddTzl(tzl.getJsdd(), tzl.getJsgm(),
					tzl.getId());
			tzlService.addTzl(addTzl);
			saveJbxx(jbxx, user);
		} else if ("拦阻桩".equals(xmzl)) {
			Lzz lzz = jbxx.getLzz();
			lzz.setId(jbxx.getId());
			AddLzz addLzz = new AddLzz(lzz.getJsdd(), lzz.getJsgm(),
					lzz.getLzzlx(), lzz.getId());
			lzzService.addlzz(addLzz);
			saveJbxx(jbxx, user);
		} else if ("隔离带".equals(xmzl)) {
			Gld gld = jbxx.getGld();
			gld.setId(jbxx.getId());
			AddGld addGld = new AddGld(gld.getJsdd(), gld.getJsgm(),
					gld.getId());
			gldService.addGld(addGld);
			saveJbxx(jbxx, user);
		} else if ("报警线路".equals(xmzl)) {
			Bjxl bjxl = jbxx.getBjxl();
			bjxl.setId(jbxx.getId());
			AddBjxl addBjxl = new AddBjxl(bjxl.getJsdd(), bjxl.getJsgm(),
					bjxl.getSbpp(), bjxl.getId());
			bjxlService.addBjxl(addBjxl);
			saveJbxx(jbxx, user);
		} else if ("拒马".equals(xmzl)) {
			Juma juma = jbxx.getJuma();
			juma.setId(jbxx.getId());
			AddJuma addJuma = new AddJuma(juma.getJsgm(), juma.getJsdd(),
					juma.getId());
			jumaService.addJuma(addJuma);
			saveJbxx(jbxx, user);
		} else if ("监控中心".equals(xmzl)) {
			Jkzx jkzx = jbxx.getJkzx();
			jkzx.setId(jbxx.getId());
			AddJkzx addJkzx = new AddJkzx(jkzx.getXsltqk(), jkzx.getXsltwlxz(),
					jkzx.getXscsxl(), jkzx.getJb(), jkzx.getId());
			jkzxService.addJkzx(addJkzx);
			saveJbxx(jbxx, user);
		} else if ("监控站".equals(xmzl)) {
			Jkz jkz = jbxx.getJkz();
			jkz.setId(jbxx.getId());
			AddJkz addJkz = new AddJkz(jkz.getXsltqk(), jkz.getXsltwlxz(),
					jkz.getXscsxl(), jkz.getXkzdgs(), jkz.getYdqdgs(),
					jkz.getGdqdgs(), jkz.getId());
			jkzService.addJkz(addJkz);
			saveJbxx(jbxx, user);
		} else if ("视频前端".equals(xmzl)) {
			Spqd spqd = jbxx.getSpqd();
			spqd.setId(jbxx.getId());
//		  改动过的
			@SuppressWarnings("unchecked")
            Jkz jkz=(Jkz)BeanToMap.transMapToBean((Map<String, Object>) jkzService.getJkzNameByJkzId(spqd.getJkz_id()),Jkz.class);
			spqd.setJkz_name(jkz.getXmmc());
			AddSpqd addSpqd = new AddSpqd(spqd.getJkz_id(), spqd.getJkz_name(),
					spqd.getFzdd(), spqd.getCsfs(), spqd.getSblx(),
					spqd.getId());
			spqdService.addSpqd(addSpqd);
			// addJbxx(jbxx, spqd);
			// spqdService.addSpqd(spqd);
			// 修改jkz中spqdgs
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("jkz_id", spqd.getJkz_id());
			condition.put("sblx", spqd.getSblx());
			saveJbxx(jbxx,user);
			Long spqdgs = spqdService.countByCondition(condition);// 移动前端个数或固定前端个数
			String zdm = "ydqdgs";
			if (ConstantParam.GDS.equals(spqd.getSblx())) {
				zdm = "gdqdgs";
			}
			// 需要进一步修改
			jkzService.updateZlgs(zdm, spqd.getJkz_id(), spqdgs);
			
		} else if ("显控终端".equals(xmzl)) {
			Xkzd xkzd = jbxx.getXkzd();
			xkzd.setId(jbxx.getId());
		// 改动过的
			@SuppressWarnings("unchecked")
            Jkz jkz=(Jkz)BeanToMap.transMapToBean((Map<String, Object>) jkzService.getJkzNameByJkzId(xkzd.getJkz_id()),Jkz.class);
			xkzd.setJkz_name(jkz.getXmmc());
			AddXkzd addXkzd = new AddXkzd(xkzd.getJkz_id(), xkzd.getJkz_name(),
					xkzd.getFzdd(), xkzd.getCsfs(), xkzd.getId());
			xkzdService.addXkzd(addXkzd);
			// addJbxx(jbxx, xkzd);
			// xkzdService.addXkzd(xkzd);
			// 修改jkz中xkzdgs
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("jkz_id", xkzd.getJkz_id());
			saveJbxx(jbxx, user);
			Long xkzdgs = xkzdService.countByCondition(condition);// 显控终端条数
			// 需要进一步修改
			jkzService.updateZlgs("xkzdgs", xkzd.getJkz_id(), xkzdgs);

		} else if ("传输线路".equals(xmzl)) {
			Csxl csxl = jbxx.getCsxl();
			csxl.setId(jbxx.getId());
         //	改动过的	
			@SuppressWarnings("unchecked")
            Jkz jkz=(Jkz)BeanToMap.transMapToBean((Map<String, Object>) jkzService.getJkzNameByJkzId(csxl.getJkz_id()),Jkz.class);
			csxl.setJkz_name(jkz.getXmmc());
			AddCsxl addCsxl = new AddCsxl(csxl.getJkz_id(), csxl.getJkz_name(),
					csxl.getXlqd(), csxl.getXlzd(), csxl.getJsdd(),
					csxl.getJsgm(), csxl.getXlxz(), csxl.getXllx(),
					csxl.getId());
			csxlService.addCsxl(addCsxl);
			saveJbxx(jbxx, user);
			// addJbxx(jbxx, csxl);
			// csxlService.addCsxl(csxl);
		} else if ("报警装置".equals(xmzl)) {
			Bjzz bjzz = jbxx.getBjzz();
			bjzz.setId(jbxx.getId());
		//改动过的	
			@SuppressWarnings("unchecked")
            Jkz jkz=(Jkz)BeanToMap.transMapToBean((Map<String, Object>) jkzService.getJkzNameByJkzId(bjzz.getJkz_id()),Jkz.class);
			bjzz.setJkz_name(jkz.getXmmc());
			AddBjzz addBjzz = new AddBjzz(bjzz.getJkz_id(), bjzz.getJkz_name(),
					bjzz.getJsgm(), bjzz.getSbpp(), bjzz.getSbxh(),
					bjzz.getId());
			bjzzService.addBjzz(addBjzz);
			saveJbxx(jbxx, user);
			// addJbxx(jbxx, bjzz);
			// bjzzService.addBjzz(bjzz);
		} else if ("供电系统".equals(xmzl)) {
			Gdxt gdxt = jbxx.getGdxt();
		//  改动过的
			gdxt.setId(jbxx.getId());
			@SuppressWarnings("unchecked")
            Jkz jkz=(Jkz)BeanToMap.transMapToBean((Map<String, Object>) jkzService.getJkzNameByJkzId(gdxt.getJkz_id()),Jkz.class);
			gdxt.setJkz_name(jkz.getXmmc());
			
			AddGdxt addGdxt = new AddGdxt(gdxt.getJkz_id(), gdxt.getJkz_name(),
					gdxt.getJsdd(), gdxt.getJsgm(), gdxt.getId());
			gdxtService.addGdxt(addGdxt);
			saveJbxx(jbxx, user);
			// addJbxx(jbxx, gdxt);
			// gdxtService.addGdxt(gdxt);

		} else if ("军警民联防平台".equals(xmzl)) {
			Jjmlfpt jjmlfpt = jbxx.getJjmlfpt();
			jjmlfpt.setId(jbxx.getId());
			AddJjmlfpt addJjmlfpt = new AddJjmlfpt(jjmlfpt.getXsltqk(),
					jjmlfpt.getXsltwlxz(), jjmlfpt.getXscsxl(),
					jjmlfpt.getHxltqk(), jjmlfpt.getHxltwlxz(),
					jjmlfpt.getHxcsxl(), jjmlfpt.getJb(), jjmlfpt.getFzdd(),
					jjmlfpt.getId());
			jjmlfptService.addJjmlfpt(addJjmlfpt);
			saveJbxx(jbxx, user);
			// addJbxx(jbxx, jjmlfpt);
			// jjmlfptService.addJjmlfpt(jjmlfpt);
		} else if ("无人值守电子哨兵".equals(xmzl)) {
			Wrzsdzsb wrzsdzsb = jbxx.getWrzsdzsb();
			wrzsdzsb.setId(jbxx.getId());
			AddWrzsdzsb addWrzsdzsb = new AddWrzsdzsb(wrzsdzsb.getFzdd(),
					wrzsdzsb.getJsgm(), wrzsdzsb.getId());
			wrzsdzsbService.addWrzsdzsb(addWrzsdzsb);
			saveJbxx(jbxx, user);
			// addJbxx(jbxx, wrzsdzsb);
			// wrzsdzsbService.addWrzsdzsb(wrzsdzsb);

		} else if ("执勤房".equals(xmzl)) {
			Zqf zqf = jbxx.getZqf();
			zqf.setId(jbxx.getId());
			AddZqf addZqf = new AddZqf(zqf.getJsdd(), zqf.getJsgm(),
					zqf.getZqflx(), zqf.getId());
			zqfService.addZqf(addZqf);
			saveJbxx(jbxx, user);
			// addJbxx(jbxx, zqf);
			// zqfService.addZqf(zqf);
		} else if ("了望塔".equals(xmzl)) {
			Lwt lwt = jbxx.getLwt();
			lwt.setId(jbxx.getId());
			AddLwt addLwt = new AddLwt(lwt.getJsdd(), lwt.getJsgm(),
					lwt.getLwtlx(), lwt.getId());
			lwtService.addLwt(addLwt);
			saveJbxx(jbxx, user);
			// addJbxx(jbxx, lwt);
			// lwtService.addLwt(lwt);
		} else if ("标志牌".equals(xmzl)) {
			Bzp bzp = jbxx.getBzp();
			bzp.setId(jbxx.getId());
			AddBzp addBzp=new AddBzp(bzp.getJsdd(),bzp.getJsgm(),bzp.getBzplx(),bzp.getId());
			bzpService.addBzp(addBzp);
			saveJbxx(jbxx, user);
			// addJbxx(jbxx, bzp);
			// bzpService.addBzp(bzp);
		}
		return resultMap;
	}

	/**
	 * 加载数据详细信息
	 * 
	 * @param request
	 * @return 修改历史： [2017年9月28日] 创建文件 by 徐成
	 */

	@RequestMapping("/loadDetail")
	public @ResponseBody
	Object loadDetail(HttpServletRequest request) {
		String id = request.getParameter("ids");
		String xmzl = request.getParameter("xmzl");
		Object jsonObj = null;
		if ("执勤道路".equals(xmzl)) {
			jsonObj = zqdlService.getDataById(id);
		} else if ("桥梁".equals(xmzl)) {
			jsonObj = qiaoLiangService.getDataById(id);
		} else if ("执勤码头".equals(xmzl)) {
			jsonObj = zqmtService.getDataById(id);
		} else if ("直升机停机坪".equals(xmzl)) {
			jsonObj = zsjtjpService.getDataById(id);
		} else if ("铁丝网".equals(xmzl)) {
			jsonObj = tswService.getDataById(id);
		} else if ("铁栅栏".equals(xmzl)) {
			jsonObj = tzlService.getDataById(id);
		} else if ("拦阻桩".equals(xmzl)) {
			jsonObj = lzzService.getDataById(id);
		} else if ("隔离带".equals(xmzl)) {
			jsonObj = gldService.getDataById(id);
		} else if ("报警线路".equals(xmzl)) {
			jsonObj = bjxlService.getDataById(id);
		} else if ("拒马".equals(xmzl)) {
			jsonObj = jumaService.getDataById(id);
		} else if ("监控中心".equals(xmzl)) {
			jsonObj = jkzxService.getDataById(id);
		} else if ("监控站".equals(xmzl)) {
			jsonObj = jkzService.getDataById(id);
		} else if ("视频前端".equals(xmzl)) {
			jsonObj = spqdService.getDataById(id);
		} else if ("显控终端".equals(xmzl)) {
			jsonObj = xkzdService.getDataById(id);
		} else if ("传输线路".equals(xmzl)) {
			jsonObj = csxlService.getDataById(id);
		} else if ("报警装置".equals(xmzl)) {
			jsonObj = bjzzService.getDataById(id);
		} else if ("供电系统".equals(xmzl)) {
			jsonObj = gdxtService.getDataById(id);
		} else if ("军警民联防平台".equals(xmzl)) {
			jsonObj = jjmlfptService.getDataById(id);
		} else if ("无人值守电子哨兵".equals(xmzl)) {
			jsonObj = wrzsdzsbService.getDataById(id);
		} else if ("执勤房".equals(xmzl)) {
			jsonObj = zqfService.getDataById(id);
		} else if ("了望塔".equals(xmzl)) {
			jsonObj = lwtService.getDataById(id);
		} else if ("标志牌".equals(xmzl)) {
			jsonObj = bzpService.getDataById(id);
		}
		return jsonObj;
	}

	/**
	 * 更新数据
	 * 
	 * @param request
	 * @return 修改历史： [2017年9月28日] 创建文件 by 徐成
	 */
	@RequestMapping("/update")
	public @ResponseBody
	Map<String, Object> update(HttpServletRequest request, Jbxx jbxx) {
	    User user = (User)request.getSession().getAttribute("loginUser");
		// TODO 操作日志未处理
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("methodTag", "update");// 方法标识，重定向时使用
		String xmzl = jbxx.getXmzl();
		resultMap.put("xmlb", jbxx.getXmlb());
		resultMap.put("xmzl", xmzl);
		if("1".equals(user.getRoleName())){
		    resultMap.put("szsf",
		            dict_AREAService.getCodeNameByCodeValue(jbxx.getSzsf()));
		}else if("2".equals(user.getRoleName())){
            resultMap.put("szsf", user.getProvince());
        }
		resultMap.put("szcs",
				dict_AREAService.getCodeNameByCodeValue(jbxx.getSzcs()));
		String id = request.getParameter("ids");
		String partTag = request.getParameter("partTag");
		String xmmc = jbxx.getXmmc();
		boolean f = false;//项目名称是否改变
		if ("执勤道路".equals(xmzl)) {
			@SuppressWarnings("unchecked")
            Zqdl old=(Zqdl) BeanToMap.transMapToBean((Map<String, Object>) zqdlService.getDataById(id),Zqdl.class);
			if(!old.getXmmc().equals(xmmc)){
			    f = true;
			}
			Zqdl zqdl = jbxx.getZqdl();
			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());
			old.setJsdd(zqdl.getJsdd());// 建设地点
			old.setJsgm(zqdl.getJsgm());// 建设规模
			old.setDllb(zqdl.getDllb());// 道路类别
			old.setDllx(zqdl.getDllx());// 道路类型
			old.setDldj(zqdl.getDldj());// 道路等级
			old.setLjlx(zqdl.getLjlx());// 路基类型
			old.setLmlx(zqdl.getLmlx());// 路面类型
			addJbxx(jbxx, old,request);
			zqdlService.updateData(old);

		} else if ("桥梁".equals(xmzl)) {
			@SuppressWarnings("unchecked")
            QiaoLiang old=(QiaoLiang) BeanToMap.transMapToBean((Map<String, Object>) qiaoLiangService.getDataById(id),QiaoLiang.class);
			if(!old.getXmmc().equals(xmmc)){
			    f = true;
            }
			QiaoLiang qiaoLiang = jbxx.getQiaoLiang();
			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());
			old.setJsdd(qiaoLiang.getJsdd());// 建设地段
			old.setJsgm(qiaoLiang.getJsgm());// 建设规模
			old.setQllx(qiaoLiang.getQllx());// 桥梁类型
			old.setZz(qiaoLiang.getZz());// 桥梁载重
			addJbxx(jbxx, old,request);
			qiaoLiangService.updateData(old);
		} else if ("执勤码头".equals(xmzl)) {
			@SuppressWarnings("unchecked")
            Zqmt old=(Zqmt) BeanToMap.transMapToBean((Map<String, Object>) zqmtService.getDataById(id),Zqmt.class);
			if(!old.getXmmc().equals(xmmc)){
			    f = true;
            }
			Zqmt zqmt = jbxx.getZqmt();
			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setJsdd(zqmt.getJsdd());// 建设地段
			old.setJsgm(zqmt.getJsgm());// 建设规模

			addJbxx(jbxx, old,request);
			zqmtService.updateData(old);
		} else if ("直升机停机坪".equals(xmzl)) {
			@SuppressWarnings("unchecked")
            Zsjtjp old=(Zsjtjp) BeanToMap.transMapToBean((Map<String, Object>) zsjtjpService.getDataById(id),Zsjtjp.class);
			if(!old.getXmmc().equals(xmmc)){
			    f = true;
            }
			Zsjtjp zsjtjp = jbxx.getZsjtjp();
			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());
			old.setJsdd(zsjtjp.getJsdd());// 建设地段
			old.setJsgm(zsjtjp.getJsgm());// 建设规模
			old.setTjplx(zsjtjp.getTjplx());// 停机坪类型

			addJbxx(jbxx, old,request);
			zsjtjpService.updateData(old);
		} else if ("铁丝网".equals(xmzl)) {
			@SuppressWarnings("unchecked")
            Tsw old=(Tsw) BeanToMap.transMapToBean((Map<String, Object>) tswService.getDataById(id),Tsw.class);
			if(!old.getXmmc().equals(xmmc)){
			    f = true;
            }
			Tsw tsw = jbxx.getTsw();
			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());
			old.setJsdd(tsw.getJsdd());// 建设地段
			old.setJsgm(tsw.getJsgm());// 建设规模
			old.setLzmgs(tsw.getLzmgs());// 拦阻门个数
			old.setTswlx(tsw.getTswlx());// 铁丝网类型

			addJbxx(jbxx, old,request);
			tswService.updateData(old);
		} else if ("铁栅栏".equals(xmzl)) {
			@SuppressWarnings("unchecked")
            Tzl old=(Tzl) BeanToMap.transMapToBean((Map<String, Object>) tzlService.getDataById(id),Tzl.class);
			if(!old.getXmmc().equals(xmmc)){
			    f = true;
            }
			Tzl tzl = jbxx.getTzl();
			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());
			old.setJsdd(tzl.getJsdd());// 建设地段
			old.setJsgm(tzl.getJsgm());// 建设规模
			addJbxx(jbxx, old,request);
			tzlService.updateData(old);
		} else if ("拦阻桩".equals(xmzl)) {
			@SuppressWarnings("unchecked")
            Lzz old=(Lzz) BeanToMap.transMapToBean((Map<String, Object>) lzzService.getDataById(id),Lzz.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Lzz lzz = jbxx.getLzz();
			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setJsdd(lzz.getJsdd());// 建设地段
			old.setJsgm(lzz.getJsgm());// 建设规模
			old.setLzzlx(lzz.getLzzlx());// 拦阻桩类型

			addJbxx(jbxx, old,request);
			lzzService.updateData(old);
		} else if ("隔离带".equals(xmzl)) {
			@SuppressWarnings("unchecked")
            Gld old=(Gld) BeanToMap.transMapToBean((Map<String, Object>) gldService.getDataById(id),Gld.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Gld gld = jbxx.getGld();
			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setJsdd(gld.getJsdd());// 建设地段
			old.setJsgm(gld.getJsgm());// 建设规模

			addJbxx(jbxx, old,request);
			gldService.updateData(old);
		} else if ("报警线路".equals(xmzl)) {
			@SuppressWarnings("unchecked")
            Bjxl old=(Bjxl) BeanToMap.transMapToBean((Map<String, Object>) bjxlService.getDataById(id),Bjxl.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Bjxl bjxl = jbxx.getBjxl();
			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setJsdd(bjxl.getJsdd());// 建设地段
			old.setJsgm(bjxl.getJsgm());// 建设规模
			old.setSbpp(bjxl.getSbpp());// 设备品牌

			addJbxx(jbxx, old,request);
			bjxlService.updateData(old);
		} else if ("拒马".equals(xmzl)) {
			@SuppressWarnings("unchecked")
            Juma old=(Juma) BeanToMap.transMapToBean((Map<String, Object>) jumaService.getDataById(id),Juma.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Juma juma = jbxx.getJuma();
			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setJsdd(juma.getJsdd());// 建设地段
			old.setJsgm(juma.getJsgm());// 建设规模

			addJbxx(jbxx, old,request);
			jumaService.updateData(old);
		} else if ("监控中心".equals(xmzl)) {
			@SuppressWarnings("unchecked")
            Jkzx old=(Jkzx) BeanToMap.transMapToBean((Map<String, Object>) jkzxService.getDataById(id),Jkzx.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Jkzx jkzx = jbxx.getJkzx();

			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setXscsxl(jkzx.getXscsxl());// 向上传输线路
			old.setXsltqk(jkzx.getXsltqk());// 向上联通情况
			old.setXsltwlxz(jkzx.getXsltwlxz());// 向上联通网络性质
			old.setJb(jkzx.getJb());// 级别

			addJbxx(jbxx, old,request);
			jkzxService.updateJkzx(old);

		} else if ("监控站".equals(xmzl)) {

			@SuppressWarnings("unchecked")
            Jkz old=(Jkz) BeanToMap.transMapToBean((Map<String, Object>) jkzService.getDataById(id),Jkz.class);
			Jkz jkz = jbxx.getJkz();
			boolean xmmcChangeTag = false;//项目名称变更标识
			if(!old.getXmmc().equals(jbxx.getXmmc())){
			    xmmcChangeTag = true;
			    f = true;
			}
			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setXsltqk(jkz.getXsltqk());// 向上联通情况
			old.setXscsxl(jkz.getXscsxl());// 向上传输线路
			old.setXsltwlxz(jkz.getXsltwlxz());// 向上联通网络性质
			old.setXkzdgs(jkz.getXkzdgs());// 显控终端个数
			old.setYdqdgs(jkz.getYdqdgs());// 移动终端个数
			old.setGdqdgs(jkz.getGdqdgs());// 固定前端个数

			addJbxx(jbxx, old,request);
			jkzService.updateJkz(old);
			//监控站名称改变，子类所在的监控站名称也需要改变
			if(xmmcChangeTag){//Csxl、Bjzz、Gdxt
			    jbxxService.updateBySql("update spqd set jkz_name = ? where jkz_id = ? ", new Object[]{jkz.getXmmc(), jkz.getId()});
			    jbxxService.updateBySql("update xkzd set jkz_name = ? where jkz_id = ? ", new Object[]{jkz.getXmmc(), jkz.getId()});
			    jbxxService.updateBySql("update csxl set jkz_name = ? where jkz_id = ? ", new Object[]{jkz.getXmmc(), jkz.getId()});
			    jbxxService.updateBySql("update bjzz set jkz_name = ? where jkz_id = ? ", new Object[]{jkz.getXmmc(), jkz.getId()});
			    jbxxService.updateBySql("update gdxt set jkz_name = ? where jkz_id = ? ", new Object[]{jkz.getXmmc(), jkz.getId()});
			}
		} else if ("视频前端".equals(xmzl)) {
			boolean sblx_change = false;// sblx是否改变
			boolean jkz_id_change = false;// jkz_id是否改变
			String old_jkz_id = "";
			String new_jkz_id = "";
			@SuppressWarnings("unchecked")
            Spqd old=(Spqd) BeanToMap.transMapToBean((Map<String, Object>) spqdService.getDataById(id),Spqd.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Spqd spqd = jbxx.getSpqd();
			if (old != null && spqd != null) {
				if (!old.getSblx().equals(spqd.getSblx())) {// 设备类型改变
					sblx_change = true;
				}
				old_jkz_id = old.getJkz_id() == null ? "" : old.getJkz_id();
				new_jkz_id = spqd.getJkz_id() == null ? "" : spqd.getJkz_id();
				if (!old_jkz_id.equals(new_jkz_id)) {// 监控站id改变
					jkz_id_change = true;
				}
			}
			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setCsfs(spqd.getCsfs());// 传输方式
			old.setJkz_id(spqd.getJkz_id());// 所在监控站
			@SuppressWarnings("unchecked")
            Jkz jkz=(Jkz) BeanToMap.transMapToBean(((Map<String,Object>)jkzService.getJkzNameByJkzId(spqd.getJkz_id())), Jkz.class);
			old.setJkz_name(jkz.getXmmc());
			old.setSblx(spqd.getSblx());// 设备类型
			old.setFzdd(spqd.getFzdd());// 放置地点
			
			addJbxx(jbxx, old,request);
			spqdService.updateSpqd(old);
			if (!jkz_id_change && sblx_change) {// jkz_id没变，sblx改变
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("jkz_id", spqd.getJkz_id());
				List<Object> list = spqdService.countGroupBySblx(condition);
				Long ydqdgs = 0l;
				Long gdqdgs = 0l;
				for (Object obj : list) {
					if (obj == null) {
						continue;
					}
					@SuppressWarnings("unchecked")
					Map<String, Object> map = (Map<String, Object>) obj;
					if (ConstantParam.GDS.equals(map.get("sblx"))) {
						gdqdgs = map.get("total") == null ? 0l : (Long) map
								.get("total");
					} else if (ConstantParam.YDS.equals(map.get("sblx"))) {
						ydqdgs = map.get("total") == null ? 0l : (Long) map
								.get("total");
					}
				}
				jkzService.updateZlgs(spqd.getJkz_id(), gdqdgs, ydqdgs);
			}
			if (jkz_id_change) {
				// 对修改前的jkz_id对应的jkz中移动前端个数，固定前端个数做修改
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("jkz_id", old_jkz_id);
				List<Object> list = spqdService.countGroupBySblx(condition);
				Long ydqdgs = 0l;
				Long gdqdgs = 0l;
				for (Object obj : list) {
					if (obj == null) {
						continue;
					}
					@SuppressWarnings("unchecked")
					Map<String, Object> map = (Map<String, Object>) obj;
					if (ConstantParam.GDS.equals(map.get("sblx"))) {
						gdqdgs = map.get("total") == null ? 0l : (Long) map
								.get("total");
					} else if (ConstantParam.YDS.equals(map.get("sblx"))) {
						ydqdgs = map.get("total") == null ? 0l : (Long) map
								.get("total");
					}
				}
				jkzService.updateZlgs(old_jkz_id, gdqdgs, ydqdgs);
				// 对修改后的jkz_id对应的jkz移动前端个数、固定前端个数做修改
				condition.put("jkz_id", new_jkz_id);
				List<Object> listNew = spqdService.countGroupBySblx(condition);
				Long ydqdgsNew = 0l;
				Long gdqdgsNew = 0l;
				for (Object obj : listNew) {
					if (obj == null) {
						continue;
					}
					@SuppressWarnings("unchecked")
					Map<String, Object> map = (Map<String, Object>) obj;
					if (ConstantParam.GDS.equals(map.get("sblx"))) {
						gdqdgsNew = map.get("total") == null ? 0l : (Long) map
								.get("total");
					} else if (ConstantParam.YDS.equals(map.get("sblx"))) {
						ydqdgsNew = map.get("total") == null ? 0l : (Long) map
								.get("total");
					}
				}
				jkzService.updateZlgs(new_jkz_id, gdqdgsNew, ydqdgsNew);
			}

		} else if ("显控终端".equals(xmzl)) {
			boolean jkz_id_change = false;// jkz_id是否改变
			String old_jkz_id = "";
			String new_jkz_id = "";
			@SuppressWarnings("unchecked")
            Xkzd old=(Xkzd) BeanToMap.transMapToBean((Map<String, Object>) xkzdService.getDataById(id),Xkzd.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Xkzd xkzd = jbxx.getXkzd();
			if (old != null && xkzd != null) {
				old_jkz_id = old.getJkz_id() == null ? "" : old.getJkz_id();
				new_jkz_id = xkzd.getJkz_id() == null ? "" : xkzd.getJkz_id();
				if (!old_jkz_id.equals(new_jkz_id)) {
					jkz_id_change = true;
				}
			}
			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setCsfs(xkzd.getCsfs());// 传输方式
			old.setJkz_id(xkzd.getJkz_id());// 所在监控站
			old.setFzdd(xkzd.getFzdd());// 放置地点
			@SuppressWarnings("unchecked")
            Jkz jkz=(Jkz) BeanToMap.transMapToBean(((Map<String,Object>)jkzService.getJkzNameByJkzId(xkzd.getJkz_id())), Jkz.class);
			old.setJkz_name(jkz.getXmmc());

			addJbxx(jbxx, old,request);
			xkzdService.updateXkzd(old);

			// 对修改前的jkz_id对应的jkz中显控终端的个数做修改
			if (jkz_id_change) {
				// 对修改前的jkz_id对应的jkz中移动前端个数，固定前端个数做修改
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("jkz_id", old_jkz_id);
				Long xkzdgs = xkzdService.countByCondition(condition);
				jkzService.updateZlgs("xkzdgs", old_jkz_id, xkzdgs);
				condition.put("jkz_id", new_jkz_id);
				Long xkzdgs_new = xkzdService.countByCondition(condition);
				jkzService.updateZlgs("xkzdgs", new_jkz_id, xkzdgs_new);
			}

		} else if ("传输线路".equals(xmzl)) {

			@SuppressWarnings("unchecked")
            Csxl old=(Csxl) BeanToMap.transMapToBean((Map<String, Object>) csxlService.getDataById(id),Csxl.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Csxl csxl = jbxx.getCsxl();

			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setJkz_id(csxl.getJkz_id());// 所在监控站
			old.setXlqd(csxl.getXlqd());// 线路起点
			old.setXlzd(csxl.getXlzd());// 线路终点
			old.setJsdd(csxl.getJsdd());// 建设地点
			old.setJsgm(csxl.getJsgm());// 建设规模
			old.setXllx(csxl.getXllx());// 线路类型

			old.setXlxz(csxl.getXlxz());// 线路性质
			@SuppressWarnings("unchecked")
            Jkz jkz=(Jkz) BeanToMap.transMapToBean(((Map<String,Object>)jkzService.getJkzNameByJkzId(csxl.getJkz_id())), Jkz.class);
			old.setJkz_name(jkz.getXmmc());

			addJbxx(jbxx, old,request);
			csxlService.updateCsxl(old);

		} else if ("报警装置".equals(xmzl)) {

			@SuppressWarnings("unchecked")
            Bjzz old=(Bjzz) BeanToMap.transMapToBean((Map<String, Object>) bjzzService.getDataById(id),Bjzz.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Bjzz bjzz = jbxx.getBjzz();

			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setJkz_id(bjzz.getJkz_id());// 所在监控站
			old.setJsgm(bjzz.getJsgm());// 建设规模
			old.setSbpp(bjzz.getSbpp());// 设备品牌
			old.setSbxh(bjzz.getSbxh());// 设备型号
			@SuppressWarnings("unchecked")
            Jkz jkz=(Jkz) BeanToMap.transMapToBean(((Map<String,Object>)jkzService.getJkzNameByJkzId(bjzz.getJkz_id())), Jkz.class);
			old.setJkz_name(jkz.getXmmc());

			addJbxx(jbxx, old,request);
			bjzzService.updateBjzz(old);

		} else if ("供电系统".equals(xmzl)) {

			@SuppressWarnings("unchecked")
            Gdxt old=(Gdxt) BeanToMap.transMapToBean((Map<String, Object>) gdxtService.getDataById(id),Gdxt.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Gdxt gdxt = jbxx.getGdxt();

			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setJkz_id(gdxt.getJkz_id());// 所在监控站
			old.setJsdd(gdxt.getJsdd());// 建设地点
			old.setJsgm(gdxt.getJsgm());// 建设规模
			@SuppressWarnings("unchecked")
            Jkz jkz=(Jkz) BeanToMap.transMapToBean(((Map<String,Object>)jkzService.getJkzNameByJkzId(gdxt.getJkz_id())), Jkz.class);
			old.setJkz_name(jkz.getXmmc());

			addJbxx(jbxx, old,request);
			gdxtService.updateGdxt(old);

		} else if ("军警民联防平台".equals(xmzl)) {

			@SuppressWarnings("unchecked")
            Jjmlfpt old=(Jjmlfpt) BeanToMap.transMapToBean((Map<String, Object>) jjmlfptService.getDataById(id),Jjmlfpt.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Jjmlfpt jjmlfpt = jbxx.getJjmlfpt();

			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setXsltqk(jjmlfpt.getXsltqk());// 向上联通网络情况
			old.setXscsxl(jjmlfpt.getXscsxl());// 向上联通传输线路
			old.setXsltwlxz(jjmlfpt.getXsltwlxz());// 向上联通网络性质

			old.setHxcsxl(jjmlfpt.getHxcsxl());// 横向传输线路情况
			old.setHxltqk(jjmlfpt.getHxltqk());// 横向联通情况
			old.setHxltwlxz(jjmlfpt.getHxltwlxz());// 横向联通网络性质
			old.setJb(jjmlfpt.getJb());// 级别
			old.setFzdd(jjmlfpt.getFzdd());// 放置地点

			addJbxx(jbxx, old,request);
			jjmlfptService.updateJjmlfpt(old);

		} else if ("无人值守电子哨兵".equals(xmzl)) {
			@SuppressWarnings("unchecked")
            Wrzsdzsb old=(Wrzsdzsb) BeanToMap.transMapToBean((Map<String, Object>) wrzsdzsbService.getDataById(id),Wrzsdzsb.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Wrzsdzsb wrzsdzsb = jbxx.getWrzsdzsb();

			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setFzdd(wrzsdzsb.getFzdd());// 放置地点
			old.setJsgm(wrzsdzsb.getJsgm());// 建设规模

			addJbxx(jbxx, old,request);
			wrzsdzsbService.updateWrzsdzsb(old);
		} else if ("执勤房".equals(xmzl)) {

			@SuppressWarnings("unchecked")
            Zqf old=(Zqf) BeanToMap.transMapToBean((Map<String, Object>) zqfService.getDataById(id),Zqf.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Zqf zqf = jbxx.getZqf();

			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());

			old.setJsdd(zqf.getJsdd());// 建设地点
			old.setZqflx(zqf.getZqflx());// 执勤房类型
			old.setJsgm(zqf.getJsgm());// 建设规模

			addJbxx(jbxx, old,request);
			zqfService.updateZqf(old);

		} else if ("了望塔".equals(xmzl)) {

			@SuppressWarnings("unchecked")
            Lwt old=(Lwt) BeanToMap.transMapToBean((Map<String, Object>) lwtService.getDataById(id),Lwt.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Lwt lwt = jbxx.getLwt();

			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setLwtlx(lwt.getLwtlx());// 了望塔类型
			old.setJsgm(lwt.getJsgm());// 建设规模
			old.setJsdd(lwt.getJsdd());// 建设地点

			addJbxx(jbxx, old,request);
			lwtService.updateLwt(old);
		} else if ("标志牌".equals(xmzl)) {
//			Bzp old = (Bzp) bzpService.getDataById(id);
			@SuppressWarnings("unchecked")
            Bzp old=(Bzp) BeanToMap.transMapToBean((Map<String, Object>) bzpService.getDataById(id),Bzp.class);
			if(!old.getXmmc().equals(xmmc)){
                f = true;
            }
			Bzp bzp = jbxx.getBzp();

			jbxx.setXmbh(old.getXmbh());
			jbxx.setBxxm(old.getBxxm());
			jbxx.setTime(new Date());

			old.setJsdd(bzp.getJsdd());// 建设地点
			old.setJsgm(bzp.getJsgm());// 建设规模
			old.setBzplx(bzp.getBzplx());// 标志牌类型

			addJbxx(jbxx, old,request);
			bzpService.updateBzp(old);
		}
		if("4".equals(partTag) && f){
		    jbxxService.updateBySql("update xmwhjl set wxxmmc = ? where wxxmid = ? ", new Object[]{xmmc, id});
		}
		return resultMap;
	}

	/**
	 * 删除数据
	 * 
	 * @param request
	 * @return 修改历史： [2017年9月28日] 创建文件 by 徐成
	 */
	@RequestMapping("/delete")
	public @ResponseBody
	Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String xmzl = request.getParameter("xmzl");// 项目子类
		resultMap.put("xmlb", request.getParameter("xmlb"));// 项目类别
		resultMap.put("xmzl", xmzl);
		resultMap.put("szsf", request.getParameter("szsf"));// 所在区域
		//partTag为4时，删除“子类项目”的前，需要先删除“项目维护记录”
		String partTag = request.getParameter("partTag");
		String idsString = request.getParameter("ids");
		
		if (idsString == null) {
			return resultMap;
		}
		if (idsString.endsWith(",")) {
			idsString = idsString.substring(0, idsString.length() - 1);
		}
		// 实体类名称
		String modelName = "";
		if (xmzl.equals("执勤道路")) {
			modelName = "AddZqdl";
		} else if (xmzl.equals("桥梁")) {
			/**
			 * 改动的
			 */
			modelName = "AddQiaoLiang";
		} else if (xmzl.equals("执勤码头")) {
			modelName = "AddZqmt";
		} else if (xmzl.equals("直升机停机坪")) {
			modelName = "AddZsjtjp";
		} else if (xmzl.equals("铁丝网")) {
			modelName = "AddTsw";
		} else if (xmzl.equals("铁栅栏")) {
			modelName = "AddTzl";
		} else if (xmzl.equals("拦阻桩")) {
			modelName = "AddLzz";
		} else if (xmzl.equals("隔离带")) {
			modelName = "AddGld";
		} else if (xmzl.equals("报警线路")) {
			modelName = "AddBjxl";
		} else if (xmzl.equals("拒马")) {
			modelName = "AddJuma";
		} else if (xmzl.equals("监控中心")) {
			modelName = "AddJkzx";
		} else if (xmzl.equals("监控站")) {
			modelName = "AddJkz";
		} else if (xmzl.equals("视频前端")) {
			modelName = "AddSpqd";
		} else if (xmzl.equals("显控终端")) {
			modelName = "AddXkzd";
		} else if (xmzl.equals("传输线路")) {
			modelName = "AddCsxl";
		} else if (xmzl.equals("报警装置")) {
			modelName = "AddBjzz";
		} else if (xmzl.equals("供电系统")) {
			modelName = "AddGdxt";
		} else if (xmzl.equals("军警民联防平台")) {
			modelName = "AddJjmlfpt";
		} else if (xmzl.equals("无人值守电子哨兵")) {
			modelName = "AddWrzsdzsb";
		} else if (xmzl.equals("执勤房")) {
			modelName = "AddZqf";
		} else if (xmzl.equals("了望塔")) {
			modelName = "AddLwt";
		} else if (xmzl.equals("标志牌")) {
			modelName = "AddBzp";
		}

		// 删除数据前先查询jkz_id、sblx等数据
		List<Object> list = new ArrayList<Object>();
		if ("视频前端".equals(xmzl)) {
			list = spqdService.getDataByPrimaryKeys(idsString);
		} else if ("显控终端".equals(xmzl)) {
			list = xkzdService.getJkz_idsByPrimaryKeys(idsString);
		}
		if("4".equals(partTag)){
		    jbxxService.deleteByIds(modelName, idsString, partTag);
		}else{
		    jbxxService.deleteByIds(modelName, idsString);
		}
		// 修改jkz_id对应的jkz中xkzdgs、gdqdgs、ydqdgs

		if ("视频前端".equals(xmzl)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			for (Object obj : list) {
				if (obj == null) {
					continue;
				}
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) obj;
				String jkz_id = map.get("jkz_id") == null ? "" : (String) map
						.get("jkz_id");
				if ("".equals(jkz_id)) {
					continue;
				}
				condition.put("jkz_id", jkz_id);
				Long ydqdgs = 0l;
				Long gdqdgs = 0l;
				List<Object> countList = spqdService
						.countGroupBySblx(condition);
				for (Object objCount : countList) {
					if (objCount == null) {
						continue;
					}
					@SuppressWarnings("unchecked")
					Map<String, Object> mapCount = (Map<String, Object>) objCount;
					if (ConstantParam.GDS.equals(mapCount.get("sblx"))) {
						gdqdgs = mapCount.get("total") == null ? 0l
								: (Long) mapCount.get("total");
					} else if (ConstantParam.YDS.equals(mapCount.get("sblx"))) {
						ydqdgs = mapCount.get("total") == null ? 0l
								: (Long) mapCount.get("total");
					}
				}
				jkzService.updateZlgs(jkz_id, gdqdgs, ydqdgs);
			}
		} else if ("显控终端".equals(xmzl)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			for (Object obj : list) {
				if (obj == null) {
					continue;
				}
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) obj;
				String jkz_id = map.get("jkz_id") == null ? "" : (String) map
						.get("jkz_id");
				if ("".equals(jkz_id)) {
					continue;
				}
				condition.put("jkz_id", jkz_id);
				Long xkzdgs = xkzdService.countByCondition(condition);
				jkzService.updateZlgs("xkzdgs", jkz_id, xkzdgs);
			}
		}
		return resultMap;
	}

	/**
	 * 导出所有数据
	 * 
	 * @return 修改历史： [2017年9月28日] 创建文件 by 徐成
	 */
	@RequestMapping("/exportAll")
	public void exportAll(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = null;
		// String ftlFileName = null;
		String partTag = request.getParameter("partTag");
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tznd", request.getParameter("tznd"));
		// 年度计划导出
		if ("1".equals(request.getParameter("partTag"))) {
			fileName = "统筹规划建设项目信息表.xls";
			// ftlFileName = "备选库数据模板.ftl";
		}
		if ("2".equals(partTag)) {
			condition.put("tznd", String.valueOf(DateUtils.getYear()));
			fileName = DateUtils.getYear() + "年度计划建设项目信息表.xls";
			// ftlFileName = "备选库数据模板.ftl";
		}
		if ("3".equals(partTag)) {
			fileName = "实施监督项目信息表.xls";
		}
		if("4".equals(partTag)){
		    fileName = "使用维护项目信息表.xls";
		}
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if ("1".equals(loginUser.getRoleName())) {
			condition.put("szsf", null);
			fileName = "全国" + fileName;
		} else {
			condition.put("szsf", loginUser.getProvince());
			fileName = loginUser.getProvince() + fileName;
		}
		if ("1".equals(partTag)
				|| "2".equals(partTag)) {
			condition.put("bxxm", "是");
		} else if ("3".equals(partTag)
				|| "4".equals(partTag)) {
			condition.put("bxxm", "否");
		}
		condition.put("partTag", partTag);

		// sheet页名称集合
		String[] sheetNameArr = { "执勤道路", "桥梁", "执勤码头", "直升机停机坪", "铁丝网", "铁栅栏",
				"拦阻桩", "隔离带", "拒马", "报警线路", "监控中心", "监控站", "视频前端", "显控终端",
				"传输线路", "供电系统", "报警装置", "军警民联防平台", "无人值守电子哨兵", "执勤房", "了望塔",
				"标志牌" };

		// 各sheet页表头集合
		List<String[]> headers = new ArrayList<String[]>();
		// 数据集
		List<List<Object>> data = new ArrayList<List<Object>>();
		// 导出列集合
		List<String[]> columnsArr = new ArrayList<String[]>();
		// 判断
		if ("1".equals(partTag)
				|| "2".equals(partTag)){
			// 执勤道路
			headers.add(ZqdlService.header);
			columnsArr.add(ZqdlService.columns);
			data.add(zqdlService.exportDataByCondition(condition));
			// 桥梁
			headers.add(QiaoLiangService.header);
			columnsArr.add(QiaoLiangService.columns);
			data.add(qiaoLiangService.exportDataByCondition(condition));
			// 执勤码头
			headers.add(ZqmtService.header);
			columnsArr.add(ZqmtService.columns);
			data.add(zqmtService.exportDataByCondition(condition));
			// 直升机停机坪
			headers.add(ZsjtjpService.header);
			columnsArr.add(ZsjtjpService.columns);
			data.add(zsjtjpService.exportDataByCondition(condition));
			// 铁丝网
			headers.add(TswService.header);
			columnsArr.add(TswService.columns);
			data.add(tswService.exportDataByCondition(condition));
			// 铁栅栏
			headers.add(TzlService.header);
			columnsArr.add(TzlService.columns);
			data.add(tzlService.exportDataByCondition(condition));
			// 拦阻桩
			headers.add(LzzService.header);
			columnsArr.add(LzzService.columns);
			data.add(lzzService.exportDataByCondition(condition));
			// 隔离带
			headers.add(GldService.header);
			columnsArr.add(GldService.columns);
			data.add(gldService.exportDataByCondition(condition));
			// 拒马
			headers.add(JumaService.header);
			columnsArr.add(JumaService.columns);
			data.add(jumaService.exportDataByCondition(condition));
			// 报警线路
			headers.add(BjxlService.header);
			columnsArr.add(BjxlService.columns);
			data.add(bjxlService.exportByCondition(condition));
			// 监控中心
			headers.add(JkzxService.header);
			columnsArr.add(JkzxService.columns);
			data.add(jkzxService.exportDataByCondition(condition));
			// 监控站
			headers.add(JkzService.header);
			columnsArr.add(JkzService.columns);
			data.add(jkzService.exportDataByCondition(condition));
			// 视频前端
			headers.add(SpqdService.header);
			columnsArr.add(SpqdService.columns);
			data.add(spqdService.exportDataByCondition(condition));
			// 显控终端
			headers.add(XkzdService.header);
			columnsArr.add(XkzdService.columns);
			data.add(xkzdService.exportDataByCondition(condition));
			// 传输线路
			headers.add(CsxlService.header);
			columnsArr.add(CsxlService.columns);
			data.add(csxlService.exportDataByCondition(condition));
			// 供电系统
			headers.add(GdxtService.header);
			columnsArr.add(GdxtService.columns);
			data.add(gdxtService.exportDataByCondition(condition));
			// 报警装置
			headers.add(BjzzService.header);
			columnsArr.add(BjzzService.columns);
			data.add(bjzzService.exportDataByCondition(condition));
			// 军警民联防平台
			headers.add(JjmlfptService.header);
			columnsArr.add(JjmlfptService.columns);
			data.add(jjmlfptService.exportDataByCondition(condition));
			// 无人值守电子哨兵
			headers.add(WrzsdzsbService.header);
			columnsArr.add(WrzsdzsbService.columns);
			data.add(wrzsdzsbService.exportDataByCondition(condition));
			// 执勤房
			headers.add(ZqfService.header);
			columnsArr.add(ZqfService.columns);
			data.add(zqfService.exportDataByCondition(condition));
			// 了望塔
			headers.add(LwtService.header);
			columnsArr.add(LwtService.columns);
			data.add(lwtService.exportDataByCondition(condition));
			// 标志牌
			headers.add(BzpService.header);
			columnsArr.add(BzpService.columns);
			data.add(bzpService.exportDataByCondition(condition));

			String root = request.getSession().getServletContext()
					.getRealPath("/dataExcel");
			String filePath = root + File.separator + fileName;

			File file = ExportUtil.createExcelForPoi(filePath, headers,
					sheetNameArr, data, columnsArr,partTag);
			downFile(response, fileName, file);
		} else if ("3".equals(partTag)||"4".equals(partTag)) {
			// 执勤道路
			headers.add(ZqdlService.headerJianDu);
			columnsArr.add(ZqdlService.columnsJiandu);
			data.add(zqdlService.exportDataByCondition(condition));
			// 桥梁
			headers.add(QiaoLiangService.headerJianDu);
			columnsArr.add(QiaoLiangService.columnsJiandu);
			data.add(qiaoLiangService.exportDataByCondition(condition));
			// 执勤码头
			headers.add(ZqmtService.headerJianDu);
			columnsArr.add(ZqmtService.columnsJiandu);
			data.add(zqmtService.exportDataByCondition(condition));
			// 直升机停机坪
			headers.add(ZsjtjpService.headerJianDu);
			columnsArr.add(ZsjtjpService.columnsJiandu);
			data.add(zsjtjpService.exportDataByCondition(condition));
			// 铁丝网
			headers.add(TswService.headerJianDu);
			columnsArr.add(TswService.columnsJiandu);
			data.add(tswService.exportDataByCondition(condition));
			// 铁栅栏
			headers.add(TzlService.headerJianDu);
			columnsArr.add(TzlService.columnsJiandu);
			data.add(tzlService.exportDataByCondition(condition));
			// 拦阻桩
			headers.add(LzzService.headerJianDu);
			columnsArr.add(LzzService.columnsJiandu);
			data.add(lzzService.exportDataByCondition(condition));
			// 隔离带
			headers.add(GldService.headerJianDu);
			columnsArr.add(GldService.columnsJiandu);
			data.add(gldService.exportDataByCondition(condition));
			// 拒马
			headers.add(JumaService.headerJianDu);
			columnsArr.add(JumaService.columnsJiandu);
			data.add(jumaService.exportDataByCondition(condition));
			// 报警线路
			headers.add(BjxlService.headerJianDu);
			columnsArr.add(BjxlService.columnsJiandu);
			data.add(bjxlService.exportByCondition(condition));
			// 监控中心
			headers.add(JkzxService.headerJianDu);
			columnsArr.add(JkzxService.columnsJiandu);
			data.add(jkzxService.exportDataByCondition(condition));
			// 监控站
			headers.add(JkzService.headerJianDu);
			columnsArr.add(JkzService.columnsJiandu);
			data.add(jkzService.exportDataByCondition(condition));
			// 视频前端
			headers.add(SpqdService.headerJianDu);
			columnsArr.add(SpqdService.columnsJiandu);
			data.add(spqdService.exportDataByCondition(condition));
			// 显控终端
			headers.add(XkzdService.headerJianDu);
			columnsArr.add(XkzdService.columnsJiandu);
			data.add(xkzdService.exportDataByCondition(condition));
			// 传输线路
			headers.add(CsxlService.headerJianDu);
			columnsArr.add(CsxlService.columnsJiandu);
			data.add(csxlService.exportDataByCondition(condition));
			// 供电系统
			headers.add(GdxtService.headerJianDu);
			columnsArr.add(GdxtService.columnsJiandu);
			data.add(gdxtService.exportDataByCondition(condition));
			// 报警装置
			headers.add(BjzzService.headerJianDu);
			columnsArr.add(BjzzService.columnsJiandu);
			data.add(bjzzService.exportDataByCondition(condition));
			// 军警民联防平台
			headers.add(JjmlfptService.headerJianDu);
			columnsArr.add(JjmlfptService.columnsJiandu);
			data.add(jjmlfptService.exportDataByCondition(condition));
			// 无人值守电子哨兵
			headers.add(WrzsdzsbService.headerJianDu);
			columnsArr.add(WrzsdzsbService.columnsJiandu);
			data.add(wrzsdzsbService.exportDataByCondition(condition));
			// 执勤房
			headers.add(ZqfService.headerJianDu);
			columnsArr.add(ZqfService.columnsJiandu);
			data.add(zqfService.exportDataByCondition(condition));
			// 了望塔
			headers.add(LwtService.headerJianDu);
			columnsArr.add(LwtService.columnsJiandu);
			data.add(lwtService.exportDataByCondition(condition));
			// 标志牌
			headers.add(BzpService.headerJianDu);
			columnsArr.add(BzpService.columnsJiandu);
			data.add(bzpService.exportDataByCondition(condition));

			String root = request.getSession().getServletContext()
					.getRealPath("/dataExcel");
			String filePath = root + File.separator + fileName;

			File file = ExportUtil.createExcelForPoi(filePath, headers,
					sheetNameArr, data, columnsArr,partTag);
			downFile(response, fileName, file);
		}
	}

	/**
	 * 生成报文
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/exportWord")
	public void exportWord(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("loginUser");
		String szsf = user.getProvince();
		String tznd = String.valueOf(DateUtils.getYear());

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("szsf", szsf);
		condition.put("tznd", tznd);

		// word文档数据填充集合
		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("szsf", szsf);
		dataMap.put("tznd", tznd);
		Object obj = jbxxService.getZytzAndDftzBySzsf(condition);
		@SuppressWarnings("unchecked")
        Map<String,Object> jstz = (Map<String, Object>) obj;
		if(jstz.get("ztz")==null || jstz.get("ztz").equals("null")||jstz.get("ztz").equals("")){
			jstz.put("ztz", 0);
		}
		if(jstz.get("zytz")==null || jstz.get("zytz").equals("null")||jstz.get("zytz").equals("")){
			jstz.put("zytz", 0);
		}
		if(jstz.get("dftz")==null || jstz.get("dftz").equals("null")||jstz.get("dftz").equals("")){
			jstz.put("dftz", 0);
		}
		dataMap.put("jstz",jstz);//年度建设投资
		// 计划建设项目都是备选项目
		condition.put("bxxm", "是");
		// 执勤道路
		List<Object> zqdlList = zqdlService.getData(condition);
		ProvinceTabelData zqdl = new ProvinceTabelData(null, zqdlList);
		dataMap.put("zqdl", zqdl);
		// 桥梁
		List<Object> qiaoliangList = qiaoLiangService.getData(condition);
		ProvinceTabelData qiaoliang = new ProvinceTabelData(null, qiaoliangList);
		dataMap.put("qiaoliang", qiaoliang);
		// 执勤码头
		List<Object> zqmtList = zqmtService.getData(condition);
		ProvinceTabelData zqmt = new ProvinceTabelData(null, zqmtList);
		dataMap.put("zqmt", zqmt);
		// 直升机停机坪
		List<Object> zsjtjpList = zsjtjpService.getData(condition);
		ProvinceTabelData zsjtjp = new ProvinceTabelData(null, zsjtjpList);
		dataMap.put("zsjtjp", zsjtjp);
		// 铁丝网
		List<Object> tswList = tswService.getData(condition);
		ProvinceTabelData tsw = new ProvinceTabelData(null, tswList);
		dataMap.put("tsw", tsw);
		// 铁栅栏
		List<Object> tzlList = tzlService.getData(condition);
		ProvinceTabelData tzl = new ProvinceTabelData(null, tzlList);
		dataMap.put("tzl", tzl);
		// 拦阻桩
		List<Object> lzzList = lzzService.getData(condition);
		ProvinceTabelData lzz = new ProvinceTabelData(null, lzzList);
		dataMap.put("lzz", lzz);
		// 隔离带
		List<Object> gldList = gldService.getData(condition);
		ProvinceTabelData gld = new ProvinceTabelData(null, gldList);
		dataMap.put("gld", gld);
		// 拒马
		List<Object> jumaList = jumaService.getData(condition);
		ProvinceTabelData juma = new ProvinceTabelData(null, jumaList);
		dataMap.put("juma", juma);
		// 报警线路
		List<Object> bjxlList = bjxlService.getData(condition);
		ProvinceTabelData bjxl = new ProvinceTabelData(null, bjxlList);
		dataMap.put("bjxl", bjxl);
		// 监控中心
		List<Object> jkzxList = jkzxService.getData(condition);
		ProvinceTabelData jkzx = new ProvinceTabelData(null, jkzxList);
		dataMap.put("jkzx", jkzx);
		// 监控站
		List<Object> jkzList = jkzService.getData(condition);
		ProvinceTabelData jkz = new ProvinceTabelData(null, jkzList);
		dataMap.put("jkz", jkz);
		// 视频前端
		List<Object> spqdList = spqdService.getData(condition);
		ProvinceTabelData spqd = new ProvinceTabelData(null, spqdList);
		dataMap.put("spqd", spqd);
		// 线控终端
		List<Object> xkzdList = xkzdService.getData(condition);
		ProvinceTabelData xkzd = new ProvinceTabelData(null, xkzdList);
		dataMap.put("xkzd", xkzd);
		// 传输线路
		List<Object> csxlList = csxlService.getData(condition);
		ProvinceTabelData csxl = new ProvinceTabelData(null, csxlList);
		dataMap.put("csxl", csxl);
		// 供电系统
		List<Object> gdxtList = gdxtService.getData(condition);
		ProvinceTabelData gdxt = new ProvinceTabelData(null, gdxtList);
		dataMap.put("gdxt", gdxt);
		// 报警装置
		List<Object> bjzzList = bjzzService.getData(condition);
		ProvinceTabelData bjzz = new ProvinceTabelData(null, bjzzList);
		dataMap.put("bjzz", bjzz);
		// 军警民联防平台
		List<Object> jjmlfptList = jjmlfptService.getData(condition);
		ProvinceTabelData jjmlfpt = new ProvinceTabelData(null, jjmlfptList);
		dataMap.put("jjmlfpt", jjmlfpt);
		// 无人值守电子哨兵
		List<Object> wrzsdzsbList = wrzsdzsbService.getData(condition);
		ProvinceTabelData wrzsdzsb = new ProvinceTabelData(null, wrzsdzsbList);
		dataMap.put("wrzsdzsb", wrzsdzsb);
		// 执勤房
		List<Object> zqfList = zqfService.getData(condition);
		ProvinceTabelData zqf = new ProvinceTabelData(null, zqfList);
		dataMap.put("zqf", zqf);
		// 了望塔
		List<Object> lwtList = lwtService.getData(condition);
		ProvinceTabelData lwt = new ProvinceTabelData(null, lwtList);
		dataMap.put("lwt", lwt);
		// 标志牌
		List<Object> bzpList = bzpService.getData(condition);
		ProvinceTabelData bzp = new ProvinceTabelData(null, bzpList);
		dataMap.put("bzp", bzp);

		String ftlFileName = "边海防基础设施建设计划.ftl";
		String root = request.getSession().getServletContext()
				.getRealPath("/wordFtl");

		String fileName = szsf + tznd + "年度边海防基础设施建设计划.doc";
		String filePath = root + File.separator + fileName;

		File file = ExportUtil.createFile(root, ftlFileName, dataMap, filePath);
		downFile(response, fileName, file);
	}

	/**
	 * 所有项目子类公共字段赋值
	 * 
	 * @param jbxx1
	 *            父类数据对象
	 * @param jbxx2
	 *            子类数据对象 修改历史： [2017年9月28日] 创建文件 by 徐成
	 */
	public void addJbxx(Jbxx jbxx1, Jbxx jbxx2,HttpServletRequest request) {
		jbxx2.setXmlb(jbxx1.getXmlb()); // 项目类别
		jbxx2.setXmzl(jbxx1.getXmzl()); // 项目子类
		jbxx2.setXmmc(jbxx1.getXmmc()); // 项目名称
		jbxx2.setXmbh(jbxx1.getXmbh()); // 项目编号
		User user = (User) request.getSession().getAttribute("loginUser");
		if(user.getRoleName().equals("2")){
			jbxx2.setSzsf(user.getProvince());
		}else{
			jbxx2.setSzsf(dict_AREAService.getCodeNameByCodeValue(jbxx1.getSzsf())); // 所在省份
		}
		jbxx2.setSzcs(dict_AREAService.getCodeNameByCodeValue(jbxx1.getSzcs())); // 所在城市
		jbxx2.setSzq(dict_AREAService.getCodeNameByCodeValue(jbxx1.getSzq())); // 所在城市
		jbxx2.setBjfx(jbxx1.getBjfx()); // 边界方向
		jbxx2.setDxlb(jbxx1.getDxlb()); // 地形类比
		jbxx2.setSslx(jbxx1.getSslx()); // 设施类型
		jbxx2.setJsxz(jbxx1.getJsxz()); // 建设性质
		jbxx2.setSydw(jbxx1.getSydw()); // 使用单位
		jbxx2.setSbdw(jbxx1.getSbdw()); // 申报单位
		jbxx2.setZytz(jbxx1.getZytz()); // 中央投资
		jbxx2.setDftz(jbxx1.getDftz()); // 地方投资
		// jbxx2.setJwd(jbxx1.getJwd()); // 经纬度
		jbxx2.setJd(jbxx1.getJd());// 经度
		jbxx2.setWd(jbxx1.getWd());// 纬度
		jbxx2.setFj(jbxx1.getFj()); // 附件
		jbxx2.setBz(jbxx1.getBz()); // 备注
		jbxx2.setTime(jbxx1.getTime()); // 数据新增或修改时间
		jbxx2.setBxxm(jbxx1.getBxxm()); // 备选项目
		jbxx2.setTznd(jbxx1.getTznd()); // 投资年度
		jbxx2.setJszt(jbxx1.getJszt());// 建设状态
		jbxx2.setCjdw(jbxx1.getCjdw());// 承建单位
		jbxx2.setJldw(jbxx1.getJldw());// 监理单位
		jbxx2.setZtbsj(jbxx1.getZtbsj());// 招投标时间
		jbxx2.setKgsj(jbxx1.getKgsj());// 开工时间
		jbxx2.setCysj(jbxx1.getCysj());// 初验时间
		jbxx2.setJgsj(jbxx1.getJgsj());// 竣工时间
		jbxx2.setSjsj(jbxx1.getSjsj());// 审计时间
		jbxx2.setSyzt(jbxx1.getSyzt());//使用状态

	}

	/**
	 * 查询监控站名称
	 * 
	 * @param request
	 * @return List<Object>
	 * @date [2017-10-11] 创建文件 by 郎川
	 */
	@RequestMapping("/queryJkzName")
	public @ResponseBody
	List<Object> getJkzName(HttpServletRequest request) {
		
		User user=(User)request.getSession().getAttribute("loginUser");
		String partTag = request.getParameter("partTag");
		String szsf=null;
		if("1".equals(user.getRoleName())){
			 szsf = request.getParameter("szsf");
			szsf = dict_AREAService.getCodeNameByCodeValue(szsf);
		}else if("2".equals(user.getRoleName())){
			 szsf = request.getParameter("szsf");
		}
		String szcs = request.getParameter("szcs");
		if(szcs.equals("0")){
			szcs=null;
		}else{
			szcs = dict_AREAService.getCodeNameByCodeValue(szcs);
		}
		String szq = request.getParameter("szq");
		if(szq.equals("0")){
			szq=null;
		}else{
			szq = dict_AREAService.getCodeNameByCodeValue(szq);
		}
		return jkzService.getJkzName(szsf, szcs, szq, partTag);
	}

	/**
	 * 数据列表多条件查询
	 * 
	 * @param condition
	 * @return
	 */
	@RequestMapping("/loadDataByCondition")
	public @ResponseBody
	PageBean loadDataByCondition(@RequestParam Map<String, Object> condition,
			HttpServletRequest req) {

		User user = (User) req.getSession().getAttribute("loginUser");
		// 上海、甘肃、天津没有边防市的需要特殊处理
		if (!"甘肃省".equals(user.getProvince())
				&& !"上海市".equals(user.getProvince())
				&& !"天津市".equals(user.getProvince())) {
			if ("2".equals(user.getRoleName())) {
				// condition中szsf表示地区，省级用户登陆时是szcs
				condition.put("szcs", condition.get("szsf"));
				condition.put("szsf", user.getProvince());
			} else if ("1".equals(user.getRoleName())) {
				condition.put("szsf", condition.get("szsf"));
			}
		} else {
			condition.put("szsf", user.getProvince());
		}
		int rows = 10;
		int currentPage = 1;
		if (CommonUtil.validCondition(condition, "offiset")) {
			currentPage = Integer.parseInt(condition.get("offiset").toString());
		}
		String xmzl = null;
		if (CommonUtil.validCondition(condition, "xmzl")) {
			xmzl = condition.get("xmzl").toString();
		}

		if ("2".equals(condition.get("partTag"))) {
			condition.put("tznd", String.valueOf(DateUtils.getYear()));
		}
		if ("1".equals(condition.get("partTag"))
				|| "2".equals(condition.get("partTag"))) {
			condition.put("bxxm", "是");
		} else if ("3".equals(condition.get("partTag"))
				|| "4".equals(condition.get("partTag"))) {
			condition.put("bxxm", "否");
			condition.put("jszt", condition.get("jszt"));
		}
		if ("4".equals(condition.get("partTag"))) {
			// TODO 建设状态是否为“已审计”
			condition.put("jszt", "已审计");
		}
		List<Object> dataList=null;
		if ("执勤道路".equals(xmzl)) {
			dataList=zqdlService.getDataByCondition(condition, currentPage, rows);
		} else if ("桥梁".equals(xmzl)) {
			dataList=qiaoLiangService.getDataByCondition(condition, currentPage,
					rows);
			
		} else if ("执勤码头".equals(xmzl)) {
			dataList=zqmtService.getDataByCondition(condition, currentPage, rows);
		} else if ("直升机停机坪".equals(xmzl)) {
			dataList=zsjtjpService.getDataByCondition(condition, currentPage,
					rows);
		} else if ("铁丝网".equals(xmzl)) {
			dataList=tswService.getDataByCondition(condition, currentPage, rows);
		} else if ("铁栅栏".equals(xmzl)) {
			dataList=tzlService.getDataByCondition(condition, currentPage, rows);
		} else if ("拦阻桩".equals(xmzl)) {
		    dataList=lzzService.getDataByCondition(condition, currentPage, rows);
		} else if ("隔离带".equals(xmzl)) {
			dataList=gldService.getDataByCondition(condition, currentPage, rows);
		} else if ("报警线路".equals(xmzl)) {
			dataList=bjxlService.getDataByCondition(condition, currentPage, rows);
		} else if ("拒马".equals(xmzl)) {
		    dataList=jumaService.getDataByCondition(condition, currentPage, rows);
		} else if ("监控中心".equals(xmzl)) {
			dataList=jkzxService.getDataByCondition(condition, currentPage, rows);
		} else if ("监控站".equals(xmzl)) {
			dataList=jkzService.getDataByCondition(condition, currentPage, rows);
		} else if ("视频前端".equals(xmzl)) {
			dataList=spqdService.getDataByCondition(condition, currentPage, rows);
		} else if ("显控终端".equals(xmzl)) {
			dataList=xkzdService.getDataByCondition(condition, currentPage, rows);
		} else if ("传输线路".equals(xmzl)) {
			dataList=csxlService.getDataByCondition(condition, currentPage, rows);
		} else if ("报警装置".equals(xmzl)) {
			dataList=bjzzService.getDataByCondition(condition, currentPage, rows);
		} else if ("供电系统".equals(xmzl)) {
			dataList=gdxtService.getDataByCondition(condition, currentPage, rows);
		} else if ("军警民联防平台".equals(xmzl)) {
			dataList=jjmlfptService.getDataByCondition(condition, currentPage,
					rows);
		} else if ("无人值守电子哨兵".equals(xmzl)) {
			dataList=wrzsdzsbService.getDataByCondition(condition, currentPage,
					rows);
		} else if ("执勤房".equals(xmzl)) {
			dataList=zqfService.getDataByCondition(condition, currentPage, rows);
		} else if ("了望塔".equals(xmzl)) {
			dataList=lwtService.getDataByCondition(condition, currentPage, rows);
		} else if ("标志牌".equals(xmzl)) {
			dataList=bzpService.getDataByCondition(condition, currentPage, rows);
		}
		return getPageBean(currentPage, rows, dataList);
	}

	/**
	 * 根据监控站的id查询监控的名称
	 * 
	 * @param id
	 * @return Object
	 * @date [2017-10-13] 创建文件 by 郎川
	 */
	public Object getJkzNameByJkzId(String id) {
		return jkzService.getJkzNameByJkzId(id);

	}

	/**
	 * @Title toImplement
	 * @Description (转入实施)
	 * @param request
	 * @return
	 * @Return String 返回类型
	 * @Throws
	 * @Date 2017年10月20日
	 * @修改历史 1. [2017年10月20日]创建文件 by 顾冲
	 */
	@RequestMapping("/toImplement")
	public @ResponseBody
	Map<String, Object> toImplement(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String xmzl = request.getParameter("xmzl");// 项目子类
		resultMap.put("xmlb", request.getParameter("xmlb"));// 项目类别
		resultMap.put("xmzl", xmzl);
		resultMap.put("szsf", request.getParameter("szsf"));// 所在区域
		String idsString = request.getParameter("toImplementId");

		if (idsString == null) {
			return resultMap;
		}

		jbxxService.toImplement(idsString, xmzl);
		return resultMap;
	}
	
	public void saveJbxx(Jbxx jbxx, User user){
	    
	    String szsf = "";// 所在省份
	    if("1".equals(user.getRoleName())){
	        szsf = dict_AREAService.getCodeNameByCodeValue(jbxx.getSzsf());
	    }else if("2".equals(user.getRoleName())){
            szsf = user.getProvince();
        }
		String szcs = dict_AREAService.getCodeNameByCodeValue(jbxx.getSzcs()); // 所在城市
		String szq = dict_AREAService.getCodeNameByCodeValue(jbxx.getSzq()); // 所在城市
		AddJbxx addJbxx = new AddJbxx(jbxx.getId(), jbxx.getXmlb(),
				jbxx.getXmzl(), jbxx.getXmmc(), jbxx.getXmbh(),
				jbxx.getBjfx(), jbxx.getSslx(), szsf,szcs,szq,
				jbxx.getJd(), jbxx.getWd(),
				jbxx.getDxlb(), jbxx.getJsxz(), jbxx.getSydw(),
				jbxx.getSbdw(), jbxx.getZytz(), jbxx.getDftz(),
				jbxx.getFj(), jbxx.getBz(), jbxx.getBxxm(), jbxx.getTznd(),
				jbxx.getJszt(), jbxx.getCjdw(), jbxx.getJldw(),
				jbxx.getZtbsj(), jbxx.getKgsj(), jbxx.getCysj(),
				jbxx.getJgsj(), jbxx.getSjsj(), jbxx.getSyzt(),
				jbxx.getTime());
		jbxxService.addJbxx(addJbxx);
	}
	
	/**
     * @Title jkzData 
     * @Description (获取监控站信息) 
     * @param request
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2017年12月4日
     * @修改历史  
     *     1. [2017年12月4日]创建文件 by 顾冲
     */
    @RequestMapping("/jkzData")
    public @ResponseBody List<Object> jkzData(HttpServletRequest request){
        String ids = request.getParameter("id");
        String xmzl = request.getParameter("xmzl");
        if(ids.endsWith(",")){
            ids.substring(0, ids.length() - 1);
        }
        String [] idsArr = ids.split(",");
        List<Object> jkz_idList = new ArrayList<Object>();
        //非备选项目、非废弃项目
        if("视频前端".equals(xmzl)){
            jkz_idList = spqdService.getJkz_idByIds(idsArr);
        }else if("显控终端".equals(xmzl)){
            jkz_idList = xkzdService.getJkz_idByIds(idsArr);
        }else if("传输线路".equals(xmzl)){
            jkz_idList = csxlService.getJkz_idByIds(idsArr);
        }else if("供电系统".equals(xmzl)){
            jkz_idList = gdxtService.getJkz_idByIds(idsArr);
        }else if("报警装置".equals(xmzl)){
            jkz_idList = bjzzService.getJkz_idByIds(idsArr);
        }
        List<Object> reusltList = jkzService.getDataByIds(jkz_idList);
        return reusltList;
    }
    
    /**
     * @Title getDataByIds 
     * @Description (根据ids获取jszt信息) 
     * @param ids
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月15日
     * @修改历史  
     *     1. [2017年12月15日]创建文件 by 顾冲
     */
    @RequestMapping("/getDataByIds")
    public @ResponseBody List<Object> getDataByIds(String ids){
        if(ids == null){
            return new ArrayList<Object>();
        }
        String [] idsArr = ids.split(",");
        return jbxxService.getDataByIds(idsArr);
    }
    
    /**
     * 获取pageBean
     * @param  currentPage
     * @param  rows
     * @param  dataList
     * @return pageBean
     */
    private PageBean getPageBean(Integer currentPage,Integer rows,List<Object> dataList){
    	int startRow = (currentPage-1)*rows;
	    int endRow = startRow+rows;
	    List<Object> list=new ArrayList<Object>();
	    for(int i = startRow;i<dataList.size()&&i<endRow;i++){
	        list.add(dataList.get(i));
	    }
	    PageBean pageBean=null;
	    if(dataList!=null&&dataList.size()>0){
	      pageBean=new PageBean(currentPage, rows, dataList.size(), list);
	    }	
		return pageBean;
    }
    
    /**
     * @Title showJkz_zl 
     * @Description (加载监控站下面的视频前端、显控终端信息) 
     * @param req
     * @param modelMap
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2018年1月6日
     * @修改历史  
     *     1. [2018年1月6日]创建文件 by 顾冲
     */
    @RequestMapping("/showJkz_zl")
    public String showJkz_zl(HttpServletRequest req, ModelMap modelMap){
        int rows = 5;
        int currentPage = 1;
        // 设施类型
        List<DICT_GY> sslxList = dict_gyService.getDict_GYsByType("设施类型");
        modelMap.addAttribute("sslxList", sslxList);
        // 边界方向
        List<DICT_GY> bjfxList = dict_gyService.getDict_GYsByType("边界方向");
        modelMap.addAttribute("bjfxList", bjfxList);
        // 地形类别
        List<DICT_GY> dxlbList = dict_gyService.getDict_GYsByType("地形类别");
        modelMap.addAttribute("dxlbList", dxlbList);
        // 建设性质
        List<DICT_GY> jsxzList = dict_gyService.getDict_GYsByType("建设性质");
        modelMap.addAttribute("jsxzList", jsxzList);
        // 建设状态
        if ("3".equals(req.getParameter("partTag"))) {
            List<DICT_GY> list_jszt = dict_gyService.getDict_GYsByType("建设状态");
            modelMap.addAttribute("list_jszt", list_jszt);
        }
        //使用状态
        if("4".equals(req.getParameter("partTag"))){
            List<DICT_GY>list_syzt=dict_gyService.getDict_GYsByType("使用状态");
            modelMap.addAttribute("list_syzt", list_syzt);
        }
        modelMap.addAttribute("partTag", req.getParameter("partTag"));
        modelMap.addAttribute("year", DateUtils.getYear());
        //查询条件
        Map<String, Object> condition = new HashMap<String, Object>();
        
        condition.put("jkz_id", req.getParameter("jkz_id"));
        PageBean pageBean = new PageBean();
        if("视频前端".equals(req.getParameter("xmzl"))){
            condition.put("sblx", req.getParameter("sblx"));
            List<Object> list = spqdService.getSpqdByCondition(condition);
            pageBean = getPageBean(currentPage, rows, list);
            modelMap.addAttribute("pageBean", pageBean);
            return "dataTablePage/jkz_spqd";
        }else if("显控终端".equals(req.getParameter("xmzl"))){
            List<Object> list = xkzdService.getXkzdByCondition(condition);
            pageBean = getPageBean(currentPage, rows, list);
            modelMap.addAttribute("pageBean", pageBean);
            return "dataTablePage/jkz_xkzd";
        }
        return "";
    }
    
    /**
     * @Title queryJkz_zl 
     * @Description (分页查询监控站下面的视频前端、显控终端信息) 
     * @return
     * @Return PageBean 返回类型 
     * @Throws 
     * @Date  2018年1月6日
     * @修改历史  
     *     1. [2018年1月6日]创建文件 by 顾冲
     */
    @RequestMapping("/queryJkz_zl")
    public @ResponseBody PageBean queryJkz_zl(HttpServletRequest req,@RequestParam Map<String, Object> condition){
        int rows = 5;
        int currentPage = 1;
        if(CommonUtil.validCondition(condition, "offiset")){
            currentPage = Integer.parseInt(req.getParameter("offiset").toString());
        }
        PageBean pageBean = new PageBean();
        List<Object> list = new ArrayList<Object>();
        if("视频前端".equals(req.getParameter("xmzl"))){
            list = spqdService.getSpqdByCondition(condition);
        }else if("显控终端".equals(req.getParameter("xmzl"))){
            list = xkzdService.getXkzdByCondition(condition);
            
        }
        pageBean = getPageBean(currentPage, rows, list);
        return pageBean;
    }
}
