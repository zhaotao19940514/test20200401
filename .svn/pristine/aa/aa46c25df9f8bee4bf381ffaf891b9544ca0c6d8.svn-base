<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		</script>
	</head>
<script>
	$(function(){
		$.ajaxSetup({cache : false});
		var cardReader = new CardReader();
		$("#myManage").taiji({
			enableAclCheck:true,
			search:{
				autoSearch:false
			}
		});
	
		$("#readNewCard").click(function(){
			var cardReader = new CardReader();
			var newCard  = cardReader.getCardId();
			 if(isNaN(newCard)){
				$.Taiji.showWarn("读卡器未读到卡片,请检查设备上是否有卡以及设备是否正常工作!");
				return;
			}
			$("#newCardId").val(newCard);
			var preBalance =cardReader.getBalance();
			if(isNaN(preBalance)){
				$.Taiji.showWarn("读卡器未读到卡片,请检查设备上是否有卡以及设备是否正常工作!");
				return;
			} 
			$("#newPreBalance").html(preBalance/100);
		});
		
		$("#Btn").click(function(){
			var oldCardId = $("#oldCardId").val();
			var newCardId = $("#newCardId").val();
			var oldPreBalance=$("#oldPreBalance").text()*100;
			var newPreBalance=$("#newPreBalance").text()*100;
			var cardStatus=$("#cardStatus").val();
			var data = {};
			data.oldCardId = oldCardId;
			data.newCardId =newCardId;
			data.oldPreBalance=oldPreBalance;
			data.newPreBalance=newPreBalance;
			data.cardStatus=cardStatus;
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			$("#Btn").attr("disabled",true); 
			cardReplace(data);
		});
		
		function cardReplace(data){
			$.ajax({
				url : "cardReplace",
				type : "POST",
				data:JSON.stringify(data),
				dataType : "json",
				contentType: "application/json",
				async:true,
				success : function(responseText) {
						cardReader.openCard();
					if(responseText.status==1){
						var cardId =$("#newCardId").val();
						var fee =responseText.balance;
						var preBalance=responseText.preBalance;
						console.log("preBalance"+preBalance);
						var command = responseText.command;
						var cosResponse = cardReader.executeMultiApdus(command);
						var rechargeId=responseText.rechargeId;
						var data = {};
						data.cardId = cardId;
						data.preBalance=preBalance;
						data.rechargeAmount=fee;
						data.rechargeId=rechargeId;
						data.command=command;
						data.cosResponse=cosResponse;
						CardChargeByCOS(data);
					}else{
						$.Taiji.hideLoading();   
						$.Taiji.showWarn(responseText.message);
						$("#Btn").attr("disabled",false); 
						cardReader.closeCard();
						$(".taiji_search_submit").click();
					}
					
				}
			});
		}
		
		function CardChargeByCOS(data){
			$.ajax({
				url:"cardChargeByCOS",
				data:JSON.stringify(data),
				type:"POST",
				dataType : "json",
				contentType: "application/json",
				error :function(error){
					$.Taiji.hideLoading();   
					$.Taiji.showWarn(error.message);
					$("#Btn").attr("disabled",false); 
					cardReader.closeCard();
					$(".taiji_search_submit").click();
				},
				success:function(response){
					if(response.status==1){
						var cardId= $("#oldCardId").val();
						var commands = response.command;
						//在卡上执行command 并获取返回值
						var cosResponse = cardReader.executeMultiApdus(commands);
						var paidAmount=data.rechargeAmount;
						var giftAmount=0;
						var confirmData = {};
						confirmData.cosResponse = cosResponse;
						confirmData.cardId=cardId;
						confirmData.rechargeId = response.rechargeId;
						confirmData.paidAmount = paidAmount;
						confirmData.giftAmount = giftAmount;
						confirmData.command = commands;
						CardChargeConfirmByCOS(confirmData);		
					}else{
						$.Taiji.hideLoading();   
						$.Taiji.showWarn(response.message);
						$("#Btn").attr("disabled",false); 
						cardReader.closeCard();
						$(".taiji_search_submit").click();
					}
					
				}
			});
			
		}
	
		
		function CardChargeConfirmByCOS(data){
			$.ajax({
				url :"CardChargeConfirmByCOS",
				type : "POST",
				dataType : "json",
				data : JSON.stringify(data),
				contentType: "application/json",
				async:true,
				error :function(error){
					alert(error);
				},
				success : function(response) {
						cardReader.closeCard();
						$.Taiji.hideLoading();   
						$.Taiji.showNote(response.message);
						$(".taiji_search_submit").click();
						$("#Btn").attr("disabled",false); 
					
				}
			});
		}
		
	});
	
</script>
<body>
<input type="hidden" id="newCardType" />
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">余额补领信息:</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" method="post">
	<table class="table table-bordered table-striped">
		<tr>
			<td>
				<label for='newCardId' class="control-label">
				<form:input path="newCardId" size="50" cssClass="form-control"
				required="required" placeholder="读新卡" /></label>
			</td>
			<td>
				<button class="btn btn-md btn-default btn btn-success btn-small"  id="readNewCard"  type="button"> <i class="fa  fa-credit-card  m-r-10  "></i>读卡</button>
			</td>
		</tr>
	</table>
		
	</form:form>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" id="closeBtn" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="Btn">确定</a>
</div>

</body>
</html>