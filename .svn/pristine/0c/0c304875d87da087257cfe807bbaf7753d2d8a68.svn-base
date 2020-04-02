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
			$.ajaxSetup({
				cache : false
			});
			$("#updateSub").click(function(){
				$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
				var bankcard = $("#bankcard").val().replace(/\s*/g,'');
				if(null==bankcard||bankcard==''){
					$.Taiji.showWarn("新银行卡号不能为空");
					$.Taiji.hideLoading();
					return;
				}
				var regStr = /^([1-9]{1})(\d{15,20})$/;
				if(!regStr.test(bankcard)){
					$.Taiji.showWarn("请正确输入银行卡号!");
					$.Taiji.hideLoading();
					return;
				}
				var  cardId = '${accountCardBalanceOperate.cardId}';
				var data={};
				data.bankcard = bankcard;
				data.cardId  = cardId;
				$.ajax({
			        url : rootUrl+"app/customerservice/card/mancancel/update",
			        type : "POST",
			        dataType : "json",
			        data:JSON.stringify(data),
			        contentType: "application/json",
			        async:true,
			        success : function(responseText) {
			        	$.Taiji.hideLoading();
			        	$("#closeBtn").click();
			        	if(responseText.status==1){
			        		$.Taiji.showNote("成功");
			        	} 
			        },
			        error:function(responseText){
			        	$.Taiji.showWarn(responseText.responseText);
			        	$.Taiji.hideLoading();
			        }
			    });
			})
		});				
		</script>
	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">银行卡号修改</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel1" id="myForm" name="myForm" cssClass="form-horizontal">
		<div class="form-group">
		 <table class="table table-bordered table-striped" style="width:80%;margin:5%">
			<!-- <table border="1" style="font-size:14px"> -->
				<tr>
					<td class="titile">原银行卡号</td>
					<td class="details">
					<%-- <form:input cssStyle="width:180px" path="customerName" value='${customerInfo.customerName}' class="form-control" disabled="true" /> --%>
					<input style="width:280px;font-size:15px;font-weight:bold;" value="${accountCardBalanceOperate.bankCardId }" class="form-control" disabled="true"/>
					</td>
				</tr>
				<tr>
					<td class="titile">新银行卡号</td>
					<td class="details">
					<%-- <form:input cssStyle="width:180px" path="customerName" value='${customerInfo.customerName}' class="form-control" disabled="true" /> --%>
					<input style="width:280px;font-size:15px;font-weight:bold;" id="bankcard" class="form-control" onkeyup="this.value =this.value.replace(/\s/g,'').replace(/[^\d]/g,'').replace(/(\d{4})(?=\d)/g,'$1 ');"/>
					</td>
				</tr>
			</table>
	</div>
</form:form>	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" id="closeBtn" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="updateSub">确定</a>
</div>

</body>
</html>