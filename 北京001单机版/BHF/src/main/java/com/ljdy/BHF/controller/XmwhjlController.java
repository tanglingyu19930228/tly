package com.ljdy.BHF.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljdy.BHF.model.DICT_GY;
import com.ljdy.BHF.model.PageBean;
import com.ljdy.BHF.model.User;
import com.ljdy.BHF.model.Whxm;
import com.ljdy.BHF.model.Xmwhjl;
import com.ljdy.BHF.service.Dict_GYService;
import com.ljdy.BHF.service.JbxxService;
import com.ljdy.BHF.service.WhxmService;
import com.ljdy.BHF.service.XmwhjlService;
import com.ljdy.BHF.utils.CommonUtil;
import com.ljdy.BHF.utils.ExportUtil;

/**
 * @ClassName XmwhjlController 
 * @Description (项目维护记录controller) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年11月16日 上午10:50:53 
 * @修改历史  
 *     1. [2017年11月16日]创建文件 by 顾冲
 */

@Controller
@RequestMapping("/xmwhjl") 
public class XmwhjlController extends BaseController{
    @Resource
    private XmwhjlService xmwhjlService;
    @Resource
    private JbxxService jbxxService;
    @Resource
    private Dict_GYService dict_gyService;
    @Resource
    private WhxmService whxmService;
    
    
    /**
     * @Title loadDataByCondition 
     * @Description (分页查询项目维护记录) 
     * @param condition
     * @param req
     * @return
     * @Return Map<String,Object> 返回类型 
     * @Throws 
     * @Date  2017年11月16日
     * @修改历史  
     *     1. [2017年11月16日]创建文件 by 顾冲
     */
    @RequestMapping("/loadTableData")
    public String loadTableData(@RequestParam Map<String, Object> condition,ModelMap model){
        int currentPage = 1;
        int rows = 5;
        if(CommonUtil.validCondition(condition, "offiset")){
            currentPage = Integer.parseInt(condition.get("offiset").toString()); 
        }
        condition.put("wxxmid", condition.get("xmid"));
        List<Object> dataList=xmwhjlService.getDataByCondition(condition, currentPage, rows);
        PageBean pageBean=getPageBean(currentPage, rows, dataList);
        model.put("xmwhjl", pageBean);
        return "xmwhjl";
    }
    
    /**
     * @Title loadDataByCondition 
     * @Description (ajax分页带条件查询维护记录)
     * @param condition
     * @return
     * @Return Map<String,Object> 返回类型 
     * @Throws 
     * @Date  2017年11月17日
     * @修改历史  
     *     1. [2017年11月17日]创建文件 by 顾冲
     */
    @RequestMapping("/loadDataByCondition")
    public @ResponseBody PageBean loadDataByCondition(@RequestParam Map<String, Object> condition){
        int currentPage = 1;
        int rows = 5;
        if(CommonUtil.validCondition(condition, "offiset")){
            currentPage = Integer.parseInt(condition.get("offiset").toString()); 
        }
        List<Object> dataList=xmwhjlService.getDataByCondition(condition, currentPage, rows);
        PageBean pageBean=getPageBean(currentPage, rows, dataList);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("xmwhjl", pageBean);
        return pageBean;
    }
    
    /**
     * @Title updateWhjl 
     * @Description (修改维修记录) 
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年11月17日
     * @修改历史  
     *     1. [2017年11月17日]创建文件 by 顾冲
     */
    @RequestMapping("/updateWhjl")
    public @ResponseBody String updateWhjl(Xmwhjl xmwhjl){
        String sql = "update xmwhjl set gzsb = ?, gzsbpp = ?, "
                + "gzsbxh = ?, gzbj = ?, shyy = ?, gzsj = ?, wxsj = ? where id = ?";
        Object [] param = new Object []{xmwhjl.getGzsb(), xmwhjl.getGzsbpp(), 
                xmwhjl.getGzsbxh(), xmwhjl.getGzbj(), xmwhjl.getShyy(), xmwhjl.getGzsj(), xmwhjl.getWxsj(), xmwhjl.getId()};
        xmwhjlService.update(sql, param);
        return "";
    }
    
    /**
     * @Title getSyztList 
     * @Description (获取使用状态集合) 
     * @return
     * @Return List<DICT_GY> 返回类型 
     * @Throws 
     * @Date  2017年11月17日
     * @修改历史  
     *     1. [2017年11月17日]创建文件 by 顾冲
     */
    @RequestMapping("/getSyztList")
    public @ResponseBody List<DICT_GY> getSyztList(){
        return dict_gyService.getDict_GYsByType("使用状态");//使用状态集合
    }
    
