$(function (){
	$("#addWhxmDlg").prev().find("a.panel-tool-close").click(function(){
		colseAddWhxm();
	});
	$("#whxmWarning").prev().find("a.panel-tool-close").click(function(){
		colseAddWhxm();
	});
	
	$(".trim").on("blur", function(){
		var thisVal = $.trim($(this).val());
		$(this).val(thisVal);
	});
	
	//“全选”按钮事件
	$(".whxmTabList thead .checkboxOne").click(function() {
		if ($(this).find("input").prop("checked")) {
			$(this).find("input").prop("checked", false);
			$(this).parents(".whxmTabList").find("tbody input").prop("checked", false);
		}else {
			$(this).find("input").prop("checked", true);
			$(this).parents(".whxmTabList").find("tbody input").prop("checked", true);
		}
	});
	whxmSubShow();
});
function whxmAjaxData(){
	var offiset = $("#whxmOffiset").val();
	$.ajax({
		url:"xmwhjl/queryWhxm",
		data:{offiset:offiset, szsf:sywh_szsf },
		type:"post",
		success:function(data){
			$("#whxmTbody").empty();
			if(data!=null){
				for(var i=0;i<data.recordList.length;i++){
					var wxxmsize = data.recordList[i].wxxmsize;
					if(wxxmsize <= 0){
						wxxmsize = 1;
					}
					var trStr = "";
					var checkOneId = "checkboxIdStart" + i;
					for(var j = 1; j < data.recordList[i].wxxmsize; j++){
						trStr = trStr + "<tr>"+
								"<td title='"+data.recordList[i].wxxmmcArr[j]+"' class='subShow' showMaxlength='20'>"+data.recordList[i].wxxmmcArr[j]+"</td>"+
								"<td title='"+data.recordList[i].wxxmbhArr[j]+"' class='subShow' showMaxlength='32'>"+data.recordList[i].wxxmbhArr[j]+"</td>"+
								"<td title='"+data.recordList[i].wxgmArr[j]+"' class='subShow' showMaxlength='30'>"+data.recordList[i].wxgmArr[j]+"</td>"
							+"</tr>";
					}
					$("#whxmTbody").append(
						"<tr>"+
						"<td rowspan='"+wxxmsize+"'>" +
							"<div class='checkboxOne' onclick='checkOneAll(this);'>"+
		                    	"<input type='checkbox' id='"+checkOneId+"' name='' value="+data.recordList[i].id+">"+
		                    	"<label for='"+checkOneId+"'></label>" +
		                    "</div>" +
		                "</td>"+
			       		"<td rowspan='"+wxxmsize+"' title='"+data.recordList[i].xmmc+"' class='subShow' showMaxlength='20'>"+data.recordList[i].xmmc+"</td>"+
			       		"<td rowspan='"+wxxmsize+"' title='"+data.recordList[i].xmbh+"' class='subShow' showMaxlength='20'>"+data.recordList[i].xmbh+"</td>"+
			            "<td rowspan='"+wxxmsize+"' title='"+data.recordList[i].wxdw+"' class='subShow' showMaxlength='20'>"+data.recordList[i].wxdw+"</td>"+
			            "<td rowspan='"+wxxmsize+"'>"+data.recordList[i].wxzfy+"</td>"+
			            "<td title='"+data.recordList[i].wxxmmcArr[0]+"' class='subShow' showMaxlength='20'>"+data.recordList[i].wxxmmcArr[0]+"</td>"+
			            "<td title='"+data.recordList[i].wxxmbhArr[0]+"' class='subShow' showMaxlength='32'>"+data.recordList[i].wxxmbhArr[0]+"</td>"+
			            "<td title='"+data.recordList[i].wxgmArr[0]+"' class='subShow' showMaxlength='30'>"+data.recordList[i].wxgmArr[0]+"</td>"+
			            "</tr>"+trStr
					);
				}
				$("#getWhxmPageCount").val(data.pageCount);//总页数
				$("#getWhxmCurrentPage").val(data.currentPage);//当前页
				$("#WhxmPageCount").text(data.pageCount);
				$(".whxmP_num .dot").remove();
				$(".whxmP_num a").remove();
				$(".whxmPagePrev").removeClass("disabled");
				$(".whxmPageNext").removeClass("disabled");
				loadWhxmPage();
				$("#whxmTbody tr").find("td").each(function(){
					if($(this).text()=="null"){
						$(this).text("");
					}
				});
			}
			whxmSubShow();
			$(".whxmTabList thead input").prop("checked", false);
			$("#whxmAddButton").removeClass("current");
		}
	});
}

