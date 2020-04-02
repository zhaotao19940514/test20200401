<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
	<tr>
		<td  style="width: 60px">${model.list}</td>
		<td style="width: 160px" >${fn:escapeXml(model.name) }</td>
		<td style="width: 60px"> <img src="${rootUrl }images/menu/${model.id}.png" style="height: 16px;width: 16px;"  /></td>
		<td style="width: 80px">${model.menuType.value}</td>
		<td>${fn:escapeXml(model.url) }</td>
		<td style="width: 60px">${model.requestMethod}</td>
		<td class="tdbtn" width="100px;">
		<a href="${rootUrl }app/acl/resource/edit/${model.id}" class="taiji_modal taiji_acl">修改</a>
		<c:if test="${not model.hasChild }">
			<a href="${rootUrl }app/acl/resource/delete/${model.id}" confirm_message="确定要删除吗?" class="taiji_remove  taiji_acl">删除</a>
		</c:if>
		</td>
	</tr>