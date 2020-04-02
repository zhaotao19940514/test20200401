<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script type="text/javascript">
$(function(){
	$("#myOpen").taiji();
	$("#myForm").validate({
		rules: {
			loginName:{required: true,maxlength:50},
			male:{required: true},
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
			$("#myOpen").triggerHandler("taijiOpenPost",[$("#myForm"),{table:"add","openerTarget":"#myManage"}]);
		}
	}); 
	$("#submit").click(function(){
		//$("#myForm").trigger("submit");
		$("#myOpen").triggerHandler("taijiOpenPost",[$("#myForm"),{table:"add","openerTarget":"#myManage"}]);
	});
	
	
	$("#closeBtn").click(function(){
		window.close();
	});
});

function setUnitValue(id,name){
	$("#unit\\.id").val(id);
	$("#unit\\.name").val(name);
}
</script>

</head>
<body >
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	
	<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<!-- end #header -->
		
	<div class="content" style="margin-left: 0px">
	<div class="row">
	<div class="col-md-12">
		 <div id="myOpen" class="well">
			<div class="modal-header">
				<h4 class="modal-title">用户管理--添加</h4>
			</div>
			<div class="modal-body">
			<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal"  action="${rootUrl }app/acl/user/add" method="post">
			  <div class="form-group">
				   <label  class="col-sm-1 control-label" for="loginName">登录名</label>
				    <div class="col-sm-3">
				    	<form:input path="loginName"  cssClass="form-control" placeholder="登录名必填"/>
				    </div>
				    <label  class="col-sm-1 control-label">单位</label>
				    <div class="col-sm-3">
				    	<div class="input-group" style="width:280px">
					    	<form:hidden path="unit.id"/>
					    	<form:input path="unit.name"  cssClass="form-control" placeholder="请选择用户所管理的单位" readonly="true"/>
							<div class="input-group-btn">
								 <%@ include file="/WEB-INF/jsp/acl/unit/selectUnit.jsp" %>
							</div>
						</div>
				    </div>
				    <label  class="col-sm-1 control-label">角色</label>
				    <div class="col-sm-3">
					    <form:select path="roleId" class="selectpicker" title="请选择..."  data-style="btn-warning" data-live-search="true" data-size="10">
							<form:option value=""></form:option>
							<form:options items="${roles}" itemLabel="name"  itemValue="id"/>
						</form:select>
				    </div>
				    
			  </div>
			  <div class="form-group">
				    <label  class="col-sm-1 control-label">姓名</label>
				    <div class="col-sm-3">
				    	<form:input path="name" cssClass="form-control" placeholder="姓名必填"/>
				    </div>
			  		<label  class="col-sm-1 control-label">密码</label>
				    <div class="col-sm-3">
				    	<form:password path="passwd" cssClass="form-control"  placeholder="密码必填"/>
				    </div>
				   <label  class="col-sm-1 control-label">确认密码</label>
		    	   <div class="col-sm-3">
			    	     <input id="confirm_pw" name="confirm_pw" type="password"  class="form-control"  placeholder="确认必填"/>
			       </div>
			  </div>	
			   <div class="form-group">
			   		<label  class="col-sm-1 control-label"  for="male">性别</label>
				    <div class="col-sm-3" >
				    	<div data-for="male">
					    	<label class="radio-inline">
					    		<form:radiobutton path="male" value="1" />男
	                         </label>
					    	<label class="radio-inline">
					    		<form:radiobutton path="male" value="0" />女
	                         </label>
				    	</div>
				    </div>
				    <label  class="col-sm-1 control-label">手机</label>
				    <div class="col-sm-3">
				    	<form:input path="mobile"  cssClass="form-control" maxlength="50" placeholder="13XXXXXXXXXXX"/><form:errors path="mobile" />
				    </div>
				     <label  class="col-sm-1 control-label">电话</label>
				    <div class="col-sm-3">
				    	<form:input path="tel"  cssClass="form-control" maxlength="50"/>
				    </div>
			   		
			   </div>    
			   <div class="form-group">
			   		<label  class="col-sm-1 control-label">传真</label>
				    <div class="col-sm-3">
				    	<form:input path="fax"  cssClass="form-control" maxlength="50"/>
				    </div>
				    <label  class="col-sm-1 control-label">电子邮箱</label>
				    <div class="col-sm-3">
				    	<form:input path="email"  cssClass="form-control"  maxlength="50" placeholder="XXX@XXX.XXX"/>
				    </div>
			   </div>
			 </form:form> 
			</div>
			<div class="modal-footer" style="text-align: center;">
				<a href="#" class="btn btn-sm btn-white"   id="closeBtn">关闭</a>
				<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
			</div>
         </div>  
	</div>
	</div>
	</div>
	</div>
	<!-- end page container -->
	
	

</body>
</html>
