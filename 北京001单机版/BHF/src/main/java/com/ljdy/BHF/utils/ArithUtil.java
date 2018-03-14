package com.ljdy.BHF.utils;

import java.math.BigDecimal;

/**
 * @ClassName ArithUtil 
 * @Description (加减乘除工具类) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月27日 上午11:34:10 
 * @修改历史  
 *     1. [2017年9月27日]创建文件 by 顾冲
 */
public class ArithUtil {
    /**
     * @Title add 
     * @Description (double类型数据相加) 
     * @param d1
     * @param d2
     * @return
     * @Return double 返回类型 
     * @Throws 
     * @Date  2017年9月27日
     * @修改历史  
     *     1. [2017年9月27日]创建文件 by 顾冲
     */
    public static double add(double d1, double d2){
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();
    }
    /**
     * @Title div 
     * @Description (这里用一句话描述这个方法的作用) 
     * @param d1 被除数
     * @param d2 除数
     * @param pointSize 小数点位数,不能未负数
     * @return
     * @Return Double 返回类型 
     * @Throws 
     * @Date  2017年10月25日
     * @修改历史  
     *     1. [2017年10月25日]创建文件 by 顾冲
     */
    public static Double div(Double d1, Double d2, int pointSize){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2, pointSize, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static Double add(BigDecimal b1, double d){
        BigDecimal b2 = new BigDecimal(Double.toString(d));
        return b1.add(b2).doubleValue();
    }
    
    /**
     * @Title div 
     * @Description (BigDecimal类型数据相除) 
     * @param b1
     * @param b2
     * @param pointSize
     * @return
     * @Return BigDecimal 返回类型 
     * @Throws 
     * @Date  2017年11月9日
     * @修改历史  
     *     1. [2017年11月9日]创建文件 by 顾冲
     */
    public static BigDecimal div(BigDecimal b1, BigDecimal b2, int pointSize){
        return b1.divide(b2, pointSize, BigDecimal.ROUND_HALF_UP);
    }
    
    
    /**
     * @Title sub 
     * @Description (double类型数据相减) 
     * @param d1
     * @param d2
     * @return
     * @Return Double 返回类型 
     * @Throws 
     * @Date  2017年11月16日
     * @修改历史  
     *     1. [2017年11月16日]创建文件 by 顾冲
     */
    public static Double sub(double d1, double d2){
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.subtract(b2).doubleValue();
    }
    
}
