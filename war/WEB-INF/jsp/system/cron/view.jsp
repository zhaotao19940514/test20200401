<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
	<h4 class="modal-title">调度任务--查看</h4>
</div>
<div class="modal-body">
<table class="table table-bordered">
		  <tr>
			<th colspan="4" style="color: gray; width: 100%; text-align: center;">调度任务信息</th>
		</tr>
		<tr>
			<th>任务名:</th>
			<td>${fn:escapeXml(pageModel.taskName)}</td>
			<th>cron:</th>
			<td>${fn:escapeXml(pageModel.cron)}</td>
		</tr>
		<tr>
			<th>任务分组:</th>
			<td>${fn:escapeXml(pageModel.taskGroup.value)}</td>
			<th>自启动:</th>
			<td>
				<c:if test="${pageModel.autoStart}">是</c:if>
				<c:if test="${!pageModel.autoStart}"><span style="color: red">否</span></c:if>
			</td>
		</tr>
		<tr>
			<th>调度器状态:</th>
			<td>
				<c:if test="${pageModel.running}"><span style="color: green">运行</span></c:if>
				<c:if test="${!pageModel.running}"><span style="color: red">停止</span></c:if>
			</td>
			<th>任务状态:</th>
			<td>
				<c:if test="${pageModel.taskRunning}"><span style="color: green">正在执行</span></c:if>
				<c:if test="${!pageModel.taskRunning}"><span style="color: red">未执行</span></c:if>
			</td>
		</tr>
		<tr>
			<th>备注:</th>
			<td colspan="3">${pageModel.info}</td>
		</tr>
</table>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
	
</body>
</html>