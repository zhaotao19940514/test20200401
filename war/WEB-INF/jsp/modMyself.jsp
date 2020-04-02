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
	//$("#modForm").validate({
	//	rules:{
	//		name:{required: true,maxlength:50},
	//		email:{email:true}
	//	},
	//	messages:{
	//		email: {
	//			email: " <font color='red'>邮箱格式不正确</font>"
	//		}
	//	},
	//	submitHandler: function(form) {
	//		$(form).ajaxSubmit(function submitSuccess(resText){
	//			if(resText.success){
	//				parent.$.nmTop().close();
	//				alert(resText.msg);
	//			}
	//			else{
	//				alert("修改失败，错误信息："+resText.msg);
	//			}
	//		});
	//	}
	//});
	$('#submit').click(function(){
		//$("#modForm").trigger("submit");
		$("#modForm").ajaxSubmit(function submitSuccess(resText){
				if(resText.success){
					$().hideModal();
					$.Taiji.showNote(resText.msg);
				}
				else{
					alert("修改失败，错误信息："+resText.msg);
				}
			});
	});
});
</script>
</head>

<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">修改个人信息</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="user" id="modForm" name="modForm"  cssClass="form-horizontal" action="${rootUrl }app/modMyself" method="post">
	<form:hidden path="id"/>
	<form:hidden path="status"/>
    <form:hidden path="roleId" />
 	<div class="form-group">
	    <label  class="col-sm-2 control-label">登录名</label>
	    <div class="col-sm-10">
	    	<form:input path="loginName"  cssClass="form-control"  maxlength="20" readonly="true"/>
	    </div>
	  </div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">姓名</label>
	    <div class="col-sm-10">
	    	<form:input path="name" cssClass="form-control" placeholder="姓名必填"/>
	    </div>
	</div>
	
	<div class="form-group">
	    <label  class="col-sm-2 control-label">性别</label>
	    <div class="col-sm-10">
	    	<label class="radio-inline">
		    	<form:radiobutton path="male" value="true"  label="男" />
            </label>
	    	<label class="radio-inline">
				<form:radiobutton path="male" value="false" label="女"/>
            </label>
	    </div>
	</div>	  
	<div class="form-group">
	    <label  class="col-sm-2 control-label">电子邮箱</label>
	    <div class="col-sm-10">
	    	<form:input path="email"  cssClass="form-control"  maxlength="50" placeholder="XXX@XXX.XXX"/>
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">手机</label>
	    <div class="col-sm-10">
	    	<form:input path="mobile"  cssClass="form-control" maxlength="50" placeholder="13XXXXXXXXXXX"/><form:errors path="mobile" />
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">电话</label>
	    <div class="col-sm-10">
	    	<form:input path="tel"  cssClass="form-control" maxlength="50"/>
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">传真</label>
	    <div class="col-sm-10">
	    	<form:input path="fax"  cssClass="form-control" maxlength="50"/>
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