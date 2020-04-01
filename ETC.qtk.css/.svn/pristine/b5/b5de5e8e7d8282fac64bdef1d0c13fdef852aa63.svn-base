<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td>${voStatus.index + 1}</td>
	<td> ${fn:escapeXml(vo.cardId)} </td>
	<td> ${fn:escapeXml(vo.vehiclePlate)} </td>
	<td> ${fn:escapeXml(vo.serialType.value)} </td>
	<td> ${fn:escapeXml(vo.enTime)} </td>
	<td> ${fn:escapeXml(vo.entolllaneName)} </td>
	<td> ${fn:escapeXml(vo.exTime)} </td>
	<td> ${fn:escapeXml(vo.extolllaneName)} </td>	
	<td> 	   
	    	<fmt:formatNumber type="number" value="${vo.preBalance/100}" pattern="########.##" minFractionDigits="2"/>
	</td>
	<td> 	   
	    	<fmt:formatNumber type="number" value="${vo.fee/100}" pattern="########.##" minFractionDigits="2"/>
	 </td>
	 <td> 	   
	    	<fmt:formatNumber type="number" value="${vo.postBalance/100}" pattern="########.##" minFractionDigits="2"/>
	 </td>		
</tr>
