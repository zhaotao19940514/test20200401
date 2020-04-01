<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td>${voStatus.index + 1}</td>
	<td>
		<a href="${rootUrl }app/apply/baseinfo/vehiclemanagement/viewInfo/${vo.id}" class="taiji_modal_lg taiji_acl">${fn:escapeXml(vo.vehicleId)}</a>
	</td>
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
		<a href="${rootUrl }app/apply/baseinfo/vehiclemanagement/edit/${vo.id}" class="taiji_modal_lg taiji_acl">修改</a>
		<a href="${rootUrl }app/apply/baseinfo/vehiclemanagement/del/${vo.id}" class="taiji_modal_lg taiji_acl">删除</a>
	</td>
</tr>
