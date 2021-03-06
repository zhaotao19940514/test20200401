<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>

<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
<script type="text/javascript" src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" ></script>
<script type="text/javascript">
$(function(){
	$("#beforeDate").val("2020-01-01");
    $("#afterDate").val("2020-01-01");
})
</script>

</head>
<body>
<input type="hidden"  class="btn" id="showModel">
<div id="modalBtn" class="modal">
<div class="modal-content">
<header class="modal-header">
<h4>错误信息</h4> 
</header>
<footer>
	<input id="cancel" type="hidden" />
	<input id="sure" type="hidden" />
</footer>
</div>
</div>
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
                            <h4 class="panel-title">物流企业办理统计</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl }app//manage" method="POST">
								<input type='hidden' id='searchExportMark' name='searchExportMark' value=0 />
								<div class="form-group">
									<form:input path="cardId"  maxlength="100"  cssClass="form-control"  placeholder="请输入物流企业名称" />
									<form:input path="cardId"  maxlength="100"  cssClass="form-control"  placeholder="请输入企业经营许可证编号" />
								</div>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5 btn btn-primary btn-small" type="button" id="searchBtn"><i class="fa fa-search  m-r-10 "></i>查询</button>
                        	</form:form>
                        </div>
						<div   class="taiji_search_result table-responsive">
							<table id="my-table" class="table table-bordered  table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>物流企业名称</th>
										<th>企业经营许可证编号</th>
										<th>办理车牌数量</th>
									</tr>
								</thead>
								<tbody>
								 <tr>
										<th>1</th>
										<th>顺丰速运</th>
										<th>575508866253</th>
										<th>23</th>
									</tr>
									<tr>
										<th>2</th>
										<th>韵达快递</th>
										<th>575508866253</th>
										<th>23</th>
									</tr>
									<tr>
										<th>3</th>
										<th>中通速运</th>
										<th>575508866253</th>
										<th>23</th>
									</tr>
									<tr>
										<th>4</th>
										<th>圆通速运</th>
										<th>575508866253</th>
										<th>23</th>
									</tr>
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