<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td style="width: 200px">${vo.agencyName }</td>
	<td style="width: 200px">${vo.permittedAgencyName }</td>
	<td style="width: 60px">${vo.cardTypeInfo }</td>
	<td><a href="${rootUrl }app/administration/agencypermission/edit/${vo.id}"
		class="taiji_modal taiji_acl">修改</a> 
		<c:if
			test="${not model.hasChild }">
			<a href="${rootUrl }app/administration/agencypermission/delete/${vo.id}"
				confirm_message="确定要删除吗?" class="taiji_remove  taiji_acl">删除</a>
		</c:if> 
		</td>
</tr>
