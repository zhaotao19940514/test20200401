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
	<td>${fn:escapeXml(vo.tel)}</td>
	<td>${fn:escapeXml(vo.agentName)}</td>
	<td>
		<%-- ${fn:replace(fn:escapeXml(vo.registeredTime),'T', ' ')} --%>
		<fmt:formatDate value="${vo.createTime.getTime()}" pattern="yyyy/MM/dd HH:mm:ss" />
	</td>
	<td>
	   ${fn:escapeXml(vo.department)}
	</td>
	<td>
		<a href="${rootUrl }app/apply/emergency/usermanager/manageEdit/${vo.customerId}" class="{width:550,height:600}">编辑</a>
	</td>
</tr>
