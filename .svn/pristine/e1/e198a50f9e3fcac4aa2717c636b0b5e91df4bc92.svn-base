<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${voStatus.count}</td>
	<td>${fn:escapeXml(vo.unit.name)}</td>
	<td>${fn:escapeXml(vo.name)}</td>
	<td><span>${fn:escapeXml(vo.info)}</span></td>
	<td>${vo.list}</td>
	<td  class="tdbtn" width="150px;">
		<c:if test="${vo.system}">系统内置角色</c:if>
		<c:if test="${!vo.system}">
			<a href="${rootUrl }app/acl/role/edit/${vo.id}" class="taiji_modal {width:550,height:300} taiji_acl m-r-10">修改</a>
			<a href="${rootUrl }app/acl/role/delete/${vo.id}" class="taiji_remove {confirm_message:'你确定要删除此角色？ '} taiji_acl m-r-10">删除</a>
			<a href="${rootUrl }app/acl/role/conf/${vo.id}" class="taiji_modal {width:550,height:600} taiji_acl">配置</a>
		</c:if>
	</td>
</tr>
