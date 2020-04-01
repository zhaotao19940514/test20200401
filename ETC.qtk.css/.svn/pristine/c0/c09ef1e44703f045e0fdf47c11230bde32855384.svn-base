<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${voStatus.count}</td>
	<td>${fn:escapeXml(vo.vehicleId)}</td>
	<td>${fn:escapeXml(vo.customerId)}</td>
	<td>${fn:escapeXml(vo.customerInfo.customerName)}</td>
	<td>
		<c:forEach items="${customerIDTypes}" var='ctit'>
			<c:if test="${ctit.typeCode eq fn:escapeXml(vo.customerInfo.customerIdType)}">${ctit.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:escapeXml(vo.customerInfo.customerIdNum)}</td>
	<%-- <td>${fn:escapeXml(vo.ownerName)}</td> --%>
	<td>${fn:escapeXml(vo.vehiclePlate)}</td>
	<td>
		<c:forEach items="${vehiclePlateColorTypes}" var='vpct'>
			<c:if test="${vpct.typeCode eq fn:escapeXml(vo.vehiclePlateColor)}">${vpct.value}</c:if>
		</c:forEach>
	</td>
	<td>
		<c:forEach items="${vehicleTypes}" var='vclt'>
			<c:if test="${vclt.typeCode eq fn:escapeXml(vo.type)}">${vclt.value}</c:if>
		</c:forEach>
	</td>
	<td>
		<fmt:formatDate value="${vo.updateTime.getTime()}" pattern="yyyy/MM/dd HH:mm:ss" /> 
	</td>
	<td>
		<c:choose>
			<c:when test="${!vo.hasObu}">
				<a href="${rootUrl}app/customerservice/obu/transfer/edit/${vo.vehicleId}" class="taiji_modal_lg taiji_acl m-r-10">过户</a>
			</c:when>
			<c:otherwise>
				<a href="javaScript:void(0);" id="oTransferBtnOff" class="taiji_acl m-r-10" style="opacity: 0.2">过户</a>
			</c:otherwise>
		</c:choose>
	</td>
</tr>
