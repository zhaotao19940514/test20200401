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
		<c:if test="${vo.emergencyFlag eq 0}">否</c:if>
		<c:if test="${vo.emergencyFlag eq 1}">是</c:if>
	</td>
	<td>
		<c:if test="${vo.emergencyFlag eq 0}">
			<a href="${rootUrl }app/apply/baseinfo/emergencyvehicle/emergencytrue/${vo.id}" class="taiji_operate {confirm_message:'确定要标记为应急车辆？', refresh:true}">标记应急</a>
		</c:if>
		<c:if test="${vo.emergencyFlag eq 1}">
			<a href="${rootUrl }app/apply/baseinfo/emergencyvehicle/emergencyfalse/${vo.id}" class="taiji_operate {confirm_message:'确定要取消应急车辆标记？', refresh:true}">取消应急</a>
		</c:if>
	</td>
</tr>
