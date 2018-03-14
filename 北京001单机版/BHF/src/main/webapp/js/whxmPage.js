/**
 * Created by LJDY529 on 2016/12/20.
 */
/*
var pageCount =  parseInt($("#getPageCount").val());     	//总页数
var currentPage =  parseInt($("#getCurrentPage").val());    //当前页
var activePage;         //当前高亮的页数
*/
$(function(){
	loadWhxmPage();
});

//加载判断页码数
function loadWhxmPage(){
	var pageCount =  parseInt($("#getWhxmPageCount").val());     	//总页数
	var currentPage =  parseInt($("#getWhxmCurrentPage").val());    //当前页
	var activePage;         //当前高亮的页数
	if(pageCount==0){//总页数为O，禁用上一页下一页。
		$(".whxmPagePrev").addClass("disabled");
        $(".whxmPageNext").addClass("disabled");
	}else if(pageCount==1){
        $(".whxmP_num").find(".whxmPageNext").before("<a>"+pageCount+"</a>");
        $(".whxmPagePrev").addClass("disabled");
        $(".whxmPageNext").addClass("disabled");
    }else if(pageCount<=7){
        for(var i=1;i<=pageCount;i++){
            $(".whxmP_num").find(".whxmPageNext").before("<a>"+i+"</a>");
        }
    }else {
        if (currentPage <= 5) {
            for (var i = 1; i <= 7; i++) {
                $(".whxmP_num").find(".whxmPageNext").before("<a>" + i + "</a>");
            }
            $(".whxmP_num .whxmPageNext").before("<span class='dot'>...</span>");
        } else if (currentPage > 5) {
            if (currentPage < pageCount - 2) {
                $(".whxmP_num").find(".whxmPageNext").before("<a>1</a><a>2</a><span class='dot'>...</span><a>" + (currentPage - 2) + "</a><a>" + (currentPage - 1) + "</a><a>" + currentPage + "</a><a>" + (currentPage + 1) + "</a><a>" + (currentPage + 2) + "</a><span class='dot'>...</span>");
            }else if (currentPage >= pageCount - 2 || currentPage <= pageCount) {
                if(currentPage == pageCount - 1) {
                    $(".whxmP_num").find(".whxmPageNext").before("<a>1</a><a>2</a><span class='dot'>...</span><a>"+ (currentPage - 3) +"</a><a>" + (currentPage - 2) + "</a><a>" + (currentPage - 1) + "</a><a>" + currentPage + "</a><a>" + (currentPage + 1) + "</a>");
                }else if (currentPage == pageCount - 2) {
                    $(".whxmP_num").find(".whxmPageNext").before("<a>1</a><a>2</a><span class='dot'>...</span><a>" + (currentPage - 2) + "</a><a>" + (currentPage - 1) + "</a><a>" + currentPage + "</a><a>" + (currentPage + 1) + "</a><a>" + (currentPage + 2) + "</a>");
                }else if(currentPage == pageCount){
                    $(".whxmP_num").find(".whxmPageNext").before("<a>1</a><a>2</a><span class='dot'>...</span><a>"+ (currentPage - 4) +"</a><a>"+ (currentPage - 3) +"</a><a>" + (currentPage - 2) + "</a><a>" + (currentPage - 1) + "</a><a>" + currentPage + "</a>");
                }
            }
        }
    }
    activePage = currentPage;
    $(".whxmP_num span").each(function(){
    	if($(this).text()!="..."){
    		$(this).attr("onclick","whxmPageClick($(this))");
    	}
    });
    $(".whxmP_num a").each(function(){
    	$(this).attr("onclick","whxmPageClick($(this))");
        if($(this).text()==activePage){
            $(this).addClass("active");
        }
    });

    //判断当前为第一页或者最后一页，上一页或下一页将禁止点击
    if(currentPage==1){
        $(".whxmPagePrev").addClass("disabled");
    }else if(currentPage==pageCount){
        $(".whxmPageNext").addClass("disabled");
    }
    
    //确定点击事件
    $('#whxmConfirm').unbind();//先解除绑定，防止多次提交   
    $('#whxmConfirm').click(function(){
    	var page = $('#whxmTurnPage').val();
    	//输入非数字或者0，页码默认为1
    	if(!/^([1-9][0-9]*)$/.test(parseInt(page))){
    		page=1;
    		return;
    	}
    	//当输入的数字大于总页数，页面跳转到最后一页
    	if(parseInt(page)<=pageCount){
    		$("#whxmOffiset").val(page);
    	}else if(pageCount==0){
    		return;
    	}else{
    		$("#whxmOffiset").val(pageCount);
    	}
    	whxmAjaxData();
    });
};


function whxmPageClick(obj){
	if(!obj.hasClass("disabled")){
		var page = parseInt($("#getWhxmCurrentPage").val());
		if(obj.text().indexOf("上一页")>-1){
    		$("#whxmOffiset").val(page-1);
    	}else if(obj.text().indexOf("下一页")>-1){
    		$("#whxmOffiset").val(page+1);
    	}else{
    		$("#whxmOffiset").val(obj.text());
    	}
		whxmAjaxData();
	}
}