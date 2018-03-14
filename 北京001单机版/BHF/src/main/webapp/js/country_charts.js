var local = window.location;
var contextPath = local.pathname.split("/")[1];
var basePath = local.protocol + "//" + local.host + "/" + contextPath;

var option1,option2,option3,option4,option5;
//按设施类型统计投资比例--饼状图颜色
var pieColor1 = ['#659DC2','#E05552','#91C7AF'];
//按省份统计投资比例--饼状图颜色
var pieColor2 = ['#7289AB','#92CA8D','#E59D87','#E05552','#F49F44','#EFDD79',
    '#7AAFFF','#73A373','#91C7AF','#CA8623','#EDBD7D','#83DBA8',
    '#659DC2','#B2D38E','#9AA8E5','#FA8F6F','#78BCBF','#D59333'];
//按项目类别统计投资比例--饼状图颜色
var pieColor3 = ['#659DC2','#E05552','#91C7AF','#D59333','#7289AB'];

//省份数组
var provinceArr = new Array();
//项目类别数组
var xmlbArr = new Array();
//按省份统计投资金额数据集合
var columnData1 = new Array();
//按项目类别统计投资金额数据集合
var columnData2 = new Array();
//按设施类型统计投资比例数据集合
var pieData1 = new Array();
//按省份统计投资比例数据集合
var pieData2 = new Array();
//按项目类别统计投资比例数据集合
var pieData3 = new Array();

$(function(){
	//按省份统计
	var szsfMap = $("#szsfMap").val().replace("{","").replace("}","");
	var szsfArr = szsfMap.split(",");
	for(var i=0;i<szsfArr.length;i++){
		var arr = szsfArr[i].split("=");
		provinceArr.push($.trim(arr[0]));
		columnData1.push(parseFloat(arr[1]));
		pieData2.push([$.trim(arr[0]),parseFloat(arr[1])]);
	}
	//判断数据是否全是0，全是0则不生成统计图
	for(var i=0;i<columnData1.length;i++){
		if(columnData1[i]!=0){
			init_column1(provinceArr,columnData1);
			init_pie2(pieData2);
			break;
		}
	}
	//按项目类别统计
	var xmlbMap=$("#xmlbMap").val().replace("{","").replace("}","");
	var xmlb=xmlbMap.split(",");
	for(var i=0;i<xmlb.length;i++){
		var arr = xmlb[i].split("=");
		xmlbArr.push($.trim(arr[0]));
		columnData2.push(parseFloat(arr[1]));
		pieData3.push([$.trim(arr[0]),parseFloat(arr[1])]);
	}
	for(var i=0;i<columnData2.length;i++){
		if(columnData2[i]!=0){
			init_column2(xmlbArr,columnData2);
			init_pie3(pieData3);
			break;
		}
	}
	//按设施类型统计
	var sslxMap=$("#sslxMap").val().replace("{","").replace("}","");
	var sslxArr=sslxMap.split(",");
	for(var i=0;i<sslxArr.length;i++){
		var arr = sslxArr[i].split("=");
		pieData1.push([$.trim(arr[0]),parseFloat(arr[1])]);
	}
	for(var i=0;i<pieData1.length;i++){
		if(pieData1[i][1]!=0){
			init_pie1(pieData1);
			break;
		}
	}
});

//按省份统计投资金额--柱状图
function init_column1(provinceArr,data){
	option1 ={
	    chart: {
	        type: 'column',
	        marginTop: 65,
	        style: {
	            fontFamily: 'Microsoft YaHei',
	            fontSize:'13px'
	        }
	    },
	    credits: {//图表版权信息
	        enabled: false
	    },
	    title: {
	        text: null
	    },
	    legend:{
	        enabled:false
	    },
	    xAxis: {
	        labels: {
	            formatter: function () {
	            	return '<a href="'+basePath+'/annualPlanProvince/load?szsf='+this.value+'&partTag='+partTag+'">'+this.value+'</a>';
	            },
	            autoRotationLimit:40
	        },
	        categories: provinceArr,
	        crosshair: true
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: '单位：万元 ',
	            rotation: 0,
	            y: -30,
	            offset: 0,
	            align: 'high'
	        },
	        labels: {
                formatter: function() {
                	return this.value;
                }
            }
	    },
	    tooltip: {
	        headerFormat: '<span style="font-size:10px">{point.key}</span>',
	        pointFormat: '<span>{point.key}:<b>{point.y:.4f} 万元</b></span>',
	        shared: true,
	        useHTML: true
	    },
	    plotOptions: {
	        column: {
	            pointPadding: 0.2,
	            borderWidth: 0,
	            dataLabels: {
	                enabled: true,//默认显示数据
					crop:false,
					overflow:"none",
	                color:'#585858',
	                format:'{y:.4f}'
	            },
	            color: "#659DC2"//柱状图颜色
	        },
	        series: {
	            events: {
	                click: function (event) {//点击柱状图跳转到相应的省
	                	$("#postForm").empty();
	                	var f=$("#postForm")[0];
	                    f.action='annualPlanProvince/load';
	                    f.innerHTML='<input type="hidden" name="szsf" value="'+event.point.category+'"/>'
	                    +'<input type="hidden" name="partTag" value="'+partTag+'"/>';//partTag在top.jsp中，为全局变量
	                    f.submit();
	                }
	            },
	            states: {
	                hover: {
	                    brightness: -0.1//鼠标挪上的亮度，变深
	                }
	            },
	            cursor:'pointer'//柱状图点击手势
	        }
	    },
	    series: [{
	        name: '按省划分',
	        data: data
	    }]
	};
	Highcharts.chart('container',option1);
}

