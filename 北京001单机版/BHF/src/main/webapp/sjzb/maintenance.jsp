<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<style type="text/css">
.implementPlanT{border: 1px solid #e6e4e4;border-collapse: collapse;/* text-align: center; */width: 100%;color: #585858;height:770px;}
.implementPlanT td, .implementPlanT th {border: 1px solid #585858;}
.implementPlanT tbody .td{text-align:left;width: 32px;}
	</style>
<table class="implementPlanT">
	<thead>
		<tr>
			<th rowspan="2">建设区域</th>
			<th colspan="2" rowspan="2" style="width: 10%">项目名称</th>
			<th colspan="5">建设规模</th>
			<th rowspan="2">建设投资合计(万元)</th>
			<th rowspan="2">中央投资合计(万元)</th>
			<th rowspan="2">地方投资合计(万元)</th>
		</tr>
		<tr>
			<th style="width:12%;">良好</th>
			<th style="width:12%;">损坏</th>
			<th style="width:12%;">废弃</th>
			<th style="width:12%;">建设数量合计</th>
			<th style="width: 4%">单位</th>
		</tr>
		<tr>
			<td colspan="3" class="">总投资</td>
			<td colspan="5"></td>
			<td style="text-align: right">
				<c:if test="${ not empty data.map_tz.dxtz and data.map_tz.dxtz ne 0 }">
				<fmt:formatNumber type="number" value="${data.map_tz.dxtz}" maxFractionDigits="4"></fmt:formatNumber>
				</c:if>
			</td>
			<td style="text-align: right">
				<c:if test="${ not empty data.map_tz.zytz and data.map_tz.zytz ne 0 }">
				<fmt:formatNumber type="number" value="${data.map_tz.zytz}" maxFractionDigits="4"></fmt:formatNumber>
				</c:if>
			</td>
			<td style="text-align: right">
				<c:if test="${not empty data.map_tz.dftz and data.map_tz.dftz ne 0 }">
				<fmt:formatNumber type="number" value="${data.map_tz.dftz}" maxFractionDigits="4"></fmt:formatNumber>
				</c:if>
			</td>
		</tr>
	</thead>

	<tbody style="text-align:right">
		<tr>
			<td rowspan="4" class="td">${szsf}</td>
			<td rowspan="4" class="td" style="width: 2%">交通保障设施</td>
			<td class="td">执勤道路</td>
			<td>
				<c:forEach items="${data.zqdl_data}" var="item">
					<c:if test="${item.syzt eq '良好' }">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.zqdl_data}" var="item">
					<c:if test="${item.syzt eq '损坏' }">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.zqdl_data}" var="item">
					<c:if test="${item.syzt eq'废弃' }">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.zqdl_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>公里</td>
			<td>
				<c:if test="${not empty data.zqdl_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.zqdl_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.zqdl_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.zqdl_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.zqdl_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.zqdl_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>

		</tr>
		<tr>
			<td class="td">桥梁</td>
			<td>
				<c:forEach items="${data.qiaoliang_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>				
				</c:forEach>				
			</td>
			<td>
				<c:forEach items="${data.qiaoliang_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.qiaoliang_data}" var="item">
						<c:if test="${item.syzt == '废弃'}">
							<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
						</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.qiaoliang_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>座</td>
			<td>
				<c:if test="${not empty data.qiaoliang_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.qiaoliang_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.qiaoliang_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.qiaoliang_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.qiaoliang_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.qiaoliang_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			
			<td class="td">执勤码头</td>
			
			<td>
				<c:forEach items="${data.zqmt_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.zqmt_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.zqmt_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.zqmt_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>座</td>
			<td>
				<c:if test="${not empty data.zqmt_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.zqmt_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.zqmt_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.zqmt_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.zqmt_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.zqmt_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			
			<td class="td">直升机停机坪</td>
			
			<td>
				<c:forEach items="${data.zsjtjp_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.zsjtjp_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.zsjtjp_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.zsjtjp_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>个</td>
			<td>
				<c:if test="${not empty data.zsjtjp_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.zsjtjp_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.zsjtjp_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.zsjtjp_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.zsjtjp_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.zsjtjp_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td rowspan="6" class="td">${szsf}</td>
			<td rowspan="6" class="td">拦阻报警设施</td>
			<td class="td">铁丝网</td>
			
			<td>
				<c:forEach items="${data.tsw_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.tsw_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.tsw_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.tsw_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>公里</td>
			<td>
				<c:if test="${not empty data.tsw_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.tsw_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.tsw_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.tsw_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.tsw_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.tsw_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		
		<tr>
			
			<td class="td">铁栅栏</td>
			
			<td>
				<c:forEach items="${data.tzl_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.tzl_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.tzl_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.tzl_data}" var="item">
				<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>公里</td>
			<td>
				<c:if test="${not empty data.tzl_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.tzl_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.tzl_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.tzl_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.tzl_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.tzl_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		
		<tr>
			
			<td class="td">拒马</td>
			
			<td>
				<c:forEach items="${data.juma_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.juma_data}" var="item">
						<c:if test="${item.syzt == '损坏'}">
							<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
						</c:if>				
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.juma_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.juma_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>个</td>
			<td>
				<c:if test="${not empty data.juma_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.juma_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.juma_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.juma_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.juma_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.juma_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			
			<td class="td">拦阻桩</td>
			
			<td>
				<c:forEach items="${data.lzz_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.lzz_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.lzz_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.lzz_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>套</td>
			<td>
				<c:if test="${not empty data.lzz_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.lzz_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.lzz_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.lzz_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.lzz_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.lzz_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>

			<tr>
			
			<td class="td">隔离带</td>
			
			<td>
				<c:forEach items="${data.gld_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.gld_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.gld_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.gld_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>米</td>
			<td>
				<c:if test="${not empty data.gld_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.gld_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.gld_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.gld_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.gld_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.gld_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		
		<tr>
			
			<td class="td">报警线路</td>
			
			<td>
				<c:forEach items="${data.bjxl_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.bjxl_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.bjxl_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.bjxl_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>公里</td>
			<td>
				<c:if test="${not empty data.bjxl_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.bjxl_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.bjxl_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.bjxl_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.bjxl_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.bjxl_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td rowspan="12" class="td">${szsf}</td>
			<td rowspan="12" class="td">指挥监控设施</td>
			<td class="td">国家级监控中心</td>
			
			<td>
				<c:forEach items="${data.gjjkzx_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.gjjkzx_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.gjjkzx_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.gjjkzx_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>个</td>
			<td>
				<c:if test="${not empty data.gjjkzx_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.gjjkzx_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.gjjkzx_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.gjjkzx_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.gjjkzx_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.gjjkzx_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			
			<td class="td">省级监控中心</td>
			
			<td>
				<c:forEach items="${data.sjjkzx_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.sjjkzx_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.sjjkzx_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.sjjkzx_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>个</td>
			<td>
				<c:if test="${not empty data.sjjkzx_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.sjjkzx_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.sjjkzx_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.sjjkzx_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.sjjkzx_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.sjjkzx_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			
			<td class="td">地级监控中心</td>
			
			<td>
				<c:forEach items="${data.djjkzx_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.djjkzx_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.djjkzx_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.djjkzx_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>个</td>
			<td>
				<c:if test="${not empty data.djjkzx_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.djjkzx_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.djjkzx_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.djjkzx_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.djjkzx_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.djjkzx_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			
			<td class="td">县级监控中心</td>
			
			<td>
				<c:forEach items="${data.xjjkzx_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.xjjkzx_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.xjjkzx_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.xjjkzx_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>个</td>
			<td>
				<c:if test="${not empty data.xjjkzx_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.xjjkzx_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.xjjkzx_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.xjjkzx_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.xjjkzx_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.xjjkzx_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			
			<td class="td">监控站</td>
			
			<td>
				<c:forEach items="${data.jkz_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.jkz_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.jkz_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.jkz_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>个</td>
			<td>
				<c:if test="${not empty data.jkz_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.jkz_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.jkz_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.jkz_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.jkz_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.jkz_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			
			<td class="td">传输线路</td>
			
			<td>
				<c:forEach items="${data.csxl_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.csxl_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.csxl_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.csxl_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>公里</td>
			<td>
				<c:if test="${not empty data.csxl_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.csxl_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.csxl_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.csxl_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.csxl_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.csxl_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			
			<td class="td">供电系统</td>
			
			<td>
				<c:forEach items="${data.gdxt_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.gdxt_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.gdxt_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.gdxt_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>套</td>
			<td>
				<c:if test="${not empty data.gdxt_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.gdxt_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.gdxt_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.gdxt_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.gdxt_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.gdxt_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			
			<td class="td">军警民联防平台</td>
			
			<td>
				<c:forEach items="${data.jjmlfpt_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.jjmlfpt_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.jjmlfpt_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.jjmlfpt_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>套</td>
			<td>
				<c:if test="${not empty data.jjmlfpt_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.jjmlfpt_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.jjmlfpt_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.jjmlfpt_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.jjmlfpt_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.jjmlfpt_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="td">无人值守电子哨兵</td>
			<td>
				<c:forEach items="${data.wrzsdzsb_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.wrzsdzsb_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.wrzsdzsb_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.wrzsdzsb_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>套</td>
			<td>
				<c:if test="${not empty data.wrzsdzsb_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.wrzsdzsb_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.wrzsdzsb_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.wrzsdzsb_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.wrzsdzsb_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.wrzsdzsb_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="td">视频前端</td>
			<td>
				<c:forEach items="${data.spqd_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.spqd_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.spqd_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.spqd_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>个</td>
			<td>
				<c:if test="${not empty data.spqd_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.spqd_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.spqd_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.spqd_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.spqd_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.spqd_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="td">显控终端</td>
			<td>
				<c:forEach items="${data.xkzd_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.xkzd_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.xkzd_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.xkzd_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>个</td>
			<td>
				<c:if test="${not empty data.xkzd_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.xkzd_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.xkzd_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.xkzd_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.xkzd_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.xkzd_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="td">报警装置</td>
			<td>
				<c:forEach items="${data.bjzz_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.bjzz_data}" var="item">
						<c:if test="${item.syzt == '损坏'}">
							<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
						</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.bjzz_data}" var="item">
						<c:if test="${item.syzt == '废弃'}">
							<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
						</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.bjzz_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>套</td>
			<td>
				<c:if test="${not empty data.bjzz_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.bjzz_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.bjzz_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.bjzz_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.bjzz_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.bjzz_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td rowspan="3" class="td">${szsf}</td>
			<td rowspan="3" class="td">辅助配套设施</td>
			<td class="td">执勤房</td>
			
			<td>
				<c:forEach items="${data.zqf_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.zqf_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.zqf_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.zqf_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>座</td>
			<td>
				<c:if test="${not empty data.zqf_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.zqf_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.zqf_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.zqf_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.zqf_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.zqf_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			
			<td class="td">了望塔</td>
			
			<td>
				<c:forEach items="${data.lwt_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.lwt_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.lwt_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.lwt_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>座</td>
			<td>
				<c:if test="${not empty data.lwt_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.lwt_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.lwt_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.lwt_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.lwt_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.lwt_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		<tr>
			
			<td class="td">标志牌</td>
			
			<td>
				<c:forEach items="${data.bzp_data}" var="item">
					<c:if test="${item.syzt == '良好'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.bzp_data}" var="item">
					<c:if test="${item.syzt == '损坏'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${data.bzp_data}" var="item">
					<c:if test="${item.syzt == '废弃'}">
						<fmt:formatNumber type="number" value="${item.jsgm }" maxFractionDigits="4"/>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:set var="count" value="0"></c:set>
				<c:forEach items="${data.bzp_data}" var="item">
					<c:set var="count" value="${count+item.jsgm}"></c:set>
				</c:forEach>
				<c:if test="${count ne 0 }">
					<fmt:formatNumber type="number" value="${count }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>座</td>
			<td>
				<c:if test="${not empty data.bzp_tz[0].dxtz }">
					<fmt:formatNumber type="number" value="${data.bzp_tz[0].dxtz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.bzp_tz[0].zytz }">
					<fmt:formatNumber type="number" value="${data.bzp_tz[0].zytz }" maxFractionDigits="4"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty data.bzp_tz[0].dftz }">
					<fmt:formatNumber type="number" value="${data.bzp_tz[0].dftz }" maxFractionDigits="4"/>
				</c:if>
			</td>
		</tr>
		
	</tbody>

</table>