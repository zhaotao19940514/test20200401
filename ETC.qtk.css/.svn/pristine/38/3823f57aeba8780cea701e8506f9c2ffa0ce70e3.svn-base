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
<%-- <script type="text/javascript" src='${rootUrl }myjs/ocx/ccbCardReader.js'></script> --%>
<style type="text/css">
#applyTestTable {
    text-align: center;
}
</style>
</head>
<body>
    <script type="text/javascript">
        $(function(){
        	
            $("#openBtn").click(function(){
	            var reader = new CardReader();
            	reader.openCard();
            });
            $("#executeApduBtn").click(function(){
            	var reader = new CardReader();
                reader.openCard();
            	var apdu = $("#apdu").val();
                var result = reader.executeApdu(apdu);
                console.log(result);
            });
            $("#executeMultiApduBtn").click(function(){
                var apdu = $("#multiApdu").val();
                var result = reader.executeMultiApdus(apdu);
                console.log(result);
            });
            $("#readFullCardInfo").click(function(){
            	reader.getFullCardInfoWithServer(function(response){
            	    console.log('--------file0015----------');        
            	    console.log(response);
            		var file0015 = JSON.parse(response.file0015Json);
            		console.log(file0015.cardVersion);
            		console.log(file0015.type);
            		console.log(file0015.vehiclePlateColor);
            		console.log(file0015.cardType);
            		console.log(file0015.cardId);
            		console.log(file0015.enableTime);
            		console.log(file0015.expireTime);
            		console.log(file0015.vehiclePlate);
            		console.log(file0015.customerType);
            		console.log('--------file0016----------');     
            		var file0016 = JSON.parse(response.file0016Json);
                    console.log(file0016.customerIdType);
                    console.log(file0016.customerIdNum);
                    console.log('customerName'+file0016.customerName);
                    console.log(file0016.cardholderId);
                    console.log('--------file000E----------');     
                    var file000E = JSON.parse(response.file000EJson);
                    console.log(file000E.obuBindingTags);
                    console.log(file000E.carFollowingSign);
                    console.log(file000E.classification);
                    console.log(file000E.vehicleType);
                    console.log(file000E.unitName);
                    console.log(file000E.plateColor);
                    console.log(file000E.retain);
                    console.log(file000E.nuclearLoad);
                    console.log(file000E.bankCardNumber);
                    console.log(file000E.preferentialRoadSection);
//                     console.log(file000E.preferentialRoadCode);
                    console.log(file000E.typesOfSubscription);
            	});
            });
            $("#closeBtn").click(function(){
            	reader.closeCard();
            });
            $("#testBtn").click(function(){
            	function checkApduResponse(response){
            	    if(response==undefined || response==null || response==''){
            	        alert('指令执行失败');
            	        return ;
            	    }
            	    if(response.length<4 || response.substring(response.length-4,response.length)!='9000'){
            	        alert('指令执行失败');
            	        return ;
            	    }
            	    var result = response.substring(0,response.length-4);
            	    return result;
            	};
            	function getCardVersion(cardReader){
            	    var commands = '00A40000023F00,00A40000021001,00B0950801';
            	    var result = cardReader.executeMultiApdus(commands);
            	    result = result.split(',')[2];
            	    result = checkApduResponse(result);
            	    console.log(result);
            	    return result;
            	};
            	function get_4X_PlateColor(cardReader){
            	    //4x 读第42位
            	    var commands = '00A40000023F00,00A40000021001,00B0950801';
            	    var result = cardReader.executeMultiApdus(commands);
            	    result = result.split(',')[2];
            	    result = checkApduResponse(result);
            	    console.log(result);
            	    return result;
            	};
            	function get_Not4X_PlateColor(cardReader){
            	    //非4x 读第43位
            	    commands = '00A40000023F00,00A40000021001,00B0952A01';
            	    var result = cardReader.executeMultiApdus(commands);
            	    result = result.split(',')[2];
            	    result = checkApduResponse(result);
            	    console.log(result);
            	    return result;
            	};
            	function getPlateColor(){
	            	var cardReader = new CardReader();
	           	    cardReader.openCard();
	            	var cardVersion = getCardVersion(cardReader);
	            	//高4位
	            	var high4 = cardVersion.substring(0,1);
	            	var plateColor = '';
	            	//非4x卡
                    if(high4 != '4'){
                        plateColor = get_Not4X_PlateColor(cardReader);
	           	    }else if(high4 == '4'){//4x卡
	           	        plateColor = get_4X_PlateColor(cardReader);
	            	}
	           	    cardReader.closeCard();
	            	return plateColor;
            	};
            	console.log(reader.getBalance());
            	console.log(reader.getPhysicalNo());
            	console.log(reader.getFaceNo());
            	console.log(reader.getUserIdentifier());
            	console.log(reader.getStaffIdentifier());
            	console.log(reader.getUserName());
            	console.log(reader.getBindingFlag());
            	console.log(reader.getUserIdNum());
            	console.log(reader.getUserIdType());
            	console.log(reader.getIssuerTypeIdentifier());
            	console.log(reader.getCardVersion());
            	console.log(reader.getCardNetId());
            	console.log(reader.getCardInnerId());
            	console.log(reader.getCardId());
            	console.log(reader.getCardEnableTime());
            	console.log(reader.getCardExpireTime());
            	console.log(reader.getCardPlateNo());
            	console.log(reader.getCardPlateColor());
            });
        });
    </script>
    <div>
       <a id="tttt" class="btn btn-primary ">tttt</a>
        <div>
            <label for="comPort">端口号:</label>
            <input type="text" id="comPort" value="0"/>
            <a id="initBtn" class="btn btn-primary ">初始化读卡器</a>
            <br/>
            <a id="testBtn" class="btn btn-primary ">读卡器测试</a>
            <br/>
            <br/>
            <div>
            <a id="openBtn" class="btn btn-primary ">打开卡片</a><br/>
            <a id="executeApduBtn" class="btn btn-primary ">执行测试APDU</a>
            <label for="apdu">卡指令:</label>
            <input type="test" id="apdu" value="0084000004" /><br/>
            <a id="executeMultiApduBtn" class="btn btn-primary ">执行测试MULTIAPDU</a>
            <label for="multiApdu">卡指令:</label>
            <input type="test" id="multiApdu" value="0084000004,0084000004,0084000004" /><br/>
            <a id="readFullCardInfo" class="btn btn-primary ">执行读取卡内全部信息</a><br/>
            <a id="closeBtn" class="btn btn-primary ">关闭卡片</a>
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