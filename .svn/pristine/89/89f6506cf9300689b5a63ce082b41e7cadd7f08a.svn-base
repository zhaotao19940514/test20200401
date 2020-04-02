<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td> ${fn:escapeXml(vo.serviceHallName)} </td>
 	<td> ${fn:escapeXml(vo.cardCount)} </td> 
 	<td> ${fn:escapeXml(vo.obuCount)} </td>
 	<td> ${fn:escapeXml(vo.cardFee)} </td> 
 	<td> ${fn:escapeXml(vo.cardFee*30)} </td> 
 	<td> ${fn:escapeXml(vo.obuFee)} </td> 
 	<td> ${fn:escapeXml(vo.obuFee*200)} </td>  
 	<td> ${fn:escapeXml(vo.amount)} </td>	
 	<td> 
 	<c:if test="${fn:escapeXml(not empty vo.amountFix)}">
 	<a href="${rootUrl}app/customerservice/report/financialstatement/view/${vo.channelId}/${vo.startDate}/${vo.endDate}" class="taiji_modal btn btn-sm btn-primary">${fn:escapeXml(vo.amountFix)}</a>
 	</c:if>
 	 </td>		
	<td> ${fn:escapeXml(vo.accountAmount-vo.accountReversal)} </td>
	<td> ${fn:escapeXml(vo.accountConsume-vo.accountSupplyCharge)} </td>	
	<td> ${fn:escapeXml(vo.cardReplacementCount)}
	<td> ${fn:escapeXml(vo.refund/100)}
	<%-- <td> ${fn:escapeXml(vo.cardReplacementCount)} </td>	
	<td> ${fn:escapeXml(vo.obuReplacementCount)} </td>		
	<td> ${fn:escapeXml(vo.refund)} </td> --%>
	<td> ${fn:escapeXml(vo.amount+vo.accountAmount-vo.accountReversal-vo.refund/100)} </td>	
</tr>
