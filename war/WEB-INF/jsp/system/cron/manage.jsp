<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script type="text/javascript">
$(function(){
	$("#myManage").taiji({
		enableAclCheck:true,
		search:{
			 autoRefresh : {
                 enable : true,
                 interval : 60000
             }
		}
	}).bind("taijiAddSuccess",function(){
		window.setTimeout(function(){
			$("#listForm").trigger("submit");
		},100);
	});
	
	$("select").change(function(){
		$("#queryButton").trigger("click");
	});
});
</script>

</head>
<body >
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
                            <h4 class="panel-title">任务</h4>
                        </div>
                        
                        <div class="panel-toolbar ">
                        	<a href="${rootUrl }app/system/cron/stopall"  class="taiji_multiOperate {confirm_message:'您确认要批量停止调度器吗?'} taiji_acl  btn  btn-success m-r-10">
							<i class="fa fa-pause m-r-5"></i>批量停止
						</a>
						<a href="${rootUrl }app/system/cron/startall"  class="taiji_multiOperate {confirm_message:'您确认要批量启动调度器吗?'} taiji_acl  btn  btn-success m-r-10" >
							<i class="fa fa-play m-r-5"></i>批量启动
						</a>
						</div>
						
						   <div class="panel-body">
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/system/cron/manage" method="post">
								<div class="form-group m-5">
									<form:input path="taskName" size="10" maxlength="50" placeholder="任务名称"  cssClass="form-control"/>
								</div>
								<div class="form-group m-5">
									<label class="control-label">任务分组</label>
									<form:select path="type"  cssClass="form-control">
										<form:option value="">全部</form:option>
										<form:options items="${types}" itemLabel="value"/>
									</form:select>
								</div>
								<div class="form-group m-5">
									<form:input path="info" size="20" maxlength="200" placeholder="备注"  cssClass="form-control"/>
								</div>
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

								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
                        	</form:form>
                        </div>
                     
							<div   class="taiji_search_result table-responsive">
								<table class="table table-striped table-bordered  table-hover">
									<thead>
										<tr>
											<th><input type="checkbox" name="checkAll" class='taiji_check_all'/></th>
											<th>序号</th>
											<th>任务名</th>
											<th>任务分组</th>
											<th>cron</th>
											<th>调度器状态</th>
											<th>任务状态</th>
											<th>备注</th>
											<th>自启动</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
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
