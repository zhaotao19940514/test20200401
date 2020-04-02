<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<div> 
<div class="searchCondtion">${param.year }年${paramMonth.value}月</div>
<div id="taiji_search_data">
	<table>
		<c:forEach items="${days}" var="week"  >
			<tr>
				<c:forEach items="${week}" var="d"  >
				<td>
					<c:choose>
						<c:when test="${not d.inWork }">
							<a id="${d.day }" href="#" style="color:red" class="cal-click">${d.day.dayOfMonth}</a>
						</c:when>
						<c:when test="${d.day.month!=param.month }">
							<a id="${d.day }" href="#"  style="color:rgb(191, 191, 197)" class="cal-click">${d.day.dayOfMonth}</a>
						</c:when>
						<c:otherwise>
							<a id="${d.day }"  href="#" class="cal-click">${d.day.dayOfMonth}</a>
						</c:otherwise>
					</c:choose>
				
				</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</div>
</div>
