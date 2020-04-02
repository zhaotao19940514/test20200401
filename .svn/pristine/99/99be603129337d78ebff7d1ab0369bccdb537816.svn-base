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
		var releaseDate = $("#releaseDate").text().trim();
		var formatted = '';
		if(releaseDate!='' && releaseDate!=null && releaseDate!=undefined){
			if(releaseDate.length>=8){
				formatted += releaseDate.substring(0,4);
				formatted += '-';
				formatted += releaseDate.substring(4,6);
				formatted += '-';
				formatted += releaseDate.substring(6,8);
				$("#releaseDate").text('');
				$("#releaseDate").text(formatted);
			}
		}
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
			<th >发行渠道名称:</th>
            <td >${fn:escapeXml(staffModel.agency.name)}</td>
			<th >发行渠道编号:</th>
			<td >${fn:escapeXml(vo.agencyId)}</td>
		</tr>
		<tr>
			<th >资金渠道名称:</th>
            <td >${fn:escapeXml(staffModel.account.name)}</td>
			<th >资金渠道编号:</th>
			<td >${fn:escapeXml(vo.accountId)}</td>
		</tr>
		<tr>
		    <th >员工姓名:</th>
            <td >${fn:escapeXml(vo.staffName)}</td>
		    <th >员工编号:</th>
            <td >${fn:escapeXml(vo.staffId)}</td>
		</tr>
		<tr>
            <th >注册日期:</th>
            <td id="releaseDate">
                 ${fn:escapeXml(vo.releaseDate)}
            </td>
            <th >录入时间:</th>
            <td >
                <fmt:formatDate value="${vo.createTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
		</tr>
</table>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
</body>
</html>