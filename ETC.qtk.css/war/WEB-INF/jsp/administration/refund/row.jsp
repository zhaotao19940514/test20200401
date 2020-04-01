<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td>${vo.transientListno }</td>
	<td>${vo.cardId }</td>
	<td>${vo.vehiclePlate }</td>
	<td>${vo.cardType}</td>
	<td>${vo.inStationName }</td>
	<td>${vo.opTime }</td>
	<td>${vo.exStationName }</td>
	<td>${vo.fee }</td>
	<td>${vo.refundFee }</td>
	<td>${vo.refundType.value }</td>
	<td>
			<a href="${rootUrl }app/administration/refund/view/${vo.id}" class="taiji_modal taiji_acl">详情</a>
			<c:if test="${vo.status != 1}">
			<a href="${rootUrl }app/administration/refund/edit/${vo.id}" class="taiji_modal taiji_acl">退款确认</a>
			</c:if>
		</td>
</tr>
