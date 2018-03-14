/**
 * Created by LJDY529 on 2016/12/20.
 */
/*
var pageCount =  parseInt($("#getPageCount").val());     	//总页数
var currentPage =  parseInt($("#getCurrentPage").val());    //当前页
var activePage;         //当前高亮的页数
*/
$(function(){
	loadPageOther();
	 //点击下拉框事件
    $(".typeTitleFn").click(function(){
        $(this).find(".typeItemFn").slideToggle();
        $(this).parent().siblings().find(".typeItemFn").slideUp();
    });
    $(".typeItemFn a").click(function(){
        var text=$(this).text();
        $(this).parents(".typeTitleFn").find(".t_name").text(text);
        $(this).parents(".typeItemFn").slideDown();
    });
    initJkz_zl();
    
    //关闭按钮事件
    $("#jkz_zlDlg").prev().find("a.panel-tool-close").click(function(){
    	$("#jkz_zlQueryDiv").empty();
    	$("#jkz_zlDlg").dialog('close');
    });
});

//加载判断页码数
function loadPageOther(){
	var pageOtherCount =  parseInt($("#getPageOtherCount").val());     	//总页数
	var currentPageOther =  parseInt($("#getCurrentPageOther").val());    //当前页
	var activePageOther;         //当前高亮的页数
	if(pageOtherCount==0){//总页数为O，禁用上一页下一页。
		$(".pageOtherPrev").addClass("disabled");
        $(".pageOtherNext").addClass("disabled");
	}else if(pageOtherCount==1){
        $(".pOther_num").find(".pageOtherNext").before("<a>"+pageOtherCount+"</a>");
        $(".pageOtherPrev").addClass("disabled");
        $(".pageOtherNext").addClass("disabled");
    }else if(pageOtherCount<=7){
        for(var i=1;i<=pageOtherCount;i++){
            $(".pOther_num").find(".pageOtherNext").before("<a>"+i+"</a>");
        }
    }else {
        if (currentPageOther <= 5) {
            for (var i = 1; i <= 7; i++) {
                $(".pOther_num").find(".pageOtherNext").before("<a>" + i + "</a>");
            }
            $(".pOther_num .pageOtherNext").before("<span class='dot'>...</span>");
        } else if (currentPageOther > 5) {
            if (currentPageOther < pageOtherCount - 2) {
                $(".pOther_num").find(".pageOtherNext").before("<a>1</a><a>2</a><span class='dot'>...</span><a>" + (currentPageOther - 2) + "</a><a>" + (currentPageOther - 1) + "</a><a>" + currentPageOther + "</a><a>" + (currentPageOther + 1) + "</a><a>" + (currentPageOther + 2) + "</a><span class='dot'>...</span>");
            }else if (currentPageOther >= pageOtherCount - 2 || currentPageOther <= pageOtherCount) {
                if(currentPageOther == pageOtherCount - 1) {
                    $(".pOther_num").find(".pageOtherNext").before("<a>1</a><a>2</a><span class='dot'>...</span><a>"+ (currentPageOther - 3) +"</a><a>" + (currentPageOther - 2) + "</a><a>" + (currentPageOther - 1) + "</a><a>" + currentPageOther + "</a><a>" + (currentPageOther + 1) + "</a>");
                }else if (currentPageOther == pageOtherCount - 2) {
                    $(".pOther_num").find(".pageOtherNext").before("<a>1</a><a>2</a><span class='dot'>...</span><a>" + (currentPageOther - 2) + "</a><a>" + (currentPageOther - 1) + "</a><a>" + currentPageOther + "</a><a>" + (currentPageOther + 1) + "</a><a>" + (currentPageOther + 2) + "</a>");
                }else if(currentPageOther == pageOtherCount){
                    $(".pOther_num").find(".pageOtherNext").before("<a>1</a><a>2</a><span class='dot'>...</span><a>"+ (currentPageOther - 4) +"</a><a>"+ (currentPageOther - 3) +"</a><a>" + (currentPageOther - 2) + "</a><a>" + (currentPageOther - 1) + "</a><a>" + currentPageOther + "</a>");
                }
            }
        }
    }
    activePageOther = currentPageOther;
    $(".pOther_num span").each(function(){
    	if($(this).text()!="..."){
    		$(this).attr("onclick","pageOtherClick($(this))");
    	}
    });
    $(".pOther_num a").each(function(){
    	$(this).attr("onclick","pageOtherClick($(this))");
        if($(this).text()==activePageOther){
            $(this).addClass("active");
        }
    });

    //判断当前为第一页或者最后一页，上一页或下一页将禁止点击
    if(currentPageOther==1){
        $(".pageOtherPrev").addClass("disabled");
    }else if(currentPageOther==pageOtherCount){
        $(".pageOtherNext").addClass("disabled");
    }
    
    //确定点击事件
    $("#confirmOther").unbind();//先解除绑定，防止多次提交   
    $('#confirmOther').click(function(){
    	var pageOther = $('#turnPageOther').val();
    	//输入非数字或者0，页码默认为1
    	if(!/^([1-9][0-9]*)$/.test(parseInt(pageOther))){
    		pageOther=1;
    		return;
    	}
    	//当输入的数字大于总页数，页面跳转到最后一页
    	if(parseInt(pageOther)<=pageOtherCount){
    		$("#offisetOther").val(pageOther);
    	}else if(pageOtherCount==0){
    		return;
    	}
    	else{
    		$("#offisetOther").val(pageOtherCount);
    	}
    	ajaxJkz_zlData();
    });
};


function pageOtherClick(obj){
	if(!obj.hasClass("disabled")){
		var pageOther = parseInt($("#getCurrentPageOther").val());
		if(obj.text().indexOf("上一页")>-1){
    		$("#offisetOther").val(pageOther-1);
    	}else if(obj.text().indexOf("下一页")>-1){
    		$("#offisetOther").val(pageOther+1);
    	}else{
    		$("#offisetOther").val(obj.text());
    	}
		ajaxJkz_zlData();
	}
}

function initJkz_zl(){
	$(".typeItemFn a").click(function(){
		$("#offisetOther").val("1");//表头筛选，默认为第一页
		ajaxJkz_zlData();
	});
	transFjOther();
}

function transFjOther(){
	$(".file-name-other").each(function(){
		var value = $(this).text();
		if(value){
			var arr = new Array();
			var fileArr = value.split(",");
			for(var i=0;i<fileArr.length;i++){
				fileArr[i] = fileArr[i].substring(fileArr[i].lastIndexOf("/")+1).substring(32);
				arr.push(fileArr[i]);
			}
			$(this).text(arr.join(","));
			$(this).attr("title",arr.join(","));
		}
	});
}




