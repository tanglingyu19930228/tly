<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML >
<html>
<head>
<base href="<%=basePath%>">

<title>福建省</title>
<style type="text/css">
.implementPlanT {
	border: 1px solid #e6e4e4;
	border-collapse: collapse;
	text-align: center;
	width: 100%;
	color: #585858;
}

.implementPlanT td,.implementPlanT th {
	border: 1px solid #e6e4e4;
}
</style>


</head>

<body>
	<table class="implementPlanT">
		<tbody>
			<tr>
				<th rowspan="2">建设区域</th>
				<th rowspan="2" colspan="2">项目名称</th>
				<th rowspan="2">建设性质</th>
				<th colspan="7">建设内容</th>
				<th rowspan="2">建设数据合计</th>
				<th rowspan="2">建设投资合计(万元)</th>
				<th rowspan="2">中央投资合计(万元)</th>
				<th rowspan="2">地方投资合计(万元)</th>
			</tr>

			<tr>

				<th>序号</th>
				<th>建设点位</th>
				<th>建设数量</th>
				<th>计量单位</th>
				<th>建设投资(万元)</th>
				<th>中央投资(万元)</th>
				<th>地方投资(万元)</th>

			</tr>
			<tr>
				<td rowspan="15">福建省</td>
				<td colspan="2">总投资</td>

				<td colspan="10"></td>
				<td>3</td>
				<td>3</td>

			</tr>
			<tr>

				<td rowspan="4">交通保障设施</td>
				<td rowspan="2">执勤道路</td>
				<td rowspan="2">新建</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>公里</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
			</tr>

			<tr>

				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>公里</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>

			</tr>

			<tr>

				<td rowspan="2">桥梁</td>
				<td rowspan="2">新建</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>座</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
			</tr>

			<tr>

				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>座</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>

			</tr>

			<tr>

				<td rowspan="10">指挥监控设施</td>
				<td rowspan="2">地级监控中心</td>
				<td rowspan="2">升级改造</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>个</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
			</tr>

			<tr>


				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>个</td>
				<td>11222</td>
				<td>11</td>
				<td>11</td>

			</tr>

			<tr>

				<td rowspan="4">县级监控中心</td>
				<td rowspan="2">新建</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>个</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>

			</tr>

			<tr>

				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>个</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>


			</tr>

			<tr>

				<td rowspan="2">升级改造</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>个</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
			</tr>

			<tr>

				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>个</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>

			</tr>

			<tr>

				<td rowspan="4">监控站</td>
				<td rowspan="2">新建</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>套</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>

			</tr>

			<tr>

				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>套</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>

			</tr>

			<tr>

				<td rowspan="2">升级改造</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>套</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
				<td rowspan="2">11</td>
			</tr>

			<tr>

				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>套</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>

			</tr>

		</tbody>
	</table>
</body>
</html>
