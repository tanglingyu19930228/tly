package com.ljdy.BHF.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.ljdy.BHF.model.ProvinceTabelData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ljdy.BHF.model.User;
import com.ljdy.BHF.utils.ArithUtil;
import com.ljdy.BHF.utils.DateUtils;

/**
 * 年度计划模块统计建设规模 单项投资总额的控制层
 * 
 * @author 郎川
 * @date 2017年9月27日 修改记录 [2017-09-28] 添加年度计划模块单项投资总额的方法 by 郎川
 */

@Controller
@RequestMapping("annualPlan")
public class AnnualPlanController extends BaseController{

    /**
     * @Title load
     * @Description (统筹规划主页面数据加载)
     * @param req
     * @return
     * @Return String 返回类型
     * @Throws
     * @Date 2017年9月25日
     * @修改历史 1. [2017年9月25日]创建文件 by 顾冲
     */
    @RequestMapping("/load")
    public String load(HttpServletRequest req, ModelMap model) {
        String tznd = req.getParameter("tznd");
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        String partTag = req.getParameter("partTag");
        if ("2".equals(partTag)) {
            tznd = String.valueOf(DateUtils.getYear());
        }
        model.put("partTag", partTag);
        model.put("year", tznd);
        String roleName = loginUser.getRoleName();
        if ("1".equals(roleName)) {
            Map<String, Object> resultMap = statisticsService.CombinedJsgmData(tznd, partTag, loginUser);
            List<Object> sfList = statisticsService.getProvinceShort();
            model.put("gjzbMap", resultMap);
            model.put("areaList", sfList);
            model.put("roleName", loginUser.getRoleName());
            return "index";
        } else if ("2".equals(roleName)) {
            Map<String, Object> resultMap = statisticsService.CombinedJsgmData(tznd, partTag ,loginUser);
            List<Object> cityList = statisticsService.getCityByProvince(loginUser.getProvince());
            model.put("szsf", loginUser.getProvince());
            model.put("gjzbMap", resultMap);
            if(cityList == null || cityList.isEmpty()){
                cityList.add(loginUser.getProvince());
            }
            if("上海市".equals(loginUser.getProvince()) || "天津市".equals(loginUser.getProvince())){
            	model.put("areaList", loginUser.getProvince());
            }else{
            	model.put("areaList", cityList);
            }
            model.put("roleName", loginUser.getRoleName());
            //TODO 计算市所在td宽度
            int i = (cityList == null || cityList.isEmpty() ? 1 : cityList.size()) + 5;
            model.put("tWidth4", ArithUtil.div(85.8, (double)i, 3).toString()+"%");
            model.put("data",tableData(loginUser,tznd));
            return "sjIndex";
        }
        return "login";
    }

