<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
</style>
</head>
	<script type="text/javascript">
		$(function() {
			var oldInfo = null;
			$("#myManage").taiji({
				enableAclCheck : true,
				search:{
					 autoSearch:false
				}
			});
			//读OBU信息
			$("#readObuBtn").click(function() {
				var reader = new ObuOfflineReader();
				debugger;
				reader.openObuDev();
				var oldInfo = reader.readFullSysInfo();
				var contractType = reader.readContractType();
				var contractVersion = reader.readContractVersion();
				var contractSerialNo = reader.readContractSerialNo();
				var enableTime = reader.readContractEnableTime();
				var expireTime = reader.readContractExpireTime();
				$("#oldInfo").val(oldInfo);
				$("#obuId").val(contractSerialNo);		
				$("#contractType").val(contractType);		
				$("#contractVersion").val(contractVersion);		
				$("#contractSerialNo").val(contractSerialNo);
				$("#enableTime").val(enableTime);
				$("#expireTime").val(expireTime);
				reader.closeObuDev();
			});
			
            $("#obuIssueBtn").click(function(){
            	var reader = new ObuOfflineReader();
            	reader.openObuDev();
            	$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
            	//writeSysInfo(reader);
            	obuInfoChange(reader);
            });
		})
			 function obuInfoChange(reader){
            	var expireTime = reader.readContractExpireTime();
            	var obuId = reader.readContractSerialNo();
            	var vehiclePlate = $("#vehiclePlate").val();
            	var vehColor = $("#vehiclePlateColor").val();
            	var vehicleId ='${vehicleInfo.vehicleId}'; //vehiclePlate+"_"+vehColor;
            	var data = {};
            	data.obuId = obuId;
            	data.expireTime = expireTime;
            	data.vehicleId = vehicleId;
            	
            	$.ajax({
        	        url : rootUrl+"app/customerservice/obu/transfer/infochange",
        	        type : "POST",
        	        dataType : "json",
        	        data:JSON.stringify(data),
        	        contentType: "application/json", 	 	 	
        	        async:true,
        	        success : function(responseText) {
        	        	
        	        	if(responseText.status==1){
        	        		var oBUInfo=responseText.oBUInfo;
        	        		var dataObj={};
        	        		dataObj.plateNum = responseText.plateNum;
        	        		dataObj.plateColor = responseText.plateColor;
        	        		dataObj.vehicleType = responseText.vehicleType;
        	        		dataObj.userType = responseText.userType;
        	        		dataObj.vehicleLength = responseText.vehicleLength;
        	        		dataObj.vehicleWidth = responseText.vehicleWidth;
        	        		dataObj.vehicleHeight = responseText.vehicleHeight; 
        	        		dataObj.wheelsCount = responseText.wheelsCount;
        	        		dataObj.axleCount = responseText.axleCount;
        	        		dataObj.wheelBase = responseText.wheelBase;
        	        		dataObj.loadOrSeat = responseText.loadOrSeat;
        	        		dataObj.vehicleFeature = responseText.vehicleFeature; 
        	        		dataObj.engineNum = responseText.engineNum;
        	        		
        	        		writeSysInfo(oBUInfo,dataObj,reader);
        	        	}else{
        	        		 $.Taiji.hideLoading();
        	        		 $.Taiji.showWarn(responseText.message);
        	        	}
        	        },
        			error:function(responseText){
        				
        				$.Taiji.hideLoading();
        				$.Taiji.showWarn(responseText.message);
        			}
        	    });
            }
          //写OBU系统信息
            function writeSysInfo(oBUInfo,dataObj,reader) {
            	var obuId = $("#obuId").val();
//             	reader.openObuDev();
            	var checkObuId = reader.readContractSerialNo();
//             	reader.closeObuDev();
            	if(obuId!=checkObuId){
            		$.Taiji.hideLoading();
					$.Taiji.showWarn("OBU读取前后不一致,请重新读取OBU");            		
            		return;
            	}
//             	 reader.openObuDev();
                 var data = {};
                 var oBUInfo = oBUInfo;
                 data.oldInfo=$("#oldInfo").val();
                 data.newObuId = oBUInfo.ObuId;
                 data.plateNum = oBUInfo.vehicle.vehiclePlate;
                 data.plateColor =oBUInfo.vehicle.vehiclePlateColor;
                 data.newObuId = reader.readContractSerialNo();
                 var result = reader.writeSystemInfoWithAjax('${rootUrl}'+"app/ocx/obu/sysinfo",data,false,function(){
                	 writeVehInfo(dataObj,reader)
                 });
                 
			}
            //向OBU写车辆信息
            function writeVehInfo(response,reader){
                 var oldInfo = $("#oldInfo").val();
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
                 	$.Taiji.showNote("过户成功");
                 	$.Taiji.hideLoading();
                 	$("#listForm").submit();
                 	$("#closeBtn").click();
           		 })
		}
	</script>
<body>	
<script type="text/javascript">
</script>
<input type="hidden" id="oldInfo" value='0'/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">OBU过户信息</h4>
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
						<td class="titile">OBU序列号：</td><td class="details" >
							<span style="float: left;"><form:input path="" id="obuId" readonly="true" cssStyle="width:150px" cssClass="form-control" /></span>
							<button class="btn btn-default btn-success" type="button" id="readObuBtn">读OBU</button></td>
						<td class="titile">协议类型：</td><td class="details"><form:input path="" id="contractType" readonly="true" cssStyle="width:180px" class="form-control" /></td>
					</tr>
					<tr>
						<td class="titile">合同版本：</td><td class="details"><form:input path="" id="contractVersion" readonly="true" cssStyle="width:220px" class="form-control" /></td>
						<td class="titile">合同序列号：</td><td class="details"><form:input path="" id="contractSerialNo" readonly="true" cssStyle="width:180px" class="form-control" /></td>
					</tr>
					<tr>
						<td class="titile">生效日期：</td><td class="details"><form:input path="enableTime" readonly="true" cssStyle="width:220px" class="form-control" /></td>
						<td class="titile">失效日期：</td><td class="details"><form:input path="expireTime" readonly="true" cssStyle="width:180px" class="form-control" /></td>
<!-- 						<td class="titile" colspan="2"></td> -->
					</tr>
				</table>
	   </form:form> 
	</div>
</div>
<div class="modal-footer">
	<a href="#" id="closeBtn" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" id="obuIssueBtn" class="btn btn-sm btn-success" >确定</a>
</div>

</body>
</html>