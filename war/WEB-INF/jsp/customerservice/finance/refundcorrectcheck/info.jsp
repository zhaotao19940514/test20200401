<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		$(function(){
			$("#submit").click(function(){
				subRefundInfo();
			});

		});
		function subRefundInfo(){
			var refundCardId = $("#refundCardId").val();
			var refundBalance = $("#refundBalance").val()*100;
			var data = {};
				data.refundCardId = refundCardId;
				data.balance = '${balance}';
				data.refundBalance = refundBalance;
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			$.ajax({
			      url : rootUrl+"app/customerservice/finance/cardrefundconfirm/saveRefundInfo",
			      type : "POST",
			      dataType : "json",
			      data:JSON.stringify(data),
			      contentType: "application/json",
			      async:true,
			      success : function(responseText) {
			    	  $.Taiji.hideLoading();
			    	  if(responseText.status==1){
			    		  $.Taiji.showNote(responseText.message);
			    		  $("#searchBtn").click();
			    		  $("#closeBtn").click();
			    	  }else{
			    		  $.Taiji.showWarn(responseText.message);
			    	  }
			      },
			      error:function(responseText){
			      	$.Taiji.showWarn(responseText.message);
			      	$.Taiji.hideLoading();
			      }
	  		});
		}
		
		function onblurText(){
			var text = $("#refundBalance").val();
			if(''==text||null==text||text=='undefined'){
				$("#refundBalance").val('${accountRefundDetail.refundBalance/100}');
			}
			
		}
		function onfocusText(){
			$("#refundBalance").val('');
		}
		function setUnitValue(id,name){
			$("#unit\\.id").val(id);
			$("#unit\\.name").val(name);
		}
		</script>
	</head>
<body>
<input type="hidden" id="refundCardId" value="${cardId }">
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">退款详情</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel2" id="myForm" name="myForm" cssClass="form-horizontal">
	<table class="table table-bordered table-striped">
		<tr>
			<td>
				<label class="control-label">应退款余额</label>
			</td>
			<td>
				<input style="width:280px;color:red;font-size:20px; font-weight:bold;" value="${accountRefundDetail.refundBalance/100}元"  class="form-control" disabled="true" />
			</td>
		</tr>
		<tr>
			<td>
				<label for='refundBalance' class="control-label">退款金额</label>
			</td>
			<td>
				<input  class="form-control" required="required" onblur="onblurText();" onfocus = "onfocusText();"  value="${accountRefundDetail.refundBalance/100}" id="refundBalance" placeholder="请输入退款金额" />
			</td>
		</tr>
		<%--<c:choose>
	 <c:when test="${accountRefundDetail!=null}">
		<tr>
			<td>
				<label for='refundBalance' class="control-label">已退款金额</label>
			</td>
			<td>
				<input style="width:280px;color:red;font-size:20px; font-weight:bold;"  class="form-control" required="required" value="${accountRefundDetail.refundBalance/100}元" disabled="disabled"/>
			</td>
		</tr>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose> --%>
		
	</table>
</form:form>
	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal" id="closeBtn">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">提交</a>
</div>

</body>
</html>