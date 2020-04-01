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
		$("#districtView").taiji({
			enableAclCheck : true,
			search : {
				autoSearch : false
			}
		});
	});
</script>
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">卡账明细</h4>
	</div>
	<div class="modal-body" id="districtView">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>当前卡内金额</th>
					<th>一月后总消费金额</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${fn:escapeXml(response.presentFee*1/100)}元</td>
					<td>${fn:escapeXml(response.totalFee*1/100)}元</td>
				</tr>
			</tbody>
			<tfoot></tfoot>
		</table>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>流水编号</th>
					<th>入站名</th>
					<th>出站名</th>
					<th>入站时间</th>
					<th>出站时间</th>
					<th>车牌号</th>
					<th>金额</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${response.transactionDetail}" var="vo"
					varStatus="voStatus">
					<tr>
						<td>${fn:escapeXml(vo.listNo)}</td>
						<td>${fn:escapeXml(vo.enStationName)}</td>
						<td>${fn:escapeXml(vo.exStationName)}</td>
						<td>${fn:escapeXml(vo.enTime)}</td>
						<td>${fn:escapeXml(vo.exTime)}</td>
						<td>${fn:escapeXml(vo.vehiclePlate)}</td>
						<td>${fn:escapeXml(vo.fee*1/100)}元</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot></tfoot>
		</table>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>

</body>
</html>