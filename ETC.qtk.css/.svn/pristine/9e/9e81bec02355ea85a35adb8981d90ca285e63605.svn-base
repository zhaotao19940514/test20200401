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
			
			
			/*  $("#showFile").click(function(){
				 var fileName=$("#fileName").val();
				 var filePath=$("#filePath").val();
				 var id=$("#id").val();
				 var url = rootUrl+"app/customerservice/finance/expenserefundapplication/showFile?id="+id;
				 window.location.href = url;
				
			}); */
	 
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
	<h4 class="modal-title">消费退费详情</h4>
</div>

<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal"  method="post">
		<div class="form-group">
			<p class="caption">查看消费退费审批信息</p>
			<table border="1">
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
					<td class="titile">录入时间：</td><td class="details"><form:input cssStyle="width:180px" readonly="true"  path="createTime" id="createTime" class="dataChange form-control" maxlength="20" /></td>
				</tr>
				<tr>
					<td class="titile">申请退费金额：</td><td class="details"><form:input cssStyle="width:180px" readonly="true" path="tradeFee" id="tradeFee" class="dataChange form-control" maxlength="9" /></td>
				</tr>
				<tr>
					<td class="titile">申请退费类型：</td>
						<td  class="details">
							<form:select path="refundType" readonly="true"   cssClass="form-control  m-r-5" data-style="btn-white" data-width="100px">
								<form:options items="${refundType}" readonly="true"  itemLabel="value" itemValue="code"/>
							</form:select>
					    </td>
				</tr>
				<tr style="display: none">
					<td class="titile">文件名称：</td><td class="details"><form:input cssStyle="width:180px" readonly="true"  path="fileName" id="fileName" class="dataChange form-control" maxlength="200" /></td>
				</tr >
				<tr style="display: none">
					<td class="titile">文件路径：</td><td class="details"><form:input cssStyle="width:180px" readonly="true"  path="filePath" id="filePath" class="dataChange form-control" maxlength="200" /></td>
				</tr>
				<tr>
					<td class="titile">查看申请上传材料：</td><td class="details"> <a href="${rootUrl }app/customerservice/finance/expenserefundapplication/showFile/${pageModel.id}/${-1}" >下载文件</a></td>
				</tr>
				<tr>
					<td class="titile">查看银行退款凭证：</td><td class="details"><a  href="${rootUrl }app/customerservice/finance/expenserefundapplication/showFile/${pageModel.id}/${5}" class="taiji_modal   taiji_acl">查看文件</a></td>
				</tr>
				<tr>
					<td class="titile">退费审批详细描述：</td><td class="details"><form:input cssStyle="width:180px" readonly="true"  path="detailedDescription" id="detailedDescription" class="dataChange form-control" maxlength="200" /></td>
				</tr>
				 <tr>
				 		
						<td class="titile">银行卡号：</td><td class="details"><form:input cssStyle="width:180px" readonly="true" path="bankCard" id="bankCard" class="dataChange form-control" minlength="16" maxlength="21" /></td>
				</tr>
				<tr>
						<td class="titile">银行卡开户行：</td><td class="details"><form:input cssStyle="width:180px" readonly="true"  path="bank" id="bank" class="dataChange form-control"  /></td>
				</tr>
				<tr>
						<td class="titile">银行卡开户姓名：</td><td class="details"><form:input cssStyle="width:180px" readonly="true" path="bankUserName" id="bankUserName" class="dataChange form-control"  /></td>
				</tr>
				<tr>
						<td class="titile">用户联系方式：</td><td class="details"><form:input cssStyle="width:180px" readonly="true" path="phone" id="phone" class="dataChange form-control"  minlength="11" maxlength="11" /></td>
				</tr>
				<tr>
					    <td class="titile">退费审核裁决金额：</td><td class="details"><form:input cssStyle="width:180px"  readonly="true" path="refundFee" id="refundFee" class="dataChange form-control" maxlength="9" /></td>
				</tr>
						
			</table>
		</div>
</form:form>
</div>
<div class="modal-footer">
     
	<a href="#" class="btn btn-sm btn-white" id="close" data-dismiss="modal">关闭</a>
	
</div>

</body>
</html>