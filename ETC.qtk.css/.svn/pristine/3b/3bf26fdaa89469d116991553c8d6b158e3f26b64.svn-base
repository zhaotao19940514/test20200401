<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
	<script>
		$(function(){
			$("#submit").click(function(){
				var cardReader = new CardReader();
				var card =cardReader.getCardId();
				var cardId=$("#cardId").val(); 
				var status=$("#status").val();
				if(status==4 || status==5){
					$.Taiji.showWarn("此卡已被注销，无法进行卡重写!");
					return;
				}
				if(status==9){
					$.Taiji.showWarn("此卡已被预注销，无法进行卡重写!");
					return;
				}
				if(isNaN(card)){
					$.Taiji.showWarn("读卡器未读到卡片,请检查设备上是否有卡以及设备是否正常工作!");
					return;
				}
				if(!card==cardId){
					$.Taiji.showWarn("读卡器读到的卡片与当前修改的卡片不是同一张!请确认!");
					return;
				}
				  	var vehiclePlate=$("#vehiclePlate").val();
				  	var vehiclePlateColor=$("#vehiclePlateColor").val();
					var customerName =$("#customerName").val();
					var customerIdNum=$("#customerIdNum").val();
					var expireTime=$("#expireTime").val();
				  if(confirm("确定要给"+cardId+"这张卡修改信息？\n\n请确认！")){
					 	var data = {};
					 	data.cardId=cardId;
						data.vehiclePlate = vehiclePlate;
						data.vehiclePlateColor=vehiclePlateColor;
						data.customerName=customerName;
						data.customerIdNum=customerIdNum;
						data.expireTime=expireTime;
						$("#submit").attr("disabled",true); 
						applyCardOrderConfirm(data,cardReader);
						$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
				  }
				  else{
						 return null;
				  }
			}); 
		 
		  
		   function applyCardOrderConfirm(dataObj,cardReader){
			  var checkCardId = cardReader.getCardId();
			  var cardId = dataObj.cardId;
			  if(cardId!=checkCardId){
				  $.Taiji.showWarn('卡号前后读取不一致,请重新读卡查询');
				  return;
			  }
			  var method = 'POST';
			  var url = rootUrl+"app/customerservice/card/cardInformationChange/applyCardOrderConfirm";
			  var cardInfoChangeStatus=0;
			  var data={};
			  data.cardId=dataObj.cardId;
			  data.applyStep=null;
			  data.vehiclePlate = dataObj.vehiclePlate;
			  data.vehiclePlateColor=dataObj.vehiclePlateColor;
			  data.customerName=dataObj.customerName;
			  data.customerIdNum=dataObj.customerIdNum;
			  data.cardInfoChangeStatus=cardInfoChangeStatus;
			  data.expireTime=dataObj.expireTime;
			  var terminateConditions = "data.cardInfoChangeStatus == 1";
			  cardReader.openCard();
			  cardReader.autoExecuteApdu(url,data,method,terminateConditions,function(){
				    cardReader.closeCard();
					saveInformation(dataObj);
			  });
			 
			} 
		   
		   
		   function saveInformation(data){
				  console.log(data);
					$.ajax({
						url : "saveInformation",
						type : "POST",
						data:JSON.stringify(data),
						dataType : "json",
						contentType: "application/json",
						async:true,
						success : function(responseText) {
							if(responseText.status==1){
								$.Taiji.hideLoading();
								$("#close").click();
								$.Taiji.showNote(responseText.message);
								$("#close").click();
								$("#listForm").submit();
							}else{
								$.Taiji.hideLoading();
								$("#close").click();
								$.Taiji.showWarn(responseText.message);
							}
						}
					});
				}
		  
		  
	
		});
	</script>	
		
<style type="text/css">
     .titile{
     	text-align:right;
     	font-size: 13px;
     };
     .details{
     	text-align:left;
     }
     .caption{
     		font-size: 15px;
     		font-weight: bold;
     		margin: 0px;
     }
</style>
	</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">修改卡片信息</h4>
</div>

<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/customerservice/card/cardInformationChange/edit" method="post">
		<div class="form-group">
			<p class="caption">卡片信息</p>
			
			<table border="1">
				<tr>
					<td class="titile">ETC号：</td><td class="details"><form:input cssStyle="width:180px" path="cardId" id="cardId" readonly="true" class="dataChange form-control" maxlength="9" /></td>
				</tr>
				<tr>
					<td class="titile">ETC卡状态：</td><td class="details"><form:input cssStyle="width:180px" path="status" id="status" readonly="true" class="dataChange form-control" maxlength="9" /></td>
				</tr>
				<tr>
					<td class="titile">用户姓名：</td><td class="details"><form:input cssStyle="width:180px" path="customerName" id="customerName" readonly="true" class="dataChange form-control" maxlength="9" /></td>
				</tr>
				<tr>
					<td class="titile">身份证号码：</td><td class="details"><form:input cssStyle="width:180px" path="customerIdNum" id="customerIdNum"  readonly="true" class="dataChange form-control" maxlength="9" /></td>
				</tr>
			    <tr>
					<td class="titile">过期时间：</td><td class="details"><form:input cssStyle="width:180px" path="expireTime" id="expireTime" readonly="true" class="dataChange form-control" maxlength="9" /></td>
				</tr> 
				<tr>
					<td class="titile">车牌号码：</td><td class="details"><form:input cssStyle="width:180px" path="vehiclePlate" id="vehiclePlate"  class="dataChange form-control" maxlength="9" /></td>
				</tr>
				<tr>
					<td class="titile">车牌颜色：</td><td class="details">
						<form:select path="vehiclePlateColor" id="vehiclePlateColor"  cssClass="disabledRemove form-control m-r-5" data-style="btn-white" data-width="120px">
							<form:option value="">--请选择--</form:option>
							<form:options items="${vehiclePlateColorTypes}" itemLabel="value" itemValue="typeCode"/>
						</form:select>
					</td>
				</tr>
			</table>
			</div>
</form:form>

</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal" id="close">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>