<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>
		<input type="checkbox" name="taskNames" class="taiji_check_one" value='${vo.taskName }'/>
	</td>
	<td style="width: 60px">${voStatus.count }</td>
	<td >
		<a href="${rootUrl }app/system/cron/view/${vo.taskName}" class="taiji_modal">${vo.taskName }</a>
	</td>
	<td>${vo.taskGroup.value }</td>
	<td style="min-width: 81px">${vo.cron }</td>
	<td align="center" style="width: 100px">
		<c:if test="${vo.running}"><span style="color: green">运行</span></c:if>
		<c:if test="${!vo.running}"><span style="color: red">停止</span></c:if>
	</td>
	<td align="center" style="width: 80px">
		<c:if test="${vo.taskRunning}"><span style="color: green">正在执行</span></c:if>
		<c:if test="${!vo.taskRunning}"><span style="color: red">未执行</span></c:if>
	</td>
	<td >${vo.info }</td>
	<td align="center" style="width: 70px">
		<c:if test="${vo.autoStart}">是</c:if>
		<c:if test="${!vo.autoStart}"><span style="color: red">否</span></c:if>
	</td>
	<td class="tdbtn"  style="width: 160px">
	<c:if test="${!vo.running }">
			<a href="${rootUrl }app/system/cron/runner/${vo.taskName}/start" confirm_message="启动调度器:${vo.taskName }?" class="taiji_update ">启动调度器</a>
			<a href="${rootUrl }app/system/cron/edit/${vo.taskName}" class="taiji_modal">修改CRON</a>
	</c:if>
	<c:if test="${vo.running }">
			<a href="${rootUrl }app/system/cron/runner/${vo.taskName}/stop" confirm_message="停止调度器:${vo.taskName }?" class="taiji_update ">停止调度器</a>
			<c:if test="${!vo.taskRunning }">
				<a href="${rootUrl }app/system/cron/runnow/${vo.taskName}" confirm_message="现在执行任务:${vo.taskName }?" class="taiji_update ">执行任务</a>
			</c:if>
	</c:if>
	</td>
</tr>
