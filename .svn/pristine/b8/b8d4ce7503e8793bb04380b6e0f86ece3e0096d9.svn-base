<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${voStatus.count}</td>
	<td>${fn:escapeXml(vo.customer.customerName) }</td>
	<td>
		<c:forEach var="cu" items="${CustomerIDType}">
			<c:if test="${fn:escapeXml(cu.typeCode eq vo.customer.customerIdType)}">${cu.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:escapeXml(vo.customer.customerIdNum)}</td>
	<td>${fn:escapeXml(vo.vehicle.vehiclePlate) }</td>
	<td>
		<c:forEach var="ve" items="${vehiclePlateColorType}">
			<c:if test="${fn:escapeXml(ve.typeCode eq vo.vehicle.vehiclePlateColor)}">${ve.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:escapeXml(vo.obuId)}</td>
	<td>
		<c:forEach var="status" items="${ObuUploadStatus}">
			<c:if test="${fn:escapeXml(status.code eq vo.status)}">${status.value}</c:if>
		</c:forEach>
	</td>
	<td>
		<c:choose>
			<c:when test="${vo.status eq '1'||vo.status eq '2'||vo.status eq '3'}">
				<a href="javaScript:void(0);" param= '${vo.obuId}' id="oLossBtn" class="taiji_modal {width:550,height:600} taiji_acl m-r-10">挂失</a>
			</c:when>
			<c:otherwise>
				<a href="javaScript:void(0);" id="oLossBtnOff" class="taiji_acl m-r-10" style="opacity: 0.2">挂失</a>
			</c:otherwise>
		</c:choose>
	</td>
