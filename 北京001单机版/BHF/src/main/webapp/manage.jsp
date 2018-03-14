<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<!--让ie使用兼容性模式-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>监督实施省级总表</title>
<link rel="icon" href="BHF.ico" type="image/x-icon" />
<link rel="stylesheet" href="themes/default/easyui.css" />
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/provinceTable.css">
<!--引入bootstrap样式-->
<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" href="css/bootstrap_progress.min.css">
<link rel="stylesheet" href="css/provinceTable_info.css">
<!--引入上传插件样式-->
<link rel="stylesheet" href="css/webuploader.css">
<script src="js/libs/jquery-1.8.3.min.js"></script>
<script src="highCharts/highcharts.js"></script>
<script src="highCharts/highcharts-3d.js"></script>
<script src="js/libs/jquery.easyui.min.js"></script>
<script src="js/libs/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="js/libs/jquery.DB_treeMenu.min.js"></script>
<script src="js/manage_charts.js"></script>
<!--控制图表切换 与省级公用-->
<script src="js/index.js"></script>
<!--上传插件-->
<script src="js/libs/webuploader.min.js"></script>
<!--导入文件js-->
<script src="js/importFile.js"></script>    
<!-- 控制高度显示的js-->
<script src="js/provinceTable.js"></script>
<script type="text/javascript">
	//鼠标滚动时切换图表
	function scrollFunc(e) {
		/* var downTrue, enable = false;//downTrue表示是否为向下滚动，enable表示是否调用事件
		e = e || window.event;
		if (e.wheelDelta) { //判断浏览器IE，谷歌滑轮事件
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
		} else if (e.detail) { //Firefox滑轮事件
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
			tabToggle(false, [ [ $("#chart_1"), option1 ],
					[ $("#chart_2"), option2 ], [ $("#chart_3"), option3 ],
					[ $("#chart_4"), option4 ], [ $("#chart_5"), option5 ] ],
					downTrue);
		} */
	}
	//给页面绑定滑轮滚动事件
	if (document.addEventListener) {
		document.addEventListener('DOMMouseScroll', scrollFunc, false);
	}
	//滚动滑轮触发scrollFunc方法
	window.onmousewheel = document.onmousewheel = scrollFunc;
	$(function() {
		//轮播按钮点击事件
		$(".p_nav .p_list li").click(
				function() {
					if (!$(this).find("span").hasClass("active")) {
						tabToggle(false, [ [ $("#chart_1"), option1 ],
								[ $("#chart_2"), option2 ],
								[ $("#chart_3"), option3 ],
								[ $("#chart_4"), option4 ],
								[ $("#chart_5"), option5 ] ], toggleFlag);
					}
				});
	});
	
</script>
</head>

<body>
	<jsp:include page="top.jsp"></jsp:include>
	<div class="main">
		<ol class="navTip breadcrumb">
			<li><a href="user/toWelcome">首页</a></li>
			<li class="active">${loginUser.province=='国家'?'全国':loginUser.province }建设项目实施监督</li>
		</ol>
		<jsp:include page="provinceLead.jsp"></jsp:include>
		<div class="tab m_right">
			<div class="tab_div" style="overflow-y:hidden;overflow-x:scroll; ">
				<!--统计表-->
				<div class="tab_cont">
					<div class="chart_panel excel_panel" style="min-width:1450px">
						<div class="cp_header">
							${szsf }基础设施实施监督统计表
							<div style="position: absolute;right: 0;top: 0px;display: inline;">
								<button type="button" class="btn_tj" style="display:${(loginUser.roleName eq '1' and szsf eq '全国') or (loginUser.roleName eq '2' and fn:contains(loginUser.province,szsf)) ? '' : 'none' };">导入数据</button>
								<button type="button" class="btn_tj" style="display:${(loginUser.roleName eq '1' and szsf eq '全国') or (loginUser.roleName eq '2' and fn:contains(loginUser.province,szsf)) ? '' : 'none' };">导出数据</button>
							</div>
						</div>
						
						<jsp:include page="sjzb/manageSjzb.jsp"></jsp:include>
					</div>
				</div>

				<%--统计图 --%>
				<div class="tab_cont">
					<input type="hidden" id="dftz" value="" />
					<input type="hidden" id="jstzColumn" value="${xmlbMap}" /> 
					<input type="hidden" id="cityDftzMap"value="${columnMap}" />
					<input type="hidden" id="jsztPie" value="${pie_jszt}" /> 
					<input type="hidden" id="zytzxmlbMap" value="" /> 
					<input type="hidden" id="dftzxmlbMap" value="" />
					<div class="chartsGroup">
						<div class="chart_panel column1 fl" style="display:${isHidden eq '1' or  loginUser.province eq '天津市' or loginUser.province eq '上海市' ? 'none':''}">
							<div class="cp_header">${szsf}各${szsf=='全国'?'边防省':'地市'}基础设施建设投资柱状图</div>
							<div id="chart_1" class="chart_item"></div>
						</div>
						<div class="chart_panel column2 fl" style="width:${isHidden eq '1' or  loginUser.province eq '天津市' || loginUser.province eq '上海市' ? '98.05%' : '32.15%' };">
							<div class="cp_header">${szsf}基础设施建设投资柱状图</div>
							<div id="chart_2" class="chart_item"></div>
						</div>
						<div class="chart_panel column2 fl" style="margin-left: ${isHidden eq '1' or  loginUser.province eq '天津市' || loginUser.province eq '上海市' ? '1.5%' : '0' };width:${isHidden eq '1' or loginUser.province eq '天津市' || loginUser.province eq '上海市' ? '48.225%' : '32.15%' };">
							<div class="cp_header">${szsf}各类建设状态投资比例图</div>
							<div id="chart_3" class="chart_item"></div>
						</div>
						<div class="chart_panel column2 fl" style="display:${isHidden eq '1' or  loginUser.province eq '天津市' || loginUser.province eq '上海市' ? 'none' : '' };">
							<div class="cp_header">${szsf}各${szsf=='全国'?'边防省':'地市'}投资比例图</div>
							<div id="chart_4" class="chart_item"></div>
						</div>
						<div class="chart_panel column2 fl" style="margin-left: 1.5%;width:${isHidden eq '1' or  loginUser.province eq '天津市' || loginUser.province eq '上海市' ? '48.225%' : '32.15%' };">
							<div class="cp_header">${szsf}基础设施建设投资比例图</div>
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
	
	<!-- 数据导入 -->
	<div id="importFile" class="easyui-dialog" data-options="modal:true,closed:true" title="实施监督数据导入 " style="width: 500px;display:none;top:20%;">
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
</body>
</html>
