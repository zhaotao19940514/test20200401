//"use strict";
var ObuOfflineReader = function() {
	ObuOfflineReader.fn.init.prototype = ObuOfflineReader.fn;
	this.init();
};
ObuOfflineReader.fn = ObuOfflineReader.prototype = {
	constructor : ObuOfflineReader,
	init : function(){
		// chrome
//		this.objStr = '<object id="ObuOfflineApi" type="application/x-itst-activex" width="0" height="0" clsid="{BE1F5CF6-655F-48FC-9734-B3A1940D4B7B}" progid="${rootUrl}obuReaderOcx/watch/OBUReadOCX.ocx"></object>';
		// ie
		this.objStr = '<object id="ObuOfflineApi" classid="clsid:{BE1F5CF6-655F-48FC-9734-B3A1940D4B7B}" codebase="${rootUrl}obuReaderOcx/watch/OBUReadOCX.ocx" style="display:none;"></object>';
//		this.sysInfo = '';
//		this.vehicleInfo = '';
//		this.writeSysInfo = '';
		if($("#ObuOfflineApi").length > 0){
			 $("#ObuOfflineApi").remove();
		}
		$("body").prepend(this.objStr);
		this.reader = $("#ObuOfflineApi").get(0);
		this.closeOnly();
	},
	readFullSysInfo : function(){
		var sysInfo = this.reader.ReadSysInfo();
		if(this.sysInfo!=sysInfo){
			this.sysInfo = sysInfo;
		}
		return sysInfo;
	},
	readSysInfo : function(){
		var sysInfo = this.reader.ReadSysInfo();
		if(this.sysInfo!=sysInfo){
			this.sysInfo = sysInfo;
		}
		return sysInfo;
	},
	readSysInfoByParam : function(i){
//		if(this.sysInfo == null || this.sysInfo == '' || this.sysInfo == undefined){
//			this.sysInfo = this.readSysInfo();
//		}
		var sysInfo = this.sysInfo;
		return sysInfo.split(',')[i];
	},
//	readServiceProviderId : function(){
//		var serviceProviderId = this.readSysInfoByParam(0);
//		return serviceProviderId;
//	},
	readContractType : function(){
		var contractType = this.readSysInfoByParam(1);
		return contractType;
	},
	readContractVersion : function(){
		var contractVersion = this.readSysInfoByParam(2);
		return contractVersion;
	},
	readContractSerialNo : function(){
		var contractSerialNo = this.readSysInfoByParam(3);
		return contractSerialNo;
	},
	readContractEnableTime : function(){
		var contractEnableTime =  this.readSysInfoByParam(4);
		return contractEnableTime;
	},
	readContractExpireTime : function(){
		var contractExpireTime = this.readSysInfoByParam(5);
		return contractExpireTime;
	},
	//拆卸状态 watch读不到。。
//	readLoadStatus : function(){
//		var loadStatus = this.readSysInfoByParam(8);
//		return loadStatus;
//	},
	readFullVehicleInfo : function(){
		var vehicleInfo = this.reader.ReadVehicleInfo();
//		return vehicleInfo;
		if(this.vehicleInfo!=vehicleInfo){
			this.vehicleInfo = vehicleInfo;
		}
		return vehicleInfo;
	},
	readVehicleInfo : function(){
		var vehicleInfo = this.reader.ReadVehicleInfo();
//		return vehicleInfo;
		if(this.vehicleInfo!=vehicleInfo){
			this.vehicleInfo = vehicleInfo;
		}
		return vehicleInfo;
	},
	readVehicleInfoByParam : function(i){
//		if(this.vehicleInfo == null || this.vehicleInfo == '' || this.vehicleInfo == undefined){
//			this.vehicleInfo = this.readVehicleInfo();
//		}
		var vehicleInfo = this.vehicleInfo;
		return vehicleInfo.split(',')[i];
	},
	readPlateNum : function(){
		var plateNum = this.readVehicleInfoByParam(0);
		return plateNum;
	},
	readPlateColor : function(){
		var plateColor = this.readVehicleInfoByParam(1);
		return plateColor;
	},
	readVehicleType : function(){
		var vehicleType = this.readVehicleInfoByParam(2);
		return vehicleType;
	},
	readUserType : function(){
		var userType = this.readVehicleInfoByParam(3);
		return userType;
	},
	readVehicleLength : function(){
		var vehicleLength = this.readVehicleInfoByParam(4);
		return vehicleLength;
	},
	readVehicleWidth : function(){
		var vehicleWidth = this.readVehicleInfoByParam(5);
		return vehicleWidth;
	},
	readVehicleHeight : function(){
		var vehicleHeight = this.readVehicleInfoByParam(6);
		return vehicleHeight;
	},
	readWheelsCount : function(){
		var wheelsCount = this.readVehicleInfoByParam(7);
		return wheelsCount;
	},
	readAxlesCount : function(){
		var axlesCount = this.readVehicleInfoByParam(8);
		return axlesCount;
	},
	readWheelBase : function(){
		var wheelBase = this.readVehicleInfoByParam(9);
		return wheelBase;
	},
	readLoadOrSeat : function(){
		var loadOrSeat = this.readVehicleInfoByParam(10);
		return loadOrSeat;
	},
	readVehicleFeature : function(){
		var vehicleFeature = this.readVehicleInfoByParam(11);
		return vehicleFeature;
	},
	readEngineNum : function(){
		var engineNum = this.readVehicleInfoByParam(12);
		return engineNum;
	},
	openObuDev : function(){
		this.closeOnly();
		// 添加object
		var result = this.reader.OpenDevice();
		if(result==0){
//			this.reader.ReadSysAndVehAndIC();
			this.readSysInfo();
			this.readVehicleInfo();
		}
		return result;
	},
	closeOnly : function(){
		var closeFlag = this.reader.CloseDevice();
		return closeFlag;
	},
	closeObuDev : function(){
		var closeFlag;
		if(this.reader!=undefined){
			 closeFlag = this.reader.CloseDevice();
		}
		// 去除object
		$("#ObuOfflineApi").remove();
		return closeFlag;
	},
	writeSystemInfo : function(systemInfo){
//		var writeResponse = ifError(this.reader.writeSysInfo(systemInfo));
//		return writeResponse;
		var split = systemInfo.split(',');
		if(split.length != 4){
			$.Taiji.showWarn('写系统信息指令长度错误');
			return ;
		}
		this.writeSysInfo = systemInfo;
		return 0;
	},
	writeVehicleInfo : function(vehicleInfo){
		if(this.writeSysInfo == '' || this.writeSysInfo == undefined || this.writeSysInfo == null){
			$.Taiji.showWarn('请先写系统信息！');
			return ;
		}
		// 为watchdata设备填充sysinfo中的车牌号/车牌颜色。。
		var sysInfo = fillVehicleInfoForSysInfo(this.writeSysInfo,vehicleInfo);
		var writeResponse = this.reader.Write_SysAndVeh(sysInfo,vehicleInfo);
		return writeResponse;
	},
	preActive : function(){
		var activeResponse = ifError(this.reader.PreReset());
		return activeResponse;
	},
	writeSystemInfoWithAjax : function(url,data,hideLoadingDialog,callback){
		writeInfoWithAjax(url,data,"POST",this,hideLoadingDialog,function(info,_this,callback){
			console.log(info);
			var result = _this.writeSystemInfo(info);
			callback && callback();
		},callback);
	},
	writeVehicleInfoWithAjax : function(url,data,hideLoadingDialog,callback){
		writeInfoWithAjax(url,data,"POST",this,hideLoadingDialog,function(info,_this,callback){
			var writeResult = _this.writeVehicleInfo(info);
			_this.writeSysInfo = '';
			callback && callback();
		},callback);
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
			console.log(response);
			if(response.success){
				var info = response.response;
				console.log(response);
				console.log(response.response);
				callback && callback(info,_this,callback2);
//				$.Taiji.showNote(response.message);
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
var fillVehicleInfoForSysInfo = function(sysInfo,vehicleInfo){
	var sysInfoSplit = sysInfo.split(',');
	var vehicleInfoSplit = vehicleInfo.split(',');
	sysInfoSplit[2] = vehicleInfoSplit[0];
	sysInfoSplit[3] = vehicleInfoSplit[1];
	var result = '';
	for (var i = 0; i < sysInfoSplit.length; i++) {
		result += sysInfoSplit[i];
		if(i!=sysInfoSplit.length-1){
			result += ',';
		}
	}	
	return result;
};
var ifError = function(code){
	console.log('code: '+code);
	var codeStr = code.toString();
	var status = codeStr.substring(0,1);
	if(parseInt(status)!=0){
		$.Taiji.showWarn('操作失败，错误码未定义');
	}else{
		return code;
	}
}
// 暂无watchObu的错误码定义
var errCode = {
	0 : "成功",
};