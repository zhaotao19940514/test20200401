<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<%-- <script type="text/javascript" src='${rootUrl }myjs/ocx/misposClient.js'></script> --%>
<%@include file="/WEB-INF/jsp/deviceInclude/posDeviceHeader.jsp"%>
<style type="text/css">
#applyTestTable {
	text-align: center;
}
</style>
</head>
<body>
	<script type="text/javascript">
		$(function() {
			$("#testOneCent").click(function() {
				var data = {};
				var posTradeType = $("#posTradeType").val();
				console.log(posTradeType == 'CONSUME');
				if (posTradeType == 'CONSUME') {
					data.amount = 1;
					// 					data.obuId='111';
				}
				// 				data.posTradeType = posTradeType;
				ajaxPosCommand(data);
			});
			$("#testOneCentConfirm")
					.click(
							function() {
								var data = {};
								var posTradeType = $("#posTradeType").val();
								if (posTradeType == 'CONSUME') {
									data.amount = 1;
									//                     data.obuId='111';
								}
								data.posTradeType = posTradeType;

								//confirm填入
								data.command = '05,,,,,000000000001,,,,,,,,,,66666,99999';
								data.posResponse = '05,62148*******6076,,,,000000000001,,10000461,195702,20180726,00,交易成功,240226010698061,240226010698,102701089992230,66666,99999';
								ajaxPosConsumeConfirm(data);
							});
		});
		function ajaxPosCommand(data) {
			$.ajax({
				url : "posTradeCos",
				data : JSON.stringify(data),
				type : 'POST',
				contentType : 'application/json',
				dataType : 'json',
				success : function(response) {
					var command = response.command;
					console.log(command);
					var misposClient = new MisPosClient();
					console.log(MisPosClient);
					var posResponse = misposClient.trade(command);
					console.log(posResponse);

					// data已包含obuId、amount、posTradeType
					data.command = command;
					data.posResponse = posResponse;
					ajaxPosConsumeConfirm(data);
				},
				error : function(response) {
					console.log(response);
				}
			});
		}
		function ajaxPosConsumeConfirm(data) {
			$.ajax({
				url : "posTradeConfirm",
				data : JSON.stringify(data),
				type : 'POST',
				contentType : 'application/json',
				dataType : 'json',
				success : function(response) {
					console.log(response);
					if (response.success) {

					}
					$.Taiji.showWarn(response.message);
				},
				error : function(response) {
					console.log(response);
				}
			});
		}
	</script>
	<div>
		<div>
			<div>
				<select id="posTradeType" name="posTradeType">
					<c:forEach items="${types}" var="type">
						<option value="${type}">${type.value}</option>
					</c:forEach>
				</select> <a id="testOneCent" class="btn btn-primary btn-sm">测试交易</a> <a
					id="testOneCentConfirm" class="btn btn-primary btn-sm">测试交易确认</a>
			</div>
		</div>
	</div>
</body>
</html>