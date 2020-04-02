<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<div>
<div id="taiji_search_data">
<table >
	<tbody>
		<c:forEach items="${pagn.result}" var="vo"  varStatus="voStatus">
			<tr>
				<td>
					<input type="checkbox" name="checkIds" class='taiji_check_one' value="${vo.id }"/>
					${fn:escapeXml(vo.loginName)}
				</td>
				<td>
				<a href="${rootUrl }app/acl/user/view/${vo.id}" class="taiji_modal taiji_acl" >${fn:escapeXml(vo.name)}</a>
				</td>
				<td><span>${fn:escapeXml(vo.unit.name)}</span></td>
				<td>
					<c:if test="${not empty vo.role }">${vo.role.name }</c:if>
				</td>
				<td>
					<c:if test="${vo.male }">男</c:if>
					<c:if test="${!vo.male }">女</c:if>
				</td>
				<td>${vo.status.value }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<div id="taiji_search_pager" >
    <label id="taiji_search_totalcount">${pagn.totalCount }</label>
    <label id="taiji_search_pagecount">${pagn.pageCount }</label>
    <label id="taiji_search_pageNo">${pagn.currentPage }</label>
</div>
</div>