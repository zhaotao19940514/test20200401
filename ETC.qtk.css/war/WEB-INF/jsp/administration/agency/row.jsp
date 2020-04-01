<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
    <td>${fn:escapeXml(vo.name)}</td>
    <td>
        ${fn:escapeXml(vo.agencyId)}
    </td>
    <td>
        ${fn:escapeXml(vo.accountId)}
    </td>
    <td>${fn:escapeXml(vo.type.value)}</td>
    <td>${fn:escapeXml(vo.contact)}</td>
    <td>${fn:escapeXml(vo.tel)}</td>
    <td>${fn:escapeXml(vo.startTime)}</td>
    <td>${fn:escapeXml(vo.endTime)}</td>
    <td>
        <a href="${rootUrl }app/administration/agency/view/${vo.id}" class="taiji_modal btn btn-sm btn-primary">详情</a>
        <a href="${rootUrl }app/administration/agency/edit/${vo.id}&${vo.accountId}" class="taiji_modal btn btn-sm btn-primary">修改</a>
        <a href="${rootUrl }app/administration/agency/delete/${vo.id}" class="taiji_operate {confirm_message:'确认删除机构：${vo.name}？ '} btn btn-sm btn-primary">删除</a>
    </td>
</tr>
