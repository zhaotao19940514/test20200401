<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${rootUrl }plugins/encrypt/sha-512.js"></script>
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$.ajaxSetup ({ cache: false}); 
	$("#submit").click(function(){
		//$("#myForm").trigger("submit");
		$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"add"}]);
	});

});
	
	function clearNoNum(obj){ 
		    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
		    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
		    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
		    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数   
		    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
		        obj.value= parseFloat(obj.value); 
		    } 
		} 
</script>
<style type="text/css">
	table{
		talbe
	}
</style>
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">新增补换设备费用信息</h4>
	</div>

	<div class="modal-body">
		<form:form modelAttribute="pageModel" id="myForm" name="myForm"
			cssClass="form-horizontal"
			action="${rootUrl }app/administration/package/replace/add"
			method="post">
			<table class="table table-bordered table-striped">
				<tr>
					<td>
						<label for='cardCost' class="control-label">卡费用</label>
					</td>
					<td>
						<form:input path="cardCost" cssClass="form-control"
						required="required" placeholder="卡费用" onkeyup="clearNoNum(this)"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for='obuCost' class="control-label">obu费用</label>
					</td>
					<td>
						<form:input path="obuCost" cssClass="form-control"
						required="required" placeholder="obu费用" htmlEscape="false"  maxlength="3"  onkeyup="clearNoNum(this)"/>
					</td>
				</tr>				
			</table>
		</form:form>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a> <a
			href="#" class="btn btn-sm btn-success" id="submit">保存</a>
	</div>

</body>
</html>