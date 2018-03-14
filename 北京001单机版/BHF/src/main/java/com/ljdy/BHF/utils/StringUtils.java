/**
 * @项目名称 BHF
 * @Title StringUtil.java 
 * @Package com.ljdy.BHF.utils 
 * @Description (用一句话描述该文件做什么) 
 * @Author 徐成 xucheng@luojiadeyi.com  
 * @Date 2017年1月12日 上午11:46:51 
 * @Version V1.0 
 * @版权 珞珈德毅科技股份有限公司所有
 * @修改历史  
 *     1. [2017年1月12日]创建文件 by 徐成
 */
package com.ljdy.BHF.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/** 
 * @ClassName StringUtil 
 * @Description (字符串工具类) 
 * @Author 徐成 xucheng@luojiadeyi.com    
 * @Date 2017年1月12日 上午11:46:51 
 * @修改历史  
 *     1. [2017年1月12日]创建文件 by 徐成
 */
public class StringUtils {

     /**
     * @Title isNum 
     * @Description (检查字符串是否为数字) 
     * @param chars
     * @return
     * @Return boolean 返回类型 
     * @Throws 
     * @Date  2017年1月12日
     * @修改历史  
     *     1. [2017年1月12日]创建文件 by 徐成
     */
    public static boolean isNum(String chars){
        if(chars== null || chars.trim().equals("")){
            return false;
        }
        if(countChar(chars,'.')>1){
            return false;
        }
        for(int i=0;i<=chars.length();i++){
            if(chars.charAt(chars.length()-1)=='0'){
                chars = chars.substring(0,chars.length()-1);
            }
            if(chars.length()>1 && chars.charAt(chars.length()-1)=='.'){
                chars = chars.substring(0,chars.length()-1);
                break;
            }
        }
        char[] arr = chars.replace(",", "").toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if(Character.isDigit(arr[i])||arr[i]=='.'){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
    
     /**
     * @Title countChar 
     * @Description (统计某个字符的数量) 
     * @param chars
     * @param c
     * @return
     * @Return int 返回类型 
     * @Throws 
     * @Date  2017年1月12日
     * @修改历史  
     *     1. [2017年1月12日]创建文件 by 徐成
     */
    public static int countChar(String chars ,char c){
        int count =0;
        if(chars== null || chars.trim().equals("")){
            return 0;
        }
        for(int i=0;i<=chars.length();i++){
            if(chars.charAt(chars.length()-1)=='0'){
                chars = chars.substring(0,chars.length()-1);
            }
            if(chars.length()>1 && chars.charAt(chars.length()-1)=='.'){
                chars = chars.substring(0,chars.length()-1);
                break;
            }
        }
        for (int i = 0; i < chars.length(); i++) {
            if(c==chars.charAt(i)){
                count++;
            }
        }
        return count;
    }
    
     /**
     * @Title getIntVal 
     * @Description (获取字符串文本对应整数) 
     * 
     * @param chars
     * @return
     * @Return int 返回类型 
     * @Throws 
     * @Date  2017年2月23日
     * @修改历史  
     *     1. [2017年2月23日]创建文件 by 徐成
     */
    public static int getIntVal(String chars){
        for(int i=0;i<=chars.length();i++){
            if(chars.indexOf(".")>-1 && chars.charAt(chars.length()-1)=='0'){
                chars = chars.substring(0,chars.length()-1);
            }
            if(chars.length()>1 && chars.charAt(chars.length()-1)=='.'){
                chars = chars.substring(0,chars.length()-1);
                break;
            }
        }
        return Integer.valueOf(chars);
    }
    
    /**
     * @Title isDate 
     * @Description (是否为时间个数字符串) 
     * @param strDate
     * @return
     * @Return boolean 返回类型 
     * @Throws 
     * @Date  2017年12月8日
     * @修改历史  
     *     1. [2017年12月8日]创建文件 by 顾冲
     */
    public static boolean isDate(String strDate){
        boolean convertSuccess = true;
        String formartStr = "";
        formartStr = "yyyy-MM-dd";
        DateFormat formart = new SimpleDateFormat(formartStr);
        try {
            formart.setLenient(false);//只能是formartStr格式，其它格式无法通过
            formart.parse(strDate);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }
    /**
     * 判断字符是否在字符数组中
     */
     public  static boolean isExist(String aa,List<String> list){
    	boolean flag=false;
    	for(int i=0;i<list.size();i++){
    		if(aa.equals(list.get(i))){
    		flag=true;
    		break;
    	}
    	}
    	return flag;
     }
}
