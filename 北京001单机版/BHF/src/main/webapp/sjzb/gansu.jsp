<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<c:if test="${partTag==1 }">
		<caption>甘肃边海防基础设施建设任务统筹规划</caption>
	</c:if>
	<c:if test="${partTag==2 }">
		<caption>甘肃${year }年度边海防基础设施建设任务</caption>
	</c:if>
	<thead>
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
	</thead>
	<tbody>
		<tr>
			<td rowspan="${data.rows + 1 }">甘肃省</td>
			<td colspan="2">总投资</td>

			<td colspan="10"></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td rowspan="${data.jtbzssRows }">交通保障设施</td>
			<td rowspan="${data.zqdlRows }">执勤道路</td>
			<td rowspan="${data.zqdlSjRows }">升级改造</td>
			<c:if test="${empty data.zqdlSj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.zqdlSj }" var="item" varStatus="s">
				<c:if test="${s.index eq 0 }">
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>公里</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.zqdlSjRows }"></td>
			<td rowspan="${data.zqdlSjRows }"></td>
			<td rowspan="${data.zqdlSjRows }"></td>
			<td rowspan="${data.zqdlSjRows }"></td>
		</tr>
		<c:forEach items="${data.zqdlSj }" var="item" varStatus="s">
			<c:if test="${s.index gt 0 }">
				<tr>
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>公里</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</tr>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.zhjkssRows }">指挥监控设施</td>
			<td rowspan="${data.spqdRows }">监控前端</td>
			<td rowspan="${data.spqdXjRows }">新建</td>
			<c:if test="${empty data.spqdXj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.spqdXj }" var="item" varStatus="s">
				<c:if test="${s.index eq 0 }">
					<td>${s.index+1 }</td>
					<td>${item.fzdd }</td>
					<td></td>
					<td>座</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.spqdXjRows }"></td>
			<td rowspan="${data.spqdXjRows }"></td>
			<td rowspan="${data.spqdXjRows }"></td>
			<td rowspan="${data.spqdXjRows }"></td>
		</tr>
		<c:forEach items="${data.spqdXj }" var="item" varStatus="s">
			<c:if test="${s.index gt 0 }">
				<tr>
					<td>${s.index+1 }</td>
					<td>${item.fzdd }</td>
					<td></td>
					<td>座</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</tr>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.fzptssRows }">辅助配套设施</td>
			<td rowspan="${data.lwtRows }">了望塔</td>
			<td rowspan="${data.lwtSjRows }">新建</td>
			<c:if test="${empty data.lwtXj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.lwtXj }" var="item" varStatus="s">
				<c:if test="${s.index eq 0 }">
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>座</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.lwtSjRows }"></td>
			<td rowspan="${data.lwtSjRows }"></td>
			<td rowspan="${data.lwtSjRows }"></td>
			<td rowspan="${data.lwtSjRows }"></td>
		</tr>
		<c:forEach items="${data.lwtXj }" var="item" varStatus="s">
			<c:if test="${s.index gt 0 }">
				<tr>
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>座</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</tr>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.bzpRows }">标志牌</td>
			<td rowspan="${data.bzpXjRows }">新建</td>
			<c:if test="${empty data.bzpXj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.bzpXj }" var="item" varStatus="s">
				<c:if test="${s.index eq 0 }">
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>座</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.bzpXjRows }"></td>
			<td rowspan="${data.bzpXjRows }"></td>
			<td rowspan="${data.bzpXjRows }"></td>
			<td rowspan="${data.bzpXjRows }"></td>
		</tr>
		<c:forEach items="${data.bzpXj }" var="item" varStatus="s">
			<c:if test="${s.index gt 0 }">
				<tr>
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>座</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</tr>
			</c:if>
		</c:forEach>
	</tbody>
</table>
</body>
</html>