    /**
     * 根据xmid导出项目维护记录
     * @param request
     * @throws Exception 
     */
    @SuppressWarnings("unchecked") 
	@RequestMapping("/exportData")
    public void exportDataByCondition(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String, Object> condition) throws Exception{
    	//获取项目名称
    	Object jbxx = jbxxService.getDataById(condition.get("xmid") == null ? "" : (String)condition.get("xmid"));
		Map<String,Object> map=(Map<String,Object>) jbxx;
		String xmmc = map.get("xmmc") == null ? "" : (String)map.get("xmmc");
		String sheetName = xmmc + "使用维护信息";
		String fileName = sheetName + ".xls";
    	//sheet页名称集合
    	String[] sheetNameArr = {sheetName};
    	//各sheet页表头集合
    	List<String[]> headers = new ArrayList<String[]>();
    	//数据集
    	List<List<Object>> data = new ArrayList<List<Object>>();
    	//导出列集合
    	List<String[]> columnsArr = new ArrayList<String[]>();
    	// 维护记录
    	headers.add(XmwhjlService.header);
    	columnsArr.add(XmwhjlService.columns);
    	//把Jbxx表中的项目名称添加到data
    	List<Object> list=xmwhjlService.getDataByCondition(condition);
    	List<Object> xmwhjlList=new ArrayList<Object>();
    	for(Object obj : list){
    		Xmwhjl xmwhjl = (Xmwhjl)obj;
    		//xmwhjl.setXmmc(xmmc);
    		//xmwhjl.setXmbh(xmbh);
    		xmwhjlList.add(xmwhjl);
    	}
    	data.add(xmwhjlList);
    	String root = request.getSession().getServletContext().getRealPath("/dataExcel");
    	String filePath = root+File.separator+fileName;
    	File file = ExportUtil.createExcelForPoi(filePath,headers,sheetNameArr,data,columnsArr,"4");
    	downFile(response,fileName,file);
    }
    
    /**
     * @Title exportWhjlData 
     * @Description (导出维护记录) 
     * @param request
     * @param response
     * @throws Exception 
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年12月8日
     * @修改历史  
     *     1. [2017年12月8日]创建文件 by 顾冲
     */
    @RequestMapping("/exportWhjlData")
    public void exportWhjlData(HttpServletRequest request,HttpServletResponse response) throws Exception{
        User user = (User)request.getSession().getAttribute("loginUser");
        String area = user.getProvince();
        if("1".equals(user.getRoleName())){
            area = "全国";
        }
        String sheetName = area + "维护记录信息";
        String fileName = sheetName + ".xls";
        String[] sheetNameArr = {sheetName};
        //各sheet页表头集合
        List<String[]> headers = new ArrayList<String[]>();
        //数据集
        List<List<Object>> data = new ArrayList<List<Object>>();
        //导出列集合
        List<String[]> columnsArr = new ArrayList<String[]>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if("2".equals(user.getRoleName())){
            paramMap.put("szsf", area);
        }
        List<Object> list = xmwhjlService.getWhjl(paramMap);
        headers.add(XmwhjlService.header);
        columnsArr.add(XmwhjlService.columns);
        data.add(list);
        
        String root = request.getSession().getServletContext().getRealPath("/dataExcel");
        String filePath = root+File.separator+fileName +".";
        
        File file = ExportUtil.createExcelForPoi(filePath,headers,sheetNameArr,data,columnsArr, "4");
        downFile(response,fileName,file);
    }
    
    /**
     * @Title loadWhxmData 
     * @Description (分页查询加载维护项目) 
     * @Return String 返回类型 
     * @Throws 
     * @Date  2017年11月22日
     * @修改历史  
     *     1. [2017年11月22日]创建文件 by 顾冲
     */
    @RequestMapping("/loadWhxmData")
    public String loadWhxmData(HttpServletRequest req, ModelMap modelMap){
        User user = (User)req.getSession().getAttribute("loginUser");
        int rows = 5;
        int currentPage = 1;
        String pageString = req.getParameter("offiset");
        if(pageString != null && !pageString.equals("")){
            currentPage = Integer.parseInt(pageString); 
        }
        Map<String, Object> param = new HashMap<String,Object>();
        if("1".equals(user.getRoleName())){
            param.put("szsf", req.getParameter("szsf"));
        }else if("2".equals(user.getRoleName())){
            param.put("szsf", user.getProvince());
        }
        // 根据条件获取所有数据       
        List<Object> dataList=whxmService.getDataByCondition(param, currentPage, rows);
        // 获取分页后的数据        
        PageBean pageBean=getPageBean(currentPage, rows, dataList);
        modelMap.put("pageBean", pageBean);
        return "whxm";
    }
    
