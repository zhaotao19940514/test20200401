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
	<h4 class="modal-title" style="text-align: center;">合作机构信息</h4>
</div>
<div class="modal-body" id="myView" style="float: left;width: 100%;">
<table class="table table-bordered" id="viewTable">
        <tr>
            <th colspan="4" style="font-weight: bold;font-size:14px;text-align: center;">机构信息</th>
        </tr>
		<tr>
			<th >机构名称:</th>
            <td>${fn:escapeXml(vo.name)}</td>
			<th >机构编号:</th>
			<td >${fn:escapeXml(vo.agencyId)}</td>
		</tr>
		<tr>
		    <th >联系人名称:</th>
            <td >${fn:escapeXml(vo.contact)}</td>
		    <th >联系人电话:</th>
            <td >${fn:escapeXml(vo.tel)}</td>
		</tr>
		<tr>
            <th >机构类型:</th>
            <td >${fn:escapeXml(vo.type.value)}</td>
            <th >发行方Id:</th>
            <td >${fn:escapeXml(vo.issuerId)}</td>
		</tr>
		<tr>
            <th >录入时间:</th>
            <td >
                <fmt:formatDate value="${vo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
		    <th >纳税人识别号:</th>
            <td >${fn:escapeXml(vo.taxPayerCode)}</td>
        </tr>
        <tr>
            <th >统一社会信用代码:</th>
            <td colspan="3">${fn:escapeXml(vo.creditCode)}</td>
        </tr>
        <tr>
            <th>地址</th>
            <td colspan="3">${fn:escapeXml(vo.address)}</td>
        </tr>
</table>
<table class="table table-bordered">
        <tr>
            <th colspan="4" style="font-weight: bold;font-size:14px;text-align: center;">老系统兼容信息</th>
        </tr>
        <tr>
            <th >老系统接口用户名:</th>
            <td>${fn:escapeXml(vo.HGUserName)}</td>
            <th >老系统接口密码:</th>
            <td >${fn:escapeXml(vo.HGPassWord)}</td>
        </tr>
        <tr>
            <th >老系统流水编号:</th>
            <td >${fn:escapeXml(vo.serialNo)}</td>
            <th >老系统发行方编号:</th>
            <td >${fn:escapeXml(vo.issueNo)}</td>
        </tr>
        <tr>
            <th >老系统保证金编号:</th>
            <td >${fn:escapeXml(vo.packageNo)}</td>
            <th >老系统记账编号:</th>
            <td >${fn:escapeXml(vo.accountNo)}</td>
        </tr>
</table>
<table class="table table-bordered">
    <tr>
        <th colspan="4" style="font-weight: bold;font-size:14px;text-align: center;">系统设置</th>
    </tr>
     <tr>
        <th >流水目录配置:</th>
        <td colspan="3">${fn:escapeXml(vo.fileDir)}</td>
    </tr>
</table>

</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
</body>
</html>