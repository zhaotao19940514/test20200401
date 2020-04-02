<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>
	 	<a href="${rootUrl }app/customerservice/finance/cardrecharge/view/${vo.channelId}" class="taiji_modal btn btn-sm btn-primary">${fn:escapeXml(vo.channelId)}</a>
	</td>
	<td>${fn:escapeXml(vo.preBalance/100)}</td>
	<td>${fn:escapeXml(vo.rechargeAmount/100)}</td>
	<td>${fn:escapeXml((vo.preBalance+vo.rechargeAmount)/100)}</td>
	<td>${fn:escapeXml(fn:replace(vo.tradeTime,'T',' '))}</td>
	<td>${fn:escapeXml(vo.chargeId)}</td>
	<td>
		<c:if test="${fn:escapeXml(vo.tradeType eq '0')}">
			<c:if test="${fn:escapeXml(vo.status eq '1')}">现金圈存交易</c:if>
		</c:if>
		<c:if test="${fn:escapeXml(vo.tradeType eq '3')}">
			<c:if test="${fn:escapeXml(vo.status eq '1')}">账户圈存交易</c:if>
		</c:if>
		<c:if test="${fn:escapeXml(vo.tradeType eq '4')}">
			<c:if test="${fn:escapeXml(vo.status eq '1')}">余额补领/退费</c:if>
		</c:if>
		<c:if test="${fn:escapeXml(vo.tradeType eq '5')}">
			<c:if test="${fn:escapeXml(vo.status eq '1')}">补卡额</c:if>
		</c:if>
		<c:if test="${fn:escapeXml(vo.status eq '2')}">圈存交易已被冲正 </c:if>
		<c:if test="${fn:escapeXml(vo.status eq '0')}">半条流水</c:if>
		<c:if test="${fn:escapeXml(vo.status eq '8')}">圈存交易申请强制修复</c:if>
	</td>
	<td>
		<c:if test="${fn:escapeXml(vo.tradeType eq '0')}">线下 </c:if>
		<c:if test="${fn:escapeXml(vo.tradeType eq '1')}">刷卡</c:if>
		<c:if test="${fn:escapeXml(vo.tradeType eq '2')}">第三方支付</c:if>
		<c:if test="${fn:escapeXml(vo.tradeType eq '3')}">账户余额 </c:if>
		<c:if test="${fn:escapeXml(vo.tradeType eq '4')}">退费额度</c:if>
		<c:if test="${fn:escapeXml(vo.tradeType eq '5')}">无需收费</c:if>
	</td>
</tr>
