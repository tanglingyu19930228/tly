/**
 * Created by LJDY529 on 2016/12/15.
 */
var excelH, chartH;//统计表的高度，图表的高度
$(function(){
    //图表高度
    $(".chart_item").height(($(window).height()-$(".top").height()-30-1-10-($(".cp_header").height()+22)*2)/2);
    $(".chart_item").css("min-height","300px");
    excelH = $(".tab_div .tab_cont").eq(0).height()+20;//margin-top15加上底部5px padding
    chartH = $(".tab_div .tab_cont").eq(1).height()+5;//底部5px padding
    //画表头
    draw();
    /*if($("#role").val()=='1'){
    	draw();
    }else if($("#role").val()=='2'){
    	sjdraw();
    }*/
    //可见区域高度默认为表的高度
    $(".tab_div").height(excelH);
    /*$(".toggleBtn button").click(function(){
        tabToggle($(this),true,[[$("#container"),option1],[$("#container4"),option2],[$("#container1"),option3],[$("#container2"),option4],[$("#container3"),option5]]);
    });*/
    $(window).resize(function(){
        excelH = $(".tab_div .tab_cont").eq(0).height()+20;
        chartH = $(".tab_div .tab_cont").eq(1).height()+5;
        //判断当前显示的是统计图还是统计表
        if(toggleFlag){
            $(".tab_div").height(excelH);
        }else{
            $(".tab_div").height(chartH);
            translateH(true);
        }
    });
    //点击统计表 18个边防省跳转到相应的各省
    $(".provLink").click(function(){
    	$("#postForm").empty();
    	var f=$("#postForm")[0];
        f.action='annualPlanProvince/load';
        f.innerHTML='<input type="hidden" name="szsf" value="'+$(this).text()+'"/>'
        + '<input type="hidden" name="partTag" value="'+partTag+'"/>';//top.jsp中设置partTag为全局变量
        f.submit();
    });
});
//画表头
function draw() {
    var canvas = document.getElementById("canvas");
    if (canvas.getContext) {
    	var ctx = canvas.getContext("2d");
        ctx.strokeStyle = "#585858";
        ctx.beginPath();
        ctx.moveTo(0, 0);
        ctx.lineTo(100, 63);
        ctx.moveTo(0, 0);

        ctx.lineTo(260, 55);

        //ctx.closePath();
        ctx.stroke();
    }
};
