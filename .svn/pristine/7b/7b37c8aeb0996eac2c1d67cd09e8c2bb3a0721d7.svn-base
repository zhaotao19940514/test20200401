<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function(){
		$.ajaxSetup({
            cache : false
        });
	});
</script>
<style type="text/css">
    viewTable td{
	    word-wrap:break-word;
	    word-break:break-all;
	    width:30%;
    }
    viewTable th{
        width:15%;
    }
</style>
</head>
<body>
<div class="modal-header" style="float: left;width: 100%;">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title" style="text-align: center;">服务网点信息</h4>
</div>
<div class="modal-body" id="myView" style="float: left;width: 100%;">
<table class="table table-bordered" id="viewTable">
        <tr>
            <th colspan="4" style="font-weight: bold;font-size:14px;text-align: center;">服务网点信息</th>
        </tr>
		<tr>
			<th >服务网点名称:</th>
            <td >${fn:escapeXml(vo.serviceHall.name)}</td>
			<th >服务网点编号:</th>
			<td >${fn:escapeXml(vo.serviceHallId)}</td>
		</tr>
		<tr>
		    <th >卡设备类型:</th>
            <td >${fn:escapeXml(vo.cardDeviceType.value)}</td>
		    <th >OBU设备类型:</th>
            <td >${fn:escapeXml(vo.obuDeviceType.value)}</td>
		</tr>
		<tr>
            <th >POS设备类型:</th>
            <td >${fn:escapeXml(vo.posDeviceType.value)}</td>
            <th >创建时间:</th>
            <td >${fn:replace(fn:escapeXml(vo.createTime),'T',' ')}</td>
		</tr>
		<tr>
            <th >更新时间:</th>
            <td colspan="3">${fn:replace(fn:escapeXml(vo.updateTime),'T',' ')}</td>
        </tr>
</table>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
</body>
</html>