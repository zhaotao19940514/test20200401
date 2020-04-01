<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<th>${fn:escapeXml(voStatus.count)}</th>
			<th>${vo.userType }</th>
			<th>${vo.identType }</th>
			<th>${ vo.identNo }</th>
			<th>${vo.vehiclePlate }</th>
			<th>${ vo.vehiclePlateColor}</th>
			<th>${ vo.userMobile}</th>
			<th>${ vo.orderNo}</th>
			<th>${ vo.respMessage}</th>
</tr>