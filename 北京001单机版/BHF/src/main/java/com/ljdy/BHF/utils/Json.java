package com.ljdy.BHF.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * json工具
 * 修改时间：
 *      [2017-11-23] 创建文件 by 徐成
 */
public class Json {

    /**
     * 获取json字符串
     * @param obj
     * @return
     */
    public static String transToJson(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
