<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/jsp/deviceInclude/obuDeviceHeader.jsp"%>
	<script>
	$(function() {
		$('.modal-body').taiji();
		oldObuId = '${oldObuId}';
		//var flag = 0;
		$("#submit").click(function(){ 
			var vehColor = '${vehColor}';
			var vehiclePlate = '${vehiclePlate}';
			/* if(flag==1){
				$.Taiji.showWarn("请先注销原OBU");
				return;
			} */
			var reader = new ObuOfflineReader();
			var newObuId = $("#newObuId").val();
			if(null==newObuId||""==newObuId||newObuId=='undefined'){
				 $.Taiji.showWarn("请先读OBU");
				return;
			}
			/* reader.openObuDev();
			var checkObuId = reader.readContractSerialNo();
			reader.closeObuDev();
			if(checkObuId != newObuId){
				$.Taiji.showWarn("新电子标签序列号前后不一致,请重新读电子标签");
				return;
			} */
			var supReason = $("#supReason").val();
			if(supReason=='1'){
        		obuRefund();
        	 }else{
        		 doObuCancel('${oldObuId}',obuStatus,'${vehiclePlate}','${vehColor}');
        	 }
		});
		
		$("#readobuBtn").click(function() {
			var reader = new ObuOfflineReader();
			reader.openObuDev();
			var newObuId = reader.readContractSerialNo();
			
			if(isNaN(newObuId)){
				$.Taiji.showWarn("未读到电子标签信息");
				return;
			}
			if(oldObuId==newObuId){
				$.Taiji.showWarn("请将新OBU放在读签器上进行换签");
				return;
			}
			var oldInfo = reader.readSysInfo();
			$("#oldInfo").val(oldInfo);
			$("#newObuId").val(newObuId);
			reader.closeObuDev();
		});
	});
	function obuRefund(){
		$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
		var newObuId = $("#newObuId").val();
		$.ajax({
			url:rootUrl+"app/customerservice/obu/exchange/obuRefund?newObuId="+newObuId,
			type:"POST",
			contentType : 'application/json',
	        dataType : 'json',
			success:function(responseText){
				alert("请收取OBU设备费:"+responseText+"元");
				var misposClient = new MisPosClient();
				var posMoney = responseText;
				//var posMoney = responseText;
				ajaxPosCommand(posMoney,misposClient);//调用pos机消费方法
				console.log(posMoney); 
			},
			error:function(error){
				$.Taiji.hideLoading();
				$.Taiji.showWarn(error.message);
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
		data.obuId = $("#newObuId").val();
        $.ajax({
            url:url+"app/ocx/mispos/posTradeConfirm",
            data:JSON.stringify(data),
            type:'POST',
            contentType : 'application/json',
            dataType : 'json',
            success:function(response){
            	if(response.success){
            		doObuCancel('${oldObuId}',obuStatus,'${vehiclePlate}','${vehColor}');
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
	</head>
<body>
<input id="oldInfo" type="hidden"/>
<input id="vehicleNum" type="hidden" value='${vehiclePlate}'/>
<input id="vecolor" type="hidden" value='${vehColor}'/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">新电子标签信息:</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" method="post">
	<table class="table table-bordered table-striped">
		<tr>
			<td>
				<label for='newObuId' class="control-label">
				<form:input path="newObuId" size="50" cssClass="form-control"
				required="required" placeholder="读新电子标签序列号" /></label>
			</td>
			<td>
				<button class=" btn btn-md btn-default btn btn-success btn-small" id="readobuBtn"  type="button"> <i class="fa  fa-credit-card  m-r-10  "></i>读签</button>
			</td>
		</tr>
		<tr>
			<td>
				<label for='newObuId' class="control-label">
				<form:input path="newObuId" size="50" cssClass="form-control"
				required="required" placeholder="${oldObuId }" readonly="true" /></label>
			</td>
			<!-- <td>
				 <button class="btn btn-md btn-default btn btn-primary btn-small"   id="cancelOBton"  type="button"> <i class="fa  fa-credit-card  m-r-10  "></i>注销</button>
			</td> -->
		</tr>
		<tr>  
			<td colspan='2'>
				<form:select path="supReason" size="50px" class="selectpicker" title="请选择更换原因" data-style="btn-warning" >
					<form:option value="0" selected="selected">自然损坏</form:option>
					<form:option value="1">人为损坏</form:option>
				</form:select>
			</td>
			</tr>
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