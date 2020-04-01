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
				saveDiscript();
			});

		});
		function saveDiscript(){
			var refundCardId = $("#refundCardId").val();
			var description = $("#description").val();
			var data = {};
				data.refundCardId = refundCardId;
				data.description = description;
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			$.ajax({
			      url : rootUrl+"app/customerservice/finance/cardrefundconfirm/saveDiscript",
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
		
		</script>
	</head>
<body>
<input type="hidden" id="refundCardId" value="${cardId }">
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">添加描述信息</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel2" id="myForm" name="myForm" cssClass="form-horizontal">
	<table class="table table-bordered table-striped">
		<tr>
			<td>
				<label class="control-label">请对该条退款进行描述:</label>
			</td>
			<td>
				<textarea rows="3" cols="50" id="description"></textarea>
			</td>
		</tr>
	
		
	</table>
</form:form>
	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal" id="closeBtn">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">确定</a>
</div>

</body>
</html>