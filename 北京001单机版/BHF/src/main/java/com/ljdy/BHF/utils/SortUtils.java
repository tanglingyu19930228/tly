/**
 * @项目名称 BHF
 * @Title SortUtils.java 
 * @Package com.ljdy.BHF.utils 
 * @Description (用一句话描述该文件做什么) 
 * @Author 徐成 xucheng@luojiadeyi.com  
 * @Date 2016年12月14日 下午4:59:02 
 * @Version V1.0 
 * @版权 珞珈德毅科技股份有限公司所有
 * @修改历史  
 *     1. [2016年12月14日]创建文件 by 徐成
 */
package com.ljdy.BHF.utils;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/** 
 * @ClassName SortUtils 
 * @Description (排序工具类) 
 * @Author 徐成 xucheng@luojiadeyi.com    
 * @Date 2016年12月14日 下午4:59:02 
 * @修改历史  
 *     1. [2016年12月14日]创建文件 by 徐成
 */
public class SortUtils {

     /**
     * @Title sortMapByKey 
     * @Description (Map集合根据Key排序) 
     * @param map
     * @return
     * @Return Map<Double,String> 返回类型 
     * @Throws 
     * @Date  2016年12月14日
     * @修改历史  
     *     1. [2016年12月14日]创建文件 by 徐成
     */
    public static Map<String,String> sortMapByKey(Map<String,String> map){
        ValueComparator bvc =  new ValueComparator(map);  
        Map<String,String> sortMap = new TreeMap<String,String>(bvc); 
        sortMap.putAll(map);  
        return sortMap;
    }
    
     /**
     * @Title test 
     * @Description (map遍历) 
     * @param map
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2017年1月13日
     * @修改历史  
     *     1. [2017年1月13日]创建文件 by 徐成
     */
    public static String ergodicMap(Map<String, String> map){
        StringBuffer data = new StringBuffer(); 
        for(Map.Entry<String, String> entry : map.entrySet()){
            data.append(entry.getKey()+"： "+entry.getValue());
        }
        return data.toString();
    }
    
}

class ValueComparator implements Comparator<String> ,Serializable{  

    private static final long serialVersionUID = 7796556535933714521L;
    
    Map<String, String> base;  
    public ValueComparator(Map<String, String> base) {  
        this.base = base;  
    }  
  
    // Note: this comparator imposes orderings that are inconsistent with equals.      
    public int compare(String a, String b) {  
        if (Double.valueOf(base.get(a)) >= Double.valueOf(base.get(b))) {  
            return -1;  
        } else {  
            return 1;  
        } // returning 0 would merge keys  
    }  
}  
