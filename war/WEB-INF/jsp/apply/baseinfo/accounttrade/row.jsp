<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>${fn:escapeXml(vo.customerId)}</td>
	<%-- <td>${fn:escapeXml(vo.tradeType)}</td> --%>
	 <td>
		<c:forEach var="type" items="${TradeType}">
			<c:if test="${fn:escapeXml(type eq vo.tradeType)}">${type.value}</c:if>
		</c:forEach>
	</td> 
	<td>${fn:escapeXml(vo.preBalance/100)}</td>
	<td>${fn:escapeXml(vo.tradeFee/100)}</td>
	<td>${fn:escapeXml(vo.tradeTime)}</td>
</tr>
