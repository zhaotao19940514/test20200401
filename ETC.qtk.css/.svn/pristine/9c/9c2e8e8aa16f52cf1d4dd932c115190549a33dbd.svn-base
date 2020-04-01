var MisPosClient = function() {
	// chrome
//	this.objStr = '<object id="ICBCMisPosClient" type="application/x-itst-activex" width="0" height="0" clsid="{7e50e44e-a583-4ff1-80de-f69438b22b63}" progid="${rootUrl}posOcx/CardMis.ocx#version=1,0,0,1"></object>';
	// ie
	this.objStr = '<object id="ICBCMisPosClient"  classid="clsid:{7e50e44e-a583-4ff1-80de-f69438b22b63}" codebase="${rootUrl}posOcx/CardMis.ocx#version=1,0,0,2" style="display:none;"></object>';
	this.trade = function(command){
		if($("#ICBCMisPosClient").length > 0){
			 $("#ICBCMisPosClient").remove();
		}
		$("body").prepend(this.objStr);
		this.pos = $("#ICBCMisPosClient").get(0);
		var posResponse = this.pos.cardtrans(command);
		$("#ICBCMisPosClient").remove();
		return posResponse;
	};
	this.testSignOn = function(){
		if($("#ICBCMisPosClient").length > 0){
			 $("#ICBCMisPosClient").remove();
		}
		$("body").prepend(this.objStr);
		this.pos = $("#ICBCMisPosClient").get(0);
		var signOn = "09,,,,,,,,,,,,,,,66666,99999";
		this.trade(signOn);
		$("#ICBCMisPosClient").remove();
	};
	this.validPosResponse = function(posRespnose){
		if(posRespnose == null || posRespnose == '' || posRespnose == undefined){
			$.Taiji.showWarn('pos指令执行结果为空！');
			return false;
		}
		var split = posRespnose.split(',');
		if(split.length != 17 && split.length != 21){
			$.Taiji.showWarn('pos指令执行结果格式不正确!');
			return false;
		}
		var executeResult = split[10];
		if(executeResult!='00'){
			$.Taiji.showWarn('pos指令执行失败：'+split[11]);
			return false;
		}
		return true;
	};
	this.getResponseBankCardNo = function(posRespnose){
		if(this.validPosResponse(posRespnose)){
			var split = posRespnose.split(',');
			return split[1];
		}
	};
	this.getResponseReferNo = function(posRespnose){
		if(this.validPosResponse(posRespnose)){
			var split = posRespnose.split(',');
			return split[7];
		}
	};
};
