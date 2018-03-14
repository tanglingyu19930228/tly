<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<title>海南省</title>
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
				<td rowspan="${data.hnsj+1}">海南省</td>
				<td colspan="2">总投资</td>
				<td colspan="10">10000</td>
				<td>5000</td>
				<td>5000</td>
			</tr>
			<tr>
				<td rowspan="${data.jtbzss}">交通保障设施</td>
				<td rowspan="${data.list_zqdlRows }">海防执勤道路</td>
				<td rowspan="${data.list_zqdlRows }">新建</td>
				<c:if test="${empty data.list_zqdl}">
					<td>111</td>
					<td>111</td>
					<td>111</td>
					<td>公里</td>
					<td>111</td>
					<td>111</td>
					<td>111</td>
				</c:if>
				
				<c:forEach items="${data.list_zqdl}" var="item" varStatus="s">
					<c:if test="${s.index eq 0}">
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>公里</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
					</c:if>
				</c:forEach>
				<td rowspan="${data.list_zqdlRows }">111</td>
				<td rowspan="${data.list_zqdlRows }">111</td>
				<td rowspan="${data.list_zqdlRows }">111</td>
				<td rowspan="${data.list_zqdlRows }">111</td>
			</tr>
			
			<c:forEach items="${data.list_zqdl}" var="item" varStatus="s">
				<c:if test="${s.index ne 0}">
			<tr>
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>公里</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
			</tr>
			
			</c:if>
			</c:forEach>
			
			<tr>
				<td rowspan="${data.xjqiaolianglistRows}">桥梁</td>
				<td rowspan="${data.xjqiaolianglistRows}">新建</td>
				<c:if test="${ empty data.xjqiaolianglist}">
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>座</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				</c:if>
				<c:forEach items="${data.xjqiaolianglist}" var="item" varStatus="s">
					<c:if test="${s.index eq 0}">
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>座</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
					</c:if>
				</c:forEach>
				<td rowspan="${data.xjqiaolianglistRows}">11</td>
				<td rowspan="${data.xjqiaolianglistRows}">11</td>
				<td rowspan="${data.xjqiaolianglistRows}">11</td>
				<td rowspan="${data.xjqiaolianglistRows}">11</td>
			</tr>
		<c:forEach items="${data.xjqiaolianglist }" var="item" varStatus="s">
			<c:if test="${s.index ne 0}">
			<tr>
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>座</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
			</tr>
			</c:if>
		</c:forEach>

			<tr>
				<td rowspan="${data.list_mtRows}">码头</td>
				<td rowspan="${data.list_mtRows}">新建</td>
				<c:if test="${empty data.list_mt }">
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>座</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				</c:if>
				<c:forEach items="${data.list_mt }" var="item" varStatus="s">
					<c:if test="${s.index eq 0}">
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>座</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
					</c:if>
				</c:forEach>
				<td rowspan="${data.list_mtRows }">11</td>
				<td rowspan="${data.list_mtRows }">11</td>
				<td rowspan="${data.list_mtRows }">11</td>
				<td rowspan="${data.list_mtRows }">11</td>
			</tr>
			<c:forEach items="${data.list_mt}" var="item" varStatus="s">
				<c:if test="${s.index ne 0}">
					 <tr>
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>座</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>

					</tr>
				</c:if>
			</c:forEach>
			
			<tr>
				<td rowspan="${data.zhjkss}">指挥监控设施</td>
				<td rowspan="${data.djijkxzlistRows}">地级监控中心</td>
				<td rowspan="${data.djijkxzlistRows}">升级改造</td>
				<c:if test="${empty data.djijkxzlist }">
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>个</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				</c:if>
				<c:forEach items="${data.djijkxzlist}" var="item" varStatus="s">
					<c:if test="${s.index eq 0}">
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>个</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
					</c:if>
				</c:forEach>
				<td rowspan="${data.djijkxzlistRows}">11</td>
				<td rowspan="${data.djijkxzlistRows}">11</td>
				<td rowspan="${data.djijkxzlistRows}">11</td>
				<td rowspan="${data.djijkxzlistRows}">11</td>
			</tr>
			<c:forEach items="${data.djijkxzlist}" var="item" varStatus="s">
				<c:if test="">
				<tr>
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>个</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>

				</tr>
				</c:if>
			</c:forEach>
			
			<tr>
				<td rowspan="${data.xjijkxzlistRows}">县级监控中心</td>
				<td rowspan="${data.xjijkxzlistRows}">升级改造</td>
				<c:if test="${empty data.xjijkxzlist}">
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>个</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				</c:if>
				<c:forEach items="${data.xjijkxzlist}" var="item" varStatus="s">
					<c:if test="${s.index eq 0}">
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>个</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
					</c:if>
				</c:forEach>
				<td rowspan="${data.xjijkxzlistRows}">11</td>
				<td rowspan="${data.xjijkxzlistRows}">11</td>
				<td rowspan="${data.xjijkxzlistRows}">11</td>
				<td rowspan="${data.xjijkxzlistRows}">11</td>
			</tr>
			<c:forEach items="${data.xjijkxzlist}" var="item" varStatus="s">
				<c:if test="${s.index ne 0}">
					<tr>
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>个</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>

					</tr>
				</c:if>
			</c:forEach>
			
			<tr>
				<td rowspan="${data.xjjkzlistRows}">监控站</td>
				<td rowspan="${data.xjjkzlistRows}">新建</td>
				<c:if test="${empty data.xjjkzlist }">
					<td>22</td>
					<td>22</td>
					<td>22</td>
					<td>套</td>
					<td>22</td>
					<td>22</td>
					<td>22</td>
				</c:if>
				<c:forEach items="${data.xjjkzlist}" var="item" varStatus="s">
					<c:if test="${s.index eq 0}">
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>套</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
					</c:if>
				</c:forEach>
				<td rowspan="${data.xjjkzlistRows}">22</td>
				<td rowspan="${data.xjjkzlistRows}">22</td>
				<td rowspan="${data.xjjkzlistRows}">22</td>
				<td rowspan="${data.xjjkzlistRows}">22</td>
			</tr>

		<c:forEach items="${data.xjjkzlist}" var="item" varStatus="s">
			<c:if test="${s.index ne 0}">
					<tr>
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>套</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
					</tr>
			</c:if>
		</c:forEach>

			
			<tr>
				<td rowspan="${data.list_spqdRows}">监控前端</td>
				<td rowspan="${data.list_spqdRows}">新建</td>
				<c:if test="${empty data.list_spqd}">
					<td>11</td>
					<td>11</td>
					<td>11</td>
					<td>个</td>
					<td>11</td>
					<td>11</td>
					<td>11</td>
				</c:if>
				
				<c:forEach items="${data.list_spqd}" var="item" varStatus="s">
					<c:if test="${s.index eq 0}">
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>个</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
					</c:if>
				</c:forEach>
				<td rowspan="${data.list_spqdRows}">11</td>
				<td rowspan="${data.list_spqdRows}">11</td>
				<td rowspan="${data.list_spqdRows}">11</td>
				<td rowspan="${data.list_spqdRows}">11</td>
			</tr>

		<c:forEach items="${data.list_spqd}" var="item" varStatus="s">
			<c:if test="${s.index ne 0}">
				<tr>
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>个</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
			</tr>
			</c:if>
		</c:forEach>
		</tbody>
	</table>


