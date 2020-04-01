<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function(){
		$.ajaxSetup({cache : false});
		$("#sure").click(function(){
			var customerId ='${pageModel1.customerId}';
			var name ='${pageModel1.customerName}';
			var passWord=$("#passWord").val();
			var newPassWord=$("#newPassWord").val();
			$.Taiji.defConfirm("确定要修改"+name+"的用户账密码吗？\n\n请确认！").done(function(){ 
				if(passWord==newPassWord){
					$.Taiji.showWarn('新密码不得与旧密码一致!');
				    return;
				}
				 	var data = {};
					data.customerId = customerId;
					data.password=passWord;
					data.newPassWord=newPassWord;
					update(data);
			});
		});
		$("#initialization").click(function(){
			var customerId ='${pageModel1.customerId}';
			var name ='${pageModel1.customerName}';
			var newPassWord=$("#newPassWord").val();
			$.Taiji.defConfirm("确定要初始化"+name+"的用户账密码吗？\n\n请确认！").done(function(){ 
				 	var data = {};
					data.customerId = customerId;
					initialization(data);
			});
		});
		
		function update(data){
			$.ajax({
				url:"update",
				data:JSON.stringify(data),
				type:"POST",
				dataType : "json",
				contentType: "application/json",
				success:function(response){
					$.Taiji.hideLoading();
					if(response.status==1){
						$("#closeBtn").click();
						$.Taiji.showNote(response.message);
						$(".taiji_search_submit").click();
					}else{
						$("#closeBtn").click();
						$.Taiji.showWarn(response.message);
					}
				}
			});
			
		}
		
		function initialization(data){
			$.ajax({
				url:"initialization",
				data:JSON.stringify(data),
				type:"POST",
				dataType : "json",
				contentType: "application/json",
				success:function(response){
					$.Taiji.hideLoading();
					if(response.status==1){
						$("#closeBtn").click();
						$.Taiji.showNote(response.message);
						$(".taiji_search_submit").click();
					}else{
						$("#closeBtn").click();
						$.Taiji.showWarn(response.message);
					}
				}
			});
			
		}
		
	});
</script>
</head>
<body>

<div class="modal-header">
	<h4 class="modal-title">用户账密码管理</h4>
	
</div>
<div class="modal-body">



 <form:form modelAttribute="pageModel1" id="myForm" name="myForm" cssClass="form-horizontal" method="post">
	<table class="table table-bordered table-striped">
		<tr>
			<th>用户编号:</th>
			<td>
				${pageModel1.customerId}
			</td>
		</tr>
		<tr>
			<th>用户名称:</th>
			<td>${pageModel1.customerName}</td>
		</tr>
		<tr>
			<th>用户旧密码:</th>
			<td >
				<input id="passWord" cssStyle="width:180px" placeholder="密码初始值为123!"  cssClass="form-control" />
			</td>
		</tr>
		<tr>
			<th>用户新密码:</th>
			<td >
				<input id="newPassWord" cssStyle="width:180px" placeholder="不得为空!"  cssClass="form-control" />
			</td>
		</tr>`
		
	</table>
</form:form>
</div>
<div class="modal-footer">
	<a id="sure" href="#" class="btn btn-sm btn-white">修改密码</a>
	<a id="initialization" href="#" class="btn btn-sm btn-white">初始化密码</a>
	<a href="#" class="btn btn-sm btn-white" id="closeBtn" data-dismiss="modal">关闭</a>
</div>
	
</body>
</html>