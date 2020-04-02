<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>${fn:escapeXml(vo.customer.customerName)}</td>
	<td>
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '101')}">身份证（含临时身份证）</c:if>
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '102')}">护照（限外籍人士）</c:if>
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '103')}">港澳居民来往内地通行证</c:if>
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '104')}">台湾居民来往大陆通行证</c:if>
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '105')}">军官证</c:if>
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '106')}">武警警察身份证</c:if>
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '201')}"> 统一社会信用代码证书</c:if>
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '202')}">组织机构代码证</c:if>
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '203')}">营业执照</c:if>
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '204')}">事业单位法人证书</c:if>
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '205')}">社会团体法人登记证书</c:if>
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '206')}">律师事务所执业许可证</c:if> 
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '199')}">个人证件缺省值</c:if> 
		<c:if test="${fn:escapeXml(vo.customer.customerIdType eq '299')}">单位证件缺省值</c:if> 
	</td>
	<td>${fn:escapeXml(vo.customer.customerIdNum)}</td>
	<td>${fn:escapeXml(vo.vehicle.vehiclePlate)}</td>
	<td>
		<c:if test="${fn:escapeXml(vo.vehicle.vehiclePlateColor eq '0')}">蓝色</c:if>
		<c:if test="${fn:escapeXml(vo.vehicle.vehiclePlateColor eq '1')}">黄色</c:if>
		<c:if test="${fn:escapeXml(vo.vehicle.vehiclePlateColor eq '2')}">黑色</c:if>
		<c:if test="${fn:escapeXml(vo.vehicle.vehiclePlateColor eq '3')}">白色</c:if>
		<c:if test="${fn:escapeXml(vo.vehicle.vehiclePlateColor eq '4')}">渐变绿色</c:if>
		<c:if test="${fn:escapeXml(vo.vehicle.vehiclePlateColor eq '5')}">黄绿双拼色</c:if>
		<c:if test="${fn:escapeXml(vo.vehicle.vehiclePlateColor eq '6')}">蓝白渐变色</c:if>
		<c:if test="${fn:escapeXml(vo.vehicle.vehiclePlateColor eq '9')}">未确定</c:if>
	</td>
	<td>${fn:escapeXml(vo.cardId)}</td>
	<td>
		<c:if test="${fn:escapeXml(vo.status eq '0')}">申请</c:if>
		<c:if test="${fn:escapeXml(vo.status eq '1')}">正常</c:if>
		<c:if test="${fn:escapeXml(vo.status eq '2')}">有卡挂起</c:if>
		<c:if test="${fn:escapeXml(vo.status eq '3')}">无卡挂起</c:if>
		<c:if test="${fn:escapeXml(vo.status eq '4')}">有卡注销</c:if>
		<c:if test="${fn:escapeXml(vo.status eq '5')}">无卡注销</c:if>
		<c:if test="${fn:escapeXml(vo.status eq '6')}">卡挂失</c:if>
		<c:if test="${fn:escapeXml(vo.status eq '9')}">预注销</c:if>
	</td>
	<td>
		<c:choose>
			<c:when test="${Integer.valueOf(vo.cardType/100) eq 1}">记账卡</c:when>
			<c:when test="${Integer.valueOf(vo.cardType/100) eq 2}">储值卡</c:when>
			<c:otherwise>不进行检测</c:otherwise>
		</c:choose>
	</td>
	<td>
	<c:if test="${vo.status eq '4' or vo.status eq '5' }">
		 <a href="${rootUrl }app/customerservice/finance/balancesupply/info/${vo.cardId}"  class="taiji_modal  {width:550,height:600} taiji_acl" >余额补领</a>
	</c:if>
	</td>
</tr>
<script>

</script>