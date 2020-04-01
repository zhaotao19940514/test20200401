<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tr>
	<td>${fn:escapeXml(vo.batchId)}</td>
	<td>
	<c:if test="${vo.brand =='1'}">捷德</c:if> <c:if
			test="${vo.brand =='2'}">握奇</c:if> <c:if test="${vo.brand =='3'}">天喻</c:if>
		<c:if test="${vo.brand =='4'}">明华</c:if> <c:if
			test="${vo.brand =='5'}">楚天龙</c:if> <c:if test="${vo.brand =='6'}">金邦达</c:if>
		<c:if test="${vo.brand =='7'}">航天信息</c:if> <c:if
			test="${vo.brand =='8'}">鸿博</c:if> <c:if test="${vo.brand =='9'}">恒宝</c:if>
		<c:if test="${vo.brand =='10'}">复旦</c:if> <c:if
			test="${vo.brand =='11'}">精工伟达</c:if> <c:if test="${vo.brand =='12'}">华大智宝</c:if>
	</td>
	<td><c:if test="${vo.type ==111}">记账卡</c:if> <c:if
			test="${vo.type ==211}">储值卡</c:if></td>
	<td>
		<c:if test="${empty vo.inboundNum || vo.inboundNum eq 0}">${fn:escapeXml(vo.outboundNum)}</c:if>
		<c:if test="${not empty vo.inboundNum && vo.inboundNum ne 0 }">${fn:escapeXml(vo.inboundNum)}</c:if>
	</td>
	<td>${fn:escapeXml(vo.startId)}</td>
	<td>${fn:escapeXml(vo.endId)}</td>
	<td>
		<c:if test="${vo.outServiceHallName eq vo.serviceHallName || empty vo.outServiceHallName}">--------------------</c:if> 
		<c:if test="${vo.outServiceHallName ne vo.serviceHallName && not empty vo.outServiceHallName}"><a href="${rootUrl }app/apply/baseinfo/commonQuery/servicehallIdView/${vo.outServiceHallId }" class="taiji_modal_lg taiji_acl">${fn:escapeXml(vo.outServiceHallName)}</a></c:if>
	</td>
	<%-- <td>${fn:escapeXml(vo.outServiceHallName)}</td> --%>
	<td><a href="${rootUrl }app/apply/baseinfo/commonQuery/servicehallIdView/${vo.serviceHallId }" class="taiji_modal_lg taiji_acl">${fn:escapeXml(vo.serviceHallName)}</a></td>
	<td>
		<a href="${rootUrl }app/apply/baseinfo/commonQuery/userByLoginNameView/${vo.userName}" class="taiji_modal_lg taiji_acl">${vo.userName}</a>
	</td> 
	<td><c:if test="${vo.status =='1'}">入库</c:if> <c:if
			test="${vo.status =='2'}">调拨</c:if>
			<c:if test="${vo.status =='5'}">调拨申请</c:if>
			<c:if test="${vo.status =='9'}">冲正</c:if>
			</td>
	<td> 
	<fmt:formatDate value="${vo.createTime.getTime()}" pattern="yyyy-MM-dd HH:mm:ss" ></fmt:formatDate>
	</td>
	 <td>
	 <c:if test="${vo.status =='1'}">
		<a href="${rootUrl }app/administration/inventory/devicewarehousing/card/delete/${vo.id}"
		confirm_message="确定要冲正吗?" class="taiji_operate taiji_acl btn-small">冲正</a>
	</c:if>
	<c:if test="${(vo.status =='5' && vo.serviceHallId==serviceHallId) || (vo.status =='5' && serviceHallId=='5201010600401140003')}">
		<a href="${rootUrl }app/administration/inventory/deviceallocation/card/confirm/${vo.id}"
				confirm_message="确定要执行调拨确认吗?" class="taiji_operate  taiji_acl">调拨确认</a>
	</c:if>
		</td>  
</tr>
