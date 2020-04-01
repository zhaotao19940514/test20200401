<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tr>
	<td>${fn:escapeXml(vo.obuId)}</td>
	<td>
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
	<td>${fn:escapeXml(vo.batchId)}</td>
	<td><a href="${rootUrl }app/apply/baseinfo/commonQuery/servicehallIdView/${vo.serviceHallId }" class="taiji_modal_lg taiji_acl">${fn:escapeXml(vo.serviceHallId)}</a></td>
	<td>${fn:escapeXml(vo.serviceHallName)}</td>
	<td>
		<a href="${rootUrl }app/apply/baseinfo/commonQuery/userByLoginNameView/${vo.userName}" class="taiji_modal_lg taiji_acl">${vo.userName}</a>
	</td>
	<td>
		<c:if test="${vo.status =='1'}">入库</c:if> 
		<c:if test="${vo.status =='2'}">调拨</c:if>
		<c:if test="${vo.status =='4'}">发行</c:if>
		<c:if test="${vo.status =='5'}">调拨申请</c:if>
		<c:if test="${vo.status =='9'}">冲正</c:if>
	</td>
	<td> 
	<fmt:formatDate value="${vo.createTime.getTime()}" pattern="yyyy-MM-dd HH:mm:ss" ></fmt:formatDate>
	</td>
</tr>
