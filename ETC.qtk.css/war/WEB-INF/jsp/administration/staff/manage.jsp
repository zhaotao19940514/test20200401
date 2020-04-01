<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$.ajaxSetup({cache : false});
		$("#myManage").taiji({
			enableAclCheck : true,
			search : {
				seachResultType : 'tbody',
				autoSearch : false
			}
		}).on("taijiOperateSuccess",function(){
			$(".taiji_search_submit").click();
		}).on("taijiAddSuccess",function(){
            $(".taiji_search_submit").click();
        }).on("taijiEditSuccess",function(){
            $(".taiji_search_submit").click();
        }).on("taijiSearchSuccess",function(){
        	$(".releaseDate").each(function(){
	        	var releaseDate = $(this).text().trim();
	            var formatted = '';
	            if(releaseDate!='' && releaseDate!=null && releaseDate!=undefined){
	                if(releaseDate.length>=8){
	                    formatted += releaseDate.substring(0,4);
	                    formatted += '-';
	                    formatted += releaseDate.substring(4,6);
	                    formatted += '-';
	                    formatted += releaseDate.substring(6,8);
	                    $(this).text('');
	                    $(this).text(formatted);
	                }
	            }
        	});
        });
	});
</script>

</head>
<body>
	<div id="form-target"></div>
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>

	<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp"%>
		<!-- end #header -->

		<!-- begin #sidebar -->
		<%@ include file="/WEB-INF/jsp/sidebar.jsp"%>
		<!-- end #sidebar -->
		<!-- 内容页 -->
		<!-- begin #content -->
		<div id="content" class="content">
			<ol class="breadcrumb pull-right">
			</ol>

			<div class="row">
				<!-- begin col-12 -->
				<div class="col-md-12">
					<div id="myManage" class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<a href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-default"
									data-click="panel-expand"><i class="fa fa-expand"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-success"
									data-click="panel-reload"><i class="fa fa-repeat"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-warning"
									data-click="panel-collapse"><i class="fa fa-minus"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-danger"
									data-click="panel-remove"><i class="fa fa-times"></i></a>
							</div>
							<h4 class="panel-title">工号管理</h4>
						</div>

						<div class="panel-body">
							<form:form cssClass="taiji_search_form form-inline m-t-5"
								modelAttribute="queryModel" id="listForm" name="listForm"
								action="${rootUrl}app/administration/staff/manage" method="post">
								<a href="${rootUrl}app/administration/staff/add"  class="taiji_modal taiji_acl btn btn-success m-r-10"><i class="fa fa-plus m-r-5"></i>添加</a>
								<!-- 查询条件 -->
								<div class="taiji_search_condition  m-t-5"></div>
                                <div class="form-group m-t-5">
                                    <label class="control-label">工号</label>
                                    <form:input path="queryStaffId" cssClass="form-control"
                                        maxlength="20" size="20" />
                                </div>
                                <div class="form-group m-t-5">
                                    <label class="control-label">姓名</label>
                                    <form:input path="queryStaffName" cssClass="form-control"
                                        maxlength="20" size="20" />
                                </div>
                                <div class="form-group m-t-5">
                                    <label class="control-label">服务网点</label>
                                    <select name="queryServiceHallId" class="taiji_autocomplete form-control" data-url="${rootUrl }app/administration/servicehall/queryByName" data-placeholder="请选择网点" style="width: 20em;">
						                    <option></option>
						            </select>
                                </div>
                                <button class="taiji_search_submit btn btn-md btn-success m-l-5"
                                    type="button">
                                    <i class="fa fa-search  m-r-10 "></i>查询
                                </button>
                                <button class="taiji_search_reset btn btn-md btn-default"
                                    type="button">
                                    <i class="fa  fa-refresh  m-r-10  "></i>重置
                                </button>
						</form:form>
						<div class="taiji_search_result">
							<table class="table table-striped table-bordered  table-hover">
								<thead>
									<tr>
										<th>员工编号</th>
										<th>员工姓名</th>
									    <th>网点名称</th>
										<th>网点编号</th>
										<th>注册日期</th>
										<th>发行渠道编号</th>
										<th>资金渠道编号</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<div class="panel-footer text-right ">
							<div class="pageturn taiji_pager"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 内容页 -->
	<!-- 版权页 -->
	<a href="javascript:;"
		class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
		data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
	<!-- 版权页 -->
	</div>
</body>
</html>