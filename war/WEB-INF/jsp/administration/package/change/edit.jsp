<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#submit").click(function() {
			$("#myManage").triggerHandler("taijiModalPost", [ $("#myForm"), {
				table : "edit"
			} ]);
		});		
	});

	
</script>
</head>
<body>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">套餐变更管理--修改</h4>
	</div>
	<div class="modal-body">
		<form:form modelAttribute="pageModel" id="myForm" name="myForm"
			cssClass="form-horizontal" action="${rootUrl }app/administration/package/change/edit"
			method="post">
			<form:hidden path="id" />
			<table class="table table-bordered table-striped">
				<tr>
					<td>
					<label for='cardId' class="control-label">卡号</label>
					</td>
					<td>
					<form:input path="cardId" cssClass="form-control" required="required" readonly="true" placeholder="记账卡套餐名称必填" />
					</td>
				</tr>
				 <tr>
					<td>
					<label for='packageName' class="control-label">套餐</label>
					</td>
					 <td>  
					  <form:select path="packageName"  class="form-control m-r-5" data-width="180px">
											<c:forEach items="${packageName}" var='p'>
											<form:option value="${ p.packageName }" class='packageName'>${ p.packageName }</form:option>
											</c:forEach>
										</form:select>
					</td>  
				</tr> 
				<tr>
					<td><label class="control-label" for='startTime'>开始时间</label>
					</td>
					<td>
						<div class="form-inline">
							<form:input cssStyle="width:200px" path="startTime"
								readonly="true" cssClass="form-control" />
							<button type="button" class="btn btn-default "
								onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-ddTHH:mm:ss'});">
								<i class="fa fa-calendar"></i>
							</button>
						</div>
					</td>
				</tr>
				 <%-- <tr>
					<td><label class="control-label" for="endTime">失效日期</label>
					</td>
					<td>
						<div class="form-inline">
							<form:input cssStyle="width:200px" path="endTime"
								readonly="true" cssClass="form-control" />
							<button type="button" class="btn btn-default form-control"
								onclick="WdatePicker({el:$dp.$('endTime'),dateFmt:'yyyy-MM-ddTHH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});">
								<i class="fa fa-calendar"></i>
							</button>
						</div>
					</td>
				</tr> --%>
			</table>
			
		</form:form>
   </div>
	
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a> <a
			href="#" class="btn btn-sm btn-success" id="submit">保存</a>
	</div>

</body>
</html>