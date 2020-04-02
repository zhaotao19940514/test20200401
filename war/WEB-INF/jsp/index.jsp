<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<link rel='stylesheet' href='${rootUrl }plugins/fullcalendar/fullcalendar.css' />
<script src='${rootUrl }plugins/moment/moment.min.js'></script>
<script src="${rootUrl }plugins/fullcalendar/fullcalendar.js"></script>
<script src="${rootUrl }plugins/echart/echarts.js"></script>
<script type="text/javascript">
$(function(){
	$("#myPanel3").taiji();
	$('#calendar').fullCalendar({
		
		 defaultDate: '2015-06-18',
		 businessHours: false, // display business hours
		 editable: true,
		 viewRender: function (view) {
			var viewStart = moment(view.start).format();
			var viewEnd = moment(view.end).format();
			console.log(viewStart+"->"+viewEnd);
			},
			select: function(start, end) {
				var title = prompt('Event Title:');
				var eventData;
				if (title) {
					eventData = {
						title: title,
						start: start,
						end: end
					};
					$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
				}
				$('#calendar').fullCalendar('unselect');
			},
		 eventRender: function(event, element) {
		     console.log(element);
		    },
		 dayClick: function(date, jsEvent, view) {
			 console.log(date);
		    },
	     events: [
	          	// red areas where no events can be dropped
				{
					start: '2015-06-01',
					end: '2015-06-03',
					overlap: false,
					rendering: 'background',
					color: '#ff9f89'
				},
				{
					start: '2015-06-27',
					end: '2015-06-30',
					overlap: false,
					rendering: 'background',
					color: '#ff9f89'
				}
	            ]
    });
	var schdata2 = {start: '2015-06-24',title:'摇号', end: '2015-06-27'};
	$('#calendar').fullCalendar('renderEvent', schdata2, true);
});
</script>
<script type="text/javascript">
// 路径配置
require.config({
    paths: {
        echarts: '${rootUrl }plugins/echart'
    }
});
// 使用
require(
    [
        'echarts',
        'echarts/chart/line',
        'echarts/chart/bar' 
    ],
    function (ec) {
   		initCharts(ec);
        $.getJSON("${rootUrl}app/system/oplog/chart/multi").done(function(d){
        	setOption1($("#chart1").data("echart"),d);
        });
        $.getJSON("${rootUrl}app/system/oplog/chart/single").done(function(d){
        	setOption2($("#chart2").data("echart"),d);
        });
    }
  );
 function initCharts(ec){
	 	var $charts=$("#chart1,#chart2");
    	$charts.each(function(){
    		var myChart=ec.init(this);
    		$(this).data("echart",myChart);
    	});
    	$charts.parents(".panel").on("click","[data-click=panel-expand]",function(e){
			$(e.delegateTarget).find("[id^=chart]").animate({fontSize:"2em"},100,function(){
						$(this).data("echart").resize()
					});
    	});
    	$charts.parents(".panel").on("click","[data-click=panel-reload]",function(e){
    		$(e.delegateTarget).find("[id^=chart]").data("echart").restore();
    	});
 } 
 function setOption1(myChart1,d){
	        var option = {
	        		 title : {
	        		        text: '一周用户操作次数统计图',
	        		        subtext: '多条件分组示例'
	        		    },
	        		    tooltip : {
	        		        trigger: 'axis'
	        		    },
	        		    legend: {
	        		        data:d.legend
	        		    },
	        		    toolbox: {
	        		        show : true,
	        		        feature : {
	        		            mark : {show: true},
	        		            dataView : {show: true, readOnly: false},
	        		            magicType : {show: true, type: ['line', 'bar']},
	        		            restore : {show: true},
	        		            saveAsImage : {show: true}
	        		        }
	        		    },
	        		    calculable : true,
	        		    xAxis : [
	        		        {
	        		            type : 'category',
	        		            boundaryGap : false,
	        		            data : d.xAxis
	        		        }
	        		    ],
	        		    yAxis : [
	        		        {
	        		            type : 'value',
	        		            axisLabel : {
	        		                formatter: '{value} 次'
	        		            },
	        		            min:d.minValue,
	        		            max:d.maxValue
	        		        }
	        		    ],
	        		    series : [
	        		        {
	        		            name:d.series[0].name,
	        		            type:d.series[0].type,
	        		            data:d.series[0].data,
	        		            markPoint : {
	        		                data : [
	        		                    {type : 'max', name: '最大值'},
	        		                    {type : 'min', name: '最小值'}
	        		                ]
	        		            },
	        		            markLine : {
	        		                data : [
	        		                    {type : 'average', name: '平均值'}
	        		                ]
	        		            }
	        		        },
	        		        {
	        		        	name:d.series[1].name,
	        		            type:d.series[1].type,
	        		            data:d.series[1].data,
	        		            markLine : {
	        		                data : [
	        		                    {type : 'average', name : '平均值'}
	        		                ]
	        		            }
	        		        }
	        		    ]
	        };
	
	        // 为echarts对象加载数据 
	        myChart1.setOption(option); 
 } 
 
 function setOption2(myChart2,d){
	 var option = {
 		    title : {
 		        text: d.title,
 		        subtext: '单条件分组示例'
 		    },
 		    tooltip : {
 		        trigger: 'axis'
 		    },
 		    legend: {
 		    	show:false,
 		        data:d.legend
 		    },
 		    toolbox: {
 		        show : true,
 		        orient: 'vertical',
 		        x: 'right',
 		        y: 'center',
 		        feature : {
 		            mark : {show: true},
 		            dataView : {show: true, readOnly: false},
 		            magicType: {show: true, type: ['line', 'bar']},
 		            restore : {show: true},
 		            saveAsImage : {show: true}
 		        }
 		    },
 		    calculable : true,
 		    xAxis : [
 		        {
 		            type : 'value',
 		            boundaryGap : [0, 0.01]
 		        }
 		    ],
 		    yAxis : [
 		        {
 		            type : 'category',
 		            data : d.xAxis
 		        }
 		    ],
 		    series : [
 		        {
 		            name:d.series[0].name,
 		            type:d.series[0].type,
 		            data:d.series[0].data,
     		        markLine : {
 		                data : [
 		                    {type : 'average', name : '平均值'}
 		                ]
 		            }
 		        }
 		    ]
 		};
 	 myChart2.setOption(option);        
 }
