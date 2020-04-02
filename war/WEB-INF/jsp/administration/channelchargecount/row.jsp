<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td> ${fn:escapeXml(vo.handleDate)} </td>
 	<td> ${fn:escapeXml(vo.serviceHallName)} </td> 
 	<td> ${fn:escapeXml(vo.count)} </td>		
 	 <td> 	   
	    	<fmt:formatNumber type="number" value="${vo.amount/100}" pattern="########.##" minFractionDigits="2"/>
	 </td>		
	
</tr>
