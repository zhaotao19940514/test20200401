<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">obu信息</h4>
	</div>
	<div class="modal-body" id="obuInfoView">
		<table class="table table-bordered">
			<tr>
				<th colspan="4" style="color: gray; width: 100%; text-align: center;">obu信息</th>
			</tr>
			<tr>
				<th>obu编号</th>
				<td>${fn:escapeXml(vo.obuId)}</td>
				<th>obu网络编号</th>
                <td>${fn:escapeXml(vo.netId)}</td>
			</tr>
			<tr>
				<th>obu品牌:</th>
				<td>${fn:escapeXml(vo.brand)}</td>
				<th>obu型号:</th>
				<td>${fn:escapeXml(vo.model)}</td>
			</tr>
			<tr>
				<th>用户编号:</th>
				<td>${fn:escapeXml(vo.customerId)}</td>
				<th>车辆编号:</th>
				<td>${fn:escapeXml(vo.vehicleId)}</td>
			</tr>
			<tr>
				<th>obu启用时间:</th>
				<td>${fn:escapeXml(fn:replace(vo.enableTime,'T',' '))}</td>
				<th>obu到期时间:</th>
				<td>${fn:escapeXml(fn:replace(vo.expireTime,'T',' '))}</td>
			</tr>
			<tr>
                <th>obu注册方式:</th>
                <td>
                    <c:if test="${fn:escapeXml(vo.registeredType) eq 1 }">线上</c:if>
                    <c:if test="${fn:escapeXml(vo.registeredType) eq 2 }">线下</c:if>
                </td>
                <th>obu注册渠道编号:</th>
                <td>${fn:escapeXml(vo.registeredChannelId)}</td>
            </tr>
            <tr>
                <th>obu注册时间:</th>
                <td>${fn:escapeXml(fn:replace(vo.registeredTime,'T',' '))}</td>
                <th>obu安装方式:</th>
                <td>
                    <c:if test="${fn:escapeXml(vo.installType) eq 1 }">自行安装</c:if>
                    <c:if test="${fn:escapeXml(vo.installType) eq 2 }">网点安装</c:if>
                </td>
            </tr>
            <tr>
                <th>obu安装/激活地点:</th>
                <td>${fn:escapeXml(vo.installChannelId)}</td>
                <th>obu安装/激活时间 :</th>
                <td>${fn:escapeXml(fn:replace(vo.installTime,'T',' '))}</td>
            </tr>
            <tr>
                <th>obu状态:</th>
                <td>
                    <c:if test="${fn:escapeXml(vo.status) eq 0 }">未安装</c:if>
                    <c:if test="${fn:escapeXml(vo.status) eq 1 }">正常</c:if>
                    <c:if test="${fn:escapeXml(vo.status) eq 2 }">有签挂起</c:if>
                    <c:if test="${fn:escapeXml(vo.status) eq 3 }">无签挂起</c:if>
                    <c:if test="${fn:escapeXml(vo.status) eq 4 }">有签注销</c:if>
                    <c:if test="${fn:escapeXml(vo.status) eq 5 }">无签注销</c:if>
                    <c:if test="${fn:escapeXml(vo.status) eq 6 }">标签挂失</c:if>
                    <c:if test="${fn:escapeXml(vo.status) eq 7 }">标签过期</c:if>
                    <c:if test="${fn:escapeXml(vo.status) eq 8 }">已过户</c:if>
                    <c:if test="${fn:escapeXml(vo.status) eq 9 }">标签禁用</c:if>
                </td>
                <th>obu状态变更时间:</th>
                <td>${fn:escapeXml(fn:replace(vo.statusChangeTime,'T',' '))}</td>
            </tr>
            <tr>
                <th>obu安装结果:</th>
                <td>
                    <c:if test="${fn:escapeXml(vo.status) eq 0 }">成功</c:if>
                    <c:if test="${fn:escapeXml(vo.status) eq 1 }">失败</c:if>
                </td>
                <th>obu安装失败原因:</th>
                <td>${fn:escapeXml(vo.failReason)}</td>
            </tr>
            <tr>
                <th>obu录入时间:</th>
                <td>
                    <fmt:formatDate value="${vo.createTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <th>obu更新时间:</th>
                <td>
                    <fmt:formatDate value="${vo.updateTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
		</table>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>
</body>
</html>