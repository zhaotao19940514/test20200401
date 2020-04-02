<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/jsp/deviceInclude/obuDeviceHeader.jsp"%>
<script src="${rootUrl }plugins/encrypt/sha-512.js"></script>

<style type="text/css">
          .titile{
          	text-align:right;
          	font-size: 13px;
          };
          .details{
          	text-align:left;
          }
          .caption{
          		font-size: 15px;
          		font-weight: bold;
          		margin: 0px;
          }
          .essential {
				color: red;
		  }
</style>
</head>
<body>
	<script type="text/javascript">
		var obuBrand = 0;
		var obuModel = "0";
		$(function() {
			
			$.ajaxSetup ({ cache: false});
			$("#myManage").taiji({
				enableAclCheck : true,
				search:{
					 autoSearch:false
				}
			});
			//读OBU信息
			$("#readObuBtn").click(function() {
// 			 	debugger; 
				var reader = new ObuOfflineReader();
				reader.openObuDev();
// 				var serviceProviderId = reader.readServiceProviderId();
				var contractType = reader.readContractType();
				var contractVersion = reader.readContractVersion();
				var contractSerialNo = reader.readContractSerialNo();
				$("#obuId").val(contractSerialNo);		
// 				$("#serviceProviderId").val(serviceProviderId);		
				$("#contractType").val(contractType);		
				$("#contractVersion").val(contractVersion);		
				$("#contractSerialNo").val(contractSerialNo);
				reader.closeObuDev();
			});
			//OBU发行
            $("#obuIssueBtn").click(function(){
            	var reader = new ObuOfflineReader();
            	var obuId = $("#obuId").val();
            	$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
            	obuInventoryCheck(obuId,reader);
            });
            //obu库存校验
            function obuInventoryCheck(obuId,reader) {
				var url = '${rootUrl }';
				if(obuId == null || obuId == "" || isNaN(obuId)){
					$.Taiji.hideLoading();
					$.Taiji.showWarn("请先读OBU信息！");
				}else{
				    var data = {};
				    data.type = 2;
				    data.obuId = obuId;
					$.ajax({
						url:url+"app/apply/emergency/equipmentissue/inventoryVerify",
						data:JSON.stringify(data),
						type:"POST",
						contentType : 'application/json',
				        dataType : 'json',
						success:function(response){
// 							console.log(response);
// 							debugger;
							if(response.success){
				            	obuBrand = Number(response.brand);
				            	obuModel = response.model;
				            	writeSysInfo(reader);//调用写OBU系统系信息方法
			            	}else{
								$.Taiji.hideLoading();
				            	$.Taiji.showWarn(response.message+"校验失败！");
			            	}
						},
						error:function(error){
							$.Taiji.hideLoading();
							console.log(error);
							$.Taiji.showWarn("库存校验请求失败！");
						}
					});
		        }
			}
            //写OBU系统信息
            function writeSysInfo(reader) {
            	 reader.openObuDev();
                 var data = {};
                 data.oldInfo = reader.readSysInfo();
                 data.newObuId = reader.readContractSerialNo();
                 var vehicleId = '${vehicleInfo.vehicleId }';
                 var plateInfos = vehicleId.split('_');
                 if(plateInfos!=undefined && plateInfos.length == 2){
                	 data.plateNum = plateInfos[0];
                     data.plateColor = plateInfos[1];
                 }
                 var result = reader.writeSystemInfoWithAjax('${rootUrl}'+"app/ocx/obu/sysinfo",data,false,function(){
                	 var url = '${rootUrl }';
                	 var obuId = $("#obuId").val();
                	 var customerId = '${vehicleInfo.customerId }';
//                  	 var vehicleId = '${vehicleInfo.vehicleId }';
                 	 var data = {};
                 	 data.type = 1;//1-发行申请  2-发行确认
                 	 data.obuId = obuId;
                 	 data.customerId = customerId;
                 	 data.vehicleId = vehicleId;
                	 $.ajax({
 						url:url+"app/apply/emergency/equipmentissue/obuApplyAndConfirm",
 						data:JSON.stringify(data),
 						type:"POST",
 						contentType : 'application/json',
                        dataType : 'json',
 						success:function(response){
//  							debugger;
 							if(response.success){
 								writeVehInfo(response,reader);//写车辆信息
 							}else{
 								reader.closeObuDev();
 								$.Taiji.hideLoading();
 								$.Taiji.showWarn(response.message);
 							}
 						},
 						error:function(error){
 							reader.closeObuDev();
 							$.Taiji.hideLoading();
 							$.Taiji.showWarn('OBU发行申请失败！');
 						}
 					});
                 });
                 
			}
            //向OBU写车辆信息
            function writeVehInfo(response,reader){
                 var oldInfo = reader.readVehicleInfo();
                 console.log(oldInfo);
                 var data = {};
                 data.oldInfo = oldInfo;
                 data.plateNum = response.plateNum;
                 data.plateColor = response.plateColor;
                 data.vehicleType = response.vehicleType;
                 data.userType = response.userType;
                 data.vehicleLength = response.vehicleLength;
                 data.vehicleWidth = response.vehicleWidth;
                 data.vehicleHeight = response.vehicleHeight; 
                 data.wheelsCount = response.wheelsCount;
                 data.axleCount = response.axleCount;
                 data.wheelBase = response.wheelBase;
                 data.loadOrSeat = response.loadOrSeat;
                 data.vehicleFeature = response.vehicleFeature; 
                 data.engineNum = response.engineNum;
                 var url = '${rootUrl}'+"app/ocx/obu/vehicleinfo";
                 reader.writeVehicleInfoWithAjax(url,data,true,function(){
                 	reader.closeObuDev();
                 	var url = '${rootUrl }';
                 	var vehicleId = '${vehicleInfo.vehicleId }';
                 	var obuId = $("#obuId").val();
                 	var data = {};
                 	data.type = 2;//1-发行申请  2-发行确认
                 	data.obuId = obuId;
                 	data.brand = obuBrand;
                 	data.model = obuModel;
                 	data.vehicleId = vehicleId;
                 	$.ajax({
 						url:url+"app/apply/emergency/equipmentissue/obuApplyAndConfirm",
 						data:JSON.stringify(data),
                        type:"POST",
                        contentType : 'application/json',
                        dataType : 'json',
 						success:function(response){
//  							debugger;
 							if(response.success){
 								$.Taiji.hideLoading();
 								$.Taiji.showNote("OBU发行成功！");
 								$("#closeBtn").click();
 							}else{
 								$.Taiji.hideLoading();
 								$.Taiji.showWarn(response.message);
 							}
 						},
 						error:function(error){
 							$.Taiji.hideLoading();
 							$.Taiji.showWarn('OBU发行确认失败！');
 						}
 					});
                 });
            }

		});
	</script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">发OBU</h4>