//打开新增页面
function openAddWhxm(obj){
	$(obj).parent().find("button").removeClass("current");
	$(obj).addClass("current");
	$.ajax({
		url:"xmwhjl/getDestoryData",
		type:"post",
		data:{szsf:sywh_szsf, xmzl:sywh_xmzl},
		dataType:"json",
		success:function(data){
			if(data.destoryDataList == null || data.destoryDataList.length == 0){
				$("#whxmWarning").find(".editOne").text("没有损坏的项目，无法添加维护项目！");
        		$("#whxmWarning").show().dialog("open");
			}else{
				$("#whxmAddDynamicTable .dw").text(data.dw);
				//监控中心、监控站 、军警民联防平台 、 视频前端  、显控终端，此五类没有jsgm字段，赋值“1”
				if(xmzl == '监控中心' || xmzl == '监控站' || xmzl == '军警民联防平台' || xmzl == '视频前端' || xmzl == '显控终端'){
					$("#whxmAddDynamicTable input[name='wxgm']").val(1);
					$("#whxmAddDynamicTable input[name='wxgm']").prop("readonly","readonly");
				}else{
					$("#whxmAddDynamicTable [name='wxgm']").attr("title","最大值"+data.destoryDataList[0].jsgm);
				}
				var $wxxmmcObj = $("#whxmAddDynamicTable tbody tr:eq(0) select[name='wxxmmc']:eq(0)");
				var $wxxmidObj = $("#whxmAddDynamicTable tbody tr:eq(0) select[name='wxxmid']:eq(0)");
				var $wxxmbhObj = $("#whxmAddDynamicTable tbody tr:eq(0) select[name='wxxmbhSelect']:eq(0)");
				var $maxJsgmObj = $("#whxmAddDynamicTable tbody tr:eq(0) select[name='maxJsgmSelect']:eq(0)");
				$wxxmmcObj.empty();
				$wxxmidObj.empty();
				$wxxmbhObj.empty();
				$maxJsgmObj.empty();
				for(var i = 0; i < data.destoryDataList.length; i++){
					$wxxmmcObj.append("<option value='"+data.destoryDataList[i].xmmc+"'>"+data.destoryDataList[i].xmmc+"</option>");
					$wxxmidObj.append("<option value='"+data.destoryDataList[i].id+"'>"+data.destoryDataList[i].id+"</option>");
					$wxxmbhObj.append("<option value='"+data.destoryDataList[i].xmbh+"'>"+data.destoryDataList[i].xmbh+"</option>");
					if(xmzl == '监控中心' || xmzl == '监控站' || xmzl == '军警民联防平台' || xmzl == '视频前端' || xmzl == '显控终端'){
						$maxJsgmObj.append("<option value='"+1+"'>"+1+"</option>");
					}else{
						$maxJsgmObj.append("<option value='"+data.destoryDataList[i].jsgm+"'>"+data.destoryDataList[i].jsgm+"</option>");
					}
				}
				$("#whxmAddDynamicTable tbody tr:eq(0) input[name='wxxmbh']:eq(0)").val(data.destoryDataList[0].xmbh);
				$("#addWhxmDlg").show().dialog("open");
			}
		}
	});
}
//添加行
function addTr(){
	if($("#whxmAddDynamicTable tbody").find("tr").length >= 10){//动态添加行最多10行
		return false;
	}
	var countTr = $("#whxmAddDynamicTable tbody").find("tr").length;
	var trObj = $("#whxmAddDynamicTable tbody").find("tr:eq(0)");
	$("#whxmAddDynamicTable tbody").append(trObj[0].outerHTML);
	var xmzl = sywh_xmzl;
	if(xmzl == '监控中心' || xmzl == '监控站' || xmzl == '军警民联防平台' || xmzl == '视频前端' || xmzl == '显控终端'){
		$("#whxmAddDynamicTable input[name='wxgm']").val(1);
	}else{
		$("#whxmAddDynamicTable tbody tr:eq("+countTr+")").find("input[name='wxgm']").val("");
	}
	var lastWxxmbhVal = $("#whxmAddDynamicTable tbody tr:eq("+countTr+")").find("select[name='wxxmbhSelect'] option:selected").val();
	$("#whxmAddDynamicTable tbody tr:eq("+countTr+")").find("input[name='wxxmbh']").val(lastWxxmbhVal);
	$(".whxmTips").remove();//移除所有的提示
}
//删除行
function delTr(obj){
	var countTr = $("#whxmAddDynamicTable tbody").find("tr").length;
	if(countTr <= 1){
		return false;
	}
	$(obj).parent("td").parent("tr").remove();
}

