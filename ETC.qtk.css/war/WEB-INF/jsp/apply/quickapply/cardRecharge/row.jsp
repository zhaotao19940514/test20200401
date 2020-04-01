<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${vo.cardId}</td>
	<td>${vo.preBalance}</td>
	<td>${vo.balance}</td>
	<td>${vo.postBalance}</td>
	<td>${vo.chargeTime}</td>
	<td>
		<a href="${rootUrl }app/acl/role/conf/${vo.id}" class="taiji_modal {width:550,height:600} taiji_acl">配置</a>
	</td>
</tr>
