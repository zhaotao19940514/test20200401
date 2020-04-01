<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td> ${fn:escapeXml(vo.createTime)} </td>
	<td> ${fn:escapeXml(vo.amount/100)}元 </td>
	<td> ${fn:escapeXml(vo.bankCardNo)} </td>
	<td> ${fn:escapeXml(vo.rspMessage)} </td>
	<td>
		<c:if test="${fn:escapeXml(vo.isCompleted)}">
			<a param = '${vo.amount }' href="#" id="unLossBtn" class="taiji_acl m-r-10">撤销</a>			
		</c:if>
		<c:if test="${fn:escapeXml(!vo.isCompleted)}">
			${fn:escapeXml(vo.returnCode)}已撤销该次交易		
		</c:if>
	</td>
</tr>
