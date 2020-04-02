<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${(vo.cardId)}</td>
	<td>${vo.paymentFee*-1/100}元</td>
	<td>${vo.handleDate}</td>
	<td>${vo.agencyName}</td>
	<%-- 		<c:if test="${vo.paymentFee <= 0}">
								
								<span>asdsada</span>

	</c:if>
	<c:if test="${vo.paymentFee ne 0}">
		<td>
			<a href="${rootUrl }app/customerservice/card/balance/paymentBack/${vo.cardId}/${vo.paymentFee}" class="taiji_remove {confirm_message:'请确认金额补交完毕    后点击确定 '} taiji_acl m-r-10">金额补交</a>
	</td>
	</c:if> --%>

	<c:choose>
		<c:when test="${vo.paymentFee ne 0}">
			<td><a
				href="${rootUrl }app/customerservice/card/balance/paymentBack/${vo.cardId}/${vo.paymentFee}"
				class="taiji_remove {confirm_message:'请确认金额补交完毕    后点击确定 '} taiji_acl m-r-10">金额补交</a>
				<a
				href="${rootUrl }app/customerservice/card/balance/paymentDetail/${vo.cardId}"
				class="taiji_modal_lg taiji_acl">补交明细</a> <a
				href="${rootUrl }app/customerservice/card/balance/balanceTransaction/${vo.cardId}"
				class="taiji_modal_lg taiji_acl">卡账明细</a></td>
		</c:when>
		<c:otherwise>
			<td>卡账正常 <a
				href="${rootUrl }app/customerservice/card/balance/paymentDetail/${vo.cardId}"
				class="taiji_modal_lg taiji_acl">补交明细</a> <a
				href="${rootUrl }app/customerservice/card/balance/balanceTransaction/${vo.cardId}"
				class="taiji_modal_lg taiji_acl">卡账明细</a></td>
		</c:otherwise>
	</c:choose>


</tr>
