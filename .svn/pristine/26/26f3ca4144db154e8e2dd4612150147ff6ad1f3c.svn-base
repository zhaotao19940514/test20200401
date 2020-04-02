<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<script type='text/javascript' src='${rootUrl}myjs/chromeversion/jlCardReader.js'></script>
<script type="text/javascript">
$.ajaxSetup({cache : false});
</script>
<style type="text/css">
#applyTestTable {
    text-align: center;
}
</style>
</head>
<body>
<%--     <object id="vfjCardReader" type="application/x-itst-activex" width="0" height="0" clsid="{db83c620-81dc-11e1-b0c4-0800200c9a66}" progid="${rootUrl}cardReaderOcx/CardReaderOcx.ocx"></object> --%>
<%-- <object id="vfjCardReader" type="application/x-itst-activex" width="0" height="0" clsid="{db83c620-81dc-11e1-b0c4-0800200c9a66}" progid="${rootUrl}cardReaderOcx/CardReaderOcx.ocx"></object> --%>
    config : ${loginUser.staff.serviceHall }
    <script type="text/javascript">
        var applyStep = 1;//请求到第几次
        $(function() {
            $("#tCardId").click(function(){
            	var cardReader = new CardReader();
            	cardReader.openCard();
            	var faceNo = cardReader.getFaceNo();
            	cardReader.closeCard();
//             	alert(faceNo);
            	console.log(faceNo);
            });
            $("#tBalance").click(function(){
            	var cardReader = new CardReader();
            	cardReader.openCard();
            	var balance = cardReader.getBalance();
            	cardReader.closeCard();
//             	alert(faceNo);
            	console.log(balance);
            });
            $("#tUserInfo").click(function(){
            	var cardReader = new CardReader();
            	cardReader.openCard();
            	var info1 = cardReader.getUserIdentifier();
            	var info2 = cardReader.getStaffIdentifier();
            	var info3 = cardReader.getUserName();
            	var info4 = cardReader.getUserIdNum();
            	var info5 = cardReader.getUserIdType();
            	cardReader.closeCard();
//             	alert(faceNo);
            	console.log(info1);
            	console.log(info2);
            	console.log(info3);
            	console.log(info4);
            	console.log(info5);
            });
            $("#tUserInfoAll").click(function(){
            	var cardReader = new CardReader();
            	var cardInfo = cardReader.getUserInfo();
				console.log(cardInfo);
            });
            $("#tCardInfoAll").click(function(){
            	var cardReader = new CardReader();
            	var cardInfo = cardReader.getCardInfoAll();
				console.log(cardInfo);
            });
            $("#tCardInfo").click(function(){
            	var cardReader = new CardReader();
            	var cardInfo = cardReader.getCardInfo();
				console.log(cardInfo);
            });
            $("#tCardInfoOnly").click(function(){
            	var cardReader = new CardReader();
            	var info1 = cardReader.getIssuerIdentifier();
            	var info2 = cardReader.getIssuerTypeIdentifier();
            	var info3 = cardReader.getCardVersion();
            	var info4 = cardReader.getCardNetId();
            	var info5 = cardReader.getCardInnerId();
            	var info6 = cardReader.getCardEnableTime();
            	var info7 = cardReader.getCardExpireTime();
            	var info8 = cardReader.getCardPlateNo();
            	var info9 = cardReader.getCardUserType();
            	var info10 = cardReader.getCardInfoPlateColor();
            	var info11 = cardReader.getCardInfoVehicleType();
//             	var info12 = cardReader.getCardPlateColor();
				console.log(info1);
				console.log(info2);
				console.log(info3);
				console.log(info4);
				console.log(info5);
				console.log(info6);
				console.log(info7);
				console.log(info8);
				console.log(info9);
				console.log(info10);
				console.log(info11);
// 				console.log(info12);
            });
            $("#onlyExecuteApduBtn").click(function(){
            	var cardReader = new CardReader();
            	var apdu = $("#txtAPDU").val();
				console.log(apdu);
            	var info = cardReader.executeApdu(apdu);
				console.log("执行结果：");
				console.log(info);
                cardReader.closeCard();
            });
            $("#initBtn").click(function(){
                cardReader.openCard();
                var comport = $("#comPort").val();//由页面填入端口号
                cardReader.init(comport);
                cardReader.closeCard();
            });
            $("#openBtn").click(function(){
            	var cardReader = new CardReader();
                 var status = cardReader.openCard();
                 console.log(status);
                 if(status == 0){
 					$.Taiji.showNote('打开卡片成功！');
 				}
                 cardReader.closeCard();
            });
            $("#closeBtn").click(function(){
            	var cardReader = new CardReader();
                var status = cardReader.closeCard();
                console.log(status);
                if(status == 0){
					$.Taiji.showNote('关闭卡片成功！');
				}
            });
            $("#executeApduBtn").click(function(){
            	var cardReader = new CardReader();
                var opened = cardReader.openCard();
                var commands = "00A40000023F00,00A40000021001,0020000003123456,805C000204";
                var response = cardReader.executeMultiApdus(commands);
                console.log(response);
                cardReader.closeCard();
            });
            $("#testBtn").click(function(){
            	 
                 cardReader.openCard();
                console.log(cardReader);
                console.log(cardReader.getBalance());
                console.log(cardReader.getPhysicalNo());
    //              console.log(cardReader.getFaceNo());
                console.log(cardReader.getmPSAMNo());
                console.log(cardReader.getCirculation());
                console.log(cardReader.getUserInfo());
                console.log(cardReader.getCardInfo());
                console.log(cardReader.getLastTradeInfo());
                console.log(cardReader.getCardTradeNo());
    //              console.log(cardReader.checkConnection());
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
                cardReader.closeCard();
            });
            $("#myManage").taiji({
                enableAclCheck : true
            });
            $("#applyCardTestBtn").click(function() {
            	var cardReader = new CardReader();
                 cardReader.openCard();
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
//              data.cosResponse = null;
//              data.command = null;
//              data.cosRecordId = null;
                //调用结束条件  一个返回为boolean值的语句
                var terminateConditions = "data.cardId != '' && data.cardId != null && data.cardId != undefined";
                var url = "applyCard";
                var method = 'POST';
                cardReader.autoExecuteApdu(url,data,method,terminateConditions,function(){
                	cardReader.closeCard();
                });
            });
        });
    </script>
    <div>
       <a id="tCardId" class="btn btn-primary ">读取卡号</a>
       <a id="tBalance" class="btn btn-primary ">读取余额</a>
       <a id="tUserInfo" class="btn btn-primary ">GetUserInfo</a>
       <a id="tUserInfoAll" class="btn btn-primary ">GetUserInfoNoParam</a>
       <a id="tCardInfoAll" class="btn btn-primary ">getCardInfoAll</a>
       <a id="tCardInfoOnly" class="btn btn-primary ">GetCardInfo</a>
       <a id="tCardInfo" class="btn btn-primary ">GetCardInfoNoParam</a>
       <div>
       	<textarea id="txtAPDU" rows="5" cols="30"></textarea>
       	<a id="onlyExecuteApduBtn" class="btn btn-primary ">执行单条测试APDU</a>
       </div>
        <div>
            <label for="comPort">端口号:</label>
            <input type="text" id="comPort" value="0"/>
            <a id="initBtn" class="btn btn-primary ">初始化读卡器</a>
            <br/>
            <a id="testBtn" class="btn btn-primary ">读卡器测试</a>
            <div>
            <a id="openBtn" class="btn btn-primary ">打开卡片</a>
            <a id="closeBtn" class="btn btn-primary ">关闭卡片</a>
            <a id="executeApduBtn" class="btn btn-primary ">执行测试APDU</a>
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