package com.ljdy.BHF.controller;

import com.ljdy.BHF.model.DICT_GY;
import com.ljdy.BHF.service.Dict_GYService;
import com.ljdy.BHF.utils.Json;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 共用字典操作请求处理
 * 修改时间：
 *      [2017-11-27] 创建文件 by 徐成
 */
@Controller
@RequestMapping("/dict")
public class Dict_GyController {

    @Resource
    private Dict_GYService dict_gyService;

    @RequestMapping("/load")
    public void load(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String typeName = request.getParameter("typeName");

        List<DICT_GY> list = dict_gyService.findAll(typeName);
        //分页
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));//默认0开始
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int startRow = pageIndex*pageSize;
        int endRow = startRow+pageSize;
        List<Object> data = new ArrayList<Object>();
        for(int i = startRow;i<list.size()&&i<endRow;i++){
            data.add(list.get(i));
        }

        Map<String,Object> result = new HashMap<String,Object>();
        result.put("data",data);
        result.put("total",list.size());
        response.getWriter().write(Json.transToJson(result));
    }

    /**
     * 地区字典表通用添加、修改、删除方法通用方法
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/save")
    public @ResponseBody String save(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String json = request.getParameter("data");
        if(json!=null&& !"".equals(json)){
            ObjectMapper mapper = new ObjectMapper();
            List<Object> rows = mapper.readValue(json,new TypeReference<List<Object>>() { });
            for(int i=0;i<rows.size();i++){
                @SuppressWarnings("unchecked")
                LinkedHashMap<String,Object> row = (LinkedHashMap<String, Object>) rows.get(i);
                String id = row.get("id")!=null?row.get("id").toString():"";
                String state = row.get("_state") != null ? row.get("_state").toString() : "";
                if(state.equals("added") || id.equals("")){//新增
                    DICT_GY dict_gy = new DICT_GY();
                    String typeName = row.get("typeName").toString();
                    dict_gy.setTypeName(typeName);
                    String codeName = row.get("codeName").toString();
                    dict_gy.setCodeName(codeName);
                    dict_gy.setCodeValue(row.get("codeValue").toString());
                    String superCode = row.get("superCode")==null||"".equals(row.get("superCode"))?null:row.get("superCode").toString();
                    dict_gy.setSuperCode(superCode);
                    dict_gy.setOrderCode(dict_gyService.getNextOrderCode(typeName));
                    dict_gyService.save(dict_gy);
                }else if(state.equals("removed") || state.equals("deleted")){//删除
                    dict_gyService.deleteByID(id);
                }else if(state.equals("modified") || state.equals("")){//修改
                    DICT_GY dict_gy =dict_gyService.getDateByID(id);
                    dict_gy.setTypeName(row.get("typeName").toString());
                    dict_gy.setCodeName(row.get("codeName").toString());
                    dict_gy.setCodeValue(row.get("codeValue").toString());
                    String superCode = row.get("superCode")==null||"".equals(row.get("superCode"))?null:row.get("superCode").toString();
                    dict_gy.setSuperCode(superCode);
                    dict_gyService.update(dict_gy);
                }
            }
        }
        return "success";
    }
}
