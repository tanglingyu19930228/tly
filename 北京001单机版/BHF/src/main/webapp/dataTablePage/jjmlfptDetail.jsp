<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 军警民联防平台详细信息 -->
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
                <span class="typeTitle" id="sslx"><i class="t_name">设施类型</i><i class="icons icons-down"></i>
                <ul class="typeItem">
                    <li><a>设施类型</a><i class="icons icons-up"></i></li>
                    <c:forEach items="${sslxList }" var="item">
                    	<li><a>${item.codeName }</a></li>
                    </c:forEach>
                </ul>
			</span>
            </th>
            <th class="thPosition">
                <span class="typeTitle" id="bjfx"><i class="t_name">边界方向</i><i class="icons icons-down"></i>
                <ul class="typeItem">
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
                <span class="typeTitle" id="dxlb"><i class="t_name">地形类别</i><i class="icons icons-down"></i>
                <ul class="typeItem">
                    <li><a>地形类别</a><i class="icons icons-up"></i></li>
                    <c:forEach items="${dxlbList }" var="item">
                    	<li><a>${item.codeName }</a></li>
                    </c:forEach>
                </ul>
			</span>
            </th>
             <th class="thPosition">
                <span class="typeTitle" id="jsxz"><i class="t_name">建设性质</i><i class="icons icons-down"></i>
                <ul class="typeItem">
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
				<span class="typeTitle" id="tznd">
					<i class="t_name">投资年度</i><i class="icons icons-down"></i>
					<ul class="typeItem">
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
            <th class="thPosition">
                <span class="typeTitle" id="xsltqk"><i class="t_name">向上联通情况</i><i class="icons icons-down"></i>
                <ul class="typeItem">
                    <li><a>向上联通情况</a><i class="icons icons-up"></i></li>
                    <c:forEach items="${list_ltqk }" var="item">
                    	<li><a>${item.codeName }</a></li>
                    </c:forEach>
                </ul>
             </span>
            </th>
            <th class="thPosition">
                <span class="typeTitle" id="xsltwlxz"><i class="t_name">向上联通网络性质</i><i class="icons icons-down"></i>
                <ul class="typeItem">
                    <li><a>向上联通网络性质</a><i class="icons icons-up"></i></li>
                    <c:forEach items="${list_ltwlxz }" var="item">
                    	<li><a>${item.codeName }</a></li>
                    </c:forEach>
                </ul>
             </span>
            </th>
            <th class="thPosition">
                <span class="typeTitle" id="xscsxl"><i class="t_name">向上传输线路</i><i class="icons icons-down"></i>
                <ul class="typeItem">
                    <li><a>向上传输线路</a><i class="icons icons-up"></i></li>
                    <c:forEach items="${list_csxl }" var="item">
                    	<li><a>${item.codeName }</a></li>
                    </c:forEach>
                </ul>
             </span>
            </th>
            <th class="thPosition">
                <span class="typeTitle" id="hxltqk"><i class="t_name">横向联通情况</i><i class="icons icons-down"></i>
                <ul class="typeItem">
                    <li><a>横向联通情况</a><i class="icons icons-up"></i></li>
                    <c:forEach items="${hx_list_ltqk }" var="item">
                    	<li><a>${item.codeName }</a></li>
                    </c:forEach>
                </ul>
             </span>
            </th>
            <th class="thPosition">
                <span class="typeTitle" id="hxltwlxz"><i class="t_name">横向联通网络性质</i><i class="icons icons-down"></i>
                <ul class="typeItem">
                    <li><a>横向联通网络性质</a><i class="icons icons-up"></i></li>
                    <c:forEach items="${hx_list_ltwlxz}" var="item">
                    	<li><a>${item.codeName }</a></li>
                    </c:forEach>
                </ul>
             </span>
            </th>
            <th class="thPosition">
                <span class="typeTitle" id="hxcsxl"><i class="t_name">横向传输线路</i><i class="icons icons-down"></i>
                <ul class="typeItem">
                    <li><a>横向传输线路</a><i class="icons icons-up"></i></li>
                    <c:forEach items="${list_csxl }" var="item">
                    	<li><a>${item.codeName }</a></li>
                    </c:forEach>
                </ul>
             </span>
            </th>
            <th class="thPosition">
                <span class="typeTitle" id="jb"><i class="t_name">级别</i><i class="icons icons-down"></i>
                <ul class="typeItem">
                    <li><a>级别</a><i class="icons icons-up"></i></li>
                    <c:forEach items="${list_jb }" var="item">
                    	<li><a>${item.codeName }</a></li>
                    </c:forEach>
                </ul>
             </span>
            </th>
            <th>放置地点</th>
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
	                    <input type="checkbox" id="" name="" value="${fn:escapeXml(item.id) }"/>
	                    <label for=""></label>
	                </div>
	            </td>
	            <td>${xmlb }</td>
	            <td>${xmzl }</td>
	            <td title="${fn:escapeXml(item.xmmc) }"><span class="nameTd" >${fn:escapeXml(item.xmmc) }</span></td>
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
	            <td class="file-name" title="${fn:escapeXml(item.fj) }">${fn:escapeXml(item.fj) }</td>
	            <td title="${fn:escapeXml(item.bz) }">${fn:escapeXml(item.bz) }</td>
	            <td>${fn:escapeXml(item.xsltqk) }</td>
	            <td>${fn:escapeXml(item.xsltwlxz) }</td>
	            <td>${fn:escapeXml(item.xscsxl) }</td>
	            <td>${fn:escapeXml(item.hxltqk) }</td>
	            <td>${fn:escapeXml(item.hxltwlxz) }</td>
	            <td>${fn:escapeXml(item.hxcsxl) }</td>
	            <td>${fn:escapeXml(item.jb) }</td>
	            <td>${fn:escapeXml(item.fzdd) }</td>
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
					<td>${fn:escapeXml(item.syzt)}</td><%--使用状态 --%>
				</c:if>        
	        </tr>
    	</c:forEach>
    </tbody>
