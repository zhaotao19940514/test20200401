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
<script type="text/javascript">
	$(function() {
		$.ajaxSetup ({ cache: false});
		$("#myManage").taiji({
			enableAclCheck : true,
			search : {
				autoSearch : true,
				autoRefresh : {
					enable : true,

				}
			}
		}).on("taijiOperateSuccess", function() {
			$(".taiji_search_submit").click();
		});
		
		 //页面加载完成隐藏单位证件类型
		$(".unitIdType").each(function () {
	      if(!$(this).parent().is('span')) {
	          $(this).wrap("<span style='display:none'></span>");
	      }
	   });
		$(".personalIdType").each(function () {
		      if(!$(this).parent().is('span')) {
		          $(this).wrap("<span style='display:none'></span>");
		      }
		   });
		$("#customerType").change(function() {
			var type = $(this).val();
			if(type==1||type==2){
				$(".personalIdType").each(function(){
	           	 if($(this).parent().is('span')){
	                 $(this).unwrap();
	             }
	       		 });
				$(".unitIdType").each(function () {
	                if(!$(this).parent().is('span')) {
	                    $(this).wrap("<span style='display:none'></span>");
	                }
	            });
			}
			if(type==3){
				$(".unitIdType").each(function(){
	           	 if($(this).parent().is('span')){
	                 $(this).unwrap();
	             }
	      		});
				$(".personalIdType").each(function () {
	                if(!$(this).parent().is('span')) {
	                    $(this).wrap("<span style='display:none'></span>");
	                }
	            });
			}
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
							<h4 class="panel-title">卡签设备型号管理</h4>
						</div>
						<div class="panel-toolbar ">
							<a
								href="${rootUrl }app/administration/inventory/devicemodel/card/add"
								class="taiji_modal	taiji_acl btn  btn-success m-5"><i
								class="fa fa-plus m-r-5"></i>添加</a>
						</div>
						<div class="panel-body">
							<form:form modelAttribute="queryModel"
								cssClass="taiji_search_form form-inline m-t-5 " id="listForm"
								name="listForm"
								action="${rootUrl}app/administration/inventory/devicemodel/card/manage"
								method="post">
								<div class="form-group">
									<div class="form-group m-5">
										<form:label path="searchType">产品类型</form:label>
										<form:select path="searchType" cssClass="form-control m-r-5"
											id="customerType" data-style="btn-white" data-width="150px">
											<option value="">--请选择--</option>
											<form:options items="${equipmentType}" itemLabel="value"
												itemValue="code" />
										</form:select>
									</div>
									<div class="form-group m-5">
										<form:label path="searchBrand">品牌</form:label>
										<form:select path="searchBrand" class="form-control"
											data-style="btn-white" data-width="180px">
											<option value="">--请选择--</option>
											<c:forEach items="${cardType}" var='it'>
												<option value="${it.code}" class='personalIdType'>${it.value}</option>
											</c:forEach>
											<c:forEach items="${obuType}" var='ut'>
												<option class='unitIdType' value="${ut.code}">${ut.value}</option>
											</c:forEach>
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
							<table id="my-table" class="table table-striped table-bordered  table-hover">
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