var ObuOfflineReader = function() {
	ObuOfflineReader.fn.init.prototype = ObuOfflineReader.fn;
	this.init();
	this.sysInfo = '';
	this.vehicleInfo = '';
};
ObuOfflineReader.fn = ObuOfflineReader.prototype = {
	constructor : ObuOfflineReader,
	init : function(){
		// chrome
		this.objStr = '<object id="ObuOfflineApi" type="application/x-itst-activex" width="0" height="0" clsid="{C941447F-91E6-4EE0-87B8-D62702FD5651}" progid="${rootUrl}obuReaderOcx/ObuApiOcx.ocx"></object>';
		// ie
//		this.objStr = '<object id="ObuOfflineApi" classid="clsid:{C941447F-91E6-4EE0-87B8-D62702FD5651}" codebase="${rootUrl}obuReaderOcx/ObuApiOcx.ocx" style="display:none;"></object>';
		
		if($("#ObuOfflineApi").length > 0){
			 $("#ObuOfflineApi").remove();
		}
		$("body").prepend(this.objStr);
		this.reader = $("#ObuOfflineApi").get(0);
	},
	readFullSysInfo : function(){
		var sysInfo = this.reader.JL_ReadSysInfo();
		if(sysInfo==''){
			$.Taiji.showWarn('设备未打开');
		}else{
			if(this.sysInfo!=sysInfo){
				this.sysInfo = sysInfo;
			}
			return sysInfo;
		}
	},
	readSysInfo : function(){
		var sysInfo = this.reader.JL_ReadSysInfo();
		if(this.sysInfo!=sysInfo){
			this.sysInfo = sysInfo;
		}
		return sysInfo;
	},
	readSysInfoByParam : function(i){
		var sysInfo = this.sysInfo;
//		var sysInfo = this.reader.JL_ReadSysInfo();
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
		var vehicleInfo = this.reader.JL_ReadVehInfo();
		if(vehicleInfo==''){
			$.Taiji.showWarn('设备未打开');
		}else{
			if(this.vehicleInfo!=vehicleInfo){
				this.vehicleInfo = vehicleInfo;
			}
			return vehicleInfo;
		}
	},
	readVehicleInfo : function(){
		var vehicleInfo = this.reader.JL_ReadVehInfo();
		if(this.vehicleInfo!=vehicleInfo){
			this.vehicleInfo = vehicleInfo;
		}
		return vehicleInfo;
	},
	readVehicleInfoByParam : function(i){
		var vehicleInfo = this.vehicleInfo;
//		var vehicleInfo = this.reader.JL_ReadVehInfo();
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
		var result = ifError(openDev(this.reader));
		if(result==0){
			this.readSysInfo();
			this.readVehicleInfo();
		}
		return result;
	},
	closeOnly : function(){
		var closeFlag = ifError(this.reader.JL_CloseDev());
		return closeFlag;
	},
	closeObuDev : function(){
		var closeFlag = ifError(this.reader.JL_CloseDev());
		this.vehicleInfo = '';
		this.sysInfo = '';
		$("#ObuOfflineApi").remove();
		return closeFlag;
	},
	writeSystemInfo : function(systemInfo){
		// 车牌颜色需要处理成四位 如颜色为1 则处理为0001
		systemInfo = dealPlateColor(systemInfo);
		console.log(systemInfo);
		var writeResponse = ifError(this.reader.JL_WriteSysInfo(systemInfo));
		return writeResponse;
	},
	writeVehicleInfo : function(vehicleInfo){
		var writeResponse = ifError(this.reader.JL_WriteVehInfo(vehicleInfo));
		console.log(writeResponse);
		return writeResponse;
	},
	preActive : function(){
		var activeResponse = ifError(this.reader.JL_PreActivated());
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
	}
}

function dealPlateColor(systemInfo){
	if(systemInfo==null || systemInfo==undefined || systemInfo==""){
		alert("未成功获取写入系统信息内容，请联系管理员");
		return ;
	}
	var split = systemInfo.split(",");
	if(split.length<9){
		alert("写入系统信息内容长度错误(<9)，请联系管理员");
		return ;
	}
	var plateColor = split[8];
	if(plateColor.length<4){
		for (var i = 0; i < 4; i++) {
			if(plateColor.length<4){
				plateColor = "0"+plateColor;
			}
		}
	}
	split[8] = plateColor;
	var result = "";
	for (var i = 0; i < split.length; i++) {
		result += split[i];
		if(i!=split.length-1){
			result += ","; 
		}
	}
	return result;
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
function openDev(obuReader){
	var comms = "1,2,3,4,5,6,7,8,9,10";
//	var comms = "9";
    var commports = comms.split(',');
    var openInfo = "通信接口类型,1,";
    var opened = 100;
    for (var i = 0; i < commports.length; i++) {
        var openCommand = openInfo + commports[i];
        opened = obuReader.JL_OpenDev(openCommand);
        console.log(commports[i]);
        console.log(opened);
        if(opened==0){
        	console.log(commports[i]);
            break;
        }
    }
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
	500 : "输入参数错误",
	undefined : "obu设备读写发生未知错误，请联系管理员"
};