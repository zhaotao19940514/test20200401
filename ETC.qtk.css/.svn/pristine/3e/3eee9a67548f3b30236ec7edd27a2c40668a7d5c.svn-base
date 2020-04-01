<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
    <td><a href="${rootUrl }app/apply/baseinfo/commonQuery/userByLoginNameView/${vo.username }" class="taiji_modal_lg taiji_acl">${fn:escapeXml(vo.username)}</a></td>
    <td>
        ${fn:escapeXml(vo.operateTime)}
    </td>
    <td>${fn:escapeXml(vo.serviceType.serviceName)}</td>
    <td>${fn:escapeXml(vo.operateType.value)}</td>
    <td>${fn:escapeXml(vo.remoteIp)}</td>
    <td>${fn:escapeXml(vo.discription)}</td>
    <td>
        <a href="${rootUrl }app/system/operateLog/view/${vo.id}" class="taiji_modal btn btn-sm btn-primary">详情</a>
        <c:if test="${vo.serviceType.wetherPrintReceipt}">
            <a href="${rootUrl }app/system/operateLog/printReceipt/${vo.id}" class="btn btn-sm btn-primary" target="_blank">打印回执</a>
        </c:if>
    </td>
</tr>
