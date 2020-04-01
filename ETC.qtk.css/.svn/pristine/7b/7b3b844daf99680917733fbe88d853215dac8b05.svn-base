<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>
		<c:forEach var="type" items="${TradeType}">
			<c:if test="${fn:escapeXml(type eq vo.tradeType)}">${type.value}</c:if>
		</c:forEach>
	</td>
	<td>${fn:escapeXml(vo.channelId)}</td>
	<td>${fn:escapeXml(fn:replace(vo.tradeTime,'T',' '))}</td>
	<td>${fn:escapeXml(vo.preBalance/100)}</td>
	<td>${fn:escapeXml(vo.tradeFee/100)}</td>
	
	<td>
		<c:if test="${fn:escapeXml(vo.tradeType eq 'ACCOUNT_CHARGE')}">${fn:escapeXml((vo.preBalance+vo.tradeFee)/100)}</c:if>
		<c:if test="${fn:escapeXml(vo.tradeType eq 'ACCOUNT_REVERSAL')}">${fn:escapeXml((vo.preBalance-vo.tradeFee)/100)}</c:if>
		<c:if test="${fn:escapeXml(vo.tradeType eq 'ACCOUNT_CONSUME')}">${fn:escapeXml((vo.preBalance-vo.tradeFee)/100)}</c:if>
		<c:if test="${fn:escapeXml(vo.tradeType eq 'ACCOUNT_SUPPLYCHARGE')}">${fn:escapeXml((vo.preBalance+vo.tradeFee)/100)}</c:if>
	</td>
	
	<!-- <td>
		<a id="charge" href="#" class="taiji_modal {width:550,height:600} taiji_acl">充值</a>
	</td> -->
</tr>
