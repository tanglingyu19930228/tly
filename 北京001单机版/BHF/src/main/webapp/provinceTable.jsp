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
    <title>省市表格</title>
    <link rel="icon" href="BHF.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/jquery.mCustomScrollbar.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/provinceTable.css">
    <script src="js/libs/jquery-1.8.3.min.js"></script>
    <script src="highCharts/highcharts.js"></script>
    <script src="highCharts/highcharts-3d.js"></script>
    <script src="js/libs/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="js/libs/jquery.DB_treeMenu.min.js"></script>
    <script src="js/prov_charts.js"></script>
    <!--控制图表切换 与省级公用-->
    <script src="js/index.js"></script>
    <!-- 控制高度显示的js-->
    <script src="js/provinceTable.js"></script>
    <script>
        //鼠标滚动时切换图表
        function scrollFunc(e) {
            /* var downTrue, enable = false;//downTrue表示是否为向下滚动，enable表示是否调用事件
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
                tabToggle(false,[[$("#chart_1"),option1],[$("#chart_2"),option2],[$("#chart_3"),option3],[$("#chart_4"),option4],[$("#chart_5"),option5]], downTrue);
            } */
        }
        //给页面绑定滑轮滚动事件
        if (document.addEventListener) {
            document.addEventListener('DOMMouseScroll', scrollFunc, false);
        }
        //滚动滑轮触发scrollFunc方法
        window.onmousewheel = document.onmousewheel = scrollFunc;
         $(function(){
            //轮播按钮点击事件
            $(".p_nav .p_list li").click(function(){
                if(!$(this).find("span").hasClass("active")){
                    tabToggle(false,[[$("#chart_1"),option1],[$("#chart_2"),option2],[$("#chart_3"),option3],[$("#chart_4"),option4],[$("#chart_5"),option5]], toggleFlag);
                }
            }) ;
        });
         var area = '${szsf }';
         function changeSjzbTznd(obj){
        	 $("#postForm").empty();
				var f=$("#postForm")[0];
		        f.action='annualPlanProvince/load';
		        f.innerHTML='<input type="hidden" name="szsf" value="'+area+'"/>'
		        + '<input type="hidden" name="tznd" value="'+$(obj).val()+'"/>'
		        + '<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
		        f.submit();
         }
    </script>
