<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
.implementPlanT{border: 1px solid #e6e4e4;border-collapse: collapse;text-align: center;width: 100%;color: #585858;}
.implementPlanT td, .implementPlanT th {border: 1px solid #e6e4e4;}
</style>
<table class="implementPlanT">
	<c:if test="${partTag==1 }">
		<caption>吉林边海防基础设施建设任务统筹规划</caption>
	</c:if>
	<c:if test="${partTag==2 }">
		<caption>吉林${year }年度边海防基础设施建设任务</caption>
	</c:if>
	<thead>
		<tr>
			<th rowspan="2">建设区域</th>
			<th rowspan="2" colspan="2">项目名称</th>
			<th rowspan="2">建设性质</th>
			<th colspan="7">建设内容</th>
			<th rowspan="2">建设数量合计</th>
			<th rowspan="2">建设投资合计（万元）</th>
			<th rowspan="2">中央投资合计（万元）</th>
			<th rowspan="2">地方投资合计（万元）</th>
		</tr>
		<tr>
			<th>序号</th>
			<th>建设点位</th>
			<th>数量</th>
			<th>计量单位</th>
			<th>建设投资（万元）</th>
			<th>中央投资（万元）</th>
			<th>地方投资（万元）</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td rowspan="${data.rows+1}">吉林省</td>
			<td colspan="2">总投资</td>
			<td colspan="10"></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td rowspan="${data.zqdl ==null || fn:length(data.zqdl)==0 ?1:fn:length(data.zqdl)}">交通保障设施</td>
			<td rowspan="${data.zqdl ==null || fn:length(data.zqdl)==0 ?1:fn:length(data.zqdl)}">执勤道路</td>
			<td rowspan="${data.zqdl ==null || fn:length(data.zqdl)==0 ?1:fn:length(data.zqdl)}">新建</td>
			<c:if test="${data.zqdl ==null || fn:length(data.zqdl)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.zqdl }" var="item" varStatus="s">
				<c:if test="${s.index ==0 }">
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>公里</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.zqdl ==null || fn:length(data.zqdl)==0 ?1:fn:length(data.zqdl)}"></td>
			<td rowspan="${data.zqdl ==null || fn:length(data.zqdl)==0 ?1:fn:length(data.zqdl)}"></td>
			<td rowspan="${data.zqdl ==null || fn:length(data.zqdl)==0 ?1:fn:length(data.zqdl)}"></td>
			<td rowspan="${data.zqdl ==null || fn:length(data.zqdl)==0 ?1:fn:length(data.zqdl)}"></td>
		</tr>
		<c:forEach items="${data.zqdl }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
				<td>${s.index+1 }</td>
				<td>${item.jsdd }</td>
				<td>${item.jsgm }</td>
				<td>公里</td>
				<td>${item.zytz+item.dftz }</td>
				<td>${item.zytz }</td>
				<td>${item.dftz }</td>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.lzss_rows }">拦阻报警设施</td>
			<td rowspan="${data.lzss_rows }">铁丝网</td>
			<td rowspan="${data.tsw_xj ==null || fn:length(data.tsw_xj)==0 ?1:fn:length(data.tsw_xj)}">新建</td>
			<c:if test="${data.tsw_xj ==null || fn:length(data.tsw_xj)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.tsw_xj }" var="item" varStatus="s">
				<c:if test="${s.index ==0 }">
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>公里</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.tsw_xj ==null || fn:length(data.tsw_xj)==0 ?1:fn:length(data.tsw_xj)}"></td>
			<td rowspan="${data.tsw_xj ==null || fn:length(data.tsw_xj)==0 ?1:fn:length(data.tsw_xj)}"></td>
			<td rowspan="${data.tsw_xj ==null || fn:length(data.tsw_xj)==0 ?1:fn:length(data.tsw_xj)}"></td>
			<td rowspan="${data.tsw_xj ==null || fn:length(data.tsw_xj)==0 ?1:fn:length(data.tsw_xj)}"></td>
		</tr>
		<c:forEach items="${data.tsw_xj }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
				<td>${s.index+1 }</td>
				<td>${item.jsdd }</td>
				<td>${item.jsgm }</td>
				<td>公里</td>
				<td>${item.zytz+item.dftz }</td>
				<td>${item.zytz }</td>
				<td>${item.dftz }</td>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.tsw_sj ==null || fn:length(data.tsw_sj)==0 ?1:fn:length(data.tsw_sj)}">升级</td>
			<c:if test="${data.tsw_sj ==null || fn:length(data.tsw_sj)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.tsw_sj }" var="item" varStatus="s">
				<c:if test="${s.index ==0 }">
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>公里</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.tsw_sj ==null || fn:length(data.tsw_sj)==0 ?1:fn:length(data.tsw_sj)}"></td>
			<td rowspan="${data.tsw_sj ==null || fn:length(data.tsw_sj)==0 ?1:fn:length(data.tsw_sj)}"></td>
			<td rowspan="${data.tsw_sj ==null || fn:length(data.tsw_sj)==0 ?1:fn:length(data.tsw_sj)}"></td>
			<td rowspan="${data.tsw_sj ==null || fn:length(data.tsw_sj)==0 ?1:fn:length(data.tsw_sj)}"></td>
		</tr>
		<c:forEach items="${data.tsw_xj }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
				<td>${s.index+1 }</td>
				<td>${item.jsdd }</td>
				<td>${item.jsgm }</td>
				<td>公里</td>
				<td>${item.zytz+item.dftz }</td>
				<td>${item.zytz }</td>
				<td>${item.dftz }</td>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.jkss_rows }">指挥监控设施</td>
			<td rowspan="1">监控前端</td>
			<td rowspan="1">新建</td>
			<c:if test="${data.spqd_xj ==null || fn:length(data.spqd_xj)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td>个</td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.spqd_xj }" var="item" varStatus="s">
				<c:if test="${s.index ==0 }">
					<td>${s.index+1 }</td>
					<td>${item.fzdd }</td>
					<td></td>
					<td>个</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.spqd_xj ==null || fn:length(data.spqd_xj)==0 ? 1 : fn:length(data.spqd_xj) }"></td>
			<td rowspan="${data.spqd_xj ==null || fn:length(data.spqd_xj)==0 ? 1 : fn:length(data.spqd_xj) }"></td>
			<td rowspan="${data.spqd_xj ==null || fn:length(data.spqd_xj)==0 ? 1 : fn:length(data.spqd_xj) }"></td>
			<td rowspan="${data.spqd_xj ==null || fn:length(data.spqd_xj)==0 ? 1 : fn:length(data.spqd_xj) }"></td>
		</tr>
		<c:forEach items="${data.spqd_xj }" var="item" varStatus="s">
			<c:if test="${s.index >0 }">
				<tr>
					<td>${s.index+1 }</td>
					<td>${item.fzdd }</td>
					<td></td>
					<td>个</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</tr>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.csxl_xj ==null || fn:length(data.csxl_xj)==0 ? 1 : fn:length(data.csxl_xj) }">传输线路</td>
			<td rowspan="${data.csxl_xj ==null || fn:length(data.csxl_xj)==0 ? 1 : fn:length(data.csxl_xj) }">新建</td>
			<c:if test="${data.csxl_xj ==null || fn:length(data.csxl_xj)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td>公里</td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.csxl_xj }" var="item" varStatus="s">
				<c:if test="${s.index ==0 }">
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>个</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.csxl_xj ==null || fn:length(data.csxl_xj)==0 ? 1 : fn:length(data.csxl_xj) }"></td>
			<td rowspan="${data.csxl_xj ==null || fn:length(data.csxl_xj)==0 ? 1 : fn:length(data.csxl_xj) }"></td>
			<td rowspan="${data.csxl_xj ==null || fn:length(data.csxl_xj)==0 ? 1 : fn:length(data.csxl_xj) }"></td>
			<td rowspan="${data.csxl_xj ==null || fn:length(data.csxl_xj)==0 ? 1 : fn:length(data.csxl_xj) }"></td>
		</tr>
		<c:forEach items="${data.csxl_xj }" var="item" varStatus="s">
			<c:if test="${s.index >0 }">
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
			<td rowspan="${data.bzp ==null || fn:length(data.bzp)==0 ?1:fn:length(data.bzp)}">辅助配套设施</td>
			<td rowspan="${data.bzp ==null || fn:length(data.bzp)==0 ?1:fn:length(data.bzp)}">标志牌</td>
			<td rowspan="${data.bzp ==null || fn:length(data.bzp)==0 ?1:fn:length(data.bzp)}">新建</td>
			<c:if test="${data.bzp ==null || fn:length(data.bzp)==0}">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.bzp }" var="item" varStatus="s">
				<c:if test="${s.index ==0 }">
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>公里</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.bzp ==null || fn:length(data.bzp)==0 ?1:fn:length(data.bzp)}"></td>
			<td rowspan="${data.bzp ==null || fn:length(data.bzp)==0 ?1:fn:length(data.bzp)}"></td>
			<td rowspan="${data.bzp ==null || fn:length(data.bzp)==0 ?1:fn:length(data.bzp)}"></td>
			<td rowspan="${data.bzp ==null || fn:length(data.bzp)==0 ?1:fn:length(data.bzp)}"></td>
		</tr>
		<c:forEach items="${data.bzp }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
				<td>${s.index+1 }</td>
				<td>${item.jsdd }</td>
				<td>${item.jsgm }</td>
				<td>公里</td>
				<td>${item.zytz+item.dftz }</td>
				<td>${item.zytz }</td>
				<td>${item.dftz }</td>
			</c:if>
		</c:forEach>
	</tbody>
</table>