<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>
		 <c:forEach var="re" items="${RefundDetailType}">
			<c:if test="${fn:escapeXml(re eq vo.refundType)}">${re.value}</c:if>
		</c:forEach>
	</td>
	 <td>${vo.cancelTime }</td> 
	<td> <fmt:formatNumber value="${vo.accountCardBalance/100}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber></td>
	<td> 
	<fmt:formatNumber value="${vo.cancelBalance/100}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber>
	</td>
	
	<td> <fmt:formatNumber value="${vo.postBalance/100}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber></td>
	<c:if test="${jhflag==true }">
		<td> <fmt:formatNumber value="${vo.noChargeFee/100}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber></td>
	</c:if>
	<td> <fmt:formatNumber value="${vo.refundBalance/100}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber></td>
	
	<td>
		<c:if test="${vo.refundType eq 'WTJTF'||vo.refundType eq'ECHDTF' }">
			<a  href="${rootUrl }app/customerservice/finance/cardrefundconfirm/info/${vo.cardId }" class="taiji_modal taiji_acl btn  btn-white btn-small">提交退款金额</a>
		</c:if>
		<a  href="${rootUrl }app/customerservice/finance/cardrefundconfirm/edit/${vo.cardId }" class="taiji_modal_full taiji_acl btn  btn-white btn-small" >最近交易</a>
		 <a  href="${rootUrl }app/customerservice/finance/cardrefundconfirm/edit_lkf/${vo.cardId }" class="taiji_modal_full taiji_acl btn  btn-white btn-small" >老流水交易</a> 
		<a  href="${rootUrl }app/customerservice/finance/cardrefundconfirm/discript/${vo.cardId }" class="taiji_modal taiji_acl btn  btn-white btn-small" >添加描述</a>
		<c:if test="${reverFlag}">
			<a  href="${rootUrl }app/customerservice/finance/cardrefundconfirm/operateRecode/${vo.cardId }" class="taiji_modal_lg taiji_acl btn  btn-white btn-small" >操作记录</a>
		</c:if>
	</td>
</tr>