</head>
<body style="overflow-y:hidden">
<jsp:include page="top.jsp"></jsp:include>
<div class="main">
 	<ol class="navTip breadcrumb">
 		<li><a href="user/toWelcome">首页</a></li>
 		<c:if test="${partTag eq '1' }">
 			<li><a href="annualPlan/load?partTag=${partTag}">${loginUser.province=='国家'?'全国':loginUser.province }统筹规划报表</a></li>
 			<li class="active">${szsf }统筹规划报表</li>
 		</c:if>
 		<c:if test="${partTag eq '2' }">
 			<li><a href="annualPlan/load?partTag=${partTag}">${loginUser.province=='国家'?'全国':loginUser.province }年度计划报表</a></li>
	 		<li class="active">${szsf }年度计划报表</li>
 		</c:if>
 	</ol> 
	<jsp:include page="provinceLead.jsp"></jsp:include>
    <div class="tab m_right">
        <!--<div class="tab_title toggleBtn">
            <button type="button" class="current">统计表</button><button type="button">统计图</button>
        </div>-->
        <div class="tab_div" style="overflow-y:hidden;overflow-x:scroll; ">
            <!--统计表-->
            <div class="tab_cont">
                <div class="chart_panel excel_panel" style="min-width:1450px">
                <div class="cp_header">
                	<div style="position: absolute;right: 0;top: 0px;display: inline;display:${partTag eq '1' ? '' : 'none'};">
	                	<select onchange="changeSjzbTznd(this);" style="" class="yearSelect1">
							<option value="">请选择年份</option>
							<c:forEach items="${yearList }" var="item">
						        <option value="${item }" ${year eq item ? 'selected' : '' }>${item }年</option>
						    </c:forEach>
						</select>
					</div>
                </div>
                	<jsp:include page="sjzb/sjzb.jsp"></jsp:include>
                </div>
            </div>
            <!--省级统计图-->
            <div class="tab_cont">
            <input type="hidden" id="dftz" value="${dftzAndZytzMap.dftz }"/>
            <input type="hidden" id="zytz" value="${dftzAndZytzMap.zytz }"/>
            <input type="hidden" id="cityDftzMap" value="${cityDftzMap }"/>
            <input type="hidden" id="cityZytzMap" value="${cityZytzMap }"/>
            
            <input type="hidden" id="zytzxmlbMap" value="${zytzxmlbMap }"/>
            <input type="hidden" id="dftzxmlbMap" value="${dftzxmlbMap }"/>
                <div class="chartsGroup">
                    <div class="chart_panel column1 fl" style="display:${loginUser.roleName eq '1' and param.szsf ne '天津' and param.szsf ne '上海' ? '' : 'none' }">
                        <div class="cp_header"><c:if test="${loginUser.roleName eq '1' }">${szsf}</c:if>各地市<c:if test="${partTag eq '2' and not empty year }">${year }年</c:if><c:if test="${partTag eq '1' and not empty year and year ne '' }">${year }年</c:if>基础建设投资柱状图</div>
                        <div id="chart_1" class="chart_item"></div>
                    </div>
                    <div class="chart_panel column2 fl" style="width:${loginUser.roleName eq '1' and param.szsf ne '天津' and param.szsf ne '上海' ? '32.15%' : '98.05%' };margin-left:${loginUser.roleName eq '1' and param.szsf ne '天津' and param.szsf ne '上海' ? '1.5%' : '0%' };">
                        <div class="cp_header"><c:if test="${loginUser.roleName eq '1' }">${szsf}</c:if><c:if test="${loginUser.roleName eq '2' }">${szsf}</c:if><c:if test="${partTag eq '2' and not empty year }">${year }年</c:if><c:if test="${partTag eq '1' and not empty year and year ne '' }">${year }年</c:if>基础设施建设投资柱状图</div>
                        <div id="chart_2" class="chart_item"></div>
                    </div>
                    <div class="chart_panel column2 fl" style="margin-left: 0;width:${loginUser.roleName eq '1' and param.szsf ne '天津' and param.szsf ne '上海' ? '32.15%' : '48.225%' };">
                        <div class="cp_header"><c:if test="${loginUser.roleName eq '1' }">${szsf}</c:if><c:if test="${loginUser.roleName eq '2' }">${szsf}</c:if><c:if test="${partTag eq '2' and not empty year }">${year }年</c:if><c:if test="${partTag eq '1' and not empty year and year ne '' }">${year }年</c:if>中央/地方投资比例图</div>
                        <div id="chart_3" class="chart_item"></div>
                    </div>
                    <div class="chart_panel column2 fl" style="display:${loginUser.roleName eq '1' and param.szsf ne '天津' and param.szsf ne '上海' ? '' : 'none' }">
                        <div class="cp_header"><c:if test="${loginUser.roleName eq '1' }">${szsf}</c:if>各地市<c:if test="${partTag eq '2' and not empty year }">${year }年</c:if><c:if test="${partTag eq '1' and not empty year and year ne '' }">${year }年</c:if>基础建设投资比例图</div>
                        <div id="chart_4" class="chart_item"></div>
                    </div>
                    <div class="chart_panel column2 fl" style="width:${loginUser.roleName eq '1' and param.szsf ne '天津' and param.szsf ne '上海' ? '32.15%' : '48.225%' };">
                        <div class="cp_header"><c:if test="${loginUser.roleName eq '1' }">${szsf}</c:if><c:if test="${loginUser.roleName eq '2' }">${szsf}</c:if><c:if test="${partTag eq '2' and not empty year }">${year }年</c:if><c:if test="${partTag eq '1' and not empty year and year ne '' }">${year }年</c:if>基础设施建设投资比例图</div>
                        <div id="chart_5" class="chart_item"></div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<!--轮播切换圆圈-->
<div class="p_nav">
    <ul class="p_list">
        <li><span class="active"></span></li>
        <li><span></span></li>
    </ul>
</div>
</body>
</html>
