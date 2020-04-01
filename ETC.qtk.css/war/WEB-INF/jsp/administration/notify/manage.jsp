<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<script src="${rootUrl}plugins/datepicker/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$.ajaxSetup ({ cache: false});
	$("#myManage").taiji({
		enableAclCheck:true
	}).on("taijiAddSuccess", function() {
		$(".taiji_search_submit").click();
	}).on("taijiEditSuccess", function() {
		$(".taiji_search_submit").click();
	}).on("taijiOperateSuccess",function(){
		$(".taiji_search_submit").click();
	}) ;
	
	/* $("#myManage select").change(function() {
		$("#queryButton").click();
	}); */
	
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
							<h4 class="panel-title">通知公告管理</h4>
						</div>

						<%-- <c:if test="${type eq 'ADMIN' }"> --%>
						<div class="panel-toolbar ">
							<a href="${rootUrl}app/administration/notify/add"
								class="taiji_modal_lg taiji_acl btn btn-success m-r-10"><i
								class="fa fa-plus m-r-5"></i>添加文章</a>
						</div>
						<%-- </c:if> --%>
						<div class="panel-body">
							<form:form cssClass="taiji_search_form form-inline m-t-5 "
								modelAttribute="queryModel" id="listForm" name="listForm"
								action="${rootUrl}app/administration/notify/manage"
								method="post">
								<input type="hidden" name="type" id="type"
									value="${fn:escapeXml(type)}" />
								<div class="form-group m-5">
									<div class="form-group m-5">
										<label class="control-label">标题:</label>
										<form:input path="articleTitle" size="50" maxlength="20"
											placeholder="标题" cssClass="form-control" />
									</div>
									<%-- <div  class="form-group m-5">
									    <label class="control-label">发布时间</label>
										<div  class="input-group">
		 						           <form:input style="width:150px" id="startTime" path="startTime" cssClass="form-control" />
							               <span class="input-group-btn">
									        <button type="button" class="btn btn-default" onfocus="WdatePicker({el:'startTime',dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'endTime\')}'});" class="Wdate" size="21"><i class="fa fa-calendar"></i></button>
								           </span>
							           </div>&nbsp;至
							           <div  class="input-group">
		 						           <form:input style="width:150px" id="endTime" path="endTime" cssClass="form-control" />
							               <span class="input-group-btn">
									        <button type="button" class="btn btn-default" onfocus="WdatePicker({el:'endTime',dateFmt:'yyyy-MM-dd 00:00:00',minDate:'#F{$dp.$D(\'startTime\')}'});" class="Wdate" size="21"><i class="fa fa-calendar"></i></button>
								           </span>
							           </div>
									</div> --%>
									<%-- <c:if test="${type eq 'ADMIN' }">
									<div class="form-group m-5">
										<label class="control-label">状态</label>
										<form:select path="articleStatus" cssClass="form-control" data-style="btn-white" data-width="80px">
											<form:option value="">全部</form:option>
											<form:options itemLabel="value"/>
										</form:select>
									</div>
									</c:if> --%>
									<%-- <div class="form-group m-5">
										<label class="control-label">每页条数</label>
										<form:select path="pageSize"  cssClass="form-control"  data-style="btn-white" data-width="80px">
											<form:option value="2">2</form:option>
											<form:option value="10">10</form:option>
											<form:option value="16">16</form:option>
											<form:option value="20">20</form:option>
											<form:option value="50">50</form:option>
											<form:option value="100">100</form:option>
										</form:select>
									</div> --%>
									<button id="queryButton"
										class="taiji_search_submit btn btn-md btn-success m-r-5"
										type="button">
										<i class="fa fa-search  m-r-10 "></i>查询
									</button>
									<button class="taiji_search_reset btn btn-md btn-default"
										type="button">
										<i class="fa  fa-refresh  m-r-10  "></i>重置
									</button>
								</div>
							</form:form>

							<div
								class="taiji_search_result taiji_table_float table-responsive">
								<table class="table table-striped table-bordered  table-hover">
									<thead>
										<tr>
											<!-- <th width="80px">序号</th> -->
											<th>标题</th>
											<th>是否发布</th>
											<th>发布日期</th>
											<th>修改日期</th>
											<th width="90px">是否置顶</th> 
											<th>置顶变更时间</th>
											<th>操作员</th>
											<th width="240px" style="text-align: center;">操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
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
	<%-- <div class="foot">
    <%@include file="/WEB-INF/jsp/preparation/footer.jsp" %>
	</div> --%>


</body>
</html>
