var ObuOfflineReader = function() {
	ObuOfflineReader.fn.init.prototype = ObuOfflineReader.fn;
	this.init();
	this.sysInfo = '';
	this.vehicleInfo = '';
};
ObuOfflineReader.fn = ObuOfflineReader.prototype = {
	constructor : ObuOfflineReader,
	init : function(){
		this.reader = $("#ObuOfflineApi").get(0);
		// 定义指令集
		this.readerCommands = {
				// 指令格式 : 指令,入参...
				openObu :'OpenDevice',
				closeObu : 'CloseDevice',
				readSysInfo : 'readSysInfo',
				readVehInfo : 'readVehicleInfo',// Test
				writeSysInfo : 'writeSysInfo,sysInfo',
				writeVehInfo : 'writeVehicleInfo，vehInfo',
				writeSysAndVehInfo : 'writeSysAndVehInfo,sysInfo,vehInfo',
				getProperty : 'getProperty,info,propertyName'
		};
		// 定义属性在信息字符串中的位置
		this.propertiesIndexs = {
				plateNum : 'vehInfo,0', // 车牌号
				plateColor : 'vehInfo,1', // 车牌颜色
				vehicleType : 'vehInfo,2', // 车辆类型
				vehicleUserType : 'vehInfo,3', // 车辆用户类型
				vehicleLength : 'vehInfo,4', // 车长
				vehicleWidth : 'vehInfo,5', // 车宽
				vehicleHeight : 'vehInfo,6', // 车高
				wheelsCount : 'vehInfo,7', // 车轮数
				axlesCount : 'vehInfo,8', // 车轴数
				wheelBase : 'vehInfo,9', // 轴距
				load : 'vehInfo,10', // 载重/座位数
				vehicleFeature : 'vehInfo,11', // 车辆特征
				engineNum : 'vehInfo,12' // 发动机编号
		};
		// 定义信息格式？？
		this.propertiesIndexs = {
				sysInfo : '',
				vehicleInfo : ''
		};
	},
	readFullSysInfo : function(){
		var sysInfo = this.sysInfo;
		if(sysInfo==''){
			$.Taiji.showWarn('设备未打开');
		}else{
			return sysInfo;
		}
	},
	readSysInfo : function(){
// var sysInfo = this.reader.readSysInfo();
// if(this.sysInfo!=sysInfo){
// this.sysInfo = sysInfo;
// }
// return sysInfo;
		var result = this.executeOcxFunction('readSysInfo');
		this.sysInfo = result;
		return result;
	},
	readSysInfoByParam : function(i){
		var sysInfo = this.sysInfo;
		return sysInfo.split(',')[i];
	},
	readServiceProviderId : function(){
		var serviceProviderId = this.readSysInfoByParam(2);
		return serviceProviderId;
	},
	readContractType : function(){
		var contractType = this.readSysInfoByParam(3);
		return contractType;
	},
	readContractVersion : function(){
		var contractVersion = this.readSysInfoByParam(4);
		return contractVersion;
	},
	readContractSerialNo : function(){
		var contractSerialNo = this.readSysInfoByParam(5);
		return contractSerialNo;
	},
	readContractEnableTime : function(){
		var contractEnableTime =  this.readSysInfoByParam(6);
		return contractEnableTime;
	},
	readContractExpireTime : function(){
		var contractExpireTime = this.readSysInfoByParam(7);
		return contractExpireTime;
	},
	readLoadStatus : function(){
		var loadStatus = this.readSysInfoByParam(8);
		return loadStatus;
	},
	readFullVehicleInfo : function(){
		var vehicleInfo = this.vehicleInfo;
		if(vehicleInfo==''){
			$.Taiji.showWarn('设备未打开');
		}else{
			return vehicleInfo;
		}
	},
	readVehicleInfo : function(){
//		var vehicleInfo = this.reader.readVehicleInfo();
//		if(this.vehicleInfo!=vehicleInfo){
//			this.vehicleInfo = vehicleInfo;
//		}
//		return vehicleInfo;
		return this.executeOcxFunction('readVehInfo');
	},
	readVehicleInfoByParam : function(i){
		var vehicleInfo = this.vehicleInfo;
		return vehicleInfo.split(',')[i];
	},
	readPlateNum : function(){
		var plateNum = this.readVehicleInfoByParam(2);
		return plateNum;
	},
	readPlateColor : function(){
		var plateColor = this.readVehicleInfoByParam(3);
		return plateColor;
	},
	readVehicleType : function(){
		var vehicleType = this.readVehicleInfoByParam(4);
		return vehicleType;
	},
	readUserType : function(){
		var userType = this.readVehicleInfoByParam(5);
		return userType;
	},
	readVehicleLength : function(){
		var vehicleLength = this.readVehicleInfoByParam(6);
		return vehicleLength;
	},
	readVehicleWidth : function(){
		var vehicleWidth = this.readVehicleInfoByParam(7);
		return vehicleWidth;
	},
	readVehicleHeight : function(){
		var vehicleHeight = this.readVehicleInfoByParam(8);
		return vehicleHeight;
	},
	readWheelsCount : function(){
		var wheelsCount = this.readVehicleInfoByParam(9);
		return wheelsCount;
	},
	readAxlesCount : function(){
		var axlesCount = this.readVehicleInfoByParam(10);
		return axlesCount;
	},
	readWheelBase : function(){
		var wheelBase = this.readVehicleInfoByParam(11);
		return wheelBase;
	},
	readLoadOrSeat : function(){
		var loadOrSeat = this.readVehicleInfoByParam(12);
		return loadOrSeat;
	},
	readVehicleFeature : function(){
		var vehicleFeature = this.readVehicleInfoByParam(13);
		return vehicleFeature;
	},
	readEngineNum : function(){
		var engineNum = this.readVehicleInfoByParam(14);
		return engineNum;
	},
	openObuDev : function(){
//		this.closeObuDev();
		return this.executeOcxFunction('openObu');
	},
	closeObuDev : function(){
		return this.executeOcxFunction('closeObu');
	},
	writeSystemInfo : function(systemInfo){
		var writeResponse = ifError(this.reader.JL_WriteSysInfo(systemInfo));
		return writeResponse;
	},
	writeVehicleInfo : function(vehicleInfo){
		var writeResponse = ifError(this.reader.JL_WriteVehInfo(vehicleInfo));
		return writeResponse;
	},
	writeSysAndVehInfo : function(sysInfo,vehicleInfo){
		var wirteResponse = this.reader.Write_SysAndVeh(sysInfo,vehicleInfo);
		return wirteResponse;
	},
	preActive : function(){
		var activeResponse = ifError(this.reader.PreReset());
		return activeResponse;
	},
	writeSystemInfoWithAjax : function(url,data,hideLoadingDialog,callback){
		writeInfoWithAjax(url,data,"POST",this,hideLoadingDialog,function(info,_this,callback){
			_this.writeSystemInfo(info);
			callback && callback();
		},callback);
	},
	writeVehicleInfoWithAjax : function(url,data,hideLoadingDialog,callback){
		writeInfoWithAjax(url,data,"POST",this,hideLoadingDialog,function(info,_this,callback){
			_this.writeVehicleInfo(info);
			callback && callback();
		},callback);
	},
	writeSysInfoAndVehicleInfoWithAjax : function(url,data,hideLoadingDialog,callback){
		writeInfoWithAjax(url,data,"POST",this,hideLoadingDialog,function(info,_this,callback){
			_this.writeVehicleInfo(info);
			callback && callback();
		},callback);
	},
	executeOcxFunction : function(command){
		var functionParams = this.readerCommands[command];
		var functionStr = this.generateFunctionStr(functionParams);
		$.Taiji.showWarn(functionStr);
		return eval(functionStr);
	},
	generateFunctionStr : function(functionParams){
		var functionParamsArray = functionParams.split(',');
		var length = functionParamsArray.length;
		if(length < 1){
			return '';
		}
		var functionName = functionParamsArray[0];
		var readerStr = 'this.reader.';
		var paramsStr;
		if(length == 1){
			paramsStr = '();';
		}else if(length > 1){
			paramsStr = '(';
			for (var i = 1; i < functionParamsArray.length; i++) {
				var formalParamName = functionParamsArray[i];
				paramsStr += formalParamName;
				if(i!=functionParamsArray.length-1){
					paramsStr += ',';
				}
			}
			paramsStr += ');';
		}
		return readerStr+functionName+paramsStr;
	},
	getProperty : function(info,propertyName){
		var properties = info.split(',');
		var index = parseInt(this.propertiesIndex[propertyName]);
		if(index>=0){
			return properties[index];
		}
	}
}
function writeInfoWithAjax(url,data,method,_this,hideLoadingDialog,callback,callback2){
	$.ajax({
		url : url,
		type : method,
		data : JSON.stringify(data),
		contentType : 'application/json',
		dataType : 'json',
		success : function(response){
			if(response.success){
				var info = response.response;
				console.log(response);
				console.log(response.response);
				callback && callback(info,_this,callback2);
// $.Taiji.showNote(response.message);
				hideLoading(hideLoadingDialog);
			}else{
				$.Taiji.showWarn(response.message);
				hideLoading(true);
			}
		},
		error : function(){
			$.Taiji.showWarn("请求服务器获取指令失败，请重试或联系管理员");
			hideLoading(true);
		}
	});
}
function hideLoading(hideLoadingDialog){
	if(hideLoadingDialog){
		$.Taiji.hideLoading();
	}
}
function openDev(obuReader){
     var opened = obuReader.OpenDevice();
     return opened;
}
var ifError = function(code){
	var codeStr = code.toString();
	var status = codeStr.substring(0,1);
	if(parseInt(status)!=0){
		$.Taiji.showWarn(errCode[code]);
	}else{
		return code;
	}
}

var errCode = {
	0 : "成功",
	100 : "设备打开失败",
	200 : "OBU搜索失败",
	201 : "OBU通讯超时",
	202 : "OBU应用锁定",
	203 : "OBU系统锁定",
	204 : "OBU读系统失败",
	205 : "OBU读车辆失败",
	206 : "OBU取随机数失败",
	207 : "OBU写系统失败",
	208 : "OBU写车辆失败",
	300 : "PSAM复位失败",
	301 : "PSAM选目录失败",
	302 : "PSAM分散失败",
	303 : "PSAM解密失败",
	304 : "PSAM计算MAC失败",
	500 : "输入参数错误"
};