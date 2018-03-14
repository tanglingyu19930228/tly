//按中央投资、地方投资比例---统计图颜色配置
var pieColor1 = ['#659DC2','#E05552'];
//按项目类别统计投资比例---统计图颜色配置
var pieColor2 = ['#659DC2','#E05552','#91C7AF','#D59333','#7289AB'];
//按省份统计投资比例---统计图颜色配置
var pieColor3 = ['#7289AB','#92CA8D','#E59D87','#E05552','#F49F44','#EFDD79',
    '#7AAFFF','#73A373','#91C7AF','#CA8623','#EDBD7D','#83DBA8',
    '#659DC2','#B2D38E','#9AA8E5','#FA8F6F','#78BCBF','#D59333'];

var szcsArr = new Array();
var dataArr1 = new Array();
var dataArr2 = new Array();
var xmlbArr = new Array();
var dataArr3 = new Array();
var dataArr4 = new Array();
var pieData1 = new Array();
var pieData2 = new Array();

var option1,option2,option3,option4,option5;
var legendFlag=true;
$(function(){
	//按中央投资、地方投资统计投资比例
	var zyzt = $("#zytz").val()==""?0:parseFloat($("#zytz").val());
	var dftz = $("#dftz").val()==""?0:parseFloat($("#dftz").val());
	if(zyzt!=0 || dftz !=0){
		init_pie1(zyzt,dftz);
	}
	
	//按市区统计投资金额、投资比例
	var cityZytzMap = $("#cityZytzMap").val().replace("{","").replace("}","");//中央投资数据
	var cityDftzMap = $("#cityDftzMap").val().replace("{","").replace("}","");//地方投资数据
	var cityZytzArr=cityZytzMap.split(",");
	var cityDftzArr=cityDftzMap.split(",");
	for(var i=0;i<cityZytzArr.length;i++){
		for(var j=0;j<cityDftzArr.length;j++){
			var arr = cityZytzArr[i].split("=");
			var arr1 = cityDftzArr[j].split("=");
			if($.trim(arr[0])==$.trim(arr1[0])){
				szcsArr.push($.trim(arr[0]));
				dataArr1.push(parseFloat(arr[1]));//中央投资
				dataArr2.push(parseFloat(arr1[1]));//地方投资
				var sum = parseFloat(arr1[1])+parseFloat(arr[1]);
				pieData1.push([arr[0],sum]);
			}
		}
	}
	for(var x=0;x<szcsArr.length;x++){
		if(dataArr1[x]!=0 ||dataArr2[x]!=0){
			init_column1(szcsArr,dataArr1,dataArr2);
			break;
		}
	}
	for(var x=0;x<szcsArr.length;x++){
		if(pieData1[x][1]!=0){
			if(szcsArr.length>8){
				legendFlag = false;
			}
			init_pie2(pieData1);
			break;
		}
	}
	
	//按项目类别统计投资金额、投资比例
	var dftzxmlbMap = $("#dftzxmlbMap").val().replace("{","");
	var zytzxmlbMap = $("#zytzxmlbMap").val().replace("{","");
	var zytzxmlbArr=zytzxmlbMap.split(",");
	var dftzxmlbArr=dftzxmlbMap.split(",");
	for(var i=0;i<zytzxmlbArr.length;i++){
		for(var j=0;j<dftzxmlbArr.length;j++){
			var arr = zytzxmlbArr[i].split("=");
			var arr1 = dftzxmlbArr[j].split("=");
			if($.trim(arr[0])==$.trim(arr1[0])){
				xmlbArr.push(arr[0]);//设施类别
				dataArr3.push(parseFloat(arr[1]));//中央投资
				dataArr4.push(parseFloat(arr1[1]));//地方投资
				var sum = parseFloat(arr1[1])+parseFloat(arr[1]);
				pieData2.push([arr[0], sum]);
			}
		}
	}
	for(var i=0;i<xmlbArr.length;i++){
		if(dataArr3[i]!=0||dataArr4[i]!=0){
			init_column2(xmlbArr,dataArr3,dataArr4);
			break;
		}
	}
	for(var i=0;i<xmlbArr.length;i++){
		if(pieData2[i][1]!=0){
			init_pie3(pieData2);
			break;
		}
	}
});
	
