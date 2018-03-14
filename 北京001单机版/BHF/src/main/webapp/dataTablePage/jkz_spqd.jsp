<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="js/pageOther.js"></script>
<div style="overflow: auto;min-height: 300px;">
	<input type="hidden" id="offisetOther" name="offisetOther" />
	<input type="hidden" id="zl_jkz_id" value="${param.jkz_id }"/>
	<input type="hidden" id="spqd_sblx" value="${param.sblx }"/>
	<table class="tabList comBorder">
	    <thead>
	        <tr>
	            <th>项目类别</th>
	            <th>项目子类</th>
	            <th>项目名称</th>
	           
	            <th class="thPosition">
	                <span class="typeTitle typeTitleFn" id="sslxOther"><i class="t_name">设施类型</i><i class="icons icons-down"></i>
	                <ul class="typeItem typeItemFn">
	                    <li><a>设施类型</a><i class="icons icons-up"></i></li>
	                    <c:forEach items="${sslxList }" var="item">
	                    	<li><a>${item.codeName }</a></li>
	                    </c:forEach>
	                </ul>
				</span>
	            </th>
	            <th class="thPosition">
	                <span class="typeTitle typeTitleFn" id="bjfxOther"><i class="t_name">边界方向</i><i class="icons icons-down"></i>
	                <ul class="typeItem typeItemFn">
	                    <li><a>边界方向</a><i class="icons icons-up"></i></li>
	                    <c:forEach items="${bjfxList }" var="item">
	                    	<li><a>${item.codeName }</a></li>
	                    </c:forEach>
	                </ul>
				</span>
	            </th>
	            <th>建设区域</th>
	            <!-- <th>经纬度</th> -->
	            <th>经度</th>
	            <th>纬度</th>
	            <th class="thPosition">
	                <span class="typeTitle typeTitleFn" id="dxlbOther"><i class="t_name">地形类别</i><i class="icons icons-down"></i>
	                <ul class="typeItem typeItemFn">
	                    <li><a>地形类别</a><i class="icons icons-up"></i></li>
	                    <c:forEach items="${dxlbList }" var="item">
	                    	<li><a>${item.codeName }</a></li>
	                    </c:forEach>
	                </ul>
				</span>
	            </th>
	            <th class="thPosition">
	                <span class="typeTitle typeTitleFn" id="jsxzOther"><i class="t_name">建设性质</i><i class="icons icons-down"></i>
	                <ul class="typeItem typeItemFn">
	                    <li><a>建设性质</a><i class="icons icons-up"></i></li>
	                    <c:forEach items="${jsxzList }" var="item">
	                    	<li><a>${item.codeName }</a></li>
	                    </c:forEach>
	                </ul>
				</span>
	            </th>
	            
	            <th>使用单位</th>
	           
	            <th>申报单位</th>
	            <th>中央投资</th>
	            <th>地方投资</th>
	            <c:if test="${partTag eq '2' }">
	            	<th>投资年度</th>
	            </c:if>
	            
	            <c:if test="${partTag eq '1' or partTag eq '3' or partTag eq '4' }">
	            	<th class="thPosition">
					<span class="typeTitle typeTitleFn" id="tzndOther">
						<i class="t_name">投资年度</i><i class="icons icons-down"></i>
						<ul class="typeItem typeItemFn">
		                    <li><a>投资年度</a><i class="icons icons-up"></i></li>
		                    <c:forEach var="i" begin="${year -5 }" end="${year + 5 }">
		                    	<li><a>${i }</a></li>
		                    </c:forEach>
		                </ul>
					</span>
				</th>
	            </c:if>
	            
	            <th>附件</th>
	            <th>备注</th>
	            <th>所在监控站</th>
	            <th>放置地点</th>  
	            <th>传输方式</th>           
	            <th>设备类型</th>           
	            <c:if test="${partTag eq '3' or partTag eq '4'}">
		            <c:if test="${partTag eq '3'}">
						<th class="thPosition">
							<span class="typeTitle typeTitleFn" id="jsztOther">
								<i class="t_name">建设状态</i><i class="icons icons-down"></i>
								<ul class="typeItem typeItemFn">
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
						<span class="typeTitle typeTitleFn" id="syztOther">
							<i class="t_name">使用状态</i><i class="icons icons-down"></i>
							<ul class="typeItem typeItemFn">
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
	     <tbody id="tbodyOther">
	    	<c:forEach items="${pageBean.recordList }" var="item">
		        <tr>
		            <td>${item.xmlb }</td>
		            <td>${item.xmzl }</td>
		            <td title="${fn:escapeXml(item.xmmc) }"><span>${fn:escapeXml(item.xmmc) }</span></td>
		            <td>${fn:escapeXml(item.sslx) }</td>
		            <td>${fn:escapeXml(item.bjfx) }</td>
		            <td>${fn:escapeXml(item.szsf) }${fn:escapeXml(item.szcs) }${fn:escapeXml(item.szq) }</td>
		            <%-- <td>${fn:escapeXml(item.jwd)  }</td> --%>
		            <td>${fn:escapeXml(item.jd)  }</td>
		            <td>${fn:escapeXml(item.wd)  }</td>
		            <td>${fn:escapeXml(item.dxlb) }</td>
		            <td>${fn:escapeXml(item.jsxz) }</td>
		            <td>${fn:escapeXml(item.sydw) }</td>
		            <td>${fn:escapeXml(item.sbdw) }</td>
		            <td><fmt:formatNumber value="${item.zytz}" maxFractionDigits="4"/></td>
		            <td><fmt:formatNumber value="${item.dftz}" maxFractionDigits="4"/></td>
		            <td>${fn:escapeXml(item.tznd) }</td>
		            <td class="file-name-other" title="${fn:escapeXml(item.fj) }">${fn:escapeXml(item.fj) }</td>
		            <td title="${fn:escapeXml(item.bz) }">${fn:escapeXml(item.bz) }</td>
		            <td>${fn:escapeXml(item.jkz_name)}</td>
		            <td>${fn:escapeXml(item.fzdd)}</td>	            
		            <td>${fn:escapeXml(item.csfs) }</td>
		            <td>${fn:escapeXml(item.sblx) }</td>
		            <c:if test="${partTag eq '3' or partTag eq '4'}">
						<td>${fn:escapeXml(item.jszt) }</td><%--建设状态 --%>
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
</div>
<div class="pageOther">
	<input type="hidden" id="getCurrentPageOther"
		value="${pageBean.currentPage}"> <input type="hidden"
		id="getPageOtherCount" value="${pageBean.pageCount}"> <span
		class="pOther_num"><span class="pageOtherPrev"><b class="arr"></b>上一页</span><span
		class="pageOtherNext">下一页<b class="arr"></b></span></span> <span
		class="pOther_skip"><em>共<b id="pageOtherCount">${pageBean.pageCount}</b>页，&nbsp;跳到
	</em> <input type="text" id="turnPageOther" maxlength="6"><em>页&nbsp;</em><a
		href="javascript:" id="confirmOther">确定</a> </span>
