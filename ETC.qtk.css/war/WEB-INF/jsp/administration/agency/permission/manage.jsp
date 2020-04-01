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
	}).editable({
		selector:".editable",//元素选择器，对目标元素启用编辑
		url:"editGrid",//编辑提交的url
	    success: editGridSuccess//服务器返回成功的回调方法
	}).on("taijiSearchSuccess",function(event,result){
		
	}).on("taijiEditSuccess",function(event,result){
		
	});
	
	function editGridSuccess(response, newValue) {
    	if($.isPlainObject(response)&&response.success===true){
    		$.Taiji.showNote(response.msg);
    	}else{
        	var result=$.parseJSON($(response).find("#taiji_note").text());
        	if(result&&result.success===false){
        		return $.Taiji.base64.decode(result.msg);	        	
        	}
        }
    }
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
                            <h4 class="panel-title">渠道控制权限查询</h4>
                        </div>
                        
                        <div class="panel-toolbar ">
                        	<a href="${rootUrl }app/administration/agencypermission/add"  class="taiji_modal_lg taiji_acl btn  btn-success m-5"><i class="fa fa-plus m-r-5"></i>添加渠道权限</a>
                        	<a href="${rootUrl }app/administration/agencypermission/batchAdd"  class="taiji_modal_lg taiji_acl btn  btn-success m-5"><i class="fa fa-plus m-r-5"></i>批量添加渠道权限</a>
                        	<a href="${rootUrl }app/administration/agencypermission/batchDelete"  class="taiji_modal_lg taiji_acl btn  btn-success m-5"><i class="fa fa-times"></i>&nbsp;&nbsp;批量删除渠道权限</a>
                        </div>
                       
                        <div class="panel-body">
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/administration/agencypermission/manage" method="post">
							<div class="form-group m-t-5">
									<label class="control-label">控制渠道</label> <select
										name="agencyId"
										class="taiji_autocomplete form-control"
										data-url="${rootUrl }app/administration/agencypermission/queryByAgencyName"
										data-placeholder="请选择控制渠道" style="width: 20em;">
										<option id="agency1"></option>
									</select>
								</div>
								<div class="form-group m-t-5">
									<label class="control-label">被控渠道</label> <select
										name="permittedAgencyId"
										class="taiji_autocomplete form-control"
										data-url="${rootUrl }app/administration/agencypermission/queryByPermissionAgencyName"
										data-placeholder="请选择被控制渠道" style="width: 20em;">
										<option id="agency2"></option>
									</select>
								</div>
								<div class="form-group m-5">
									卡类型<form:select path="cardTypeCode"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="160px">
										<form:option value="">--请选择卡类型--</form:option>
										<form:options items="${cardTypeCode}" itemLabel="value" itemValue="code"/>
									</form:select>
								</div>
								<div class="form-group m-5">
										<label class="control-label">每页条数</label>
										<form:select path="pageSize" cssClass="form-control"
											data-style="btn-white" data-width="80px">
											<form:option value="10">10</form:option>
											<form:option value="20">20</form:option>
											<form:option value="50">50</form:option>
										</form:select>
								</div>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button"  id="queryBtn"><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default" type="button" onclick="window.location.reload()"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
                        	</form:form>
						</div>
						<div   class="taiji_search_result taiji_table_float table-responsive">
							<table class="table table-striped table-bordered  table-hover">
								<thead>
									<tr>
									<th class="taiji_sortable {orderBy:'agencyId',desc:true}">控制渠道</th>
										<th  class="taiji_sortable {orderBy:'permittedAgencyId',desc:true}"><span>被控渠道</span></th>
										<th width="200px">卡类型</th>
										<th width="200px">操作</th>
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
