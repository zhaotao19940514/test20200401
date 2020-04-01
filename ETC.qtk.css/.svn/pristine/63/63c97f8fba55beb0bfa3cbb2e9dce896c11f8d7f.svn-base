<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
	<script type="text/javascript">
		var individualIdType;
		var unitIdType;
		$(function() {
			$.ajaxSetup ({ cache: false});
			$("#myManage").taiji({
				enableAclCheck : true,
				search : {
					autoSearch : false
				}
			});
			
			//页面加载完成隐藏单位证件类型
// 			$(".unitIdType").toggle();
// 			$(".unitIdType").attr("disabled", true);
			//证件类型数据初始化
			idTypeDataInit();
			function idTypeDataInit() {
				individualIdType = $(".personalIdType");
				unitIdType = $(".unitIdType");
				$("#customerIdType option:not(:first)").remove();
				for (var i = 0; i < individualIdType.length; i++) {
					$("#customerIdType").append(individualIdType[i]);
				}
			}
			
			$("#customerType").change(function() {
				var type = $(this).val();
				if(type==1){
					$("#bloc").hide();
					$("#customerIdType option:not(:first)").remove();
					for (var i = 0; i < individualIdType.length; i++) {
						$("#customerIdType").append(individualIdType[i]);
					}
					$("#customerIdType option:first").prop("selected", 'selected'); 
// 					$(".personalIdType").removeAttr("disabled");
// 					$(".unitIdType").attr("disabled", true);
// 					$(".personalIdType").toggle();
// 					$(".unitIdType").toggle();
				}
				if(type==2){
					$("#bloc").show();
					$("#customerIdType option:not(:first)").remove();
					for (var i = 0; i < unitIdType.length; i++) {
						$("#customerIdType").append(unitIdType[i]);
					}
					$("#customerIdType option:first").prop("selected", 'selected'); 
// 					$(".unitIdType").removeAttr("disabled");
// 					$(".personalIdType").attr("disabled", true);
// 					$(".unitIdType").toggle();
// 					$(".personalIdType").toggle();
				}
			});
			
			//用户是否已存在校验
			$("#customerCheckBtn").click(function() {
				var customerType = $("#customerType").val();
				var customerIdType = $("#customerIdType").val();
				var customerIdNum = $("#customerIdNum").val();
				var department = $("#department").val();
				var url = '${rootUrl }';
				if(customerType == null || customerType == ""){
					$.Taiji.showWarn("请先选择用户类型！");
				}else if(customerIdType == null || customerIdType == ""){
					$.Taiji.showWarn("请先选择证件类型！");
				}else if(customerIdNum == null || customerIdNum == ""){
					$.Taiji.showWarn("请输入证件号！");
				}else if(customerType == 2 && department == null || department == ""){
					$.Taiji.showWarn("请输入分支机构名称！");
				}else{
					var data ={};
					data.checkType = 1;
					data.customerType = customerType;
					data.customerIdType = customerIdType;
					data.customerIdNum = customerIdNum;
					data.department = department;
					$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
					$.ajax({
						url:url+"app/apply/quickapply/infoCheck/quickApplyCheck",
						data:JSON.stringify(data),
						type:"POST",
						contentType : 'application/json',
				        dataType : 'json',
						success:function(response){
// 							console.log(response);
							$.Taiji.hideLoading();
							if(response.success){
				            	$.Taiji.showNote(response.message+"校验通过！可以开户！");
			            	}else{
				            	$.Taiji.showWarn(response.message+"校验不通过！不可以开户");
				            	var toUrl = url + "app/apply/baseinfo/usermanager/manageEdit/" + response.customerId;
				            	if(response.customerId != null){
					            	if(confirm("该用户已存在！是否跳转到该用户编辑页面?")){
					            		window.location.replace(toUrl);
					            	}
				            	}
			            	}
						},
						error:function(error){
// 							console.log(error);
							$.Taiji.hideLoading();
							$.Taiji.showWarn('请求校验失败！');
						}
					});
		        }
			});
			
			//车辆是否已存在校验
			$("#vehicleCheckBtn").click(function() {
				var vehiclePlate = $("#vehiclePlate").val();
				var vehiclePlateColor = $("#vehiclePlateColor").val();
				var type = $("#type").val();
				var url = '${rootUrl }';
				if(vehiclePlate == null || vehiclePlate == ""){
					$.Taiji.showWarn("请先填写车牌号！");
				}else if(vehiclePlateColor == null || vehiclePlateColor == "" || vehiclePlateColor == 9){
					$.Taiji.showWarn("请先选择车牌颜色！");
				}else{
					var data ={};
					data.checkType = 2;
					data.vehiclePlate = vehiclePlate;
					data.vehiclePlateColor = vehiclePlateColor;
					data.type = type;
					$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
					$.ajax({
						url:url+"app/apply/quickapply/infoCheck/quickApplyCheck",
						data:JSON.stringify(data),
						type:"POST",
						contentType : 'application/json',
				        dataType : 'json',
						success:function(response){
// 							console.log(response);
// 							debugger;
							$.Taiji.hideLoading();
							if(response.success){
								$.Taiji.showWarn(response.message);
			            	}else{
			            		$.Taiji.showWarn(response.message);
// 				            	$.Taiji.showWarn("校验失败！");
				            	var toUrl = url + "app/apply/baseinfo/usermanager/manageEdit/" + response.customerId;
				            	if(response.customerId != null){
					            	if(confirm("该车辆本地已存在！是否跳转到对应的用户编辑页面?")){
					            		window.location.replace(toUrl);
					            	}
				            	}
			            	}
						},
						error:function(error){
							$.Taiji.hideLoading();
							$.Taiji.showWarn('请求校验失败！');
						}
					});
		        }
			});
			
		});
	</script>

