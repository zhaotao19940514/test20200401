<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" ></script>
<script type="text/javascript">
$(function(){
	$("#myManage").taiji({
		enableAclCheck:true
	}).on("taijiEditSuccess",function(event,result){
		
	});
	
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
                            <h4 class="panel-title">每日清算统计</h4>
                        </div>
                        
                        <%-- <div class="panel-toolbar ">
                     		<a href="${rootUrl }app/acl/user/add"  class="taiji_modal	 taiji_acl btn  btn-success m-5"><i class="fa fa-plus m-r-5"></i>添加</a>
                        </div> --%>
                       
                        <div class="panel-body">
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/customerservice/cardobuquery/queryOutOBUDispose/manage" method="post">
								<div class="form-group">
									<div class="form-group m-5">
										<label class="control-label">版本日期</label>
										<form:input cssStyle="width:140px" path="date"
											readonly="true" cssClass="form-control" />
										<button type="button"
											class="btn btn-default btn-success form-control"
											onclick="WdatePicker({el:$dp.$('date'),dateFmt:'yyyyMMdd'});">
											<i class="fa fa-calendar"></i>
										</button>
									</div>
									<div class="form-group m-5">
										<a href="${rootUrl }app/customerservice/cardobuquery/queryOutOBUDispose/export"
											data-selector="#date"
											class="taiji_export {fileUrl:'${rootUrl }app/customerservice/cardobuquery/queryOutOBUDispose/export/file',confirm_message:'您确认要操作吗?'} btn  btn-success m-r-10 ">
											<i class="fa fa-file-excel-o m-r-10"></i>导出Excel
										</a>
										<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
									</div>
								</div>

                        	</form:form>
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
