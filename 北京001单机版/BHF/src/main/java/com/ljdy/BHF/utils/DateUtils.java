/**
 * //TODO 添加文件描述
 * 项目名称 : RealestateRegister
 * @Title: DateUtils.java 
 * @Package com.ljdy.realestateRegister.utils 
 * @Description: TODO(日期工具类) 
 * @author 徐成 1127273457@qq.com;
 * @date 2016年9月6日 下午5:08:50 
 * @version V1.0 
 * 珞珈德毅科技股份有限公司源代码，版权归珞珈德毅科技股份有限公司所有。
 * 修改历史 : 
 *     1. [2016年9月6日]创建文件 by linyu
 */
package com.ljdy.BHF.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * //TODO 添加类描述
 * @ClassName: DateUtils 
 * @Description: TODO(日期工具类) 
 * @author 徐成   1127273457@qq.com;
 * @date 2016年9月6日 下午5:08:50 
 * @修改历史 : 
 *     1. [2016年9月6日]创建文件 by 徐成  
 *     
 */

public class DateUtils {
	
    public static String DATE_FORMAT_DATEONLY = "yyyy-MM-dd"; // 年/月/日
    public static String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss"; // 年/月/日

    
    /**
     * //TODO 添加方法功能描述
     * @Title: formatDate 
     * @Description: TODO(取得指定日期格式的字符串) 
     * @param date
     * @param format
     * @return
     * @return String    返回类型 
     * @throws 
     * 创建日期 : 2016年9月6日
     * 修改历史 : 
     *     1. [2016年9月6日]创建文件 by 徐成
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
    
     /**
     * @Title convertStrToDate 
     * @Description (将指定格式的string转化成Date) 
     * @return
     * @throws ParseException 
     * @Return Date 返回类型 
     * @Throws 
     * @Date  2016年9月22日
     * @修改历史  
     *     1. [2016年9月22日]创建文件 by 梅河营
     */
    public static Date convertStrToDate(String dateString,String formate){
    	SimpleDateFormat sdf = new SimpleDateFormat(formate);
    	
    	Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    	
    	return date;
    }
    
     /**
     * @Title getYear 
     * @Description (获取当前年份) 
     * @return
     * @Return int 返回类型 
     * @Throws 
     * @Date  2016年12月14日
     * @修改历史  
     *     1. [2016年12月14日]创建文件 by 徐成
     */
    public static int getYear(){
        Calendar calendar = Calendar.getInstance();
        
        return calendar.get(Calendar.YEAR);
    }
}
