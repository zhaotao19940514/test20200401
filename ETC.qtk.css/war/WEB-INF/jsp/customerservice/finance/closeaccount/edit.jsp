<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		$(function(){
			$.ajaxSetup({cache : false});
			$("#accountCardBalanceOperateType").change(function(){
				var dir = $("#accountCardBalanceOperateType").val();
				if(dir=='银行卡'){
					$("#banktr").show();
				}else{
					$("#banktr").hide();
					$("#bankCard").val("");
				}
			})
			$("#tijiao").click(function(){
				var customerId = '${pageModel.customerId}';
				console.log("customerId"+customerId);
				var accountCardBalanceOperateType = $("#accountCardBalanceOperateType").val();
				console.log("accountCardBalanceOperateType"+accountCardBalanceOperateType);
				var bankCard = $("#bankCard").val();
				var data={};
				data.customerId=customerId;
				data.accountCardBalanceOperateType=accountCardBalanceOperateType;
				data.bankCard=bankCard;
				if(accountCardBalanceOperateType=='银行卡'&&""==bankCard){
					$.Taiji.showWarn("请输入银行卡号");
				}
				doCancel(data);
				$("#tijiao").attr("disabled",true);
				$("#closeBtn").attr("disabled",true);
				$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			});
		});
		
		function doCancel(data){
			  $.ajax({
		        url : "edit",
		        type : "POST",
		        dataType : "json",
		        data:JSON.stringify(data),
		        contentType: "application/json",
		        async:true,
		        success : function(responseText) {
		      	  if(responseText.status==1){
		      		$("#closeBtn").attr("disabled",false);
		      		  if("现金"==data.accountCardBalanceOperateType){
		      			$.Taiji.hideLoading();
		      			$("#closeBtn").click();
		      			alert(responseText.message+"。请收取现金:"+responseText.refund+"元");
		      		  }else{
	      			  $.Taiji.hideLoading();
		      		  $("#closeBtn").click();
		      		  $.Taiji.showNote(responseText.message);
		      		  }
		      	  }else{
		      		  $.Taiji.hideLoading();
		      		  $("#closeBtn").click();
		      		  $.Taiji.showWarn(responseText.message);
		      	  }
		        },
		        error:function(responseText){
		        	$.Taiji.showWarn(responseText.message);
		        	$.Taiji.hideLoading();
		        }
		    });
		}
		
		</script>
	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">返款方式</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal">
	<table class="table table-bordered table-striped">
		<tr>
			<td>
				<label for='accountCardBalanceOperateType' class="control-label"><b>返款方式</b></label>
			</td>
			<td>
			<form:select path="accountCardBalanceOperateType"  class="form-control  m-r-5" title="请选择返款方式" data-style="btn-warning" >
					<form:option value="银行卡">银行卡</form:option>
					<form:option  value="现金">现金</form:option>
				</form:select>
			</td>
		</tr>
		<tr id = "banktr" style="display:none">
			<td>	
				<label for='bankCard' class="control-label">请输入银行卡号</label>
			</td>
			<td>
				<form:input path="bankCard" cssClass="form-control"
				required="required" placeholder="请输入银行卡号" htmlEscape="false"/>
			</td>
		</tr>
	</table>
</form:form>
	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" id="closeBtn" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="tijiao">确定销户</a>
</div>

</body>
</html>