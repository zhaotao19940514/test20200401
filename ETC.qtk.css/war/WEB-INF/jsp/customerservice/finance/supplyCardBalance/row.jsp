<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>${fn:escapeXml(vo.customerName)}</td>
	<td>${fn:escapeXml(vo.vehiclePlate)}</td>
	<td>
		<c:if test="${vo.vehiclePlateColor eq '0'}">蓝色</c:if>
		<c:if test="${vo.vehiclePlateColor eq '1'}">黄色</c:if>
		<c:if test="${vo.vehiclePlateColor eq '2'}">黑色</c:if>
		<c:if test="${vo.vehiclePlateColor eq '3'}">白色</c:if>
		<c:if test="${vo.vehiclePlateColor eq '4'}">渐变绿色</c:if>
		<c:if test="${vo.vehiclePlateColor eq '5'}">黄绿双拼色</c:if>
		<c:if test="${vo.vehiclePlateColor eq '6'}">蓝白渐变色</c:if>
		<c:if test="${vo.vehiclePlateColor eq '9'}">未确定</c:if>
	</td>
	<td>${fn:escapeXml(vo.cardBalance/100)}</td>
	<td>${fn:escapeXml(vo.enableTime)}</td>
	<td>
		<c:if test="${vo.status eq '2'}">
		<a param = '${vo.cardId}' param2 = '${vo.cardBalance}' param3 = '${vo.enableTime}' id="supply" href="#" class=" taiji_acl btn btn-success">补卡额</a>
		</c:if>
		<c:if test="${vo.status eq '1'}">
		已补卡额
		</c:if>
		|
		 <a  href="${rootUrl }app/customerservice/finance/supplyCardBalance/showPhoto/${vo.cardId}&${vo.enableTime}"  
    class="taiji_modal_lg taiji_acl btn  btn-success ">查看图片</a>
        |
    <a href="${rootUrl }app/customerservice/finance/supplyCardBalance/info/${vo.cardId}&${vo.enableTime}" 
  		class="taiji_modal taiji_acl btn btn-success" >上传图片</a>
	</td>
</tr>
