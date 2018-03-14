package com.ljdy.BHF.utils;

import com.ljdy.BHF.model.Jbxx;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName BeanToMap 
 * @Description (将Java Bean转为Map的工具类) 
 * @Author 李金阳 lijy@luojiadeyi.com    
 * @Date 2016年12月20日 下午3:17:53 
 * @修改历史  
 *     1. [2016年12月20日]创建文件 by 李金阳
*/
public class BeanToMap{

     /**
     * @Title transBeanToMap 
     * @Description (Java Bean转为Map集合) 
     * @param obj
     * @return
     * @Return Map<String,Object> 返回类型 
     * @Throws 
     * @Date  2016年12月20日
     * @修改历史  
     *     1. [2016年12月20日]创建文件 by 李金阳
     */
    public static Map<String, Object> transBeanToMap(Object obj, String[] fields) {
    
        if(obj == null){
            return null;
        }
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                
                // 过滤class属性和用户指定过滤的属性
                if (!key.equals("class") && !contains(fields, key)) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
    
                    map.put(key, value);
                }
    
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    
        return map;
    
    }
    
     /**
     * @Title contains 
     * @Description (判断某字段是否被字段数组包涵) 
     * @param fields
     * @param field
     * @return
     * @Return boolean 返回类型 
     * @Throws 
     * @Date  2016年12月20日
     * @修改历史  
     *     1. [2016年12月20日]创建文件 by 李金阳
     */
    private static boolean contains (String[] fields, String field) {
        
        boolean result = false;
        
        if(fields == null) {
            return result;
        }
        
        for (String str : fields) {
            if (str.equals(field)) {
                result = true;
                break;
            }
        }
        
        return result;
        
    }

    //根据属性名称获取属性数据类型
  	public static String getPropertyType(Class<?> type,String property){
  		Field[] fields = type.getDeclaredFields();
  		for (Field field : fields) {
  			field.setAccessible(true);  
              String propertyType = field.getType().toString();  
              int lastIndex = propertyType.lastIndexOf(".");  
              propertyType = propertyType.substring(lastIndex + 1);
              if(property.equals(field.getName())){
              	return propertyType;
              }
  		}
  		return null;
  	}

    /**
     * Map集合转实体类对象
     * @param map
     * @param type
     * @return
     */
    public static Object transMapToBean(Map<String,Object> map ,Class<?> type){
        if(map==null){
            return null;
        }
        Object obj =null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            obj = type.newInstance(); // 创建 JavaBean 对象
            PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
            for (int i = 0; i< propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();

                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);
                    if("null".equals(value) || value==null){
                        value = null;
                        descriptor.getWriteMethod().invoke(obj, value);
                    }else{
                        if("String".equals(getPropertyType(obj.getClass(),propertyName))|| "String".equals(getPropertyType(Jbxx.class,propertyName))){
                            descriptor.getWriteMethod().invoke(obj, value);
                        }else if("BigDecimal".equals(getPropertyType(obj.getClass(),propertyName))|| "BigDecimal".equals(getPropertyType(Jbxx.class,propertyName))){
                            descriptor.getWriteMethod().invoke(obj, new BigDecimal(Double.parseDouble(value.toString().trim())));
                        }else if("Date".equals(getPropertyType(obj.getClass(),propertyName))||"Date".equals(getPropertyType(Jbxx.class,propertyName))){
                            Date date = DateUtils.convertStrToDate(value.toString(),DateUtils.DATE_FORMAT_DATEONLY);
                            descriptor.getWriteMethod().invoke(obj, date);
                        }else if("Integer".equals(getPropertyType(obj.getClass(),propertyName))||"Integer".equals(getPropertyType(Jbxx.class,propertyName))){
                            descriptor.getWriteMethod().invoke(obj,Integer.parseInt(value.toString().trim()));
                        }else if("Double".equals(getPropertyType(obj.getClass(),propertyName))||"Double".equals(getPropertyType(Jbxx.class,propertyName))){
                            descriptor.getWriteMethod().invoke(obj,Double.parseDouble(value.toString().trim()));
                        }else if ("Set".equals(getPropertyType(obj.getClass(),propertyName))||"Set".equals(getPropertyType(Jbxx.class,propertyName)) ){
                            continue;
                        }
                    }
                }
            }
        } catch (IntrospectionException e) {//获取实体类对象失败
            e.printStackTrace();
        } catch (InstantiationException e) {//实例化异常
            e.printStackTrace();
        } catch (IllegalAccessException e) {//实例化异常
            e.printStackTrace();
        } catch (IllegalArgumentException e) {//反射异常
            e.printStackTrace();
        } catch (InvocationTargetException e) {//反射异常
            e.printStackTrace();
        }
        return obj;
    }
}
