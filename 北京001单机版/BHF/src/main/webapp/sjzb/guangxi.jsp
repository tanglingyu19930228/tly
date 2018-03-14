<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
				<td rowspan="${data.sjgx+1}">广西壮族自治区</td>
				<td colspan="2">总投资</td>
				<td colspan="10">10000</td>
				<td>5000</td>
				<td>5000</td>

			</tr>

			<tr>

				<td rowspan="${data.jtbzssRows}">交通保障设施</td>
				<td rowspan="${data.zqdlRows}">执勤道路</td>
				<td rowspan="${data.xjzqdlRows}">新建</td>
				<c:if test="${empty data.xjzqdllist}">
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>公里</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
				</c:if>
				<c:forEach items="${data.xjzqdllist}" var="item" varStatus="s">
				<c:if test="${s.index eq 0 }">
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>公里</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
				</c:if>
				</c:forEach>
				<td rowspan="${data.xjzqdlRows}">11</td>
				<td rowspan="${data.xjzqdlRows}">11</td>
				<td rowspan="${data.xjzqdlRows}">11</td>
				<td rowspan="${data.xjzqdlRows}">11</td>

			</tr>
			<c:forEach items="${data.xjzqdllist }" var="item" varStatus="s">
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

				<td rowspan="${data.sjzqdListRows}">升级改造</td>
					<c:if test="${empty data.sjzqdList}">
					
					<td>111</td>
					<td>111</td>
					<td>111</td>
					<td>公里</td>
					<td>111</td>
					<td>111</td>
					<td>111</td>
					</c:if>
					<c:forEach items="${data.sjzqdList}" var="item" varStatus="s">
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
				<td rowspan="${data.sjzqdListRows }">111</td>
				<td rowspan="${data.sjzqdListRows }">111</td>
				<td rowspan="${data.sjzqdListRows }">111</td>
				<td rowspan="${data.sjzqdListRows }">111</td>
			</tr>
				<c:forEach items="${ data.sjzqdList}" var="item" varStatus="s">
					<c:if test="${s.index ne 0 }">
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
				<c:if test="${empty data.xjqiaolianglist }">
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
							<td>公里</td>
							<td>${item.zytz+item.dftz}</td>
							<td>${item.zytz}</td>
							<td>${item.dftz}</td>									
					</c:if>
				</c:forEach>
				<td rowspan="${data.xjqiaolianglistRows }">11</td>
				<td rowspan="${data.xjqiaolianglistRows }">11</td>
				<td rowspan="${data.xjqiaolianglistRows }">11</td>
				<td rowspan="${data.xjqiaolianglistRows }">11</td>
			</tr>
		<c:forEach items="${data.xjqiaolianglist }" var="item" varStatus="s">
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
				<td rowspan="${data.lzbjss}">拦阻报警设施</td>
				<td rowspan="${data.tswRows}">铁丝网</td>
				<td rowspan="${data.tswRows }">新建</td>
				<c:if test="${empty data.list_tsw }">
				<td>111</td>
				<td>111</td>
				<td>111</td>
				<td>公里</td>
				<td>111</td>
				<td>111</td>
				<td>111</td>
				</c:if>
				<c:forEach items="${data.list_tsw}" var="item" varStatus="s">
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
				<td rowspan="${data.tswRows }">111</td>
				<td rowspan="${data.tswRows }">111</td>
				<td rowspan="${data.tswRows }">111</td>
				<td rowspan="${data.tswRows }">111</td>
			</tr>
			<c:forEach items="${data.list_tsw}" var="item" varStatus="s">
				<c:if test="${s.index ne 0 }">
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
				<td rowspan="${data.tzlRows}">铁栅栏</td>
				<td rowspan="${data.tzlRows}">新建</td>
				<c:if test="${empty data.list_tzl }">
				<td>111</td>
				<td>111</td>
				<td>111</td>
				<td>公里</td>
				<td>111</td>
				<td>111</td>
				<td>111</td>
				</c:if>
				<c:forEach items="${data.list_tzl}" var="item" varStatus="s">
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
				<td rowspan="${data.tzlRows}">111</td>
				<td rowspan="${data.tzlRows}">111</td>
				<td rowspan="${data.tzlRows}">111</td>
				<td rowspan="${data.tzlRows}">111</td>
			</tr>
			
			
		<c:forEach items="${data.list_tzl}" var="item" varStatus="s">
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
				<td rowspan="${data.zhjkssRows}">指挥监控设施</td>
				<td rowspan="${data.xjijkxzlistRows}">县级监控中心</td>
				<td rowspan="${data.xjijkxzlistRows}">新建</td>
				<c:if test="${empty data.xjijkxzlist}">
				<td>111</td>
				<td>111</td>
				<td>111</td>
				<td>个</td>
				<td>111</td>
				<td>111</td>
				<td>111</td>
				</c:if>
				
				<c:forEach items="${data.xjijkxzlist}" var="item" varStatus="s">
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
				<td rowspan="${data.xjijkxzlistRows}">111</td>
				<td rowspan="${data.xjijkxzlistRows}">111</td>
				<td rowspan="${data.xjijkxzlistRows}">111</td>
				<td rowspan="${data.xjijkxzlistRows}">111</td>
			</tr>
			<c:forEach items="${data.xjijkxzlist}" var="item" varStatus="s">
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
				<td rowspan="${data.jkzAll}">监控站</td>
				<td rowspan="${data.xjjkzlistRows }">新建</td>
				<c:if test="${empty data.xjjkzlist }">
				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>套</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>
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
				<td rowspan="${data.xjjkzlistRows}">11</td>
				<td rowspan="${data.xjjkzlistRows}">11</td>
				<td rowspan="${data.xjjkzlistRows}">11</td>
				<td rowspan="${data.xjjkzlistRows}">11</td>
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
				<td rowspan="${data.sjjkzlistRows}">升级改造</td>
				<c:if test="${empty data.sjjkzlist }">
					<td>11</td>
					<td>11</td>
					<td>11</td>
					<td>套</td>
					<td>11</td>
					<td>11</td>
					<td>11</td>
				</c:if>
				
				<c:forEach items="${data.sjjkzlist}" var="item" varStatus="s">
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
				<td rowspan="${data.sjjkzlistRows}">11</td>
				<td rowspan="${data.sjjkzlistRows}">11</td>
				<td rowspan="${data.sjjkzlistRows}">11</td>
				<td rowspan="${data.sjjkzlistRows}">11</td>
			</tr>
	
			<c:forEach items="${data.sjjkzlist}" var="item" varStatus="s" >
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
				<td rowspan="${data.fzptss}">辅助配套设施</td>
				<td rowspan="${data.xjbzpListRows }">标志牌</td>
				<td rowspan="${data.xjbzpListRows }">新建</td>
				<c:if test="${empty data.xjbzpList }">
				<td></td>
				<td></td>
				<td></td>
				<td>座</td>
				<td></td>
				<td></td>
				<td></td>
				</c:if>
				
				<c:forEach items="${data.xjbzpList}" var="item" varStatus="s">
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
				<td rowspan="${data.xjbzpListRows}"></td>
				<td rowspan="${data.xjbzpListRows}"></td>
				<td rowspan="${data.xjbzpListRows}"></td>
				<td rowspan="${data.xjbzpListRows}"></td>
			</tr>
	
			<c:forEach items="${data.xjbzpList}" var="item" varStatus="s">
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
		</tbody>
	</table>

