<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" ></script>
		<script src="${rootUrl }myjs/vehicle/vehicleType.js" ></script>
		<link href="${rootUrl }myjs/vehicle/vehicleInfo.css" rel="stylesheet" />
		<script type="text/javascript">
		$(function(){
			$.ajaxSetup ({ cache: false});
			$("#myManageDelete").taiji({
				enableAclCheck:true,
				search:{
					 autoSearch:true
				}
			});
			$("#submit").click(function(){
				//$("#myForm").trigger("submit");
				$(".disabledRemove").removeAttr("disabled");
				$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"remove"}]);
				$(".disabledAdd").attr("disabled", true);
			});
			/* showRemedy();
			function showRemedy() {
				var type = $("#vehicleType").val();
				if(type==0){
					$(".portativePower").hide();
					$(".carryPassenger").show();
					$("#refrigeratedTrucks").hide();
				}
				if(type==1){
					$(".carryPassenger").hide();
					$(".portativePower").show();
					$("#refrigeratedTrucks").show();
				}
			} */

		});
		
		</script>
		
	</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">删除车辆信息</h4>
</div>
<div id="myManageDelete" class="panel panel-inverse">
<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/apply/emergency/vehiclemanagement/del" method="post">
		<div class="form-group">
			<p class="caption">车辆信息</p>
			
			<table border="1">
				<tr style="display: none;">
					<td><form:input cssStyle="width:180px" path="customerId" class="form-control"  placeholder="必填"/></td>
					<td ><form:input cssStyle="width:180px" path="outsideDimensions" class="form-control" placeholder="必填"/></td>
					<td colspan="4"><form:input cssStyle="width:180px" path="emergencyFlag" class="form-control"  placeholder="必填"/></td>
				</tr>
				<tr>
					<td class="titile"><span class="essential">*</span>车牌号码：</td><td class="details"><form:input cssStyle="width:180px" path="vehiclePlate" id="vehiclePlate" readonly="true" class="dataChange form-control" maxlength="9" placeholder="必填"/></td>
					<td class="titile"><span class="essential">*</span>车牌颜色：</td><td class="details">
										<form:select path="vehiclePlateColor" id="vehiclePlateColor" disabled="true" cssClass="disabledRemove disabledAdd form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:option value="">--请选择--</form:option>
											<form:options items="${vehiclePlateColorTypes}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
					<td class="titile">使用性质：</td><td class="details">
										<form:select path="useCharacter" disabled="true" cssClass="disabledRemove disabledAdd form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:option value="">--请选择--</form:option>
											<form:options items="${vehicleUseCharacters}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
				</tr>
				<tr>
					<td class="titile"><span class="essential">*</span>客货类别：</td><td class="details">
										<form:select path="vehicleType" id="vehicleType" disabled="true" cssClass="disabledRemove disabledAdd form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:option value="">--请选择--</form:option>
											<form:options items="${vehicleTypeSimples}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
					<td class="titile"><span class="essentialCar">*</span>核定座位数：</td>
					<td class="details"><form:input id="approvedCount" path="approvedCount" readonly="true" cssStyle="width:180px" class="form-control typeParameter" maxlength="3" placeholder="客车必填"/></td>
					<td class="titile">核定载重(kg)：</td>
					<td class="details"><form:input id="permittedWeight" path="permittedWeight" readonly="true" cssStyle="width:180px" class="form-control" maxlength="6" placeholder=""/></td>
				</tr>
				<tr>
					<td class="titile"><span class="essential">*</span>车型：</td><td class="details">
										<form:select path="type" id="type" disabled="true" cssClass="disabledRemove disabledAdd form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:option value="">--请选择--</form:option>
											<form:options items="${vehicleTypes}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
					<td class="titile" >是否为冷藏运输：</td>
					<td class="details" >
							<form:select path="refrigeratedTrucks" disabled="true" cssClass="form-control m-r-5 disabledRemove disabledAdd" data-style="btn-white" data-width="120px">
								<form:option value="">--请选择--</form:option>
								<form:options items="${carType}" itemLabel="value" itemValue="code"/>
							</form:select>
					</td>
				</tr>
				<tr>
					<td class="titile">品牌型号：</td><td class="details"><form:input path="vehicleModel" readonly="true" cssStyle="width:180px" class="form-control" maxlength="50" /></td>
					<td class="titile"><span class="essential">*</span>车辆识别代号：</td><td class="details"><form:input path="VIN" readonly="true" cssStyle="width:180px" class="form-control" maxlength="50" placeholder="必填"/></td>
					<td class="titile">发动机号：</td><td class="details"><form:input path="engineNum" readonly="true" cssStyle="width:180px" class="form-control" maxlength="50" /></td>
				</tr>
				<tr>
					<td class="titile">行驶证注册日期：</td><td class="details"><form:input cssStyle="width:180px" path="registerDate" readonly="true" cssClass="form-control" /></td>
					<td class="titile">行驶证发证日期：</td><td class="details"><form:input cssStyle="width:180px" path="issueDate" readonly="true" cssClass="form-control" /></td>
					<td class="titile">档案编号：</td><td class="details"><form:input path="fileNum" readonly="true" cssStyle="width:180px" class="form-control" maxlength="50" /></td>
				</tr>
				<tr>
					<td class="titile">所有人名称：</td><td class="details"><form:input path="ownerName" readonly="true" cssStyle="width:180px" class="form-control" maxlength="50" /></td>
					<td class="titile">所有人证件类型：</td><td class="details">
										<form:select path="ownerIdType" disabled="true" cssClass="disabledRemove disabledAdd form-control m-r-5" style="width:175px">
											<form:option value="">--请选择--</form:option>
											<form:options items="${customerIDTypes}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
					<td class="titile">所有人证件号码：</td><td class="details"><form:input path="ownerIdNum" readonly="true" cssStyle="width:180px" class="form-control" maxlength="30" /></td>
				</tr>
				<tr>
					<td class="titile">所有人联系方式：</td><td class="details"><form:input path="ownerTel" readonly="true" cssStyle="width:180px" class="form-control" maxlength="20" /></td>
					<td class="titile">所有人联系地址：</td><td class="details"><form:input path="ownerAddress" readonly="true" cssStyle="width:180px" class="form-control" maxlength="100" /></td>
					<td class="titile"><span class="essential">*</span>指定联系人姓名：</td><td class="details"><form:input path="contacts" readonly="true" cssStyle="width:180px" class="form-control" maxlength="50" placeholder="必填"/></td>
				</tr>
				<tr>
					<td class="titile">整备质量(kg)：</td><td class="details"><form:input path="maintenanceMass" readonly="true" cssStyle="width:180px" class="form-control" maxlength="6" /></td>
					<td class="titile">牵引总质量(kg)：</td><td class="details"><form:input path="permittedTowWeight" readonly="true" cssStyle="width:180px" class="form-control" maxlength="6" /></td>
					<td class="titile"><span class="essentialLorry">*</span>总质量(kg)：</td><td class="details"><form:input id="totalMass" path="totalMass" readonly="true" cssStyle="width:180px" class="form-control typeParameter" maxlength="6" placeholder="货车必填"/></td>
				</tr>
				<tr>
					<td class="titile" rowspan="2">检验记录：</td><td class="details" rowspan="2"><form:textarea path="testRecord" readonly="true" style="height:70px" class="form-control" maxlength="120"/></td>
					<td class="titile">轴型：</td><td class="details"><form:input path="axisType" readonly="true" cssStyle="width:180px" class="form-control" maxlength="30" /></td>
					<td class="titile"><span class="essential">*</span>车轴数：</td><td class="details"><form:input path="axleCount" readonly="true" cssStyle="width:180px" class="form-control" maxlength="2" placeholder="必填" /></td>
				</tr>
				<tr>
					<td class="titile">轴距(mm)：</td><td class="details"><form:input path="axleDistance" readonly="true" cssStyle="width:180px" class="form-control" maxlength="5" /></td>
					<td class="titile">车轮数：</td><td class="details"><form:input path="wheelCount" readonly="true" cssStyle="width:180px" class="form-control" maxlength="3" /></td>
				</tr>
				<tr>
					<td class="titile"><span class="essential">*</span>车长(mm)：</td><td class="details"><form:input id="vehicleLength" path="vehicleLength" readonly="true" cssStyle="width:180px" class="form-control typeParameter" maxlength="6" placeholder="必填"/></td>
					<td class="titile"><span class="essential">*</span>车宽(mm)：</td><td class="details"><form:input path="vehicleWidth" readonly="true" cssStyle="width:180px" class="form-control"  maxlength="5" placeholder="必填"/></td>
					<td class="titile"><span class="essential">*</span>车高(mm)：</td><td class="details"><form:input path="vehicleHeight" readonly="true" cssStyle="width:180px" class="form-control" maxlength="5" placeholder="必填"/></td>
				</tr>
				<tr>
					<td class="titile">行驶证照片：</td><td class="details" colspan="2"><a href="${rootUrl }app/apply/emergency/vehiclemanagement/viewOne/${pageModel.id }" class="taiji_modal_lg taiji_acl btn  btn-success">查看</a></td>
					<td class="titile">车辆照片：</td><td class="details" colspan="2"><a href="${rootUrl }app/apply/emergency/vehiclemanagement/viewTwo/${pageModel.id }" class="taiji_modal_lg taiji_acl btn  btn-success">查看</a></td>
				</tr>
			</table>
			</div>
</form:form>

</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">确认删除</a>
</div>
</div>
</body>
</html>