//提交表单
function submitAddWhxm(){
	$(".whxmTips").remove();//移除所有的提示
	var success = whxmFormValidate("#addWhxmForm").form();
	if(!success){
		return false;
	}
	if(sywh_xmzl != '监控中心' && sywh_xmzl != '监控站' && sywh_xmzl != '军警民联防平台' && sywh_xmzl != '视频前端' && sywh_xmzl != '显控终端'){
		var wxddUnPassFirstIndex = -1;
		var wxddYzx = "";
		$("#whxmAddDynamicTable tbody input[name='wxdd']").each(function(index,item){
			var thisVal = $.trim($(item).val());
			if(thisVal == ""){//非空
				wxddUnPassFirstIndex = index;
				wxddYzx = "非空";
				return false;
			}
			if(thisVal.length > 80){
				wxddUnPassFirstIndex = index;
				wxddYzx = "长度过大";
				return false;
			}
		});
		if(wxddUnPassFirstIndex > -1){
			var errorSpan = "";
			if(wxddYzx == '非空'){
				errorSpan = "<span class='whxmTips'>这是必填字段</span>";
			}else if(wxddYzx == '长度过大'){
				errorSpan = "<span class='whxmTips'>最多输入80字符</span>";
			}
			var objOne = $("#whxmAddDynamicTable tbody [name='wxdd']:eq("+wxddUnPassFirstIndex+")");
			objOne.parent().append(errorSpan);
			objFocus(objOne);
			return false;
		}
		var wxgmUnPassFirstIndex = -1;//wxgm验证不通过的第一个下标
		var yzx = "";//验证项
		//验证非空、数值长度、是否为数字
		$("#whxmAddDynamicTable tbody input[name='wxgm']").each(function(index,item){
			var thisVal = $.trim($(item).val());
			if(thisVal == ""){//非空
				wxgmUnPassFirstIndex = index;
				yzx = "非空";
				return false;
			}
			if(thisVal.length > 80){
				wxgmUnPassFirstIndex = index;
				yzx = "长度过大";
				return false;
			}
			if(isNaN(thisVal)){
				wxgmUnPassFirstIndex = index;
				yzx = "不是数字";
				return false;
			}
			var maxJsgmVal = $("#whxmAddDynamicTable tbody tr:eq("+index+")").find("select[name='maxJsgmSelect']").val();
			if(parseFloat(thisVal) <= 0 || parseFloat(thisVal) > parseFloat(maxJsgmVal)){
				wxgmUnPassFirstIndex = index;
				yzx = "数值范围不对,"+ maxJsgmVal;
				return false;
			}
		});
		if(wxgmUnPassFirstIndex>-1){
			var errorSpan = "";
			if(yzx == '非空'){
				errorSpan = "<span class='whxmTips'>这是必填字段</span>";
			}else if(yzx == '长度过大'){
				errorSpan = "<span class='whxmTips'>最多80个字符</span>";
			}else if(yzx == '不是数字'){
				errorSpan = "<span class='whxmTips'>请输入数字</span>";
			}else if(yzx.indexOf("数值范围不对") >= 0){
				var currentMaxVal =  yzx.split(",")[1];
				errorSpan = "<span class='whxmTips'>请输入大于0，不大于设施规模"+currentMaxVal+"的数字</span>";
			}
			var objOne = $("#whxmAddDynamicTable tbody [name='wxgm']:eq("+wxgmUnPassFirstIndex+")");
			objOne.parent().append(errorSpan);
			objFocus(objOne);
			return false;
		}
	}
	
	var wxfyUnPassFirstIndex = -1;
	var wxfyYzx = "";
	$("#whxmAddDynamicTable tbody input[name='wxfy']").each(function(index,item){
		var thisVal = $.trim($(item).val());
		if(thisVal == ""){
			wxfyUnPassFirstIndex = index;
			wxfyYzx = "这是必填字段";
			return false;
		}
		var exp = new RegExp(/^([1-9][0-9]{0,8}||[0]||[1-9][0-9]{0,8}\.[0-9]{1,4}||[0]\.[0-9]{1,4})$/);
		if(!exp.test(thisVal)){
			wxfyUnPassFirstIndex = index;
			wxfyYzx = "应输入数字，且整数位最多9位，小数位最多4位";
			return false;
		}
		
	});
	if(wxfyUnPassFirstIndex > -1){
		var errorSpan = "<span class='whxmTips'>"+wxfyYzx+"</span>";
		var objOne = $("#whxmAddDynamicTable tbody [name='wxfy']:eq("+wxfyUnPassFirstIndex+")");
		objOne.parent().append(errorSpan);
		objFocus(objOne);
		return false;
	}
	if(success){
		
		var wxxmidArr = new Array();
		$("#addWhxmForm select[name='wxxmid'] option:selected").each(function(index,item){
			var valStr = $(item).val();
			valStr = valStr.replace(/\s|\xA0/g, "");
			wxxmidArr.push(valStr);
		});
		var wxxmidStr = wxxmidArr.join(", ");
		var wxxmbhArr = new Array();
		$("#addWhxmForm input[name='wxxmbh']").each(function(index,item){
			wxxmbhArr.push($(item).val());
		});
		var wxxmmcArr = new Array();
		$("#addWhxmForm select[name='wxxmmc'] option:selected").each(function(index,item){
			var valStr = $(item).val();
			valStr = valStr.replace(/\s|\xA0/g, "");
			wxxmmcArr.push(valStr);
		});
		var wxxmmcStr = wxxmmcArr.join(", ");
		var wxxmbhArr = new Array();
		$("#addWhxmForm input[name='wxxmbh']").each(function(index,item){
			wxxmbhArr.push($(item).val());
		});
		var wxxmbhStr = wxxmbhArr.join(", ");
		var wxgmArr = new Array();
		//拼接“维修规模”
		$("#addWhxmForm input[name='wxgm']").each(function(index,item){
			var currentWxddObj = $("#addWhxmForm input[name='wxdd']:eq("+index+")");
			var currentWxddVal = $.trim(currentWxddObj.val());
			var currentWxgmVal = $.trim($(item).val());
			var dw = $("#addWhxmForm .dw:eq(0)").text();
			if(sywh_xmzl != '监控中心' && sywh_xmzl != '监控站' && sywh_xmzl != '军警民联防平台' && sywh_xmzl != '视频前端' && sywh_xmzl != '显控终端'){
				wxgmArr.push(currentWxddVal + ",共" + currentWxgmVal + dw);
			}else{
				wxgmArr.push(currentWxgmVal + dw);
			}
		});
		var wxgmStr = wxgmArr.join("; ");
		
		var wxfyArr = new Array();
		$("#addWhxmForm input[name='wxfy']").each(function(index,item){
			var valStr = $(item).val();
			valStr = valStr.replace(/\s|\xA0/g, "");
			wxfyArr.push(valStr);
		});
		var wxfyStr = wxfyArr.join(", ");
		
		var xmmc = $("#addWhxmForm input[name='xmmc']").val();
		var xmbh = $("#addWhxmForm input[name='xmbh']").val();
		var wxdw = $("#addWhxmForm input[name='wxdw']").val();
		var wxzfy = $("#addWhxmForm input[name='wxzfy']").val();
		//关闭维护项目添加页面，防止多次提交
		$("#addWhxmDlg").dialog("close");
		colseWhxm();
		$.ajax({
			url:'xmwhjl/addWhxm',
			type:'post',
			data:{wxxmidArr:wxxmidStr, wxxmmcStr:wxxmmcStr, wxxmbhStr:wxxmbhStr, wxgmStr:wxgmStr, wxfyStr:wxfyStr,
				xmmc:xmmc, xmbh:xmbh, wxdw:wxdw, szsf:sywh_szsf, xmzl:sywh_xmzl, wxzfy:wxzfy},
			dataType:"json",
			success:function(data){
				$("#whxmAddDynamicTable tbody tr:gt(0)").remove();
				$("#whxmAddDynamicTable tbody tr:eq(0)").find("input").val("");
				$("#addWhxmForm tr input").val("");
				whxmAjaxData();
			}
		});
	}
}

