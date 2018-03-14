<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head lang="en">
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <!--让ie使用兼容性模式-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>首页</title>
    <link rel="icon" href="BHF.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="themes/default/easyui.css" />
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/index.css">
    <!--引入bootstrap样式-->
	<link rel="stylesheet" href="css/bootstrap_progress.min.css">
	<link rel="stylesheet" href="css/provinceTable.css">
	<link rel="stylesheet" href="css/provinceTable_info.css">
    <!--引入上传插件样式-->
	<link rel="stylesheet" href="css/webuploader.css">
    <script src="js/libs/jquery-1.8.3.min.js"></script>
    <script src="highCharts/highcharts.js"></script>
    <script src="highCharts/highcharts-3d.js"></script>
    <script src="js/libs/jquery.easyui.min.js"></script>
    <!--引入jquery-validte插件-->
    <script src="js/libs/jquery.validate.js"></script>
    <!--jquery-validte中文包-->
    <script src="js/libs/messages_zh.js"></script>
    <!--编辑规划投资总额操作-->
    <script src="js/editAmount.js"></script>
    <!--控制图表切换 国家级-->
    <script src="js/chartsToggle.js"></script>
    <!--国家级图表-->
    <script src="js/country_charts.js"></script>
    <!--控制图表切换 与省级公用-->
    <script src="js/index.js"></script>
    <!--上传插件-->
	<script src="js/libs/webuploader.min.js"></script>
	<!--导入文件js-->
	<script src="js/importFile.js"></script>    
    <script type="text/javascript">
    	$(function() {
    		window.onresize=function() {
    			window.location.reload();
    		};
            //轮播按钮点击事件
           	$(".p_nav .p_list li").click(function(){
                if(!$(this).find("span").hasClass("active")){
                    tabToggle(true, [[$("#container"), option1], [$("#container4"), option2], [$("#container1"), option3], [$("#container2"), option4], [$("#container3"), option5]], toggleFlag);
                }
           	});
    	});
 		//鼠标滚动时切换图表
        function scrollFunc(e) {
           /*  var downTrue, enable = false;//downTrue表示是否为向下滚动，enable表示是否调用事件
            e = e || window.event;
            if (e.wheelDelta) {  //判断浏览器IE，谷歌滑轮事件
                if (e.wheelDelta > 0) { //当滑轮向上滚动时
                    if (!toggleFlag) {
                        downTrue = false;
                        enable = true;
                    }
                }
                if (e.wheelDelta < 0) { //当滑轮向下滚动时
                    if (toggleFlag) {
                        downTrue = true;
                        enable = true;
                    }
                }
            } else if (e.detail) {  //Firefox滑轮事件
                if (e.detail < 0) { //当滑轮向上滚动时
                    if (!toggleFlag) {
                        downTrue = false;
                        enable = true;
                    }
                }
                if (e.detail > 0) { //当滑轮向下滚动时
                    if (toggleFlag) {
                        downTrue = true;
                        enable = true;
                    }
                }
            }
            if (enable) {
                tabToggle(true, [[$("#container"), option1], [$("#container4"), option2], [$("#container1"), option3], [$("#container2"), option4], [$("#container3"), option5]], downTrue);
            } */
        }
        //给页面绑定滑轮滚动事件
        if (document.addEventListener) {
            document.addEventListener('DOMMouseScroll', scrollFunc, false);
        }
        //滚动滑轮触发scrollFunc方法
         window.onmousewheel =document.onmousewheel = scrollFunc;
        //根据投资年度统计
        function changeTznd(obj){
        	$("#postForm").empty();
        	var f=$("#postForm")[0];
            f.action='annualPlan/load';
            f.innerHTML='<input type="hidden" name="tznd" value="'+$(obj).val()+'"/>'
            +'<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
            f.submit();
        }
    </script>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<input type="hidden" id="role" value="${roleName}">
