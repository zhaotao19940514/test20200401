<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		$(function(){
			$.ajaxSetup ({ cache: false}); 
			$("#submit").click(function(){
				$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"none"}]);
			});

		});
		</script>
	</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">单位管理--添加</h4>
</div>

<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal"  action="${rootUrl}app/acl/unit/add" method="post">
		<form:hidden path="id"/>
		<form:hidden path="parentId"/>
	<div class="form-group">
		<label  class="col-sm-2 control-label">单位名称:</label>
		<div class="col-sm-10">
			<form:input path="name"  maxlength="100"  cssClass="form-control"  placeholder="单位名称必填" />
		</div>
	</div>
	<div class="form-group">
		<label  class="col-sm-2 control-label">序号:</label>
		<div class="col-sm-10">
			<form:input path="list"  cssClass="form-control" placeholder="序号必填" />
		</div>
	</div>
	
</form:form>

</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>