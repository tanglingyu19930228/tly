package com.ljdy.BHF.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ljdy.BHF.model.DICT_GY;
import com.ljdy.BHF.service.Dict_GYService;

/**
 * 点击项目类别 项目子类分别跳转到相对应的详细界面 用于分别控制项目子类跳转的控制层(DetailPlanController)
 * 
 * @author郎川
 * @date 2017年9月27日
 */
@Controller
@RequestMapping("detaliPlan/")
public class DetailPlanController {
	@Resource
	private Dict_GYService dict_gyService;

	@RequestMapping("detail")
	public String load(HttpServletRequest request) {
		String szsf = "";
		String xmzl = "";
		String xmlb = "";
		try {
			szsf = new String(request.getParameter("szsf").getBytes(
					"ISO-8859-1"), "UTF-8");
			xmzl = new String(request.getParameter("xmzl").getBytes(
					"ISO-8859-1"), "UTF-8");
			xmlb = new String(request.getParameter("xmlb").getBytes(
					"ISO-8859-1"), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(szsf + " " + xmzl + " " + xmlb);
		String part = request.getParameter("part");
		System.out.println(part);
		List<DICT_GY> xmlblist = dict_gyService.getDict_GYsByType("项目类别");
		List<DICT_GY> xmzllist = dict_gyService.getDict_GYsByType("项目子类");

		request.setAttribute("szsf", szsf);
		request.setAttribute("xmzl", xmzl);
		request.setAttribute("xmlb", xmlb);
		request.setAttribute("part", part);
		request.setAttribute("xmlblist", xmlblist);
		request.setAttribute("xmzllist", xmzllist);

		return "provinceTableDetail";
	}

}
