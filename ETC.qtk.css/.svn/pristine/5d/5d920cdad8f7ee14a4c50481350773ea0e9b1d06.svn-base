<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${voStatus.index + 1}</td>
	<c:set var="index" value="${fn:indexOf(vo.vehicleId, '_') }"/>
	<td>${fn:substring(vo.vehicleId, 0, index)}</td>
	<td>
		<c:set var="vehiclePlateColor" value="${fn:substring(vo.vehicleId, index+1,index+2) }"/>
		<c:forEach items="${vehiclePlateColorTypes}" var='vpct'>
			<c:if test="${vpct.typeCode eq vehiclePlateColor}">${vpct.value}</c:if>
		</c:forEach>
	</td>
	<td><a href="${rootUrl }app/apply/baseinfo/commonQuery/issuePackageNumView/${vo.packageNum}" class="taiji_modal_lg taiji_acl">${fn:escapeXml(vo.packageNum)}</a></td>
	<td>${fn:escapeXml(vo.handleTime)}</td>
	<td>${fn:escapeXml(vo.rechargeMoney)}/
		<c:if test="${vo.rechargeType eq 0}">POS</c:if>
		<c:if test="${vo.rechargeType eq 1}">现金</c:if>
	</td>
	<td>${fn:escapeXml(vo.cardCost)}/
		<c:if test="${vo.cardCostType eq 0}">POS</c:if>
		<c:if test="${vo.cardCostType eq 1}">现金</c:if>
	</td>
	<td>${fn:escapeXml(vo.obuCost)}/
		<c:if test="${vo.obuCostType eq 0}">POS</c:if>
		<c:if test="${vo.obuCostType eq 1}">现金</c:if>
	</td>
	<td>${fn:escapeXml(vo.receiveMoney)}</td>
	<td>
		<c:forEach items="${payStatus}" var='status'>
			<c:if test="${status.code eq vo.status}">${status.value}</c:if>
		</c:forEach>
	</td>
	<td>
		<a href="${rootUrl }app/administration/package/issuerecords/view/${vo.id}" class="taiji_modal_lg taiji_acl">详情</a>
		<c:if test="${0 eq vo.status}">
			<span> | </span>
			<a href="${rootUrl }app/administration/package/issuerecords/verifyPay/${vo.id}" class="taiji_modal_lg taiji_acl">确认支付</a>
			<span> | </span>
			<a href="${rootUrl }app/administration/package/issuerecords/verifyRepeal/${vo.id}" class="taiji_operate {confirm_message:'确定要作废？', refresh:true}">作废</a>
		</c:if>
	</td>
</tr>
