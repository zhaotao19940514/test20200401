<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%> 
<script>
	$(function(){
		$.ajaxSetup({cache : false});
		
		
		
		$("#readNewCard").click(function(){
			var cardReader = new CardReader();
			var card  = cardReader.getCardId();
			var cardBalance = cardReader.getBalance();
			 if(isNaN(card)){
				$.Taiji.showWarn("读卡器未读到卡片,请检查设备上是否有卡以及设备是否正常工作!");
				return;
			}
			$("#newCardId").val(card); 
			$("#newPreBalance").val(cardBalance/100);
		});
		
		$("#Btn").click(function(){
			var cardStatus = $("#cardStatus").val();
			if(isNaN(cardStatus)){
				$.Taiji.showWarn("此功能必须读旧卡方可完成后续操作!");
				return;
			}
			var cardReader = new CardReader();
			var oldCardId = $("#oldCardId").val();
			var newCardId = $("#newCardId").val();
			debugger;
			var oldPreBalance=$("#oldPreBalance").val()*100;
			var newPreBalance=$("#newPreBalance").val()*100;
			var cardStatus =$("#cardStatus").val();
			var data = {};
			data.oldCardId = oldCardId;
			data.newCardId =newCardId;
			data.oldPreBalance=oldPreBalance;
			data.newPreBalance=newPreBalance;
			data.cardStatus=cardStatus;
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			$("#Btn").attr("disabled",true); 
			cardReplace(data,cardReader);
		});
		
		function cardReplace(data,cardReader){
			$.ajax({
				url : "cardReplace",
				type : "POST",
				data:JSON.stringify(data),
				dataType : "json",
				contentType: "application/json",
				async:true,
				success : function(responseText) {
					if(responseText.status==1){
						if(responseText.chargeStatus==1){
							debugger;
							cardReader.openCard();
							var oldCardId=$("#oldCardId").val();
							var cardId =$("#newCardId").val();
							var fee =$("#oldPreBalance").val()*100;
							var preBalance=$("#newPreBalance").val()*100;
							console.log("preBalance"+preBalance);
							var command = responseText.command;
							var cosResponse = cardReader.executeMultiApdus(command);
							var rechargeId=responseText.rechargeId;
							var data = {};
							data.oldCardId=oldCardId;
							data.cardId = cardId;
							data.preBalance=preBalance;
							data.rechargeAmount=fee;
							data.rechargeId=rechargeId;
							data.command=command;
							data.cosResponse=cosResponse;
							cardChargeByCOS(data,cardReader);	
						}else{
							$.Taiji.hideLoading();
							$.Taiji.showWarn("该新卡存在半条流水,请进行圈存修复!");
							$("#Btn").attr("disabled",false); 
							cardReader.closeCard();
							$("#closeBtn").click();
						}
						
					}else{
						$.Taiji.hideLoading();   
						$.Taiji.showWarn(responseText.message);
						$("#Btn").attr("disabled",false); 
						cardReader.closeCard();
						$("#closeBtn").click();
					}
					
				}
			});
		}
		
		function cardChargeByCOS(data,cardReader){
			$.ajax({
				url:"cardChargeByCOS",
				data:JSON.stringify(data),
				type:"POST",
				dataType : "json",
				contentType: "application/json",
				success:function(response){
					if(response.status==1){
						if(response.commandType==2){
							var cardId= $("#cardId").val();
							var commands = response.command;
							debugger;
							//在卡上执行command 并获取返回值
							var cosResponse = cardReader.executeMultiApdus(commands);
							var paidAmount =$("#oldPreBalance").val()*100;
							var giftAmount ="0";
							var confirmData = {};
							confirmData.paidAmount = paidAmount;
							confirmData.giftAmount = giftAmount;
							confirmData.cosResponse = cosResponse;
							confirmData.cardId=cardId;
							confirmData.rechargeId = response.rechargeId;
							confirmData.tradeType=data.tradeType;
							confirmData.command = commands;
							confirmData.postBalance=data.postBalance;
							cardChargeConfirmByCOS(confirmData,cardReader);
						}else if(response.commandType==1){
							var command = response.command;
							var preBalance= data.preBalance;
							var cosResponse = cardReader.executeMultiApdus(command);
							var rechargeId = data.rechargeId;
							var paidAmount =data.paidAmount;
							var giftAmount =data.giftAmount;
							var chargeType=data.chargeType;
							var tradeType=data.tradeType;
							var postBalance=data.postBalance;
							var dataObj={};
							dataObj.paidAmount = paidAmount;
							dataObj.giftAmount = giftAmount;
							dataObj.preBalance = preBalance;
							dataObj.chargeType=chargeType;
							dataObj.tradeType=tradeType;
							dataObj.command=command;
							dataObj.cosResponse=cosResponse;
							dataObj.rechargeId=rechargeId;
							dataObj.cardId = data.cardId;
							dataObj.postBalance=postBalance;
						    cardChargeByCOS(dataObj,cardReader);
						}
				}else{
					$.Taiji.hideLoading();
					$.Taiji.showWarn(response.message);
					$("#Btn").attr("disabled",false); 
				}
				}
			});
			
		}

		function cardChargeConfirmByCOS(data,cardReader){
			$.ajax({
				url :"cardChargeConfirmByCOS",
				type : "POST",
				dataType : "json",
				data : JSON.stringify(data),
				contentType: "application/json",
				async:true,
				success : function(response) {
					if(response.postBalance!=null && response.postBalance!="" && response.postBalance != undefined){
						$.Taiji.showNote(response.message);
					}else{
						$.Taiji.showWarn("卡片圈存失败!");
					}
					cardReader.closeCard();
					$.Taiji.hideLoading();
					$("#Btn").attr("disabled",false); 
					$(".taiji_search_submit").click();
					$("#closeBtn").click();
					$("#readCard").click();
				}
			});
		}
		
	});
	
</script>
</head>
<body>

<div class="modal-header">
	<h4 class="modal-title">信息--确认</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" method="post">
<table class="table table-bordered">
		
		<tr>
			<th>黔通卡号:</th>
			<td>
				${pageModel.oldCardId}
			</td>
		</tr>
		<tr>
			<th>合作机构编号:</th>
			<td>
				${pageModel.agencyId}
			</td>
		</tr>
		<tr>
			<th>用户编号:</th>
			<td>
				${pageModel.customerId}
			</td>
		</tr>
		
		<tr>
			<th>
				<form:input id="newCardId" path="newCardId"  maxlength="100"  cssClass="form-control"  placeholder="新卡卡号" />
			</th>
			<td>
			<button id="readNewCard" class="btn btn-md btn-success" type="button"><i class="fa  fa-credit-card   m-r-10  "></i>读新卡</button> 
			</td>
			<th style="display: none;">
				<form:input id="newPreBalance" path="newPreBalance"  maxlength="100"  cssClass="form-control"  placeholder="新卡余额" />
			</th>
		</tr>
</table>
</form:form>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal" id="closeBtn">关闭</a>
	<a id="Btn" class="btn btn-sm btn-white" href="#" >确认余额补领</a>
</div>
	
</body>
</html>