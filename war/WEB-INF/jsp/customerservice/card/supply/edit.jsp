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
	$(function() {
		$.ajaxSetup({
			cache : false
		});
		$('.modal-body').taiji();
		var clickWay;
		cardType = '${cardType}';
		$("#submit").click(function(){ 
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			var cardReader = new CardReader();
			var newCardId = $("#newCardId").val();
			if(null==newCardId||""==newCardId||newCardId=='undefined'){
				 $.Taiji.showWarn("请先读卡");
				return;
			}
			var checkCardId = cardReader.getCardId();
			if(checkCardId != newCardId){
				$.Taiji.showWarn("新卡号前后读取不一致,请重新读卡");
				return;
			}
			var cardReader = new CardReader();
			cardRefund();
		});
		$("#readCardBtn").click(function() {
			var cardReader = new CardReader();
			var newCardId = cardReader.getCardId();
			var newCardType = cardReader.getIssuerTypeIdentifier();
			if(isNaN(newCardId)){
				$.Taiji.showWarn("未读到卡信息");
				return;
			}
			if(newCardType!=cardType){
				$.Taiji.showWarn("新旧卡类型不一致");
				return;
			}
			/* if(23==newCardType){
				$("#packageId").show();
			}else{
				$("#packageId").hide();
			} */
			$("#newCardId").val(newCardId);
			
		});
	});
	function cardRefund(){
	     $.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
	     var data = {};
	     $.ajax({
	        url : rootUrl+"app/customerservice/card/supply/cardRefund",
	        type : "POST",
	        dataType : "json",
	        data:JSON.stringify(data),
	        contentType: "application/json",
	        async:true,
	        success : function(responseText) {
	        	alert("请收取卡费用:"+responseText+"元");
	        	var misposClient = new MisPosClient();
				var posMoney = responseText;
				ajaxPosCommand(posMoney,misposClient);//调用pos机消费方法
				console.log(posMoney); 
	        },
	         error:function(responseText){
	           	$.Taiji.showWarn(responseText.message);
	           	$.Taiji.hideLoading();
	         }
	    });
	    }
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
		data.cardId = $("#newCardId").val();
        $.ajax({
            url:url+"app/ocx/mispos/posTradeConfirm",
            data:JSON.stringify(data),
            type:'POST',
            contentType : 'application/json',
            dataType : 'json',
            success:function(response){
            	if(response.success){
            		var cardReader = new CardReader();
            		oldCardCancel('${cardId}','${cardType}',cardReader);
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
	</script>
<body>
<input type="hidden" id="newCardType" />
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">补卡信息:</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" method="post">
	<table class="table table-bordered table-striped">
		<tr>
			<td>
				<label for='newCardId' class="control-label">
				<form:input path="newCardId" size="50" cssClass="form-control"
				required="required" placeholder="读新卡信息" /></label>
			</td>
			<td>
				<button class="btn btn-md btn-default btn btn-success btn-small"  id="readCardBtn"  type="button"> <i class="fa  fa-credit-card  m-r-10  "></i>读卡</button>
			</td>
		</tr>
		<tr>
			<td>
				<label for=cardId class="control-label">
				<form:input path="cardId" size="50" cssClass="form-control"
				required="required" placeholder="${cardId }" readonly="true" /></label>
			</td>
			<td></td>
			<%-- <c:if test="${cardType eq 22 }">
				<td>
					<button class="btn btn-md btn-default btn btn-primary btn-small"   id="cancelOBton"  type="button"> <i class="fa  fa-credit-card  m-r-10  "></i>注销</button>
				</td> 			
			</c:if>
			<c:if test = "${cardType eq 23 }">
				<td></td>
			</c:if> --%>
		</tr>
		<%-- <tr id ='packageId' style="display:none"> 
			<td colspan='2'>
				<form:select path="packageType" size="50px" class="selectpicker" title="请选择新卡套餐类型" data-style="btn-warning">
				</form:select>
			</td>
		</tr> --%>
		</table>
		
	</form:form>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" id="closeBtn" data-dismiss="modal">关闭</a>
	<!-- <a href="javaScript:void(0);" class="btn btn-primary btn-small supplyBtn">补卡</a> -->
	<a href="#" class="btn btn-sm btn-success"  id="submit">确定</a>
</div>

</body>
</html>