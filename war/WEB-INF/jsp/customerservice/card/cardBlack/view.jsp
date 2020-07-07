<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		$(function(){
			$("#submit").click(function(){
				//$("#myForm").trigger("submit");
				$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"edit"}]);
			});

		});
		
		function setUnitValue(id,name){
			$("#unit\\.id").val(id);
			$("#unit\\.name").val(name);
		}
		</script>
	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">黑名单上传日志</h4>
</div>
<div >
<table class="table table-bordered">
<tbody >
			<c:forEach var="li" items="${queryYgzLog}"  varStatus="liStatus">
			<tr>
				<td colspan="2">${liStatus.index + 1}</td></tr>
				<c:forEach var="vo" items="${li}"  varStatus="voStatus">
				<tr>
					<td>
						<c:if test="${voStatus.index==0}">日志ID</c:if>
						<c:if test="${voStatus.index==1}">上传时间</c:if>
						<c:if test="${voStatus.index==2}">上传参数</c:if>
						<c:if test="${voStatus.index==3}">部中心返回结果</c:if>
					</td>
					<td>${fn:escapeXml(vo)}</td>
				</tr>
				</c:forEach>
				
			</c:forEach>
			</tbody>
</table>	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>

</body>
</html>