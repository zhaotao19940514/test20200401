<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<script type="text/javascript" src="${rootUrl }plugins/webuploader/webuploader.js"></script>
<script type="text/javascript">
var individualIdType;
var unitIdType;
$(function(){
	$.ajaxSetup ({ cache: false});
	$("#myManage").taiji({
		enableAclCheck:true,
		search:{
			 autoSearch:true
		}
	}); 
	//页面加载完成隐藏单位证件类型
// 	$(".unitIdType").hide();
// 	$(".unitIdType").attr("disabled", true);
	//证件类型数据初始化
	idTypeDataInit();
	function idTypeDataInit() {
		var type = $("#customerType").val();
		individualIdType = $(".personalIdType");
		unitIdType = $(".unitIdType");
		$("#idType option").remove();
		if(type==1){
			$("#bloc").hide();
			for (var i = 0; i < individualIdType.length; i++) {
				$("#idType").append(individualIdType[i]);
			}
			$("#idType option:first").prop("selected", 'selected');
// 			$(".personalIdType").removeAttr("disabled");
// 			$(".unitIdType").attr("disabled", true);
		}
		if(type==2){
			$("#bloc").show();
			for (var i = 0; i < unitIdType.length; i++) {
				$("#idType").append(unitIdType[i]);
			}
			$("#idType option:first").prop("selected", 'selected');
// 			$(".unitIdType").removeAttr("disabled");
// 			$(".personalIdType").attr("disabled", true);
		}
	}
	
	$("#customerType").change(function() {
		var type = $(this).val();
		if(type==1){
			$("#bloc").hide();
			$("#idType option:not(:first)").remove();
			for (var i = 0; i < individualIdType.length; i++) {
				$("#idType").append(individualIdType[i]);
			}
			$("#idType option:first").prop("selected", 'selected');
// 			$(".personalIdType").removeAttr("disabled");
// 			$(".unitIdType").attr("disabled", true);
		}
		if(type==2){
			$("#bloc").show();
			$("#idType option:not(:first)").remove();
			for (var i = 0; i < unitIdType.length; i++) {
				$("#idType").append(unitIdType[i]);
			}
			$("#idType option:first").prop("selected", 'selected');
// 			$(".unitIdType").removeAttr("disabled");
// 			$(".personalIdType").attr("disabled", true);
		}
	});
	
	$("#tjAjaxFormBtn").click(function(){
// 		if(!validatePicType()){
// 			return;
// 		}
		$("#addFormDiv").taiji("ajaxForm",$("#addForm"),{
			bsSuccess:function(responseText,note){
				$.Taiji.showNote(note);
				var result = $.parseJSON(responseText);
				var cstmId = result.customerId;
				$("#searchCustomerId").val(cstmId);
				$("#carContent").show();
				$("#addCustomer").hide();
				var addCar = '<a href="${rootUrl }app/apply/emergency/usermanager/addCar/' + cstmId +'" class="taiji_modal_lg taiji_acl btn  btn-success m-5"><i class="fa fa-plus m-r-5"></i>新增车辆</a>';
				$("#addCar").prepend(addCar);
				$("#searchCardBtn").click();
				var viewPic = '<a href="${rootUrl }app/apply/emergency/quickopen/view/' + cstmId +'" class="taiji_modal_lg taiji_acl btn  btn-success m-5">查看开户证件图片</a>';
// 				$("#picture").append(viewPic);
				$("#file").after(viewPic);
		      }
			});
			return false;
		});
	
	
	$("#file").change(function(){
		$("#imgsViewer").html('');
		var files = $(this).get(0).files;
		for(var i = 0;i<files.length;i++){
			var file = files[i];
			var reader = new FileReader();//新建一个FileReader
			reader.readAsDataURL(file);
			reader.addEventListener("load", function (e) {
		        var image = new Image();
		        image.height = 80;
		        image.width = 160;
// 		        image.title = file.name;
		        image.src = this.result;
		        $(image).addClass("showPic") ;
		        $("#imgsViewer")[0].appendChild( image );
		        loadingShowPic();
		    }, false);
		}
		
	});
	
	function loadingShowPic(){
		$(".showPic").mouseover(function(){
    		  $(this).css("height","230px");
    		  $(this).css("width","450px");
    	});
		$(".showPic").mouseout(function(){
			$(this).css("height","80px");
  		  	$(this).css("width","160px");
		});
	}
	
// 	function validatePicType(){
// 		var files = $("#file").get(0).files;
// 		for(var i = 0;i<files.length;i++){
// 			var file = files[i];
// 			if(file.type != 'image/png' && file.type != 'image/jpg' && file.type != 'image/jpeg'){
// 				$.Taiji.showWarn("只能上传png、jpg、jpeg格式的照片！");
// 				return false;
// 			}
// 		}
// 		return true;
// 	}
});
	
</script>

<style type="text/css">
	.essential {
		color: red;
	}
