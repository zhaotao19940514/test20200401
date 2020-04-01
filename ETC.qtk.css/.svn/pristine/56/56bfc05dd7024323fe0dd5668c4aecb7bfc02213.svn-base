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
	<h4 class="modal-title">OBU详情</h4>
</div>
<div class="modal-body">
<table id="viewTable" class="table table-bordered">
		<tr>
			<th colspan="6" style="color: gray; width: 100%; text-align: center;">客户信息</th>
		</tr>
		<tr>
			<th>客户名称：</th>
			<td>${fn:escapeXml(pageModel.customer.customerName)}</td>
			<th>证件类型：</th>
			<td>
				<c:forEach items="${types}" var='type'>
					<c:if test="${type.typeCode eq pageModel.customer.customerIdType}">${type.value}</c:if>
				</c:forEach>
			</td>
			<th>证件号码：</th>
			<td>${fn:escapeXml(pageModel.customer.customerIdNum)}</td>
		</tr>
		<tr>
			<th>客户编号：</th>
			<td>${fn:escapeXml(pageModel.customer.customerId)}</td>
			<th>联系号码：</th>
			<td>${fn:escapeXml(pageModel.customer.tel)}</td>
			<th>联系地址：</th>
			<td>${fn:escapeXml(pageModel.customer.address)}</td>
		</tr>
		<tr>
			<th colspan="6" style="color: gray; width: 100%; text-align: center;">OBU信息</th>
		</tr>
		<tr>
			<th>OBU编号：</th>
			<td>${fn:escapeXml(pageModel.obuId)}</td>
			<th>渠道：</th>
			<td>
				<c:forEach items="${agencys}" var='agnc'>
					<c:if test="${agnc.agencyId eq fn:substring(fn:escapeXml(pageModel.registeredChannelId), 0, 11)}">${agnc.name}</c:if>
				</c:forEach>
			</td>
			<th>型号：</th>
			<td>${fn:escapeXml(pageModel.model)}</td>
		</tr>
		<tr>
			<th>启用时间：</th>
			<td>${fn:replace(fn:escapeXml(pageModel.enableTime),'T', ' ')}</td>
			<th>到期时间：</th>
			<td>${fn:replace(fn:escapeXml(pageModel.expireTime),'T', ' ')}</td>
			<th>状态：</th>
			<td>
				<c:forEach items="${obuUploadStatus}" var='ous'>
					<c:if test="${ous.code eq pageModel.status}">${ous.value}</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>注册渠道编号：</th>
			<td><a href="${rootUrl }app/apply/baseinfo/commonQuery/servicehallIdView/${pageModel.registeredChannelId }" class="taiji_modal_lg taiji_acl">${fn:escapeXml(pageModel.registeredChannelId)}</a></td>
			<th>注册时间：</th>
			<td>${fn:replace(fn:escapeXml(pageModel.registeredTime),'T', ' ')}</td>
			<th>状态变更时间：</th>
			<td>${fn:replace(fn:escapeXml(pageModel.statusChangeTime),'T', ' ')}</td>
		</tr>
		<tr>
			<th colspan="6" style="color: gray; width: 100%; text-align: center;">车辆信息</th>
		</tr>
		<tr>
			<th>车辆编号：</th>
			<td>${fn:escapeXml(pageModel.vehicle.vehicleId)}</td>
			<th>车牌号：</th>
			<td>${fn:escapeXml(pageModel.vehicle.vehiclePlate)}</td>
			<th>车牌颜色：</th>
			<td>
				<c:forEach items="${vehiclePlateColorTypes}" var='vpct'>
					<c:if test="${vpct.typeCode eq pageModel.vehicle.vehiclePlateColor}">${vpct.value}</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>所有人名称：</th>
			<td>${fn:escapeXml(pageModel.vehicle.ownerName)}</td>
			<th>所有人证件类型：</th>
			<td>
				<c:forEach items="${types}" var='type'>
					<c:if test="${type.typeCode eq pageModel.vehicle.ownerIdType}">${type.value}</c:if>
				</c:forEach>
			</td>
			<th>所有人证件号：</th>
			<td>${fn:escapeXml(pageModel.vehicle.ownerIdNum)}</td>
		</tr>
		<tr>
			<th>所有人Tel：</th>
			<td>${fn:escapeXml(pageModel.vehicle.ownerTel)}</td>
			<th>核定载人数：</th>
			<td>${fn:escapeXml(pageModel.vehicle.approvedCount)}</td>
			<th>核定载重kg：</th>
			<td>${fn:escapeXml(pageModel.vehicle.permittedWeight)}</td>
		</tr>
</table>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
	
</body>
</html>