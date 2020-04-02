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
			var r=confirm("是否继续操作");
		if (r==true)
		{
			$("#myManage").triggerHandler("taijiModalPost", [ $("#myForm"), {
				table : "edit"
			} ]);
		}
		else
		{
			$.Taiji.showWarn("已取消操作");
		}
			
		});

	});
</script>
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">回款操作</h4>
	</div>

	<div class="modal-body">

		<form:form modelAttribute="pageModel" id="myForm" name="myForm"
			cssClass="form-horizontal"
			action="${rootUrl}app/administration/refund/edit" method="post">
			<form:hidden path="id" />
			<div class="form-group">
				<table id="viewTable" class="table table-bordered">
					<tr>
						<th>流水号：</th>
						<td>${fn:escapeXml(pageModel.transientListno)}</td>
						<th>卡号：</th>
						<td>${fn:escapeXml(pageModel.cardId)}</td>
					</tr>
					<tr>
						<th>车牌号：</th>
						<td>${fn:escapeXml(pageModel.vehiclePlate)}</td>
						<th>流水金额：</th>
						<td>${fn:escapeXml(pageModel.fee)}</td>
					</tr>
					<tr>
						<th>进站时间：</th>
						<td>${fn:escapeXml(pageModel.inOptime)}</td>
						<th>进站口：</th>
						<td>${fn:escapeXml(pageModel.inStationName)}</td>
						</tr>
					<tr>
						<th>出站时间：</th>
					
						<td>${fn:escapeXml(pageModel.opTime)}</td>
						<th>出站口：</th>
						<td>${fn:escapeXml(pageModel.exStationName)}</td>
					</tr>
					<tr>
						<th>实际退费金额：</th>
						<td>${fn:escapeXml(pageModel.refundFee)}</td>
						<th>退费类型：</th>
						<td>${fn:escapeXml(pageModel.refundType.value)}</td>
					</tr>

				</table>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
				<label class="col-sm-2 control-label" style = "width:120px;">银行是否回款：</label>
					<form:select path="pay" cssClass="form-control  m-r-5"
						data-style="btn-white" data-width="160px" style = "width:100px;">
						<form:option value="-1">--请选择--</form:option>
						<form:option value="1">是</form:option>
						<form:option value="0">否</form:option>
					</form:select>
				</div>
			</div>
		</form:form>

	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a> <a
			href="#" class="btn btn-sm btn-success" id="submit">确认</a>
	</div>

</body>
</html>