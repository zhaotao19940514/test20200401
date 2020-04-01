var CardReader = function() {
	CardReader.fn.init.prototype = CardReader.fn;
	var result = this.init();
	this.cardInfoAll = '';
};
CardReader.fn = CardReader.prototype = {
	constructor : CardReader,
	init : function() {
		//chrome
		this.objStr = '<object id="jlCardReader" type="application/x-itst-activex" width="0" height="0" clsid="{E726A4DB-39DE-4510-8DD0-3EEC3452B167}" progid="${rootUrl}cardReaderOcx/jl/ReaderOcx.ocx"></object>';
		//ie
//		this.objStr = '<object id="jlCardReader"  classid="clsid:{E726A4DB-39DE-4510-8DD0-3EEC3452B167}" codebase="${rootUrl}cardReaderOcx/jl/ReaderOcx.ocx" style="display:none;" ></object>';
		if($("#jlCardReader").length > 0){
			 $("#jlCardReader").remove();
		}
		$("body").prepend(this.objStr);
		this.reader = $("#jlCardReader").get(0);
		var result = this.setInfo();
		if(result == 0){
			console.log('初始化设备成功！');
		} else{
			console.log('初始化设备失败！');
		}
		this.reader.CloseCard();
	},
	setInfo : function(){
		var ReaderType = 4;
		var Com = 0;
		var SAMType = 1;
		var result = this.reader.SetInfo(ReaderType, Com, SAMType);
		return result;
	},
	getBalance : function() {
		return this.ifError(this.reader.GetBalance());
	},
	getPhysicalNo : function() {
		
		return this.ifError(this.reader.GetPhysicalNo());
	},
	getFaceNo : function() {
		return this.ifError(this.reader.GetFaceNo());
	},
	getmPSAMNo : function() {
		
		return this.ifError(this.reader.GetmPSAMNo());
	},
	getCirculation : function() {
		
		var circulation = this.ifError(this.reader.GetCirculation());
		if (circulation == 0) {
			return "保留";
		} else if (circulation == 1) {
			return "封闭式MTC入口";
		} else if (circulation == 2) {
			return "封闭式MTC出口";
		} else if (circulation == 3) {
			return "封闭式ETC入口";
		} else if (circulation == 4) {
			return "封闭式ETC出口";
		} else if (circulation == 5) {
			return "MTC开放式";
		} else if (circulation == 6) {
			return "ETC开放式";
		} else {
			return "未知:" + circulation;
		}
	},
	getUserInfo : function() {
		
		var userInfo = this.ifError(this.reader.GetUserInfoNoParam());
		return userInfo;
	},
	getUserIdentifier : function() {
		
		var userIdentifier = this.ifError(this.reader.GetUserInfo(1));
		return userIdentifier;
	},
	getStaffIdentifier : function() {
		
		var staffIdentifier = this.ifError(this.reader.GetUserInfo(2));
		return staffIdentifier;
	},
	getUserName : function() {
		
		var username = this.ifError(this.reader.GetUserInfo(3));
		return username;
	},
	getBindingFlag : function(){
		this.cardInfoAll = this.ifError(this.reader.GetCardInfoAll());
		console.log(this.cardInfoAll);
		var cardInfoArray = this.cardInfoAll.split(',');
		var bindingFlagStr = cardInfoArray[14];
		var nameValuePair = bindingFlagStr.split(':');
//		console.log(nameValuePair[0]);
//		console.log(nameValuePair[1]);
		return nameValuePair[1].substring(1,2);
	},
	getUserIdNum : function() {
		
		return this.ifError(this.reader.GetUserInfo(4));
	},
	getUserIdType : function() {
		
		return this.ifError(this.reader.GetUserInfo(5));
	},
	getCardInfo : function() {
		
		return this.ifError(this.reader.GetCardInfoNoParam());
	},
	getIssuerIdentifier : function() {
		
		return this.ifError(this.reader.GetCardInfo(1));
	},
	getIssuerTypeIdentifier : function() {
		return this.ifError(this.reader.GetCardInfo(2));
	},
	getCardVersion : function() {
		return this.ifError(this.reader.GetCardInfo(3));
	},
	getCardNetId : function() {
		return this.ifError(this.reader.GetCardInfo(4));
	},
	getCardInnerId : function() {
		return this.ifError(this.reader.GetCardInfo(5));
	},
	getCardId : function() {
		return  this.getCardNetId()+this.getCardInnerId();
	},
	getCardEnableTime : function() {
		return this.ifError(this.reader.GetCardInfo(6));
	},
	getCardExpireTime : function() {
		return this.ifError(this.reader.GetCardInfo(7));
	},
	getCardPlateNo : function() {
		
		return this.ifError(this.reader.GetCardInfo(8));
	},
	getCardUserType : function() {
		
		return this.ifError(this.reader.GetCardInfo(9));
	},
	getCardInfoPlateColor : function() {
		
		return this.ifError(this.reader.GetCardInfo(10));
	},
	getCardInfoVehicleType : function() {
		
		return this.ifError(this.reader.GetCardInfo(11));
	},
	getCardPlateColor : function() {
	    this.openCard();
		var cardVersion = getCardVersion(this).toString();
		//高4位
		var high4 = cardVersion.substring(0,1);
		var plateColor = '';
		//非4x卡
	    if(high4 != '4'){
	    	plateColor = get_Not4X_PlateColor(this);
	    }else if(high4 == '4'){//4x卡
	    	plateColor = get_4X_PlateColor(this);
		}
	    this.reader.CloseCard();
		return plateColor;
	},
	getLastTradeInfo : function() {
		
		return this.ifError(this.reader.GetLastTradeInfo());
	},
	getCardTradeNo : function() {
		
		return this.ifError(this.reader.GetCardTradeNo());
	},
	checkConnection : function() {
		return this.ifError(this.reader.CheckConnection());
	},
	getCardInfoAll : function() {
		
		return this.ifError(this.reader.GetCardInfoAll());
	},
	getUser : function() {
		
		return this.ifError(this.reader.GetUser());
	},
	openCard : function() {
		this.reader.CloseCard();
		var openFlag = this.reader.OpenCard();
		return openFlag;
	},
	closeCard : function() {
		var closeFlag = this.reader.CloseCard();
		$("#jlCardReader").remove();
		return closeFlag;
	},
	executeApdu : function(apdu) {
		console.log(this.reader);
		var cosres = this.ifError(this.reader.ExecuteApdu(apdu));
		var cosReponseArr = cosres.split('\|');
		var singleCosResponse = '';
		if (cosReponseArr.length == 3) {
			singleCosResponse = cosReponseArr[2] + cosReponseArr[1];
		} else if (cosReponseArr.length == 2) {
			singleCosResponse = cosReponseArr[1];
		}
		return singleCosResponse;
	},
	executeMultiApdus : function(multiApdu){
		 var apdus = multiApdu.split(',');
		 var cosResponse = '';
		 for(var i = 0;i<apdus.length;i++){
			 	var apdu = apdus[i];
				var cosres = this.ifError(this.reader.ExecuteApdu(apdu));
				var cosReponseArr = cosres.split('\|');
				var singleCosResponse = '';
				if (cosReponseArr.length == 3) {
					singleCosResponse = cosReponseArr[2] + cosReponseArr[1];
				} else if (cosReponseArr.length == 2) {
					singleCosResponse = cosReponseArr[1];
				}
				cosResponse += singleCosResponse;
				if(i!=apdus.length-1){
					cosResponse += ',';
				}
		 }
		 return cosResponse;
	},
	autoExecuteApdu : function(url, dataObj, method, terminateConditions,callback) {
		dataObj.applyStep = 1;
		var dataIn = transForm(dataObj);
		autoExecuteWithServer(url, dataIn, method, terminateConditions, this,callback);
	},
	// 错误提示
	ifError : function(result) {
		if (result == -1)
			$.Taiji.showWarn('初始化读卡器失败');
		else if (result == -2)
			$.Taiji.showWarn('找不到卡片');
		else if (result == -3)
			$.Taiji.showWarn('读卡器找不到SAM卡');
		else if (result == -4)
			$.Taiji.showWarn('卡片（SAM卡、CPU卡）复位失败 ');
		else if (result == -5)
			$.Taiji.showWarn('获取PSAM卡号失败');
		else if (result == -6)
			$.Taiji.showWarn('打开卡片失败');
		else if (result == -7)
			$.Taiji.showWarn('读取卡片信息失败 ');
		else if (result == -8)
			$.Taiji.showWarn('不支持的卡类型  ');
		else if (result == -9)
			$.Taiji.showWarn('传入的参数有误');
		else if (result == -10)
			$.Taiji.showWarn('选择卡片文件失败');
		else if (result == -11)
			$.Taiji.showWarn('消费初始化失败');
		else if (result == -12)
			$.Taiji.showWarn('计算MAC1码失败');
		else if (result == -13)
			$.Taiji.showWarn('消费（扣款）失败');
		else if (result == -14)
			$.Taiji.showWarn('校验MAC2码失败');
		else if (result == -15)
			$.Taiji.showWarn('验证个人密码失败');
		else if (result == -21)
			$.Taiji.showWarn('初始化通用密钥失败');
		else if (result == -22)
			$.Taiji.showWarn('计算密钥失败');
		else if (result == -23)
			$.Taiji.showWarn('圈存初始化失败');
		else if (result == -24)
			$.Taiji.showWarn('圈存失败');
		else if (result == -25)
			$.Taiji.showWarn('校验TAC码失败');
		else if (result == -26)
			$.Taiji.showWarn('非储值卡，不能充值');
		else if (result == -27)
			$.Taiji.showWarn('卡片已过期');
		else if (result == -31)
			$.Taiji.showWarn('取随机数失败');
		else if (result == -32)
			$.Taiji.showWarn('修改二进制文件失败 ');
		else if (result == -33)
			$.Taiji.showWarn('修改记录文件失败 ');
		else if (result == -34)
			$.Taiji.showWarn('读取二进制文件失败');
		else if (result == -35)
			$.Taiji.showWarn('读取记录文件失败');
		else if (result == -36)
			$.Taiji.showWarn('读取钱包文件失败');
		else if (result == -41)
			$.Taiji.showWarn('连接加密服务端失败');
		else if (result == -42)
			$.Taiji.showWarn('加密机计算MAC失败');
		else if (result == -61)
			$.Taiji.showWarn('卡片已被锁定');
		else if (result == -62)
			$.Taiji.showWarn('卡片锁定失败');
		else if (result == -99)
			$.Taiji.showWarn('命令执行成功');
		else if (result == -999)
			$.Taiji.showWarn('打开串口失败');
		else if (result == -1000)
			$.Taiji.showWarn('未定义错误');
		else
			return result;
	}
};
function transForm(data) {
	var dataObj = {};
	for (var param in data) {
		dataObj[param] = data[param];
	}
	dataObj.applyStep = 1;
	dataObj.command = null;
	dataObj.cosResponse = null;
	dataObj.cosRecordId = null;
	dataObj.rechargeId = null;
	return dataObj;
};
function transFormOrigin(dataObj,data) {
	for (var param in data) {
		dataObj[param] = data[param];
	}
	return dataObj;
};
function autoExecuteWithServer(url, dataObj, method, terminateConditions, _this,callback) {
	var afterHandler = callback;
	$.ajax({
		url : url,
		data : JSON.stringify(dataObj),
		type : method,
		contentType : 'application/json',
		dataType : 'json',
		success : function(data) {
			console.log(data);
			
			if (data.status != 1) {
				endOprate(_this, dataObj);
				$.Taiji.showWarn(data.message);
			} else if (!eval(terminateConditions)) {// 未达到结束条件
//				if (dataObj.applyStep == 1) {
//					var result = _this.openCard();
//					console.log(result);
//				}
				dataObj = transFormOrigin(dataObj,data);
				// 计步
				dataObj.applyStep = dataObj.applyStep + 1;
				if (data.command != '' && data.command != null && data.command != undefined) {
					dataObj.command = data.command;
					var cmds = data.command.split(',');
					var cosResponse = '';
					for (var i = 0; i < cmds.length; i++) {
						var apdu = cmds[i];
						var singleCosResponse = _this.executeApdu(apdu);
						if (i != cmds.length - 1) {
							singleCosResponse += ',';
						}
						cosResponse += singleCosResponse;
					}
					dataObj.cosResponse = cosResponse;
					console.log(cosResponse);
				}
				
				if (data.cosRecordId != '' && data.cosRecordId != null && data.cosRecordId != undefined) {
					dataObj.cosRecordId = data.cosRecordId;
				}
				if (data.rechargeId != '' && data.rechargeId != null && data.rechargeId != undefined) {
					dataObj.rechargeId = data.rechargeId;
				}
				if (dataObj.applyStep <= 15) {// 最多允许请求15次 防止无限请求
					console.log(dataObj);
					autoExecuteWithServer(url, dataObj, method, terminateConditions, _this,callback);
				} else {
					$.Taiji.showWarn("请求过多，终止请求");
				}
			} else {
				endOprate(_this, dataObj);
				callback && callback();
			}
		},
		error : function(error) {
			console.log(error);
			$.Taiji.hideLoading();
			$.Taiji.showWarn("请求错误 ajax:error");
		}
	})
};
function endOprate(_this, dataObj) {
	_this.reader.CloseCard();
	$.Taiji.hideLoading();
	dataObj.applyStep = 1;
};

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
	var commands = '00A40000023F00,00A40000021001,00B0950901';
    var result = cardReader.executeMultiApdus(commands);
    result = result.split(',')[2];
    result = checkApduResponse(result);
    console.log(result);
    return result;
};
function get_4X_PlateColor(cardReader){
	//4x 读第42位
    var commands = '00A40000023F00,00A40000021001,00B0952901';
    var result = cardReader.executeMultiApdus(commands);
    result = result.split(',')[2];
    result = checkApduResponse(result);
    console.log(result);
    return parseInt(result,16);
};
function get_Not4X_PlateColor(cardReader){
	//非4x 读第43位
	commands = '00A40000023F00,00A40000021001,00B0952A01';
    var result = cardReader.executeMultiApdus(commands);
    result = result.split(',')[2];
    result = checkApduResponse(result);
    console.log(result);
    return parseInt(result,16);
};
//function getPlateColor(){
//	var cardReader = new CardReader();
//    cardReader.openCard();
//	var cardVersion = getCardVersion(cardReader);
//	//高4位
//	var high4 = cardVersion.substring(0,1);
//	var plateColor = '';
//	//非4x卡
//    if(high4 != '4'){
//    	plateColor = get_Not4X_PlateColor(cardReader);
//    }else if(high4 == '4'){//4x卡
//    	plateColor = get_4X_PlateColor(cardReader);
//	}
//    cardReader.closeCard();
//	return plateColor;
//};
