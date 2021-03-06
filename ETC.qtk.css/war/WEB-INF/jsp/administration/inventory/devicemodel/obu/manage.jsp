<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<script type="text/javascript">
	$(function() {
		$.ajaxSetup ({ cache: false});
		$("#myManage").taiji({
			enableAclCheck : true
		}).on("taijiOperateSuccess", function() {
			$(".taiji_search_submit").click();
		});
	});
</script>

</head>
<body>
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
							<h4 class="panel-title">OBU设备型号管理</h4>
						</div>
						<div class="panel-toolbar ">
							<a
								href="${rootUrl }app/administration/inventory/devicemodel/obu/add"
								class="taiji_modal	taiji_acl btn  btn-success m-5"><i
								class="fa fa-plus m-r-5"></i>添加</a>
						</div>
						<div class="panel-body">
							<form:form modelAttribute="queryModel"
								cssClass="taiji_search_form form-inline m-t-5 " id="listForm"
								name="listForm"
								action="${rootUrl}app/administration/inventory/devicemodel/obu/manage"
								method="post">
								<div class="form-group">
									<form:label path="searchBrand">品牌:</form:label>
									<form:select path="searchBrand" cssClass="form-control"
										data-style="btn-white" data-width="100px">
										<form:option value="">请选择</form:option>
											<form:option value="1">埃特斯</form:option>
											<form:option value="2">金溢</form:option>
											<form:option value="3">聚利</form:option>
											<form:option value="4">东海</form:option>
											<form:option value="5">航天信息</form:option>
											<form:option value="6">千方</form:option>
											<form:option value="7">万集</form:option>
											<form:option value="8">中兴</form:option>
											<form:option value="9">握奇</form:option>
											<form:option value="10">搜林</form:option>
											<form:option value="11">成谷</form:option>
											<form:option value="12">云星宇</form:option>
											<form:option value="13">华虹</form:option>
											<form:option value="14">黔通电子</form:option>
											<form:option value="15">通行宝</form:option>
											<form:option value="16">赛格</form:option>
									</form:select>
								</div>
								<div class="form-group m-5">
									<label class="control-label">每页条数</label>
									<form:select path="pageSize" cssClass="form-control"
										data-style="btn-white" data-width="80px">
										<form:option value="10">10</form:option>
										<form:option value="20">20</form:option>
										<form:option value="50">50</form:option>
										<form:option selected="selected" value="100">100</form:option>
										<form:option value="200">200</form:option>
										<form:option value="500">500</form:option>
									</form:select>
								</div>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5"
									type="button">
									<i class="fa fa-search  m-r-10 "></i>查询
								</button>
								<button class="taiji_search_reset btn btn-md btn-default"
									type="button">
									<i class="fa  fa-refresh  m-r-10  "></i>重置
								</button>
							</form:form>
						</div>
						<div class="taiji_search_result table-responsive">
							<table id="my-table" class="table table-bordered  table-hover">
								<thead>
									<tr>
										<th>产品类型</th>
										<th>品牌</th>
										<th>型号</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<div class="panel-footer text-right">
							<div class="pageturn taiji_pager"></div>

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
		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->

</body>
</html>