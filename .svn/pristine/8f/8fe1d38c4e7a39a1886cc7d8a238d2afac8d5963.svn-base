<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(vo.project.name)}</td>
	<td>${fn:escapeXml(vo.name)}</td>
	<td>${fn:escapeXml(vo.path)}</td>
	<td >
		<a href="${rootUrl }app/template/edit/${vo.id}" class="taiji_modal {width:550,height:300} taiji_acl m-r-10">修改</a>
		<a href="${rootUrl }app/template/delete/${vo.id}" class="taiji_remove {confirm_message:'你确定要删除吗？ '} taiji_acl">删除</a>
		<a href="${rootUrl }app/template/download/${vo.id}" class="taiji_acl" >下载</a>
	</td>
</tr>
