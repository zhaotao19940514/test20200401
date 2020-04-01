<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td>${vo.operation4xType.value}</td>
	<td>${vo.startId }</td>
	<td>${vo.endId }</td>
	<td>${vo.deviceVersion.name }</td>
	<td>${vo.operationTime }</td>
	<td>${vo.staffId }</td>
	<td>${vo.batchNo }</td>
	<td><c:choose>
							<c:when test="${ vo.rollBack==true}">
								<a
									href="${rootUrl }app/administration/section4x/operationlog/rollback/${vo.batchNo}/${vo.rollBackType}"
									confirm_message="确定要回滚至此版本吗?" class="taiji_remove  taiji_acl">回滚至此版本</a>
							</c:when>
							
							<c:otherwise>
				   						不支持更长时间的回滚或该次操作不支持回滚
				  			 </c:otherwise>
		</c:choose>
	</td>
</tr>
