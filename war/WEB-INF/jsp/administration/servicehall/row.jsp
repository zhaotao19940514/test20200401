<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
    <td>${fn:escapeXml(vo.name)}</td>
    <td>
        ${fn:escapeXml(vo.serviceHallId)}
    </td>
    <td>${fn:escapeXml(vo.agencyId)}</td>
    <td>${fn:escapeXml(vo.contact)}</td>
    <td>${fn:escapeXml(vo.tel)}</td>
    <td>${fn:replace(fn:escapeXml(vo.startTime),'T',' ')}</td>
    <td>${fn:replace(fn:escapeXml(vo.endTime),'T',' ')}</td>
<!--     <td> -->
<%--         <fmt:formatDate value="${vo.createTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
<!--     </td> -->
    <td style="width:15em;">
        <a href="${rootUrl }app/administration/servicehall/view/${vo.id}" class="taiji_modal_lg btn btn-sm btn-primary">详情</a>
        <a href="${rootUrl }app/administration/servicehall/edit/${vo.id}" class="taiji_modal_lg btn btn-sm btn-primary">修改</a>
        <a href="${rootUrl }app/administration/servicehall/delete/${vo.id}" class="taiji_operate {confirm_message:'确认删除网点：${vo.name}？ '} btn btn-sm btn-primary">删除</a>
    </td>
</tr>
