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
				//$("#myForm").trigger("submit");
				$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"add"}]);
			});

		});
		
		</script>
	</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">定时任务--添加</h4>
</div>

<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/timing/task/add" method="post">
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">任务名称:</label>
	    <div class="col-sm-10">
	    	<form:input path="name" class="form-control"  placeholder="请输入名称"/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">任务类型:</label>
	    <div class="col-sm-10">
	    	<c:forEach items="${taskTypes}" var="t">
			    <label class="radio-inline">
                     <input name="taskType" value="${t }"  type="radio">
                     ${t.value}
                 </label>
	    	</c:forEach>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">任务:</label>
	    <div class="col-sm-10">
	    	<form:input path="job" class="form-control"  placeholder="请输入job"/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">执行时间:</label>
	    <div class="col-sm-10">
	   		 <div  class="input-group" style="width:188px">
				<form:input cssStyle="width:150px"   path="startTime" readonly="true"  cssClass="form-control" />
			    <span class="input-group-btn">
					<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-dd HH:mm:ss'});"><i class="fa fa-calendar"></i></button>
				</span>
			</div>
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