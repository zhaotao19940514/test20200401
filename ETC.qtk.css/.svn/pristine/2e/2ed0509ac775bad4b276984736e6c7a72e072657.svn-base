<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td> ${fn:escapeXml(vo.handleDate)} </td>
	<td> 工商银行 </td>
	<td>
	   <c:if test="${vo.rechargeCount eq null}">0</c:if>
	   <c:if test="${!(vo.rechargeCount eq null)}"> ${vo.rechargeCount}</c:if>
	 </td>
	<td>
	     <fmt:formatNumber type="number" value="${vo.rechargeAmount/100}" pattern="########.##" minFractionDigits="2"/>
      </td>
	<td>
	       <c:if test="${vo.refundCount eq null}">0</c:if>
	     <c:if test="${!(vo.refundCount eq null)}">${vo.refundCount}</c:if>
	 </td>
	<td>
		     <fmt:formatNumber type="number" value="${vo.refundAmount/100}" pattern="########.##" minFractionDigits="2"/>
	 </td>
</tr>
