package com.ljdy.BHF.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    public final static String Md5(String str){
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            //将字符串转换成字节数组
            byte bt[] = str.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest msgdDigest = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            msgdDigest.update(bt);
            //得到密文
            byte md[] = msgdDigest.digest(bt);
            //将密文转换成十六进制字符串
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                char a = hexDigits[md[i] >>> 4 & 0xf];
                sb.append(a);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    
     /**
     * @Title getLic 
     * @Description (生成license密文) 
     * @param mac
     * @return
     * @throws Exception
     * @Return String 返回类型 
     * @Throws 
     * @Date  2017年4月6日
     * @修改历史  
     *     1. [2017年4月6日]创建文件 by 徐成
     */
    public static String getLic(String mac) throws Exception{
        StringBuffer sb = new StringBuffer();
        String str = mac.replace("-", "");
        for (int i = 0; i < str.length(); i++) {
            sb.append(Md5(String.valueOf(str.charAt(i))));
        }
        
        return sb.toString();
    }
    
}
