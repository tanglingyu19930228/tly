package com.ljdy.BHF.controller;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
import com.ljdy.BHF.model.Whxm;
import com.ljdy.BHF.model.Xmwhjl;
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
import com.ljdy.BHF.service.WhxmService;
import com.ljdy.BHF.service.WrzsdzsbService;
import com.ljdy.BHF.service.XkzdService;
import com.ljdy.BHF.service.XmwhjlService;
import com.ljdy.BHF.service.ZqdlService;
import com.ljdy.BHF.service.ZqfService;
import com.ljdy.BHF.service.ZqmtService;
import com.ljdy.BHF.service.ZsjtjpService;
import com.ljdy.BHF.utils.CommonUtil;
import com.ljdy.BHF.utils.DateUtils;
import com.ljdy.BHF.utils.SortUtils;
import com.ljdy.BHF.utils.StringUtils;

/**
 * Excel数据导入数据库
 * @author 徐成
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("import")
public class ImportController extends BaseController{
	
	@Resource
    private XmwhjlService xmwhjlService;
    
    @Resource
    private WhxmService whxmService;
    
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /**
	 * Excel文件上传
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/import_Excel",method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String importExcel(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file) throws Exception{
		String uploadPath = request.getSession().getServletContext().getRealPath("upload/data");
		String fileName = file.getOriginalFilename();
		String extension = fileName.indexOf(".")==-1?"":fileName.substring(fileName.lastIndexOf(".")+1);
		String msg = null;
		String partTag = request.getParameter("partTag");
		if("xls".equals(extension) || "xlsx".equals(extension)){
			String newFileName = UUID.randomUUID()+fileName;
			File toFile = new File(uploadPath,newFileName);
			if(!toFile.exists()){
				toFile.mkdirs();
			}
			file.transferTo(toFile);
			msg = readExcel(toFile,extension,Integer.parseInt(partTag));
			if(msg==null){
				msg = "导入成功";
			}
		}else{
			msg = "文件类型不符合，请上传Excel文件";
		}
		return msg;
		
	}
	
	/**
	 * 读取Excel文件，返回数据导入结果
	 * @param file
	 * @param extension
	 * @return
	 * @throws Exception
	 */
	private String readExcel(File file,String extension,int partTag) throws Exception{
		List<Object> saveList = new ArrayList<Object>();
		List<Object> updateList = new ArrayList<Object>();
		Workbook wb = null;
		if("xls".equals(extension)){
            try {
				wb=new HSSFWorkbook(new FileInputStream(file));
			} catch (Exception e) {
				String msg = "文件格式有误,请将文件另存为格式正确的Excel文件后上传";
				return msg;
			}
        }else if("xlsx".equals(extension)){
            try {
				wb = new XSSFWorkbook(new FileInputStream(file));
			} catch (Exception e) {
				String msg = "文件格式有误,请将文件另存为格式正确的Excel文件后上传";
				return msg;
			}
        }
		Map<String,String> errorTip = new HashMap<String,String>();
		int sheetCount = wb.getNumberOfSheets();
		int count = 0; //文件中数据总行数
		for (int x = 0; x < sheetCount; x++) {
			Sheet sheet = wb.getSheetAt(x);
			count += sheet.getLastRowNum();
		}
		if(count > 20000){
			String msg = "导入失败，因为：文件中有"+count+"条数据，为保证上传效率最大化和系统正常运行，系统单次支持的最大条数为20000条，您本次上传数据过多，请减少单次上传数据数量，并分批次上传";
			return msg;
		}else{
			for(int i=0;i<sheetCount;i++){
				Sheet sheet = wb.getSheetAt(i);
				Row row = null;
				String sheetName = sheet.getSheetName();
				
				Row row0 = sheet.getRow(sheet.getFirstRowNum());
				String xmzl=sheetName;
				int startIndex= getStartIndex(xmzl);
				if(partTag == 1 || partTag == 2){//根据建设状态判断
				    if(!"".equals(getValue(row0.getCell(startIndex)))){
				        String msg = "请导入统筹规划或者年度计划的数据";
				        return msg;
				    }
				}else if(partTag == 3){
				    if(!"建设状态".equals(getValue(row0.getCell(startIndex))) || !"".equals(getValue(row0.getCell(startIndex + 8)))){
                        String msg = "请导入实施监督的数据";
                        return msg;
                    }
				}else if(partTag == 4){
				    if(!"使用状态".equals(getValue(row0.getCell(startIndex + 8)))){
				        String msg = "请导入使用维护的数据";
				        return msg;
				    }
				}
				
				if("执勤道路".equals(sheetName)){				    
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddZqdl zqdl = new AddZqdl();
							String id;
							String xmbh;
							if(getValue(cell(row,ZqdlService.columns,"id"))==null || getValue(cell(row,ZqdlService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,ZqdlService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,ZqdlService.columns,"xmbh"))==null || getValue(cell(row,ZqdlService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,ZqdlService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							zqdl.setId(id);
							zqdl.setJsdd(getValue(cell(row,ZqdlService.columns,"jsdd")));//建设地点
							zqdl.setJsgm(new BigDecimal(getValue(cell(row,ZqdlService.columns,"jsgm"))));//建设规模
							zqdl.setDllb(getValue(cell(row,ZqdlService.columns,"dllb")));//道路类别
							zqdl.setDllx(getValue(cell(row,ZqdlService.columns,"dllx")));//道路类型
							zqdl.setDldj(getValue(cell(row,ZqdlService.columns,"dldj")));//道路等级
							zqdl.setLjlx(getValue(cell(row,ZqdlService.columns,"ljlx")));//路基类型
							zqdl.setLmlx(getValue(cell(row,ZqdlService.columns,"lmlx")));//路面类型
							jbxx.setFj(getValue(cell(row,ZqdlService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,ZqdlService.columns,"bz")));//备注
							list.add(jbxx);
							list.add(zqdl);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Zqdl");
					}
				}else if("桥梁".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddQiaoLiang qiaoLiang = new AddQiaoLiang();
							String id;
							String xmbh;
							if(getValue(cell(row,QiaoLiangService.columns,"id"))==null || getValue(cell(row,QiaoLiangService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,QiaoLiangService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,QiaoLiangService.columns,"xmbh"))==null || getValue(cell(row,QiaoLiangService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,QiaoLiangService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							qiaoLiang.setId(id);
							qiaoLiang.setJsdd(getValue(cell(row,QiaoLiangService.columns,"jsdd")));//建设地点
							qiaoLiang.setJsgm(StringUtils.getIntVal(getValue(cell(row,QiaoLiangService.columns,"jsgm"))));//建设规模
							qiaoLiang.setQllx(getValue(cell(row,QiaoLiangService.columns,"qllx")));//桥梁类型
							qiaoLiang.setZz(Double.parseDouble(getValue(cell(row,QiaoLiangService.columns,"zz"))));//载重
							jbxx.setFj(getValue(cell(row,QiaoLiangService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,QiaoLiangService.columns,"bz")));//备注
							list.add(jbxx);
							list.add(qiaoLiang);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"QiaoLiang");
					}
				}else if("执勤码头".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddZqmt zqmt = new AddZqmt();
							String id;
							String xmbh;
							if(getValue(cell(row,ZqmtService.columns,"id"))==null || getValue(cell(row,ZqmtService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,ZqmtService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,ZqmtService.columns,"xmbh"))==null || getValue(cell(row,ZqmtService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,ZqmtService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							zqmt.setId(id);
							zqmt.setJsdd(getValue(cell(row,ZqmtService.columns,"jsdd")));//建设地点
							zqmt.setJsgm(StringUtils.getIntVal(getValue(cell(row,ZqmtService.columns,"jsgm"))));//建设规模
							jbxx.setFj(getValue(cell(row,ZqmtService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,ZqmtService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(zqmt);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Zqmt");
					}
				}else if("直升机停机坪".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddZsjtjp zsjtjp = new AddZsjtjp();
							String id;
							String xmbh;
							if(getValue(cell(row,ZsjtjpService.columns,"id"))==null || getValue(cell(row,ZsjtjpService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,ZsjtjpService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,ZsjtjpService.columns,"xmbh"))==null || getValue(cell(row,ZsjtjpService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,ZsjtjpService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							zsjtjp.setId(id);
							zsjtjp.setJsdd(getValue(cell(row,ZsjtjpService.columns,"jsdd")));//建设地点
							zsjtjp.setJsgm(StringUtils.getIntVal(getValue(cell(row,ZsjtjpService.columns,"jsgm"))));//建设规模
							zsjtjp.setTjplx(getValue(cell(row,ZsjtjpService.columns,"tjplx")));//停机坪类型
							jbxx.setFj(getValue(cell(row,ZsjtjpService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,ZsjtjpService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(zsjtjp);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Zsjtjp");
					}
				}else if("铁丝网".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddTsw tsw = new AddTsw();
							String id;
							String xmbh;
							if(getValue(cell(row,TswService.columns,"id"))==null || getValue(cell(row,TswService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,TswService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,TswService.columns,"xmbh"))==null || getValue(cell(row,TswService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,TswService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							tsw.setId(id);
							tsw.setJsdd(getValue(cell(row,TswService.columns,"jsdd")));//建设地点
							tsw.setJsgm(new BigDecimal(Double.parseDouble(getValue(cell(row,TswService.columns,"jsgm")))));//建设规模
						    tsw.setLzmgs(Integer.parseInt(getValue(cell(row,TswService.columns,"lzmgs"))));//拦阻门个数
							tsw.setTswlx(getValue(cell(row,TswService.columns,"tswlx")));//铁丝网类型
							jbxx.setFj(getValue(cell(row,TswService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,TswService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(tsw);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Tsw");
					}
				}else if("铁栅栏".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddTzl tzl = new AddTzl();
							String id;
							String xmbh;
							if(getValue(cell(row,TzlService.columns,"id"))==null || getValue(cell(row,TzlService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,TzlService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,TzlService.columns,"xmbh"))==null || getValue(cell(row,TzlService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,TzlService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							tzl.setId(id);
							tzl.setJsdd(getValue(cell(row,TzlService.columns,"jsdd")));//建设地点
							tzl.setJsgm(new BigDecimal(Double.parseDouble(getValue(cell(row,TzlService.columns,"jsgm")))));//建设规模
							jbxx.setFj(getValue(cell(row,TzlService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,TzlService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(tzl);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Tzl");
					}
					
				}else if("拦阻桩".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddLzz lzz = new AddLzz();
							String id;
							String xmbh;
							if(getValue(cell(row,LzzService.columns,"id"))==null || getValue(cell(row,LzzService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,LzzService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,LzzService.columns,"xmbh"))==null || getValue(cell(row,LzzService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,LzzService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							lzz.setId(id);
							lzz.setJsdd(getValue(cell(row,LzzService.columns,"jsdd")));//建设地点
							lzz.setJsgm(StringUtils.getIntVal(getValue(cell(row,LzzService.columns,"jsgm"))));//建设规模
							lzz.setLzzlx(getValue(cell(row,LzzService.columns,"lzzlx")));//拦阻桩类型
							jbxx.setFj(getValue(cell(row,LzzService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,LzzService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(lzz);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Lzz");
					}
				}else if("隔离带".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddGld gld = new AddGld();
							String id;
							String xmbh;
							if(getValue(cell(row,GldService.columns,"id"))==null || getValue(cell(row,GldService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,GldService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,GldService.columns,"xmbh"))==null || getValue(cell(row,GldService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,GldService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							gld.setId(id);
							gld.setJsdd(getValue(cell(row,GldService.columns,"jsdd")));//建设地点
							gld.setJsgm(new BigDecimal(Double.parseDouble(getValue(cell(row,GldService.columns,"jsgm")))));//建设规模
							jbxx.setFj(getValue(cell(row,GldService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,GldService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(gld);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Gld");
					}
				}else if("报警线路".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddBjxl bjxl = new AddBjxl();
							String id;
							String xmbh;
							if(getValue(cell(row,BjxlService.columns,"id"))==null || getValue(cell(row,BjxlService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,BjxlService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,BjxlService.columns,"xmbh"))==null || getValue(cell(row,BjxlService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,BjxlService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							bjxl.setId(id);
							bjxl.setJsdd(getValue(cell(row,BjxlService.columns,"jsdd")));//建设地点
							bjxl.setJsgm(new BigDecimal(Double.parseDouble(getValue(cell(row,BjxlService.columns,"jsgm")))));//建设规模
							bjxl.setSbpp(getValue(cell(row,BjxlService.columns,"sbpp")));
							jbxx.setFj(getValue(cell(row,BjxlService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,BjxlService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(bjxl);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Bjxl");
					}
				}else if("拒马".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddJuma juma = new AddJuma();
							String id;
							String xmbh;
							if(getValue(cell(row,JumaService.columns,"id"))==null || getValue(cell(row,JumaService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,JumaService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,JumaService.columns,"xmbh"))==null || getValue(cell(row,JumaService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,JumaService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							juma.setId(id);
							juma.setJsdd(getValue(cell(row,JumaService.columns,"jsdd")));//建设地点
							juma.setJsgm(StringUtils.getIntVal(getValue(cell(row,JumaService.columns,"jsgm"))));//建设规模
							jbxx.setFj(getValue(cell(row,JumaService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,JumaService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(juma);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Juma");
					}
					
				}else if("监控中心".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddJkzx jkzx = new AddJkzx();
							String id;
							String xmbh;
							if(getValue(cell(row,JkzxService.columns,"id"))==null || getValue(cell(row,JkzxService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,JkzxService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,JkzxService.columns,"xmbh"))==null || getValue(cell(row,JkzxService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,JkzxService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							jkzx.setId(id);
							jkzx.setJb(getValue(cell(row,JkzxService.columns,"jb")));
							jkzx.setXscsxl(getValue(cell(row,JkzxService.columns,"xscsxl")));
							jkzx.setXsltqk(getValue(cell(row,JkzxService.columns,"xsltqk")));
							jkzx.setXsltwlxz(getValue(cell(row,JkzxService.columns,"xsltwlxz")));
							jbxx.setFj(getValue(cell(row,JkzxService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,JkzxService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(jkzx);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Jkzx");
					}
					
				}else if("监控站".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddJkz jkz = new AddJkz();
							String id;
							String xmbh;
							if(getValue(cell(row,JkzService.columns,"id"))==null || getValue(cell(row,JkzService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,JkzService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,JkzService.columns,"xmbh"))==null || getValue(cell(row,JkzService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,JkzService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							jkz.setId(id);
							String gdqdgs=getValue(cell(row,JkzService.columns,"gdqdgs"));
							jkz.setGdqdgs(StringUtils.getIntVal(gdqdgs==null||"".equals(gdqdgs)?"0":gdqdgs));
							String xkzdgs=getValue(cell(row,JkzService.columns,"xkzdgs"));
							jkz.setXkzdgs(StringUtils.getIntVal(xkzdgs==null||"".equals(xkzdgs)?"0":xkzdgs));
							jkz.setXscsxl(getValue(cell(row,JkzService.columns,"xscsxl")));
							jkz.setXsltqk(getValue(cell(row,JkzService.columns,"xsltqk")));
							jkz.setXsltwlxz(getValue(cell(row,JkzService.columns,"xsltwlxz")));
							String ydqdgs=getValue(cell(row,JkzService.columns,"ydqdgs"));
							jkz.setYdqdgs(StringUtils.getIntVal(ydqdgs==null||"".equals(ydqdgs)?"0":ydqdgs));
							jbxx.setFj(getValue(cell(row,JkzService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,JkzService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(jkz);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Jkz");
					}
					
				}else if("视频前端".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddSpqd spqd = new AddSpqd();
							String id;
							String xmbh;
							if(getValue(cell(row,SpqdService.columns,"id"))==null || getValue(cell(row,SpqdService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,SpqdService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,SpqdService.columns,"xmbh"))==null || getValue(cell(row,SpqdService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,SpqdService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							spqd.setId(id);
							spqd.setCsfs(getValue(cell(row,SpqdService.columns,"csfs")));
							spqd.setFzdd(getValue(cell(row,SpqdService.columns,"fzdd")));
							spqd.setJkz_id(getValue(cell(row,SpqdService.columns,"jkz_id")));
							spqd.setJkz_name(getValue(cell(row,SpqdService.columns,"jkz_name")));
							spqd.setSblx(getValue(cell(row,SpqdService.columns,"sblx")));
							jbxx.setFj(getValue(cell(row,SpqdService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,SpqdService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(spqd);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Spqd");
					}
					
				}else if("显控终端".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddXkzd xkzd = new AddXkzd();
							String id;
							String xmbh;
							if(getValue(cell(row,XkzdService.columns,"id"))==null || getValue(cell(row,XkzdService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,XkzdService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,XkzdService.columns,"xmbh"))==null || getValue(cell(row,XkzdService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,XkzdService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							xkzd.setId(id);
							xkzd.setCsfs(getValue(cell(row,XkzdService.columns,"csfs")));
							xkzd.setFzdd(getValue(cell(row,XkzdService.columns,"fzdd")));
							xkzd.setJkz_id(getValue(cell(row,XkzdService.columns,"jkz_id")));
							xkzd.setJkz_name(getValue(cell(row,XkzdService.columns,"jkz_name")));
							jbxx.setFj(getValue(cell(row,XkzdService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,XkzdService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(xkzd);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Xkzd");
					}
				}else if("传输线路".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddCsxl csxl = new AddCsxl();
							String id;
							String xmbh;
							if(getValue(cell(row,CsxlService.columns,"id"))==null || getValue(cell(row,CsxlService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,CsxlService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,CsxlService.columns,"xmbh"))==null || getValue(cell(row,CsxlService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,CsxlService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							csxl.setId(id);
							csxl.setJkz_id(getValue(cell(row,CsxlService.columns,"jkz_id")));
							csxl.setJkz_name(getValue(cell(row,CsxlService.columns,"jkz_name")));
							csxl.setJsdd(getValue(cell(row,CsxlService.columns,"jsdd")));//建设地点
							csxl.setJsgm(new BigDecimal(Double.parseDouble(getValue(cell(row,CsxlService.columns,"jsgm")))));//建设规模
							csxl.setXllx(getValue(cell(row,CsxlService.columns,"xllx")));
							csxl.setXlqd(getValue(cell(row,CsxlService.columns,"xlqd")));
							csxl.setXlxz(getValue(cell(row,CsxlService.columns,"xlxz")));
							csxl.setXlzd(getValue(cell(row,CsxlService.columns,"xlzd")));
							jbxx.setFj(getValue(cell(row,CsxlService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,CsxlService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(csxl);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Csxl");
					}
				
				}else if("供电系统".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddGdxt gdxt = new  AddGdxt();
							String id;
							String xmbh;
							if(getValue(cell(row,GdxtService.columns,"id"))==null || getValue(cell(row,GdxtService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,GdxtService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,GdxtService.columns,"xmbh"))==null || getValue(cell(row,GdxtService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,GdxtService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							gdxt.setId(id);
							gdxt.setJkz_id(getValue(cell(row,GdxtService.columns,"jkz_id")));
							gdxt.setJkz_name(getValue(cell(row,GdxtService.columns,"jkz_name")));
							gdxt.setJsdd(getValue(cell(row,GdxtService.columns,"jsdd")));//建设地点
							gdxt.setJsgm(StringUtils.getIntVal(getValue(cell(row,GdxtService.columns,"jsgm"))));//建设规模
							jbxx.setFj(getValue(cell(row,GdxtService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,GdxtService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(gdxt);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Gdxt");
					}
					
				}else if("报警装置".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddBjzz bjzz = new AddBjzz();
							String id;
							String xmbh;
							if(getValue(cell(row,BjzzService.columns,"id"))==null || getValue(cell(row,BjzzService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,BjzzService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,BjzzService.columns,"xmbh"))==null || getValue(cell(row,BjzzService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,BjzzService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							bjzz.setId(id);
							bjzz.setJkz_id(getValue(cell(row,BjzzService.columns,"jkz_id")));
							bjzz.setJkz_name(getValue(cell(row,BjzzService.columns,"jkz_name")));
							bjzz.setSbpp(getValue(cell(row,BjzzService.columns,"sbpp")));
							bjzz.setSbxh(getValue(cell(row,BjzzService.columns,"sbxh")));
							bjzz.setJsgm(StringUtils.getIntVal(getValue(cell(row,BjzzService.columns,"jsgm"))));//建设规模
							jbxx.setFj(getValue(cell(row,BjzzService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,BjzzService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(bjzz);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Bjzz");
					}
				}else if("无人值守电子哨兵".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddWrzsdzsb wrzsdzsb = new AddWrzsdzsb();
							String id;
							String xmbh;
							if(getValue(cell(row,WrzsdzsbService.columns,"id"))==null || getValue(cell(row,WrzsdzsbService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,WrzsdzsbService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,WrzsdzsbService.columns,"xmbh"))==null || getValue(cell(row,WrzsdzsbService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,WrzsdzsbService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							wrzsdzsb.setId(id);
							wrzsdzsb.setFzdd(getValue(cell(row,WrzsdzsbService.columns,"fzdd")));
							wrzsdzsb.setJsgm(StringUtils.getIntVal(getValue(cell(row,WrzsdzsbService.columns,"jsgm"))));//建设规模
							jbxx.setFj(getValue(cell(row,WrzsdzsbService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,WrzsdzsbService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(wrzsdzsb);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Wrzsdzsb");
					}
				}else if("军警民联防平台".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddJjmlfpt jjmlfpt = new AddJjmlfpt();
							String id;
							String xmbh;
							if(getValue(cell(row,JjmlfptService.columns,"id"))==null || getValue(cell(row,JjmlfptService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,JjmlfptService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,JjmlfptService.columns,"xmbh"))==null || getValue(cell(row,JjmlfptService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,JjmlfptService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							jjmlfpt.setId(id);
							jjmlfpt.setFzdd(getValue(cell(row,JjmlfptService.columns,"fzdd")));
							jjmlfpt.setHxcsxl(getValue(cell(row,JjmlfptService.columns,"hxcsxl")));
							jjmlfpt.setHxltqk(getValue(cell(row,JjmlfptService.columns,"hxltqk")));
							jjmlfpt.setHxltwlxz(getValue(cell(row,JjmlfptService.columns,"hxltwlxz")));
							jjmlfpt.setJb(getValue(cell(row,JjmlfptService.columns,"jb")));
							jjmlfpt.setXscsxl(getValue(cell(row,JjmlfptService.columns,"xscsxl")));
							jjmlfpt.setXsltqk(getValue(cell(row,JjmlfptService.columns,"xsltqk")));
							jjmlfpt.setXsltwlxz(getValue(cell(row,JjmlfptService.columns,"xsltwlxz")));
							jbxx.setFj(getValue(cell(row,JjmlfptService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,JjmlfptService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(jjmlfpt);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Jjmlfpt");
					}
					
				}else if("执勤房".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddZqf zqf = new AddZqf();
							String id;
							String xmbh;
							if(getValue(cell(row,ZqfService.columns,"id"))==null || getValue(cell(row,ZqfService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,ZqfService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,ZqfService.columns,"xmbh"))==null || getValue(cell(row,ZqfService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,ZqfService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							zqf.setId(id);
							zqf.setJsdd(getValue(cell(row,ZqfService.columns,"jsdd")));//建设地点
							zqf.setJsgm(StringUtils.getIntVal(getValue(cell(row,ZqfService.columns,"jsgm"))));//建设规模
							zqf.setZqflx(getValue(cell(row,ZqfService.columns,"zqflx")));
							jbxx.setFj(getValue(cell(row,ZqfService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,ZqfService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(zqf);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Zqf");
					}
					
				}else if("了望塔".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddLwt lwt = new AddLwt();
							String id;
							String xmbh;
							if(getValue(cell(row,LwtService.columns,"id"))==null || getValue(cell(row,LwtService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,LwtService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,LwtService.columns,"xmbh"))==null || getValue(cell(row,LwtService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,LwtService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							lwt.setId(id);
							lwt.setJsdd(getValue(cell(row,LwtService.columns,"jsdd")));//建设地点
							lwt.setJsgm(StringUtils.getIntVal(getValue(cell(row,LwtService.columns,"jsgm"))));//建设规模
							lwt.setLwtlx(getValue(cell(row,LwtService.columns,"lwtlx")));
							jbxx.setFj(getValue(cell(row,LwtService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,LwtService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(lwt);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Lwt");
					}
				}else if("标志牌".equals(sheetName)){
					List<List<Object>> data = new ArrayList<List<Object>>();//当前sheet页读取到的数据
					ArrayList<String> idArr = new ArrayList<String>();
					StringBuffer errorRow = new StringBuffer();
					for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
						row = sheet.getRow(k);
						if(validate(sheetName,row, partTag)){
							List<Object> list = new ArrayList<Object>();
							AddJbxx jbxx = new AddJbxx();
							AddBzp bzp = new AddBzp();
							String id;
							String xmbh;
							if(getValue(cell(row,BzpService.columns,"id"))==null || getValue(cell(row,BzpService.columns,"id")).equals("")){
								id = CommonUtil.createUUID();
								jbxx.setId(id);
							}else{
								id = getValue(cell(row,BzpService.columns,"id"));
								jbxx.setId(id);
							}
							if(getValue(cell(row,BzpService.columns,"xmbh"))==null || getValue(cell(row,BzpService.columns,"xmbh")).equals("")){
							    xmbh = CommonUtil.createUUID();
							    jbxx.setXmbh(xmbh);
							}else{
							    xmbh = getValue(cell(row,BzpService.columns,"xmbh"));
							    jbxx.setXmbh(xmbh);
							}
							idArr.add(id);
							setValue(jbxx,row,partTag);
							bzp.setId(id);
							bzp.setJsdd(getValue(cell(row,BzpService.columns,"jsdd")));//建设地点
							bzp.setJsgm(StringUtils.getIntVal(getValue(cell(row,BzpService.columns,"jsgm"))));//建设规模
							bzp.setBzplx(getValue(cell(row,BzpService.columns,"bzplx")));
							jbxx.setFj(getValue(cell(row,BzpService.columns,"fj")));//附件
							jbxx.setBz(getValue(cell(row,BzpService.columns,"bz")));//备注
							
							list.add(jbxx);
							list.add(bzp);
							data.add(list);
						}else{
							errorRow.append(k+1+",");//错误数据行号
						}
						if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
							String errorRows = errorRow.toString();
							errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
						}
					}
					if(idArr!=null && idArr.size()>0){
						insertOrUpdateForXmzl(idArr,data,saveList,updateList,"Bzp");
					}
				}else{
					return "请上传项目数据文件";
				}
			}
		}
		//执行插入或更新操作
		jbxxService.batchSave(saveList);
		jbxxService.batchUpdate(updateList);
		
		//返回导入结果
		if(SortUtils.ergodicMap(errorTip)!=null && !SortUtils.ergodicMap(errorTip).trim().equals("")){
            return "部分数据导入成功，错误数据列表如下：</br>"+SortUtils.ergodicMap(errorTip);
        }else{
            return null;
        }
	}
	
	/**
	 * 对象属性赋值
	 * @param jbxx
	 * @param row
	 * @param partTag 
	 */
	private void setValue(AddJbxx jbxx,Row row, int partTag){
		jbxx.setXmlb(getValue(cell(row,ZqdlService.columns,"xmlb")));//项目类别
		jbxx.setXmzl(getValue(cell(row,ZqdlService.columns,"xmzl")));//项目子类
		jbxx.setXmmc(getValue(cell(row,ZqdlService.columns,"xmmc")));//项目名称
		jbxx.setBjfx(getValue(cell(row,ZqdlService.columns,"bjfx")));//边界方向
		jbxx.setSslx(getValue(cell(row,ZqdlService.columns,"sslx")));//设施类型
		jbxx.setSzsf(getValue(cell(row,ZqdlService.columns,"szsf")));//建设区域--所在省份
		jbxx.setSzcs(getValue(cell(row,ZqdlService.columns,"szcs")));//建设区域--所在城市
		jbxx.setSzq(getValue(cell(row,ZqdlService.columns,"szq")));//建设区域--所在区
		jbxx.setJd(getValue(cell(row,ZqdlService.columns,"jd")));//经度
		jbxx.setWd(getValue(cell(row,ZqdlService.columns,"wd")));//纬度
		jbxx.setDxlb(getValue(cell(row,ZqdlService.columns,"dxlb")));//地形类别
		jbxx.setJsxz(getValue(cell(row,ZqdlService.columns,"jsxz")));//建设性质
		jbxx.setSydw(getValue(cell(row,ZqdlService.columns,"sydw")));//使用单位
		jbxx.setSbdw(getValue(cell(row,ZqdlService.columns,"sbdw")));//申报单位
		jbxx.setZytz(Double.valueOf(getValue(cell(row,ZqdlService.columns,"zytz"))));//中央投资
		jbxx.setDftz(Double.valueOf(getValue(cell(row,ZqdlService.columns,"dftz"))));//地方投资
        String tznd = getValue(cell(row,ZqdlService.columns,"tznd"));//投资年度
        if(tznd!=null && !tznd.equals("") && tznd.indexOf(".")>-1){
        	jbxx.setTznd(tznd.substring(0,tznd.lastIndexOf(".")));
        }else{
        	jbxx.setTznd(tznd);
        }
        if(partTag ==1 ||partTag ==2){
        	jbxx.setBxxm("是");
        }else if(partTag==3 || partTag==4){
        	jbxx.setBxxm("否");
        	String xmzl=getValue(cell(row,ZqdlService.columns,"xmzl"));
        	int startIndex= getStartIndex(xmzl);
			importOtherColunmnsByCell(jbxx, row, startIndex, partTag);
		}
        jbxx.setTime(new Date());//数据新增或修改时间
	}
	
	/**
	 * 各个子项目导入excel表格所不同的列(主要是根据子项目导入列的序号导入从建设状态后面的字段)
	 */
	private void  importOtherColunmnsByCell(AddJbxx jbxx, Row row,int i, int partTag){
		jbxx.setJszt(getValue(row.getCell(i)));
		jbxx.setCjdw(getValue(row.getCell(i+1)));
		jbxx.setJldw(getValue(row.getCell(i+2)));
		if(!"".equals(getValue(row.getCell(i+3)))){
		    jbxx.setZtbsj(DateUtils.convertStrToDate(getValue(row.getCell(i+3)), "yyyy-MM-dd"));
		}
		if(!"".equals(getValue(row.getCell(i+4)))){
		    jbxx.setKgsj(DateUtils.convertStrToDate(getValue(row.getCell(i+4)), "yyyy-MM-dd"));
		}
		if(!"".equals(getValue(row.getCell(i+5)))){
		    jbxx.setCysj(DateUtils.convertStrToDate(getValue(row.getCell(i+5)), "yyyy-MM-dd"));
		}
		if(!"".equals(getValue(row.getCell(i+6)))){
		    jbxx.setJgsj(DateUtils.convertStrToDate(getValue(row.getCell(i+6)), "yyyy-MM-dd"));
		}
		if(!"".equals(getValue(row.getCell(i+7)))){
		    jbxx.setSjsj(DateUtils.convertStrToDate(getValue(row.getCell(i+7)), "yyyy-MM-dd"));
		}
		if(partTag == 4){
		    jbxx.setSyzt(getValue(row.getCell(i+8)));
		}
	}
	
	/**
	 * 获取单元格数据
	 * @param cell
	 * @return
	 */
    @SuppressWarnings("deprecation")
    private String getValue(Cell cell){
		if (cell == null) {
            return "";
        } else {
            if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                if (DateUtil.isCellDateFormatted(cell)) {
                    final SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
                    return sdf.format(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            } else {
                return String.valueOf(cell.getStringCellValue()).trim().replaceAll("\r", "").replaceAll("\n", "")
                        .replaceAll("'", "’");
            }
        }
	}
    /**
     * 获取有特殊字符单元格的数据
     * @param cell
     * @return
     */
    private String getSpecialValue(Cell cell) {
        if (cell == null) {
            return "";
        } else {
            return String.valueOf(cell.getStringCellValue()).trim();
        }
    }
	
	/**
	 * 获取单元格对象
	 * @param row
	 * @param arr
	 * @param field
	 * @return
	 */
	private Cell cell(Row row,String[] arr,String field){
		int index = CommonUtil.getIndexOfArr(arr,field);
		if(index!=-1){
			return row.getCell(index);
		}
		return null;
	}
	
	/**
	 * 获取实施监督、使用维护模块数据导入时“建设状态”字段索引
	 * @param xmzl
	 * @return
	 */
	private static int getStartIndex(String xmzl){
		int startIndex = 0;
		if (xmzl.equals("执勤道路")) {
            startIndex = ZqdlService.header.length;
        } else if (xmzl.equals("桥梁")) {
            startIndex = QiaoLiangService.header.length;
        } else if (xmzl.equals("执勤码头")) {
            startIndex = ZqmtService.header.length;
        } else if (xmzl.equals("直升机停机坪")) {
            startIndex = ZsjtjpService.header.length;
        } else if (xmzl.equals("铁丝网")) {
            startIndex = TswService.header.length;
        } else if (xmzl.equals("铁栅栏")) {
            startIndex = TzlService.header.length;
        } else if (xmzl.equals("拦阻桩")) {
            startIndex = LzzService.header.length;
        } else if (xmzl.equals("隔离带")) {
            startIndex = GldService.header.length;
        } else if (xmzl.equals("拒马")) {
            startIndex = JumaService.header.length;
        } else if (xmzl.equals("报警线路")) {
            startIndex = BjxlService.header.length;
        } else if (xmzl.equals("监控中心")) {
            startIndex = JkzxService.header.length;
        } else if (xmzl.equals("监控站")) {
            startIndex = JkzService.header.length;
        } else if (xmzl.equals("视频前端")) {
            startIndex = SpqdService.header.length;
        } else if (xmzl.equals("显控终端")) {
            startIndex = XkzdService.header.length;
        } else if (xmzl.equals("传输线路")) {
            startIndex = CsxlService.header.length;
        } else if (xmzl.equals("供电系统")) {
            startIndex = GdxtService.header.length;
        } else if (xmzl.equals("报警装置")) {
            startIndex = BjzzService.header.length;
        } else if (xmzl.equals("无人值守电子哨兵")) {
            startIndex = WrzsdzsbService.header.length;
        } else if (xmzl.equals("军警民联防平台")) {
            startIndex = JjmlfptService.header.length;
        } else if (xmzl.equals("执勤房")) {
            startIndex = ZqfService.header.length;
        } else if (xmzl.equals("了望塔")) {
            startIndex = LwtService.header.length;
        } else if (xmzl.equals("标志牌")) {
            startIndex = BzpService.header.length;
        }
		return startIndex;
	}
	
	/**
	 * Excel表格数据校验
	 * @param sheetName
	 * @param row
	 * @return
	 */
	private boolean validate(String sheetName,Row row, int partTag){
		boolean checkJbxx = true;
		boolean checkSubClass = true;
		//验证基本信息(子类service中columns数组中部分为jbxx的字段，已“执勤道路”为例)
		
		if(getValue(cell(row,ZqdlService.columns,"xmlb"))==null||getValue(cell(row,ZqdlService.columns,"xmlb")).trim().equals("")){
			checkJbxx = false;
		}
		if(getValue(cell(row,ZqdlService.columns,"xmzl"))==null||getValue(cell(row,ZqdlService.columns,"xmzl")).trim().equals("")){
			checkJbxx = false;
		}
		if(getValue(cell(row,ZqdlService.columns,"xmmc"))==null||getValue(cell(row,ZqdlService.columns,"xmmc")).trim().equals("")){
			checkJbxx = false;
		}
		if(getValue(cell(row,ZqdlService.columns,"bjfx"))==null||getValue(cell(row,ZqdlService.columns,"bjfx")).trim().equals("")){
			checkJbxx = false;
		}
		if(getValue(cell(row,ZqdlService.columns,"sslx"))==null||getValue(cell(row,ZqdlService.columns,"sslx")).trim().equals("")){
			checkJbxx = false;
		}
		if(getValue(cell(row,ZqdlService.columns,"szsf"))==null||getValue(cell(row,ZqdlService.columns,"szsf")).trim().equals("")){
			checkJbxx = false;
		}
		if(getValue(cell(row,ZqdlService.columns,"dxlb"))==null||getValue(cell(row,ZqdlService.columns,"dxlb")).trim().equals("")){
			checkJbxx = false;
		}
		if(getValue(cell(row,ZqdlService.columns,"jsxz"))==null||getValue(cell(row,ZqdlService.columns,"jsxz")).trim().equals("")){
			checkJbxx = false;
		}
		if(getValue(cell(row,ZqdlService.columns,"sydw"))==null||getValue(cell(row,ZqdlService.columns,"sydw")).trim().equals("")){
			checkJbxx = false;
		}
		if(!StringUtils.isNum(getValue(cell(row,ZqdlService.columns,"zytz")))){
			checkJbxx = false;
		}
		if(!StringUtils.isNum(getValue(cell(row,ZqdlService.columns,"dftz")))){
			checkJbxx = false;
		}
		int startIndex = 0;
		if(partTag == 3 || partTag == 4){
            String xmzl = getValue(cell(row, ZqdlService.columns, "xmzl"));
            startIndex = getStartIndex(xmzl);
		    if(getValue(row.getCell(startIndex))==null || getValue(row.getCell(startIndex)).trim().equals("")){//建设状态
		        checkJbxx = false;
		    }
		    if(getValue(row.getCell(startIndex+3))!=null && !getValue(row.getCell(startIndex+3)).trim().equals("") && !StringUtils.isDate(getValue(row.getCell(startIndex+3)))){//招投标时间
		        checkJbxx = false;
		    }
		    if(getValue(row.getCell(startIndex+4))!=null && !getValue(row.getCell(startIndex+4)).trim().equals("") && !StringUtils.isDate(getValue(row.getCell(startIndex+4)))){//开工时间
		        checkJbxx = false;
		    }
		    if(getValue(row.getCell(startIndex+5))!=null && !getValue(row.getCell(startIndex+5)).trim().equals("") && !StringUtils.isDate(getValue(row.getCell(startIndex+5)))){//初验时间
		        checkJbxx = false;
		    }
		    if(getValue(row.getCell(startIndex+6))!=null && !getValue(row.getCell(startIndex+6)).trim().equals("") && !StringUtils.isDate(getValue(row.getCell(startIndex+6)))){//竣工时间
		        checkJbxx = false;
		    }
		    if(getValue(row.getCell(startIndex+7))!=null && !getValue(row.getCell(startIndex+7)).trim().equals("") && !StringUtils.isDate(getValue(row.getCell(startIndex+7)))){//审计时间
		        checkJbxx = false;
		    }
		}
		if(partTag == 4){
		    if(getValue(row.getCell(startIndex+8))==null || getValue(row.getCell(startIndex+8)).trim().equals("")){//使用状态
                checkJbxx = false;
            }
		}

		if ("执勤道路".equals(sheetName)){
			if(getValue(cell(row,ZqdlService.columns,"jsdd"))==null||getValue(cell(row,ZqdlService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,ZqdlService.columns,"jsgm")))){
				checkSubClass = false;
			}
			if(getValue(cell(row,ZqdlService.columns,"dllb"))==null||getValue(cell(row,ZqdlService.columns,"dllb")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,ZqdlService.columns,"dllx"))==null||getValue(cell(row,ZqdlService.columns,"dllx")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,ZqdlService.columns,"dldj"))==null||getValue(cell(row,ZqdlService.columns,"dldj")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,ZqdlService.columns,"ljlx"))==null||getValue(cell(row,ZqdlService.columns,"ljlx")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,ZqdlService.columns,"lmlx"))==null||getValue(cell(row,ZqdlService.columns,"lmlx")).trim().equals("")){
				checkSubClass = false;
			}
		}

		if("桥梁".equals(sheetName)){
			if(getValue(cell(row,QiaoLiangService.columns,"jsdd"))==null||getValue(cell(row,QiaoLiangService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,QiaoLiangService.columns,"jsgm")))){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,QiaoLiangService.columns,"zz")))){
				checkSubClass = false;
			}
		}

		if("执勤码头".equals(sheetName)){
			if(getValue(cell(row,ZqmtService.columns,"jsdd"))==null||getValue(cell(row,ZqmtService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,ZqmtService.columns,"jsgm")))){
				checkSubClass = false;
			}
		}

		if("直升机停机坪".equals(sheetName)){
			if(getValue(cell(row,ZsjtjpService.columns,"jsdd"))==null||getValue(cell(row,ZsjtjpService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,ZsjtjpService.columns,"jsgm")))){
				checkSubClass = false;
			}
			if(getValue(cell(row,ZsjtjpService.columns,"tjplx"))==null||getValue(cell(row,ZsjtjpService.columns,"tjplx")).trim().equals("")){
				checkSubClass = false;
			}
		}

		if("铁丝网".equals(sheetName)){
			if(getValue(cell(row,TswService.columns,"jsdd"))==null||getValue(cell(row,TswService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,TswService.columns,"jsgm")))){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,TswService.columns,"lzmgs")))){
				checkSubClass = false;
			}
		}

		if("铁栅栏".equals(sheetName)){
			if(getValue(cell(row,TzlService.columns,"jsdd"))==null||getValue(cell(row,TzlService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,TzlService.columns,"jsgm")))){
				checkSubClass = false;
			}
		}
		if("拦阻桩".equals(sheetName)){
			if(getValue(cell(row,LzzService.columns,"jsdd"))==null||getValue(cell(row,LzzService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,LzzService.columns,"jsgm")))){
				checkSubClass = false;
			}
		}
		if("隔离带".equals(sheetName)){
			if(getValue(cell(row,GldService.columns,"jsdd"))==null||getValue(cell(row,GldService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,GldService.columns,"jsgm")))){
				checkSubClass = false;
			}
		}
		if("拒马".equals(sheetName)){
			if(getValue(cell(row,JumaService.columns,"jsdd"))==null||getValue(cell(row,JumaService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,JumaService.columns,"jsgm")))){
				checkSubClass = false;
			}
		}
		if("报警线路".equals(sheetName)){
			if(getValue(cell(row,BjxlService.columns,"jsdd"))==null||getValue(cell(row,BjxlService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,BjxlService.columns,"jsgm")))){
				checkSubClass = false;
			}
		}
		if("监控中心".equals(sheetName)){
			if(getValue(cell(row,JkzxService.columns,"xsltqk"))==null||getValue(cell(row,JkzxService.columns,"xsltqk")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,JkzxService.columns,"xsltwlxz"))==null||getValue(cell(row,JkzxService.columns,"xsltwlxz")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,JkzxService.columns,"xscsxl"))==null||getValue(cell(row,JkzxService.columns,"xscsxl")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,JkzxService.columns,"jb"))==null||getValue(cell(row,JkzxService.columns,"jb")).trim().equals("")){
				checkSubClass = false;
			}
		}
		if("监控站".equals(sheetName)){
			if(getValue(cell(row,JkzService.columns,"xsltqk"))==null||getValue(cell(row,JkzService.columns,"xsltqk")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,JkzService.columns,"xsltwlxz"))==null||getValue(cell(row,JkzService.columns,"xsltwlxz")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,JkzService.columns,"xscsxl"))==null||getValue(cell(row,JkzService.columns,"xscsxl")).trim().equals("")){
				checkSubClass = false;
			}
		}
		if("视频前端".equals(sheetName)){
		    if(getValue(cell(row,SpqdService.columns,"jkz_id"))==null||getValue(cell(row,SpqdService.columns,"jkz_id")).trim().equals("")){
		        checkSubClass = false;
		    }
			if(getValue(cell(row,SpqdService.columns,"jkz_name"))==null||getValue(cell(row,SpqdService.columns,"jkz_name")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,SpqdService.columns,"fzdd"))==null||getValue(cell(row,SpqdService.columns,"fzdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,SpqdService.columns,"sblx"))==null||getValue(cell(row,SpqdService.columns,"sblx")).trim().equals("")){
				checkSubClass = false;
			}
		}
		if("显控终端".equals(sheetName)){
		    if(getValue(cell(row,XkzdService.columns,"jkz_id"))==null||getValue(cell(row,XkzdService.columns,"jkz_id")).trim().equals("")){
		        checkSubClass = false;
		    }
			if(getValue(cell(row,XkzdService.columns,"jkz_name"))==null||getValue(cell(row,XkzdService.columns,"jkz_name")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,XkzdService.columns,"fzdd"))==null||getValue(cell(row,XkzdService.columns,"fzdd")).trim().equals("")){
				checkSubClass = false;
			}
		}
		if("传输线路".equals(sheetName)){
		    if(getValue(cell(row,CsxlService.columns,"jkz_id"))==null||getValue(cell(row,CsxlService.columns,"jkz_id")).trim().equals("")){
		        checkSubClass = false;
		    }
			if(getValue(cell(row,CsxlService.columns,"jkz_name"))==null||getValue(cell(row,CsxlService.columns,"jkz_name")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,CsxlService.columns,"xlqd"))==null||getValue(cell(row,CsxlService.columns,"xlqd")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,CsxlService.columns,"xlzd"))==null||getValue(cell(row,CsxlService.columns,"xlzd")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,CsxlService.columns,"jsdd"))==null||getValue(cell(row,CsxlService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,CsxlService.columns,"jsgm")))){
				checkSubClass = false;
			}
		}
		if("供电系统".equals(sheetName)){
		    if(getValue(cell(row,GdxtService.columns,"jkz_id"))==null||getValue(cell(row,GdxtService.columns,"jkz_id")).trim().equals("")){
		        checkSubClass = false;
		    }
			if(getValue(cell(row,GdxtService.columns,"jkz_name"))==null||getValue(cell(row,GdxtService.columns,"jkz_name")).trim().equals("")){
				checkSubClass = false;
			}
			if(getValue(cell(row,GdxtService.columns,"jsdd"))==null||getValue(cell(row,GdxtService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,GdxtService.columns,"jsgm")))){
				checkSubClass = false;
			}
		}
		if("报警装置".equals(sheetName)){
		    if(getValue(cell(row,BjzzService.columns,"jkz_id"))==null||getValue(cell(row,BjzzService.columns,"jkz_id")).trim().equals("")){
		        checkSubClass = false;
		    }
			if(getValue(cell(row,BjzzService.columns,"jkz_name"))==null||getValue(cell(row,BjzzService.columns,"jkz_name")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,BjzzService.columns,"jsgm")))){
				checkSubClass = false;
			}
		}
		if("无人值守电子哨兵".equals(sheetName)){
			if(getValue(cell(row,WrzsdzsbService.columns,"fzdd"))==null||getValue(cell(row,WrzsdzsbService.columns,"fzdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,WrzsdzsbService.columns,"jsgm")))){
				checkSubClass = false;
			}
		}
		if("军警民联防平台".equals(sheetName)){
			if(getValue(cell(row,JjmlfptService.columns,"fzdd"))==null||getValue(cell(row,JjmlfptService.columns,"fzdd")).trim().equals("")){
				checkSubClass = false;
			}
		}
		if("执勤房".equals(sheetName)){
			if(getValue(cell(row,ZqfService.columns,"jsdd"))==null||getValue(cell(row,ZqfService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,ZqfService.columns,"jsgm")))){
				checkSubClass = false;
			}
			if(getValue(cell(row,ZqfService.columns,"zqflx"))==null||getValue(cell(row,ZqfService.columns,"zqflx")).trim().equals("")){
				checkSubClass = false;
			}
		}
		if("了望塔".equals(sheetName)){
			if(getValue(cell(row,LwtService.columns,"jsdd"))==null||getValue(cell(row,LwtService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,LwtService.columns,"jsgm")))){
				checkSubClass = false;
			}
			if(getValue(cell(row,LwtService.columns,"lwtlx"))==null||getValue(cell(row,LwtService.columns,"lwtlx")).trim().equals("")){
				checkSubClass = false;
			}
		}
		if("标志牌".equals(sheetName)){
			if(getValue(cell(row,BzpService.columns,"jsdd"))==null||getValue(cell(row,BzpService.columns,"jsdd")).trim().equals("")){
				checkSubClass = false;
			}
			if(!StringUtils.isNum(getValue(cell(row,BzpService.columns,"jsgm")))){
				checkSubClass = false;
			}
			if(getValue(cell(row,BzpService.columns,"bzplx"))==null||getValue(cell(row,BzpService.columns,"bzplx")).trim().equals("")){
				checkSubClass = false;
			}
		}
		return checkJbxx && checkSubClass;
	}
	
	/**
     * Excel文件上传(项目维护记录)
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/importWhjl",method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String importWhjl(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file) throws Exception{
        String uploadPath = request.getSession().getServletContext().getRealPath("upload/data");
        String fileName = file.getOriginalFilename();
        String extension = fileName.indexOf(".")==-1?"":fileName.substring(fileName.lastIndexOf(".")+1);
        String msg = null;
        if("xls".equals(extension) || "xlsx".equals(extension)){
            String newFileName = UUID.randomUUID()+fileName;
            File toFile = new File(uploadPath,newFileName);
            if(!toFile.exists()){
                toFile.mkdirs();
            }
            file.transferTo(toFile);
            msg = readWhjlExcel(toFile,extension);
            if(msg==null){
                msg = "导入成功";
            }
        }else{
            msg = "文件类型不符合，请上传Excel文件";
        }
        return msg;
    }
    
    /**
     * Excel表格数据校验
     * @param sheetName
     * @param row
     * @return
     */
    private boolean validateWhjl(String sheetName,Row row){
        boolean validateResult = true;
        
        if(getValue(cell(row,XmwhjlService.columns,"wxxmid"))==null||getValue(cell(row,XmwhjlService.columns,"wxxmid")).trim().equals("")){
            validateResult = false;
        }
        if(getValue(cell(row,XmwhjlService.columns,"wxxmbh"))==null||getValue(cell(row,XmwhjlService.columns,"wxxmbh")).trim().equals("")){
            validateResult = false;
        }
        if(getValue(cell(row,XmwhjlService.columns,"wxxmmc"))==null||getValue(cell(row,XmwhjlService.columns,"wxxmmc")).trim().equals("")){
            validateResult = false;
        }
        if(getValue(cell(row,XmwhjlService.columns,"wxgm"))==null||getValue(cell(row,XmwhjlService.columns,"wxgm")).trim().equals("")){
            validateResult = false;
        }
        if(getValue(cell(row,XmwhjlService.columns,"whxmid"))==null||getValue(cell(row,XmwhjlService.columns,"whxmid")).trim().equals("")){
            validateResult = false;
        }
        if(getValue(cell(row,XmwhjlService.columns,"id"))==null||getValue(cell(row,XmwhjlService.columns,"id")).trim().equals("")){
            validateResult = false;
        }
        if(!StringUtils.isNum(getValue(cell(row,XmwhjlService.columns,"wxfy")))){
            validateResult = false;
        }
        if(getValue(cell(row,XmwhjlService.columns,"gzsj"))!=null && !getValue(cell(row,XmwhjlService.columns,"gzsj")).trim().equals("") && !StringUtils.isDate(getValue(cell(row,XmwhjlService.columns,"gzsj")))){
            validateResult = false;
        }
        if(getValue(cell(row,XmwhjlService.columns,"wxsj"))!=null && !getValue(cell(row,XmwhjlService.columns,"wxsj")).trim().equals("") && !StringUtils.isDate(getValue(cell(row,XmwhjlService.columns,"wxsj")))){
            validateResult = false;
        }
        return validateResult;
    }
    
    /**
     * 读取Excel文件，返回数据导入结果
     * @param file
     * @param extension
     * @return
     * @throws Exception
     */
    private String readWhjlExcel(File file,String extension) throws Exception{
        List<Object> list = new ArrayList<Object>();
        Workbook wb = null;
        if("xls".equals(extension)){
            try {
                wb=new HSSFWorkbook(new FileInputStream(file));
            } catch (Exception e) {
                String msg = "文件格式有误,请将文件另存为格式正确的Excel文件后上传";
                return msg;
            }
        }else if("xlsx".equals(extension)){
            try {
                wb = new XSSFWorkbook(new FileInputStream(file));
            } catch (Exception e) {
                String msg = "文件格式有误,请将文件另存为格式正确的Excel文件后上传";
                return msg;
            }
        }
        Map<String,String> errorTip = new HashMap<String,String>();
        int sheetCount = wb.getNumberOfSheets();
        int count = 0; //文件中数据总行数
        for (int x = 0; x < sheetCount; x++) {
            Sheet sheet = wb.getSheetAt(x);
            count += sheet.getLastRowNum();
        }
        ArrayList<String> idArr = new ArrayList<String>();
        if(count > 20000){
             String msg = "导入失败，因为：文件中有"+count+
                "条数据，为保证上传效率最大化和系统正常运行，系统单次支持的最大条数为20000条，您本次上传数据过多，请减少单次上传数据数量，并分批次上传";
             return msg;
        }else{
            for(int i=0;i<sheetCount;i++){
                Sheet sheet = wb.getSheetAt(i);
                Row row = null;
                String sheetName = sheet.getSheetName();
                
                StringBuffer errorRow = new StringBuffer();
                for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
                    row = sheet.getRow(k);
                    if(validateWhjl(sheetName,row)){
                        idArr.add(getValue(cell(row,XmwhjlService.columns,"id")));
                        Xmwhjl xmwhjl = new Xmwhjl();
                        xmwhjl.setId(getValue(cell(row,XmwhjlService.columns,"id")));
                        xmwhjl.setSyzt("损坏");
                        xmwhjl.setWxxmid(getValue(cell(row,XmwhjlService.columns,"wxxmid")));
                        xmwhjl.setWxxmbh(getValue(cell(row,XmwhjlService.columns,"wxxmbh")));
                        xmwhjl.setWxxmmc(getValue(cell(row,XmwhjlService.columns,"wxxmmc")));
                        xmwhjl.setWxgm(getSpecialValue(cell(row,XmwhjlService.columns,"wxgm")));
                        xmwhjl.setWxfy(new BigDecimal(getValue(cell(row,XmwhjlService.columns,"wxfy"))));
                        xmwhjl.setGzsb(getSpecialValue(cell(row,XmwhjlService.columns,"gzsb")));
                        xmwhjl.setGzsbpp(getSpecialValue(cell(row,XmwhjlService.columns,"gzsbpp")));
                        xmwhjl.setGzsbxh(getSpecialValue(cell(row,XmwhjlService.columns,"gzsbxh")));
                        xmwhjl.setGzbj(getSpecialValue(cell(row,XmwhjlService.columns,"gzbj")));
                        xmwhjl.setShyy(getSpecialValue(cell(row,XmwhjlService.columns,"shyy")));
                        xmwhjl.setWhzt(getSpecialValue(cell(row,XmwhjlService.columns,"whzt")));
                        xmwhjl.setWhxmid(getValue(cell(row,XmwhjlService.columns,"whxmid")));
                        xmwhjl.setWhjlzt(getValue(cell(row,XmwhjlService.columns,"whjlzt")) == "" ? null : getValue(cell(row,XmwhjlService.columns,"whjlzt")));
                        String gzsj = getValue(cell(row,XmwhjlService.columns,"gzsj"));
                        if(gzsj ==null || gzsj.trim() == ""){
                            xmwhjl.setGzsj(null);
                        }else{
                            xmwhjl.setGzsj(DateUtils.convertStrToDate(gzsj, "yyyy-MM-dd"));
                        }
                        String wxsj = getValue(cell(row,XmwhjlService.columns,"wxsj"));
                        if(wxsj ==null || wxsj.trim() == ""){
                            xmwhjl.setWxsj(null);
                        }else{
                            xmwhjl.setWxsj(DateUtils.convertStrToDate(wxsj, "yyyy-MM-dd"));
                        }
                        list.add(xmwhjl);
                    }else{
                        errorRow.append(k+1+",");//错误数据行号
                    }
                    if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
                        String errorRows = errorRow.toString();
                        errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
                    }
                }
            }
            if(list.size()>0 && idArr.size()>0){
                insertOrUpdateWhjl(list,idArr);
            }
            if(SortUtils.ergodicMap(errorTip)!=null && !SortUtils.ergodicMap(errorTip).trim().equals("")){
                return "部分数据导入成功，错误数据列表如下：</br>"+SortUtils.ergodicMap(errorTip);
            }else{
                return null;
            }
        }
    }
    
    /**
     * 执行数据插入或更新
     * @param list
     * @param xmbhArr
     */
    private void insertOrUpdateWhjl(List<Object> list,List<String> idArr){
        List<Object> saveList = new ArrayList<Object>();    //待保存入库的数据
        List<Object> updateList = new ArrayList<Object>();  //待更新入库的数据
        List<Object> existList = new ArrayList<Object>();   //数据库中待更新的数据    
        
        //根据项目编号集合查找数据库数据中待更新的数据，每次500条
        if(idArr.size()>500){
            List<String> newArr = new ArrayList<String>();
            for(int i=0;i<idArr.size();i++){
                newArr.add(idArr.get(i));
                if((i+1)%500==0 || i==idArr.size()-1){
                    existList.addAll(xmwhjlService.getDataByArray(newArr));
                    newArr = new ArrayList<String>(); 
                }
            }
        }else{
            existList.addAll(xmwhjlService.getDataByArray(idArr));
        }
        
        StringBuffer idBuffer = new StringBuffer();
        for (Object obj : existList) {
            if(obj!=null){
                Xmwhjl xmwhjl = (Xmwhjl) obj;
                idBuffer.append(xmwhjl.getId()+" ");
            }
        }
        
        //匹配待更新数据与导入的数据集，区分出待插入数据和待跟新入库的数据
        if(existList.size()==0){
            saveList = list;
        }else{
            for(int x=0;x<list.size();x++){
                Xmwhjl xmwhjl = (Xmwhjl) list.get(x);
                String id = xmwhjl.getId();
                if(idBuffer.toString().indexOf(id)>-1){
                    for (int i = 0; i < existList.size(); i++) {
                        updateList.add(xmwhjl);
                    }
                }else{
                    saveList.add(xmwhjl);
                }
            }
        }
        //执行批量插入或更新操作
        jbxxService.batchSave(saveList);
        jbxxService.batchUpdate(updateList);
    }
    
    /**
     * Excel文件上传(维护项目)
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/importWhxm",method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String importWhxm(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file) throws Exception{
        String uploadPath = request.getSession().getServletContext().getRealPath("upload/data");
        String fileName = file.getOriginalFilename();
        String extension = fileName.indexOf(".")==-1?"":fileName.substring(fileName.lastIndexOf(".")+1);
        String msg = null;
        if("xls".equals(extension) || "xlsx".equals(extension)){
            String newFileName = UUID.randomUUID()+fileName;
            File toFile = new File(uploadPath,newFileName);
            if(!toFile.exists()){
                toFile.mkdirs();
            }
            file.transferTo(toFile);
            msg = readWhxmExcel(toFile,extension);
            if(msg==null){
                msg = "导入成功";
            }
        }else{
            msg = "文件类型不符合，请上传Excel文件";
        }
        return msg;
    }
    
    /**
     * 读取Excel文件，返回数据导入结果
     * @param file
     * @param extension
     * @return
     * @throws Exception
     */
    private String readWhxmExcel(File file,String extension) throws Exception{
        List<Object> list = new ArrayList<Object>();
        Workbook wb = null;
        if("xls".equals(extension)){
            try {
                wb=new HSSFWorkbook(new FileInputStream(file));
            } catch (Exception e) {
                String msg = "文件格式有误,请将文件另存为格式正确的Excel文件后上传";
                return msg;
            }
        }else if("xlsx".equals(extension)){
            try {
                wb = new XSSFWorkbook(new FileInputStream(file));
            } catch (Exception e) {
                String msg = "文件格式有误,请将文件另存为格式正确的Excel文件后上传";
                return msg;
            }
        }
        Map<String,String> errorTip = new HashMap<String,String>();
        int sheetCount = wb.getNumberOfSheets();
        int count = 0; //文件中数据总行数
        for (int x = 0; x < sheetCount; x++) {
            Sheet sheet = wb.getSheetAt(x);
            count += sheet.getLastRowNum();
        }
        ArrayList<String> idArr = new ArrayList<String>();
        if(count > 20000){
             String msg = "导入失败，因为：文件中有"+count+
                "条数据，为保证上传效率最大化和系统正常运行，系统单次支持的最大条数为20000条，您本次上传数据过多，请减少单次上传数据数量，并分批次上传";
             return msg;
        }else{
            for(int i=0;i<sheetCount;i++){
                Sheet sheet = wb.getSheetAt(i);
                Row row = null;
                String sheetName = sheet.getSheetName();
                
                StringBuffer errorRow = new StringBuffer();
                for(int k = sheet.getFirstRowNum()+1; k <= sheet.getLastRowNum(); k++){
                    row = sheet.getRow(k);
                    if(validateWhxm(sheetName,row)){
                        idArr.add(getValue(cell(row,WhxmService.columns,"id")));
                        Whxm whxm = new Whxm();
                        String id = getValue(cell(row,WhxmService.columns,"id"));
                        if(id == null || id.trim() == ""){
                            id = CommonUtil.createUUID();
                        }
                        whxm.setId(id);
                        whxm.setXmbh(getValue(cell(row,WhxmService.columns,"xmbh")));
                        whxm.setXmmc(getValue(cell(row,WhxmService.columns,"xmmc")));
                        whxm.setSzsf(getValue(cell(row,WhxmService.columns,"szsf")));
                        whxm.setWxdw(getValue(cell(row,WhxmService.columns,"wxdw")));
                        whxm.setWxzfy(new BigDecimal(getValue(cell(row,WhxmService.columns,"wxzfy"))));
                        whxm.setWxxmbh(getSpecialValue(cell(row,WhxmService.columns,"wxxmbh")));
                        whxm.setWxxmmc(getSpecialValue(cell(row,WhxmService.columns,"wxxmmc")));
                        whxm.setWxgm(getSpecialValue(cell(row,WhxmService.columns,"wxgm")));
                        whxm.setCreateTime(new Date());
                        list.add(whxm);
                    }else{
                        errorRow.append(k+1+",");//错误数据行号
                    }
                    if(k== sheet.getLastRowNum() && !errorRow.toString().equals("")){
                        String errorRows = errorRow.toString();
                        errorTip.put(sheetName,"第"+errorRows.substring(0, errorRows.length()-1)+"行</br>");
                    }
                }
                
            }
            if(list.size()>0 && idArr.size()>0){
                insertOrUpdateWhxm(list,idArr);
            }
            
            if(SortUtils.ergodicMap(errorTip)!=null && !SortUtils.ergodicMap(errorTip).trim().equals("")){
                return "部分数据导入成功，错误数据列表如下：</br>"+SortUtils.ergodicMap(errorTip);
            }else{
                return null;
            }
        }
    }
    
    /**
     * Excel表格数据校验(维护项目)
     * @param sheetName
     * @param row
     * @return
     */
    private boolean validateWhxm(String sheetName,Row row){
        boolean validateResult = true;
        if(getValue(cell(row,WhxmService.columns,"xmmc"))==null||getValue(cell(row,WhxmService.columns,"xmmc")).trim().equals("")){
            validateResult = false;
        }
        if(getValue(cell(row,WhxmService.columns,"xmbh"))==null||getValue(cell(row,WhxmService.columns,"xmbh")).trim().equals("")){
            validateResult = false;
        }
        if(getValue(cell(row,WhxmService.columns,"wxdw"))==null||getValue(cell(row,WhxmService.columns,"wxdw")).trim().equals("")){
            validateResult = false;
        }
        if(getValue(cell(row,WhxmService.columns,"wxgm"))==null||getValue(cell(row,WhxmService.columns,"wxgm")).trim().equals("")){
            validateResult = false;
        }
        if(!StringUtils.isNum(getValue(cell(row,WhxmService.columns,"wxzfy")))){
            validateResult = false;
        }
        if(getValue(cell(row,WhxmService.columns,"wxxmmc"))==null||getValue(cell(row,WhxmService.columns,"wxxmmc")).trim().equals("")){
            validateResult = false;
        }
        if(getValue(cell(row,WhxmService.columns,"wxxmbh"))==null||getValue(cell(row,WhxmService.columns,"wxxmbh")).trim().equals("")){
            validateResult = false;
        }
        if(getValue(cell(row,WhxmService.columns,"szsf"))==null||getValue(cell(row,WhxmService.columns,"szsf")).trim().equals("")){
            validateResult = false;
        }
        return validateResult;
    }
    
    /**
     * 执行数据插入或更新（维护项目）
     * @param list
     * @param xmbhArr
     */
    private void insertOrUpdateWhxm(List<Object> list,List<String> idArr){
        List<Object> saveList = new ArrayList<Object>();    //待保存入库的数据
        List<Object> updateList = new ArrayList<Object>();  //待更新入库的数据
        List<Object> existList = new ArrayList<Object>();   //数据库中待更新的数据    
        
        //根据项目编号集合查找数据库数据中待更新的数据，每次500条
        if(idArr.size()>500){
            List<String> newArr = new ArrayList<String>();
            for(int i=0;i<idArr.size();i++){
                newArr.add(idArr.get(i));
                if((i+1)%500==0 || i==idArr.size()-1){
                    existList.addAll(whxmService.getDataByArray(newArr));
                    newArr = new ArrayList<String>(); 
                }
            }
        }else{
            existList.addAll(whxmService.getDataByArray(idArr));
        }
        
        StringBuffer idBuffer = new StringBuffer();
        for (Object obj : existList) {
            if(obj!=null){
                Whxm whxm = (Whxm) obj;
                idBuffer.append(whxm.getId()+" ");
            }
        }
        
        //匹配待更新数据与导入的数据集，区分出待插入数据和待跟新入库的数据
        if(existList.size()==0){
            saveList = list;
        }else{
            for(int x=0;x<list.size();x++){
                Whxm whxm = (Whxm) list.get(x);
                String id = whxm.getId();
                if(idBuffer.toString().indexOf(id)>-1){
                    for (int i = 0; i < existList.size(); i++) {
                        updateList.add(whxm);
                    }
                }else{
                    saveList.add(whxm);
                }
            }
        }
        //执行批量插入或更新操作
        jbxxService.batchSave(saveList);
        jbxxService.batchUpdate(updateList);
    }
    
    /**
	 * 对从Excel文件中读取到的项目子类数据执行插入或更新入库操作
	 * @param xmbhArr		项目编号数组，用于获取数据库中存在的数据
	 * @param data			Excel文件中读取到的项目子类
	 * @param saveList		需要保存入库的数据
	 * @param updateList	需要更新入库的数据
	 * @param tableName		项目子类数据对应的数据表
	 */
    private void insertOrUpdateForXmzl(ArrayList<String> idArr, List<List<Object>> data, List<Object> saveList, List<Object> updateList, String tableName){
		AddJbxx addJbxx = null;
    	//获取数据库中存在的数据  
		List<Object> existData = new ArrayList<Object>();
		if(idArr.size()>500){
			ArrayList<String> newArr = new ArrayList<String>();
			for(int x=0;x<idArr.size();x++){
				newArr.add(idArr.get(x));
				if((x+1)%500==0 || x==idArr.size()-1){
					existData.addAll(jbxxService.getDataByArray(newArr,tableName));
					newArr = new ArrayList<String>();
				}
			}
		}else{
			existData.addAll(jbxxService.getDataByArray(idArr,tableName));
		}
		if(existData.size() == 0){//Excel文件中的数据全部需要执行insert
			for(List<Object> list : data){
				addJbxx = (AddJbxx)list.get(0);
				saveList.add(addJbxx);
				Object obj = list.get(1);
				saveList.add(subclassInstance(obj,tableName));
			}
		}else{//Excel文件中的数据部分需要执行insert，部分需要执行update
			for(List<Object> list : data){
				addJbxx = (AddJbxx)list.get(0);
				Object obj = list.get(1);
				String id = addJbxx.getId();
				for (int x=0;x<existData.size();x++) {
					@SuppressWarnings("unchecked")
                    Map<String,Object> map = (Map<String,Object>)existData.get(x);
					if(id.equals(map.get("id"))){
						addJbxx.setId(id);
						updateList.add(addJbxx);
						updateList.add(subclassInstance(obj,tableName));
						break;
					}else{
						if(x==existData.size()-1){
							saveList.add(addJbxx);
							saveList.add(subclassInstance(obj,tableName));
						}
					}
				}
			}
		}
	}

	/**
	 * 子类对象ID赋值
	 * @param obj
	 * @param tableName
	 * @param id
	 * @return
	 */
	private Object subclassInstance(Object obj,String tableName){
		if("Zqdl".equals(tableName)){
			AddZqdl addZqdl = (AddZqdl) obj;
			return addZqdl;
		}else if("QiaoLiang".equals(tableName)){
			AddQiaoLiang addQiaoLiang = (AddQiaoLiang) obj;
			return addQiaoLiang;
		}else if("Zqmt".equals(tableName)){
			AddZqmt addZqmt = (AddZqmt) obj;
			return addZqmt;
		}else if("Zsjtjp".equals(tableName)){
			AddZsjtjp addZsjtjp = (AddZsjtjp) obj;
			return addZsjtjp;
		}else if("Tsw".equals(tableName)){
			AddTsw addTsw = (AddTsw) obj;
			return addTsw;
		}else if("Tzl".equals(tableName)){
			AddTzl addTzl = (AddTzl) obj;
			return addTzl;
		}else if("Juma".equals(tableName)){
			AddJuma addJuma = (AddJuma) obj;
			return addJuma;
		}else if("Lzz".equals(tableName)){
			AddLzz addLzz=(AddLzz)obj;
			return addLzz;
		}else if("Gld".equals(tableName)){
			AddGld addGld=(AddGld)obj;
			return addGld;
		}else if("Bjxl".equals(tableName)){
			AddBjxl addBjxl=(AddBjxl)obj;
			return addBjxl;
		}else if("Jkzx".equals(tableName)){
			AddJkzx addJkzx=(AddJkzx)obj;
			return addJkzx;
		}else if("Jkz".equals(tableName)){
			AddJkz addJkz=(AddJkz)obj;
			return addJkz;
		}else if("Csxl".equals(tableName)){
			AddCsxl addCsxl=(AddCsxl)obj;
			return addCsxl;
		}else if("Gdxt".equals(tableName)){
			AddGdxt addGdxt=(AddGdxt)obj;
			return addGdxt;
		}else if("Jjmlfpt".equals(tableName)){
			AddJjmlfpt addJjmlfpt=(AddJjmlfpt)obj;
			return addJjmlfpt;
		}else if("Wrzsdzsb".equals(tableName)){
			AddWrzsdzsb addWrzsdzsb=(AddWrzsdzsb)obj;
			return addWrzsdzsb;
		}else if("Spqd".equals(tableName)){
			AddSpqd addSpqd=(AddSpqd)obj;
			return addSpqd;
		}else if("Xkzd".equals(tableName)){
			AddXkzd addXkzd=(AddXkzd)obj;
			return addXkzd;
		}else if("Bjzz".equals(tableName)){
			AddBjzz addBjzz=(AddBjzz)obj;
			return addBjzz;
		}else if("Zqf".equals(tableName)){
			AddZqf addZqf=(AddZqf)obj;
			return addZqf;
		}else if("Lwt".equals(tableName)){
			AddLwt addLwt=(AddLwt)obj;
			return addLwt;
		}else if("Bzp".equals(tableName)){
			AddBzp addBzp=(AddBzp)obj;
			return addBzp;
		}
		return null;
	}
    
}