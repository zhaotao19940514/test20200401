<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<%-- <td>${voStatus.count}</td> --%>
	<td> ${fn:escapeXml(vo.cardId)} </td>
	<td> ${fn:escapeXml(vo.packageName)} </td>
	<td> ${fn:replace(vo.startTime,'T', ' ')}</td>
	<td> ${fn:replace(vo.endTime,'T', ' ')}</td>
	<td>
		<a href="${rootUrl }app/administration/package/change/edit/${vo.id}" class="taiji_modal taiji_acl">修改</a>	    
	</td>
</tr>
