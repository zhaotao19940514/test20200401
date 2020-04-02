<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		
		</script>
	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">描述信息</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel2" id="myForm" name="myForm" cssClass="form-horizontal">
	<table class="table table-bordered table-striped">
		<tr>
			<td>序号</td>
			<td>IP</td>
			<td style="width:100px">描述</td>
			<td>详情</td>
			<td>操作时间</td>
		</tr>
		
			<c:forEach items="${list}" var="li"  varStatus="voStatus">
				<tr>
						<td>${fn:escapeXml(voStatus.count)}</td>
						<td>${li.remoteIp }</td>
						<td>
							<label class="control-label">${li.discription }</label>
						</td>
						<td>${li.record }</td>
						<td>${li.createTime }</td>
				</tr>
			</c:forEach>
		
	</table>
</form:form>
	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal" id="closeBtn">关闭</a>
</div>

</body>
</html>