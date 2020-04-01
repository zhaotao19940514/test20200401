<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${voStatus.index + 1}</td>
	<td><fmt:formatDate value="${vo.createTime.getTime()}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
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
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>
		<c:if test="${fn:substring(fn:escapeXml(vo.cardType), 0, 1) eq 1}">记账卡</c:if>
		<c:if test="${fn:substring(fn:escapeXml(vo.cardType), 0, 1) eq 2}">储值卡</c:if>
	</td>
	<td>
		<c:forEach items="${cardUploadStatus}" var='cus'>
			<c:if test="${cus.code eq vo.status}">${cus.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:replace(fn:escapeXml(vo.statusChangeTime),'T', ' ')}</td>
	<td><fmt:formatDate value="${vo.updateTime.getTime()}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
	<td>
		<a href="${rootUrl }app/apply/baseinfo/card/view/${vo.cardId}" class="taiji_modal_lg taiji_acl">详情</a>
	</td>
</tr>