</div>
<div class="modal-body">
	<div class="form-group">
		<form:form modelAttribute="customerInfo" cssClass="form-horizontal" action="" method="post">
			<p class="caption">客户信息</p>
			<table border="1">
				<tr style="display: none;">
					<td colspan="6"><form:input cssStyle="width:180px" path="customerId" id="customerId" class="form-control" disabled="true" /></td>
				</tr>
				<tr>
					<td class="titile">客户名称：</td><td class="details"><form:input cssStyle="width:180px" path="customerName" class="form-control" disabled="true" /></td>
					<td class="titile" width="91px">证件类型：</td><td class="details">
						<form:select path="customerIdType" disabled="true" cssClass="form-control m-r-5"
												style="width:175px" >
												<form:options items="${customerIDTypes}" itemLabel="value"
													itemValue="typeCode"/>
											</form:select></td>
					<td class="titile" width="130px">证件号码：</td><td class="details"><form:input cssStyle="width:180px" path="customerIdNum" class="form-control" disabled="true" /></td>
				</tr>
			</table>
   		</form:form>
	</div>
	<div class="form-group">
		<form:form modelAttribute="vehicleInfo" cssClass="form-horizontal" action="" method="post">
			<p class="caption">车辆信息</p>
			<table border="1">
				<tr>
					<td class="titile">车牌号码：</td><td class="details"><form:input cssStyle="width:180px" disabled="true" path="vehiclePlate" class="form-control"  placeholder="必填"/></td>
					<td class="titile">车牌颜色：</td><td class="details">
										<form:select path="vehiclePlateColor" disabled="true"  cssClass="form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:option value="">全部</form:option>
											<form:options items="${vehiclePlateColorTypes}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
					<td class="titile">使用性质：</td><td class="details">
										<form:select path="useCharacter" disabled="true" cssClass="form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:option value="">全部</form:option>
											<form:options items="${vehicleUseCharacters}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
				</tr>
				<tr>
					<td class="titile">客货类别：</td><td class="details">
										<form:select path="vehicleType" disabled="true" cssClass="form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:options items="${vehicleTypeSimples}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
					<td class="titile" ><c:choose>
											<c:when test="${vehicleInfo.vehicleType eq 1 }"><span>载重（kg）：</span></c:when>
											<c:otherwise><span>座位数：</span></c:otherwise>
										</c:choose></td>
					<td class="details" ><c:choose>
											<c:when test="${vehicleInfo.vehicleType eq 1 }"><span><form:input path="permittedWeight" disabled="true" cssStyle="width:180px" class="form-control" /></span></c:when>
											<c:otherwise><span><form:input path="approvedCount" disabled="true" cssStyle="width:180px" class="form-control" /></span></c:otherwise>
										</c:choose></td>
					<td class="titile">车型：</td><td class="details">
										<form:select path="type" disabled="true" cssClass="form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:options items="${vehicleTypes}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
				</tr>
				<tr>
					<td class="titile">品牌型号：</td><td class="details"><form:input path="vehicleModel" disabled="true" cssStyle="width:180px" class="form-control"  placeholder="必填"/></td>
					<td class="titile">车辆识别代码：</td><td class="details"><form:input path="VIN" disabled="true" cssStyle="width:180px" class="form-control"  placeholder="必填"/></td>
					<td class="titile">发动机号：</td><td class="details"><form:input path="engineNum" disabled="true" cssStyle="width:180px" class="form-control"  placeholder="必填"/></td>
				</tr>
				<tr>
					<td class="titile">车轮数：</td><td class="details"><form:input path="wheelCount" disabled="true" cssStyle="width:180px" class="form-control"  placeholder="必填"/></td>
					<td class="titile">车轴数：</td><td class="details"><form:input path="axleCount" disabled="true" cssStyle="width:180px" class="form-control"  placeholder="必填"/></td>
					<td class="titile">外廓尺寸（长*宽*高）</td><td class="details"><form:input path="outsideDimensions" disabled="true" cssStyle="width:180px" class="form-control"  placeholder="必填"/></td>
				</tr>
			</table>
   		</form:form>
	</div>
	<div class="form-group">
		<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="" method="post">
			<p class="caption">OBU信息</p>
				<table border="1">
					<tr>
						<td class="titile"><span class="essential">*</span>OBU序列号：</td><td class="details" >
							<span style="float: left;"><form:input path="" id="obuId" readonly="true" cssStyle="width:150px" cssClass="form-control" /></span>
							<button class="btn btn-default btn-success" type="button" id="readObuBtn">读OBU</button></td>
