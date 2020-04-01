<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function(){
		 $(document).on('click.bs.dropdown.data-api', '.dropdown .noclear', function (e) { e.stopPropagation() });
	});
</script>
</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">用户管理--查看</h4>
</div>
<div class="modal-body">
<table class="table table-bordered">
		  <tr>
			<th colspan="4" style="color: gray; width: 100%; text-align: center;">用户信息</th>
		</tr>
		<tr>
			<th>用户名:</th>
			<td>
				<div class="dropdown">
					<a  href="#" data-toggle="dropdown" class="dropdown-toggle">${fn:escapeXml(pageModel.name)}<span class="caret"></span></a>
					<div class="dropdown-menu noclear" style="min-width: 250px">
						<ul class="list-group" style="padding: 10px">
							<li class="list-group-item list-group-item-info">用户名:${fn:escapeXml(pageModel.name)}</li>
							<li class="list-group-item list-group-item-info">登录名:${fn:escapeXml(pageModel.loginName)}</li>
							<li class="list-group-item list-group-item-info">邮箱:${fn:escapeXml(pageModel.email)}</li>
							<li class="list-group-item list-group-item-info">手机:${pageModel.mobile}</li>
							<li class="list-group-item list-group-item-info">状态:${pageModel.status.value}</li>
							<li class="list-group-item list-group-item-info">性别:<c:if test="${pageModel.male}">男</c:if>
									<c:if test="${!pageModel.male}">女</c:if></li>
						
						</ul>
					</div>
				</div>
			</td>
			<th>登录名:</th>
			<td>${fn:escapeXml(pageModel.loginName)}</td>
		</tr>
		<tr>
			<th>单位</th>
			<td colspan="3">${fn:escapeXml(pageModel.unit.name)}</td>
		</tr>
		<tr>
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
		</tr>
		<tr>
			<th>电话:</th>
			<td>${pageModel.tel}</td>
			<th>传真:</th>
			<td>${pageModel.fax}</td>
		</tr>
		<tr>
            <th>工号:</th>
            <td colspan="3">${pageModel.staff.staffId}</td>
        </tr>
</table>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
	
</body>
</html>