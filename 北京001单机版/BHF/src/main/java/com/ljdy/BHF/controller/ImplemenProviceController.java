package com.ljdy.BHF.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljdy.BHF.model.DICT_GY;


/**
 * 实施监督模块点击18个边防省跳转后台控制层
 * @author 郎川
 *@date 2017年11月7日
 */
@Controller
@RequestMapping("implemenProvice")
public class ImplemenProviceController  extends  BaseController{
	
	@RequestMapping("/load")
	public String load(HttpServletRequest request){
		//查询项目类别
		List<DICT_GY> xmlbList=dict_gyService.getDict_GYsByType("项目类别");
		//查询项目子类
		List<DICT_GY>xmzlList=dict_gyService.getDict_GYsByType("项目子类");
		
		request.setAttribute("xmlbList", xmlbList);
		request.setAttribute("xmzlList", xmzlList);
		
		
		return "provinceTable";
	}
	
	public  Map<String, Object>tableData(String szsf,String szcs,String partTag){
		
		//返回的数据
		Map<String, Object>data = new HashMap<String,Object>();
		
		//查询条件
		Map<String, Object>condition=new HashMap<String, Object>();
		String bxxm=null;
		if("3".equals(partTag)){
			bxxm="否";
		}
		condition.put("bxxm", bxxm);
		condition.put("szsf", szsf);
		condition.put("szcs", szcs);
		
		//交通报警设施 执勤道路
		//List<Object>zqdl_data=zqdlService.getManageData(condition);
		List<Object>zqdl_tz=zqdlService.getManageTz(condition);
		
		//data.put("zqdl_data", zqdl_data);
		data.put("zqdl_tz", zqdl_tz);
		
		//交通保障设施 桥梁
		List<Object>qiaoliang_data=qiaoLiangService.getManageData(condition);
		List<Object>qiaoliang_tz=qiaoLiangService.getManageTz(condition);
		data.put("qiaoliang_data", qiaoliang_data);
		data.put("qiaoliang_tz", qiaoliang_tz);
		
		//交通保障设施 执勤码头
		List<Object>zqmt_data=zqmtService.getManageData(condition);
		List<Object>zqmt_tz=zqmtService.getManageTz(condition);
		data.put("zqmt_data", zqmt_data);
		data.put("zqmt_tz", zqmt_tz);
		
		//交通保障设施 直升机停机坪
		List<Object>zsjtjp_data=zsjtjpService.getManageData(condition);
		List<Object>zsjtjp_tz=zsjtjpService.getManageTz(condition);
		data.put("zsjtjp_data", zsjtjp_data);
		data.put("zsjtjp_tz", zsjtjp_tz);
		
		
		//拦阻报警设施 铁丝网
		List<Object>tsw_data=tswService.getManageData(condition);
		List<Object>tsw_tz=tswService.getManageTz(condition);
		
		data.put("tsw_data", tsw_data);
		data.put("tsw_tz", tsw_tz);
		
		//拦阻报警设施  铁栅栏
		List<Object>tzl_data=tzlService.getManageData(condition);
		List<Object>tzl_tz=tzlService.getManageTz(condition);
		data.put("tzl_data", tzl_data);
		data.put("tzl_tz", tzl_tz);
		
		//拦阻报警设施 骏马
		List<Object>juma_data=jumaService.getManageData(condition);
		List<Object>juma_tz=jumaService.getManageTz(condition);
		data.put("juma_data", juma_data);
		data.put("juma_tz", juma_tz);
		
        //拦阻报警设施 拦阻桩
		List<Object>lzz_data=lzzService.getManageData(condition);
		List<Object>lzz_tz=lzzService.getManageTz(condition);
		data.put("lzz_data", lzz_data);
		data.put("lzz_tz", lzz_tz);
		
		//拦阻报警设施 隔离带
		List<Object>gld_data=gldService.getManageData(condition);
		List<Object>gld_tz=gldService.getManageTz(condition);
		data.put("gld_data", gld_data);
		data.put("gld_tz", gld_tz);
		
		//拦阻报警设施 报警线路
		List<Object>bjxl_data=bjxlService.getManageData(condition);
		List<Object>bjxl_tz=bjxlService.getManageTz(condition);
		data.put("bjxl_data", bjxl_data);
		data.put("bjxl_tz", bjxl_tz);
		
		//指挥监控设施 监控中心(国家级)
		condition.put("jb", "国家级");
		List<Object>gj_jkzx_data=jkzxService.getManageData(condition);
		List<Object>gj_jkzx_tz=jkzxService.getManageTz(condition);
		data.put("gj_jkzx_data", gj_jkzx_data);
		data.put("gj_jkzx_tz", gj_jkzx_tz);
		
		//指挥监控设施 监控中心(省级)
		condition.put("jb", "省级");
		List<Object>sj_jkzx_data=jkzxService.getManageData(condition);
		List<Object>sj_jkzx_tz=jkzxService.getManageTz(condition);
		data.put("sj_jkzx_data", sj_jkzx_data);
		data.put("sj_jkzx_tz", sj_jkzx_tz);
		
		//指挥监控设施 监控中心(省级)
		condition.put("jb", "地级");
		List<Object>dj_jkzx_data=jkzxService.getManageData(condition);
		List<Object>dj_jkzx_tz=jkzxService.getManageTz(condition);
		data.put("dj_jkzx_data", dj_jkzx_data);
		data.put("dj_jkzx_tz", dj_jkzx_tz);
		
		//指挥监控设施 监控中心(省级)
		condition.put("jb", "县级");
		List<Object>xj_jkzx_data=jkzxService.getManageData(condition);
		List<Object>xj_jkzx_tz=jkzxService.getManageTz(condition);
		data.put("xj_jkzx_data", xj_jkzx_data);
		data.put("xj_jkzx_tz", xj_jkzx_tz);
		
		//指挥监控设施 监控站
		List<Object>jkz_data=jkzService.getManageData(condition);
		List<Object>jkz_tz=jkzService.getManageTz(condition);
		data.put("jkz_data", jkz_data);
		data.put("jkz_tz", jkz_tz);
		
		//指挥监控设施 传输线路
		List<Object>csxl_data=csxlService.getManageData(condition);
		List<Object>csxl_tz=csxlService.getManagetz(condition);
		data.put("csxl_data", csxl_data);
		data.put("csxl_tz", csxl_tz);
		
		//指挥监控设施 供电系统
		List<Object>gdxt_data=gdxtService.getManageData(condition);
		List<Object>gdxt_tz=gdxtService.getManagetz(condition);
		data.put("gdxt_data", gdxt_data);
		data.put("gdxt_tz", gdxt_tz);
		
		//指挥监控设施 军警民联防平台
		List<Object>jjm_data=jjmlfptService.getManageData(condition);
		List<Object>jjm_tz=jjmlfptService.getManageTz(condition);
		data.put("jjm_data", jjm_data);
		data.put("jjm_tz", jjm_tz);
		
		//指挥监控设施 无人值守电子哨兵
		List<Object>wrzs_data=wrzsdzsbService.getManageData(condition);
		List<Object>wrzs_tz=wrzsdzsbService.getManageTz(condition);
		data.put("wrzs_data", wrzs_data);
		data.put("wrzs_tz", wrzs_tz);
		
		//指挥监控设施 视频前端
		List<Object>spqd_data=spqdService.getManageData(condition);
		List<Object>spqd_tz=spqdService.getManageTz(condition);
		data.put("spqd_data", spqd_data);
		data.put("spqd_tz", spqd_tz);
		
		//指挥监控设施 显控终端
		List<Object>xkzd_data=xkzdService.getManageData(condition);
		List<Object>xkzd_tz=xkzdService.getManageTz(condition);
		data.put("xkzd_data", xkzd_data);
		data.put("xkzd_tz", xkzd_tz);
		
		//指挥监控设施 报警装置
		List<Object>bjzz_data=bjzzService.getManageData(condition);
		List<Object>bjzz_tz=bjzzService.getManageTz(condition);
		data.put("bjzz_data", bjzz_data);
		data.put("bjzz_tz", bjzz_tz);
		
		//辅助配套设施 执勤房
		List<Object>zqf_data=zqfService.getManageData(condition);
		List<Object>zqf_tz=zqfService.getManageTz(condition);
		data.put("zqf_data", zqf_data);
		data.put("zqf_tz", zqf_tz);
		
		//辅助配套设施 了望塔
		List<Object>lwt_data=lwtService.getManageData(condition);
		List<Object>lwt_tz=lwtService.getManageTz(condition);
		data.put("lwt_data", lwt_data);
		data.put("lwt_tz", lwt_tz);
		
		//辅助配套设施 标志牌
		List<Object>bzp_data=bzpService.getManageData(condition);
		List<Object>bzp_tz=bzpService.getManageTz(condition);
		data.put("bzp_data", bzp_data);
		data.put("bzp_tz", bzp_tz);
		return data;
	}

}
