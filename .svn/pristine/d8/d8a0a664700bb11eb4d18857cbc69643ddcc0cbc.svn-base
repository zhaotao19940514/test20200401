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
	$.fn.showPopover.defaults.placement="right";
	$.fn.showPopover.defaults.viewport=null;

	 $("#myForm").validate({
		/* rules: {
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
		},  */
		 submitHandler: function(form) {
			$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"add"}]);
		}
	}); 
	$("#submit").click(function(){
// 		$("#myForm").trigger("submit");
		/*  $("#passwd").val(hex_sha512($("#passwd1").val()));
		$("#confirm_pw").val(hex_sha512($("#confirm_pw1").val()));  */
		$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"add"}]);
	});

});

function setServiceHallValue(id, name) {
	$("#serviceHall\\.id").val(id);
	$("#serviceHall\\.name").val(name);
}
</script>
</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">员工管理--添加</h4>
</div>
 <div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/administration/inventory/devicewarehousing/card/add" method="post">
	  <%--  <div class="form-group">
	    <label  class="col-sm-2 control-label">网点编号</label>
	    <div class="col-sm-10">
	    	<form:input path="serviceHallId"  cssClass="form-control" placeholder="网点编号必填"/>
	    </div> 
	  </div>  --%> 
	  <%--  <div class="form-group">
	    <label  class="col-sm-2 control-label">网点ID</label>
	    <div class="col-sm-10">
	    	<form:input path="hallId"  cssClass="form-control" placeholder="网点编号必填"/>
	    </div>
	  </div>  --%>
	  
	   <div class="form-group">
				<label class="col-sm-2 control-label">所属网点</label>
				<div class="col-sm-10">
					<div class="input-group" style="width: 280px">
							<form:hidden path="serviceHall.id"/>
						<form:input path="serviceHall.name" cssClass="form-control"
							placeholder="请选择用户所管理的网点"/>
						<div class="input-group-btn">
							<%@ include file="/WEB-INF/jsp/administration/inventory/devicewarehousing/card/selectUnit.jsp"%>
						</div>
					</div>
				</div>
			</div>  
		 <%-- <div class="form-group">
		    <label  class="col-sm-2 control-label">网点名称</label>
		    <div class="col-sm-10">
			    <select name="name" class="taiji_autocomplete form-control" data-url="${rootUrl }app/hall/listByName" data-placeholder="请选择网点" style="width: 150px">
						<option></option>
				</select>
		    </div>
		 </div> --%>
	  
	  <%-- <div class="form-group">
	    <label  class="col-sm-2 control-label">网点编号</label>
	    <div class="col-sm-10">
	    	<form:input path="serviceHallId"  cssClass="form-control" placeholder="网点编号必填"/>
	    </div>
	  </div>  --%>
	  
	 <%--  <div class="form-group">
	  	<label  class="col-sm-2 control-label">员工编号</label>
	    <div class="col-sm-10">
			<div class="input-group" style="width:280px">
		    		<form:input path="staffId"  cssClass="form-control" placeholder="员工编号必填"/>
			</div>
	    </div>
	 </div> --%>
	 <%--  <div class="form-group">
	    <label  class="col-sm-2 control-label">员工姓名</label>
	    <div class="col-sm-10">
		   <div class="input-group" style="width:280px">
		    		<form:input path="staffName"  cssClass="form-control" placeholder="员工姓名必填"/>
			</div>
	    </div>
	  </div>
	  <div class="form-group">
		<label class="col-sm-2 control-label">员工类型</label>
			<div class="col-sm-10">
			 	<div class="input-group" style="width:280px">
					<form:input path="staffType" cssClass="form-control" placeholder="类型必填" />
				</div>
			</div>
	</div> --%>
  	</form:form> 
 </div>

<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>