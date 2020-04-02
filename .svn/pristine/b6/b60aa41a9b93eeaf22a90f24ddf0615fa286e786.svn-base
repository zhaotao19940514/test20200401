var CardReader = function() {
	CardReader.fn.init.prototype = CardReader.fn;
	var result = this.init();
};
CardReader.fn = CardReader.prototype = {
	constructor : CardReader,
	init : function() {
		try {
			// chrome
//			this.objStr = '<object id="ccbCardReader" type="application/x-itst-activex" width="0" height="0" clsid="{1C82A352-5A9B-46F8-880A-2E02FEBE5197}" progid="${rootUrl}cardReaderOcx/ccb/CCB_GWI_IC.ocx"></object>';
			// ie
			this.objStr = '<object id="ccbCardReader"  classid="clsid:{1C82A352-5A9B-46F8-880A-2E02FEBE5197}" codebase="${rootUrl}cardReaderOcx/CardReaderOcx.ocx" style="display:none;" ></object>';
			if($("#ccbCardReader").length > 0){
				 $("#ccbCardReader").remove();
			}
			$("body").prepend(this.objStr);
			this.reader = $("#ccbCardReader").get(0);
			this.portNo = 2;
			this.baud = 9600;
			this.slotNum = 0;
			this.timeOut = 1000;
			this.cardId = '';
			this.openCard();
		} catch (e) {
			console.log(e);
		}
	},
	getBalance : function() {
		try {
			var apdus = "00A40000023F00,00A40000021001,0020000003123456,805C000204";
			var result = this.getParamWithApdus(apdus,3);
			return parseInt(result,16);
		} catch (e) {
			console.log(e);
			$.Taiji.showWarn('获取余额失败！');
		}
	},
	getPhysicalNo : function() {
		var apdus = "00A40000023F00,00A40000021001,00B0950C08";
		var result = this.getParamWithApdus(apdus,2);
		return result;
	},
	getFaceNo : function() {
		var apdus = "00A40000023F00,00A40000021001,00B0950A0A";
		var result = this.getParamWithApdus(apdus,2);
		return result;
	},
//	getmPSAMNo : function() {

//		return ifError(this.reader.GetmPSAMNo());
//	},
//	getCirculation : function() {

//		var circulation = ifError(this.reader.GetCirculation());
//		if (circulation == 0) {
//			return "保留";
//		} else if (circulation == 1) {
//			return "封闭式MTC入口";
//		} else if (circulation == 2) {
//			return "封闭式MTC出口";
//		} else if (circulation == 3) {
//			return "封闭式ETC入口";
//		} else if (circulation == 4) {
//			return "封闭式ETC出口";
//		} else if (circulation == 5) {
//			return "MTC开放式";
//		} else if (circulation == 6) {
//			return "ETC开放式";
//		} else {
//			return "未知:" + circulation;
//		}
//	},
	
//	getUserInfo : function() {

//		return ifError(this.reader.GetUserInfoNoParam());
//	},
	getUserIdentifier : function() {
		this.getFullCardInfoWithServer();
		return this.file0016.cardholderId;
	},
	getStaffIdentifier : function() {
		this.getFullCardInfoWithServer();
		return this.file0016.systemWorksId;
	},
	getUserName : function() {
		this.getFullCardInfoWithServer();
		return this.file0016.customerName;
	},
	getBindingFlag : function(){
		this.getFullCardInfoWithServer();
		return this.file000E.obuBindingTags;
	},
	getUserIdNum : function() {
		this.getFullCardInfoWithServer();
		return this.file0016.customerIdNum;
	},
	getUserIdType : function() {
		this.getFullCardInfoWithServer();
		return this.file0016.customerIdType;
	},
//	getCardInfo : function() {

//		return ifError(this.reader.GetCardInfoNoParam());
//	},
//	getIssuerIdentifier : function() {

//		return ifError(this.reader.GetCardInfo(1));
//	},
	getIssuerTypeIdentifier : function() {
		this.getFullCardInfoWithServer();
		return this.file0015.cardType;
	},
	getCardVersion : function() {
		this.getFullCardInfoWithServer();
		return this.file0015.cardVersion;
	},
	getCardNetId : function() {
		try {
			var cardId = this.getCardId();
			return cardId.substring(0,4);
		} catch (e) {
			console.log(e);
		}
	},
	getCardInnerId : function() {
		try {
			var cardId = this.getCardId();
			return cardId.substring(4,cardId.length);
		} catch (e) {
			console.log(e);
		}
	},
	getCardId : function() {
		return this.getFaceNo();
	},
	getCardEnableTime : function() {
		this.getFullCardInfoWithServer();
		return this.file0015.enableTime;
	},
	getCardExpireTime : function() {
		this.getFullCardInfoWithServer();
		return this.file0015.expireTime;
	},
	getCardPlateNo : function() {
		this.getFullCardInfoWithServer();
		return this.file0015.vehiclePlate;
	},
//	getCardUserType : function() {

//		return ifError(this.reader.GetCardInfo(9));
//	},
	getCardPlateColor : function() {
		this.getFullCardInfoWithServer();
		return this.file0015.vehiclePlateColor;
	},
//	getLastTradeInfo : function() {

//		return ifError(this.reader.GetLastTradeInfo());
//	},
//	getCardTradeNo : function() {

//		return ifError(this.reader.GetCardTradeNo());
//	},
//	checkConnection : function() {
//
//		return ifError(this.reader.CheckConnection());
//	},
//	getCardInfoAll : function() {

//		return ifError(this.reader.GetCardInfoAll());
//	},
//	getUser : function() {

//		return ifError(this.reader.GetUser());
//	},
	// TODO
	checkStatus : function(result){
		var arr = result.toString().split('\|');
		if(arr[0]!=0){
			if(arr[0] == '-3'){
				$.Taiji.showWarn('请检查设备连通性！确认读卡器所用端口号为2！');
			}
			return arr[0];
			// 可能的其它情况。。。暂无文档
		}else{
			return 0;
		}
	},
	openCard : function() {
		try {
			var result = this.closeOnly();
			var status = this.checkStatus(result);
			if(status!=0){
				return status;
			}
			result = this.reader.CCB_ICC_PowerOn(this.portNo,this.baud,this.slotNum,this.timeOut);
			return this.checkStatus(result);
		} catch (e) {
			console.log(e);
			$.Taiji.showWarn("卡片上电失败，请联系管理员检查参数是否正确！");
		}
	},
	closeOnly : function(){
		try {
			var result = this.reader.CCB_ICC_PowerOff(this.portNo,this.baud,this.slotNum,this.timeOut);
			this.checkStatus(result);
			return result;
		} catch (e) {
			console.log(e);
			$.Taiji.showWarn("卡片下电失败，请联系管理员检查参数是否正确！");
		}
	},
	closeCard : function() {
		try {
			var result = this.reader.CCB_ICC_PowerOff(this.portNo,this.baud,this.slotNum,this.timeOut);
			this.checkStatus(result);
			$("#ccbCardReader").remove();
			return result;
		} catch (e) {
			console.log(e);
			$.Taiji.showWarn("卡片下电失败，请联系管理员检查参数是否正确！");
		}
	},
	checkApduResponse : function(result){
		if(result==null || result == ''|| result==undefined){
			$.Taiji.showWarn('apdu指令返回错误！执行已中断！');
			return ;
		}
		var arr = result.split('\|');
		if(arr.length !=2 ){
			$.Taiji.showWarn('apdu指令返回长度错误！执行已中断！');
			return ;
		}else{
			if(arr[0] != 0){
				$.Taiji.showWarn('apdu指令执行错误！执行已中断！');
				return ;
			}else{
				if(arr[1]==null || arr[1] == ''|| arr[1]==undefined || arr[1].length < 4){
					var message = 'apdu指令执行错误！执行已中断！返回：'+arr[1]+".";
					$.Taiji.showWarn(message);
//					return ;
					throw message;
				}
//				else if(!arr[1].endWith("9000")){
//					$.Taiji.showWarn('apdu指令执行失败，返回值"+arr[1]+"，请谨慎操作以免锁卡！');
//					return ;
//				}
			}
		}
	},
	executeApdu : function(apdu){
		try {
			var result = this.reader.CCB_ICC_Apdu(this.portNo,this.baud,this.slotNum,apdu,this.timeOut);
			this.checkStatus(result);
			this.checkApduResponse(result);
			return result;
		} catch (e) {
			console.log(e);
			$.Taiji.showWarn("卡片上电失败，请联系管理员检查参数是否正确！");
		}
	},
	executeMultiApdus : function(multiApdu){
		 var apdus = multiApdu.split(',');
		 var cosResponse = '';
		 for(var i = 0;i<apdus.length;i++){
			 	var apdu = apdus[i];
				var cosres = this.executeApdu(apdu);
				this.checkApduResponse(cosres);
				var cosReponseArr = cosres.split('\|');
				var singleCosResponse = '';
				singleCosResponse = cosReponseArr[1];
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
	getFullCardInfoWithServer : function(callback){
		try {
			var nowCardId = this.getCardId();
			if(this.cardId == nowCardId){
				return;
			}
			var _this = this;
			var opened = this.openCard();
			if(opened!=undefined && opened.toString().length>=1 && opened.toString().substring(0,1) == 0){
				
				var readFile0015Apdu = "00A40000023F00,00A40000021001,00B0950000";
				var readFile0016Apdu = "00A40000023F00,00B0960000";
				var readFile000EApdu = "00A40000023F00,00A40000021001,00B08E0000";
				
				var card0015Response = this.executeMultiApdus(readFile0015Apdu);
				var card0016Response = this.executeMultiApdus(readFile0016Apdu);
				var card000EResponse = this.executeMultiApdus(readFile000EApdu);
				var data = {};
				var arr0015 = card0015Response.split(',');
				var arr0016 = card0016Response.split(',');
				var arr000E = card000EResponse.split(',');
				data.file0015CardResponse = arr0015[2].substring(0,arr0015[2].length-4);
				data.file0016CardResponse = arr0016[1].substring(0,arr0016[1].length-4);
				data.file000ECardResponse = arr000E[2].substring(0,arr000E[2].length-4);
				var url = "/css/app/ocx/card/cardinforesolve";
				$.ajax({
					url : url,
					data : JSON.stringify(data),
					type : "POST",
					async: false,
					contentType : 'application/json',
					dataType : 'json',
					success : function(response) {
						_this.file0015 = JSON.parse(response.file0015Json);
						_this.file0016 = JSON.parse(response.file0016Json);
						_this.file000E = JSON.parse(response.file000EJson);
						_this.cardId = _this.file0015.cardId;
						callback && callback(response);
					},
					error : function(){
						$.Taiji.showWarn('请求解析卡文件内容失败！请联系系统管理员！');
					}
				});
			}else{
				callback && callback();
			}
		} catch (e) {
			console.log(e);
		}
	},
	getParamWithApdus : function(apdus,index){
		try {
			this.openCard();
			var response = this.executeMultiApdus(apdus);
			var arr = response.split(',');
			var result = arr[index].trim();
			if(result!=null && result!=undefined && result.length>=4){
				result = result.substring(0,result.length-4);
			}
			this.closeOnly();
			return result;
		} catch (e) {
			console.log(e);
			$.Taiji.showWarn('获取属性失败！');
		}
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
				console.log(dataObj.applyStep);
				if (data.command != '' && data.command != null && data.command != undefined) {
					dataObj.command = data.command;
					var cosResponse = _this.executeMultiApdus(dataObj.command);
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
//				$.Taiji.showWarn('操作成功！');
			}
		},
		error : function(error) {
			console.log(error);
			endOprate(_this, dataObj);
			$.Taiji.hideLoading();
			$.Taiji.showWarn("请求错误 ajax:error");
		}
	})
};
function endOprate(_this, dataObj) {
	_this.closeOnly();
	$.Taiji.hideLoading();
	dataObj.applyStep = 1;
};
