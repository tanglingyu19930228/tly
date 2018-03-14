<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
.implementPlanT{border: 1px solid #e6e4e4;border-collapse: collapse;text-align: center;width: 100%;color: #585858;}
.implementPlanT td, .implementPlanT th {border: 1px solid #585858;}
.right{text-align:right;}
.jsqy{
	width:2%;
}
.xmlb{
	width:2%;
}
.xmzl{
	width:2%;
}
.jsdd{
	/* width:30%; */
	text-align: left;
}
</style>
<table id="sjzb" class="implementPlanT" border="1">
	<c:if test="${partTag==1 }">
		<caption>${szsf }<c:if test="${not empty year }">${year }年</c:if>边海防基础设施建设任务统筹规划</caption>
	</c:if>
	<c:if test="${partTag==2 }">
		<caption>${szsf }${year }年度边海防基础设施建设任务</caption>
	</c:if>
	<thead>
		<tr>
			<th rowspan="2">建设区域</th>
			<th rowspan="2" colspan="2">项目名称</th>
			<th rowspan="2">建设性质</th>
			<th colspan="7">建设内容</th>
			<th rowspan="2">建设数量合计</th>
			<th rowspan="2">建设投资合计<br/>（万元）</th>
			<th rowspan="2">中央投资合计<br/>（万元）</th>
			<th rowspan="2">地方投资合计<br/>（万元）</th>
		</tr>
		<tr>
			<th>序号</th>
			<th style="width:30%;">建设点位</th>
			<th>数量</th>
			<th>计量单位</th>
			<th>建设投资（万元）</th>
			<th>中央投资（万元）</th>
			<th>地方投资（万元）</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td colspan="3">总投资</td>
			<td colspan="9"></td>
			<td class="right">
				<c:if test="${data.tzHjMap.dxtz ne 0 }">
					<fmt:formatNumber type="number" value="${data.tzHjMap.dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td class="right">
				<c:if test="${data.tzHjMap.zytz ne 0 }">
					<fmt:formatNumber type="number" value="${data.tzHjMap.zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td class="right">
				<c:if test="${data.tzHjMap.dftz ne 0 }">
					<fmt:formatNumber type="number" value="${data.tzHjMap.dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<!-- 交通设施 -->
		<c:if test="${data.jtss_rows !=0 }">
			<tr>
				<td rowspan="${data.jtss_rows }" class="jsqy">${szsf }</td>
				<td rowspan="${data.jtss_rows }" class="xmlb">交通保障设施</td>
				<td rowspan="${data.zqdl_row }" class="xmzl">执勤道路</td>
				<!-- 新建执勤道路 -->
				<td rowspan="${data.xj_zqdl.rows }">新建</td>
				<c:if test="${fn:length(data.xj_zqdl.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_zqdl.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_zqdl.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_zqdl.rows }"><fmt:formatNumber type="number" value="${data.xj_zqdl.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_zqdl.rows }"><fmt:formatNumber type="number" value="${data.xj_zqdl.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_zqdl.rows }"><fmt:formatNumber type="number" value="${data.xj_zqdl.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_zqdl.rows }"><fmt:formatNumber type="number" value="${data.xj_zqdl.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_zqdl.dataList }" var="item" varStatus="s">
				<c:if test="${s.index != 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<!-- 升级执勤道路 -->
			<tr>
				<td rowspan="${data.sj_zqdl.rows }">升级</td>
				<c:if test="${fn:length(data.sj_zqdl.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_zqdl.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_zqdl.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_zqdl.rows }"><fmt:formatNumber type="number" value="${data.sj_zqdl.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_zqdl.rows }"><fmt:formatNumber type="number" value="${data.sj_zqdl.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_zqdl.rows }"><fmt:formatNumber type="number" value="${data.sj_zqdl.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_zqdl.rows }"><fmt:formatNumber type="number" value="${data.sj_zqdl.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_zqdl.dataList }" var="item" varStatus="s">
				<c:if test="${s.index != 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<!-- 新建桥梁-->
			<tr>
				<td rowspan="${data.qiaoliang_row }">桥梁</td>
				<td rowspan="${data.xj_qiaoliang.rows }">新建</td>
				<c:if test="${fn:length(data.xj_qiaoliang.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_qiaoliang.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_qiaoliang.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_qiaoliang.rows }"><fmt:formatNumber type="number" value="${data.xj_qiaoliang.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_qiaoliang.rows }"><fmt:formatNumber type="number" value="${data.xj_qiaoliang.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_qiaoliang.rows }"><fmt:formatNumber type="number" value="${data.xj_qiaoliang.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_qiaoliang.rows }"><fmt:formatNumber type="number" value="${data.xj_qiaoliang.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_qiaoliang.dataList }" var="item" varStatus="s">
				<c:if test="${s.index != 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<!-- 升级桥梁-->
			<tr>
				<td rowspan="${data.sj_qiaoliang.rows }">升级</td>
				<c:if test="${fn:length(data.sj_qiaoliang.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_qiaoliang.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_qiaoliang.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_qiaoliang.rows }"><fmt:formatNumber type="number" value="${data.sj_qiaoliang.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_qiaoliang.rows }"><fmt:formatNumber type="number" value="${data.sj_qiaoliang.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_qiaoliang.rows }"><fmt:formatNumber type="number" value="${data.sj_qiaoliang.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_qiaoliang.rows }"><fmt:formatNumber type="number" value="${data.sj_qiaoliang.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_qiaoliang.dataList }" var="item" varStatus="s">
				<c:if test="${s.index != 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<!-- 新建执勤码头-->
			<tr>
				<td rowspan="${data.zqmt_row }">执勤码头</td>
				<td rowspan="${data.xj_zqmt.rows }">新建</td>
				<c:if test="${fn:length(data.xj_zqmt.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_zqmt.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_zqmt.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_zqmt.rows }"><fmt:formatNumber type="number" value="${data.xj_zqmt.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_zqmt.rows }"><fmt:formatNumber type="number" value="${data.xj_zqmt.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_zqmt.rows }"><fmt:formatNumber type="number" value="${data.xj_zqmt.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_zqmt.rows }"><fmt:formatNumber type="number" value="${data.xj_zqmt.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_zqmt.dataList }" var="item" varStatus="s">
				<c:if test="${s.index != 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<!-- 升级执勤码头-->
			<tr>
				<td rowspan="${data.sj_zqmt.rows }">升级</td>
				<c:if test="${fn:length(data.sj_zqmt.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_zqmt.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_zqmt.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_zqmt.rows }"><fmt:formatNumber type="number" value="${data.sj_zqmt.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_zqmt.rows }"><fmt:formatNumber type="number" value="${data.sj_zqmt.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_zqmt.rows }"><fmt:formatNumber type="number" value="${data.sj_zqmt.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_zqmt.rows }"><fmt:formatNumber type="number" value="${data.sj_zqmt.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_zqmt.dataList }" var="item" varStatus="s">
				<c:if test="${s.index != 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<!-- 新建直升机停机坪-->
			<tr>
				<td rowspan="${data.zsjtjp_row }">直升机停机坪</td>
				<td rowspan="${data.xj_zsjtjp.rows }">新建</td>
				<c:if test="${fn:length(data.xj_zsjtjp.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_zsjtjp.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_zsjtjp.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_zsjtjp.rows }"><fmt:formatNumber type="number" value="${data.xj_zsjtjp.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_zsjtjp.rows }"><fmt:formatNumber type="number" value="${data.xj_zsjtjp.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_zsjtjp.rows }"><fmt:formatNumber type="number" value="${data.xj_zsjtjp.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_zsjtjp.rows }"><fmt:formatNumber type="number" value="${data.xj_zsjtjp.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_zsjtjp.dataList }" var="item" varStatus="s">
				<c:if test="${s.index != 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_zsjtjp.rows }">升级</td>
				<c:if test="${fn:length(data.sj_zsjtjp.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_zsjtjp.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_zsjtjp.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_zsjtjp.rows }"><fmt:formatNumber type="number" value="${data.sj_zsjtjp.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_zsjtjp.rows }"><fmt:formatNumber type="number" value="${data.sj_zsjtjp.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_zsjtjp.rows }"><fmt:formatNumber type="number" value="${data.sj_zsjtjp.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_zsjtjp.rows }"><fmt:formatNumber type="number" value="${data.sj_zsjtjp.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_zsjtjp.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:if>
		<!-- 拦阻设施 -->
		<c:if test="${data.lzss_rows !=0 }">
			<tr>
				<td rowspan="${data.lzss_rows }">${szsf }</td>
				<td rowspan="${data.lzss_rows }">拦阻报警设施</td>
				<td rowspan="${data.tsw_row }">铁丝网</td>
				<td rowspan="${data.xj_tsw.rows  }">新建</td>
				<c:if test="${fn:length(data.xj_tsw.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_tsw.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_tsw.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_tsw.rows }"><fmt:formatNumber type="number" value="${data.xj_tsw.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_tsw.rows }"><fmt:formatNumber type="number" value="${data.xj_tsw.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_tsw.rows }"><fmt:formatNumber type="number" value="${data.xj_tsw.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_tsw.rows }"><fmt:formatNumber type="number" value="${data.xj_tsw.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_tsw.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_tsw.rows }">升级</td>
				<c:if test="${fn:length(data.sj_tsw.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_tsw.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_tsw.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_tsw.rows }"><fmt:formatNumber type="number" value="${data.sj_tsw.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_tsw.rows }"><fmt:formatNumber type="number" value="${data.sj_tsw.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_tsw.rows }"><fmt:formatNumber type="number" value="${data.sj_tsw.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_tsw.rows }"><fmt:formatNumber type="number" value="${data.sj_tsw.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_tsw.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			
			<!-- 铁栅栏 -->
			<tr>
				<td rowspan="${data.tzl_row  }">铁栅栏</td>
				<td rowspan="${data.xj_tzl.rows  }">新建</td>
				<c:if test="${fn:length(data.xj_tzl.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_tzl.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_tzl.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_tzl.rows }"><fmt:formatNumber type="number" value="${data.xj_tzl.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_tzl.rows }"><fmt:formatNumber type="number" value="${data.xj_tzl.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_tzl.rows }"><fmt:formatNumber type="number" value="${data.xj_tzl.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_tzl.rows }"><fmt:formatNumber type="number" value="${data.xj_tzl.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_tzl.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_tzl.rows  }">升级</td>
				<c:if test="${fn:length(data.sj_tzl.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_tzl.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_tzl.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_tzl.rows }"><fmt:formatNumber type="number" value="${data.sj_tzl.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_tzl.rows }"><fmt:formatNumber type="number" value="${data.sj_tzl.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_tzl.rows }"><fmt:formatNumber type="number" value="${data.sj_tzl.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_tzl.rows }"><fmt:formatNumber type="number" value="${data.sj_tzl.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_tzl.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			
			<!-- 拒马 -->
			<tr>
				<td rowspan="${data.juma_row  }">拒马</td>
				<td rowspan="${data.xj_juma.rows  }">新建</td>
				<c:if test="${fn:length(data.xj_juma.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_juma.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_juma.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_juma.rows }"><fmt:formatNumber type="number" value="${data.xj_juma.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_juma.rows }"><fmt:formatNumber type="number" value="${data.xj_juma.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_juma.rows }"><fmt:formatNumber type="number" value="${data.xj_juma.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_juma.rows }"><fmt:formatNumber type="number" value="${data.xj_juma.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_juma.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_juma.rows  }">升级</td>
				<c:if test="${fn:length(data.sj_juma.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_juma.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_juma.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_juma.rows }"><fmt:formatNumber type="number" value="${data.sj_juma.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_juma.rows }"><fmt:formatNumber type="number" value="${data.sj_juma.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_juma.rows }"><fmt:formatNumber type="number" value="${data.sj_juma.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_juma.rows }"><fmt:formatNumber type="number" value="${data.sj_juma.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_juma.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			
			<!-- 拦阻桩 -->
			<tr>
				<td rowspan="${data.lzz_row  }">拦阻桩</td>
				<td rowspan="${data.xj_lzz.rows  }">新建</td>
				<c:if test="${fn:length(data.xj_lzz.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_lzz.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_lzz.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_lzz.rows }"><fmt:formatNumber type="number" value="${data.xj_lzz.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_lzz.rows }"><fmt:formatNumber type="number" value="${data.xj_lzz.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_lzz.rows }"><fmt:formatNumber type="number" value="${data.xj_lzz.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_lzz.rows }"><fmt:formatNumber type="number" value="${data.xj_lzz.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_lzz.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_lzz.rows  }">升级</td>
				<c:if test="${fn:length(data.sj_lzz.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_lzz.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_lzz.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_lzz.rows }"><fmt:formatNumber type="number" value="${data.sj_lzz.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_lzz.rows }"><fmt:formatNumber type="number" value="${data.sj_lzz.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_lzz.rows }"><fmt:formatNumber type="number" value="${data.sj_lzz.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_lzz.rows }"><fmt:formatNumber type="number" value="${data.sj_lzz.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_lzz.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			
			<!-- 隔离带 -->
			<tr>
				<td rowspan="${data.gld_row }">隔离带</td>
				<td rowspan="${data.xj_gld.rows }">新建</td>
				<c:if test="${fn:length(data.xj_gld.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_gld.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">米</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_gld.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_gld.rows }"><fmt:formatNumber type="number" value="${data.xj_gld.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_gld.rows }"><fmt:formatNumber type="number" value="${data.xj_gld.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_gld.rows }"><fmt:formatNumber type="number" value="${data.xj_gld.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_gld.rows }"><fmt:formatNumber type="number" value="${data.xj_gld.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_gld.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">米</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_gld.rows }">升级</td>
				<c:if test="${fn:length(data.sj_gld.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_gld.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">米</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_gld.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_gld.rows }"><fmt:formatNumber type="number" value="${data.sj_gld.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_gld.rows }"><fmt:formatNumber type="number" value="${data.sj_gld.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_gld.rows }"><fmt:formatNumber type="number" value="${data.sj_gld.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_gld.rows }"><fmt:formatNumber type="number" value="${data.sj_gld.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_gld.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">米</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			
			
			<!-- 报警线路 -->
			<tr>
				<td rowspan="${data.bjxl_row }">报警线路</td>
				<td rowspan="${data.xj_bjxl.rows }">新建</td>
				<c:if test="${fn:length(data.xj_bjxl.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_bjxl.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_bjxl.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_bjxl.rows }"><fmt:formatNumber type="number" value="${data.xj_bjxl.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_bjxl.rows }"><fmt:formatNumber type="number" value="${data.xj_bjxl.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_bjxl.rows }"><fmt:formatNumber type="number" value="${data.xj_bjxl.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_bjxl.rows }"><fmt:formatNumber type="number" value="${data.xj_bjxl.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_bjxl.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_bjxl.rows }">升级</td>
				<c:if test="${fn:length(data.sj_bjxl.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_bjxl.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_bjxl.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_bjxl.rows }"><fmt:formatNumber type="number" value="${data.sj_bjxl.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_bjxl.rows }"><fmt:formatNumber type="number" value="${data.sj_bjxl.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_bjxl.rows }"><fmt:formatNumber type="number" value="${data.sj_bjxl.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_bjxl.rows }"><fmt:formatNumber type="number" value="${data.sj_bjxl.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_bjxl.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:if>
		
		<!-- 指挥监控设施 -->
		<c:if test="${data.zhjkss_rows !=0 }">
			<tr>
				<td rowspan="${data.zhjkss_rows }">${szsf }</td>
				<td rowspan="${data.zhjkss_rows }">指挥监控设施</td>
				<td rowspan="${data.jkzxGjj_row }">国家级监控中心</td>
				<td rowspan="${data.xj_jkzxGjj.rows  }">新建</td>
				<c:if test="${fn:length(data.xj_jkzxGjj.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_jkzxGjj.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_jkzxGjj.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_jkzxGjj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxGjj.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkzxGjj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxGjj.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkzxGjj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxGjj.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkzxGjj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxGjj.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_jkzxGjj.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_jkzxGjj.rows }">升级</td>
				<c:if test="${fn:length(data.sj_jkzxGjj.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_jkzxGjj.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_jkzxGjj.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_jkzxGjj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxGjj.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkzxGjj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxGjj.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkzxGjj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxGjj.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkzxGjj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxGjj.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_jkzxGjj.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.jkzxSj_row }">省级监控中心</td>
				<td rowspan="${data.xj_jkzxSj.rows  }">新建</td>
				<c:if test="${fn:length(data.xj_jkzxSj.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_jkzxSj.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_jkzxSj.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_jkzxSj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxSj.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkzxSj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxSj.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkzxSj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxSj.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkzxSj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxSj.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_jkzxSj.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_jkzxSj.rows }">升级</td>
				<c:if test="${fn:length(data.sj_jkzxSj.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_jkzxSj.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_jkzxSj.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_jkzxSj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxSj.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkzxSj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxSj.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkzxSj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxSj.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkzxSj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxSj.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_jkzxSj.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.jkzxDj_row }">地级监控中心</td>
				<td rowspan="${data.xj_jkzxDj.rows  }">新建</td>
				<c:if test="${fn:length(data.xj_jkzxDj.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_jkzxDj.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_jkzxDj.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_jkzxDj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxDj.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkzxDj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxDj.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkzxDj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxDj.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkzxDj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxDj.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_jkzxDj.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_jkzxDj.rows }">升级</td>
				<c:if test="${fn:length(data.sj_jkzxDj.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_jkzxDj.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_jkzxDj.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_jkzxDj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxDj.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkzxDj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxDj.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkzxDj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxDj.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkzxDj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxDj.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_jkzxDj.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.jkzxXj_row }">县级监控中心</td>
				<td rowspan="${data.xj_jkzxXj.rows  }">新建</td>
				<c:if test="${fn:length(data.xj_jkzxXj.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_jkzxXj.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_jkzxXj.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_jkzxXj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxXj.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkzxXj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxXj.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkzxXj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxXj.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkzxXj.rows }"><fmt:formatNumber type="number" value="${data.xj_jkzxXj.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_jkzxXj.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_jkzxXj.rows }">升级</td>
				<c:if test="${fn:length(data.sj_jkzxXj.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_jkzxXj.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_jkzxXj.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_jkzxXj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxXj.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkzxXj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxXj.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkzxXj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxXj.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkzxXj.rows }"><fmt:formatNumber type="number" value="${data.sj_jkzxXj.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_jkzxXj.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<!-- 监控站 -->
			<tr>
				<td rowspan="${data.jkz_row }">监控站</td>
				<td rowspan="${data.xj_jkz.rows }">新建</td>
				<c:if test="${fn:length(data.xj_jkz.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_jkz.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_jkz.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_jkz.rows }"><fmt:formatNumber type="number" value="${data.xj_jkz.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkz.rows }"><fmt:formatNumber type="number" value="${data.xj_jkz.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkz.rows }"><fmt:formatNumber type="number" value="${data.xj_jkz.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jkz.rows }"><fmt:formatNumber type="number" value="${data.xj_jkz.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_jkz.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_jkz.rows }">升级</td>
				<c:if test="${fn:length(data.sj_jkz.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_jkz.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_jkz.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_jkz.rows }"><fmt:formatNumber type="number" value="${data.sj_jkz.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkz.rows }"><fmt:formatNumber type="number" value="${data.sj_jkz.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkz.rows }"><fmt:formatNumber type="number" value="${data.sj_jkz.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jkz.rows }"><fmt:formatNumber type="number" value="${data.sj_jkz.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_jkz.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			
			<!-- 传输线路 -->
			<tr>
				<td rowspan="${data.csxl_row }">传输线路</td>
				<td rowspan="${data.xj_csxl.rows }">新建</td>
				<c:if test="${fn:length(data.xj_csxl.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_csxl.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_csxl.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_csxl.rows }"><fmt:formatNumber type="number" value="${data.xj_csxl.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_csxl.rows }"><fmt:formatNumber type="number" value="${data.xj_csxl.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_csxl.rows }"><fmt:formatNumber type="number" value="${data.xj_csxl.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_csxl.rows }"><fmt:formatNumber type="number" value="${data.xj_csxl.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_csxl.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_csxl.rows }">升级</td>
				<c:if test="${fn:length(data.sj_csxl.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_csxl.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_csxl.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_csxl.rows }"><fmt:formatNumber type="number" value="${data.sj_csxl.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_csxl.rows }"><fmt:formatNumber type="number" value="${data.sj_csxl.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_csxl.rows }"><fmt:formatNumber type="number" value="${data.sj_csxl.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_csxl.rows }"><fmt:formatNumber type="number" value="${data.sj_csxl.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_csxl.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">公里</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			
			
			<!-- 供电系统 -->
			<tr>
				<td rowspan="${data.gdxt_row }">供电系统</td>
				<td rowspan="${data.xj_gdxt.rows }">新建</td>
				<c:if test="${fn:length(data.xj_gdxt.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_gdxt.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_gdxt.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_gdxt.rows }"><fmt:formatNumber type="number" value="${data.xj_gdxt.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_gdxt.rows }"><fmt:formatNumber type="number" value="${data.xj_gdxt.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_gdxt.rows }"><fmt:formatNumber type="number" value="${data.xj_gdxt.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_gdxt.rows }"><fmt:formatNumber type="number" value="${data.xj_gdxt.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_gdxt.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_gdxt.rows }">升级</td>
				<c:if test="${fn:length(data.sj_gdxt.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_gdxt.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_gdxt.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_gdxt.rows }"><fmt:formatNumber type="number" value="${data.sj_gdxt.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_gdxt.rows }"><fmt:formatNumber type="number" value="${data.sj_gdxt.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_gdxt.rows }"><fmt:formatNumber type="number" value="${data.sj_gdxt.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_gdxt.rows }"><fmt:formatNumber type="number" value="${data.sj_gdxt.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_gdxt.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			
			<!-- 军警民联防平台 -->
			<tr>
				<td rowspan="${data.jjmlfpt_row }">军警民联防平台</td>
				<td rowspan="${data.xj_jjmlfpt.rows }">新建</td>
				<c:if test="${fn:length(data.xj_jjmlfpt.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_jjmlfpt.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right">1</td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_jjmlfpt.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_jjmlfpt.rows }"><fmt:formatNumber type="number" value="${data.xj_jjmlfpt.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jjmlfpt.rows }"><fmt:formatNumber type="number" value="${data.xj_jjmlfpt.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jjmlfpt.rows }"><fmt:formatNumber type="number" value="${data.xj_jjmlfpt.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_jjmlfpt.rows }"><fmt:formatNumber type="number" value="${data.xj_jjmlfpt.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_jjmlfpt.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right">1</td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_jjmlfpt.rows }">升级</td>
				<c:if test="${fn:length(data.sj_jjmlfpt.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_jjmlfpt.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right">1</td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_jjmlfpt.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_jjmlfpt.rows }"><fmt:formatNumber type="number" value="${data.sj_jjmlfpt.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jjmlfpt.rows }"><fmt:formatNumber type="number" value="${data.sj_jjmlfpt.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jjmlfpt.rows }"><fmt:formatNumber type="number" value="${data.sj_jjmlfpt.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_jjmlfpt.rows }"><fmt:formatNumber type="number" value="${data.sj_jjmlfpt.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_jjmlfpt.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right">1</td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			
			
			
			<!-- 无人值守电子哨兵 -->
			<tr>
				<td rowspan="${data.wrzsdzsb_row }">无人值守电子哨兵</td>
				<td rowspan="${data.xj_wrzsdzsb.rows }">新建</td>
				<c:if test="${fn:length(data.xj_wrzsdzsb.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_wrzsdzsb.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_wrzsdzsb.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_wrzsdzsb.rows }"><fmt:formatNumber type="number" value="${data.xj_wrzsdzsb.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_wrzsdzsb.rows }"><fmt:formatNumber type="number" value="${data.xj_wrzsdzsb.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_wrzsdzsb.rows }"><fmt:formatNumber type="number" value="${data.xj_wrzsdzsb.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_wrzsdzsb.rows }"><fmt:formatNumber type="number" value="${data.xj_wrzsdzsb.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_wrzsdzsb.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_wrzsdzsb.rows }">升级</td>
				<c:if test="${fn:length(data.sj_wrzsdzsb.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_wrzsdzsb.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_wrzsdzsb.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_wrzsdzsb.rows }"><fmt:formatNumber type="number" value="${data.sj_wrzsdzsb.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_wrzsdzsb.rows }"><fmt:formatNumber type="number" value="${data.sj_wrzsdzsb.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_wrzsdzsb.rows }"><fmt:formatNumber type="number" value="${data.sj_wrzsdzsb.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_wrzsdzsb.rows }"><fmt:formatNumber type="number" value="${data.sj_wrzsdzsb.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_wrzsdzsb.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<!-- 视频前端 -->
			<tr>
				<td rowspan="${data.spqd_row }">视频前端</td>
				<td rowspan="${data.xj_spqd.rows }">新建</td>
				<c:if test="${fn:length(data.xj_spqd.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_spqd.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_spqd.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_spqd.rows }"><fmt:formatNumber type="number" value="${data.xj_spqd.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_spqd.rows }"><fmt:formatNumber type="number" value="${data.xj_spqd.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_spqd.rows }"><fmt:formatNumber type="number" value="${data.xj_spqd.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_spqd.rows }"><fmt:formatNumber type="number" value="${data.xj_spqd.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_spqd.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_spqd.rows }">升级</td>
				<c:if test="${fn:length(data.sj_spqd.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_spqd.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_spqd.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_spqd.rows }"><fmt:formatNumber type="number" value="${data.sj_spqd.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_spqd.rows }"><fmt:formatNumber type="number" value="${data.sj_spqd.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_spqd.rows }"><fmt:formatNumber type="number" value="${data.sj_spqd.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_spqd.rows }"><fmt:formatNumber type="number" value="${data.sj_spqd.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_spqd.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			
			<!--显控终端 -->
			<tr>
				<td rowspan="${data.xkzd_row }">显控终端</td>
				<td rowspan="${data.xj_xkzd.rows }">新建</td>
				<c:if test="${fn:length(data.xj_xkzd.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_xkzd.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_xkzd.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_xkzd.rows }"><fmt:formatNumber type="number" value="${data.xj_xkzd.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_xkzd.rows }"><fmt:formatNumber type="number" value="${data.xj_xkzd.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_xkzd.rows }"><fmt:formatNumber type="number" value="${data.xj_xkzd.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_xkzd.rows }"><fmt:formatNumber type="number" value="${data.xj_xkzd.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_xkzd.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_xkzd.rows }">升级</td>
				<c:if test="${fn:length(data.sj_xkzd.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_xkzd.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_xkzd.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_xkzd.rows }"><fmt:formatNumber type="number" value="${data.sj_xkzd.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_xkzd.rows }"><fmt:formatNumber type="number" value="${data.sj_xkzd.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_xkzd.rows }"><fmt:formatNumber type="number" value="${data.sj_xkzd.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_xkzd.rows }"><fmt:formatNumber type="number" value="${data.sj_xkzd.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_xkzd.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.fzdd }</td>
						<td class="right">1</td>
						<td class="right">个</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			
			<!-- 报警装置 -->
			<tr>
				<td rowspan="${data.bjzz_row }">报警装置</td>
				<td rowspan="${data.xj_bjzz.rows }">新建</td>
				<c:if test="${fn:length(data.xj_bjzz.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_bjzz.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_bjzz.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_bjzz.rows }"><fmt:formatNumber type="number" value="${data.xj_bjzz.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_bjzz.rows }"><fmt:formatNumber type="number" value="${data.xj_bjzz.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_bjzz.rows }"><fmt:formatNumber type="number" value="${data.xj_bjzz.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_bjzz.rows }"><fmt:formatNumber type="number" value="${data.xj_bjzz.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_bjzz.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_bjzz.rows }">升级</td>
				<c:if test="${fn:length(data.sj_bjzz.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_bjzz.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_bjzz.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_bjzz.rows }"><fmt:formatNumber type="number" value="${data.sj_bjzz.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_bjzz.rows }"><fmt:formatNumber type="number" value="${data.sj_bjzz.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_bjzz.rows }"><fmt:formatNumber type="number" value="${data.sj_bjzz.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_bjzz.rows }"><fmt:formatNumber type="number" value="${data.sj_bjzz.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_bjzz.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.szsf}${item.szcs == null?"":item.szcs }${item.szq==null?"":item.szq }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">套</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:if>
		
		
		<!-- 辅助配套设施 -->
		<c:if test="${data.fzptss_rows !=0 }">
			<tr>
				<td rowspan="${data.fzptss_rows }">${szsf }</td>
				<td rowspan="${data.fzptss_rows }">辅助配套设施</td>
				<td rowspan="${data.zqf_row }">执勤房</td>
				<td rowspan="${data.xj_zqf.rows  }">新建</td>
				<c:if test="${fn:length(data.xj_zqf.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_zqf.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_zqf.dataList)gt 0 }">
					<td class="right" rowspan="${data.xj_zqf.rows }"><fmt:formatNumber type="number" value="${data.xj_zqf.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_zqf.rows }"><fmt:formatNumber type="number" value="${data.xj_zqf.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_zqf.rows }"><fmt:formatNumber type="number" value="${data.xj_zqf.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_zqf.rows }"><fmt:formatNumber type="number" value="${data.xj_zqf.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_zqf.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_zqf.rows }">升级</td>
				<c:if test="${fn:length(data.sj_zqf.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_zqf.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_zqf.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_zqf.rows }"><fmt:formatNumber type="number" value="${data.sj_zqf.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_zqf.rows }"><fmt:formatNumber type="number" value="${data.sj_zqf.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_zqf.rows }"><fmt:formatNumber type="number" value="${data.sj_zqf.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_zqf.rows }"><fmt:formatNumber type="number" value="${data.sj_zqf.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_zqf.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			
			<!-- 了望塔 -->
			<tr>
				<td rowspan="${data.lwt_row }">了望塔</td>
				<td rowspan="${data.xj_lwt.rows }">新建</td>
				<c:if test="${fn:length(data.xj_lwt.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_lwt.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_lwt.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_lwt.rows }"><fmt:formatNumber type="number" value="${data.xj_lwt.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_lwt.rows }"><fmt:formatNumber type="number" value="${data.xj_lwt.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_lwt.rows }"><fmt:formatNumber type="number" value="${data.xj_lwt.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_lwt.rows }"><fmt:formatNumber type="number" value="${data.xj_lwt.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_lwt.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_lwt.rows }">升级</td>
				<c:if test="${fn:length(data.sj_lwt.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_lwt.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_lwt.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_lwt.rows }"><fmt:formatNumber type="number" value="${data.sj_lwt.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_lwt.rows }"><fmt:formatNumber type="number" value="${data.sj_lwt.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_lwt.rows }"><fmt:formatNumber type="number" value="${data.sj_lwt.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_lwt.rows }"><fmt:formatNumber type="number" value="${data.sj_lwt.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_lwt.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			
			<!-- 标志牌 -->
			<tr>
				<td rowspan="${data.bzp_row }">标志牌</td>
				<td rowspan="${data.xj_bzp.rows }">新建</td>
				<c:if test="${fn:length(data.xj_bzp.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.xj_bzp.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.xj_bzp.dataList) gt 0 }">
					<td class="right" rowspan="${data.xj_bzp.rows }"><fmt:formatNumber type="number" value="${data.xj_bzp.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_bzp.rows }"><fmt:formatNumber type="number" value="${data.xj_bzp.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_bzp.rows }"><fmt:formatNumber type="number" value="${data.xj_bzp.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.xj_bzp.rows }"><fmt:formatNumber type="number" value="${data.xj_bzp.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.xj_bzp.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">面</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td rowspan="${data.sj_bzp.rows }">升级</td>
				<c:if test="${fn:length(data.sj_bzp.dataList)==0 }">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</c:if>
				<c:forEach items="${data.sj_bzp.dataList }" var="item" varStatus="s">
					<c:if test="${s.index == 0 }">
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(data.sj_bzp.dataList) gt 0 }">
					<td class="right" rowspan="${data.sj_bzp.rows }"><fmt:formatNumber type="number" value="${data.sj_bzp.jsgm_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_bzp.rows }"><fmt:formatNumber type="number" value="${data.sj_bzp.jstz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_bzp.rows }"><fmt:formatNumber type="number" value="${data.sj_bzp.zytz_sum }" maxFractionDigits="4"/></td>
					<td class="right" rowspan="${data.sj_bzp.rows }"><fmt:formatNumber type="number" value="${data.sj_bzp.dftz_sum }" maxFractionDigits="4"/></td>
				</c:if>
			</tr>
			<c:forEach items="${data.sj_bzp.dataList }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
					<tr>
						<td>${s.index+1 }</td>
						<td class="jsdd">${item.jsdd }</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/></td>
						<td class="right">座</td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz+item.dftz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.zytz }" maxFractionDigits="4"/></td>
						<td class="right"><fmt:formatNumber type="number" value="${item.dftz }" maxFractionDigits="4"/></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:if>
	</tbody>
</table>