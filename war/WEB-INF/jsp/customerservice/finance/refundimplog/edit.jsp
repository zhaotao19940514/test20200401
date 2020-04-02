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
	<h4 class="modal-title">交易详情</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel1" id="myForm" name="myForm" cssClass="form-horizontal">
	<table class="table table-bordered">
		<tr>
			<td>序号</td>
			<td>卡号</td>
			<td>描述信息</td>
		</tr>
		
			<c:forEach var="li" items="${list}"  varStatus="voStatus">
				<tr>
					<td>${fn:escapeXml(voStatus.count)}</td>
					<td>${li.cardId }</td>
					<td>${li.failMessage }</td>
			  </tr>
			</c:forEach>
	</table>
</form:form>
	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>

</body>
</html>