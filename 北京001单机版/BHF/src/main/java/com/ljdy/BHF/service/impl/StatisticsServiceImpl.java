package com.ljdy.BHF.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.ConstantParam;
import com.ljdy.BHF.model.User;
import com.ljdy.BHF.service.BjxlService;
import com.ljdy.BHF.service.BjzzService;
import com.ljdy.BHF.service.BzpService;
import com.ljdy.BHF.service.CsxlService;
import com.ljdy.BHF.service.GdxtService;
import com.ljdy.BHF.service.GldService;
import com.ljdy.BHF.service.JbxxService;
import com.ljdy.BHF.service.JjmlfptService;
import com.ljdy.BHF.service.JkzService;
import com.ljdy.BHF.service.JkzxService;
import com.ljdy.BHF.service.JumaService;
import com.ljdy.BHF.service.LwtService;
import com.ljdy.BHF.service.LzzService;
import com.ljdy.BHF.service.QiaoLiangService;
import com.ljdy.BHF.service.SpqdService;
import com.ljdy.BHF.service.StatisticsService;
import com.ljdy.BHF.service.TswService;
import com.ljdy.BHF.service.TzlService;
import com.ljdy.BHF.service.WrzsdzsbService;
import com.ljdy.BHF.service.XkzdService;
import com.ljdy.BHF.service.ZqdlService;
import com.ljdy.BHF.service.ZqfService;
import com.ljdy.BHF.service.ZqmtService;
import com.ljdy.BHF.service.ZsjtjpService;
import com.ljdy.BHF.utils.ArithUtil;
import com.ljdy.BHF.utils.CommonUtil;
import com.ljdy.BHF.utils.DateUtils;

