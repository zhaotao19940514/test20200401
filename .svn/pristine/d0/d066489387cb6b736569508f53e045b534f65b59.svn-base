<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function() {
		$.ajaxSetup ({ cache: false});
		$('.modal-footer').taiji();
		$("#submit").click(function() {
			$("#myManage").triggerHandler("taijiModalPost", [ $("#addForm"), {
				table : "add"
			} ]);
		});
	});
</script>
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">obu设备型号管理--添加</h4>
	</div>
	<div class="modal-body">
		<form:form modelAttribute="pageModel" id="addForm" name="addForm"
			cssClass="form-horizontal"
			action="${rootUrl }app/administration/inventory/devicemodel/obu/add"
			method="post">
			<div class="form-group">
				<label class="col-sm-2 control-label">产品类型</label>
				<div class="col-sm-6">
					<div class="input-group" style="width: 280px">
						<form:input path="type" required="required"
							cssClass="form-control" placeholder="产品类型必填" readonly="true" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">品牌</label>
				<div class="col-sm-6">
					<form:select path="brand" class="form-control">
						<form:option value="">请选择</form:option>
						<form:option value="1">埃特斯</form:option>
						<form:option value="2">金溢</form:option>
						<form:option value="3">聚利</form:option>
						<form:option value="4">东海</form:option>
						<form:option value="5">航天信息</form:option>
						<form:option value="6">千方</form:option>
						<form:option value="7">万集</form:option>
						<form:option value="8">中兴</form:option>
						<form:option value="9">握奇</form:option>
						<form:option value="10">搜林</form:option>
						<form:option value="11">成谷</form:option>
						<form:option value="12">云星宇</form:option>
						<form:option value="13">华虹</form:option>
						<form:option value="14">黔通电子</form:option>
						<form:option value="15">通行宝</form:option>
						<form:option value="16">赛格</form:option>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">型号</label>
				<div class="col-sm-6">
					<form:select path="model" class="form-control">
						<form:option value="蓝牙型">蓝牙型</form:option>
						<form:option value="标准型">标准型</form:option>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">备注</label>
				<div class="col-sm-6">
					<div class="input-group" style="width: 280px">
						<form:input path="remarks" required="required"
							cssClass="form-control" placeholder="如有非列举卡、标签品牌需先向部路网中心报备" />
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<div class="modal-footer">
		<a id="submit" href="javaScript:void(0);"
			class="btn btn-primary btn-sm">保存</a>
		<!--  -->
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>

</body>
</html>