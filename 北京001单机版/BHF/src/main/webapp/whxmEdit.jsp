<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/whxm.css">
<form id="updateWhxmForm" class="updateWhxmForm">
	<input type="hidden" name="whxmid" value="${whxm[0].whxmid }"/>
	<table style="width:100%;">
		<tr>
			<td class="right" style="width:100px;">项目名称：</td>
			<td class="left" style="position: relative;">
				<input type="text" name="xmmc" value="${whxm[0].xmmc }" style="width:90%;height:30px;" class="lengthRequriedVa trim" /><span class="requiredSymb">*</span>
			</td>
			<td class="right" style="width:100px;">项目编号：</td>
			<td class="left" style="position: relative;">
				<input type="text" name="xmbh" value="${whxm[0].xmbh }" style="width:90%;height:30px;" class="lengthRequriedVa trim" /><span class="requiredSymb">*</span>
			</td>
		</tr>
		<tr>
			<td class="right">维修单位：</td>
			<td class="left" style="position: relative;">
				<input type="text" name="wxdw" value="${whxm[0].wxdw }" style="width:90%;height:30px;" class="lengthRequriedVa trim" /><span class="requiredSymb">*</span>
			</td>
			<td class="right">维修总费用：</td>
			<td class="left" style="position: relative;">
				<input type="text" name="wxzfy" value="${whxm[0].wxzfy }" style="width:auto;height:30px;" readonly="readonly"/> 万元<span class="requiredSymb">*</span>
			</td>
		</tr>
	</table>	
	<table style="width:100%;" class="updateList" id="whjlListUpdate">
		<thead>
			<tr>
				<th>维修项目名称</th>
				<th>维修项目编号</th>
				<th>维修规模</th>
				<th>维修费用(万元)</th>
				<th>维护状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${whxm }" var="whjl">
				<tr>
					<td>
						<input type="hidden" name="whjlid" value="${whjl.whjlid }"/>
						<input type="hidden" name="wxxmbh" value="${whjl.wxxmbh }"/>
						<input type="hidden" name="wxxmmc" value="${whjl.wxxmmc }"/>${whjl.wxxmmc }
					</td>
					<td>
						<input type="hidden" name="wxxmbh" value="${whjl.wxxmbh }"/>${whjl.wxxmbh }
					</td>
					<td>
						<input type="hidden" name="wxgm" value="${whjl.wxgm }"/>
						${whjl.wxgm }
					</td>
					<td>
						<input type="hidden" name="wxfy" value="${whjl.wxfy }"/>${whjl.wxfy }
					</td>
					<td>
						<c:if test="${whjl.whzt eq '维护完成' }">
							<input type="hidden" name="whzt" value="${whjl.whzt }"/>${whjl.whzt }
						</c:if>
						<c:if test="${whjl.whzt ne '维护完成' }">
							<select name="whzt" style="width:100%;height: 30px;">
								<c:forEach items="${whztList }" var="whzt">
									<option value="${whzt.codeName }" ${whzt.codeName eq whjl.whzt ? 'selected' : '' }>${whzt.codeName }</option>
								</c:forEach>
							</select>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form>
