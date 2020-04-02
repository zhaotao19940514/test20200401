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
		$("#agencyId").change(function(){
			$("#showAgencyId").text("");
			$("#showAgencyId").text("机构编号:"+$("#agencyId").val());
		});
	});
</script>
</head>
<body>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">服务网点管理--添加</h4>
	</div>
	<div class="modal-body">
		<form:form modelAttribute="addModel" id="addForm" name="addForm"
			cssClass="form-horizontal"
			action="${rootUrl }app/administration/servicehall/add" method="post">
			<table class="table table-bordered">
			    <th colspan="4">服务网点信息</th>
				<tr>
					<th><label class="control-label">服务网点名称:</label></th>
					<td><form:input path="name" cssClass="form-control"
							maxlength="128" placeholder="服务网点名称 必填" /></td>
					<th><label class="control-label">服务网点编号:</label></th>
					<td><form:input path="serviceHallId" cssClass="form-control"
							maxlength="20" placeholder="服务网点编号 必填" /></td>
				</tr>
				<tr>
					<th><label class="control-label">网点联系人名称:</label></th>
					<td><form:input path="contact" cssClass="form-control"
							maxlength="128" placeholder="网点联系人名称 必填" /></td>
					<th><label class="control-label">网点联系人电话:</label></th>
					<td><form:input path="tel" cssClass="form-control"
							maxlength="20" placeholder="网点联系人电话 必填" /></td>
				</tr>
				<tr>
					<th><label class="control-label">起始日期:</label></th>
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
					<th><label class="control-label">结束时间:</label></th>
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
                    <th><label class="control-label">营业时间:</label></th>
                    <td><form:input path="businessHours"
                            cssClass="form-control" maxlength="128" placeholder="营业时间" /></td>
                    <th><label class="control-label">机构编号:</label></th>
                    <td>
                        <form:select path="agencyId" cssClass="form-control">
                            <form:option value="">请选择</form:option>
                            <form:options items="${agencies}" itemLabel="name" itemValue="agencyId"/>
                        </form:select>
                        <div id="showAgencyId" style="color:blue;"></div>
                    </td>
                </tr>
                <tr>
                    <th><label class="control-label">服务项目:</label></th>
                    <td colspan="3"><form:input path="serviceItems"
                            cssClass="form-control" maxlength="256" placeholder="服务项目" /></td>
                </tr>
				<tr>
					<th><label class="control-label">地址:</label></th>
					<td colspan="3"><form:input path="address"
							cssClass="form-control" maxlength="128" placeholder="地址" /></td>
				</tr>
				<tr>
                    <th><label class="control-label">开户行:</label></th>
                    <td><form:input path="bank" cssClass="form-control"
                            maxlength="128" placeholder="开户行"/></td>
                    <th><label class="control-label">开户行账号:</label></th>
                    <td><form:input path="bankAccount" cssClass="form-control"
                            maxlength="11" placeholder="开户行账号"/></td>
                </tr>
                <tr>
                    <th><label class="control-label">开户行地址:</label></th>
                    <td colspan="3"><form:input path="bankAddr"
                            cssClass="form-control" maxlength="128" placeholder="开户行地址"/></td>
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