<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td>
	    <c:if test="${vo.type eq 1}">黔通储值卡</c:if>
		<c:if test="${vo.type eq 2}">黔通记账卡</c:if>
		<c:if test="${vo.type eq 3}">建行卡</c:if>
		<c:if test="${vo.type eq 4}">工行卡</c:if>
	</td>
	<td> ${fn:escapeXml(vo.timeStampDate)} </td>
 	<td> ${fn:escapeXml(vo.totalCount)} </td> 	
 	 <td> 	   
	    	<fmt:formatNumber type="number" value="${vo.totalMoney/100}" pattern="########.##" minFractionDigits="2"/>
	 </td>		
	
</tr>
