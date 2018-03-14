/**
 * @项目名称 RealestateRegister
 * @Title TokenProccessor.java 
 * @Package com.ljdy.realestateRegister.utils 
 * @Description (生成Token的工具类，TokenProccessor) 
 * @Author 李金阳 lijy@luojiadeyi.com  
 * @Date 2016年10月12日 上午9:30:37 
 * @Version V1.0 
 * @版权 珞珈德毅科技股份有限公司所有
 * @修改历史  
 *     1. [2016年10月12日]创建文件 by 李金阳
 */
package com.ljdy.BHF.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import sun.misc.BASE64Encoder;

/** 
 * @ClassName TokenProccessor 
 * @Description (生成Token的工具类，TokenProccessor) 
 * @Author 李金阳 lijy@luojiadeyi.com    
 * @Date 2016年10月12日 上午9:30:37 
 * @修改历史  
 *     1. [2016年10月12日]创建文件 by 李金阳
 */
@SuppressWarnings("restriction")
public class TokenProccessor {
    
    /*
    *单例设计模式（保证类的对象在内存中只有一个）
    *1、把类的构造函数私有
    *2、自己创建一个类的对象
    *3、对外提供一个公共的方法，返回类的对象
    */
    private TokenProccessor(){}
       
    private static final TokenProccessor instance = new TokenProccessor();
    
     /**
     * @Title getInstance 
     * @Description (返回类的对象) 
     * @return
     * @Return TokenProccessor 返回类型 
     * @Throws 
     * @Date  2016年10月12日
     * @修改历史  
     *     1. [2016年10月12日]创建文件 by 李金阳
     */
    public static TokenProccessor getInstance(){
        return instance;
    }
    
     /**
     * @Title makeToken 
     * @Description (生成Token) 
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2016年10月12日
     * @修改历史  
     *     1. [2016年10月12日]创建文件 by 李金阳
     */
    public String makeToken() {
        
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        
        try {
            
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(token.getBytes());
            
            // Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
            
        }catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        
    }
       
}
