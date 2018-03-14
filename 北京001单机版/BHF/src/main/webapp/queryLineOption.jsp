<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	.showLineLabel{display:inline-block;width:130px;}
</style>
<div style="float:left;" id="queryLineOptionItem0Div">
列表显示：
</div>
<div id="queryLineOptionItem1Div">
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="全选" checked style="cursor:pointer;"/><label class="checkBoxText" style="cursor:pointer;">所有</label></label> 
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="项目类别" checked/><label class="checkBoxText">项目类别</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="项目子类" checked/><label class="checkBoxText">项目子类</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="项目名称" checked/><label class="checkBoxText">项目名称</label></label>    
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="设施类型" checked/><label class="checkBoxText">设施类型</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="边界方向" checked/><label class="checkBoxText">边界方向</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="建设区域" checked/><label class="checkBoxText">建设区域</label></label>
<!-- <label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="经纬度" checked/><label class="checkBoxText">经纬度</label></label> -->
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="经度" checked/><label class="checkBoxText">经度</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="纬度" checked/><label class="checkBoxText">纬度</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="地形类别" checked/><label class="checkBoxText">地形类别</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="建设性质" checked/><label class="checkBoxText">建设性质</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine"  visibility="visible" value="使用单位" checked/><label class="checkBoxText">使用单位</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="申报单位" checked/><label class="checkBoxText">申报单位</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="中央投资" checked/><label class="checkBoxText">中央投资</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="地方投资" checked/><label class="checkBoxText">地方投资</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="投资年度" checked/><label class="checkBoxText">投资年度</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="附件" checked/><label class="checkBoxText">附件</label></label>
<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="备注" checked/><label class="checkBoxText">备注</label></label> 
<c:if test="${xmzl ne '监控中心' and xmzl ne '监控站' and xmzl ne '军警民联防平台' and xmzl ne '无人值守电子哨兵' and xmzl ne '视频前端'
 	and xmzl ne '显控终端' and xmzl ne '报警装置' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="建设地点" checked/><label class="checkBoxText">建设地点</label></label>   
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="建设规模" checked/><label class="checkBoxText">建设规模</label></label>
</c:if>
<c:if test="${xmzl eq '执勤道路' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="道路类别" checked/><label class="checkBoxText">道路类别</label></label>
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="道路类型" checked/><label class="checkBoxText">道路类型</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="道路等级" checked/><label class="checkBoxText">道路等级</label></label>
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="路基类型" checked/><label class="checkBoxText">路基类型</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="路面类型" checked/><label class="checkBoxText">路面类型</label></label> 
</c:if>
<c:if test="${xmzl eq '桥梁' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="桥梁类型" checked/><label class="checkBoxText">桥梁类型</label> </label>
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="载重" checked/><label class="checkBoxText">载重</label></label>
</c:if>
<c:if test="${xmzl eq '直升机停机坪' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="停机坪类型" checked/><label class="checkBoxText">停机坪类型</label></label> 
</c:if>
<c:if test="${xmzl eq '铁丝网' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="铁丝网类型" checked/><label class="checkBoxText">铁丝网类型</label></label>
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="拦阻门个数" checked/><label class="checkBoxText">拦阻门个数</label></label> 
</c:if>
<c:if test="${xmzl eq '拦阻桩' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="拦阻桩类型" checked/><label class="checkBoxText">拦阻桩类型</label></label> 
</c:if>
<c:if test="${xmzl eq '报警线路' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="设备品牌" checked/><label class="checkBoxText">设备品牌</label></label>
</c:if>
<c:if test="${xmzl eq '监控中心' or xmzl eq '监控站' or xmzl eq '军警民联防平台' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="向上联通情况" checked/><label class="checkBoxText">向上联通情况</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="向上联通网络性质" checked/><label class="checkBoxText">向上联通网络性质</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="向上传输线路" checked/><label class="checkBoxText">向上传输线路</label></label>
</c:if>
<c:if test="${xmzl eq '监控中心' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="级别" checked/><label class="checkBoxText">级别</label></label> 
</c:if>
<c:if test="${xmzl eq '监控站' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="显控终端个数" checked/><label class="checkBoxText">显控终端个数</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="移动前端个数" checked/><label class="checkBoxText">移动前端个数</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="固定前端个数" checked/><label class="checkBoxText">固定前端个数</label></label> 
</c:if>
<c:if test="${xmzl eq '传输线路' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="所在监控站" checked/><label class="checkBoxText">所在监控站</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="线路起点" checked/><label class="checkBoxText">线路起点</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="线路终点" checked/><label class="checkBoxText">线路终点</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="线路性质" checked/><label class="checkBoxText">线路性质</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="线路类型" checked/><label class="checkBoxText">线路类型</label></label> 
</c:if>
<c:if test="${xmzl eq '供电系统' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="所在监控站" checked/><label class="checkBoxText">所在监控站</label></label> 
</c:if>
<c:if test="${xmzl eq '军警民联防平台' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="横向联通情况" checked/><label class="checkBoxText">横向联通情况</label></label>
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="横向联通网络性质" checked/><label class="checkBoxText">横向联通网络性质</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="横向传输线路" checked/><label class="checkBoxText">横向传输线路</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="级别" checked/><label class="checkBoxText">级别</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="放置地点" checked/><label class="checkBoxText">放置地点</label></label> 
</c:if>
<c:if test="${xmzl eq '无人值守电子哨兵' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="放置地点" checked/><label class="checkBoxText">放置地点</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="建设规模" checked/><label class="checkBoxText">建设规模</label></label> 
</c:if>
<c:if test="${xmzl eq '视频前端' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="设备类型" checked/><label class="checkBoxText">设备类型</label></label> 
</c:if>
<c:if test="${xmzl eq '视频前端'}">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="所在监控站" checked/><label class="checkBoxText">所在监控站</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="放置地点" checked/><label class="checkBoxText">放置地点</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="传输方式" checked/><label class="checkBoxText">传输方式</label></label> 
</c:if>
<c:if test="${xmzl eq '显控终端' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="所在监控站" checked/><label class="checkBoxText">所在监控站</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="放置地点" checked/><label class="checkBoxText">放置地点</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="传输方式" checked/><label class="checkBoxText">传输方式</label></label> 
</c:if>
<c:if test="${xmzl eq '报警装置' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="所在监控站" checked/><label class="checkBoxText">所在监控站</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="建设规模" checked/><label class="checkBoxText">建设规模</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="设备品牌" checked/><label class="checkBoxText">设备品牌</label></label> 
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="设备型号" checked/><label class="checkBoxText">设备型号</label></label> 
</c:if>
<c:if test="${xmzl eq '执勤房' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="执勤房类型" checked/><label class="checkBoxText">执勤房类型</label></label> 
</c:if>
<c:if test="${xmzl eq '了望塔' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="了望塔类型" checked/><label class="checkBoxText">了望塔类型</label></label> 
</c:if>
<c:if test="${xmzl eq '标志牌' }">
	<label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="标志牌类型" checked/><label class="checkBoxText">标志牌类型</label></label> 