//改变维修项目id的值
function changeWxxmidAndWxxmbh(obj){
	var index = obj.selectedIndex;
	var whxmidObj = $(obj).parent().find("select[name='wxxmid']");
	var whxmbhObj = $(obj).parent().find("select[name='wxxmbhSelect']");
	var maxJsgmObj = $(obj).parent().find("select[name='maxJsgmSelect']");
	whxmidObj.find("option:eq("+index+")")[0].selected=true;
	whxmbhObj.find("option:eq("+index+")")[0].selected=true;
	maxJsgmObj.find("option:eq("+index+")")[0].selected=true;
	var whxmbhSelectVal = $(whxmbhObj).find("option:selected").val();
	$(obj).parent("td").next().find("input[name='wxxmbh']").val(whxmbhSelectVal);
	$(obj).parents("tr").find("input[name='wxgm']").attr("title","最大值"+maxJsgmObj.val());
}

//清空所有空格
function trimAllBlank(obj){
	var valStr = $(obj).val();
	valStr = valStr.replace(/\s|\xA0/g, "");
	$(obj).val(valStr);
}
//关闭维修项目添加页面
function colseAddWhxm(){
	$(".whxmTips").remove();//移除所有的提示
	$("#whxmAddButton").removeClass("current");
	$("#addWhxmDlg").dialog("close");
	$("#whxmAddDynamicTable tbody tr:gt(0)").remove();
	$("#addWhxmForm tr input").val("");
}

