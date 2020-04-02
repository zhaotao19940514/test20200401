<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>${fn:escapeXml(vo.preBalance/100)}</td>
	<td>${fn:escapeXml(vo.rechargeAmount/100)}</td>
	<td>${fn:escapeXml((vo.preBalance+vo.rechargeAmount)/100)}</td>
	<td>${fn:escapeXml(fn:replace(vo.tradeTime,'T',' '))}</td>
	<td style="display: none;">${vo.chargeId}</td>
	<td>
		 <a href="${rootUrl }app/customerservice/finance/cardrechargefix/info/${vo.chargeId}" class="taiji_collapse taiji_acl" >查看详情</a>
	</td>
</tr>

