$(function(){
    //绑定事件，只对当前元素有效，后面添加的元素需要重新绑定。
	//点击下拉框事件
	$("#xmwhjlDiv .typeTitle").click(function(){
		$(this).find(".typeItem").slideToggle();
		$(this).parent().siblings().find(".typeItem").slideUp();
	});
	$("#xmwhjlDiv .typeItem a").click(function(){
		var text=$(this).text();
		$(this).parents(".typeTitle").find(".t_name").text(text);
		$(this).parents(".typeItem").slideDown();
	});
	$("#xmwhjlDiv .typeItem a").click(function(){
		$("#whjlOffiset").val("1");
		whjlAjaxData();
	});
	initDynaElment();
	showWhjlLine();
	subShow();
	
	$("#xmwhjlQueryDlg").prev().find("a.panel-tool-close").click(function(){
		$("#xmwhjlDiv").empty();
		$("#jkz_zlDlg").dialog('close');
	});
	
});

$(document).on("click","#whjlTbody .yzx",function(e) {
    var target = $(e.target);
    if (target.closest(".whjlTips").length == 0) {
        $(".whjlTips").remove();
    }
});

//保存项目维护记录
function saveWhjl(obj){
	$(".whjlTips").remove();//移除所有的提示
	var errorSpan = "";
	var trObj = $(obj).parent("td").parent("tr");
	
	var gzsb = $.trim(trObj.find("td:eq(5)").find("textarea").val());
	var gzsbpp = $.trim(trObj.find("td:eq(6)").find("textarea").val());
	if(sywh_xmzl == '监控站' || sywh_xmzl == '报警装置'){
		var gzsbTd = trObj.find("td:eq(5)");
		if(gzsb.length > 200){
			errorSpan = "<span class='whjlTips'>不得超过200个字符</span>";
			gzsbTd.append(errorSpan);
			return false;
		}
		var gzsbppTd = trObj.find("td:eq(6)");
		if(gzsbpp.length > 200){
			errorSpan = "<span class='whjlTips'>不得超过200个字符</span>";
			gzsbppTd.append(errorSpan);
			return false;
		}
	}
	var gzsbxh = $.trim(trObj.find("td:eq(7)").find("textarea").val());
	if(sywh_xmzl == '报警装置'){
		var gzsbxhTd = trObj.find("td:eq(7)");
		if(gzsbxh.length > 200){
			errorSpan = "<span class='whjlTips'>不得超过200个字符</span>";
			gzsbxhTd.append(errorSpan);
			return false;
		}
	}
	var gzbj = $.trim(trObj.find("td:eq(8)").find("textarea").val());
	if(sywh_xmzl == '报警装置' ||  sywh_xmzl == '监控站'){
		gzbj = "";
	}else{
		var gzbjId = trObj.find("td:eq(8)");
		if(gzbj.length > 200){
			errorSpan = "<span class='whjlTips'>不得超过200个字符</span>";
			gzbjId.append(errorSpan);
			return false;
		}
	}
	
	var shyy = $.trim(trObj.find("td:eq(9)").find("textarea").val());
	var shyyTd = trObj.find("td:eq(9)");
	if(shyy.length > 200){
		errorSpan = "<span class='whjlTips'>不得超过200个字符</span>";
		shyyTd.append(errorSpan);
		return false;
	}
	var gzsj = $.trim(trObj.find("td:eq(10)").find("input[type='hidden']").val());
	var wxsj = $.trim(trObj.find("td:eq(11)").find("input[type='hidden']").val());
	if(wxsj != "" && gzsj != ""){
		var gzsjStr = gzsj.replace(/[\-]/g,'');
		var wxsjStr = wxsj.replace(/[\-]/g,'');
		if(parseInt(wxsjStr) < parseInt(gzsjStr)){
			var gzsjTd = trObj.find("td:eq(10)");
			errorSpan = "<span class='whjlTips'>故障时间不能大于维修时间</span>";
			gzsjTd.append(errorSpan);
			return false;
		}
	}
	var id = $(obj).find("label").text();
	$.ajax({
		url:"xmwhjl/updateWhjl",
		type:"post",
		data:{id:id,gzsb:gzsb, gzsbpp:gzsbpp, gzsbxh:gzsbxh, shyy:shyy, gzsj:gzsj, wxsj:wxsj, gzbj:gzbj},
		dataType:"json",
		success:function(data){
			whjlAjaxData();
		}
	});
}

//删除行
function delTr(obj){
	$(obj).parent("td").parent("tr").remove();
	$("#xmwhjlDiv .tab_title button:eq(0)").attr("disabled",false);
	$("#xmwhjlDiv .tab_title button:eq(0)").removeClass("current");
	$("#xmwhjlDiv .tab_title button:eq(0)").css("backgroundColor","");
	
}

