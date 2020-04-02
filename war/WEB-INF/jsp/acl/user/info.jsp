<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<body>
		<table  width="100%"  class="table table-bordered ">
			<tr>
				<th>用户名:</th>
				<td>${fn:escapeXml(pageModel.name)}</td>
				<th>登录名:</th>
				<td>${fn:escapeXml(pageModel.loginName)}</td>
				<th>状态:</th>
				<td>${pageModel.status.value}</td>
				<th>性别:</th>
				<td>
					<c:if test="${pageModel.male}">男</c:if>
					<c:if test="${!pageModel.male}">女</c:if>
				</td>
			</tr>
			<tr>
				<th>邮箱:</th>
				<td>${fn:escapeXml(pageModel.email)}</td>
				<th>手机:</th>
				<td>${pageModel.mobile}</td>
				<th>电话:</th>
				<td>${pageModel.tel}</td>
				<th>传真:</th>
				<td>${pageModel.fax}</td>
			</tr>
			<tr>
			     <th>工号：</th>
			     <td colspan="7">${pageModel.staff.staffId}</td>
			</tr>
		</table>
		
</body>
</html>