/**
 * @ClassName StatisticsServiceImpl
 * @Description (统计service实现类)
 * @Author 顾冲 guchong@luojiadeyi.com
 * @Date 2017年9月28日 下午7:45:27
 * @修改历史 1. [2017年9月28日]创建文件 by 顾冲
 */
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
    @Resource
    private ZqdlService zqdlService;

    @Resource
    private QiaoLiangService qiaoLiangService;

    @Resource
    private ZqmtService zqmtService;

    @Resource
    private ZsjtjpService zsjtjpService;

    @Resource
    private TswService tswService;

    @Resource
    private TzlService tzlService;

    @Resource
    private LzzService lzzService;

    @Resource
    private GldService gldService;

    @Resource
    private JumaService jumaService;

    @Resource
    private JkzxService jkzxService;

    @Resource
    private XkzdService xkzdService;

    @Resource
    private BjxlService bjxlService;

    @Resource
    private SpqdService spqdService;

    @Resource
    private CsxlService csxlService;

    @Resource
    private GdxtService gdxtService;

    @Resource
    private JjmlfptService jjmlfptService;

    @Resource
    private WrzsdzsbService wrzsdzsbService;

    @Resource
    private ZqfService zqfService;

    @Resource
    private LwtService lwtService;

    @Resource
    private BzpService bzpService;

    @Resource
    private BaseDao<Object> baseDao;

    @Resource
    private JbxxService jbxxService;
    
    @Resource
    private BjzzService bjzzService;
    
    @Resource
    private JkzService jkzService;
    
    /**
      * @Title CombinedJsgmData 
      * @Description (封装建设规模数据) 
      * @param tznd
      * @param partTag
      * @param loginUser
      * @return 
      * @see com.ljdy.BHF.service.StatisticsService#CombinedJsgmData(java.lang.String, java.lang.String, com.ljdy.BHF.model.User)
      * @Date  2017年10月24日
      * @修改历史  
      *     1. [2017年10月24日]创建文件 by 顾冲 
     *
     */
    @Override
    public Map<String, Object> CombinedJsgmData(String tznd, String partTag, User loginUser) {
        //备选项目
        String bxxm = null;
        if("1".equals(partTag) || "2".equals(partTag)){
            bxxm = "是";
        }else if("3".equals(partTag) || "4".equals(partTag)){
            bxxm = "否";
        }
        Map<String, Object> gjzbMap = new HashMap<String, Object>();// 建设规模总信息集合
        if (loginUser == null) {
            return null;
        }
        gjzbMap.put("year", tznd);
        //获取当前年前后五年list
        List<Integer> yearList = new ArrayList<Integer>();
        int currentYear = DateUtils.getYear();
        for(int i = currentYear -5 ; i < currentYear + 6 ; i++){
            yearList.add(i);
        }
        gjzbMap.put("yearList", yearList);
        String roleName = loginUser.getRoleName();
        Map<String, Object> jsgmParamMap = new HashMap<String, Object>();//建设规模参数
        jsgmParamMap.put("tznd", tznd);// 投资年度
        jsgmParamMap.put("roleName", loginUser.getRoleName());// 角色名称
        if("2".equals(roleName)){
            jsgmParamMap.put("szsf", loginUser.getProvince());// 所在省份
        }
        jsgmParamMap.put("bxxm", bxxm);

        // 投资参数
        Map<String, Object> dxtzParamMap = new HashMap<String, Object>();
        dxtzParamMap.put("tznd", tznd);
        dxtzParamMap.put("bxxm", bxxm);
        dxtzParamMap.put("roleName", roleName);
        if ("2".equals(roleName)) {
            dxtzParamMap.put("szsf", loginUser.getProvince());// 省级用户查询投资，需要传szsf（所在省份）,国家级不需要
        }
        List<Object> areaList = new ArrayList<Object>();
        if ("1".equals(roleName)) {
            areaList = getProvince();
            gjzbMap.put("bjHaxCdList", initBjHaxCd());// 边界海岸线长度数据
        } else if ("2".equals(roleName)) {
            areaList = getCityByProvince(loginUser.getProvince());
            //TODO 省级未处理边界线长度数据
            gjzbMap.put("bjHaxCdList", new ArrayList<Object>());// 边界海岸线长度数据
        }
        /**
         * 执勤道路
         */

        List<Object> zqdlList = zqdlService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> zqdlXjList = convertToList(zqdlList, areaList, ConstantParam.XJ, null, null, roleName);
        List<Object> zqdlSjList = convertToList(zqdlList, areaList, ConstantParam.SJ, null, null, roleName);
        List<Object> zqdlDxtzList = zqdlService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> zqdlXjTzMap = getTzFromList(zqdlDxtzList, ConstantParam.XJ, null, null);// 获取新建的投资
        Map<String, Object> zqdlSjTzMap = getTzFromList(zqdlDxtzList, ConstantParam.SJ, null, null);// 获取升级的投资

        addToList(zqdlXjList, zqdlXjTzMap);
        addToList(zqdlSjList, zqdlSjTzMap);
        gjzbMap.put("zqdlXj", zqdlXjList);
        gjzbMap.put("zqdlSj", zqdlSjList);
        /**
         * 桥梁
         */

        List<Object> qiaoLiangList = qiaoLiangService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> qiaoLiangListNew = convertToList(qiaoLiangList, areaList, null, null, null, roleName);
        List<Object> qialLiangDxtzList = qiaoLiangService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> qiaoLiangTzMap = getTzFromList(qialLiangDxtzList, null, null, null);// 获取投资
        addToList(qiaoLiangListNew, qiaoLiangTzMap);
        gjzbMap.put("qiaoLiang", qiaoLiangListNew);
        /**
         * 执勤码头
         */
        List<Object> zqmtList = zqmtService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> zqmtListNew = convertToList(zqmtList, areaList, null, null, null, roleName);
        List<Object> zqmtDxtzList = zqmtService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> zqmtTzMap = getTzFromList(zqmtDxtzList, null, null, null);// 获取投资
        addToList(zqmtListNew, zqmtTzMap);
        gjzbMap.put("zqmt", zqmtListNew);
        /**
         * 直升机坪
         */
        List<Object> zsjtjpList = zsjtjpService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> zsjtjpListNew = convertToList(zsjtjpList, areaList, null, null, null, roleName);
        List<Object> zsjtjpDxtzList = zsjtjpService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> zsjtjpTzMap = getTzFromList(zsjtjpDxtzList, null, null, null);// 获取投资
        addToList(zsjtjpListNew, zsjtjpTzMap);
        gjzbMap.put("zsjtjp", zsjtjpListNew);
        
        //交通保障设施小计
        List<Map<String, Object>> jtbzssList = new ArrayList<Map<String, Object>>();
        jtbzssList.add(zqdlXjTzMap);     
        jtbzssList.add(zqdlSjTzMap);
        jtbzssList.add(qiaoLiangTzMap);
        jtbzssList.add(zqmtTzMap);
        jtbzssList.add(zsjtjpTzMap);
        addSubTotal(gjzbMap, "jtbzssSub", jtbzssList);
        
        /**
         * 铁丝网
         */
        List<Object> tswList = tswService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> tswXjGdsList = convertToList(tswList, areaList, ConstantParam.XJ, ConstantParam.GDS, "tswlx", roleName);// 铁丝网新建固定式
        List<Object> tswXjYdsList = convertToList(tswList, areaList, ConstantParam.XJ, ConstantParam.YDS, "tswlx", roleName);// 铁丝网新建移动式
        List<Object> tswSjGdsList = convertToList(tswList, areaList, ConstantParam.SJ, ConstantParam.GDS, "tswlx", roleName);// 铁丝网升级固定式
        List<Object> tswSjYdsList = convertToList(tswList, areaList, ConstantParam.SJ, ConstantParam.YDS, "tswlx", roleName);// 铁丝网升级移动式

        List<Object> tswDxtzList = tswService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> tswXjGdsTzMap = getTzFromList(tswDxtzList, ConstantParam.XJ, ConstantParam.GDS, "tswlx");// 获取新建固定式投资
        Map<String, Object> tswXjYdsTzMap = getTzFromList(tswDxtzList, ConstantParam.XJ, ConstantParam.YDS, "tswlx");// 获取新建移动式投资
        Map<String, Object> tswSjGdsTzMap = getTzFromList(tswDxtzList, ConstantParam.SJ, ConstantParam.GDS, "tswlx");// 获取升级固定式投资
        Map<String, Object> tswSjYdsTzMap = getTzFromList(tswDxtzList, ConstantParam.SJ, ConstantParam.YDS, "tswlx");// 获取升级移动式投资

        addToList(tswXjGdsList, tswXjGdsTzMap);
        addToList(tswXjYdsList, tswXjYdsTzMap);
        addToList(tswSjGdsList, tswSjGdsTzMap);
        addToList(tswSjYdsList, tswSjYdsTzMap);
        gjzbMap.put("tswXjGds", tswXjGdsList);
        gjzbMap.put("tswXjYds", tswXjYdsList);
        gjzbMap.put("tswSjGds", tswSjGdsList);
        gjzbMap.put("tswSjYds", tswSjYdsList);

        /**
         * 铁栅栏
         */
        List<Object> tzlList = tzlService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> tzlListNew = convertToList(tzlList, areaList, null, null, null, roleName);
        List<Object> tzlDxtzList = tzlService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> tzlTzMap = getTzFromList(tzlDxtzList, null, null, null);// 获取投资
        addToList(tzlListNew, tzlTzMap);
        gjzbMap.put("tzl", tzlListNew);

        /**
         * 拦阻桩
         */
        List<Object> lzzList = lzzService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> lzzXjGdsList = convertToList(lzzList, areaList, ConstantParam.XJ, ConstantParam.GDS, "lzzlx", roleName);
        List<Object> lzzXjSjsList = convertToList(lzzList, areaList, ConstantParam.XJ, ConstantParam.SJS, "lzzlx", roleName);
        List<Object> lzzSjGdsList = convertToList(lzzList, areaList, ConstantParam.SJ, ConstantParam.GDS, "lzzlx", roleName);
        List<Object> lzzSjSjsList = convertToList(lzzList, areaList, ConstantParam.SJ, ConstantParam.SJS, "lzzlx", roleName);

        List<Object> lzzDxtzList = lzzService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> lzzXjGdsTzMap = getTzFromList(lzzDxtzList, ConstantParam.XJ, ConstantParam.GDS, "lzzlx");// 新建固定式投资
        Map<String, Object> lzzXjSjsTzMap = getTzFromList(lzzDxtzList, ConstantParam.XJ, ConstantParam.SJS, "lzzlx");// 新建升降式投资
        Map<String, Object> lzzSjGdsTzMap = getTzFromList(lzzDxtzList, ConstantParam.SJ, ConstantParam.GDS, "lzzlx");// 升级固定式投资
        Map<String, Object> lzzSjSjsTzMap = getTzFromList(lzzDxtzList, ConstantParam.SJ, ConstantParam.SJS, "lzzlx");// 升级升降式投资

        addToList(lzzXjGdsList, lzzXjGdsTzMap);
        addToList(lzzXjSjsList, lzzXjSjsTzMap);
        addToList(lzzSjGdsList, lzzSjGdsTzMap);
        addToList(lzzSjSjsList, lzzSjSjsTzMap);
        gjzbMap.put("lzzXjGds", lzzXjGdsList);
        gjzbMap.put("lzzXjSjs", lzzXjSjsList);
        gjzbMap.put("lzzSjGds", lzzSjGdsList);
        gjzbMap.put("lzzSjSjs", lzzSjSjsList);

        /**
         * 隔离带
         */
        List<Object> gldList = gldService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> gldListNew = convertToList(gldList, areaList, null, null, null, roleName);
        List<Object> gldDxtzList = gldService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> gldTzMap = getTzFromList(gldDxtzList, null, null, null);// 获取投资
        addToList(gldListNew, gldTzMap);
        gjzbMap.put("gld", gldListNew);

        /**
         * 警戒线路
         */
        List<Object> bjxlList = bjxlService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> bjxlListNew = convertToList(bjxlList, areaList, null, null, null, roleName);
        List<Object> bjxlDxtzList = bjxlService.getTz(dxtzParamMap);// 获取报警线路信息
        Map<String, Object> bjxlTzMap = getTzFromList(bjxlDxtzList, null, null, null);// 获取投资
        addToList(bjxlListNew, bjxlTzMap);
        gjzbMap.put("bjxl", bjxlListNew);

        /**
         * 拒马
         */
        List<Object> jumaList = jumaService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> jumaListNew = convertToList(jumaList, areaList, null, null, null, roleName);
        List<Object> jumaDxtzList = jumaService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> jumaTzMap = getTzFromList(jumaDxtzList, null, null, null);// 获取投资
        addToList(jumaListNew, jumaTzMap);
        gjzbMap.put("juma", jumaListNew);
                 
        //拦阻报警设施小计
        List<Map<String, Object>> lzbjssList = new ArrayList<Map<String, Object>>();
        lzbjssList.add(tswXjGdsTzMap);     
        lzbjssList.add(tswXjYdsTzMap);     
        lzbjssList.add(tswSjGdsTzMap);     
        lzbjssList.add(tswSjYdsTzMap);     
        lzbjssList.add(tzlTzMap);     
        lzbjssList.add(lzzXjGdsTzMap);     
        lzbjssList.add(lzzXjSjsTzMap);     
        lzbjssList.add(lzzSjGdsTzMap);     
        lzbjssList.add(lzzSjSjsTzMap);     
        lzbjssList.add(gldTzMap);     
        lzbjssList.add(bjxlTzMap);     
        lzbjssList.add(jumaTzMap);     
        
        addSubTotal(gjzbMap, "lzbjssSub", lzbjssList);

        /**
         * 监控中心
         */
        List<Object> jkzxList = jkzxService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> jkzxGjjXjList = convertToList(jkzxList, areaList, ConstantParam.XJ, ConstantParam.GJJ, "jb", roleName);
        List<Object> jkzxGjjSjList = convertToList(jkzxList, areaList, ConstantParam.SJ, ConstantParam.GJJ, "jb", roleName);
        List<Object> jkzxSjXjList = convertToList(jkzxList, areaList, ConstantParam.XJ, ConstantParam.SJI, "jb", roleName);
        List<Object> jkzxSjSjList = convertToList(jkzxList, areaList, ConstantParam.SJ, ConstantParam.SJI, "jb", roleName);
        List<Object> jkzxDjXjList = convertToList(jkzxList, areaList, ConstantParam.XJ, ConstantParam.DJ, "jb", roleName);
        List<Object> jkzxDjSjList = convertToList(jkzxList, areaList, ConstantParam.SJ, ConstantParam.DJ, "jb", roleName);
        List<Object> jkzxXjXjList = convertToList(jkzxList, areaList, ConstantParam.XJ, ConstantParam.XJI, "jb", roleName);
        List<Object> jkzxXjSjList = convertToList(jkzxList, areaList, ConstantParam.SJ, ConstantParam.XJI, "jb", roleName);

        List<Object> jkzxDxtzList = jkzxService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> jkzxGjjXjTzMap = getTzFromList(jkzxDxtzList, ConstantParam.XJ, ConstantParam.GJJ, "jb");// 获取国家级新建投资
        Map<String, Object> jkzxGjjSjTzMap = getTzFromList(jkzxDxtzList, ConstantParam.SJ, ConstantParam.GJJ, "jb");// 获取国家级升级投资
        Map<String, Object> jkzxSjXjTzMap = getTzFromList(jkzxDxtzList, ConstantParam.XJ, ConstantParam.SJI, "jb");// 获取省级新建投资
        Map<String, Object> jkzxSjSjTzMap = getTzFromList(jkzxDxtzList, ConstantParam.SJ, ConstantParam.SJI, "jb");// 获取省级升级投资
        Map<String, Object> jkzxDjXjTzMap = getTzFromList(jkzxDxtzList, ConstantParam.XJ, ConstantParam.DJ, "jb");// 获取地级新建投资
        Map<String, Object> jkzxDjSjTzMap = getTzFromList(jkzxDxtzList, ConstantParam.SJ, ConstantParam.DJ, "jb");// 获取地级升级投资
        Map<String, Object> jkzxXjXjTzMap = getTzFromList(jkzxDxtzList, ConstantParam.XJ, ConstantParam.XJI, "jb");// 获取县级新建投资
        Map<String, Object> jkzxXjSjTzMap = getTzFromList(jkzxDxtzList, ConstantParam.SJ, ConstantParam.XJI, "jb");// 获取县级升级投资

        addToList(jkzxGjjXjList, jkzxGjjXjTzMap);
        addToList(jkzxGjjSjList, jkzxGjjSjTzMap);
        addToList(jkzxSjXjList, jkzxSjXjTzMap);
        addToList(jkzxSjSjList, jkzxSjSjTzMap);
        addToList(jkzxDjXjList, jkzxDjXjTzMap);
        addToList(jkzxDjSjList, jkzxDjSjTzMap);
        addToList(jkzxXjXjList, jkzxXjXjTzMap);
        addToList(jkzxXjSjList, jkzxXjSjTzMap);
        gjzbMap.put("jkzxGjjXj", jkzxGjjXjList);
        gjzbMap.put("jkzxGjjSj", jkzxGjjSjList);
        gjzbMap.put("jkzxSjXj", jkzxSjXjList);
        gjzbMap.put("jkzxSjSj", jkzxSjSjList);
        gjzbMap.put("jkzxDjXj", jkzxDjXjList);
        gjzbMap.put("jkzxDjSj", jkzxDjSjList);
        gjzbMap.put("jkzxXjXj", jkzxXjXjList);
        gjzbMap.put("jkzxXjSj", jkzxXjSjList);
        
        /**
         * 监控站-监控站系统
         */
        // 获取建设规模信息
        List<Object> jkzxtList = jkzService.getJsgm(jsgmParamMap);
        List<Object> jkzxtXjList = convertToList(jkzxtList, areaList, ConstantParam.XJ, null, null, roleName);// 新建
        List<Object> jkzxtSjList = convertToList(jkzxtList, areaList, ConstantParam.SJ, null, null, roleName);// 升级
        // 获取投资信息
        List<Object> jkzxtDxtzList = jkzService.getTz(dxtzParamMap);
        Map<String, Object> jkzxtXjTzMap = getTzFromList(jkzxtDxtzList, ConstantParam.XJ, null, null);// 新建投资
        Map<String, Object> jkzxtSjTzMap = getTzFromList(jkzxtDxtzList, ConstantParam.SJ, null, null);// 升级投资
        // 将合计、平均投资、投资添加到map中
        addToList(jkzxtXjList, jkzxtXjTzMap);
        addToList(jkzxtSjList, jkzxtSjTzMap);

        gjzbMap.put("jkzxtXj", jkzxtXjList);
        gjzbMap.put("jkzxtSj", jkzxtSjList);
        
        /**
         * 监控站-显控终端
         */
        // 获取建设规模信息
        List<Object> xkzdList = xkzdService.getJsgm(jsgmParamMap);
        List<Object> xkzdXjList = convertToList(xkzdList, areaList, ConstantParam.XJ, null, null, roleName);// 新建
        List<Object> xkzdSjList = convertToList(xkzdList, areaList, ConstantParam.SJ, null, null, roleName);// 升级
        // 获取投资信息
        List<Object> xkzdDxtzList = xkzdService.getTz(dxtzParamMap);
        Map<String, Object> xkzdXjTzMap = getTzFromList(xkzdDxtzList, ConstantParam.XJ, null, null);// 新建投资
        Map<String, Object> xkzdSjTzMap = getTzFromList(xkzdDxtzList, ConstantParam.SJ, null, null);// 升级投资

        // 将合计、平均投资、投资添加到map中
        addToList(xkzdXjList, xkzdXjTzMap);
        addToList(xkzdSjList, xkzdSjTzMap);

        gjzbMap.put("xkzdXj", xkzdXjList);
        gjzbMap.put("xkzdSj", xkzdSjList);

        /**
         * 监控站-显控前端和移动前端（视频前端）
         */
        // 获取建设规模信息
        List<Object> spqdList = spqdService.getJsgm(jsgmParamMap);
        List<Object> gdqdXjList = convertToList(spqdList, areaList, ConstantParam.XJ, ConstantParam.GDS, "sblx", roleName);// 固定前端新建
        List<Object> gdqdSjList = convertToList(spqdList, areaList, ConstantParam.SJ, ConstantParam.GDS, "sblx", roleName);// 固定前端升级
        List<Object> ydqdXjList = convertToList(spqdList, areaList, ConstantParam.XJ, ConstantParam.YDS, "sblx", roleName);// 移动前端新建
        List<Object> ydqdSjList = convertToList(spqdList, areaList, ConstantParam.SJ, ConstantParam.YDS, "sblx", roleName);// 移动前端升级

        // 获取投资信息
        List<Object> spqdDxtzList = spqdService.getTz(dxtzParamMap);
        Map<String, Object> gdqdXjTzMap = getTzFromList(spqdDxtzList, ConstantParam.XJ, ConstantParam.GDS, "sblx");// 固定前端新建投资
        Map<String, Object> gdqdSjTzMap = getTzFromList(spqdDxtzList, ConstantParam.SJ, ConstantParam.GDS, "sblx");// 固定前端升级投资
        Map<String, Object> ydqdXjTzMap = getTzFromList(spqdDxtzList, ConstantParam.XJ, ConstantParam.YDS, "sblx");// 移动前端新建投资
        Map<String, Object> ydqdSjTzMap = getTzFromList(spqdDxtzList, ConstantParam.SJ, ConstantParam.YDS, "sblx");// 移动前端升级投资

        addToList(gdqdXjList, gdqdXjTzMap);
        addToList(gdqdSjList, gdqdSjTzMap);
        addToList(ydqdXjList, ydqdXjTzMap);
        addToList(ydqdSjList, ydqdSjTzMap);
        gjzbMap.put("gdqdXj", gdqdXjList);
        gjzbMap.put("gdqdSj", gdqdSjList);
        gjzbMap.put("ydqdXj", ydqdXjList);
        gjzbMap.put("ydqdSj", ydqdSjList);

        /**
         * 传输线路
         */
        // 获取建设规模信息
        List<Object> csxlList = csxlService.getJsgm(jsgmParamMap);
        List<Object> csxlListNew = convertToList(csxlList, areaList, null, null, null, roleName);

        // 获取投资信息
        List<Object> csxlDxtzList = csxlService.getTz(dxtzParamMap);
        Map<String, Object> csxlTzMap = getTzFromList(csxlDxtzList, null, null, null);// 获取投资

        addToList(csxlListNew, csxlTzMap);
        gjzbMap.put("csxl", csxlListNew);

        /**
         * 供电系统
         */
        List<Object> gdxtList = gdxtService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> gdxtListNew = convertToList(gdxtList, areaList, null, null, null, roleName);

        List<Object> gdxtDxtzList = gdxtService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> gdxtTzMap = getTzFromList(gdxtDxtzList, null, null, null);// 获取投资

        addToList(gdxtListNew, gdxtTzMap);
        gjzbMap.put("gdxt", gdxtListNew);

        /**
         * 军警民联防平台
         */
        List<Object> jjmlfptList = jjmlfptService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> jjmlfptListNew = convertToList(jjmlfptList, areaList, null, null, null, roleName);

        List<Object> jjmlfptDxtzList = jjmlfptService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> jjmlfptTzMap = getTzFromList(jjmlfptDxtzList, null, null, null);// 获取投资

        addToList(jjmlfptListNew, jjmlfptTzMap);
        gjzbMap.put("jjmlfpt", jjmlfptListNew);

        /**
         * 无人职守电子哨兵
         */
        List<Object> wrzsdzsbList = wrzsdzsbService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> wrzsdzsbListNew = convertToList(wrzsdzsbList, areaList, null, null, null, roleName);

        List<Object> wrzsdzsbDxtzList = wrzsdzsbService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> wrzsdzsbTzMap = getTzFromList(wrzsdzsbDxtzList, null, null, null);// 获取投资

        addToList(wrzsdzsbListNew, wrzsdzsbTzMap);
        gjzbMap.put("wrzsdzsb", wrzsdzsbListNew);
        
        /**
         * 报警装置
         */
        List<Object> bjzzList = bjzzService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> bjzzListNew = convertToList(bjzzList, areaList, null, null, null, roleName);

        List<Object> bjzzDxtzList = bjzzService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> bjzzTzMap = getTzFromList(bjzzDxtzList, null, null, null);// 获取投资

        addToList(bjzzListNew, bjzzTzMap);
        gjzbMap.put("bjzz", bjzzListNew);       
        
        //指挥监控设施小计
        List<Map<String, Object>> zhjkssList = new ArrayList<Map<String, Object>>();
        zhjkssList.add(jkzxGjjXjTzMap);     
        zhjkssList.add(jkzxGjjSjTzMap);     
        zhjkssList.add(jkzxSjXjTzMap);     
        zhjkssList.add(jkzxSjSjTzMap);     
        zhjkssList.add(jkzxDjXjTzMap);     
        zhjkssList.add(jkzxDjSjTzMap);     
        zhjkssList.add(jkzxXjXjTzMap);     
        zhjkssList.add(jkzxXjSjTzMap);     
        zhjkssList.add(jkzxtXjTzMap);     
        zhjkssList.add(jkzxtSjTzMap);     
        zhjkssList.add(xkzdXjTzMap);     
        zhjkssList.add(xkzdSjTzMap);     
        zhjkssList.add(gdqdXjTzMap);     
        zhjkssList.add(gdqdSjTzMap);     
        zhjkssList.add(ydqdXjTzMap);     
        zhjkssList.add(ydqdSjTzMap);     
        zhjkssList.add(csxlTzMap);     
        zhjkssList.add(gdxtTzMap);     
        zhjkssList.add(jjmlfptTzMap);     
        zhjkssList.add(wrzsdzsbTzMap);     
        zhjkssList.add(bjzzTzMap);     
        addSubTotal(gjzbMap, "zhjkssSub", zhjkssList);

        /**
         * 执勤房
         */
        List<Object> zqfList = zqfService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> zqfListNew = convertToList(zqfList, areaList, null, null, null, roleName);

        List<Object> zqfDxtzList = zqfService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> zqfTzMap = getTzFromList(zqfDxtzList, null, null, null);// 获取投资

        addToList(zqfListNew, zqfTzMap);
        gjzbMap.put("zqf", zqfListNew);

        /**
         * 瞭望塔
         */
        List<Object> lwtList = lwtService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> lwtListNew = convertToList(lwtList, areaList, null, null, null, roleName);

        List<Object> lwtDxtzList = lwtService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> lwtTzMap = getTzFromList(lwtDxtzList, null, null, null);// 获取投资

        addToList(lwtListNew, lwtTzMap);
        gjzbMap.put("lwt", lwtListNew);

        /**
         * 标志牌
         */
        List<Object> bzpList = bzpService.getJsgm(jsgmParamMap);// 获取建设规模信息
        List<Object> bzpListNew = convertToList(bzpList, areaList, null, null, null, roleName);

        List<Object> bzpDxtzList = bzpService.getTz(dxtzParamMap);// 获取投资信息
        Map<String, Object> bzpTzMap = getTzFromList(bzpDxtzList, null, null, null);// 获取投资

        addToList(bzpListNew, bzpTzMap);
        gjzbMap.put("bzp", bzpListNew);
        
        //辅助配套设施小计
        List<Map<String, Object>> fzptssList = new ArrayList<Map<String, Object>>();
        fzptssList.add(zqfTzMap);     
        fzptssList.add(lwtTzMap);     
        fzptssList.add(bzpTzMap);     
           
        addSubTotal(gjzbMap, "fzptssSub", fzptssList);

        /**
         * 各地投资
         */
        Map<String, Object> tzParam = new HashMap<String, Object>();//tznd、roleName、szsf、bxxm为参数中公共key，当返回结果集后其它key应该设置为空
        tzParam.put("tznd", tznd);
        tzParam.put("roleName", loginUser.getRoleName());
        tzParam.put("szsf", loginUser.getProvince());
        tzParam.put("bxxm", bxxm);
        
        
        //统计表投资
        List<Object> ztzGroupList = getTzGroupByArea(tzParam);
        Map<String, Object> ztzList = convertTzMap(ztzGroupList, areaList);
        gjzbMap.put("zytzList", ztzList.get("zytzList"));
        gjzbMap.put("dftzList", ztzList.get("dftzList"));
        gjzbMap.put("tzzeList", ztzList.get("tzzeList"));

        //查询投资参数，三种方式条件一样
        Map<String, Object> tzParamMap = new HashMap<String, Object>();
        tzParamMap.put("tznd", tznd);
        tzParamMap.put("roleName", loginUser.getRoleName());
        tzParamMap.put("szsf",loginUser.getProvince());
        //国家级用户需将szsf置为null
        if("1".equals(loginUser.getRoleName())){
            tzParamMap.put("szsf",null);
        }
        tzParamMap.put("bxxm", bxxm);
        
        
        /**
         * 根据地区分组单项投资
         */
        
        Map<String, Double> areaMap = getDxtzGroupByArea(tzParamMap, areaList, loginUser.getRoleName());
        gjzbMap.put("areaMap", areaMap);

        /**
         * 根据项目类别分组获取单项投资
         */
        Map<String, Double> xmlbMap = getDxtzGroupByXmlb(tzParamMap);
        gjzbMap.put("xmlbMap", xmlbMap);

        /**
         * 根据设施类型分组获取单项投资
         */
        Map<String, Double> sslxMap = getDxtzGroupBySslx(tzParamMap);
        gjzbMap.put("sslxMap", sslxMap);
        return gjzbMap;
    }

    /**
     * @Title getTzFromList
     * @Description (获取集合中的投资)
     * @param list
     *            分组查询的数据结果集
     * @param jsxz
     *            建设类型 ，为“新建”或者升级,根据jsxz分组的为新建或者升级，没有则为null
     * @param qtfl
     *            其它分类，拦阻桩（qtfl为“固定式”或者“升降式”） 监控中心（qtfl为“省级”、“国家级”或者“地县”） 视频前端（qtfl为“固定”或者“移动”）
     *            铁丝网（当jsxz为“新建”时，qtlx为“固定式”或者“升降式”；当jsxz为“升级”时，qtfl为null）,按照qtfl分组则赋值，没有则为null
     * @param qtflKey
     *            其它分类key， lzzlx(拦阻桩类型 ) jb(监控中心级别) sblx(视频前端设备类型) tswlx(铁丝网类型),其余为null
     * @return
     * @Return Map 返回类型
     * @Throws
     * @Date 2017年9月28日
     * @修改历史 1. [2017年9月28日]创建文件 by 顾冲
     */
    public Map<String, Object> getTzFromList(List<Object> list, String jsxz, String qtfl, String qtflKey) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        double zyztSum = 0.0;
        double dftzSum = 0.0;
        double dxtzSum = 0.0;
        for (Object object : list) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) object;
            // 判断建设性质是否相同
            if (jsxz != null && jsxz.trim() != "" && !jsxz.equals(map.get("jsxz"))) {
                continue;
            }
            // 判断其它分类是否相同
            if (qtfl != null && !qtfl.equals(map.get(qtflKey))) {
                continue;
            }
            zyztSum += map.get("zytz") == null ? 0.0 : (double)map.get("zytz");
            dftzSum += map.get("dftz") == null ? 0.0 : (double)map.get("dftz");
            dxtzSum += map.get("dxtz") == null ? 0.0 : (double)map.get("dxtz");
        }
        resultMap.put("zytz", zyztSum);
        resultMap.put("dftz", dftzSum);
        resultMap.put("dxtz", dxtzSum);
        return resultMap;
    }

    /**
     * @Title convertToList
     * @Description (将分组结果接转换成需要的list) 如list[0]=jsgm0 list[1]=jsgm1
     * @param groupResultList 分组查询结果集，分组字段jsxz和qtfz
     * @param jsxz
     *            建设性质，新建或者升级，按照建设性质分组查询的结果集需要传值，没有则为null
     * @param qtfl
     *            其它分组，按照其它分组分组查询的结果集需要传值，没有则为null
     * @param qtflKey
     *            分组字段， lzzlx(拦阻桩类型 ) jb(监控中心级别) sblx(视频前端设备类型) tswlx(铁丝网类型)，其它为null
     * @param areaList
     *            有序的地区集合
     * @param roleName
     *            用户角色，判断结果集是根据jszf（所在省份）还是szcs（所在城市）分组
     * @return
     * @Return List<Object> 返回类型
     * @Throws
     * @Date 2017年9月30日
     * @修改历史 1. [2017年9月30日]创建文件 by 顾冲
     */
    public List<Object> convertToList(List<Object> groupResultList, List<Object> areaList, String jsxz, String qtfz, String qtfzKey, String roleName) {
        List<Object> resultList = new ArrayList<Object>();
        String szdqKey = "1".equals(roleName) ? "szsf" : "szcs";// 根据所在省份或者所在城市分组
        if(areaList != null && !areaList.isEmpty()){
            for (Object area : areaList) {// 地区和建设规模在其对应的集合中的下标一致
                BigDecimal jsgm = new BigDecimal(0);
                for (Object object : groupResultList) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map = (Map<String, Object>) object;
                    if (jsxz != null && jsxz != "" && !jsxz.equals(map.get("jsxz"))) {
                        continue;
                    }
                    if (qtfz != null && qtfz != "" && !qtfz.equals(map.get(qtfzKey))) {
                        continue;
                    }
                    if(map.get("jsgm") == null){
                        continue;
                    }
                    if (area.equals(map.get(szdqKey))) {
                        if(map.get("jsgm") instanceof BigDecimal){
                            BigDecimal bd = (BigDecimal) map.get("jsgm");
                            jsgm = jsgm.add(bd);
                        }else if(map.get("jsgm") instanceof Long){
                            BigDecimal bd = BigDecimal.valueOf((long)map.get("jsgm"));
                            jsgm = jsgm.add(bd);
                        }
                    }
                }
                resultList.add(jsgm);
            }
        }else{//部分省份没有边防市
            BigDecimal jsgm = new BigDecimal(0);
            for (Object object : groupResultList) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                if (jsxz != null && jsxz != "" && !jsxz.equals(map.get("jsxz"))) {
                    continue;
                }
                if (qtfz != null && qtfz != "" && !qtfz.equals(map.get(qtfzKey))) {
                    continue;
                }
                if(map.get("jsgm") == null){
                    continue;
                }
                if(map.get("jsgm") instanceof BigDecimal){
                    jsgm = jsgm.add((BigDecimal)map.get("jsgm"));
                }else if(map.get("jsgm") instanceof Long){
                    jsgm = jsgm.add(BigDecimal.valueOf((long)map.get("jsgm")));
                }
            }
            resultList.add(jsgm);
        }
        return resultList;
    }

    /**
     * @Title addToList
     * @Description (将数据按照列表顺序添加到集合中)
     * @param list list实质类型为List<BigDecimal>
     * @param tzMap
     * @Return void 返回类型
     * @Throws
     * @Date 2017年10月16日
     * @修改历史 1. [2017年10月16日]创建文件 by 顾冲
     */
    public void addToList(List<Object> list, Map<String, Object> tzMap) {
        BigDecimal heji = new BigDecimal(0);
        BigDecimal avg = new BigDecimal(0);
        for (Object obj : list) {
            if (obj == null) {
                continue;
            }
            heji = heji.add((BigDecimal)obj);
        }
        Double dxtz = tzMap.get("dxtz") == null ? 0.0 : (Double) tzMap.get("dxtz");
        if(heji.compareTo(BigDecimal.ZERO) != 0){
            avg = ArithUtil.div(BigDecimal.valueOf(dxtz), heji, 4);
        }
        // 添加顺序不可变，与页面保持一致
        list.add(heji);
        list.add(avg);
        list.add(dxtz);
        list.add(tzMap.get("zytz"));
        list.add(tzMap.get("dftz"));
    }

    /**
     * @Title initCityMap
     * @Description (初始化某个省的城市)
     * @param szfs
     *            所在省份
     * @return
     * @Return Map<String,String> 返回类型
     * @Throws
     * @Date 2017年9月30日
     * @修改历史 1. [2017年9月30日]创建文件 by 顾冲
     */
    public Map<String, String> initCityMap(String szfs) {
        Map<String, String> cityMap = new HashMap<String, String>();
        cityMap.put("roleName", "2");
        return cityMap;
    }

    /**
     * @Title getCityByProvince
     * @Description (查询某省的城市)
     * @param province
     * @return
     * @see com.ljdy.BHF.service.StatisticsService#getCityByProvince(java.lang.String)
     * @Date 2017年10月9日
     * @修改历史 1. [2017年10月9日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getCityByProvince(String province) {
        StringBuffer hql = new StringBuffer(
                "select new map(a.codeName as codeName) from DICT_AREA a,DICT_AREA b  where b.codeName like ? and b.codeValue=a.superCode order by a.orderCode");
        List<Object> param = new ArrayList<Object>();
        param.add("%"+ province + "%");
        List<Object> resultList = baseDao.find(hql.toString(), param);
        List<Object> areaList = new ArrayList<Object>();
        for (Object object : resultList) {
            if (object == null) {
                continue;
            }
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) object;
            areaList.add(map.get("codeName"));
        }
        return areaList;
    }

    /**
     * @Title getProvince
     * @Description (获取省份集合)
     * @return
     * @see com.ljdy.BHF.service.StatisticsService#getProvince()
     * @Date 2017年10月9日
     * @修改历史 1. [2017年10月9日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getProvince() {
        StringBuffer hql = new StringBuffer(
                "select new map(a.codeName as codeName) from DICT_AREA a where a.superCode is null and a.codeName is not null");
        List<Object> queryList = baseDao.find(hql.toString());
        List<Object> areaList = new ArrayList<Object>();
        List<Object> resultList = new ArrayList<Object>();
        for (Object object : queryList) {
            if (object == null) {
                continue;
            }
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) object;
            areaList.add(map.get("codeName"));
        }
        List<Object> sfjcList = getProvinceShort();
        for (Object sfjc : sfjcList) {
            Object obj = null;
            for (Object area : areaList) {
                if (area.toString().contains(sfjc.toString())) {
                    obj = area;
                }
            }
            resultList.add(obj);
        }
        return resultList;
    }

    /**
     * @Title getTzGroupByArea
     * @Description (根据地区分组获取投资)
     * @param param
     *            查询参数
     * @return
     * @see com.ljdy.BHF.service.StatisticsService#getSbtzByArea(java.lang.String)
     * @Date 2017年10月9日
     * @修改历史 1. [2017年10月9日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getTzGroupByArea(Map<String, Object> param) {
        // rolename必传,省级用户必传所在省份
        String roleName = "";
        if (CommonUtil.validCondition(param, "roleName")) {
            roleName = (String) param.get("roleName");
        }
        List<Object> paramList = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("select new map(sum(j.dftz+j.zytz) as tzze, sum(j.dftz) as dftz, sum(j.zytz) as zytz");
        if ("1".equals(roleName)) {
            hql.append(" , j.szsf as area");
        } else if ("2".equals(roleName)) {
            hql.append(" , j.szcs as area");
        }
        hql.append(") from Jbxx j where 1=1");
        if(CommonUtil.validCondition(param, "bxxm")){
            hql.append(" and j.bxxm = ?");
            paramList.add(param.get("bxxm"));
        }
        if (CommonUtil.validCondition(param, "tznd")) {
            paramList.add(param.get("tznd"));
            hql.append(" and j.tznd = ?");
        }
        if ("1".equals(roleName)) {
            hql.append(" group by j.szsf");
        } else if ("2".equals(roleName)) {
            hql.append(" and j.szsf = ? ");
            paramList.add(param.get("szsf"));
            hql.append(" group by j.szcs");
        }
        return baseDao.find(hql.toString(), paramList);
    }

    /**
     * @Title convertTzMap
     * @Description (将投资金额集合转换成页面需要的类型)
     * @param resultList 分组结果集
     * @param areaList  地区集合
     * @return
     * @Return List<Object> 返回类型
     * @Throws
     * @Date 2017年10月9日
     * @修改历史 1. [2017年10月9日]创建文件 by 顾冲
     */
    public Map<String, Object> convertTzMap(List<Object> resultList, List<Object> areaList) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Double> zytzList = new ArrayList<Double>();// 中央投资
        List<Double> dftzList = new ArrayList<Double>();// 地方投资
        List<Double> tzzeList = new ArrayList<Double>();// 投资总额
        if(areaList != null && !areaList.isEmpty()){
            for (Object object : areaList) {
                double zytz = 0.0;
                double dftz = 0.0;
                double tzze = 0.0;
                for (Object obj : resultList) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map = (Map<String, Object>) obj;
                    if (object.equals(map.get("area"))) {
                        zytz = map.get("zytz") == null ? 0.0 : (Double)map.get("zytz");
                    }
                    if (object.equals(map.get("area"))) {
                        dftz = map.get("dftz") == null ? 0.0 : (Double)map.get("dftz");
                    }
                    if (object.equals(map.get("area"))) {
                        tzze = map.get("tzze") == null ? 0.0 : (Double)map.get("tzze");
                    }
                }
                zytzList.add(zytz);
                dftzList.add(dftz);
                tzzeList.add(tzze);
            }
        }else{
            double zytz = 0.0;
            double dftz = 0.0;
            double tzze = 0.0;
            for (Object obj : resultList) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) obj;
                    zytz = map.get("zytz") == null ? 0.0 : (Double)map.get("zytz");
                    dftz = map.get("dftz") == null ? 0.0 : (Double)map.get("dftz");
                    tzze = map.get("tzze") == null ? 0.0 : (Double)map.get("tzze");
            }
            zytzList.add(zytz);
            dftzList.add(dftz);
            tzzeList.add(tzze);
        }
        
        // 投资金额总计
        Double zytzSum = 0.0;
        Double dftzSum = 0.0;
        Double tzzeSum = 0.0;
        for (Double object : zytzList) {
            if (object != null) {
                zytzSum = ArithUtil.add(zytzSum, object);
            }
        }
        for (Double object : dftzList) {
            if (object != null) {
                dftzSum = ArithUtil.add(dftzSum, object);
            }
        }
        for (Double object : tzzeList) {
            if (object != null) {
                tzzeSum = ArithUtil.add(tzzeSum, object);
            }
        }
        zytzList.add(zytzSum);
        dftzList.add(dftzSum);
        tzzeList.add(tzzeSum);
        resultMap.put("zytzList", zytzList);
        resultMap.put("dftzList", dftzList);
        resultMap.put("tzzeList", tzzeList);
        return resultMap;
    }

    /**
     * @Title getDxtzGroupByArea
     * @Description (根据地区分组获取单项投资)
     * @param paramMap
     * @param areaList
     * @return
     * @Return Map<String,Double> 返回类型
     * @Throws
     * @Date 2017年10月9日
     * @修改历史 1. [2017年10月9日]创建文件 by 顾冲
     */
    @Override
    public Map<String, Double> getDxtzGroupByArea(Map<String, Object> paramMap, List<Object> areaList, String roleName) {
        // TODO 只对国家级用户进行了地区简写处理，省级用户未做地区简写处理
        List<Object> sfsxList = getProvinceShort();// 省份简称集合
        List<Object> queryList = jbxxService.getDxtzGroupByArea(paramMap);
        Map<String, Double> resultMap = new HashMap<String, Double>();
        if(areaList != null && !areaList.isEmpty()){
            for (Object area : areaList) {
                if (area == null || area.toString() == "") {
                    continue;
                }
                double value = 0.0;
                for (Object object : queryList) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map = (Map<String, Object>) object;
                    if (area.equals(map.get("area"))) {
                        value = map.get("dxtz") == null ? 0.0 : (Double)map.get("dxtz");
                    }
                }
                Object areaSx = area;
                if ("1".equals(roleName)) {
                    for (Object obj : sfsxList) {
                        if (area.toString().indexOf(obj.toString()) >= 0) {
                            areaSx = obj;
                        }
                    }
                }
                resultMap.put((String)areaSx, value);
            }
           
        }else{
            double value = 0.0;
            for (Object object : queryList) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                double dxtz = map.get("dxtz") == null ? 0.0 : (Double)map.get("dxtz");
                value = ArithUtil.add(value, dxtz);
            }
            resultMap.put(paramMap.get("szsf") != null ? (String)paramMap.get("szsf") : "", value);//无边防市以省替代
        }
        return sortMap(resultMap);
    }

    /**
     * @Title getDxtzGroupByXmlb
     * @Description (根据项目类别分组获取单项投资)
     * @param paramMap
     *            查询参数
     * @return
     * @Return Map<String,Double> 返回类型
     * @Throws
     * @Date 2017年10月9日
     * @修改历史 1. [2017年10月9日]创建文件 by 顾冲
     */
    @Override
    public Map<String, Double> getDxtzGroupByXmlb(Map<String, Object> paramMap) {
        Map<String, Double> resultMap = new HashMap<String, Double>();
        List<Object> queryList = jbxxService.getDxtzGroupByXmlb(paramMap);
        List<Object> xmlbList = getXmlb();
        for (Object xmlb : xmlbList) {
            double value = 0.0;
            if (xmlb == null || "".equals(((String)xmlb).trim())) {
                continue;
            }
            for (Object object : queryList) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                if (xmlb.equals(map.get("xmlb"))) {
                    value = map.get("dxtz") == null ? 0.0 :  (Double)map.get("dxtz");
                }
            }
            resultMap.put((String)xmlb, value);
        }
        return sortMap(resultMap);
    }
    
    /**
     * @Title getDxtzGroupBySslx
     * @Description (根据设施类型获取单项投资)
     * @param paramMap
     * @return
     * @Return Map<String,Double> 返回类型
     * @Throws
     * @Date 2017年10月9日
     * @修改历史 1. [2017年10月9日]创建文件 by 顾冲
     */
    @Override
    public Map<String, Double> getDxtzGroupBySslx(Map<String, Object> paramMap) {
        List<Object> queryList = jbxxService.getDxtzGroupBySslx(paramMap);
        Map<String, Double> resultMap = new HashMap<String, Double>();
        List<Object> sslxList = getSslx();
        for (Object sslx : sslxList) {
            if (sslx == null || sslx.toString() == "") {
                continue;
            }
            double value = 0.0;
            for (Object object : queryList) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                if (sslx.equals(map.get("sslx"))) {
                    value = map.get("dxtz") == null ? 0.0 : (Double)map.get("dxtz");
                }
            }
            resultMap.put((String)sslx, value);
        }
        return sortMap(resultMap);
    }
    
    /**
     * @Title getXmlb
     * @Description (获取项目类别)
     * @return
     * @Return List<Object> 返回类型
     * @Throws
     * @Date 2017年10月9日
     * @修改历史 1. [2017年10月9日]创建文件 by 顾冲
     */
    public List<Object> getXmlb() {
        List<Object> list = baseDao.find("select new map(d.codeName as xmlb) from DICT_GY d where d.typeName = '项目类别' order by orderCode");
        List<Object> resultList = new ArrayList<Object>();
        for (Object object : list) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) object;
            resultList.add(map.get("xmlb"));
        }
        return resultList;
    }

    /**
     * @Title getSslx
     * @Description (获取设施类型)
     * @return
     * @Return List<Object> 返回类型
     * @Throws
     * @Date 2017年10月9日
     * @修改历史 1. [2017年10月9日]创建文件 by 顾冲
     */
    public List<Object> getSslx() {
        List<Object> list = baseDao.find("select new map(d.codeName as sslx) from DICT_GY d where d.typeName = '设施类型' order by orderCode");
        List<Object> resultList = new ArrayList<Object>();
        for (Object object : list) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) object;
            resultList.add(map.get("sslx"));
        }
        return resultList;
    }

    /**
     * @Title getProvinceShort
     * @Description (获取省份简称)
     * @return
     * @Return List<Object> 返回类型
     * @Throws
     * @Date 2017年10月10日
     * @修改历史 1. [2017年10月10日]创建文件 by 顾冲
     */
    @Override
    public List<Object> getProvinceShort() {
        List<Object> sfzslbList = new ArrayList<Object>();
        sfzslbList.add("辽宁");
        sfzslbList.add("吉林");
        sfzslbList.add("黑龙江");
        sfzslbList.add("内蒙古");
        sfzslbList.add("甘肃");
        sfzslbList.add("新疆");
        sfzslbList.add("河北");
        sfzslbList.add("天津");
        sfzslbList.add("山东");
        sfzslbList.add("江苏");
        sfzslbList.add("上海");
        sfzslbList.add("浙江");
        sfzslbList.add("福建");
        sfzslbList.add("广东");
        sfzslbList.add("海南");
        sfzslbList.add("广西");
        sfzslbList.add("西藏");
        sfzslbList.add("云南");
        return sfzslbList;
    }

    /**
     * @Title initBjHaxCd
     * @Description 初始化边界、海岸线长度 （与省份展示列顺序要一致,各省的边界、海岸线是固定的）
     * @return
     * @Return List<Object> 返回类型
     * @Throws
     * @Date 2017年10月11日
     * @修改历史 1. [2017年10月11日]创建文件 by 顾冲
     */
    public List<Object> initBjHaxCd() {
        List<Object> list = new ArrayList<Object>();
        list.add(2404.3);
        list.add(1385);
        list.add(2981);
        list.add(4262);
        list.add(65);
        list.add(6771);
        list.add(478.3);
        list.add(133.4);
        list.add(3024.4);
        list.add(1039.7);
        list.add(167.8);
        list.add(2253.7);
        list.add(3323.6);
        list.add(4314.1);
        list.add(1642.6);
        list.add(2115.2);
        list.add(2795);
        list.add(3278);
        list.add(42434.1);
        return list;
    }
    
    /**
     * @Title sortMap 
     * @Description (根据key值对map排序) 
     * @param unsortMap
     * @return
     * @Return Map<String,Double> 返回类型 
     * @Throws 
     * @Date  2017年10月19日
     * @修改历史  
     *     1. [2017年10月19日]创建文件 by 顾冲
     */
    public Map<String, Double> sortMap(Map<String, Double> unsortMap) {
        List<Entry<String, Double>> list = new ArrayList<Entry<String,Double>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                double q1 = o1.getValue();
                double q2 = o2.getValue();
                double p = q2-q1;
                if(p > 0){
                    return 1;
                }else if(p == 0){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        Map<String, Double> sortMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> set : list) {
            sortMap.put(set.getKey(), set.getValue());
        }
        return sortMap;
    }
    
    /**
      * @Title getDftzAndZytz 
      * @Description (根据投资年度、所在省份等条件，地区分组获取中央投资和地方投资) 
      * @param param
      * @param areaList
      * @return 
      * @see com.ljdy.BHF.service.StatisticsService#getDftzAndZytzGroupByArea(java.util.Map, java.util.List)
      * @Date  2017年10月19日
      * @修改历史  
      *     1. [2017年10月19日]创建文件 by 顾冲 
     *
     */
    @Override
    public Map<String, Object> getDftzAndZytzGroupByArea(Map<String, Object> param, List<Object> areaList){
        List<Object> resultList = jbxxService.getDftzAndZytzGroupByArea(param);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Double> zytzMap = new HashMap<String, Double>();// 中央投资
        Map<String, Double> dftzMap = new HashMap<String, Double>();// 地方投资
        for (Object object : areaList) {
            Object zytz = null;
            Object dftz = null;
            for (Object obj : resultList) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) obj;
                if (object.equals(map.get("szcs"))) {
                    zytz = map.get("zytz");
                    dftz = map.get("dftz");
                }
            }
            zytzMap.put((String)object, zytz == null ?  0.0 : (Double)zytz);
            dftzMap.put((String)object, dftz == null ?  0.0 : (Double)dftz);
        }
        resultMap.put("zytz", sortMap(zytzMap));
        resultMap.put("dftz", sortMap(dftzMap));
        return resultMap;
    }

    
    /**
      * @Title getDftzAndZytzGroupByXmlb 
      * @Description (根据省份等条件，项目类别分组获取中央投资和地方投资) 
      * @param param
      * @return 
      * @see com.ljdy.BHF.service.StatisticsService#getDftzAndZytzGroupByXmlb(java.util.Map, java.util.List)
      * @Date  2017年10月19日
      * @修改历史  
      *     1. [2017年10月19日]创建文件 by 顾冲 
     *
     */
    @Override
    public Map<String, Object> getDftzAndZytzGroupByXmlb(Map<String, Object> param) {
        List<Object> resultList = jbxxService.getZytzAndDftzGroupByXmlb(param);
        List<Object> xmlbList = getXmlb();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Double> zytzMap = new HashMap<String, Double>();// 中央投资
        Map<String, Double> dftzMap = new HashMap<String, Double>();// 地方投资
        for (Object object : xmlbList) {
            Object zytz = null;
            Object dftz = null;
            for (Object obj : resultList) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) obj;
                if (object.equals(map.get("xmlb"))) {
                    zytz = map.get("zytz");
                    dftz = map.get("dftz");
                }
            }
            zytzMap.put((String)object, zytz == null ?  0.0 : (Double)zytz);
            dftzMap.put((String)object, dftz == null ?  0.0 : (Double)dftz);
        }
        resultMap.put("zytz", zytzMap);
        resultMap.put("dftz", dftzMap);
        return resultMap;
    }
    
    /**
      * @Title getDftzAndZytz 
      * @Description (根据省份、投资年度等条件获取中央投资和地方投资) 
      * @param param
      * @return 
      * @see com.ljdy.BHF.service.StatisticsService#getDftzAndZytz(java.util.Map)
      * @Date  2017年10月20日
      * @修改历史  
      *     1. [2017年10月20日]创建文件 by 顾冲 
     *
     */
    @Override
    public Map<String, Object> getDftzAndZytz(Map<String, Object> paramMap) {
        List<Object> param = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("select new map(sum(j.dftz) as dftz, sum(j.zytz) as zytz) from Jbxx j where 1=1");
        if(CommonUtil.validCondition(paramMap, "szsf")){
            hql.append(" and j.szsf like ?");
            param.add("%" + paramMap.get("szsf") + "%");
        }
        if(CommonUtil.validCondition(paramMap, "szcs")){
            hql.append(" and j.szcs like ?");
            param.add("%" + paramMap.get("szcs") + "%");
        }
        if(CommonUtil.validCondition(paramMap, "bxxm")){
            hql.append(" and j.bxxm = ?");
            param.add(paramMap.get("bxxm"));
        }
        if(CommonUtil.validCondition(paramMap, "tznd")){
            hql.append(" and j.tznd = ?");
            param.add(paramMap.get("tznd"));
        }
        List<Object> list =  baseDao.find(hql.toString(), param);
        for (Object obj : list) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>)obj;
            return map;
        }
        return null;
    }
    /**
     * @Title addSubTotal 
     * @Description (计算小计,并添加到返回结果集中) 
     * @param resultMap
     * @param subKey
     * @param list
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年11月2日
     * @修改历史  
     *     1. [2017年11月2日]创建文件 by 顾冲
     */
    protected void addSubTotal(Map<String, Object> resultMap, String subKey, List<Map<String, Object>> list){
        List<Object> xjList = new ArrayList<Object>();
        double subZytz = 0.0;
        double subDftz = 0.0;
        double subDxtz = 0.0;
        for (Map<String, Object> map : list) {
            double zytz = map.get("zytz") == null ? 0.0 : (double)map.get("zytz");
            double dftz = map.get("zytz") == null ? 0.0 : (double)map.get("dftz");
            double dxtz = map.get("zytz") == null ? 0.0 : (double)map.get("dxtz");
            subZytz = ArithUtil.add(subZytz, zytz);
            subDftz = ArithUtil.add(subDftz, dftz);
            subDxtz = ArithUtil.add(subDxtz, dxtz);
        }
        //添加顺序不可变，与页面一致   单项投资、中央投资、地方投资
        xjList.add(subDxtz);
        xjList.add(subZytz);
        xjList.add(subDftz);
        resultMap.put(subKey, xjList);
    }
    
    /**
     * @Title sumList 
     * @Description (list求和) 
     * @param list  list不为空，list中元素的长度一致，且元素里面的值不为null
     * @return
     * @Return List<Double> 返回类型 
     * @Throws 
     * @Date  2017年11月8日
     * @修改历史  
     *     1. [2017年11月8日]创建文件 by 顾冲
     */
    protected List<Double> sumList(List<List<Double>> list) {
        List<Double> resultList = new ArrayList<Double>();
        for(int i = 0; i< list.get(0).size(); i++){
            double sum = 0.0;
            for(List<Double> objList : list){
                sum = ArithUtil.add(sum, objList.get(i));
            }
            resultList.add(sum);
        } 
        return resultList;
    }
    
    
    /**
     * @Title getTzGroupByArea 
     * @Description (根据区域获取投资) 
     * @param groupResultList
     * @param areaList
     * @return
     * @Return Map<String,List<Double>> 返回类型 
     * @Throws 
     * @Date  2017年11月8日
     * @修改历史  
     *     1. [2017年11月8日]创建文件 by 顾冲
     */
    protected Map<String, List<Double>> getTzGroupByArea(List<Object> groupResultList, List<Object> areaList){
        Map<String, List<Double>> resultMap = new HashMap<String, List<Double>>();
        List<Double> dxtzList = new ArrayList<Double>();
        List<Double> zytzList = new ArrayList<Double>();
        List<Double> dftzList = new ArrayList<Double>();
        if(areaList != null && !areaList.isEmpty()){
            for(Object area : areaList){
                double dxtz = 0.0;
                double zytz = 0.0;
                double dftz = 0.0;
                for (Object object : groupResultList) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map = (Map<String, Object>) object;
                    double dxtzNew = (Double)map.get("dxtz");
                    double zytzNew = (Double)map.get("zytz");
                    double dftzNew = (Double)map.get("dftz");
                    Object obj = map.get("area");
                    if(obj != null && obj.equals(area)){
                        dxtz = ArithUtil.add(dxtz, dxtzNew);
                        zytz = ArithUtil.add(zytz, zytzNew);
                        dftz = ArithUtil.add(dftz, dftzNew);
                    }
                }
                dxtzList.add(dxtz);
                zytzList.add(zytz);
                dftzList.add(dftz);
            }
        }else{
            double dxtz = 0.0;
            double zytz = 0.0;
            double dftz = 0.0;
            for (Object object : groupResultList) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                double dxtzNew = (Double)map.get("dxtz");
                double zytzNew = (Double)map.get("zytz");
                double dftzNew = (Double)map.get("dftz");
                dxtz = ArithUtil.add(dxtz, dxtzNew);
                zytz = ArithUtil.add(zytz, zytzNew);
                dftz = ArithUtil.add(dftz, dftzNew);
            }
            dxtzList.add(dxtz);
            zytzList.add(zytz);
            dftzList.add(dftz);
        }
        resultMap.put("dxtz", dxtzList);
        resultMap.put("zytz", zytzList);
        resultMap.put("dftz", dftzList);
        return resultMap;
    }
    
}
