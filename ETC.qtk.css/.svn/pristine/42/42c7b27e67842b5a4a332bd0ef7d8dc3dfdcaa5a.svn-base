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
        });
		
		$("#docDownload").click(function(){
	        $("#docDownloadSubmit").click();
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
							<h4 class="panel-title">网点管理</h4>
						</div>
						<div class="panel-toolbar ">
                     		<a href="${rootUrl }app/administration/servicehall/file"  class="taiji_modal	 taiji_acl btn  btn-success m-5"><i class="fa fa-upload" > </i>导入Excel</a>
                        </div>
                        <div class="panel-toolbar ">
                     		<button id="docDownload" type="button" class="btn btn-primary btn-sm"><i class="fa fa-download"> </i>下载导入网点模板</button>
			                       <form action="${rootUrl}app/user/dlocx/serviceExcel" method="post" class="hide">
			   							 <button id="docDownloadSubmit" type="submit" class="btn btn-primary btn-sm" style="display: none" ></button>
								   </form>
						</div>
						<div class="panel-body">
							<form:form cssClass="taiji_search_form form-inline m-t-5"
								modelAttribute="queryModel" id="listForm" name="listForm"
								action="${rootUrl}app/administration/servicehall/manage" method="post">
								<a href="${rootUrl}app/administration/servicehall/add"  class="taiji_modal_lg taiji_acl btn btn-success m-r-10"><i class="fa fa-plus m-r-5"></i>添加</a>
								<!-- 查询条件 -->
								<div class="taiji_search_condition  m-t-5"></div>
								
                                <div class="form-group m-t-5">
                                    <label class="control-label">服务网点名称</label>
                                    <form:input path="queryName" cssClass="form-control"
                                        maxlength="20" size="20" />
                                </div>
                                <div class="form-group m-t-5">
                                    <label class="control-label">服务网点联系人名称</label>
                                    <form:input path="queryContact" cssClass="form-control"
                                        maxlength="20" size="20" />
                                </div>
                                <div class="form-group m-t-5">
                                    <label class="control-label">服务网点编号</label>
                                    <form:input path="queryServiceHallId" cssClass="form-control"
                                        maxlength="20" size="20" />
                                </div>
								<div class="form-group m-t-5">
                                    <label class="control-label">合作机构编号</label>
                                    <form:input path="queryAgencyId" cssClass="form-control"
                                        maxlength="20" size="20" />
                                </div>
                                <button class="taiji_search_submit btn btn-md btn-success m-l-5"
                                    type="button">
                                    <i class="fa fa-search  m-r-10 "></i>查询
                                </button>
                                <button class="taiji_search_reset btn btn-md btn-default"
                                    type="button">
                                    <i class="fa  fa-refresh  m-r-10 "></i>重置
                                </button>
						</div>
						</form:form>
						<div class="taiji_search_result">
							<table class="table table-striped table-bordered  table-hover">
								<thead>
									<tr>
										<th>网点名称</th>
										<th>网点编号</th>
										<th>机构编号</th>
										<th>联系人姓名</th>
										<th>联系人电话</th>
										<th>起始时间</th>
										<th>终止时间</th>
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