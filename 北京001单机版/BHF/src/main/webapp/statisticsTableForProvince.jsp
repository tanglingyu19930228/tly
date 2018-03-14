<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="chart_panel excel_panel">
    <div class="cp_header" style="position: relative;">
        <span id="title">${loginUser.province }<c:if test="${not empty gjzbMap.year }">${gjzbMap.year }年</c:if>基础设施建设规划表</span>
        <div class="excel_btns">
            <c:if test="${partTag eq '1'}">
                <select class="yearSelect fl" onchange="changeTznd(this);" id="tznd">
                    <option value="">请选择年份</option>
                    <c:forEach items="${gjzbMap.yearList }" var="item">
                        <option value="${item }" ${gjzbMap.year eq item ? 'selected' : '' }>${item }年</option>
                    </c:forEach>
                </select>
            </c:if>
            <button type="button" class="btn_tj">导入数据</button>
            <button type="button" class="btn_tj">导出数据</button>
            <c:if test="${partTag eq '2'}">
                <button type="button" class="btn_tj">生成报文</button>
            </c:if>
            <button id="exportExcel" type="button" class="btn_tj">生成报表</button>
            <a id="tableToExcle" style="display:none;"></a>
        </div>
    </div>
    <table class="basicPlanT" id="table" cellpadding=0 cellspacing=0 border="1" style="border:1px solid #585858;">
        <tbody>
        <tr style="height: 68px;" class="province">
            <td colspan="2" class="noBR t_header" style="padding-top:35px; ">项目</td>
            <td class="noBR t_header" style="padding-top: 10px;">数量</td>
            <td class="t_header" style="padding: 0 0 35px 25px;">单位</td>
            <c:forEach items="${areaList }" var="area">
                <th class="provLink">${area }</th>
            </c:forEach>
            <th class="tLineH">合计</th>
            <th class="thposition">平均造价<br/><span class="thabsolute">(万元)</span></th>
            <th class="thposition">单项投资<br/><span class="thabsolute">(万元)</span></th>
            <th class="thposition">中央投资<br/><span class="thabsolute">(万元)</span></th>
            <th class="thposition noBR">地方投资<br/><span class="thabsolute">(万元)</span></th>
        </tr>
        <%-- <tr class="bjxTr" height="30px;">
            <th colspan="4">边界、海岸线长度<span>（公里）</span><a class="bjxEditLink" style="display:none;">编辑</a></th>
            <c:forEach items="${gjzbMap.bjHaxCdList }" var="item" varStatus="index">
                <td class="bjxEditTd"><div class="bjxEditItems" contenteditable="false">${item }</div></td>
            </c:forEach>
            <c:if test="${empty gjzbMap.bjHaxCdList }">
                 <c:forEach items="${areaList }">
                    <td class="bjxEditTd"><div class="bjxEditItems" contenteditable="false"></div></td>
                </c:forEach>
                    <td class="bjxEditTd"><div class="bjxEditItems" contenteditable="false"></div></td>
            </c:if>
        </tr> --%>
        <tr>
            <th rowspan="6" class="tWidth1">交通保障设施</th>
            <td rowspan="2" class="tWidth2">执勤道路</td>
            <td colspan="2">新建（公里）</td>
            <c:forEach items="${gjzbMap.zqdlXj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="2">升级（公里）</td>
            <c:forEach items="${gjzbMap.zqdlSj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">桥梁（座）</td>
            <c:forEach items="${gjzbMap.qiaoLiang }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">执勤码头（座）</td>
            <c:forEach items="${gjzbMap.zqmt }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">直升机停机坪（个）</td>
            <c:forEach items="${gjzbMap.zsjtjp }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">小　计</td>
            <c:forEach items="${areaList }">
                <td></td>
            </c:forEach>
            <td></td>
            <td></td>
            <c:forEach items="${gjzbMap.jtbzssSub }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <th rowspan="13" class="tWidth1">拦阻报警设施</th>
            <td rowspan="4" class="tpad1">铁丝网</td>
            <td rowspan="2" class="tWidth5">新建</td>
            <td class="tWidth3">固定式（公里）</td>
            <c:forEach items="${gjzbMap.tswXjGds }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td>移动式（公里）</td>
            <c:forEach items="${gjzbMap.tswXjYds }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td rowspan="2">升级</td>
            <td class="tWidth3">固定式（公里）</td>
            <c:forEach items="${gjzbMap.tswSjGds }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td class="tWidth3">移动式（公里）</td>
            <c:forEach items="${gjzbMap.tswSjYds }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">铁栅栏（公里）</td>
            <c:forEach items="${gjzbMap.tzl }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td rowspan="4" class="tpad1">拦阻桩</td>
            <td rowspan="2">新建</td>
            <td>固定式（套）</td>
            <c:forEach items="${gjzbMap.lzzXjGds }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td>自动升降式（套）</td>
            <c:forEach items="${gjzbMap.lzzXjSjs }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td rowspan="2">升级</td>
            <td>固定式（套）</td>
            <c:forEach items="${gjzbMap.lzzSjGds }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td>自动升降式（套）</td>
            <c:forEach items="${gjzbMap.lzzSjSjs }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">隔离带（米）</td>
            <c:forEach items="${gjzbMap.gld }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">报警线路（公里）</td>
            <c:forEach items="${gjzbMap.bjxl }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">拒马（个）</td>
            <c:forEach items="${gjzbMap.juma }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">小　计</td>
            <c:forEach items="${areaList }">
                <td></td>
            </c:forEach>
            <td></td>
            <td></td>
            <c:forEach items="${gjzbMap.lzbjssSub }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <th rowspan="22" class="tWidth1">指挥监控设施</th>
            <td rowspan="8" class="tpad1">监控中心</td>
            <td rowspan="2">国家级</td>
            <td>新建（个）</td>
            <c:forEach items="${gjzbMap.jkzxGjjXj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td>升级（个）</td>
            <c:forEach items="${gjzbMap.jkzxGjjSj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td rowspan="2">省级</td>
            <td>新建（个）</td>
            <c:forEach items="${gjzbMap.jkzxSjXj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td>升级（个）</td>
            <c:forEach items="${gjzbMap.jkzxSjSj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td rowspan="2">地级</td>
            <td>新建（个）</td>
            <c:forEach items="${gjzbMap.jkzxDjXj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td>升级（个）</td>
            <c:forEach items="${gjzbMap.jkzxDjSj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td rowspan="2">县级</td>
            <td>新建（个）</td>
            <c:forEach items="${gjzbMap.jkzxXjXj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td>升级（个）</td>
            <c:forEach items="${gjzbMap.jkzxXjSj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td rowspan="8" class="tpad1">监控站</td>
            <td rowspan="2" class="tpad2">监控站系统</td>
            <td>新建（个）</td>
            <c:forEach items="${gjzbMap.jkzxtXj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td>升级（个）</td>
            <c:forEach items="${gjzbMap.jkzxtSj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td rowspan="2" class="tpad2">显控终端</td>
            <td>新建（个）</td>
            <c:forEach items="${gjzbMap.xkzdXj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td>升级（个）</td>
            <c:forEach items="${gjzbMap.xkzdSj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td rowspan="2" class="tpad2">固定前端</td>
            <td>新建（个）</td>
            <c:forEach items="${gjzbMap.gdqdXj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td>升级（个）</td>
            <c:forEach items="${gjzbMap.gdqdSj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td rowspan="2" class="tpad2">移动前端</td>
            <td>新建（个）</td>
            <c:forEach items="${gjzbMap.ydqdXj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td>升级（个）</td>
            <c:forEach items="${gjzbMap.ydqdSj }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">传输线路（公里）</td>
            <c:forEach items="${gjzbMap.csxl }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">供电系统（套）</td>
            <c:forEach items="${gjzbMap.gdxt }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">军警民联防平台（套）</td>
            <c:forEach items="${gjzbMap.jjmlfpt }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">无人值守电子哨兵（套）</td>
            <c:forEach items="${gjzbMap.wrzsdzsb }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>

        <tr>
            <td colspan="3">报警装置（套）</td>
            <c:forEach items="${gjzbMap.bjzz }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">小　计</td>
            <c:forEach items="${areaList }">
                <td></td>
            </c:forEach>
            <td></td>
            <td></td>
            <c:forEach items="${gjzbMap.zhjkssSub }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <th rowspan="4" class="tWidth1">辅助配套设施</th>
            <td colspan="3">执勤房（座）</td>
            <c:forEach items="${gjzbMap.zqf }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">了望塔（座）</td>
            <c:forEach items="${gjzbMap.lwt }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">标志牌（座）</td>
            <c:forEach items="${gjzbMap.bzp }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <td colspan="3">小　计</td>
            <c:forEach items="${areaList }">
                <td></td>
            </c:forEach>
            <td></td>
            <td></td>
            <c:forEach items="${gjzbMap.fzptssSub }" var="item" varStatus="index">
                <c:if test="${!index.last}" >
                    <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
                <c:if test="${index.last}" >
                    <td class="noBR"><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
                </c:if>
            </c:forEach>
        </tr>
        <tr>
            <th colspan="4">中央投资<span>（万元）</span></th>
            <c:forEach items="${gjzbMap.zytzList }" var="item">
                <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
            </c:forEach>
            <td></td>
            <td></td>
            <td></td>
            <td class="noBR"></td>
        </tr>
        <tr>
            <th colspan="4">地方配套<span>（万元）</span></th>
            <c:forEach items="${gjzbMap.dftzList }" var="item">
                <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
            </c:forEach>
            <td></td>
            <td></td>
            <td></td>
            <td class="noBR"></td>
        </tr>
        <tr class="lastTr">
            <th colspan="4">投资总额<span>（万元）</span></th>
            <c:forEach items="${gjzbMap.tzzeList }" var="item">
                <td><fmt:formatNumber type="number" value="${empty item ? 0 : item }" maxFractionDigits="4"/></td>
            </c:forEach>
            <td></td>
            <td></td>
            <td></td>
            <td class="noBR"></td>
        </tr>
        </tbody>
    </table>
    <canvas id="canvas" width="254" height="70" style="position: absolute;top:44px;left: 0px;z-index: 1;transition: 0.8s;"></canvas>
</div>