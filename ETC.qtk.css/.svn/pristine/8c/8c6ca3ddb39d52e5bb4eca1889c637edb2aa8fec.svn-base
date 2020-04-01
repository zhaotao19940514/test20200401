<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(vo.packageNum)}</td>
	<td> ${fn:escapeXml(vo.packageName)} </td>
	<td>
			<c:if test="${vo.packageState eq 1}">有效</c:if>
			<c:if test="${!(vo.packageState eq 1)}">无效</c:if>
	</td>
	<td>
		<a href="${rootUrl }app/administration/package/account/edit/${vo.id}" class="taiji_modal taiji_acl">修改</a>	    
	</td>
</tr>
