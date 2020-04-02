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
			//$("#addForm").validate({
			//	rules: {
			//		name:{required: true,maxlength:100},
			//		url:{required: true,maxlength:150},
			//		logoFile:{required: function(){						
			//							if($("#menuType").val()=='COLUMN'||$("#menuType").val()=='BOX_TAB') {
			//								return true;
			//							} 
			//								return false;
			//							}},
			//		list:{required: true,digits:true}
			//	},
				
			//	 submitHandler: function(form) {
			//		 if($("#logoFile").val()==""){
			//				//避免ajax提交空file报错
			//				$('#logoFile').remove();
			//		  }
			//		 var option={
			//			 };
			//		$.taijiAddSubmit(form,option);
			//	}
			//});
			
			$("#submit").click(function(){
				//$("#addForm").trigger("submit");
				if($("#logoFile").val()==""){
					$('#logoFile').remove();
				}
				$("#resourceManage").triggerHandler("taijiModalPost",[$("#addForm"),{table:"add"}]);
			});
			
			$("#list").val("");
			
		});
		</script>
	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">资源管理--添加</h4>
</div>
<div class="modal-body">

<form:form modelAttribute="pageModel" id="addForm" name="addForm"  cssClass="form-horizontal"  action="${rootUrl }app/acl/resource/add" method="post"  enctype="multipart/form-data">
<form:hidden path="menuType" />
<form:hidden path="type" />
<form:hidden path="menuId" />
	<div class="form-group">
	    <label  class="col-sm-2 control-label">资源名</label>
	    <div class="col-sm-10">
	    	<form:input path="name"   cssClass="form-control" />
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">资源URL</label>
	    <div class="col-sm-10">
	    	<form:input path="url"    cssClass="form-control" />
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">请求方法</label>
	    <div class="col-sm-10">
	    	<form:select path="requestMethod"  cssClass="form-control">
				<form:option value="GET"></form:option>
				<form:option value="POST"></form:option>
				<form:option value="PUT"></form:option>
				<form:option value="DELETE"></form:option>
			</form:select>
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">资源图片</label>
	    <div class="col-sm-10">
	    	<input  type="file"  name="logoFile"  id="logoFile"   class="form-control"  />
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