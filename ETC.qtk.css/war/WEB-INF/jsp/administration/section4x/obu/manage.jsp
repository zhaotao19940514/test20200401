<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<link href="${rootUrl }plugins/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet" />
<script src="${rootUrl }plugins/bootstrap-editable/js/bootstrap-editable.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#myManage").taiji({
		enableAclCheck:true,
		search : {
			autoSearch : false
		}
	})
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
                            <h4 class="panel-title">OBU 4x号段管理</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/administration/section4x/obu/manage" method="post">
                        	<div class="form-group">
									<div class="form-group m-5">
									<form:label path="obuId">OBU号</form:label>
										<form:input path="obuId" cssClass="form-control" placeholder=""/>
										<form:label path="version">OBU版本</form:label>
										<form:select path="version"  cssClass="form-control m-r-5" data-style="btn-white" data-width="100px">
											<form:option value="">--请选择--</form:option>
											<form:options items="${version}" itemLabel="name" itemValue="code"/>
										</form:select>
									</div>
								<div class="form-group m-5">
									<div class="form-group m-5">
				<label class="control-label">每页条数</label>	
						<form:select path="pageSize"  cssClass="form-control">
							<form:option value="10">10</form:option>
							<form:option value="20">20</form:option>
							<form:option value="50">50</form:option>
							<form:option value="100">100</form:option>
						</form:select>
				</div>		
							<a href="${rootUrl }app/administration/section4x/obu/add"  class="taiji_modal_lg taiji_acl btn  btn-success m-5"><i class="fa fa-plus m-r-5"></i>添加号段</a>
							<a href="${rootUrl }app/administration/section4x/obu/batchAdd"  class="taiji_modal_lg taiji_acl btn  btn-success m-5"><i class="fa fa-plus m-r-5"></i>批量添加号段</a>
						<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
									<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
										</div>
												</div>
                        	
                        	
                        	</form:form>
						</div>
						<div   class="taiji_search_result taiji_table_float table-responsive">
							<table class="table table-striped table-bordered  table-hover">
								<thead>
									<tr>
									<th>序号</th>
										<th>起始号段</th>
										<th>终止号段</th>
										<th>数量</th>
 											<th>版本</th>
										<th>更新时间</th>
									<!-- 	<th>操作</th> -->
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
