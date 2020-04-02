<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#myManage").taiji({
		enableAclCheck:true,
		search:{
			 autoSearch:false,
			 autoRefresh : {
				    enable : false,
				    interval : 10000
				   }
		}
	}).on("taijiAsyncSuccess",function(event,result){
	}).on("taijiSearchSuccess",function(event,result){
	});
	
	$("#select").click(function(){
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		if(!startTime || !endTime){
			$.Taiji.showWarn("请选择日期后继续");
			return false;
		}
		
		$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
		var data={};
		data.startTime = startTime;
		data.endTime = endTime;
	    $.ajax({
	        url:"${rootUrl}app/report/truckOpenCard/manage",
	        data:JSON.stringify(data),
	        type:"POST",
	        contentType:'application/json;charset=UTF-8',
	        dataType : 'json',
	        success:function(data){
	        	//清空数据
	        	$("#tbody1  tr:not(:first)").html("");
				//往表格填数据
	        	var tempHtml = "";
                for(var i = 0; i< data.length; i++)
                {
                     tempHtml += "<tr><td>"+data[i][0]+"</td><td>"+data[i][1]+"</td></tr>";
                }
                
                $("#tbody1").append(tempHtml);
                $.Taiji.hideLoading();
	        },
	        error:function(error){
	        	$.Taiji.hideLoading();
				$.Taiji.showWarn("查询失败，请联系管理员");
	        }
	    });
	});
	
	$("#reset").click(function(){
		$("#startTime").val("");
		$("#endTime").val("");
	});

});


 </script>

</head>
<body>
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
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
			
			<!-- begin row -->
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div id="myManage" class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">工建行办理数量查询</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl }app/customerservice/report/lssuancePerBank/manage" method="POST">
								<!-- 查询条件 -->
								<div class="taiji_search_condition  m-t-5">
								</div>
								<div class="form-group m-t-5">
									<label class="control-label">日志日期</label>
									<div  class="input-group">
				 						<form:input cssStyle="width:150px"   path="startTime" readonly="false"  cssClass="form-control" />
									    <span class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
								</div>
								<div class="form-group m-t-5">	
									<label class="control-label">至</label>
									<div  class="input-group">
					  					<form:input cssStyle="width:150px" path="endTime"  readonly="false" cssClass="form-control"   />
										<span class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('endTime'),dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
				 				</div>	
								<button id="select" class=" btn btn-md btn-success m-r-5 btn btn-primary btn-small" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button id="reset" class=" btn btn-md btn-default btn btn-primary btn-small" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
                        	</form:form>
                        </div>
						<div   class=" table-responsive">
							<table id="my-table" class="table table-bordered  table-hover" >
								<thead>
									<tr>
										<th colspan="3" style="text-align: center">工建行办理数量</th>
									</tr>
								</thead>
								<tbody id="tbody1">
									<tr>
										<th>单位名称</th>
										<th>数量</th>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer text-right">
							<div class="pageturn taiji_pager">
	                        </div>
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