<style type="text/css">
	.essential {
		color: red;
	}
</style>

</head>
<body >

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
                            <h4 class="panel-title">用户和车辆信息校验</h4>
                        </div>
                        <div class="panel-body">
							<form:form cssClass="form-inline m-t-5 "
								modelAttribute="pageModel" id="listForm" name="listForm"
								action="" method="post">
								<form:label path="customerType"><span class="essential">*</span>客户类型：</form:label>
								<form:select path="customerType" cssClass="form-control m-r-5"
									id="customerType" data-style="btn-white" data-width="150px">
									<form:options items="${customerType}" itemLabel="value"
										itemValue="typeCode" />
								</form:select>
								<form:label path="customerIdType"><span class="essential">*</span>证件类型：</form:label>
								<select id="customerIdType" name="customerIdType" class="form-control"
									data-style="btn-white" data-width="180px">
									<option value="">--请选择--</option>
									<c:forEach items="${idType}" var='it'>
										<option class='personalIdType' value="${it.typeCode}">${it.value}</option>
									</c:forEach>
									<c:forEach items="${unitIdType}" var='ut'>
										<option class='unitIdType' value="${ut.typeCode}">${ut.value}</option>
									</c:forEach>
								</select>
								<form:label path="customerIdNum"><span class="essential">*</span>证件号：</form:label>
								<form:input path="customerIdNum" id="customerIdNum" maxlength="180"
									cssClass="form-control" placeholder="证件号必填" />
								<span style="display: none;" id="bloc"><form:label path="department"><span class="essential">*</span>分支机构名称：</form:label>
									  <form:input path="department" id="department" maxlength="80"
									  cssClass="form-control" value="本部" /></span>
								<a href="#" class="btn btn-sm btn-success" id="customerCheckBtn">用户信息校验</a>
							</form:form>
						</div>
						<div class="panel-body">
							<form:form cssClass="form-inline m-t-5 "
								modelAttribute="pageModel" id="listForm" name="listForm"
								action="" method="post">
								<form:label path="vehiclePlate"><span class="essential">*</span>车牌号：</form:label>
								<form:input path="vehiclePlate" maxlength="180"
									cssClass="form-control" placeholder="车牌号必填" />
								<form:label path="vehiclePlateColor"><span class="essential">*</span>车牌颜色：</form:label>
								<form:select path="vehiclePlateColor" id="vehiclePlateColor" cssClass="dataChange form-control m-r-5" data-style="btn-white" data-width="120px">
									<form:option value="">全部</form:option>
									<form:options items="${vehiclePlateColorTypes}" itemLabel="value" itemValue="typeCode"/>
								</form:select>
								<form:label path="type"><span class="essential">*</span>收费车型：</form:label>
								<form:select path="type" id="type" cssClass="dataChange form-control m-r-5" data-style="btn-white" data-width="120px">
									<form:options items="${vehicleTypes}" itemLabel="value" itemValue="typeCode"/>
								</form:select>
								<a href="#" class="btn btn-sm btn-success" id="vehicleCheckBtn">车辆信息校验</a>
							</form:form>
						</div>
						<div class="panel-footer text-right">
							<div class="pageturn taiji_pager">
	                        </div>
	                       
             		  </div>
					</div>

			    </div>

			</div>

		</div>
		
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
	</div>

</body>
</html>
