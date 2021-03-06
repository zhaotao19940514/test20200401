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
				//$("#myForm").trigger("submit");
				$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"edit"}]);
			});

		});
		
		function setUnitValue(id,name){
			$("#unit\\.id").val(id);
			$("#unit\\.name").val(name);
		}
		</script>
	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">卡账明细（单位：元）</h4>
</div>
<div class="modal-body">
	
	<table class="table table-bordered table-striped">
	<tr>
			<td>
				<label  class="control-label"><h4>华软卡账</h4></label>
			</td>
			 <td>
			 	<label >
						<h4>${balModel.accountBalance/100 }</h4>
			 	</label>
			</td> 
		</tr>
		<tr>
			<td>
				<label  class="control-label"><h4>2019年后总充值</h4></label>
			</td>
			 <td>
			 	<label >
						<h4>${balModel.chargeBalance/100 }</h4>
			 	</label>
			</td> 
		</tr>
		<tr>
			<td>
				<label  class="control-label"><h4>2019年后总通行</h4></label>
			</td>
			 <td>
			 	<label >
						<h4>${balModel.trafficBalance/100 }</h4>
			 	</label>
			</td> 
		</tr>
		<tr>
			<td>
				<label  class="control-label"><h4>卡账金额</h4></label>
			</td>
			 <td>
			 	<label >
						<h4>${balModel.reckonBalnace/100 }</h4>
			 	</label>
			</td> 
		</tr>
	</table>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>

</body>
</html>