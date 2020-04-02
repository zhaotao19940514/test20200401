<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
    <td>
        ${fn:escapeXml(vo.staffId)}
    </td>
    <td>${fn:escapeXml(vo.staffName)}</td>
    <td>${fn:escapeXml(vo.serviceHall.name)}</td>
    <td>${fn:escapeXml(vo.serviceHallId)}</td>
    <td class="releaseDate">${fn:escapeXml(vo.releaseDate)}</td>
    <td>${fn:escapeXml(vo.agencyId)}</td>
    <td>${fn:escapeXml(vo.accountId)}</td>
    <td style="width:15em;">
        <a href="${rootUrl }app/administration/staff/view/${vo.id}" class="taiji_modal_lg btn btn-sm btn-primary">详情</a>
        <a href="${rootUrl }app/administration/staff/edit/${vo.id}" class="taiji_modal_lg btn btn-sm btn-primary">修改</a>
        <a href="${rootUrl }app/administration/staff/delete/${vo.id}" class="taiji_operate {confirm_message:'确认删除工号：${vo.staffId}？ '} btn btn-sm btn-primary">删除</a>
    </td>
</tr>
