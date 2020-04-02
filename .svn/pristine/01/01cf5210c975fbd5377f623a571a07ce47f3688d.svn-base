<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>${fn:escapeXml(vo.preBalance/100)}</td>
	<td>${fn:escapeXml(vo.rechargeAmount/100)}</td>
	<td>${fn:escapeXml((vo.preBalance+vo.rechargeAmount)/100)}</td>
	<td>${fn:escapeXml(fn:replace(vo.tradeTime,'T',' '))}</td>
	<td style="display: none;">${vo.chargeId}</td>
	<td>
		<a id='show' style="display:none" href="${rootUrl }app/customerservice/finance/halfauditing/update/${vo.chargeId}"  
    class="taiji_operate {confirm_message:'是否申请修复审核 ',refresh:true} taiji_acl">申请强制修复</a>
  		<a id="uploadPhoto" href="${rootUrl }app/customerservice/finance/halfauditing/info/${vo.chargeId}" 
  		class="taiji_modal {width:550,height:600} taiji_acl" >上传图片</a>
	</td>
</tr>
