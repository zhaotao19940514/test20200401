<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	
	<td>${fn:escapeXml(vo.customerName)}</td>
	<td>
		<c:forEach items="${customerIDTypes}" var='ctit'>
			<c:if test="${ctit.typeCode eq vo.customerIdType}">${ctit.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:replace(vo.customerIdNum, fn:substring(vo.customerIdNum, 6, 14), "***")} </td>
	 <td>${fn:replace(vo.bankCardId, fn:substring(vo.bankCardId,5,15), "***")} </td>
	<td>${fn:escapeXml(vo.cancelTime)}</td>
	<td>
		<c:forEach items="${CardRefundDetailType}" var='re'>
			<c:if test="${re.typeCode eq vo.type}">${re.value}</c:if> 
		</c:forEach>
	</td>
	<td>
		<c:choose>
			<c:when test="${vo.refundType eq 'WTJTF'}">
				待确认
			</c:when>
			<c:otherwise>
			<fmt:formatNumber value="${vo.refundBalance/100}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber>
			</c:otherwise>
		</c:choose>
	 </td>
	 <td>
	 	<c:choose>
			<c:when test="${vo.compleBalance==null}">
				————
			</c:when>
			<c:otherwise>
			<fmt:formatNumber value="${vo.compleBalance/100}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber>
			</c:otherwise>
		</c:choose>
	 </td>
	 <%-- <td>
	 	<a  href="javaScript:void(0);" id="revereRefund"  refundCardId ="${vo.cardId}" class="taiji_acl btn  btn-white btn-small" >二次核定</a>
	 </td> --%>
</tr>
