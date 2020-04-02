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
			<c:when test="${vo.status eq '1'}">	
				<a href="#" param = '${vo.obuId }' vehicleNum ='${vo.vehicle.vehiclePlate}' vecolor='${vo.vehicle.vehiclePlateColor}' id="rewriteBtn"  href="javascript:void(0)" class="taiji_acl btn  btn-white btn-small">重写OBU</a>
				<a href="${rootUrl }app/customerservice/obu/rewrite/edit/${vo.obuId}" class="taiji_modal taiji_acl btn  btn-white btn-small">重写车牌</a>
			</c:when>
			<c:otherwise>
				<a  id="rewriteBtn1" href="#"  class="taiji_acl" style="opacity: 0.2">重写</a>
			</c:otherwise>
		</c:choose>
		
	</td>
</tr>