</c:if>


<!-- 如果partTag==3新增 建设状态, 承建单位, 监理单位, 招投标时间, 开工时间 ,初验时间 ,竣工时间 ,审验时间 -->
<c:if test="${partTag eq '3' or partTag eq '4'}">
    <label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="建设状态" checked/><label class="checkBoxText">建设状态</label></label>
    <label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="承建单位" checked/><label class="checkBoxText">承建单位</label></label> 
    <label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="监理单位" checked/><label class="checkBoxText">监理单位</label></label> 
    <label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="招投标时间" checked/><label class="checkBoxText">招投标时间</label></label> 
    <label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="开工时间" checked/><label class="checkBoxText">开工时间</label></label> 
    <label class="showLineLabel" ><input type="checkbox" name="showLine" visibility="visible" value="初验时间" checked/><label class="checkBoxText">初验时间</label></label> 
    <label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="竣工时间" checked/><label class="checkBoxText">竣工时间</label></label> 
    <label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="审计时间" checked/><label class="checkBoxText">审计时间</label></label> 
</c:if>
<c:if test="${partTag eq '4'}">
    <label class="showLineLabel"><input type="checkbox" name="showLine" visibility="visible" value="使用状态" checked/><label class="checkBoxText">使用状态</label></label> 
</c:if>
</div>