<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head></head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script type="text/javascript">
	var cardId = $("#cardId").val();
	
</script>

<body>
<!-- <form name="serForm" action="/css/app/customerservice/card/unloss/readFileContent" method="post" >

<input type="submit" value="解析txt入库"/>
</form>
 <h1>------------------------</h1>
 <form name="charForm" action="/css/app/customerservice/card/unloss/readReChargeFileContent" method="post" >

<input type="submit" value="解析充值流水txt入库"/>
</form>
<h1>------------------------</h1>
 <form name="ccbForm" action="/css/app/customerservice/card/unloss/readCCBCancelContent" method="post" >

<input type="submit" value="注销数据入库"/>
</form> -->
<!--  <form name="charForm" action="/css/app/customerservice/card/unloss/readLkfContent" method="post" >

<input type="submit" value="导入老系统数据"/>
</form> -->
 <!-- <form  modelAttribute="queryModel" name="cancelForm" action="/css/app/customerservice/card/unloss/deleteCancelDetail" method="post" >
<input type="text" id='cardId' name="cardId"/>
<input type="submit" value="删除注销信息"/>
</form>  -->

<!-- <form name="outProvinceForm" action="/css/app/customerservice/card/unloss/exportOutProvince" method="post" >

<input type="submit" value="导入省外数据"/>
</form> -->
<form action="${rootUrl }app/customerservice/card/cancel/fileUpload" method="post" enctype="multipart/form-data">
<input type="file" name="filename" multiple="multiple" />
<input type="submit" value="文件上传" />
</form>
</body>
</html>