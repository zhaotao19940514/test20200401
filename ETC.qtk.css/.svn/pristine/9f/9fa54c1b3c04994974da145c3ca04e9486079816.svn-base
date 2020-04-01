<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>
		<c:if test="${vo.type =='1'}">储值卡</c:if>
		<c:if test="${vo.type =='2'}">记账卡</c:if>
		<c:if test="${vo.type =='3'}">电子标签</c:if>
	</td>
	<td><c:if test="${vo.brand =='1'}">捷德</c:if> <c:if
			test="${vo.brand =='2'}">握奇</c:if> <c:if test="${vo.brand =='3'}">天喻</c:if>
		<c:if test="${vo.brand =='4'}">明华</c:if> <c:if
			test="${vo.brand =='5'}">楚天龙</c:if> <c:if test="${vo.brand =='6'}">金邦达</c:if>
		<c:if test="${vo.brand =='7'}">航天信息</c:if> <c:if
			test="${vo.brand =='8'}">鸿博</c:if> <c:if test="${vo.brand =='9'}">恒宝</c:if>
		<c:if test="${vo.brand =='10'}">复旦</c:if> <c:if
			test="${vo.brand =='11'}">精工伟达</c:if> <c:if test="${vo.brand =='12'}">华大智宝</c:if>
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
	<td>${fn:escapeXml(vo.model)}</td>
	<td><a
		href="${rootUrl }app/administration/inventory/devicemodel/card/edit/${vo.id}"
		class="taiji_modal taiji_acl btn  btn-white btn-small">修改</a> <a
		href="${rootUrl }app/administration/inventory/devicemodel/card/delete/${vo.id}"
		confirm_message="确定要删除吗?"
		class="taiji_remove  taiji_acl btn btn-white btn-small">删除</a></td>
</tr>
