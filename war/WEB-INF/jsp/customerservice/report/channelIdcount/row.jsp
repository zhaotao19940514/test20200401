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
 	<td> ${fn:escapeXml(vo.amountFix)} </td>		
	<td> ${fn:escapeXml(vo.accountAmount-vo.accountReversal)} </td>
	<td> ${fn:escapeXml(vo.accountConsume)} </td>
	<td> ${fn:escapeXml(vo.cardReplacementCount)} </td>
	<td> ${fn:escapeXml(vo.refund/100)}
	<td> ${fn:escapeXml(vo.amount+vo.accountAmount-vo.accountReversal-vo.refund/100)} </td>	
</tr>
