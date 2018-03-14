<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <!--让ie使用兼容性模式-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
    <link rel="icon" href="BHF.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/common.css">
    <script src="js/libs/jquery-1.8.3.min.js"></script>
    <script>
     var loginSum = 0;  

     var user;

    $(function(){
        setTime();
        setInterval(setTime,1000);
        GetLastUser();

        //input输入框去空格
        $("input[type='text']").blur(function(){
            var value = $.trim(this.value);
            this.value = value;
        });
    });
        function setTime(){
            var now = new Date();
            var year = now.getFullYear();
            var month = now.getMonth() + 1;
            var day = now.getDate();
            var hour = now.getHours();
            var minutes = now.getMinutes();
            var second = now.getSeconds();
            if(second<10){
                second = '0'+second;
            }
            if(minutes<10){minutes="0"+minutes;}
            $(".time").text(year+"年"+month+"月"+day+"日"+" "+hour+":"+minutes+":"+second);
        };
        //用户名密码验证
        function validate(){
            if($("#username").val()==""||$("#username").val()==null||$("#userpwd").val()==""||$("#userpwd").val()==null){
                $("#error").text("请输入用户名和密码！");
            }else{
                $("#error").text("");
		        
		        //判断重复提交   
		        if(loginSum == 0){  
		            loginSum = 1;  
		        }else{
		        	return;
		        } 
                 //提交  
		        $.ajax({    
		            url:"user/login",    
		            type:'POST',    
		            data:$("#loginForm").serialize(),    
		            dataType:'text',  
		            error:function(){  
		                loginSum = 0;  
		            },  
		            success:function (data){   
		                if(data == 'loginFailed'){ 
		                	$("#error").text("用户名或密码错误");
		                    loginSum = 0;  
		                   
		                }else{
		                	//保存密码  
		                    SetPwdAndChk(); 
		                	if(data == 'userManager'){
		                		window.location.href="managerSystem/index.jsp";
		                	}else{
		                    	window.location.href="user/toWelcome";  
		                	}
		                };  
		            }  
        		});
            }
        } 
         //=============js cookie===============  
  
    function GetLastUser() {  
        var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";  
        user = GetCookie(id);
        if (user != null) {
            document.getElementById('username').value = user;
        } else {  
            //document.getElementById('username').value = "001";  
        }  
        GetPwdAndChk();  
    }; 
    //点击登录时触发客户端事件   
    function SetPwdAndChk() {  
        //取用户名   
        var usr = document.getElementById('username').value;  
        //将最后一个用户信息写入到Cookie   
        SetLastUser(usr);  
        //如果记住密码选项被选中   
        var checked = document.getElementById('remember').checked;  
        if (checked == true) {  
            //取密码值   
            var pwd = document.getElementById('userpwd').value;  
            var expdate = new Date();  
            expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));  
            //将用户名和密码写入到Cookie   
            SetCookie(usr, pwd, expdate);  
        } else {  
            //如果没有选中记住密码,则立即过期   
            ResetCookie();  
        }  
    };  
    function SetLastUser(usr) {  
        var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";  
        var expdate = new Date();  
        //当前时间加上两周的时间   
        expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));  
        SetCookie(id, usr, expdate);  
    };  
    //用户名失去焦点时调用该方法   
    function GetPwdAndChk() {  
        var usr = document.getElementById('username').value;  
        var pwd = GetCookie(usr);  
        if (pwd != null) {  
            document.getElementById('remember').checked = true;  
            document.getElementById('userpwd').value = pwd;  
        } else {  
            document.getElementById('remember').checked = false;  
            document.getElementById('userpwd').value = "";  
        }  
    };  
    //取Cookie的值   
    function GetCookie(name) {  
        var arg = name + "=";  
        var alen = arg.length;  
        var clen = document.cookie.length;  
        var i = 0;  
        while (i < clen) {  
            var j = i + alen;  
            if (document.cookie.substring(i, j) == arg)  
                return getCookieVal(j);  
            i = document.cookie.indexOf(" ", i) + 1;  
            if (i == 0)  
                break;  
        }  
        return null;  
    };  
  
    function getCookieVal(offset) {  
        var endstr = document.cookie.indexOf(";", offset);  
        if (endstr == -1)  
            endstr = document.cookie.length;  
        return unescape(document.cookie.substring(offset, endstr));  
    };  
    //写入到Cookie   
    function SetCookie(name, value, expires) {  
        var argv = SetCookie.arguments;  
        //本例中length = 3   
        var argc = SetCookie.arguments.length;  
        var expires = (argc > 2) ? argv[2] : null;  
        var path = (argc > 3) ? argv[3] : null;  
        var domain = (argc > 4) ? argv[4] : null;  
        var secure = (argc > 5) ? argv[5] : false;  
        document.cookie = name  
                + "="  
                + escape(value)  
                + ((expires == null) ? "" : ("; expires=" + expires  
                        .toGMTString()))  
                + ((path == null) ? "" : ("; path=" + path))  
                + ((domain == null) ? "" : ("; domain=" + domain))  
                + ((secure == true) ? "; secure" : "");  
    };  
    function ResetCookie() {  
        var usr = document.getElementById('username').value;
        var expdate = new Date();  
        SetCookie(usr, null, expdate);  
    };  
    //enter键提交表单登陆
    function enterKeyLogin(e){
        if(e.key == "Enter"){//回车键
            var loginName = $("#username").val();
            if(loginName=="" || $("#userpwd").val()==""){
                return false;
            }
            if(user!=null && user!="" && loginName !=user && $("#username").is(":focus")){
                document.getElementById('remember').checked = false;
                document.getElementById('userpwd').value = "";
                return false;
            }
            validate();
        }
    }
    </script>
</head>
<body onkeydown="enterKeyLogin(event);">
    <div class="lg_center">
       <div class="login_text"></div>   
       <form id="loginForm" class="panel_body">
       <div class="panel_main">
	       	<div class="login_info">
	           <div class="panel_user">
	           	<div class="panel_label_user"></div>
	           	<input onblur="GetPwdAndChk();" type="text" id="username" name="loginName" placeholder="请输入用户名">
	           </div>
	           <div class="panel_pass">
	           	<div class="panel_label_pass"></div>
	           	<input id="userpwd" name="pwd" type="password" placeholder="请输入密码">
	           </div>
	         </div>
           <div class="btn_group">
               <button type="button" onclick="validate();" class="lg_btn" style="cursor:pointer;"></button>
               <div class="clear"></div>
           </div>
       </div>
       <div class="clear"></div>
       <div class="info_rem">
	       <span id="error" class="error"><c:if test="${ msg != null}">${msg }</c:if></span>
	       <div class="checkbox_group">
	           <input type="checkbox" class="checkbox" id="remember" name="" style="visibility:visible;cursor:pointer;"/>
	           <label class="checkbox_rem" for="remember" style="cursor:pointer;">记住密码</label>
	       </div>
       </div>
           
       </form>
 </div>
</body>
</html>
