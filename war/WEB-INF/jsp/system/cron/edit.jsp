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
		</script>
	</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">任务cron参数配置</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/system/cron/edit" method="post">
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">任务名称</label>
	    <div class="col-sm-10">
	    	<form:input path="taskName" readonly="true" cssClass="form-control" />
	    </div>
	  </div>
  	<div class="form-group">
	    <label  class="col-sm-2 control-label">cron</label>
	    <div class="col-sm-10">
	    	<form:input path="cron" cssClass="form-control" placeholder="cron必填"/>
	    </div>
	</div>
	
	<div class="form-group">
	    <label  class="col-sm-2 control-label">备注</label>
	    <div class="col-sm-10">
	    	<form:input path="info" readonly="true"  cssClass="form-control"/>
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