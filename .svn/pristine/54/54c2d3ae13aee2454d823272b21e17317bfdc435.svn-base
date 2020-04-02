<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>
		<c:choose>
			<c:when test="${vo.cardType eq 1}">记账卡</c:when>
			<c:when test="${vo.cardType eq 2}">储值卡</c:when>
			<c:otherwise>不进行检测</c:otherwise>
		</c:choose>
	</td>
	<td>${fn:escapeXml(vo.agencyId)}</td>
	<td>${fn:escapeXml(vo.channelId)}</td>
	<td>${fn:escapeXml(vo.staffId)}</td>
	<td><fmt:formatDate value="${vo.createTime.getTime()}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
	<td>
			 <c:if test="${vo.cardType eq 2}">
				<a href="${rootUrl }app/customerservice/card/mancancel/edit/${vo.cardId}" class="taiji_modal taiji_acl btn  btn-white btn-small">退款详情</a>
			</c:if> 
			<%--  <c:if test="${accountCardBalanceOperate.type eq 'BANK_CARD'}">
				<a href="${rootUrl }app/customerservice/card/mancancel/update/${vo.cardId}" class="taiji_modal taiji_acl btn  btn-white btn-small">银行卡号修改</a>
			 </c:if>  --%>
		
	</td>
</tr>
