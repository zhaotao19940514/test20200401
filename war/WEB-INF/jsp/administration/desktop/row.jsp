<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td><a href="${rootUrl }app/administration/notify/view/${vo.id}" class="taiji_modal_lg taiji_acl btn btn-small" >${fn:escapeXml(vo.title)}</a></td>
	<td>${fn:escapeXml(vo.releaseDate)}</td>
	<td>
	<c:if test="${vo.zd ==1}">是</c:if>
	<c:if test="${vo.zd ==0}">否</c:if>
	</td>
	<td>${fn:escapeXml(vo.topTime)}</td>
	<td>${fn:escapeXml(vo.name)}</td>
 	<%-- <td>
		<a href="${rootUrl }app/administration/notify/view/${vo.id}" class="taiji_modal_lg taiji_acl btn btn-white btn-small" >详细信息</a>
	</td> --%>
</tr>
