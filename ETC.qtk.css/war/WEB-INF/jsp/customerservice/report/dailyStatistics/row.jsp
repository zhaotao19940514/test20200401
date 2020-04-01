<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(vo.DistributionG.channelName)}</td>
	<td>${fn:escapeXml(vo.DistributionG.count)}</td>
</tr>
