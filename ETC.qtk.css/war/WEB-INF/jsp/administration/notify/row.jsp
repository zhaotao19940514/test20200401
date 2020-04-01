<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(vo.title)}</td>
	<%-- <td>${fn:escapeXml(vo.status)}</td> --%>
	<td>
	<c:if test="${vo.status =='1'}">是</c:if>
	<c:if test="${vo.status =='0'}">否</c:if>
	</td>
	<td>${fn:escapeXml(vo.releaseDate)}</td>
	<td>${fn:escapeXml(vo.createTime)}</td>
	<%-- <td>${fn:escapeXml(vo.zd)}</td>  --%>
	<td>
	<c:if test="${vo.zd ==1}">是</c:if>
	<c:if test="${vo.zd ==0}">否</c:if>
	</td>
	<td>${fn:escapeXml(vo.topTime)}</td>
	<td>${fn:escapeXml(vo.name)}</td>
	<td><a href="${rootUrl }app/administration/notify/edit/${vo.id}"
		class="taiji_modal taiji_acl btn  btn-white btn-small">修改</a> <a
		href="${rootUrl }app/administration/notify/delete/${vo.id}"
		confirm_message="确定要删除吗?"
		class="taiji_remove  taiji_acl btn btn-white btn-small">删除</a> <a
		href="${rootUrl }app/administration/notify/view/${vo.id}"
		class="taiji_modal_lg taiji_acl btn btn-white btn-small">详细信息</a>
		<c:if test="${vo.zd ==1}"><a href="${rootUrl }app/administration/notify/qxzd/${vo.id}"
		class="taiji_operate taiji_acl btn  btn-white btn-small">取消置顶</a></c:if>
		<c:if test="${vo.zd ==0}"><a href="${rootUrl }app/administration/notify/zd/${vo.id}"
		class="taiji_operate taiji_acl btn  btn-white btn-small">置顶</a></c:if>
		</td>
</tr>
