<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td> ${voStatus.index +1} </td>
	<td> ${fn:escapeXml(vo.customerName)} </td>
	<td> ${fn:escapeXml(vo.vehicleId)} </td>
	<td> ${fn:escapeXml(vo.staffId)} </td>
	<td> ${fn:escapeXml(vo.cancelTime)} </td>
	<td> ${fn:escapeXml(vo.channelName)} </td>
</tr>
