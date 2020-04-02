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

			$('.selectpicker').selectpicker('render');
		});
		</script>
	</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">模板管理--修改</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/template/edit" method="post">
	 <form:hidden path="id" />
	 
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">项目名称</label>
	    <div class="col-sm-10">
	    	<p class="form-control-static">${pageModel.project.name }</p>
	    </div>
	  </div>
	 
	   <div class="form-group">
	    <label  class="col-sm-2 control-label">模板名称</label>
	    <div class="col-sm-10">
	    	<form:input path="name"  cssClass="form-control" placeholder="模板名称"/>
	    </div>
	  </div>
	  
	 <div class="form-group">
		 <label  class="col-sm-2 control-label">模板文件</label>
	    <div class="col-sm-10">
		   <form:input type="file" path="templateFile"
				class="file templateFile" style="cursor:pointer; width:150px" accept=".doc,.DOC,.docx,DOCX"  />
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