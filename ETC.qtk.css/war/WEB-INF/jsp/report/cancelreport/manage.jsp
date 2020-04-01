<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<link
	href="${rootUrl }plugins/bootstrap-editable/css/bootstrap-editable.css"
	rel="stylesheet" />
<script
	src="${rootUrl }plugins/bootstrap-editable/js/bootstrap-editable.min.js"
	type="text/javascript"></script>
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#myManage").taiji({
			enableAclCheck : true,
			search : {
				 autoSearch:false
			}
		});
		$("#myManage select").change(function() {
			
		});

		$("#dataExport").click(function() {
			dataExport();
		});
	});

	function dataExport() {
		$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
		var agencyId = $("#agencyId").val();
		if(agencyId==""){
			$.Taiji.hideLoading();
			return;
		}
		var startTime = $("#startTime").val();
		if(startTime==""){
			$.Taiji.hideLoading();
			return;
		}
		var endTime = $("#endTime").val();
		if(endTime==""){
			$.Taiji.hideLoading();
			return;
		}
		var vehicleIsGui = $("#vehicleIsGui").val();
		var agencyName = $("#agencyId").find("option:selected").text();
		$.ajax({
					type : "post",
					url : "${rootUrl}app/report/cancelreport/export",
					data : {
						"vehicleIsGui":vehicleIsGui,
						"startTime":startTime,
						"endTime":endTime,
						"agencyId":agencyId
					},
					success : function(data) {
						window.location.href = "${rootUrl}app/report/cancelreport/export?agencyName="+agencyName+"&startTime="+startTime+"&endTime="+endTime;
						$.Taiji.hideLoading();
					},
					error : function(d, msg, t) {
						$.Taiji.ShowWarn("请稍后再试");
					}
				});
	};
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
							<h4 class="panel-title">渠道注销量查询</h4>
						</div>
						<div class="panel-body">
							<form:form cssClass="taiji_search_form form-inline m-t-5 "
								modelAttribute="queryModel" id="listForm" name="listForm"
								action="${rootUrl}app/report/cancelreport/manage" method="post">
								<br>
								<div class="form-group m-5">
									<label>渠道</label>
									<form:select path="agencyId" style="width:220px;height:30px;display:inline-block;" data-style="btn-white" data-width="300px">
													<option value="">-----------请选择渠道--------------</option>
													<c:forEach items="${agency}" var="agency">
													<option value="${agency.agencyId }">${agency.name }</option>
													</c:forEach>
												</form:select>
								</div>
								<div class="form-group m-5">
									<label class="control-label">是否限制贵籍</label>
									<form:select path="vehicleIsGui" style="width:50px;height:30px;display:inline-block;">
										<option value="1">是</option>
										<option value="0">否</option>
										</form:select>
								</div>
								<div class="form-group m-t-5">
									<label class="control-label">日期</label>
									<div class="input-group">
										<form:input  cssStyle="width:150px"   path="startTime" readonly="true"  cssClass="form-control" />
										<span class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"><i class="fa fa-calendar"></i></button>
										
										</span>
									</div>
								</div>
								<div class="form-group m-t-5">
									<label class="control-label">至</label>
									<div class="input-group">
										<form:input cssStyle="width:150px" path="endTime"  readonly="true" cssClass="form-control"   />
										<span class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('endTime'),dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"><i class="fa fa-calendar"></i></button>
								</span>
									</div>
								</div>

								<button class="taiji_search_submit btn btn-md btn-success m-r-5"
									type="button">
									<i class="fa fa-search  m-r-10 "></i>查询
								</button>
								<button class="taiji_search_reset btn btn-md btn-default"
									type="button">
									<i class="fa  fa-refresh  m-r-10  "></i>重置
								</button>
<!-- 								<div class="form-group m-5"> -->
<!-- 									<label class="control-label">每页条数</label> -->
<%-- 									<form:select path="pageSize" cssClass="selectpicker" --%>
<%-- 										data-style="btn-white" data-width="80px"> --%>
<%-- 										<form:option value="10">10</form:option> --%>
<%-- 										<form:option value="40" selected="selected">30</form:option> --%>
<%-- 										<form:option value="200">200</form:option> --%>
<%-- 									</form:select> --%>
<!-- 								</div> -->
								<a href="#none" class="btn btn-sm btn-success" id="dataExport">数据导出</a>

							</form:form>
						</div>
						<div
							class="taiji_search_result taiji_table_float table-responsive">
							<table class="table table-striped table-bordered  table-hover"
								id="tableExcel">
								<thead>
									<tr>
										<th>序号</th>
										<th>用户名</th>
										<th>车牌</th>
										<th>工号</th>
										<th>注销时间</th>
										<th>网点名称</th>
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
