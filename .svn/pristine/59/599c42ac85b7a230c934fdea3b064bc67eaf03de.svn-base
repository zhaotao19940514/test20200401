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
		
		function setUnitValue(id,name){
			$("#unit\\.id").val(id);
			$("#unit\\.name").val(name);
		}
		</script>
	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">角色管理--修改</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/acl/role/edit" method="post">
	<form:hidden path="id"/>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">角色名称</label>
	    <div class="col-sm-10">
	    	<form:input path="name"/>
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
	    <label  class="col-sm-2 control-label">显示顺序</label>
	    <div class="col-sm-10">
	    	<form:input path="list"/>
	    </div>
	  </div>
	   <div class="form-group">
	    <label  class="col-sm-2 control-label">描述</label>
	    <div class="col-sm-10">
	    	<form:textarea rows="5" cols="50" class="form-control" path="info"/>
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