package com.ljdy.BHF.controller;

import com.ljdy.BHF.model.DICT_AREA;
import com.ljdy.BHF.model.User;
import com.ljdy.BHF.service.DICT_AREAService;
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
 * 地区字典操作请求处理 修改时间： [2017-11-22] 创建文件 by 徐成
 */
@Controller
@RequestMapping("/area")
public class Dict_AreaController {

	@Resource
	protected DICT_AREAService dict_AREAService;

	/**
	 * 省市区三级联动
	 * 
	 * @param request
	 * @return Object
	 * @date [2017-10-09] 创建文件 by 郎川
	 */
	@RequestMapping("/querychildcity")
	public @ResponseBody
	List<DICT_AREA> queryChildCity(HttpServletRequest request) {
		String superCode = request.getParameter("superCode");
		// return dict_AREAService.findByCodeName(superName);
		return dict_AREAService.findBySuperCode(superCode);
	}

	/**
	 * 获取市数据
	 * 
	 * @param request
	 * @return Object
	 * @date [2017-10-09] 创建文件 by 郎川
	 */
	@RequestMapping("/loadCity")
	public @ResponseBody
	List<DICT_AREA> loadCity(HttpServletRequest request) {
		String szsf = request.getParameter("szsf");
		String superCode = dict_AREAService.getCodeValueByCodeName(szsf);
		return dict_AREAService.findBySuperCode(superCode);
	}

	/**
	 * 获取区数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/loadQu")
	public @ResponseBody
	List<DICT_AREA> loadQu(HttpServletRequest request) {
		String szsf = request.getParameter("szsf");
		String superCode = dict_AREAService.getCodeValueByCodeName(szsf);
		String szcs = request.getParameter("szcs");
		String codeValue = dict_AREAService.getCodeValueByCodeNameAndSuperCode(
				szcs, superCode);
		return dict_AREAService.findBySuperCode(codeValue);
	}

    /**
     * 加载地区字典数据
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/loadData")
    public void loadData(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        User user = (User) request.getSession().getAttribute("loginUser");
        String province = user.getProvince();
        List<DICT_AREA> areaList = new ArrayList<DICT_AREA>();

        //查询条件
        String codeName_key = request.getParameter("codeName");
        String codeValue_key = request.getParameter("codeValue");

        if("国家".equals(province)){
            areaList = dict_AREAService.getData(null,null,codeName_key,codeValue_key);
        }else{
            String codeValue = dict_AREAService.getCodeValueByCodeName(province);
            areaList = dict_AREAService.getData(codeValue,province,codeName_key,codeValue_key);
        }

        //分页
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int startRow = pageIndex*pageSize;
        int endRow = startRow+pageSize;

        List<Object> data = new ArrayList<Object>();
        for(int i = startRow;i<areaList.size()&&i<endRow;i++){
            data.add(areaList.get(i));
        }

        Map<String,Object> result = new HashMap<String,Object>();
        result.put("data",data);
        result.put("total",areaList.size());
        response.getWriter().write(Json.transToJson(result));
    }

    /**
     * 地区字典通用新增、修改、删除方法
     * @param request
     * @throws IOException
     */
    @RequestMapping("/save")
    public @ResponseBody String save(HttpServletRequest request) throws IOException {
        String json = request.getParameter("data");
        if(json!=null&& !"".equals(json)){
            //json = json.replace("[","").replace("]","");
            //System.out.print(json);
            ObjectMapper mapper = new ObjectMapper();
            List<Object> rows = mapper.readValue(json,new TypeReference<List<Object>>() { });
            for(int i=0;i<rows.size();i++){
                @SuppressWarnings("unchecked")
                LinkedHashMap<String,Object> row = (LinkedHashMap<String, Object>) rows.get(i);
                String id = row.get("id")!=null?row.get("id").toString():"";
                String state = row.get("_state") != null ? row.get("_state").toString() : "";
                if(state.equals("added") || id.equals("")){//新增
                    DICT_AREA dict_area = new DICT_AREA();
                    dict_area.setTypeName(row.get("typeName").toString());
                    dict_area.setCodeName(row.get("codeName").toString());
                    dict_area.setCodeValue(row.get("codeValue").toString());
                    String superCode = row.get("superCode")==null?null:row.get("superCode").toString();
                    dict_area.setSuperCode(superCode);
                    List<DICT_AREA> list = dict_AREAService.findBySuperCode(superCode);
                    dict_area.setOrderCode(list!=null&&list.size()>0?list.size()+1:1);
                    dict_AREAService.add(dict_area);
                }else if(state.equals("removed") || state.equals("deleted")){//删除
                    dict_AREAService.deleteByID(id);
                }else if(state.equals("modified") || state.equals("")){//修改
                    DICT_AREA dict_area = dict_AREAService.getDataByID(id);
                    dict_area.setTypeName(row.get("typeName").toString());
                    dict_area.setCodeName(row.get("codeName").toString());
                    dict_area.setSuperCode(row.get("superCode")==null?null:row.get("superCode").toString());
                    dict_area.setCodeValue(row.get("codeValue").toString());
                    dict_AREAService.update(dict_area);
                }
            }
        }
        return "success";
    }

    /**
     * 验证行政编码唯一性
     * @param request
     * @param response
     */
    @RequestMapping("/isSingleCode")
    public void isSingleCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String codeValue = request.getParameter("codeValue");
        String codeName = dict_AREAService.getCodeNameByCodeValue(codeValue);
        if(codeName==null){
            response.getWriter().write(Json.transToJson(true));
        }else{
            response.getWriter().write(Json.transToJson(false));
        }
    }
}