//按地市统计投资金额----柱状图	
function init_column1(szcsArr,dataArr1,dataArr2){
	option1 = {
		chart:{
			type:'column',
			marginTop:65,
			style: {
                fontFamily: 'Microsoft YaHei',
                fontSize:'13px'
            }
		},
		colors:['#659DC2','#89D3D6'],	//柱状图颜色
		credits:{						//不显示highcharts官网链接
			enabled:false
		},
		title:{
			text:null
		},
		legend:{
			enabled:true,	//显示图例
			align:'left',
			verticalAlign:'top',
			x:75,
			y:8,
			itemStyle:{
				color:'#5f5f5f',
				fontsize:'13px'
			}
		},
		xAxis:{
			categories:szcsArr,
			crosshair:true,
			labels: {
                autoRotationLimit:40
            }
		},
		yAxis:{
			min:0,
			title:{
				text:'单位：万元 ',
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
			headerFormat:'<small>{point.key}</small><table>',
			pointFormat:'<tr><td>{series.name}: </td>'+
				'<td style="text-align: right"><b>{point.y:.4f} 万元</b></td></tr>',
			footerFormat: '</table>',
			shared: true,
            useHTML: true
			//valueDecimals: 4,//小数点后保留4位小数
		},
		plotOptions:{
			column:{
				pointPadding:0.1,//同一点两个柱之间的距离
				borderWidth: 0,
                dataLabels: {
                    enabled: true,//默认显示数据
                    crop:false,
                    overflow:"none",
                    color:'#585858',
                    format:'{y:.4f}'
                }
			},
			series: {
                states: {
                    hover: {
                        brightness: -0.1//鼠标挪上的亮度，变深
                    }
                }
            }
		},
		series:[
			{
				name:'中央投资',
				data:dataArr1
			},
			{
				name:'地方投资',
				data:dataArr2
			}
		]
	};
	Highcharts.chart('chart_1',option1);
}

//按项目类别统计----柱状图
function init_column2(xmlbArr,dataArr1,dataArr2){
	option2={
        chart: {
            type: 'column',
            marginTop:65,
            style: {
                fontFamily: 'Microsoft YaHei',
                fontSize:'13px'
            }
        },
        colors:['#659DC2','#89D3D6'],	//柱状图颜色
        credits: {						//不显示highcharts官网链接
            enabled: false
        },
        title: {
            text:null
        },
        legend:{
            enabled:true, //隐藏图例
            align: 'left',
            verticalAlign: 'top',
            x:75,
            y:8,
            itemStyle: {
                color: '#5f5f5f',
                fontSize:'13px'
            }
        },
        xAxis: {
            categories: xmlbArr,
            crosshair: true,
            labels: {
                autoRotationLimit:40
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '单位：万元',
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
            headerFormat: '<small>{point.key}</small><table>',
            pointFormat: '<tr><td>{series.name}: </td>' +
				'<td style="text-align: right"><b>{point.y:.4f} 万元</b></td></tr>',
            footerFormat: '</table>',
            useHTML: true,
            shared: true,
            //valueDecimals: 4,//小数点后保留4位小数
        },
        plotOptions: {
            column: {
                pointPadding: 0.05,//同一点两个柱之间的距离
                borderWidth: 0,
                dataLabels: {
                    enabled: true,//默认显示数据
                    crop:false,
                    overflow:"none",
                    color:'#585858',
                    format:'{y:.4f}'
                }
            },
            series:{
                states: {
                    hover: {
                        brightness: -0.1//鼠标挪上的亮度，变深
                    }
                }
            }
        },
        series: [
			{
				name: '中央投资',
				data:dataArr1
			},
			{
				name: '地方投资',
				data:dataArr2
			}
        ]
    };
	Highcharts.chart('chart_2',option2);
}

//按中央投资、地方投资统计----饼状图
function init_pie1(zytz,dftz){
	option3 = {
		chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 50,
                beta: 0
            },
            style: {
                fontFamily: 'Microsoft YaHei',
                fontSize:'13px'
            }
        },
		colors:pieColor1,
		credits: {//不显示highcharts官网链接
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
		plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.4f}%',
                    distance: 10,
                    color:'#585858'
                },
                showInLegend: true//显示图例
            }
        },
		series:[{
			type:'pie',
			data:[
				['中央投资',zytz],
				{
					name:'地方投资',
					y:dftz,
					sliced:false,
					selected:false
				}
			]
		}]
	};
	Highcharts.chart('chart_3',option3);
}

//按所在城市统计----饼状图
function init_pie2(data){
	option4 = {
		chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 50,
                beta: 0
            },
            style: {
                fontFamily: 'Microsoft YaHei',
                fontSize:'13px'
            }
        },
		colors:pieColor3,
        credits: {			//不显示highcharts官网链接
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
		tooltip: {
            pointFormat: '<b>{point.percentage:.4f}%</b>'
        },
		plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.4f}%',
                    distance: 5,
                    color:'#585858'
                },
                showInLegend: true//显示图例
            }
        },
		series:[{
			type:'pie',
			data:data,
			states: {
                hover: {
                    brightness: -0.1//鼠标挪上的亮度，变深
                }
            }
		}]
	};
	Highcharts.chart('chart_4',option4);
}

//按项目类别统计----饼状图
function init_pie3(data){
	option5 = {
		chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 50,
                beta: 0
            },
            style: {
                fontFamily: 'Microsoft YaHei',
                fontSize:'13px'
            }
        },
		colors:pieColor2,
        credits: {			//不显示highcharts官网链接
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
		tooltip: {
            pointFormat: '<b>{point.percentage:.4f}%</b>'
        },
		plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.4f}%',
                    distance: 5,
                    color:'#585858'
                },
                showInLegend: true//显示图例
            }
        },
		series:[{
			type:'pie',
			data:data,
			states: {
                hover: {
                    brightness: -0.1//鼠标挪上的亮度，变深
                }
            }
		}]
	};
	Highcharts.chart('chart_5',option5);
}