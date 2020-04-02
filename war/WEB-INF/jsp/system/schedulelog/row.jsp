<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>
		<javatime:format value="${vo.startTime}" style="LM"  />
	</td>
	<td>
		<javatime:format value="${vo.endTime}" pattern="yyyy-MM-dd HH:mm:ss"  />
	</td>
	<td>
		<a href="${rootUrl }app/system/cron/view/${vo.taskName}" class="taiji_modal">${fn:escapeXml(vo.taskName)}</a>
	</td>
	<td>
		<c:if test="${vo.bySystem }">系统触发</c:if>
		<c:if test="${!vo.bySystem }">手工触发</c:if>
	</td>
	<td>${vo.currentCron }</td>
	<td>${vo.timeStr }(${vo.execTime }ms)</td>
</tr>
