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
<%-- <script type="text/javascript" src='${rootUrl }myjs/ocx/watchObuReader.js'></script> --%>
<style type="text/css">
#applyTestTable {
    text-align: center;
}
</style>
</head>
<body>
<%--     <object id="ObuOfflineApi" name="ObuOfflineApi" classid='clsid:BE1F5CF6-655F-48FC-9734-B3A1940D4B7B' style="height:18pt;width:120;display:none" codebase="${rootUrl}obuReaderOcx/watch/OBUReadOCX.ocx"> --%>
<!--     </object> -->
<%--     <object id="ObuOfflineApi" type="application/x-itst-activex" clsid="{BE1F5CF6-655F-48FC-9734-B3A1940D4B7B}" progid="${rootUrl}obuReaderOcx/watch/OBUReadOCX.ocx" height="0" width="0"></object> --%>
    <script type="text/javascript">
        $(function(){
            $("#testObuId").click(function(){
            	var reader = new ObuOfflineReader();
            	reader.openObuDev();
                console.log(reader.readContractSerialNo());
                reader.closeObuDev();
            });
            $("#testVehInfo").click(function(){
            	var reader = new ObuOfflineReader();
                reader.openObuDev();
                var vehinfo = reader.readVehicleInfo();
                console.log(reader.readPlateNum());
                console.log(reader.readPlateColor());
                console.log(reader.readVehicleType());
                console.log(reader.readUserType());
                console.log(reader.readVehicleLength());
                console.log(reader.readVehicleWidth());
                console.log(reader.readVehicleHeight());
                console.log(reader.readWheelsCount());
                console.log(reader.readAxlesCount());
                console.log(reader.readWheelBase());
                console.log(reader.readLoadOrSeat());
                console.log(reader.readVehicleFeature());
                console.log(reader.readEngineNum());
                reader.closeObuDev();
            });
            $("#testReadSysInfo").click(function(){
            	var reader = new ObuOfflineReader();
                reader.openObuDev();
                var sysInfo = reader.readSysInfo();
                console.log(sysInfo);
                console.log(reader.readContractSerialNo());
                reader.closeObuDev();
            });
            $("#testWriteSysInfo").click(function(){
            	var reader = new ObuOfflineReader();
//                 0,车辆信息,贵JZZ069,0,1,0,15,35,15,0,0,0,5,0,4LSH4260271
//                 0,系统信息,B9F3D6DD00010001,16,40,5202164300000018,20180807,20280807,00
//                 var ocx = $("#ObuOfflineApi").get(0);
//                 var info = '车辆信息,贵JZZ066,0,1,10,25,10,2,6,4,5,0,0,4LSH4260271';
//                 var result = ocx.JL_WriteVehInfo(info);
//                 reader.writeVehicleInfo(info);
//                 ocx.JL_WriteSysInfo('系统信息,B9F3D6DD00010001,16,40,5202164300000018,20180807,20280807,00');
                reader.openObuDev();
                var newObuId = reader.readContractSerialNo();
                console.log(newObuId);
                var oldInfo = reader.readFullSysInfo();
                console.log(oldInfo);
                var data = {};
                data.oldInfo = oldInfo;
                data.newObuId = newObuId;
                var result = reader.writeSystemInfoWithAjax('${rootUrl}'+"app/ocx/obu/sysinfo",data,false,function(){
                	$.Taiji.showWarn("success!!");
                	reader.closeObuDev();
                });
            });
            $("#testWriteVehInfo").click(function(){
            	var reader = new ObuOfflineReader();
                reader.openObuDev();
                var oldInfo = reader.readFullVehicleInfo();
                // 0,车辆信息,贵A3KL70,0,1,0,0,0,0,0,0,0,5,,2AZH593143
//                 var result = reader.writeVehicleInfo(oldInfo);
//                 $.Taiji.showWarn(result);
                console.log(oldInfo);
                var data = {};
                data.oldInfo = oldInfo;
                data.plateNum = '贵A1GT66';
                data.plateColor = 0;
                data.vehicleType = 1;
                data.userType = 0;
                data.vehicleLength = 0;
                data.vehicleWidth = 0;
                data.vehicleHeight = 0; 
                data.wheelsCount = 0;
                data.axleCount = 0;
                data.wheelBase = 0;
                data.loadOrSeat = 5;
                data.vehicleFeature = '1'; 
                data.engineNum = '2AZH593143';
                var url = '${rootUrl}'+"app/ocx/obu/vehicleinfo";
                reader.writeVehicleInfoWithAjax(url,data,true,function(){
                	$.Taiji.showWarn('test callback');
                	reader.closeObuDev();
                	console.log(reader);
                });
            });
            $("#testOpenDev").click(function(){
            	var reader = new ObuOfflineReader();
            	console.log(reader.openObuDev());
            });
            $("#testCloseDev").click(function(){
            	var reader = new ObuOfflineReader();
            	console.log(reader.closeObuDev());
            });
            $("#testPreActive").click(function(){
            	var reader = new ObuOfflineReader();
                console.log(reader.preActive());
            });
        });
    </script>
    <div>
        <div>
            <div>
                <a id="testObuId" class="btn btn-primary btn-sm">读obuId</a>
            </div>
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
                <a id="testPreActive" class="btn btn-primary btn-sm">预激活测试</a>
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