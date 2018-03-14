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
		<caption>西藏边海防基础设施建设任务统筹规划</caption>
	</c:if>
	<c:if test="${partTag==2 }">
		<caption>西藏${year }年度边海防基础设施建设任务</caption>
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
			<td rowspan="${data.rows + 1 }">西藏自治区</td>
			<td colspan="2">总投资</td>
			<td colspan="10"></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td rowspan="${data.jtbzssRows }">交通保障设施</td>
			<td rowspan="${data.zqdlRows }">执勤道路</td>
			<td rowspan="${data.zqdlXjRows }">新建</td>
			<c:if test="${empty data.zqdlXj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.zqdlXj }" var="item" varStatus="s">
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
		<c:forEach items="${data.zqdlXj }" var="item" varStatus="s">
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
			<td rowspan="${data.qiaoLiangRows }">桥梁</td>
			<td rowspan="${data.qiaoLiangXjRows }">新建</td>
			<c:if test="${empty data.qiaoLiangXj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.qiaoLiangXj }" var="item" varStatus="s">
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
			<td rowspan="${data.qiaoLiangXjRows }"></td>
			<td rowspan="${data.qiaoLiangXjRows }"></td>
			<td rowspan="${data.qiaoLiangXjRows }"></td>
			<td rowspan="${data.qiaoLiangXjRows }"></td>
		</tr>
		<c:forEach items="${data.qiaoLiangXj }" var="item" varStatus="s">
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
			<td rowspan="${data.zsjtjpRows }">直升机停机坪</td>
			<td rowspan="${data.zsjtjpXjRows }">新建</td>
			<c:if test="${empty data.zsjtjpXj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.zsjtjpXj }" var="item" varStatus="s">
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
			<td rowspan="${data.zsjtjpXjRows }"></td>
			<td rowspan="${data.zsjtjpXjRows }"></td>
			<td rowspan="${data.zsjtjpXjRows }"></td>
			<td rowspan="${data.zsjtjpXjRows }"></td>
		</tr>
		<c:forEach items="${data.zsjtjpXj }" var="item" varStatus="s">
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
			<td rowspan="${data.zsjtjpSjRows }">升级改造</td>
			<c:if test="${empty data.zsjtjpSj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.zsjtjpSj }" var="item" varStatus="s">
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
			<td rowspan="${data.zsjtjpSjRows }"></td>
			<td rowspan="${data.zsjtjpSjRows }"></td>
			<td rowspan="${data.zsjtjpSjRows }"></td>
			<td rowspan="${data.zsjtjpSjRows }"></td>
		</tr>
		<c:forEach items="${data.zsjtjpSj }" var="item" varStatus="s">
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
			<td rowspan="${data.lzbjssRows }">拦阻报警装置</td>
			<td rowspan="${data.tswRows }">铁丝网</td>
			<td rowspan="${data.tswXjRows }">新建</td>
			<c:if test="${empty data.tswXj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.tswXj }" var="item" varStatus="s">
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
			<td rowspan="${data.tswXjRows }"></td>
			<td rowspan="${data.tswXjRows }"></td>
			<td rowspan="${data.tswXjRows }"></td>
			<td rowspan="${data.tswXjRows }"></td>
		</tr>
		<c:forEach items="${data.tswXj }" var="item" varStatus="s">
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
					<td>个</td>
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
					<td>个</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</tr>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.fzptssRows }">配套设施</td>
			<td rowspan="${data.zqfRows }">执勤房</td>
			<td rowspan="${data.zqfXjRows }">新建</td>
			<c:if test="${empty data.zqfXj }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.zqfXj }" var="item" varStatus="s">
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
			<td rowspan="${data.zqfXjRows }"></td>
			<td rowspan="${data.zqfXjRows }"></td>
			<td rowspan="${data.zqfXjRows }"></td>
			<td rowspan="${data.zqfXjRows }"></td>
		</tr>
		<c:forEach items="${data.zqfXj }" var="item" varStatus="s">
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
			<td rowspan="${data.lwtXjRows }"></td>
			<td rowspan="${data.lwtXjRows }"></td>
			<td rowspan="${data.lwtXjRows }"></td>
			<td rowspan="${data.lwtXjRows }"></td>
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
	</tbody>
</table>
</body>
</html>
