<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>${fn:escapeXml(vo.orgCode)}</td>
	<td>${fn:escapeXml(vo.vehiclePlate)}</td>
	<td>
		<c:forEach var="ve" items="${vehiclePlateColorType}">
			<c:if test="${fn:escapeXml(ve.typeCode eq vo.vehiclePlateColor)}">${ve.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:escapeXml(vo.userIdNum)}</td>
	<td>
		<c:forEach var="cu" items="${customerIDType}">
			<c:if test="${fn:escapeXml(cu.typeCode eq vo.userIdType)}">${cu.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>${fn:escapeXml(vo.obuId)}</td>
	<td>${fn:escapeXml(vo.updateTime)}</td>
	<td>
		<c:forEach var="st" items="${bankSignServiceType}">
			<c:if test="${fn:escapeXml(st eq vo.serviceType)}">${st.value}</c:if>
		</c:forEach>
	</td>
	<td>
		<c:forEach var="se" items="${bankSignSendType}">
			<c:if test="${fn:escapeXml(se eq vo.sendStatus)}">${se.value}</c:if>
		</c:forEach>
	</td>
	<td>
		<a rowId ="${vo.id}" " href="javascript:void(0);" class="taiji_acl" id="reSend">重发</a>
	</td>
</tr>
