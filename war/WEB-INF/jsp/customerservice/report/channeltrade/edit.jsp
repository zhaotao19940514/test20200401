<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
	<meta http-equiv="expires" content="0" />
	<meta http-equiv=
	"pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript">
	$(function(){
// 		$("#myView").taiji();
	    $("#tab").taiji();
	});			
		
		</script>
	</head>
<body>
<input type="hidden" id="cardBalance" value="${accountCardBalance}" />
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">网点充值消费详情</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel1" id="myForm" name="myForm" cssClass="form-horizontal">
		<div class="form-group">
		 <table class="table table-bordered table-striped" id="tab" style="width:80%;margin:5%">
			<tr>
				<td>网点名称</td>
				<td>圈存金额(元)</td>
				<td>半条金额(元)</td>
				<td>账户充值金额(元)</td>
				<td>账户冲正金额(元)</td>
				<td>退款金额(元)</td>
				<td>补交金额(元)</td>
				<td>合计</td>
			</tr>
			
				<c:forEach items="${channelModel}" var="li"  varStatus="voStatus">
					<tr>
							<td>${li.regionName }</td>
							<td>${li.amount }</td>
							<td>${li.btAount }</td>
							<td>${li.accountAmount }</td>
							<td>${li.correctAmount }</td>
							<td>${li.refund }</td>
							<td>${li.payAmount }</td>
							<td><fmt:formatNumber value="${fn:escapeXml(li.sum)}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber> </td>
					</tr>
			</c:forEach>
			</table>
	</div>
</form:form>	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" id="closeBtn" data-dismiss="modal">关闭</a>
	<!-- <a href="#" class="btn btn-sm btn-success"  id="submit">确定</a> -->
</div>

</body>
</html>