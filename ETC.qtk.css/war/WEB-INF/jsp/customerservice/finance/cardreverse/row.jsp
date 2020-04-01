<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>${fn:escapeXml(vo.preBalance/100)}</td>
	<td>${fn:escapeXml(vo.rechargeAmount/100)}</td>
	<td>${fn:escapeXml((vo.preBalance+vo.rechargeAmount)/100)}</td>
	<td>${fn:escapeXml(fn:replace(vo.tradeTime,'T',' '))}</td>
	<td style="display: none;">${vo.chargeId}</td>
	<td>
	<c:if test="${voStatus.count ==1}">
		 <a href="${rootUrl }app/customerservice/finance/cardreverse/info/${vo.chargeId}" class="taiji_collapse taiji_acl" >冲正</a>
	</c:if>
	</td>
</tr>
