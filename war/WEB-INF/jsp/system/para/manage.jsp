<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script type="text/javascript">
$(function(){
	$("#myManage").taiji({
		enableAclCheck:true
	}).on("taijiAjaxFormSuccess",function(event,result){
		
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
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">系统参数</h4>
                        </div>
						<div class="panel-body">
							<form:form modelAttribute="para" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/system/para/manage" method="post">
								  <div class="form-group">
								    <label  class="col-sm-4 control-label">页眉：</label>
								    <div class="col-sm-6">
								    	<form:input path="printHead"  cssClass="form-control" placeholder="页眉必填"  cssStyle="width:200px"/>
								    </div>
								  </div>
							</form:form>	  
                        </div>
						<div class="panel-footer text-center">
							<a href="#" data-form="#myForm" class="taiji_ajaxForm {confirm_message:'你确定修改吗?'} btn btn-sm btn-success" id="taijiAjaxformBtn">保存</a>
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
