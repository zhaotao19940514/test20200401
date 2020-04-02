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

			//$('.selectpicker').selectpicker('render');
		});
		
		function setUnitValue(id,name){
			$("#unit\\.id").val(id);
			$("#unit\\.name").val(name);
		}
		</script>
	</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">用户管理--修改</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/acl/user/edit" method="post">
	 <form:hidden path="id" />
	 <form:hidden path="loginName" />
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">登录名</label>
	    <div class="col-sm-10">
	    	<p class="form-control-static">${pageModel.loginName }</p>
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
	    	<form:select path="roleId" class="selectpicker" title="请选择。。。"  data-style="btn-warning" data-live-search="true" data-size="10">
				<form:option value=""></form:option>
				<form:options items="${roles}" itemLabel="name"  itemValue="id"/>
			</form:select>
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