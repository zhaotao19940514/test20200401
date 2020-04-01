<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>${vo.refundImpLog.fileName }</td> 
	<td>${vo.failMessage }</td> 
	<td>${vo.createTime }</td> 
	<td>
		<a href="${rootUrl }app/apply/baseinfo/commonQuery/userByStaffIdView/${vo.refundImpLog.staffId }" class="taiji_modal_lg taiji_acl">${vo.refundImpLog.staffId }</a>
	</td> 
	<%-- <td>
		<a href="${rootUrl }app/customerservice/finance/refundimplog/edit/${vo.id }" class="taiji_modal_lg taiji_acl">查看详情</a>
	</td> --%>
</tr>
