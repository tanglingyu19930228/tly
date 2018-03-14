<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<base href="<%=basePath%>">
<!--让ie使用兼容性模式-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>省市表格-详细列表</title>
<style type="text/css">
input {
	visibility : visible !important;
}
</style>
<link rel="icon" href="BHF.ico" type="image/x-icon" />
<link rel="stylesheet" href="themes/default/easyui.css" />
<link rel="stylesheet" href="themes/icon.css" />
<link rel="stylesheet" href="css/demo.css">
<link rel="stylesheet" href="css/datebox.css">
<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css">
<!--引入上传插件样式-->
<link rel="stylesheet" href="css/webuploader.css">
<!--引入bootstrap样式-->
<link rel="stylesheet" href="css/bootstrap_progress.min.css">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/provinceTable.css">
<link rel="stylesheet" href="css/provinceTable_info.css">
<style type="text/css">
	span.datebox{
			width:99.5% !important;
			height: 28px !important;
		}

	body {
		padding:0px;
	}
	/* 改变input输入框改变颜色 */
	input:-webkit-autofill, textarea:-webkit-autofill, select:-webkit-autofill {
		-webkit-box-shadow: 0 0 0px 1000px #ecf4e6 inset;
	}
	/* 解决添加项目*占一行 */
	.textbox{
		width:82% !important;
	}
	
	/*解决添加项目有些下拉框不对齐*/
	.change_left{
		margin-left:4px;
	}
	span.textbox-disabled{
		background-color:rgb(233,233,233) !important;
	}
</style>

<!--分页样式-->
<script src="js/libs/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" href="css/page.css">
<link rel="stylesheet" href="css/pageOther.css">
<script src="js/libs/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/libs/jquery.datebox.js"></script>
<script src="js/libs/easyui-lang-zh_CN.js"></script>
<script src="js/libs/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="js/libs/jquery.DB_treeMenu.min.js"></script>
<!--引入jquery-validte插件-->
<script src="js/libs/jquery.validate.js"></script>
<!--jquery-validte中文包-->
<script src="js/libs/messages_zh.js"></script>
<!--上传插件-->
<script src="js/libs/webuploader.min.js"></script>
<!-- 控制高度显示的js-->
<script src="js/provinceTable.js"></script>
<script src="js/provinceTable_details.js"></script>
<!--导入文件js-->
<script src="js/importFile.js"></script>
<!-- 引入juicer.js文件 -->
<script src="js/libs/juicer-min.js"></script>
<script type="text/javascript">
	//重写日期格式化函数
	Date.prototype.toLocaleString = function() {
		var year = this.getFullYear();
		var month = this.getMonth() + 1<10?"0"+(this.getMonth() + 1):this.getMonth() + 1;
		var day = this.getDate()<10?"0"+this.getDate():this.getDate();
	    return year+"-"+month+"-"+day;
	};
	function downLoadFile(obj){
		var fileName = obj.next().text();
		$.ajax({
			url:'file/isExist?fileName='+encodeURIComponent(encodeURIComponent(fileName)),
			//data:'fileName='+fileName,
			type:'post',
			success:function(data){
				if(!data){
					$("#warning").find(".editOne").text("文件不存在");
	        		$("#warning").show().dialog("open");
				}else{
					window.location.href = "file/download?fileName="+encodeURIComponent(encodeURIComponent(fileName));
				}
			}
		});
	}
	//日期控件初始化
	function myformatter(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	}
	function myparser(s){
		if (!s) return new Date();
		var ss = (s.split('-'));
		var y = parseInt(ss[0],10);
		var m = parseInt(ss[1],10);
		var d = parseInt(ss[2],10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
			return new Date(y,m-1,d);
		} else {
			return new Date();
		}
	}
	$(function(){  
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
});  
</script>

