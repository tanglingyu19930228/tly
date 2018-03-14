<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 桥梁详细信息 -->
<table class="tabList comBorder">
    <thead>
        <tr>
            <th>
				<div class="checkboxOne">
                    <input type="checkbox" id="checkedAll" name=""/>
                    <label for="checkedAll"></label>
                </div>
				<span class="spanAll">全选</span>
			</th>
			<th>项目类别</th>
			<th>项目子类</th>
			<th>项目名称</th>
			<th class="thPosition">
				<span class="typeTitle" id="sslx">
					<i class="t_name">设施类型</i><i class="icons icons-down"></i>
					<ul class="typeItem">
	                    <li><a>设施类型</a><i class="icons icons-up"></i></li>
	                    <c:forEach items="${sslxList }" var="item">
	                    	<li><a>${fn:escapeXml(item.codeName) }</a></li>
	                    </c:forEach>
	                </ul>
				</span>
			</th>
			<th class="thPosition">
				<span class="typeTitle" id="bjfx">
					<i class="t_name">边界方向</i><i class="icons icons-down"></i>
					<ul class="typeItem">
	                    <li><a>边界方向</a><i class="icons icons-up"></i></li>
	                    <c:forEach items="${bjfxList }" var="item">
	                    	<li><a>${fn:escapeXml(item.codeName) }</a></li>
	                    </c:forEach>
	                </ul>
				</span>
			</th>
			<th>建设区域</th>
			<!-- <th>经纬度</th> -->
			<th>经度</th>
			<th>纬度</th>
			<th class="thPosition">
				<span class="typeTitle" id="dxlb">
					<i class="t_name">地形类别</i><i class="icons icons-down"></i>
					<ul class="typeItem">
	                    <li><a>地形类别</a><i class="icons icons-up"></i></li>
	                    <c:forEach items="${dxlbList }" var="item">
	                    	<li><a>${fn:escapeXml(item.codeName) }</a></li>
	                    </c:forEach>
	                </ul>
				</span>
			</th>
			<th class="thPosition">
				<span class="typeTitle" id="jsxz">
					<i class="t_name">建设性质</i><i class="icons icons-down"></i>
					<ul class="typeItem">
	                    <li><a>建设性质</a><i class="icons icons-up"></i></li>
	                    <c:forEach items="${jsxzList }" var="item">
	                    	<li><a>${fn:escapeXml(item.codeName) }</a></li>
	                    </c:forEach>
	                </ul>
				</span>
			</th>
			<th>使用单位</th>
			<th>申报单位</th>
			<!-- 建设状态  使用单位 使用状态  申报单位 承建单位  监理单位 建设预算-->
			<th>中央投资</th>
			<th>地方投资</th>
			<c:if test="${partTag eq '2' }">
            	<th>投资年度</th>
            </c:if>
            <c:if test="${partTag eq '1' or partTag eq '3' or partTag eq '4' }">
            	<th class="thPosition">
					<span class="typeTitle" id="tznd">
						<i class="t_name">投资年度</i><i class="icons icons-down"></i>
						<ul class="typeItem">
		                    <li><a>投资年度</a><i class="icons icons-up"></i></li>
		                    <c:forEach var="i" begin="${year - 5 }" end="${year + 5 }">
		                    	<li><a>${i }</a></li>
		                    </c:forEach>
		                </ul>
					</span>
				</th>
            </c:if>
			<!-- 开工时间  竣工时间-->
            <th>附件</th>
            <!-- 填写人 -->
            <th>备注</th>
            <!-- 处理意见 -->
            <th>建设地点</th>
            <th>建设规模</th>
            <%--建设状态 --%>
			<c:if test="${partTag eq '3' or partTag eq '4'}">
			<c:if test="${partTag eq '3'}">
				<th class="thPosition">
				<span class="typeTitle" id="jszt">
					<i class="t_name">建设状态</i><i class="icons icons-down"></i>
					<ul class="typeItem">
	                    <li><a>建设状态</a><i class="icons icons-up"></i></li>
	                    <c:forEach items="${list_jszt }" var="item">
	                    	<li><a>${fn:escapeXml(item.codeName) }</a></li>
	                    </c:forEach>
	                </ul>
				</span>
			</th>
			</c:if>
			<c:if test="${partTag eq '4'}">
			<th>建设状态</th>
			</c:if>
			<th>承建单位</th>
			<th>监理单位</th>
			<th>招投标时间</th>
			<th>开工时间</th>
			<th>竣工时间</th>
			<th>初验时间</th>
			<th>审计时间</th>
			</c:if>
			<c:if test="${partTag eq '4'}">
			<th class="thPosition">
					<span class="typeTitle" id="syzt">
						<i class="t_name">使用状态</i><i class="icons icons-down"></i>
						<ul class="typeItem">
		                    <li><a>使用状态</a><i class="icons icons-up"></i></li>
		                    <c:forEach items="${list_syzt }" var="item">
		                    	<li><a>${fn:escapeXml(item.codeName) }</a></li>
		                    </c:forEach>
		                </ul>
					</span>
				</th>
			</c:if>
        </tr>
    </thead>
    <tbody id="tbody">
    	<c:forEach items="${pageBean.recordList }" var="item">
			<tr>
				<td>
					<div class="checkboxOne" onclick="selectAll($(this))">
	                    <input type="checkbox" id="" name="" value="${fn:escapeXml(item.id) }"/><!-- id -->
	                    <label for=""></label>
	                </div>
				</td>
				<td>${fn:escapeXml(item.xmlb) }</td><!-- 项目类别 -->
				<td>${fn:escapeXml(item.xmzl) }</td><!-- 项目子类 -->
				<td title="${fn:escapeXml(item.xmmc) }"><span class="nameTd" >${fn:escapeXml(item.xmmc) }</span></td><!-- 项目名称 -->
				<td>${fn:escapeXml(item.sslx) }</td><!-- 设施类型 -->
				<td>${fn:escapeXml(item.bjfx) }</td><!-- 边界方向 -->
				<td>${fn:escapeXml(item.szsf) }${fn:escapeXml(item.szcs) }${fn:escapeXml(item.szq) }</td><!-- 建设区域 -->
				<%-- <td>${fn:escapeXml(item.jwd) }</td>	<!-- 经纬度 --> --%>
				<td>${fn:escapeXml(item.jd) }</td>	<!-- 经纬度 -->
				<td>${fn:escapeXml(item.wd) }</td>	<!-- 经纬度 -->
				<td>${fn:escapeXml(item.dxlb) }</td><!-- 地形类别 -->
				<td>${fn:escapeXml(item.jsxz) }</td><!-- 建设性质 -->
				<td>${fn:escapeXml(item.sydw) }</td><!-- 使用单位 -->
				<td>${fn:escapeXml(item.sbdw) }</td><!-- 申报单位 -->
				<!-- 建设状态  使用单位 使用状态  申报单位 承建单位  监理单位 建设预算-->
				<td><fmt:formatNumber value="${item.zytz}" maxFractionDigits="4"/></td><!-- 中央投资 -->
	            <td><fmt:formatNumber value="${item.dftz}" maxFractionDigits="4"/></td><!-- 地方投资 -->
	            <td>${fn:escapeXml(item.tznd) }</td>	<!-- 投资年度 -->
	            <!-- 开工时间  竣工时间-->
				<td class="file-name" title="${fn:escapeXml(item.fj) }">${fn:escapeXml(item.fj) }</td>	<!-- 附件 -->
				<!-- 填写人 -->
				<td title="${fn:escapeXml(item.bz) }">${fn:escapeXml(item.bz) }</td>	<!-- 备注-->
				<!-- 处理意见 -->
				<td>${fn:escapeXml(item.jsdd) }</td><!-- 建设地点 -->
				<td>${fn:escapeXml(item.jsgm) }</td><!-- 建设规模-->
				<c:if test="${partTag eq '3' or partTag eq '4'}">
					<td id="jszt">${fn:escapeXml(item.jszt) }</td><%--建设状态 --%>
					<td>${fn:escapeXml(item.cjdw) }</td><%--承建单位 --%>
					<td>${fn:escapeXml(item.jldw) }</td><%--监理单位 --%>
					<td><fmt:formatDate value="${item.ztbsj }" pattern="yyyy-MM-dd"/></td><%--招投标时间 --%>
					<td><fmt:formatDate value="${item.kgsj }" pattern="yyyy-MM-dd"/></td><%--开工时间 --%>
					<td><fmt:formatDate value="${item.jgsj }" pattern="yyyy-MM-dd"/></td><%--竣工时间 --%>
					<td><fmt:formatDate value="${item.cysj }" pattern="yyyy-MM-dd"/></td><%--初验时间 --%>
					<td><fmt:formatDate value="${item.sjsj }" pattern="yyyy-MM-dd"/></td><%--审计时间 --%>
				</c:if>
				<c:if test="${partTag eq '4'}">
					<td>${fn:escapeXml(item.syzt)}</td>
				</c:if>
			</tr>
		</c:forEach>
    </tbody>
