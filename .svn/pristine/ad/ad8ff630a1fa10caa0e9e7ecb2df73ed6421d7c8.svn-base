<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${voStatus.index + 1}</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>
		<c:forEach items="${cardBlackType}" var='vts'>
			<c:if test="${vts.code eq vo.type}">${vts.name}</c:if>
		</c:forEach>
	</td>
	<td>${fn:escapeXml(vo.creationTime)}</td>
	<td>${fn:escapeXml(vo.createTime)}<td>
		<a id = "ahyperlinks" href="${rootUrl }app/customerservice/card/cardBlack/view/${vo.cardId}_${vo.createTime}_${vo.status}" class="taiji_modal_lg taiji_acl">详情</a>
	</td>
</tr>
