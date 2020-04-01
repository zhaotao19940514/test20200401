<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%> 
<style type="text/css">
#applyTestTable {
	text-align: center;
}
</style>
<script type="text/javascript"> 
$(function(){
	$("#testDiv").taiji().on("taijiJME",function(data){
		console.log(data);
	});
});
</script>
</head>
<body>
<!--     <object name="KeeperClient" classid='clsid:7e50e44e-a583-4ff1-80de-f69438b22b63' style="height:18pt;width:120;display:none"  -->
<%--         codebase="${rootUrl}posOcx/CardMis.ocx#version=1,0,0,1"> --%>
    </object>
	<div id="testDiv">
	    <form action="${rootUrl}app/ocx/dlocx/card" method="post" >
	       <button type="submit" class="btn btn-primary btn-sm">下载读卡器驱动</button>
	    </form>
	    <form action="${rootUrl}app/ocx/dlocx/obu" method="post" >
           <button type="submit" class="btn btn-primary btn-sm">下载读签器驱动</button>
        </form>
        <form action="${rootUrl}app/ocx/dlocx/pos" method="post" >
           <button type="submit" class="btn btn-primary btn-sm">下载pos机驱动</button>
        </form>
        <form action="${rootUrl}app/ocx/mispos/configIniCreation" method="post" >
           <input name="portNum" id="portNum"/>
           <button type="submit" class="btn btn-primary btn-sm">生成pos机配置文件</button>
        </form>
	</div>
</body>
</html>