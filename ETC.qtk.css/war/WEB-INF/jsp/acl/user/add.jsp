<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${rootUrl }plugins/encrypt/sha-512.js"></script>
<script type="text/javascript">
$(function(){
	$.ajaxSetup ({ cache: false}); 
	$.fn.showPopover.defaults.placement="up";
	$.fn.showPopover.defaults.viewport=null;

	$("#myForm").validate({
		rules: {
			loginName:{required: true,maxlength:50},
			name:{required: true,maxlength:50},
			passwd:{required:true},
			confirm_pw:{required:true, equalTo:"#passwd"}
		},
		messages: {
			passwd: {
				required: "密码必填！"
			},
			confirm_pw: {
				required: "确认密码必填！",
				equalTo: "请检查两次输入是否一致"
			}
		},
		 submitHandler: function(form) {
			$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"add"}]);
		}
	}); 
	$("#submit").click(function(){
// 		$("#myForm").trigger("submit");
		$("#passwd").val(hex_sha512($("#passwd1").val()));
		$("#confirm_pw").val(hex_sha512($("#confirm_pw1").val()));
		$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"add"}]);
	});
	
	

});

function setUnitValue(id,name){
	$("#unit\\.id").val(id);
	$("#unit\\.name").val(name);
}


</script>

<style type="text/css">
.form-control {width: 250px; }


</style>
</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">用户管理--添加</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/acl/user/add" method="post">
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">登录名</label>
	    <div class="col-sm-10">
	    	<form:input path="loginName"  cssClass="form-control" placeholder="登录名必填"/>
	    </div>
	  </div>
	  <div class="form-group">
        <label  class="col-sm-2 control-label">工号</label>
        <div class="col-sm-10">
            <form:input path="staffId" cssClass="form-control" placeholder="工号必填"/>
        </div>
      </div>
	  <div class="form-group">
	  	<label  class="col-sm-2 control-label">单位</label>
	    <div class="col-sm-10">
			<div class="input-group" style="width:280px">
		    	<form:hidden path="unit.id"/>
		    	<form:input path="unit.name"  cssClass="form-control" placeholder="请选择用户所管理的单位" readonly="true"/>
				<div class="input-group-btn">
					 <%@ include file="/WEB-INF/jsp/acl/unit/selectUnit.jsp" %>
				</div>
			</div>
	    </div>
	 </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">角色</label>
	    <div class="col-sm-10">
		    <select name="roleId" class="taiji_autocomplete form-control" data-url="${rootUrl }app/acl/role/listByName" data-placeholder="请选择角色" style="width: 150px">
					<option></option>
			</select>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">密码</label>
	    <div class="col-sm-10">
	    	<form:hidden path="passwd" />
	    	<input type="password" id="passwd1" class="form-control"  placeholder="密码必填"/> 
	    </div>
	  </div>
	 <div class="form-group">
    	 <label  class="col-sm-2 control-label">确认密码</label>
    	 <div class="col-sm-10">
    	 	<form:hidden path="confirm_pw" />
    	 	<input type="password" id="confirm_pw1" class="form-control"  placeholder="确认必填"/>
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
		  <select name="male" title="请选择性别" class="selectpicker" style="width: 150px">
				<option value="true">男</option>
				<option value="false">女</option>
			</select>
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