<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/jsp/deviceInclude/obuDeviceHeader.jsp"%>
	<script>
	$(function() {
		$('.modal-body').taiji();
		vehCheck = 0;
		//var flag = 0;
		$("#submit").click(function(){ 
			var vehPlate = $("#vehPlate").val();
			var vehColor = $("#vehColor").val();
			if(null==vehPlate||""==vehPlate||vehPlate=='undefined'){
				$.Taiji.showWarn("请填写车牌号");
				return;
			}
			if(null==vehColor||""==vehColor||vehColor=='undefined'){
				$.Taiji.showWarn("请选择车牌颜色");
				return;
			}
			if(vehCheck==0){
				$.Taiji.showWarn("请先进行车牌检测");
				return;
			}
			var reader = new ObuOfflineReader();
			reader.openObuDev();
			var obuId = reader.readContractSerialNo();
			reader.closeObuDev();
			if(null==obuId||""==obuId||obuId=='undefined'){
				 $.Taiji.showWarn("请将OBU放在读签器上");
				return;
			}
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			formSubmit('${vehicleId }','${oldObuId}');
			
		});
		$("#vehCheckBtn").click(function(){
			var vehPlate = $("#vehPlate").val();
			var vehColor = $("#vehColor").val();
			if(null==vehPlate||""==vehPlate||vehPlate=='undefined'){
				$.Taiji.showWarn("请填写车牌号");
				return;
			}
			if(null==vehColor||""==vehColor||vehColor=='undefined'){
				$.Taiji.showWarn("请选择车牌颜色");
				return;
			}
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			var data={};
			data.vehPlate = vehPlate;
			data.vehColor = vehColor;
			 $.ajax({
			        url:rootUrl+"app/customerservice/obu/rewrite/VehiclePlateOnlyCheck",
			        data:JSON.stringify(data),
			        type:"POST",
			        contentType : 'application/json',
			        dataType : 'json',
			        success:function(response){
			            if(response.code=="SUCCESS"){
			            	$.Taiji.showNote("车牌校验成功");
			            	$.Taiji.hideLoading();
			            	vehCheck =1;
			            }else{
			            	$.Taiji.hideLoading();
			            	$.Taiji.showWarn(response.message);
			            	vehCheck = 0;
			            }    
			        },
			        error:function(error){
			        	$.Taiji.hideLoading();
			        	$.Taiji.showWarn(error.message);
			        	vehCheck = 0;
			        }
			    });
		});
		function formSubmit(oldVehicleId,obuId){
			var vehPlate = $("#vehPlate").val();
			var vehColor = $("#vehColor").val();
			console.log("oldVehicleId:"+oldVehicleId);
			console.log("obuId:"+obuId);
			console.log("vehColor:"+vehColor);
			var newVehicleId = vehPlate+"_"+vehColor;
			/* if(newVehicleId==oldVehicleId){
				$.Taiji.showWarn("车牌不能一致");
				$.Taiji.hideLoading();
				return;
			} */
			var data={};
			data.vehPlate = vehPlate;
			data.vehColor = vehColor;
			data.vehicleId = oldVehicleId;
			data.obuId = obuId;
			 $.ajax({
			        url:rootUrl+"app/customerservice/obu/unhang/edit",
			        data:JSON.stringify(data),
			        type:"POST",
			        contentType : 'application/json',
			        dataType : 'json',
			        success:function(response){
			        	if(response.status==1){
			        		$("#clickType").val(1);
			        		writeSystemInfo(vehPlate,vehColor);
			        	}else{
			        		$.Taiji.hideLoading();
			        		$.Taiji.showWarn(response.message);
			        	}
			        	
			        },
			        error:function(error){
			        	$.Taiji.hideLoading();
			        	$.Taiji.showWarn(error.message);
			        }
			    });
		}
		
	});
	</script>
	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">车牌号重写信息:</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="veQueryModel" id="myForm" name="myForm" cssClass="form-horizontal" method="post">
	<table class="table table-bordered table-striped">
		<%-- <tr>
			<td>
				<label for='vehicleId' class="control-label">
				<form:input path="vehicleId" size="50" cssClass="form-control"
				required="required" placeholder="${vehicleId }" readonly="true" /></label>
			</td>
			<td></td>
		</tr> --%>
		<tr>
			<td>
				<label for='vehPlate' class="control-label">
				<form:input path="vehPlate" size="50" cssClass="form-control"
				required="required" placeholder="输入新车牌号" /></label>
			</td>
			<td>
				<button class=" btn btn-md btn-default btn btn-success btn-small" id="vehCheckBtn"  type="button"> <i class="fa  fa-credit-card  m-r-10  "></i>车牌检测</button>
			</td>
		</tr>
		 <tr>  
			<td>
				<form:select path="vehColor" size="50px" class="selectpicker" title="请选择车牌颜色" data-style="btn-warning" >
					<form:option value="" selected="selected">请选择车牌颜色</form:option>
					<form:options items="${vehiclePlateColorType}" itemLabel="value" itemValue="typeCode"/>
				</form:select> 
			</td>
			<td></td>
		</tr> 
		</table>
		
	</form:form>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" id="closeBtn" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">确定</a>
</div>

</body>
</html>