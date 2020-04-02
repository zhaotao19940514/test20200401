<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		
		<script type="text/javascript">
		$(function(){
			$.ajaxSetup ({ cache: false}); 
			//$("#editForm").validate({
			//	rules: {
			//		name:{required: true,maxlength:100},
			//		list:{required: true,digits:true}
			//	},
			//	
			//	 submitHandler: function(form) {
			//		 var option={
			//			 };
			//		$.taijiEditSubmit(form,option);
			//	}
			//});
			
			$("#submit").click(function(){
				//$("#editForm").trigger("submit");
				$("#resourceManage").triggerHandler("taijiModalPost",[$("#editForm"),{table:"edit"}]);
			});
			
		});
		</script>
	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">资源管理--修改</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel" id="editForm" name="editForm"  cssClass="form-horizontal" action="${rootUrl }app/acl/resource/edit" method="post"  enctype="multipart/form-data">
<form:hidden path="menuType" />
<form:hidden path="type" />
<form:hidden path="menuId" />
<form:hidden path="url" />
<form:hidden path="requestMethod" />
<form:hidden path="id" />
	<div class="form-group">
	    <label  class="col-sm-2 control-label">资源名</label>
	    <div class="col-sm-10">
	    	<form:input path="name"   cssClass="form-control" />
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">资源URL</label>
	    <div class="col-sm-10">
	    	<p class="form-control-static">${pageModel.url}</p>
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">请求方法</label>
	    <div class="col-sm-10">
	    	<p class="form-control-static">${pageModel.requestMethod}</p>
	    </div>
	</div>	
	<div class="form-group">
	    <label  class="col-sm-2 control-label">资源图片</label>
	    <div class="col-sm-10">
	    	<input  type="file"  name="logoFile"  id="logoFile" class="form-control"/>
	    </div>
	</div>	
	<div class="form-group">
	    <label  class="col-sm-2 control-label">排序号</label>
	    <div class="col-sm-10">
	    	<form:input path="list"    cssClass="form-control" />
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