//维护项目部分验证
function whxmFormValidate(objId){
	return $(objId).validate({
		onkeyup:false,//在 keyup 时验证false
		onfocusout:false,//失去焦点时验证（不包括复选框/单选按钮）false
		onfocusin:false,//获得焦点的事件
		rules: {
			lengthRequriedVa:{required:true,maxlength: 200},
			numberRequiredVa:{required:true,numberV:true}
		},
		showErrors : function(errorMap, errorList) {
			$.each(errorList, function(i, v) {
				whxmErrorLoc($(v.element),v.message);
				objFocus($(v.element));
				return false;
			});
		}
	});
}


//错误提示显示的方法，obj为错误对象，text为提示语
function whxmErrorLoc(obj,text){
    var errorSpan = "<span class='whxmTips'></span>";
    obj.parent().append(errorSpan);
    obj.parent().find(".whxmTips").text(text);
    
};


//错误对象高亮
function objFocus(obj){
    obj.addClass("focused");
    setTimeout(function(){
    	obj.removeClass("focused");
    },2000);

}

$(document).on("click","input",function(e) {
    var target = $(e.target);
    if (target.closest(".whxmTips").length == 0) {
        $(".whxmTips").remove();
    }
});
//验证维修规模
function wxgmUnPassFirstIndex(){
	$("#whxmAddDynamicTable tbody input[name='wxgm']").each(function(index,item){
		if($(this).val() == ""){
			return index;
		}
	});
}
//导出维护项目
function exportWhxmData(){
	$("#postForm").empty();
	var f=$("#postForm")[0];
    f.action = 'xmwhjl/exportWhxmData';
    f.submit();
}

//导入维护项目
function importWhxmData(obj){
	$(obj).parent().find("button").removeClass("current");
	$(obj).addClass("current");
	if(uploader3 != undefined){
        uploader3.destroy();
    }
    initWhxmUploaderXls();
    $("#whxmFileList .file-item").remove();//清空上次未上传的文件
    $("#whxmFileList").find(".fileNameView").show();
    $("#importWhxmFile").show().dialog("open");
}
function changeWxzfy(obj){
	var wxzfy = 0;
	var f = false; 
	$("#whxmAddDynamicTable tbody input[name='wxfy']").each(function(index,item){
		var currentVal = $(item).val();
		if(currentVal !=="" && !isNaN(currentVal)){
			f = true;
			wxzfy = (wxzfy*10000 + parseFloat(currentVal)*10000)/10000;
		}
	});
	if(f){
		$("#addWhxmForm input[name='wxzfy']").val(wxzfy);
	}else{
		$("#addWhxmForm input[name='wxzfy']").val("");
	}
}

/*function checkboxControl(obj, checkWay){
	if(checkWay == 'all'){
		var f = $(obj).find(".checkAll").is(":checked");
		if(f){
			$("#whxmTbody .checkOne").prop("checked", true);
		}else{
			$("#whxmTbody .checkOne").prop("checked", false);
		}
	}else{
		var f = true;
		$("#whxmTbody .checkOne").each(function(){
			var currentCheck = $(this).is(":checked");
			if(!currentCheck){
				f = false;
			}
		});
		if(f){
			$("#whxmCheckedAll").prop("checked", true);
		}else{
			$("#whxmCheckedAll").prop("checked", false);
		}
	}
}*/

