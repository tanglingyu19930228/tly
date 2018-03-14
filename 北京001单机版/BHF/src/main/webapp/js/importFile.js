/**
 * Created by LJDY529 on 2016/12/20.
 */
var local = window.location;
var contextPath = local.pathname.split("/")[1];
var basePath = local.protocol + "//" + local.host + "/" + contextPath;

var $list,$btn,$whjlBtn,$whjlList,$whxmBtn,$whxmList;
var fileCountZip;
$(function(){
	$btn =$("#uploadBtn");   //开始上传按钮  
	$whjlBtn =$("#whjlUploadBtn");   //开始上传按钮  (维护记录)
	$whxmBtn =$("#whxmUploadBtn");   //开始上传按钮  (维护项目)
    $list = $('#fileList');
    $whjlList = $('#whjlFileList');
    $whxmList = $('#whxmFileList');
//    $zipList = $('.zip-list:visible');
    $btn.on('click', function() {   
        uploader.upload(); 
    }); 
    $whjlBtn.on('click', function() {   
    	uploader2.upload(); 
    }); 
    $whxmBtn.on('click', function() {   
    	uploader3.upload(); 
    }); 
});
// 初始化Web Uploader
var uploader,uploader1,uploader2,uploader3;//uploader1为附件上传使用对象，其它地方不可用 ;  uploader2用于维护记录 ; uploader3用于维护项目 ;
function initUploaderXls(){
    uploader   = WebUploader.create({

        // 选完文件后，是否自动上传。
        auto: false,

        // swf文件路径
        swf: basePath + '/js/libs/Uploader.swf',

        // 文件接收服务端。
        server:'import/import_Excel?partTag='+partTag,
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePicker',

        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,

        fileNumLimit:2,//最大上传数量
        // 只允许选择excel文件。
        accept: {
            title: 'excel',
            extensions: 'xls,xlsx',
            mimeTypes: '.xls,.xlsx'
        }
    });
    uploader.on('beforeFileQueued',function(file){
        //如果文件是0k
        if(file.size==0){
        	 $("#fileWarning").html("文件大小为0,无法上传");
	        setTimeout(function(){
	        	$("#fileWarning").html("");
	        },3000);
        }
    });
    // 当有文件添加进来的时候
    var fileCount = 0;//选择文件数量
    uploader.on( 'fileQueued', function( file ) {
        if(fileCount==0){
            $("#fileList .fileNameView").hide();
        }
        var $li = $(
            '<div id="' + file.id + '" class="file-item">' +
            '<div class="info"><span class="file-name">' + file.name + '</span><span class="remove-this" title="移除文件">×</span></div>' +
            '<p class="state" >等待上传...</p></div>'
        );

        // $list为容器jQuery实例
        $list.append( $li );
        fileCount++;
        $li.on('click', '.remove-this', function() {
        	var $item = $(this).parents(".file-item");
        	uploader.removeFile(uploader.getFile($item.attr("id")),true);
            $item.remove();
            fileCount--;
            if(fileCount==0){
                $("#fileList .fileNameView").show();
            }
        });

    });
    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress-bar');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar progress-bar-success" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo( $li ).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css( 'width', percentage * 100 + '%' );
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on( 'uploadSuccess', function( file,response ) {
//    	console.log(response);
    	if(response._raw!=""&&response._raw!="导入成功"){
    		$( '#'+file.id ).find('p.state').text("");
    		$( '#'+file.id ).append("<div class='warning'>"+response._raw+"</div>");
    	}else{
    		$( '#'+file.id ).find('p.state').text('导入成功');
    		if(partTag == '1' || partTag == '2'){
    			$("#postForm").empty();
    			var f=$("#postForm")[0];
    			f.action='annualPlan/load';
    			f.innerHTML='<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
    			f.submit();
    		}else if(partTag == '3'){
    			toPart('implement/load',3);
    		}else if(partTag == '4'){
    			toPart('usemaintenance/load',4);
    		}
    	}
        $( '#'+file.id ).find(".remove-this").remove();//已经上传的不可删除 
    });

    // 文件上传失败，显示上传出错。
    uploader.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('p.state').text('上传出错');
    });

    // 完成上传完了，成功或者失败，先删除进度条。
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });

    uploader.on("error",function (type){
        if (type=="Q_TYPE_DENIED"){
            $("#fileWarning").html("请上传excel格式文件");
        }else if(type=="Q_EXCEED_NUM_LIMIT"){
        	$("#fileWarning").html("文件数量不能超过"+uploader.options.fileNumLimit+"个");
        }
        setTimeout(function(){
        	$("#fileWarning").html("");
        },3000);
    });
}
//初始化附件上传
function initUploaderZip(xmmc,szsf,type){
    var obj;
    if(partTag=="2"){
        obj=type+" #plan";
    }else if(partTag=="3"){
        obj=type+" #implement";
    }else if(partTag=="4"){
        obj=type+" #maintenance";
    }

    uploader1   = WebUploader.create({

        // 选完文件后，是否自动上传。
        auto: true,

        // swf文件路径
        swf: basePath + '/js/libs/Uploader.swf',

        // 文件接收服务端。
        server:'file/upload?szsf='+szsf+'&xmmc='+encodeURIComponent(encodeURIComponent(xmmc))+'&partTag='+partTag,
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        //pick: '.upload-btn1',
        pick:obj,

        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,

        fileNumLimit:5,//最大上传数量
        // 只允许选择excel文件。
        accept: {
            title: 'zip',
            extensions: 'txt,img,jpg,png,gif,jpeg,doc,docx,xls,xlsx,zip,rar',
            mimeTypes: '.txt,.img,.jpg,.png,.gif,.jpeg,.doc,.docx,.xls,.xlsx,.zip,.rar'
        }
    });
    uploader1.on('beforeFileQueued',function(file){
        //如果文件是0k
        if(file.size==0){
        	$(".zipWarning").html("文件大小为0,无法上传");
	        setTimeout(function(){
	        	$(".zipWarning").html("");
	        },3000);
        }
    });
    fileCountZip = 0;
    // 当有文件添加进来的时候
    uploader1.on( 'fileQueued', function( file ) {
        if(fileCountZip==0){
            //$(".zip-list:visible .fileNameView").hide();
            $(obj+"_file").hide();
        }
        //如果超过了最大上传数量就禁止上传并给出提示
        if(uploader1.options.fileNumLimit==fileCountZip){
        	uploader1.cancelFile( file );
        	$(".zipWarning").html("文件数量不能超过"+uploader1.options.fileNumLimit+"个");
	        setTimeout(function(){
	        	$(".zipWarning").html("");
	        },3000);
        	return false;
        }
        var $li = $(
            '<div id="' + file.id + '" class="file-item">' +
            '<div class="info"><span class="file-name">' + file.name + '</span><span class="remove-this" title="移除文件">×</span></div>' +
            '<p class="state" style="color:red;">等待上传...</p></div>'
        );
        // $list为容器jQuery实例
        //$('.zip-list:visible').append( $li );
        $(obj).prev().append($li);
        if($li.prev().hasClass("file-exist")){
        	$li.css("margin-top","10px");
		}
        fileCountZip++;
        $li.on('click', '.remove-this', function() {
        	var $item = $(this).parents(".file-item");
        	uploader1.removeFile(uploader1.getFile($item.attr("id")),true);//删除队列中的文件
        	//移除文件路径
        	var fileName = $(this).prev().text();
        	var $input;
        	if($("#updateInfo").is(":visible")){
        		$input = $(".editFj");
        	}else{
        		$input = $(".addFj");
        	}
        	var fileArr =$input.val().split(",");
        	for(var i=0;i<fileArr.length;i++){
        		if(fileArr[i].indexOf(fileName)>-1){
        			if(partTag == '2' && fileArr[i].indexOf('/计划阶段/') > -1){
        				fileArr.splice(i,1);
        			}
        			if(partTag == '3' && fileArr[i].indexOf('/实施阶段/') > -1){
        				fileArr.splice(i,1);
        			}
        			if(partTag == '4' && fileArr[i].indexOf('/维护阶段/') > -1){
        				fileArr.splice(i,1);
        			}
            	}
        	};
        	$input.val(fileArr.join(","));
        	$item.next(".file-item").css("margin-top","10px");
        	if(fileCountZip ==1){
        		$item.next(".file-item").css("margin-top","0px");
        	}
        	$item.remove();
            fileCountZip--;
            if(fileCountZip==0){
                //$(".zip-list:visible .fileNameView").show();
                $(obj+"_file").show();
            }
        });

    });
    // 文件上传过程中创建进度条实时显示。
    uploader1.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress-bar');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar progress-bar-success" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo( $li ).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css( 'width', percentage * 100 + '%' );
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader1.on( 'uploadSuccess', function( file ,response) {
    	console.log(response);
    	if(response._raw.indexOf("上传失败")>-1){
    		$( '#'+file.id ).find('p.state').text(response._raw);
    	}else{
    		$( '#'+file.id ).find('p.state').text('上传成功');
    		//提交到后台文件地址
    		var $input;
        	if($("#updateInfo").is(":visible")){
        		$input = $(".editFj");
        	}else{
        		$input = $(".addFj");
        	}
    		var filePath = $input.val();
    		if(filePath==""||filePath==null||filePath=="null"){
    			$input.val(response._raw);
    		}else{
    			$input.val(filePath+","+response._raw);
    		}  
    	}
    });

    // 文件上传失败，显示上传出错。
    uploader1.on( 'uploadError', function( file ,response ) {
        $( '#'+file.id ).find('p.state').text('上传出错');
    });

    // 完成上传完了，成功或者失败，先删除进度条。
    uploader1.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });

    uploader1.on("error",function (type){
        if (type=="Q_TYPE_DENIED"){
            /*$("#zipWarning").html("请上传excel格式文件");*/
        }else if(type=="Q_EXCEED_NUM_LIMIT"){
        	$(".zipWarning").html("文件数量不能超过"+uploader1.options.fileNumLimit+"个");
        }
        setTimeout(function(){
        	$(".zipWarning").html("");
        },3000);
    });
};

