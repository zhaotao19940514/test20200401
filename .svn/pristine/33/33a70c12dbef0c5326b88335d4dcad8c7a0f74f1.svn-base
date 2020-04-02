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
            <td >${fn:escapeXml(vo.name)}</td>
			<th >服务网点编号:</th>
			<td >${fn:escapeXml(vo.serviceHallId)}</td>
		</tr>
		<tr>
		    <th >联系人名称:</th>
            <td >${fn:escapeXml(vo.contact)}</td>
		    <th >联系人电话:</th>
            <td >${fn:escapeXml(vo.tel)}</td>
		</tr>
		<tr>
            <th >起始日期:</th>
            <td >${fn:escapeXml(vo.startTime)}</td>
            <th >终止日期:</th>
            <td >${fn:escapeXml(vo.endTime)}</td>
		</tr>
		<tr>
            <th>营业时间</th>
            <td colspan="3">${fn:escapeXml(vo.businessHours)}</td>
        </tr>
        <tr>
            <th>服务项目</th>
            <td colspan="3">${fn:escapeXml(vo.serviceItems)}</td>
        </tr>
		<tr>
            <th >机构编号:</th>
            <td >${fn:escapeXml(vo.agencyId)}</td>
            <th >录入时间:</th>
            <td>
		        <fmt:formatDate value="${vo.createTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
		    </td>
        </tr>
        <tr>
            <th >开户行:</th>
            <td >${fn:escapeXml(vo.bank)}</td>
            <th >开户行账号:</th>
            <td >${fn:escapeXml(vo.bankAccount)}</td>
        </tr>
        <tr>
            <th>开户行地址</th>
            <td colspan="3">${fn:escapeXml(vo.bankAddr)}</td>
        </tr>
        <tr>
            <th>地址</th>
            <td colspan="3">${fn:escapeXml(vo.address)}</td>
        </tr>
</table>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
</body>
</html>