<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td> ${fn:escapeXml(vo.opTime)} </td>
	<td> ${fn:escapeXml(vo.etcCardId)} </td>
	<td> ${fn:escapeXml(vo.bankCardId)} </td>
	<td>
		<c:if test="${vo.opType eq 'REMOVE'}">解约</c:if>
		<c:if test="${vo.opType eq 'BIND'}">签约</c:if>
	</td>
	<td> ${fn:escapeXml(vo.staffId)} </td>
	<td> ${fn:escapeXml(vo.opOrder)} </td>
	<td> ${fn:escapeXml(vo.opResult)} </td>
	<td>
		<a href="${rootUrl}app/contract/view/${vo.etcCardId}/${vo.bankCardId}" class="taiji_modal taiji_acl">详情</a>
	</td>
</tr>
