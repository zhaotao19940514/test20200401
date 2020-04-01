<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<%@include file="/WEB-INF/jsp/deviceInclude/obuDeviceHeader.jsp"%>
<style type="text/css">
#applyTestTable {
    text-align: center;
}
</style>
</head>
<body>
    ${loginUser }
<%--     <object id="ObuOfflineApi" name="ObuOfflineApi" classid='clsid:C941447F-91E6-4EE0-87B8-D62702FD5651' style="height:18pt;width:120;display:none" codebase="${rootUrl}obuReaderOcx/ObuApiOcx.ocx"> --%>
<!--     </object> -->
    <script type="text/javascript">
        $(function(){
        	var reader = new ObuOfflineReader();
//             console.log(reader);
//             reader.openObuDev();
//             console.log(reader.readSysInfo());
//             console.log(reader.readVehicleInfo());
            $("#testVehInfo").click(function(){
	            var reader = new ObuOfflineReader();
                reader.openObuDev();
                var vehinfo = reader.readVehicleInfo();
                console.log(vehinfo);
//                 console.log(reader.readPlateNum());
//                 console.log(reader.readPlateColor());
//                 console.log(reader.readVehicleType());
//                 console.log(reader.readUserType());
//                 console.log(reader.readVehicleLength());
//                 console.log(reader.readVehicleWidth());
//                 console.log(reader.readVehicleHeight());
//                 console.log(reader.readWheelsCount());
//                 console.log(reader.readAxlesCount());
//                 console.log(reader.readWheelBase());
//                 console.log(reader.readLoadOrSeat());
//                 console.log(reader.readVehicleFeature());
//                 console.log(reader.readEngineNum());
                reader.closeObuDev();
            });
            $("#testReadSysInfo").click(function(){
            	 var reader = new ObuOfflineReader();
                reader.openObuDev();
                var sysInfo = reader.readFullSysInfo();
                console.log(sysInfo);
                console.log(reader.readContractSerialNo());
                reader.closeObuDev();
            });
            $("#testWriteSysInfo").click(function(){
            	var reader = new ObuOfflineReader();
                reader.openObuDev();
//                 0,车辆信息,贵JZZ069,0,1,0,15,35,15,0,0,0,5,0,4LSH4260271
//                 0,系统信息,B9F3D6DD00010001,16,40,5202164300000018,20180807,20280807,00
//                 var ocx = $("#ObuOfflineApi").get(0);
//                 var info = '车辆信息,贵JZZ066,0,1,10,25,10,2,6,4,5,0,0,4LSH4260271';
//                 var result = ocx.JL_WriteVehInfo(info);
//                 reader.writeVehicleInfo(info);
//                 ocx.JL_WriteSysInfo('系统信息,B9F3D6DD00010001,16,40,5202164300000018,20180807,20280807,00');
//                 console.log(result);
                var newObuId = reader.readContractSerialNo();
                alert(newObuId);
//                 var oldInfo = reader.readFullSysInfo();
//                 var data = {};
//                 data.plateNum = "贵ZEE125";
//                 data.plateColor = "0";
//                 data.oldInfo = oldInfo;
//                 alert(oldInfo);
//                 data.newObuId = newObuId;
//                 var result = reader.writeSystemInfoWithAjax('${rootUrl}'+"app/ocx/obu/sysinfo",data,false,function(){
//                 	$.Taiji.showWarn("success!!");
//                 	reader.closeObuDev();
//                 });
                var command = "系统信息,B9F3D6DD00020001,16,10,5202181308005131,20181026,20281026,00,0000,贵ZEE125,";
                console.log(command);
                console.log(command.split(",").length);
//                 var result = reader.writeSystemInfo(command);
                var result = reader.reader.JL_WriteSysInfo(command);
                console.log(result);
                reader.closeObuDev();
            });
            $("#testWriteVehInfo").click(function(){
            	var reader = new ObuOfflineReader();
                reader.openObuDev();
                var oldInfo = reader.readFullVehicleInfo();
                // 0,车辆信息,贵A3KL70,0,1,0,0,0,0,0,0,0,5,,2AZH593143
                //车辆信息,粤S7B3Y3,0,1,1,0,0,0,4,2,0,1,东风日产牌DFL6462VBL2,030063Y
                console.log(oldInfo);
                var data = {};
                data.oldInfo = oldInfo;
                data.plateNum = '贵ZEE125';
                data.plateColor = 0;
                data.vehicleType = 1;
                data.userType = 1;
                data.vehicleLength = 0;
                data.vehicleWidth = 0;
                data.vehicleHeight = 0; 
                data.wheelsCount = 4;
                data.axleCount = 2;
                data.wheelBase = 0;
                data.loadOrSeat = 1;
                data.vehicleFeature = '2'; 
                data.engineNum = '030063Y';
                var url = '${rootUrl}'+"app/ocx/obu/vehicleinfo";
                reader.writeVehicleInfoWithAjax(url,data,true,function(){
                	$.Taiji.showWarn('test callback');
                	reader.closeObuDev();
                	console.log(reader);
                });
            });
            $("#testOpenDev").click(function(){
            	reader.openObuDev();
            });
            $("#testCloseDev").click(function(){
                reader.closeObuDev();
            });
        });
    </script>
    <div>
        <div>
            <div>
                <a id="testReadSysInfo" class="btn btn-primary btn-sm">读系统信息测试</a>
            </div>
            <div>
                <a id="testVehInfo" class="btn btn-primary btn-sm">读车辆信息测试</a>
            </div>
            <div>
                <a id="testWriteSysInfo" class="btn btn-primary btn-sm">写系统信息测试</a>
            </div>
            <div>
                <a id="testWriteVehInfo" class="btn btn-primary btn-sm">写车辆信息测试</a>
            </div>
            <div>
                <a id="testOpenDev" class="btn btn-primary btn-sm">打开设备测试</a>
            </div>
             <div>
                <a id="testCloseDev" class="btn btn-primary btn-sm">关闭设备测试</a>
            </div>
        </div>
    </div>
</body>
</html>