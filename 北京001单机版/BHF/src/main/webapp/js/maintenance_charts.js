//根据使用状态统计的饼状图的颜色
var peiColor=["#78BCBF","#7289AB","#92CA8D"];
var pieColor2 = ['#7289AB','#92CA8D','#E59D87','#E05552','#F49F44','#EFDD79',
                 '#7AAFFF','#73A373','#91C7AF','#CA8623','#EDBD7D','#83DBA8',
                 '#659DC2','#B2D38E','#9AA8E5','#FA8F6F','#78BCBF','#D59333'];
var pieColor3 = ['#659DC2','#E05552','#91C7AF','#D59333','#7289AB'];
var syztpei=new Array();
var syztdata=new Array();
var provinceArr = new Array();
var proviceData = new Array();
var provincePie = new Array();
var option1,option2,option3,option4,option5;
//项目类别
var xmlb= new Array();
var xmlbData= new Array();
var xmlbPie = new Array();
$(function(){
	var jsztPie=$("#syztPie").val().replace("{","").replace("}","");
	var ary=jsztPie.split(",");
	for(var i=0;i<ary.length;i++){
		var ary1=ary[i].split("=");
		syztdata.push(parseFloat(ary1[1]));
		syztpei.push([$.trim(ary1[0]),parseFloat(ary1[1])]);
	}
	var columnData=$("#cityDftzMap").val().replace("{","").replace("}","");
	var colArr=columnData.split(",");
		for(var i=0;i<colArr.length;i++){
			var province=colArr[i].split("=");
			provinceArr.push($.trim(province[0]));
			proviceData.push(parseFloat(province[1]));
			provincePie.push([$.trim(province[0]),parseFloat(province[1])]);
		}
		
		var xmlbColumn=$("#syztColumn").val().replace("{","").replace("}","");
		var xmlbAll=xmlbColumn.split(",");
	for(var i=0;i<xmlbAll.length;i++){
		var xmlbArr=xmlbAll[i].split("=");
		xmlb.push($.trim(xmlbArr[0]));
		xmlbData.push(parseFloat(xmlbArr[1]));
		xmlbPie.push([$.trim(xmlbArr[0]),parseFloat(xmlbArr[1])]);
	}
	
	//数据求和统计
	var sumProvice=0;
	for(var i=0;i<proviceData.length;i++){
		sumProvice+=proviceData[i];
	}
	if(sumProvice!=0){
		columnChart();
		pie2Chart();
	}
	//项目类别数据求和统计
	var sumXmlb=0;
	for(var i=0;i<xmlbData.length;i++){
		sumXmlb+=xmlbData[i];
	}
	if(sumXmlb!=0){
		column2Chart();
		pie3Chart();
	}
	
	//使用状态数据求和
	var syzt_Data=0;
	for(var i=0;i<syztdata.length;i++){
		syzt_Data+=syztdata[i];
	}
	if(syzt_Data!=0){
		pieChart();
	}
});
//按地市统计投资金额----柱状图
function columnChart(){
	option1 = {
		chart:{
			type:'column',
			marginTop:65,
			style: {
                fontFamily: 'Microsoft YaHei',
                fontSize:'13px'
            }
		},
		credits:{						//不显示highcharts官网链接
			enabled:false
		},
		title:{
			text:null
		},
		legend:{
			enabled:false
		},
		xAxis:{
			categories:provinceArr,
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
		tooltip: {
			 headerFormat: '<span style="font-size:10px">{point.key}</span>',
		     pointFormat: '<span>{point.key}:<b>{point.y:.4f} 万元</b></span>',
	        shared: true,
	        useHTML: true
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
		series:[{
			data:proviceData
		}
		]
	};
	Highcharts.chart('chart_1',option1);
}

//按项目类别统计----柱状图
function column2Chart(){
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
        	enabled:false
        },
        xAxis: {
            categories: xmlb,
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
        	 headerFormat: '<span style="font-size:10px">{point.key}</span>',
		     pointFormat: '<span>{point.key}:<b>{point.y:.4f} 万元</b></span>',
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
				data:xmlbData
			}
        ]
    };
	Highcharts.chart('chart_2',option2);

}
//按照使用状态统计的中央投资和地方投资---饼状图
function pieChart(){
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
		colors:peiColor,
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
			data:syztpei,
			states:{
				hover:{
					brightness: -0.1//鼠标挪上的亮度，变深
				}
			}
		}]
	};
	Highcharts.chart('chart_3',option3);
}
function pie2Chart(){
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
			data:provincePie,
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
function pie3Chart(){
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
			data:xmlbPie,
			states: {
                hover: {
                    brightness: -0.1//鼠标挪上的亮度，变深
                }
            }
		}]
	};
	Highcharts.chart('chart_5',option5);
}