    /**
     * @Title queryWhxm 
     * @Description (分页查询维护项目) 
     * @param currentPage
     * @param modelMap
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2017年11月22日
     * @修改历史  
     *     1. [2017年11月22日]创建文件 by 顾冲
     */
    @RequestMapping("/queryWhxm")
    public @ResponseBody PageBean queryWhxm(HttpServletRequest request, ModelMap modelMap,
            @RequestParam(value = "offiset", required = false, defaultValue = "1") Integer currentPage){
        User user = (User)request.getSession().getAttribute("loginUser");
        int rows = 5;
        Map<String, Object> paramMap = new HashMap<String,Object>();
        if("1".equals(user.getRoleName())){
            paramMap.put("szsf", request.getParameter("szsf"));
        }else if("2".equals(user.getRoleName())){
            paramMap.put("szsf", user.getProvince());
        }
        //查询所有数据
        List<Object> dataList=whxmService.getDataByCondition(paramMap, currentPage, rows);
        //获取分页数据 
        return getPageBean(currentPage, rows, dataList);
    }
    
    /**
     * @Title getDestoryData 
     * @Description (获取损坏的项目信息) 
     * @param condition
     * @return
     * @Return Map<String, Object> 返回类型 
     * @Throws 
     * @Date  2017年11月22日
     * @修改历史  
     *     1. [2017年11月22日]创建文件 by 顾冲
     */
    @RequestMapping("/getDestoryData")
    public @ResponseBody Map<String, Object> getDestoryData(HttpServletRequest request){
        Map<String, Object> condition = new HashMap<String, Object>();
        User user = (User)request.getSession().getAttribute("loginUser");
        if("1".equals(user.getRoleName())){
            condition.put("szsf", request.getParameter("szsf"));
        }else if("2".equals(user.getRoleName())){
            condition.put("szsf", user.getProvince());
        }
        condition.put("syzt", "损坏");
        condition.put("bxxm", "否");
        condition.put("id", request.getParameter("id"));
        String xmzl = request.getParameter("xmzl");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Object> destoryDataList = new ArrayList<Object>();
        if("执勤道路".equals(xmzl)){
            //单位：公里  number 14，4
            destoryDataList = zqdlService.getDestroyData(condition);
            resultMap.put("dw", "公里");
        }else if("桥梁".equals(xmzl)){
            //单位：座  number
            destoryDataList = qiaoLiangService.getDestroyData(condition);
            resultMap.put("dw", "座");
        }else if("执勤码头".equals(xmzl)){
            //单位：座  number
            destoryDataList = zqmtService.getDestroyData(condition);
            resultMap.put("dw", "座");
        }else if("直升机停机坪".equals(xmzl)){
            //单位：个 number
            destoryDataList = zsjtjpService.getDestroyData(condition);
            resultMap.put("dw", "个");
        }else if("铁丝网".equals(xmzl)){
            //单位：公里  number 14，4
            destoryDataList = tswService.getDestroyData(condition);
            resultMap.put("dw", "公里");
        }else if("铁栅栏".equals(xmzl)){
            //单位：公里  number 14，4
            destoryDataList = tzlService.getDestroyData(condition);
            resultMap.put("dw", "公里");
        }else if("拒马".equals(xmzl)){
            //单位：个 number
            destoryDataList = jumaService.getDestroyData(condition);
            resultMap.put("dw", "个");
        }else if("拦阻桩".equals(xmzl)){
            //单位：个 number
            destoryDataList = lzzService.getDestroyData(condition);
            resultMap.put("dw", "个");
        }else if("隔离带".equals(xmzl)){
            //单位：米  number 14,4
            destoryDataList = gldService.getDestroyData(condition);
            resultMap.put("dw", "米");
        }else if("报警线路".equals(xmzl)){
            //单位：公里  number 14，4
            destoryDataList = bjxlService.getDestroyData(condition);
            resultMap.put("dw", "公里");
        }else if("监控中心".equals(xmzl)){
            //无建设规模
            destoryDataList = jkzxService.getDestroyData(condition);
            resultMap.put("dw", "个");
        }else if("监控站".equals(xmzl)){
            //无建设规模
            destoryDataList = jkzService.getDestroyData(condition);
            resultMap.put("dw", "个");
        }else if("传输线路".equals(xmzl)){
            //单位：米  number 14，4
            destoryDataList = csxlService.getDestroyData(condition);
            resultMap.put("dw", "米");
        }else if("供电系统".equals(xmzl)){
            //单位：套  number
            destoryDataList = gdxtService.getDestroyData(condition);
            resultMap.put("dw", "套");
        }else if("军警民联防平台".equals(xmzl)){
            //无建设规模
            destoryDataList = jjmlfptService.getDestroyData(condition);
            resultMap.put("dw", "套");
        }else if("无人值守电子哨兵".equals(xmzl)){
            //单位：套  number
            destoryDataList = wrzsdzsbService.getDestroyData(condition);
            resultMap.put("dw", "套");
        }else if("视频前端".equals(xmzl)){
            //无建设规模
            destoryDataList = spqdService.getDestroyData(condition);
            resultMap.put("dw", "个");
        }else if("显控终端".equals(xmzl)){
            //无建设规模
            destoryDataList = xkzdService.getDestroyData(condition);
            resultMap.put("dw", "个");
        }else if("报警装置".equals(xmzl)){
            //单位：套 number
            destoryDataList = bjzzService.getDestroyData(condition);
            resultMap.put("dw", "套");
        }else if("执勤房".equals(xmzl)){
            //单位：座  number
            destoryDataList = zqfService.getDestroyData(condition);
            resultMap.put("dw", "座");
        }else if("了望塔".equals(xmzl)){
            //单位：座  number
            destoryDataList = lwtService.getDestroyData(condition);
            resultMap.put("dw", "座");
        }else if("标志牌".equals(xmzl)){
            //单位：座 number
            destoryDataList = bzpService.getDestroyData(condition);
            resultMap.put("dw", "座");
        }
        resultMap.put("destoryDataList", destoryDataList);
        return resultMap;
    }
    
