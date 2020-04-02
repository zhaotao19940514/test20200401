<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td> ${fn:escapeXml(vo.fileName)} </td>
	<td> ${fn:escapeXml(vo.submitInBlackAmount)} </td>
	<td> ${fn:escapeXml(vo.inBlackAmount)} </td>
	<td> ${fn:escapeXml(vo.submitOutBlackAmount)} </td>
	<td> ${fn:escapeXml(vo.outBlackAmount)} </td>
</tr>
