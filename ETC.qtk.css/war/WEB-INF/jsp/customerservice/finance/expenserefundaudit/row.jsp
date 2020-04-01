<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td style="display: none">${fn:escapeXml(vo.id)}</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>${fn:escapeXml(fn:replace(vo.createTime,'T',' '))}</td>
	<td>${fn:escapeXml(vo.tradeFee/100)}</td>
	<td>${fn:escapeXml(vo.refundFee/100)}</td>
	<td>${fn:escapeXml(vo.phone)}</td>
	<td>${fn:escapeXml(vo.bankCard)}</td>
	<td>
		<c:if test="${vo.status eq '5'}">
		<a href="${rootUrl }app/customerservice/finance/expenserefundapplication/info/${vo.id}/${vo.status}"  class="taiji_modal  btn btn-success taiji_acl" >确认退款成功，上传相关凭证</a>
		</c:if>
		<c:if test="${vo.status eq '-1'}">
			<a  href="#"  id="deleteById" param = '${vo.id}' class="taiji_acl btn  btn-success ">驳回</a>
		</c:if>
		<c:if test="${vo.status eq '-1'}">
			<a  href="${rootUrl }app/customerservice/finance/expenserefundaudit/finance/${vo.id}"  
    class="taiji_modal_lg taiji_acl btn  btn-success ">受理</a>
		</c:if>
		<a  href="${rootUrl }app/customerservice/finance/expenserefundaudit/select/${vo.id}"  
    class="taiji_modal_lg taiji_acl btn  btn-success ">查看详情</a>
	</td> 
</tr>
