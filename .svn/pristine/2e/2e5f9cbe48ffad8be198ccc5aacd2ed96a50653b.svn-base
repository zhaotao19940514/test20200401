<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">数据对比</h4>
	</div>
	<div class="modal-body" id="dataView">
		<table class="table table-bordered">
			<tr>
				<th colspan="4"
					style="color: gray; width: 100%; text-align: center;">数据对比</th>
			</tr>
			<tr>
				<th></th>
				<th>旧数据</th>
				<th>新数据</th>
			</tr>
			<c:forEach items="params" var="param">
			    <tr>
	                <th>${param.name}</th>
	                <td>${param.old}</td>
	                <td>${param.new}</td>
                </tr>
			</c:forEach>
		</table>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>

</body>
</html>