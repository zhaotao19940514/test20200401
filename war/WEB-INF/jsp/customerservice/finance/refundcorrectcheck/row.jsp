<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>
		 <c:forEach var="re" items="${RefundDetailType}">
			<c:if test="${fn:escapeXml(re eq vo.refundType)}">${re.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:escapeXml(vo.cancelTime)}</td>
	<td>${fn:escapeXml(vo.accountCardBalance/100)}</td>
	<td>${fn:escapeXml(vo.againAccountCardBalance/100)}</td>
	<td>${fn:escapeXml(vo.cardBalance/100)}</td>
	<td>${fn:escapeXml(vo.postBalance/100)}</td>
	<td>${fn:escapeXml(vo.minumsValue/100)}</td>
	<td>${fn:escapeXml(vo.refundBalance/100)}</td>
</tr>
