<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td> 
		<a href="${rootUrl }app/customerservice/report/regionstatistics/edit/${vo.regionName}_${vo.startDate}_${vo.endDate}"  class="taiji_modal_lg">${fn:escapeXml(vo.regionName)} </a>
	</td>
	 <td> ${fn:escapeXml(vo.amount)} </td>
	 <td> ${fn:escapeXml(vo.btAount)} </td>
	<td> ${fn:escapeXml(vo.accountAmount)} </td>
	<td> ${fn:escapeXml(vo.correctAmount)} </td>
	<td>
		<a href="${rootUrl }app/customerservice/report/regionstatistics/refund/${vo.regionName}_${vo.startDate}_${vo.endDate}"  class="taiji_modal_lg">${fn:escapeXml(vo.refund)} </a>
	</td>
	<td> ${fn:escapeXml(vo.payAmount)} </td>
	<td> 
		<fmt:formatNumber value="${fn:escapeXml(vo.sum)}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber> 
	 </td>
</tr>
