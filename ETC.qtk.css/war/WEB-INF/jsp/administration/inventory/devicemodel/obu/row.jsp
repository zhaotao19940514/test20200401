<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(vo.type)}</td>
	<td><c:if test="${vo.brand =='1'}">埃特斯</c:if> <c:if
			test="${vo.brand =='2'}">金溢</c:if> <c:if test="${vo.brand =='3'}">聚利</c:if>
		<c:if test="${vo.brand =='4'}">东海</c:if> <c:if
			test="${vo.brand =='5'}">航天信息</c:if> <c:if test="${vo.brand =='6'}">千方</c:if>
		<c:if test="${vo.brand =='7'}">万集</c:if> <c:if
			test="${vo.brand =='8'}">中兴</c:if> <c:if test="${vo.brand =='9'}">握奇</c:if>
		<c:if test="${vo.brand =='10'}">搜林</c:if> <c:if
			test="${vo.brand =='11'}">成谷</c:if> <c:if test="${vo.brand =='12'}">云星宇</c:if>
		<c:if test="${vo.brand =='13'}">华虹</c:if> <c:if
			test="${vo.brand =='14'}">黔通电子</c:if> <c:if test="${vo.brand =='15'}">通行宝</c:if>
		<c:if test="${vo.brand =='16'}">赛格</c:if>
	</td>
	<td>${fn:escapeXml(vo.model)}</td>
	<td><a
		href="${rootUrl }app/administration/inventory/devicemodel/obu/edit/${vo.id}"
		class="taiji_modal taiji_acl btn  btn-white btn-small">修改</a> <a
		href="${rootUrl }app/administration/inventory/devicemodel/obu/delete/${vo.id}"
		class="taiji_operate {confirm_message:'是否确认删除？'}  taiji_acl btn btn-white btn-small">删除</a>
	</td>
</tr>