    /**
     * @Title addWhxm 
     * @Description (添加维护项目) 
     * @param req
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2017年12月11日
     * @修改历史  
     *     1. [2017年12月11日]创建文件 by 顾冲
     */
    @RequestMapping("/addWhxm")
    public @ResponseBody String addWhxm(HttpServletRequest req){
        List<Object> xmwhjlList = new ArrayList<Object>();
        String wxxmidStr = req.getParameter("wxxmidArr");
        String wxxmmcStr = req.getParameter("wxxmmcStr");
        String wxxmbhStr = req.getParameter("wxxmbhStr");
        String wxgmStr = req.getParameter("wxgmStr");
        String wxfyStr = req.getParameter("wxfyStr");
        String [] wxxmidArr = wxxmidStr.split(", ");
        String [] wxxmmcArr = wxxmmcStr.split(", ");
        String [] wxxmbhArr = wxxmbhStr.split(", ");
        String [] wxgmArr = wxgmStr.split("; ");
        String [] wxfyArr = wxfyStr.split(", ");
        String id = CommonUtil.createUUID();//维护项目id
        for(int i = 0; i < wxxmmcArr.length; i++ ){
            Xmwhjl xmwhjl = new Xmwhjl();
            xmwhjl.setId(CommonUtil.createUUID());
            xmwhjl.setWxxmid(wxxmidArr[i]);
            xmwhjl.setWxxmmc(wxxmmcArr[i]);
            xmwhjl.setWxxmbh(wxxmbhArr[i]);
            xmwhjl.setWxgm(wxgmArr[i]);
            xmwhjl.setWxfy(new BigDecimal(wxfyArr[i]));
            xmwhjl.setWhxmid(id);
            xmwhjl.setWhzt("维护未开始");
            xmwhjlList.add(xmwhjl);
        }
        String xmmc = req.getParameter("xmmc");
        String xmbh = req.getParameter("xmbh");
        String wxdw = req.getParameter("wxdw");
        BigDecimal wxzfy = new BigDecimal(req.getParameter("wxzfy") == null ? "" : req.getParameter("wxzfy"));
        User user = (User)req.getSession().getAttribute("loginUser");
        //String xmzl = req.getParameter("xmzl");
        Whxm whxm = new Whxm();
        whxm.setId(id);
        whxm.setWxxmmc(wxxmmcStr);
        whxm.setWxxmbh(wxxmbhStr);
        whxm.setWxgm(wxgmStr);
        whxm.setXmmc(xmmc);
        whxm.setXmbh(xmbh);
        whxm.setWxdw(wxdw);
        whxm.setWxzfy(wxzfy);
        whxm.setCreateTime(new Date());
        //whxm.setXmzl(xmzl);
        whxm.setSzsf(user.getProvince());
        whxmService.addWhxm(whxm);
        jbxxService.batchSave(xmwhjlList);//保存维护记录信息
        return "";
    }
    
