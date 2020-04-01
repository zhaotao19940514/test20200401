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
	});
</script>
</head>
<body>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">机构管理--添加</h4>
	</div>
	<div class="modal-body">
		<form:form modelAttribute="addModel" id="addForm" name="addForm"
			cssClass="form-horizontal"
			action="${rootUrl }app/administration/agency/add" method="post">
			<table class="table table-bordered">
			    <th colspan="4">机构信息</th>
				<tr>
					<th><label class="control-label">机构名称</label></th>
					<td><form:input path="name" cssClass="form-control"
							maxlength="128" placeholder="必填" /></td>
					<th><label class="control-label">机构编号</label></th>
					<td><form:input path="agencyId" cssClass="form-control"
							maxlength="11" placeholder="必填" /></td>
				</tr>
				<tr>
					<th><label class="control-label">机构联系人名称</label></th>
					<td><form:input path="contact" cssClass="form-control"
							maxlength="128" placeholder="必填" /></td>
					<th><label class="control-label">机构联系人电话</label></th>
					<td><form:input path="tel" cssClass="form-control"
							maxlength="20" placeholder="必填" /></td>
				</tr>
				<tr>
					<th><label class="control-label">起始日期</label></th>
					<td>
						<div class="input-group">
							<form:input path="startTime"
								readonly="true" cssClass="form-control" />
							<span class="input-group-btn">
								<button type="button" class="btn btn-default"
									onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'});">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
					</td>
					<th><label class="control-label">结束时间</label></th>
					<td><div class="input-group">
							<form:input path="endTime" readonly="true"
								cssClass="form-control" />
							<span class="input-group-btn">
								<button type="button" class="btn btn-default"
									onclick="WdatePicker({el:$dp.$('endTime'),dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'});">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div></td>
				</tr>
				<tr>
					<th>资金渠道:</th>
					<td>
                        <select name="accountId" class="taiji_autocomplete form-control" data-url="${rootUrl }app/administration/agency/queryByNameForModal" data-placeholder="请选择资金渠道" style="width:35.5em;">
                            <option></option>
                        </select>
                    </td>
				</tr>
				<tr>
					<th><label class="control-label">地址</label></th>
					<td colspan="3"><form:input path="address"
							cssClass="form-control" maxlength="128" placeholder="" /></td>
				</tr>
			</table>
			<table class="table table-bordered">
			    <tr>
			        <th colspan="4">老系统兼容信息</th>
			    </tr>
				<tr>
					<th><label class="control-label">老系统接口用户名</label></th>
					<td><form:input path="HGUserName" cssClass="form-control"
							maxlength="64" placeholder="" /></td>
					<th><label class="control-label">老系统接口密码</label></th>
					<td><form:input path="HGPassWord" cssClass="form-control"
							maxlength="64" placeholder="" /></td>
				</tr>
				<tr>
					<th><label class="control-label">老系统流水编号</label></th>
                    <td><form:input path="serialNo" cssClass="form-control"
                            maxlength="64" placeholder="" /></td>
                    <th><label class="control-label">老系统发行方编号</label></th>
                    <td><form:input path="issueNo" cssClass="form-control"
                            maxlength="64" placeholder="" /></td>
				</tr>
				<tr>
					<th><label class="control-label">老系统保证金编号</label></th>
                    <td><form:input path="packageNo" cssClass="form-control"
                            maxlength="64" placeholder="" /></td>
                    <th><label class="control-label">老系统记账编号</label></th>
                    <td><form:input path="accountNo" cssClass="form-control"
                            maxlength="64" placeholder="" /></td>
				</tr>
			</table>
			<table class="table table-bordered">
			     <tr>
                    <th colspan="4">系统设置</th>
                </tr>
			    <tr>
			        <th><label class="control-label">流水目录设置</label></th>
                    <td colspan="3"><form:input path="fileDir" cssClass="form-control"
                            maxlength="256" placeholder="" /></td>
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