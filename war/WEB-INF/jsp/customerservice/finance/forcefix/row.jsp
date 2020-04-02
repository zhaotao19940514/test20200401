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
		 <a id="show"   href="${rootUrl }app/customerservice/finance/forcefix/info/${vo.chargeId}" 
		 class="taiji_modal_lg taiji_acl btn  btn-success " >强制修复</a>
		 <a  href="${rootUrl }app/customerservice/finance/forcefix/showPhoto/${vo.chargeId}"  
    class="taiji_modal_lg taiji_acl btn  btn-success ">查看图片</a>
	</td>
</tr>
