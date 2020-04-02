<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${voStatus.index + 1}</td>
	<td>${fn:escapeXml(vo.vehicleId)}</td>
	<td>${fn:escapeXml(vo.customerId)}</td>
	<td>${fn:escapeXml(vo.customerInfo.customerName)}</td>
	<td>
		<c:forEach items="${customerIDTypes}" var='ctit'>
			<c:if test="${ctit.typeCode eq vo.customerInfo.customerIdType}">${ctit.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:escapeXml(vo.customerInfo.customerIdNum)}</td>
	<%-- <td>${fn:escapeXml(vo.ownerName)}</td> --%>
	<td>${fn:escapeXml(vo.vehiclePlate)}</td>
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
		<fmt:formatDate value="${vo.updateTime.getTime()}" pattern="yyyy/MM/dd HH:mm:ss" /> 
	</td>
	<td>
		<%-- <a href="${rootUrl }app/apply/quickapply/equipmentissue/openCard/${vo.vehicleId}" class="taiji_modal_lg taiji_acl">发卡</a>
		<span>|</span>
		<c:if test="${1 eq vo.type || 2 eq vo.type || 3 eq vo.type || 4 eq vo.type}">
			<a href="${rootUrl }app/apply/quickapply/equipmentissue/openOBU/${vo.vehicleId}" class="taiji_modal_lg taiji_acl">发OBU</a>
			<span>|</span>
		</c:if> --%>
		<%-- <c:if test="${!(1 eq vo.type || 2 eq vo.type || 3 eq vo.type || 4 eq vo.type)}">
			<c:if test="${!vo.hasCard}">
				<a href="${rootUrl }app/apply/quickapply/equipmentissue/issuePackage/${vo.id}" class="taiji_modal_lg taiji_acl">发行套餐</a>
				<span>|</span>
			</c:if>
		</c:if>
		<c:if test="${1 eq vo.type || 2 eq vo.type || 3 eq vo.type || 4 eq vo.type}">
		</c:if> --%>
		<c:if test="${!vo.hasObu || !vo.hasCard}">
			<a href="${rootUrl }app/apply/quickapply/equipmentissue/issuePackage/${vo.id}" class="taiji_modal_lg taiji_acl">发行套餐</a>
			<span>|</span>
		</c:if>
		<c:if test="${!vo.hasCard}">
			<a href="${rootUrl }app/apply/quickapply/equipmentissue/openCard/${vo.id}" class="taiji_modal_lg taiji_acl">发卡</a>
			<span>|</span>
		</c:if>
		<%-- <c:if test="${1 eq vo.type || 2 eq vo.type || 3 eq vo.type || 4 eq vo.type}">
		</c:if> --%>
		<c:if test="${!vo.hasObu}">
			<a href="${rootUrl }app/apply/quickapply/equipmentissue/openOBU/${vo.id}" class="taiji_modal_lg taiji_acl">发OBU</a>
			<span>|</span>
		</c:if>
		<a href="${rootUrl }app/apply/quickapply/equipmentissue/returnReceipt/${vo.id}" target="_blank" class="taiji_acl">打印回执</a>
		<span>|</span>
		<a href="${rootUrl }app/apply/baseinfo/vehiclemanagement/edit/${vo.id}" class="taiji_modal_lg taiji_acl">修改</a>
	</td>
</tr>
