<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 页面头部 -->
<div class="top">
    <img src="img/inner_logo3.png" class="logo" title="" alt="" width="350" height="52"/>
    <div class="top_right fr">
        <div class="root_div fr">
            <span class="root"><i class="icons icons-root" style=" background-position:${loginUser.roleName eq '1' ? '0px -27px' : '-35px -27px'};"></i>${fn:escapeXml(loginUser.province)}</span>
            <a class="exit" href="user/exitLogin" onclick="exitLogin();"><i class="icons icons-exit"></i>退出</a>
        </div>
        <ul class="top_nav fr" style="display:${welJspTag eq '1' ? 'none' : '' };">
            <li><a href="user/toWelcome">首页</a></li>
            <li onclick="toPart('annualPlan/load',1);" style="cursor:pointer;"><a class="${partTag eq '1' ? 'current' : ''}">统筹规划</a></li>
            <li onclick="toPart('annualPlan/load',2);" style="cursor:pointer;"><a class="${partTag eq '2' ? 'current' : ''}">年度计划</a></li>
            <li onclick="toPart('implement/load',3);" style="cursor:pointer;"><a class="${partTag eq '3' ? 'current' : ''}">实施监督</a></li>
            <li class="last" onclick="toPart('usemaintenance/load',4);" style="cursor:pointer;"><a class="${partTag eq '4' ? 'current' : ''}">使用维护</a></li>
        </ul>
    </div> 
    <input type="hidden" id="role" value="${loginUser.roleName }"/>
</div>
<form action="" method="post" style="display:none;" id="postForm">
</form>
<script>
	//退出登陆
	function exitLogin(){
		location.href="user/exitLogin";
	}
	//进入引导页面
	function toWelcome(){
		location.href="user/toWelcome";
	}
	var partTag = "${partTag}";//模块标识
	var year = "${year }";//投资年度
	var roleName = "${loginUser.roleName }";
	
	function toPart(url,partTag){
		$("#postForm").empty();
		var f=$("#postForm")[0];
        f.action = url;
        f.innerHTML='<input type="hidden" name="partTag" value="'+partTag+'"/>';
        f.submit();
  	}

  	// session开始时间,刷新页面可重置
  	var globalLastSessionTime = new Date();

    /**
     * 定时器，每分钟执行一次
     * 验证session是否超时，超时则直接退出
     */
	window.setInterval(function(){
        var now = new Date();
        var past = now - globalLastSessionTime;
        if(past>1800000){
            exitLogin();
        }
    },60000);

	//按下键盘，重置session开始时间
	document.onkeydown = function () {
        globalLastSessionTime = new Date();
    }

    //监听鼠标位置，重置session开始时间
    var x ;
    var y ;
    document.onmousemove = function(event){
        var x1 = event.clientX;
        var y1 = event.clientY;
        if (x != x1 || y != y1) {
            globalLastSessionTime = new Date();
        }
        x = x1;
        y = y1;
    }
</script>