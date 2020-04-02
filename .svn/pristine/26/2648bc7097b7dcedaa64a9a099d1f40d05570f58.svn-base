<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%><div> 
<div>
<div id="taiji_search_data">
<table >
	<tbody>
		<c:forEach items="${pagn.result}" var="vo"  varStatus="voStatus">
			<%@ include file="row.jsp"%>
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
