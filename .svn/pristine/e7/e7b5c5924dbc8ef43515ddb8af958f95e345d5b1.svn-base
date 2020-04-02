<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<script type="text/javascript">
$(function(){
	$.ajaxSetup ({ cache: false});
	$("#myManage").taiji({
		enableAclCheck:true,
		search:{
            autoSearch:false
        }
	});
});
</script>

</head>
<body>
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
                            <h4 class="panel-title">公共基础信息弹窗显示测试</h4>
                        </div>
                        <div class="panel-body">
                        	<div class="form-group m-5">
								<div class="form-group m-5">
									<a href="${rootUrl }app/apply/baseinfo/commonQuery/agencyIdView/52010106004" class="taiji_modal_lg taiji_acl">机构详情agencyId测试</a>
								</div>
								<div class="form-group m-5">
									<a href="${rootUrl }app/apply/baseinfo/commonQuery/servicehallIdView/5201010600427010014" class="taiji_modal_lg taiji_acl">服务网点详情servicehallId测试</a>
								</div>
								<div class="form-group m-5">
									<a href="${rootUrl }app/apply/baseinfo/commonQuery/staffIdView/13339608880" class="taiji_modal_lg taiji_acl">工号详情StaffId测试</a>
								</div>
								<div class="form-group m-5">
									<a href="${rootUrl }app/apply/baseinfo/commonQuery/userByStaffIdView/kctest" class="taiji_modal_lg taiji_acl">员工详情StaffId测试</a>
								</div>
								<div class="form-group m-5">
									<a href="${rootUrl }app/apply/baseinfo/commonQuery/userByLoginNameView/kctest" class="taiji_modal_lg taiji_acl">员工详情LoginName测试</a>
								</div>
								<div class="form-group m-5">
									<a href="${rootUrl }app/apply/baseinfo/commonQuery/issuePackageNumView/3" class="taiji_modal_lg taiji_acl">发行套餐详情PackageNum测试</a>
								</div>
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