<%-- 						<td class="titile">服务提供商编号：</td><td class="details"><form:input path="" id="serviceProviderId" readonly="true" cssStyle="width:180px" class="form-control" /></td> --%>
						<td class="titile"><span class="essential">*</span>协议类型：</td><td class="details"><form:input path="" id="contractType" readonly="true" cssStyle="width:180px" class="form-control" /></td>
						<td class="titile"><span class="essential">*</span>合同版本：</td><td class="details"><form:input path="" id="contractVersion" readonly="true" cssStyle="width:180px" class="form-control" /></td>
					</tr>
					<tr>
						<td class="titile"><span class="essential">*</span>合同序列号：</td><td class="details"><form:input path="" id="contractSerialNo" readonly="true" cssStyle="width:220px" class="form-control" /></td>
						<td class="titile"><span class="essential">*</span>生效日期：</td><td class="details"><form:input path="enableTime" readonly="true" cssStyle="width:180px" class="form-control" /></td>
						<td class="titile"><span class="essential">*</span>失效日期：</td><td class="details"><form:input path="expireTime" readonly="true" cssStyle="width:180px" class="form-control" /></td>
					</tr>
				</table>
	   </form:form> 
	</div>
</div>
<div class="modal-footer">
	<a href="#" id="closeBtn" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" id="obuIssueBtn" class="btn btn-sm btn-success" >发OBU</a>
</div>

</body>
</html>