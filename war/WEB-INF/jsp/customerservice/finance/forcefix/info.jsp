<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function(){
		$.ajaxSetup({cache : false});
		$("#sure").click(function(){
			var cardId ='${pageModel.cardId}';
			var chargeId='${pageModel.chargeId}';
			$.Taiji.defConfirm("确定要强制确认"+cardId+"的这条充值记录吗？\n\n请确认！").done(function(){ 
				 	 var data = {};
					data.cardId = cardId;
					data.chargeId=chargeId;
					update(data);
			});
		});
		
		$("#delete").click(function(){
			var cardId ='${pageModel.cardId}';
			var chargeId='${pageModel.chargeId}';
			$.Taiji.defConfirm("确定要强制作废"+cardId+"的这条充值记录吗？\n\n请确认！").done(function(){ 
				 	 var data = {};
					data.cardId = cardId;
					data.chargeId=chargeId;
					deleteDetil(data);
		});
		});
		
		function update(data){
			$.ajax({
				url:"update",
				data:JSON.stringify(data),
				type:"POST",
				dataType : "json",
				contentType: "application/json",
				success:function(response){
					$.Taiji.hideLoading();
					if(response.status==1){
						$("#closeBtn").click();
						$.Taiji.showNote(response.message);
						$(".taiji_search_submit").click();
					}else{
						$.Taiji.showWarn(response.message);
					}
				}
			});
			
		}
		
		function deleteDetil(data){
			$.ajax({
				url:"deleteDetil",
				data:JSON.stringify(data),
				type:"POST",
				dataType : "json",
				contentType: "application/json",
				success:function(response){
					$.Taiji.hideLoading();
					if(response.status==1){
						$("#closeBtn").click();
						$.Taiji.showNote(response.message);
						$(".taiji_search_submit").click();
					}else{
						$.Taiji.showWarn(response.message);
						$(".taiji_search_submit").click();
					}
					
				}
			});
			
		}
		
	});
</script>
</head>
<body>

<div class="modal-header">
	<h4 class="modal-title">强制修复 信息--确认</h4>
	
</div>
<div class="modal-body">



 <form:form modelAttribute="pageModel1" id="myForm" name="myForm" cssClass="form-horizontal" method="post">
	<table class="table table-bordered table-striped">
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
</form:form>
</div>
<div class="modal-footer">
	<a id="sure" href="#" class="btn-white">确认强制修复</a>
	<a id="delete" href="#" class="btn-white">确认强制作废</a>
	<a href="#" class="btn btn-sm btn-white" id="closeBtn" data-dismiss="modal">关闭</a>
</div>
	
</body>
</html>