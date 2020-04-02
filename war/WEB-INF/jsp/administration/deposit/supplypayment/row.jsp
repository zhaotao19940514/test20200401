<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>${fn:escapeXml(vo.customer.customerName)}</td>
	<td>${fn:escapeXml(vo.vehicle.vehiclePlate)}</td>
	<td>
		<c:forEach var="ve" items="${vehiclePlateColorType}">
			<c:if test="${fn:escapeXml(ve.typeCode eq vo.vehicle.vehiclePlateColor)}">${ve.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<c:choose>
		<c:when test="${vo.status eq 1 }">
			<td>黑名单</td>
		</c:when>
		<c:otherwise>
			<td>白名单</td>
		</c:otherwise>
	</c:choose>
	<td>
		<c:choose>
			<c:when test="${Integer.valueOf(vo.cardType/100) eq 1}">记账卡</c:when>
			<c:when test="${Integer.valueOf(vo.cardType/100) eq 2}">储值卡</c:when>
			<c:otherwise>不进行检测</c:otherwise>
		</c:choose>
	</td>
	<td>${fn:escapeXml(vo.accountId/100)}</td>
	<td>${fn:escapeXml(vo.packageId)}</td>
	<td>
		<c:choose>
			<c:when test="${vo.accountId>=50000}">
				<a  href="javascript:void(0);" id="supplyPayBtnOff" class="taiji_acl" style="opacity: 0.2">补交</a>
			</c:when>
			<c:otherwise>
				<a  href="${rootUrl }app/administration/deposit/supplypayment/edit/${vo.cardId }" id="supplyPayBtn" class="taiji_modal {width:550,height:600} taiji_acl m-r-10" >补交</a>
			
			</c:otherwise>
			
		</c:choose>
	</td>
</tr>