</table>
<script>
	function init(){
		$(".typeItem a").click(function(){
			$("#offiset").val("1");
			ajaxData();
		});
		transFj();
	}
	function ajaxData(){
		var sslxTag = false;//设施类型是否显示标识
		var bjfxTag = false;//边界方向是否显示标识
		var dxlbTag = false;//地形类别是否显示标识
		var jsxzTag = false;//建设性质是否显示标识
		var tzndTag = false;//投资年度是否显示标识
		var jsztTag = false;//建设状态是否显示标识
		var syztTag =false;//使用状态是否显示标识
		for(var i = 0; i < $("input[name='showLine']:checked").length; i++){
			var checkedVal = $($("input[name='showLine']:checked")[i]).val();
			if("设施类型" == checkedVal){
				sslxTag = true;
			}
			if("边界方向" == checkedVal){
				bjfxTag = true;
			}
			if("地形类别" == checkedVal){
				dxlbTag = true;
			}
			if("建设性质" == checkedVal){
				jsxzTag = true;
			}
			if("投资年度" == checkedVal){
				tzndTag = true;
			}
			/*建设状态*/
			if("建设状态"==checkedVal){
				jsztTag=true;
			}
			if("使用状态"==checkedVal){
			syztTag=true;
			}
		}
		var sslx =null;
		if($("#sslx").find(".t_name").text() !="设施类型"){
			if(sslxTag){
				sslx = $("#sslx").find(".t_name").text();
			}
		}
		
		var bjfx = null;
		if($("#bjfx").find(".t_name").text() !="边界方向"){
			if(bjfxTag){
				bjfx = $("#bjfx").find(".t_name").text();
			}
		}
		var dxlb = null;
		if($("#dxlb").find(".t_name").text() !="地形类别"){
			if(dxlbTag){
				dxlb = $("#dxlb").find(".t_name").text();
			}
		}
		var jsxz = null;
		if($("#jsxz").find(".t_name").text() !="建设性质"){
			if(jsxzTag){
				jsxz = $("#jsxz").find(".t_name").text();
			}
		}
		var tznd = null;
		if($("#tznd").find(".t_name").text() !="投资年度"){
			if(tzndTag){
				tznd = $("#tznd").find(".t_name").text();
			}
		}

		/* 建设状态 */
				var jszt=null;
				if($("#jszt").find(".t_name").text()!="建设状态"){
					if(jsztTag){
						jszt=$("#jszt").find(".t_name").text();
					}
				}
		
		var syzt=null;
		if($("#syzt").find(".t_name").text() !="使用状态"){
			if(syztTag){
			syzt=$("#syzt").find(".t_name").text();
			}
		}
		
		var condition = {"szsf":'${szsf}',"xmlb":'${xmlb}',"xmzl":'${xmzl}',"sslx":sslx,"bjfx":bjfx,"dxlb":dxlb,
			"jsxz":jsxz,"tznd":tznd,"offiset":$("#offiset").val(),"partTag":partTag ,"jszt":jszt,"syzt":syzt};
		$.ajax({
			url:"xmzl/loadDataByCondition",
			data:condition,
			type:"post",
			success:function(data){
				$("#tbody").empty();
				if(data!=null){
					for(var i=0;i<data.recordList.length;i++){
						var ssjdTd = "";//实施监督td
						if(${partTag eq '3' or partTag eq '4'}){
							ssjdTd = "<td id='jszt'>"+data.recordList[i].jszt+"</td>"+
				            "<td>"+data.recordList[i].cjdw+"</td>"+
				            "<td>"+data.recordList[i].jldw+"</td>"+
				            "<td class='transTime'>"+data.recordList[i].ztbsj+"</td>"+
				            "<td class='transTime'>"+data.recordList[i].kgsj+"</td>"+
				            "<td class='transTime'>"+data.recordList[i].jgsj+"</td>"+
				            "<td class='transTime'>"+data.recordList[i].cysj+"</td>"+
				            "<td class='transTime'>"+data.recordList[i].sjsj+"</td>";
						}
						var syztTd="";
						if('${partTag}'=='4'){
							syztTd="<td>"+data.recordList[i].syzt+"</td>";
						}
						
						$("#tbody").append(
							"<tr>"+
							"<td><div class='checkboxOne' onclick=\"selectAll($(this))\">"+
			                    "<input type='checkbox' id='' name='' value="+data.recordList[i].id+">"+
			                "<label for=''></label></div></td>"+
				       		"<td>"+data.recordList[i].xmlb+"</td>"+
				       		"<td>"+data.recordList[i].xmzl+"</td>"+
				       		"<td title='"+data.recordList[i].xmmc+"'><span class='nameTd'>"+data.recordList[i].xmmc+"</span></td>"+
				       		"<td>"+data.recordList[i].sslx+"</td>"+
				       		"<td>"+data.recordList[i].bjfx+"</td>"+
				       		"<td>"+data.recordList[i].szsf+data.recordList[i].szcs+data.recordList[i].szq+"</td>"+
				       		/* "<td>"+data.recordList[i].jwd+"</td>"+ */	
				       		"<td>"+data.recordList[i].jd+"</td>"+	
				       		"<td>"+data.recordList[i].wd+"</td>"+	
				       		"<td>"+data.recordList[i].dxlb+"</td>"+
				       		"<td>"+data.recordList[i].jsxz+"</td>"+
			            	"<td>"+data.recordList[i].sydw+"</td>"+
			            	"<td>"+data.recordList[i].sbdw+"</td>"+
			            	"<td>"+data.recordList[i].zytz+"</td>"+
			            	"<td>"+data.recordList[i].dftz+"</td>"+
			            	"<td>"+data.recordList[i].tznd+"</td>"+
			            	"<td class='file-name' title='"+data.recordList[i].fj+"'>"+data.recordList[i].fj+"</td>"+
				            "<td title='"+data.recordList[i].bz+"'>"+data.recordList[i].bz+"</td>"+
				            "<td>"+data.recordList[i].jsdd+"</td>"+
				            "<td>"+data.recordList[i].jsgm+"</td>"+
				            ssjdTd+syztTd+
				            "</tr>"
						);
					}
                    $(".tabList thead .checkboxOne").find("input").prop("checked", false);
					$("#getPageCount").val(data.pageCount);//总页数
					$("#getCurrentPage").val(data.currentPage);//当前页
					$("#pageCount").text(data.pageCount);
					$(".p_num .dot").remove();
					$(".p_num a").remove();
					$(".pagePrev").removeClass("disabled");
					$(".pageNext").removeClass("disabled");
					loadPage();
					$("#tbody tr").find("td").each(function(){
						if($(this).text()=="null"){
							$(this).text("");
						}
					});
					//日期格式化
					$(".transTime").each(function(){
						var value = $(this).text();
						if(value=="null"){
							$(this).text("");
						}else if(value!=""){
							var unixTimestamp = new Date( value*1 ) ;
				            var commonTime = unixTimestamp.toLocaleString();
				            $(this).text(commonTime);
						}
					});
					menuHeight();
					assignId($(".tabList tr"),"checkbox");
				}
				transFj();
				hideQueryLine();//隐藏展示列数据
			}
		});
	}
</script>