</div>
<div class="clear"></div>
<script>
	function initJkz_zl(){
		$(".typeItemFn a").click(function(){
			$("#offisetOther").val("1");//表头筛选，默认为第一页
			ajaxJkz_zlData();
		});
		transFj();
	}
	function ajaxJkz_zlData(){
		var jkz_id = $("#zl_jkz_id").val();
		var sblx = $("#spqd_sblx").val();
		
		var sslx =null;
		if($("#sslxOther").find(".t_name").text() !="设施类型"){
			sslx = $("#sslxOther").find(".t_name").text();
		}
		
		var bjfx = null;
		if($("#bjfxOther").find(".t_name").text() !="边界方向"){
			bjfx = $("#bjfxOther").find(".t_name").text();
		}
		var dxlb = null;
		if($("#dxlbOther").find(".t_name").text() !="地形类别"){
			dxlb = $("#dxlbOther").find(".t_name").text();
		}
		var jsxz = null;
		if($("#jsxzOther").find(".t_name").text() !="建设性质"){
			jsxz = $("#jsxzOther").find(".t_name").text();
		}
		var tznd = null;
		if($("#tzndOther").find(".t_name").text() !="投资年度"){
			tznd = $("#tzndOther").find(".t_name").text();
		}
		var jszt = null;
		if($("#jsztOther").find(".t_name").text() !="建设状态"){
			jszt = $("#jsztOther").find(".t_name").text();
		}
		var syzt=null;
		if($("#syztOther").find(".t_name").text() !="使用状态"){
			syzt=$("#syztOther").find(".t_name").text();
		}
		var condition = {"sslx":sslx,"bjfx":bjfx,"jkz_id":jkz_id,"dxlb":dxlb,"jsxz":jsxz,"tznd":tznd,"xmzl":'视频前端',"sblx":sblx,
				"offiset":$("#offisetOther").val(),"partTag":partTag,"jszt":jszt,"syzt":syzt};
		$.ajax({
			url:'xmzl/queryJkz_zl',
			data:condition,
			type:'post',
			success:function(data){
				$("#tbodyOther").empty();
				if(data!=null){
					for(var i=0;i<data.recordList.length;i++){
						var ssjdTd = "";//实施监督td
						if(${partTag eq '3' or partTag eq '4'}){
							ssjdTd = "<td>"+data.recordList[i].jszt+"</td>"+
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
						$("#tbodyOther").append(
							"<tr>"+
				       		"<td>"+data.recordList[i].xmlb+"</td>"+
				       		"<td>"+data.recordList[i].xmzl+"</td>"+
				       		"<td title='"+data.recordList[i].xmmc+"'><span>"+data.recordList[i].xmmc+"</span></td>"+
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
			            	"<td class='file-name-other' title='"+data.recordList[i].fj+"'>"+data.recordList[i].fj+"</td>"+
				            "<td title='"+data.recordList[i].bz+"'>"+data.recordList[i].bz+"</td>"+
				            "<td>"+data.recordList[i].jkz_name+"</td>"+
				            "<td>"+data.recordList[i].fzdd+"</td>"+
				            "<td>"+data.recordList[i].csfs+"</td>"+
				            "<td>"+data.recordList[i].sblx+"</td>"+
				            ssjdTd+syztTd+
				            "</tr>"				           
						);
					}
					$("#getPageOtherCount").val(data.pageCount);//总页数
					$("#getCurrentPageOther").val(data.currentPage);//当前页
					$("#pageOtherCount").text(data.pageCount);
					$(".pOther_num .dot").remove();
					$(".pOther_num a").remove();
					$(".pageOtherPrev").removeClass("disabled");
					$(".pageOtherNext").removeClass("disabled");
					loadPageOther();
					$("#tbodyOther tr").find("td").each(function(){
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
				}
				transFj();
			}
		});
	}
</script>