//维护记录导入
function initWhjlUploaderXls(){
    uploader2   = WebUploader.create({

        // 选完文件后，是否自动上传。
        auto: false,

        // swf文件路径
        swf: basePath + '/js/libs/Uploader.swf',

        // 文件接收服务端。
        server:'import/importWhjl',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#whjlFilePicker',

        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,

        fileNumLimit:2,//最大上传数量
        // 只允许选择excel文件。
        accept: {
            title: 'excel',
            extensions: 'xls,xlsx',
            mimeTypes: '.xls,.xlsx'
        }
    });
    uploader2.on('beforeFileQueued',function(file){
        //如果文件是0k
        if(file.size==0){
        	 $("#whjlFileWarning").html("文件大小为0,无法上传");
	        setTimeout(function(){
	        	$("#whjlFileWarning").html("");
	        },3000);
        }
    });
    // 当有文件添加进来的时候
    var fileCount = 0;//选择文件数量
    uploader2.on( 'fileQueued', function( file ) {
        if(fileCount==0){
            $("#whjlFileList .fileNameView").hide();
        }
        var $li = $(
            '<div id="' + file.id + '" class="file-item">' +
            '<div class="info"><span class="file-name">' + file.name + '</span><span class="remove-this" title="移除文件">×</span></div>' +
            '<p class="state" >等待上传...</p></div>'
        );

        // $whjlList为容器jQuery实例
        $whjlList.append( $li );
        fileCount++;
        $li.on('click', '.remove-this', function() {
        	var $item = $(this).parents(".file-item");
        	uploader2.removeFile(uploader2.getFile($item.attr("id")),true);
            $item.remove();
            fileCount--;
            if(fileCount==0){
                $("#whjlFileList .fileNameView").show();
            }
        });

    });
    // 文件上传过程中创建进度条实时显示。
    uploader2.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress-bar');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar progress-bar-success" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo( $li ).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css( 'width', percentage * 100 + '%' );
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader2.on( 'uploadSuccess', function( file,response ) {
//    	console.log(response);
    	if(response._raw!=""&&response._raw!="导入成功"){
    		$( '#'+file.id ).find('p.state').text("");
    		$( '#'+file.id ).append("<p class='warning'>"+response._raw+"</p>");
    	}else{
    		$( '#'+file.id ).find('p.state').text('导入成功');
    		$("#importWhjlFile").dialog("close");
    	}
        $( '#'+file.id ).find(".remove-this").remove();//已经上传的不可删除 
    });

    // 文件上传失败，显示上传出错。
    uploader2.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('p.state').text('上传出错');
    });

    // 完成上传完了，成功或者失败，先删除进度条。
    uploader2.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });

    uploader2.on("error",function (type){
        if (type=="Q_TYPE_DENIED"){
            $("#whjlFileWarning").html("请上传excel格式文件");
        }else if(type=="Q_EXCEED_NUM_LIMIT"){
        	$("#whjlFileWarning").html("文件数量不能超过"+uploader2.options.fileNumLimit+"个");
        }
        setTimeout(function(){
        	$("#whjlFileWarning").html("");
        },3000);
    });
}
//维护项目导入
function initWhxmUploaderXls(){
	uploader3   = WebUploader.create({
		
		// 选完文件后，是否自动上传。
		auto: false,
		
		// swf文件路径
		swf: basePath + '/js/libs/Uploader.swf',
		
		// 文件接收服务端。
		server:'import/importWhxm',
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick: '#whxmFilePicker',
		
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		resize: false,
		
		fileNumLimit:2,//最大上传数量
		// 只允许选择excel文件。
		accept: {
			title: 'excel',
			extensions: 'xls,xlsx',
			mimeTypes: '.xls,.xlsx'
		}
	});
	uploader3.on('beforeFileQueued',function(file){
		//如果文件是0k
		if(file.size==0){
			$("#whxmFileWarning").html("文件大小为0,无法上传");
			setTimeout(function(){
				$("#whxmFileWarning").html("");
			},3000);
		}
	});
	// 当有文件添加进来的时候
	var fileCount = 0;//选择文件数量
	uploader3.on( 'fileQueued', function( file ) {
		if(fileCount==0){
			$("#whxmFileList .fileNameView").hide();
		}
		var $li = $(
				'<div id="' + file.id + '" class="file-item">' +
				'<div class="info"><span class="file-name">' + file.name + '</span><span class="remove-this" title="移除文件">×</span></div>' +
				'<p class="state" >等待上传...</p></div>'
		);
		
		// $whjlList为容器jQuery实例
		$whxmList.append( $li );
		fileCount++;
		$li.on('click', '.remove-this', function() {
			var $item = $(this).parents(".file-item");
			uploader3.removeFile(uploader3.getFile($item.attr("id")),true);
			$item.remove();
			fileCount--;
			if(fileCount==0){
				$("#whxmFileList .fileNameView").show();
			}
		});
		
	});
	// 文件上传过程中创建进度条实时显示。
	uploader3.on( 'uploadProgress', function( file, percentage ) {
		var $li = $( '#'+file.id ),
		$percent = $li.find('.progress-bar');
		
		// 避免重复创建
		if ( !$percent.length ) {
			$percent = $('<div class="progress progress-striped active">' +
					'<div class="progress-bar progress-bar-success" role="progressbar" style="width: 0%">' +
					'</div>' +
			'</div>').appendTo( $li ).find('.progress-bar');
		}
		
		$li.find('p.state').text('上传中');
		
		$percent.css( 'width', percentage * 100 + '%' );
	});
	
	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader3.on( 'uploadSuccess', function( file,response ) {
//    	console.log(response);
		if(response._raw!=""&&response._raw!="导入成功"){
			$( '#'+file.id ).find('p.state').text("");
			$( '#'+file.id ).append("<p class='warning'>"+response._raw+"</p>");
			$( '#'+file.id ).find(".remove-this").remove();//已经上传的不可删除 
		}else{
			$( '#'+file.id ).find('p.state').text('导入成功');
			whxmAjaxData();
			$( '#'+file.id ).find(".remove-this").remove();//已经上传的不可删除 
			$("#importWhxmFile").dialog("close");
		}
	});

	// 文件上传失败，显示上传出错。
	uploader3.on( 'uploadError', function( file ) {
		$( '#'+file.id ).find('p.state').text('上传出错');
	});
	
	// 完成上传完了，成功或者失败，先删除进度条。
	uploader3.on( 'uploadComplete', function( file ) {
		$( '#'+file.id ).find('.progress').fadeOut();
	});
	
	uploader3.on("error",function (type){
		if (type=="Q_TYPE_DENIED"){
			$("#whjlFileWarning").html("请上传excel格式文件");
		}else if(type=="Q_EXCEED_NUM_LIMIT"){
			$("#whjlFileWarning").html("文件数量不能超过"+uploader2.options.fileNumLimit+"个");
		}
		setTimeout(function(){
			$("#whjlFileWarning").html("");
		},3000);
	});
}