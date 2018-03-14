package com.ljdy.BHF.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ljdy.BHF.model.User;
import com.ljdy.BHF.service.DICT_AREAService;
import com.ljdy.BHF.service.StatisticsService;
import com.ljdy.BHF.utils.DateUtils;

/**
 * @ClassName OverAllPlanController
 * @Description (统筹规划controller)
 * @Author 顾冲 guchong@luojiadeyi.com
 * @Date 2017年9月25日 下午3:48:31
 * @修改历史 1. [2017年9月25日]创建文件 by 顾冲
 */
@Controller()
@RequestMapping("/overAllPlan")
public class OverAllPlanController {
	@Resource
	private StatisticsService statisticsService;

	@Resource
	private DICT_AREAService dict_areaService;

	/**
	 * @Title load
	 * @Description (统筹规划主页面数据加载)
	 * @param req
	 * @return
	 * @Return String 返回类型
	 * @Throws
	 * @Date 2017年9月25日
	 * @修改历史 1. [2017年9月25日]创建文件 by 顾冲
	 */
	@RequestMapping("/load")
	public String load(HttpServletRequest req, ModelMap model) {
		String tznd = req.getParameter("tznd");
		User loginUser = (User) req.getSession().getAttribute("loginUser");
		String partTag = req.getParameter("partTag");
		if ("2".equals(partTag)) {
			tznd = String.valueOf(DateUtils.getYear());
		}
		model.put("partTag", partTag);
		String roleName = loginUser.getRoleName();
		if (tznd != null && tznd.trim() != "" && !"null".equals(tznd)) {
			model.put("tznd", tznd);
		}
		if ("1".equals(roleName)) {
			Map<String, Object> resultMap = statisticsService.CombinedJsgmData(
					tznd, partTag, loginUser);
			List<Object> sfList = statisticsService.getProvinceShort();
			model.put("gjzbMap", resultMap);
			model.put("areaList", sfList);
			return "index";
		} else if ("2".equals(roleName)) {
			Map<String, Object> resultMap = statisticsService.CombinedJsgmData(
					tznd, partTag, loginUser);
			List<Object> cityList = statisticsService
					.getCityByProvince(loginUser.getProvince());
			model.put("gjzbMap", resultMap);
			if (cityList == null || cityList.isEmpty()) {
				cityList.add(loginUser.getProvince());
			}
			model.put("areaList", cityList);
			return "sjIndex";
		}
		return "login";
	}

}
