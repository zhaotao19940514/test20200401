<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
	<style type="text/css">
		table th{
			width:8em;
		}
	</style>
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">通知公告管理--查看</h4>
	</div>
	<div class="modal-body">
		<table class="table table-bordered">
			<tr>
				<th>标题:</th>
				<td>${fn:escapeXml(pageModel.title)}</td>
			</tr>
			<tr>
				<th>发布时间:</th>
				<td>${fn:escapeXml(pageModel.releaseDate)}</td>
			</tr>
			<tr>
			<th>内容:</th>
				<td>
					${fn:escapeXml(pageModel.content)}
				</td>
			</tr>
			<tr>
				 <th>更新时间:</th>
				<td>
						${fn:escapeXml(pageModel.createTime)}
				</td>
			</tr>
		</table>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>
</body>
</html>