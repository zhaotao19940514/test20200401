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
	<h4 class="modal-title" style="text-align: center;">合作机构详情</h4>
</div>
<div class="modal-body" id="myView" style="float: left;width: 100%;">
	<table class="table table-bordered" id="viewTable">
        <tr>
            <th colspan="4" style="font-weight: bold;font-size:14px;text-align: center;">机构信息</th>
        </tr>
		<tr>
			<th >机构名称:</th>
            <td>${fn:escapeXml(viewModel.name)}</td>
			<th >机构编号:</th>
			<td >${fn:escapeXml(viewModel.agencyId)}</td>
		</tr>
		<tr>
		    <th >联系人名称:</th>
            <td >${fn:escapeXml(viewModel.contact)}</td>
		    <th >联系人电话:</th>
            <td >${fn:escapeXml(viewModel.tel)}</td>
		</tr>
		<tr>
            <th >机构类型:</th>
            <td >${fn:escapeXml(viewModel.type.value)}</td>
            <th >发行方Id:</th>
            <td >${fn:escapeXml(viewModel.issuerId)}</td>
		</tr>
        <tr>
            <th>地址</th>
            <td colspan="3">${fn:escapeXml(viewModel.address)}</td>
        </tr>
	</table>
	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
</body>
</html>