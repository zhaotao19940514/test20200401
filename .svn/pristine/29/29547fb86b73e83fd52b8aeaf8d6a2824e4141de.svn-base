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
    td{
	    word-wrap:break-word;
	    word-break:break-all;
    }
</style>
</head>
<body>
<div class="modal-header" style="float: left;width: 100%;">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title" style="text-align: center;">服务网点详情</h4>
</div>
<div class="modal-body" id="myView" style="float: left;width: 100%;">
	<table class="table table-bordered" id="viewTable">
        <tr>
            <th colspan="4" style="font-weight: bold;font-size:14px;text-align: center;">服务网点信息</th>
        </tr>
		<tr>
			<th >服务网点名称:</th>
            <td >${fn:escapeXml(serviceHall.name)}</td>
			<th >服务网点编号:</th>
			<td >${fn:escapeXml(serviceHall.serviceHallId)}</td>
		</tr>
		<tr>
		    <th >联系人名称:</th>
            <td >${fn:escapeXml(serviceHall.contact)}</td>
		    <th >联系人电话:</th>
            <td >${fn:escapeXml(serviceHall.tel)}</td>
		</tr>
		<%-- <tr>
            <th >起始日期:</th>
            <td >${fn:escapeXml(serviceHall.startTime)}</td>
            <th >终止日期:</th>
            <td >${fn:escapeXml(serviceHall.endTime)}</td>
		</tr>
		<tr>
            <th >录入时间:</th>
            <td colspan="3">
		        <fmt:formatDate value="${vo.createTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
		    </td>
        </tr> --%>
		<tr>
            <th >机构编号:</th>
            <td >${fn:escapeXml(serviceHall.agencyId)}</td>
            <th >营业时间</th>
            <td >${fn:escapeXml(serviceHall.businessHours)}</td>
        </tr>
        <tr>
            <th>服务项目</th>
            <td colspan="3">${fn:escapeXml(serviceHall.serviceItems)}</td>
        </tr>
        <tr>
            <th>地址</th>
            <td colspan="3">${fn:escapeXml(serviceHall.address)}</td>
        </tr>

        <tr>
            <th colspan="4" style="font-weight: bold;font-size:14px;text-align: center;">所属机构信息</th>
        </tr>
		<tr>
			<th >机构名称:</th>
            <td>${fn:escapeXml(agency.name)}</td>
			<th >机构编号:</th>
			<td >${fn:escapeXml(agency.agencyId)}</td>
		</tr>
		<tr>
		    <th >联系人名称:</th>
            <td >${fn:escapeXml(agency.contact)}</td>
		    <th >联系人电话:</th>
            <td >${fn:escapeXml(agency.tel)}</td>
		</tr>
		<tr>
            <th >机构类型:</th>
            <td >${fn:escapeXml(agency.type.value)}</td>
            <th >发行方Id:</th>
            <td >${fn:escapeXml(agency.issuerId)}</td>
		</tr>
        <tr>
            <th>地址</th>
            <td colspan="3">${fn:escapeXml(agency.address)}</td>
        </tr>
	</table>
	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
</body>
</html>