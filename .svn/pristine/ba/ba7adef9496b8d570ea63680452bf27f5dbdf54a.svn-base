<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<script src="${rootUrl }plugins/datepicker/WdatePicker.js" type="text/javascript"></script>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script type="text/javascript">
$(function(){
	$("#myManage").taiji({
		enableAclCheck:true,
	}).on("taijiEditSuccess",function(event,result){
		//$(".taiji_search_submit").click();
	});
	$("#addBtn").click(function(){
		$(this).showModal({size:"modal-lg",backdrop:false,data:{loginName:"sample"}});
		return false;
	});
	
	$("#myManage select").change(function(){
		$(".taiji_search_submit").click();
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
                        <div class="panel-heading" style="background-color:#C2C2C2">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                                <h4 class="panel-title" style="color:black;font-size:16px;" >模板管理</h4>
                        </div>
                        
                        <div class="panel-toolbar ">
                     		<a href="${rootUrl }app/template/add"  class="taiji_modal	 taiji_acl btn  btn-success m-5"><i class="fa fa-plus m-r-5"></i>添加</a>
                        </div>
                        
                        <div class="panel-body">
                        	
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/template/manage" method="post">
								<div class="form-group m-5">
									<div class="form-group m-5">
										<form:input path="name" size="25" maxlength="50" placeholder="模板名称"  cssClass="form-control"/>
									</div>
									<div class="form-group m-5">
										<label class="control-label">每页条数</label>
										<form:select path="pageSize"  cssClass="selectpicker"  data-style="btn-white" data-width="80px">
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
								</div>
                        	</form:form>
						
							<div   class="taiji_search_result taiji_table_float table-responsive">
								<table class="table table-striped table-bordered  table-hover">
									<thead>
										<tr>
											<th>项目名称</th>
											<th>模板名称</th>
											<th>模板路经</th>
											<th width="140px" align="center">操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
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
