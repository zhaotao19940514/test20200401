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
	$.fn.showPopover.defaults.placement="right";
	$.fn.showPopover.defaults.viewport=null;
	$("#submit").click(function(){
		$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"add"}]);
	});
});
function minus (){
	var end = $("#endId").val().substring(4);	
	var start = $("#startId").val().substring(4);
	if(start =="" || end ==""){
		$.Taiji.showWarn("请填写完卡号后重试");
		return ;
	}
	var amount = end-start+1;
	$("#amount").text("共计"+amount+"张");
}
</script>
</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">4x版本卡号段管理--添加</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="addModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/administration/section4x/card/add" method="POST">
									<div style="display: inline-block;">
										<div class="form-group" style="width: 280px">
										<div class="col-sm-6">
										<div class="input-group" style="width: 280px">
										<form:label path="startId">起始号段</form:label>
										<form:input id="startId" path="startId" cssClass="form-control" placeholder=""/>
										</div>
										</div>
										</div>
											<div class="form-group">
										<div class="col-sm-6">
										<div class="input-group" style="width: 280px">
										<form:label path="endId">终止号段</form:label>
										<form:input  id="endId" path="endId" cssClass="form-control" placeholder=""/>
										</div>
										</div>
											</div>
										<div class="form-group" >
											<div class="col-sm-6">
											<div class="input-group" style="width: 280px">
												<form:label path="version">卡版本</form:label>
										<form:select path="version"  id="version" cssClass="form-control m-r-5" data-style="btn-white">
											<form:option value="">--请选择--</form:option>
											<form:options items="${version}" itemLabel="name" itemValue="code"/>
										</form:select>
											</div>
										</div>		
										
										</div>
								<button type="button" onclick="minus()">计算卡数量</button>
								<span id="amount"> 共计 张</span>
										</div>
								
   </form:form> 
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>