//按项目类别统计投资金额--柱状图
function init_column2(xmlbArr,data){
	option2 = {
		chart: {
			type: 'column',
			marginTop:65,
			style: {
				fontFamily: 'Microsoft YaHei',
				fontSize:'13px'
			}
		},
		credits:{
			enabled:false,
		},
		title:{
			text:null
		},
		legend:{
			enabled:false
		},
		xAxis:{
			categories:xmlbArr,
			crosshair:true,
			labels:{
				autoRotationLimit:40
			}
		},
		yAxis:{
			min:0,
			title:{
				text:'单位：万元',
				rotation:0,
				y:-30,
				offset:0,
				align:'high'
			},
			labels: {
                formatter: function() {
                	return this.value;
                }
            }
		},
		tooltip:{
			headerFormat:'<span style="font-size:10px">{point.key}</span>',
			pointFormat:'<span>{point.key}:<b>{point.y:.4f} 万元</b></span>',
			shared:true,
			useHTML:true
		},
		plotOptions:{
			column:{
				pointPadding:0.2,
				borderWidth:0,
				dataLabels:{
					enabled:true,
                    crop:false,
                    overflow:"none",
					color:'#585858',
					format:'{y:.4f}'
				},
				color:'#659DC2', 
			}
		},
		series:[{
			name:'按类型划分',
			data:data,
			states:{
				hover:{
					brightness: -0.1//鼠标挪上的亮度，变深
				}
			}
		}]
	},
	Highcharts.chart('container4',option2);
}

//按设施类型统计投资比例--饼状图
function init_pie1(pieData1){
	option3 = {
		chart:{
			type:'pie',
			options3d:{
				enabled:true,
				alpha:50,
				bete:0
			},
			style:{
				fontFamily: 'Microsoft YaHei',
                fontSize:'13px'
			}
		},
		colors:pieColor1,
		credits:{
			enabled: false
		},
		legend:{
			enabled:true, //显示图例
			itemStyle: {
				color: '#5f5f5f',
				fontSize:'13px'
			}
		},
		title:{
			text:null
		},
		tooltip:{
			pointFormat: '<b>{point.percentage:.4f}%</b>'
		},
		plotOptions:{
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				depth: 35,
				dataLabels: {
					enabled: true,
					format: '<b>{point.name}</b>: {point.percentage:.4f}%',
					color:'#585858',
					distance: 10
				},
				showInLegend: true//显示图例
			}
		},
		series:[{
			type:'pie',
			data:pieData1,
			states:{
				hover:{
					brightness: -0.1//鼠标挪上的亮度，变深
				}
			}
		}]
	},
	Highcharts.chart('container1',option3);
}

//按省份统计投资比例--饼状图
function init_pie2(pieData2){
	option4 = {
		chart:{
			type:'pie',
			options3d:{
				enabled:true,
				alpha:50,
				bete:0
			},
			style:{
				fontFamily: 'Microsoft YaHei',
                fontSize:'13px'
			}
		},
		colors:pieColor2,
		credits:{
			enabled: false
		},
		legend:{
			enabled:true, //显示图例
			itemStyle: {
				color: '#5f5f5f',
				fontSize:'13px'
			}
		},
		title:{
			text:null
		},
		tooltip:{
			pointFormat: '<b>{point.percentage:.4f}%</b>'
		},
		plotOptions:{
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				depth: 35,
				dataLabels: {
					enabled: true,
					format: '<b>{point.name}</b>: {point.percentage:.4f}%',
					color:'#585858',
					distance: 10
				},
				showInLegend: true//显示图例
			}
		},
		series:[{
			type:'pie',
			data:pieData2,
			states:{
				hover:{
					brightness: -0.1//鼠标挪上的亮度，变深
				}
			}
		}]
	};
	Highcharts.chart('container2',option4);
}

//按设施类型统计投资比例--饼状图
function init_pie3(pieData3){
	option5 = {
		chart:{
			type:'pie',
			options3d:{
				enabled:true,
				alpha:50,
				bete:0
			},
			style:{
				fontFamily: 'Microsoft YaHei',
                fontSize:'13px'
			}
		},
		colors:pieColor3,
		credits:{
			enabled: false
		},
		legend:{
			enabled:true, //显示图例
			itemStyle: {
				color: '#5f5f5f',
				fontSize:'13px'
			}
		},
		title:{
			text:null
		},
		tooltip:{
			pointFormat: '<b>{point.percentage:.4f}%</b>'
		},
		plotOptions:{
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				depth: 35,
				dataLabels: {
					enabled: true,
					format: '<b>{point.name}</b>: {point.percentage:.4f}%',
					color:'#585858',
					distance: 10
				},
				showInLegend: true//显示图例
			}
		},
		series:[{
			type:'pie',
			data:pieData3,
			states:{
				hover:{
					brightness: -0.1//鼠标挪上的亮度，变深
				}
			}
		}]
	},
	Highcharts.chart('container3',option5);
}