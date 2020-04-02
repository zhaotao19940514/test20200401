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
			enableAclCheck : false,
			search:{
				 autoSearch:false
			}
		}).on("taijiAddSuccess", function() {
			$(".taiji_search_submit").click();
		}).on("taijiEditSuccess", function() {
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
							<h4 class="panel-title">黔通卡库存查询</h4>
						</div>
						<div class="panel-body">
							<form:form modelAttribute="queryModel"
								cssClass="taiji_search_form form-inline m-t-5 " id="listForm"
								name="listForm"
								action="${rootUrl}app/administration/inventory/inventoryQuery/card/manage"
								method="post">
								<div class="form-group">
									<div class="form-group m-5">
										<form:label path="batchId">批次号</form:label>
										<form:input path="batchId" size="25" maxlength="50"
											placeholder="批次号" cssClass="form-control" />
									</div>
									<div class="form-group m-5">
										<form:label path="serviceHallId">网点编号</form:label>
										<form:input path="serviceHallId" size="25" maxlength="50"
											placeholder="网点编号" cssClass="form-control" />
									</div>
									<div class="form-group m-5">
										<form:label path="serviceHallName">网点名称</form:label>
										<form:input path="serviceHallName" size="25" maxlength="50"
											placeholder="网点名称" cssClass="form-control" />
									</div>
									<div class="form-group m-5">
										<form:label path="startCardId">卡号</form:label>
										<form:input path="startCardId" size="25" maxlength="50"
											placeholder="卡号" cssClass="form-control" />
										<form:label path="endCardId">至</form:label>
										<form:input path="endCardId" size="25" maxlength="50"
											placeholder="卡号" cssClass="form-control" />
									</div>
									<div class="form-group m-5">
										<form:label path="status">状态</form:label>
										<form:select path="status" cssClass="form-control"
											data-style="btn-white" data-width="100px">
											<form:option selected="selected" value="">全部</form:option>
											<form:option value='1'>入库</form:option>
											<form:option value='2'>调拨</form:option>
											<form:option value='5'>调拨申请</form:option>
											<form:option value='4'>发行</form:option>
										</form:select>
									</div>
									<div class="form-group m-5">
										<label class="control-label">每页条数</label>
										<form:select path="pageSize" cssClass="form-control"
											data-style="btn-white" data-width="80px">
											<form:option value="10">10</form:option>
											<form:option value="20">20</form:option>
											<form:option selected="selected" value="50">50</form:option>
											<form:option value="100">100</form:option>
											<form:option value="200">200</form:option>
											<form:option value="500">500</form:option>
										</form:select>
										<button class="taiji_search_submit btn btn-md btn-success m-r-5"
											type="button">
											<i class="fa fa-search  m-r-10 "></i>查询
										</button>
										<button class="taiji_search_reset btn btn-md btn-default"
											type="button">
											<i class="fa  fa-refresh  m-r-10  "></i>重置
										</button>
									</div>
								</div>
							</form:form>
						</div>
						<div class="taiji_search_result taiji_table_float table-responsive">
							<table id="my-table" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>卡号</th>
										<th>卡品牌</th>
										<th>卡类型</th>
										<th>批次号</th>
										<th>网点编号</th>
										<th>网点名称</th>
										<th>业务员</th>
										<th>状态</th>
										<th>操作时间</th>
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