<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>运维管理系统首页</title>
    <link rel="icon" href="../BHF.ico" type="image/x-icon"/>
    <script src="scripts/boot.js" type="text/javascript"></script>
    <link href="css/demo.css" type="text/css"/>
    <style type="text/css">
        body{
            margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
        }
        .header{
            background:url("img/header.gif") repeat-x 0 -1px;
        }
        .forLoginUserAll{
            width: 100%;
            height: 100%;
        }
        .forLoginUser{
            display: inline-block;
            float:left;
            width: 40px;
            height: 40px;
            border-radius: 40px;
            background: #2467a6;
            line-height: 40px;
            text-align: center;
            font-size:1.5em;
            font-family:"微软雅黑";
            margin-left: 1%;
            margin-top: 4%;

        }
        .divUser{
             display: inline-block;
            float:left;
            width: 40%;
            height: 40px;
            margin-left: 3%;
            line-height:20px ;
            margin-top: 4%;
            font-size:1.2em;
            color: white;
            font-family:"微软雅黑";
        }
        .forLoginOut{
            display: inline-block;
            float: right;
            height: 100%;
            width: 40%;
        }
        .forLoginOut0{
            width: 50%;
            height:30px;
            line-height: 30px;
            border:solid 1px #3A88D8;
            margin-top: 10%;
            font-family:"微软雅黑";
            text-align: center;
            font-size:1.4em;
            color: #3A88D8;

        }
    </style>
</head>
<body>
<input id="loginUserProvince" value="${loginUser.province}" type="hidden"/>
<input id="loginUserID" value="${loginUser.id}" type="hidden"/>
<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
    <div class="header" region="north" height="70" showSplit="false" showHeader="false" style="background: rgb(6, 75, 113);position: relative">
        <img src="img/manager_logo.png" class="logo" title="" alt="" height="100%"/>
        <div style="position:fixed;top:0px;right:0px; width: 380px;height: 70px;">
            <div class="forLoginUserAll">
            <div class="forLoginUser">管</div>
            <div class="divUser">${loginUser.province}<br>管理员</div>
                <div class="forLoginOut">
                    <a href="../user/exitLogin" style=" text-decoration: none;"><div class="forLoginOut0"><img src="../img/exit.png" alt="">&nbsp&nbsp退&nbsp出</div></a>
            </div>
            </div>
        </div>
    </div>
    <div title="center" region="center" style="border:0;" bodyStyle="overflow:hidden;">
        <!--Splitter-->
        <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0;">
            <div size="180" maxSize="250" minSize="100" showCollapseButton="true" style="border:0;background: #E8EFF7;">
                <!--OutlookTree-->
                <div id="leftTree" class="mini-outlooktree" url="data/outlooktree.txt" onnodeclick="onNodeSelect"
                     textField="text" idField="id" parentField="pid">
                </div>
            </div>
            <div showCollapseButton="false" style="border:0;">
                <!--Tabs-->
                <div id="mainTabs" class="mini-tabs" activeIndex="2" style="width:100%;height:100%;"
                     plain="false" onactivechanged="onTabsActiveChanged">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    mini.parse();

    var tree = mini.get("leftTree");

    $(function(){
        var node = {id:"userManager",text:"用户管理"};
        showTab(node);
    })

    function showTab(node) {
        var tabs = mini.get("mainTabs");
        var id = node.id;
        var tab = tabs.getTab(id);
        if (!tab) {
            tab = {};
            tab._nodeid = node.id;
            tab.name = id;
            tab.title = node.text;
            tab.showCloseButton = true;

            tab.url = node.id + ".html";

            tabs.addTab(tab);
        }
        tabs.activeTab(tab);
    }

    function onNodeSelect(e) {
        var node = e.node;
        var isLeaf = e.isLeaf;

        if (isLeaf) {
            showTab(node);
        }
    }

    //退出
    function exit() {
        location.href="../user/exitLogin"
    }
    function onQuickClick(e) {
        tree.expandPath("datagrid");
        tree.selectNode("datagrid");
    }

    function onTabsActiveChanged(e) {
        var tabs = e.sender;
        var tab = tabs.getActiveTab();
        if (tab && tab._nodeid) {
            var node = tree.getNode(tab._nodeid);
            if (node && !tree.isSelectedNode(node)) {
                tree.selectNode(node);
            }
        }
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
            exit();
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
</html>
