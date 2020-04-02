<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${voStatus.index + 1}</td>
	<td>${fn:escapeXml(vo.customerId)}</td>
	<td>${fn:escapeXml(vo.customerName)}</td>
	<td>
		<c:forEach items="${types}" var='type'>
			<c:if test="${type.typeCode eq vo.customerIdType}">${type.value}</c:if>
		</c:forEach>
	</td>	<td>${fn:escapeXml(vo.customerIdNum)}</td>
	<td>${fn:escapeXml(vo.agentName)}</td>
	<td>
		<%-- ${fn:replace(fn:escapeXml(vo.registeredTime),'T', ' ')} --%>
		<fmt:formatDate value="${vo.createTime.getTime()}" pattern="yyyy/MM/dd HH:mm:ss" />
	</td>
	<td>
		<a href="${rootUrl }app/customerservice/finance/useraccountmanage/info/${vo.customerId}"  class="taiji_modal_lg taiji_acl btn  btn-success ">修改密码</a>
	</td>
</tr>
