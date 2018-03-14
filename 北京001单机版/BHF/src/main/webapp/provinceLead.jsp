<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 左侧导航栏 -->
<script>
 	$(function(){
 		if(${not empty areaList }){
			var sf = $(".DB_1D").children("a");
			for(var i=0;i<sf.length;i++){
				if('市辖区'.indexOf(sf.text())>-1){
					sf.next().show();
					sf.prev().attr("src","img/tree_off.png");
					var zl=sf.next().find("a");
					for(var i=0;i<zl.length;i++){
						if('${xmzl}'==zl.eq(i).text()){
							zl.eq(i).parent().parent().show();
							zl.eq(i).parent().parent().prev().prev().attr("src","img/tree_off.png");
						}
					}
				}
				if('${szsf}'.indexOf(sf.eq(i).text()) >= 0){
					sf.eq(i).next().show();
					var zl = sf.eq(i).next().find("a");
					for(var i=0;i<zl.length;i++){
						if('${xmzl}'==zl.eq(i).text()){
							zl.eq(i).parent().parent().show();
						}
					}
				}
			}
 		}else{
 			var $xmzl = $(".DB_3D a");
			for(var i = 0; i< $xmzl.length; i ++){
				if('${xmzl}'==$xmzl.eq(i).text()){
					$xmzl.eq(i).parent().parent().show();
				}
			}
 		}
		
		$(".DB_1D").children("a").click(function(){
			var sf = $(this).text();
			var szcs=null;
			if($("#role").val()=='2'){
			 szcs=$(this).text();
			}
			if('${partTag}'=='2' || '${partTag}'=='1'){
				$("#postForm").empty();
				var f=$("#postForm")[0];
		        f.action='annualPlanProvince/load';
		        f.innerHTML='<input type="hidden" name="szsf" value="'+sf+'"/>'
		        + '<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
		        f.submit();
			}else if('${partTag}'=='3'){
	        	var sf = $(this).text(); 
				$("#postForm").empty();
				var f=$("#postForm")[0];
		        f.action='implement/load';
		        f.innerHTML='<input type="hidden" name="szsf" value="'+sf+'"/>'
		        +'<input type="hidden" name="szcs" value="'+szcs+'"/>'
		        + '<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
		        f.submit();
	        }else if('${partTag}'=='4'){
	        	var sf = $(this).text(); 
				$("#postForm").empty();
				var f=$("#postForm")[0];
		        f.action='usemaintenance/load';
		        f.innerHTML='<input type="hidden" name="szsf" value="'+sf+'"/>'
		        +'<input type="hidden" name="szcs" value="'+szcs+'"/>'
		        + '<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
		        f.submit();
	        }
	        
		});

		$(".DB_3D").find("a").click(function(){
			var xmzl = $(this).text(); 
			var szsf = $(this).parents(".DB_1D").children("a").text();
			var xmlb = $(this).parents(".DB_3D").prev().text();
			$("#postForm").empty();
			var f=$("#postForm")[0];
	        f.action='xmzl/loadDataTable';
	        f.innerHTML='<input type="hidden" name="szsf" value="'+szsf+'"/>'
	        + '<input type="hidden" name="xmzl" value="'+xmzl+'"/>'
	        + '<input type="hidden" name="xmlb" value="'+xmlb+'"/>'
	        + '<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
	        f.submit();
	        
		});

		if($(".DB_plus").text()=='其他设施'){
			$(this).click(function(){
				var xmlb=$(this).text();
				var szsf=$(this).parents(".DB_1D").children("a").text();
				//window.location.href="detail_load.action?szsf="+szsf+"&xmlb="+xmlb;
			});
		}
		
		$(".DB_3D a").each(function(){
	    	if($(this).parents(".DB_3D").is(":visible")){
	    		if('${xmzl}'==$(this).text()){
	        		$(this).addClass("current");
	        	}
	    	}
	    });
 	});
 	//展示所有
 	function showAll(){
 		if('${partTag}'=='2' || '${partTag}'=='1'){
	 		$("#postForm").empty();
	 		var f=$("#postForm")[0];
	        f.action='annualPlan/load';
	        f.innerHTML='<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
	        f.submit();
 		}else if('${partTag}'=='3') {
 			$("#postForm").empty();
 	 		var f=$("#postForm")[0];
 	        f.action='implement/load';
 	        f.innerHTML='<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
 	        f.submit();
 		}else if('${partTag}'=='4') {
 			$("#postForm").empty();
 	 		var f=$("#postForm")[0];
 	        f.action='usemaintenance/load';
 	        f.innerHTML='<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
 	        f.submit();
 		}
 	}
 	
