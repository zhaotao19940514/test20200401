<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>${fn:escapeXml(voStatus.count)}</td>
	<td>
		<a href="${rootUrl }app/customerservice/card/cancelrefund/info/${vo.cardId}" class="taiji_modal">${fn:escapeXml(vo.cardId)}</a>
	</td>
	<td>${fn:escapeXml(vo.cusName)}</td>
	<td>
		<c:forEach var="ba" items="${BankType}">
			 <c:if test="${fn:escapeXml(ba.code eq vo.bankType)}">${ba.value}</c:if> 
		</c:forEach>
	</td>
	<%-- <td>${fn:escapeXml(vo.bankType)}</td> --%>
	<td>${fn:escapeXml(vo.bankCardId)}</td>
	<td>
		<c:choose>
			<c:when test="${vo.refundType ne 'YTJTF'&&vo.refundType ne 'GLYQR'&&vo.refundType ne 'YWCTF'&&vo.refundType ne 'TFYDC'}">
				无
			</c:when>
			<c:otherwise>
				${fn:escapeXml(vo.refundBalance/100)}
			</c:otherwise>
		</c:choose>
	</td>
	<td>${fn:escapeXml(vo.cancelTime)}</td>
	<%-- <td>${fn:escapeXml(vo.cusTel)}</td> --%>
	<td>
		<c:if test="${vo.attachStatus=='' }">
			<a href="${rootUrl }app/customerservice/card/cancelrefund/view/${vo.cardId}" class="taiji_modal">查看</a></td>
		</c:if>
	<td>
		 <c:choose>
		 	<c:when test="${vo.attachStatus==0 }">
		 		未确认
		 	</c:when>
		 	<c:when test="${vo.attachStatus==1 }">
		 		<font style="color:red">未通过</font>
		 	</c:when>
		 	<c:when test="${vo.attachStatus==2 }">
		 		已确认
		 	</c:when>
		 </c:choose>
	</td>
	<td>
		 <c:forEach var="re" items="${refundDetailType}">
			<c:if test="${fn:escapeXml(re eq vo.refundType)}">${re.value}</c:if>
		</c:forEach>
	</td>
	<td>
		<c:if  test="${vo.refundType ne 'YWCTF'&&vo.refundType ne'TFYDC'}">
			<a href="${rootUrl }app/customerservice/card/cancelrefund/edit/${vo.cardId}" rowCardId ="${vo.cardId}" class="taiji_modal">修改</a>
		</c:if>
		<!-- <a  href="javascript:void(0);" id="hangBtnOff" class="taiji_acl">操作日志</a> -->
		<c:if test="${fileName!=null&&vo.attachStatus==0 }">
			<a  href="javascript:void(0);" rowCardId ="${vo.cardId}" id="attachConfirm" class="taiji_acl">附件确认</a>
			<a  href="javascript:void(0);" rowCardId ="${vo.cardId}" id="attachRefuse" class="taiji_acl">附件不符</a>
		</c:if>
	</td>
	
	</td>
</tr>
