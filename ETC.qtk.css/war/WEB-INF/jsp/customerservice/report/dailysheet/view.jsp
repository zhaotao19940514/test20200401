<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	
<body>

		<tr>
			<td> ${fn:escapeXml(vos.cash)} </td>
			<td> ${fn:escapeXml(vos.fee-vos.cash)} </td> 
		</tr>

</body>
</html>