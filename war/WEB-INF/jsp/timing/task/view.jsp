<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function(){
	});
</script>
</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">定时任务--查看</h4>
</div>
<div class="modal-body">
<table class="table table-bordered">
		  <tr>
			<th colspan="10" style="color: gray; width: 100%; text-align: center;">定时任务信息</th>
		</tr>
		<tr>
			<th>任务名称：</th>
			<td>${fn:escapeXml(pageModel.name)}</td>
			<th>状态：</th>
			<td>${pageModel.status.value}</td>
		</tr>
		<tr>
			<th>提交人：</th>
			<td>${fn:escapeXml(pageModel.user.name)}</td>
			<th>提交时间：</th>
			<td><javatime:format value="${pageModel.creatTime}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
		</tr>
		<tr>
			<th>任务：</th>
			<td>
			${pageModel.taskType.value}（${pageModel.job}）
			</td>
			<th>执行时间：</th>
			<td><javatime:format value="${pageModel.startTime}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
		</tr>
		<tr>
		</tr>
</table>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
	
</body>
</html>