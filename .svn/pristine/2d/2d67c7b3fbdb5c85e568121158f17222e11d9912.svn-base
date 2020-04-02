<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
$(function(){
	$.ajaxSetup ({ cache: false}); 
	$.fn.showPopover.defaults.placement="right";
	$.fn.showPopover.defaults.viewport=null;

	$("#submit").click(function(){
		$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"add"}]);
	});
});
$("#agencyId").change(function(){
	var agencyId = $("#agencyId").val();
	var agencyIdInfo =$("#agencyId").find("option:selected").text()
	if(agencyId!=null){
		$("#agencyInfo").text(agencyIdInfo+"-"+agencyId);
	}
})
$("#permittedAgencyId").change(function(){
	var permittedAgencyId = $("#permittedAgencyId").val();
	var permittedAgencyIdInfo =$("#permittedAgencyId").find("option:selected").text()
	if(permittedAgencyId!=null){
		$("#permittedAgencyInfo").text(permittedAgencyIdInfo+"-"+permittedAgencyId);
	}
});
$("#cardType").change(function(){
	$("#cardTypeInfo").text($(this).find("option:selected").text());
});
</script>
</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">渠道权限管理--添加</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="addModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/administration/agencypermission/add" method="POST">
	
					<div>
						<div>
										<div class="form-group m-t-5">
												<label class="control-label" style="font-size:15px">控制渠道</label> <select
													id="agencyId"
													name="agencyId"
													class="taiji_autocomplete form-control"
													style="width:220px;"
													data-url="${rootUrl }app/administration/agencypermission/queryByAgencyName"
													data-placeholder="请选择控制渠道" style="width: 20em;">
													<option></option>
												</select>
											</div>
										<div class="form-group m-t-5">
												<label class="control-label" style="font-size:15px">被控渠道</label> <select
													id="permittedAgencyId"
													name="permittedAgencyId"
													class="taiji_autocomplete form-control"
													style="width:220px;"
													data-url="${rootUrl }app/administration/agencypermission/queryByPermissionAgencyName"
													data-placeholder="请选择被控制渠道" style="width: 20em;">
													<option></option>
												</select>
										</div>
						</div>
											<div class="form-group m-t-5" style="width:290px;display：float left;">
										<span style="width:60px;height:30px;display:inline-block;font-size:15px">卡类型</span>
													<select id="cardType" name="cardTypeCode" style="width:220px;height:30px;display:inline-block;font-size:13px" data-style="btn-white" data-width="300px">
													<option value="">-----------请选择卡类型--------------</option>
													<c:forEach items="${cardTypeCode}" var="code">
													<option value="${code.code }">${code.value }</option>
													</c:forEach>
												</select>
										</div>
										<span  style="color: red;font-size: 15px">当前操作会添加以下权限信息 请确认无误后提交</span>
											<table class="table table-bordered" >
					<tr>
					<th style="font-size:13px;"></th>
							<th style="font-size:13px;">控制渠道</th>
							<th style="font-size:13px;">被控制渠道</th>
							<th style="font-size:13px;">卡类型</th>
					</tr>
					<tr>
					<th style="font-size:13px;">渠道信息</th>
							<th style="font-size:13px;" id="agencyInfo"></th>
							<th style="font-size:13px;" id="permittedAgencyInfo"></th>
							<th style="font-size:13px;" id="cardTypeInfo"></th>
					</tr>
			</table>
								</div>
   </form:form> 
</div>

<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>