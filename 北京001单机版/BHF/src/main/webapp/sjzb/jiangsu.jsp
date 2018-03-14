<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
.implementPlanT{border: 1px solid #e6e4e4;border-collapse: collapse;text-align: center;width: 100%;color: #585858;}
.implementPlanT td, .implementPlanT th {border: 1px solid #e6e4e4;}
</style>
<table class="implementPlanT">
	<caption>江苏边海防基础设施任务</caption>
	<thead>
		<tr>
	   		<th rowspan="2">建设区域</th>
	   		<th rowspan="2" colspan="2">项目名称</th>
	   		<th rowspan="2">建设性质</th>
	   		<th colspan="7">建设内容</th>
	   		<th rowspan="2">建设数量合计</th>
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
       		<td rowspan="${data.rows + 1 }">江苏省</td>
       		<td colspan="2">总投资</td>
       		<td colspan="10"></td>
       		<td></td>
       		<td></td>
       	</tr>
		<tr>
			<td rowspan="${data.zhjkssRows }">指挥监控设施</td>
			<td rowspan="${data.jkzxDjRows }">地级监控中心</td>
			<td rowspan="${data.jkzxDjSjRows }">升级改造</td>
			<c:if test="${empty data.jkzxDjSj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.jkzxDjSj }" var="item" varStatus="s">
				<c:if test="${s.index eq 0 }">
					<td>${s.index+1 }</td>
					<td></td>
					<td>1</td>
					<td>个</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.jkzxDjSjRows }"></td>
			<td rowspan="${data.jkzxDjSjRows }"></td>
			<td rowspan="${data.jkzxDjSjRows }"></td>
			<td rowspan="${data.jkzxDjSjRows }"></td>
		</tr>
		<c:forEach items="${data.jkzxDjSj }" var="item" varStatus="s">
			<c:if test="${s.index gt 0 }">
				<tr>
					<td>${s.index+1 }</td>
					<td></td>
					<td>1</td>
					<td>个</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</tr>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.jkzxXjRows }">县级监控中心</td>
			<td rowspan="${data.jkzxXjXjRows }">新建</td>
			<c:if test="${empty data.jkzxXjXj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.jkzxXjXj }" var="item" varStatus="s">
				<c:if test="${s.index eq 0 }">
					<td>${s.index+1 }</td>
					<td></td>
					<td>1</td>
					<td>个</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.jkzxXjXjRows }"></td>
			<td rowspan="${data.jkzxXjXjRows }"></td>
			<td rowspan="${data.jkzxXjXjRows }"></td>
			<td rowspan="${data.jkzxXjXjRows }"></td>
		</tr>
		<c:forEach items="${data.jkzxXjXj }" var="item" varStatus="s">
			<c:if test="${s.index gt 0 }">
				<tr>
					<td>${s.index+1 }</td>
					<td></td>
					<td>1</td>
					<td>个</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</tr>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.jkzxXjSjRows }">升级改造</td>
			<c:if test="${empty data.jkzxXjSj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.jkzxXjSj }" var="item" varStatus="s">
				<c:if test="${s.index eq 0 }">
					<td>${s.index+1 }</td>
					<td></td>
					<td>1</td>
					<td>个</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.jkzxXjSjRows }"></td>
			<td rowspan="${data.jkzxXjSjRows }"></td>
			<td rowspan="${data.jkzxXjSjRows }"></td>
			<td rowspan="${data.jkzxXjSjRows }"></td>
		</tr>
		<c:forEach items="${data.jkzxXjSj }" var="item" varStatus="s">
			<c:if test="${s.index gt 0 }">
				<tr>
					<td>${s.index+1 }</td>
					<td></td>
					<td>1</td>
					<td>个</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</tr>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.jkzRows }">监控站</td>
			<td rowspan="${data.jkzXjRows }">新建</td>
			<c:if test="${empty data.jkzXj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.jkzXj }" var="item" varStatus="s">
				<c:if test="${s.index eq 0 }">
					<td>${s.index+1 }</td>
					<td></td>
					<td>1</td>
					<td>套</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.jkzXjRows }"></td>
			<td rowspan="${data.jkzXjRows }"></td>
			<td rowspan="${data.jkzXjRows }"></td>
			<td rowspan="${data.jkzXjRows }"></td>
		</tr>
		<c:forEach items="${data.jkzXj }" var="item" varStatus="s">
			<c:if test="${s.index gt 0 }">
				<tr>
					<td>${s.index+1 }</td>
					<td></td>
					<td>1</td>
					<td>套</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</tr>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.jkzSjRows }">升级改造</td>
			<c:if test="${empty data.jkzSj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.jkzSj }" var="item" varStatus="s">
				<c:if test="${s.index eq 0 }">
					<td>${s.index+1 }</td>
					<td></td>
					<td>1</td>
					<td>套</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.jkzSjRows }"></td>
			<td rowspan="${data.jkzSjRows }"></td>
			<td rowspan="${data.jkzSjRows }"></td>
			<td rowspan="${data.jkzSjRows }"></td>
		</tr>
		<c:forEach items="${data.jkzSj }" var="item" varStatus="s">
			<c:if test="${s.index gt 0 }">
				<tr>
					<td>${s.index+1 }</td>
					<td></td>
					<td>1</td>
					<td>套</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</tr>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.csxlRows }">传输线路</td>
			<td rowspan="${data.csxlXjRows }">新建</td>
			<c:if test="${empty data.csxlXj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.csxlXj }" var="item" varStatus="s">
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
			<td rowspan="${data.csxlXjRows }"></td>
			<td rowspan="${data.csxlXjRows }"></td>
			<td rowspan="${data.csxlXjRows }"></td>
			<td rowspan="${data.csxlXjRows }"></td>
		</tr>
		<c:forEach items="${data.csxlXj }" var="item" varStatus="s">
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
	</tbody>
</table>
