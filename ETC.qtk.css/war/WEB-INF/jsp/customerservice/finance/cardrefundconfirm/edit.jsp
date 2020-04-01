<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		$(function(){
			$("#submit").click(function(){
				//$("#myForm").trigger("submit");
				$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"edit"}]);
			});

		});
		
		function setUnitValue(id,name){
			$("#unit\\.id").val(id);
			$("#unit\\.name").val(name);
		}
		</script>
	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">交易详情</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel1" id="myForm" name="myForm" cssClass="form-horizontal">
	<table class="table table-bordered" style="width:1100px">
		<tr>
			<td>序号</td>
			<td>是否连续</td>
			<td>交易类型</td>
			<td>卡号</td>
			<td>交易前金额(元)</td>
			<td>交易金额(元)</td>
			<td>交易后金额(元)</td>
			<td>入口站名</td>
			<td>入口时间</td>
			<td>出口站名</td>
			<td>出口时间</td>
			<td>充值时间</td>
		</tr>
		
			<c:forEach var="li" items="${list}"  varStatus="voStatus">
				<tr>
					<td>${fn:escapeXml(voStatus.count)}</td>
					<td>
						<c:if test="${li.continuey eq 0 }">
							<img src="${rootUrl }images/error.png" style="height: 16px;width: 16px;"  />
						</c:if>
						<c:if test="${li.continuey eq 1 }">
							<img src="${rootUrl }images/right.png" style="height: 16px;width: 16px;"  />
						</c:if>
					</td>
					<td>${li.tradeType }</td>
					<td>${li.cardId }</td>
					<td> <fmt:formatNumber value="${li.preBalance/100}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber></td>
					<td> <fmt:formatNumber value="${li.fee/100}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber></td>
					<td> <fmt:formatNumber value="${li.postBalance/100}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber></td>
					<td>${li.enTolllaneName }</td>
					<td>${li.enTime }</td>
					<td>${li.exTolllaneName }</td>
					<td>${li.exTime }</td>
					<td>${li.chargeTime }</td>
			  </tr>
			</c:forEach>
	</table>
</form:form>
	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>

</body>
</html>