<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">充值记录</h4>
	</div>
	<div class="modal-body" id="chargeDetailView">
		<table class="table table-bordered">
			<tr>
				<th>交易编号:</th>
				<td>${fn:escapeXml(vo.chargeId)}</td>
				<th>实收金额:</th>
				<td>
				    <fmt:formatNumber value="${vo.paidAmount/100 }" pattern="0.00"/>
				</td>
			</tr>
			<tr>
				<th>赠送金额</th>
				<td>
                    <fmt:formatNumber value="${vo.giftAmount/100 }" pattern="0.00"/>
                </td>
				<th>充值前卡内余额</th>
				<td>
                    <fmt:formatNumber value="${vo.preBalance/100 }" pattern="0.00"/>
                </td>
			</tr>
			<tr>
				<th>充值金额:</th>
				<td>
                    <fmt:formatNumber value="${vo.rechargeAmount/100 }" pattern="0.00"/>
                </td>
				<th>卡号:</th>
				<td>${fn:escapeXml(vo.cardId)}</td>
			</tr>
			<tr>
				<th>交易时间:</th>
				<td>${fn:escapeXml(fn:replace(vo.tradeTime,'T',' '))}</td>
				<th>渠道类型:</th>
				<td>
<%-- 				    ${fn:escapeXml(vo.tradeType)} --%>
				    <c:if test="${vo.tradeType eq 1 }">线上</c:if>
                    <c:if test="${vo.tradeType eq 2 }">线下</c:if>
				</td>
			</tr>
			<tr>
				<th>交易状态:</th>
				<td>
<%-- 				    ${fn:escapeXml(vo.status)} --%>
				    <c:if test="${vo.status eq 0 }">未完成</c:if>
				    <c:if test="${vo.status eq 1 }">已完成</c:if>
				    <c:if test="${vo.status eq 8 }">申请修复</c:if>
				    <c:if test="${vo.status eq 9 }">申请销毁</c:if>
				</td>
				<th>渠道编号:</th>
				<td>${fn:escapeXml(vo.channelId)}</td>
			</tr>
			<tr>
                <th>机构编号:</th>
                <td>${fn:escapeXml(vo.agencyId)}</td>
                <th>创建时间:</th>
                <td>
                    <fmt:formatDate value="${vo.createTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
            <tr>
                <th>交易确认状态:</th>
                <td>
<%--                     ${fn:escapeXml(vo.isConfirmed)} --%>
                    <c:if test="${vo.isConfirmed eq 0 }">未确认</c:if>
                    <c:if test="${vo.isConfirmed eq 1 }">已确认</c:if>
                </td>
                <th>交易修复状态:</th>
                <td>
<%--                     ${fn:escapeXml(vo.completedByFix)} --%>
                    <c:if test="${vo.completedByFix eq 0 }">未修复</c:if>
                    <c:if test="${vo.completedByFix eq 1 }">已修复</c:if>
                </td>
            </tr>
		</table>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>

</body>
</html>