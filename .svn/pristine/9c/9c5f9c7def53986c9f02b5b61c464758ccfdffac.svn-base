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
				$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
				var tradeFee=$("#tradeFee").val();
				var id = $("#id").val();
				var cardId = $("#ETCcardId").val();
				var refundFee=$("#refundFee").val()*100;
				var refundDescription=$("#refundDescription").val();
				debugger;
				if(tradeFee<$("#refundFee").val()){
					$.Taiji.hideLoading();
					$.Taiji.showWarn("审核裁定金额不可大于通行消费金额!");
					return;
				}
				var data = {};	
				data.id=id;
				data.cardId=cardId;
				data.refundFee=refundFee;
				data.refundDescription=refundDescription;
				$.ajax({
					url :"finance",
					type : "POST",
					dataType : "json",
					data : JSON.stringify(data),
					contentType: "application/json",
					async:true,
					success : function(response) {
						debugger;
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
	<h4 class="modal-title">消费退费审核</h4>
</div>

<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal"  method="post">
		<div class="form-group">
			<table border="1" style=" margin:auto">
				<tr >
					<td class="titile">流水号：</td><td class="details"><form:input cssStyle="width:180px" readonly="true" path="id" id="id" class="dataChange form-control" /></td>
				</tr>
				<tr>
					<td class="titile">ETC卡号：</td><td class="details"><form:input cssStyle="width:180px" readonly="true"  path="cardId" id="ETCcardId" class="dataChange form-control"  /></td>
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
					<td class="titile">补卡金额：</td><td class="details"><form:input cssStyle="width:180px" readonly="true" path="tradeFee" id="tradeFee" class="dataChange form-control" maxlength="9" /></td>
				</tr>
				<tr>
					<td class="titile">申请退费类型：</td>
						<td  class="details">
							<form:select path="refundType" readonly="true" cssClass="form-control  m-r-5" data-style="btn-white" data-width="100px">
								<form:options readonly="true" items="${refundType}" itemLabel="value" itemValue="code"/>
							</form:select>
					    </td>
				</tr>
				<tr>
					<td class="titile">消费退费申请描述：</td><td class="details"><form:input cssStyle="width:180px"  readonly="true" path="detailedDescription" id="detailedDescription" class="dataChange form-control" maxlength="200" /></td>
				</tr>
				 <tr>
						 <c:if test="${!(fn:escapeXml(pageModel.bankCard eq 'null' ))}">
						<td class="titile">银行卡号：</td><td class="details"><form:input cssStyle="width:180px" readonly="true" path="bankCard" id="bankCard" class="dataChange form-control" minlength="16" maxlength="21" /></td>
						</c:if>
				</tr>
				<tr>
						<c:if test="${!(fn:escapeXml(pageModel.bankCard eq 'null' ))}">
						<td class="titile">银行卡开户行：</td><td class="details"><form:input cssStyle="width:180px" readonly="true"  path="bank" id="bank" class="dataChange form-control"  /></td>
						</c:if>
				</tr>
				<tr>
						<c:if test="${!(fn:escapeXml(pageModel.bankCard eq 'null' ))}">
						<td class="titile">银行卡开户姓名：</td><td class="details"><form:input cssStyle="width:180px" readonly="true" path="bankUserName" id="bankUserName" class="dataChange form-control"  /></td>
						</c:if>
				</tr>
				<tr>
						<td class="titile">用户联系方式：</td><td class="details"><form:input cssStyle="width:180px" readonly="true" path="phone" id="phone" class="dataChange form-control"  minlength="11" maxlength="11" /></td>
				</tr>
				<tr>
					<td class="titile">退费审核裁定金额：</td><td class="details"><form:input cssStyle="width:180px"  path="refundFee" id="refundFee" class="dataChange form-control" maxlength="9" /></td>
				</tr>
				<tr>
						<td class="titile">退费审核裁定描述：</td><td class="details"><form:input cssStyle="width:180px"  path="refundDescription" id="refundDescription" class="dataChange form-control" maxlength="200" /></td>
				</tr>	
						
			</table>
		</div>
</form:form>
</div>
<div class="modal-footer">
     
	<a href="#" class="btn btn-sm btn-white" id="close" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">审核通过</a>
	
</div>

</body>
</html>