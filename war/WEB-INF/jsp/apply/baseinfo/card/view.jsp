<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function(){
		$.ajaxSetup ({ cache: false});
		$("#viewTable").taiji();
	});
</script>
</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">卡详情</h4>
</div>
<div class="modal-body">
<table id="viewTable" class="table table-bordered">
		<tr>
			<th colspan="6" style="color: gray; width: 100%; text-align: center;">卡信息</th>
		</tr>
		<tr>
			<th>卡余额（元）：</th>
			<td>
			<%--业务人员一直以这个金额当做退款金额的对比 --%>
				<%-- <c:choose>
					<c:when test="${fn:escapeXml(pageModel.balance/100) eq 0.0}">——</c:when>
					<c:when test="${fn:substring(fn:escapeXml(pageModel.cardType), 0, 1) eq 1}">——</c:when>
					<c:otherwise>${fn:escapeXml(pageModel.balance/100)}</c:otherwise>
				</c:choose> --%>
			</td>
			<th>卡编号：</th>
			<td>${fn:escapeXml(pageModel.cardId)}</td>
			<th>客户编号：</th>
			<td>${fn:escapeXml(pageModel.userId)}</td>
		</tr>
		<tr>
			<th>车辆编号：</th>
			<td>${fn:escapeXml(pageModel.vehicleId)}</td>
			<th>卡启用时间：</th>
			<td>${fn:replace(fn:escapeXml(pageModel.enableTime),'T', ' ')}</td>
			<th>卡到期时间：</th>
			<td>${fn:replace(fn:escapeXml(pageModel.expireTime),'T', ' ')}</td>
		</tr>
		<tr>
			<th>卡类型：</th>
			<td colspan="5">
				<c:forEach items="${cardTypes}" var='cdtp'>
					<c:if test="${cdtp.code eq pageModel.cardType}">${cdtp.value}</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>卡型号：</th>
			<td>${fn:escapeXml(pageModel.model)}</td>
			<th>卡网络编号：</th>
			<td>${fn:escapeXml(pageModel.netId)}</td>
			<th>渠道：</th>
			<td>
				<c:forEach items="${agencys}" var='agcy'>
					<c:if test="${agcy.agencyId eq pageModel.agencyId}">${agcy.name}</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>开卡渠道编号：</th>
			<td><a href="${rootUrl }app/apply/baseinfo/commonQuery/servicehallIdView/${pageModel.channelId }" class="taiji_modal_lg taiji_acl">${fn:escapeXml(pageModel.channelId)}</a></td>
			<th>卡状态：</th>
			<td colspan="3">
				<c:forEach items="${cardUploadStatus}" var='type'>
					<c:if test="${type.code eq pageModel.cardStatus}">${type.value}</c:if>
				</c:forEach>
			</td>
		</tr>
</table>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
	
</body>
</html>