//分页查询维护记录
function whjlAjaxData(){
	var wxxmid = $("#whjlQueryDiv input[name='wxxmid']:eq(0)").val();
	var condition = {"wxxmid":wxxmid,"offiset":$("#whjlOffiset").val() };
	$.ajax({
		url:"xmwhjl/loadDataByCondition",
		data:condition,
		type:"post",
		success:function(data){
			//启用 添加记录按钮
			$("#xmwhjlDiv .tab_title button:eq(0)").attr("disabled",false);
			$("#xmwhjlDiv .tab_title button:eq(0)").removeClass("current");
			$("#xmwhjlDiv .tab_title button:eq(0)").css("backgroundColor","");
			$("#whjlTbody").empty();
			if(data!=null && data.recordList != null){
				for(var i=0;i<data.recordList.length;i++){
					$("#whjlTbody").append(
						"<tr>"+
							"<td>" +
								'<span onclick="editWhjl(this);" style="cursor:pointer;color: #659dc1;" class="whjlEditSpan">编辑</span>'+
								'<span style="display:none;cursor:pointer;color: #659dc1;" onclick="saveWhjl(this);"><label style="display:none;">'+data.recordList[i].id+'</label>保存</span>&nbsp;'+
								'<span style="display:none;cursor:pointer;color: #659dc1;" onclick="cancelWhjl(this);">取消</span>'+
							"</td>"+
							"<td title='"+data.recordList[i].wxxmmc+"' class='subShow' showMaxlength='20'>"+data.recordList[i].wxxmmc+"</td>"+
				       		"<td title='"+data.recordList[i].wxgm+"' class='subShow' showMaxlength='30'>"+data.recordList[i].wxgm+"</td>"+
				       		"<td>"+data.recordList[i].wxfy+"</td>"+
				       		"<td title='"+data.recordList[i].wxdw+"' class='subShow' showMaxlength='20'>"+data.recordList[i].wxdw+"</td>"+
				       		"<td style='position:relative;' class='yzx' title='"+data.recordList[i].gzsb+"'>"+
				       			'<span class="subShow" showMaxlength="20">'+data.recordList[i].gzsb+'</span>'+
				       		"</td>"+
				       		"<td style='position:relative;' class='yzx' title='"+data.recordList[i].gzsbpp+"'>"+
				       			'<span class="subShow" showMaxlength="20">'+data.recordList[i].gzsbpp+'</span>'+
				       		"</td>"+
				       		"<td style='position:relative;' class='yzx' title='"+data.recordList[i].gzsbxh+"'>"+
				       			'<span class="subShow" showMaxlength="20">'+data.recordList[i].gzsbxh+'</span>'+
				       		"</td>"+
				       		"<td style='position:relative;' class='yzx' title='"+data.recordList[i].gzbj+"'>"+
				       			"<span class='subShow' showMaxlength='20'>"+data.recordList[i].gzbj+"</span>"+
				       		"</td>"+
				       		"<td style='position:relative;' class='yzx' title='"+data.recordList[i].shyy+"'>"+
				       			'<span class="subShow" showMaxlength="20">'+data.recordList[i].shyy+'</span>'+
				       		"</td>"+
				       		"<td style='position:relative;width:100px;' class='yzx'>"+
				       			'<input type="text" value="'+data.recordList[i].gzsj +'" disabled="disabled" class="showItem transTime"/>' +
				       		"</td>"+
				       		"<td style='position:relative;width:100px;' class='yzx'>"+
				       			'<input type="text" value="'+data.recordList[i].wxsj +'" disabled="disabled" class="showItem transTime"/>' +
				       		"</td>"+
			       		"</tr>"
					);
				} 
				showWhjlLine();
				$("#getWhjlPageCount").val(data.pageCount);//总页数
				$("#getWhjlCurrentPage").val(data.currentPage);//当前页
				$("#whjlPageCount").text(data.pageCount);
				$(".whjlP_num a").remove();
				$(".whjlPagePrev").removeClass("disabled");
				$(".whjlPageNext").removeClass("disabled");
				loadWhjlPage();
				$("#whjlTbody tr").each(function(index, item){
					if(roleName != '2'){
						$(item).find("td:eq(0)").hide();
					}
				});
				$("#whjlTbody tr").find("td").each(function(){
					if($(this).find("input").length > 0){
						if($(this).find("input").val()=="null"){
							$(this).find("input").val("");
						}
					}else if($(this).find("span").length > 0){
						if($(this).find("span").text()=="null"){
							$(this).find("span").text("");
						}
					}else{
						if($(this).text() == "null"){
							$(this).text("");
						}
					}
					if($(this).attr("title")=="null"){
						$(this).attr("title","");
					}
				});
			} 
			//日期格式化
			$(".transTime").each(function(){
				var value = $(this).val();
				if(value=="null"){
					$(this).val("");
				}else if(value!=""){
					var unixTimestamp = new Date( value*1 ) ;
		            var commonTime = unixTimestamp.toLocaleString();
		            $(this).val(commonTime);
				}
			});
			//截取显示
			subShow();
			initDynaElment();
		}
	});
}

