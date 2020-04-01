<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="display: none"> ${fn:escapeXml(vo.id)} </td>
	<td>${voStatus.index + 1}</td>
	<td style="display: none"> ${fn:escapeXml(vo.cardId)} </td>
	<td style="display: none"> ${fn:escapeXml(vo.vehiclePlate)} </td>
	<%-- <td> ${fn:escapeXml(vo.consumptionType)} </td> --%>
	<td> ${fn:escapeXml(vo.enTime)} </td>
	<td> ${fn:escapeXml(vo.enName)} </td>
	<td> ${fn:escapeXml(vo.exTime)} </td>
	<td> ${fn:escapeXml(vo.exName)} </td>	
	<td> 	   
	    	<fmt:formatNumber type="number" value="${vo.preBalance/100}" pattern="########.##" minFractionDigits="2"/>
	</td>
	<td> 	   
	    	<fmt:formatNumber type="number" value="${vo.fee/100}" pattern="########.##" minFractionDigits="2"/>
	 </td>
	 <td> 	   
	    	<fmt:formatNumber type="number" value="${vo.postBalance/100}" pattern="########.##" minFractionDigits="2"/>
	 </td>
	 <td>
		<c:if test="${vo.status eq '0'}">未申请退费</c:if>
		<c:if test="${vo.status eq '-1'}">待受理</c:if>
		<c:if test="${vo.status eq '2'}">驳回</c:if>
		<c:if test="${vo.status eq '4'}">受理成功，已生成待圈存订单</c:if>
		<c:if test="${vo.status eq '5'}">待银行退费</c:if>
		<c:if test="${vo.status eq '7'}">已银行转账退费</c:if>
	</td>
	<td> ${fn:escapeXml(vo.refundFee/100)} </td>
	<td>
		<c:if test="${vo.status eq '0'}">
			<a href="${rootUrl }app/customerservice/finance/expenserefundapplication/login/${vo.id}/${vo.cardId}"  class="taiji_modal   taiji_acl" >退费申请</a>
		</c:if>
		<%-- <c:if test="${vo.status eq '4'}">
			<a param = '${vo.cardId}'  param2 = '${vo.refundFee}' param3 = '${vo.id}'id="charge" href="#"  class=" taiji_acl btn btn-success" >退费圈存</a>
		</c:if> --%>
		<%-- <c:if test="${vo.status eq '5'}">
			<a param = '${vo.id}' id="trueFinance" href="#"  class="taiji_modal   taiji_acl" >银行退费确认</a>
		</c:if> --%>
		<c:if test="${vo.status eq '-1'}">
			<a href="${rootUrl }app/customerservice/finance/expenserefundapplication/info/${vo.id}/${vo.status}"  class="taiji_modal   taiji_acl" >上传文件</a>
		</c:if>
		<c:if test="${!(vo.status eq '0')}">
			<a href="${rootUrl }app/customerservice/finance/expenserefundapplication/show/${vo.id}"  class="taiji_modal   taiji_acl" >查看详情</a>
		</c:if>
	</td>
		
</tr>
