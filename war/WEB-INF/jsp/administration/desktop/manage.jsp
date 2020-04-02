<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script src="${rootUrl}plugins/datepicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#myManage").taiji({
		enableAclCheck:true
	}).on("taijiAddSuccess",function(event,result){
	});
	
	$("#myManage select").change(function() {
		$("#queryButton").click();
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
                            <h4 class="panel-title">通知公告桌面</h4>
                        </div>
                        
                    	<%-- <c:if test="${type eq 'ADMIN' }"> --%>
                      	<%-- </c:if> --%>
                        <div class="panel-body">
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/administration/desktop/manage" method="post">
                        		<input type="hidden" name="type" id="type" value="${fn:escapeXml(type)}" />

						</form:form>
							<div   class="taiji_search_result taiji_table_float table-responsive">
								<table class="table table-striped table-bordered  table-hover">
									<thead>
										<tr>
											<th>标题</th>
											<th>发布日期</th>
										 	<th>是否置顶</th>
										 	<th>置顶变更时间</th>
										 	<th>操作员</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
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
	<%-- <div class="foot">
    <%@include file="/WEB-INF/jsp/preparation/footer.jsp" %>
	</div> --%>
	

</body>
</html>