    /**
     * @Title exportWhxmData 
     * @Description (导出维护记录) 
     * @param request
     * @param response
     * @throws Exception
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年12月11日
     * @修改历史  
     *     1. [2017年12月11日]创建文件 by 顾冲
     */
    @RequestMapping("/exportWhxmData")
    public void exportWhxmData(HttpServletRequest request,HttpServletResponse response) throws Exception{
        User user = (User)request.getSession().getAttribute("loginUser");
        String area = user.getProvince();
        if("1".equals(user.getRoleName())){
            area = "全国";
        }
        String sheetName = area + "维护项目信息";
        String fileName = sheetName + ".xls";
        String[] sheetNameArr = {sheetName};
        //各sheet页表头集合
        List<String[]> headers = new ArrayList<String[]>();
        //数据集
        List<List<Object>> data = new ArrayList<List<Object>>();
        //导出列集合
        List<String[]> columnsArr = new ArrayList<String[]>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if("2".equals(user.getRoleName())){
            paramMap.put("szsf", area);
        }
        List<Object> list = whxmService.getData(paramMap);
        headers.add(WhxmService.header);
        columnsArr.add(WhxmService.columns);
        data.add(list);
        
        String root = request.getSession().getServletContext().getRealPath("/dataExcel");
        String filePath = root+File.separator+fileName +".";
        
        File file = ExportUtil.createExcelForPoi(filePath,headers,sheetNameArr,data,columnsArr, "4");
        downFile(response,fileName,file);
    }
    
    /**
     * @Title getDataById 
     * @Description (这里用一句话描述这个方法的作用) 
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年12月22日
     * @修改历史  
     *     1. [2017年12月22日]创建文件 by 顾冲
     */
    @RequestMapping("/getDataById")
    public String getDataById(String id, ModelMap model){
        List<Object> list = whxmService.getDataById(id);
        model.put("whxm", list);
        List<DICT_GY> whztList = dict_gyService.getDict_GYsByType("维护状态");
        model.put("whztList", whztList);
        return "whxmEdit";
    }
    
    @RequestMapping("/updateWhxm")
    public @ResponseBody String updateWhxm(HttpServletRequest req){
        String xmmc = req.getParameter("xmmc");
        String whxmid = req.getParameter("whxmid");
        String xmbh = req.getParameter("xmbh");
        String wxdw = req.getParameter("wxdw");
        String whzt = req.getParameter("whzt");
        String whjlid = req.getParameter("whjlid");
        String [] whjlidArr = whjlid.split(",");//维护记录id用“,”分割
        String [] whztArr = whzt.split(",");//维护状态id用“,”分割
        String wxxmbhStr = req.getParameter("wxxmbh");
        String wxxmmcStr = req.getParameter("wxxmmc");
        String wxgmStr = req.getParameter("wxgm");
        //查询维护项目包含的项目子类
        List<Object>wxxmidList =  xmwhjlService.getWxxmidByWhxmid(whxmid);
        
        for (int i = 0; i < whztArr.length; i++) {
            String sql = "update xmwhjl set whzt = ? where id = ? ";
            xmwhjlService.update(sql, new Object []{whztArr[i], whjlidArr[i]});
        }
        String whxmSql = "update whxm set xmmc = ?, xmbh = ?, wxdw = ?, "
                + "wxxmbh = ?, wxxmmc = ?, wxgm = ? where id = ? ";
        whxmService.update(whxmSql, new Object []{xmmc, xmbh, wxdw, wxxmbhStr, wxxmmcStr, wxgmStr, whxmid});
        //如果项目子类的所以维护记录都维护完成，则项目子类的使用状态为良好
        for (Object object : wxxmidList) {
            long unComplentedcount = xmwhjlService.getUnCompletedCountByWxxmid((String)object);
            if(unComplentedcount == 0){
                jbxxService.updateSyztByXmid((String)object, "良好");
            }else{
                jbxxService.updateSyztByXmid((String)object, "损坏");
            }
        }
        return "";
    }

    /**
     * 获取pageBean
     * @param  currentPage
     * @param  rows
     * @param  dataList
     * @return pageBean
     */
    private PageBean getPageBean(Integer currentPage,Integer rows,List<Object> dataList){
    	int startRow = (currentPage-1)*rows;
	    int endRow = startRow+rows;
	    List<Object> list=new ArrayList<Object>();
	    for(int i = startRow;i<dataList.size()&&i<endRow;i++){
	        list.add(dataList.get(i));
	    }
	    PageBean pageBean=null;
	    if(dataList!=null&&dataList.size()>0){
	      pageBean=new PageBean(currentPage, rows, dataList.size(), list);
	    }	
		return pageBean;
    }
    
}
