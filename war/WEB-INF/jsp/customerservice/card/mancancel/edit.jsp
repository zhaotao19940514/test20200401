<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
	<meta http-equiv="expires" content="0" />
	<meta http-equiv=
	"pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript">
	$(function(){
// 		$("#myView").taiji();
	    $("#tab").taiji();
	});			
		/* function confirmRefund(){
			  $.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			  var cardBalance = $("#cardBalance").val();
			  var balanceType = $("#balanceType").val();
			  var customerId = '${customerInfo.customerId}';
			  var cardId = '${cardId}';
			  var data = {};
			  data.cardBalance = cardBalance;
			  data.balanceType = balanceType;
			  data.cardId = cardId;
			  data.customerId = customerId;
			  $.ajax({
		          url : rootUrl+"app/customerservice/card/mancancel/confirmRefund",
		          type : "POST",
		          dataType : "json",
		          data:JSON.stringify(data),
		          contentType: "application/json",
		          async:true,
		          success : function(responseText) {
		        	  $.Taiji.hideLoading();
		        	  var str;
		        	  if(balanceType=='ACCOUNT'){
		        		  str = "余额已转入该用户账户";
		        	  }
		        	  if(balanceType=='BANK_CARD'){
		        		  str="请将余额转入该用户银行卡";
		        	  }
		        	  if(balanceType=='CASH'){
		        		  str = "需支付给该用户的余额为:"+cardBalance/100+"元";
		        	  }
		        	  if(responseText.status==1){
		        	 	 alert(str);
		        	  }else{
		        		  alert(responseText.message);
		        	  }
		          },
		          error:function(responseText){
		          	$.Taiji.showWarn(responseText.message);
		          	$.Taiji.hideLoading();
		          }
		      });

		}	 */
		</script>
	</head>
<body>
<input type="hidden" id="cardBalance" value="${accountCardBalance}" />
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">销卡详情</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="queryModel1" id="myForm" name="myForm" cssClass="form-horizontal">
		<div class="form-group">
		 <table class="table table-bordered table-striped" id="tab" style="width:80%;margin:5%">
			<!-- <table border="1" style="font-size:14px"> -->
				<tr>
					<td class="titile">客户名称：</td>
					<td class="details">
					<%-- <form:input cssStyle="width:180px" path="customerName" value='${customerInfo.customerName}' class="form-control" disabled="true" /> --%>
					<input style="width:280px;font-size:15px;font-weight:bold;" value="${accountRefundDetail.customerName}" class="form-control" disabled="true"/>
					</td>
				</tr>
				<tr>	
					<td class="titile">证件号：</td>
					<td class="details">
						<input style="width:280px;font-size:15px;font-weight:bold;" value="${customerInfo.customerIdNum }" class="form-control" disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="titile">退款方式:</td>
					<td class="details">
						<c:forEach var="ba" items="${AccountCardBalanceOperateType}">
							<c:if test="${ba eq accountCardBalanceOperate.type}">
								<input style="width:280px;font-size:15px;font-weight:bold;" value="${ba.value}"  class="form-control" disabled="true"/>
							</c:if>
						</c:forEach>
					<%-- <form:select path="balanceType" class="form-control" data-style="btn-warning" style="text-align:center;font-size:15px" disabled="true">
						<form:options items="${AccountCardBalanceOperateType}" itemLabel="value"/>
					</form:select> --%>
					</td>
				</tr>
				<c:if test="${accountCardBalanceOperate.type eq'BANK_CARD'}">
					<tr>	
						<td class="titile">银行卡号:</td>
						<td class="details">
							<input style="width:280px;font-size:15px;font-weight:bold;" value="${accountCardBalanceOperate.bankCardId}" class="form-control" disabled="true"/>
						</td>
					</tr>	
				</c:if>
				<tr>	
					<td class="titile">退款状态:</td>
					<td class="details">
						<input style="width:280px;font-size:15px;font-weight:bold;" class="form-control" disabled="true" value='${refundDetailType }'>
					</td>
				</tr>
				<tr>
					<td class="titile">退款金额:</td> 
					<td colspan='3' class="details">
						 <c:choose>
							<c:when test="${flag}">
										<a href="${rootUrl }app/customerservice/card/mancancel/view/${accountRefundDetail.cardId}"  class="taiji_modal_lg">${fn:escapeXml(accountRefundDetail.refundBalance/100)}元</a>
							</c:when>
							<c:otherwise>
								<input style="width:280px;color:red;font-size:20px; font-weight:bold;" value="未过资金争议期，无法查看"  class="form-control" disabled="true" />
							</c:otherwise>
						</c:choose> 
					</td>
				</tr>
			</table>
	</div>
</form:form>	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" id="closeBtn" data-dismiss="modal">关闭</a>
	<!-- <a href="#" class="btn btn-sm btn-success"  id="submit">确定</a> -->
</div>

</body>
</html>