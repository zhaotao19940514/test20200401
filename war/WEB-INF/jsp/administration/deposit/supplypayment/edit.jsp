<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
	<meta http-equiv="expires" content="0" />
	<meta http-equiv=
	"pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<script type="text/javascript">
		$(function(){
			$.ajaxSetup({
				cache : false
			});
			$("#submit").click(function(){
				 $("#submit").attr("disabled",true);
				$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
				var misposClient = new MisPosClient();
				var posMoney = $("#payBalance").val();
				ajaxPosCommand(posMoney,misposClient);//调用pos机消费方法
				console.log(posMoney);  
			});
		});
		//pos机消费方法
		function ajaxPosCommand(posMoney,misposClient){
			var data={};
			data.amount = posMoney*100;
			console.log(JSON.stringify(data));
			var url = '${rootUrl }';
			$.ajax({
				url:url+"app/ocx/mispos/posTradeCos",
				data:JSON.stringify(data),
				type:'POST',
				contentType : 'application/json',
		        dataType : 'json',
		        success:function(response){
		        	var command = response.command;
		            var posResponse = misposClient.trade(command);
		            data.command = command;
		            data.posResponse = posResponse;
		            console.log(posResponse);
		            ajaxPosConsumeConfirm(data);
		        },
		        error:function(response){
		        	$.Taiji.hideLoading();
		        	console.log(response);
		        }
			});
		};
		//pos机消费成功后，调用        pos机消费确认，打消费日志
		function ajaxPosConsumeConfirm(data){
			var url = '${rootUrl }';
			data.cardId = $("#cardId").val();
            $.ajax({
                url:url+"app/ocx/mispos/posTradeConfirm",
                data:JSON.stringify(data),
                type:'POST',
                contentType : 'application/json',
                dataType : 'json',
                success:function(response){
                	if(response.success){
                		returnwhite();
                	}else{
                		$.Taiji.hideLoading();
                		$.Taiji.showWarn(response.message);
                	}
                },
                error:function(response){
                	$.Taiji.hideLoading();
                	$.Taiji.showWarn("消费日志出错！");
                }
            });
        };
        
        function returnwhite(){
        	var cardId = '${cardId}';
        	var payBalance = $("#payBalance").val();
        	var data={};
        	data.cardId = cardId;
        	data.payBalance = payBalance*100;
        	 $.ajax({
			      url : rootUrl+"app/administration/deposit/supplypayment/doSubmit",
			      type : "POST",
			      dataType : "json",
			      data:JSON.stringify(data),
			      contentType: "application/json",
			      async:true,
			      success : function(responseText) {
			    	  $.Taiji.hideLoading();
			    	  if(responseText.status==1){
			    		  $.Taiji.showNote(responseText.message);
			    		  $("#listForm").submit();
			    		  $("#closeBtn").click();	
			    	  }else{
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
	<h4 class="modal-title">补交</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel1" id="myForm" name="myForm" cssClass="form-horizontal">
	<table class="table table-bordered table-striped">
		<tr>
			<td>
				<label for='cardId' class="control-label"><b>ETC卡号</b></label>
			</td>
			<td>
				<form:input path="cardId" cssClass="form-control  m-r-5" required="required" disabled="true" htmlEscape="false" value='${cardId}' />
			</td>
		</tr>
		<tr>
			<td>
				<label for='cardId' class="control-label"><b>保证金总额(元)</b></label>
			</td>
			<td>
				<form:input path="cardId" cssClass="form-control  m-r-5" required="required" disabled="true" htmlEscape="false" value='500' />
			</td>
		</tr>
		<tr>
			<td>
				<label for='cardId' class="control-label"><b>剩余保证金额(元)</b></label>
			</td>
			<td>
				<form:input path="cardId" cssClass="form-control  m-r-5" required="required" disabled="true" htmlEscape="false" value='${collateCardBalance.balance/100}' />
			</td>
		</tr>
		<tr>
			<td>
				<label for='cardId' class="control-label"><b>补交方式</b></label>
			</td>
			<td>
				<form:input path="cardId" cssClass="form-control  m-r-5" required="required" disabled="true" htmlEscape="false" value='刷POS' />
			</td>
		</tr>
		<tr>
			<td>
				<label for="payBalance" class="control-label"><b>补交金额(元)</b></label>
			</td>
			<td>
			<input style="width:280px;color:red;font-size:20px; font-weight:bold;" id='payBalance' value='${collateCardBalance.payBalance/100}' class="form-control" disabled="disabled" />
			</td>
		</tr>
	</table>
</form:form>
	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" id="closeBtn" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">支付并保存</a>
</div>

</body>
</html>