    /**
     * 省报表数据
     * @param partTag
     * @param user
     * @param tznd
     * @return
     */
    private Map<String,Object> tableData(User user,String tznd){
        Map<String,Object> data = new HashMap<String, Object>();
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("tznd", tznd);
        condition.put("szsf", user.getProvince());
        condition.put("bxxm","是");
        //建设投资合计、中央投资合计、地方投资合计
        Map<String, Object> tzHjMap = jbxxService.getTzByArea(condition);
        data.put("tzHjMap", tzHjMap);

        condition.put("jsxz", "新建");
        /**
         * 交通保障设施
         */
        //新建执勤道路
        List<Object> xj_zqdl_data = zqdlService.getData(condition);
        ProvinceTabelData xj_zqdl = new ProvinceTabelData("新建", xj_zqdl_data);
        data.put("xj_zqdl", xj_zqdl);

        //新建桥梁
        List<Object> xj_qiaoliang_data = qiaoLiangService.getData(condition);
        ProvinceTabelData xj_qiaoliang = new ProvinceTabelData("新建", xj_qiaoliang_data);
        data.put("xj_qiaoliang", xj_qiaoliang);

        //新建执勤码头
        List<Object> xj_zqmt_data = zqmtService.getData(condition);
        ProvinceTabelData xj_zqmt = new ProvinceTabelData("新建" ,xj_zqmt_data);
        data.put("xj_zqmt", xj_zqmt);

        //新建直升机停机坪
        List<Object> xj_zsjtjp_data = zsjtjpService.getData(condition);
        ProvinceTabelData xj_zsjtjp = new ProvinceTabelData("新建", xj_zsjtjp_data);
        data.put("xj_zsjtjp", xj_zsjtjp);

        condition.put("jsxz", "升级");
        //升级执勤道路
        List<Object> sj_zqdl_data = zqdlService.getData(condition);
        ProvinceTabelData sj_zqdl = new ProvinceTabelData("升级", sj_zqdl_data);
        data.put("sj_zqdl", sj_zqdl);

        //升级桥梁
        List<Object> sj_qiaoliang_data = qiaoLiangService.getData(condition);
        ProvinceTabelData sj_qiaoliang = new ProvinceTabelData("升级", sj_qiaoliang_data);
        data.put("sj_qiaoliang", sj_qiaoliang);

        //升级执勤码头
        List<Object> sj_zqmt_data = zqmtService.getData(condition);
        ProvinceTabelData sj_zqmt = new ProvinceTabelData("升级", sj_zqmt_data);
        data.put("sj_zqmt", sj_zqmt);

        //升级直升机停机坪
        List<Object> sj_zsjtjp_data = zsjtjpService.getData(condition);
        ProvinceTabelData sj_zsjtjp = new ProvinceTabelData("升级", sj_zsjtjp_data);
        data.put("sj_zsjtjp", sj_zsjtjp);

        int zqdl_row = xj_zqdl.getRows() + sj_zqdl.getRows();
        data.put("zqdl_row", zqdl_row);
        int qiaoliang_row = xj_qiaoliang.getRows()+sj_qiaoliang.getRows();
        data.put("qiaoliang_row", qiaoliang_row);
        int zqmt_row = xj_zqmt.getRows()+sj_zqmt.getRows();
        data.put("zqmt_row", zqmt_row);
        int zsjtjp_row = xj_zsjtjp.getRows()+sj_zsjtjp.getRows();
        data.put("zsjtjp_row", zsjtjp_row);
        int jtss_rows = zqdl_row+qiaoliang_row+zqmt_row+zsjtjp_row;
        data.put("jtss_rows", jtss_rows);

        /**
         * 拦阻报警设施
         */
        condition.put("jsxz", "新建");
        //新建铁丝网
        List<Object> xj_tsw_data = tswService.getData(condition);
        ProvinceTabelData xj_tsw = new ProvinceTabelData("新建", xj_tsw_data);
        data.put("xj_tsw", xj_tsw);

        //新建铁栅栏
        List<Object> xj_tzl_data = tzlService.getData(condition);
        ProvinceTabelData xj_tzl = new ProvinceTabelData("新建", xj_tzl_data);
        data.put("xj_tzl", xj_tzl);

        //新建拦阻桩
        List<Object> xj_lzz_data = lzzService.getData(condition);
        ProvinceTabelData xj_lzz = new ProvinceTabelData("新建", xj_lzz_data);
        data.put("xj_lzz", xj_lzz);

        //新建隔离带
        List<Object> xj_gld_data = gldService.getData(condition);
        ProvinceTabelData xj_gld = new ProvinceTabelData("新建", xj_gld_data);
        data.put("xj_gld", xj_gld);

        //新建拒马
        List<Object> xj_juma_data = jumaService.getData(condition);
        ProvinceTabelData xj_juma = new ProvinceTabelData("新建", xj_juma_data);
        data.put("xj_juma", xj_juma);

        //新建报警线路
        List<Object> xj_bjxl_data = bjxlService.getData(condition);
        ProvinceTabelData xj_bjxl = new ProvinceTabelData("新建", xj_bjxl_data);
        data.put("xj_bjxl", xj_bjxl);

        condition.put("jsxz", "升级");
        //升级铁丝网
        List<Object> sj_tsw_data = tswService.getData(condition);
        ProvinceTabelData sj_tsw = new ProvinceTabelData("升级",sj_tsw_data);
        data.put("sj_tsw", sj_tsw);

        //升级铁栅栏
        List<Object> sj_tzl_data = tzlService.getData(condition);
        ProvinceTabelData sj_tzl = new ProvinceTabelData("升级",sj_tzl_data);
        data.put("sj_tzl", sj_tzl);

        //升级拦阻桩
        List<Object> sj_lzz_data = lzzService.getData(condition);
        ProvinceTabelData sj_lzz = new ProvinceTabelData("升级", sj_lzz_data);
        data.put("sj_lzz", sj_lzz);

        //升级隔离带
        List<Object> sj_gld_data = gldService.getData(condition);
        ProvinceTabelData sj_gld = new ProvinceTabelData("升级", sj_gld_data);
        data.put("sj_gld", sj_gld);

        //升级拒马
        List<Object> sj_juma_data = jumaService.getData(condition);
        ProvinceTabelData sj_juma = new ProvinceTabelData("升级", sj_juma_data);
        data.put("sj_juma", sj_juma);

        //升级报警线路
        List<Object> sj_bjxl_data = bjxlService.getData(condition);
        ProvinceTabelData sj_bjxl = new ProvinceTabelData("升级", sj_bjxl_data);
        data.put("sj_bjxl", sj_bjxl);

        int tsw_row = xj_tsw.getRows() + sj_tsw.getRows();
        data.put("tsw_row", tsw_row);

        int tzl_row = xj_tzl.getRows()+sj_tzl.getRows();
        data.put("tzl_row", tzl_row);

        int juma_row = xj_juma.getRows()+sj_juma.getRows();
        data.put("juma_row", juma_row);

        int lzz_row = xj_lzz.getRows()+sj_lzz.getRows();
        data.put("lzz_row", lzz_row);

        int gld_row = xj_gld.getRows()+sj_gld.getRows();
        data.put("gld_row", gld_row);

        int bjxl_row = xj_bjxl.getRows()+sj_bjxl.getRows();
        data.put("bjxl_row", bjxl_row);

        int lzss_rows = tsw_row + tzl_row + juma_row + lzz_row + gld_row + bjxl_row;
        data.put("lzss_rows", lzss_rows);

        /**
         * 指挥监控设施
         */
        condition.put("jsxz", "新建");

        //新建国家级监控中心
        condition.put("jb", "国家级");
        List<Object> xj_jkzxGjj_data = jkzxService.getData(condition);
        ProvinceTabelData xj_jkzxGjj = new ProvinceTabelData("新建", xj_jkzxGjj_data);
        data.put("xj_jkzxGjj", xj_jkzxGjj);

        //新建省级监控中心
        condition.put("jb", "省级");
        List<Object> xj_jkzxSj_data = jkzxService.getData(condition);
        ProvinceTabelData xj_jkzxSj = new ProvinceTabelData("新建", xj_jkzxSj_data);
        data.put("xj_jkzxSj", xj_jkzxSj);

        //新建地级监控中心
        condition.put("jb", "地级");
        List<Object> xj_jkzxDj_data = jkzxService.getData(condition);
        ProvinceTabelData xj_jkzxDj = new ProvinceTabelData("新建", xj_jkzxDj_data);
        data.put("xj_jkzxDj", xj_jkzxDj);

        //新建县级监控中心
        condition.put("jb", "县级");
        List<Object> xj_jkzxXj_data = jkzxService.getData(condition);
        ProvinceTabelData xj_jkzxXj = new ProvinceTabelData("新建", xj_jkzxXj_data);
        data.put("xj_jkzxXj", xj_jkzxXj);
        condition.put("jb", null);

        //新建监控站
        List<Object> xj_jkz_data = jkzService.getData(condition);
        ProvinceTabelData xj_jkz = new ProvinceTabelData("新建", xj_jkz_data);
        data.put("xj_jkz", xj_jkz);

        //新建传输线路
        List<Object> xj_csxl_data = csxlService.getData(condition);
        ProvinceTabelData xj_csxl = new ProvinceTabelData("新建", xj_csxl_data);
        data.put("xj_csxl", xj_csxl);

        //新建供电系统
        List<Object> xj_gdxt_data = gdxtService.getData(condition);
        ProvinceTabelData xj_gdxt = new ProvinceTabelData("新建", xj_gdxt_data);
        data.put("xj_gdxt", xj_gdxt);

        //新建军警民联防平台
        List<Object> xj_jjmlfpt_data = jjmlfptService.getData(condition);
        ProvinceTabelData xj_jjmlfpt = new ProvinceTabelData("新建", xj_jjmlfpt_data);
        data.put("xj_jjmlfpt", xj_jjmlfpt);

        //新建无人值守电子哨兵
        List<Object> xj_wrzsdzsb_data = wrzsdzsbService.getData(condition);
        ProvinceTabelData xj_wrzsdzsb = new ProvinceTabelData("新建", xj_wrzsdzsb_data);
        data.put("xj_wrzsdzsb", xj_wrzsdzsb);

        //新建视频前端
        List<Object> xj_spqd_data = spqdService.getData(condition);
        ProvinceTabelData xj_spqd = new ProvinceTabelData("新建", xj_spqd_data);
        data.put("xj_spqd", xj_spqd);

        //新建显控制端
        List<Object> xj_xkzd_data = xkzdService.getData(condition);
        ProvinceTabelData xj_xkzd = new ProvinceTabelData("新建", xj_xkzd_data);
        data.put("xj_xkzd", xj_xkzd);

        //新建报警装置
        List<Object> xj_bjzz_data = bjzzService.getData(condition);
        ProvinceTabelData xj_bjzz = new ProvinceTabelData("新建", xj_bjzz_data);
        data.put("xj_bjzz", xj_bjzz);

        condition.put("jsxz", "升级");

        //升级国家级监控中心
        condition.put("jb", "国家级");
        List<Object> sj_jkzxGjj_data = jkzxService.getData(condition);
        ProvinceTabelData sj_jkzxGjj = new ProvinceTabelData("升级", sj_jkzxGjj_data);
        data.put("sj_jkzxGjj", sj_jkzxGjj);

        //升级省级监控中心
        condition.put("jb", "省级");
        List<Object> sj_jkzxSj_data = jkzxService.getData(condition);
        ProvinceTabelData sj_jkzxSj = new ProvinceTabelData("升级", sj_jkzxSj_data);
        data.put("sj_jkzxSj", sj_jkzxSj);

        //升级地级中心
        condition.put("jb", "地级");
        List<Object> sj_jkzxDj_data = jkzxService.getData(condition);
        ProvinceTabelData sj_jkzxDj = new ProvinceTabelData("升级", sj_jkzxDj_data);
        data.put("sj_jkzxDj", sj_jkzxDj);

        //升级县级中心
        condition.put("jb", "县级");
        List<Object> sj_jkzxXj_data = jkzxService.getData(condition);
        ProvinceTabelData sj_jkzxXj = new ProvinceTabelData("升级", sj_jkzxXj_data);
        data.put("sj_jkzxXj", sj_jkzxXj);
        condition.put("jb", null);

        //升级监控站
        List<Object> sj_jkz_data = jkzService.getData(condition);
        ProvinceTabelData sj_jkz = new ProvinceTabelData("升级", sj_jkz_data);
        data.put("sj_jkz", sj_jkz);

        //升级传输线路
        List<Object> sj_csxl_data = csxlService.getData(condition);
        ProvinceTabelData sj_csxl = new ProvinceTabelData("升级", sj_csxl_data);
        data.put("sj_csxl", sj_csxl);

        //升级供电系统
        List<Object> sj_gdxt_data = gdxtService.getData(condition);
        ProvinceTabelData sj_gdxt = new ProvinceTabelData("升级", sj_gdxt_data);
        data.put("sj_gdxt", sj_gdxt);

        //升级军警民联防平台
        List<Object> sj_jjmlfpt_data = jjmlfptService.getData(condition);
        ProvinceTabelData sj_jjmlfpt = new ProvinceTabelData("升级", sj_jjmlfpt_data);
        data.put("sj_jjmlfpt", sj_jjmlfpt);

        //升级无人值守电子哨兵
        List<Object> sj_wrzsdzsb_data = wrzsdzsbService.getData(condition);
        ProvinceTabelData sj_wrzsdzsb = new ProvinceTabelData("升级", sj_wrzsdzsb_data);
        data.put("sj_wrzsdzsb", sj_wrzsdzsb);

        //升级视频前端
        List<Object> sj_spqd_data = spqdService.getData(condition);
        ProvinceTabelData sj_spqd = new ProvinceTabelData("升级", sj_spqd_data);
        data.put("sj_spqd", sj_spqd);

        //升级显控制端
        List<Object> sj_xkzd_data = xkzdService.getData(condition);
        ProvinceTabelData sj_xkzd = new ProvinceTabelData("升级", sj_xkzd_data);
        data.put("sj_xkzd", sj_xkzd);

        //升级报警装置
        List<Object> sj_bjzz_data = bjzzService.getData(condition);
        ProvinceTabelData sj_bjzz = new ProvinceTabelData("升级", sj_bjzz_data);
        data.put("sj_bjzz", sj_bjzz);

        int jkzxGjj_row = xj_jkzxGjj.getRows() + sj_jkzxGjj.getRows();
        data.put("jkzxGjj_row", jkzxGjj_row);

        int jkzxSj_row = xj_jkzxSj.getRows() + sj_jkzxSj.getRows();
        data.put("jkzxSj_row", jkzxSj_row);

        int jkzxDj_row = xj_jkzxDj.getRows() + sj_jkzxDj.getRows();
        data.put("jkzxDj_row", jkzxDj_row);

        int jkzxXj_row = xj_jkzxXj.getRows() + sj_jkzxXj.getRows();
        data.put("jkzxXj_row", jkzxXj_row);

        int jkzx_row = jkzxGjj_row + jkzxSj_row + jkzxDj_row + jkzxXj_row;
        data.put("jkzx_row", jkzx_row);

        int jkz_row = xj_jkz.getRows()+sj_jkz.getRows();
        data.put("jkz_row", jkz_row);

        int csxl_row = xj_csxl.getRows()+sj_csxl.getRows();
        data.put("csxl_row", csxl_row);

        int gdxt_row = xj_gdxt.getRows()+sj_gdxt.getRows();
        data.put("gdxt_row", gdxt_row);

        int jjmlfpt_row = xj_jjmlfpt.getRows()+sj_jjmlfpt.getRows();
        data.put("jjmlfpt_row", jjmlfpt_row);

        int wrzsdzsb_row = xj_wrzsdzsb.getRows()+sj_wrzsdzsb.getRows();
        data.put("wrzsdzsb_row", wrzsdzsb_row);

        int spqd_row = xj_spqd.getRows()+sj_spqd.getRows();
        data.put("spqd_row", spqd_row);

        int xkzd_row = xj_xkzd.getRows()+sj_xkzd.getRows();
        data.put("xkzd_row", xkzd_row);

        int bjzz_row = xj_bjzz.getRows()+sj_bjzz.getRows();
        data.put("bjzz_row", bjzz_row);

        int zhjkss_rows = jkzx_row + jkz_row + csxl_row + gdxt_row + jjmlfpt_row + wrzsdzsb_row + spqd_row + xkzd_row + bjzz_row;
        data.put("zhjkss_rows", zhjkss_rows);

        /**
         * 辅助配套设施
         */
        condition.put("jsxz", "新建");
        //新建瞭望塔
        List<Object> xj_zqf_data = zqfService.getData(condition);
        ProvinceTabelData xj_zqf = new ProvinceTabelData("新建", xj_zqf_data);
        data.put("xj_zqf", xj_zqf);

        //新建了望塔
        List<Object> xj_lwt_data = lwtService.getData(condition);
        ProvinceTabelData xj_lwt = new ProvinceTabelData("新建", xj_lwt_data);
        data.put("xj_lwt", xj_lwt);

        //新建标志牌
        List<Object> xj_bzp_data = bzpService.getData(condition);
        ProvinceTabelData xj_bzp = new ProvinceTabelData("新建", xj_bzp_data);
        data.put("xj_bzp", xj_bzp);

        condition.put("jsxz", "升级");
        //升级瞭望塔
        List<Object> sj_zqf_data = zqfService.getData(condition);
        ProvinceTabelData sj_zqf = new ProvinceTabelData("升级", sj_zqf_data);
        data.put("sj_zqf", sj_zqf);

        //升级了望塔
        List<Object> sj_lwt_data = lwtService.getData(condition);
        ProvinceTabelData sj_lwt = new ProvinceTabelData("升级", sj_lwt_data);
        data.put("sj_lwt", sj_lwt);

        //升级标志牌
        List<Object> sj_bzp_data = bzpService.getData(condition);
        ProvinceTabelData sj_bzp = new ProvinceTabelData("升级", sj_bzp_data);
        data.put("sj_bzp", sj_bzp);

        int zqf_row = xj_zqf.getRows() + sj_zqf.getRows();
        data.put("zqf_row", zqf_row);

        int lwt_row = xj_lwt.getRows() + sj_lwt.getRows();
        data.put("lwt_row", lwt_row);

        int bzp_row = xj_bzp.getRows() + sj_bzp.getRows();
        data.put("bzp_row", bzp_row);

        int fzptss_rows = zqf_row + lwt_row + bzp_row;
        data.put("fzptss_rows", fzptss_rows);

        return data;
    }
}
