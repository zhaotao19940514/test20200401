<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
	[
		<c:forEach items="${list}" var="appResource">
			<c:choose>
				<c:when test="${appResource.menuType eq 'NOT_MENU'}">
					{"name":"${appResource.name }","id":"${appResource.menuType }_${appResource.id }", },
				</c:when>
				<c:otherwise>
					{"name":"${appResource.name }","id":"${appResource.menuType }_${appResource.id }","isParent":${appResource.hasChild}},
				</c:otherwise>
			</c:choose>
		</c:forEach>
	]