package com.ljdy.BHF.utils;

import java.util.Map;
import java.util.UUID;

/**
 * 通用工具类
 * @author 徐成
 * @修改历史  
 *     1. [2017年9月26日]创建文件 by 徐成
 */
public class CommonUtil {

	/**
	 * 判断条件有效性
	 * @param condition
	 * @param key
	 * @return
	 */
	public static boolean validCondition(Map<String, Object> condition,String key){
		return condition.containsKey(key) && condition.get(key)!=null 
				&& !condition.get(key).equals("") && !condition.get(key).equals("null");
	}
	
	/**
	 * 生成唯一标识
	 * @return
	 */
	public static String createUUID(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}

	/**
	 * 获取元素在数组中的索引
	 * @return
	 */
	public static int getIndexOfArr(Object[] arr,Object obj){
		if(arr==null || obj==null || obj.equals("")){
			return -1;
		}
		for(int i=0;i<arr.length;i++){
			if(arr[i].equals(obj)){
				return i;
			}
		}
		return -1;
	}
}