</style>

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
							<h4 class="panel-title">快速开户</h4>
						</div>
						<div class="panel-body">
							<div id='addFormDiv'>
								<form:form modelAttribute="pageModel"
									cssClass="form-inline m-t-5 " id="addForm" name="cListForm"
									action="${rootUrl}app/apply/emergency/quickopen/add"
									method="post">
									<div class="form-group m-5">
										<form:label path="customerType"><span class="essential">*</span>客户类型：</form:label>
										<form:select path="customerType" cssClass="form-control m-r-5"
											id="customerType" data-style="btn-white" data-width="150px">
											<form:options items="${customerType}" itemLabel="value"
												itemValue="typeCode" />
										</form:select>
										<form:label path="customerIdType"><span class="essential">*</span>证件类型：</form:label>
										<select id="idType" name="customerIdType" class="form-control"
											data-style="btn-white" data-width="180px">
											<option value="">--请选择--</option>
											<c:forEach items="${idType}" var='it'>
												<option value="${it.typeCode}" class='personalIdType'>${it.value}</option>
											</c:forEach>
											<c:forEach items="${unitIdType}" var='ut'>
												<option class='unitIdType' value="${ut.typeCode}">${ut.value}</option>
											</c:forEach>
										</select>
										<form:label path="customerIdNum"><span class="essential">*</span>证件号码：</form:label>
										<form:input path="customerIdNum" maxlength="50"
											cssClass="form-control" placeholder="证件号码必填" />
									</div>
									<br>
									<div class="form-group m-5">
										<form:label path="customerName"><span class="essential">*</span>客户名称：</form:label>
										<form:input path="customerName" maxlength="50"
											cssClass="form-control" placeholder="客户名称必填" />
										<form:label path="tel"><span class="essential">*</span>手机号码：</form:label>
										<form:input path="tel" maxlength="13" cssClass="form-control"
											placeholder="手机号码必填" />
										<form:label path="address"><span class="essential">*</span>联系地址：</form:label>
										<form:input path="address" maxlength="100"
											cssClass="form-control" placeholder="联系地址必填" />
										
									</div>
									<%-- <br>
									<div class="form-group m-5">
										<form:label path="">邮箱：</form:label>
										<form:input path="" maxlength="80" cssClass="form-control" placeholder="邮箱" />
										<form:label path="">报装地址：</form:label>
										<form:input path="" maxlength="80" cssClass="form-control" placeholder="报装地址" />
									</div> --%>
									<div id="bloc" class="form-group m-5" style="display: none;">
										<div>
											<form:label path="department"><span class="essential">*</span>分支机构名称：</form:label>
											<form:input path="department" maxlength="80"
												cssClass="form-control" value="本部" placeholder="单位客户必填"/>
											<%-- <form:label path="">单位类型：</form:label>
											<form:select path="" cssClass="form-control m-r-5"
												data-style="btn-white" data-width="180px">
												<form:option value="">--请选择--</form:option>
											</form:select> --%>
											<form:label path="agentName"><span class="essential">*</span>经办人名称：</form:label>
											<form:input path="agentName" maxlength="50"
												cssClass="form-control" placeholder="单位客户必填" />
											<form:label path="agentIdType"><span class="essential">*</span>经办人证件类型：</form:label>
											<form:select path="agentIdType" cssClass="form-control m-r-5"
												data-style="btn-white" data-width="180px">
												<%-- <form:option value="">--请选择--</form:option> 
												<form:options items="${idType}" itemLabel="value"
													itemValue="typeCode" /> --%>
												<option value="101">身份证(含临时身份证)</option>
											</form:select>
											<form:label path="agentIdNum"><span class="essential">*</span>经办人证件号：</form:label>
											<form:input path="agentIdNum" maxlength="50"
												cssClass="form-control" placeholder="单位客户必填" />
										</div>
									</div>
									<br>
									<div id="picture">
									<form:label path="">证件照：</form:label>
										<input id="file" type="file" class="form-control" multiple="multiple" accept="image/png,image/jpg,image/jpeg" name="file" />
										<p style="font-size: 10px;color: red;">注：只能上传png、jpg、jpeg格式的照片！</p>
									</div>
									<div id = 'imgsViewer'></div>
								</form:form>
							</div>
							<div class="text-right" id="operationPanel">
								<span id="addCustomer"><a href="#"
									class="{confirm_message:'你确定提交操作吗?'} btn btn-sm btn-success"
									id="tjAjaxFormBtn">保存</a></span>
								<span id="addCar"></span>
							</div>
							<div id="carContent" style="display: none;">
								<div class="panel-body">
									<form:form cssClass="taiji_search_form form-inline m-t-5 "
										modelAttribute="queryModel" id="listForm" name="listForm"
										action="${rootUrl}app/apply/emergency/quickopen/manage" method="post">
										<div  style="display: none;">
											<div class="form-group m-5">
												<form:input path="customerId" id="searchCustomerId" size="25" maxlength="50"
													placeholder="用户编号" cssClass="form-control" value=""/>
											</div>
											<div class="form-group m-5">
												<label class="control-label">每页条数</label>
												<form:select path="pageSize" cssClass="form-control m-r-5"
													data-style="btn-white" data-width="80px">
													<form:option value="10" selected="selected">10</form:option>
												</form:select>
											</div>
										</div>
										<div class="form-group m-5">
												<form:label path="vehiclePlate">车牌号：</form:label>
												<form:input path="vehiclePlate" size="25" maxlength="9"
													placeholder="车牌号" cssClass="form-control" />
										</div>
										<button id="searchCardBtn"
											class="taiji_search_submit btn btn-md btn-success m-r-5"
											type="button">
											<i class="fa fa-search  m-r-10 "></i>查询
										</button>
									</form:form>
								</div>
								<div
									class="taiji_search_result taiji_table_float table-responsive">
									<table class="table table-striped table-bordered  table-hover">
										<thead>
											<tr>
												<th width="60px">序号</th>
												<th width="80px">车辆编号</th>
												<th width="80px">车牌号码</th>
												<th width="60px">车牌颜色</th>
												<th width="60px">收费车型</th>
												<th width="60px">是否应急车辆</th>
												<th width="130px">操作</th>
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


						</div>
					</div>
				</div>
			</div>
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