<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		</script>
	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">注销信息</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModelEdit" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/customerservice/card/loss/edit" method="post">
	<table class="table table-bordered table-striped">
	<tr>
			<td>
				<label for='cusType' class="control-label"><b>开户类型</b></label>
			</td>
			 <td>
			 	<label id='cusType'>
				 	<c:if test="${refund.cusType==1}">
						<h4>个人</h4>
					</c:if>
					<c:if test="${refund.cusType==2}">
						<h4>单位</h4>
					</c:if>
			 	</label>
			 		
				
			</td> 
		</tr>
		 <tr>
			<td>
				<label  class="control-label"><b>开户行</b></label>
			</td>
			 <td>
			<select id="bankType" style="width:100%;height:35px"  title="" data-style="btn-warning" disabled="true" >
					<c:forEach var="ba" items="${bankType}">
						<c:if test="${ba.code eq refund.bankType}">
							<option value="${ba.code }">${ba.value }</option>
						</c:if> 
					</c:forEach>
			</select>
			
			</td> 
		</tr>
		<tr>
			<td>
				<label class="control-label">开户行所在省、市</label>
			</td>
			<td>
				<form:input path="province" cssClass="form-control  m-r-5" value="${refund.province },${refund.sell }" required="required" htmlEscape="false" disabled="true" />
			</td>
		</tr>
		<tr>
			<td>
				<label class="control-label">开户人名称</label>
			</td>
			<td>
				<form:input path="cusName" cssClass="form-control  m-r-5" value="${refund.cusName }" required="required" htmlEscape="false" disabled="true" />
			</td>
		</tr>
		<tr>
			<td>
				<label class="control-label">银行卡号</label>
			</td>
			<td>
				<form:input path="bankCardId" cssClass="form-control  m-r-5" onkeyup="this.value =this.value.replace(/\s/g,'').replace(/[^\d]/g,'').replace(/(\d{4})(?=\d)/g,'$1 ');"
				required="required" value="${refund.bankCardId }" disabled="true" htmlEscape="false"/>
			</td>
		</tr>
		<tr id = "banktr">
			<td>
				<label class="control-label">联系方式</label>
			</td>
			<td>
				<form:input path="cusTel" cssClass="form-control  m-r-5" value="${refund.cusTel }" disabled="true" required="required" htmlEscape="false"/>
			</td>
		</tr> 
			</table>
	
</form:form>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" id="closeBtn" data-dismiss="modal">关闭</a>
</div>

</body>
</html>