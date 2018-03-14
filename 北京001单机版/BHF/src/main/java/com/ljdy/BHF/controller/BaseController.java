package com.ljdy.BHF.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.ljdy.BHF.service.BjxlService;
import com.ljdy.BHF.service.BjzzService;
import com.ljdy.BHF.service.BzpService;
import com.ljdy.BHF.service.CsxlService;
import com.ljdy.BHF.service.DICT_AREAService;
import com.ljdy.BHF.service.Dict_GYService;
import com.ljdy.BHF.service.GdxtService;
import com.ljdy.BHF.service.GldService;
import com.ljdy.BHF.service.JbxxService;
import com.ljdy.BHF.service.JjmlfptService;
import com.ljdy.BHF.service.JkzService;
import com.ljdy.BHF.service.JkzxService;
import com.ljdy.BHF.service.JumaService;
import com.ljdy.BHF.service.LwtService;
import com.ljdy.BHF.service.LzzService;
import com.ljdy.BHF.service.QiaoLiangService;
import com.ljdy.BHF.service.SpqdService;
import com.ljdy.BHF.service.StatisticsService;
import com.ljdy.BHF.service.TswService;
import com.ljdy.BHF.service.TzlService;
import com.ljdy.BHF.service.WrzsdzsbService;
import com.ljdy.BHF.service.XkzdService;
import com.ljdy.BHF.service.XmwhjlService;
import com.ljdy.BHF.service.ZqdlService;
import com.ljdy.BHF.service.ZqfService;
import com.ljdy.BHF.service.ZqmtService;
import com.ljdy.BHF.service.ZsjtjpService;

/**
 * 请求处理基础类
 * 
 * @author LJDY817
 * 
 */
@Controller
@Scope("prototype")
public class BaseController {

	@Resource
	protected Dict_GYService dict_gyService;

	@Resource
	protected DICT_AREAService dict_AREAService;

	@Resource
	protected JbxxService jbxxService;

	@Resource
	protected ZqdlService zqdlService;

	@Resource
	protected QiaoLiangService qiaoLiangService;

	@Resource
	protected ZqmtService zqmtService;

	@Resource
	protected ZsjtjpService zsjtjpService;

	@Resource
	protected TswService tswService;

	@Resource
	protected TzlService tzlService;

	@Resource
	protected LzzService lzzService;

	@Resource
	protected GldService gldService;

	@Resource
	protected JumaService jumaService;

	@Resource
	protected BjxlService bjxlService;

	@Resource
	protected JkzxService jkzxService;

	@Resource
	protected JkzService jkzService;

	@Resource
	protected XkzdService xkzdService;

	@Resource
	protected SpqdService spqdService;

	@Resource
	protected CsxlService csxlService;

	@Resource
	protected BjzzService bjzzService;

	@Resource
	protected GdxtService gdxtService;

	@Resource
	protected WrzsdzsbService wrzsdzsbService;

	@Resource
	protected JjmlfptService jjmlfptService;

	@Resource
	protected ZqfService zqfService;

	@Resource
	protected LwtService lwtService;

	@Resource
	protected BzpService bzpService;

	@Resource
	protected StatisticsService statisticsService;

	@Resource
	protected XmwhjlService xmwhjlService;

	/**
	 * 将文件下载到本地 消除文件名中文乱码
	 * 
	 * @param response
	 * @param downFileName
	 * @param file
	 * @throws Exception
	 */
	public void downFile(HttpServletResponse response, String downFileName,
			File file) throws Exception {
		String fileName = new String(downFileName.getBytes("UTF-8"),
				"iso8859-1");
		response.setContentType("application/force-download");// 设置强制下载不打开
		response.setCharacterEncoding("UTF-8");
		response.addHeader("Content-Disposition", "attachment;fileName="
				+ fileName);// 设置文件名
		byte[] buffer = new byte[1024 * 1024 * 4];
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		OutputStream os = response.getOutputStream();
		int i = bis.read(buffer);
		while (i != -1) {
			os.write(buffer, 0, i);
			i = bis.read(buffer);
		}
		bis.close();
		fis.close();
	}
}
