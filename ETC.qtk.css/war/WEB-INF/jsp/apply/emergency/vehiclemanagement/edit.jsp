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
			$("#myManageEdit").taiji({
				enableAclCheck:true,
				search:{
					 autoSearch:true
				}
			}); 
			$("#submit").click(function(){
				//$("#myForm").trigger("submit");
// 				if(!validatePicType()){
// 					return;
// 				}
				$(".disabledRemove").removeAttr("disabled");
				$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"edit"}]);
				$(".disabledAdd").attr("disabled", true);
			});
			

			//根据总质量和车轴数动态改变收费车型
			/* $(".typeDataChange").change(function() {
				var totalMass = $("#totalMass").val();
				var axleCount = $("#axleCount").val();
				var type = $("#type");
				type.empty();
				if(totalMass != '' && axleCount != ''){
					if(axleCount == 2){
						if(totalMass < 4500){
							type.append("<option value='21'>一类专项作业车</option>"); 
						}else{
							type.append("<option value='22'>二类专项作业车</option>"); 
						}
					}else if(axleCount == 3){
						type.append("<option value='23'>三类专项作业车</option>"); 
					}else if(axleCount >= 4){
						type.append("<option value='24'>四类专项作业车</option>"); 
					}else {
						$.Taiji.showWarn("请输入正确的车轴数！");
					}
				}
			}); */
			
			/* function showRemedy() {
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
			
			//校验上传文件类型
// 			function validatePicType(){
// 				var filesA = $("#drivingLicensePic").get(0).files;
// 				var filesB = $("#vehiclePic").get(0).files;
// 				for(var i = 0;i<filesA.length;i++){
// 					var file = filesA[i];
// 					if(file.type != 'image/png' && file.type != 'image/jpg' && file.type != 'image/jpeg'){
// 						$.Taiji.showWarn("行驶证只能上传png、jpg、jpeg格式的照片！");
// 						return false;
// 					}
// 				}
// 				for(var i = 0;i<filesB.length;i++){
// 					var file = filesB[i];
// 					if(file.type != 'image/png' && file.type != 'image/jpg' && file.type != 'image/jpeg'){
// 						$.Taiji.showWarn("车辆只能上传png、jpg、jpeg格式的照片！");
// 						return false;
// 					}
// 				}
// 				return true;
// 			}
		});
		
		</script>
		
	</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">修改车辆信息</h4>
</div>

<div id="myManageEdit" class="panel panel-inverse">
<div class="modal-body">
<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/apply/emergency/vehiclemanagement/edit" method="post">
		<div class="form-group">
			<p class="caption">车辆信息</p>
			
			<table border="1">
				<tr style="display: none;">
					<td colspan="1"><form:input cssStyle="width:180px" path="customerId" class="form-control"  placeholder="必填"/></td>
					<td colspan="5"><form:input cssStyle="width:180px" path="emergencyFlag" class="form-control"  placeholder="必填"/></td>
				</tr>
				<tr>
					<td class="titile"><span class="essential">*</span>车牌号码：</td><td class="details"><form:input cssStyle="width:180px" path="vehiclePlate" id="vehiclePlate" readonly="true" class="dataChange form-control" maxlength="9" placeholder="必填"/></td>
					<td class="titile"><span class="essential">*</span>车牌颜色：</td><td class="details">
										<form:select path="vehiclePlateColor" id="vehiclePlateColor" disabled="true" cssClass="disabledRemove disabledAdd form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:option value="">--请选择--</form:option>
											<form:options items="${vehiclePlateColorTypes}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
					<td class="titile">使用性质：</td><td class="details">
										<form:select path="useCharacter"  cssClass="form-control m-r-5" data-style="btn-white" data-width="120px">
<%-- 											<form:option value="">--请选择--</form:option> --%>
											<form:options items="${vehicleUseCharacters}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
				</tr>
				<tr>
					<td class="titile"><span class="essential">*</span>客货类别：</td><td class="details">
										<form:select path="vehicleType" id="vehicleType" cssClass="form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:option value="">--请选择--</form:option>
											<form:options items="${vehicleTypeSimples}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
					<td class="titile"><span class="essentialCar">*</span>核定座位数：</td>
					<td class="details"><form:input id="approvedCount" path="approvedCount" cssStyle="width:180px" class="form-control typeParameter" maxlength="3" placeholder=""/></td>
					<td class="titile">核定载重(kg)：</td>
					<td class="details"><form:input id="permittedWeight" path="permittedWeight" cssStyle="width:180px" class="form-control" maxlength="6" placeholder=""/></td>
				</tr>
				<tr>
					<td class="titile"><span class="essential">*</span>车型：</td><td class="details">
										<form:select path="type" id="type" cssClass="form-control m-r-5" data-style="btn-white" data-width="120px">
											<c:forEach items="${vehicleTypes}" var="vehType">
												<c:if test="${pageModel.type eq vehType.typeCode}">
													<form:option value="${vehType.typeCode }">${vehType.value }</form:option>
												</c:if>
											</c:forEach>
										</form:select></td>
					<td class="titile"  >是否为冷藏运输：</td>
					<td class="details" >
							<form:select path="refrigeratedTrucks" cssClass="form-control m-r-5" data-style="btn-white" data-width="120px">
								<form:option value="">--请选择--</form:option>
								<form:options items="${carType}" itemLabel="value" itemValue="code"/>
							</form:select>
					</td>
				</tr>
				<tr>
					<td class="titile">品牌型号：</td><td class="details"><form:input path="vehicleModel" cssStyle="width:180px" class="form-control" maxlength="50" /></td>
					<td class="titile"><span class="essential">*</span>车辆识别代号：</td><td class="details"><form:input path="VIN" cssStyle="width:180px" class="form-control" maxlength="50" placeholder="必填"/></td>
					<td class="titile">发动机号：</td><td class="details"><form:input path="engineNum" cssStyle="width:180px" class="form-control" maxlength="50" /></td>
				</tr>
				<tr>
					<td class="titile">行驶证注册日期：</td><td class="details">
						<div class="form-inline">
							<form:input cssStyle="width:140px" path="registerDate"
								readonly="true" cssClass="form-control" />
							<button type="button" class="btn btn-default btn-success form-control"
								onclick="WdatePicker({el:$dp.$('registerDate'),dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'issueDate\')}'});">
								<i class="fa fa-calendar"></i></button>
						</div></td>
					<td class="titile">行驶证发证日期：</td><td class="details">
						<div class="form-inline">
							<form:input cssStyle="width:139px" path="issueDate"
								readonly="true" cssClass="form-control" />
							<button type="button" class="btn btn-default btn-success form-control"
								onclick="WdatePicker({el:$dp.$('issueDate'),dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'registerDate\')}'});">
								<i class="fa fa-calendar"></i></button>
					 	</div></td>
					<td class="titile">档案编号：</td><td class="details"><form:input path="fileNum" cssStyle="width:180px" class="form-control" maxlength="50" /></td>
				</tr>
				<tr>
					<td class="titile">所有人名称：</td><td class="details"><form:input path="ownerName" cssStyle="width:180px" class="form-control" maxlength="50" /></td>
					<td class="titile">所有人证件类型：</td><td class="details">
										<form:select path="ownerIdType"  cssClass="form-control m-r-5" style="width:175px">
											<form:option value="">--请选择--</form:option>
											<form:options items="${customerIDTypes}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
					<td class="titile">所有人证件号码：</td><td class="details"><form:input path="ownerIdNum" cssStyle="width:180px" class="form-control" maxlength="30" /></td>
				</tr>
				<tr>
					<td class="titile">所有人联系方式：</td><td class="details"><form:input path="ownerTel" cssStyle="width:180px" class="form-control" maxlength="20" /></td>
					<td class="titile">所有人联系地址：</td><td class="details"><form:input path="ownerAddress" cssStyle="width:180px" class="form-control" maxlength="100" /></td>
					<td class="titile"><span class="essential">*</span>指定联系人姓名：</td><td class="details"><form:input path="contacts" cssStyle="width:180px" class="form-control" maxlength="50" placeholder="必填"/></td>
				</tr>
				<tr>
					<td class="titile">整备质量(kg)：</td><td class="details"><form:input path="maintenanceMass" cssStyle="width:180px" class="form-control" maxlength="6" /></td>
					<td class="titile">牵引总质量(kg)：</td><td class="details"><form:input path="permittedTowWeight" cssStyle="width:180px" class="form-control" maxlength="6" /></td>
					<td class="titile"><span class="essentialLorry">*</span>总质量(kg)：</td><td class="details"><form:input id="totalMass" path="totalMass" cssStyle="width:180px" class="form-control typeParameter" maxlength="6" placeholder="必填"/></td>
				</tr>
				<tr>
					<td class="titile" rowspan="2">检验记录：</td><td class="details" rowspan="2"><form:textarea path="testRecord" style="height:70px" class="form-control" maxlength="120"/></td>
					<td class="titile">轴型：</td><td class="details"><form:input path="axisType" cssStyle="width:180px" class="form-control" maxlength="30" /></td>
					<td class="titile"><span class="essentialLorry">*</span>车轴数(含悬浮轴)：</td><td class="details"><form:input id="axleCount" path="axleCount" cssStyle="width:180px" class="form-control typeParameter" maxlength="2" min="2" placeholder="货车必填"/></td>
				</tr>
				<tr>
					<td class="titile">轴距(mm)：</td><td class="details"><form:input path="axleDistance" cssStyle="width:180px" class="form-control" maxlength="5" /></td>
					<td class="titile">车轮数：</td><td class="details"><form:input path="wheelCount" cssStyle="width:180px" class="form-control" maxlength="3" /></td>
				</tr>
				<tr>
					<td class="titile"><span class="essential">*</span>车长(mm)：</td><td class="details"><form:input id="vehicleLength" path="vehicleLength" cssStyle="width:180px" class="form-control typeParameter" maxlength="6" placeholder="必填"/></td>
					<td class="titile"><span class="essential">*</span>车宽(mm)：</td><td class="details"><form:input path="vehicleWidth" cssStyle="width:180px" class="form-control"  maxlength="5" placeholder="必填"/></td>
					<td class="titile"><span class="essential">*</span>车高(mm)：</td><td class="details"><form:input path="vehicleHeight" cssStyle="width:180px" class="form-control" maxlength="5" placeholder="必填"/></td>
				</tr>
				<tr>
					<td class="titile">行驶证照片：</td>
					<td class="details" colspan="2"><span style="float: left;"><input id="drivingLicensePic" name="drivingLicensePic" type="file" style="width: 230px" class="form-control" multiple="multiple" accept="image/png,image/jpg,image/jpeg" /></span>
						<a href="${rootUrl }app/apply/emergency/vehiclemanagement/viewOne/${pageModel.id }" class="taiji_modal_lg taiji_acl btn  btn-success">查看</a></td>
					<td class="titile">车辆照片：</td>
					<td class="details" colspan="2"><span style="float: left;"><input id="vehiclePic" name="vehiclePic" type="file" style="width: 234px" class="form-control" multiple="multiple" accept="image/png,image/jpg,image/jpeg" /></span>
						<a href="${rootUrl }app/apply/emergency/vehiclemanagement/viewTwo/${pageModel.id }" class="taiji_modal_lg taiji_acl btn  btn-success">查看</a></td>
				</tr>
				<tr>
					<td colspan="6">
						<span style="font-size: 12px;color: red;">注：只能上传png、jpg、jpeg格式的照片；车型在填入总质量、车轴数、车长后自动计算，请务必如实填写！</span>
					</td>
				</tr>
			</table>
			</div>
</form:form>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>
</div>

</body>
</html>