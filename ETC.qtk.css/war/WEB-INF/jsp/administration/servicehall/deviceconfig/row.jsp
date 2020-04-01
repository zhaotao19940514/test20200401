<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
    <td>${fn:escapeXml(vo.serviceHall.name)}</td>
    <td>
        ${fn:escapeXml(vo.serviceHallId)}
    </td>
    <td>${fn:escapeXml(vo.cardDeviceType.value)}</td>
    <td>${fn:escapeXml(vo.obuDeviceType.value)}</td>
    <td>${fn:escapeXml(vo.posDeviceType.value)}</td>
    <td>${fn:replace(fn:escapeXml(vo.createTime),'T',' ')}</td>
    <td>${fn:replace(fn:escapeXml(vo.updateTime),'T',' ')}</td>
    <td style="width:15em;">
        <a href="${rootUrl }app/administration/servicehall/deviceconfig/view/${vo.id}" class="taiji_modal_lg btn btn-sm btn-primary">详情</a>
        <a href="${rootUrl }app/administration/servicehall/deviceconfig/edit/${vo.id}" class="taiji_modal_lg btn btn-sm btn-primary">修改</a>
        <a href="${rootUrl }app/administration/servicehall/deviceconfig/delete/${vo.id}" class="taiji_operate {confirm_message:'确认删除网点：${vo.serviceHall.name}设备类型配置？ '} btn btn-sm btn-primary">删除</a>
    </td>
</tr>
