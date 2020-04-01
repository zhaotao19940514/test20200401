<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function(){
		$.ajaxSetup ({ cache: false});
		$("#myManageEdit").taiji();
		$("#submit").click(function(){
			$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"edit"}]);
		});
	});
</script>
<style type="text/css">
	.titleW{
		width: 120px
	}
	#tableContent td{
		vertical-align:middle;
	}
	#tableContent th{
		vertical-align:middle
	}
</style>
</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">确认支付</h4>
</div>
<div id="myManageEdit" class="modal-body" style="overflow: auto;">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/administration/package/issuerecords/verifyPay" method="post">
	<table id="tableContent" class="table table-bordered table-striped">
		<tr>
			<th class="titleW">创建时间：</th>
			<td colspan="1">${fn:escapeXml(pageModel.handleTime)}</td>
			<th class="titleW">更新时间：</th>
			<td colspan="1">${fn:escapeXml(pageModel.updateTime)}</td>
			<th class="titleW">套餐编号：</th>
			<td colspan="1"><a href="${rootUrl }app/apply/baseinfo/commonQuery/issuePackageNumView/${pageModel.packageNum}" class="taiji_modal_lg taiji_acl">${fn:escapeXml(pageModel.packageNum)}</a></td>
			<th class="titleW">套餐状态：</th>
			<td colspan="1">
				<c:forEach items="${issuePackagePayStatus }" var="payStatus">
					<c:if test="${payStatus.code eq pageModel.status }">${payStatus.value }</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>员工编号：</th>
			<td><a href="${rootUrl }app/apply/baseinfo/commonQuery/staffIdView/${pageModel.staffId }" class="taiji_modal_lg taiji_acl">${fn:escapeXml(pageModel.staffId)}</a></td>
			<th>网点编号：</th>
			<td><a href="${rootUrl }app/apply/baseinfo/commonQuery/servicehallIdView/${pageModel.serviceHallId }" class="taiji_modal_lg taiji_acl">${fn:escapeXml(pageModel.serviceHallId)}</a></td>
			<th>车牌编号：</th>
			<td>${fn:escapeXml(pageModel.vehicleId)}</td>
			<th>套餐总金额（元）：</th>
			<td><fmt:formatNumber type="number" value="${pageModel.receiveMoney }" pattern="0.00" maxFractionDigits="2"/></td>
		</tr>
		<tr>
			<th>充值金额（元）：</th>
			<td><fmt:formatNumber type="number" value="${pageModel.rechargeMoney }" pattern="0.00" maxFractionDigits="2"/></td>
			<th>充值收费方式：</th>
			<td>
				<c:if test="${pageModel.rechargeType eq 0 }">POS</c:if>
				<c:if test="${pageModel.rechargeType eq 1 }">现金</c:if>
			</td>
			<th>卡费用（元）：</th>
			<td><fmt:formatNumber type="number" value="${pageModel.cardCost }" pattern="0.00" maxFractionDigits="2"/></td>
			<th>卡费用收费方式：</th>
			<td>
				<c:if test="${pageModel.cardCostType eq 0 }">POS</c:if>
				<c:if test="${pageModel.cardCostType eq 1 }">现金</c:if>
			</td>
		</tr>
		<tr>
			<th>obu费用（元）：</th>
			<td><fmt:formatNumber type="number" value="${pageModel.obuCost }" pattern="0.00" maxFractionDigits="2"/></td>
			<th>obu费用收费方式：</th>
			<td>
				<c:if test="${pageModel.obuCostType eq 0 }">POS</c:if>
				<c:if test="${pageModel.obuCostType eq 1 }">现金</c:if>
			</td>
			<th>备注：</th>
			<td colspan="3">${fn:escapeXml(pageModel.remarks)}</td>
		</tr>
		<tr>
			<th>卡发行状态：</th>
			<td>
				<c:forEach items="${issueStatusTypes }" var="issueStatus">
					<c:if test="${issueStatus.code eq pageModel.cardIssueStatus }">${issueStatus.value }</c:if>
				</c:forEach>
			</td>
			<th>卡号：</th>
			<td>${fn:escapeXml(pageModel.cardId)}</td>
			<th>obu发行状态：</th>
			<td>
				<c:forEach items="${issueStatusTypes }" var="issueStatus">
					<c:if test="${issueStatus.code eq pageModel.obuIssueStatus }">${issueStatus.value }</c:if>
				</c:forEach>
			</td>
			<th>obu号：</th>
			<td>${fn:escapeXml(pageModel.obuId)}</td>
		</tr>
		<tr style="display: none;">
			<td colspan="8"><form:input cssStyle="width:180px" path="id" class="form-control"  placeholder="必填"/></td>
		</tr>
		<tr>
			<td class="titile" style="color: red;">* 请录入备注：</td>
			<td colspan="7"><form:textarea path="remarks" class="form-control" maxlength="120"/></td>
		</tr>
		<tr>
			<td colspan="8">
				<span style="font-size: 14px;color: red;">注：POS收费成功，自动确认支付失败，手动确认支付，请输入备注信息，如刷POS的小票编号或其他可以说明收费成功的信息，请认真核查和录入备注信息后再进行操作，否则后果网点自行负责！</span>
			</td>
		</tr>
	</table>
</form:form>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">已收费确认支付</a>
</div>
	
</body>
</html>