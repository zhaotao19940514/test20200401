<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%> 
<style type="text/css">
#applyTestTable {
	text-align: center;
}
</style>
</head>
<body>
<!-- 	<object ID="vfjCardReader" width="0" height="0" style="display: none" -->
<!-- 		classid="clsid:db83c620-81dc-11e1-b0c4-0800200c9a66" -->
<%-- 		codebase="${rootUrl}cardReaderOcx/CardReaderOcx.ocx"></object> --%>
	<script type="text/javascript">
		var applyStep = 1;//请求到第几次
		var cardReader = new CardReader();
		$(function() {
			$("#tttt").click(function(){
                console.log(cardReader.getCardId());
            });
			$("#initBtn").click(function(){
				var comport = $("#comPort").val();//由页面填入端口号
				cardReader.init(comport);
			});
			$("#openBtn").click(function(){
                cardReader.openCard();
            });
			$("#closeBtn").click(function(){
                cardReader.closeCard();
            });
			$("#executeApduBtn").click(function(){
				var commands = "00A40000023F00,00A40000021001,0020000003123456,805C000204";
                var response = cardReader.executeMultiApdus(commands);
                console.log(response);
            });
			$("#callBackTest").click(function(){
				callBackTest(function(){
					$.Taiji.showWarn(2);
				});
			});
			function callBackTest(callback){
				$.Taiji.showWarn("1");
				callback && callback();
			}
			$("#testBtn").click(function(){
				console.log(cardReader);
	 			console.log(cardReader.getBalance());
	 			console.log(cardReader.getPhysicalNo());
	//  			console.log(cardReader.getFaceNo());
	 			console.log(cardReader.getmPSAMNo());
	 			console.log(cardReader.getCirculation());
	 			console.log(cardReader.getUserInfo());
	 			console.log(cardReader.getCardInfo());
	 			console.log(cardReader.getLastTradeInfo());
	 			console.log(cardReader.getCardTradeNo());
	//  			console.log(cardReader.checkConnection());
	 			console.log(cardReader.getCardInfoAll());
	 			console.log(cardReader.getUser());
	 			console.log(cardReader.getUserIdentifier());
	 			console.log(cardReader.getStaffIdentifier());
	 			console.log(cardReader.getUserName());
	 			console.log(cardReader.getUserIdNum());
	 			console.log(cardReader.getUserIdType());
	 			console.log(cardReader.getIssuerIdentifier());
	 			console.log(cardReader.getIssuerTypeIdentifier());
	 			console.log(cardReader.getCardVersion());
	 			console.log(cardReader.getCardNetId());
	 			console.log(cardReader.getCardInnerId());
	 			console.log(cardReader.getCardEnableTime());
	 			console.log(cardReader.getCardExpireTime());
	 			console.log(cardReader.getCardPlateNo());
	 			console.log(cardReader.getCardUserType());
	 			console.log(cardReader.getCardPlateColor());
			});
			$("#myManage").taiji({
				enableAclCheck : true
			});
			$("#applyCardTestBtn").click(function() {
				var cardId = $("#cardId").val();
				var cardType = $("#cardType").val();
				var userId = $("#userId").val();
				var enableTime = $("#enableTime").val();
				var expireTime = $("#expireTime").val();
				var plateNo = $("#plateNo").val();
				var plateColor = $("#plateColor").val();
				var vehicleId = plateNo + '_' + plateColor;
				var pkgId = $("#pkgId").val();
				var netId = $("#netId").val();
				var cosResponse;
				var command;
				var cosRecordId;
				var data = {};
				data.cardId = cardId;
				data.cardType = cardType;
				data.userId = userId;
				data.enableTime = enableTime;
				data.expireTime = expireTime;
				data.vehicleId = vehicleId;
				data.pkgId = pkgId;
				data.netId = netId;
// 				data.cosResponse = null;
// 				data.command = null;
// 				data.cosRecordId = null;
				//调用结束条件  一个返回为boolean值的语句
				var terminateConditions = "data.cardId != '' && data.cardId != null	&& data.cardId != undefined";
				var url = "applyCard";
				var method = 'POST';
				cardReader.autoExecuteApdu(url,data,method,terminateConditions);
			});
		});
	</script>
	<div>
	   <a id="tttt" class="btn btn-primary ">tttt</a>
		<div>
			<label for="comPort">端口号:</label>
			<input type="text" id="comPort" value="COM3"/>
			<a id="initBtn" class="btn btn-primary ">初始化读卡器</a>
			<br/>
			<a id="testBtn" class="btn btn-primary ">读卡器测试</a>
			<div>
            <a id="openBtn" class="btn btn-primary ">打开卡片</a>
            <a id="closeBtn" class="btn btn-primary ">关闭卡片</a>
            <a id="executeApduBtn" class="btn btn-primary ">执行测试APDU</a>
            <a id="callBackTest" class="btn btn-primary ">回调测试</a>
        </div>
		</div>
		<table id="applyTestTable">
			<tbody>
				<tr>
					<th><label for="cardId">卡片编号</label></th>
					<td><input name="cardId" id="cardId" type="text"
						value="52011750220200003680"></td>
				</tr>
				<tr>
					<th><label for="netId">卡片网络编号</label></th>
					<td><input name="netId" id="netId" type="text" value="5201">
					</td>
				</tr>
				<tr>
					<th><label for="cardType">卡类型</label></th>
					<td><select name="cardType" id="cardType">
							<option value="">请选择</option>
							<option value="1">记账卡</option>
							<option selected="selected" value="2">储值卡</option>
					</select></td>
				</tr>
				<tr>
					<th><label for="userId">用户ID</label></th>
					<td><input name="userId" id="userId" type="text"
						value="52010118061970031"></td>
				</tr>
				<tr>
					<th><label for="enableTime">生效时间</label></th>
					<td><input name="enableTime" id="enableTime" type="text"
						value="2018-07-21T00:00:00"></td>
				</tr>
				<tr>
					<th><label for="expireTime">失效时间</label></th>
					<td><input name="expireTime" id="expireTime" type="text"
						value="2028-07-21T00:00:00"></td>
				</tr>
				<tr>
					<th><label for="plateNo">车牌号</label></th>
					<td><input name="plateNo" id="plateNo" type="text"
						value="贵Z00000"></td>
				</tr>
				<tr>
					<th><label for="plateColor">车牌颜色</label></th>
					<td><input name="plateColor" id="plateColor" type="text"
						value="4"></td>
				</tr>
				<tr>
					<th><label for="pkgId">套餐编号</label></th>
					<td><input name="pkgId" id="pkgId" type="text" value="5"></td>
				</tr>
				<tr>
					<td>
						<button class="btn btn-primary btn-small" id="applyCardTestBtn">一键测试卡发行</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>