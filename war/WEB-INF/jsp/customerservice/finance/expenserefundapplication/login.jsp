<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
	<script type="text/javascript" src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" ></script>
	<script>
		$(function(){
			$.ajaxSetup({cache : false});
			 $("#myManage").taiji({
				enableAclCheck:true,
				search:{
					 autoSearch:false,
					 search:{
						 autoSearch:false
						}
				}
			}); 
			
		
			
			$("#submit").click(function(){
				debugger;
				var id = $("#loginId").val();
				var cardId = $("#loginCardId").val();
				var tradeFee=$("#tradeFee").val()*100;
				var createTime=$("#createTime").val();
				var bankCard=$("#bankCard").val();
				var bank=$("#bank").val();
				var vehiclePlate=$("#vehiclePlate").val();
				var vehiclePlateColor=$("#vehiclePlateColor").val();
				var tradeTime=$("#tradeTime").val();
				var refundType=$("#refundType").val();
				var detailedDescription=$("#detailedDescription").val();
				var bankUserName=$("#bankUserName").val();
				var phone=$("#phone").val();
				var data = {};	
				data.id=id;
				data.cardId=cardId;
				data.tradeFee=tradeFee;
				data.createTime=createTime;
				data.bankCard=bankCard;
				data.bank=bank;
				data.vehiclePlate=vehiclePlate;
				data.vehiclePlateColor=vehiclePlateColor;
				data.tradeTime=tradeTime;
				data.refundType=refundType;
				data.detailedDescription=detailedDescription;
				data.bankUserName=bankUserName;
				data.phone=phone;
				$.ajax({
					url :"login",
					type : "POST",
					dataType : "json",
					data : JSON.stringify(data),
					contentType: "application/json",
					async:true,
					success : function(response) {
						if(response.status==1){
							$.Taiji.hideLoading();
							$.Taiji.showNote(response.message);
							$("#close").click();
							$(".taiji_search_submit").click();
						}else{
							$.Taiji.hideLoading();
							 $.Taiji.showWarn(response.message);
							 $("#close").click();
							 $(".taiji_search_submit").click();
							 return;
						}
					}
				});
				
			});
	
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
	<h4 class="modal-title" style=" margin:auto">消费退费申请</h4>
</div>

<div class="modal-body">
<form:form modelAttribute="loginModel" id="loginForm" name="loginForm" cssClass="form-horizontal"  method="post">
		<div  class="modal-body">
			<table border="1" style=" margin:auto">
				<tr >
					<td class="titile">流水号：</td><td class="details"><form:input cssStyle="width:180px" readonly="true" path="loginId" class="dataChange form-control" /></td>
				</tr>
				<tr>
					<td class="titile">ETC卡号：</td><td class="details"><form:input cssStyle="width:180px" readonly="true"  path="loginCardId"  class="dataChange form-control"  /></td>
				</tr>
				<tr>
					<td class="titile">车牌号：</td><td class="details"><form:input cssStyle="width:180px" readonly="true"  path="vehiclePlate" id="vehiclePlate" class="dataChange form-control" /></td>
				</tr>
				<tr>
					<td class="titile">车牌颜色：</td>
						<td style="display: true" class="details">
							<form:select path="vehiclePlateColor" readonly="true"   cssClass="form-control  m-r-5" data-style="btn-white" data-width="100px">
								<form:options items="${vehiclePlateColorTypes}"  readonly="true"  itemLabel="value" itemValue="typeCode"/>
							</form:select>
					    </td>
				</tr>
				<tr>
					<td class="titile">通行时间：</td><td class="details"><form:input cssStyle="width:180px" readonly="true"  path="tradeTime" id="tradeTime" class="dataChange form-control" maxlength="20" /></td>
				</tr>
				<tr>
					<td class="titile">申请补卡金额：</td><td class="details"><form:input cssStyle="width:180px" path="tradeFee" id="tradeFee" class="dataChange form-control" maxlength="9" /></td>
				</tr>
				<tr>
					<td class="titile">申请退费类型：</td>
						<td  class="details">
							<form:select path="refundType"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="100px">
								<form:options items="${refundType}" itemLabel="value" itemValue="code"/>
							</form:select>
					    </td>
				</tr>
				<tr>
					<td class="titile">详细描述：</td><td class="details"><form:input cssStyle="width:180px" path="detailedDescription" id="detailedDescription" class="dataChange form-control" maxlength="200" /></td>
				</tr>
				 <tr>
				 	<c:if test="${fn:escapeXml(loginModel.cardType eq '2' )}">
						<td class="titile">银行卡号：</td><td class="details"><form:input cssStyle="width:180px" path="bankCard" id="bankCard" class="dataChange form-control" minlength="16" maxlength="21" /></td>
					</c:if>
				</tr>
				<tr>
				 	<c:if test="${fn:escapeXml(loginModel.cardType eq '2')}">
						<td class="titile">银行卡开户行：</td><td class="details"><form:input cssStyle="width:180px" path="bank" id="bank" class="dataChange form-control"  /></td>
					</c:if>
				</tr>
				<tr>
				 	<c:if test="${fn:escapeXml(loginModel.cardType eq '2')}">
						<td class="titile">银行卡开户姓名：</td><td class="details"><form:input cssStyle="width:180px" path="bankUserName" id="bankUserName" class="dataChange form-control"  /></td>
					</c:if>
				</tr>
				<tr>
						<td class="titile">用户联系方式：</td><td class="details"><form:input cssStyle="width:180px" path="phone" id="phone" class="dataChange form-control"  minlength="11" maxlength="11" /></td>
				</tr>
				
						
						
			</table>
		</div>
</form:form>
</div>
<div class="modal-footer">
     
	<a href="#" class="btn btn-sm btn-white" id="close" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
	
</div>

</body>
</html>