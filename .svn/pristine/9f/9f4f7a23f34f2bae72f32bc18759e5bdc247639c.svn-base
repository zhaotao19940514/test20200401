<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr
data-tt-id="${vo.id}"
<c:if test="${vo.unitLevel!=0 }">data-tt-parent-id="${vo.parentId}" </c:if>
>
	<td>
	<c:if test="${vo.hasChild }"><i class="text-warning fa fa-folder fa-lg m-r-5"></i>${fn:escapeXml(vo.name)}</c:if>
	<c:if test="${!vo.hasChild }"><i class="text-warning fa fa-file fa-lg m-r-5"></i>${fn:escapeXml(vo.name)}</c:if>
	
	</td>
	<td style="width: 60px">${fn:escapeXml(vo.list)}</td>
	
	<td>
		<a href="${rootUrl}app/acl/unit/add/${vo.id}" class="taiji_modal taiji_acl">添加下级单位</a>
			<a href="${rootUrl}app/acl/unit/edit/${vo.id}" class="taiji_modal  taiji_acl">修改</a>
		<c:if test="${vo.unitLevel!=0 }">
			<a href="${rootUrl}app/acl/unit/delete/${vo.id}" class="taiji_remove {confirm_message:'你确定要删除吗？ '}  taiji_acl">删除</a>
		</c:if>
	</td>
</tr>
