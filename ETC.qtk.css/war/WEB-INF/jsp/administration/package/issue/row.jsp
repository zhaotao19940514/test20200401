<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   


<tr>
    <td> ${fn:escapeXml(vo.packageNum)} 
    <td> ${fn:escapeXml(vo.packageName)} </td>
	<td> ${fn:escapeXml(vo.vehicleType)} </td>
	<td> 
	   <c:if test="${vo.issueType eq 1}">发卡</c:if>
	   <c:if test="${vo.issueType eq 2}">发OBU</c:if>
	   <c:if test="${vo.issueType eq 3}">发卡发OBU</c:if>
	 </td>	
	<td> ${fn:escapeXml(vo.enableTime)} </td>
	<td> ${fn:escapeXml(vo.expireTime)} </td>
	<td>
		<fmt:formatNumber value="${fn:escapeXml(vo.rechargeMoney)}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> 	 
	</td>
	<td>
		<fmt:formatNumber value="${fn:escapeXml(vo.obuCost)}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> 	 
	</td>
	<td>
		<fmt:formatNumber value="${fn:escapeXml(vo.cardCost)}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> 	 	
	</td>
	<td> ${fn:escapeXml(vo.createTime)} </td>
	<td> ${fn:escapeXml(vo.createPerson)} </td>
	<td>
		<a href="${rootUrl}app/administration/package/issue/edit/${vo.id}" class="taiji_modal taiji_acl">修改</a>	    
	</td>
</tr>