</script>

<div class="m_left fl">	
	<div id="treeMenu">
	    <span class="nation">
	    	<label  onclick="showAll();" style="cursor:pointer;">
	    		<a>
	    			<i class="icons icons-flag"></i>${loginUser.roleName eq '1' ? '全国' : loginUser.province }
	    		</a>
	    	</label>
	    </span>
	    <div class="scrollMenu">
	    	<!-- 省下面有 边防市-->
		    <c:if test="${not empty areaList }">
			    <ul id="DB_tree">
			    	<c:forEach items="${areaList }" var="province">
			    		<li class="DB_1D">
				    		<c:if test="${fn:contains(szsf, province) }">
				    			<img class="DB_plus plusImg" src="img/tree_off.png" alt="" />
				    		</c:if>
				    		<c:if test="${!fn:contains(szsf, province)  }">
				    			<img  class="DB_plus plusImg" src="img/tree_on.png" alt="" />
				    		</c:if>
				    		<a href="javascript:void(0);">${province }</a>
				            <ul class="DB_2D">
				            	<c:forEach items="${xmlbList }" var="item">
					                <li>
					                	<c:if test="${item.codeName==xmlb && fn:contains(szsf, province) && item.codeName!='其他设施'}">
					                		<img class="DB_plus plusImg" src="img/tree_off.png" alt=""/>
					                	</c:if>
					                	<c:if test="${!(item.codeName==xmlb && fn:contains(szsf, province)) && item.codeName!='其他设施'}">
					                		<img id="dd" class="DB_plus plusImg" src="img/tree_on.png" alt=""/>
					                	</c:if>
					                	<%-- <c:if test="${item.codeName!=xmlb && province==szsf && item.codeName!='其他设施'}">
					                		<img class="DB_plus plusImg" src="img/tree_on.png" alt=""/>
					                	</c:if>
					                	<c:if test="${item.codeName!=xmlb && province!=szsf && item.codeName!='其他设施'}">
					                		<img class="DB_plus plusImg" src="img/tree_on.png" alt=""/>
					                	</c:if> --%>
					                	<span class="DB_plus">${fn:escapeXml(item.codeName) }</span>
					                	<ul class="DB_3D">
											<c:forEach items="${xmzlList }" var="xmzl">
												<c:if test="${xmzl.superCode==item.codeValue }">
													<li><a href="javascript:void(0);">${fn:escapeXml(xmzl.codeName) }</a></li>
												</c:if>
											</c:forEach>
										</ul>
									</li>
				            	</c:forEach>
				            </ul>
				        </li>
			    	</c:forEach>
			    </ul>
		    </c:if>
		    <!--省下级没有市-->
		    <c:if test="${empty areaList }">
		    	 <ul id="DB_tree">
					<li>
						<ul class="DB_1D">
							<c:forEach items="${xmlbList }" var="item">
								<li>
									<c:if test="${item.codeName==xmlb &&item.codeName!='其他设施'}">
										<img class="DB_plus plusImg icon" src="img/tree_off.png" alt="" />
									</c:if> 
									<c:if test="${item.codeName!=xmlb && item.codeName!='其他设施'}">
										<img class="DB_plus plusImg icon"src="img/tree_on.png" alt="" />
									</c:if>
									<span class="DB_plus">${fn:escapeXml(item.codeName) }</span>
									<ul class="DB_3D">
										<c:forEach items="${xmzlList }" var="xmzl">
											<c:if test="${xmzl.superCode==item.codeValue }">
												<li><a id="icon" href="javascript:void(0);">${fn:escapeXml(xmzl.codeName) }</a></li>
											</c:if>
										</c:forEach>
									</ul>
								</li>
							</c:forEach>
						</ul>
					</li>
				</ul>
		    </c:if>
	    </div>
	</div>
</div>