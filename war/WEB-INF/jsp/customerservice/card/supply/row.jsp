<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>${fn:escapeXml(vo.customer.customerName)}</td>
	<td>
		<c:forEach var="cu" items="${CustomerIDType}">
			<c:if test="${fn:escapeXml(cu.typeCode eq vo.customer.customerIdType)}">${cu.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:escapeXml(vo.customer.customerIdNum)}</td>
	<td>${fn:escapeXml(vo.vehicle.vehiclePlate)}</td>
	<td>
		<c:forEach var="ve" items="${vehiclePlateColorType}">
			<c:if test="${fn:escapeXml(ve.typeCode eq vo.vehicle.vehiclePlateColor)}">${ve.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>
		<c:forEach var="card" items="${CardUploadStatus}">
			<c:if test="${fn:escapeXml(card.code eq vo.status)}">${card.value}</c:if>
		</c:forEach>
	</td>
	<td>
		<c:choose>
			<c:when test="${Integer.valueOf(vo.cardType/100) eq 1}">记账卡</c:when>
			<c:when test="${Integer.valueOf(vo.cardType/100) eq 2}">储值卡</c:when>
			<c:otherwise>不进行检测</c:otherwise>
		</c:choose>
	</td>
	<td>
			<c:choose>
				<c:when test="${vo.status eq '6'&&Integer.valueOf(vo.cardType/100) eq 2}">
					<a href="${rootUrl }app/customerservice/card/supply/edit/${vo.cardId}"  class="taiji_modal  {width:550,height:600} taiji_acl">补卡</a>
				</c:when>
				<c:otherwise>
					<a href="javascript:void(0);" id="cSupplyBtnOff"  class="taiji_acl m-r-10" style="opacity: 0.2">补卡</a>
				</c:otherwise>
			</c:choose>
	</td>
</tr>
<script>

</script>