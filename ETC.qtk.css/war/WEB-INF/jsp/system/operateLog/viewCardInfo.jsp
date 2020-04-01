<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">卡信息</h4>
	</div>
	<div class="modal-body" id="cardIntoView">
		<table class="table table-bordered">
			<tr>
				<th>卡编号:</th>
				<td>${fn:escapeXml(vo.cardId)}</td>
				<th>卡网络编号:</th>
				<td>${fn:escapeXml(vo.netId)}</td>
			</tr>
			<tr>
				<th>卡类型:</th>
                <td>${fn:escapeXml(vo.cardType)}</td>
				<th>卡品牌</th>
				<td>${fn:escapeXml(vo.brand)}</td>
			</tr>
			<tr>
				<th>卡型号:</th>
				<td>${fn:escapeXml(vo.model)}</td>
				<th>机构编号:</th>
				<td>${fn:escapeXml(vo.agencyId)}</td>
			</tr>
			<tr>
				<th>用户编号:</th>
				<td>${fn:escapeXml(vo.customerId)}</td>
				<th>车辆编号:</th>
				<td>${fn:escapeXml(vo.vehicleId)}</td>
			</tr>
			<tr>
                <th>卡生效时间:</th>
                <td>${fn:replace(fn:escapeXml(vo.enableTime),'T',' ')}</td>
                <th>卡失效时间:</th>
                <td>${fn:replace(fn:escapeXml(vo.expireTime),'T',' ')}</td>
            </tr>
            <tr>
                <th>渠道编号:</th>
                <td>${fn:escapeXml(vo.channelId)}</td>
                <th>发行类型:</th>
                <td>
                    <c:if test="${fn:escapeXml(vo.issuedType) eq 1}">线上</c:if>
                    <c:if test="${fn:escapeXml(vo.issuedType) eq 2}">线下</c:if>
                </td>
            </tr>
            <tr>
                <th>发行时间:</th>
                <td>${fn:replace(fn:escapeXml(vo.issuedTime),'T',' ')}</td>
                <th>卡状态:</th>
                <td>
                    <c:if test="${vo.status eq 0}">申请</c:if>
                    <c:if test="${vo.status eq 1}">正常</c:if>
                    <c:if test="${vo.status eq 2}">有卡挂起</c:if>
                    <c:if test="${vo.status eq 3}">无卡挂起</c:if>
                    <c:if test="${vo.status eq 4}">有卡注销</c:if>
                    <c:if test="${vo.status eq 5}">无卡注销</c:if>
                    <c:if test="${vo.status eq 6}">卡挂失</c:if>
                    <c:if test="${vo.status eq 9}">预注销</c:if>
                    <c:if test="${vo.status eq 99}">已删除</c:if>
                </td>
            </tr>
            <tr>
                <th>卡状态变更时间:</th>
                <td>${fn:replace(fn:escapeXml(vo.statusChangeTime),'T',' ')}</td>
                <th>套餐类型:</th>
                <td>${fn:escapeXml(vo.packageType)}</td>
            </tr>
            <tr>
                <th>套餐编号:</th>
                <td>${fn:escapeXml(vo.packageId)}</td>
                <th>套餐生效时间:</th>
                <td>${fn:replace(fn:escapeXml(vo.packageEnableTime),'T',' ')}</td>
            </tr>
            <tr>
                <th>账户机构:</th>
                <td>${fn:escapeXml(vo.accountOrganization)}</td>
                <th>账户卡编号:</th>
                <td>${fn:escapeXml(vo.accountCardNo)}</td>
            </tr>
            <tr>
                <th>创建时间:</th>
                <td>
                    <fmt:formatDate value="${vo.createTime.time}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>
                <th>更新时间:</th>
                <td>
                    <fmt:formatDate value="${vo.updateTime.time}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>
            </tr>
		</table>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>

</body>
</html>