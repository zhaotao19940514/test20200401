<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${voStatus.index + 1}</td>
	<td>${fn:escapeXml(vo.customer.customerName)}</td>
	<td>${fn:escapeXml(vo.vehicle.vehiclePlate)}</td>
	<td>
		<c:forEach items="${vehiclePlateColorTypes}" var='vpct'>
			<c:if test="${vpct.typeCode eq vo.vehicle.vehiclePlateColor}">${vpct.value}</c:if>
		</c:forEach>
	</td>
	<td>
		<c:forEach items="${vehicleTypes}" var='vts'>
			<c:if test="${vts.typeCode eq vo.vehicle.type}">${vts.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:escapeXml(vo.obuId)}</td>
	<td>
		<c:forEach items="${agencys}" var='agnc'>
			<c:if test="${agnc.agencyId eq fn:substring(fn:escapeXml(vo.registeredChannelId), 0, 11)}">${agnc.name}</c:if>
		</c:forEach>
	</td>
	<td>
		<c:forEach items="${obuUploadStatus}" var='ous'>
			<c:if test="${ous.code eq vo.status}">${ous.value}</c:if>
		</c:forEach>
	</td>
	<td>
		<a href="${rootUrl }app/apply/baseinfo/obu/view/${vo.obuId}" class="taiji_modal_lg taiji_acl">详情</a>
	</td>
</tr>