//编辑维护记录
function editWhjl(obj){
	var $currentTrObj = $(obj).parent("td").parent("tr");
	var $currentTdObj = $(obj).parent("td");
	$currentTrObj.find("td").css("border","1px solid");
	$currentTdObj.find("span").show();
	$(obj).hide();	
	$currentTrObj.find("td.yzx").find("span").hide();
	//追加元素（after）顺序不可变
	$currentTrObj.find("td.yzx").each(function(index, item){
		if($(item).find("span").length > 0){
			var currentTitle = $(item).attr("title");
			$(item).find("span").after("<textarea>"+currentTitle+"</textarea>");
		}
	});
	$currentTrObj.find("input.showItem").hide();
	$currentTrObj.find("input.showItem").after("<input type='text' style='cursor:pointer;' class='easyui-datebox dateInit' />");
	$currentTrObj.find("input.showItem").each(function(){
		var currentDateVal =  $(this).val();
		$(this).next().val(currentDateVal);
	});
	$('.dateInit').datebox({
	    required:false
	});
	initDate();
	$(".dateInit").next().addClass("dateOutSpan");
	$(".dateInit").next().children("span").addClass("dateInnerSpan");
	$(".dateInit").datebox({editable:false});
	$(".dateInit").next().children("input[type='text']").css("background-color","#f3f6f2");
	$(".dateInit").next().children("input[type='text']").css("margin-top","-3px");
	$(".dateInit").next().children("span").find("a").css("height","20px");
	initDynaElment();
	$("#whjlTbody").find(".whjlEditSpan").attr("onclick","");
	$("#whjlTbody").find(".whjlEditSpan").css("color","grey");
}

//取消
function cancelWhjl(obj){
	var $tdObj = $(obj).parent();
	var $trObj = $(obj).parent().parent();
	$trObj.find("td").css("border","none");
	$trObj.find("textarea").prop("disabled",true);
	//remove顺序不可变
	$trObj.find("input.showItem").nextAll().remove();
	$trObj.find("input.showItem").show();
	$trObj.find("td.yzx").find("span").nextAll().remove();
	$trObj.find("td.yzx").find("span").show();
	
	
	$tdObj.find("span").each(function(index, item){
		if($(item).text() == "编辑"){
			$(item).show();
		}else{
			$(item).hide();
		}
	});
	initDynaElment();
	$("#whjlTbody").find(".whjlEditSpan").attr("onclick","editWhjl(this)");
	$("#whjlTbody").find(".whjlEditSpan").css("color","#659dc1");
}

//初始化动态元素
function initDynaElment(){
	$("#whjlTbody textarea").css("background-color","#fff");
	$("#whjlTbody input").css({"background-color":"#fff", "border":"none", "margin":"none", "padding":"none"});
	$("#whjlTbody tr:nth-of-type(odd) input").css("background-color", "#f3f6f2");
	$("#whjlTbody tr:nth-of-type(odd) textarea").css("background-color", "#f3f6f2");
}

function subShow(){
	$("td.subShow,span.subShow").each(function(){
		var showMaxlength = $(this).attr("showMaxlength");
		var textVal = $(this).text();
		if(textVal.length > showMaxlength){
			textVal = textVal.substr(0, showMaxlength) + "...";
			 $(this).text(textVal);
		}
	});
}

function showWhjlLine(){
	if(sywh_xmzl != '监控站' && sywh_xmzl != '报警装置'){
		$(".xhjlTabList thead tr").find("th").eq(5).hide();
		$(".xhjlTabList thead tr").find("th").eq(6).hide();
		$(".xhjlTabList tbody tr").each(function(index,item){
			$(item).find("td").eq(5).hide();
			$(item).find("td").eq(6).hide();
		});
	}
	if(sywh_xmzl != '报警装置'){
		$(".xhjlTabList thead tr").find("th").eq(7).hide();
		$(".xhjlTabList tbody tr").each(function(index,item){
			$(item).find("td").eq(7).hide();
		});
	}
	if(sywh_xmzl == '报警装置' || sywh_xmzl == '监控站'){
		$(".xhjlTabList thead tr").find("th").eq(8).hide();
		$(".xhjlTabList tbody tr").each(function(index,item){
			$(item).find("td").eq(8).hide();
		});
	}
}
