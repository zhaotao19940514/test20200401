<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td> <a href="${rootUrl }app/apply/baseinfo/commonQuery/staffIdView/${vo.staffId }" class="taiji_modal_lg taiji_acl">${fn:escapeXml(vo.staffId)}</a> </td>
	<td> ${fn:escapeXml(vo.tradeTime)} </td>
	<td>
	    <c:if test="${vo.type eq 1}">充值</c:if>
		<c:if test="${vo.type eq 2}">冲正</c:if>
		<c:if test="${vo.type eq 3}">开户</c:if>
		<c:if test="${vo.type eq 4}">销户</c:if>
		<c:if test="${vo.type eq 5}">预销户</c:if>
	</td>
 	<td> ${fn:escapeXml(vo.cardId)} </td> 
 	<td> ${fn:escapeXml(vo.vehiclePlate)} </td>	
	<td> 
	    <c:if test="${vo.cardType eq 1}">记账卡</c:if>
	    <c:if test="${vo.cardType eq 2}">储值卡</c:if>
	</td>
	<td> 
	    <c:if test="${!(vo.receiveMoney eq 0)}">
	    	<fmt:formatNumber type="number" value="${vo.receiveMoney/100}" pattern="########.##" minFractionDigits="2"/>
		</c:if>
		<c:if test="${vo.receiveMoney eq 0}">0</c:if>
	 </td>	
	<td> 
		<c:if test="${!(vo.amountReceivable eq 0)}">
	    	<fmt:formatNumber type="number" value="${vo.amountReceivable/100}" pattern="########.##" minFractionDigits="2"/>
		</c:if>
		<c:if test="${vo.amountReceivable eq 0}">0</c:if>
	</td>
	<td> ${fn:escapeXml(vo.customerIdNum)} </td>
</tr>
