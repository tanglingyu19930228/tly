<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	
	<title>系统首页</title>
	<link rel="icon" href="BHF.ico" type="image/x-icon" />
	<link rel="stylesheet" href="css/base.css" />
	<link rel="stylesheet" href="css/welcome.css" />
	<script src="js/libs/jquery-1.8.3.min.js"></script>
	<script src="js/welcome.js"></script>
</head>

<body>
	<jsp:include page="top.jsp"></jsp:include>
	<div class="content">
		<div class="main" style="overflow-x:scroll">
			<h3>欢 迎 您 进 入 边 海 防 基 础 设 施 项 目 管 理 系 统！</h3>
		</div>
		<ul class="items">
			<li onclick="toPart('annualPlan/load',1);" style="cursor:pointer;"><a><img src="img/tongchou2.png" /></a><span>统筹规划</span></li>
			
			<li onclick="toPart('annualPlan/load',2);" style="cursor:pointer;"><a><img src="img/jihua2.png" /></a><span>年度计划</span></li>
			
			<li onclick="toPart('implement/load',3);" style="cursor:pointer;"><a><img src="img/jiandu2.png" /></a><span>实施监督</span></li>
			
			<li class="lastLi" onclick="toPart('usemaintenance/load',4);" style="cursor:pointer;"><a><img src="img/weihu2.png" /></a> <span>使用维护</span></li>
		</ul>
	</div>
</body>
</html>