</table>
<script>
	function init(){
		$(".typeItem a").click(function(){
			$("#offiset").val("1");//表头筛选，默认为第一页
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
		var xsltqkTag = false;//向上联通情况是否显示标识
		var xsltwlxzTag = false;//向上联通网络性质是否显示标识
		var xscsxlTag = false;//向上传输线路是否显示标识
		var hxltqkTag = false;//横向联通情况是否显示标识
		var hxltwlxzTag = false;//横向联通网络性质是否显示标识
		var hxcsxlTag = false;//横向传输线路是否显示标识
		var jbTag = false;//级别是否显示标识
		var jsztTag =false;//建设状态是否显示
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
			if("向上联通情况" == checkedVal){
				xsltqkTag = true;
			}
			if("向上联通网络性质" == checkedVal){
				xsltwlxzTag = true;
			}
			if("向上传输线路" == checkedVal){
				xscsxlTag = true;
			}
			if("横向联通情况" == checkedVal){
				hxltqkTag = true;
			}
			if("横向联通网络性质" == checkedVal){
				hxltwlxzTag = true;
			}
			if("横向传输线路" == checkedVal){
				hxcsxlTag = true;
			}
			if("级别" == checkedVal){
				jbTag = true;
			}
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
		var xsltqk = null;
		if($("#xsltqk").find(".t_name").text()!="向上联通情况"){
			if(xsltqkTag){
				xsltqk=$("#xsltqk").find(".t_name").text();
			}
		}
		var xsltwlxz = null;
		if($("#xsltwlxz").find(".t_name").text()!="向上联通网络性质"){
			if(xsltwlxzTag){
				xsltwlxz=$("#xsltwlxz").find(".t_name").text();
			}
		}
		var xscsxl = null;	
		if($("#xscsxl").find(".t_name").text()!="向上传输线路"){
			if(xscsxlTag){
				xscsxl=$("#xscsxl").find(".t_name").text();
			}
		}
		var hxltqk = null;
		if($("#hxltqk").find(".t_name").text()!="横向联通情况"){
			if(hxltqkTag){
				hxltqk=$("#hxltqk").find(".t_name").text();
			}
		}
		var hxltwlxz = null;
		if($("#hxltwlxz").find(".t_name").text()!="横向联通网络性质"){
			if(hxltwlxzTag){
				hxltwlxz=$("#hxltwlxz").find(".t_name").text();
			}
		}
		var hxcsxl = null;
		if($("#hxcsxl").find(".t_name").text()!="横向传输线路"){
			if(hxcsxlTag){
				hxcsxl=$("#hxcsxl").find(".t_name").text();
			}
		}
		var jb = null;
		if($("#jb").find(".t_name").text()!="级别"){
			if(jbTag){
				jb=$("#jb").find(".t_name").text();
			}
		}
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
			
			var condition = {"szsf":'${szsf}',"xmlb":'${xmlb}',"xmzl":'${xmzl}',"sslx":sslx,"bjfx":bjfx,
				"dxlb":dxlb,"jsxz":jsxz,"tznd":tznd,"xsltqk":xsltqk,"xsltwlxz":xsltwlxz,"xscsxl":xscsxl,
				"hxltqk":hxltqk,"hxltwlxz":hxltwlxz,"hxcsxl":hxcsxl,"jb":jb,"offiset":$("#offiset").val(),"partTag":partTag,"jszt":jszt,"syzt":syzt};
			$.ajax({
				url:'xmzl/loadDataByCondition',
				data:condition,
				type:'post',
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
					            "<td>"+data.recordList[i].xsltqk+"</td>"+
					            "<td>"+data.recordList[i].xsltwlxz+"</td>"+
					            "<td>"+data.recordList[i].xscsxl+"</td>"+
					            "<td>"+data.recordList[i].hxltqk+"</td>"+
					            "<td>"+data.recordList[i].hxltwlxz+"</td>"+
					            "<td>"+data.recordList[i].hxcsxl+"</td>"+
					            "<td>"+data.recordList[i].jb+"</td>"+
					            "<td>"+data.recordList[i].fzdd+"</td>"+
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