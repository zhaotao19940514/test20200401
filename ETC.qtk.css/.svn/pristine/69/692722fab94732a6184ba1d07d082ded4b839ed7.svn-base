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
	var batchInfo = $("#batchInfo").val();
	if(batchInfo ==""){
		$.Taiji.showWarn("请填写完批量信息后重试");
		return ;
	}
 	var amount = 0;
 	var section = batchInfo.split(",");
 	for(var i=0;i<section.length;i++){
 		if(i==0){
 			var obuSection = section[i];
 	 		var detail = obuSection.split('-');
 	 		if(detail[0].length!=16 || detail[1].length!=16){
 	 			$.Taiji.showWarn("号段信息输入有误，具体号段信息为"+detail);
 	 			return ;
 	 		}
 	 		if(detail[1]-detail[0]<0){
 	 			$.Taiji.showWarn("结束号段比起始号段大，具体号段信息为:"+detail);
 	 			return ;
 	 		}
 	 		amount = detail[1]-detail[0]+1+amount;
 		}else{
 			var obuSection = section[i];
 	 		var detail = obuSection.split('-');
 	 		if(detail[0].length!=17 || detail[1].length!=16){
 	 			$.Taiji.showWarn("号段信息输入有误，具体号段信息为"+detail);
 	 			return ;	
 	 		}
 	 		if(detail[1]-detail[0]<0){
 	 			$.Taiji.showWarn("结束号段比起始号段大，具体号段信息为:"+detail);
 	 			return ;
 	 		}
 	 		amount = detail[1]-detail[0]+1+amount;
 		}
 	}
 	$("#amount").text("共计"+amount+"张");
 }
</script>
</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">4x版本OBU号段管理--批量添加</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="addModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/administration/section4x/obu/batchAdd" method="POST">
													<div style="float: left;margin-right: 40px">
										<form:label path="batchInfo">批量信息</form:label>
										<form:textarea id="batchInfo" path="batchInfo" cssClass="form-control" placeholder="" cssStyle="width:290px;height:200px;resize:none;" />
									</div>
									<div style="float: right;margin-right:200px;">
									
									<h6>批量添加：填写批量添加号段+OBU类型</h6><br>
									<h5 style="color:red;">输入完毕后请点击计算数量确认数量后入库</h5><br>
									<h6>批量添加示例：</h6><br>
									<h6>5200000000000000-5200000000000001,</h6>
									<h6>5200000000000003-5200000000000004</h6><br>
									<h6>支持的分割符为英文状态下的逗号(,)</h6>
									</div>
										<div class="form-group" >
											<div class="col-sm-6">
											<div class="input-group" style="width: 280px">
												<form:label path="version">OBU版本</form:label>
										<form:select path="version"  id="version" cssClass="form-control m-r-5" data-style="btn-white">
											<form:option value="">--请选择--</form:option>
											<form:options items="${version}" itemLabel="name" itemValue="code"/>
										</form:select>
											</div>
													<button type="button" onclick="minus()">计算数量</button><span id="amount">共计 张</span>
										</div>		
								
										
										</div>
								
   </form:form> 
</div>
<div class="modal-footer" style="margin-left: 300px">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>