<div class="main">
	<ol class="navTip breadcrumb">
		<li><a href="user/toWelcome">首页</a></li>
		<c:if test="${partTag eq '1'}"><li class="active">全国统筹规划报表</li></c:if>
		<c:if test="${partTag eq '2' }"><li class="active">全国年度计划报表</li></c:if>
	</ol>
    <div class="tab">
        <div class="tab_div" style="overflow:hidden;min-width: 1780px;">
            <!--统计表-->
            <div class="tab_cont" style="overflow-y: hidden;">
                <div class="chart_panel excel_panel">
                    <div class="cp_header" style="position: relative;">
                    	<span id="title">全国<c:if test="${not empty gjzbMap.year }">${gjzbMap.year }年</c:if>基础设施建设规划表</span>
                        <div class="excel_btns">
                        	<c:if test="${partTag eq '1'}">
	                        	<select class="yearSelect fl" onchange="changeTznd(this);" id="tznd">
							        <option value="">请选择年份</option>
							        <c:forEach items="${gjzbMap.yearList }" var="item">
								        <option value="${item }" ${gjzbMap.year eq item ? 'selected' : '' }>${item }年</option>
							        </c:forEach>
								</select>
                            </c:if>
                        	<button type="button" class="btn_tj">导入数据</button>
                        	<button type="button" class="btn_tj">导出数据</button>
                            <c:if test="${partTag eq '2'}">
                            	<button type="button" class="btn_tj" style="display:none;">生成报文</button>
                            </c:if>
                            <button id="exportExcel" type="button" class="btn_tj">生成报表</button>
                            <a id="tableToExcle" style="display:none;"></a>
                        </div>
                    </div>
                <form id="formAmount" name="formAmount" action="" method="post">
                	<input type="hidden" name="gjzb.id" value="${gjzb.id}">
                    <table class="basicPlanT" id="table" cellpadding=0 cellspacing=0 border="1" style="border:1px solid #585858;">
                        <tbody>
                        <tr style="height: 68px;" class="province">
                            <td colspan="2" class="noBR t_header" style="padding-top:35px; ">项目</td>
                            <td class="noBR t_header" style="padding-top: 10px;">数量</td>
                            <td class="t_header" style="padding: 0 0 35px 25px;">单位</td>
                            <c:forEach items="${areaList }" var="area">
                            	<th class="tWidth4 provLink">${area }</th>
                            </c:forEach>
                            <th class="tWidth4 tLineH">合计</th>
                            <th rowspan="2" class="tWidth4 thposition">平均造价<span class="thabsolute">(万元)</span></th>
                            <th rowspan="2" class="tWidth4 thposition">单项投资<span class="thabsolute">(万元)</span></th>
                            <th rowspan="2" class="tWidth4 thposition">中央投资<span class="thabsolute">(万元)</span></th>
                            <th rowspan="2" class="tWidth4 thposition noBR">地方投资<span class="thabsolute">(万元)</span></th>
                        </tr>
                        <tr>
                            <th colspan="4">边界、海岸线长度<span>（公里）</span></th>
                            <c:forEach items="${gjzbMap.bjHaxCdList }" var="item" varStatus="index">
                            	<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            </c:forEach>
                        </tr>
                        <tr>
                            <th rowspan="6" class="tWidth1">交通保障设施</th>
                            <td rowspan="2" class="tWidth2">执勤道路</td>
                            <td colspan="2">新建（公里）</td>
                            <c:forEach items="${gjzbMap.zqdlXj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
	                            	<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="2">升级（公里）</td>
                            <c:forEach items="${gjzbMap.zqdlSj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">桥梁（座）</td>
                            <c:forEach items="${gjzbMap.qiaoLiang }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">执勤码头（座）</td>
                            <c:forEach items="${gjzbMap.zqmt }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">直升机停机坪（个）</td>
                            <c:forEach items="${gjzbMap.zsjtjp }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">小　计</td>
                            <c:forEach items="${areaList }">
	                            <td></td>
                            </c:forEach>
                            <td></td>
                            <td></td>
                            <c:forEach items="${gjzbMap.jtbzssSub }" var="item" varStatus="index">
	                           <c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <th rowspan="13" class="tWidth1">拦阻报警设施</th>
                            <td rowspan="4" class="tpad1">铁丝网</td>
                            <td rowspan="2" class="tWidth5">新建</td>
                            <td class="tWidth3">固定式（公里）</td>
                            <c:forEach items="${gjzbMap.tswXjGds }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>移动式（公里）</td>
                            <c:forEach items="${gjzbMap.tswXjYds }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td rowspan="2">升级</td>
                            <td class="tWidth3">固定式（公里）</td>
                            <c:forEach items="${gjzbMap.tswSjGds }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>移动式（公里）</td>
                            <c:forEach items="${gjzbMap.tswSjYds }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">铁栅栏（公里）</td>
                            <c:forEach items="${gjzbMap.tzl }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td rowspan="4" class="tpad1">拦阻桩</td>
                            <td rowspan="2">新建</td>
                            <td>固定式（套）</td>
                            <c:forEach items="${gjzbMap.lzzXjGds }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>自动升降式（套）</td>
                            <c:forEach items="${gjzbMap.lzzXjSjs }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td rowspan="2">升级</td>
                            <td>固定式（套）</td>
                            <c:forEach items="${gjzbMap.lzzSjGds }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>自动升降式（套）</td>
                            <c:forEach items="${gjzbMap.lzzSjSjs }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">隔离带（米）</td>
                            <c:forEach items="${gjzbMap.gld }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">报警线路（公里）</td>
                            <c:forEach items="${gjzbMap.bjxl }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">拒马（个）</td>
                            <c:forEach items="${gjzbMap.juma }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">小　计</td>
                            <c:forEach items="${areaList }">
	                            <td></td>
                            </c:forEach>
                            <td></td>
                            <td></td>
                            <c:forEach items="${gjzbMap.lzbjssSub }" var="item" varStatus="index">
	                           <c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <th rowspan="22" class="tWidth1">指挥监控设施</th>
                            <td rowspan="8" class="tpad1">监控中心</td>
                            <td rowspan="2">国家级</td>
                            <td>新建（个）</td>
                            <c:forEach items="${gjzbMap.jkzxGjjXj }" var="item" varStatus="index">
                            	 <c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>升级（个）</td>
                            <c:forEach items="${gjzbMap.jkzxGjjSj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td rowspan="2">省级</td>
                            <td>新建（个）</td>
                            <c:forEach items="${gjzbMap.jkzxSjXj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>升级（个）</td>
                            <c:forEach items="${gjzbMap.jkzxSjSj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td rowspan="2">地级</td>
                            <td>新建（个）</td>
                            <c:forEach items="${gjzbMap.jkzxDjXj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>升级（个）</td>
                            <c:forEach items="${gjzbMap.jkzxDjSj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td rowspan="2">县级</td>
                            <td>新建（个）</td>
                            <c:forEach items="${gjzbMap.jkzxXjXj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>升级（个）</td>
                            <c:forEach items="${gjzbMap.jkzxXjSj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td rowspan="8" class="tpad1">监控站</td>
                            <td rowspan="2" class="tpad2">监控站系统</td>
                            <td>新建（个）</td>
                            <c:forEach items="${gjzbMap.jkzxtXj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>升级（个）</td>
                             <c:forEach items="${gjzbMap.jkzxtSj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td rowspan="2" class="tpad2">显控终端</td>
                            <td>新建（个）</td>
                            <c:forEach items="${gjzbMap.xkzdXj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>升级（个）</td>
                            <c:forEach items="${gjzbMap.xkzdSj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td rowspan="2" class="tpad2">固定前端</td>
                            <td>新建（个）</td>
                            <c:forEach items="${gjzbMap.gdqdXj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>升级（个）</td>
                            <c:forEach items="${gjzbMap.gdqdSj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td rowspan="2" class="tpad2">移动前端</td>
                            <td>新建（个）</td>
                            <c:forEach items="${gjzbMap.ydqdXj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>升级（个）</td>
                            <c:forEach items="${gjzbMap.ydqdSj }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">传输线路（公里）</td>
                            <c:forEach items="${gjzbMap.csxl }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">供电系统（套）</td>
                            <c:forEach items="${gjzbMap.gdxt }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">军警民联防平台（套）</td>
                            <c:forEach items="${gjzbMap.jjmlfpt }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">无人值守电子哨兵（套）</td>
                            <c:forEach items="${gjzbMap.wrzsdzsb }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        
                        <tr>
                            <td colspan="3">报警装置（套）</td>
                            <c:forEach items="${gjzbMap.bjzz }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        
                        <tr>
                            <td colspan="3">小　计</td>
                            <c:forEach items="${areaList }">
	                            <td></td>
                            </c:forEach>
                            <td></td>
                            <td></td>
                            <c:forEach items="${gjzbMap.zhjkssSub }" var="item" varStatus="index">
	                           <c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <th rowspan="4" class="tWidth1">辅助配套设施</th>
                            <td colspan="3">执勤房（座）</td>
                             <c:forEach items="${gjzbMap.zqf }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">了望塔（座）</td>
                             <c:forEach items="${gjzbMap.lwt }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">标志牌（座）</td>
                             <c:forEach items="${gjzbMap.bzp }" var="item" varStatus="index">
                            	<c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td colspan="3">小　计</td>
                            <c:forEach items="${areaList }">
	                            <td></td>
                            </c:forEach>
                            <td></td>
                            <td></td>
                            <c:forEach items="${gjzbMap.fzptssSub }" var="item" varStatus="index">
	                           <c:if test="${!index.last}" >
                            		<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            	<c:if test="${index.last}" >
                            		 <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            	</c:if>
                            </c:forEach>
                        </tr>
                        <tr>
                            <th colspan="4">中央投资<span>（万元）</span></th>
                            <c:forEach items="${gjzbMap.zytzList }" var="item">
                            	<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            </c:forEach>
                            <td></td>
                            <td></td>
                            <td></td>
		                    <td class="noBR"></td>
                        </tr>
                        <tr>
                        	<th colspan="4">地方配套<span>（万元）</span></th>
                            <c:forEach items="${gjzbMap.dftzList }" var="item">
                            	<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            </c:forEach>
                            <td></td>
                            <td></td>
                            <td></td>
		                    <td class="noBR"></td>
                        </tr>
                        <!-- 
                        <tr class="planAmountTr lastTr">
                            <th colspan="4">规划投资总额<span>（万元）</span><a class="editLink">编辑</a></th>
                            <td class="editTd"><div class="editItems" rows="1" disabled>
                            ${gjzb.liaoNing }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.jiLin }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.heiLongJiang }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.neiMengGu }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.ganSu }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.xinJiang }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.heBei }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.tianJin }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.shanDong }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.jiangSu }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.shangHai }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.zheJiang }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.fuJian }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.guangDong }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.haiNan }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.guangXi }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.xiZang }</textarea></td>
                            <td class="editTd"><textarea class="editItems" rows="1" disabled>
                            ${gjzb.yunNan }</textarea></td>
                            <td class="itemAmount editTd"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="noBR"></td>
                        </tr>
						 -->
                        <tr class="lastTr">
                            <th colspan="4">各地上报投资额<span>（万元）</span></th>
                            <c:forEach items="${gjzbMap.tzzeList }" var="item">
                            	<td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                            </c:forEach>
                            <td></td>
                            <td></td>
                            <td></td>
		                    <td class="noBR"></td>
                            
                        </tr>

                        </tbody>
                    </table>
                </form>
				<canvas id="canvas" width="254" height="70" style="position: absolute;top:44px;left: 0px;z-index: 1;transition: 0.8s;"></canvas>
                </div>
            </div>
            <!--统计图-->
            <div class="tab_cont" style="overflow-y: hidden;">
            	<input type="hidden" id="szsfMap" value="${gjzbMap.areaMap }"/>
            	<input type="hidden" id="xmlbMap" value="${gjzbMap.xmlbMap }"/>
            	<input type="hidden" id="sslxMap" value="${gjzbMap.sslxMap }"/>
                <div class="chartsGroup">
                    <div class="chart_panel column1 fl">
                        <div class="cp_header">全国<c:if test="${not empty gjzbMap.year }">${gjzbMap.year }年</c:if>各边防省基础设施建设投资柱状图</div>
                        <div id="container" class="chart_item"></div>
                    </div>
                    <div class="chart_panel column2 fl">
                        <div class="cp_header">全国<c:if test="${not empty gjzbMap.year }">${gjzbMap.year }年</c:if>基础设施建设投资柱状图</div>
                        <div id="container4" class="chart_item"></div>
                    </div>
                    <div class="chart_panel column2 fl" style="margin-left: 0;">
                        <div class="cp_header">全国<c:if test="${not empty gjzbMap.year }">${gjzbMap.year }年</c:if>各类设施投资比例图</div>
                        <div id="container1" class="chart_item"></div>
                    </div>
                    <div class="chart_panel column2 fl">
                        <div class="cp_header">全国<c:if test="${not empty gjzbMap.year }">${gjzbMap.year }年</c:if>各边防省投资比例图</div>
                        <div id="container2" class="chart_item"></div>
                    </div>
                    <div class="chart_panel column2 fl">
                        <div class="cp_header">全国<c:if test="${not empty gjzbMap.year }">${gjzbMap.year }年</c:if>基础设施建设投资比例图</div>
                        <div id="container3" class="chart_item"></div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
<!--轮播切换圆圈-->
<div class="p_nav">
    <ul class="p_list">
        <li><span class="active"></span></li>
        <li><span></span></li>
    </ul>
</div>
<!-- 数据导入 -->
<div id="importFile" class="easyui-dialog" data-options="modal:true,closed:true" title="${partTag == 1?"统筹规划":"年度计划"}数据导入" style="width: 500px;display:none;top:20%;">
	<div data-options="region:'center'" style="padding:25px 40px;" class="dialog_center">
		<div class="scrollBlock" style="height: auto;">
			<div id="fileList" class="uploader-list">
				<div class="fileNameView"></div>
			</div>
		</div>
		<div id="filePicker">选择文件</div>
		<div class="clear"></div>
		<div id="fileWarning" class="warning"></div>
	</div>
	<div data-options="region:'south',border:false"	style="text-align:right;padding: 20px 40px 50px;">
		<button type="button" class="btn" id="uploadBtn">上传</button>
		<button type="button" class="btn cancal_btn">取消</button>
	</div>
</div>
</div>
</body>
</html>
