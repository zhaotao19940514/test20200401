<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">卡账补交明细</h4>
	</div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>补交编号</th>
					<th>卡号</th>
					<th>金额</th>
					<th>补交时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${response.paymentDetail}" var="vo"
					varStatus="voStatus">
					<tr>
						<td>${fn:escapeXml(vo.paymentId)}</td>
						<td>${fn:escapeXml(vo.cardId)}</td>
						<td>${fn:escapeXml(vo.fee*1/100)}元</td>
						<td>${fn:escapeXml(vo.paymentTime)}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot></tfoot>
		</table>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>

</body>
</html>