<script>
var partTag ='${partTag}';
var update_xmzl='${xmzl}';
var update_bjfx='${item.codeName}';
	var toggleFlag =true;
	var roleName = '${loginUser.roleName }';
	var province = '${loginUser.province }';
	$(function(){
		//需要多次设置queryLineOptionItem0Div的高度，防止queryLineOptionItem1Div高度变化造成不对齐。
		$("#queryLineOptionItem0Div").css("height",$("#queryLineOptionItem1Div").height()+"px");
		$("#queryLineOptionItem0Div").css("height",$("#queryLineOptionItem1Div").height()+"px");
		$("#tab_reset").click(function(){$("#formAdd")[0].reset();});
		$(".scrollBlock").mCustomScrollbar({mouseWheelPixels: 100});
		$(".overflowX").mCustomScrollbar({mouseWheelPixels: 100,axis:"x",scrollButtons:{enable:true}});
		$(".tab_div").height($(".m_right").height()-$(".tab_title").height()-15-2);
        $(window).resize(function(){
            $(".tab_div").height($(".m_right").height()-$(".tab_title").height()-15-2);
        });
        //juicer模板引擎偏好设置
        juicer.set({  
		    'tag::operationOpen': '{@',  
		    'tag::operationClose': '}',  
		    'tag::interpolateOpen': '%{',  
		    'tag::interpolateClose': '}',  
		    'tag::noneencodeOpen': '%%{',  
		    'tag::noneencodeClose': '}',  
		    'tag::commentOpen': '{#',  
		    'tag::commentClose': '}'  
		});
        init();
      	// 展示列隐藏显示控制
		$(".showLineLabel").click(function(){
			var $this = $(this).find("input[name='showLine']")[0];
			var $thisVal = $this.value;
			if($thisVal == "全选"){
				if($this.checked){
					$("input[name='showLine']").prop("checked",true);
					$("#tab_cont_div .tabList thead tr th").show();
					$("#tab_cont_div .tabList tbody tr td").show();
				}else{
					$("input[name='showLine']").prop("checked",false);
					$("#tab_cont_div .tabList thead tr th:gt(0)").hide();
					$("#tab_cont_div .tabList thead tr th:eq(0)").show();//全选列展示
					for(var i = 0; i < $("#tab_cont_div .tabList tbody tr").length; i++){
						$("#tab_cont_div .tabList tbody tr:eq("+i+") td:eq(0)").show();//checkbox列展示
						$("#tab_cont_div .tabList tbody tr:eq("+i+") td:gt(0)").hide();
					}
				}
			}else{
				if($this.checked){
					var length = $("input[name='showLine']").length;
					var showLineLength = $("input[name='showLine']:checked").length;
					if(showLineLength == length -1){
						$($("input[name='showLine']")[0]).prop("checked",true);
					}
					var $thead_tr_th = $("#tab_cont_div .tabList thead tr th");
					var $tbody_tr = $("#tab_cont_div .tabList tbody tr");
					var index;
					$("#tab_cont_div .tabList thead tr th:eq(0)").show();//全选列展示
					for(var i = 0; i < $thead_tr_th.length; i++){
						if($($thead_tr_th[i]).text().indexOf($thisVal) >= 0){
							$($thead_tr_th[i]).show();
							index = i;
						}
					}
					$("#tab_cont_div .tabList tbody tr:eq(0) td:eq(0)").show();//checkbox列展示
					for(var i = 0; i < $tbody_tr.length; i++){
						$("#tab_cont_div .tabList tbody tr:eq("+i+") td:eq("+index+")").show();
					}
				}else{
					var index;
					$($("input[name='showLine']")[0]).prop("checked",false);
					$("#tab_cont_div .tabList thead tr th:eq(0)").show();//全选列展示
					for(var i = 0; i < $("#tab_cont_div .tabList thead tr th").length; i++){
						if($($("#tab_cont_div .tabList thead tr th")[i]).text().indexOf($thisVal) >= 0){
							$($("#tab_cont_div .tabList thead tr th")[i]).hide();
							index = i;
						}
					}
					for(var i = 0; i < $("#tab_cont_div .tabList tbody tr").length; i++){
						$("#tab_cont_div .tabList tbody tr:eq("+i+") td:eq("+index+")").hide();
						$("#tab_cont_div .tabList tbody tr:eq("+i+") td:eq(0)").show();//checkbox列展示
					}
				}
			}
			
			var checkedLength = $("input[name='showLine']:checked").length;
			if(checkedLength == 0){
				$("#tab_cont_div .tabList thead tr th:eq(0)").hide();
				for(var i = 0; i < $("#tab_cont_div .tabList tbody tr").length; i++){
					$("#tab_cont_div .tabList tbody tr:eq("+i+") td:eq(0)").hide();
				}
			}else{
				$("#tab_cont_div .tabList thead tr th:eq(0)").show();
				for(var i = 0; i < $("#tab_cont_div .tabList tbody tr").length; i++){
					$("#tab_cont_div .tabList tbody tr:eq("+i+") td:eq(0)").show();
				}
			}
			
			//对表格长度做控制   4000px
			var lineCount = $("#tab_cont_div .tabList thead tr th").length;//表头个数
			var showLineCount = $("input[name='showLine']:checked").length;//显示列个数
			if(showLineCount != lineCount){
				showLineCount = showLineCount + 1;
			}
			var lengthValue = (showLineCount/lineCount) *4000;//展示列的length属性值
			if(lengthValue < 1600){
				lengthValue = 1600;
			}
			$("#tab_cont_div .tabList").parent().css("width",lengthValue + "px");
			$("#tab_cont_div .tabList").css("width",lengthValue + "px");
		});
      
		  $(".checkBoxText").click(function(){
			  var $this = this;
			  var checkBox = $($this).parent().find("input[type='checkbox']")[0];
			  if(checkBox.checked){
				  checkBox.checked = false;
			  }else{
				  checkBox.checked = true;
			  }
		  });
		  //input框失去焦点去前后空格
		  $("input[type='text']").blur(function(){
			  var value = $(this).val();
			  $(this).val($.trim(value));
		  });

      	if('${loginUser.roleName}'=="2"){
			var szsf = '${loginUser.province}';
            $.ajax({
                url:"area/loadCity",
                data:"szsf="+szsf,
                async:false,
                dataType:"json",
                success:function (data) {
                    if(data!=null && data.length>0){
                        $("#formAdd #address2").show();
                        for(var i=0;i<data.length;i++){
							$("#formAdd #address2").append("<option value='"+data[i].codeValue+"' >"+data[i].codeName+"</option>");
                        }
                    }
                }
            })
	  	}
      
      	$("#jsqyDiv select").on("click",function(){//点击区域，清除验证提示框
      		$(".tips").remove();
			$(".jsqyTips").remove();
      	});
    });

	/**
	 * 项目名称值改变事件
	 * @param obj
	 * @param superObj
	 */
	function xmmcChange(obj,superObj){
		var xmmc = obj.val();
		var szsf = $(superObj+" #address1").val();
		if(xmmc!=null && xmmc!="" && szsf!=null && szsf!='0'){
		    if('${loginUser.roleName}'=="1"){
                szsf = $(superObj+" #address1").find("option:selected").text();
            }
            initUpload(xmmc,szsf,superObj);
		}else{
            if(uploader1 != undefined){
                uploader1.destroy();
            }
		}
	}

	function selectChecked(obj,superObj,num,index){
        var superCode =obj.val();
        if(!superCode){
			return;
		}
        if(superCode==0){
            if(index == 1){
                $(superObj+" #address2").show();
                $(superObj+" #address2").empty();
                $(superObj+" #address2").append("<option value='0'>--请选择市--</option>");
                $(superObj+" #address3").show();
                $(superObj+" #address3").empty();
                $(superObj+" #address3").append("<option value='0'>--请选择区--</option>");
			}else{
                $(superObj+" #address3").show();
                $(superObj+" #address3").empty();
                $(superObj+" #address3").append("<option value='0'>--请选择区--</option>");
			}
            $(superObj+".szjkz").empty();
            return;
		}
        var xmzl=$("#xmzl").val();
        var url="";
        if(num!=1){
            url = "area/querychildcity";
		}
		var xmmc = $(superObj+" #xmmc").val();
		if(partTag!='1'&&(index ==1 || (index==2 && $("#role").val()==2))){
            if(xmmc!=null && $.trim(xmmc)!=""){
                var szsf;
                if($("#role").val()==1){
                    szsf = obj.find("option:selected").text();
                }else{
                    szsf = '${loginUser.province}';
				}
                //initUpload(xmmc,szsf,"#formAdd");
                initUpload(xmmc,szsf,superObj);
			}else{
                $("#warning").find(".editOne").text("请填写项目名称！");
                $("#warning").show().dialog("open");
			}
		}
		$.ajax({
			url:url,
			data:"superCode="+superCode,
            async:false,
            dataType:"json",
			success:function (data) {
			    if(data!=null && data.length>0){
			        if(index==1){
                        $(superObj+" #address2").parent().show();
                        $(superObj+" #address2").empty();
                        $(superObj+" #address2").append("<option value='0'>--请选择市--</option>");
                        $(superObj+" #address3").empty();
                        $(superObj+" #address3").append("<option value='0'>--请选择区--</option>");
						for(var i=0;i<data.length;i++){
                            $(superObj+" #address2").append("<option value='"+data[i].codeValue+"' >"+data[i].codeName+"</option>");
						}
					}else{
                        $(superObj+" #address3").parent().show();
                        $(superObj+" #address3").empty();
                        $(superObj+" #address3").append("<option value='0'>--请选择区--</option>");
                        for(var i=0;i<data.length;i++){
                            $(superObj+" #address3").append("<option value='"+data[i].codeValue+"' >"+data[i].codeName+"</option>");
                        }
					}
				}else{
			        if(index==1){
                        $(superObj+" #address2").empty();
                        $(superObj+" #address2").parent().hide();
                        $(superObj+" #address3").empty();
                        $(superObj+" #address3").parent().hide();
					}else{
                        $(superObj+" #address3").empty();
                        $(superObj+" #address3").parent().hide();
					}
                    if(xmzl=="视频前端" || xmzl=="显控终端" || xmzl=="传输线路" || xmzl=="报警装置" ||xmzl=="供电系统"){
                        loadJkz($(superObj+".szjkz"),superObj);
                    }
				}
			}
		})
	}

  	//将需要隐藏的列隐藏
	function hideQueryLine(){
		var hideLineArr = new Array();
		var showLineNotChecked = $("input[name='showLine']:gt(0)").not("input[name='showLine']:checked");
		var hideIndexArr = new Array();
		for(var i = 0; i < showLineNotChecked.length; i++){
			hideLineArr.push($(showLineNotChecked[i]).val());
		}
		if(hideLineArr != null && hideLineArr.length > 0){
			//展示列名称
			for(var i = 0; i<$("#tab_cont_div .tabList thead tr th").length; i++){
				var thTextPerIndex = $("#tab_cont_div .tabList thead tr th:eq("+i+")").text();
				for(var j = 0; j < hideLineArr.length; j++){
					if(thTextPerIndex.indexOf(hideLineArr[j]) >= 0){
						hideIndexArr.push(i);
					}
				}
			}
			for(var i = 0 ; i < hideIndexArr.length; i++){
				for(var j = 0; j<  $("#tab_cont_div .tabList tbody tr").length; j++){
					$("#tab_cont_div .tabList #tbody tr:eq("+j+") td:eq("+hideIndexArr[i]+")").hide();
				}
			}
		}
		
		var checkedLength = $("input[name='showLine']:checked").length;
		if(checkedLength == 0){
			$("#tab_cont_div .tabList tbody tr:eq(0) td:eq(0)").hide();//checkbox列展示
			for(var i = 0; i < $("#tab_cont_div .tabList tbody tr").length; i++){
				$("#tab_cont_div .tabList tbody tr:eq("+i+") td:eq(0)").hide();
			}
		}else{
			$("#tab_cont_div .tabList tbody tr:eq(0) td:eq(0)").show();//checkbox列展示
			for(var i = 0; i < $("#tab_cont_div .tabList tbody tr").length; i++){
				$("#tab_cont_div .tabList tbody tr:eq("+i+") td:eq(0)").show();
			}
		}
		//对表格长度做控制   4000px
		var lineCount = $("#tab_cont_div .tabList thead tr th").length;//表头个数
		var showLineCount = $("input[name='showLine']:checked").length;//显示列个数
		if(showLineCount != lineCount){
			showLineCount = showLineCount + 1;
		}
		var lengthValue = (showLineCount/lineCount) *4000;//展示列的length属性值
		if(lengthValue < 1600){
			lengthValue = 1600;
		}
		$("#tab_cont_div .tabList").parent().css("width",lengthValue + "px");
		$("#tab_cont_div .tabList").css("width",lengthValue + "px");
	}
  	function toForward(){
		var sf=$("#szsf").val();
		$("#postForm").empty();
		var f=$("#postForm")[0];
        f.action='annualPlanProvince/load';
        f.innerHTML='<input type="hidden" name="szsf" value="'+sf+'"/>'
        + '<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
        f.submit();
  	}
  	
  	function closeDownLoad(){
  		$("#downloadDlg").dialog("close");
  	}
	//使用维护起
  	//切换tab页  
  	var sywh_xmlb = '${xmlb }';
  	var sywh_xmzl = '${xmzl }';
  	var sywh_szsf = '${szsf }';
  	function switchTab(tabIndex){
  		$("#whxmTabDiv").empty();
  		if(tabIndex == 0){
  			$("#postForm").empty();
        	var f=$("#postForm")[0];
            f.action='xmzl/loadDataTable';
            f.innerHTML='<input type="hidden" name="xmlb" value="'+sywh_xmlb+'"/>'
            +'<input type="hidden" name="xmzl" value="'+sywh_xmzl+'"/>'
            +'<input type="hidden" name="szsf" value="'+sywh_szsf+'"/>'//所在区域
            +'<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
            f.submit();
            $("#whxmTabDiv").hide();
  		}else if(tabIndex == 1){
  			$("#whxmTabDiv").load("xmwhjl/loadWhxmData", {szsf:sywh_szsf, xmzl:sywh_xmzl}, function(){
	  			$("#sywhTabDiv button:eq(0)").removeClass("current");
	  			$("#sywhTabDiv button:eq(1)").addClass("current");
  				$("#whjlTabDiv").hide();
                $("#whxmTabDiv").show();
  			});
  		}
  	}
  	/*
  		去除特殊字符
  		windows下    / \ : * " < > | ？ 等字符无法创建文件夹
  	*/	
  	function removeSpecialChar(obj){
  		var value = $(obj).val();
  		value = value.replace(/[\/\\\:\*\"\<\>\|\?]/g,'');
  		$(obj).val(value);
  	}
	//使用维护止
	
	//第一次下拉地形类别、边界方向，按ctrl、alt等键，下拉内容显示不全，enterKey方法消除此种缺陷。
	function enterKey(e){
  	    var timesRun = 0;
        var interval = setInterval(function(){
            timesRun += 1;
            if(timesRun <= 10){
                $(".combo-p .combobox-item").css("display", "block");
            }else{
                clearInterval(interval);
			}
        }, 100);
	}
</script>
    
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<div class="main" style="/*overflow:hidden;*/overflow-x: hidden;overflow-y: hidden;">
		<ol class="navTip breadcrumb">
			<li><a href="user/toWelcome">首页</a></li>
 			<c:if test="${partTag eq '1' }">
 				<li><a href="annualPlan/load?partTag=${partTag}">${loginUser.province=='国家'?'全国':loginUser.province }统筹规划报表</a></li>
 				<li><a href="javascript:void(0);" onclick="toForward();">${szsf}统筹规划报表</a></li>
 			</c:if>
 			<c:if test="${partTag eq '2' }">
 				<li><a href="annualPlan/load?partTag=${partTag}">${loginUser.province=='国家'?'全国':loginUser.province }年度计划报表</a></li>
				<li><a href="javascript:void(0);" onclick="toForward();">${szsf}年度计划报表</a></li>
 			</c:if>
			<c:if test="${partTag eq '3'}">
				<li><a href="implement/load?partTag=${partTag}">${loginUser.province=='国家'?'全国':loginUser.province }建设项目实施监督</a></li>
			</c:if>
			<c:if test="${partTag eq '4'}">
				<li><a href="usemaintenance/load?partTag=${partTag}">${loginUser.province=='国家'?'全国':loginUser.province }建设项目使用维护</a></li>
			</c:if>
			<li class="active">${xmlb}----${xmzl}</li>
		</ol>
		<jsp:include page="provinceLead.jsp"></jsp:include>
		
		<%-- 使用维护起--%>
		<div style="display:${partTag eq '4' ? '' : 'none' };" class="sywhTab_title" id="sywhTabDiv">
			<button type="button" onclick="switchTab(0);" class="current">设施项目</button>
			<button type="button" onclick="switchTab(1);">维护项目</button>
		</div>
		<%-- 使用维护止--%>
		
		<div id="whjlTabDiv" class="tab m_right">
			<div class="tab_title">
				<c:if test="${loginUser.roleName eq '2' or partTag eq '1' or partTag eq '2'}">
					<button type="button" class="tab_reset">添加项目</button>
					<button type="button">删除项目</button>
					<button type="button">修改项目</button>
				</c:if>
				<button type="button" style="display:${partTag eq '4' ? '' : 'none'}">导入维护记录</button>
				<button type="button" style="display:${partTag eq '4' ? '' : 'none'}">导出维护记录</button>
				<button type="button" class="lastBtn" style="display:${partTag eq '2' ? '' : 'none' }">转入实施</button>
				<button type="button" style="display:${partTag eq '3' ? '' : 'none'} ">转入维护</button>
			</div>
			<!-- <div class="import_item fr">
            	<form action="import_importExcel.action" method="post" enctype="multipart/form-data">
		            <input type="file" placeholder="请选择Excel文件" name="upload" accept=".XLS,.xlsx"><button type="button">导入文件</button>
		            <button type="submit">确定</button>
	            </form>
	        </div> -->
        	<div class="queryLineOptionDiv">
	        	<jsp:include page="queryLineOption.jsp"></jsp:include>
        	</div>
			<div class="tab_div" style="overflow-y:auto; height: 700px;">
				<!-- 统计表 -->
				<div class="tab_cont" id="tab_cont_div">
					<div class="overflowX">
						<input type="hidden" id="offiset" name="offiset" />
						<c:if test="${xmzl!=null && xmzl=='执勤道路' }">
							<jsp:include page="dataTablePage/zqdl.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='桥梁' }">
							<jsp:include page="dataTablePage/qiaoliang.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='执勤码头' }">
							<jsp:include page="dataTablePage/zqmt.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='直升机停机坪' }">
							<jsp:include page="dataTablePage/zsjtjp.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='铁丝网' }">
							<jsp:include page="dataTablePage/tsw.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='铁栅栏' }">
							<jsp:include page="dataTablePage/tzl.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='拦阻桩' }">
							<jsp:include page="dataTablePage/lzz.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='隔离带' }">
							<jsp:include page="dataTablePage/gld.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl == '报警线路' }">
							<jsp:include page="dataTablePage/bjxl.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl == '拒马' }">
							<jsp:include page="dataTablePage/juma.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='执勤房' }">
							<jsp:include page="dataTablePage/zqfDetail.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='了望塔' }">
							<jsp:include page="dataTablePage/lwtDetail.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='标志牌' }">
							<jsp:include page="dataTablePage/bzpDetail.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='传输线路' }">
							<jsp:include page="dataTablePage/csxlDetail.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='报警装置' }">
							<jsp:include page="dataTablePage/bjzz.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl == '供电系统' }">
							<jsp:include page="dataTablePage/gdxt.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='无人值守电子哨兵' }">
							<jsp:include page="dataTablePage/wrzsdzsb.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='监控中心' }">
							<jsp:include page="dataTablePage/jkzxDetail.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='监控站' }">
							<jsp:include page="dataTablePage/jkzDetail.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='视频前端' }">
							<jsp:include page="dataTablePage/spqdDetail.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='显控终端' }">
							<jsp:include page="dataTablePage/xkzdDetail.jsp"></jsp:include>
						</c:if>
						<c:if test="${xmzl!=null && xmzl=='军警民联防平台' }">
							<jsp:include page="dataTablePage/jjmlfptDetail.jsp"></jsp:include>
						</c:if>
					</div>
					<%-- <p class="depiction">${szsf}${xmzl}：新建<strong> <fmt:formatNumber
								value="${list_xj }" maxFractionDigits="4" /></strong><span class="unit"></span>，升级<strong>
							<fmt:formatNumber value="${list_sj}" maxFractionDigits="4" />
						</strong><span class="unit"></span>，
						试点<strong>${sdJsgm }</strong><span class="unit"></span>，
						总投资<strong> <fmt:formatNumber value="${zytz+dftz }"
								maxFractionDigits="4" /></strong>万元，其中中央投资<strong> <fmt:formatNumber
								value="${zytz }" maxFractionDigits="4" /></strong>万元，地方投资<strong>
							<fmt:formatNumber value="${dftz }" maxFractionDigits="4" />
						</strong>万元。
					</p> --%>
					<div class="page">
						<input type="hidden" id="getCurrentPage"
							value="${pageBean.currentPage}"> <input type="hidden"
							id="getPageCount" value="${pageBean.pageCount}"> <span
							class="p_num"><span class="pagePrev"><b class="arr"></b>上一页</span><span
							class="pageNext">下一页<b class="arr"></b></span></span> <span
							class="p_skip"><em>共<b id="pageCount">${pageBean.pageCount}</b>页，&nbsp;跳到
						</em> <input type="text" id="turnPage" maxlength="6"><em>页&nbsp;</em><a
							href="javascript:" id="confirm">确定</a> </span>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<%-- 使用维护起--%>
		<div id="whxmTabDiv" class="m_right" style="display: none;overflow-x: auto;"></div>
		<%-- 使用维护止--%>
	</div>

	<!-- 隐藏的Input 用于保存选中的ID -->
	<input type="hidden" id="ids" />
	<div id="addDlg" class="easyui-dialog" data-options="modal:true,closed:true,closable: false" title=" " style="width:850px;display:none;">
		<div data-options="region:'center'" style="padding:25px 65px;" class="dialog_center">
			<div class="scrollBlock" id="scrollForm" style="height:650px;overflow: auto;">
				<div class="addInfo" id="addInfo" onkeydown="enterKey(event);">
					<form action="xmzl/add" id="formAdd" name="formAdd" method="post" class="commonForm">
						<input type="hidden" id="tokenId" name="token" value="${token}">
						<div class="w50 project_item">
							<label>项目类别：</label>
							<div class="wRmb">
								<input type="text" id="tjxmlb" name="xmlb" value="${xmlb}" readonly="readonly">
							</div>
							<span class="start">*</span>
						</div>
						<div class="w50 project_item">
							<label>项目子类：</label>
							<div class="wRmb">
							<input type="text" id="tjxmzl" name="xmzl" value="${xmzl}" readonly="readonly">
							</div>
							<span class="start">*</span>
						</div>
						<div class="clear" style="padding:0;"></div>
						<div class="project_item">
							<label>项目名称：</label>
							<input type="text" class="lengthRequriedVa input01" name="xmmc" id="xmmc" onkeyup="removeSpecialChar(this);" onblur="removeSpecialChar(this);" onchange="xmmcChange($(this),'#formAdd');"/>
							<span class="start">*</span>
						</div>
						<div class="project_item" id="jsqyDiv"> 
							<label>建设区域：</label>
							<label style="position: relative;width: 26.5%;" class="wRmb">
								<c:if test="${loginUser.roleName eq '2' }">
									<input type="text" id="address1" class="sj" name="szsf" value="${loginUser.province}" style="width:100%;margin-right:1.5%;"/>
								</c:if>
								<c:if test="${loginUser.roleName eq '1' }">
									<select class="select01" id="address1" onchange='selectChecked($(this),"#formAdd ",2,1)' name="szsf" style="width:100%;">
										<option value="0">--请选择省份--</option>
										<c:forEach items="${sfList }" var="item">
											<option value="${item.codeValue }">${item.codeName }</option>
										</c:forEach>
									</select>
								</c:if>
							</label>
							<label style="position: relative;width: 26.5%;" class="wRmb">
								<select class="select01" onchange='selectChecked($(this),"#formAdd ",2,2)' id="address2" name="szcs" style="width:100%;">
									<option value="0">--请选择市--</option>
								</select>
							</label>
							<label style="position: relative;width: 26.5%;" class="wRmb">
								<select class="select01 lastSelect" onchange='loadJkz($("#formAdd .szjkz"),"#formAdd ")' id="address3" name="szq" style="width:100%;">
									<option value="0">--请选择区--</option>
								</select>
							</label>
							<span class="start">*</span>
						</div>
						<div class="multi project_item">
							<label>边界方向：</label>
							<select class="easyui-combobox" id="bj" name="bjfx" data-options="multiple:true,multiline:true" style="width:65%;">
								<c:forEach items="${bjfxList }" var="item">
									<option value="${item.codeName }">${item.codeName }</option>
								</c:forEach>
							</select><span class="start">*</span>
						</div>
						<div class="multi project_item">
							<label>地形类别：</label>
								<select class="easyui-combobox" id="dx" name="dxlb" data-options="multiple:true,multiline:true" style="width:65%;">
								<c:forEach items="${dxlbList }" var="item">
									<option value="${item.codeName }">${item.codeName }</option>
								</c:forEach>
							</select>
							<span class="start">*</span>
						</div>
						<div class="w50 project_item">
							<label>经度：</label>
							<div class="wRmb">
								<input type="text" class="jd input03" name="jd" />
							</div>
						</div>
						<div class="w50 project_item">
							<label>纬度：</label>
							<div class="wRmb">
								<input type="text" class="wd input03" name="wd" />
							</div>
						</div>
						<div class="w50 project_item">
							<label>设施类型：</label>
							<select  class="select02" name="sslx">
								<c:forEach items="${sslxList }" var="item">
									<option value="${item.codeName }">${item.codeName }</option>
								</c:forEach>
							</select>
							<span class="start">*</span>
						</div>
						<div class="clear" style="padding:0;"></div>
						<div class="w50 project_item">
							<label>建设性质：</label>
							<select class="select02" name="jsxz">
								<c:forEach items="${jsxzList }" var="item">
									<option value="${item.codeName }">${item.codeName }</option>
								</c:forEach>
							</select><span class="start">*</span>
						</div>
						<div class="w50 project_item">
							<label>使用单位：</label>
							<div class="wRmb">
								<input type="text" class="lengthRequriedVa input01" name="sydw" />
							</div>
							<span class="start">*</span>
						</div>
						<div class="clear" style="padding:0;"></div>
						<div class="w50 project_item">
							<label>申报单位：</label>
							<div class="wRmb">
								<input type="text" class="lengthVa input03" name="sbdw" />
							</div>
						</div>
						<div class="w50 dataDiv project_item">
							<label>投资年度：</label>
							<div class="wRmb">
							<c:if test="${partTag=='2'}">
							<input type="text"  class="yearVa ndjh" name="tznd" value="${year}"/>
							</c:if>
							<c:if test="${partTag=='1' || partTag=='3' || partTag eq '4'}">
							<input type="text"  class="yearVa" name="tznd"/>
							</c:if>
								<span class="dw" style="padding-left: 7px;">年</span>
							</div>
							<span class="start">*</span>
						</div>
						<div class="clear" style="padding:0;"></div>
						<div class="w50 project_item">
							<label>中央投资：</label>
							<div class="wRmb">
								<input type="text" class="numberRequiredVa" name="zytz" />
								<span class="dw">万元</span>
							</div>
							<span class="start">*</span>
						</div>
						<div class="w50 project_item">
							<label>地方投资：</label>
							<div class="wRmb">
								<input type="text" class="numberRequiredVa" name="dftz" />
								<span class="dw">万元</span>
							</div>
							<span class="start">*</span>
						</div>
						<div class="clear" style="padding:0;"></div>
						<c:if test="${xmzl == '执勤道路' }">
							<div class="dutyRoad project_item">
								<!--执勤道路-->
								<div >
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="zqdl.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="zqdl.jsgm" />
										<span class="dw">公里</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>道路类别：</label>
									<select class="select02" name="zqdl.dllb">
										<c:forEach items="${dllbList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>道路类型：</label>
									<select class="select02" name="zqdl.dllx">
										<c:forEach items="${dllxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label>道路等级：</label>
									<select class="select02" name="zqdl.dldj">
										<c:forEach items="${dldjList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>路基类型：</label>
									<select class="select02" name="zqdl.ljlx">
										<c:forEach items="${ljlxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label>路面类型：</label>
									<select class="select02" name="zqdl.lmlx">
										<c:forEach items="${lmlxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='桥梁' }">
							<div class="bridge project_item">
								<!--桥梁-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="qiaoLiang.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="qiaoLiang.jsgm" />
										<span class="dw" style="padding-left: 7px;">座</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>桥梁类型：</label>
									<select class="select02" name="qiaoLiang.qllx">
										<c:forEach items="${qllxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>载重：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="qiaoLiang.zz" />
										<span class="dw" style="padding-left: 7px;">吨</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='执勤码头' }">
							<div class="wharf project_item">
								<!--执勤码头-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="zqmt.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="zqmt.jsgm" />
										<span class="dw" style="padding-left: 7px;">座</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='直升机停机坪' }">
							<div class="tarmac project_item">
								<!--直升机停机坪-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="zsjtjp.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>停机坪类型：</label><select class="select02 change_left"
										name="zsjtjp.tjplx">
										<c:forEach items="${tjplxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="zsjtjp.jsgm" />
										<span class="dw" style="padding-left: 7px;">个</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl =='铁丝网'}">
							<div class="ironNet project_item">
								<!--铁丝网-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="tsw.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="tsw.jsgm" />
										<span class="dw">公里</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>铁丝网类型：</label><select class="select02 change_left" name="tsw.tswlx">
										<c:forEach items="${tswlxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>拦阻门：</label>
									<div class="wRmb">
										<input type="text" class="numL" name="tsw.lzmgs" maxlength="5" />
										<span class="dw" style="padding-left: 7px;">个</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='铁栅栏' }">
							<div class="ironFence project_item">
								<!--铁栅栏-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="tzl.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="tzl.jsgm" />
										<span class="dw">公里</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='拦阻桩' }">
							<div class="hinderStake project_item">
								<!--拦阻桩-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="lzz.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="lzz.jsgm" /><span
											class="dw" style="padding-left: 7px;">套</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>拦阻桩类型：</label><select class="select02 change_left" name="lzz.lzzlx">
										<c:forEach items="${lzzlxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='隔离带' }">
							<div class="knifeRest project_item">
								<!--隔离带-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="gld.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="gld.jsgm" />
										<span class="dw" style="padding-left: 7px;">米</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='拒马' }">
							<div class="median project_item">
								<!--拒马-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="juma.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="juma.jsgm" />
										<span class="dw" style="padding-left: 7px;">个</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='报警线路' }">
							<div class="alarmCircuit project_item">
								<!--报警线路-->
								<div style="clear: both;">
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="bjxl.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="bjxl.jsgm" /><span
											class="dw">公里</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>设备品牌：</label>
									<div class="wRmb">
										<input type="text" class="lengthVa input03" name="bjxl.sbpp" maxlength="60"/>
									</div>
								</div>
								<!-- <div class="w50">
									<label>设备品牌：</label>
									<input type="text" class="lengthVa input03" name="bjxl.sbpp" />
								</div> -->
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='监控中心' }">
							<div class="surveillanceCenter project_item">
								<!--监控中心-->
								<div class="w50">
									<label class="ver-middle">向上联通情况：</label>
									<select class="select02 ver-middle" name="jkzx.xsltqk">
										<c:forEach items="${list_ltqk}" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label class="ver-middle">向上联通网络性质：</label>
									<select class="select02 ver-middle" name="jkzx.xsltwlxz">
										<c:forEach items="${list_ltwlxz }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label class="ver-middle">向上传输线路：</label>
									<select class="select02 ver-middle" name="jkzx.xscsxl">
										<c:forEach items="${list_csxl }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label>级别：</label><select class="select02 change_left" name="jkzx.jb">
										<c:forEach items="${list_jb }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='监控站' }">
							<div class="monitorStation project_item">
								<!--监控站-->
								<div class="w50">
									<label class="ver-middle">向上联通情况：</label>
									<select class="select02 ver-middle" name="jkz.xsltqk">
										<c:forEach items="${list_ltqk }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label class="ver-middle">向上联通网络性质：</label>
									<select class="select02 ver-middle" name="jkz.xsltwlxz">
										<c:forEach items="${list_ltwlxz }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label class="ver-middle">向上传输线路：</label>
									<select class="select02 ver-middle" name="jkz.xscsxl">
										<c:forEach items="${list_csxl }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label>显控终端：</label>
									<div class="wRmb">
										<input type="text" class="numVa jkzzlgs_readonly" name="jkz.xkzdgs" maxlength="5" />
										<span class="dw" style="padding-left: 7px;">个</span>
									</div>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>移动前端：</label>
									<div class="wRmb">
										<input type="text" class="numVa jkzzlgs_readonly" name="jkz.ydqdgs" maxlength="5" />
										<span class="dw" style="padding-left: 7px;">个</span>
									</div>
								</div>
								<div class="w50">
									<label>固定前端：</label>
									<div class="wRmb">
										<input type="text" class="numVa jkzzlgs_readonly" name="jkz.gdqdgs" maxlength="5" />
										<span class="dw" style="padding-left: 7px;">个</span>
									</div>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='视频前端' }">
							<div class="vedioLeadingEnd project_item">
								<input type="hidden" name="spqd.jkz_name" id="spqd">
								<!--视频前端-->
								<div>
									<label>传输方式：</label>
									<input type="text" class="lengthVa input03" name="spqd.csfs" />
								</div>
								<div style="clear: both;">
									<label>放置地点：</label>
									<input type="text"class="lengthRequriedVa input01" name="spqd.fzdd" />
									<span class="start">*</span>
								</div> 
								<div class="w50">
									<label>所在监控站：</label>
									<select class="select02 szjkz change_left" name="spqd.jkz_id" onchange="loadjkzname('${xmzl}',this.options[this.options.selectedIndex].text)">
										 <%-- <c:forEach items="${list_jkzName}" var="item">
				                    		<option value="${item.id}">${item.xmmc}</option>
				                    	</c:forEach>  --%>
									</select>
								</div>
								<div class="w50">
									<label>设备类型：</label>
									<select class="select02" name="spqd.sblx">
										<c:forEach items="${list_spqdllx }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='显控终端' }">
							<div class="samkoonEnd project_item">
								<!--显控终端-->
								<input type="hidden" name="xkzd.jkz_name" id="xkzd">
								<div class="w50">
									<!-- <label>所在监控站：</label> -->
									<label>所在监控站：</label>
									<select  class="select02 szjkz change_left" name="xkzd.jkz_id" onchange="loadjkzname('${xmzl}',this.options[this.options.selectedIndex].text)">
										<%-- <c:forEach items="${list_jkzName }" var="item">
				                    		<option value="${item.id }">${item.xmmc }</option>
				                    	</c:forEach> --%>
									</select>
								</div>
								<div style="clear:both;">
									<label>传输方式：</label>
									<input type="text" class="lengthVa input03" name="xkzd.csfs" />
								</div>
								<div>
									<label>放置地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="xkzd.fzdd" />
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='传输线路' }">
							<div class="transmissionLink project_item">
								<!--传输线路-->
								<input type="hidden" name="csxl.jkz_name" id="csxl">
								 <div class="w50">
									<label>所在监控站：</label>
									 <select  class="select02 szjkz" name="csxl.jkz_id" onchange="loadjkzname('${xmzl}',this.options[this.options.selectedIndex].text);">
										<%-- <c:forEach items="${list_jkzName }" var="item">
				                    		<option value="${item.id }">${item.xmmc }</option>
				                    	</c:forEach> --%>
									</select> 
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="csxl.jsgm" />
										<span class="dw">公里</span>
									</div>
									<span class="start">*</span>
								</div>
								<div style="clear:both;">
									<label>线路起点：</label>
									<input type="text" class="lengthRequriedVa input01" name="csxl.xlqd" />
									<span class="start">*</span>
								</div>
								<div >
									<label>线路终点：</label>
									<input type="text" class="lengthRequriedVa input01" name="csxl.xlzd" />
									<span class="start">*</span>
								</div>
								<div >
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="csxl.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>线路性质：</label><select class="select02 change_left" name="csxl.xlxz">
										<c:forEach items="${list_xlxz }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label>线路类型：</label><select class="select02 change_left" name="csxl.xllx">
										<c:forEach items="${list_xllx }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='报警装置' }">
							<div class="warningDevice project_item">
								<!--报警装置-->
								<input type="hidden" name="bjzz.jkz_name" id="bjzz">
								<div class="w50">
									<label>所在监控站：</label><select  class="select02 szjkz change_left" name="bjzz.jkz_id" onchange="loadjkzname('${xmzl}',this.options[this.options.selectedIndex].text)">
									</select>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="bjzz.jsgm" />
										<span class="dw" style="padding-left: 7px;">套</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50" style="float:right;">
									<label>设备品牌：</label>
									<div class="wRmb">
										<input type="text"  name="bjzz.sbpp" />
									</div>
								</div>
								<div class="w50" style="float:right;">
									<label>设备型号：</label>
									<div class="wRmb">
										<input type="text"  name="bjzz.sbxh" />
									</div>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='供电系统' }">
							<div class="powerSystem project_item">
								<!--供电系统-->
								<input type="hidden" name="gdxt.jkz_name" id="gdxt">
								<div class="w50">
									<!-- <label>所在监控站：</label> -->
									<label>所在监控站：</label><select class="select02 szjkz change_left" name="gdxt.jkz_id" onchange="loadjkzname('${xmzl}',this.options[this.options.selectedIndex].text)">
										<%-- <c:forEach items="${list_jkzName}" var="item">
				                    		<option value="${item.id }">${item.xmmc }</option>
				                    	</c:forEach> --%>
									</select>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="gdxt.jsgm" /><span
											class="dw" style="padding-left: 7px;">套</span>
									</div>
									<span class="start">*</span>
								</div>
								<div style="clear: both;">
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="gdxt.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='军警民联防平台' }">
							<div class="defensePlatform project_item">
								<!--军警民联防平台-->
								<div class="w50">
									<label class="ver-middle">向上联通情况：</label><select
										class="select02 ver-middle change_left" name="jjmlfpt.xsltqk">
										<c:forEach items="${list_ltqk }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label class="ver-middle">向上联通网络性质：</label><select
										class="select02 ver-middle change_left" name="jjmlfpt.xsltwlxz">
										<c:forEach items="${list_ltwlxz }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label class="ver-middle">向上传输线路：</label><select
										class="select02 ver-middle change_left" name="jjmlfpt.xscsxl">
										<c:forEach items="${list_csxl }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label class="ver-middle">横向联通情况：</label><select
										class="select02 ver-middle change_left" name="jjmlfpt.hxltqk">
										<c:forEach items="${hx_list_ltqk }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label class="ver-middle">横向联通网络性质：</label><select
										class="select02 ver-middle change_left" name="jjmlfpt.hxltwlxz">
										<c:forEach items="${hx_list_ltwlxz }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label class="ver-middle">横向传输线路：</label><select
										class="select02 ver-middle change_left" name="jjmlfpt.hxcsxl">
										<c:forEach items="${list_csxl }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>级别：</label><select class="select02 change_left" name="jjmlfpt.jb">
										<c:forEach items="${list_jb }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div>
									<label>放置地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="jjmlfpt.fzdd" />
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='无人值守电子哨兵' }">
							<div class="electronicSentinel project_item">
								<!--无人值守电子哨兵-->
								<div>
									<label>放置地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="wrzsdzsb.fzdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="wrzsdzsb.jsgm" />
										<span class="dw" style="padding-left: 7px;">套</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='执勤房' }">
							<div class="dutyHouse project_item">
								<!--执勤房-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01 change_left" name="zqf.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>执勤房类型：</label><select class="select02 change_left" name="zqf.zqflx">
										<c:forEach items="${list_zqflx }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="zqf.jsgm" />
										<span class="dw" style="padding-left: 7px;">座</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='了望塔' }">
							<div class="watchtower project_item">
								<!--了望塔-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="lwt.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="lwt.jsgm" />
										<span class="dw" style="padding-left: 7px;">座</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>了望塔类型：</label><select class="select02 change_left" name="lwt.lwtlx">
										<c:forEach items="${list_lwtlx }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='标志牌' }">
							<div class="denoter project_item">
								<!--标志牌-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="bzp.jsdd" />
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="bzp.jsgm" />
										<span class="dw" style="padding-left: 7px;">座</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>标志牌类型：</label><select class="select02 change_left" name="bzp.bzplx">
										<c:forEach items="${list_bzplx }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<%--基本信息公共字段 --%>
						<c:if test="${partTag eq '3' or partTag eq '4' }">
							<div class="w50">
								<label>建设状态：</label>
								<c:if test="${partTag eq '3'}">
									<select class="select02" name="jszt">
										<c:forEach items="${list_jszt }" var="item">
										<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${partTag eq '4'}">
									<div class="wRmb">
										<input type="text" name="jszt" class="jszt_readonly" value="已审计"/>
									</div>
								</c:if>
								<span class="start">*</span>
							</div>
							<div class="w50">
								<label>承建单位：</label>
								<div class="wRmb">
									<input type="text" class="lengthVa input03" name="cjdw" id="cjdw"/>
								</div>
							</div>
							<div class="clear" style="padding:0;"></div>
							<div class="w50">
								<label>监理单位：</label>
								<div class="wRmb">
									<input type="text" class="lengthVa input03" name="jldw" id="jldw"/>
								</div>
							</div>
							<div class="w50" id="ztbsj">
								<label>招投标时间：</label>
								<div class="wRmb">
									<input type="text"  name="ztbsj" id="ztbsj" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" />
								</div>
							</div>
							<div class="clear" style="padding:0;"></div>
							<div class="w50" id="kgsj">
								<label>开工时间：</label>
								<div class="wRmb"><%--data-beatpicker="true"--%>
									<input type="text"   name="kgsj"  id="kgsj" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"/>
								</div>
							</div>
							<div class="w50" id="jgsj">
								<label>竣工时间：</label>
								<div class="wRmb" id="jgsj">
									<input type="text"   name="jgsj"  id="jgsj" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"/>
								</div>
							</div>
							<div class="clear" style="padding:0;"></div>
							<div class="w50" id="cysj">
								<label>初验时间：</label>
								<div class="wRmb" id="cysj">
									<input type="text"   name="cysj"  id="cysj" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"/>
								</div>
							</div>
							<div class="w50" id="sjsj">
								<label>审计时间：</label>
								<div class="wRmb" id="sjsj">
									<input type="text"   name="sjsj"  id="sjsj" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"/>
								</div>
							</div>
							<div class="clear" style="padding:0;"></div>
						</c:if>
						<c:if  test="${partTag eq '4' }">
							<div class="w50">
								<label>使用状态：</label>
								<select class="select02" name="syzt">
									<c:forEach items="${list_syzt }" var="item">
									<option value="${item.codeName }">${item.codeName }</option>
									</c:forEach>
								</select>
								<span class="start">*</span>
							</div>
							<div class="clear" style="padding:0;"></div>
						</c:if>
						<div class="clear" style="padding:0;"></div>
						<c:if test="${partTag eq '2' or partTag eq '3' or partTag eq '4' }">
							<div class="project_item">
								<label style="vertical-align: top;height:30px;line-height: 30px;">计划阶段附件：</label>
								<div class="uploader-list zip-list">
									<div class="fileNameView" id="plan_file"></div>
								</div>
								<div class="upload-btn1" id="plan">上传</div>
							</div>
							<div class="clear" style="padding:0;"></div>
						</c:if>
						<c:if test="${partTag eq '3' or partTag eq '4' }">
							<div class="project_item">
								<label style="vertical-align: top;height:30px;line-height: 30px;">实施阶段附件：</label>
								<div class="uploader-list zip-list">
									<div class="fileNameView" id="implement_file"></div>
								</div>
								<div class="upload-btn1" id="implement">上传</div>
							</div>
							<div class="clear" style="padding:0;"></div>
						</c:if>
						<c:if test="${partTag eq '4' }">
							<div class="project_item">
								<label style="vertical-align: top;height:30px;line-height: 30px;">维护阶段附件：</label>
								<div class="uploader-list zip-list">
									<div class="fileNameView" id="maintenance_file"></div>
								</div>
								<div class="upload-btn1" id="maintenance">上传</div>
							</div>
						</c:if>
						<input type="hidden" class="addFj" name="fj" />
						<div class="warning zipWarning" style="padding:0;margin-left:15%;"></div>
						<div class="warea project_item">
							<label>备注：</label>
							<div class="w85">
								<textarea class="remarkText textarea01" name="bz" maxlength="600"></textarea>
							</div>
						</div>
					</form>
				</div>
				<%--修改div开始--%>
				<div id="updateInfo" class="addInfo" onkeydown="enterKey(event);">
				<form action="" id="formUpdate" name="formUpdate" method="post" class="commonForm">
					 	<input type="hidden" id="tokenId" name="token" value="${token}">
						<div class="w50 project_item">
							<label>项目类别：</label>
							<div class="wRmb">
								<input type="text" id="tjxmlb" name="xmlb" value="${xmlb}" readonly="readonly">
							</div>
							<span class="start">*</span>
						</div>
						<div class="w50 project_item">
							<label>项目子类：</label>
							<div class="wRmb">
							<input type="text" id="tjxmzl" name="xmzl" value="${xmzl}" readonly="readonly">
							</div>
							<span class="start">*</span>
						</div>
						<div class="clear" style="padding:0;"></div>
						 <div class="project_item">
							<label>项目名称：</label> 
							<input type="text" class="lengthRequriedVa input01" name="xmmc" id="xmmc" onkeyup="removeSpecialChar(this);" onblur="removeSpecialChar(this);"  onchange="xmmcChange($(this),'#formUpdate')"/>
							 <span class="start">*</span>
						</div>
						<div class="project_item" id="jsqyDiv"> 
							<label>建设区域：</label>
							<label style="position: relative;width: 26.5%;" class="wRmb">
								<c:if test="${loginUser.roleName eq '2' }">
									<input type="text" id="address1" class="sj" name="szsf" value="${loginUser.province}" style="width:100%;margin-right:1.5%;"/>
								</c:if>
								<c:if test="${loginUser.roleName eq '1' }">
									<select class="select01" id="address1" onchange='selectChecked($(this),"#formUpdate ",2,1)' name="szsf" style="width:100%;">
										<option value="0">--请选择省份--</option>
										<c:forEach items="${sfList }" var="item">
											<option value="${item.codeValue}">${item.codeName }</option>
										</c:forEach>
									</select>
								</c:if>
							</label>
							<label style="position: relative;width: 26.5%;" class="wRmb">
								<select class="select01" onchange='selectChecked($(this),"#formUpdate ",2,2)' id="address2" name="szcs" style="width:100%;">
									<option value="0">--请选择市--</option>
								</select>
							</label>
							<label style="position: relative;width: 26.5%;" class="wRmb">
								<select class="select01 lastSelect" onchange='loadJkz($("#formUpdate .szjkz"),"#formUpdate ")' id="address3" name="szq" style="width:100%;">
									<option value="0">--请选择区--</option>
								</select>
							</label>
							<span class="start">*</span>
						</div>
						<div class="multi project_item">
							<label>边界方向：</label>
							<select class="easyui-combobox" id="bj" name="bjfx" data-options="multiple:true,multiline:true" style="width:65%;">
								<c:forEach items="${bjfxList }" var="item">
									<option value="${item.codeName }" >${item.codeName }</option>
								</c:forEach>
							</select><span class="start">*</span>
						</div>
						<div class="multi project_item">
							<label>地形类别：</label>
								<select class="easyui-combobox" id="dx" name="dxlb" data-options="multiple:true,multiline:true" style="width:65%;">
								<c:forEach items="${dxlbList }" var="item">
									<option value="${item.codeName }">${item.codeName }</option>
								</c:forEach>
							</select>
							<span class="start">*</span>
						</div>
						<div class="w50 project_item">
							<label>经度：</label>
							<div class="wRmb">
								<input type="text" class="jd input03" name="jd" id="jd"/>
							</div>
						</div>
						<div class="w50 project_item">
							<label>纬度：</label>
							<div class="wRmb">
								<input type="text" class="wd input03" name="wd" id="wd"/>
							</div>
						</div>
						<div class="w50 project_item">
							<label>设施类型：</label>
							<select  class="select02" name="sslx" id="sslx">
								<c:forEach items="${sslxList }" var="item">
									<option value="${item.codeName }">${item.codeName }</option>
								</c:forEach>
							</select>
							<span class="start">*</span>
						</div>
						<div class="clear" style="padding:0;"></div>
						<div class="w50 project_item">
							<label>建设性质：</label>
							<select class="select02" name="jsxz" id="jsxz">
								<c:forEach items="${jsxzList }" var="item">
									<option value="${item.codeName }">${item.codeName }</option>
								</c:forEach>
							</select><span class="start">*</span>
						</div>
						<div class="w50 project_item">
							<label>使用单位：</label>
							<div class="wRmb">
								<input type="text" class="lengthRequriedVa input01" name="sydw" id="sydw"/>
							</div>
							<span class="start">*</span>
						</div>
						<div class="clear" style="padding:0;"></div>
						<div class="w50 project_item">
							<label>申报单位：</label>
							<div class="wRmb">
								<input type="text" class="lengthVa input03" name="sbdw" id="sbdw"/>
							</div>
						</div>
						<div class="w50 dataDiv project_item">
							<label>投资年度：</label>
							<div class="wRmb">
							<c:if test="${partTag=='2'}">
							<input type="text"  class="yearVa ndjh" name="tznd" value="${year}" id="tznd"/>
							</c:if>
							<c:if test="${partTag=='1' || partTag=='3' || partTag eq '4'}">
							<input type="text"  class="yearVa" name="tznd" id="tznd"/>
							</c:if>
								<span class="dw" style="padding-left: 7px;">年</span>
							</div>
							<span class="start">*</span>
						</div>
						<div class="clear" style="padding:0;"></div>
						<div class="w50 project_item">
							<label>中央投资：</label>
							<div class="wRmb">
								<input type="text" class="numberRequiredVa" name="zytz" id="zytz"/>
								<span class="dw">万元</span>
							</div>
							<span class="start">*</span>
						</div>
						<div class="w50 project_item">
							<label>地方投资：</label>
							<div class="wRmb">
								<input type="text" class="numberRequiredVa" name="dftz" id="dftz"/>
								<span class="dw">万元</span>
							</div>
							<span class="start">*</span>
						</div>
						<div class="clear" style="padding:0;"></div>
						<c:if test="${xmzl == '执勤道路' }">
							<div class="dutyRoad project_item">
								<!--执勤道路-->
								<div >
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="zqdl.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="zqdl.jsgm" id="jsgm"/>
										<span class="dw">公里</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>道路类别：</label>
									<select class="select02" name="zqdl.dllb" id="dllb">
										<c:forEach items="${dllbList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>道路类型：</label>
									<select class="select02" name="zqdl.dllx" id="dllx">
										<c:forEach items="${dllxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label>道路等级：</label>
									<select class="select02" name="zqdl.dldj" id="dldj">
										<c:forEach items="${dldjList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>路基类型：</label>
									<select class="select02" name="zqdl.ljlx" id="ljlx">
										<c:forEach items="${ljlxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label>路面类型：</label>
									<select class="select02" name="zqdl.lmlx" id="lmlx">
										<c:forEach items="${lmlxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='桥梁' }">
							<div class="bridge project_item">
								<!--桥梁-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="qiaoLiang.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="qiaoLiang.jsgm" id="jsgm"/>
										<span class="dw" style="padding-left: 7px;">座</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>桥梁类型：</label>
									<select class="select02" name="qiaoLiang.qllx" id="qllx">
										<c:forEach items="${qllxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>载重：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="qiaoLiang.zz" id="zz"/>
										<span class="dw" style="padding-left: 7px;">吨</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='执勤码头' }">
							<div class="wharf project_item">
								<!--执勤码头-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="zqmt.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="zqmt.jsgm" id="jsgm"/>
										<span class="dw" style="padding-left: 7px;">座</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='直升机停机坪' }">
							<div class="tarmac project_item">
								<!--直升机停机坪-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="zsjtjp.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>停机坪类型：</label><select class="select02 change_left"
										name="zsjtjp.tjplx" id="tjplx">
										<c:forEach items="${tjplxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="zsjtjp.jsgm" id="jsgm"/>
										<span class="dw" style="padding-left: 7px;">个</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl =='铁丝网'}">
							<div class="ironNet project_item">
								<!--铁丝网-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="tsw.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="tsw.jsgm" id="jsgm"/>
										<span class="dw">公里</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>铁丝网类型：</label><select class="select02 change_left" name="tsw.tswlx" id="tswlx">
										<c:forEach items="${tswlxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>拦阻门：</label>
									<div class="wRmb">
										<input type="text" class="numL" name="tsw.lzmgs" maxlength="5" id="lzmgs"/>
										<span class="dw" style="padding-left: 7px;">个</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='铁栅栏' }">
							<div class="ironFence project_item">
								<!--铁栅栏-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="tzl.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="tzl.jsgm" id="jsgm"/>
										<span class="dw">公里</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='拦阻桩' }">
							<div class="hinderStake project_item">
								<!--拦阻桩-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="lzz.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="lzz.jsgm" id="jsgm"/><span
											class="dw" style="padding-left: 7px;">套</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>拦阻桩类型：</label><select class="select02 change_left" name="lzz.lzzlx" id="lzzlx">
										<c:forEach items="${lzzlxList }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='隔离带' }">
							<div class="knifeRest project_item">
								<!--隔离带-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="gld.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="gld.jsgm" id="jsgm"/>
										<span class="dw" style="padding-left: 7px;">米</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='拒马' }">
							<div class="median project_item">
								<!--拒马-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="juma.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="juma.jsgm" id="jsgm"/>
										<span class="dw" style="padding-left: 7px;">个</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='报警线路' }">
							<div class="alarmCircuit project_item">
								<!--报警线路-->
								<div style="clear: both;">
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="bjxl.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="bjxl.jsgm" id="jsgm"/><span
											class="dw">公里</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>设备品牌：</label>
									<div class="wRmb">
										<input type="text" class="lengthVa input03" name="bjxl.sbpp" maxlength="60" id="sbpp"/>
									</div>
								</div>
								<!-- <div class="w50">
									<label>设备品牌：</label>
									<input type="text" class="lengthVa input03" name="bjxl.sbpp" />
								</div> -->
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='监控中心' }">
							<div class="surveillanceCenter project_item">
								<!--监控中心-->
								<div class="w50">
									<label class="ver-middle">向上联通情况：</label>
									<select class="select02 ver-middle" name="jkzx.xsltqk" id="xsltqk">
										<c:forEach items="${list_ltqk}" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label class="ver-middle">向上联通网络性质：</label>
									<select class="select02 ver-middle" name="jkzx.xsltwlxz" id="xsltwlxz">
										<c:forEach items="${list_ltwlxz }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label class="ver-middle">向上传输线路：</label>
									<select class="select02 ver-middle" name="jkzx.xscsxl" id="xscsxl">
										<c:forEach items="${list_csxl }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label>级别：</label><select class="select02 change_left" name="jkzx.jb" id="jb">
										<c:forEach items="${list_jb }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='监控站' }">
							<div class="monitorStation project_item">
								<!--监控站-->
								<div class="w50">
									<label class="ver-middle">向上联通情况：</label>
									<select class="select02 ver-middle" name="jkz.xsltqk" id="xsltqk">
										<c:forEach items="${list_ltqk }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label class="ver-middle">向上联通网络性质：</label>
									<select class="select02 ver-middle" name="jkz.xsltwlxz" id="xsltwlxz">
										<c:forEach items="${list_ltwlxz }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label class="ver-middle">向上传输线路：</label>
									<select class="select02 ver-middle" name="jkz.xscsxl" id="xscsxl">
										<c:forEach items="${list_csxl }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label>显控终端：</label>
									<div class="wRmb">
										<input type="text" class="numVa jkzzlgs_readonly" name="jkz.xkzdgs" maxlength="5" id="xkzdgs"/>
										<span class="dw" style="padding-left: 7px;">个</span>
									</div>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>移动前端：</label>
									<div class="wRmb">
										<input type="text" class="numVa jkzzlgs_readonly" name="jkz.ydqdgs" maxlength="5" id="ydqdgs"/>
										<span class="dw" style="padding-left: 7px;">个</span>
									</div>
								</div>
								<div class="w50">
									<label>固定前端：</label>
									<div class="wRmb">
										<input type="text" class="numVa jkzzlgs_readonly" name="jkz.gdqdgs" maxlength="5" id="gdqdgs"/>
										<span class="dw" style="padding-left: 7px;">个</span>
									</div>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='视频前端' }">
							<div class="vedioLeadingEnd project_item">
								<input type="hidden" name="spqd.jkz_name" id="spqd">
								<!--视频前端-->
								<div>
									<label>传输方式：</label>
									<input type="text" class="lengthVa input03" name="spqd.csfs" id="csfs"/>
								</div>
								<div style="clear: both;">
									<label>放置地点：</label>
									<input type="text"class="lengthRequriedVa input01" name="spqd.fzdd" id="fzdd"/>
									<span class="start">*</span>
								</div> 
								<div class="w50">
									<label>所在监控站：</label>
									<select class="select02 szjkz change_left" name="spqd.jkz_id" onchange="loadjkzname('${xmzl}',this.options[this.options.selectedIndex].text)" id="jkz_id">
										 <c:forEach items="${list_jkzName}" var="item">
				                    		<option value="${item.id}">${item.xmmc}</option>
				                    	</c:forEach> 
									</select>
								</div>
								<div class="w50">
									<label>设备类型：</label>
									<select class="select02" name="spqd.sblx" id="sblx">
										<c:forEach items="${list_spqdllx }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='显控终端' }">
							<div class="samkoonEnd project_item">
								<!--显控终端-->
								<input type="hidden" name="xkzd.jkz_name" id="xkzd">
								<div class="w50">
									<!-- <label>所在监控站：</label> -->
									<label>所在监控站：</label>
									<select  class="select02 szjkz change_left" name="xkzd.jkz_id" onchange="loadjkzname('${xmzl}',this.options[this.options.selectedIndex].text)" id="jkz_id">
										<c:forEach items="${list_jkzName }" var="item">
				                    		<option value="${item.id }">${item.xmmc }</option>
				                    	</c:forEach>
									</select>
								</div>
								<div style="clear:both;">
									<label>传输方式：</label>
									<input type="text" class="lengthVa input03" name="xkzd.csfs" id="csfs"/>
								</div>
								<div>
									<label>放置地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="xkzd.fzdd" id="fzdd"/>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='传输线路' }">
							<div class="transmissionLink project_item">
								<!--传输线路-->
								<input type="hidden" name="csxl.jkz_name" id="csxl">
								 <div class="w50">
									<label>所在监控站：</label>
									 <select  class="select02 szjkz" name="csxl.jkz_id" onchange="loadjkzname('${xmzl}',this.options[this.options.selectedIndex].text);" id="jkz_id">
										<c:forEach items="${list_jkzName }" var="item">
				                    		<option value="${item.id }">${item.xmmc }</option>
				                    	</c:forEach>
									</select> 
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numberRequiredVa" name="csxl.jsgm" id="jsgm"/>
										<span class="dw">公里</span>
									</div>
									<span class="start">*</span>
								</div>
								<div style="clear:both;">
									<label>线路起点：</label>
									<input type="text" class="lengthRequriedVa input01" name="csxl.xlqd" id="xlqd"/>
									<span class="start">*</span>
								</div>
								<div >
									<label>线路终点：</label>
									<input type="text" class="lengthRequriedVa input01" name="csxl.xlzd" id="xlzd"/>
									<span class="start">*</span>
								</div>
								<div >
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="csxl.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>线路性质：</label><select class="select02 change_left" name="csxl.xlxz" id="xlxz">
										<c:forEach items="${list_xlxz }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label>线路类型：</label><select class="select02 change_left" name="csxl.xllx" id="xllx">
										<c:forEach items="${list_xllx }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='报警装置' }">
							<div class="warningDevice project_item">
								<!--报警装置-->
								<input type="hidden" name="bjzz.jkz_name" id="bjzz">
								<div class="w50">
									<label>所在监控站：</label><select  class="select02 szjkz change_left" name="bjzz.jkz_id" onchange="loadjkzname('${xmzl}',this.options[this.options.selectedIndex].text)" id="jkz_id">
									</select>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="bjzz.jsgm" id="jsgm"/>
										<span class="dw" style="padding-left: 7px;">套</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50" style="float:right;">
									<label>设备品牌：</label>
									<div class="wRmb">
										<input type="text"  name="bjzz.sbpp" id="sbpp"/>
									</div>
								</div>
								<div class="w50" style="float:right;">
									<label>设备型号：</label>
									<div class="wRmb">
										<input type="text"  name="bjzz.sbxh" id="sbxh"/>
									</div>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='供电系统' }">
							<div class="powerSystem project_item">
								<!--供电系统-->
								<input type="hidden" name="gdxt.jkz_name" id="gdxt">
								<div class="w50">
									<!-- <label>所在监控站：</label> -->
									<label>所在监控站：</label><select class="select02 szjkz change_left" name="gdxt.jkz_id" onchange="loadjkzname('${xmzl}',this.options[this.options.selectedIndex].text)" id="jkz_id">
										<c:forEach items="${list_jkzName}" var="item">
				                    		<option value="${item.id }">${item.xmmc }</option>
				                    	</c:forEach>
									</select>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="gdxt.jsgm" id="jsgm"/><span
											class="dw" style="padding-left: 7px;">套</span>
									</div>
									<span class="start">*</span>
								</div>
								<div style="clear: both;">
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="gdxt.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='军警民联防平台' }">
							<div class="defensePlatform project_item">
								<!--军警民联防平台-->
								<div class="w50">
									<label class="ver-middle">向上联通情况：</label><select
										class="select02 ver-middle change_left" name="jjmlfpt.xsltqk" id="xsltqk">
										<c:forEach items="${list_ltqk }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label class="ver-middle">向上联通网络性质：</label><select
										class="select02 ver-middle change_left" name="jjmlfpt.xsltwlxz" id="xsltwlxz">
										<c:forEach items="${list_ltwlxz }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label class="ver-middle">向上传输线路：</label><select
										class="select02 ver-middle change_left" name="jjmlfpt.xscsxl" id="xscsxl">
										<c:forEach items="${list_csxl }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label class="ver-middle">横向联通情况：</label><select
										class="select02 ver-middle change_left" name="jjmlfpt.hxltqk" id="hxltqk">
										<c:forEach items="${hx_list_ltqk }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label class="ver-middle">横向联通网络性质：</label><select
										class="select02 ver-middle change_left" name="jjmlfpt.hxltwlxz" id="hxltwlxz">
										<c:forEach items="${hx_list_ltwlxz }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="w50">
									<label class="ver-middle">横向传输线路：</label><select
										class="select02 ver-middle change_left" name="jjmlfpt.hxcsxl" id="hxcsxl">
										<c:forEach items="${list_csxl }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div class="w50">
									<label>级别：</label><select class="select02 change_left" name="jjmlfpt.jb" id="jb">
										<c:forEach items="${list_jb }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select><span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
								<div>
									<label>放置地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="jjmlfpt.fzdd" id="fzdd"/>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='无人值守电子哨兵' }">
							<div class="electronicSentinel project_item">
								<!--无人值守电子哨兵-->
								<div>
									<label>放置地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="wrzsdzsb.fzdd" id="fzdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="wrzsdzsb.jsgm" id="jsgm"/>
										<span class="dw" style="padding-left: 7px;">套</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='执勤房' }">
							<div class="dutyHouse project_item">
								<!--执勤房-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01 change_left" name="zqf.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>执勤房类型：</label><select class="select02 change_left" name="zqf.zqflx" id="zqflx">
										<c:forEach items="${list_zqflx }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="zqf.jsgm" id="jsgm"/>
										<span class="dw" style="padding-left: 7px;">座</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='了望塔' }">
							<div class="watchtower project_item">
								<!--了望塔-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="lwt.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="lwt.jsgm" id="jsgm"/>
										<span class="dw" style="padding-left: 7px;">座</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>了望塔类型：</label><select class="select02 change_left" name="lwt.lwtlx" id="lwtlx">
										<c:forEach items="${list_lwtlx }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<c:if test="${xmzl=='标志牌' }">
							<div class="denoter project_item">
								<!--标志牌-->
								<div>
									<label>建设地点：</label>
									<input type="text" class="lengthRequriedVa input01" name="bzp.jsdd" id="jsdd"/>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>建设规模：</label>
									<div class="wRmb">
										<input type="text" class="numRequiredVa" name="bzp.jsgm" id="jsgm"/>
										<span class="dw" style="padding-left: 7px;">座</span>
									</div>
									<span class="start">*</span>
								</div>
								<div class="w50">
									<label>标志牌类型：</label><select class="select02 change_left" name="bzp.bzplx" id="bzplx">
										<c:forEach items="${list_bzplx }" var="item">
											<option value="${item.codeName }">${item.codeName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="clear" style="padding:0;"></div>
							</div>
						</c:if>
						<%--基本信息公共字段 --%>
						<c:if test="${partTag eq '3' or partTag eq '4' }">
							<div class="w50">
								<label>建设状态：</label>
								<c:if test="${partTag eq '3'}">
								<select class="select02" name="jszt" id="jszt">
									<c:forEach items="${list_jszt }" var="item">
									<option value="${item.codeName }">${item.codeName }</option>
									</c:forEach>
								</select>
								</c:if>
								<c:if test="${partTag eq '4'}">
									<div class="wRmb">
									<input type="text" name="jszt" class="jszt_readonly" value="已审计"/>
									</div>
								</c:if>
								<span class="start">*</span>
							</div>
							<div class="w50">
								<label>承建单位：</label>
								<div class="wRmb">
									<input type="text" class="lengthVa input03" name="cjdw" id="cjdw"/>
								</div>
							</div>
							<div class="clear" style="padding:0;"></div>
							<div class="w50">
								<label>监理单位：</label>
								<div class="wRmb">
									<input type="text" class="lengthVa input03" name="jldw" id="jldw"/>
								</div>
							</div>
							<div class="w50" id="ztbsj">
								<label>招投标时间：</label>
								<div class="wRmb">
									<input type="text"  id="ztbsj" name="ztbsj" class="easyui-datebox"  />
								</div>
							</div>
							<div class="clear" style="padding:0;"></div>
							<div class="w50" id="kgsj">
								<label>开工时间：</label>
								<div class="wRmb">
									<input type="text"  id="kgsj" name="kgsj"   class="easyui-datebox" />
								</div>
							</div>
							<div class="w50" id="jgsj">
								<label>竣工时间：</label>
								<div class="wRmb">
									<input type="text" id="jgsj"   name="jgsj"   class="easyui-datebox" />
								</div>
							</div>
							<div class="clear" style="padding:0;"></div>
							<div class="w50" id="cysj">
								<label>初验时间：</label>
								<div class="wRmb">
									<input type="text"  id="cysj" name="cysj"   class="easyui-datebox" />
								</div>
							</div>
							<div class="w50" id="sjsj">
								<label>审计时间：</label>
								<div class="wRmb" >
									<input type="text"  id="sjsj" name="sjsj"   class="easyui-datebox" />
								</div>
							</div>
							<div class="clear" style="padding:0;"></div>
						</c:if>
						<c:if  test="${partTag eq '4' }">
							<div class="w50">
								<label>使用状态：</label>
								<select class="select02" name="syzt" id="syzt">
									<c:forEach items="${list_syzt }" var="item">
									<option value="${item.codeName }">${item.codeName }</option>
									</c:forEach>
								</select>
								<span class="start">*</span>
							</div>
							<div class="clear" style="padding:0;"></div>
						</c:if>
						<div class="clear" style="padding:0;"></div>
						<c:if test="${partTag eq '2' or partTag eq '3' or partTag eq '4'}">
							<div class="project_item">
								<label style="vertical-align: top;height:30px;line-height: 30px;">计划阶段附件：</label>
								<div class="uploader-list zip-list">
									<div class="fileNameView" id="plan_file"></div>
								</div>
								<div class="upload-btn1" id="plan">上传</div>
							</div>
						</c:if>
						<c:if test="${partTag eq '3' or partTag eq '4'}">
							<div class="project_item">
								<label style="vertical-align: top;height:30px;line-height: 30px;">实施阶段附件：</label>
								<div class="uploader-list zip-list">
									<div class="fileNameView" id="implement_file"></div>
								</div>
								<div class="upload-btn1" id="implement">上传</div>
							</div>
						</c:if>
						<c:if test="${partTag eq '4'}">
							<div class="project_item">
								<label style="vertical-align: top;height:30px;line-height: 30px;">维护阶段附件：</label>
								<div class="uploader-list zip-list">
									<div class="fileNameView" id="maintenance_file"></div>
								</div>
								<div class="upload-btn1" id="maintenance">上传</div>
							</div>
						</c:if>
						<input type="hidden" class="editFj" name="fj"/>
						<div class="warning zipWarning" style="padding:0;margin-left:15%;"></div>
						<div class="warea project_item">
							<label>备注：</label>
							<div class="w85">
								<textarea class="remarkText textarea01" name="bz" maxlength="600" id="bz"></textarea>
							</div>
						</div>
					</form>
				</div>
				<%--修改div结束 --%>
			</div>
		</div>
		<div data-options="region:'south',border:false" style="text-align:right;padding: 30px 65px;" class="buttonDiv">
			<button type="button" class="btn cancal_btn close">关闭</button>
			<button type="button" class="btn confirm" id="addSubmit">确定</button>
			<button type="button" class="btn cancal_btn cancal" id="tab_reset">取消</button>
		</div>
	</div>
	<div id="removeDlg" class="easyui-dialog"
		data-options="modal:true,closed:true" title=" "
		style="width:500px;display:none;">
		<div data-options="region:'center'" style="padding:25px 40px;"
			class="dialog_center">
			<p class="removeOne" style="text-align: center;font-size: 1rem;">是否确定删除已选项目？</p>
			<p class="removeTwo" style="text-align: center;font-size: 1rem;">请选择删除的项目！</p>
		</div>
		<div data-options="region:'south',border:false" class="dialog_button"
			style="text-align:center;padding: 10px;">
			<!-- 隐藏的form表单，用于提交删除项目参数 -->
			<form action="xmzl/delete" name="deleteFrom" id="deleteFrom" method="post">
				<input type="hidden" id="szsf" name="szsf" value="${szsf}"/>
				<input type="hidden" id="xmzl" name="xmzl" value="${xmzl}"/>
				<input type="hidden" id="xmlb" name="xmlb" value="${xmlb}"/>
				<input type="hidden" id="xmlb" name="partTag" value="${partTag }"/>
				<input type="hidden" id="deleteId" name="ids"/>
			</form>
			<button type="button" class="btn" id="removeSubmit">确定</button>
			<button type="button" class="btn cancal_btn">取消</button>
		</div>
	</div>
	
	<div id="toImplementDlg" class="easyui-dialog"
		data-options="modal:true,closed:true" title=" "
		style="width:500px;display:none;">
		<div data-options="region:'center'" style="padding:25px 40px;"
			class="dialog_center">
			<p class="toImplementOne" style="text-align: center;font-size: 1rem;">是否确定将已选项目转入实施？</p>
			<p class="toImplementTwo" style="text-align: center;font-size: 1rem;">请选择需要转入实施的项目！</p>
			<p class="toImplementThree" style="text-align: center;font-size: 1rem;"></p>
		</div>
		<div data-options="region:'south',border:false" class="dialog_button"
			style="text-align:center;padding: 10px;">
			<!-- 隐藏的form表单，用于提交转入实施的参数 -->
			<form action="xmzl/toImplement" name="toImplementForm" id="toImplementForm" method="post">
				<input type="hidden" id="szsf" name="szsf" value="${szsf}"/>
				<input type="hidden" id="xmzl" name="xmzl" value="${xmzl}"/>
				<input type="hidden" id="xmlb" name="xmlb" value="${xmlb}"/>
				<input type="hidden" id="toImplementId" name="toImplementId"/>
			</form>
			<button type="button" class="btn" id="toImplementSubmit">确定</button>
			<button type="button" class="btn cancal_btn">取消</button>
		</div>
	</div>
	
	<%--转入使用维护对话框--%>
	<div id="toUseMaintenanceDlg" class="easyui-dialog"
		data-options="modal:true,closed:true" title=" "
		style="width:500px;display:none;">
		<div data-options="region:'center'" style="padding-top:20px;" class="dialog_center">
			<p class="toUseTip" style="text-align: center;font-size: 1rem;"></p>
		</div>
		<div data-options="region:'south',border:false" class="dialog_button"
			style="text-align:center;padding: 20px 0px;">
			<!-- 隐藏的form表单，用于提交转入实施的参数 -->
			<form action="implement/toUseMaintenance" name="toImplementForm" id="toUseForm" method="post">
				<input type="hidden" id="szsf" name="szsf" value="${szsf}"/>
				<input type="hidden" id="xmzl" name="xmzl" value="${xmzl}"/>
				<input type="hidden" id="xmlb" name="xmlb" value="${xmlb}"/>
				<input type="hidden" id="partTag" name="partTag" value="${partTag}">
				<input type="hidden" id="toUseId" name="toUseId">
			</form>
			<button type="button" class="btn" id="toUseSubmit">确定</button>
			<button type="button" class="btn cancal_btn">取消</button>
		</div>
	</div>
	
	<div id="importFile" class="easyui-dialog"
		data-options="modal:true,closed:true" title=" "
		style="width: 500px;display:none;">
		<div data-options="region:'center'" style="padding:25px 40px;"
			class="dialog_center">
			<div class="scrollBlock" style="max-height:300px;">
				<div id="fileList" class="uploader-list">
					<div class="fileNameView"></div>
				</div>
			</div>
			<div id="filePicker">选择文件</div>
			<div class="clear"></div>
			<div id="fileWarning" class="warning"></div>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align:right;padding: 20px 40px 50px;">
			<button type="button" class="btn" id="uploadBtn">上传</button>
			<button type="button" class="btn cancal_btn">取消</button>
		</div>
	</div>
	<div id="projectResult" class="easyui-dialog"
		data-options="modal:true,closed:true" title=" "
		style="width: 500px;display:none;">
		<div data-options="region:'center'"
			style="padding:60px 70px 15px 150px;background: url('img/warn.png') no-repeat left 72px top 64px;"
			class="dialog_center">
			<p style="font-size: 1.1em;line-height: 30px;">因部分项目不符合转入项目建设实施条件无法转入项目实施。</p>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align:right;padding: 20px 40px 50px;">
			<button type="button" class="btn cancal_btn" id="">确定</button>
		</div>
	</div>
	<div id="warning" class="easyui-dialog"
		data-options="modal:true,closed:true" title=" "
		style="width:500px;display:none;">
		<div data-options="region:'center'" style="padding:25px 40px;"
			class="dialog_center">
			<p class="editOne" style="text-align: center;font-size: 1rem;">？</p>
		</div>
	</div>
	<div id="downloadDlg" class="easyui-dialog"
		data-options="modal:true,closed:true" title=" "
		style="width:500px;display:none;">
		<div data-options="region:'center'" style="padding:25px 40px;"
			class="dialog_center">
			<p class="removeOne" style="text-align: center;font-size: 1rem;">至少有一个附件没有，是否继续下载？</p>
		</div>
		<div data-options="region:'south',border:false" class="dialog_button"
			style="text-align:right;padding: 30px 40px;">
			<form name="downloadForm" method="post" action="">
				<input type="submit" class="btn" value="确定" />
				<input type="button" class="btn" value="取消" onclick="closeDownLoad();"/>
			</form>
		</div>
	</div>
	<%--使用维护:维护记录部分起 --%>
	<div id="xmwhjlQueryDlg" class="easyui-dialog" data-options="modal:true,closed:true" title=" " style="width:80%;display:none;">
		<div data-options="region:'center'" style="padding:10px 30px 10px 30px;" class="dialog_center">
			<div class="scrollBlock" id="xmwhjlScrollForm" style="height: auto;overflow: auto;">
				<div class="xmwhjl" id="xmwhjlDiv"></div>
			</div>
		</div>
	</div>
	
	<div id="importWhjlFile" class="easyui-dialog" data-options="modal:true,closed:true" title="导入维护记录"
		style="width: 500px;display:none;">
		<div data-options="region:'center'" style="padding:25px 40px;"class="dialog_center">
			<div class="scrollBlock" style="height: auto;">
				<div id="whjlFileList" class="uploader-list">
					<div class="fileNameView"></div>
				</div>
			</div>
			<div id="whjlFilePicker">选择文件</div>
			<div class="clear"></div>
			<div id="whjlFileWarning" class="warning"></div>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align:right;padding: 20px 40px 50px;">
			<button type="button" class="btn" id="whjlUploadBtn">上传</button>
			<button type="button" class="btn cancal_btn">取消</button>
		</div>
	</div>
	
	<div id="importWhxmFile" class="easyui-dialog"
		data-options="modal:true,closed:true" title="导入维护项目"
		style="width: 500px;display:none;">
		<div data-options="region:'center'" style="padding:25px 40px;"
			class="dialog_center">
			<div class="scrollBlock" style="max-height:300px;">
				<div id="whxmFileList" class="uploader-list">
					<div class="fileNameView"></div>
				</div>
			</div>
			<div id="whxmFilePicker">选择文件</div>
			<div class="clear"></div>
			<div id="whxmFileWarning" class="warning"></div>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align:right;padding: 20px 40px 50px;">
			<button type="button" class="btn" id="whxmUploadBtn">上传</button>
			<button type="button" class="btn cancal_btn">取消</button>
		</div>
	</div>
	<%--使用维护:维护记录部分止  --%>
	<!-- 监控站下面的视频前端、显控终端 -->
	<%--使用维护:维护记录部分起 --%>
	
	<div id="jkz_zlDlg" class="easyui-dialog" data-options="modal:true,closed:true" title=" " style="width:80%;display:none;">
		<div data-options="region:'center'" style="padding:10px 30px 10px 30px;" class="dialog_center">
			<div id="jkz_zlQueryDiv">
			</div>
		</div>
	</div>
	
</body>
<!--分页js-->
<script src="js/page.js"></script>
</html>
