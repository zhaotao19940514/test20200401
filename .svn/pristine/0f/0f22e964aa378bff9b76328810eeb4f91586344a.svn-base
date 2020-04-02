<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
	$(function() {
		$.ajaxSetup({
            cache : false
        })
	});
	$("#submit").click(function() {
		$("#myManage").triggerHandler("taijiModalPost", [ $("#editForm"), {
			table : "edit"
		} ]);
	});
</script>
<script type="text/javascript">
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
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">渠道权限管理--修改</h4>
	</div>
	<div class="modal-body">
		<form:form modelAttribute="editModel" id="editForm" name="editForm"
			cssClass="form-horizontal"
			action="${rootUrl }app/administration/agencypermission/edit" method="post">
			<input type="hidden" id="id" name="id" value="${oldInfo.id}" />
			<table class="table table-bordered">
					<tr>
					<th style="font-size:15px;">权限信息</th>
					<th style="font-size:15px;">当前权限信息</th>
					<th style="font-size:15px;">需要修改的权限信息</th>
					</tr>
				<tr>
					<th><label style="font-size:10px;">控制渠道</label></th>
					<th id="agencyName">${oldInfo.agencyName}</th>
					<th></th>
				</tr>
				<tr>
					<th><label  style="font-size:10px;">被控渠道</label></th>
					<th>${oldInfo.permittedAgencyName}</th>
					<td>
									<select
										id="permittedAgencyId"
										name="permittedAgencyId"
										class="taiji_autocomplete form-control"
										data-url="${rootUrl }app/administration/agencypermission/queryByPermissionAgencyName"
										data-placeholder="请选择渠道" style="width: 20em;">
										<option></option>
									</select>
					</td>
					</tr>
					<tr>
							<th><label  style="font-size:10px;">卡类型</label></th>
							<th>${oldInfo.cardTypeInfo}</th>
					<td>
													<select id="cardType" name="cardTypeCode" style="width:220px;height:30px;display:inline-block;font-size:13px" data-style="btn-white" data-width="300px">
													<option value="">-----------请选择卡类型--------------</option>
													<c:forEach items="${cardTypeCode}" var="code">
													<option value="${code.code }">${code.value }</option>
													</c:forEach>
												</select>
					</td>
					</tr>
			</table>
			<div>
			<h5  style="color: red">当前操作会修改为以下权限信息 请确认无误后提交</h5>
											<table class="table table-bordered" >
					<tr>
					<th style="font-size:15px;"></th>
							<th style="font-size:15px;">控制渠道</th>
							<th style="font-size:15px;">被控制渠道</th>
							<th style="font-size:15px;">卡类型</th>
					</tr>
					<tr>
					<th style="font-size:15px;">渠道号</th>
							<th style="font-size:15px;" id="agencyIdInfo"> ${agencyId}</th>
							<th style="font-size:15px;" id="permittedAgencyInfo"></th>
							<th style="font-size:15px;" id="cardTypeInfo"></th>
					</tr>
			</table>
			
			</div>
		</form:form>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a> <a
			href="#" class="btn btn-sm btn-success" id="submit">保存</a>
	</div>
<script type="text/javascript">
var agencyIdInfo =$("#agencyIdInfo").text();
var agencyName =$("#agencyName").text();
$("#agencyIdInfo").text(agencyName+"-"+agencyIdInfo);

</script>
</body>
</html>