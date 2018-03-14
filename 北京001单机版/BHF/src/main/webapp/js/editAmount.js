/**
 * Created by LJDY529 on 2017/1/11.
 */
$(function(){
    //加载进来计算总额
    var amount =0;
    $(".editItems").each(function(){
        if($.trim($(this).val())!=""&&$.trim($(this).val())!=null){//去首位空格判断。避免页面显示NaN
        	var reg = new RegExp(",","g");
        	$(this).val($.trim($(this).val()).replace(reg,""));
            amount += parseFloat($(this).val());
        }
    });
    if(amount!=0){
        $(".itemAmount").html(parseFloat(amount.toFixed(4)));
    }/*else{
    	$(".itemAmount").html(amount);
    }*/
    
    //此页面加载，设定有值的列表高度
	$(".editItems").each(function(){
		if($(this).text()==""||$(this).text()==null){
			$(this).height("17px");
		}else{
			$(this).height("auto");
			$(this).height($(this)[0].scrollHeight);
			$(this).after("<div id='cloneT'></div>");
			$("#cloneT").append($(this).clone());
			$("#cloneT").find("textarea").val($(this).val());//在火狐下克隆的textarea内容为空
			$("#cloneT").find("textarea").height("17px");
			if($("#cloneT").find("textarea")[0].scrollHeight<=17){
				$(this).height("17px");
			}
			$("#cloneT").remove();
		}
	});

    
    //高度自动适应的规划投资总额填写
    $(document).on("input propertyChange","textarea.editItems",function(){
        $(this).height("auto");
        $(this).height($(this)[0].scrollHeight);
        $(this).after("<div id='cloneT'></div>");
        $("#cloneT").append($(this).clone());
        $("#cloneT").find("textarea").val($(this).val());//在火狐下克隆的textarea内容为空
        $("#cloneT").find("textarea").height("17px");
        if($("#cloneT").find("textarea")[0].scrollHeight<=17){
            $(this).height("17px");
        }
        $("#cloneT").remove();
        //动态创建一个textarea 叫做b 具备a的一切样式 .最好的方式是将a clone下来。获取到里面的value值。
        // 设置当前的height为line-height也就是 30.
        //判断b的scrollHeight 的值是否大于30 即可
        excelH = $(".tab_div .tab_cont").eq(0).height()+20;//动态改变可见区域的高度，important
        $(".tab_div").height(excelH);
    });
    //varchar(14,4)验证
    $.validator.addMethod("numberV",function(value, element, param){
        var exp=new RegExp(/^([1-9][0-9]{0,9}||[0]||[1-9][0-9]{0,9}\.[0-9]{1,4}||[0]\.[0-9]{1,4})$/);
        return this.optional(element) || exp.test(value.trim());
    },"应输入数字，且整数位最多10位，小数位最多4位");

    //点击编辑，可以填写规划投资总额
    $(".editLink").click(function(){
        $(".editItems").removeAttr("disabled");
        $(".itemAmount").html("<button type='button' class='icon-ok'>√</button>");
        $(".editTd").css("background-color","#eee");
    });
    
    //点击编辑，可以填写边界线、海岸线
   /* $(".bjxEditLink").click(function(){
        $(".bjxEditItems").prop("contenteditable",true);
        //$(".itemAmount").html("<button type='button' class='icon-ok'>√</button>");
        $(".bjxEditTd").css("background-color","#eee");
        $(".bjxEditItems").css("background-color","#eee");
    });*/
    
    //点击其他位置隐藏提示框
    $("textarea").bind("click",function(e) {
        var target = $(e.target);
        if (target.closest(".tips").length == 0) {
            $(".tips").remove();
        }
    });
    //编辑完成提交
    $(document).on("click",".icon-ok",function(){
        $(".tips").remove();//移除所有的提示
        var success = formValidate().form();
        if(success){
        	$(".editItems").each(function(){
                if($.trim($(this).val())!=""&&$.trim($(this).val())!=null){//去首位空格判断。避免页面显示NaN
                	$(this).val($.trim($(this).val()));
                }else{
                	$(this).val("");
                }
            });
            document.formAmount.submit();
        }
    });
});
function formValidate(){
    return $("#formAmount").validate({
        onkeyup:false,//在 keyup 时验证false
        onfocusout:false,//失去焦点时验证（不包括复选框/单选按钮）false
        onfocusin:false,//获得焦点的事件
        rules: {
            editItems:{numberV:true}
        },
        showErrors : function(errorMap, errorList) {
            $.each(errorList, function(i, v) {
                var errorSpan = "<span class='tips'></span>";
                $(v.element).parent().append(errorSpan);
                $(v.element).parent().find(".tips").text(v.message);
                return false;
            });
        }
    });
};