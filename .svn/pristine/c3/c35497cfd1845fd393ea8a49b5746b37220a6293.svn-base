<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>

	$(function(){
		$.ajaxSetup({cache : false});
		$("#Btn").click(function(){
			var cardReader = new CardReader();
			var cardId ='${pageModel.cardId}';
			var fee ='${pageModel.rechargeAmount}';
			var chargeId='${pageModel.chargeId}';
			var card=cardReader.getCardId();
			var data = {};
			data.cardId = cardId;
			data.fee=fee;
			data.chargeId=chargeId;
			if(cardId=card){
			$("#Btn").attr("disabled",true); 
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE); 
			cardReverseInitWithCOS(data,cardReader);
		}else{
			alert("请检查该卡片是否为卡号为"+cardId+"的卡");
		}
			
			
		});
		
		//申请
		function cardReverseInitWithCOS(data,cardReader){
			$.ajax({
				url : "cardReverseInitWithCOS",
				type : "POST",
				data:JSON.stringify(data),
				dataType : "json",
				contentType: "application/json",
				async:true,
				success : function(responseText) {
							//打开卡片
							cardReader.openCard();
							if(responseText.status==1){
							var cos = responseText.cos;
							var cardId='${pageModel.cardId}';
							var fee ='${pageModel.rechargeAmount}';
							var chargeId='${pageModel.chargeId}'; 
							var tradeType='${pageModel.tradeType}';
							
							//冲正初始化指令结果
							var initializeResponse = cardReader.executeMultiApdus(cos);
							var data={};
							data.cardId=cardId;
							data.command=cos;
							data.fee=fee;
							data.chargeId=chargeId;
							data.tradeType=tradeType;
							data.initializeResponse=initializeResponse;
							cardReverseDebitWithCOS(data,cardReader);
						}else{
							 $.Taiji.hideLoading();
							 $.Taiji.showWarn(responseText.message);
							 cardReader.closeCard();
							 $("#Btn").attr("disabled",false); 
						}
				}
			});
			}
		
			//消费
			function cardReverseDebitWithCOS(data,cardReader){
				$.ajax({
					url:"cardReverseDebitWithCOS",
					data:JSON.stringify(data),
					type:"POST",
					dataType : "json",
					contentType: "application/json",
					success:function(response){
						if(response.status==1){
							var chargeId='${pageModel.chargeId}'; 
							var fee ='${pageModel.rechargeAmount}';
							var command=response.command;
							var cardId='${pageModel.cardId}';
							//冲正初始化指令结果
							var cosResponse = cardReader.executeMultiApdus(command);
							var data = {};
							data.cardId=cardId;
							data.chargeId=chargeId;
							data.fee=fee;
							data.command=command;
							data.cosResponse=cosResponse;
							cardReverseConfirmWithCOS(data,cardReader);	
						}else{
							$.Taiji.hideLoading();
							$.Taiji.showWarn(response.message);
							cardReader.closeCard();
							$("#Btn").attr("disabled",false); 
						}
						
					}
				});
			}
			
			function cardReverseConfirmWithCOS(data,cardReader){
			$.ajax({
				url :"cardReverseConfirmWithCOS",
				type : "POST",
				dataType : "json",
				data : JSON.stringify(data),
				contentType: "application/json",
				async:true,
				success : function(response) {
					if(response.reverseConfirmStatus==1){
						$.Taiji.showNote("圈存冲正成功");
					}else{
						$.Taiji.showWarn(response.message);
					}
					$.Taiji.hideLoading();
					cardReader.closeCard();
					$("#Btn").attr("disabled",false); 
					$(".taiji_search_submit").click();
				}
			});
		}
	});
</script>
</head>
<body>

<div class="modal-header">
	<h4 class="modal-title">冲正信息--确认</h4>
</div>
<div class="modal-body">
<table class="table table-bordered">
		 
		<tr>
			<th>黔通卡号:</th>
			<td>
				${pageModel.cardId}
			</td>
		</tr>
		<tr>
			<th>充值前余额(元):</th>
			<td>${pageModel.preBalance/100}</td>
		</tr>
		<tr>
			<th>需冲正金额(元):</th>
			<td >
				${pageModel.rechargeAmount/100}
			</td>
		</tr>
		<tr>
			<th>充值流水号:</th>
			<td >
				${pageModel.chargeId}
			</td>
		</tr>
		<tr>
			<th>充值时间:</th>
			<td>
				${fn:replace(pageModel.tradeTime,'T',' ')}
			</td>
		</tr>
		<tr>
			<th>充值方式:</th>
			
			<td>
				<c:if test="${fn:escapeXml(pageModel.tradeType eq '0')}">
					<c:if test="${fn:escapeXml(pageModel.status eq '1')}">现金圈存交易</c:if>
				</c:if>
				<c:if test="${fn:escapeXml(pageModel.tradeType eq '3')}">
					<c:if test="${fn:escapeXml(pageModel.status eq '1')}">账户圈存交易</c:if>
				</c:if>
				<c:if test="${fn:escapeXml(pageModel.tradeType eq '4')}">
					<c:if test="${fn:escapeXml(pageModel.status eq '1')}">余额补领</c:if>
				</c:if>
			</td>
		</tr>
</table>
</div>
<div class="modal-footer">
	<a id="Btn" href="#" class="btn-white">确认冲正</a>
</div>
	
</body>
</html>