//打开修改页面
function openUpdateWhxm(obj){
	var whxmIds = new Array();
	$(obj).parent().find("button").removeClass("current");
	$(obj).addClass("current");
	$("#whxmTbody tr").find("input").each(function(){
		if($(this).prop("checked")){
			whxmIds.push($(this).val());
		}
	});
	
	if(whxmIds == null || whxmIds.length == 0){
		$("#warning").find(".editOne").text("请选择要修改的项目！");
		$("#warning").show().dialog("open");
	}else if(whxmIds.length > 1){
		$("#warning").find(".editOne").text("每次只能修改一条项目信息，请重新选择!");
		$("#warning").show().dialog("open");
	}else{
		$("#updateWhxmInfoDiv").load("xmwhjl/getDataById",{id:whxmIds[0]},function(){
			$("#updateWhxmDlg").show().dialog("open");
			$("#updateWhxmDlg").parent().css("top","0px");
			var widHeight = $(window).height();
			var thisHeight = $("#updateWhxmDlg").height();
			$("#updateWhxmDlg").parent().css("top",  widHeight/2 - thisHeight/2 + "px");
		});
	}
	$("#updateWhxmDlg").prev().find("a.panel-tool-close").click(function(){
		colseWhxm();
	});
}

//关闭维护项目修改、新增页面
function colseWhxm(){
	$("#whxmTabDiv .tab_title button").removeClass("current");
	$(".whxmTips").remove();//移除所有的提示
	//$("#whxmUpdateButton").removeClass("current");
	$("#updateWhxmDlg").dialog("close");
	$("#updateWhxmInfoDiv").empty();
}


function submitUpdateWhxm(){
	var success = whxmFormValidate("#updateWhxmForm").form();
	if(!success){
		return false;
	}
	var whxmid = $("#updateWhxmForm").find("input[name='whxmid']").val();
	var xmmc = $("#updateWhxmForm").find("input[name='xmmc']").val();
	var xmbh = $("#updateWhxmForm").find("input[name='xmbh']").val();
	var wxdw = $("#updateWhxmForm").find("input[name='wxdw']").val();
	var wxzfy = $("#updateWhxmForm").find("input[name='wxzfy']").val();
	var whztArr = new Array();
	$("#updateWhxmForm [name='whzt']").each(function(){
		whztArr.push($(this).val());
	});
	var whjlidArr = new Array();
	$("#updateWhxmForm input[name='whjlid']").each(function(){
		whjlidArr.push($(this).val());
	});
	var wxxmbhArr = new Array();
	$("#updateWhxmForm input[name='wxxmbh']").each(function(){
		wxxmbhArr.push($(this).val());
	});
	var wxxmmcArr = new Array();
	$("#updateWhxmForm input[name='wxxmmc']").each(function(){
		wxxmmcArr.push($(this).val());
	});
	var wxgmArr = new Array();
	$("#updateWhxmForm input[name='wxgm']").each(function(){
		wxgmArr.push($(this).val());
	});
	var wxfyArr = new Array();
	$("#updateWhxmForm input[name='wxfy']").each(function(){
		wxfyArr.push($(this).val());
	});
	
	$.ajax({
		url:'xmwhjl/updateWhxm',
		type:'post',
		data:{whxmid:whxmid, xmmc:xmmc, xmbh:xmbh, wxdw:wxdw, wxzfy:wxzfy, whzt:whztArr.join(","), 
			whjlid:whjlidArr.join(","), wxxmbh:wxxmbhArr.join(", "), wxxmmc:wxxmmcArr.join(", "),
			wxgm:wxgmArr.join("; "), wxfy:wxfyArr.join(",")},
		dataType:"json",
		success:function(data){
			$("#updateWhxmDlg").dialog("close");
			colseWhxm();
			$("#updateWhxmInfoDiv").empty();
			whxmAjaxData();
		}
	});
}

function checkOneAll(obj){
	if(!$(obj).find("input").prop("checked")){
		$("#checkedAll").prop("checked", false);
		return false;
	}
	var f = false;
	$("#whxmTbody").find("input[type='checkbox']").each(function(){
		if(!$(this).prop("checked")){
			f = true;
			return false;
		}
	});
	if(f){
		$(".whxmTabList thead input").prop("checked", false);
	}else{
		$(".whxmTabList thead input").prop("checked", true);
	}
}

function whxmSubShow(){
	$("td.subShow").each(function(){
		var showMaxlength = $(this).attr("showMaxlength");
		var textVal = $(this).text();
		if(textVal.length > showMaxlength){
			textVal = textVal.substr(0, showMaxlength) + "...";
			 $(this).text(textVal);
		}
	});
}


