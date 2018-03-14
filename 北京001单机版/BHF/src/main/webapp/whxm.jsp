<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="css/whxm.css">
<script src="js/libs/jquery.easyui.min.js"></script>
<script src="js/whxmPage.js"></script>
<script src="js/whxm.js"></script>
	<div style="float: left;width: 96%;margin-left: 1.5%;">
		<div class="tab_title" style="margin-left: 0px;">
			<button type="button" id="whxmAddButton" onclick="openAddWhxm(this);" style="display:${loginUser.roleName eq '1' ? 'none' : '' };">添加项目</button>
			<button type="button" id="whxmUpdateButton" onclick="openUpdateWhxm(this);" style="display:${loginUser.roleName eq '1' ? 'none' : '' };">修改项目</button>
			<button type="button" onclick="importWhxmData(this);">导入项目</button>
			<button type="button" onclick="exportWhxmData(this);">导出项目</button>
		</div>
		<div style="overflow: auto;max-height:500px;">
			<input type="hidden" id="whxmOffiset" name="whxmOffiset"/>
			<input type="hidden" name="xmid" />
			<input type="hidden" name="xmmc" />
			<table class="whxmTabList comBorder" style="width:100%;">
				<thead>
					<tr>
						<th nowrap="nowrap">
							<div class="checkboxOne">
			                    <input type="checkbox" id="checkedAll" name="" class="checkAll"/>
			                    <label for="checkedAll">
			                    </label>
			                </div>
							<span class="spanAll">全选</span>
						</th>
						<th nowrap="nowrap">项目名称</th>
						<th nowrap="nowrap">项目编号</th>
						<th nowrap="nowrap">维修单位</th>
						<th nowrap="nowrap">维修总费用</th>
						<th nowrap="nowrap">维修项目名称</th>
						<th nowrap="nowrap">维修项目编号</th>
						<th nowrap="nowrap">维修规模</th>
					</tr>
				</thead>
				<tbody id="whxmTbody">
					<c:set var="checkboxIdStart" value="checkboxIdStart"></c:set>
					<c:forEach items="${pageBean.recordList }" var="item" varStatus="index">
						<tr>
							<td rowspan="${item.wxxmsize gt 0 ? item.wxxmsize : 1 }">
								<div class="checkboxOne" onclick="checkOneAll(this);">
				                    <input type="checkbox" id="${checkboxIdStart }${index.count }" name="" value="${fn:escapeXml(item.id) }" class="checkOne"/><!-- id -->
				                   	<label for="${checkboxIdStart }${index.count }">
				                    </label>
				                </div>
							</td>
							<%-- 项目名称--%>
							<td rowspan="${item.wxxmsize gt 0 ? item.wxxmsize : 1 }" title="${fn:escapeXml(item.xmmc) }" class="subShow" showMaxlength="20">${fn:escapeXml(item.xmmc) }</td>
							<%-- 项目编号--%>
							<td rowspan="${item.wxxmsize gt 0 ? item.wxxmsize : 1 }" title="${fn:escapeXml(item.xmbh) }" class="subShow" showMaxlength="20">${fn:escapeXml(item.xmbh) }</td>
							<%-- 维修单位--%>
							<td rowspan="${item.wxxmsize gt 0 ? item.wxxmsize : 1 }" title="${fn:escapeXml(item.wxdw) }" class="subShow" showMaxlength="20">${fn:escapeXml(item.wxdw) }</td>
							<%-- 维修总费用--%>
							<td rowspan="${item.wxxmsize gt 0 ? item.wxxmsize : 1 }">${fn:escapeXml(item.wxzfy) }</td>
							<td title="${fn:escapeXml(item.wxxmmcArr[0]) }" class="subShow" showMaxlength="20">${fn:escapeXml(item.wxxmmcArr[0]) }</td>
							<td title="${fn:escapeXml(item.wxxmbhArr[0]) }" class="subShow" showMaxlength="32">${fn:escapeXml(item.wxxmbhArr[0]) }</td>
							<td title="${fn:escapeXml(item.wxgmArr[0]) }" class="subShow" showMaxlength="30">${fn:escapeXml(item.wxgmArr[0]) }</td>
						</tr>
						<c:forEach items="${item.wxxmmcArr }" varStatus="s">
							<c:if test="${s.index != 0 }">
								<tr>
									<td title="${fn:escapeXml(item.wxxmmcArr[s.index]) }" class="subShow" showMaxlength="20">${fn:escapeXml(item.wxxmmcArr[s.index]) }</td>
									<td title="${fn:escapeXml(item.wxxmbhArr[s.index]) }" class="subShow" showMaxlength="32">${fn:escapeXml(item.wxxmbhArr[s.index]) }</td>
									<td title="${fn:escapeXml(item.wxgmArr[s.index]) }" class="subShow" showMaxlength="30">${fn:escapeXml(item.wxgmArr[s.index]) }</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="whxmPage">
			<input type="hidden" id="getWhxmCurrentPage" value="${pageBean.currentPage}"> <input type="hidden" id="getWhxmPageCount" value="${pageBean.pageCount}"> 
			<span class="whxmP_num">
				<span class="whxmPagePrev"><b class="arr"></b>上一页</span>
				<span class="whxmPageNext">下一页<b class="arr"></b></span>
			</span> 
			<span class="whxmP_skip">
				<em>共<b id="whxmPageCount">${pageBean.pageCount}</b>页，&nbsp;跳到</em> 
				<input type="text" id="whxmTurnPage" maxlength="6"><em>页&nbsp;</em>
				<a href="javascript:" id="whxmConfirm">确定</a> 
			</span>
		</div>
	</div>
	<div class="clear"></div>
	<div id="addWhxmDlg" class="easyui-dialog" data-options="modal:true,closed:true" title="新增维护项目" style="width:80%;display:none;">
		<div data-options="region:'center'" style="padding:25px 40px;" class="dialog_center">
			<div id="addWhxmInfoDiv">
				<form id="addWhxmForm" class="addWhxmForm">
					<table>
						<tr>
							<td class="right" style="width:100px;">项目名称：</td>
							<td class="left" style="position: relative;">
								<input type="text" name="xmmc" style="width:90%;height:30px;" class="lengthRequriedVa" /><span class="requiredSymb">*</span>
							</td>
							<td class="right" style="width:100px;">项目编号：</td>
							<td class="left" style="position: relative;">
								<input type="text" name="xmbh" style="width:90%;height:30px;" class="lengthRequriedVa" /><span class="requiredSymb">*</span>
							</td>
						</tr>
						<tr>
							<td class="right">维修单位：</td>
							<td class="left" style="position: relative;">
								<input type="text" name="wxdw" style="width:90%;height:30px;" class="lengthRequriedVa" /><span class="requiredSymb">*</span>
							</td>
							<td class="right">维修总费用：</td>
							<td class="left" style="position: relative;">
								<input type="text" name="wxzfy" style="width:auto;height:30px;" readonly="readonly"/> 万元<span class="requiredSymb">*</span>
							</td>
							
						</tr>
					</table>
					
					<table class="whxmAddDynamicTable" id="whxmAddDynamicTable">
						<c:if test="${param.xmzl ne '监控中心' and param.xmzl ne '监控站' and param.xmzl ne '军警民联防平台' and param.xmzl ne '视频前端' and param.xmzl ne '显控终端' }">
							<thead>
								<tr>
									<th style="width:90px;" rowspan="2"><a onclick="addTr();" style="cursor:pointer;">+添加</a></th>
									<th rowspan="2">维修项目名称</th>
									<th rowspan="2">维修项目编号</th>
									<th colspan="2">维修规模</th>
									<th style="width:200px;" rowspan="2">维修费用</th>
								</tr>
								<tr>
									<th>维护地点</th>
									<th style="width:210px;">规模</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="center"><a onclick="delTr(this);" style="cursor:pointer;">-删除</a></td>
									<td>
										<select name="wxxmmc" onchange="changeWxxmidAndWxxmbh(this);">
											<option></option>
										</select>
										<select name="wxxmid" style="display:none;">
											<option></option>
										</select>
										<select name="wxxmbhSelect" style="display:none;">
										</select>
										<select name="maxJsgmSelect" style="display:none;">
										</select>
									</td>
									<td class="left">
										<input type="text" name="wxxmbh" readonly="readonly" style="width:100%;"/>
									</td>
									<td class="left" style="position: relative;">
										<input type="text" name="wxdd" onblur="trimAllBlank(this);" style="width:100%;" title="格式:从xx到xx"/>
									</td>
									<td class="left" style="position: relative;">
										&nbsp;共&nbsp;<input type="text" name="wxgm" onblur="trimAllBlank(this);" style="width:140px;"/><span class="dw"></span><span class="requiredSymb">*</span>
									</td>
									<td class="left" style="position: relative;">
										<input type="text" name="wxfy" onblur="trimAllBlank(this);changeWxzfy(this);" style="width:140px;" maxlength="15"/> 万元<span class="requiredSymb">*</span>
									</td>
								</tr>
							</tbody>
						</c:if>
						<c:if test="${param.xmzl eq '监控中心' or param.xmzl eq '监控站' or param.xmzl eq '军警民联防平台' or param.xmzl eq '视频前端' or param.xmzl eq '显控终端' }">
							<thead>
								<tr>
									<th style="width:90px;" rowspan="2"><a onclick="addTr();" style="cursor:pointer;">+添加</a></th>
									<th>维修项目名称</th>
									<th>维修项目编号</th>
									<th style="width:210px;">维修规模</th>
									<th style="width:200px;">维修费用</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="center"><a onclick="delTr(this);" style="cursor:pointer;">-删除</a></td>
									<td>
										<select name="wxxmmc" onchange="changeWxxmidAndWxxmbh(this);">
											<option></option>
										</select>
										<select name="wxxmid" style="display:none;">
											<option></option>
										</select>
										<select name="wxxmbhSelect" style="display:none;">
										</select>
										<select name="maxJsgmSelect" style="display:none;">
										</select>
									</td>
									<td class="left">
										<input type="text" name="wxxmbh" readonly="readonly" style="width:100%;"/>
									</td>
									<td class="left" style="position: relative;">
										<input type="text" name="wxgm" onblur="trimAllBlank(this);" style="width:140px;"/><span class="dw"></span>
									</td>
									<td class="left" style="position: relative;">
										<input type="text" name="wxfy" onblur="trimAllBlank(this);changeWxzfy(this);" style="width:140px;" maxlength="15"/> 万元<span class="requiredSymb">*</span>
									</td>
								</tr>
							</tbody>
						</c:if>
					</table>
				</form>
			</div>
		</div>
		<div data-options="region:'center',border:false" class="dialog_button" style="text-align:center;padding: 0px 0px 20px 0px;">
			<button type="button" class="btn" onclick="submitAddWhxm();">保存</button>
			<button type="button" class="btn cancal_btn" onclick="colseAddWhxm();">取消</button>
		</div>
	</div>
	<div id="whxmWarning" class="easyui-dialog"
		data-options="modal:true,closed:true" title=" "
		style="width:500px;display:none;">
		<div data-options="region:'center'" style="padding:25px 40px;"
			class="dialog_center">
			<p class="editOne" style="text-align: center;font-size: 1rem;">？</p>
		</div>
	</div>
	
	
	
	<div id="updateWhxmDlg" class="easyui-dialog" data-options="modal:true,closed:true" title="编辑维护项目" style="width:80%;display:none;">
		<div data-options="region:'center'" style="padding:25px 40px;" class="dialog_center">
			<div id="updateWhxmInfoDiv"></div>
		</div>
		<div data-options="region:'center',border:false" class="dialog_button" style="text-align:center;padding: 0px 0px 20px 0px;">
			<button type="button" class="btn" onclick="submitUpdateWhxm();">保存</button>
			<button type="button" class="btn" onclick="colseWhxm();">取消</button>
		</div>
	</div>
</html>
