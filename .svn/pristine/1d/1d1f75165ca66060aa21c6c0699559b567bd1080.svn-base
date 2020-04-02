<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(vo.name)}</td>
	<td >${fn:escapeXml(vo.user.loginName)}</td>
	<td style="width:150px">	<javatime:format value="${vo.creatTime}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
	<td style="width:150px" >
		<c:if test="${vo.status  eq 'NEW'}">
			<a class="editable" href="#" data-pk="${vo.id}" data-type="text" data-name="startTime" data-title="修改时间"  >
		</c:if>
			<javatime:format value="${vo.startTime}" pattern="yyyy-MM-dd HH:mm:ss"  />
		<c:if test="${vo.status eq 'NEW'}">
			 </a>
		 </c:if>
	</td>
	<td>${vo.taskType.value}（${vo.job}）</td>
	<td style="width:120px">${vo.status.value}</td>
	
	<td style="width:130px">
		<c:if test="${vo.status eq 'NEW'}">
			<a href="${rootUrl}app/timing/task/delete/${vo.id}" class="taiji_remove {confirm_message:'删除后任务不再执行，你确定要删除吗？ '}  taiji_acl">取消并删除</a>
		</c:if>
		<a href="${rootUrl}app/timing/task/view/${vo.id}" class="taiji_modal">查看</a>
	</td>
</tr>
