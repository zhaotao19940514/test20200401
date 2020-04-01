<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function(){
		$.ajaxSetup({
            cache : false
        });
	});
</script>
<style type="text/css">
    td{
	    word-wrap:break-word;
	    word-break:break-all;
    }
</style>
</head>
<body>
<div class="modal-header" style="float: left;width: 100%;">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title" style="text-align: center;">发行套餐详情</h4>
</div>
<div class="modal-body" id="myView" style="float: left;width: 100%;">
	<table class="table table-bordered" id="viewTable">
        <tr>
            <th colspan="4" style="font-weight: bold;font-size:14px;text-align: center;">套餐信息</th>
        </tr>
		<tr>
			<th >套餐名称:</th>
            <td>${fn:escapeXml(viewModel.packageName)}</td>
			<th >套餐编号:</th>
			<td >${fn:escapeXml(viewModel.packageNum)}</td>
		</tr>
		<tr>
		    <th >启用时间:</th>
            <td >${fn:escapeXml(viewModel.enableTime)}</td>
		    <th >到期时间:</th>
            <td >${fn:escapeXml(viewModel.expireTime)}</td>
		</tr>
		<tr>
            <th >套餐类型:</th>
            <td >${fn:escapeXml(viewModel.vehicleType)}</td>
            <th >可发设备:</th>
            <td >
            	<c:if test="${viewModel.issueType eq 1}">卡</c:if>
            	<c:if test="${viewModel.issueType eq 2}">OBU</c:if>
            	<c:if test="${viewModel.issueType eq 3}">卡、OBU</c:if>
            </td>
		</tr>
        <tr>
            <th >充值费用:</th>
            	<td><fmt:formatNumber type="number" value="${viewModel.rechargeMoney }" pattern="0.00" maxFractionDigits="2"/></td>
            <th >充值收费方式:</th>
            <td >
            	<c:if test="${viewModel.rechargeType eq 1}">现金</c:if>
            	<c:if test="${viewModel.rechargeType eq 0}">POS</c:if>
            </td>
        </tr>
        <tr>
            <th >卡费用:</th>
            	<td><fmt:formatNumber type="number" value="${viewModel.cardCost }" pattern="0.00" maxFractionDigits="2"/></td>
            <th >卡收费方式:</th>
            <td >
            	<c:if test="${viewModel.cardCostType eq 1}">现金</c:if>
            	<c:if test="${viewModel.cardCostType eq 0}">POS</c:if>
            </td>
        </tr>
        <tr>
            <th >OBU费用:</th>
            	<td><fmt:formatNumber type="number" value="${viewModel.obuCost }" pattern="0.00" maxFractionDigits="2"/></td>
            <th >OBU收费方式:</th>
            <td >
            	<c:if test="${viewModel.obuCostType eq 1}">现金</c:if>
            	<c:if test="${viewModel.obuCostType eq 0}">POS</c:if>
            </td>
        </tr>
	</table>
	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
</body>
</html>