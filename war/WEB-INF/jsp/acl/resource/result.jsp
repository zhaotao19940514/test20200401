<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div>
	 <div id="taiji_note">操作成功</div>
	<table class="taiji_result_data">
		<%@ include file="row.jsp"%>
	</table>
	<label id="result_id" >
	<c:choose>
		<c:when test="${empty model}">${param.id}</c:when>
		<c:otherwise>
			${model.menuType }_${model.id }
		</c:otherwise> 
	</c:choose>
	</label>
	
	<label id="result_menuId">${menuId }</label>
	<label id="result_name">${fn:escapeXml(model.name) }</label>
</div>