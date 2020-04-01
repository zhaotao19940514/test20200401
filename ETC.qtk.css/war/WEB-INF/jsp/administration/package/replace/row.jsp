<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
    <td> 
    	<fmt:formatNumber value="${fn:escapeXml(vo.cardCost)}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> 	 
    </td>
    <td>
    	<fmt:formatNumber value="${fn:escapeXml(vo.obuCost)}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> 	 
    </td>
	<td> ${fn:escapeXml(vo.createUser)} </td>
	<td> ${fn:replace(vo.createTime,'T', ' ')}</td>
	<td>
		<a href="${rootUrl}app/administration/package/replace/edit/${vo.id}" class="taiji_modal taiji_acl">修改</a>	    
	</td>
</tr>
