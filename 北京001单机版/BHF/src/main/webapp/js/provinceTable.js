$(function(){
   $(".main").height($(window).height()-69);
   $(".tab_div").height($(".m_right").height()-2);//减去边框
   $("#treeMenu").height($(".m_right").height()-15-2);//减去边框和marginTop
    //图表高度
    $(".chart_item").height(($(".tab_div").height()-30-5-($(".cp_header").height()+22)*2)/2);
    $(".chart_item").css("min-height","300px");
  /* $(".toggleBtn button").click(function(){
      tabToggle($(this),false,[[$("#chart_1"),option1],[$("#chart_2"),option2],[$("#chart_3"),option3],[$("#chart_4"),option4],[$("#chart_5"),option5]]);
   });*/

   $(window).resize(function(){
      $(".main").height($(window).height()-69);
      $(".tab_div").height($(".m_right").height()-2);
      $("#treeMenu").height($(".m_right").height()-15-2);
      $(".scrollMenu").height($("#treeMenu").height()-$(".nation").height()-2);
      if(!toggleFlag){
         translateH(false);
      }
   });
   $(".scrollMenu").height($("#treeMenu").height()-$(".nation").height()-2);

   $('#treeMenu').DB_treeMenu({
      pageNum:null,
      motionType:'slide',                  // (slide,none)
      motionSpeed:200
   });
   
   $(".scrollMenu").mCustomScrollbar({mouseWheelPixels: 100});//scrollButtons:{enable:true}
   
   $("span.DB_plus").click(function(){
     if($(this).next().height()==1){
        $(this).prev().attr("src","img/tree_off.png");
     }else{
        $(this).prev().attr("src","img/tree_on.png");
     }
   });
});