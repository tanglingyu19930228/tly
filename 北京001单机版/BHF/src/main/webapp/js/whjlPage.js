/**
 * Created by LJDY529 on 2016/12/20.
 */
/*
var pageCount =  parseInt($("#getPageCount").val());     	//总页数
var currentPage =  parseInt($("#getCurrentPage").val());    //当前页
var activePage;         //当前高亮的页数
*/
$(function(){
	loadWhjlPage();
});

//加载判断页码数
function loadWhjlPage(){
	var pageCount =  parseInt($("#getWhjlPageCount").val());     	//总页数
	var currentPage =  parseInt($("#getWhjlCurrentPage").val());    //当前页
	var activePage;         //当前高亮的页数
	if(pageCount==0){//总页数为O，禁用上一页下一页。
		$(".whjlPagePrev").addClass("disabled");
        $(".whjlPageNext").addClass("disabled");
	}else if(pageCount==1){
        $(".whjlP_num").find(".whjlPageNext").before("<a>"+pageCount+"</a>");
        $(".whjlPagePrev").addClass("disabled");
        $(".whjlPageNext").addClass("disabled");
    }else if(pageCount<=7){
        for(var i=1;i<=pageCount;i++){
            $(".whjlP_num").find(".whjlPageNext").before("<a>"+i+"</a>");
        }
    }else {
        if (currentPage <= 5) {
            for (var i = 1; i <= 7; i++) {
                $(".whjlP_num").find(".whjlPageNext").before("<a>" + i + "</a>");
            }
            $(".whjlP_num .whjlPageNext").before("<span class='dot'>...</span>");
        } else if (currentPage > 5) {
            if (currentPage < pageCount - 2) {
                $(".whjlP_num").find(".whjlPageNext").before("<a>1</a><a>2</a><span class='dot'>...</span><a>" + (currentPage - 2) + "</a><a>" + (currentPage - 1) + "</a><a>" + currentPage + "</a><a>" + (currentPage + 1) + "</a><a>" + (currentPage + 2) + "</a><span class='dot'>...</span>");
            }else if (currentPage >= pageCount - 2 || currentPage <= pageCount) {
                if(currentPage == pageCount - 1) {
                    $(".whjlP_num").find(".whjlPageNext").before("<a>1</a><a>2</a><span class='dot'>...</span><a>"+ (currentPage - 3) +"</a><a>" + (currentPage - 2) + "</a><a>" + (currentPage - 1) + "</a><a>" + currentPage + "</a><a>" + (currentPage + 1) + "</a>");
                }else if (currentPage == pageCount - 2) {
                    $(".whjlP_num").find(".whjlPageNext").before("<a>1</a><a>2</a><span class='dot'>...</span><a>" + (currentPage - 2) + "</a><a>" + (currentPage - 1) + "</a><a>" + currentPage + "</a><a>" + (currentPage + 1) + "</a><a>" + (currentPage + 2) + "</a>");
                }else if(currentPage == pageCount){
                    $(".whjlP_num").find(".whjlPageNext").before("<a>1</a><a>2</a><span class='dot'>...</span><a>"+ (currentPage - 4) +"</a><a>"+ (currentPage - 3) +"</a><a>" + (currentPage - 2) + "</a><a>" + (currentPage - 1) + "</a><a>" + currentPage + "</a>");
                }
            }
        }
    }
    activePage = currentPage;
    $(".whjlP_num span").each(function(){
    	if($(this).text()!="..."){
    		$(this).attr("onclick","whjlPageClick($(this))");
    	}
    });
    $(".whjlP_num a").each(function(){
    	$(this).attr("onclick","whjlPageClick($(this))");
        if($(this).text()==activePage){
            $(this).addClass("active");
        }
    });

    //判断当前为第一页或者最后一页，上一页或下一页将禁止点击
    if(currentPage==1){
        $(".whjlPagePrev").addClass("disabled");
    }else if(currentPage==pageCount){
        $(".whjlPageNext").addClass("disabled");
    }
    
    //确定点击事件
    $('#whjlConfirm').unbind();//先解除绑定，防止多次提交   
    $('#whjlConfirm').click(function(){
    	var page = $('#whjlTurnPage').val();
    	//输入非数字或者0，页码默认为1
    	if(!/^([1-9][0-9]*)$/.test(parseInt(page))){
    		page=1;
    		return;
    	}
    	//当输入的数字大于总页数，页面跳转到最后一页
    	if(parseInt(page)<=pageCount){
    		$("#whjlOffiset").val(page);
    	}else if(pageCount==0){
    		return;
    	}else{
    		$("#whjlOffiset").val(pageCount);
    	}
    	whjlAjaxData();
    });
};


function whjlPageClick(obj){
	if(!obj.hasClass("disabled")){
		var page = parseInt($("#getWhjlCurrentPage").val());
		if(obj.text().indexOf("上一页")>-1){
    		$("#whjlOffiset").val(page-1);
    	}else if(obj.text().indexOf("下一页")>-1){
    		$("#whjlOffiset").val(page+1);
    	}else{
    		$("#whjlOffiset").val(obj.text());
    	}
		whjlAjaxData();
	}
}