<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	$("#logManage").taiji();
	
	$("select").change(function(){
		$("#queryButton").click();
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
		<!-- 内容页 -->
				<!-- begin #content -->
		<div id="content" class="content">
			<ol class="breadcrumb pull-right">
			</ol>
			
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
					
				<div id="logManage" class="panel panel-inverse">
					<div class="panel-heading">
                    	<div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                        </div>
                         <h4 class="panel-title">调度日志</h4>
                    </div>
                    
          <div class="panel-body">
			<form:form cssClass="taiji_search_form form-inline m-t-5" modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/system/schedulelog/manage" method="post">
				<!-- 查询条件 -->
				<div class="form-group m-5">
					<label class="control-label">日志日期</label>
					<div  class="input-group">
 						<form:input cssStyle="width:150px"   path="startTime" readonly="true"  cssClass="form-control" />
					    <span class="input-group-btn">
							<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'endTime\')}'});"><i class="fa fa-calendar"></i></button>
						</span>
					</div>
				</div>
				<div class="form-group m-5">	
					<label class="control-label">至</label>
					<div  class="input-group">
	  					<form:input cssStyle="width:150px" path="endTime"  readonly="true" cssClass="form-control"   />
						<span class="input-group-btn">
							<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('endTime'),dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'startTime\')}'});"><i class="fa fa-calendar"></i></button>
						</span>
					</div>
 				</div>	
	  			<div class="form-group m-5">	
						<label class="control-label">触发类型</label>
						<form:select path="bySystem" cssClass="form-control">
							<form:option value="">全部</form:option>
							<form:option value="true">系统触发</form:option>
							<form:option value="false">手工触发</form:option>
						</form:select>
				</div>	
				<div  class="input-group">
					<span class="input-group-addon">耗时&nbsp;&gt;</span>
					<form:input path="execTime" cssClass="form-control"/>
					<span class="input-group-addon">ms</span>
				</div>
				<p></p>
				<div class="form-group m-5">
				<label class="control-label">每页条数</label>	
						<form:select path="pageSize"  cssClass="form-control">
							<form:option value="2">2</form:option>
							<form:option value="10">10</form:option>
							<form:option value="16">16</form:option>
							<form:option value="20">20</form:option>
							<form:option value="50">50</form:option>
							<form:option value="100">100</form:option>
						</form:select>
				</div>						
						<button id="queryButton" class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
						</form:form>	
				</div>
					
	 
			<div class="taiji_search_result table-responsive">
				<table class="table table-striped table-bordered  table-hover">
					<thead>
						<tr>
							<th>开始时间</th>
							<th>结束时间</th>
							<th class="taiji_sortable {orderBy:'taskName',desc:true}">任务名称</th>
							<th>触发类型</th>
							<th>当时CRON</th>
							<th>调度耗时</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		
					<div class="panel-footer text-right ">
							<div class="pageturn taiji_pager">
	                        </div>
             		  </div>
		
		</div>
		</div>
	</div>
	</div>
	<!-- 版权页 -->
<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
<!-- 版权页 -->
	</div>
	
	<!-- 内容页 -->


</body>
</html>