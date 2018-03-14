<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<input type="hidden" id="szsfMap" value="${gjzbMap.areaMap }"/>
<input type="hidden" id="xmlbMap" value="${gjzbMap.xmlbMap }"/>
<input type="hidden" id="sslxMap" value="${gjzbMap.sslxMap }"/>
<div class="chartsGroup">
    <div class="chart_panel column1 fl" style="display:${loginUser.province eq '天津市' || loginUser.province eq '上海市' ? 'none' : '' }; height:380px;width:65.5%;" >
        <div class="cp_header">${loginUser.province }<c:if test="${not empty gjzbMap.year }">${gjzbMap.year }年</c:if>各地市基础设施建设投资柱状图</div>
        <div id="container" class="chart_item" style="min-height:300px;"></div>
    </div>
    <div class="chart_panel column2 fl" style="width:${loginUser.province eq '天津市' || loginUser.province eq '上海市' ? '98.05%' : '32.15%' };height:380px;">
        <div class="cp_header">${loginUser.province }<c:if test="${not empty gjzbMap.year }">${gjzbMap.year }年</c:if>基础设施建设投资柱状图</div>
        <div id="container4" class="chart_item" style="min-height:300px;"></div>
    </div>
    <div class="chart_panel column2 fl" style="margin-left: ${loginUser.province eq '天津市' || loginUser.province eq '上海市' ? '1.1%' : '0' };width:${loginUser.province eq '天津市' || loginUser.province eq '上海市' ? '48.225%' : '32.15%' };height:380px;">
        <div class="cp_header">${loginUser.province }<c:if test="${not empty gjzbMap.year }">${gjzbMap.year }年</c:if>各类设施投资比例图</div>
        <div id="container1" class="chart_item" style="min-height:300px;"></div>
    </div>
    <div class="chart_panel column2 fl" style="display:${loginUser.province eq '天津市' || loginUser.province eq '上海市' ? 'none' : '' };height:380px;margin-left:1.1%">
        <div class="cp_header">${loginUser.province }<c:if test="${not empty gjzbMap.year }">${gjzbMap.year }年</c:if>各地市投资比例图</div>
        <div id="container2" class="chart_item" style="min-height:300px;"></div>
    </div>
    <div class="chart_panel column2 fl" style="margin-left: 1.5%;width:${loginUser.province eq '天津市' || loginUser.province eq '上海市' ? '48.225%' : '32.15%' };height:380px;">
        <div class="cp_header">${loginUser.province }<c:if test="${not empty gjzbMap.year }">${gjzbMap.year }年</c:if>基础设施建设投资比例图</div>
        <div id="container3" class="chart_item" style="min-height:300px;"></div>
    </div>
    <div class="clear"></div>
</div>