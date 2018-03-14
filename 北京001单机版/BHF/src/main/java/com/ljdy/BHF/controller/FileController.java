package com.ljdy.BHF.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传、下载、数据导入请求处理
 * 
 * @author 徐成 修改历史： [2017年10月23日] 创建文件 by 徐成
 */
@Scope("prototype")
@Controller
@RequestMapping("file")
public class FileController extends BaseController {

	/**
	 * 文件上传
	 * 
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody
	String upload(HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile file)
			throws Exception {
		String szsf = request.getParameter("szsf");// 省份
		String xmmc = request.getParameter("xmmc");// 项目名称
        xmmc=java.net.URLDecoder.decode(xmmc, "UTF-8");
		String partTag = request.getParameter("partTag");// 项目阶段
		String lastDir = "";
		if ("1".equals(partTag)) {
			lastDir = "规划阶段";
		} else if ("2".equals(partTag)) {
			lastDir = "计划阶段";
		} else if ("3".equals(partTag)) {
			lastDir = "实施阶段";
		} else if ("4".equals(partTag)) {
			lastDir = "维护阶段";
		}
		// 设置文件上传根路劲
		/*String root = request.getSession().getServletContext()
				.getRealPath("/upload/" + szsf + "/" + xmmc + "/" + lastDir);*/
		String root = getUploadAddress(request) + ":\\upload\\"+szsf+"\\"+ xmmc + "\\" + lastDir;
		String fileName = file.getOriginalFilename();
		// 获取文件后缀
		String extension = fileName.indexOf(".") == -1 ? "" : fileName
				.substring(fileName.lastIndexOf(".") + 1);
		// 上传的文件最大10G
		long maxSize = 1024 * 1024 * 1024 * 10L;
		// 支持的文件类型:文本文件、图片、文档、Excel表格、压缩文件...
		String allowSuffix = "txt,img,jpg,png,gif,jpeg,doc,docx,xls,xlsx,zip,rar";
		if (!allowSuffix.contains(extension.toLowerCase())) {//忽略后缀大小写
			String msg = "不支持的文件类型,上传失败,支持的格式为：" + allowSuffix;
			return msg;
		} else if (file.getSize() > maxSize) {
			String msg = "上传文件过大，上传失败，文件最大10G";
			return msg;
		} else {
			String newFileName = UUID.randomUUID().toString()
					.replaceAll("-", "")
					+ fileName;
			File toFile = new File(root, newFileName);
			if (!toFile.exists()) {
				toFile.mkdirs();
			}
			try {
				file.transferTo(toFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return szsf+"/"+xmmc+"/"+lastDir+"/"+newFileName;
		}
	}

	/**
	 * 下载文件
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/download")
	public void download(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String fileName = request.getParameter("fileName");
		fileName=java.net.URLDecoder.decode(fileName, "UTF-8");
		//String root = request.getSession().getServletContext().getRealPath("upload");// 下载地址
		String root = getUploadAddress(request) + ":\\upload\\";//下载地址
		File file = new File(root, File.separator + fileName);
		downFile(response, fileName.substring(fileName.lastIndexOf("/")+33), file);
	}

	/**
	 * 验证文件是否存在
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/isExist")
	public @ResponseBody boolean isExist(HttpServletRequest request) throws Exception{
		String fileName = request.getParameter("fileName");
		try {
			fileName=java.net.URLDecoder.decode(fileName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//String root = request.getSession().getServletContext().getRealPath("upload");
		String root = getUploadAddress(request) + ":\\upload\\";//下载地址
		File file = new File(root,fileName);
		return file.exists();
	}
	/**
	 * 
	 * @Title getUploadAddress 
	 * @Description (获取上传文件盘符) 
	 * @param req
	 * @return
	 * @throws Exception
	 * @Return String 返回类型 
	 * @Throws 
	 * @Date  2018年1月18日
	 * @修改历史  
	 *     1. [2018年1月18日]创建文件 by 顾冲
	 */
	public String getUploadAddress(HttpServletRequest req) throws Exception{
        String address= "";
        String root = req.getSession().getServletContext().getRealPath("");
        File file = new File(root+ File.separator + "uploadAddress.xml");
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(file);
            Element rootElement = doc.getRootElement();
            address = rootElement.element("address").getText();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return address;
    }
}
