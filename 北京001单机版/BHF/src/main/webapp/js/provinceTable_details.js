/**
 * Created by LJDY576 on 2016/12/16.
 */
var xmzl;
var local = window.location;
var contextPath = local.pathname.split("/")[1];
var basePath = local.protocol + "//" + local.host + "/" + contextPath;
$(function(){
	xmzl=$("#xmzl").val();
	menuHeight();
	//表格获取焦点的颜色
	 $(".wRmb input").focus(function(){
	        $(this).parent().addClass("focused");
	    });
	 $(".wRmb input").blur(function(){
	        $(this).parent().removeClass("focused");
	 });
	 unitChange();
    //点击操作按钮事件
    $(".tab_title button").click(function(){
    	var ids = new Array();
    	//var bzm = '';
    	var editId;
    	$("#tbody tr").find("input").each(function(){
    		if($(this).prop("checked")){
    			ids.push($(this).val());
    			editId = $(this).attr("id");
    		}
    	});
    	if($(this).text() != "导出维护记录"){
    		$(this).addClass("current");
    	}
        $(".easyui-dialog").panel({title:$(this).text()});
        $('.zip-list').find(".file-item,.error").remove();//移除已经上传的文件
        $(".zip-list .fileNameView").show();//默认显示上传框
        if($(this).text()=="添加项目"){
        	var bjfx0 = $("#addInfo").find("[comboname='bjfx'] option").eq(0).text();
        	var dxlb0 = $("#addInfo").find("[comboname='dxlb'] option").eq(0).text();
        	$("#addInfo").find("[comboname='bjfx']").combobox('setValues', bjfx0);
        	$("#addInfo").find("[comboname='dxlb']").combobox('setValues', dxlb0);
        	$("#addInfo").show();//添加项目弹框显示
        	$("#updateInfo").hide();//隐藏修盖项目弹框
        	$(".buttonDiv").show();//显示按钮div
        	$(".buttonDiv .close").hide();
        	$(".buttonDiv .cancal,.buttonDiv .confirm").show();
        	//classify($("#xmzl").val());
        	//classify($("#tjxmzl").val());//修改 by 郎川
            $("#addDlg").show().dialog("open");
            $(this).addClass("current");
            //initUpload();
        	initElement();
        	openForm();
        	if($("#role").val()=='2'){
        		$(".sj").attr("readonly",true);
        	}
        	$("#tjxmlb").attr("readonly",true);
        	$("#tjxmzl").attr("readonly",true);
        	//限定项目类别和项目子类的选项
        	selectChecked($("#formAdd #xmlb"),"#formAdd ",1);
        	$("#formAdd #xmlb,#formAdd #tjXmxl").attr("disabled",true);
        	
        	$("#addSubmit").on("click",function(){
   			 	$(".tips").remove();//移除所有的提示
   			 	$(".jsqyTips").remove();
   		        var success = formValidate().form();
   		        if(!success){
   		        	return false;
   		        }
   		        var jsqy_pass = validateJsqy("#formAdd");//建设区域验证
   		        if(!jsqy_pass){
   		        	return false;
   		        }
   		        if(partTag=='3' || partTag == '4'){
   		        	//时间大小校验
   		        	var valiDate=validateDate("#formAdd");
   		        	if(!valiDate){
   		        		return false;
   		        	}
   		        }
   		        if(success){
   		            var multFlag = eachMulti($("#addInfo .easyui-combobox"));
   		            if(multFlag){
   		                var dataFlag = eachDatabox($(".dataDiv .textbox-value"));
   		                if(dataFlag){
   		                	//关闭当前页面，防止多次提交产生多条数据
   		                	$(this).parents(".easyui-dialog").dialog('close');
   		                	$.ajax({
		                		url:"xmzl/add?partTag="+partTag,
		                		type:"post",
		        				data:$("#formAdd").serialize(),
		        				dataType:"json",
		        				success:function(data){
		        					if(data!=null){
		        						$("#postForm").empty();
	        				        	var f=$("#postForm")[0];
	        				            f.action='xmzl/loadDataTable';
	        				            f.innerHTML='<input type="hidden" name="xmlb" value="'+data.xmlb+'"/>'
	        				            +'<input type="hidden" name="xmzl" value="'+data.xmzl+'"/>'
	        				            +'<input type="hidden" name="szsf" value="'+data.szsf+'"/>'
	        				            +'<input type="hidden" name="szcs" value="'+data.szcs+'"/>'
	        				            +'<input type="hidden" name="methodTag" value="'+data.methodTag+'"/>'
	        				            +'<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
	        				            f.submit();
		        					}
		        				}
		                	})
   		                }
   		            }
   		        }
        	});
        }else if($(this).text()=="删除项目"){
        	
        	if(ids == null || ids.length == 0){
        		var errorTip ="请选择要删除的项目";
        	}else{
        		$("#removeDlg").dialog("open");
        		$(this).addClass("current");
        	}
        	if(ids == null || ids.length == 0){
        		$(".removeOne").hide();
                $(".removeTwo").show();
                $(".dialog_button").hide();
            }else{
                $(".removeOne").show();
                $(".removeTwo").hide();
                $(".dialog_button").show();
            }
            $("#removeDlg").show().dialog("open");
            $("#deleteId").val(ids);
            document.deleteFrom.action='xmzl/delete';
        }else if($(this).text()=="修改项目"){
        	if(ids == null || ids.length == 0){
        		$("#warning").find(".editOne").text("请选择要修改的项目！");
        		$("#warning").show().dialog("open");
        	}else if(ids.length > 1){
        		$("#warning").find(".editOne").text("每次只能修改一条项目信息，请重新选择!");
        		$("#warning").show().dialog("open");
        	}else if(ids.length==1){
        		xmzl=$("#xmzl").val();
        		//$("#updateInfo").empty();
        		$("#addInfo").hide();
        		$("#updateInfo").show();
        		$(".buttonDiv").show();
        		$(".buttonDiv .close").hide();
        		$(".buttonDiv .cancal,.buttonDiv .confirm").show();
        		bzm = ids[0];
        	    $("#addDlg").show().dialog("open");
        	    $(this).addClass("current");
        	    initElement();
        		openForm();
        		$("#formUpdate #tjxmlb").attr("readonly",true);
        		$("#formUpdate #tjxmzl").attr("readonly",true);
                $.ajax({
                	url:"xmzl/loadDetail",
     				type:"post",
     				data:"ids="+bzm+"&xmzl="+xmzl,
     				dataType:"json",
     				success:function(data){
     					if(data!=null){
     						//读取模板
     						/*var tpl = document.getElementById("tpl").innerHTML;
     						//获取所在省的信息
     						//juicer 编译模板并渲染数据
     						var html = juicer(tpl,{data:data,partTag:partTag});
     						//将编译好模板放入指定位置
     						document.getElementById("updateInfo").innerHTML=html;*/
     						 //加载市、区数据
                            loadCity(data,"#formUpdate");
                            loadQu(data,"#formUpdate");
     						$("#updateInfo #xmmc").val(data.xmmc);//项目名称
     						if(roleName=='1'){
     							for(var i=0;i<$("#updateInfo #address1 option").length;i++){
     								if($("#updateInfo #address1 option")[i].text==data.szsf){
     									$("#updateInfo #address1 option")[i].selected=true;
     								}
     							}
     						}
     						/*for(var i=0;i<$("#updateInfo #address2 option").length;i++){//所在城市
     							if($("#updateInfo #address2 option")[i].text==data.szcs){
     								$("#updateInfo #address2 option")[i].selected=true;
     							}
     						}*/
     						/*$("#updateInfo #address3").append("<option value='"+data.szq+"' selected>"+data.szq+"</option>");*/
     						/*for(var i=0;i<$("#updateInfo #address3 option").length;i++){//所在区
     							if($("#updateInfo #address3 option")[i].text==data.szq){
     								$("#updateInfo #daddress3 option")[i].selected=true;
     							}
     						}*/
     						$("#updateInfo #bj").combobox("setValues",data.bjfx.split(","));//边界方向
 							$("#updateInfo #dx").combobox("setValues",data.dxlb.split(","));//地方类别
 							for(var i=0;i<$("#updateInfo #sslx option").length;i++){//设施类型
 								if($("#updateInfo #sslx option")[i].text==data.sslx){
 									$("#updateInfo #sslx option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #jsxz option").length;i++){//建设性质
 								if($("#updateInfo #jsxz option")[i].text==data.jsxz){
 									$("#updateInfo #jsxz option")[i].selected=true;
 								}
 							}
     						$("#updateInfo #jd").val(data.jd);//经度
     						$("#updateInfo #wd").val(data.wd);//纬度
     						$("#updateInfo #sydw").val(data.sydw);//使用单位
     						$("#updateInfo #sbdw").val(data.sbdw);//申报单位
     						$("#updateInfo #tznd").val(data.tznd);//投资年度
     						$("#updateInfo #zytz").val(data.zytz);//中央投资
     						$("#updateInfo #dftz").val(data.dftz);//地方投资
     						$("#updateInfo #bz").html(data.bz);//备注
     						if(update_xmzl == '执勤道路'){
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     							for(var i=0;i<$("#updateInfo #dllb option").length;i++){//道路类别
     								if($("#updateInfo #dllb option")[i].text==data.dllb){
     									$("#updateInfo #dllb option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #dllx option").length;i++){//道路类型
     								if($("#updateInfo #dllx option")[i].text==data.dllx){
     									$("#updateInfo #dllx option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #dldj option").length;i++){//道路等级
     								if($("#updateInfo #dldj option")[i].text==data.dldj){
     									$("#updateInfo #dldj option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #ljlx option").length;i++){//路基类型
     								if($("#updateInfo #ljlx option")[i].text==data.ljlx){
     									$("#updateInfo #ljlx option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #lmlx option").length;i++){//路面类型
     								if($("#updateInfo #lmlx option")[i].text==data.lmlx){
     									$("#updateInfo #lmlx option")[i].selected=true;
     								}
     							}
     						}else if(update_xmzl=='桥梁'){
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     							for(var i=0;i<$("#updateInfo #qllx option").length;i++){//桥梁类型
     								if($("#updateInfo #qllx option")[i].text==data.qllx){
     									$("#updateInfo #qllx option")[i].selected=true;
     								}
     							}
     							$("#updateInfo #zz").val(data.zz);//载重
     						}else if(update_xmzl=='执勤码头'){
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     						}else if(update_xmzl=='直升机停机坪'){
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							for(var i=0;i<$("#updateInfo #tjplx option").length;i++){//停机坪类型
     								if($("#updateInfo #tjplx option")[i].text==data.tjplx){
     									$("#updateInfo #tjplx option")[i].selected=true;
     								}
     							}
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     						}else if(update_xmzl=='铁丝网'){
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     							for(var i=0;i<$("#updateInfo #tswlx option").length;i++){//铁丝网类型
     								if($("#updateInfo #tswlx option")[i].text==data.tswlx){
     									$("#updateInfo #tswlx option")[i].selected=true;
     								}
     							}
     							$("#updateInfo #lzmgs").val(data.lzmgs);//拦阻门个数
     						}else if(update_xmzl=='铁栅栏'){
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     						}else if(update_xmzl=='拦阻桩'){
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     							for(var i=0;i<$("#updateInfo #lzzlx option").length;i++){//拦阻桩类型
     								if($("#updateInfo #lzzlx option")[i].text==data.lzzlx){
     									$("#updateInfo #lzzlx option")[i].selected=true;
     								}
     							}
     						}else if(update_xmzl=='隔离带'){
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     						}else if(update_xmzl=='拒马'){
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     						}else if(update_xmzl=='报警线路'){
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     							$("#updateInfo #sbpp").val(data.sbpp);//设备品牌
     						}else if(update_xmzl=='监控中心'){
     							for(var i=0;i<$("#updateInfo #xsltqk option").length;i++){//向上联通情况
     								if($("#updateInfo #xsltqk option")[i].text==data.xsltqk){
     									$("#updateInfo #xsltqk option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #xsltwlxz option").length;i++){//向上联通网络性质
     								if($("#updateInfo #xsltwlxz option")[i].text==data.xsltwlxz){
     									$("#updateInfo #xsltwlxz option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #xscsxl option").length;i++){//向上传输线路
     								if($("#updateInfo #xscsxl option")[i].text==data.xscsxl){
     									$("#updateInfo #xscsxl option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #jb option").length;i++){//级别
     								if($("#updateInfo #jb option")[i].text==data.jb){
     									$("#updateInfo #jb option")[i].selected=true;
     								}
     							}
     						}else if(update_xmzl=='监控站'){
     							for(var i=0;i<$("#updateInfo #xsltqk option").length;i++){//向上联通情况
     								if($("#updateInfo #xsltqk option")[i].text==data.xsltqk){
     									$("#updateInfo #xsltqk option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #xsltwlxz option").length;i++){//向上联通网络性质
     								if($("#updateInfo #xsltwlxz option")[i].text==data.xsltwlxz){
     									$("#updateInfo #xsltwlxz option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #xscsxl option").length;i++){//向上传输线路
     								if($("#updateInfo #xscsxl option")[i].text==data.xscsxl){
     									$("#updateInfo #xscsxl option")[i].selected=true;
     								}
     							}
     							$("#updateInfo #xkzdgs").val(data.xkzdgs);//显控终端
     							$("#updateInfo #ydqdgs").val(data.ydqdgs);//移动前端
     							$("#updateInfo #gdqdgs").val(data.gdqdgs);//固定前端
     						}else if(update_xmzl=='视频前端'){
     							$("#updateInfo #csfs").val(data.csfs);//传输方式
     							$("#updateInfo #fzdd").val(data.fzdd);//放置地点
     							for(var i=0;i<$("#updateInfo #jkz_id option").length;i++){//所在监控站
     								if($("#updateInfo #jkz_id option")[i].jkz_id==data.jkz_id){
     									$("#updateInfo #jkz_id option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #sblx option").length;i++){//设备类型
     								if($("#updateInfo #sblx option")[i].text==data.sblx){
     									$("#updateInfo #sblx option")[i].selected=true;
     								}
     							}
     						}else if(update_xmzl=='显控终端'){
     							for(var i=0;i<$("#updateInfo #jkz_id option").length;i++){//所在监控站
     								if($("#updateInfo #jkz_id option")[i].jkz_id==data.jkz_id){
     									$("#updateInfo #jkz_id option")[i].selected=true;
     								}
     							}
     							$("#updateInfo #csfs").val(data.csfs);//传输方式
     							$("#updateInfo #fzdd").val(data.fzdd);//放置地点
     						}else if(update_xmzl=='传输线路'){
     							for(var i=0;i<$("#updateInfo #jkz_id option").length;i++){//所在监控站
     								if($("#updateInfo #jkz_id option")[i].jkz_id==data.jkz_id){
     									$("#updateInfo #jkz_id option")[i].selected=true;
     								}
     							}
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     							$("#updateInfo #xlqd").val(data.xlqd);//线路起点
     							$("#updateInfo #xlzd").val(data.xlzd);//线路终点
     							$("#updateInfo #jsdd").val(data.jsdd);//建设终点
     							for(var i=0;i<$("#updateInfo #xlxz option").length;i++){//线路性质
     								if($("#updateInfo #xlxz option")[i].text==data.xlxz){
     									$("#updateInfo #xlxz option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #xllx option").length;i++){//线路类型
     								if($("#updateInfo #xllx option")[i].text==data.xllx){
     									$("#updateInfo #xllx option")[i].selected=true;
     								}
     							}
     							
     						}else if(update_xmzl=='报警装置'){
     							for(var i=0;i<$("#updateInfo #jkz_id option").length;i++){//所在监控站
     								if($("#updateInfo #jkz_id option")[i].jkz_id==data.jkz_id){
     									$("#updateInfo #jkz_id option")[i].selected=true;
     								}
     							}
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     							$("#updateInfo #sbpp").val(data.sbpp);//设备品牌
     							$("#updateInfo #sbxh").val(data.sbxh);//设备型号
     						}else if(update_xmzl=='供电系统'){
     							for(var i=0;i<$("#updateInfo #jkz_id option").length;i++){//所在监控站
     								if($("#updateInfo #jkz_id option")[i].jkz_id==data.jkz_id){
     									$("#updateInfo #jkz_id option")[i].selected=true;
     								}
     							}
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							
     						}else if(update_xmzl=='军警民联防平台'){
     							for(var i=0;i<$("#updateInfo #xsltqk option").length;i++){//向上联通情况
     								if($("#updateInfo #xsltqk option")[i].text==data.xsltqk){
     									$("#updateInfo #xsltqk option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #xsltwlxz option").length;i++){//向上联通网络性质
     								if($("#updateInfo #xsltwlxz option")[i].text==data.xsltwlxz){
     									$("#updateInfo #xsltwlxz option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #xscsxl option").length;i++){//向上传输线路
     								if($("#updateInfo #xscsxl option")[i].text==data.xscsxl){
     									$("#updateInfo #xscsxl option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #hxltqk option").length;i++){//横向联通情况
     								if($("#updateInfo #hxltqk option")[i].text==data.hxltqk){
     									$("#updateInfo #hxltqk option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #hxltwlxz option").length;i++){//横向联通网络性质
     								if($("#updateInfo #hxltwlxz option")[i].text==data.hxltwlxz){
     									$("#updateInfo #hxltwlxz option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #hxcsxl option").length;i++){//横向传输线路
     								if($("#updateInfo #hxcsxl option")[i].text==data.hxcsxl){
     									$("#updateInfo #hxcsxl option")[i].selected=true;
     								}
     							}
     							for(var i=0;i<$("#updateInfo #jb option").length;i++){//级别
     								if($("#updateInfo #jb option")[i].text==data.jb){
     									$("#updateInfo #jb option")[i].selected=true;
     								}
     							}
     							$("#updateInfo #fzdd").val(data.fzdd);//放置地点
     						}else if(update_xmzl=='无人值守电子哨兵'){
     							$("#updateInfo #fzdd").val(data.fzdd);//放置地点
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     						}else if(update_xmzl=='执勤房'){
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							for(var i=0;i<$("#updateInfo #zqflx option").length;i++){//执勤房类型
     								if($("#updateInfo #zqflx option")[i].text==data.zqflx){
     									$("#updateInfo #zqflx option")[i].selected=true;
     								}
     							}
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     							
     						}else if(update_xmzl=='了望塔'){
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     							for(var i=0;i<$("#updateInfo #lwtlx option").length;i++){//瞭望塔类型
     								if($("#updateInfo #lwtlx option")[i].text==data.lwtlx){
     									$("#updateInfo #lwtlx option")[i].selected=true;
     								}
     							}
     						}else if(update_xmzl=='标志牌'){
     							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
     							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
     							for(var i=0;i<$("#updateInfo #bzplx option").length;i++){//标志牌类型
     								if($("#updateInfo #bzplx option")[i].text==data.bzplx){
     									$("#updateInfo #bzplx option")[i].selected=true;
     								}
     							}
     						}
     						//基本信息公共字段
     						if(partTag=='3' || partTag =='4'){
     							for(var i=0;i<$("#updateInfo #jszt option").length;i++){//建设状态
     								if($("#updateInfo #jszt option")[i].text==data.jszt){
     									$("#updateInfo #jszt option")[i].selected=true;
     								}
     							}
     							$("#updateInfo #cjdw").val(data.cjdw);//承建单位
     							$("#updateInfo #jldw").val(data.jldw);//承建单位
     							$("#updateInfo input#ztbsj").datebox('setValue',formatDate(data.ztbsj));//招投标时间
         						$("#updateInfo input#kgsj").datebox('setValue',formatDate(data.kgsj));//开工时间
         						$("#updateInfo input#jgsj").datebox('setValue',formatDate(data.jgsj));//竣工时间
         						$("#updateInfo input#cysj").datebox('setValue',formatDate(data.cysj));//初验时间
         						$("#updateInfo input#sjsj").datebox('setValue',formatDate(data.sjsj));//审计时间
     						}
     						if(partTag == '4'){
     							for(var i=0;i<$("#updateInfo #syzt option").length;i++){//建设状态
     								if($("#updateInfo #syzt option")[i].text==data.syzt){
     									$("#updateInfo #syzt option")[i].selected=true;
     								}
     							}
     						}
     						$("#formUpdate .editFj").val(data.fj);
     						
    						//加载所在监控站数据
    						if(xmzl=="视频前端" || xmzl=="显控终端" || xmzl=="传输线路" || xmzl=="报警装置" || xmzl=="供电系统"){
    							loadJkz($("#formUpdate .szjkz"),"#formUpdate ",data.jkz_id);
    						}
    						//限定项目类别和项目子类
    						$("#formUpdate #xmlb,#formUpdate #xgxmzl").attr("disabled",true);
     						//初始化多选框插件
     						//$(".easyui-combobox").combobox();					
     						initUpload(data.xmmc,data.szsf,"#formUpdate");
     		        		initElement();
     		        		var bjData = data.bjfx.split(",");
     		        		var dxData = data.dxlb.split(",");
     		        		for(var i=0;i<bjData.length;i++){
     		        			bjData[i]=$.trim(bjData[i]);
     		        		}
     		        		for(var i=0;i<dxData.length;i++){
     		        			dxData[i]=$.trim(dxData[i]);
     		        		}
     		        		$("#formUpdate #bj").combobox("setValues",bjData);//边界方向多选赋值
     		        		$("#formUpdate #dx").combobox("setValues",dxData);//地形类型多选赋值
     		        		classify($("#xmzl").val());
     		        		showFj(data.fj);
     		        		deleteNull();
     					}
     				}
             	});
        		$("#addSubmit").on("click",function(){
        			 $(".tips").remove();//移除所有的提示
        			 $(".jsqyTips").remove();
        		        var success = formUpdateValidate().form();
        		        if(!success){
           		        	return false;
           		        }
           		        var jsqy_pass = validateJsqy("#formUpdate");//建设区域验证
           		        if(!jsqy_pass){
           		        	return false;
           		        }
           		        if(partTag == '3' || partTag == '4'){
           		        	//修改时间校验
           		        	var update_date=validateDate("#formUpdate ");
           		        	if(!update_date){
           		        		return false;
           		        	}
           		        }
        		        if(success){
        		            var multFlag = eachMulti($("#updateInfo .easyui-combobox"));
        		            if(multFlag){
        		                var dataFlag = eachDatabox($(".dataDiv .textbox-value"));
        		                if(dataFlag){
        		                	$.ajax({
        		                		url:"xmzl/update?ids="+ids[0]+"&partTag="+partTag,
        		                		type:"post",
        		        				data:$("#formUpdate").serialize(),
        		        				dataType:"json",
        		        				success:function(data){
        		        					if(data!=null){
        		        						$("#postForm").empty();
    		        				        	var f=$("#postForm")[0];
    		        				            f.action='xmzl/loadDataTable';
    		        				            f.innerHTML='<input type="hidden" name="xmlb" value="'+data.xmlb+'"/>'
    		        				            +'<input type="hidden" name="xmzl" value="'+data.xmzl+'"/>'
    		        				            +'<input type="hidden" name="szsf" value="'+data.szsf+'"/>'
    		        				            +'<input type="hidden" name="szcs" value="'+data.szcs+'"/>'
    		        				            +'<input type="hidden" name="methodTag" value="'+data.methodTag+'"/>'
    		        				            +'<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
    		        				            f.submit();
        		        					}
        		        				}
        		                	})
        		                	
        		                }
        		            }
        		        }
        		});
        	}
        }else if($(this).text()=="导入文件"){
            if(uploader != undefined){
                uploader.destroy();
            }
            initUploaderXls();
            $("#fileList .file-item").remove();//清空上次未上传的文件
            $("#fileList").find(".fileNameView").show();
            $("#importFile").show().dialog("open");
        }else if($(this).text()=="导入维护记录"){
            if(uploader2 != undefined){
                uploader2.destroy();
            }
            initWhjlUploaderXls();
            $("#whjlFileList .file-item").remove();//清空上次未上传的文件
            $("#whjlFileList").find(".fileNameView").show();
            $("#importWhjlFile").show().dialog("open");
        }else if($(this).text()=="导出维护记录"){
	   	 	$("#postForm").empty();
			var f=$("#postForm")[0];
	        f.action = 'xmwhjl/exportWhjlData';
	        f.submit();
        }else if($(this).text()=="转入实施"){
        	
        	if(ids == null || ids.length == 0){
        		$(".toImplementOne").hide();
        		$(".toImplementThree").hide();
                $(".toImplementTwo").show();
                $(".dialog_button").hide();
                
                $("#toImplementDlg").show().dialog("open");
                $("#toImplementId").val(ids);
            }else{
            	$("#toImplementDlg").dialog("open");
            	$(this).addClass("current");
            	var xmzl = $("#toImplementForm #xmzl").val();
            	//下面五种子类转入实施需要确定所在监控站转入了实施或者已经竣工，并且没有废弃。
            	if(xmzl == '视频前端' || xmzl == '显控终端' || xmzl == '传输线路' || xmzl == '供电系统' || xmzl == '报警装置'){
            		$.ajax({
            			url:'xmzl/jkzData',
            			type:'post',
            			async:false,
            			data:{xmzl:xmzl, id:ids.join(",")},
            			dataType:"json",
            			success:function(data){
            				if(data.length == 0){
            					$(".toImplementThree").hide();
            					$(".toImplementOne").hide();
            					$(".toImplementTwo").hide();
            					$(".dialog_button").hide();
            					$("#toImplementDlg").show().dialog("open");
            				}else{
            					for(var i = 0; i< data.length; i++){
            						if(data[i].bxxm == '是' || data[i].syzt == '废弃'){
            							if(data[i].bxxm == '是'){
            								$(".toImplementThree").text("已选项目所在监控站部分未实施,不能转入实施！");
            							}else{
            								$(".toImplementThree").text("已选项目所在监控站部分废弃,不能转入实施！");
            							}
            							$(".toImplementThree").show();
            							$(".toImplementOne").hide();
            							$(".toImplementTwo").hide();
            							$(".dialog_button").hide();
            							$("#toImplementDlg").show().dialog("open");
            							return false;
            						}
            					}
            					$(".toImplementOne").show();
            					$(".toImplementTwo").hide();
            					$(".toImplementThree").hide();
            					$(".dialog_button").show();
            					$("#toImplementDlg").show().dialog("open");
            					$("#toImplementId").val(ids);
            				}
            			}
            		});
            	}else{
                	 $(".toImplementOne").show();
                	 $(".toImplementTwo").hide();
                	 $(".toImplementThree").hide();
                	 $(".dialog_button").show();
                	 $("#toImplementDlg").show().dialog("open");
            		 $("#toImplementId").val(ids);
                }
            }
        }else if($(this).text()=="导出excel"){
        	/*if(ids == null || ids.length == 0){
        		$("#warning").find(".editOne").text("请选择要导出到Excel的项目!");
        		$("#warning").show().dialog("open");
        	}else{
        		$("#ids").val(ids);
        		document.exprotExcleFrom.submit();
        	}*/
        }else if($(this).text()=='转入维护'){
        	if(ids==null || ids.length==0){
                $("#toUseMaintenanceDlg").find(".toUseTip").text("请选择要转入维护的项目!");
                $("#toUseMaintenanceDlg").find("button").hide();
                $("#toUseMaintenanceDlg").show().dialog("open");
                return;
        	}else{
        		var arr = $("#tbody tr").find("input");
        		var f = false;
        		$.each(arr,function(index){
        			if ($(this).prop("checked")){
                        var txt=$(this).parent().parent().siblings("td#jszt").text();
                        if (txt!="已审计"){
                            $("#toUseMaintenanceDlg").find(".toUseTip").text("所选项目中有未审计的项目，不能转入维护!");
                            $("#toUseMaintenanceDlg button").hide();
                            $("#toUseMaintenanceDlg").show().dialog("open");
                            f = true;
						}
					}
        			if(f){
        				return false;
        			}
                    if(index == arr.length-1){
        				var xmzl = $("#xmzl").val();
                        if("视频前端"==xmzl || "显控终端"==xmzl || "传输线路"==xmzl || "供电系统"==xmzl || "报警装置"==xmzl){
                            $.ajax({
                                url:'implement/checkSzjkzInUse',
                                type:'post',
                                url:'xmzl/jkzData',
                                async:false,
                                data:{xmzl:xmzl, id:ids.join(",")},
                                success:function(p_context){
                                    for(var i=0;i<p_context.length;i++){
                                        if(p_context[i].syzt == null || p_context[i].syzt=="" || p_context[i].syzt =="null"){
                                            $("#toUseMaintenanceDlg").find(".toUseTip").text("已选项目所在监控站有未转入维护的,不能转入维护！");
                                            $("#toUseMaintenanceDlg button").hide();
                                            $("#toUseMaintenanceDlg").show().dialog("open");
                                            return;
                                        }
                                        if (i==p_context.length-1){
                                            $(".dialog_button").show();
                                            $("#toUseMaintenanceDlg button").show();
                                            $("#toUseMaintenanceDlg").find(".toUseTip").text("是否确定将已选项目转入维护？");
                                            $("#toUseMaintenanceDlg").show().dialog("open");
                                            $("#toUseId").val(ids);
										}
                                    }
                                }
                            })
						}else{
                            $(".dialog_button").show();
                            $("#toUseMaintenanceDlg button").show();
                            $("#toUseMaintenanceDlg").find(".toUseTip").text("是否确定将已选项目转入维护？");
                            $("#toUseMaintenanceDlg").show().dialog("open");
                            $("#toUseId").val(ids);
						}
                    }
				})

               /* var xmzl=$("#xmzl").val();
                if("视频前端"==xmzl || "显控终端"==xmzl || "传输线路"==xmzl || "供电系统"==xmzl || "报警装置"==xmzl){
                    $.ajax({
                        url:'implement/checkSzjkzInUse',
                        type:'post',
                        url:'xmzl/jkzData',
                        type:'post',
                        async:false,
                        data:{xmzl:xmzl, id:ids.join(",")},
                        success:function(data){
                            for(var i=0;i<data.length;i++){
                                if(data.syzt == null || data.syzt=="" || data.syzt =="null"){
                                    $(".toUseOne").hide();
                                    $(".toUseTwo").hide();
                                    $(".toUseThree").hide();
                                    $(".dialog_button").hide();
                                    $("#toUseSubmit").hide();
                                    $("#warning").find(".editOne").text("已选项目中部分项目所在监控站未转入维护,不能转入维护！");
                                    $("#warning").show().dialog("open");
                                    return;
                                }
                            }
                        }
                    })
                }else{
                    $(".toUseOne").show();
                    $(".toUseTwo").hide();
                    $(".toUseThree").hide();
                    $(".dialog_button").show();
                    $("#toUseSubmit").show();
                    $("#toUseMaintenanceDlg").show().dialog("open");
                    $("#toUseId").val(ids);
                }*/
            }
        }
    });
    
    $("#removeSubmit").click(function(){
    	$.ajax({
    		url:"xmzl/delete",
    		type:"post",
			data:$("#deleteFrom").serialize(),
			dataType:"json",
			success:function(data){
				if(data!=null){
					$("#postForm").empty();
		        	var f=$("#postForm")[0];
		            f.action='xmzl/loadDataTable';
		            f.innerHTML='<input type="hidden" name="xmlb" value="'+data.xmlb+'"/>'
		            +'<input type="hidden" name="xmzl" value="'+data.xmzl+'"/>'
		            +'<input type="hidden" name="szsf" value="'+data.szsf+'"/>'//所在区域
		            +'<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
		            f.submit();
				}
			}
    	});
    });
    $("#toImplementSubmit").click(function(){
    	$.ajax({
    		url:"xmzl/toImplement",
    		type:"post",
    		data:$("#toImplementForm").serialize(),
    		dataType:"json",
    		success:function(data){
    			if(data!=null){
    				$("#postForm").empty();
    				var f=$("#postForm")[0];
    				f.action='xmzl/loadDataTable';
    				f.innerHTML='<input type="hidden" name="xmlb" value="'+data.xmlb+'"/>'
    				+'<input type="hidden" name="xmzl" value="'+data.xmzl+'"/>'
    				+'<input type="hidden" name="szsf" value="'+data.szsf+'"/>'//所在区域
    				+'<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
    				f.submit();
    			}
    		}
    	});
    });
    
    $("#toUseSubmit").click(function(){
    	$.ajax({
    		url:'implement/toUseMaintenance',
    		type:'post',
    		data:$("#toUseForm").serialize(),
    		dataType:'json',
    		success:function(data){
    			$("#toUseMaintenanceDlg").dialog("close");
    			if(data!=null){
    				$("#postForm").empty();
    				var f=$("#postForm")[0];
    				f.action='xmzl/loadDataTable';
    				f.innerHTML='<input type="hidden" name="xmlb" value="'+data.xmlb+'"/>'
    				+'<input type="hidden" name="xmzl" value="'+data.xmzl+'"/>'
    				+'<input type="hidden" name="szsf" value="'+data.szsf+'"/>'
    				+'<input type="hidden" name="partTag" value="'+data.partTag+'"/>';
    				f.submit();
    			}
    			
    		}
    	});
    });
    //"取消"按钮事件
    $(".cancal_btn").click(function(){
        $(this).parents(".easyui-dialog").dialog('close');
        $("#addInfo").find("[comboname='bjfx']").combobox('setValues', '');
    	$("#addInfo").find("[comboname='dxlb']").combobox('setValues', '');
        $(".tab_title button").removeClass("current");
        $(".tips").remove();//移除所有的提示
        $(".jsqyTips").remove();
        $("#addSubmit").off("click");
        if(uploader1!=undefined){
            uploader1.destroy();
        }
    });
    //“关闭”按钮事件
    $(".easyui-dialog").dialog({
        onClose: function () {
            $(".tab_title button").removeClass("current");
            $(".tips").remove();//移除所有的提示
            $(".jsqyTips").remove();
        }
    });
    //点击下拉框事件
    $(".typeTitle").click(function(){
        $(this).find(".typeItem").slideToggle();
        $(this).parent().siblings().find(".typeItem").slideUp();
    });
    $(".typeItem a").click(function(){
        var text=$(this).text();
        $(this).parents(".typeTitle").find(".t_name").text(text);
        $(this).parents(".typeItem").slideDown();
    });
    //点击其他位置下拉框消失
    $(document).bind("click",function(e) {
        var target = $(e.target);
        if (target.closest(".typeItem").length == 0&&target.closest(".typeTitle").length == 0) {
            $(".typeItem").slideUp();
        }
    });

    //每行数据的checkbox添加id
    assignId($(".tabList tr"),"checkbox");

    //“全选”按钮事件
    $(".tabList thead .checkboxOne").click(function () {
        if ($(this).find("input").prop("checked")) {
            $(this).find("input").prop("checked", false);
            $(this).parents(".tabList").find("tbody input").prop("checked", false);
        }else {
            $(this).find("input").prop("checked", true);
            $(this).parents(".tabList").find("tbody input").prop("checked", true);
        }
    });
    //姓名
    /*$.validator.addMethod("personName",function(value, element, param){
        var re = /^[\u4E00-\u9FA5]{2,6}$/;
        return this.optional(element) || re.test(value);
    },"由2-6位汉字组成");*/
    //varchar(14,4)验证
    $.validator.addMethod("numberV",function(value, element, param){
        var exp=new RegExp(/^([1-9][0-9]{0,8}||[0]||[1-9][0-9]{0,8}\.[0-9]{1,4}||[0]\.[0-9]{1,4})$/);
        return this.optional(element) || exp.test(value);
    },"应输入数字，且整数位最多9位，小数位最多4位");
    //number(10)
    $.validator.addMethod("numV",function(value, element, param){
        var exp=new RegExp(/^([1-9][0-9]{0,8}||[0]||[1-9][0-9]{0,8}||[0])$/);
        return this.optional(element) || exp.test(value);
    },"应输入整数，且整数位最多9位");
    //年度验证（1900-）
    $.validator.addMethod("numL",function(value, element, param){
        var exp=new RegExp(/^[1-9][0-9]{0,4}$/);
        return this.optional(element) || exp.test(value);
    },"应输入整数，且整数位最多5位");
    
    $.validator.addMethod("yearV",function(value, element, param){
//        var exp=new RegExp(/^([1-9][9][0-9]{0,2})$/);
    	var rightY=false;
    	var num = new RegExp(/^[0-9]{4}$/);
    	if(value>=1900&&value<=9999&&num.test(value)){
    		rightY = true;
    	}
        return this.optional(element) || rightY;
    },"请输入1900-9999之间的年份");
    
    //精度的正则表达式
    $.validator.addMethod("jd",function(value, element, param){
    	var exp=/^([0-9]|[1-9][0-9]|1[0-7][0-9])(\.\d{1,8})?$/;
    	var f = false;
    	if(value == '180'){//   /^180$/.test(180)和/^180$/.test(180.0000)结果一样
    		f = true;
    	}
    	return this.optional(element)  || exp.test(value) || f;
    },"应输入0-180之间的数字，小数点后最多8位");
    //纬度的正则表达式
    $.validator.addMethod("wd",function(value, element, param){
    	var exp=/^([0-9]|[1-8][0-9])(\.\d{1,8})?$/;
    	var f = false;
    	if(value == '90'){
    		f = true;
    	}
    	return this.optional(element) || exp.test(value) || f;
    },"应输入0-90之间的数字，小数点后最多8位");
    //点击其他位置隐藏提示框
    $(document).on("click","input,textarea,.textbox-icons",function(e) {
        var target = $(e.target);
        if (target.closest(".tips").length == 0) {
            $(".tips").remove();
            $(".jsqyTips").remove();
        }
    });
    //鼠标移到一行数据选中
    $(document).on("click", "#whjlTabDiv .tabList tbody tr", function(e){
        var target = $(e.target);
        if(target.closest(".checkboxOne").length==0 && target.closest(".nameTd").length==0){
            if($(this).find("input").prop("checked")){
                $(this).find("input").prop("checked", false);
            }else{
                $(this).find("input").prop("checked", true);
            }
        }
        var f = false;
        $("#whjlTabDiv .tabList tbody .checkboxOne").each(function(){
        	if(!$(this).find("input").prop("checked")){
        		f = true;
        		return false;
        	}
        });
        if(f){
        	$("#whjlTabDiv .tabList thead input").prop("checked", false);
        }else{
        	$("#whjlTabDiv .tabList thead input").prop("checked", true);
        }
    });
    $(document).on("dblclick", "#whjlTabDiv .tabList tbody tr", function(e){
    	//显示维护记录（起）
    	if(partTag == '4'){
    		$("#xmwhjlDiv").empty();
    		$("#xmwhjlDiv").load("xmwhjl/loadTableData?xmid="+$(this).find("input").val(),function(){
    			$("#xmwhjlQueryDlg").show().dialog("open");
    			$("#xmwhjlQueryDlg").parent().css("top","20%");
    		});
    	}
    });
    //点击项目名称展示详细信息
    $(document).on("click",".nameTd",function(){
    	//$("#updateInfo").empty();
    	$("#addInfo").hide();
		$("#updateInfo").show();
        $(".buttonDiv").show();
        $(".buttonDiv .cancal,.buttonDiv .confirm").hide();
        $(".buttonDiv .close").show();
        $(".easyui-dialog").panel({title:"详细信息"});
        $("#addDlg").show().dialog("open");
        id = $(this).parent().parent().find("input:checkbox").val();
		xmzl=$("#xmzl").val();
        $.ajax({
        		url:"xmzl/loadDetail",
        		type:"post",
				data:"ids="+id+"&xmzl="+xmzl,
				dataType:"json",
				success:function(data){
					if(data!=null){
						/*//读取模板
						var tpl = document.getElementById("tpl").innerHTML;
						//获取所在省的信息
 						//juicer 编译模板并渲染数据
 						var html = juicer(tpl,{data:data,partTag:partTag});
						//将编译好模板放入指定位置
						document.getElementById("updateInfo").innerHTML=html;*/
						//加载市、区数据
						loadCity(data,"#formUpdate");
						loadQu(data,"#formUpdate");
						$("#updateInfo #xmmc").val(data.xmmc);//项目名称
 						if(roleName=='1'){
 							for(var i=0;i<$("#updateInfo #address1 option").length;i++){
 								if($("#updateInfo #address1 option")[i].text==data.szsf){
 									$("#updateInfo #address1 option")[i].selected=true;
 								}
 							}
 						}
 						
 						$("#updateInfo #bj").combobox("setValues",data.bjfx.split(","));//边界方向
							$("#updateInfo #dx").combobox("setValues",data.dxlb.split(","));//地方类别
							for(var i=0;i<$("#updateInfo #sslx option").length;i++){//设施类型
								if($("#updateInfo #sslx option")[i].text==data.sslx){
									$("#updateInfo #sslx option")[i].selected=true;
								}
							}
							for(var i=0;i<$("#updateInfo #jsxz option").length;i++){//建设性质
								if($("#updateInfo #jsxz option")[i].text==data.jsxz){
									$("#updateInfo #jsxz option")[i].selected=true;
								}
							}
 						$("#updateInfo #jd").val(data.jd);//经度
 						$("#updateInfo #wd").val(data.wd);//纬度
 						$("#updateInfo #sydw").val(data.sydw);//使用单位
 						$("#updateInfo #sbdw").val(data.sbdw);//申报单位
 						$("#updateInfo #tznd").val(data.tznd);//投资年度
 						$("#updateInfo #zytz").val(data.zytz);//中央投资
 						$("#updateInfo #dftz").val(data.dftz);//地方投资
 						$("#updateInfo #bz").html(data.bz);//备注
 						if(update_xmzl == '执勤道路'){
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 							for(var i=0;i<$("#updateInfo #dllb option").length;i++){//道路类别
 								if($("#updateInfo #dllb option")[i].text==data.dllb){
 									$("#updateInfo #dllb option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #dllx option").length;i++){//道路类型
 								if($("#updateInfo #dllx option")[i].text==data.dllx){
 									$("#updateInfo #dllx option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #dldj option").length;i++){//道路等级
 								if($("#updateInfo #dldj option")[i].text==data.dldj){
 									$("#updateInfo #dldj option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #ljlx option").length;i++){//路基类型
 								if($("#updateInfo #ljlx option")[i].text==data.ljlx){
 									$("#updateInfo #ljlx option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #lmlx option").length;i++){//路面类型
 								if($("#updateInfo #lmlx option")[i].text==data.lmlx){
 									$("#updateInfo #lmlx option")[i].selected=true;
 								}
 							}
 						}else if(update_xmzl=='桥梁'){
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 							for(var i=0;i<$("#updateInfo #qllx option").length;i++){//桥梁类型
 								if($("#updateInfo #qllx option")[i].text==data.qllx){
 									$("#updateInfo #qllx option")[i].selected=true;
 								}
 							}
 							$("#updateInfo #zz").val(data.zz);//载重
 						}else if(update_xmzl=='执勤码头'){
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 						}else if(update_xmzl=='直升机停机坪'){
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							for(var i=0;i<$("#updateInfo #tjplx option").length;i++){//停机坪类型
 								if($("#updateInfo #tjplx option")[i].text==data.tjplx){
 									$("#updateInfo #tjplx option")[i].selected=true;
 								}
 							}
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 						}else if(update_xmzl=='铁丝网'){
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 							for(var i=0;i<$("#updateInfo #tswlx option").length;i++){//铁丝网类型
 								if($("#updateInfo #tswlx option")[i].text==data.tswlx){
 									$("#updateInfo #tswlx option")[i].selected=true;
 								}
 							}
 							$("#updateInfo #lzmgs").val(data.lzmgs);//拦阻门个数
 						}else if(update_xmzl=='铁栅栏'){
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 						}else if(update_xmzl=='拦阻桩'){
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 							for(var i=0;i<$("#updateInfo #lzzlx option").length;i++){//拦阻桩类型
 								if($("#updateInfo #lzzlx option")[i].text==data.lzzlx){
 									$("#updateInfo #lzzlx option")[i].selected=true;
 								}
 							}
 						}else if(update_xmzl=='隔离带'){
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 						}else if(update_xmzl=='拒马'){
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 						}else if(update_xmzl=='报警线路'){
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 							$("#updateInfo #sbpp").val(data.sbpp);//设备品牌
 						}else if(update_xmzl=='监控中心'){
 							for(var i=0;i<$("#updateInfo #xsltqk option").length;i++){//向上联通情况
 								if($("#updateInfo #xsltqk option")[i].text==data.xsltqk){
 									$("#updateInfo #xsltqk option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #xsltwlxz option").length;i++){//向上联通网络性质
 								if($("#updateInfo #xsltwlxz option")[i].text==data.xsltwlxz){
 									$("#updateInfo #xsltwlxz option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #xscsxl option").length;i++){//向上传输线路
 								if($("#updateInfo #xscsxl option")[i].text==data.xscsxl){
 									$("#updateInfo #xscsxl option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #jb option").length;i++){//级别
 								if($("#updateInfo #jb option")[i].text==data.jb){
 									$("#updateInfo #jb option")[i].selected=true;
 								}
 							}
 						}else if(update_xmzl=='监控站'){
 							for(var i=0;i<$("#updateInfo #xsltqk option").length;i++){//向上联通情况
 								if($("#updateInfo #xsltqk option")[i].text==data.xsltqk){
 									$("#updateInfo #xsltqk option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #xsltwlxz option").length;i++){//向上联通网络性质
 								if($("#updateInfo #xsltwlxz option")[i].text==data.xsltwlxz){
 									$("#updateInfo #xsltwlxz option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #xscsxl option").length;i++){//向上传输线路
 								if($("#updateInfo #xscsxl option")[i].text==data.xscsxl){
 									$("#updateInfo #xscsxl option")[i].selected=true;
 								}
 							}
 							$("#updateInfo #xkzdgs").val(data.xkzdgs);//显控终端
 							$("#updateInfo #ydqdgs").val(data.ydqdgs);//移动前端
 							$("#updateInfo #gdqdgs").val(data.gdqdgs);//固定前端
 						}else if(update_xmzl=='视频前端'){
 							$("#updateInfo #csfs").val(data.csfs);//传输方式
 							$("#updateInfo #fzdd").val(data.fzdd);//放置地点
 							for(var i=0;i<$("#updateInfo #jkz_id option").length;i++){//所在监控站
 								if($("#updateInfo #jkz_id option")[i].jkz_id==data.jkz_id){
 									$("#updateInfo #jkz_id option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #sblx option").length;i++){//设备类型
 								if($("#updateInfo #sblx option")[i].text==data.sblx){
 									$("#updateInfo #sblx option")[i].selected=true;
 								}
 							}
 						}else if(update_xmzl=='显控终端'){
 							for(var i=0;i<$("#updateInfo #jkz_id option").length;i++){//所在监控站
 								if($("#updateInfo #jkz_id option")[i].jkz_id==data.jkz_id){
 									$("#updateInfo #jkz_id option")[i].selected=true;
 								}
 							}
 							$("#updateInfo #csfs").val(data.csfs);//传输方式
 							$("#updateInfo #fzdd").val(data.fzdd);//放置地点
 						}else if(update_xmzl=='传输线路'){
 							for(var i=0;i<$("#updateInfo #jkz_id option").length;i++){//所在监控站
 								if($("#updateInfo #jkz_id option")[i].jkz_id==data.jkz_id){
 									$("#updateInfo #jkz_id option")[i].selected=true;
 								}
 							}
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 							$("#updateInfo #xlqd").val(data.xlqd);//线路起点
 							$("#updateInfo #xlzd").val(data.xlzd);//线路终点
 							$("#updateInfo #jsdd").val(data.jsdd);//建设终点
 							for(var i=0;i<$("#updateInfo #xlxz option").length;i++){//线路性质
 								if($("#updateInfo #xlxz option")[i].text==data.xlxz){
 									$("#updateInfo #xlxz option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #xllx option").length;i++){//线路类型
 								if($("#updateInfo #xllx option")[i].text==data.xllx){
 									$("#updateInfo #xllx option")[i].selected=true;
 								}
 							}
 							
 						}else if(update_xmzl=='报警装置'){
 							for(var i=0;i<$("#updateInfo #jkz_id option").length;i++){//所在监控站
 								if($("#updateInfo #jkz_id option")[i].jkz_id==data.jkz_id){
 									$("#updateInfo #jkz_id option")[i].selected=true;
 								}
 							}
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 							$("#updateInfo #sbpp").val(data.sbpp);//设备品牌
 							$("#updateInfo #sbxh").val(data.sbxh);//设备型号
 						}else if(update_xmzl=='供电系统'){
 							for(var i=0;i<$("#updateInfo #jkz_id option").length;i++){//所在监控站
 								if($("#updateInfo #jkz_id option")[i].jkz_id==data.jkz_id){
 									$("#updateInfo #jkz_id option")[i].selected=true;
 								}
 							}
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							
 						}else if(update_xmzl=='军警民联防平台'){
 							for(var i=0;i<$("#updateInfo #xsltqk option").length;i++){//向上联通情况
 								if($("#updateInfo #xsltqk option")[i].text==data.xsltqk){
 									$("#updateInfo #xsltqk option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #xsltwlxz option").length;i++){//向上联通网络性质
 								if($("#updateInfo #xsltwlxz option")[i].text==data.xsltwlxz){
 									$("#updateInfo #xsltwlxz option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #xscsxl option").length;i++){//向上传输线路
 								if($("#updateInfo #xscsxl option")[i].text==data.xscsxl){
 									$("#updateInfo #xscsxl option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #hxltqk option").length;i++){//横向联通情况
 								if($("#updateInfo #hxltqk option")[i].text==data.hxltqk){
 									$("#updateInfo #hxltqk option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #hxltwlxz option").length;i++){//横向联通网络性质
 								if($("#updateInfo #hxltwlxz option")[i].text==data.hxltwlxz){
 									$("#updateInfo #hxltwlxz option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #hxcsxl option").length;i++){//横向传输线路
 								if($("#updateInfo #hxcsxl option")[i].text==data.hxcsxl){
 									$("#updateInfo #hxcsxl option")[i].selected=true;
 								}
 							}
 							for(var i=0;i<$("#updateInfo #jb option").length;i++){//级别
 								if($("#updateInfo #jb option")[i].text==data.jb){
 									$("#updateInfo #jb option")[i].selected=true;
 								}
 							}
 							$("#updateInfo #fzdd").val(data.fzdd);//放置地点
 						}else if(update_xmzl=='无人值守电子哨兵'){
 							$("#updateInfo #fzdd").val(data.fzdd);//放置地点
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 						}else if(update_xmzl=='执勤房'){
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							for(var i=0;i<$("#updateInfo #zqflx option").length;i++){//执勤房类型
 								if($("#updateInfo #zqflx option")[i].text==data.zqflx){
 									$("#updateInfo #zqflx option")[i].selected=true;
 								}
 							}
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 							
 						}else if(update_xmzl=='了望塔'){
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 							for(var i=0;i<$("#updateInfo #lwtlx option").length;i++){//瞭望塔类型
 								if($("#updateInfo #lwtlx option")[i].text==data.lwtlx){
 									$("#updateInfo #lwtlx option")[i].selected=true;
 								}
 							}
 						}else if(update_xmzl=='标志牌'){
 							$("#updateInfo #jsdd").val(data.jsdd);//建设地点
 							$("#updateInfo #jsgm").val(data.jsgm);//建设规模
 							for(var i=0;i<$("#updateInfo #bzplx option").length;i++){//标志牌类型
 								if($("#updateInfo #bzplx option")[i].text==data.bzplx){
 									$("#updateInfo #bzplx option")[i].selected=true;
 								}
 							}
 						}
 						//基本信息公共字段
 						if(partTag=='3' || partTag =='4'){
 							for(var i=0;i<$("#updateInfo #jszt option").length;i++){//建设状态
 								if($("#updateInfo #jszt option")[i].text==data.jszt){
 									$("#updateInfo #jszt option")[i].selected=true;
 								}
 							}
 							$("#updateInfo #cjdw").val(data.cjdw);//承建单位
 							$("#updateInfo #jldw").val(data.jldw);//承建单位
 							$("#updateInfo input#ztbsj").datebox('setValue',formatDate(data.ztbsj));//招投标时间
     						$("#updateInfo input#kgsj").datebox('setValue',formatDate(data.kgsj));//开工时间
     						$("#updateInfo input#jgsj").datebox('setValue',formatDate(data.jgsj));//竣工时间
     						$("#updateInfo input#cysj").datebox('setValue',formatDate(data.cysj));//初验时间
     						$("#updateInfo input#sjsj").datebox('setValue',formatDate(data.sjsj));//审计时间
						}
 						if(partTag == '4'){
 							for(var i=0;i<$("#updateInfo #syzt option").length;i++){//建设状态
 								if($("#updateInfo #syzt option")[i].text==data.syzt){
 									$("#updateInfo #syzt option")[i].selected=true;
 								}
 							}
 						}
 						//$("#updateInfo .editFj").val(data.fj);
 						$("#updateInfo div#ztbsj input#ztbsj").datebox("disable");
 					    $("#updateInfo div#kgsj input#kgsj").datebox("disable");
 					    $("#updateInfo div#jgsj input#jgsj").datebox("disable");
 					    $("#updateInfo div#cysj input#cysj").datebox("disable");
 					    $("#updateInfo div#sjsj input#sjsj").datebox("disable");
						//加载所在监控站数据
						if(xmzl=="视频前端" || xmzl=="显控终端" || xmzl=="传输线路" || xmzl=="报警装置" || xmzl=="供电系统"){
							loadJkz($("#formUpdate .szjkz"),"#formUpdate ",data.jkz_id);
						}
						//初始化多选框插件
						$(".easyui-combobox").combobox();
		        		initElement();
		                $("#formUpdate #bj").combobox("setValues",data.bjfx.split(","));//边界方向多选赋值
 		        		$("#formUpdate #dx").combobox("setValues",data.dxlb.split(","));//地形类型多选赋值
		                disabledForm();
		                classify($("#xmzl").val());
		                showFj_info(data.fj);
		                deleteNull();
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){ 
		            alert("出错了！！:"+XMLHttpRequest);  
		        }
     	});
    });
});

//通过所在区域加载监控站数据
function loadJkz(obj,superObj,szjkz){
	//$(superObj+".szjkz").empty();
	var szsf=$(superObj+"#address1").val();
	var szcs=$(superObj+"#address2").val();
	var szq=$(superObj+"#address3").val();

	var xmzl=$("#xmzl").val();
	if(xmzl=="视频前端" || xmzl=="显控终端" || xmzl=="传输线路" || xmzl=="报警装置" ||xmzl=="供电系统"){
		$.ajax({
			url:"xmzl/queryJkzName",
			type:"post",
			data:"szsf="+szsf+"&szcs="+szcs+"&szq="+szq+"&partTag="+partTag,
			async:false,
			dataType:"json",
			success:function(data){
				if(data!=null && data.length>0){
					$("#addSubmit").attr("disabled",false);
					$("#addSubmit").css("background","");
					$(superObj+".szjkz").empty();
					for(var i=0;i<data.length;i++){
						if(szjkz==data[i].id){
							$(superObj+".szjkz").append("<option value='"+data[i].id+"' selected>"+data[i].xmmc+"</option>");
						}else{
							$(superObj+".szjkz").append("<option value='"+data[i].id+"'>"+data[i].xmmc+"</option>");
						}
					}
				}else{
					$(superObj+".szjkz").empty();
					var errorStr = "该区域没有监控站，请先填写监控站项目信息！";
					if(partTag == "2"){
                        errorStr = "该区域没有处于“计划阶段”、“实施阶段”或者“维护阶段”的监控站，请先填写监控站项目信息！";
					}
					if(partTag == "3"){
                        errorStr = "该区域没有处于“实施阶段”或者“维护阶段”的监控站，请先填写监控站项目信息！";
					}
					if(partTag == "4"){
                        errorStr = "该区域没有处于“维护阶段”的监控站，请先填写监控站项目信息！";
					}
					$("#warning").find(".editOne").text(errorStr);
	        		$("#warning").show().dialog("open");
	        		$("#addSubmit").attr("disabled",true);
	        		$("#addSubmit").css("background","#ccc");
				}
			}
		});
	}
}

//去掉详情页中显示的null
function deleteNull(){
	var $inputText=$("#formUpdate").find("input:text,textarea");
    for(var i=0;i<$inputText.length;i++){
    	iValue=$inputText.eq(i).val();
    	if(iValue=="null"){
    		$inputText.eq(i).val("");
    	}
    }
}

//动态赋值每行数据的checkbox的id
function assignId(obj,id){
    var i=0;
    obj.each(function(){
        $(this).find("input[type='checkbox']").attr("id",id+i);
        $(this).find("input[type='checkbox']").next().attr("for",id+i);
        i++;
    });
}
function formValidate(){
    return $("#formAdd").validate({
        onkeyup:false,//在 keyup 时验证false
        onfocusout:false,//失去焦点时验证（不包括复选框/单选按钮）false
        onfocusin:false,//获得焦点的事件
        rules: {
            lengthRequriedVa:{required:true,maxlength: 80},
            numberVa:{numberV:true},
            numVa:{numV:true},
            numberRequiredVa:{required:true,numberV:true},
            numRequiredVa:{required:true,numV:true},
            yearVa:{required:true,yearV:true},
            lengthVa:{maxlength: 80},
            maxLengthVa:{maxlength:500},
            writeName:{minlength:2,maxlength:30},
            jd:{jd:true},
            wd:{wd:true},
            numL:{required:true,numL:true}
        },
        showErrors : function(errorMap, errorList) {
            $.each(errorList, function(i, v) {
                errorLoc($(v.element),v.message);
                objFocus($(v.element));
                return false;
            });
        }
    });
}
//修改表单验证
function formUpdateValidate(){                                                  
    return $("#formUpdate").validate({                                       
        onkeyup:false,//在 keyup 时验证false                                  
        onfocusout:false,//失去焦点时验证（不包括复选框/单选按钮）false                      
        onfocusin:false,//获得焦点的事件                                         
        rules: {                                                          
            lengthRequriedVa:{required:true,maxlength: 80},               
            numberVa:{numberV:true}, 
            numVa:{numV:true},                      
            numberRequiredVa:{required:true,numberV:true},
            numRequiredVa:{required:true,numV:true},         
            yearVa:{required:true,yearV:true},                            
            lengthVa:{maxlength: 80},                                     
            maxLengthVa:{maxlength:500},                                  
            writeName:{minlength:2,maxlength:30},
            jd:{jd:true},
            wd:{wd:true},
            numL:{required:true,numL:true}
        },                                                                
        showErrors : function(errorMap, errorList) {                      
            $.each(errorList, function(i, v) {                            
                errorLoc($(v.element),v.message);                         
                objFocus($(v.element));                                   
                return false;                                             
            });                                                           
        }                                                                 
    });                                                                   
}

//错误提示显示的方法，obj为错误对象，text为提示语
function errorLoc(obj,text){
    var errorSpan = "<span class='tips'></span>";
    obj.parent().append(errorSpan);
    obj.parent().find(".tips").text(text);
    //滚动条滚动到错误位置
    var objPixelScrollIntoView = obj.parents(".project_item")[0].offsetTop;
    $("#scrollForm").mCustomScrollbar("scrollTo",objPixelScrollIntoView);
};
//错误对象高亮
function objFocus(obj){
    //带单位的输入框
    if(obj.parent().hasClass("wRmb")){
        obj.parent().addClass("focused");
        setTimeout(function(){
            obj.parent().removeClass("focused");
        },2000);
    }else{
        obj.addClass("focused");
        setTimeout(function(){
            obj.removeClass("focused");
        },2000);
    }
}
//遍历多选择框，obj为遍历对象
function eachMulti(obj){
    var flag = true;
    obj.each(function(){
        var multValue = $(this).combobox("getValues").join(",");
        if(multValue==""){
            $(this).parents(".multi").find(".textbox-text").addClass("focused");
            errorLoc($(this),"这是必填字段");
            objFocus($(this));
            flag = false;
            return false;
        }else{
            flag = true;
        }
    });
    return flag;
}
//遍历时间选择框
function eachDatabox(obj){
    var data = true;
    obj.each(function(){
        if($(this).val()==""||$(this).val()==null){
        	errorLoc($(this).parent(),"这是必填字段");
            /*var datebox = $(this).parents(".dataDiv").find(".datebox");	
            datebox.css("border-color","#6eb46c");
            setTimeout(function(){
                datebox.css("border-color","#ddd");
            },2000);*/
            data = false;
            return false;
        }else{
            data = true;
        }
    });
    return data;
};
//初始化上传
function initUpload(xmmc,szsf,type){
	if(uploader1 != undefined){
        uploader1.destroy();
    }
	initUploaderZip(xmmc,szsf,type);
}
//初始化表单元素
function initElement(){
    $(".textbox").css({"width":"66.3%","height":"28px"});
    $(".combo").css({"border-radius":"0","background-color":"#ecf4e6"});
    $(".textbox-text").css({"height":"22px","line-height":"22px","background-color":"#ecf4e6"});
    $(".textbox-addon").css({"top":"0px","right":"0px"});
    $(".multi .combo-arrow").css({"width":"24px","background-color":"#ecf4e6","background-image":"url('img/select.png')","height":"28px","line-height":"28px"});
    $(".multi .textbox").css("width","83.2%");
    $(".multi .textbox-text").css({"overflow":"auto","width":"95%"});
    $(".easyui-combobox").next().find("textarea").attr("readonly",true);
    $(".datebox").css("width","66.5%");
//    $(".datebox .textbox-addon").css("right","0px");
//    $(".datebox .combo-arrow").css({"width":"32px","background-color":"#ecf4e6","height":"28px"});
//    $(".dataDiv .textbox-text").css({"line-height":"30px","height":"30px"});
//    $(".dataDiv .textbox-text").attr({"onfocus":"this.blur()","readonly":"readonly"});
};

//清空表单信息
function clearForm(){
	$(".addInfo input:not(#tjxmlb,#tjxmzl,#tokenId),.addInfo div>textarea").val("");
	$(".addInfo select:not(#formAdd #xmlb,#tjXmxl)").val("");
	$(".easyui-combobox").each(function(){
		var data = $(this).combobox("getData");
		$(this).combobox("setValue",data[0].value);
	});
};
//禁用表单元素
function disabledForm(){
    $(".addInfo input,.addInfo textarea").attr({"readonly":true,"onfocus":"this.blur()"});
    $(".addInfo select").attr("disabled",true);
    $(".addInfo input,.addInfo select,.addInfo textarea,.wRmb").css("background-color","#e9e9e9");
    $(".multi .combo-arrow").css("background-color","#e9e9e9");
    $(".wRmb input").unbind("focus");
    $("#bj,#dx").combobox("disable");//禁用多选下拉框
    //修改附件的样式，移除上传按钮
    $(".zip-list").addClass("disableUp");
    $(".upload-btn1").hide();
 
};
//启用表单元素
function openForm(){
    $(".addInfo input,.addInfo textarea").attr("readonly",false);
    $(".addInfo input.jkzzlgs_readonly").attr("readonly",true);
    $(".addInfo input.jszt_readonly").attr("readonly",true);
    $(".addInfo select").attr("disabled",false);
    $(".wRmb .ndjh").attr("readonly",true);//textbox-value
    $(".easyui-combobox").next().find("textarea").attr("readonly",true);
    $("#formAdd div#ztbsj input#ztbsj").datebox({editable:false});
    $("#formAdd div#kgsj input#kgsj").datebox({editable:false});
    $("#formAdd div#jgsj input#jgsj").datebox({editable:false});
    $("#formAdd div#cysj input#cysj").datebox({editable:false});
    $("#formAdd div#sjsj input#sjsj").datebox({editable:false});
    
    $("#formUpdate div#ztbsj input#ztbsj").datebox({editable:false});
    $("#formUpdate div#kgsj input#kgsj").datebox({editable:false});
    $("#formUpdate div#jgsj input#jgsj").datebox({editable:false});
    $("#formUpdate div#cysj input#cysj").datebox({editable:false});
    $("#formUpdate div#sjsj input#sjsj").datebox({editable:false});
    $(".addInfo input,.addInfo select,.addInfo textarea,.wRmb").css("background-color","#ecf4e6");
    $(".multi .combo-arrow").css("background-color","#ecf4e6");
    $(".addInfo input,.addInfo textarea").removeAttr("onfocus");
    $("#bj,#dx").combobox("enable");//启用多选下拉框
  //还原附件的样式，显示上传按钮
	if($(".zip-list").hasClass("disableUp")){
		$(".zip-list").removeClass("disableUp");
        $(".upload-btn1").show();
	}
};
//表头下拉菜单设置最大高度
function menuHeight(){
	//$(".typeItem").css("max-height",$(".tabList").height()-12);
    $(".typeItem").css("max-height","150px");
};
//分类显示或隐藏
function classify(currentClass){
	$(".dutyRoad,.bridge,.wharf,.tarmac,.ironNet,.ironFence,.hinderStake,.knifeRest,.median,.alarmCircuit,.surveillanceCenter,.monitorStation,.vedioLeadingEnd,.samkoonEnd,.transmissionLink,.warningDevice,.powerSystem,.defensePlatform,.dutyHouse,.watchtower,.lighthouse,.buoyLight,.drogue").hide();
	if(currentClass=="执勤道路"){
		$(".dutyRoad").show();
	}else if(currentClass=="桥梁"){
		$(".bridge").show();
	}else if(currentClass=="执勤码头"){
		$(".wharf").show();
	}else if(currentClass=="直升机停机坪"){
		$(".tarmac").show();
	}else if(currentClass=="铁丝网"){
		$(".ironNet").show();
	}else if(currentClass=="铁栅栏"){
		$(".ironFence").show();
	}else if(currentClass=="拦阻桩"){
		$(".hinderStake").show();
	}else if(currentClass=="隔离带"){
		$(".knifeRest").show();
	}else if(currentClass=="拒马"){
		$(".median").show();
	}else if(currentClass=="其他拦阻设施"){
		
	}else if(currentClass=="报警线路"){
		$(".alarmCircuit").show();
	}else if(currentClass=="监控中心"){
		$(".surveillanceCenter").show();
	}else if(currentClass=="监控站"){
		$(".monitorStation").show();
	}else if(currentClass=="视频前端"){
		$(".vedioLeadingEnd").show();
	}else if(currentClass=="显控终端"){
		$(".samkoonEnd").show();
	}else if(currentClass=="传输线路"){
		$(".transmissionLink").show();
	}else if(currentClass=="无线传输"){
		$(".wirelessTransmission").show();
	}else if(currentClass=="报警装置"){
		$(".warningDevice").show();
	}else if(currentClass=="供电系统"){
		$(".powerSystem").show();
	}else if(currentClass=="军警民联防平台"){
		$(".defensePlatform").show();
	}else if(currentClass=="无人值守电子哨兵"){
		$(".electronicSentinel").show();
	}else if(currentClass=="执勤房"){
		$(".dutyHouse").show();
	}else if(currentClass=="了望塔"){
		$(".watchtower").show();
	}else if(currentClass=="标志牌"){
		$(".denoter").show();
	}else if(currentClass=="灯塔"){
		$(".lighthouse").show();
	}else if(currentClass=="灯浮"){
		$(".buoyLight").show();
	}else if(currentClass=="浮标"){
		$(".drogue").show();
	}
} ;

//一句话描述表格（单位显示）		
function unitChange(){		
	if(xmzl=="执勤道路"||xmzl=="铁丝网"||xmzl=="铁栅栏"||xmzl=="报警线路"||xmzl=="传输线路"){		
	        $(".depiction .unit").text("公里");		
    }else if(xmzl=="桥梁"||xmzl=="执勤码头"||xmzl=="报警装置"||xmzl=="执勤房"||xmzl=="了望塔"||xmzl=="标志牌"||xmzl=="灯塔"){		
	        $(".depiction .unit").text("座");		
    }else if(xmzl=="直升机停机坪"||xmzl=="拒马"||xmzl=="监控中心"||xmzl=="监控站"||xmzl=="视频前端"||xmzl=="显控终端"||xmzl=="灯浮"||xmzl=="浮标"){		
        $(".depiction .unit").text("个");		
    }else if(xmzl=="拦阻桩"||xmzl=="无线传输"||xmzl=="供电系统"||xmzl=="军警民联防平台"||xmzl=="无人值守电子哨兵"){		
        $(".depiction .unit").text("套");		
    }else if(xmzl=="隔离带"){		
        $(".depiction .unit").text("米");		
    }else if(xmzl=="其他拦阻设施"){		

    };		
};
//修改时显示附件
function showFj(data){
	if(data!=""&&data!=null&&data!="null"){//附件
		$(".zip-list:visible .fileNameView").hide();
		var fileArr = data.split(",");
		var jhFjIndex = -1;//计划附件下标
		var ssFjIndex = -1;//实施附件下标
		var whFjIndex = -1;//维护附件下标
		var currentModFjCount = 0;//当前模块附件数
		for(var i=0;i<fileArr.length;i++){
			if(partTag == '2' && fileArr[i].indexOf("/计划阶段/")>-1){
				currentModFjCount ++;
			}else if(partTag == '3' && fileArr[i].indexOf("/实施阶段/")>-1){
				currentModFjCount ++;
			}else if(partTag == '4' && fileArr[i].indexOf("/维护阶段/")>-1){
				currentModFjCount ++;
			}
		}
		fileCountZip = currentModFjCount;//初始化当前模块附件数
		 for(var i=0;i<fileArr.length;i++){
             var obj;
             var fj_partTag;
             if(fileArr[i].indexOf("/计划阶段/")>-1){
                 obj ="#plan";
                 fj_partTag="2";
                 jhFjIndex ++;
             }else if(fileArr[i].indexOf("/实施阶段/")>-1){
                 obj ="#implement";
                 fj_partTag="3";
                 ssFjIndex ++;
             }else if(fileArr[i].indexOf("/维护阶段/")>-1){
                 obj ="#maintenance";
                 fj_partTag="4";
                 whFjIndex ++;
             }
             var fileName=fileArr[i].substring(fileArr[i].lastIndexOf("/")+1).substring(32);
             $("#formUpdate "+obj).prev().append('<div id="" class="file-item file-exist">' +
                 '<div class="info"><span class="file-name">' + fileName + '</span></div></div>');
             if(fj_partTag==partTag){
                 //$("#formUpdate "+obj).prev().append('<span class="remove-one" title="移除文件">×</span></div></div>');
            	 if(partTag == 2){
            		 $("#formUpdate "+obj).prev().find(".info:eq("+jhFjIndex+")").append('<span class="remove-one" title="移除文件">×</span>');
            	 }else  if(partTag == 3){
            		 $("#formUpdate "+obj).prev().find(".info:eq("+ssFjIndex+")").append('<span class="remove-one" title="移除文件">×</span>');
            	 }else  if(partTag == 4){
            		 $("#formUpdate "+obj).prev().find(".info:eq("+whFjIndex+")").append('<span class="remove-one" title="移除文件">×</span>');
            	 }
			 }else{
                 //$("#formUpdate "+obj).prev().append('</div></div>');
			 }
             if(obj == "#plan"){
            	 if(jhFjIndex!=0){
            		 $("#formUpdate "+obj).prev().find(".file-item").eq(jhFjIndex).css("margin-top","10px");
            	 }
             }
             if(obj == "#implement"){
            	 if(ssFjIndex!=0){
            		 $("#formUpdate "+obj).prev().find(".file-item").eq(ssFjIndex).css("margin-top","10px");
            	 }
             }
             if(obj == "#maintenance"){
            	 if(whFjIndex!=0){
            		 $("#formUpdate "+obj).prev().find(".file-item").eq(whFjIndex).css("margin-top","10px");
            	 }
             }
 
             if(i==fileArr.length-1){
                 if($("#formUpdate #plan").prev().children().length==1){
                     /*$("#formUpdate #plan").prev().append('<div id="" class="file-item file-exist">' +
                         '<div class="info"><span class="file-name">&nbsp;</span></div></div>');*/
                	 $("#formUpdate #plan").parent().find(".fileNameView").show();
                 }
                 if($("#formUpdate #implement").prev().children().length==1){
                     /*$("#formUpdate #implement").prev().append('<div id="" class="file-item file-exist">' +
                         '<div class="info"><span class="file-name">&nbsp;</span></div></div>');*/
                	 $("#formUpdate #implement").parent().find(".fileNameView").show();
                 }
                 if($("#formUpdate #maintenance").prev().children().length==1){
                     /*$("#formUpdate #maintenance").prev().append('<div id="" class="file-item file-exist">' +
                         '<div class="info"><span class="file-name">&nbsp;</span></div></div>');*/
                	 $("#formUpdate #maintenance").parent().find(".fileNameView").show();
                 }
             }
		 }
	}
	$(".remove-one").click(function() {
		var fjCount = $(this).parents(".project_item:eq(0)").find(".file-item").length;
        var $item = $(this).parents(".file-item");
        var fileName = $item.text().substring(0,$item.text().length-1);
        var fileArr =$(".editFj").val().split(",");
    	for(var i=0;i<fileArr.length;i++){
    		if(fileArr[i].indexOf(fileName)>-1){
    			if(partTag == '2' && fileArr[i].indexOf('/计划阶段/') > -1){
    				fileArr.splice(i,1);
    			}
    			if(partTag == '3' && fileArr[i].indexOf('/实施阶段/') > -1){
    				fileArr.splice(i,1);
    			}
    			if(partTag == '4' && fileArr[i].indexOf('/维护阶段/') > -1){
    				fileArr.splice(i,1);
    			}
        	}
    	};
    	$(".editFj").val(fileArr.join(","));
    	//如果删除的是第一个，那么他的下一个应当去掉margin-top
        if($item.prev().hasClass("fileNameView")){
        	$item.next(".file-item").css("margin-top","0px");
		}
        $item.remove();
        fileCountZip--;
        fjCount--;
        if(fjCount==0){
        	var objStr = "";
        	if(partTag =='2'){
        		objStr = "#plan_file";
        	}else if(partTag =='3'){
        		objStr = "#implement_file";
        	}else if(partTag =='4'){
        		objStr = "#maintenance_file";
        	}
        	$("#formUpdate").find(objStr).show();
        }
    });
};
//查看信息时显示附件
function showFj_info(data){
	$("#formUpdate .editFj").val("");//修改时$("#formUpdate .editFj")只要一个对象，查看详情需要清空此值，后续会一一添加。
	$("#formUpdate #plan").prev().find(".file-exist").remove();
	$("#formUpdate #implement").prev().find(".file-exist").remove();
	$("#formUpdate #maintenance").prev().find(".file-exist").remove();
	 
	if(data!=""&&data!=null&&data!="null"){
		$(".zip-list:visible .fileNameView").hide();
		var fileArr = data.split(",");
		/*fileCountZip = fileArr.length;*/
		var jhFjIndex = -1;//计划附件下标
		var ssFjIndex = -1;//实施附件下标
		var whFjIndex = -1;//维护附件下标
		for(var i=0;i<fileArr.length;i++){
			var obj;
			if(fileArr[i].indexOf("/计划阶段/")>-1){
				obj ="#plan";
				fj_partTag="2";
                jhFjIndex ++;
			}else if(fileArr[i].indexOf("/实施阶段/")>-1){
				obj ="#implement";
				 fj_partTag="3";
                 ssFjIndex ++;
			}else if(fileArr[i].indexOf("/维护阶段/")>-1){
				obj ="#maintenance";
				 fj_partTag="4";
                 whFjIndex ++;
			}
			var fileName=fileArr[i].substring(fileArr[i].lastIndexOf("/")+1).substring(32);
            fileArr[i]=fileArr[i].replace(basePath+"/upload/","").replace("http://localhost:8080/BHF/upload/","");
            $("#formUpdate "+obj).prev().append('<div id="" class="file-item file-exist">' +
				'<div class="info"><span class="file-name">'+ fileName +'</span></div></div>');
				var s =' <button type="button" onclick="downLoadFile($(this))">下 载</button>' +
				
            	'<label style="display:none;" class="editFj">'+fileArr[i]+'</label></div>';
			if(fj_partTag == '2'){
				$("#formUpdate "+obj).prev().find(".info:eq("+jhFjIndex+")").after('<button type="button" onclick="downLoadFile($(this))">下 载</button><label style="display:none;" class="editFj">'+fileArr[i]+'</label></div>');
			}else  if(fj_partTag == '3'){
				$("#formUpdate "+obj).prev().find(".info:eq("+ssFjIndex+")").after('<button type="button" onclick="downLoadFile($(this))">下 载</button><label style="display:none;" class="editFj">'+fileArr[i]+'</label></div>');
			}else  if(fj_partTag == '4'){
				$("#formUpdate "+obj).prev().find(".info:eq("+whFjIndex+")").after('<button type="button" onclick="downLoadFile($(this))">下 载</button><label style="display:none;" class="editFj">'+fileArr[i]+'</label></div>');
			}
				
			if(i!=0){
				$("#formUpdate "+obj).prev().find(".file-item").eq(i).css("margin-top","10px");
            }
            if(i==fileArr.length-1){
				if($("#formUpdate #plan").prev().children().length==1){
                    /*$("#formUpdate #plan").prev().append('<div id="" class="file-item file-exist">' +
                        '<div class="info"><span class="file-name">&nbsp;</span></div></div>');*/
					 $("#formUpdate #plan").parent().find(".fileNameView").show();
				}
                if($("#formUpdate #implement").prev().children().length==1){
                    /*$("#formUpdate #implement").prev().append('<div id="" class="file-item file-exist">' +
                        '<div class="info"><span class="file-name">&nbsp;</span></div></div>');*/
                	 $("#formUpdate #implement").parent().find(".fileNameView").show();
                }
                if($("#formUpdate #maintenance").prev().children().length==1){
                   /* $("#formUpdate #maintenance").prev().append('<div id="" class="file-item file-exist">' +
                        '<div class="info"><span class="file-name">&nbsp;</span></div></div>');*/
                	$("#formUpdate #maintenance").parent().find(".fileNameView").show();
                }
			}
		}
	}else{
		$("#formUpdate #plan").parent().find(".fileNameView").show();
		$("#formUpdate #implement").parent().find(".fileNameView").show();
		$("#formUpdate #maintenance").parent().find(".fileNameView").show();
	}
};

function loadjkzname(obj,objTxt){
	if(obj=="传输线路"){
		$("#csxl").val(objTxt);
	}else if(obj=="供电系统"){
		$("#gdxt").val(objTxt);
	}else if(obj=="视频前端"){
		$("#spqd").val(objTxt);
	}else if(obj=="显控终端"){
		$("#xkzd").val(objTxt);
	}else if(obj=="报警装置"){
		$("#bjzz").val(objTxt);
	}
}

/**
 * 加载市级数据
 * @param data   表单数据
 * @param superObj   JQuery对象
 */
function loadCity(data,superObj) {
	var szsf = data.szsf;
	//根据省加载市数据
    var szcs = data.szcs;
    if(szcs == null || szcs ==""){
        $(superObj+" #address2").empty();
        $(superObj+" #address2").hide();
	}
	$.ajax({
		url:"area/loadCity",
        data:"szsf="+szsf,
        async:false,
        dataType:"json",
        success:function (data) {
            if(data!=null && data.length>0){
                $(superObj+" #address2").show();
                $(superObj+" #address2").empty();
                $(superObj+" #address2").append("<option value='0'>--请选择市--</option>");
                for(var i=0;i<data.length;i++){
                	if(szcs==data[i].codeName){
                        $(superObj+" #address2").append("<option value='"+data[i].codeValue+"' selected>"+data[i].codeName+"</option>");
                    }else{
                        $(superObj+" #address2").append("<option value='"+data[i].codeValue+"' >"+data[i].codeName+"</option>");
					}
				}
			}
		}
	})
}

/**
 * 加载区（县）数据
 * @param data
 * @param superObj
 */
function loadQu(data,superObj) {
    var szsf = data.szsf;
    var szcs = data.szcs;
    var szq = data.szq;
    if(szq==null || szq == ""){
        $(superObj+" #address3").empty();
        $(superObj+" #address3").parent().hide();
	}
    $.ajax({
        url:"area/loadQu",
        data:"szsf="+szsf+"&szcs="+szcs,
        async:false,
        dataType:"json",
        success:function (data) {
            if(data!=null && data.length>0){
            	$(superObj+" #address3").parent().show();
                $(superObj+" #address3").empty();
                $(superObj+" #address3").append("<option value='0'>--请选择区--</option>");
                for(var i=0;i<data.length;i++){
                    if(szq==data[i].codeName){
                        $(superObj+" #address3").append("<option value='"+data[i].codeValue+"' selected>"+data[i].codeName+"</option>");
                    }else{
                        $(superObj+" #address3").append("<option value='"+data[i].codeValue+"' >"+data[i].codeName+"</option>");
                    }
                }
            }
        }
    })
}
//验证建设区域
function validateJsqy(formid){
	var szsf = $(formid + " #address1").val();
	var errorSpan = "<span class='jsqyTips'>请选择省份</span>";
	if(szsf == '0'){
		$(formid + " #address1").parent().append(errorSpan);
		jsqyFocus($(formid + " #address1"));
		 //滚动条滚动到错误位置
	    var objPixelScrollIntoView = $(formid + " #address1").parents(".project_item")[0].offsetTop;
	    $("#scrollForm").mCustomScrollbar("scrollTo",objPixelScrollIntoView);
		return false;
	}
	var szcs = $(formid + " #address2").val();
	errorSpan = "<span class='jsqyTips'>请选择市</span>";
	if(szcs == '0'){
		$(formid + " #address2").parent().append(errorSpan);
		jsqyFocus($(formid + " #address2"));
		var objPixelScrollIntoView = $(formid + " #address2").parents(".project_item")[0].offsetTop;
	    $("#scrollForm").mCustomScrollbar("scrollTo",objPixelScrollIntoView);
		return false;
	}
	var szq = $(formid + " #address3").val();
	errorSpan = "<span class='jsqyTips'>请选择区</span>";
	if(szq == '0'){
		$(formid + " #address3").parent().append(errorSpan);
		jsqyFocus($(formid + " #address3"));
		var objPixelScrollIntoView = $(formid + " #address3").parents(".project_item")[0].offsetTop;
	    $("#scrollForm").mCustomScrollbar("scrollTo",objPixelScrollIntoView);
		return false;
	}
	return true;
}

//错误对象高亮
function jsqyFocus(obj){
    obj.addClass("focused");
    setTimeout(function(){
        obj.removeClass("focused");
    },2000);
}
function validateDate(obj){
	var b = true;
	var ztbsj=$(obj+" div#ztbsj .textbox-value").val();//招投标时间
	var kgsj=$(obj+" div#kgsj .textbox-value").val();//开工时间
	var jgsj=$(obj+" div#jgsj .textbox-value").val();//竣工时间
	var cysj=$(obj+" div#cysj .textbox-value").val();//初验时间
	var sjsj=$(obj+" div#sjsj .textbox-value").val();//审计时间
	var ztbsjStr = ztbsj.replace(/[\-]/g,'');
	var kgsjStr = kgsj.replace(/[\-]/g,'');
	var jgsjStr = jgsj.replace(/[\-]/g,'');
	var cysjjStr = cysj.replace(/[\-]/g,'');
	var sjsjStr = sjsj.replace(/[\-]/g,'');
	
	if(ztbsjStr != "" && kgsjStr != "" && parseInt(kgsjStr) <= parseInt(ztbsjStr)){
		$("#warning").show().dialog("open");
		$(".editOne").html("开工时间不能早于招投标时间！");
		 b=false;
	}
	if( kgsjStr != "" && jgsjStr != "" && parseInt(jgsjStr) <= parseInt(kgsjStr)){
		$("#warning").show().dialog("open");
		$(".editOne").html("竣工时间不能早于开工时间！");
		 b=false;
	}
	if(cysjjStr != "" && jgsjStr != "" && parseInt(cysjjStr) <= parseInt(jgsjStr)){
		$("#warning").show().dialog("open");
		$(".editOne").html("初验时间不能早于竣工时间！");
		 b=false;
	}
	if(sjsjStr != "" && cysjjStr != "" && parseInt(sjsjStr) <= parseInt(cysjjStr)){
		$("#warning").show().dialog("open");
		$(".editOne").html("审计时间不能早于初验时间！");
		 b=false;
	}
	return b;
}

function formatDate(oldTime){
	if(oldTime!=null && oldTime!=""){
		var unixTimestamp = new Date( oldTime*1 ) ;
	    return unixTimestamp.toLocaleString();
	}
};


/**
 * 数据列表附件转化
 */
function transFj(){
	$(".file-name").each(function(){
		var value = $(this).text();
		if(value){
			var arr = new Array();
			var fileArr = value.split(",");
			for(var i=0;i<fileArr.length;i++){
				fileArr[i] = fileArr[i].substring(fileArr[i].lastIndexOf("/")+1).substring(32);
				arr.push(fileArr[i]);
			}
			$(this).text(arr.join(","));
			$(this).attr("title",arr.join(","));
		}
	});
}

/**
 * 全选
 */
function selectAll(obj){
    var checkBoxArr = $("tbody input");
	if(!obj.find("input").prop("checked")){
		$(".tabList thead .checkboxOne").find("input").prop("checked", false);
		return;
	}
	for(var i=0;i<checkBoxArr.length;i++) {
		if(checkBoxArr[i].checked){
			if(i==checkBoxArr.length-1){
				$(".tabList thead .checkboxOne").find("input").prop("checked", true);
			}
		}else{
			return;
		}
	}
}