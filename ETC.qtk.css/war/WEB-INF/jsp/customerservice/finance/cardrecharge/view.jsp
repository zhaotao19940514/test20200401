<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">网点详情</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal">
	<table class="table table-bordered table-striped">
		<tr>
			<td>
				<label class="control-label">网点编号</label>
			</td>
			<td>
				<label class="control-label">${pageModel.serviceHallId}</label>
			</td>
			<td>
				<label class="control-label">网点名称</label>
			</td>
			<td>
				<label class="control-label">${pageModel.name}</label>
			</td>
		</tr>
	</table>
</form:form>
	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal" id="closeBtn">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">确定</a>
</div>

</body>
</html>