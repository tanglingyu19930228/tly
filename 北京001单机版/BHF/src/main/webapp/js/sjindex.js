$(function(){
    sjdraw();
    //点击统计表 18个边防省跳转到相应的各省
    $(".provLink").click(function(){
    	$("#postForm").empty();
    	var f=$("#postForm")[0];
        f.action='annualPlanProvince/load';
        f.innerHTML='<input type="hidden" name="szsf" value="'+$(this).text()+'"/>'
        + '<input type="hidden" name="partTag" value="'+partTag+'"/>';//top.jsp中设置partTag为全局变量
        f.submit();
    });
    //总表按钮点击事件
    $(".btn_tj").click(function(){
        if($(this).text()=="导入数据"){
            if(uploader != undefined){
                uploader.destroy();
            }
            initUploaderXls();
            $("#fileList .file-item").remove();//清空上次未上传的文件
            $("#fileList").find(".fileNameView").show();
            $("#importFile").show().dialog("open");
        }else if($(this).text()=="生成报文"){
            if($("#role").val()==2){
            	$("#postForm").empty();
                var f=$("#postForm")[0];
                f.action='xmzl/exportWord';
                f.submit();
            }
        }else if($(this).text()=="生成报表"){
            // 使用outerHTML属性获取整个table元素的HTML代码（包括<table>标签），然后包装成一个完整的HTML文档，设置charset为urf-8以防止中文乱码
            var html = "<html><head><meta charset='utf-8' /></head><body>" + document.getElementsByTagName("table")[0].outerHTML + "</body></html>";
            // 实例化一个Blob对象，其构造函数的第一个参数是包含文件内容的数组，第二个参数是包含文件类型属性的对象
            var blob = new Blob([html], { type: "application/vnd.ms-excel" });

            var a = document.getElementById("tableToExcle");
            // 利用URL.createObjectURL()方法为a元素生成blob URL
            a.href = URL.createObjectURL(blob);
            // 设置文件名，目前只有Chrome和FireFox支持此属性
            var fileName = $("#title").text();
            a.download = fileName+".xls";
            a.click();
            //location.reload();
        }else if($(this).text()=="导出数据"){
        	var tznd = null;
			if(partTag == '1'){
				tznd = $("#tznd").val();
			}
			$("#postForm").empty();
        	var f=$("#postForm")[0];
            f.action='xmzl/exportAll';
            f.innerHTML='<input type="hidden" name="partTag" value="'+partTag+'"/>'//partTag在top.jsp中，为全局变量
            +'<input type="hidden" name="tznd" value="'+tznd+'"/>';
            f.submit();
        }
    });

    //"取消"按钮事件
    $(".cancal_btn").click(function(){
        $(this).parents(".easyui-dialog").dialog('close');
        $(".tab_title button").removeClass("current");
        $(".tips").remove();//移除所有的提示
    });
});

//画表头
function sjdraw() {
    var canvas = document.getElementById("canvas");
    if (canvas.getContext) {
        var ctx = canvas.getContext("2d");
        ctx.strokeStyle = "#585858";
        ctx.beginPath();
        ctx.moveTo(0, 0);
        ctx.lineTo(100, 63);
        ctx.moveTo(0, 0);

        ctx.lineTo(260, 55);

//                ctx.closePath();
        ctx.stroke();
    }
};