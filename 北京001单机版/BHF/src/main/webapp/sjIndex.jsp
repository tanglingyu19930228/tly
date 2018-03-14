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
    <!--引入bootstrap样式-->
	<link rel="stylesheet" href="css/bootstrap_progress.min.css">
	<link rel="stylesheet" href="css/provinceTable_info.css">
    <link rel="stylesheet" href="css/provinceTable.css">
    <link rel="stylesheet" href="css/sjindex.css">
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
    <%--<script src="js/chartsToggle.js"></script>--%>
    <!--国家级图表-->
    <script src="js/country_charts.js"></script>
    <script src="js/sjindex.js"></script>
    <!--上传插件-->
	<script src="js/libs/webuploader.min.js"></script>
	<!--导入文件js-->
	<script src="js/importFile.js"></script>
    <script type="text/javascript">

        //根据投资年度统计
        function changeTznd(obj){
        	$("#postForm").empty();
        	var f=$("#postForm")[0];
            f.action='annualPlan/load';
            f.innerHTML='<input type="hidden" name="tznd" value="'+$(obj).val()+'"/>'
            + '<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
            f.submit();
        }
        $(function () {
            $(".tab_cont").each(function(){
                if($(this).hasClass("active")){
                    $(this).show();
                }else{
                    $(this).hide();
                }
            });

            $(".p_nav .p_list li").click(function(){
                if(!$(this).find("span").hasClass("active")){
                    $(".tab_cont").each(function(){
                        if($(this).hasClass("active")){
                            $(this).hide();
                            $(this).removeClass("active")
                        }else{
                            $(this).show();
                            $(this).addClass("active")
                        }
                    });
                    if($(".p_nav .p_list li").find("span").hasClass("active")){
                        $(".p_nav .p_list li").find("span").removeClass("active");
                    }
                    $(this).find("span").addClass("active");
                }
            })
        })
        //鼠标滚动时切换图表
        function scrollFunc(e) {
           /*  var downTrue, enable = false;//downTrue表示是否为向下滚动，enable表示是否调用事件
            e = e || window.event;
            if (e.wheelDelta) {  //判断浏览器IE，谷歌滑轮事件
                if (e.wheelDelta > 0) { //当滑轮向上滚动时
                    downTrue = false;
                    enable = true;
                }
                if (e.wheelDelta < 0) { //当滑轮向下滚动时
                    downTrue = true;
                    enable = true;
                }
            } else if (e.detail) {  //Firefox滑轮事件
                if (e.detail < 0) { //当滑轮向上滚动时
                    downTrue = false;
                    enable = true;
                }
                if (e.detail > 0) { //当滑轮向下滚动时
                    downTrue = true;
                    enable = true;
                }
            }
            if (enable) {
                if(downTrue){
                    $("#staticticsPic").show();
                    $("#staticticsPic").addClass("active");
                    $("#statisticsTable").hide();
                    $("#statisticsTable").removeClass("active");
                    $(".p_nav .p_list li").find("span").eq(0).removeClass("active");
                    $(".p_nav .p_list li").find("span").eq(1).addClass("active");
                }else{
                    $("#statisticsTable").show();
                    $("#statisticsTable").addClass("active");
                    $("#staticticsPic").hide();
                    $("#staticticsPic").removeClass("active");
                    $(".p_nav .p_list li").find("span").eq(1).removeClass("active");
                    $(".p_nav .p_list li").find("span").eq(0).addClass("active");
                }
            } */
        }
        //给页面绑定滑轮滚动事件
        if (document.addEventListener) {
            document.addEventListener('DOMMouseScroll', scrollFunc, false);
        }
        //滚动滑轮触发scrollFunc方法
        window.onmousewheel =document.onmousewheel = scrollFunc;
    </script>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<input type="hidden" id="role" value="${roleName}">
<div style="display:none;"><jsp:include page="sjzb/sjzb.jsp"></jsp:include></div>
<div class="main">
    <ol class="navTip breadcrumb">
    <li><a href="user/toWelcome">首页</a></li>
    <c:if test="${partTag eq '1'}"><li class="active">${loginUser.province }统筹规划报表</li></c:if>
    <c:if test="${partTag eq '2' }"><li class="active">${loginUser.province }年度计划报表</li></c:if>
    </ol>
    <%--<div class="tab"></div>--%>
        <!--统计表-->

        <div class="tab_div" style="overflow:hidden; ">
            <div class="tab_cont active" id="statisticsTable">
                <jsp:include page="statisticsTableForProvince.jsp"></jsp:include>
            </div>
            <!--统计图-->
            <div class="tab_cont" style="margin-left:1.5%;" id="staticticsPic">
                <jsp:include page="staticticsPic.jsp"></jsp:include>
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
