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
</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">员工详情</h4>
</div>
<div class="modal-body">
	<table class="table table-bordered">
		<tr>
			<th colspan="4" style="color: gray; width: 100%; text-align: center;">员工信息</th>
		</tr>
		<tr>
			<th>员工名称:</th>
			<td>${fn:escapeXml(viewModel.name)}</td>
			<th>登录名:</th>
			<td>${fn:escapeXml(viewModel.loginName)}</td>
		</tr>
		<%-- <tr>
			<th>单位</th>
			<td colspan="3">${fn:escapeXml(viewModel.unit.name)}</td>
		</tr> --%>
		<tr>
			<th>状态:</th>
			<td>${viewModel.status.value}</td>
			<th>性别:</th>
			<td>
				<c:if test="${viewModel.male}">男</c:if>
				<c:if test="${!viewModel.male}">女</c:if>
			</td>
		</tr>
		<tr>
			<th>邮箱:</th>
			<td>${fn:escapeXml(viewModel.email)}</td>
			<th>手机:</th>
			<td>${viewModel.mobile}</td>
		</tr>
		<tr>
			<th>电话:</th>
			<td>${viewModel.tel}</td>
			<th>传真:</th>
			<td>${viewModel.fax}</td>
		</tr>
		<tr>
            <th>工号:</th>
            <td colspan="3">${viewModel.staff.staffId}</td>
        </tr>

        <tr>
            <th colspan="4" style="font-weight: bold;font-size:14px;text-align: center;">对应工号信息</th>
        </tr>
		<tr>
			<th >服务网点名称:</th>
            <td >${fn:escapeXml(viewModel.staff.serviceHall.name)}</td>
			<th >服务网点编号:</th>
			<td >${fn:escapeXml(viewModel.staff.serviceHallId)}</td>
		</tr>
		<tr>
		    <th >名称:</th>
            <td >${fn:escapeXml(viewModel.staff.staffName)}</td>
		    <th >工号:</th>
            <td >${fn:escapeXml(viewModel.staff.staffId)}</td>
		</tr>
		<tr>
            <th >注册日期:</th>
            <td id="releaseDate">
                 ${fn:escapeXml(viewModel.staff.releaseDate)}
            </td>
            <th >录入时间:</th>
            <td >
                <fmt:formatDate value="${viewModel.staff.createTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
		</tr>

	    <tr>
	        <th colspan="4" style="font-weight: bold;font-size:14px;text-align: center;">所属服务网点信息</th>
	    </tr>
		<tr>
			<th >服务网点名称:</th>
	           <td >${fn:escapeXml(viewModel.staff.serviceHall.name)}</td>
			<th >服务网点编号:</th>
			<td >${fn:escapeXml(viewModel.staff.serviceHall.serviceHallId)}</td>
		</tr>
		<tr>
		    <th >联系人名称:</th>
	           <td >${fn:escapeXml(viewModel.staff.serviceHall.contact)}</td>
		    <th >联系人电话:</th>
	           <td >${fn:escapeXml(viewModel.staff.serviceHall.tel)}</td>
		</tr>
		<tr>
	           <th >机构名称:</th>
	           <td >${fn:escapeXml(viewModel.staff.serviceHall.agencyName)}</td>
	           <th >机构编号:</th>
	           <td >${fn:escapeXml(viewModel.staff.serviceHall.agencyId)}</td>
	    </tr>
	    <tr>
	        <th >营业时间</th>
	        <td >${fn:escapeXml(viewModel.staff.serviceHall.businessHours)}</td>
	        <th>服务项目</th>
	        <td>${fn:escapeXml(viewModel.staff.serviceHall.serviceItems)}</td>
	    </tr>
	    <tr>
	        <th>地址</th>
	        <td colspan="3">${fn:escapeXml(viewModel.staff.serviceHall.address)}</td>
	    </tr>
	</table>
	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
	
</body>
</html>