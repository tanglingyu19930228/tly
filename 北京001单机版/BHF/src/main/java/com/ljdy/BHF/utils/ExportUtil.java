/**
 * @项目名称 BHF
 * @Title ExportUtil.java 
 * @Package com.ljdy.BHF.utils 
 * @Description (用一句话描述该文件做什么) 
 * @Author 徐成  1127273457@qq.com  
 * @Date 2017年1月20日 上午8:38:14 
 * @Version V1.0 
 * @版权 珞珈德毅科技股份有限公司所有
 * @修改历史  
 *     1. [2017年1月20日]创建文件 by 徐成
 */
package com.ljdy.BHF.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ljdy.BHF.model.Whxm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.ljdy.BHF.model.Jbxx;
import com.ljdy.BHF.model.Zqdl;

import freemarker.template.Configuration;
import freemarker.template.Template;

/** 
 * @ClassName ExportUtil 
 * @Description (使用Freemarker导出文件工具类) 
 * @Author 徐成  1127273457@qq.com    
 * @Date 2017年1月20日 上午8:38:14 
 * @修改历史  
 *     1. [2017年1月20日]创建文件 by 徐成
 */
@SuppressWarnings("all")
public class ExportUtil {

    private static Configuration cfg = null;
    
    static{
        cfg = new Configuration();
        cfg.setDefaultEncoding("utf-8");
    }
    
     /**
     * @Title getTemplate 
     * @Description (获取模板文件) 
     * @param templateFilePath
     * @param fileName
     * @return
     * @throws Exception
     * @Return Template 返回类型 
     * @Throws 
     * @Date  2017年2月9日
     * @修改历史  
     *     1. [2017年2月9日]创建文件 by 徐成
     */
    private static Template getTemplate(String templateFilePath,String templateFileName) throws Exception{
        cfg.setDirectoryForTemplateLoading(new File(templateFilePath));
        Template temp = null;
        try {
            temp = cfg.getTemplate(templateFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    
     /**
     * @Title createExcel 
     * @Description (生成目标文件) 
     * @param templateFilePath  模板文件路劲
     * @param templateFileName  模板文件文件名        
     * @param dataMap           数据
     * @param targerFileName    要生成的目标文件（含路径）    
     * @throws Exception
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年2月9日
     * @修改历史  
     *     1. [2017年2月9日]创建文件 by 徐成
     */
    public static File createFile(String templateFilePath,String templateFileName, Map<String,Object> dataMap,String targerFileName) throws Exception{
        File outFile = new File(targerFileName);
        Writer out = null ;
        FileOutputStream fos  =null;
        try {
            fos = new FileOutputStream(outFile);
            OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
            out = new BufferedWriter(oWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            getTemplate(templateFilePath,templateFileName).process(dataMap, out);
            out.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outFile;
    }
    
    /**
	 * @Title: exportExcel
	 * @Description: Poi导出Excel的方法
	 * @author: 徐成	 @2017-11-1
	 * @param workbook 
	 * @param sheetNum (sheet的位置，0表示第一个表格中的第一个sheet)
	 * @param sheetTitle 	(sheet的名称)
	 * @param headers    	(表格的标题)
	 * @param result   	 	(表格的数据)
	 * @param out  		 	(输出流)
	 * @param columns  		(导出列)
	 * @throws Exception
	 */
	public static void exportExcel(HSSFWorkbook workbook, int sheetNum,String sheetTitle, String[] headers, 
    	List<Object> result,OutputStream out,String[] columns,String partTag) throws Exception{
    	// 生成一个表格
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(sheetNum, sheetTitle);
		// 设置表格默认列宽度为50个字节
		sheet.setDefaultColumnWidth((short) 30);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);

		// 指定当单元格内容显示不下时自动换行
		style.setWrapText(true);

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
		    if("使用状态".equals(new HSSFRichTextString(headers[i]).toString()) && !"4".equals(partTag)){//使用维护有syzt字段，其它没有
		        continue;
		    }
			HSSFCell cell = row.createCell((short) i);
		
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text.toString());
		}
		if(result != null){
			int index = 1;
			for (Object obj : result) {
				row = sheet.createRow(index);
				int cellIndex = 0;
				for(String property :columns){
				    if(property.equals("syzt") && !"4".equals(partTag)){//使用维护有syzt字段，其它没有
				        continue;
				    }
				    HSSFCell cell = row.createCell((short) cellIndex);
					Map<String,Object> map = new HashMap<>();
					if(obj instanceof Whxm){
						map = BeanToMap.transBeanToMap(obj,null);
					}else{
						map = (Map<String,Object>)obj;
					}
				    Object o = map.get(property);
				    if(o!=null){
				    	if(o instanceof Double){
				    		cell.setCellValue(NumberFormatUtils.getPrettyNumber(NumberFormatUtils.format(Double.valueOf(o.toString()), "0.00000")));
				    	}else if(o instanceof Integer){
				    		BigDecimal bigDecimal = new BigDecimal(Integer.valueOf(o.toString()));
				    		cell.setCellValue(bigDecimal.toString());
				    	}else if(o instanceof Date){
				    		cell.setCellValue(o.toString().trim().substring(0, 10));
				    	}else if(o instanceof BigDecimal){
				    		cell.setCellValue(o==null?"":o.toString());
				    	}else if(o instanceof Timestamp){
				    		cell.setCellValue(o.toString().trim().substring(0, 10));
				    	}else{
				    		cell.setCellValue(o==null?"":o.toString().replace("[","").replace("]",""));
				    	}
				    }
                    cellIndex++;
				}
				index++;
			}
		}
    }
	
	/**
	 * 调用Poi生成Excel文件方法生成有多个sheet页的Excel文件
	 * @param filePath		(生成文件的文件路劲)
	 * @param headers		(表头集合)
	 * @param sheetNameArr	(sheet页名称集合)
	 * @param data			(数据集)
	 * @param columnsArr	(导出列)
	 * @return
	 * @throws Exception
	 */
	public static File createExcelForPoi(String filePath,List<String[]> headers,
			String[] sheetNameArr,List<List<Object>> data,List<String[]> columnsArr, String partTag) throws Exception{
		File file = new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStream out = new FileOutputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook();
		for(int i=0;i<sheetNameArr.length;i++){
			exportExcel(workbook,i,sheetNameArr[i],headers.get(i),data.get(i),out,columnsArr.get(i), partTag);
		}
		workbook.write(out);
		out.close();
		return file;
	}
}