</script>
<style type="text/css">
.panel .panel-body{
min-height: 100px;
}
</style>
</head>
<body >
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<div id="alert-success" class="alert alert-success  ">
		<i class="fa fa-check-circle fa-2x "></i>
		<strong></strong>
	</div>
	<div id="alert-info" class="alert alert-info  ">
		<i class="fa fa-info-circle  fa-2x"></i>
		<strong></strong>
	</div>
	<div id="alert-loading" class="alert alert-info  "><strong></strong>
		<div  class="fade in"><span class="spinner"></span></div>
	</div>
	
	<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
		<!-- end #sidebar -->
		
		<!-- begin #content -->
		<div id="content" class="content">
			<ol class="breadcrumb pull-right">
			</ol>
			<!-- begin page-header -->
			<h1 class="page-header">首页</h1>
			<!-- end page-header -->
			<!-- begin row -->
			<div class="row">
				<div class="col-md-8"  >
					<div  class="panel panel-inverse"  data-sortable-id="index1">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">图表</h4>
                        </div>
                        <div class="panel-body">
                        	<div id="chart1" style="min-height: 500px"></div>
                        </div>
					</div>
				</div>
				<div class="col-md-4"  >
					<div  class="panel panel-inverse"  data-sortable-id="index1">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">图表</h4>
                        </div>
                        <div class="panel-body">
                        	<div id="chart2" style="min-height: 500px"></div>
                        </div>
					</div>
				</div>
			</div>
			<!-- begin row -->
			<div class="row">
			    <div class="col-md-6">
			        <!-- begin panel -->
                    <div id="myPanel1" class="panel panel-inverse"  data-sortable-id="index1">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">日历</h4>
                        </div>
                        <div class="panel-body">
                        	<div id="calendar"></div>
                        </div>
					</div>
                    <!-- end panel -->
			        <!-- begin panel -->
                    <div id="myPanel2" class="panel panel-inverse" data-sortable-id="index2">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">myPanel2</h4>
                        </div>
                         <div class="panel-body">
                        
                        </div>
					</div>
                    <!-- end panel -->
			    </div>
			    <div class="col-md-6">
			        <!-- begin panel -->
                    <div id="myPanel3" class="panel panel-inverse" data-sortable-id="index3">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">示例</h4>
                        </div>
                        <div class="panel-body"  style="min-height: 400px">
                        	<a class="btn btn-primary m-5"  href="${rootUrl }app/system/workday/manage">工作日</a>
                        	<a class="btn btn-warning m-5"  href="${rootUrl }app/sample/binAjax">binFile</a>
                        	<a class="btn btn-info m-5"  href="${rootUrl }app/sample/tjAjax" >tjAjax</a>
                        	<a class="btn btn-success m-5"  href="${rootUrl }app/sample/wizard" >wizard</a>
                        	<a id="authBtn" class="btn btn-danger m-5"  href="${rootUrl }app/acl/user/Ajax" >auth</a>
                        	<a class="btn btn-default m-5"  href="${rootUrl }app/sample/file" >file</a>
                        	<a class="btn btn-primary m-5"  href="${rootUrl }app/timing/task/manage">简单定时任务</a>
                        </div>
					</div>
                    <!-- end panel -->
			        <!-- begin panel -->
                    <div id="myPanel4" class="panel panel-inverse"  data-sortable-id="index4">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">myPanel4</h4>
                        </div>
                         <div class="panel-body">
                        
                        </div>
					</div>
                    <!-- end panel -->
			        <!-- begin panel -->
                    <div id="myPane5" class="panel panel-inverse"  data-sortable-id="index5">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">myPanel5</h4>
                        </div>
                         <div class="panel-body">
                        
                        </div>
					</div>
                    <!-- end panel -->
			    </div>
			    <!-- end col-12 -->
			</div>
			<!-- end row -->
		</div>
		<!-- end #content -->
		
					
		<!-- begin scroll to top btn -->
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->
	
	

</body>
</html>
