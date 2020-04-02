<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${rootUrl }plugins/password-indicator/css/password-indicator.css" rel="stylesheet" />
<script src="${rootUrl }plugins/password-indicator/js/password-indicator.js" type="text/javascript"></script>
<script src="${rootUrl }plugins/encrypt/sha-512.js"></script>
<script type="text/javascript">
$(function(){
	
	/* $("#passwd1").passwordStrength(); */
	$('#modPwSubmit').click(function(){
		//$("#modPwForm").trigger("submit");
		/* if(!$("#passwordStrengthDiv").is(".is100")){
			alert("新密码的复杂强度不够");
			return false;
		} */
		$("#oldPasswd").val(hex_sha512($("#oldPasswd1").val()));
		$("#passwd").val(hex_sha512($("#passwd1").val()));
		$("#confirm_pw").val(hex_sha512($("#confirm_pw1").val()));
		
		$("#modPwForm").ajaxSubmit(function submitSuccess(resText){
			if(resText.success){
				$.Taiji.showNote(resText.msg);
				$().hideModal();
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
	<h4 class="modal-title">修改密码</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="passwdModel" id="modPwForm" name="modPwForm"  cssClass="form-horizontal" action="${rootUrl }app/modPasswd" method="post">
	<div class="form-group">
	    <label  class="col-sm-2 control-label">原始密码</label>
	    <div class="col-sm-10">
	    	<form:hidden path="oldPasswd"/>
	    	<input type="password"  id="oldPasswd1" class="form-control passwd-control"  placeholder="密码必填"/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">新密码</label>
	    <div class="col-sm-10">
	    	<form:hidden path="passwd"/>
	    	<input type="password" id="passwd1" class="form-control passwd-control"  placeholder="密码必填"/>
	    	<!-- <div id="passwordStrengthDiv" class="is0 m-t-5"></div>
	    	<span class="help-block">密码长度8-12之间，包含数字、字母（区分大小写）、特殊符号</span> -->
	    </div>
	  </div>
	 <div class="form-group">
    	 <label  class="col-sm-2 control-label">确认密码</label>
    	 <div class="col-sm-10">
    	 	<form:hidden path="confirm_pw"/>
	    	<input id="confirm_pw1" type="password"  class="form-control passwd-control"  placeholder="确认必填"/>
	    </div>
  	</div>	
</form:form>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="modPwSubmit">保存</a>
</div>
</body>
</html>