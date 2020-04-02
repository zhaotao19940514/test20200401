<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(vo.batchId)}</td>
	<td>${fn:escapeXml(vo.model)}</td>
	<td>
	<%-- <c:if test="${vo.brand =='1'}">埃特斯</c:if> <c:if
			test="${vo.brand =='2'}">金溢</c:if> <c:if test="${vo.brand =='3'}">聚利</c:if>
		<c:if test="${vo.brand =='4'}">东海</c:if> <c:if
			test="${vo.brand =='5'}">航天信息</c:if> <c:if test="${vo.brand =='6'}">千方</c:if>
		<c:if test="${vo.brand =='7'}">万集</c:if> <c:if
			test="${vo.brand =='8'}">中兴</c:if> <c:if test="${vo.brand =='9'}">握奇</c:if>
		<c:if test="${vo.brand =='10'}">搜林</c:if> <c:if
			test="${vo.brand =='11'}">成谷</c:if> <c:if test="${vo.brand =='12'}">云星宇</c:if>
		<c:if test="${vo.brand =='13'}">华虹</c:if> <c:if
			test="${vo.brand =='14'}">黔通电子</c:if> <c:if test="${vo.brand =='15'}">通行宝</c:if>
		<c:if test="${vo.brand =='16'}">赛格</c:if> --%>
		<c:if test="${vo.brand =='101'}">埃特斯</c:if>
	<c:if test="${vo.brand =='102'}">金溢</c:if>
	<c:if test="${vo.brand =='103'}">聚利</c:if>
	<c:if test="${vo.brand =='104'}">东海</c:if>
	<c:if test="${vo.brand =='105'}">航天信息</c:if>
	<c:if test="${vo.brand =='106'}">千方</c:if>
	<c:if test="${vo.brand =='107'}">万集</c:if>
	<c:if test="${vo.brand =='108'}">中兴</c:if>
	<c:if test="${vo.brand =='109'}">握奇</c:if>
	<c:if test="${vo.brand =='110'}">搜林</c:if>
	<c:if test="${vo.brand =='111'}">成谷</c:if>
	<c:if test="${vo.brand =='112'}">云星宇</c:if>
	<c:if test="${vo.brand =='113'}">华虹</c:if>
	<c:if test="${vo.brand =='114'}">黔通电子</c:if>
	<c:if test="${vo.brand =='115'}">通行宝</c:if>
	<c:if test="${vo.brand =='116'}">赛格</c:if>
		</td>
	<td>${fn:escapeXml(vo.type)}</td>
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
	<td><a href="${rootUrl }app/apply/baseinfo/commonQuery/servicehallIdView/${vo.serviceHallId }" class="taiji_modal_lg taiji_acl">${fn:escapeXml(vo.serviceHallName)}</a></td>
	<td>
		<a href="${rootUrl }app/apply/baseinfo/commonQuery/userByLoginNameView/${vo.userName}" class="taiji_modal_lg taiji_acl">${vo.userName}</a>
	</td>
	<td><c:if test="${vo.status =='1'}">入库</c:if> <c:if
			test="${vo.status =='2'}">调拨</c:if>
			<c:if test="${vo.status =='9'}">冲正</c:if>
			<c:if test="${vo.status =='5'}">调拨申请</c:if>
			</td>
	<td> 
	<fmt:formatDate value="${vo.createTime.getTime()}" pattern="yyyy-MM-dd HH:mm:ss" ></fmt:formatDate>
	</td>
	 <td>
	 <c:if test="${vo.status =='1'}">
	<a href="${rootUrl }app/administration/inventory/devicewarehousing/obu/delete/${vo.id}"
		confirm_message="确定要冲正吗?" class="taiji_operate  taiji_acl">冲正</a>
		</c:if>
		<c:if test="${(vo.status =='5' && vo.serviceHallId==serviceHallId) || (vo.status =='5' && serviceHallId=='5201010600401140003')}">
			<a href="${rootUrl }app/administration/inventory/deviceallocation/obu/confirm/${vo.id}"
				confirm_message="确定要执行调拨确认操作吗?" class="taiji_operate  taiji_acl">调拨确认</a>
		</c:if>
		</td>  
</tr>
