<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${voStatus.index + 1}</td>
	<td>
		<a href="${rootUrl }app/apply/emergency/vehiclemanagement/viewInfo/${vo.id}" class="taiji_modal_lg taiji_acl">${fn:escapeXml(vo.vehicleId)}</a>
	</td>
	<td>${vo.vehiclePlate}</td>
	<td>
		<c:forEach items="${vehiclePlateColorTypes}" var='vpct'>
			<c:if test="${vpct.typeCode eq vo.vehiclePlateColor}">${vpct.value}</c:if>
		</c:forEach>
	</td>
	<td>
		<c:forEach items="${vehicleTypes}" var='vclt'>
			<c:if test="${vclt.typeCode eq vo.type}">${vclt.value}</c:if>
		</c:forEach>
	</td>
	<td>
		<c:if test="${vo.emergencyFlag eq 0}">否</c:if>
		<c:if test="${vo.emergencyFlag eq 1}">是</c:if>
	</td>
	<td>
		<%-- <c:if test="${11 eq vo.type || 12 eq vo.type || 13 eq vo.type || 14 eq vo.type || 15 eq vo.type}">
			<c:if test="${!vo.hasCard}">
				<a href="${rootUrl }app/apply/emergency/equipmentissue/issuePackage/${vo.id}" class="taiji_modal_lg taiji_acl">发行套餐</a>
				<span>|</span>
			</c:if>
		</c:if>
		<c:if test="${!(11 eq vo.type || 12 eq vo.type || 13 eq vo.type || 14 eq vo.type || 15 eq vo.type)}">
		</c:if> --%>
		<c:if test="${!vo.hasObu || !vo.hasCard}">
			<a href="${rootUrl }app/apply/emergency/equipmentissue/issuePackage/${vo.id}" class="taiji_modal_lg taiji_acl">发行套餐</a>
			<span>|</span>
		</c:if>
		<c:if test="${!vo.hasCard}">
			<a href="${rootUrl }app/apply/emergency/equipmentissue/openCard/${vo.id}" class="taiji_modal_lg taiji_acl">发卡</a>
			<span>|</span>
		</c:if>
		<%-- <c:if test="${!(11 eq vo.type || 12 eq vo.type || 13 eq vo.type || 14 eq vo.type || 15 eq vo.type)}">
			<c:if test="${!vo.hasObu && !vo.hasCard}">
				<span>|</span>
			</c:if>
		</c:if> --%>
		<c:if test="${!vo.hasObu}">
			<a href="${rootUrl }app/apply/emergency/equipmentissue/openOBU/${vo.id}" class="taiji_modal_lg taiji_acl">发OBU</a>
			<span>|</span>
		</c:if>
		
		<a href="${rootUrl }app/apply/emergency/equipmentissue/returnReceipt/${vo.id}" target="_blank" class="taiji_acl">打印回执</a>
	</td>
</tr>
