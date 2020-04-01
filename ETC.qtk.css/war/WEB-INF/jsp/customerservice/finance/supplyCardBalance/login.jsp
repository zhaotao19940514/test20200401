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
			$("#ETCcardId").change(function(){
				 if($("#ETCcardId").val()==""){
					$.Taiji.showWarn("请确认是否输入了卡号!");
					return;
				}
				if($("#ETCcardId").val().length!=20){
					$.Taiji.showWarn("卡号位数错误 !");
					return;
				}
		 		var cardId = $("#ETCcardId").val();
				var data = {};		
				data.cardId=cardId;
				$.ajax({
					url :"findCardInfoByCardId",
					type : "POST",
					dataType : "json",
					data : JSON.stringify(data),
					contentType: "application/json",
					async:true,
					success : function(response) {
						if(response.status==1){
							$("#cardId").val(response.cardId);
							$("#vehiclePlate").val(response.vehiclePlate);
							$("#vehiclePlateColor").val(response.vehiclePlateColor);
							$("#customerName").val(response.customerName);
							$("#customerIdNum").val(response.customerIdNum);
							$("#enableTime").val(response.enableTime);
							$("#expireTime").val(response.expireTime);
							$("#show1").css("display","");
							$("#show2").css("display","");
							$("#show3").css("display","");
							$("#show4").css("display","");
							$("#show5").css("display","");
						}else{
							 $.Taiji.showWarn('请检查此卡信息是否存在以及卡信息的完整性!');
							 return;
						}
					}
				});
		}); 
			
			
			$("#submit").click(function(){
				debugger;
				var cardId = $("#ETCcardId").val();
				if(cardId==""){
					 $.Taiji.showWarn('请输入要录入补卡额数据的卡号!');
					 return;
				}
				var paidAmount=$("#paidAmount").val()*100;
				if(paidAmount<=0){
					 $.Taiji.showWarn('请输入要补卡额的金额!');
					 return;
				}
				var vehiclePlate=$("#vehiclePlate").val();
				var customerName=$("#customerName").val();
				var enableTime=$("#enableTime").val();
				var expireTime=$("#expireTime").val();
				var vehiclePlateColor=$("#vehiclePlateColor").val();
				var data = {};		
				data.cardId=cardId;
				data.paidAmount=paidAmount;
				data.vehiclePlate=vehiclePlate;
				data.vehiclePlateColor=vehiclePlateColor;
				data.customerName=customerName;
				data.enableTime=enableTime;
				data.expireTime=expireTime;
				$.ajax({
					url :"login",
					type : "POST",
					dataType : "json",
					data : JSON.stringify(data),
					contentType: "application/json",
					async:true,
					success : function(response) {
						if(response.status==1){
							$.Taiji.showNote(response.message);
							$("#close").click();
							$(".taiji_search_submit").click();
						}else{
							 $.Taiji.showWarn('补卡额数据入库失败,请重新入库!');
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
	<h4 class="modal-title">补卡额</h4>
</div>

<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal"  method="post">
		<div class="form-group">
			<p class="caption">录入补卡额数据</p>
			<table border="1">
				<tr>
					<td class="titile">ETC卡号：</td><td class="details"><form:input cssStyle="width:180px" path="cardId" id="ETCcardId" class="dataChange form-control" maxlength="20" /></td>
				</tr>
				<tr>
					<td class="titile">补卡金额：</td><td class="details"><form:input cssStyle="width:180px" path="paidAmount" id="paidAmount" class="dataChange form-control" maxlength="9" /></td>
				</tr>
				<tr style="display: none;">
					<td class="titile">录入日期：</td>
					<td>
 						<form:input  cssStyle="width:180px"   path="enableTime" readonly="true"  cssClass="form-control" />
					    <span class="input-group-btn">
							<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('enableTime'),dateFmt:'yyyyMMdd'});"><i class="fa fa-calendar"></i></button>
						</span>
					</td>
				</tr>
				<tr id="show1" style="display: none">
					<td class="titile">车牌号：</td><td class="details"><form:input cssStyle="width:180px"  readonly="true" path="vehiclePlate" id="vehiclePlate"  class="dataChange form-control" maxlength="20" /></td>
				</tr>
				<tr id="show2" style="display: none">
					<td class="titile">车牌颜色：</td>
						<td  class="details">
							<form:select path="vehiclePlateColor" readonly="true" cssClass="form-control  m-r-5" data-style="btn-white" data-width="100px">
								<form:options items="${vehiclePlateColorTypes}" itemLabel="value" itemValue="typeCode"/>
							</form:select>
					    </td>
				</tr>
				<tr id="show3" style="display: none">
					<td class="titile">用户名称：</td><td class="details"><form:input cssStyle="width:180px"  readonly="true" path="customerName" id="customerName"  class="dataChange form-control" maxlength="20" /></td>
				</tr>	
				<tr id="show4" style="display: none">
					<td class="titile">用户证件号：</td><td class="details"><form:input cssStyle="width:180px"  readonly="true" path="customerIdNum" id="customerIdNum"  class="dataChange form-control" maxlength="20" /></td>
				</tr>
				<tr id="show5" style="display: none" >
					<td class="titile">过期时间：</td>
					<td>
 						<form:input cssStyle="width:180px"   path="expireTime"   readonly="true" cssClass="form-control" />
					    <span class="input-group-btn">
							<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('expireTime'),dateFmt:'yyyyMMdd'});"><i class="fa fa-calendar"></i></button>
						</span>
					</td>
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