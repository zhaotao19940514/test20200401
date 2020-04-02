<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%> 
<script>
	$(function(){
		$.ajaxSetup({cache : false});
		$("#myManage").taiji({
			enableAclCheck:true,
			search:{
				autoSearch:false
			}
		});
		
		$("#Btn").click(function(){
			var cardReader = new CardReader();
			if($("#cardId").val()==""){
				$.Taiji.showWarn("请确认是否输入了卡号!");
				return;
			}
			if($("#cardId").val().length!=20){
				$.Taiji.showWarn("卡号位数错误 !");
				return;
			}
			var cardId ='${pageModel.cardId}';
			var fee ='${pageModel.rechargeAmount}';
			var preBalance= '${pageModel.preBalance}';
			$.Taiji.defConfirm("确定要圈存修复这条充值记录吗？\n\n请确认！").done(function(){ 
			    var data = {};
			    data.cardId = cardId;
			    data.preBalance=preBalance;
				data.rechargeAmount=fee;
				var readCard  = cardReader.getCardId();
				if(isNaN(readCard)){
					$.Taiji.showWarn("读卡器未读到卡片,请检查设备上是否有卡以及设备是否正常工作!");
					return;
				}else  if(readCard!=data.cardId){
		    		$.Taiji.showWarn("当前读卡器的卡与要操作的卡不一致,请检查!");
				}else{
					$("#Btn").attr("disabled",true); 
					$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
					cardChargeCheck(data,cardReader);
				}
			});
		});
		
		function cardChargeCheck(data,cardReader){
			$.ajax({
				url : "cardChargeCheck",
				type : "POST",
				data:JSON.stringify(data),
				dataType : "json",
				contentType: "application/json",
				async:true,
				success : function(responseText) {
					cardReader.openCard();
					if(responseText.status==1){
						if(responseText.chargeStatus==2){
							var command = responseText.command;
							var cosResponse = cardReader.executeMultiApdus(command);
							var rechargeId = '${pageModel.chargeId}'; 
							var dataObj={};
							dataObj.command=command;
							dataObj.cosResponse=cosResponse;
							dataObj.rechargeId=rechargeId;
							cardChargeFix(dataObj,cardReader);
						}else{
							cardReader.closeCard();
							$.Taiji.hideLoading();
							$.Taiji.showWarn(responseText.message);
							$("#Btn").attr("disabled",false); 
						}// =1 去圈存，无需修复圈存
					}else{
						cardReader.closeCard();
						$.Taiji.hideLoading();
						$.Taiji.showWarn(responseText.message);
						$("#Btn").attr("disabled",false); 
					}
					
				}
			});
		}
				
				function cardChargeFix(data,cardReader){
					$.ajax({
						url:"cardChargeFix",
						data:JSON.stringify(data),
						type:"POST",
						dataType : "json",
						contentType: "application/json",
						success:function(response){
							if(response.status==1){
								if(response.fixStatus==1){
									var command = response.command;
									var cosResponse = cardReader.executeMultiApdus(command);
									var rechargeId = '${pageModel.chargeId}'; 
									var dataObj={};
									dataObj.command=command;
									dataObj.cosResponse=cosResponse;
									dataObj.rechargeId=rechargeId;
									cardChargeFix(dataObj,cardReader);
								}else if(response.fixStatus==2){
									//在卡上执行command 并获取返回值
									var rechargeId=response.rechargeId;
									var fee=response.fee;
									var preBalance=response.preBalance;
									var tradeType=response.tradeType;
									var confirmData = {};
									confirmData.rechargeId = rechargeId;
									confirmData.paidAmount =fee;
									confirmData.giftAmount = 0;
									confirmData.cosResponse="9000";
									cardChargeConfirmByCOS(confirmData,cardReader);
								}else if(response.fixStatus==3){
									var rechargeId = response.rechargeId;
									var cardId='${pageModel.cardId}';
									var rechargeAmount=response.fee;
									var preBalance= response.preBalance;
									var tradeType=response.tradeType;
									var command = response.command;
									var cosResponse = cardReader.executeMultiApdus(command);
									var dataObj={};
									dataObj.cardId = cardId;
									dataObj.rechargeAmount = rechargeAmount;
									dataObj.preBalance = preBalance;
									dataObj.command=command;
									dataObj.cosResponse=cosResponse;
									dataObj.rechargeId=rechargeId;
									dataObj.tradeType=tradeType;
									cardChargeByCOS(dataObj,cardReader);
								}
							}else{
								$.Taiji.hideLoading();
								cardReader.closeCard();
								$.Taiji.showWarn(response.message);
								$("#Btn").attr("disabled",false); 
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
							var cardId= '${pageModel.cardId}';
							var commands = response.command;
							//在卡上执行command 并获取返回值
							var cosResponse = cardReader.executeMultiApdus(commands);
							var confirmData = {};
							confirmData.cosResponse = cosResponse;
							confirmData.cardId=cardId;
							confirmData.rechargeId = response.rechargeId;
							confirmData.paidAmount = data.rechargeAmount;
							confirmData.giftAmount = 0;
							confirmData.command = commands;
							cardChargeConfirmByCOS(confirmData,cardReader);	
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
								$.Taiji.showWarn(response.message);
							}
								cardReader.closeCard();
								$.Taiji.hideLoading();  
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
	<h4 class="modal-title">圈存修复 信息--确认</h4>
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
			<th>充值金额(元):</th>
			<td >
				${pageModel.rechargeAmount/100}
			</td>
		</tr>
		<tr>
			<th>充值后余额(元):</th>
			<td >
				${(pageModel.preBalance+pageModel.rechargeAmount)/100}
			</td>
		</tr>
		<tr>
			<th>充值时间:</th>
			<td>
				${pageModel.tradeTime}
			</td>
		</tr>
		<tr>
			<th>充值流水号:</th>
			<td >
				${pageModel.chargeId}
			</td>
		</tr>
		
</table>
</div>
<div class="modal-footer">
	<a id="Btn" href="#" class="btn-white">确认圈存修复</a>
</div>
	
</body>
</html>