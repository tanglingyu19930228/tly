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
		<caption>辽宁边海防基础设施建设任务统筹规划</caption>
	</c:if>
	<c:if test="${partTag==2 }">
		<caption>辽宁${year }年度边海防基础设施建设任务</caption>
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
			<td rowspan="${data.rows+1}">辽宁省</td>
			<td colspan="2">总投资</td>
			<td colspan="10"></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td rowspan="${data.jtss_rows }">交通保障设施</td>
			<td rowspan="${data.hf_zqdl ==null || fn:length(data.hf_zqdl)==0 ?1:fn:length(data.hf_zqdl)}">海防执勤道路</td>
			<td rowspan="${data.hf_zqdl ==null || fn:length(data.hf_zqdl)==0 ?1:fn:length(data.hf_zqdl)}">新建</td>
			<c:if test="${data.hf_zqdl ==null || fn:length(data.hf_zqdl)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.hf_zqdl }" var="item" varStatus="s">
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
			<td rowspan="${data.hf_zqdl ==null || fn:length(data.hf_zqdl)==0 ?1:fn:length(data.hf_zqdl)}"></td>
			<td rowspan="${data.hf_zqdl ==null || fn:length(data.hf_zqdl)==0 ?1:fn:length(data.hf_zqdl)}"></td>
			<td rowspan="${data.hf_zqdl ==null || fn:length(data.hf_zqdl)==0 ?1:fn:length(data.hf_zqdl)}"></td>
			<td rowspan="${data.hf_zqdl ==null || fn:length(data.hf_zqdl)==0 ?1:fn:length(data.hf_zqdl)}"></td>
		</tr>
		<c:forEach items="${data.hf_zqdl }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
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
			<td rowspan="${data.bf_zqdl_rows }">边防执勤道路</td>
			<td rowspan="${data.bf_zqdl_xj ==null || fn:length(data.bf_zqdl_xj)==0 ?1:fn:length(data.bf_zqdl_xj)}">新建</td>
			<c:if test="${data.bf_zqdl_xj ==null || fn:length(data.bf_zqdl_xj)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.bf_zqdl_xj }" var="item" varStatus="s">
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
			<td rowspan="${data.bf_zqdl_xj ==null || fn:length(data.bf_zqdl_xj)==0 ?1:fn:length(data.bf_zqdl_xj)}"></td>
			<td rowspan="${data.bf_zqdl_xj ==null || fn:length(data.bf_zqdl_xj)==0 ?1:fn:length(data.bf_zqdl_xj)}"></td>
			<td rowspan="${data.bf_zqdl_xj ==null || fn:length(data.bf_zqdl_xj)==0 ?1:fn:length(data.bf_zqdl_xj)}"></td>
			<td rowspan="${data.bf_zqdl_xj ==null || fn:length(data.bf_zqdl_xj)==0 ?1:fn:length(data.bf_zqdl_xj)}"></td>
		</tr>
		<c:forEach items="${data.bf_zqdl_xj }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
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
			<td rowspan="${data.bf_zqdl_sj ==null || fn:length(data.bf_zqdl_sj)==0 ?1:fn:length(data.bf_zqdl_sj)}">升级</td>
			<c:if test="${data.bf_zqdl_sj ==null || fn:length(data.bf_zqdl_sj)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.bf_zqdl_sj }" var="item" varStatus="s">
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
			<td rowspan="${data.bf_zqdl_sj ==null || fn:length(data.bf_zqdl_sj)==0 ?1:fn:length(data.bf_zqdl_sj)}"></td>
			<td rowspan="${data.bf_zqdl_sj ==null || fn:length(data.bf_zqdl_sj)==0 ?1:fn:length(data.bf_zqdl_sj)}"></td>
			<td rowspan="${data.bf_zqdl_sj ==null || fn:length(data.bf_zqdl_sj)==0 ?1:fn:length(data.bf_zqdl_sj)}"></td>
			<td rowspan="${data.bf_zqdl_sj ==null || fn:length(data.bf_zqdl_sj)==0 ?1:fn:length(data.bf_zqdl_sj)}"></td>
		</tr>
		<c:forEach items="${data.bf_zqdl_sj }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
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
			<td rowspan="${data.qiaoliang ==null || fn:length(data.qiaoliang)==0 ?1:fn:length(data.qiaoliang)}">桥梁</td>
			<td rowspan="${data.qiaoliang ==null || fn:length(data.qiaoliang)==0 ?1:fn:length(data.qiaoliang)}">新建</td>
			<c:if test="${data.qiaoliang ==null || fn:length(data.qiaoliang)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.qiaoliang }" var="item" varStatus="s">
				<c:if test="${s.index ==0 }">
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>座</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.qiaoliang ==null || fn:length(data.qiaoliang)==0 ?1:fn:length(data.qiaoliang)}"></td>
			<td rowspan="${data.qiaoliang ==null || fn:length(data.qiaoliang)==0 ?1:fn:length(data.qiaoliang)}"></td>
			<td rowspan="${data.qiaoliang ==null || fn:length(data.qiaoliang)==0 ?1:fn:length(data.qiaoliang)}"></td>
			<td rowspan="${data.qiaoliang ==null || fn:length(data.qiaoliang)==0 ?1:fn:length(data.qiaoliang)}"></td>
		</tr>
		<c:forEach items="${data.bf_zqdl_sj }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
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
			<td rowspan="${data.zqmt ==null || fn:length(data.zqmt)==0 ?1:fn:length(data.zqmt)}">码头</td>
			<td rowspan="${data.zqmt ==null || fn:length(data.zqmt)==0 ?1:fn:length(data.zqmt)}">新建</td>
			<c:if test="${data.zqmt ==null || fn:length(data.zqmt)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.zqmt }" var="item" varStatus="s">
				<c:if test="${s.index ==0 }">
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>座</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.zqmt ==null || fn:length(data.zqmt)==0 ?1:fn:length(data.zqmt)}"></td>
			<td rowspan="${data.zqmt ==null || fn:length(data.zqmt)==0 ?1:fn:length(data.zqmt)}"></td>
			<td rowspan="${data.zqmt ==null || fn:length(data.zqmt)==0 ?1:fn:length(data.zqmt)}"></td>
			<td rowspan="${data.zqmt ==null || fn:length(data.zqmt)==0 ?1:fn:length(data.zqmt)}"></td>
		</tr>
		<c:forEach items="${data.zqmt }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
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
			<td rowspan="${data.zsjtjp ==null || fn:length(data.zsjtjp)==0 ?1:fn:length(data.zsjtjp)}">直升机停机坪</td>
			<td rowspan="${data.zsjtjp ==null || fn:length(data.zsjtjp)==0 ?1:fn:length(data.zsjtjp)}">新建</td>
			<c:if test="${data.zsjtjp ==null || fn:length(data.zsjtjp)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.zsjtjp }" var="item" varStatus="s">
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
			<td rowspan="${data.zsjtjp ==null || fn:length(data.zsjtjp)==0 ?1:fn:length(data.zsjtjp)}"></td>
			<td rowspan="${data.zsjtjp ==null || fn:length(data.zsjtjp)==0 ?1:fn:length(data.zsjtjp)}"></td>
			<td rowspan="${data.zsjtjp ==null || fn:length(data.zsjtjp)==0 ?1:fn:length(data.zsjtjp)}"></td>
			<td rowspan="${data.zsjtjp ==null || fn:length(data.zsjtjp)==0 ?1:fn:length(data.zsjtjp)}"></td>
		</tr>
		<c:forEach items="${data.zsjtjp }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
				<tr>
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>个</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</tr>
			</c:if>
		</c:forEach>
		<tr>
			<td rowspan="${data.lzss_rows }">拦阻报警设施</td>
			<td rowspan="${data.tsw ==null || fn:length(data.tsw)==0 ?1:fn:length(data.tsw)}">铁丝网</td>
			<td rowspan="${data.tsw ==null || fn:length(data.tsw)==0 ?1:fn:length(data.tsw)}">新建</td>
			<c:if test="${data.tsw ==null || fn:length(data.tsw)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.tsw }" var="item" varStatus="s">
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
			<td rowspan="${data.tsw ==null || fn:length(data.tsw)==0 ?1:fn:length(data.tsw)}"></td>
			<td rowspan="${data.tsw ==null || fn:length(data.tsw)==0 ?1:fn:length(data.tsw)}"></td>
			<td rowspan="${data.tsw ==null || fn:length(data.tsw)==0 ?1:fn:length(data.tsw)}"></td>
			<td rowspan="${data.tsw ==null || fn:length(data.tsw)==0 ?1:fn:length(data.tsw)}"></td>
		</tr>
		<c:forEach items="${data.tsw }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
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
			<td rowspan="${data.tzl ==null || fn:length(data.tzl)==0 ?1:fn:length(data.tzl)}">铁栅栏</td>
			<td rowspan="${data.tzl ==null || fn:length(data.tzl)==0 ?1:fn:length(data.tzl)}">新建</td>
			<c:if test="${data.tzl ==null || fn:length(data.tzl)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.tzl }" var="item" varStatus="s">
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
			<td rowspan="${data.tzl ==null || fn:length(data.tzl)==0 ?1:fn:length(data.tzl)}"></td>
			<td rowspan="${data.tzl ==null || fn:length(data.tzl)==0 ?1:fn:length(data.tzl)}"></td>
			<td rowspan="${data.tzl ==null || fn:length(data.tzl)==0 ?1:fn:length(data.tzl)}"></td>
			<td rowspan="${data.tzl ==null || fn:length(data.tzl)==0 ?1:fn:length(data.tzl)}"></td>
		</tr>
		<c:forEach items="${data.tzl }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
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
			<td rowspan="${data.ptss_rows }">辅助配套设施</td>
			<td rowspan="${data.zqf ==null || fn:length(data.zqf)==0 ?1:fn:length(data.zqf)}">执勤房</td>
			<td rowspan="${data.zqf ==null || fn:length(data.zqf)==0 ?1:fn:length(data.zqf)}">新建</td>
			<c:if test="${data.zqf ==null || fn:length(data.zqf)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.zqf }" var="item" varStatus="s">
				<c:if test="${s.index ==0 }">
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>座</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.zqf ==null || fn:length(data.zqf)==0 ?1:fn:length(data.zqf)}"></td>
			<td rowspan="${data.zqf ==null || fn:length(data.zqf)==0 ?1:fn:length(data.zqf)}"></td>
			<td rowspan="${data.zqf ==null || fn:length(data.zqf)==0 ?1:fn:length(data.zqf)}"></td>
			<td rowspan="${data.zqf ==null || fn:length(data.zqf)==0 ?1:fn:length(data.zqf)}"></td>
		</tr>
		<c:forEach items="${data.zqf }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
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
			<td rowspan="${data.lwt ==null || fn:length(data.lwt)==0 ?1:fn:length(data.lwt)}">了望塔</td>
			<td rowspan="${data.lwt ==null || fn:length(data.lwt)==0 ?1:fn:length(data.lwt)}">新建</td>
			<c:if test="${data.lwt ==null || fn:length(data.lwt)==0 }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>
			<c:forEach items="${data.lwt }" var="item" varStatus="s">
				<c:if test="${s.index ==0 }">
					<td>${s.index+1 }</td>
					<td>${item.jsdd }</td>
					<td>${item.jsgm }</td>
					<td>座</td>
					<td>${item.zytz+item.dftz }</td>
					<td>${item.zytz }</td>
					<td>${item.dftz }</td>
				</c:if>
			</c:forEach>
			<td rowspan="${data.lwt ==null || fn:length(data.lwt)==0 ?1:fn:length(data.lwt)}"></td>
			<td rowspan="${data.lwt ==null || fn:length(data.lwt)==0 ?1:fn:length(data.lwt)}"></td>
			<td rowspan="${data.lwt ==null || fn:length(data.lwt)==0 ?1:fn:length(data.lwt)}"></td>
			<td rowspan="${data.lwt ==null || fn:length(data.lwt)==0 ?1:fn:length(data.lwt)}"></td>
		</tr>
		<c:forEach items="${data.lwt }" var="item" varStatus="s">
			<c:if test="${s.index !=0 }">
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
			<td rowspan="${data.bzp ==null || fn:length(data.bzp)==0 ?1:fn:length(data.bzp)}">标志牌</td>
			<td rowspan="${data.bzp ==null || fn:length(data.bzp)==0 ?1:fn:length(data.bzp)}">新建</td>
			<c:if test="${data.bzp ==null || fn:length(data.bzp)==0 }">
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
					<td>座</td>
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