
$("#vehicleType").change(function() {
	showEssential();
	typeComputus();
});
$("#qianYing").change(function() {
	DrivingPermitChange();
});
showEssential();
DrivingPermitChange();
function DrivingPermitChange(){
	var qianYingType = $("#qianYing").val();
	if(qianYingType==0&&vehicleType!=""){
		$(".drivingPermit").hide();
	}
	if(qianYingType!=0&&vehicleType!=""){
		$(".drivingPermit").show();
	}
	if(qianYingType==""){
		$(".drivingPermit").hide();
	}
};

function showEssential() {
	var vehicleType = $("#vehicleType").val();
	if(vehicleType==0 && vehicleType!=""){
		$(".essentialLorry").hide();
		$(".essentialLo").hide();
		$(".essentialCar").show();
	}
	if(vehicleType==1){
		$(".essentialCar").hide();
		$(".essentialLorry").show();
		$(".essentialLo").show();
	}
	if(vehicleType==2){
		$(".essentialCar").hide();
		$(".essentialLorry").show();
		$(".essentialLo").hide();
	}
	if(vehicleType==""){
		$(".essentialLorry").show();
		$(".essentialCar").show();
		$(".essentialLo").hide();
	}
};
//动态计算车型
$(".typeParameter").change(function() {
	typeComputus();
});
//车型计算
function typeComputus() {
	var type = $("#type");
	type.empty();
	var vehicleType = $("#vehicleType").val();
	console.log(vehicleType);
	if(vehicleType == 0 && vehicleType != ""){
		console.log("客车");
		var vehicleLength = $("#vehicleLength").val();
		var approvedCount = $("#approvedCount").val();
		if(vehicleLength < 6000 && approvedCount <= 9)
			type.append("<option value='1' selected='selected'>一型客车</option>"); 
		if(vehicleLength < 6000 && approvedCount >=10 && approvedCount <= 19)
			type.append("<option value='2' selected='selected'>二型客车</option>");
		if(vehicleLength >= 6000 && approvedCount <= 39)
			type.append("<option value='3' selected='selected'>三型客车</option>");
		if(vehicleLength >= 6000 && approvedCount >= 40)
			type.append("<option value='4' selected='selected'>四型客车</option>");
	}
	if(vehicleType == 1){
		console.log("货车");
		var vehicleLength = $("#vehicleLength").val();
		var totalMass = $("#totalMass").val();
		var axleCount = $("#axleCount").val();
		if(axleCount == 2) {
			if(vehicleLength < 6000 && totalMass < 4500)
				type.append("<option value='11' selected='selected'>一型货车</option>");
			if(vehicleLength >= 6000 || totalMass >= 4500)
				type.append("<option value='12' selected='selected'>二型货车</option>");
		}
		if(axleCount == 3)
			type.append("<option value='13' selected='selected'>三型货车</option>");
		if(axleCount == 4)
			type.append("<option value='14' selected='selected'>四型货车</option>");
		if(axleCount == 5)
			type.append("<option value='15' selected='selected'>五型货车</option>");
		if(axleCount == 6)
			type.append("<option value='16' selected='selected'>六型货车</option>");
	}
	if(vehicleType == 2){
		console.log("专项车");
		var vehicleLength = $("#vehicleLength").val();
		var totalMass = $("#totalMass").val();
		var axleCount = $("#axleCount").val();
		if(axleCount == 2) {
			if(vehicleLength < 6000 && totalMass < 4500)
				type.append("<option value='21' selected='selected'>一型专项作业车</option>");
			if(vehicleLength >= 6000 || totalMass >= 4500)
				type.append("<option value='22' selected='selected'>二型专项作业车</option>");
		}
		if(axleCount == 3)
			type.append("<option value='23' selected='selected'>三型专项作业车</option>");
		if(axleCount == 4)
			type.append("<option value='24' selected='selected'>四型专项作业车</option>");
		if(axleCount == 5)
			type.append("<option value='25' selected='selected'>五型专项作业车</option>");
		if(axleCount >= 6)
			type.append("<option value='26' selected='selected'>六型专项作业车</option>");
	}
};