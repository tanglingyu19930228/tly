
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>>
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
		<c:if test="${partTag=='1'}">
			<caption>辽宁边海防基础设施建设任务统筹规划</caption>
		</c:if>
		<c:if test="${partTag}">
			<caption>辽宁${year }年度边海防基础设施建设任务</caption>
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
				<td rowspan="${data.sjAll+3}">云南省</td>
				<td colspan="2">总投资</td>
				<td colspan="10">10000</td>
				<td>5000</td>
				<td>5000</td>
			</tr>

			<tr>
				<td rowspan="${data.jtbzssAll}">交通保障设施</td>
				<td rowspan="${data.zqdlRows==0 ? 1 :data.zqdlRows}">执勤道路</td>
				<td	rowspan="${data.xjzqdlRows==0 ? 1 :data.xjzqdlRows}">新建</td>
				<c:if test="${ empty data.xjzqdllist }">
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>公里</td>
					<td>7</td>
					<td>4</td>
					<td>3</td>
				</c:if>
				 <c:forEach items="${data.xjzqdllist}" var="item" varStatus="s">
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
				<td
					rowspan="${data.xjzqdlRows==0 ? 1 :data.xjzqdlRows}"></td>
				<td
					rowspan="${ data.xjzqdlRows == 0 ? 1 :data.xjzqdlRows}"></td>
				<td
					rowspan="${ data.xjzqdlRows == 0  ? 1 :data.xjzqdlRows}"></td>
				<td
					rowspan="${  data.xjzqdlRows == 0  ? 1 :data.xjzqdlRows}"></td>
			</tr>

			<c:forEach items="${data.xjzqdllist }" var="item" varStatus="s">
				<c:if test="${s.index gt 0 }">
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
			<!-- <tr>
				<td>3</td>
				<td>3</td>
				<td>3</td>
				<td>公里</td>
				<td>6</td>
				<td>3</td>
				<td>3</td>
			</tr> -->

			<tr>
				<td
					rowspan="${empty data.sjzqdListRows  ? 1 :data.sjzqdListRows}">升级改造</td>
				<c:if test="${ empty data.sjzqdListRows }">
					<td>3</td>
					<td>3</td>
					<td>3</td>
					<td>公里</td>
					<td>3</td>
					<td>3</td>
					<td>3</td>
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
				<td
					rowspan="${empty data.sjzqdListRows  ? 1 :data.sjzqdListRows}"></td>
				<td
					rowspan="${empty data.sjzqdListRows  ? 1 :data.sjzqdListRows}"></td>
				<td
					rowspan="${empty data.sjzqdListRows  ? 1 :data.sjzqdListRows}"></td>
				<td
					rowspan="${empty data.sjzqdListRows ? 1 :data.sjzqdListRows}"></td>
			</tr>

			<c:forEach items="${data.sjzqdList}" var="item" varStatus="s">
				<c:if test="${s.index gt 0}">
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


			<!-- <tr>
				<td>3</td>
				<td>3</td>
				<td>3</td>
				<td>3</td>
				<td>3</td>
				<td>3</td>
				<td>3</td>
			</tr> -->

			<tr>

				<td
					rowspan="${ empty data.xjqiaolianglistRows  ? 1 : data.xjqiaolianglistRows}">桥梁</td>
				<td
					rowspan="${empty data.xjqiaolianglistRows ? 1 : data.xjqiaolianglistRows}">新建</td>
				<c:if test="${ empty data.xjqiaolianglistRows}">
					<td>3</td>
					<td>3</td>
					<td>3</td>
					<td>3</td>
					<td>3</td>
					<td>3</td>
					<td>3</td>
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

				<td rowspan="${data.xjqiaolianglistRows}">3</td>
				<td rowspan="${data.xjqiaolianglistRows}">3</td>
				<td rowspan="${data.xjqiaolianglistRows}">3</td>
				<td rowspan="${data.xjqiaolianglistRows}">33</td>
			</tr>

			<c:forEach items="${data.xjqiaolianglist }" var="item" varStatus="s">
				<c:if test="${s.index gt 0}">
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

			<!-- <tr>

				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>


			</tr> -->

			<tr>

				<td
					rowspan="${ empty data.xjzsjtjplistRows ? 1 :data.xjzsjtjplistRows }">直升机停机坪</td>
				<td
					rowspan="${empty data.xjzsjtjplistRows ? 1 : data.xjzsjtjplistRows}">新建</td>
				<c:if test="${empty data.xjzsjtjplistRows}">
					<td>4</td>
					<td>3</td>
					<td>3</td>
					<td>座</td>
					<td>3</td>
					<td>2</td>
					<td>1</td>
				</c:if>

				<c:forEach items="${data.xjzsjtjplist}" var="item" varStatus="s">
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
				<td rowspan="${data.xjzsjtjplistRows}">1</td>
				<td rowspan="${data.xjzsjtjplistRows }">1</td>
				<td rowspan="${data.xjzsjtjplistRows}">1</td>
				<td rowspan="${data.xjzsjtjplistRows }">1</td>
			</tr>

			<c:forEach items="${data.xjzsjtjplist}" var="item" varStatus="s">
				<c:if test="${s.index gt 0}">
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

			<!-- 	<tr>


				<td>2</td>
				<td>2</td>
				<td>2</td>
				<td>座</td>
				<td>2</td>
				<td>2</td>
				<td>2</td>

			</tr> -->
			<!-- <tr>


				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>座</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>


			</tr> -->

			<tr>

				<td rowspan="${data.zhjkssAll+2}">指挥监控设施</td>
				<td
					rowspan="${  data.xjjjmlfptlistRows == 0  ? 1 :data.xjjjmlfptlistRows }">军警民联防平台</td>
				<td
					rowspan="${  data.xjjjmlfptlistRows == 0 ? 1 : data.xjjjmlfptlistRows}">新建</td>
				<c:if test="${empty data.xjjjmlfptlist }">
					<td>22</td>
					<td>22</td>
					<td>22</td>
					<td>套</td>
					<td>22</td>
					<td>22</td>
					<td>22</td>
				</c:if>

				<c:forEach items="${data.xjjjmlfptlist}" var="item" varStatus="s">
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
				<td rowspan="${data.xjjjmlfptlistRows}">22</td>
				<td rowspan="${data.xjjjmlfptlistRows}">22</td>
				<td rowspan="${data.xjjjmlfptlistRows}">22</td>
				<td rowspan="${data.xjjjmlfptlistRows}">22</td>

			</tr>

			<c:forEach items="${data.xjjjmlfptlist}" var="item" varStatus="s">
				<c:if test="${s.index gt 0}">
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

			<!-- <tr>

				<td>22</td>
				<td>22</td>
				<td>22</td>
				<td>套</td>
				<td>22</td>
				<td>22</td>
				<td>22</td>

			</tr> -->

			<tr>

				<td rowspan="2">监控示范段</td>
				<td rowspan="2">新建</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>公里</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td rowspan="2">1</td>
				<td rowspan="2">1</td>
				<td rowspan="2">1</td>
				<td rowspan="2">1</td>

			</tr>

			<tr>



				<td>1122</td>
				<td>1122</td>
				<td>1122</td>
				<td>公里</td>
				<td>122</td>
				<td>1122</td>
				<td>1122</td>

			</tr>

			<tr>


				<td rowspan="${ data.djjkzxlistRows == 0  ? 1: data.djjkzxlistRows}">地级监控中心</td>
				<td rowspan="${ data.djjkzxlistRows ==0 ? 1 : data.djjkzxlistRows}">新建</td>
				<c:if test="${empty data.djjkzxlist}">

					<td>22</td>
					<td>22</td>
					<td>22</td>
					<td>个</td>
					<td>22</td>
					<td>22</td>
					<td>22</td>
				</c:if>

				<c:forEach items="${data.djjkzxlist}" var="item" varStatus="s">
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
				<td rowspan="${data.djjkzxlistRows }">22</td>
				<td rowspan="${data.djjkzxlistRows }">22</td>
				<td rowspan="${data.djjkzxlistRows }">22</td>
				<td rowspan="${data.djjkzxlistRows }">22</td>
			</tr>

			<c:forEach items="${data.djjkzxlist}" var="item" varStatus="s">
				<tr>
					<c:if test="${s.index gt 0}">
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>个</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
					</c:if>
				</tr>
			</c:forEach>
			<!-- <tr>


				<td>2</td>
				<td>2</td>
				<td>2</td>
				<td>个</td>
				<td>2</td>
				<td>2</td>
				<td>2</td>

			</tr> -->

			<tr>

				<td
					rowspan="${data.xjijkxzlistRows  }">县级监控中心</td>
				<td
					rowspan="${ data.xjijkxzlistRows }">新建</td>
				<c:if test="${empty data.xjijkxzlist}">
					<td>221</td>
					<td>221</td>
					<td>221</td>
					<td>个</td>
					<td>221</td>
					<td>221</td>
					<td>221</td>
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
				<td rowspan="${data.xjijkxzlistRows}">221</td>
				<td rowspan="${data.xjijkxzlistRows}">221</td>
				<td rowspan="${data.xjijkxzlistRows}">221</td>
				<td rowspan="${data.xjijkxzlistRows}">221</td>

			</tr>

			<c:forEach items="${data.xjijkxzlist}" var="item" varStatus="s">
				<c:if test="${s.index gt 0}">
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


			<!-- <tr>


				<td>3</td>
				<td>3</td>
				<td>3</td>
				<td>个</td>
				<td>3</td>
				<td>3</td>
				<td>3</td>

			</tr> -->

			<tr>

				<td rowspan="${ data.jkzAll == 0  ? 1 :data.jkzAll}">监控站</td>
				<td rowspan="${ data.xjjkzlistRows ==0 ? 1 :data.xjjkzlistRows }">新建</td>
				<c:if test="${empty data.xjjkzlist}">
					<td>22</td>
					<td>22</td>
					<td>22</td>
					<td>个</td>
					<td>22</td>
					<td>22</td>
					<td>22</td>
				</c:if>
				<c:forEach items="${data.xjjkzlist}" var="item" varStatus="s">
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
				<td rowspan="${data.xjjkzlistRows}">22</td>
				<td rowspan="${data.xjjkzlistRows}">22</td>
				<td rowspan="${data.xjjkzlistRows}">22</td>
				<td rowspan="${data.xjjkzlistRows}">22</td>

			</tr>

			<c:forEach items="${data.xjjkzlist}" var="item" varStatus="s">
				<c:if test="${s.index gt 0}">
					<tr>
						<td>${s.index+1}</td>
						<td>${item.jsdd}</td>
						<td>${item.jsgm}</td>
						<td>个</td>
						<td>${item.zytz+item.dftz}</td>
						<td>${item.zytz}</td>
						<td>${item.dftz}</td>
					</tr>
					<tr></tr>
				</c:if>
			</c:forEach>

			<!-- <tr>



				<td>22</td>
				<td>22</td>
				<td>22</td>
				<td>个</td>
				<td>22</td>
				<td>22</td>
				<td>22</td>

			</tr> -->

			<tr>


				<td rowspan="${ data.sjjkzlistRows == 0  ? 1 : data.sjjkzlistRows}">升级改造</td>
				<c:if test="${empty data.sjjkzlist}">

					<td>22</td>
					<td>22</td>
					<td>22</td>
					<td>个</td>
					<td>22</td>
					<td>22</td>
					<td>22</td>
				</c:if>
				<c:forEach items="${data.sjjkzlist}" var="item" varStatus="s">
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
				<td rowspan="${data.sjjkzlistRows}">22</td>
				<td rowspan="${data.sjjkzlistRows}">22</td>
				<td rowspan="${data.sjjkzlistRows}">22</td>
				<td rowspan="${data.sjjkzlistRows}">22</td>
			</tr>

			<c:forEach items="${data.sjjkzlist}" var="item" varStatus="s">
				<c:if test="${s.index gt 0}">
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

			<!-- <tr>


				<td>22</td>
				<td>22</td>
				<td>22</td>
				<td>个</td>
				<td>22</td>
				<td>22</td>
				<td>22</td>

			</tr> -->

			<tr>

				<td rowspan="${ data.fzptssAll }">辅助配套设施</td>
				<td rowspan="${ data.xjzqflistRows == 0 ? 1 : data.xjzqflistRows}">执勤房</td>
				<td rowspan="${ data.xjzqflistRows == 0 ? 1 : data.xjzqflistRows}">新建</td>
				<c:if test="${empty data.xjzqflist}">
					<td>22</td>
					<td>22</td>
					<td>22</td>
					<td>座</td>
					<td>22</td>
					<td>22</td>
					<td>22</td>
				</c:if>

				<c:forEach items="${data.xjzqflist}" var="item" varStatus="s">
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
				<td rowspan="${data.xjzqflistRows }">22</td>
				<td rowspan="${data.xjzqflistRows }">22</td>
				<td rowspan="${data.xjzqflistRows }">22</td>
				<td rowspan="${data.xjzqflistRows }">22</td>

			</tr>
			<c:forEach items="${data.xjzqflist}" var="item" varStatus="s">
				<c:if test="${s.index gt 0}">
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

			<!-- <tr>
				<td>111</td>
				<td>111</td>
				<td>111</td>
				<td>个</td>
				<td>111</td>
				<td>111</td>
				<td>111</td>
			</tr> -->

			<tr>

				<td rowspan="${ data.xjlwtlistRows == 0 ? 1 :data.xjlwtlistRows }">瞭望塔</td>
				<td rowspan="${ data.xjlwtlistRows == 0 ? 1 :data.xjlwtlistRows}">新建</td>
				<c:if test="${empty data.xjlwtlist}">
					<td>11</td>
					<td>11</td>
					<td>11</td>
					<td>座</td>
					<td>11</td>
					<td>11</td>
					<td>11</td>
				</c:if>
				<c:forEach items="${data.xjlwtlist}" var="item" varStatus="s">
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
				<td rowspan="${data.xjlwtlistRows}">11</td>
				<td rowspan="${data.xjlwtlistRows}">11</td>
				<td rowspan="${data.xjlwtlistRows}">11</td>
				<td rowspan="${data.xjlwtlistRows}">11</td>
			</tr>

			<c:forEach items="${data.xjlwtlist }" var="item" varStatus="s">
				<c:if test="${s.index gt 0}">
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


			<!-- <tr>


				<td>221</td>
				<td>221</td>
				<td>221</td>
				<td>座</td>
				<td>221</td>
				<td>221</td>
				<td>221</td>

			</tr> -->

			<tr>

				<td rowspan="${data.xjbzpListRows== 0 ? 1 :data.xjbzpListRows }">标志牌</td>
				<td rowspan="${data.xjbzpListRows== 0 ? 1 :data.xjbzpListRows}">新建</td>
				<c:if test="${empty data.xjbzpList}">
					<td>122</td>
					<td>122</td>
					<td>122</td>
					<td>座</td>
					<td>122</td>
					<td>122</td>
					<td>122</td>
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
				<td rowspan="${data.xjbzpListRows}">122</td>
				<td rowspan="${data.xjbzpListRows}">122</td>
				<td rowspan="${data.xjbzpListRows}">122</td>
				<td rowspan="${data.xjbzpListRows}">122</td>
			</tr>

			<c:forEach items="${data.xjbzpList}" var="item" varStatus="s">
				<c:if test="${s.index gt 0}">
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

			<!-- <tr>


				<td>11</td>
				<td>11</td>
				<td>11</td>
				<td>座</td>
				<td>11</td>
				<td>11</td>
				<td>11</td>

			</tr> -->



		</tbody>

	</table>

