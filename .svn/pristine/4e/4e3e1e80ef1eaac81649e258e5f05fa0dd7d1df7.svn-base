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
		});
		$("#submit").click(function() {
			$("#myManage").triggerHandler("taijiModalPost", [ $("#addForm"), {
				table : "add"
			} ]);
		});
// 		var name = '${addModel.serviceHall.name}';
//         var serviceHallId = '${addModel.serviceHallId}';
//         $("#addForm .chosen-single span").text(name);
//         $("#serviceHallId").append('<option selected="selected" value="'+serviceHallId+'">'+name+'</option>');
// 		$("#showServiceHallId").text("网点编号:" + $("#serviceHallId").val());
		$("#serviceHallId").change(function() {
			$("#showServiceHallId").text("");
			$("#showServiceHallId").text("网点编号:" + $("#serviceHallId").val());
		});
	});
</script>
</head>
<body>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">服务网点设备类型配置</h4>
	</div>
	<div class="modal-body">
		<form:form modelAttribute="addModel" id="addForm" name="addForm"
			cssClass="form-horizontal"
			action="${rootUrl }app/administration/servicehall/deviceconfig/add"
			method="post">
			<form:hidden path="id"/>
			<table class="table table-bordered">
				<tr>
					<th>服务网点:</th>
					<td>
					    <form:select path="serviceHallId"
							cssClass="taiji_autocomplete form-control"
							data-url="${rootUrl }app/administration/servicehall/queryByNameForModal"
							data-placeholder="请选择网点" style="width:35.5em;">
							<form:option value=""></form:option>
						</form:select>
						<div id="showServiceHallId" style="color:blue;"></div>
				    </td>
				</tr>
				<tr>
					<th><label class="control-label">卡设备类型</label></th>
					<td>
					    <form:select path="cardDeviceType" cssClass="form-control">
							<form:option value="">请选择</form:option>
							<form:options itemLabel="value" />
						</form:select>
				    </td>
				</tr>
				<tr>
                    <th><label class="control-label">OBU设备类型</label></th>
                    <td>
                        <form:select path="obuDeviceType" cssClass="form-control">
                            <form:option value="">请选择</form:option>
                            <form:options itemLabel="value" />
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <th><label class="control-label">POS设备类型</label></th>
                    <td>
                        <form:select path="posDeviceType" cssClass="form-control">
                            <form:option value="">请选择</form:option>
                            <form:options itemLabel="value" />
                        </form:select>
                    </td>
                </tr>
			</table>
		</form:form>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a> <a
			href="#" class="btn btn-sm btn-success" id="submit">保存</a>
	</div>

</body>
</html>