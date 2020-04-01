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
				<c:when test="${vo.status ne '9'}">
					<c:choose>
					<c:when test="${Integer.valueOf(vo.cardType/100) eq 2&&vo.status ne '4'&&vo.status ne '5'}">
							<a  href="${rootUrl }app/customerservice/card/cancel/edit/${vo.cardId }?type=0&cardType=${Integer.valueOf(vo.cardType/100)}" class="taiji_modal {width:550,height:600} taiji_acl m-r-10">注销</a>
					</c:when>
					<c:when test="${vo.status eq '4'||vo.status eq '5'}">
						<a href="javascript:void(0);" id="cancelBtnOff" class="taiji_acl" style="opacity: 0.2">注销</a>
					</c:when>
					<c:otherwise>
						<a rowJCardId ="${vo.cardId}" rowCardId ="${vo.cardId}" param="${Integer.valueOf(vo.cardType/100)}" href="javascript:void(0);" id="preCancelBtn" class="taiji_acl">注销</a>
							<!-- <a href="javascript:void(0);" id="cancelBtnOff" class="taiji_acl" style="opacity: 0.2">注销</a> -->
					</c:otherwise>
				</c:choose> 
				</c:when>
				<c:otherwise>
					<c:if test="${Integer.valueOf(vo.cardType/100) eq 2&&vo.status eq 9}">
						<a href="javascript:void(0);" id="cancelBtn" rowCardId ="${vo.cardId}" class="taiji_acl">注销</a>
					</c:if>
					<c:if test="${forceFlag eq 1 && Integer.valueOf(vo.cardType/100) eq 1}">
						<a rowJCardId ="${vo.cardId}" param="${Integer.valueOf(vo.cardType/100)}" href="javascript:void(0);" class="taiji_acl" id="forceCancel">强制注销</a>
					</c:if>
				</c:otherwise>
			</c:choose>
	</td>
</tr>
