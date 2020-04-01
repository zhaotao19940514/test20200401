<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">ETC用户信息</h4>
	</div>
	<div class="modal-body" id="customerView">
		<table class="table table-bordered">
			<tr>
				<th colspan="4"
					style="color: gray; width: 100%; text-align: center;">用户信息</th>
			</tr>
			<tr>
				<th>开户人名称:</th>
				<td>${fn:escapeXml(vo.customerName)}</td>
				<th>用户编号:</th>
                <td>${fn:escapeXml(vo.customerId)}</td>
            </tr>
            <tr>
				<th>开户人证件类型:</th>
                <td>
                    <c:if test="${fn:escapeXml(vo.customerIdType) eq 101}">身份证（含临时身份证）</c:if>
                    <c:if test="${fn:escapeXml(vo.customerIdType) eq 102}">护照（限外籍人士）</c:if>
                    <c:if test="${fn:escapeXml(vo.customerIdType) eq 103}">港澳居民来往内地通行证</c:if>
                    <c:if test="${fn:escapeXml(vo.customerIdType) eq 104}">台湾居民来往大陆通行证</c:if>
                    <c:if test="${fn:escapeXml(vo.customerIdType) eq 105}">军官证</c:if>
                    <c:if test="${fn:escapeXml(vo.customerIdType) eq 106}">武警警察身份证</c:if>
                    <c:if test="${fn:escapeXml(vo.customerIdType) eq 201}">统一社会信用代码证书</c:if>
                    <c:if test="${fn:escapeXml(vo.customerIdType) eq 202}">组织机构代码证</c:if>
                    <c:if test="${fn:escapeXml(vo.customerIdType) eq 203}">营业执照</c:if>
                    <c:if test="${fn:escapeXml(vo.customerIdType) eq 204}">事业单位法人证书</c:if>
                    <c:if test="${fn:escapeXml(vo.customerIdType) eq 205}">社会团体法人登记证书</c:if>
                    <c:if test="${fn:escapeXml(vo.customerIdType) eq 206}">律师事务所执业许可证</c:if>
                </td>
                <th>开户人证件号:</th>
                <td>${fn:escapeXml(vo.customerIdNum)}</td>
			</tr>
			<tr>
                <th>开户人/经办人 电话:</th>
                <td>${fn:escapeXml(vo.tel)}</td>
                <th>开户方式:</th>
                <td>${fn:escapeXml(vo.registeredType)}</td>
            </tr>
			<tr>
				<th>发行方编号</th>
				<td>${fn:escapeXml(vo.issuerId)}</td>
				<th>注册日期</th>
                <td>${fn:escapeXml(vo.registeredDate)}</td>
			</tr>
			<tr>
				<th>流水编号:</th>
				<td>${fn:escapeXml(vo.seriseNo)}</td>
				<th>用户类型:</th>
				<td>
				    <c:if test="${fn:escapeXml(vo.customerType) eq 1}">个人</c:if>
                    <c:if test="${fn:escapeXml(vo.customerType) eq 2}">单位</c:if>
				</td>
			</tr>
			<tr>
				<th>注册渠道编号:</th>
				<td>${fn:escapeXml(vo.channelId)}</td>
				<th>开户时间:</th>
				<td>${fn:escapeXml(vo.registeredTime)}</td>
			</tr>
			<tr>
                <th>部门/分支机构名称:</th>
                <td>${fn:escapeXml(vo.department)}</td>
                <th>指定经办人姓名:</th>
                <td>${fn:escapeXml(vo.agentName)}</td>
            </tr>
            <tr>
                <th>指定经办人证件类型:</th>
                <td>
                    <c:if test="${fn:escapeXml(vo.agentIdType) eq 101}">身份证（含临时身份证）</c:if>
                    <c:if test="${fn:escapeXml(vo.agentIdType) eq 102}">护照（限外籍人士）</c:if>
                    <c:if test="${fn:escapeXml(vo.agentIdType) eq 103}">港澳居民来往内地通行证</c:if>
                    <c:if test="${fn:escapeXml(vo.agentIdType) eq 104}">台湾居民来往大陆通行证</c:if>
                    <c:if test="${fn:escapeXml(vo.agentIdType) eq 105}">军官证</c:if>
                    <c:if test="${fn:escapeXml(vo.agentIdType) eq 106}">武警警察身份证</c:if>
                    <c:if test="${fn:escapeXml(vo.agentIdType) eq 201}">统一社会信用代码证书</c:if>
                    <c:if test="${fn:escapeXml(vo.agentIdType) eq 202}">组织机构代码证</c:if>
                    <c:if test="${fn:escapeXml(vo.agentIdType) eq 203}">营业执照</c:if>
                    <c:if test="${fn:escapeXml(vo.agentIdType) eq 204}">事业单位法人证书</c:if>
                    <c:if test="${fn:escapeXml(vo.agentIdType) eq 205}">社会团体法人登记证书</c:if>
                    <c:if test="${fn:escapeXml(vo.agentIdType) eq 206}">律师事务所执业许可证</c:if>
                </td>
                <th>指定经办人证件号:</th>
                <td>${fn:escapeXml(vo.agentIdNum)}</td>
            </tr>
            <tr>
                <th>客户状态:</th>
                <td>
                    <c:choose>
                        <c:when test="${fn:escapeXml(vo.status) eq 1}">正常</c:when>
                        <c:otherwise>异常</c:otherwise>
                    </c:choose>
                </td>
                <th>状态变更时间:</th>
                <td>${fn:escapeXml(vo.statusChangeTime)}</td>
            </tr>
            <tr>
                <th>创建时间:</th>
                <td>
                    <fmt:formatDate value="${vo.createTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <th>更新时间:</th>
                <td>
                    <javatime:format value="${vo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"></javatime:format>
                </td>
            </tr>
			<tr>
				<th>开户人地址:</th>
				<td colspan="3">${fn:escapeXml(vo.address)}</td>
			</tr>
		</table>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>

</body>
</html>