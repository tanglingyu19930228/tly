<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="css/whjlPage.css">
<link rel="stylesheet" href="css/whjlTable.css">
<script src="js/whjlPage.js"></script>
<script src="js/xmwhjl.js"></script>
<script type="text/javascript">
function initDate(){
	var buttons = $.extend([], $.fn.datebox.defaults.buttons);  
            buttons.splice(1, 0, {  
                text: '清除',  
                handler: function (target) {  
                    $(target).datebox("setValue","");  
                }  
            });  
            $('.easyui-datebox').datebox({  
                buttons: buttons  
            });  
}
</script>
<div id="whjlQueryDiv">
	<input type="hidden" id="whjlOffiset" name="whjlOffiset" />
	<c:if test="${not empty xmwhjl.recordList}">
		<input type="hidden" name="wxxmid" value="${xmwhjl.recordList[0].wxxmid }" />
	</c:if>
	<table class="xhjlTabList comBorder">
		<thead>
			<tr>
				<th nowrap="nowrap" style="width: 80px;display:${loginUser.roleName eq '2' ? '' : 'none'};">操作</th>
				<th nowrap="nowrap">项目名称</th>
				<th nowrap="nowrap">维修规模</th>
				<th nowrap="nowrap">维修费用(万元)</th>
				<th nowrap="nowrap">维修单位</th>
				<th nowrap="nowrap">故障设备</th>
				<th nowrap="nowrap">故障设备品牌</th>
				<th nowrap="nowrap">故障设备型号</th>
				<th nowrap="nowrap">故障部件</th>
				<th nowrap="nowrap">损坏原因</th>
				<th nowrap="nowrap">故障时间</th>
	            <th nowrap="nowrap">维修时间</th>
			</tr>
		</thead>
		<tbody id="whjlTbody">
			<c:forEach items="${xmwhjl.recordList }" var="item">
				<tr>
					<td style="display:${loginUser.roleName eq '2' ? '' : 'none'};">
						<span onclick="editWhjl(this);" style="cursor:pointer;color: #659dc1;" class="whjlEditSpan">编辑</span>
						<span style="display:none;cursor:pointer;color: #659dc1;" onclick="saveWhjl(this);"><label style="display:none;">${item.id }</label>保存</span>
						<span style="display:none;cursor:pointer;color: #659dc1;" onclick="cancelWhjl(this);">取消</span>
					</td>
					<td title="${fn:escapeXml(item.wxxmmc) }" class="subShow" showMaxlength="20">${fn:escapeXml(item.wxxmmc) }</td><%--项目名称--%>
					<td title="${fn:escapeXml(item.wxgm) }" class="subShow" showMaxlength="30">${fn:escapeXml(item.wxgm) }</td><%--维修规模--%> 
					<td>${fn:escapeXml(item.wxfy) }</td><%--维修费用--%> 
					<td title="${fn:escapeXml(item.wxdw) }" class="subShow" showMaxlength="20">${fn:escapeXml(item.wxdw) }</td><%--维修单位--%>
					<td style='position:relative;' class="yzx" title="${fn:escapeXml(item.gzsb) }"><span class="subShow" showMaxlength="20">${fn:escapeXml(item.gzsb) }</span></td><%--故障设备--%>
					<td style='position:relative;' class="yzx" title="${fn:escapeXml(item.gzsbpp) }"><span class="subShow" showMaxlength="20">${fn:escapeXml(item.gzsbpp) }</span></td><%--故障设备品牌--%>
					<td style='position:relative;' class="yzx" title="${fn:escapeXml(item.gzsbxh) }"><span class="subShow" showMaxlength="20">${fn:escapeXml(item.gzsbxh) }</span></td><%--故障设备型号--%>
					<td style='position:relative;' class="yzx" title="${fn:escapeXml(item.gzbj) }"><span class="subShow" showMaxlength="20">${fn:escapeXml(item.gzbj) }</span></td><%--故障部件--%>
					<td style='position:relative;' class="yzx" title="${fn:escapeXml(item.shyy) }"><span class="subShow" showMaxlength="20">${fn:escapeXml(item.shyy) }</span></td><%--损坏原因--%>
					<td style='position:relative;width:100px;' class="yzx">
						<input type="text" value="<fmt:formatDate value='${item.gzsj }' pattern='yyyy-MM-dd'/>" disabled="disabled" class="showItem"/>
					</td><%--故障时间--%>
					<td style='position:relative;width:100px;' class="yzx">
						<input type="text" value="<fmt:formatDate value='${item.wxsj }' pattern='yyyy-MM-dd'/>" disabled="disabled" class="showItem"/>
					</td><%--维修时间--%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="whjlPage">
	<input type="hidden" id="getWhjlPageCount" value="${xmwhjl.pageCount }"/>
	<input type="hidden" id="getWhjlCurrentPage" value="${xmwhjl.currentPage }"/>
	<span class="whjlP_num">
		<span class="whjlPagePrev"><b class="arr"></b>上一页</span>
		<span class="whjlPageNext">下一页<b class="arr"></b></span>
	</span> 
	<span class="whjlP_skip">
		<em>共<b id="whjlPageCount">${xmwhjl.pageCount}</b>页，&nbsp;跳到 </em>
		<input type="text" id="whjlTurnPage" maxlength="6"/>
		<em>页&nbsp;</em>
		<a href="javascript:" id="whjlConfirm">确定</a>
	</span>
</div>
<div class="clear"></div>





