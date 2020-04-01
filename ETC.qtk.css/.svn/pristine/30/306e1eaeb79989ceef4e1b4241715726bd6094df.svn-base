<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
	$(function() {
		$(".modal-content")
				.attr(
						"style",
						"width:80vw !important;margin-left:-10vw;");
		$("img").attr("style","max-width: 70vw;");
	});
</script>
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
				<th>标题</th>
				<td>${pageModel.title}</td>
			</tr>
			<tr>
				<th>内容</th>
				<td>${pageModel.content}</td>
			</tr>
			<tr>
				<th>更新时间</th>
				<td>${pageModel.createTime}</td>
			</tr> 
			<tr>
				<th>发布时间</th>
				<td>${pageModel.releaseDate}</td>
			</tr>
		</table>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>

	</div>

</body>
</html>