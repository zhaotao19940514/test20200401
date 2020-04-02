<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td style="width: 60px">
		<input type="checkbox" name="ids" class="taiji_check_one" value='${vo.id }'/>
	</td>
	<td  >
		<a href="${rootUrl }app/acl/user/view/${vo.user.id}" class="taiji_modal">${fn:escapeXml(vo.user.loginName)}</a>
	</td>
	<td >
		<!-- The style expected value is two characters, one for date, one for time, from S=Short, M=Medium, L=Long, F=Full, -=None.  
		<javatime:format value="${vo.optime}" style="MM"  />
		-->
		<javatime:format value="${vo.optime}" pattern="yyyy-MM-dd HH:mm:ss"  />
	</td>
	<td style="width: 100px">${fn:escapeXml(vo.ip)}</td>
	<td ><span>${vo.logType.value }</span></td>
	<td>${fn:escapeXml(vo.info)}</td>
</tr>
