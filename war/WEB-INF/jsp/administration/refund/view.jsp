<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function() {
		$.ajaxSetup({
			cache : false
		});
		$("#viewTable").taiji();
	});
</script>
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">退费详情</h4>
	</div>
	<div class="modal-body">
		<table id="viewTable" class="table table-bordered">
			<c:if test="${pageModel.status == 1}">
				<tr>
					<th>操作工号：</th>
					<td>${fn:escapeXml(pageModel.userId)}</td>
					<th>银行回款状态：</th>
					<c:if test="${pageModel.pay==0}">
						<td>未回款</td>
					</c:if>
					<c:if test="${pageModel.pay==1}">
						<td>已回款</td>
					</c:if>
					
				</tr>
				<tr>
					<th>操作时间：</th>
					<td>${fn:escapeXml(pageModel.updateTime)}</td>
					<th>退款状态：</th>
					<c:if test="${pageModel.status==0}">
						<td>未处理</td>
					</c:if>
					<c:if test="${pageModel.status==1}">
						<td>已处理</td>
					</c:if>
				</tr>

			</c:if>
			<tr>
				<th>流水号：</th>
				<td>${fn:escapeXml(pageModel.transientListno)}</td>
				<th>卡号：</th>
				<td>${fn:escapeXml(pageModel.cardId)}</td>
			</tr>
			<tr>
				
				<th>流水金额：</th>
				<td>${fn:escapeXml(pageModel.fee)}</td>
				<th>实际退费金额：</th>
				<td>${fn:escapeXml(pageModel.refundFee)}</td>
			</tr>
			<tr>
				<th>车牌号：</th>
				<td>${fn:escapeXml(pageModel.vehiclePlate)}</td>
				<th>退费类型：</th>
				<td>${fn:escapeXml(pageModel.refundType.value)}</td>
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
			

		</table>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>

</body>
</html>