<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>${fn:escapeXml(vo.agencyId)}</td>
	<td>${fn:escapeXml(vo.agencyName)}</td>
	<td>${fn:escapeXml(vo.checkContract)}</td>
	<td>${fn:escapeXml(vo.openNotify)}</td>
	<td>${fn:escapeXml(vo.openObuNotify)}</td>
	<td>${fn:escapeXml(vo.cardNotice)}</td>
	<td>${fn:escapeXml(vo.signNotice)}</td>
	<td>${fn:escapeXml(vo.bankSignUrl)}</td>
	<td>
		<a  href="${rootUrl }app/administration/notice/noticeconfig/edit/${vo.id }" class="taiji_modal {width:550,height:600} taiji_acl m-r-10">修改</a>
	</td>
</tr>
