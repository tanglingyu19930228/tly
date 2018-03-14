package com.ljdy.BHF.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName IpAddrUtil 
 * @Description (ip地址工具类) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月25日 上午9:01:45 
 * @修改历史  
 *     1. [2017年9月25日]创建文件 by 顾冲
 */
public class IpAddrUtil {
    /**
     * @Title getIpAddr 
     * @Description (获取ip地址) 
     * @param request
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2017年9月25日
     * @修改历史  
     *     1. [2017年9月25日]创建文件 by 顾冲
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
